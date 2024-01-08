<template>
    <div id="lg">
  
      <!-- 使用本地图片路径显示图片 -->
      <img :src="localImageUrl" alt="本地图片" style="width: 40px; height:40px;">
  
      <!-- 遍历 fits 数组并显示每个适应选项 -->

    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        fits: ['scale-down'],
        localImageUrl: require('@/assets/logo.png'), // 请替换为你的本地图片路径
      };
    },
  };
  </script>
  
  <style scoped>
  
  #lg{
   height: 10%;
 }
  </style>