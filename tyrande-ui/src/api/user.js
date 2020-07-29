import request from '@/utils/request'

/**
 * 登录
 * @param data 登录账户和密码
 */
export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

/**
 * 获取用户信息
 * @param token
 */
export function getInfo(token) {
  return request({
    url: '/info',
    method: 'get',
    params: { token }
  })
}

/**
 * 登出
 */
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}
