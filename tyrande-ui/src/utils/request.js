import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

/**
 * 创建 axios
 */
const service = axios.create({
  baseURL: process.env.NODE_ENV === 'production' ? process.env['VUE_APP_HOST_URL'] + process.env['VUE_APP_BASE_API'] : process.env['VUE_APP_BASE_API'],
  // 超时时间
  timeout: 5000
})

/**
 * request 拦截器
 */
service.interceptors.request.use(
  config => {
    // 修改操作需要去除掉两个时间参数
    if (config.method == 'put') {
      const data = config.data
      const arr = data.split('&')
      const resultArr = []
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].indexOf('updateTime') == -1 && arr[i].indexOf('createTime') == -1) {
          resultArr.push(arr[i])
        }
      }
      config.data = resultArr.join('&')
    }
    // 所有请求都携带 token
    if (store.getters.token) {
      config.headers['Authorization'] = getToken()
    }
    return config
  },
  error => {
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

/**
 * response 拦截器
 */
service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code === 0) {
      return res
    } else {
      Message({
        message: res.message || '系统错误',
        type: 'error',
        duration: 3 * 1000
      })
      return Promise.reject(new Error(res.message || '系统错误'))
    }
  },
  error => {
    console.log('err:' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 3 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
