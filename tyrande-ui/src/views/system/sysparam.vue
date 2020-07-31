<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-card>
      <el-form :inline="true" :model="searchForm" class="app-container-searchForm">
        <el-form-item label="参数名称">
          <el-input v-model="searchForm.paramName" placeholder="参数名称"></el-input>
        </el-form-item>
        <el-form-item label="参数类型">
          <el-select v-model="searchForm.paramType" placeholder="参数类型">
            <el-option label="系统参数" value="1"></el-option>
            <el-option label="业务参数" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="doSearch">搜索</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 数据列表 -->
    <el-card>
      <el-row>
        <el-button type="primary" icon="el-icon-circle-plus-outline">添加</el-button>
      </el-row>

      <el-table border :data="gridData">
        <el-table-column type="index" label="序号" width="50"/>
        <el-table-column prop="date" label="日期"/>
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="address" label="地址"/>
        <el-table-column fixed="right" label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-view" size="small">查看</el-button>
            <el-button type="text" icon="el-icon-edit" size="small">编辑</el-button>
            <el-button type="text" icon="el-icon-delete" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: 'SysParam',

    data() {
      return {
        // 查询参数对象
        searchForm: {
          paramName: '',
          paramType: ''
        },
        // 列表数据
        gridData: [{
          date: '2016-05-02',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-02',
          name: '王小虎2',
          province: '上海2',
          city: '普陀区2',
          address: '上海市普陀区金沙江路 1518 弄2',
          zip: 200333
        }]
      }
    },
    methods: {
      // 查询
      doSearch: function () {

      }
    }
  }
</script>

<style lang='scss' scoped>

</style>
