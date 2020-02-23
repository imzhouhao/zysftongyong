/* eslint-disable */
import axios from 'axios'
import config from '@/config'
const baseUrl = process.env.NODE_ENV === 'development' ? config.baseUrl.dev : config.baseUrl.pro;

const addErrorLog = errorInfo => {
    const {
        statusText, status,
        data: { msg },
        config: { data,method },
        request: { responseURL}
    } = errorInfo
    let info = {
        type: 'ajax',
        method: method,
        params: JSON.stringify( data),
        code: status,
        statusText: statusText,
        msg: msg,
        requestUrl: responseURL
    }
    if (!responseURL.includes('sys-log-error')) {
        store.dispatch('addErrorLog', info)
    }
}

// Set config defaults when creating the instance
const instance = axios.create({
    baseURL: baseUrl,
    headers: {
        'Content-Type': 'application/json'
    },
});



instance.defaults.timeout = 10000;

/**
 * 请求拦截器
 */
instance.interceptors.request.use(function (config) {
    return config;
}, function (error) {
    return Promise.reject(error);
});

/**
 * 返回拦截器
 */
instance.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    let errorInfo = error.response;
    if (!errorInfo) {
        const {request: {statusText, status}, config} = JSON.parse(JSON.stringify(error))
        errorInfo = {
            statusText,
            status,
            request: {responseURL: config.url}
        }
    }
    addErrorLog(errorInfo)
    return Promise.reject(error);
});

export default instance;
