<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">首页</router-link> |
      <!--<router-link to="/about">关于我们</router-link> |-->
      <router-link to="/company">企业数据</router-link> |
      <router-link to="/industrial-category">产业分类数据</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style lang="less">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
