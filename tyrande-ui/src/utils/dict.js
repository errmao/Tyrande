/**
 * 格式化字典
 */
export function formatDict(value, dictKey) {
  const arr = this.$dictMap[dictKey]
  for (let i = 0; i < arr.length; i++) {
    const item = arr[i]
    if (item.dictRealValue === (value + '')) {
      return item.dictValue
    }
  }
  return ''
}
