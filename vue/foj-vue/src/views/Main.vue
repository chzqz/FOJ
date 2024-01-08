<template>
  <div id="BG">
    <div id="DH">
  <daohang></daohang>
  <router-view></router-view>
  </div>
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

<style>
#BG{
  background: linear-gradient(200deg,#e0faf2, #b3d4f7); /* 修改这里的颜色值 */
  overflow: hidden;
  height: 100%;
}
#DH{
  width:1440px;
  margin:auto;
  

}
</style>
