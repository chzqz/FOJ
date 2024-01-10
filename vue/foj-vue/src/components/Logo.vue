<template>
    <div id="lg" >
      <!-- 使用本地图片路径显示图片 -->
      <img class="logo_img" :src="localImageUrl" alt="本地图片"  >
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
    methods: {

    },
    props: {
      width: {
        type: String,
        default: '40px'
      },
      height: {
        type: String,
        default: '40px'
      }
    }
  }; 
</script>
  
<style scoped>
  .logo_img {
    width: v-bind(width); 
    height:v-bind(height);
  }
  
</style>
