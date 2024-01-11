<template>
  <div>
    <div id="ZT">
      <el-main>
        <el-button id="Create" type="primary" icon="el-icon-edit" @click="createDialog()"></el-button>
        <el-button type="danger" icon="el-icon-delete" circle @click="deleteDialog()"></el-button>
        <el-dialog :visible.sync="dialogTableVisible">

          <div>
            <div class="NR">题目名：<span><el-input type="textarea" placeholder="请输入内容"
                  v-model="questionData.name"></el-input></span></div>
            <div class="NR">题目描述：<span><el-input type="textarea" placeholder="请输入内容"
                  v-model="questionData.description"></el-input></span></div>
            <div class="NR">提示：<span><el-input type="textarea" placeholder="请输入内容"
                  v-model="questionData.tip"></el-input></span></div>
            <div class="NR">最大运行时间(ms):<span><el-input type="textarea" placeholder="请输入内容"
                  v-model="questionData.maxTime"></el-input></span></div>
            <div class="NR">最大运行内存(MB):<span><el-input type="textarea" placeholder="请输入内容"
                  v-model="questionData.maxMemory"></el-input></span></div>
            <div class="NR">最大线程数：<span><el-input type="textarea" placeholder="请输入内容"
                  v-model="questionData.maxProc"></el-input></span></div>
            <div class="NR"> 标签id列表:
              <el-select v-model="questionData.tags" multiple placeholder="请选择"  value-key="id">
                <el-option v-for="item in options2" :key="item.id" :label="item.name" :value="item">
                </el-option>
              </el-select>
            </div>
            <div><el-switch v-model="value1" active-text="题目开放"> </el-switch>
              <el-select v-model="questionData.level" placeholder="请选择" >
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </div>
            <el-button type="primary" round @click="handleExternalConfirm()">保存</el-button>
          </div>
        </el-dialog>
        <!-- 测试案例对话框 -->
        <el-dialog
          :title="currentTitle"
          :visible.sync="testDialogVisible"
          width="30%">
          <!-- 内容: 测试案例表格 -->
          <template>
            <el-table
              :data="currentTests"
              style="width: 100%">
              <el-table-column
                label="案例名"
                width="100">
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  <span style="margin-left: 10px">{{ scope.row.name }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="输入"
                width="120">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.in }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="输出"
                width="120">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.out }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="状态"
                width="80">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.status }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="danger"
                    @click="removeTest(scope.row.id)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </template>
          <!-- 内层 -->
          <el-dialog
            width="30%"
            title="上传案例"
            :visible.sync="uploadVisible"
            append-to-body>
            <el-upload
              class="upload-demo"
              action=""
              :auto-upload='false'
              multiple
              :on-change="changeFiles"
              :file-list="fileList">
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
            <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="upload()">上 传</el-button>
            </span>
          </el-dialog>
          <!-- 页脚 -->
          <span slot="footer" class="dialog-footer">
            
            <el-button type="primary" @click="uploadVisible = true">添 加</el-button>
            <el-button type="primary" @click="testDialogVisible = false">确 定</el-button>
          </span>
        </el-dialog>



        <div class=" input-box mb20">


          <el-table :data="tableData" style="width: 100%" size="small" height="800px" stripe
            @selection-change="handleSelectionChange">

            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column label="题目id" width="90">
              <template slot-scope="scope">
                <span style="margin-left: 0px">{{ scope.row.id }}</span>
              </template>
            </el-table-column>
            <el-table-column label="题目名" width="180">
              <template slot-scope="scope">
                <span style="margin-left: 0px">{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column label="难度" width="80">
              <template slot-scope="scope">
                <span style="margin-left: 0px">{{ formatDifficulty(scope.row) }}</span>
              </template>
            </el-table-column>
            <el-table-column label="通过率" width="80">
              <template slot-scope="scope">
                <span style="margin-left: 0px">{{ (scope.row.passRate * 100).toFixed(2) }}%</span>
              </template>
            </el-table-column>
            <el-table-column label="标签" width="200">
              <template slot-scope="scope">
                <span v-for="(item, index) in scope.row.tags.slice(0, 2)" :key="index" style="margin-left: 20px;">
                  <el-tag :color="item.color" effect="dark">
                    {{ item.name }}
                  </el-tag>
                </span>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="250px">
              <template slot-scope="scope">
                <!-- 在此处定义按钮 -->

                <el-button type="primary" icon="el-icon-edit" @click="openDialog(scope.row.id)">编辑</el-button>
                <el-button type="primary" icon="el-icon-s-order" @click="showTestDialog(scope.row.id, scope.row.name)">测试用例</el-button>
              </template>
            </el-table-column>
            <!-- 其他列... -->
          </el-table>
          <!-- 分页器 -->
          <div class="block" style="margin-top: 15px;">
            <!-- <el-pagination layout="prev, pager, next" :total="totalItems" background
              @current-change="handleCurrentChange">
            </el-pagination> -->
          </div>


        </div>

      </el-main>
    </div>
  </div>
</template>



<script>
import Vuetext from '../components/Vuetext.vue'
import Cookies from 'js-cookie';

export default {
  data() {
    return {
      tableData: [], // 从后端获取的数据
      // totalItems: 100, // 从后端获取的总条目数
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示的条目数
      selectedRows: [], // 保存勾选的行数据
      currentTests: [], //当前测试案例数组
      dialogTableVisible: false,
      testDialogVisible: false,
      currentTitle: '', //当前选中题目标题
      currentId:null, //当前id
      questionData: [],
      uploadVisible: false, //上传框显示
      value1: true,
      options: [
        { value: 0, label: '简单' },
        { value: 1, label: '普通' },
        { value: 2, label: '困难' }
      ],
      value: '',
      fileList: [],
      uploadList: [], //真实的上传文件的列表
      options2: [],
      tags: []

    };
  },
  components: {
    Vuetext,

  },

  methods: {
    formatDifficulty(row) {
      switch (row.level) {
        case 0:
          return '简单';
        case 1:
          return '普通';
        case 2:
          return '困难';
        default:
          return '';
      }
    },
    async createDialog() {


      var ID;
      const h = this.$createElement;
      let url = '/setter/questions';
      console.log("获取题目id的接口为：" + url);
      await this.$axios.post(url)
        .then((response) => {
          ID = response.data.data;
          console.log("获取的题目id为：" + ID);
        }).catch(function (error) {
          console.log("获取题目id错误" + error);
        })


      await this.openDialog(ID);

      // url='/user/question/1';


    },
    openDialog(ID, name) {
      console.log("获取的题目为："+ID+':' + name);
      this.dialogTableVisible = true;
      let url = '/user/question/' + ID;
      
      this.$axios.get(url)
        .then((response) => {
          this.questionData = response.data.data;
          // this.value=this.questionData.level;
          // this.value1 = this.questionData.status === 1;
          // this.tags=this.questionData.tags;
          console.log("获取的题目信息", this.questionData);
        })
        .catch((error) => {
          console.log("获取题目信息错误" + error);
        });


    },

    QuestionList() {
      console.log("用户id为", Cookies.get("id"));

      let url = '/user/questions/list';
      const params = {
        uid: Cookies.get("id"),
        id: '', // 题目id，根据你的需求传递
        name: '', // 题目名，根据你的需求传递
        page: this.currentPage,
        pageSize: this.pageSize,
      };
      this.$axios.get(url, { params })
        .then(response => {
          // 从响应中获取数据
          this.tableData = response.data.data.data;
          console.log("获得的题目列表为" + response.data.data.data);
          // 如果后端提供了总条目数，你可能需要更新总条目数
          // this.totalItems = response.data.data.total;
        })
        .catch(error => {
          console.error('Error fetching table data:', error);
        });
    },

    async handleExternalConfirm() {
      console.log("此时数据为", this.questionData);
      let url = '/setter/questions';
      const tagIds = this.questionData.tags.map((tag)=>{return tag.id});
      console.log(tagIds);
      const params = {
        id: this.questionData.id,
        name: this.questionData.name,
        description: this.questionData.description,
        tip: this.questionData.tip,
        maxTime: this.questionData.maxTime,
        maxMemory: this.questionData.maxMemory,
        maxProc: this.questionData.maxProc,
        tags: tagIds, // 使用获取到的一维数组
        status: this.questionData.status,
        level: this.questionData.level
      }
      await this.$axios.put(url, params)
        .then((response) => {
        }).catch((error) => {
          console.log("错误" + error);
        });

      await this.QuestionList();
    },
    //   QuestionModify(url) {

    //   this.$axios.get(url)
    //     .then((response) => {
    //       this.questionData = response.data.data;
    //       this.value=this.questionData.level;
    //       this.value1 = this.questionData.status === 1;
    //       this.tags=this.questionData.tags;
    //       console.log("获取的题目信息", this.questionData.tags);
    //     })
    //     .catch((error) => {
    //       console.log("获取题目信息错误" + error);
    //     });

    // }
    TabsList() {
      let url = '/user/tags/list';
      this.$axios.get(url)
        .then((response) => {
          this.options2 = response.data.data;

          console.log("获取的标签列表", this.options2);
        })
        .catch((error) => {
          console.log("获取题目信息错误" + error);
        });
    },
    async deleteDialog() {
      let url = '/setter/questions';
      console.log(this.selectedRows);

      await this.$axios.delete(url, { data: this.selectedRows })
        .then((response) => {
        })
        .catch((error) => {
          console.log("错误" + error);
        });

      await this.QuestionList()

    },
    handleSelectionChange(val) {
      // 将勾选行的题目id存储到selectedRows中
      this.selectedRows = val.map(row => row.id);
      // 在这里，this.selectedRows 将包含勾选的题目id
      console.log('勾选的题目id:', this.selectedRows);
    },

    showTestDialog(id,name) {
      this.currentId = id;
      this.currentTitle = name;
      console.log("编辑测试案例:",id);
      this.$axios.get('/setter/testcases/qid/'+id).then((response)=>{
        console.log('获取案例响应:',response);
        if(response.data.code==200){
          this.currentTests = response.data.data;
        }
        this.testDialogVisible = true;
      })
    },
    async upload() {
      console.log("上传文件列表", this.uploadList);
      let fd = new FormData();
      this.fileList = [];
      this.uploadList.forEach(file =>{
        fd.append('files',file.raw)
      })
      fd.append('qid',this.currentId);
      const config={
        headers: {
          "Content-Type": "multipart/form-data",
        }
      }

      await this.$axios.post('/setter/testcases',fd,config).then(response =>{
        console.log('上传成功');
      })

      await this.$axios.get('/setter/testcases/qid/'+this.currentId).then((response)=>{
        console.log('获取案例响应:',response);
        if(response.data.code==200){
          this.currentTests = response.data.data;
        }
      })
      this.uploadVisible = false;
      
    },

    changeFiles(file,fileList){
      console.log('文件选中:',fileList);
      this.uploadList = fileList
      console.log('文件选中:',fileList);
    },

    removeTest(id){
      console.log("删除案例:"+id);


      this.$axios.delete('/setter/testcases',{params: {id:id}}).then(response=>{
        console.log('删除成功');
        this.$axios.get('/setter/testcases/qid/'+this.currentId).then((response)=>{
          console.log('获取案例响应:',response);
          if(response.data.code==200){
            this.currentTests = response.data.data;
          }
        })
      })
    }
  },
  mounted() {
    this.QuestionList();
    this.TabsList();
  }
}


</script>

<style scoped>
#TD {
  margin-top: 2%;
}

/* 设置一个卡片元素的基本样式 */
.card {

  border-radius: 50px;
  /* 设置圆角半径为50px */
  background: #e0e0e0;
  /* 设置背景颜色为灰色 */
  /* 设置两个颜色的阴影效果，形成凸起和凹陷的效果 */
  box-shadow:
    20px 20px 60px #bebebe,
    /* 右下方的深灰色阴影 */
    -20px -20px 60px #ffffff;
  /* 左上方的白色阴影 */
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  height: 1000px;
  width: 1000px;
  margin: auto;
  border-radius: 20px;
  /* 调整圆角半径根据需要 */
  margin-top: 1%;

}

#dh {
  width: 100%;
  margin-left: -10%;
  margin-top: 0%;
  background-color: rgb(255, 255, 255);
}

#TD {
  margin-top: 2%;

  border-radius: 20px;

}

#Create {
  margin-right: 100px;
  border-radius: 90px;
}

::v-deep #ZT > main > div:nth-child(4) > div{
  width:600px !important;
}

</style>