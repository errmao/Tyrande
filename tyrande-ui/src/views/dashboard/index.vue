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
          process.env.VUE_APP_IMG_URL + process.env.VUE_APP_GIF_DIR + '/ty3.gif',
          process.env.VUE_APP_IMG_URL + process.env.VUE_APP_GIF_DIR + '/ty4.gif'
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
