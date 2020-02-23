<template>
  <div>
    <h3>企业数据功能导航</h3>
    <ul>
      <li><a href="/manage/company" target="_blank" rel="noopener">企业数据管理</a></li>
      <li><a href="/company/scatter-map" target="_blank" rel="noopener">企业散点地图</a></li>
      <li><a href="/company/heat-map" target="_blank" rel="noopener">企业热力地图</a></li>
    </ul>
  </div>
</template>
<script>
/* eslint-disable */
export default {
  name: 'map-index-page',
  components:{
  },
  data () {
    return {

    }
  },
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
