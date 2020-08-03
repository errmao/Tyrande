import request from '@/utils/request'
import qs from 'qs'

/*************************************
 * 系统管理-参数管理
 *************************************/

/**
 * 查询-数据列表
 */
export function getPageList(data) {
  return request.get('/sysparams/listPage', {
    params: data
  })
}

/**
 * 删除
 */
export function doDelete(id) {
  return request.delete('/sysparams/delete?id=' + id)
}

/**
 * 添加保存
 */
export function doAddSave(data) {
  return request.post('/sysparams/save', qs.stringify(data))
}

/**
 * 编辑保存
 */
export function doEditSave(data) {
  return request.put('/sysparams/update', qs.stringify(data))
}

/**
 * 根据主键查询
 */
export function doView(id) {
  return request.get('/sysparams/getById', {
    params: {
      id: id
    }
  })
}
