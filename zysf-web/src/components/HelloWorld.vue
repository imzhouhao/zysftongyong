<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h3>隐私声明</h3>
    <ul>
      <li><a href="http://39.97.238.112" target="_blank" rel="noopener">数据来源</a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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
