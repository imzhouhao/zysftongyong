/**
 * <p>
 * 产业分类信息表 API
 * </p>
 * @author zzq
 * @since 2019-07-16
 */
/* eslint-disable */
import axios from '@/libs/axios'
export function fetchPage(query) {
    return axios.request({
        url: '/api/industrial-category/page',
        method: 'get',
        params: query
    })
}

export function fetchList(query) {
    return axios.request({
        url: '/api/industrial-category/list',
        method: 'get',
        params: query
    })
}
export function fetchListByIds(query) {
  return axios.request({
    url: '/api/industrial-category/list/ids',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
    return axios.request({
        url: '/api/industrial-category',
        method: 'post',
        data: obj
    })
}

export function getObj(id) {
    return axios.request({
        url: '/api/industrial-category/' + id,
        method: 'get'
    })
}

export function delObj(id) {
    return axios.request({
        url: '/api/industrial-category/' + id,
        method: 'delete'
    })
}

export function putObj(obj) {
    return axios.request({
        url: '/api/industrial-category',
        method: 'put',
        data: obj
    })
}
