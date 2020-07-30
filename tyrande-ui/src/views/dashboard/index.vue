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
          'https://121.196.58.164/files/tyrande//ty3.gif',
          'https://121.196.58.164/files/tyrande//ty4.gif'
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
