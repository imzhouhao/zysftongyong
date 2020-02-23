<template>
  <div>
    <Button type="info" size="default" @click="exportData(1)" style="margin-right: 5px;" >
      <Icon type="ios-download-outline"></Icon>导出为CSV
    </Button>
    <Button  type="success" icon="md-download" style="margin-right: 5px;" :loading="exportLoading" @click="exportSourceDataExcel">导出为EXCEL</Button>
    <Button  type="warning" icon="md-download" :loading="exportLoading" @click="exportSourceDataJson">导出为JSON</Button>
  </div>
</template>
<script>
/* eslint-disable */
import excel from '@/libs/excel'
export default {
  name: 'export-data-component',
  props: {
    title: {
      type: String
    },
    tableData: {
      type: Array
    },
    columns: {
      type: Array
    },
    tableRef: {
      type: Object
    }
  },
  data(){
    return {
      exportLoading: false,
    }
  },
  methods: {
    exportData (type) {
      let _this = this;
      if (type === 1) {
        _this.tableRef.exportCsv({
          filename: _this.title
        });
      }
    },
    exportSourceDataExcel(){
      this.exportExcel(this.tableData)
    },
    exportSourceDataJson(){
      this.exportJson(this.tableData)
    },
    exportExcel (columnData) {
      let _this = this;
      if (columnData.length > 0) {
        _this.exportLoading = true;
        const columns = _this.columns, tableTitle = _this.title;
        const params = {
          title: columns.map( c => {
            return c['title']
          }),
          key: columns.map( c => {return c['key']}),
          data: columnData,
          autoWidth: true,
          filename: tableTitle,
        };
        excel.export_array_to_excel(params)
        _this.exportLoading = false
      } else {
        _this.$Message.info('表格数据不能为空！')
      }
    },
    exportJson(tableData){
      //下载为json文件
      let aLink = document.createElement('a');
      aLink.download = this.title+".json";
      aLink.style.display = 'none';
      // 字符内容转变成blob地址
      let blob = new Blob([JSON.stringify(tableData, null, "\t")], {type: "application/json"});
      aLink.href = URL.createObjectURL(blob);
      // 触发点击
      document.body.appendChild(aLink);
      aLink.click();
      // 然后移除
      document.body.removeChild(aLink);
    }
  }
}
</script>
