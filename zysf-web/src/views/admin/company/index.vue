<template>
  <div>
    <Card shadow>
      <div slot="title">
        <h4>信息管理</h4>
      </div>
      <div slot="extra">
        <Button type="primary" size="small" icon="ios-briefcase" @click="showExport">数据导出</Button>
      </div>
      <div>
        <Button type="success" style="margin-bottom: 10px;margin-right: 5px;" icon="md-trash"
                @click="clearOrder">清除排序</Button>
        <Button type="success" style="margin-bottom: 10px;margin-right: 5px;" icon="md-add" @click="show(-1)">添加</Button>
        <Select v-model="type"
                @on-change="getPage"
                clearable
                style="width:150px;margin-bottom: 10px;margin-right: 5px;">
          <Option value="name">企业名称</Option>
          <Option value="address">企业地址</Option>
        </Select>
        <Input v-model="searchKeyword" placeholder="请输入搜索关键词..."
               @on-blur="getPage"
               @on-change="getPage"
               clearable
               style="width: 200px;margin-bottom: 10px;" />
      </div>
      <Table border size="large"
             ref="table"
             :columns="columns"
             :data="objList"
             @on-sort-change='sortChange'
      ></Table>
      <br/>
      <Page style="text-align: center"
            @on-change="handleChange"
            @on-page-size-change='handlePageSize'
            :total="page.total"
            :page-size-opts="page.pageSizeOpts"
            show-sizer
            show-elevator
            show-total
      />
    </Card>
    <div>
      <Modal v-model="modalDel" title="删除信息" @on-ok="handleDelete" :loading="modalDelLoading">
        <p style="text-align: center"><strong>您确定要删除该信息吗？</strong></p>
        <div slot="footer">
          <Button type="error" size="large" long :loading="modalDelLoading" @click="handleDelete">删除</Button>
        </div>
      </Modal>
      <Modal v-model="modalShow" title="信息查看" @on-ok="handleAdd" ok-text="确定" :loading="modalShowLoading">
        <Form :model="objForm" :label-width="80">
          <FormItem label="主键id" prop="id">
            <p v-if="disabled">
              {{objForm.id}}
            </p>
            <Input v-model="objForm.id" placeholder="主键..." v-else disabled/>
          </FormItem>
          <FormItem label="企业名称" prop="name">
            <p v-if="disabled">
              {{objForm.name}}
            </p>
            <Input v-model="objForm.name" placeholder="企业名称..." v-else />
          </FormItem>
          <FormItem label="信用编码" prop="code">
            <p v-if="disabled">
              {{objForm.code}}
            </p>
            <Input v-model="objForm.code" placeholder="信用编码..." v-else />
          </FormItem>
          <FormItem label="企业地址" prop="address">
            <p v-if="disabled">
              {{objForm.address}}
            </p>
            <Input v-model="objForm.address" placeholder="企业地址..." v-else />
          </FormItem>
          <FormItem label="经度" prop="lng">
            <p v-if="disabled">
              {{objForm.lng}}
            </p>
            <Input v-model="objForm.lng" placeholder="经度..." v-else />
          </FormItem>
          <FormItem label="纬度" prop="lat">
            <p v-if="disabled">
              {{objForm.lat}}
            </p>
            <Input v-model="objForm.lat" placeholder="纬度..." v-else />
          </FormItem>
          <FormItem label="创建时间" prop="lat">
            <p v-if="disabled">
              {{objForm.lat}}
            </p>
            <Input v-model="objForm.lat" placeholder="创建时间..." v-else disabled/>
          </FormItem>
          <FormItem label="lat" prop="lat">
            <p v-if="disabled">
              {{objForm.lat}}
            </p>
            <Input v-model="objForm.utime" placeholder="更新时间..." v-else disabled/>
          </FormItem>
        </Form>
      </Modal>
      <Modal v-model="modalShowExport" title="数据导出" width="50%"
             ok-text="确定">
        <export-component :columns="columns"
                          :tableRef="tableRef"
                          :title="'company-data'"
                          :tableData="objList"/>
      </Modal>
    </div>
  </div>
</template>
<script>
/* eslint-disable */
import * as CompanyAPI from '@/api/company/api'
import { initColumns } from './default-data'
import { toLine } from '@/libs/text'
import ExportComponent from '@/components/export'

export default {
  name: 'company-page',
  components: {
    ExportComponent
  },
  data() {
    return {
      type: null,
      disabled: false,
      modalDel: false,
      modalDelLoading: false,
      modalShowExport: false,
      modalShow: false,
      modalShowLoading: false,
      searchKeyword: '',
      objForm: {},
      objList: [],
      page :{
        pageSizeOpts:[5, 10, 20, 30, 40, 100],
        total: 0, // 总页数
        current: 1, // 当前页数
        pageSize: 10, // 每页显示多少条,
        descs: ['ctime'],//排序字段
        ascs: []//排序字段
      },
      tableRef: null,//数据导出用
    }
  },
  computed:{
    columns(){
      return initColumns(this);
    },
  },
  mounted(){
    this.getPage();
  },
  methods:{
    getPage(){
      let _this = this;
      let page = _this.page, params = {};
      if(_this.type && _this.searchKeyword) {
        params[_this.type] = _this.searchKeyword;
      }

      let descs = page.descs.join();
      let ascs = page.ascs.join();
      let querystring = Object.assign({
        current: page.current,
        size: page.pageSize,
        "descs": descs,
        "ascs": ascs,
      }, params);
      CompanyAPI.fetchPage(querystring).then( resp => {
        _this.objList = resp.data.data.records;
        _this.page.total = resp.data.data.total;
        _this.page.current = resp.data.data.current;
      }).catch(err => {
        _this.$Message.error("获取列表信息失败");
      })
    },
    show(index, disabled){
      let _this = this;
      _this.disabled = disabled;
      if(index > -1){
        _this.objForm = _this.objList[index];
      } else {
        _this.objForm = {
          id:'',
          name:'',
          code:'',
          address:'',
          lng:'',
          lat:'',
        }
      }
      _this.modalShow = true;
    },
    delete(index) {
      let _this = this;
      _this.modalDel = true;
      _this.objForm = _this.objList[index];
    },
    handleDelete () {
      let _this = this;
      CompanyAPI.delObj(this.objForm['id']).then(res => {
        _this.$Message.success('删除成功！');
        _this.modalDel = false;
        _this.getPage();
      }).catch(err => {
        _this.$Message.error('删除失败，请重试！');
      })
    },
    handleAdd(){
      let _this = this;
      if(_this.disabled) {
        _this.modalShow = false;
        return false;
      }
      if(!_this.objForm['id']) {
          CompanyAPI.addObj(_this.objForm).then(resp=>{
          _this.$Message.success('添加成功！');
          _this.getPage();
        }).catch(err => {
          this.$Message.error('添加新失败!');
        })
      }else{
          CompanyAPI.putObj(_this.objForm).then(resp=>{
          _this.$Message.success('修改成功！');
          _this.getPage();
        }).catch(err => {
          _this.$Message.error('修改失败!' );
        })
      }
    },
    sortChange(obj){
      let order = obj.order;
      let key = toLine(obj.key);
      if(order === 'asc') {
        let keyIndex = this.page.ascs.indexOf(key);
        if( keyIndex !== -1) {
          this.page.ascs.splice(keyIndex,1);
        }
        this.page.ascs.unshift(key)
        this.page.descs = []
      } else if(order === 'desc') {
        let keyIndex = this.page.descs.indexOf(key);
        if( keyIndex !== -1) {
          this.page.descs.splice(keyIndex,1);
        }
        this.page.descs.unshift(key)
        this.page.ascs = []
      }
      this.getPage()
    },
    clearOrder(){
      this.page.descs = [];
      this.page.ascs = [];
      this.getPage();
    },
    handleChange(value){
      this.page.current = value;
      this.getPage()
    },
    handlePageSize(value){
      this.page.pageSize = value;
      this.getPage()
    },
    showExport(){
      this.modalShowExport = true;
      this.tableRef = this.$refs.table;
    }
  },
}
</script>
<style scoped>

</style>
