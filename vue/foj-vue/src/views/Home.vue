<template>
  <div id="ZT" style="margin-top: 35px; padding-bottom: 40px; height: 830px;">
    <el-main style="height: 780px;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); background-color: rgb(255, 255, 255);" ><TiDan id="TD"></TiDan></el-main>
  </div>
</template>
  
  <script>
  import CommonAside from '../components/CommonAside.vue'
  import Snowflake from '../components/Snowflake.vue'
  import daohang from '../components/daohang.vue'
  import TiDan from '../components/TiDan.vue'
  
  
  export default {
    data() {
      return {};
    },
    components: {
      CommonAside,
      Snowflake,
      daohang,
      TiDan,
    
    },
  };
  </script>
  
  <style scoped>
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
 
  
  </style>
  