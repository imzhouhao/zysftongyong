<template>
  <div>
    <div style="height:800px;" ref="myMapEchart"></div>
  </div>
</template>
<script>
/* eslint-disable */
import echarts from 'echarts';
import { list2Tree } from '@/libs/tree';
import * as IndustrialCategoryAPI from '@/api/industrial-category/api'
export default {
  name: 'industrial-category-tree-chart-page',
  components:{
    echarts
  },
  data () {
    return {
      dom: null,
      objList: [],
    }
  },
  mounted () {
    this.dom = echarts.init(this.$refs.myMapEchart);
    this.getList();
  },
  methods: {
    getList(){
      let _this = this;
      let params = {};
      let querystring = Object.assign(params);
      IndustrialCategoryAPI.fetchList(querystring).then( resp => {
        _this.objList = resp.data.data;
        _this.drawMap();
      })
    },
    drawMap(){
      let _this = this;
      let treeData = {
        name:'产业链分类数据',
        children: list2Tree(_this.objList)
      }
      let option = {
        // tooltip: {
        //   trigger: 'item',
        //   triggerOn: 'mousemove'
        // },
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series:[
          {
            type: 'tree',
            data: [treeData],
            // left: '2%',
            // right: '2%',
            top: '8%',
            bottom: '20%',
            symbol: 'emptyCircle',
            orient: 'vertical',//vertical,horizontal,BT
            // layout: 'radial',//vertical,radial,horizontal
            initialTreeDepth: 7,
            symbolSize: 7,
            expandAndCollapse: true,
            label: {
              normal: {
                position: 'top',
                rotate: -90,
                verticalAlign: 'middle',
                align: 'right',
                fontSize: 9
              }
            },
            leaves: {
              label: {
                normal: {
                  position: 'bottom',
                  rotate: -90,
                  verticalAlign: 'middle',
                  align: 'left'
                }
              }
            },
            animationDurationUpdate: 750
          }
        ]
      };
      _this.dom.setOption(option);
    },
  }
}
</script>
<style scoped>

</style>
