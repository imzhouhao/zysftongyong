<template>
  <div>
    <baidu-map :style="{'height': heightStr, width: '100%'}"
               :center="center"
               :zoom="zoom"
               :scroll-wheel-zoom="true"
               @ready="handler">
      <bm-scale anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-scale>
      <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
      <bm-overview-map anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :isOpen="true"></bm-overview-map>
      <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
                      :showAddressBar="true"
                      :autoLocation="true"></bm-geolocation>
      <bml-heatmap :data="heatmapData"
                   :max="100"
                   :radius="20">
      </bml-heatmap>
    </baidu-map>
  </div>
</template>

<script>
/* eslint-disable */
import { BmlHeatmap } from 'vue-baidu-map'
import * as CompanyAPI from '@/api/company/api'

export default {
    name: 'china-heat-map-page',
    components: {
      BmlHeatmap
    },
    data () {
      return {
        center: {lng: 116.46, lat: 39.92},
        zoom: 6,
        heatmapData:[],
      }
    },
    computed:{
      minHeight(){
        let minHeight= window.screen.height;
        return  minHeight * 0.7;
      },
      heightStr(){
        return this.minHeight+"px"
      }
    },
    mounted () {
      this.getList();
    },
    methods:{
      handler ({BMap, map}) {
        console.log(BMap, map)
      },
      getList(){
        let _this = this;
        let fieldList = ['id','name','lat','lng'], fields = fieldList.join();
        let params = {};
        let querystring = Object.assign({fields:fields}, params);
        CompanyAPI.fetchFieldsList(querystring).then( resp => {
          _this.heatmapData = resp.data.data;
        })
      },
    }
  }
</script>

<style scoped>

</style>
