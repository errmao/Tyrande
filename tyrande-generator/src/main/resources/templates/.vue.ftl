<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-card>
      <el-form :inline="true" :model="searchForm" class="app-container-searchForm">
        <el-form-item label="主键">
          <el-input v-model="searchForm.id" placeholder="主键"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getPageList">
            {{defaultSettings.btnSearch}}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 数据列表 -->
    <el-card>
      <el-row class="app-container-toolbar">
        <el-button type="primary" icon="el-icon-circle-plus-outline"
                   @click="visibleConfig.add = true">
          {{defaultSettings.btnAdd}}
        </el-button>
      </el-row>

      <!-- 数据表格 -->
      <el-table border :data="gridData.list">
        <el-table-column type="index" label="序号" width="50"/>
        <#list columns as column>
        <el-table-column prop="${column.javaField}" label="${column.columnComment}"/>
        </#list>d
        <el-table-column fixed="right" label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-view" size="small"
                       @click="visibleConfig.view =true, viewForm = scope.row">
              {{defaultSettings.btnView}}
            </el-button>
            <el-button type="text" icon="el-icon-edit" size="small"
                       @click="doEdit(scope.row.id)">
              {{defaultSettings.btnEdit}}
            </el-button>
            <el-button type="text" icon="el-icon-delete" size="small"
                       @click="doDelete(scope.row.id)">{{defaultSettings.btnDelete}}
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区域 -->
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="searchForm.current"
        :layout="defaultSettings.page"
        :total="gridData.total">
      </el-pagination>
    </el-card>

    <!-- 查看 -->
    <el-dialog :title="defaultSettings.btnView" :visible.sync="visibleConfig.view" width="50%">
      <span>
        <el-form :model="viewForm" label-width="120px" disabled>
         <#list columns as column>
          <el-form-item label="${column.columnComment}" prop="${column.javaField}">
            <el-input v-model="viewForm.${column.javaField}"/>
          </el-form-item>
         </#list>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="visibleConfig.view = false">{{defaultSettings.closeButtonText}}</el-button>
      </span>
    </el-dialog>

    <!-- 添加 -->
    <el-dialog :title="defaultSettings.btnAdd" :visible.sync="visibleConfig.add" width="50%"
               @close="doAddClose">
      <span>
        <el-form :model="addForm" :rules="checkRules" ref="addFormRef" label-width="120px">
          <#list columns as column>
          <el-form-item label="${column.columnComment}" prop="${column.javaField}">
            <el-input v-model="addForm.${column.javaField}"></el-input>
          </el-form-item>
          </#list>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button
          @click="visibleConfig.add = false">{{defaultSettings.cancelButtonText}}</el-button>
        <el-button type="primary"
                   @click="doAddSave">{{defaultSettings.confirmButtonText}}</el-button>
      </span>
    </el-dialog>

    <!-- 编辑 -->
    <el-dialog :title="defaultSettings.btnEdit" :visible.sync="visibleConfig.edit" width="50%"
               @close="doEditClose">
    <span>
      <el-form :model="editForm" :rules="checkRules" ref="editFormRef" label-width="120px">
        <#list columns as column>
        <el-form-item label="${column.columnComment}" prop="${column.javaField}">
         <el-input v-model="editForm.${column.javaField}"></el-input>
        </el-form-item>
        </#list>
      </el-form>
    </span>
    <span slot="footer" class="dialog-footer">
      <el-button
        @click="visibleConfig.edit = false">{{defaultSettings.cancelButtonText}}</el-button>
      <el-button type="primary"
                 @click="doEditSave">{{defaultSettings.confirmButtonText}}</el-button>
    </span>
    </el-dialog>

  </div>
</template>

<script>
  import defaultSettings from '@/settings'
  import {
    doAddSave,
    doDelete,
    doEditSave,
    doView,
    getPageList
  } from "@/api/${moduleName}/${classNameLower}/${className}";

  export default {
    name: '${className}',
    data() {
      return {
        defaultSettings: defaultSettings,

        // 查询参数对象
        searchForm: {
          current: 1
        },
        // 列表数据
        gridData: {
          list: [],
          total: 0
        },

        // 对话框是否展示配置
        visibleConfig: {
          add: false,
          edit: false,
          view: false
        },

        // 添加或编辑对话框校验规则
        checkRules: {
        <#list columns as column >
          ${column.javaField}: [{required: true, message: '请输入${column.columnComment}', trigger: 'blur'}],
        </#list>
        },

        // 查看数据
        {},

        // 添加对话框数据
        {},

        // 编辑对话框数据
        {}
      }
    },
    created() {
      this.getPageList()
    },
    methods: {
      // 列表查询
      getPageList() {
        getPageList(this.searchForm).then((res) => {
          const {records, total} = res.data;
          this.gridData.list = records;
          this.gridData.total = total;
        }).catch(() => {
          this.$message.error(defaultSettings.failureSearch);
        })
      },

      // 分页栏页数改变监听
      handleCurrentChange(newCurrent) {
        this.searchForm.current = newCurrent
        this.getPageList()
      },

      // 删除
      doDelete(id) {
        const rowId = id;
        this.$confirm(defaultSettings.deleteText, defaultSettings.typeInfoText, {
          confirmButtonText: defaultSettings.confirmButtonText,
          cancelButtonText: defaultSettings.cancelButtonText,
          type: defaultSettings.typeWarning
        }).then(
          async () => {
            doDelete(rowId).then(() => {
              this.$message.success(defaultSettings.successDelete);
            })
            this.getPageList()
          });
      },

      // 添加-保存
      doAddSave() {
        this.$refs.addFormRef.validate(async valid => {
          if (!valid) {
            return;
          }
          await doAddSave(this.addForm);
          this.$message.success(defaultSettings.successAdd)
          this.visibleConfig.add = false;
          this.getPageList();
        })
      },
      // 添加-关闭
      doAddClose() {
        this.$refs.addFormRef.resetFields()
      },

      // 编辑
      async doEdit(id) {
        const res = await doView(id);
        this.editForm = res.data;
        this.visibleConfig.edit = true;
      },

      // 编辑-保存
      doEditSave() {
        this.$refs.editFormRef.validate(async valid => {
          if (!valid) {
            return;
          }
          await doEditSave(this.editForm);
          this.$message.success(defaultSettings.successEdit)
          this.visibleConfig.edit = false;
          this.getPageList();
        })
      },

      // 编辑-关闭
      doEditClose() {
        this.$refs.editFormRef.resetFields()
      }
    }
  }
</script>

<style lang='scss' scoped>

</style>
