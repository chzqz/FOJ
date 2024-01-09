<template>
    <el-row class="demo-avatar demo-basic">
      <el-col :span="12">
        <div class="demo-basic--circle">
            <div class="block"><el-avatar :size="40" :src="circleUrl"></el-avatar></div>
        </div>
      </el-col>  
     
    </el-row>
  </template>
 
  <script>
  export default {
    props: ['circleUrl'],
    data () {
      return {
        circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
       
        sizeList: ["large", "medium", "small"]
      }
    }
  }

</script>
  