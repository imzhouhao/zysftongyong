<template>
  <div>
    <div>
      <Button @click="initSelect" style="margin-right: 5px;">请选择展示节点</Button>
      <Button @click="showAll">显示全部</Button>
    </div>
    <div :style="{height:screenHeight+'px'}" ref="myMapEchart"></div>
    <div>
      <Modal v-model="modalShow" title="分类选择树" width="50%" @on-ok="selectedNodes">
        <Tree ref="tree" :data="treeData" :load-data="loadData" show-checkbox></Tree>
      </Modal>
    </div>
  </div>
</template>
<script>
/* eslint-disable */
import echarts from 'echarts';
import { list2Tree } from '@/libs/tree';
import * as IndustrialCategoryAPI from '@/api/industrial-category/api'
export default {
  name: 'industrial-category-relation-chart2-page',
  components:{
    echarts
  },
  data () {
    return {
      modalShow: false,
      treeData: [],
      dom: null,
      objList: [],
      linkData: [],
      requestIds: [],
    }
  },
  computed: {
    screenHeight(){
      return window.screen.height;
    }
  },
  mounted () {
    this.dom = echarts.init(this.$refs.myMapEchart);
    this.getTree({parentId: -1});
  },
  methods: {
    showAll(){
      this.getAll();
    },
    initSelect(){
      this.modalShow = true;
      this.treeData = [];
      this.getTree({parentId:-1})
    },
    getAll(){
      let _this = this;
      let params = {};
      let querystring = Object.assign(params);
      IndustrialCategoryAPI.fetchList(querystring).then( resp => {
        _this.objList = resp.data.data;
        _this.drawMap();
      })
    },
    // 子节点的option
    selectedNodes () {
      let nodes = this.$refs.tree.getCheckedAndIndeterminateNodes();
      let ids = nodes.map( node => node.id );
      IndustrialCategoryAPI.fetchListByIds({ids:ids.join()}).then(resp => {
          this.objList = resp.data.data;
          this.drawMap();
      })
    },
    getTree(querystring){
      let _this = this;
      IndustrialCategoryAPI.fetchList(querystring).then( resp => {
        _this.treeData = _this.handleTreeList(resp.data.data)
        _this.objList = resp.data.data;
        _this.drawMap();
      })
    },
    loadData (item, callback) {
      let _this = this;
      IndustrialCategoryAPI.fetchList({parentId: item.id}).then( resp => {
        callback(_this.handleTreeList(resp.data.data));
      })
    },
    handleTreeList(list){
      for(let i=0, len = list.length; i< len; i++) {
        list[i]['loading'] = false;
      }
      return list2Tree(list);
    },
    drawMap(){
      let _this = this;
      _this.dom.clear();
      _this.linkData = [];
      let links = _this.generateLinks();
      let option = {
        title: {
          text: "产业链分类数据",
          top: "top",
          left: "left",
          textStyle: {
            color: '#292421'
          }
        },
        tooltip: {
          formatter: function (params) {
            return params.data.name.split('-')[0]+":"+params.value;
          }
        },
        backgroundColor: '#FFFFFF',
        legend: {
          show : true,
          data : [
            {
              name : '层级一',
              icon : 'rect'
            },
            {
              name : '层级二',
              icon : 'roundRect'
            },
            {
              name : '层级三',
              icon : 'circle'
            } ,
            {
              name : '层级四',
              icon : 'circle'
            } ,
            {
              name : '层级五',
              icon : 'circle'
            } ,
            {
              name : '层级六',
              icon : 'circle'
            }
          ],
          textStyle: {
            color: '#292421'
          },
          icon: 'circle',
          type: 'scroll',
          orient: 'horizontal',
          left: 10,
          top: 20,
          bottom: 20,
          itemWidth: 10,
          itemHeight: 10
        },
        animationDuration: 0,
        animationEasingUpdate: 'quinticInOut',
        series: [{
          name: '产业链关系图谱',
          type: 'graph',
          layout: 'force',
          force: {
            repulsion: 300,
            gravity: 0.1,
            edgeLength: 15,
            layoutAnimation: true,
          },
          data: _this.linkData,
          links: links,
          categories:[
            {
              name : '层级一',
              symbol : '',
              label : {
              }
            },
            {
              name : '层级二',
              symbol : ''
            },
            {
              name : '层级三',
              symbol : ''
            },
            {
              name : '层级四',
              symbol : ''
            },
            {
              name : '层级五',
              icon : ''
            } ,
            {
              name : '层级六',
              icon : ''
            }
            ],
          roam: true,
          label: {
            normal: {
              show: true,
              position: 'bottom',
              formatter: function (params) {
                return params.data.name.split('-')[0];
              },
              fontSize: 10,
              fontStyle: '600',
            }
          },
          // lineStyle: {
          //   normal: {
          //     opacity: 0.9,
          //     width: 1.5,
          //     curveness: 0
          //   }
          // },
          focusNodeAdjacency: true,
          // label: {
          //   normal: {
          //     show: true,
          //     position: 'top',
          //   }
          // },
          lineStyle: {
            normal: {
              color: 'source',
              curveness: 0,
              type: "solid"
            }
          }
        }]
      };
      _this.dom.setOption(option);
      _this.dom.on('click', function (params) {
        let oid = params.data.oid;
        if(_this.requestIds.indexOf(oid) ===-1){
          _this.requestIds.push(oid)
          IndustrialCategoryAPI.fetchList({parentId: oid}).then( resp => {
            _this.objList = _this.objList.concat(resp.data.data);
            _this.drawMap();
          })
        }

      });
    },
    generateLinks(){
      let _this = this;
      let tree = list2Tree(_this.objList);
      let links = [], root = null, symbolSize = 35, category = 0;
      _this.traverseTree(root, tree, links, symbolSize, category);
      return links;
    },
    traverseTree(pnode, nodes, links, symbolSize, category){
      if(symbolSize < 5) {
        symbolSize = 5;
      }
      let _this = this;
      if (!nodes || nodes.length === 0) {
        return nodes;
      }
      for (let i = 0, len = nodes.length; i < len; i++) {
        let item = nodes[i];
        let info = {
          oid: item.id,
          name: item.name+"-"+item.id,
          value: item.children.length,
          category: category,
          "symbolSize": symbolSize,
          "draggable": "true",
        };
        if(pnode) {
          links.push(
            {
              source: pnode.name+"-"+pnode.id,
              target: item.name+"-"+item.id
            }
          );
        }
        _this.linkData.push(info)
        _this.traverseTree(item, item.children, links, symbolSize - 5, category+1);
      }
    }
  }
}
</script>
<style scoped>

</style>
