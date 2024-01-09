<template>
    <div class="a" @sort-change="getParams" >
  <div id="PB">
<!-- <el-card id="card0" class="box-card"> -->
  <div class="PageHeader">
    <el-card id="card0" class="box-card" >
  <el-page-header @back="goBack" content="题目描述">
      </el-page-header>
    </el-card>
  <div id="Card" >

  <el-card id="card1" class="box-card" >
    <div>
    <span id="Title">{{ questionData.data.name }}</span>

    </div>
    <div id="Tag">
    <span v-for="(item, index) in questionData.data.tags" :key="index"> <el-tag :color="item.color" effect="dark">{{ item.name }}</el-tag> </span>
    </div>
    <div id="Text">
     
      <span>{{ questionData.data.description }}</span>
    </div>
    <div id="Tip">
      <div id="TipTitle">提示:</div>
      <span id="TipText">{{ questionData.data.tip }}</span>
    </div>
  </el-card>
  <div>
  <el-card id="card2" class="box-card">
  </el-card>
  <el-card id="card3" class="box-card">
  </el-card>
</div>
</div>
 </div>
<!-- </el-card> -->


</div>
</div>

</template>

<script>

export default {
  data() {
  return{
    questionData:[],
    questionData_id:'',
    
  };
},
  methods: {
      goBack() {
        console.log('go back');
      },
      
      getParams() {
           
            this.questionData_id = this.$route.query.questionData_id;
            console.log(2222, this.questionData_id);
      },
      Pagecreate()
      {
        this.getParams();
        console.log(3333, this.questionData_id);
        let url = '/user/question/'+this.questionData_id;

      //   const params = {  
      //     code:'',
      //     msg:'',
      //     data:{
      //       id:'',
      //       name:'',
      //       description:'',
      //       tip:'',
      //       maxTime:'',
      //       maxMemory:'',
      //       testcases:{
      //         input:'',
      //         output:'',
      //       },
      //       uid:'',
      //       passRate:'',
      //       tags:{
      //         id:'',
      //         name:'',
      //         color:'',
      //         level:''
      //       }
      //               }
      // };
    
       this.$axios.get(url)
    
        .then(response => {
          // 从响应中获取数据
          console.log(response.data);
          this.questionData = response.data;
  
        })
        .catch(error => {
          console.error('Error fetching question data:', error);
        })
      
      }
    },
    mounted() {
    // 组件挂载时初始化数据
    this.Pagecreate()
 
  },
  
}
</script>

<style scoped>
/* 使用深度作用选择器，限制样式仅作用于 .PageHeader 组件内部 */
.a /deep/ .PageHeader {
  /* background-color:rgb(253, 253, 253); */
}
.a{
    /* width:1440px; */
    width:100%;
  height: 100%;
 
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
 
  
  width:100%;
  height: 100%;
}
#Card{

  display: flex;
  align-items: center; 
}
#card0{
  width: 99%;
height: 60px;
margin-left: 12px;
margin-top: 10px;
border-radius: 20px; 
}
#card1{
width: 800px;
height: 800px;
margin-left: 12px;
margin-top: 10px;
border-radius: 20px; 
}
#card2{
width: 1090px;
height: 550px;
margin-left: 5px;
margin-top: -240px;
border-radius: 20px; 
}
#card3{
  width: 1090px;
  margin-top:5px;
  border-radius: 20px;
}
#Tabs{
  margin-left: -4%;
  margin-top:-4%;
  width: 800px;

}
.PageHeader{
   /* background-color: aliceblue;  */
  width:100%;
  
  /* background: linear-gradient(200deg,#dadada, #ececec); */
}

#Title{
  font-size: 26px; 
  font-weight: bold;

}
#Tag{
  margin-top:20px;
}
#Text{
margin-top:30px;
}
#Tip{
  margin-top:50px;
}
#TipTitle{
  font-size: 17px;
  font-weight: 550;

}



  
</style>