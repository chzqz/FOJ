<template>
    

  <ReturnVnodes :time="this.time"></ReturnVnodes>


</template>

<script>
import Vuetext from '../components/Vuetext.vue'
import ReturnVnodes from '../components/ReturnVnodes.vue';

export default {
    data(){
      return{
       time:1
      }
    },
    components: {
      Vuetext,
      ReturnVnodes
    },
  };
</script>

<style>

</style>