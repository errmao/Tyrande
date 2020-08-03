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
          <el-button type="primary" icon="el-icon-search" @click="getPageList">搜索</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 数据列表 -->
    <el-card>
      <el-row class="app-container-toolbar">
        <el-button type="primary" icon="el-icon-circle-plus-outline">添加</el-button>
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
            <el-button type="text" icon="el-icon-view" size="small">查看</el-button>
            <el-button type="text" icon="el-icon-edit" size="small">编辑</el-button>
            <el-button type="text" icon="el-icon-delete" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区域 -->
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="searchForm.current"
        layout="prev, pager, next, jumper, ->, total"
        :total="gridData.total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
  import {getPageList} from "@/api/system/SysParams";

  export default {
    name: 'SysParam',
    data() {
      return {
        // 查询参数对象
        searchForm: {
          current: 1,
          paramName:'',
          paramEnName: ''
        },
        // 列表数据
        gridData: {
          list: [],
          total: 0
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
          const {records, total} = res.data;
          this.gridData.list = records;
          this.gridData.total = total;
        }).catch(() => {
          this.$message.error("请求失败");
        })
      },

      // 分页栏选择页数方法
      handleCurrentChange(newCurrent) {
        console.log(this.searchForm);
        this.searchForm.current = newCurrent
        this.getPageList()
      }

    }
  }
</script>

<style lang='scss' scoped>

</style>
