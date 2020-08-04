import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/styles/index.scss' // global css
import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control

Vue.use(ElementUI, { size: 'small' })

Vue.config.productionTip = false

// 挂载全局方法
import { getDicts } from '@/api/system/sysdictkey/SysDictKey'
import { formatDict } from '@/utils/dict'
Vue.prototype.$getDicts = getDicts
Vue.prototype.$formatDict = formatDict
// 全局字典
Vue.prototype.$dictMap = {}

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
