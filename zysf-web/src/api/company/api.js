/**
 * <p>
 *  企业信息 API
 * </p>
 * @author zzq
 * @since 2019-07-10
 */
/* eslint-disable */
import axios from '@/libs/axios'
export function fetchPage(query) {
    return axios.request({
        url: '/api/company/page',
        method: 'get',
        params: query
    })
}

export function fetchList(query) {
    return axios.request({
        url: '/api/company/list',
        method: 'get',
        params: query
    })
}
export function fetchFieldsList(query) {
  return axios.request({
    url: '/api/company/list/fields',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
    return axios.request({
        url: '/api/company',
        method: 'post',
        data: obj
    })
}

export function getObj(id) {
    return axios.request({
        url: '/api/company/' + id,
        method: 'get'
    })
}

export function delObj(id) {
    return axios.request({
        url: '/api/company/' + id,
        method: 'delete'
    })
}

export function putObj(obj) {
    return axios.request({
        url: '/api/company',
        method: 'put',
        data: obj
    })
}
