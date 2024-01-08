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
    >
    
      <el-table-column prop="state" label="状态" width="100px"></el-table-column>
      <el-table-column prop="name" label="题目" sortable width="250px"></el-table-column>
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
import Search from './Search.vue'
export default {
  
  data() {
    return {
      tableData: [], // 从后端获取的表格数据
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
      // 这里使用假数据，你需要替换成从后端获取的数据
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      const sortedData = this.generateFakeData().sort((a, b) => {
        const propA = this.sortOptions.prop ? a[this.sortOptions.prop] : null;
        const propB = this.sortOptions.prop ? b[this.sortOptions.prop] : null;

        if (propA === propB) {
          return 0;
        }

        if (this.sortOptions.order === 'ascending') {
          return propA < propB ? -1 : 1;
        } else {
          return propA > propB ? -1 : 1;
        }
      });

      this.tableData = sortedData.slice(start, end);
    },
    // 生成假数据的逻辑，实际项目中需要替换成从后端获取的数据
    generateFakeData() {
      const data = [];
      for (let i = 1; i <= this.totalItems; i++) {
        data.push({
          id: i,
          state:1,
          name: `题目 ${i}`,
          rate: Math.random() * 100, // 生成随机通过率
          level: Math.floor(Math.random() * 3) + 1, // 生成随机难度（1-3）
   
          // 其他字段...
        });
      }
      return data;
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

