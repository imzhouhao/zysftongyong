/* eslint-disable */
/**
 * 将一维的扁平数组转换为多层级对象，这里可以没有树顶层的id
 * @param data 原始数据
 * @param attributes 一些属性标识
 * @returns {Array} 返回多层次的树状结构数据
 */
export function list2Tree(source, attributes){
  if(!source){
    return null;
  }
  if(!attributes){
    // 属性配置信息
    attributes = {
      id: 'id',
      parentId: 'parentId',
      name: 'name',
      rootId: '-1'
    };
  }
  // 对源数据深度克隆
  let cloneData = JSON.parse(JSON.stringify(source))
  let id =attributes['id'], parentId = attributes['parentId'],
    rootId = attributes['rootId'];
  let idList = {},
    treeList = [];
  for (let i = 0, len = cloneData.length; i < len; i++) {
    //生成一个以id为键的对象
    idList[cloneData[i][id]] = cloneData[i]
  }
  for (let j = 0, len1 = cloneData.length; j < len1; j++) {
    let aVal = cloneData[j];
    let aValParent = idList[aVal[parentId]];
    //如果aValParent存在；就说明当前的aVal是aValParent的孩子
    if(aVal['id'] == rootId){
      aVal['expand'] = true;
    } else {
      aVal['expand'] = true;
    }
    aVal['id'] = aVal[id];
    aVal['label'] = aVal[attributes.name];
    aVal['title'] = aVal[attributes.name];
    aVal['parentId'] = aVal[parentId];
    if(!aVal['children']){
      aVal['children'] = [];
    }
    if (aValParent) {
      if (aValParent['children']) {
        aValParent['children'].push(aVal)
      } else {
        aValParent['children'] = [];
        aValParent['children'].push(aVal)
      }
    } else {
      // aVal['parentId'] = rootId;
      treeList.push(aVal)
    }
  }
  return treeList
}

// 递归遍历树
export function traverseTreeAndSetVal(nodes, key, val){
  if (!nodes || nodes.length == 0) {
    return nodes;
  }
  for (let i = 0, len = nodes.length; i < len; i++) {
    let item = nodes[i];
    item[key] = val;
    traverseTreeAndSetVal(item.children, key, val);
  }
}
