/**
 * Created by PanJiaChen on 16/11/18.
 */
import { checkDictKey } from '@/api/system/sysdictkey/SysDictKey'

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  const valid_map = ['admin', 'editor']
  return valid_map.indexOf(str.trim()) >= 0
}

/**
 * 英文或数字
 */
export function isNumberAndSe(rule, value, callback) {
  const reg = /^[a-zA-Z0-9]+$/
  if (value == '' || value == undefined || value == null) {
    callback()
  } else {
    if (!reg.test(value)) {
      callback(new Error('仅由英文字母数字组成'))
    } else {
      callback()
    }
  }
}

/**
 * 字典项不能重复添加
 * @param rule
 * @param value
 * @param callback
 */
export function dictKeyValidate(rule, value, callback) {
  checkDictKey(value).then(res => {
    if (res.data == null) {
      callback()
    } else {
      callback(new Error('字典项已存在'))
    }
  })
}
