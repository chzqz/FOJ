<template>
  <div id="DH">
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

<style>
#DH{
  margin-left: 11%;
}
</style>
