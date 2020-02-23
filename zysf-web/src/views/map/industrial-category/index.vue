<template>
  <div>
    <h3>产业数据功能导航</h3>
    <ul>
      <li><a href="/manage/industrial-category" target="_blank" rel="noopener">产业分类数据管理</a></li>
      <li><a href="/industrial-category/relation" target="_blank" rel="noopener">关系展示</a></li>
      <li><a href="/industrial-category/tree" target="_blank" rel="noopener">分类数据树</a></li>
    </ul>
  </div>
</template>
<script>
/* eslint-disable */
export default {
  name: 'industrial-category-index-page',
  components:{
  },
  data () {
    return {

    }
  }
}
</script>
<style scoped lang="less">
  h3 {
    margin: 40px 0 0;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
</style>

