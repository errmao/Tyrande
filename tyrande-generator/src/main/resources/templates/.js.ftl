import request from '@/utils/request'
import qs from 'qs'

/*************************************
* ${tableNameRemark}
*************************************/

/**
 * 查询-数据列表
 */
export function getPageList(data) {
  return request.get('/${classNameLower}/listPage', {
    params: data
  })
}

/**
 * 删除
 */
export function doDelete(id) {
  return request.delete('/${classNameLower}/delete?id=' + id)
}

/**
 * 添加保存
 */
export function doAddSave(data) {
  return request.post('/${classNameLower}/save', qs.stringify(data))
}

/**
 * 编辑保存
 */
export function doEditSave(data) {
  return request.put('/${classNameLower}/update', qs.stringify(data))
}

/**
 * 根据主键查询
 */
export function doView(id) {
  return request.get('/${classNameLower}/getById', {
    params: {
      id: id
    }
  })
}
