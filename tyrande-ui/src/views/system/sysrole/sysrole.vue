<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-card>
      <el-form :inline="true" :model="searchForm" class="app-container-searchForm">
        <el-form-item label="角色名称">
          <el-input v-model="searchForm.roleName" placeholder="角色名称" />
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
        <!-- 数据列表 -->
        <el-card class="role_menu_role_class">
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
          <el-table border :data="gridData.list" highlight-current-row @current-change="roleRowChangeClick">
            <el-table-column type="index" label="序号" width="50" />
            <el-table-column prop="roleName" label="角色名称" />
            <el-table-column prop="roleDesc" label="角色描述" />
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
      </el-col>

      <!-- 分配菜单区域 -->
      <el-col :span="13">
        <el-card class="role_menu_menu_class">
          <el-row class="app-container-toolbar">
            <div class="tool-btn-three">
              <el-button
                type="primary"
                icon="el-icon-sunny"
                @click="saveRoleMenu"
              >
                {{ defaultSettings.btnSave }}
              </el-button>
            </div>
          </el-row>
          <div class="tree-class">
            <el-tree
              ref="menuTreeRef"
              :data="treeData"
              node-key="id"
              default-expand-all
              :default-checked-keys="select.menu"
              :props="props"
              check-strictly
              show-checkbox
            />
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 查看 -->
    <el-dialog :title="defaultSettings.btnView" :visible.sync="visibleConfig.view" width="50%">
      <span>
        <el-form :model="viewForm" label-width="120px" disabled>
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="viewForm.roleName" />
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDesc">
            <el-input v-model="viewForm.roleDesc" />
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
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="addForm.roleName" />
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDesc">
            <el-input v-model="addForm.roleDesc" />
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
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="editForm.roleName" />
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDesc">
            <el-input v-model="editForm.roleDesc" />
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
import {
  doAddSave,
  doDelete,
  doEditSave,
  doView,
  getPageList,
  getRoleMenu,
  saveRoleMenu
} from '@/api/system/sysrole/SysRole'
import {
  getPageList as getMenuTree
} from '@/api/system/sysmenu/SysMenu'

export default {
  name: 'SysRole',
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
        roleName: [{ required: true, message: '请输入角色名称', trigger: 'blur' }],
        roleDesc: [{ required: true, message: '请输入角色描述', trigger: 'blur' }]
      },

      // 查看数据
      viewForm: {},

      // 添加对话框数据
      addForm: {},

      // 编辑对话框数据
      editForm: {},

      /**
       * 菜单树配置
       */
      props: {
        label: 'menuName',
        children: 'sub'
      },
      treeData: [],
      // 选中的角色
      select: {
        menu: [],
        role: null
      }
    }
  },
  created() {
    this.getPageList()
    this.initMenuTree()
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
    // 角色表格行点击事件
    async roleRowChangeClick(currentRow) {
      // 重置右侧菜单面板
      this.select.menu = []
      await this.initMenuTree()
      // 查询需要勾选的
      if (currentRow == null) {
        this.select.role = null
      } else {
        // 有选中的角色
        this.select.role = currentRow
        // 查询角色拥有的菜单
        const { data } = await getRoleMenu(currentRow.id)
        if (Object.keys(data).length !== 0) {
          this.select.menu = data
        }
      }
    },

    /**
     * 菜单树配置
     */
    // 初始化菜单树数据
    async initMenuTree() {
      const res = await getMenuTree()
      this.treeData = res.data
    },

    // 保存角色权限关联
    async saveRoleMenu() {
      // 判读是否选择角色
      if (this.select.role == null) {
        this.$message.error('请选择角色')
      }
      // 获取选中菜单权限
      const selectArr = this.$refs.menuTreeRef.getCheckedKeys()
      const data = {
        role: this.select.role.id,
        menu: selectArr
      }
      await saveRoleMenu(data).then(() => {
        this.$message.success(defaultSettings.successSave)
      })
    }
  }
}
</script>

<style lang='scss' scoped>

  .role_menu_role_class {
    margin-right: -5px;
  }

  .role_menu_menu_class {
    margin-left: -5px;
    max-height: 780px;
    overflow-y: auto;
    .tree-class {
        >.el-tree{
          >.el-tree-node{
            /*设置横向滚动条*/
            min-width: 100%;
            display: inline-block;
            /*设置根节点隐藏*/
            >.el-tree-node__content{
              display: none;
            }
          }
        }
    }
  }
</style>
