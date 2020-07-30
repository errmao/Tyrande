<template>
  <div class="dashboard-container">
    <el-image :src="src" lazy/>
  </div>
</template>

<script>
  export default {
    name: 'Dashboard',
    data() {
      return {
        src: 'https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3'
      }
    }
  }
</script>

<style lang="scss" scoped>
  .dashboard-container {
    background-color: #E3E3E3;
    // 居中
    display: flex;
    justify-content: center;
  }

  .app-main {
    background-color: #E3E3E3;
  }
</style>
