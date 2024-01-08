<template>
<div id="ZT">
    <container>
      
     
      <el-main ><TiDan id="TD"></TiDan></el-main>
      
    </container>
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
  #ZT{
     margin-left: -11%; 
  
  }
  
  </style>
  