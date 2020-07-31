<template>
  <div class="dashboard-container">
    <el-image v-for="url in urls" :key="url" :src="url" lazy></el-image>
  </div>
</template>

<script>
  export default {
    name: 'Dashboard',
    data() {
      return {
        urls: [
          'http://errmao.gitee.io/errmao.gitee.io/_media/_img/ty3.gif',
          'http://errmao.gitee.io/errmao.gitee.io/_media/_img/ty4.gif'
        ]
      }
    }
  }
</script>

<style lang="scss" scoped>
  .dashboard-container {
    background-color: #e3e3e3;
    min-height: 94.7vh;
  }

</style>
