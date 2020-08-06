import request from '@/utils/request'
import qs from 'qs'

/** ***********************************
* 系统角色表
*************************************/

/**
 * 查询-数据列表
 */
export function getPageList(data) {
  return request.get('/sysrole/listPage', {
    params: data
  })
}

/**
 * 删除
 */
export function doDelete(id) {
  return request.delete('/sysrole/delete?id=' + id)
}

/**
 * 添加保存
 */
export function doAddSave(data) {
  return request.post('/sysrole/save', qs.stringify(data))
}

/**
 * 编辑保存
 */
export function doEditSave(data) {
  return request.put('/sysrole/update', qs.stringify(data))
}

/**
 * 根据主键查询
 */
export function doView(id) {
  return request.get('/sysrole/getById', {
    params: {
      id: id
    }
  })
}

/**
 * 根据角色编号查询拥有的菜单
 */
export function getRoleMenu(id) {
  return request.get('/sysrole/getRoleMenu', {
    params: {
      id: id
    }
  })
}

/**
 * 保存菜单角色
 */
export function saveRoleMenu(data) {
  return request.post('/sysrole/saveRoleMenu', data)
}
