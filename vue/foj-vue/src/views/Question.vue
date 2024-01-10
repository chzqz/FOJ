<template>
   <div >
    <div id="ZT">
        <el-main >
          <el-button id="Create" type="primary" icon="el-icon-edit" @click="openDialog"></el-button>
 
            <div class=" input-box mb20">
	<input type="text" class="input" />
	<span class="span">搜</span>
</div>
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
  </el-main>
    </div>
  </div>
</template>

<script>
import Vuetext from '../components/Vuetext.vue'

  export default {
    components: {
      Vuetext
  },
    methods: {
       
    openDialog() {
        const h = this.$createElement;
        this.$msgbox({
         
          message: h('Vuetext'),
         
          beforeClose: (action, instance, done) => {
            if (action === 'confirm') {
              instance.confirmButtonLoading = true;
              instance.confirmButtonText = '执行中...';
              setTimeout(() => {
                done();
                setTimeout(() => {
                  instance.confirmButtonLoading = false;
                }, 300);
              }, 3000);
            } else {
              done();
            }
          }
        }).then(action => {
          this.$message({
            type: 'info',
            message: 'action: ' + action
          });
        });
      }

  }
}
  
  
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
.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    height: 1000px;
    width:1000px;
    margin:auto;
    border-radius: 20px; /* 调整圆角半径根据需要 */
    margin-top: 1%;
   
  }
  
  #dh {
    width: 100%;
    margin-left: -10%;
    margin-top: 0%;
    background-color: rgb(255, 255, 255);
  }
  #TD{
    margin-top: 2%;

    border-radius: 20px;
  
  }
  #Create{
    margin-right: 100px;
    border-radius: 90px;
  }
</style>