
<template>
  <div >
    <Search  @fromchild="handleSelectionChange" />  <!-- ref="Srch" -->
    <div id="TD">
      <el-table
    :data="tableData"
    style="width: 100%"  >
    <el-table-column
      label="状态"
      width="80">
      <template slot-scope="scope">
      
        <span style="margin-left: 10px">{{ scope.row.status }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="题目id"
      width="100">
      <template slot-scope="scope">
      
        <span style="margin-left: 10px">{{ scope.row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="题目名"
      width="200">
      <template slot-scope="scope">
        <!-- <el-popover trigger="hover" placement="top">
          <p>姓名: {{ scope.row.name }}</p>
          <p>住址: {{ scope.row.address }}</p>
          <div slot="reference" class="name-wrapper"> -->
            <!-- <el-tag size="medium">{{ scope.row.name }}</el-tag>
          
        </el-popover> -->
        <span style="margin-left: -10px" @click="Searchname">
          <el-button type="text" @click="Searchname(scope.row.id)"> {{ scope.row.name }}</el-button>
        </span>
      </template>
    </el-table-column>
    <el-table-column label="难度" width="150">
    <template slot-scope="scope">
      <span style="margin-left: 0px">{{ formatDifficulty(scope.row) }}</span>
    </template>
  </el-table-column>
  <el-table-column label="通过率" width="180">
    <template slot-scope="scope">
      <span style="margin-left: 0px">{{scope.row.passRate.toFixed(2) }}</span>
    </template>
  </el-table-column>
    <el-table-column
      label="标签"
      >
      <template slot-scope="scope">
      
    
        <span v-for="(item, index) in scope.row.tags.slice(0,2)" :key="index"> <el-tag :color="item.color" effect="dark">{{ item.name }}</el-tag> </span>
      </template>
    </el-table-column>
  </el-table>
    
    <div class="block">
      <el-pagination    
        layout="prev, pager, next"
        :total="totalItems"
        @current-change="handleCurrentChange"
      >
    </el-pagination> 
    </div>
  </div>
  </div>
</template>
<script>

import Search from './Search.vue';

export default {
  data() {
    return {
      tableData: [], // 从后端获取的数据
      totalItems: 100, // 从后端获取的总条目数
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示的条目数
      sortOptions: {
        prop: '', // 当前排序的属性名
        order: '', // 当前排序的顺序，可选值为 'ascending' 或 'descending'
      },
      selectionOptions: {
        value1: '',
        value2: [],
        // 添加其他需要的参数
      }
      
    };
  },
  components: {
    Search
  },
  methods: {
    // 处理页码变化
    handleCurrentChange(page) {
      this.currentPage = page;
      // 根据当前页码从后端获取相应的数据
      this.fetchTableData();
    },
    // 处理表格列排序
    handleSortChange({ prop, order }) {
      this.sortOptions.prop = prop;
      this.sortOptions.order = order;
      // 根据当前排序条件从后端获取相应的数据
      this.fetchTableData();
    },
    // 从后端获取表格数据的逻辑
    fetchTableData() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      let url = '/user/questions/list';
      
      const params = {  
        uid:'',
        id: '', // 题目id，根据你的需求传递
        name: '', // 题目名，根据你的需求传递
        level: this.selectionOptions.value1, // 使用选择框的值作为搜索条件
   
        tags: this.selectionOptions.value2, // 使用选择框的值作为搜索条件
        page: this.currentPage,
        pageSize: this.pageSize,
      };
 
      // 发送HTTP GET请求
      this.$axios.get(url, { params })
        .then(response => {
          // 从响应中获取数据
          this.tableData = response.data.data.data;
  
          // 如果后端提供了总条目数，你可能需要更新总条目数
          // this.totalItems = response.data.data.total;
        })
        .catch(error => {
          console.error('Error fetching table data:', error);
        });
    },
    formatPassRate(row) {
      // 自定义渲染函数，保留两位小数
      return row.passRate.toFixed(2);
    },
    formatDifficulty(row) {
      switch (row.level) {
        case 0:
          return '简单';
        case 1:
          return '普通';
        case 2:
          return '困难';
        default:
          return '';
      }
    },
    handleSelectionChange(res) {
    
      this.selectionOptions = res;
      // 根据选择框的值更新排序条件
      // this.updateSortOptions();
      // 重新获取表格数据
      this.fetchTableData();
    },
    Searchname(ID){
  
      this.$router.push(
        { 
          path:'/answer',
          query: {
              questionData_id:ID ,
                        }
       });
      
    }
  },
  mounted() {
    // 组件挂载时初始化数据
    
    this.fetchTableData();
  },
};
</script>


<style> 
#TD{
  margin-top: 2%;
}
/* 设置一个卡片元素的基本样式 */
.card {
 
  border-radius: 50px; /* 设置圆角半径为50px */
  background: #e0e0e0; /* 设置背景颜色为灰色 */
  /* 设置两个颜色的阴影效果，形成凸起和凹陷的效果 */
  box-shadow:
    20px 20px 60px #bebebe, /* 右下方的深灰色阴影 */
    -20px -20px 60px #ffffff; /* 左上方的白色阴影 */
}
</style>

