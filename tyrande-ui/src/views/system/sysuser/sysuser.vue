<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-card>
      <el-form :inline="true" :model="searchForm" class="app-container-searchForm">
        <el-form-item label="登录账户">
          <el-input v-model="searchForm.loginCode" placeholder="登录账户" />
        </el-form-item>
        <el-form-item label="用户名称">
          <el-input v-model="searchForm.userName" placeholder="用户名称" />
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
        >{{ defaultSettings.btnAdd }}
        </el-button>
      </el-row>

      <!-- 数据表格 -->
      <el-table border :data="gridData.list" highlight-current-row>
        <el-table-column type="index" label="序号" width="50" />
        <el-table-column prop="loginCode" label="登陆账户" />
        <el-table-column prop="userName" label="用户名称" />
        <el-table-column prop="sex" label="性别" :formatter="formatSex" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="avatar" label="头像" />
        <el-table-column prop="address" label="地址" />
        <el-table-column prop="email" label="邮箱" width="160" />
        <el-table-column prop="validEmail" label="邮箱验证" :formatter="formatIsOrNot" />
        <el-table-column prop="tel" label="电话" />
        <el-table-column prop="validTel" label="电话验证" :formatter="formatIsOrNot" />
        <el-table-column prop="status" label="用户状态" :formatter="formatUserStatus" />
        <el-table-column fixed="right" label="操作" width="180">
          <template slot-scope="scope">
            <el-button
              type="text"
              icon="el-icon-view"
              size="small"
              @click="doView(scope.row)"
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
          <el-form-item label="登陆账户" prop="loginCode">
            <el-input v-model="viewForm.loginCode" />
          </el-form-item>
          <el-form-item label="用户名称" prop="userName">
            <el-input v-model="viewForm.userName" />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="viewForm.sex">
              <option-dict :dict="defaultSettings.dict.sex" />
            </el-select>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input-number v-model="viewForm.age" :min="0" :max="150" />
          </el-form-item>
          <el-form-item label="头像" prop="avatar">
            <el-input v-model="viewForm.avatar" />
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="viewForm.address" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="viewForm.email" />
          </el-form-item>
          <el-form-item label="邮箱验证" prop="validEmail">
            <el-select v-model="viewForm.validEmail">
              <option-dict :dict="defaultSettings.dict.isOrNot" />
            </el-select>
          </el-form-item>
          <el-form-item label="电话" prop="tel">
            <el-input v-model="viewForm.tel" />
          </el-form-item>
          <el-form-item label="电话验证" prop="validTel">
            <el-select v-model="viewForm.validTel">
              <option-dict :dict="defaultSettings.dict.isOrNot" />
            </el-select>
          </el-form-item>
          <el-form-item label="用户状态" prop="status">
            <el-select v-model="viewForm.status">
              <option-dict :dict="defaultSettings.dict.userStatus" />
            </el-select>
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button
          @click="visibleConfig.view = false"
        >{{ defaultSettings.closeButtonText }}</el-button>
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
          <el-form-item label="登陆账户" prop="loginCode">
            <el-input v-model="addForm.loginCode" />
          </el-form-item>
          <el-form-item label="用户名称" prop="userName">
            <el-input v-model="addForm.userName" />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="addForm.sex" placeholder="请选择">
              <option-dict :dict="defaultSettings.dict.sex" />
            </el-select>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input-number v-model="addForm.age" :min="0" :max="150" />
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="addForm.address" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="addForm.email" />
          </el-form-item>
          <el-form-item label="电话" prop="tel">
            <el-input v-model="addForm.tel" />
          </el-form-item>
          <el-form-item label="用户状态" prop="status">
            <el-select v-model="addForm.status" placeholder="请选择">
              <option-dict :dict="defaultSettings.dict.userStatus" />
            </el-select>
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
          <el-form-item label="登陆账户" prop="loginCode">
            <el-input v-model="editForm.loginCode" />
          </el-form-item>
          <el-form-item label="用户名称" prop="userName">
            <el-input v-model="editForm.userName" />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="editForm.sex" placeholder="请选择">
              <option-dict :dict="defaultSettings.dict.sex" />
            </el-select>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="editForm.age" />
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="editForm.address" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="editForm.email" />
          </el-form-item>
          <el-form-item label="电话" prop="tel">
            <el-input v-model="editForm.tel" />
          </el-form-item>
          <el-form-item label="用户状态" prop="status">
            <el-select v-model="editForm.status" placeholder="请选择">
              <option-dict :dict="defaultSettings.dict.userStatus" />
            </el-select>
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
import { doAddSave, doDelete, doEditSave, doView, getPageList } from '@/api/system/sysuser/SysUser'

export default {
  name: 'SysUser',
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
        loginCode: [{ required: true, message: '请输入登陆账户', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        userName: [{ required: true, message: '请输入用户名称', trigger: 'blur' }],
        sex: [{ required: true, message: '请输入性别', trigger: 'blur' }],
        age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
        status: [{ required: true, message: '请输入用户状态', trigger: 'blur' }]
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
    this.$getDict(defaultSettings.dict.sex)
    this.$getDict(defaultSettings.dict.userStatus)
    this.$getDict(defaultSettings.dict.isOrNot)
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

    // 查看
    doView(row) {
      this.visibleConfig.view = true
      this.viewForm = row
      this.viewForm.sex = row.sex + ''
      this.viewForm.validTel = row.validTel + ''
      this.viewForm.validEmail = row.validEmail + ''
      this.viewForm.status = row.status + ''
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
      this.editForm.sex = res.data.sex + ''
      this.editForm.status = res.data.status + ''
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

    // 格式化
    formatSex(row, column, cellValue) {
      return this.$formatDict(cellValue, defaultSettings.dict.sex)
    },
    formatIsOrNot(row, column, cellValue) {
      return this.$formatDict(cellValue, defaultSettings.dict.isOrNot)
    },
    formatUserStatus(row, column, cellValue) {
      return this.$formatDict(cellValue, defaultSettings.dict.userStatus)
    }
  }
}
</script>

<style lang='scss' scoped>

</style>
