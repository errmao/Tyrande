<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-card>
      <el-form :inline="true" :model="searchForm" class="app-container-searchForm">
        <el-form-item label="参数中文名">
          <el-input v-model="searchForm.paramName" placeholder="参数中文名"></el-input>
        </el-form-item>
        <el-form-item label="参数英文名">
          <el-input v-model="searchForm.paramEnName" placeholder="参数英文名"></el-input>
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
        <el-table-column prop="paramName" label="参数中文名"/>
        <el-table-column prop="paramEnName" label="参数英文名"/>
        <el-table-column prop="paramValue" label="参数值"/>
        <el-table-column prop="paramDesc" label="描述"/>
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
          <el-form-item label="参数中文名" prop="paramName">
            <el-input v-model="viewForm.paramName"/>
          </el-form-item>
          <el-form-item label="参数英文名" prop="paramEnName">
            <el-input v-model="viewForm.paramEnName"/>
          </el-form-item>
          <el-form-item label="参数值" prop="paramValue">
            <el-input v-model="viewForm.paramValue"/>
          </el-form-item>
          <el-form-item label="描述" prop="paramDesc">
            <el-input v-model="viewForm.paramDesc"/>
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button
          @click="visibleConfig.view = false">{{defaultSettings.closeButtonText}}</el-button>
      </span>
    </el-dialog>

    <!-- 添加 -->
    <el-dialog :title="defaultSettings.btnAdd" :visible.sync="visibleConfig.add" width="50%"
               @close="doAddClose">
      <span>
         <el-form :model="addForm" :rules="checkRules" ref="addFormRef" label-width="120px">
          <el-form-item label="参数中文名" prop="paramName">
            <el-input v-model="addForm.paramName"></el-input>
          </el-form-item>
          <el-form-item label="参数英文名" prop="paramEnName">
            <el-input v-model="addForm.paramEnName"></el-input>
          </el-form-item>
          <el-form-item label="参数值" prop="paramValue">
            <el-input v-model="addForm.paramValue"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="paramDesc">
            <el-input v-model="addForm.paramDesc"></el-input>
          </el-form-item>
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
          <el-form-item label="参数中文名" prop="paramName">
            <el-input v-model="editForm.paramName"></el-input>
          </el-form-item>
          <el-form-item label="参数英文名" prop="paramEnName">
            <el-input v-model="editForm.paramEnName"></el-input>
          </el-form-item>
          <el-form-item label="参数值" prop="paramValue">
            <el-input v-model="editForm.paramValue"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="paramDesc">
            <el-input v-model="editForm.paramDesc"></el-input>
          </el-form-item>
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
  } from "@/api/system/sysparams/SysParams";
  import {isNumberAndSe} from "@/utils/validate";

  export default {
    name: 'SysParam',
    data() {
      return {
        defaultSettings: defaultSettings,

        // 查询参数对象
        searchForm: {
          current: 1,
          paramName: '',
          paramEnName: ''
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
          paramName: [
            {required: true, message: '请输入参数中文名', trigger: 'blur'},
            {min: 1, max: 20, message: "请输入1-20个字符的参数中文名", trigger: 'blur'}],
          paramEnName: [
            {required: true, message: '请输入参数英文名', trigger: 'blur'},
            {min: 1, max: 50, message: "请输入1-50个字符或数字", trigger: 'blur'},
            {validator: isNumberAndSe, trigger: 'blur'}],
          paramValue: [{required: true, message: '请输入参数值', trigger: 'blur'}],
          paramDesc: [{max: 200, message: "描述不能超过200个字符", trigger: 'blur'}]
        },

        // 查看数据
        viewForm: {},

        // 添加对话框数据
        addForm: {},

        // 编辑对话框数据
        editForm: {}
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
