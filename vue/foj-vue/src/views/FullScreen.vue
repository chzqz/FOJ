<template>
  
    <div id="BG">
      <daohang></daohang>
      
  
    <router-view></router-view>
    </div>
  </template>
  
  <script>
  import daohang from'../components/daohang.vue'
  export default {
      data() {
        return {};
      },
      components: {
        
        daohang,
     
      
      },
    };
  </script>
  
  <style scoped>
  #BG{
    background: linear-gradient(200deg,#dadada, #ececec); /* 修改这里的颜色值 */
    min-height: 100%;
    min-width:100%;
    /* width:100%; */
    /* height: 100%; */
  }
  </style>
  