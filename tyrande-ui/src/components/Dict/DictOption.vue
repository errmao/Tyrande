<template>
  <div>
    <el-option
      v-for="item in dictData"
      :key="item.id"
      :label="item.dictValue"
      :value="item.dictRealValue"
    />
  </div>
</template>

<script>

export default {
  name: 'DictOption',
  props: {
    dict: {
      type: String
    }
  },
  data() {
    return {
      dictData: []
    }
  },
  async created() {
    this.dictData = this.$dictMap[this.dict]
  }
}
</script>

<style scoped>

</style>
