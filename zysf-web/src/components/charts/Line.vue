<template>
  <div ref="dom" class="charts chart-line"></div>
</template>

<script>
/* eslint-disable */
import echarts from 'echarts'
import shine from './shine.json'
import { on, off } from '@/libs/util'
echarts.registerTheme('shine', shine);
export default {
  name: 'ChartLine',
  props: {
    value: Object,
    legend: Object,
    text: String,
    subtext: String,
    showZoom: Boolean
  },
  data () {
    return {
      dom: null
    }
  },
  computed:{},
  methods: {
    resize () {
      this.dom.resize()
    },
    refresh () {
      let xAxisData = Object.keys(this.value);
      let valueData = Object.values(this.value);
      let legendInfo = [];
      for (let key in valueData[0]) {
        legendInfo.push(key)
      }
      let seriesData = [], minValue=valueData[0][legendInfo[0]],maxValue=valueData[0][legendInfo[0]];
      for(let i = 0, len = legendInfo.length; i< len;i++){
        let litem = legendInfo[i];
        let itemValues = valueData.map(vd =>{
          return vd[litem];
        });
        let min1 = Math.min.apply(Math, itemValues);
        let max1 = Math.max.apply(Math, itemValues);
        minValue = min1>minValue?minValue:min1;
        maxValue = max1>maxValue?max1:maxValue;
      }
      if (valueData.length > 0) {
        for (let key in valueData[0]) {
          seriesData.push({name: key, data: [], type: 'line'})
        }
      }
      for (let i = 0; i < valueData.length; i++) {
        let cnt = 0
        for (let key in valueData[i]) {
          seriesData[cnt++].data.push(valueData[i][key])
        }
      }
      let option = {
        title: {
          text: this.text,
          subtext: this.subtext,
          x: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: this.legend,
          // top: '15'
        },
        toolbox: {
          show: true,
          orient: 'vertical',
          top: 'center',
          feature: {
            dataView: {show: true},
            saveAsImage: {show: true}
          },
          right: '20'
        },
        grid: {
          left: '10%',
          right: '10%',
          bottom: '15%',
          top: '15%',
          containLabel: true
        },
        calculable: true,
        xAxis: {
          type: 'category',
          data: xAxisData
        },
        yAxis: {
          type: 'value',
          min: minValue,
          max: maxValue
        },
        series: seriesData
      }
      this.dom.setOption(option)
    }
  },
  watch: {
    value: {
      handler () {
        if (this) {
          this.refresh()
        }
      },
      deep: true
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.dom = echarts.init(this.$refs.dom, 'shine')
      this.refresh()
      on(window, 'resize', this.resize)
    })
  },
  beforeDestroy () {
    off(window, 'resize', this.resize)
  }
}
</script>

<style scoped>

</style>
