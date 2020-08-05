import request from '@/utils/request'
import qs from 'qs'

/** ***********************************
* 系统菜单表
*************************************/

/**
 * 查询-数据列表
 */
export function getPageList(data) {
  return request.get('/sysmenu/listPage', {
    params: data
  })
}

/**
 * 删除
 */
export function doDelete(id) {
  return request.delete('/sysmenu/delete?id=' + id)
}

/**
 * 添加保存
 */
export function doAddSave(data) {
  return request.post('/sysmenu/save', qs.stringify(data))
}

/**
 * 编辑保存
 */
export function doEditSave(data) {
  return request.put('/sysmenu/update', qs.stringify(data))
}

/**
 * 根据主键查询
 */
export function doView(id) {
  return request.get('/sysmenu/getById', {
    params: {
      id: id
    }
  })
}
