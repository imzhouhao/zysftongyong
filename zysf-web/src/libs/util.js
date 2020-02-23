/* eslint-disable */
/**
 * @description 绑定事件 on(element, event, handler)
 */
export const on = (function () {
  if (document.addEventListener) {
    return function (element, event, handler) {
      if (element && event && handler) {
        element.addEventListener(event, handler, false)
      }
    }
  } else {
    return function (element, event, handler) {
      if (element && event && handler) {
        element.attachEvent('on' + event, handler)
      }
    }
  }
})()

/**
 * @description 解绑事件 off(element, event, handler)
 */
export const off = (function () {
  if (document.removeEventListener) {
    return function (element, event, handler) {
      if (element && event) {
        element.removeEventListener(event, handler, false)
      }
    }
  } else {
    return function (element, event, handler) {
      if (element && event) {
        element.detachEvent('on' + event, handler)
      }
    }
  }
})()

export function sortArr (arr){
  if (arr && arr.length > 0) {
    for (let i = 0, len = arr.length; i < len; i++) {
      let item = arr[i];
      sortArr(item["children"]);
    }
    arr.sort( (a, b) => {
      return a.sort - b.sort;
    })
  }
}

/**
 * 判断数组是否包含某个元素
 * @param array
 * @param obj
 * @returns {boolean}
 */
export function arrayContains(array, obj){
  for (let i = 0, len = array.length; i < len; i++){
    if (typeof obj == 'object'){
      let flag = objDiff(obj, array[i]);
      if (flag) return true;
    }else {
      if (array[i] == obj){//如果要求数据类型也一致，这里可使用恒等号===
        return true;
      }
    }
  }
  return false;
}

export function arrayIndexOf(array, obj){
  for (let i = 0, len = array.length; i < len; i++){
    if (typeof obj == 'object'){
      let flag = objDiff(obj, array[i]);
      if (flag) return i;
    }else {
      if (array[i] == obj){//如果要求数据类型也一致，这里可使用恒等号===
        return i;
      }
    }
  }
  return -1;
}
