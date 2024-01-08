<template>
  
  <div class="WK">
    <div id="tx" class="flex-container">
      <el-col :span="12">
        <div class="demo-basic--circle"></div>
        <div class="block">
          <el-avatar shape="square" :size="90" :src="squareUrl"></el-avatar>
        </div>
      </el-col>
      <div id="name">
        <!-- 将用户名作为标题传递给 el-descriptions -->
        <el-descriptions :title="username"></el-descriptions>
      </div>
    </div>
    <div id="xx">
      <el-container>
  <el-aside width="320px">
    <div id="xx1">
      <el-card shadow="always" :style="{ margin: cardMargin_1, width: cardWidth_1, height: cardHeight_1, aspectRatio: cardAspectRatio_1 }">

    <div>
      <el-descriptions title="个人简介">
      <el-descriptions-item label="用户邮箱">{{useraddress}}</el-descriptions-item>
    </el-descriptions>
      <el-descriptions title="">
        <el-descriptions title="">
      <el-descriptions-item label="用户创建日期">{{ usercreatetime }} </el-descriptions-item>
    </el-descriptions>
      <el-descriptions-item label="用户留言">{{ userword }} </el-descriptions-item>
    </el-descriptions>
    
    <el-button type="primary" plain>编辑个人资料</el-button>

    </div>
</el-card>
    </div>
</el-aside>
<el-container>
    
    <el-header height="280px">
  <el-row :gutter="0">
    <el-col :span="100">
      
        <el-card id="xx2" shadow="always" :style="{ margin: cardMargin_2, width: cardWidth_2, height: cardHeight_2, aspectRatio: cardAspectRatio_2 }">
          
          <div id="incard_1">
        <DateNumber/>
     
        <el-progress id="TGL" type="circle" :percentage="25"></el-progress>
      
       
       
         <span id="TGL_BT" >通过率</span>
      </div>
          
    
        </el-card>
      
    </el-col>
    <el-col :span="12">
      <div >
        <el-card id="xx3" shadow="always" :style="{ margin: cardMargin_2, width: cardWidth_2, height: cardHeight_2, aspectRatio: cardAspectRatio_2 }">
          
        </el-card>
      </div>
    </el-col>
  </el-row>
</el-header>
    <el-main>
   
      <div >
  <el-card id="xx4" shadow="always" :style="{ margin: cardMargin_3, width: cardWidth_3, height: cardHeight_3, aspectRatio: cardAspectRatio_3 }">
    <span>历史记录</span>
  
  <HistTiDan/>
</el-card>
   </div>
    </el-main>
  </el-container>
</el-container>
    

    </div>
  </div>
 

</template>

<script>
import DateNumber from '../components/DateNumber.vue';
import HistTiDan from '../components/HistTiDan.vue';
import Morehist from '../components/Morehist.vue'
export default {
  
  data() {
  return {
    squareUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
    sizeList: ["large"],
    username: '野原汉堡包',
    useraddress:"603252462@qq.com",
    userword:"hello world",
    usercreatetime:"2002-09-09",
    like: true,
      
        value2: 1314,
        title: "增长人数",

    cardMargin_1: "10px", // 设置卡片外边距
    cardWidth_1: "300px", // 设置卡片宽度
    cardHeight_1: "800px", // 设置卡片高度
 
    cardMargin_2: "10px", // 设置卡片外边距
    cardWidth_2: "400px", // 设置卡片宽度
    cardHeight_2: "260px", // 设置卡片高度

    cardMargin_3: "10px", // 设置卡片外边距
    cardWidth_3: "820px", // 设置卡片宽度
    cardHeight_3: "500px" // 设置卡片高度
  
   
  };

},
components: {
      DateNumber,
      HistTiDan,
      Morehist,
    },
};
</script>

<style scoped>
 
#tx {
  margin-left: 170px;
  margin-top: 20px;
  display: flex;
  flex-direction: row;
}

.block {
  margin: 0%;
  display: flex;
  flex-direction: row;
}

#name {
  margin-left: 40px;
}
#xx{
  display: flex;
  flex-direction: row;

}

#xx2{
  text-align: center;
}

#xx2:hover{
            transform: translateY(-10px);
            box-shadow:0 0 10px rgba(0, 0, 0, .3);
        }
 #xx3:hover{
            transform: translateY(-10px);
            box-shadow:0 0 10px rgba(0, 0, 0, .3);
        }
#xx4:hover{
            transform: translateY(-10px);
            box-shadow:0 0 10px rgba(0, 0, 0, .3);
        }

#changebutton:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,.24),
    0 17px 50px 0 rgba(0,0,0,.19);
}
#usernumber{
  margin-top: 40px;

}
.flex-container {
  display: flex;
  align-items: center; /* 垂直居中对齐 */
}
.el-header, .el-footer {
    background-color: #ffffff;
    color: #333;
  }
  
  .el-aside {
    background-color: #ffffff;
    color: #333;

    margin-left: 160px;
  
  }
  
  .el-main {
    background-color: #ffffff;
    color: #333;
   
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  #Tt1{

   margin-right: 10px;
  font-family: 'Your Font', sans-serif; /* 设置字体 */
  font-size: 10px; /* 设置字体大小 */
  font-weight:300; /* 设置字体粗细 */
  color: #333; /* 设置字体颜色 */
  /* 还可以添加其他样式，如行高、字间距等 */

  }
  #incard_1{
    display: flex;
  align-items: center; 
  }
 
  #TGL{
    margin-left: 60px;
  }
  #TGL_ZT{ 
    display:inline;
  flex-direction: row;
}
#TGL_BT{
  margin-top: 160px;
  margin-left: -87px;
}
.container button{
  background-color: #FFFFFF;
              border: 1px solid #222222;
              border-radius: 8px;
              box-sizing: border-box;
              color: #222222;
              cursor: pointer;
              display: inline-block;
              font-family: Circular,-apple-system,BlinkMacSystemFont,Roboto,"Helvetica Neue",sans-serif;
              font-size: 16px;
              font-weight: 600;
              line-height: 20px;
              margin: 0;
              outline: none;
              padding: 13px 23px;
              position: relative;
              text-align: center;
              text-decoration: none;
              touch-action: manipulation;
              transition: box-shadow .2s,-ms-transform .1s,-webkit-transform .1s,transform .1s;
              user-select: none;
              -webkit-user-select: none;
              width: auto;

}
.WK{
  width:1440px;
 
}

</style>