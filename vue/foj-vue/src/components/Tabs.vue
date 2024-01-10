<template>
    <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
      <el-tab-pane
        :key="item.id"
        v-for="(item, index) in editableTabs"
        :label="'测试 '+(index+1)"
        :id="item.id"
      >
      <div>输入： <el-input v-model="item.input" placeholder=""></el-input></div>
      <div>输出： <el-input v-model="item.output" placeholder="" :disabled="true"></el-input></div>
      </el-tab-pane>
    </el-tabs>
  
  </template>
  
  <script>
  export default {
    props:{
        editableTabs:{
            type:Array,
        
        },
        tabIndex:{
            type:Number,
            default:0,
        }
             
       },
  
    data() {
      return {
       editableTabsValue:'1',
        // editableTabs: [{
        //   title: 'Case 1',
        //   name: '1',
        //   content: 'Tab 1 content'
        // }, {
        //   title: 'Case 2',
        //   name: '2',
        //   content: 'Tab 2 content'
        // },
        //  {
        //   title: 'Case 3',
        //   name: '3',
        //   content: 'Tab 3 content'
        // }],
        // tabIndex: 3,
  

       
      }
    },
    methods: {
      handleTabsEdit(targetid, action) {
        if (action === 'add') {
          let newInput = '';
          let newTabid = ++this.tabIndex ;
          this.editableTabs.push({
            title: 'New Tab',
            id: newTabid,
            input: newInput,
            output: '1'
          });
          this.editableTabsValue = newTabid-1+'';
        }
        if (action === 'remove') {
          let tabs = this.editableTabs;
          let activeid = this.editableTabsValue;
          if (activeid === targetid) {
            tabs.forEach((tab, index) => {
              if (tab.id === targetid) {
                let nextTab = tabs[index + 1] || tabs[index - 1];
                if (nextTab) {
                  activeid = nextTab.id;
                }
              }
            });
          }
          
          this.editableTabsValue = activeid;
          this.editableTabs = tabs.filter(tab => tab.id !== targetid);
        }
      }
    },
    Output(){

    }
  }
  </script>
  
  <style>
  
  </style>