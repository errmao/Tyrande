import request from '@/utils/request'
import qs from 'qs'

/** ***********************************
* 系统字典项表
*************************************/

/**
 * 查询-数据列表
 */
export function getPageList(data) {
  return request.get('/sysdictkey/listPage', {
    params: data
  })
}

/**
 * 删除
 */
export function doDelete(id) {
  return request.delete('/sysdictkey/delete?id=' + id)
}

/**
 * 添加保存
 */
export function doAddSave(data) {
  return request.post('/sysdictkey/save', qs.stringify(data))
}

/**
 * 编辑保存
 */
export function doEditSave(data) {
  return request.put('/sysdictkey/update', qs.stringify(data))
}

/**
 * 根据主键查询
 */
export function doView(id) {
  return request.get('/sysdictkey/getById', {
    params: {
      id: id
    }
  })
}

/**
 * 校验字典项是否重复
 * @param dictKey
 */
export function checkDictKey(dictKey) {
  return request.get('/sysdictkey/checkDictKey', {
    params: {
      dictKey: dictKey
    }
  })
}

/** ***********************************
 * 系统字典值表
 *************************************/

/**
 * 查询-数据列表
 */
export function getValuePageList(data) {
  return request.get('/sysdictvalue/listPage', {
    params: data
  })
}

/**
 * 删除
 */
export function doValueDelete(id) {
  return request.delete('/sysdictvalue/delete?id=' + id)
}

/**
 * 添加保存
 */
export function doValueAddSave(data) {
  return request.post('/sysdictvalue/save', qs.stringify(data))
}

/**
 * 编辑保存
 */
export function doValueEditSave(data) {
  return request.put('/sysdictvalue/update', qs.stringify(data))
}

/**
 * 根据主键查询
 */
export function doValueView(id) {
  return request.get('/sysdictvalue/getById', {
    params: {
      id: id
    }
  })
}

/** ***********************************
 * 字典工具类
 *************************************/

/**
 * 根据字典项查询所有字典值
 */
export function getDicts(dictKey) {
  return request.get('/sysdictvalue/getByDictKey', {
    params: {
      dictKey: dictKey
    }
  })
}
