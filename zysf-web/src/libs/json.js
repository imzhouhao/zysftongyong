/* eslint-disable */
export function exportJson(title, jsonData) {
  //下载为json文件
  let aLink = document.createElement('a');
  aLink.download = title+".json";
  aLink.style.display = 'none';
  // 字符内容转变成blob地址
  let blob = new Blob([JSON.stringify(jsonData, null, "\t")], {type: "application/json"});
  aLink.href = URL.createObjectURL(blob);
  // 触发点击
  document.body.appendChild(aLink);
  aLink.click();
  // 然后移除
  document.body.removeChild(aLink);
}
