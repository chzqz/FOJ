<template>
  <div class="a" @sort-change="getParams">
    <div id="PB">
      <!-- <el-card id="card0" class="box-card"> -->
      <div class="PageHeader">
        <el-card id="card0" class="box-card">
          <el-page-header @back="goBack" content="题目描述">

          </el-page-header>
          <div id="Buttons">
            <el-row>
              <el-button-group>
                <el-button icon="el-icon-caret-right" @click="ReturnOutput" round
                  style="color: rgb(255,130,79);">运行</el-button>
                <el-button icon="el-icon-upload" @click="ReturnBoolm" round style="color: rgb(0,199,29);">提交</el-button>
              </el-button-group>

            </el-row>
          </div>
        </el-card>
        <div id="Card">

          <!-- 题目卡片 -->
          <el-card style="overflow: auto" id="card1" class="box-card">

            <div>
              <span id="Title">{{ questionData.data.name }}</span>
            </div>
            <div id="Tag">
              <span v-for="(item, index) in questionData.data.tags" :key="index"> 
                <el-tag :color="item.color" effect="dark" style="margin-right: 10px; border: 0; box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
                  {{ item.name }}
                </el-tag> 
              </span>
            </div>
            <div id="Text">
              <p v-html="questionData.data.description" style="white-space:pre-wrap"></p>
            </div>

            <el-divider></el-divider>
            <div id="Example">
              <span v-for="(item, index) in questionData.data.testcases" :key="index">
                <div id="ExampleTitile">案例{{ index + 1 }}:</div>
                <div style="padding-left: 20px; margin-top: 15px; border-left: 2px solid #DCDFE6;">
                  <div>
                    <span id="inputTitile">输入
                      <pre><p v-html="item.input" style="line-height: 26px; font-weight: bold;  background-color:rgb(245, 245, 245); border-radius: 10px; padding: 10px;"></p></pre>
                    </span>
                  </div>
                  <div>
                    <span id="outputTitile">输出</span>
                    <pre><p v-html="item.output" style="line-height: 26px; font-weight: bold;  background-color:rgb(245, 245, 245); border-radius: 10px; padding: 10px;"></p></pre>
                  </div>
                </div>
              </span>
            </div>
            
            <div id="Tip">
              <div id="TipTitle">提示:</div>
              <span id="TipText"></span>
              <pre><p v-html="questionData.data.tip" style="line-height: 26px; font-weight: bold;  background-color:rgb(245, 245, 245); border-radius: 10px; padding: 10px;"></p></pre>
            </div>
            <el-divider></el-divider>
            <div id="Max" style="margin-top: 20px;">
              <div>
                <span class="MaxTitile">最大运行时间：</span>
                <span v-html="(questionData.data.maxTime / 1000000).toFixed(2)" style="line-height: 26px; font-weight: bold;  background-color:rgb(245, 245, 245); border-radius: 3px; padding: 3px;"></span>
                <span>ms</span>
              </div>
              
              <div> <span class="MaxTitile">最大运行空间：</span>
                <span v-html="(questionData.data.maxMemory / 1000000).toFixed(2)" style="line-height: 26px; font-weight: bold;  background-color:rgb(245, 245, 245); border-radius: 3px; padding: 3px;"></span>
                <span>MB</span>
              </div>
            </div>
          </el-card>
          <div style="float: right; width: 70%;">

            <!-- 代码块卡片 -->
            <el-card id="card2" class="box-card">
              <div id="LanguageChoose">
                <el-select v-model="selectedLanguage" placeholder="请选择" @change="handleLanguageChange">
                  <el-option v-for="(item, index) in options" :key="index" :label="item" :value="item">
                  </el-option>
                </el-select>
              </div>


              <!-- <p>{{type[0]}}</p> -->
              <AceJavascripttest ref="mychild" id="Acepage" :height=500 :value=value :theme=theme :readOnly=false
                :LA="mappedLanguage"></AceJavascripttest>
            </el-card>

            <!-- 测试用例卡片 -->
            <el-card id="card3" class="box-card">
              <el-tabs type="border-card">

                <el-tab-pane label="测试用例">
                  <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
                    <el-tab-pane :key="item.name" v-for="(item, index) in editableTabs" :label="'测试 ' + (index + 1)"
                      :name="item.name">
                      <div>输入： <el-input type="textarea" v-model="item.input" placeholder="" :autosize=inputSize resize="none"></el-input></div>

                    </el-tab-pane>
                  </el-tabs>

                </el-tab-pane>
                <el-tab-pane label="测试结果">
                  <el-tabs v-model="editableTabsValue" type="card"  @edit="handleTabsEdit">
                    <el-tab-pane :key="item.name" v-for="(item, index) in editableTabs" :label="'测试 ' + (index + 1)"
                      :name="item.name">
                      <div>输入： <el-input type="textarea" :rows="2" v-model="item.input" placeholder=""
                          :disabled="true" :autosize=inputSize  resize="none"></el-input></div>
                      <div>输出： <el-input type="textarea" v-model="item.output" placeholder="" :disabled="true" :autosize=inputSize  resize="none"></el-input>
                      </div>
                    </el-tab-pane>
                  </el-tabs>

                </el-tab-pane>

              </el-tabs>
              <div>
                <!-- 对话框 -->
                <el-dialog
                  title="提交结果"
                  :visible.sync="resultVisible"
                  width="30%"
                  :before-close="handleClose">
                  <div>
                    <return-vnodes :result="AnswerData" :question="questionData" :lang="mappedLanguage"></return-vnodes>
                  </div>
                  <span slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="resultVisible = false">确 定</el-button>
                  </span>
                </el-dialog>
              </div>
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
import ReturnVnodes from '../components/ReturnVnodes.vue'
export default {
  components: {
    AceJavascripttest,
    Tabs,
    ReturnVnodes,
  },
  data() {
    return {

      AnswerData: [],//存放返回的判断结果的相关信息

      inputSize: { minRows: 2 },
      resultVisible: false, // 结果弹框是否显示
      questionData: '',//这个题目对应的信息
      questionData_id: '',

      type: [],
      value: '',//此时这个代码编辑框内显示的东西
     

      options: [],//语言选中框的选项
      selectedLanguage:'C',//当前选择的语言



      editableTabsValue: '1',//当前选中的测试用例
      editableTabs: [],//用来储存测试用例对应的input和output
      tabIndex: 3,


      code: ''



    };
  },
  computed: {
    mappedLanguage() {
      // 映射关系
      const mapping = {
        'C': 'c_cpp',
        'C++': 'c_cpp',
        'Java': 'java',
        'Python' : 'python'

      };
     console.log(1111,mapping[this.selectedLanguage])
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
    Pagecreate() {
      this.getParams();
      console.log(3333, this.questionData_id);
      let url = '/user/question/' + this.questionData_id;

      this.$axios.get(url)
        .then(response => {
          // 从响应中获取数据
          console.log(222333, response.data);
          this.questionData = response.data;
          var arr = response.data.data.testcases;
          for (const i in arr) {
            this.editableTabs.push({ name: arr[i].id + '', input: arr[i].input, output: '' })
          }
          this.tabIndex = arr.length;

          url = '/user/language/list';

        })
        .catch(error => {
          console.error('Error fetching question data:', error);
        })

      url = '/user/language/list';
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


      console.log(123, this.editableTabs);

    },

    handleTabsEdit(targetName, action) {
      console.log(1111, this.editableTabsValue);
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
      console.log(999, this.tabIndex)
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
      console.log(888, this.tabIndex)
    },
    GetValue() {
      return this.$refs.mychild.getValue();
    },
    ReturnOutput() {

      var newInput = [];
      for (const tab of this.editableTabs) {
        newInput.push(tab.input);
      }

      let url = '/user/test';
      const params = {
        qid: this.questionData.data.id,
        code: this.GetValue(),
        language: this.selectedLanguage,
        testcases: newInput,
      };
      console.log('this：', this.$axios);

      this.$axios.post(url, params)
        .then((response) => {


          // 确保 editableTabs 和 response.data.data.output长度相同
          if (response.data.data.output.length === this.editableTabs.length) {
            for (let i = 0; i < response.data.data.output.length; i++) {
              this.editableTabs[i].output = response.data.data.output[i]
            }
          }
        })
        .catch(function (error) {
          console.log("错误1：" + error);
        });


    },
    //提交代码
    async ReturnBoolm() {
      let url = '/user/judge';
      const params = {
        qid: this.questionData.data.id,
        code: this.GetValue(),
        language: this.selectedLanguage,
      };
      try {
        const postResponse = await this.$axios.post(url, params);
        url = '/user/judge/' + postResponse.data.data;
        console.log("返回的题目解答id", url);

        const getResponse = await this.$axios.get(url);
        this.AnswerData = getResponse.data; // 直接在这里设置 AnswerData
        console.log("返回的数据", this.AnswerData);
        this.resultVisible = true;
        
        // const h = this.$createElement;

        // this.$msgbox({
        //   title: '结果',
        //   customClass: 'resultBox',
        //   message: h('ReturnVnodes', {
        //     props: this.AnswerData.data
        //   }),

        //   // beforeClose: (action, instance, done) => {
        //   //   // if (action === 'confirm') {
        //   //   //   instance.confirmButtonLoading = true;
        //   //   //   instance.confirmButtonText = '执行中...';
        //   //   //   setTimeout(() => {
        //   //   //     done();
        //   //   //     setTimeout(() => {
        //   //   //       instance.confirmButtonLoading = false;
        //   //   //     }, 300);
        //   //   //   }, 3000);
        //   //   // } else {
        //   //   //   done();
        //   //   // }
        //   //   done();
        //   // }
        // })


        } catch (error) {
          console.log("错误：" + error);
        }
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
/* .a /deep/ .PageHeader {
  /* background-color:rgb(253, 253, 253); */

.a {
  /* width:1440px; */
  width: 99%;
  height: 100%;
  margin: auto;

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

#PB {
  /* display: flex; */


  width: 100%;
  height: 100%;
}

#Card {
  display: flex;
  align-items: center;
}

#card0 {
  display: flex;
  width: 100%;
  height: 60px;
  /* margin-left: 12px; */
  margin-top: 10px;
  border-radius: 20px;
  
}

#card1 {
  width: 800px;
  height: 800px;
  /* margin-left: 12px; */
  margin-top: 10px;
  overflow: auto;
  border-radius: 20px;
  float: left;
  width: 29.7%;
}

#card2 {
  width: 100%;
  height: 465px;
  margin-left: 5px;
  margin-top: 5px;
  border-radius: 20px;
  overflow: hidden;
}

#card3 {
  width: 100%;
  height: 320px;
  margin-top: 5px;
  margin-left: 5px;
  border-radius: 20px;

}

#Tabs {
  margin-left: -4%;
  margin-top: -4%;
  width: 800px;

}

.PageHeader {
  /* background-color: aliceblue;  */
  width: 100%;

  /* background: linear-gradient(200deg,#dadada, #ececec); */
}

#Title {
  font-size: 26px;
  font-weight: bold;

}

#Tag {
  margin-top: 20px;
}

#Text {
  margin-top: 30px;
  width: 100%;

}

#Tip {
  margin-top: 50px;
}

#TipTitle {
  font-size: 17px;
  font-weight: 550;

}

el-card {
  overflow: auto;
}

#Example {
  margin-top: 30px;

}

#ExampleTitile {
  font-size: 15px;
  font-weight: 560;


}


.MaxTitile {
  font-size: 15px;
  font-weight: 560;
  line-height: 30px;
}

#Acepage {
  margin-top: 10px;
  margin-left: -20px;

}

.a /deep/ .ace_gutter {
  background-color: #ffffff;
}

#LanguageChoose {
  margin-top: -20px;
  border: none;

}

.a /deep/.el-input__inner {
  border: none;
}

#button_1 {
  align-items: center;
  background-color: #eeeeee;

  box-shadow: transparent 0 0 0 3px, rgba(18, 18, 18, .1) 0 6px 20px;
  box-sizing: border-box;
  color: #121212;
  cursor: pointer;
  display: inline-flex;
  flex: 1 1 auto;
  font-family: Inter, sans-serif;
  font-size: 0.87rem;
  font-weight: 500;
  justify-content: center;
  line-height: 1;
  margin: 0;
  outline: none;
  padding: 1rem 1.2rem;
  text-align: center;
  text-decoration: none;
  transition: box-shadow .2s, -webkit-box-shadow .2s;
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

  box-shadow: transparent 0 0 0 3px, rgba(18, 18, 18, .1) 0 6px 20px;
  box-sizing: border-box;
  color: #121212;
  cursor: pointer;
  display: inline-flex;
  flex: 1 1 auto;
  font-family: Inter, sans-serif;
  font-size: 0.87rem;
  font-weight: 500;
  justify-content: center;
  line-height: 1;
  margin: 0;
  outline: none;
  padding: 1rem 1.2rem;
  text-align: center;
  text-decoration: none;
  transition: box-shadow .2s, -webkit-box-shadow .2s;
  white-space: nowrap;
  border: 0;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;

}

#button_2:hover {
  box-shadow: #d8d8d8 0 0 0 3px, transparent 0 0 0 0;
}

#Buttons {
  margin-top: -37px;
  margin-left: 825px;
}

::v-deep #card3 > div > div.el-tabs.el-tabs--top.el-tabs--border-card{
  border: 0;
  box-shadow: none;
}

::v-deep #pane-12 > div > div > textarea {
  height: 160px;
}

::v-deep .el-tag{
  height: 26px;
  line-height: 26px;
}
::v-deep #card3 > div{
  padding: 0%;
}

::v-deep #pane-12 > div > div > textarea{
  background-color: rgb(245,247,250);
}

#card3 > div > div.el-tabs.el-tabs--top.el-tabs--border-card{
  overflow: auto;
}

::v-deep #card3 > div > div:nth-child(2) > div > div{
  width: 800px !important;
  border-radius: 24px;
  height: auto;
}

::v-deep .el-dialog__header{
  border-bottom: 2px solid #d8d8d8;
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