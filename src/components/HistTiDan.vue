<template>
    <div id="">
      <Search ref="Srch" />
      <div id="TD">
      <el-table
        :data="tableData"
        style="width: 100%; margin-bottom: 20px;;"
        row-key="id"
        stripe
        default-expand-all
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
      
        <el-table-column prop="No"  width="100px"></el-table-column>
        <el-table-column prop="name"  width="250px"></el-table-column>
        <el-table-column prop="rate"   width="100px"></el-table-column>
        <el-table-column prop="level"   width="110px"></el-table-column>
        <el-table-column prop="tags" ></el-table-column>
      </el-table>
      
     
    </div>
    </div>
  </template>
  
  <script>
    export default {
      data() {
        return {
          tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
        }
      }
    }
  </script>