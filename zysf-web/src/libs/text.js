/* eslint-disable */
/**
 * iview table的列渲染，对于超出部门顶部显示Tooltip
 * @param h
 * @param params
 * @param field
 * @param placement
 * @returns {*}
 */
export function renderInfo( field, placement = 'right') {
  return (h, params) => {
    if (params.row[field]) {
      //正则获取字符串包含数组和大小写英文字母的内容
      let numberAndEnglish = String(params.row[field]).replace(/[^0-9][a-z][A-Z]+/g, '');
      //正则获取字符串包含汉字的内容
      let ChineseLength = String(params.row[field]).replace(/[^\u4e00-\u9fa5]+/g, '');
      let paramsRowfield = numberAndEnglish.length * 8 + ChineseLength.length * 12;
      let paramsRowItemColumnContent = params.row[field];
      //如果内容存在空格
      let fieldValue = params.row[field];
      if(typeof fieldValue === 'string') {
        if (params.row[field].indexOf(" ") !== -1) {
          //使用正则吧空格替换为span包裹的&nbsp（因为多个空格浏览器只显示一个空格）
          paramsRowItemColumnContent = params.row[field].replace(/\s/g, '<span>&nbsp;</span>');
        }
      }
      if ((params.column._width * 0.9 < paramsRowfield)) {
        return h('div', [
          h('Tooltip', {
            props: {placement: placement},
            transfer: true
          }, [
            h('span', {
              style: {//如果内容超出span标签的具体宽度，超出部分显示省略号
                display: 'inline-block',
                width: params.column._width * 0.9 + 'px',
                overflow: 'hidden',
                textOverflow: 'ellipsis',
                whiteSpace: 'nowrap',
              },
              domProps: {//向iview的dom的Props设置innerHTML用来渲染标签，iview的新版本只能渲染字符串
                innerHTML: paramsRowItemColumnContent
              }
            }),
            h('span', {
              slot: 'content',
              style: {whiteSpace: 'normal', wordBreak: 'break-all'},
              domProps: {
                innerHTML: paramsRowItemColumnContent
              }
            },)
          ])
        ])
      } else {
        return h('div', [
          h('span', {
            domProps: {
              innerHTML: paramsRowItemColumnContent
            }
          })
        ])
      }
    }
  }
}


// 下划线转换驼峰
export function toCamp(name) {
  return name.replace(/\_(\w)/g, function(all, letter){
    return letter.toUpperCase();
  });
}
// 驼峰转换下划线
export function toLine(name) {
  return name.replace(/([A-Z])/g,"_$1").toLowerCase();
}

export function prettyNum(num) {
  if(String(num).indexOf('.') !== -1) {
    return num.toFixed(2);
  }
  return num;
}
