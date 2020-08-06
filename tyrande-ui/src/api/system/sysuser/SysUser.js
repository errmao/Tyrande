import request from '@/utils/request'
import qs from 'qs'

/** ***********************************
* 系统用户表
*************************************/

/**
 * 查询-数据列表
 */
export function getPageList(data) {
  return request.get('/sysuser/listPage', {
    params: data
  })
}

/**
 * 删除
 */
export function doDelete(id) {
  return request.delete('/sysuser/delete?id=' + id)
}

/**
 * 添加保存
 */
export function doAddSave(data) {
  return request.post('/sysuser/save', qs.stringify(data))
}

/**
 * 编辑保存
 */
export function doEditSave(data) {
  return request.put('/sysuser/update', qs.stringify(data))
}

/**
 * 根据主键查询
 */
export function doView(id) {
  return request.get('/sysuser/getById', {
    params: {
      id: id
    }
  })
}

/**
 * 查询用户角色信息
 */
export function getUserRoleInfo(id) {
  return request.get('/sysuser/getUserRoleInfo', {
    params: {
      id: id
    }
  })
}

export function doConfigSave(data) {
  return request.post('/sysuser/doConfigSave', qs.stringify(data))
}
