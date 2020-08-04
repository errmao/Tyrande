<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-card>
      <el-form :inline="true" :model="searchForm" class="app-container-searchForm">
        <el-form-item label="字典名称">
          <el-input v-model="searchForm.dictName" placeholder="字典名称" />
        </el-form-item>
        <el-form-item label="字典项">
          <el-input v-model="searchForm.dictKey" placeholder="字典项" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getPageList">
            {{ defaultSettings.btnSearch }}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-row :gutter="20">
      <!-- 字典项表格 -->
      <el-col :span="11">
        <el-card class="dict_key-class">
          <el-row class="app-container-toolbar">
            <el-button
              type="primary"
              icon="el-icon-circle-plus-outline"
              @click="visibleConfig.add = true"
            >
              {{ defaultSettings.btnAdd }}
            </el-button>
          </el-row>
          <el-table
            ref="dictKeyTable"
            border
            :data="gridData.list"
            highlight-current-row
            @current-change="handleRowChange"
            @row-click="dictKeyRowClick"
          >
            <el-table-column type="index" label="序号" width="50" />
            <el-table-column prop="dictName" label="字典名称" />
            <el-table-column prop="dictKey" label="字典项" />
            <el-table-column prop="dictDesc" label="字典描述" />
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="scope">
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
      </el-col>

      <!-- 字典值表格 -->
      <el-col :span="13">
        <el-card class="dict-value-class">
          <el-row class="app-container-toolbar">
            <el-button
              type="primary"
              icon="el-icon-circle-plus-outline"
              @click="dictValueAdd"
            >
              {{ defaultSettings.btnAdd }}
            </el-button>
          </el-row>
          <el-table border :data="gridData_value.list" max-height="640">
            <el-table-column type="index" label="序号" width="50" />
            <el-table-column prop="dictRealValue" label="真实值" />
            <el-table-column prop="dictValue" label="展示值" />
            <el-table-column prop="dictOrder" label="字典顺序" />
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="scope">
                <el-button
                  type="text"
                  icon="el-icon-edit"
                  size="small"
                  @click="doValueEdit(scope.row.id)"
                >
                  {{ defaultSettings.btnEdit }}
                </el-button>
                <el-button
                  type="text"
                  icon="el-icon-delete"
                  size="small"
                  @click="doValueDelete(scope.row.id)"
                >{{ defaultSettings.btnDelete }}
                </el-button>
              </template>
            </el-table-column>
          </el-table>

        </el-card>
      </el-col>
    </el-row>

    <!-- 字典项添加 -->
    <el-dialog
      :title="defaultSettings.btnAdd"
      :visible.sync="visibleConfig.add"
      width="50%"
      @close="doAddClose"
    >
      <span>
        <el-form ref="addFormRef" :model="addForm" :rules="addCheckRules" label-width="120px">
          <el-form-item label="字典名称" prop="dictName">
            <el-input v-model="addForm.dictName" />
          </el-form-item>
          <el-form-item label="字典项" prop="dictKey">
            <el-input v-model="addForm.dictKey" />
          </el-form-item>
          <el-form-item label="字典描述" prop="dictDesc">
            <el-input v-model="addForm.dictDesc" />
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

    <!-- 字典项编辑 -->
    <el-dialog
      :title="defaultSettings.btnEdit"
      :visible.sync="visibleConfig.edit"
      width="50%"
      @close="doEditClose"
    >
      <span>
        <el-form ref="editFormRef" :model="editForm" :rules="editCheckRules" label-width="120px">
          <el-form-item label="字典名称" prop="dictName">
            <el-input v-model="editForm.dictName" />
          </el-form-item>
          <el-form-item label="字典项" prop="dictKey">
            <el-input v-model="editForm.dictKey" disabled />
          </el-form-item>
          <el-form-item label="字典描述" prop="dictDesc">
            <el-input v-model="editForm.dictDesc" />
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

    <!-- 字典值添加 -->
    <el-dialog
      :title="defaultSettings.btnAdd"
      :visible.sync="visibleConfig_value.add"
      width="50%"
      @close="doValueAddClose"
    >
      <span>
        <el-form ref="addValueFormRef" :model="addValueForm" :rules="checkValueRules" label-width="120px">
          <el-form-item label="真实值" prop="dictRealValue">
            <el-input v-model="addValueForm.dictRealValue" />
          </el-form-item>
          <el-form-item label="展示值" prop="dictValue">
            <el-input v-model="addValueForm.dictValue" />
          </el-form-item>
          <el-form-item label="字典顺序" prop="dictOrder">
            <el-input-number v-model="addValueForm.dictOrder" controls-position="right" :min="1" :max="100" />
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button
          @click="visibleConfig_value.add = false"
        >{{ defaultSettings.cancelButtonText }}</el-button>
        <el-button
          type="primary"
          @click="doValueAddSave"
        >{{ defaultSettings.confirmButtonText }}</el-button>
      </span>
    </el-dialog>

    <!-- 字典值编辑 -->
    <el-dialog
      :title="defaultSettings.btnEdit"
      :visible.sync="visibleConfig_value.edit"
      width="50%"
      @close="doValueEditClose"
    >
      <span>
        <el-form ref="editValueFormRef" :model="editValueForm" :rules="checkValueRules" label-width="120px">
          <el-form-item label="真实值" prop="dictRealValue">
            <el-input v-model="editValueForm.dictRealValue" />
          </el-form-item>
          <el-form-item label="展示值" prop="dictValue">
            <el-input v-model="editValueForm.dictValue" />
          </el-form-item>
          <el-form-item label="字典顺序" prop="dictOrder">
            <el-input-number v-model="editValueForm.dictOrder" controls-position="right" :min="1" :max="100" />
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button
          @click="visibleConfig_value.edit = false"
        >{{ defaultSettings.cancelButtonText }}</el-button>
        <el-button
          type="primary"
          @click="doValueEditSave"
        >{{ defaultSettings.confirmButtonText }}</el-button>
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
  doValueAddSave,
  doValueDelete,
  doView,
  getPageList,
  getValuePageList,
  doValueEditSave,
  doValueView
} from '@/api/system/sysdictkey/SysDictKey'
import { dictKeyValidate } from '@/utils/validate'

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

      // 当前选中行
      currentRow: null,

      // 对话框是否展示配置
      visibleConfig: {
        add: false,
        edit: false
      },

      // 添加或编辑对话框校验规则
      addCheckRules: {
        dictKey: [{ required: true, message: '请输入字典项', trigger: 'blur' },
          { validator: dictKeyValidate, trigger: 'blur' }],
        dictName: [{ required: true, message: '请输入字典名称', trigger: 'blur' }],
        dictDesc: [{ required: true, message: '请输入字典描述', trigger: 'blur' }]
      },
      editCheckRules: {
        dictKey: [{ required: true, message: '请输入字典项', trigger: 'blur' }],
        dictName: [{ required: true, message: '请输入字典名称', trigger: 'blur' }],
        dictDesc: [{ required: true, message: '请输入字典描述', trigger: 'blur' }]
      },

      // 查看数据
      viewForm: {},

      // 添加对话框数据
      addForm: {},

      // 编辑对话框数据
      editForm: {},

      /**
         * 字典值参数
         */
      searchForm_value: {
        current: 1,
        size: -1,
        dictKeyId: ''
      },
      gridData_value: {
        list: []
      },
      visibleConfig_value: {
        add: false,
        edit: false
      },
      addValueForm: {},
      editValueForm: {},
      checkValueRules: {
        dictValue: [{ required: true, message: '请输入展示值', trigger: 'blur' }],
        dictRealValue: [{ required: true, message: '请输入真实值', trigger: 'blur' }],
        dictOrder: [{ required: true, message: '请输入字典顺序', trigger: 'blur' }]
      }
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

        // 搜索时清除字典值表
        this.searchForm_value.dictKeyId = ''
        this.gridData_value.list = []
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
            this.getPageList()
          })
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
    },

    // 字典项表格行点击事件
    dictKeyRowClick(row) {
      // 查询字典值表
      this.searchForm_value.dictKeyId = row.id
      this.getValuePageList()
    },

    // 当前行改变
    handleRowChange(currentRow) {
      this.currentRow = currentRow
    },

    /**
       *字典值表方法
       */
    getValuePageList() {
      getValuePageList(this.searchForm_value).then((res) => {
        const { records } = res.data
        this.gridData_value.list = records
      }).catch(() => {
        this.$message.error(defaultSettings.failureSearch)
      })
    },

    // 字典值添加按钮点击
    dictValueAdd() {
      if (this.currentRow == null) {
        this.$message.error('请先选择字典项')
        return
      }
      this.addValueForm.dictKeyId = this.currentRow.id
      this.visibleConfig_value.add = true
    },

    // 字典值添加-关闭
    doValueAddClose() {
      this.$refs.addValueFormRef.resetFields()
    },
    // 字典值保存
    doValueAddSave() {
      this.$refs.addValueFormRef.validate(async valid => {
        if (!valid) {
          return
        }
        await doValueAddSave(this.addValueForm)
        this.$message.success(defaultSettings.successAdd)
        this.visibleConfig_value.add = false
        this.getValuePageList()
      })
    },

    // 字典值删除
    doValueDelete(id) {
      const rowId = id
      this.$confirm(defaultSettings.deleteText, defaultSettings.typeInfoText, {
        confirmButtonText: defaultSettings.confirmButtonText,
        cancelButtonText: defaultSettings.cancelButtonText,
        type: defaultSettings.typeWarning
      }).then(
        async() => {
          doValueDelete(rowId).then(() => {
            this.$message.success(defaultSettings.successDelete)
            this.getValuePageList()
          })
        })
    },
    // 编辑
    async doValueEdit(id) {
      const res = await doValueView(id)
      this.editValueForm = res.data
      this.visibleConfig_value.edit = true
    },
    // 编辑-保存
    doValueEditSave() {
      this.$refs.editValueFormRef.validate(async valid => {
        if (!valid) {
          return
        }
        await doValueEditSave(this.editValueForm)
        this.$message.success(defaultSettings.successEdit)
        this.visibleConfig_value.edit = false
        this.getValuePageList()
      })
    },

    // 编辑-关闭
    doValueEditClose() {
      this.$refs.editValueFormRef.resetFields()
    }
  }
}
</script>

<style lang='scss' scoped>
  .dict_key-class {
    margin-right: -5px;
  }

  .dict-value-class {
    margin-left: -5px;
  }
</style>
