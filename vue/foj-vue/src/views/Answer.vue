<template>
  <div class="a" @sort-change="getParams" >
<div id="PB">
<!-- <el-card id="card0" class="box-card"> -->
<div class="PageHeader">
  <el-card id="card0" class="box-card" >
<el-page-header @back="goBack" content="题目描述">
 
    </el-page-header>
    <div id="Buttons">
  <el-row>
<el-button id="button_1" @click="ReturnOutput">测试</el-button>
<el-button id="button_2">提交</el-button>

  </el-row>
</div>
  </el-card>
<div id="Card" >

<el-card style="overflow: auto" id="card1" class="box-card" >
  <div>
  <span id="Title">{{ questionData.data.name }}</span>

  </div>
  <div id="Tag">
  <span v-for="(item, index) in questionData.data.tags" :key="index"> <el-tag :color="item.color" effect="dark">{{ item.name }}</el-tag> </span>
  </div>
    <div id="Text">{{ questionData.data.description }}</div>
  <div id="Tip">
    <div id="TipTitle">提示:</div>
    <span id="TipText">{{ questionData.data.tip }}</span> 
  


  </div>

  <div id="Example">
    
    <span v-for="(item, index) in questionData.data.testcases" :key="index"> 
      <div id="ExampleTitile">案例{{ index + 1 }}</div>
      <div><span id="inputTitile">输入：</span>{{ item.input }}</div>
      <div><span id="outputTitile">输出：</span>{{ item.output }}</div><br/> 
    </span>
  </div>

  <div id="Max">
    <span>
  <span class="MaxTitile">最大运行时间：</span>
{{ (questionData.data.maxTime / 1000000).toFixed(2) }}
<span>ms</span>
    </span>

    <span id="set"> <span class="MaxTitile">最大运行空间：</span>
{{ (questionData.data.maxMemory / 1000000).toFixed(2) }}
<span>MB</span>
    </span>
  </div>
</el-card>
<div>
<el-card id="card2" class="box-card">
  <div id="LanguageChoose">
  <el-select v-model="selectedLanguage" placeholder="请选择" @change="handleLanguageChange">
    <el-option
      v-for="(item, index) in options"
      :key="index"
      :label="item"
      :value="item">
    </el-option>
  </el-select>
</div>


  <!-- <p>{{type[0]}}</p> -->
    <AceJavascripttest ref="mychild" id="Acepage"  :height=500 :value=value :theme=theme :readOnly=false :lang="mappedLanguage"></AceJavascripttest>
</el-card>
<el-card id="card3" class="box-card">
  <el-tabs type="border-card">
  
<el-tab-pane label="测试用例">  
  <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
  <el-tab-pane
    :key="item.name"
    v-for="(item, index) in editableTabs"
        :label="'测试 '+(index+1)"
        :name="item.name"
  >
      <div>输入： <el-input v-model="item.input" placeholder=""></el-input></div>
      <div>输出： <el-input v-model="item.output" placeholder="" :disabled="true"></el-input></div>
      </el-tab-pane>
    </el-tabs>

</el-tab-pane>
<el-tab-pane label="测试结果"></el-tab-pane>

</el-tabs>

 

<div></div>
</el-card>
</div>
</div>
</div>
<!-- </el-card> -->


</div>
</div>

</template>

<script>
import AceJavascripttest from '../components/AceJavascripttest.vue'
import Tabs from '../components/Tabs.vue'
import Cookies from 'js-cookie';
export default {
components:{
  AceJavascripttest,
  Tabs,
},
data() {
return{
  testArray:[],

  editableTabsValue:3,

  questionData:'',
  questionData_id:'',

  type: [],
  value:'',
  theme:'xcode',

  options: [],
  selectedLanguage:'C/C++',

  tabIndex:0,
 
  editableTabsValue:'1',
        editableTabs: [],
         tabIndex: 3,



};
},
computed: {
    mappedLanguage() {
      // 映射关系
      const mapping = {
        'C/C++': 'c_cpp',
      
      };

      // 映射处理
      return mapping[this.selectedLanguage];
    },
  },
methods: {
    goBack() {
      console.log('go back');   
      this.$router.go(-1)
    },
    
    getParams() {
         
          this.questionData_id = this.$route.query.questionData_id;
          // console.log(2222, this.questionData_id);
    },
    Pagecreate()
    {
      this.getParams();
      console.log(3333, this.questionData_id);
      let url = '/user/question/'+this.questionData_id;
       
     this.$axios.get(url)
      .then(response => {
        // 从响应中获取数据
        console.log(222333,response.data);
        this.questionData = response.data;
        var arr =response.data.data.testcases;
        for(const i in arr){
          this.editableTabs.push({name:arr[i].id+'',input:arr[i].input,output:''})
        }
        this.tabIndex=arr.length;
      
       url='/user/language/list';

      })
      .catch(error => {
        console.error('Error fetching question data:', error);
      })
     
      url='/user/language/list';
      this.$axios.get(url)
      .then(response => {
        // 从响应中获取数据
        ;
        console.log(response.data);
        this.options = response.data.data;
        console.log(this.options);
      })
      .catch(error => {
        console.error('Error fetching options:', error);
      })
      
    
    },
   
    handleTabsEdit(targetName, action) {
    console.log(1111,this.editableTabsValue);
    if (action === 'add') {
      let newTabName = ++this.tabIndex + '';
      let newInput = ''; // 初始化输入值
      this.editableTabs.push({
        title: 'New Tab',
        name: newTabName,
        input: newInput,
        output: '1'
      });
      this.editableTabsValue = newTabName;
    }
    console.log(999,this.tabIndex)
    if (action === 'remove') {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }

      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.name !== targetName);
    }
    console.log(888,this.tabIndex)
  },
  GetValue()
  {
    return this.$refs.mychild.getValue();
  },
  ReturnOutput()
  {
    console.log(2333,this.selectedLanguage);
    var newInput = [];
    for (const tab of this.editableTabs) {
      newInput.push(tab.input);
    }
   
    let url='/user/test';
        const params = {
          qid:this.questionData.data.id,
          code:this.GetValue(),
          language:this.selectedLanguage,
          testcases:newInput,
        };
        console.log('this：',this.$axios);

    this.$axios.post(url, params )
    .then((response) => {
      // 处理返回的 output 值
      const Outputs = response.data;
      console.log(1111111,Outputs);
      // 遍历 outputs，并将值放入 editableTabs 中
      Outputs.forEach((Output, index) => {
        if (index < this.editableTabs.length) {
          this.editableTabs[index].output = Output;
        }
      });
    })
    .catch(function (error) {
      console.log("错误1：" + error);
    });
    
        console.log(this.editableTabs);
},
},

  mounted() {
  // 组件挂载时初始化数据
  this.Pagecreate()

},


  


}
</script>

<style scoped>
/* 使用深度作用选择器，限制样式仅作用于 .PageHeader 组件内部 */
/* .a /deep/ .PageHeader {
  /* background-color:rgb(253, 253, 253); */
 
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
  display: flex;
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
height: 465px;
margin-left: 5px;
margin-top: 5px;
border-radius: 20px; 
overflow: hidden;
padding:3px;
}
#card3{
  width: 1090px;
  height: 320px;
  margin-top:5px;
  margin-left: 5px;
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
width:100%;
height: 100%;

}
#Tip{
  margin-top:50px;
}
#TipTitle{
  font-size: 17px;
  font-weight: 550;

}
el-card{
  overflow: auto;
}

#Example{
  margin-top:30px;

}
#ExampleTitile{
  font-size: 15px;
  font-weight: 560;


}
#set{
  margin-left: 90px;
}
.MaxTitile{
  font-size: 15px;
  font-weight: 560;
}
#Acepage{
  margin-top:10px;
  margin-left:-20px;

}
.a /deep/ .ace_gutter {
  background-color: #ffffff;
}
#LanguageChoose{
  margin-top:-20px;
  border: none;

}
.a /deep/.el-input__inner {
  border: none;
}
#button_1 {
  align-items: center;
              background-color: #eeeeee;
            
              box-shadow: transparent 0 0 0 3px,rgba(18, 18, 18, .1) 0 6px 20px;
              box-sizing: border-box;
              color: #121212;
              cursor: pointer;
              display: inline-flex;
              flex: 1 1 auto;
              font-family: Inter,sans-serif;
              font-size: 0.87rem;
              font-weight: 500;
              justify-content: center;
              line-height: 1;
              margin: 0;
              outline: none;
              padding: 1rem 1.2rem;
              text-align: center;
              text-decoration: none;
              transition: box-shadow .2s,-webkit-box-shadow .2s;
              white-space: nowrap;
              border: 0;
              user-select: none;
              -webkit-user-select: none;
              touch-action: manipulation;


            }
  
            #button_1:hover {
              box-shadow: #d8d8d8 0 0 0 3px, transparent 0 0 0 0;
            }
            #button_2 {
              align-items: center;
              background-color: #eeeeee;
           
              box-shadow: transparent 0 0 0 3px,rgba(18, 18, 18, .1) 0 6px 20px;
              box-sizing: border-box;
              color: #121212;
              cursor: pointer;
              display: inline-flex;
              flex: 1 1 auto;
              font-family: Inter,sans-serif;
              font-size: 0.87rem;
              font-weight: 500;
              justify-content: center;
              line-height: 1;
              margin: 0;
              outline: none;
              padding: 1rem 1.2rem;
              text-align: center;
              text-decoration: none;
              transition: box-shadow .2s,-webkit-box-shadow .2s;
              white-space: nowrap;
              border: 0;
              user-select: none;
              -webkit-user-select: none;
              touch-action: manipulation;

            }
  
            #button_2:hover {
              box-shadow: #d8d8d8 0 0 0 3px, transparent 0 0 0 0;
            }
            #Buttons{
              margin-top:-37px;
              margin-left:790px;
            }
            /* 去掉tabs标签栏下的下划线 */
      /* 去掉tabs标签栏下的下划线 */
      /* #ChangePageButton{
        margin-top:-22px;
        margin-left:-20px ;
        height: 35px;
          width:1440px;
          background-color: #f7f4f4;
      }
      .ChangeButtons{
        height: 35px;
        text-align: center;
        background-color: #f7f4f4;
        border: none;

      } */

</style>