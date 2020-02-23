<template>
  <div>
    <div :style="{height: heightStr ,width:'100%'}" ref="myMapEchart"></div>
  </div>
</template>

<script>
/* eslint-disable */
import { random } from '@/libs/random';
import {arrayContains, arrayIndexOf} from '@/libs/util';
import echarts from 'echarts';
// import 'echarts/map/js/china.js'
import * as CompanyAPI from '@/api/company/api'

export default {
    name: 'china-scatter-map-page',
    components: {
      echarts,
    },
    data () {
      return {
        dom: null,
          center: {lng: 116.46, lat: 39.92},
        zoom: 5,
        objList:[],
      }
    },
    computed:{
      scatterMapData(){
        let _this = this, info = [];
        _this.objList.forEach(obj => {
          info.push(
            {
              name:obj.name,
              value: 1
            }
          )
        });
        return info;
      },
      geoCoordMap(){
        let _this = this, info = {};
        _this.objList.forEach(obj => {
          info[obj.name] = [obj.lng, obj.lat];
        });
        return info;
      },
      minHeight(){
        let minHeight= window.screen.height;
        return  minHeight * 0.7;
      },
      heightStr(){
        return this.minHeight+"px"
      }
    },
    mounted () {
      this.dom = echarts.init(this.$refs.myMapEchart);
      this.getList();
    },
    methods:{
      convertData(data){
        let res = [],_this = this;
        for (let i = 0, len = data.length; i <5800; i++) {
          let geoCoord = _this.geoCoordMap[data[i].name];
          if (geoCoord) {
            res.push({
              name: data[i].name,
              value: geoCoord.concat(data[i].value)
            });
          }
        }
        return res;
      },
      getList(){
        let _this = this;
        let fieldList = ['id','name','lat','lng'], fields = fieldList.join();
        let params = {};
        let querystring = Object.assign({fields:fields}, params);
        CompanyAPI.fetchFieldsList(querystring).then( resp => {
          _this.objList = resp.data.data;
          _this.drawMap();
        })
      },
      drawMap(){
        let _this = this;
        // 基于准备好的dom，初始化echarts实例
        window.onresize = _this.dom.resize;
        let option = {
          title: {
            text: '全国产业链体检大数据',
            subtext: '企业地理位置',
            sublink: 'http://39.97.238.112',
            left: 'center'
          },
          tooltip : {
            trigger: 'item',
            formatter: '{a}<br />{b},{c}',
          },
          bmap: {
            center: [_this.center.lng, _this.center.lat],
            zoom: _this.zoom,
            roam: true,
            mapStyle: {
              styleJson: [{
                'featureType': 'water',
                'elementType': 'all',
                'stylers': {
                  'color': '#d1d1d1'
                }
              }, {
                'featureType': 'land',
                'elementType': 'all',
                'stylers': {
                  'color': '#f3f3f3'
                }
              }, {
                'featureType': 'railway',
                'elementType': 'all',
                'stylers': {
                  'visibility': 'off'
                }
              }, {
                'featureType': 'highway',
                'elementType': 'all',
                'stylers': {
                  'color': '#fdfdfd'
                }
              }, {
                'featureType': 'highway',
                'elementType': 'labels',
                'stylers': {
                  'visibility': 'off'
                }
              }, {
                'featureType': 'arterial',
                'elementType': 'geometry',
                'stylers': {
                  'color': '#fefefe'
                }
              }, {
                'featureType': 'arterial',
                'elementType': 'geometry.fill',
                'stylers': {
                  'color': '#fefefe'
                }
              }, {
                'featureType': 'poi',
                'elementType': 'all',
                'stylers': {
                  'visibility': 'off'
                }
              }, {
                'featureType': 'green',
                'elementType': 'all',
                'stylers': {
                  'visibility': 'off'
                }
              }, {
                'featureType': 'subway',
                'elementType': 'all',
                'stylers': {
                  'visibility': 'off'
                }
              }, {
                'featureType': 'manmade',
                'elementType': 'all',
                'stylers': {
                  'color': '#d1d1d1'
                }
              }, {
                'featureType': 'local',
                'elementType': 'all',
                'stylers': {
                  'color': '#d1d1d1'
                }
              }, {
                'featureType': 'arterial',
                'elementType': 'labels',
                'stylers': {
                  'visibility': 'off'
                }
              }, {
                'featureType': 'boundary',
                'elementType': 'all',
                'stylers': {
                  'color': '#fefefe'
                }
              }, {
                'featureType': 'building',
                'elementType': 'all',
                'stylers': {
                  'color': '#d1d1d1'
                }
              }, {
                'featureType': 'label',
                'elementType': 'labels.text.fill',
                'stylers': {
                  'color': '#999999'
                }
              }]
            }
          },
          series : [
            {
              name: '企业地理位置',
              type: 'scatter',
              coordinateSystem: 'bmap',
              data: _this.convertData(_this.scatterMapData),
                symbolSize: function (val) {
                  return val[2] * 8;
                },
                label: {
                  show: false,
                  normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                  },
                  emphasis: {
                    show: false
                  }
                },
                itemStyle: {
                  normal: {
                    color: 'purple'
                  }
                }
            },
            // {
            //   name: 'Top 5',
            //   type: 'effectScatter',
            //   coordinateSystem: 'bmap',
            //   data: _this.convertData(_this.scatterMapData.sort(function (a, b) {
            //     return b.value - a.value;
            //   }).slice(0, 6)),
            //   symbolSize: function (val) {
            //     return val[2] * 5;
            //   },
            //   showEffectOn: 'render',
            //   rippleEffect: {
            //     brushType: 'stroke'
            //   },
            //   hoverAnimation: true,
            //   label: {
            //     normal: {
            //       formatter: '{b}',
            //       position: 'right',
            //       show: true
            //     }
            //   },
            //   itemStyle: {
            //     normal: {
            //       color: 'purple',
            //       shadowBlur: 10,
            //       shadowColor: '#333'
            //     }
            //   },
            //   zlevel: 1
            // }
          ]
        };
        // _this.dom.clear();
        _this.dom.setOption(option)
      }
    }
  }
</script>

<style scoped>

</style>
