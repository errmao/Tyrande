<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-card>
      <el-form :inline="true" :model="searchForm" class="app-container-searchForm">
        <el-form-item label="主键">
          <el-input v-model="searchForm.id" placeholder="主键" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getPageList">
            {{ defaultSettings.btnSearch }}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 数据列表 -->
    <el-card>
      <el-row class="app-container-toolbar">
        <el-button
          type="primary"
          icon="el-icon-circle-plus-outline"
          @click="visibleConfig.add = true"
        >
          {{ defaultSettings.btnAdd }}
        </el-button>
      </el-row>

      <!-- 数据表格 -->
      <el-table border :data="gridData.list">
        <el-table-column type="index" label="序号" width="50" />
        <el-table-column prop="dictKey" label="字典项" />
        <el-table-column prop="dictName" label="字典名" />
        <el-table-column prop="dictDesc" label="字典描述" />
        <el-table-column prop="dictStatus" label="字典状态（1-启用 2-停用）" />
        d
        <el-table-column fixed="right" label="操作" width="180">
          <template slot-scope="scope">
            <el-button
              type="text"
              icon="el-icon-view"
              size="small"
              @click="visibleConfig.view =true, viewForm = scope.row"
            >
              {{ defaultSettings.btnView }}
            </el-button>
            <el-button
              type="text"
              icon="el-icon-edit"
              size="small"
              @click="doEdit(scope.row.id)"
            >
              {{ defaultSettings.btnEdit }}
            </el-button>
            <el-button
              type="text"
              icon="el-icon-delete"
              size="small"
              @click="doDelete(scope.row.id)"
            >{{ defaultSettings.btnDelete }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区域 -->
      <el-pagination
        :current-page="searchForm.current"
        :layout="defaultSettings.page"
        :total="gridData.total"
        @current-change="handleCurrentChange"
      />
    </el-card>

    <!-- 查看 -->
    <el-dialog :title="defaultSettings.btnView" :visible.sync="visibleConfig.view" width="50%">
      <span>
        <el-form :model="viewForm" label-width="120px" disabled>
          <el-form-item label="字典项" prop="dictKey">
            <el-input v-model="viewForm.dictKey" />
          </el-form-item>
          <el-form-item label="字典名" prop="dictName">
            <el-input v-model="viewForm.dictName" />
          </el-form-item>
          <el-form-item label="字典描述" prop="dictDesc">
            <el-input v-model="viewForm.dictDesc" />
          </el-form-item>
          <el-form-item label="字典状态（1-启用 2-停用）" prop="dictStatus">
            <el-input v-model="viewForm.dictStatus" />
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="visibleConfig.view = false">{{ defaultSettings.closeButtonText }}</el-button>
      </span>
    </el-dialog>

    <!-- 添加 -->
    <el-dialog
      :title="defaultSettings.btnAdd"
      :visible.sync="visibleConfig.add"
      width="50%"
      @close="doAddClose"
    >
      <span>
        <el-form ref="addFormRef" :model="addForm" :rules="checkRules" label-width="120px">
          <el-form-item label="字典项" prop="dictKey">
            <el-input v-model="addForm.dictKey" />
          </el-form-item>
          <el-form-item label="字典名" prop="dictName">
            <el-input v-model="addForm.dictName" />
          </el-form-item>
          <el-form-item label="字典描述" prop="dictDesc">
            <el-input v-model="addForm.dictDesc" />
          </el-form-item>
          <el-form-item label="字典状态（1-启用 2-停用）" prop="dictStatus">
            <el-input v-model="addForm.dictStatus" />
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button
          @click="visibleConfig.add = false"
        >{{ defaultSettings.cancelButtonText }}</el-button>
        <el-button
          type="primary"
          @click="doAddSave"
        >{{ defaultSettings.confirmButtonText }}</el-button>
      </span>
    </el-dialog>

    <!-- 编辑 -->
    <el-dialog
      :title="defaultSettings.btnEdit"
      :visible.sync="visibleConfig.edit"
      width="50%"
      @close="doEditClose"
    >
      <span>
        <el-form ref="editFormRef" :model="editForm" :rules="checkRules" label-width="120px">
          <el-form-item label="字典项" prop="dictKey">
            <el-input v-model="editForm.dictKey" />
          </el-form-item>
          <el-form-item label="字典名" prop="dictName">
            <el-input v-model="editForm.dictName" />
          </el-form-item>
          <el-form-item label="字典描述" prop="dictDesc">
            <el-input v-model="editForm.dictDesc" />
          </el-form-item>
          <el-form-item label="字典状态（1-启用 2-停用）" prop="dictStatus">
            <el-input v-model="editForm.dictStatus" />
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button
          @click="visibleConfig.edit = false"
        >{{ defaultSettings.cancelButtonText }}</el-button>
        <el-button
          type="primary"
          @click="doEditSave"
        >{{ defaultSettings.confirmButtonText }}</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import defaultSettings from '../tyrande-ui/src/settings'
import { doAddSave, doDelete, doEditSave, doView, getPageList } from 'templates/.vuesystem/sysdictkey/SysDictKey'

export default {
  name: 'SysDictKey',
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
        dictKey: [{ required: true, message: '请输入字典项', trigger: 'blur' }],
        dictName: [{ required: true, message: '请输入字典名', trigger: 'blur' }],
        dictDesc: [{ required: true, message: '请输入字典描述', trigger: 'blur' }],
        dictStatus: [{ required: true, message: '请输入字典状态（1-启用 2-停用）', trigger: 'blur' }]
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
        const { records, total } = res.data
        this.gridData.list = records
        this.gridData.total = total
      }).catch(() => {
        this.$message.error(defaultSettings.failureSearch)
      })
    },

    // 分页栏页数改变监听
    handleCurrentChange(newCurrent) {
      this.searchForm.current = newCurrent
      this.getPageList()
    },

    // 删除
    doDelete(id) {
      const rowId = id
      this.$confirm(defaultSettings.deleteText, defaultSettings.typeInfoText, {
        confirmButtonText: defaultSettings.confirmButtonText,
        cancelButtonText: defaultSettings.cancelButtonText,
        type: defaultSettings.typeWarning
      }).then(
        async() => {
          doDelete(rowId).then(() => {
            this.$message.success(defaultSettings.successDelete)
          })
          this.getPageList()
        })
    },

    // 添加-保存
    doAddSave() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) {
          return
        }
        await doAddSave(this.addForm)
        this.$message.success(defaultSettings.successAdd)
        this.visibleConfig.add = false
        this.getPageList()
      })
    },
    // 添加-关闭
    doAddClose() {
      this.$refs.addFormRef.resetFields()
    },

    // 编辑
    async doEdit(id) {
      const res = await doView(id)
      this.editForm = res.data
      this.visibleConfig.edit = true
    },

    // 编辑-保存
    doEditSave() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) {
          return
        }
        await doEditSave(this.editForm)
        this.$message.success(defaultSettings.successEdit)
        this.visibleConfig.edit = false
        this.getPageList()
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
