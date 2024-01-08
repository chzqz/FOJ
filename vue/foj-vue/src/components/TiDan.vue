<template>
  <div >
    <Search ref="Srch" />
    <div id="TD">
    <el-table
      :data="tableData"
      style="width: 100%; margin-bottom: 20px;;"
      row-key="id"
      stripe
      default-expand-all
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      @sort-change="handleSortChange"
    >
    
      <el-table-column prop="state" label="状态" width="100px" ></el-table-column>
      <el-table-column prop="name" label="题目" sortable  width="250px"></el-table-column>
      <el-table-column prop="rate" label="通过率" sortable width="100px"></el-table-column>
      <el-table-column prop="level" label="难度" sortable width="110px"></el-table-column>
      <el-table-column prop="tags" label="标签"></el-table-column>
    </el-table>
    
    <div class="block">
      <el-pagination
        layout="prev, pager, next"
        :total="totalItems"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
  </div>
  </div>
</template>

<script>
import axios from 'axios';
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
      let url = 'http://localhost:8080/user/questions/list';

      const params = {  
        id: '', // 题目id，根据你的需求传递
        name: '', // 题目名，根据你的需求传递
        level: '', // 难度 (0:简单, 1:中等, 2:困难)，根据你的需求传递
        tags: [], // 标签id集合，根据你的需求传递
        page: 0,
        pageSize:10,
      
      };

      // 发送HTTP GET请求
      axios.get(url, {params} )
      .then(response => {
        // 从响应中获取数据
        console.log(params);
        const responseData = response.data;
        
        // 这里假设 responseData 是一个包含所有数据的数组
        this.tableData = responseData.slice(start, end);

        // 如果后端提供了总条目数，你可能需要更新总条目数
        // this.totalItems = responseData.length;
      })
      .catch(error => {
        console.error('Error fetching table data:', error);
      });
    },
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

