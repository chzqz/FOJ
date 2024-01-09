<template>
    <div class="a" >
   
    
   
  <div id="PB">
<!-- <el-card id="card0" class="box-card"> -->
  <div class="PageHeader">
  <el-page-header @back="goBack" content="题目描述">
      </el-page-header>
  <div id="card0">

  <el-card id="card1" class="box-card">
    <span id="Title">{{ questions_name }}</span>
  </el-card>
  <el-card id="card2" class="box-card">
  </el-card>

 </div> 
<!-- </el-card> -->
</div>

</div>
</div>

</template>

<script>
export default {
  data() {
  return{
    questions_name:'111',
  };
},
  methods: {
      goBack() {
        console.log('go back');
      }
    }
  
}
</script>

<style scoped>
/* 使用深度作用选择器，限制样式仅作用于 .PageHeader 组件内部 */
.a /deep/ .PageHeader {
  background-color:rgb(253, 253, 253);
}
.a{
    width:1440px;
 
}
 .text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }

  .box-card {
    width: 480px;
  }
  #PB{
    display: flex;
  width: 1440px;
  margin-top: 5px;
}
#card0{
  width:1440px;
  display: flex;
  align-items: center; 
}
#card1{
width: 700px;
height: 650px;
margin-left: 12px;
margin-top: 10px;
border-radius: 20px; 
}
#card2{
width: 700px;
height: 650px;
margin-left: 5px;
margin-top: 10px;
border-radius: 20px; 
}
#Tabs{
  margin-left: -4%;
  margin-top:-4%;
  width: 800px;

}
.PageHeader{
  background-color: aliceblue;
}

#Title{
  font-size: 26px; 
 
}
  
</style>