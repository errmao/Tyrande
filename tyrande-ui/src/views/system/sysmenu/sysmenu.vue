<template>
  <div class="app-container">
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
      <el-table
        border
        :data="gridData.list"
        row-key="id"
        :tree-props="{children: 'sub'}"
      >
        <el-table-column prop="id" label="菜单编号" />
        <el-table-column prop="menuName" label="菜单名称" />
        <el-table-column prop="menuLevel" label="菜单层级">
          <template slot-scope="scope">
            <el-tag
              plain
              size="mini"
              :type="(scope.row.menuLevel=='1' ? 'success' : (scope.row.menuLevel=='2'? 'warning': scope.row.menuLevel=='3'?'danger':'info'))"
            >
              {{ formatMenuLevel(scope.row.menuLevel) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="menuUrl" label="请求路径" />
        <el-table-column fixed="right" label="操作" width="180">
          <template slot-scope="scope">
            <el-button
              type="text"
              icon="el-icon-view"
              size="small"
              @click="doView(scope.row.id)"
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
    </el-card>

    <!-- 查看 -->
    <el-dialog :title="defaultSettings.btnView" :visible.sync="visibleConfig.view" width="50%">
      <span>
        <el-form :model="viewForm" label-width="120px" disabled>
          <el-form-item label="菜单名称">
            <el-input v-model="viewForm.menuName" />
          </el-form-item>
          <el-form-item label="菜单层级" prop="menuLevel">
            <el-select v-model="viewForm.menuLevel">
              <option-dict :dict="defaultSettings.dict.menuLevel" />
            </el-select>
          </el-form-item>
          <el-form-item label="上级菜单" prop="pid">
            <el-cascader
              :key="viewForm.id"
              v-model="viewForm.pid"
              :options="viewPidData"
              :props="{
                emitPath:false,
                expandTrigger: 'hover',
                children:'sub',
                label:'menuName',
                value:'id'}"
            />
          </el-form-item>
          <el-form-item label="请求路径">
            <el-input v-model="viewForm.menuUrl" />
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
          <el-form-item label="菜单名称" prop="menuName">
            <el-input v-model="addForm.menuName" />
          </el-form-item>
          <el-form-item label="菜单层级" prop="menuLevel">
            <el-select v-model="addForm.menuLevel" @change="menuLevelChangeForAdd">
              <option-dict :dict="defaultSettings.dict.menuLevel" />
            </el-select>
          </el-form-item>
          <el-form-item label="上级菜单" prop="pid">
            <el-cascader
              :key="addForm.pid"
              v-model="addForm.pid"
              :options="addPidData"
              :props="{
                emitPath:false,
                expandTrigger: 'hover',
                children:'sub',
                label:'menuName',
                value:'id'}"
            />
          </el-form-item>
          <el-form-item label="请求路径" prop="menuUrl">
            <el-input v-model="addForm.menuUrl" />
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
          <el-form-item label="菜单名称" prop="menuName">
            <el-input v-model="editForm.menuName" />
          </el-form-item>
          <el-form-item label="菜单层级" prop="menuLevel">
            <el-select v-model="editForm.menuLevel" @change="menuLevelChangeForEdit">
              <option-dict :dict="defaultSettings.dict.menuLevel" />
            </el-select>
          </el-form-item>
          <el-form-item label="上级菜单" prop="pid">
            <el-cascader
              :key="editForm.id"
              v-model="editForm.pid"
              :options="editPidData"
              :props="{
                emitPath:false,
                expandTrigger: 'hover',
                children:'sub',
                label:'menuName',
                value:'id'}"
            />
          </el-form-item>
          <el-form-item label="请求路径" prop="menuUrl">
            <el-input v-model="editForm.menuUrl" />
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
import defaultSettings from '@/settings'
import { doAddSave, doDelete, doEditSave, doView, getMenuByLevel, getPageList } from '@/api/system/sysmenu/SysMenu'

export default {
  name: 'SysMenu',
  data() {
    return {
      defaultSettings: defaultSettings,

      // 列表数据
      gridData: {
        list: []
      },

      // 对话框是否展示配置
      visibleConfig: {
        add: false,
        edit: false,
        view: false
      },

      // 添加或编辑对话框校验规则
      checkRules: {
        menuName: [{ required: true, message: '菜单名称不能为空', trigger: 'blur' }],
        menuLevel: [{ required: true, message: '菜单层级不能为空', trigger: 'blur' }],
        pid: [{ required: true, message: '上级菜单不能为空', trigger: 'blur' }],
        menuUrl: [{ required: true, message: '菜单路径不能为空', trigger: 'blur' }]
      },

      // 查看数据
      viewForm: {},

      // 添加对话框数据
      addForm: {},
      addPidData: [],

      // 编辑对话框数据
      editForm: {},
      editPidData: [],

      viewPidData: []
    }
  },
  created() {
    this.$getDict(defaultSettings.dict.menuLevel)
    this.menuLevelChangeForAdd(1)
    this.getPageList()
  },
  methods: {
    // 列表查询
    getPageList() {
      getPageList().then((res) => {
        this.gridData.list = res.data
      }).catch(() => {
        this.$message.error(defaultSettings.failureSearch)
      })
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

    async doView(id) {
      const res = await doView(id)
      await this.menuLevelChangeForView(res.data.menuLevel)
      this.viewForm = res.data
      this.viewForm.menuLevel = this.viewForm.menuLevel + ''
      this.visibleConfig.view = true
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
      await this.menuLevelChangeForEdit(res.data.menuLevel)
      this.editForm = res.data
      this.editForm.menuLevel = this.editForm.menuLevel + ''
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
    formatMenuLevel(cellValue) {
      return this.$formatDict(cellValue, defaultSettings.dict.menuLevel)
    },

    // 新增对话框菜单层级事件
    async menuLevelChangeForAdd(newValue) {
      const { data } = await getMenuByLevel(newValue)
      this.addPidData = data
      this.addForm.pid = null
    },

    async menuLevelChangeForEdit(newValue) {
      const { data } = await getMenuByLevel(newValue)
      this.editPidData = data
      this.editForm.pid = null
    },
    async menuLevelChangeForView(newValue) {
      const { data } = await getMenuByLevel(newValue)
      this.viewPidData = data
    }

  }
}
</script>

<style lang='scss' scoped>

</style>
