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
      <el-table border :data="gridData.list">
        <el-table-column type="index" label="序号" width="50" />
        <el-table-column prop="loginCode" label="登陆账户" />
        <el-table-column prop="userName" label="用户名称" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="avatar" label="头像" />
        <el-table-column prop="address" label="地址" />
        <el-table-column prop="email" label="邮箱" width="160" />
        <el-table-column prop="validEmail" label="邮箱验证" />
        <el-table-column prop="tel" label="电话" />
        <el-table-column prop="validTel" label="电话验证" />
        <el-table-column prop="status" label="用户状态" />
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
          <el-form-item label="登陆账户" prop="loginCode">
            <el-input v-model="viewForm.loginCode" />
          </el-form-item>
          <el-form-item label="用户名称" prop="userName">
            <el-input v-model="viewForm.userName" />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-input v-model="viewForm.sex" />
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="viewForm.age" />
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
            <el-input v-model="viewForm.validEmail" />
          </el-form-item>
          <el-form-item label="电话" prop="tel">
            <el-input v-model="viewForm.tel" />
          </el-form-item>
          <el-form-item label="电话验证" prop="validTel">
            <el-input v-model="viewForm.validTel" />
          </el-form-item>
          <el-form-item label="用户状态" prop="status">
            <el-input v-model="viewForm.status" />
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
          <el-form-item label="密码" prop="password">
            <el-input v-model="addForm.password" />
          </el-form-item>
          <el-form-item label="用户名称" prop="userName">
            <el-input v-model="addForm.userName" />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-input v-model="addForm.sex" />
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="addForm.age" />
          </el-form-item>
          <el-form-item label="头像" prop="avatar">
            <el-input v-model="addForm.avatar" />
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="addForm.address" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="addForm.email" />
          </el-form-item>
          <el-form-item label="邮箱是否验证 0-否 1-是" prop="validEmail">
            <el-input v-model="addForm.validEmail" />
          </el-form-item>
          <el-form-item label="电话" prop="tel">
            <el-input v-model="addForm.tel" />
          </el-form-item>
          <el-form-item label="电话是否验证 0-否 1-是" prop="validTel">
            <el-input v-model="addForm.validTel" />
          </el-form-item>
          <el-form-item label="用户状态 0-未使用 1-正常 2-锁定 3-过期" prop="status">
            <el-input v-model="addForm.status" />
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
          <el-form-item label="密码" prop="password">
            <el-input v-model="editForm.password" />
          </el-form-item>
          <el-form-item label="用户名称" prop="userName">
            <el-input v-model="editForm.userName" />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-input v-model="editForm.sex" />
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="editForm.age" />
          </el-form-item>
          <el-form-item label="头像" prop="avatar">
            <el-input v-model="editForm.avatar" />
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
            <el-input v-model="editForm.status" />
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
        loginCode: [
          { required: true, message: '请输入登陆账户', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        userName: [
          { required: true, message: '请输入用户名称', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请输入性别 1-男 2-女', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请输入年龄', trigger: 'blur' }
        ],
        avatar: [
          { required: true, message: '请输入头像', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        validEmail: [
          { required: true, message: '请输入邮箱是否验证 0-否 1-是', trigger: 'blur' }
        ],
        tel: [
          { required: true, message: '请输入电话', trigger: 'blur' }
        ],
        validTel: [
          { required: true, message: '请输入电话是否验证 0-否 1-是', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请输入用户状态 0-未使用 1-正常 2-锁定 3-过期', trigger: 'blur' }
        ]
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
