<template>
   <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
  <el-tab-pane
    :key="item.name"
    v-for="(item, index) in editableTabs"
        :label="'测试 '+(index+1)"
        :name="item.name"
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
  handleTabsEdit(targetName, action) {
    console.log(1111,this.editableTabsValue);
    if (action === 'add') {
      let newTabName = ++this.tabIndex + '';
      let newInput = ''; // 初始化输入值
      this.editableTabs.push({
        title: 'New Tab',
        name: newTabName,
        input: newInput,
        output: '1'
      });
      this.editableTabsValue = newTabName;
    }
    console.log(999,this.tabIndex)
    if (action === 'remove') {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }

      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.name !== targetName);
    }
    console.log(888,this.tabIndex)
  }
}
}
  </script>
  
  <style>
  </style>