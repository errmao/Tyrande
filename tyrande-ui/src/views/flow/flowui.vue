<template>
  <div class="iframe-container">
    <iframe :src="src" scrolling="auto" frameborder="0" class="frame">
    </iframe>
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
    this.src = process.env['VUE_APP_HOST_URL'] + '/tyrande/flowable-modeler/index.html'
  },
  methods: {
  }
}
</script>

<style scoped>

</style>
