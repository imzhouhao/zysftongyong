<template>
  <div>
    <Row >
      <i-col span="24">
        <div>
          <Form inline>
            <FormItem label="展示方向">
              <Checkbox v-model="horizontal">Horizontal</Checkbox>
            </FormItem>
            <FormItem label="折叠">
              <Checkbox v-model="collapsable">Collapsable</Checkbox>
            </FormItem>
            <FormItem label="展开">
              <Checkbox v-model="expandAll" @on-change="expandChange">Expand All</Checkbox>
            </FormItem>
            <FormItem label="节点背景">
              <Select v-model="labelClassName" clearable style="width: 150px;" @on-change="changeBackColor">
                <Option value="bg-white">bg-white</Option>
                <Option value="bg-orange">bg-orange</Option>
                <Option value="bg-gold">bg-gold</Option>
                <Option value="bg-gray">bg-gray</Option>
                <Option value="bg-lightpink">bg-lightpink</Option>
                <Option value="bg-tomato">bg-tomato</Option>
              </Select>
            </FormItem>
            <FormItem label="节点背景">
              <Button @click="exportData">导出数据</Button>
            </FormItem>
          </Form>
        </div>
      </i-col>
    </Row>
    <Row>
      <i-col span="24">
        <div class="tree-center">
          <org-tree name="test"
                    :data="data"
                    :horizontal="horizontal"
                    :collapsable="collapsable"
                    :label-class-name="labelClassName"
                    :render-content="renderContent"
                    @on-expand="onExpand"
                    @on-node-click="onNodeClick"
          />
        </div>
      </i-col>
    </Row>
    <div>
      <Modal v-model="modalShow" title="信息查看" ok-text="确定" @on-ok="handleAdd" :loading="modalShowLoading">
        <Form :model="objForm" :label-width="80">
          <FormItem label="ID" prop="id">
            <p v-if="disabled">
              {{objForm.id}}
            </p>
            <Input v-model="objForm.id" placeholder="..." v-else disabled/>
          </FormItem>
          <FormItem label="父id" prop="parentId">
            <p v-if="disabled">
              {{objForm.parentId}}
            </p>
            <Input v-model="objForm.parentId" placeholder="父id..." v-else disabled/>
          </FormItem>
          <FormItem label="名称" prop="name">
            <p v-if="disabled">
              {{objForm.name}}
            </p>
            <Input v-model="objForm.name" placeholder="名称..." v-else />
          </FormItem>
          <FormItem label="创建时间" prop="ctime">
            <p v-if="disabled">
              {{objForm.ctime}}
            </p>
            <Input v-model="objForm.ctime" placeholder="创建时间..." v-else disabled/>
          </FormItem>
          <FormItem label="更新时间" prop="utime">
            <p v-if="disabled">
              {{objForm.utime}}
            </p>
            <Input v-model="objForm.utime" placeholder="更新时间..." v-else disabled/>
          </FormItem>
        </Form>
        <Button type="primary" @click="addNewChild"  style="margin-right: 5px;" v-if="disabled">添加子节点</Button>
        <Button type="success" @click="addNewChild(false)" style="margin-right: 5px;" v-if="disabled">修改</Button>
        <Button type="error" @click="deleteItem" style="margin-right: 5px;" v-if="disabled">删除</Button>
      </Modal>
      <Modal v-model="modalDel" title="删除信息" @on-ok="handleDelete" :loading="modalDelLoading">
        <p style="text-align: center"><strong>您确定要删除该信息吗？</strong></p>
        <div slot="footer">
          <Button type="error" size="large" long :loading="modalDelLoading" @click="handleDelete">删除</Button>
        </div>
      </Modal>
    </div>
  </div>
</template>
<script>
/* eslint-disable */
import OrgTree from '@/components/tree'
import { list2Tree } from '@/libs/tree'
import { traverseTreeAndSetVal } from '@/libs/tree'
import * as IndustrialCategoryAPI from '@/api/industrial-category/api'
import {exportJson} from "@/libs/json";

export default {
  components:{
    OrgTree
  },
  data() {
    return {
      disabled: false,
      modalDel: false,
      modalDelLoading: false,
      modalShow: false,
      modalShowLoading: false,
      objForm: {},
      data: {
        id: -1,
        parentId: null,
        name: "产业链分类",
        type:'descr',//描述性质的内容
        children:[],
        expand:true,
      },
      horizontal: false,
      collapsable: true,
      expandAll: false,
      labelClassName: "bg-white",
    };
  },
  computed:{
  },
  mounted(){
    this.getList();
  },
  methods: {
    exportData(){//导出json数据
      exportJson('产业分类数据',this.data)
    },
    handleAdd(){
      let _this = this;
      if(_this.disabled) {
        _this.modalShow = false;
        return false;
      }
      if(!_this.objForm['id']) {
        IndustrialCategoryAPI.addObj(_this.objForm).then(resp=>{
          _this.$Message.success('添加成功！');
          _this.modalShow = false;
          _this.getList();
        }).catch(err => {
          this.$Message.error('新添加失败!');
        })
      }else{
        IndustrialCategoryAPI.putObj(_this.objForm).then(resp=>{
          _this.$Message.success('修改成功！');
          _this.modalShow = false;
          _this.getList();
        }).catch(err => {
          _this.$Message.error('修改失败!' );
        })
      }
    },
    handleDelete () {
      let _this = this;
      if(!_this.objForm['parentId']){
        return false;
      }
      let children = _this.objForm.children;
      if(children && children.length > 0) {
        this.$Message.error({
          content:"有子节点不能直接删除",
          duration: 2
        })
        return false;
      }
      IndustrialCategoryAPI.delObj(_this.objForm['id']).then(res => {
        _this.$Message.success('删除成功！');
        _this.modalDel = false;
        _this.modalShow = false;
        _this.getList();
      }).catch(err => {
        _this.$Message.error('删除失败，请重试！');
      })
    },
    getList(){
      let _this = this;
      let params = {};
      IndustrialCategoryAPI.fetchList(params).then( resp => {
        let list = resp.data.data;
        if(list && list.length > 0) {
          _this.data.children = list2Tree(list);
        }
      }).catch(err => {
        _this.$Message.error("获取列表信息失败");
      })
    },
    renderContent(h, data) {
      let showName = data['name'];
      return showName;
    },
    onExpand(data) {
      if(!data) {
        return false;
      }
      if ("expand" in data) {
        data.expand =  !data.expand;
        if (!data.expand && data.children && data.children.length > 0) {
          this.collapse(data.children);
        }
      } else {
        this.$set(data, "expand", true);
      }
    },
    addNewChild(flag){
      let _this = this;
      if(!_this.objForm['parentId']){
        return false;
      }
      _this.disabled = false;
      if(flag) {
        let parentId = _this.objForm['id'];
        _this.objForm = {
          id:'',
          parentId: parentId,
          name:'',
          ctime:'',
          utime:'',
        };
      }
      _this.modalShow = true;
    },
    deleteItem() {
      let _this = this;
      _this.modalDel = true;
    },
    onNodeClick(e, data) {
      let _this = this;
      console.log('click',data)
      _this.disabled = true;
      _this.objForm = data;
      _this.modalShow = true;
    },
    collapse(list) {
      let _this = this;
      list.forEach(function(child) {
        if (child.expand) {
          child.expand = false;
        }
        child.children && _this.collapse(child.children);
      });
    },
    expandChange() {
      this.toggleExpand(this.data, this.expandAll);
    },
    toggleExpand(data, val) {
      let _this = this;
      if (Array.isArray(data)) {
        data.forEach(function(item) {
          _this.$set(item, "expand", val);
          if (item.children) {
            _this.toggleExpand(item.children, val);
          }
        });
      } else {
        this.$set(data, "expand", val);
        if (data.children) {
          _this.toggleExpand(data.children, val);
        }
      }
    },
    changeBackColor(){
      console.log(this.labelClassName)
    },
  },
};
</script>
<style type="text/css" lang="less">
  .tree-center{
    text-align: center;
  }
  .node-label-color(@backcolor){
    background-color:@backcolor;
  }
  .org-tree-node-label {
    white-space: nowrap;
    font-size: 15px;
    font-weight: bold;
    cursor: pointer;
  }
  .bg-white {
    background-color: white;
  }
  .bg-orange {
    background-color: orange;
  }
  .bg-gold {
    background-color: gold;
  }
  .bg-gray {
    background-color: gray;
  }
  .bg-lightpink {
    background-color: lightpink;
  }
  .bg-chocolate {
    background-color: chocolate;
  }
  .bg-tomato {
    background-color: tomato;
  }
</style>
