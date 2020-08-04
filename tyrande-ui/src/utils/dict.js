import { getDictList } from '@/api/system/sysdictkey/SysDictKey'

/**
 * 加载字典
 */
export async function getDict(dictKey) {
  if (this.$dictMap[dictKey]) {
    return
  }
  await getDictList(dictKey).then(res => {
    this.$dictMap[dictKey] = res.data
  })
}

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
