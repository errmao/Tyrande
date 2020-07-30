import {asyncRoutes, constantRoutes} from '@/router'

/**
 * 判断是否拥有菜单权限
 */
function hasPermission(menus, route) {
  if (menus.length == 0) {
    return true;
  }
  let result = false;
  menus.forEach(menu => {
    if (menu.menuUrl == route.path) {
      result = true;
      return false;
    }
  })
  return result;
}

/**
 * 过滤没有权限的菜单
 */
export function filterAsyncRoutes(routes, menus) {
  const res = []

  routes.forEach(route => {
    const tmp = {...route}
    if (hasPermission(menus, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, menus)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

/**
 * 根据状态加载路由
 */
const actions = {
  generateRoutes({commit}, menus) {
    return new Promise(resolve => {
      // 查询
      let accessedRoutes = filterAsyncRoutes(asyncRoutes, menus)
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
