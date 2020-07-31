import request from '@/utils/request'

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
