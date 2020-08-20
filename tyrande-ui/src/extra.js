import Vue from 'vue'

// 挂载全局字典方法
import { formatDict, getDict } from '@/utils/dict'
Vue.prototype.$getDict = getDict
Vue.prototype.$formatDict = formatDict

// 全局字典缓存
Vue.prototype.$dictMap = {}

// 选择器的选项组件
import DictOption from '@/components/Dict/DictOption'
Vue.component('option-dict', DictOption)
