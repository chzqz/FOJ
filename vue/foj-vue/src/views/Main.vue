<template>
  
  <div id="BG">
    <daohang></daohang>
  <div id="DH">
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
    events: {
      'reload': function () {
        console.log(daohang);
      }
    }
    
  };
</script>

<style scoped>
#BG{
  background: linear-gradient(200deg,#e0faf2, #b3d4f7); /* 修改这里的颜色值 */
  min-height: 100%;
}
#DH{
  width:1440px;
  margin:auto;
  

}
</style>
