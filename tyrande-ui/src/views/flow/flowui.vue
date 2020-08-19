<template>
  <div>
    <iframe id="show-iframe" :src="src" scrolling="auto" frameborder="0" class="frame" />
  </div>
</template>

<script>
export default {
  name: 'Flowui',
  data() {
    return {
      src: ''
    }
  },
  mounted() {
    this.src = process.env['VUE_APP_HOST_URL'] + '/tyrande/flowui/index.html'
    const oIframe = document.getElementById('show-iframe')
    const deviceWidth = document.documentElement.clientWidth
    const deviceHeight = document.documentElement.clientHeight
    oIframe.style.width = deviceWidth + 'px'
    oIframe.style.height = deviceHeight - 60 + 'px'
  },
  methods: {
  }
}
</script>

<style scoped>

</style>
