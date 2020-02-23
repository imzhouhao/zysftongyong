/* eslint-disable */
const baseUrl = {
    dev: 'http://localhost:8081',
    pro: 'http://39.97.238.112:8081/'
};
const url = process.env.NODE_ENV === 'development' ? baseUrl.dev : baseUrl.pro;
export default {
    url: url,
    baseUrl: baseUrl,
}
