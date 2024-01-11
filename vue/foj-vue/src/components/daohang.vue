 <template>
  <div class="dh">
    <div class="horizontal-container">
      <div class="logo_div" style="margin-top:7px; width: 150px; padding-left: 24px;" @click="gotoHome()" ><Logo></Logo></div>
      <el-divider direction="vertical"></el-divider>
      <div id="ml" class="wide-ml">
        <el-tabs v-model="activeName" @tab-click="selectTab">
          
          <el-tab-pane label="题单" name="list" ></el-tab-pane>
          <el-tab-pane label="题目管理" name="questionsManager" v-if="authority>0"></el-tab-pane>
          <el-tab-pane label="后台管理" name="AdminManager" v-if="authority>1"></el-tab-pane>
   
          <template slot-scope="props">
            <div class="custom-tab" :class="{ 'is-active': props.isActive }">{{ props.label }}</div>
          </template>
        </el-tabs>
      </div >
      
      <el-divider direction="vertical"></el-divider>

      <div style="width: 200px;">
        <div v-if="$route.path!='/login'" onselectstart="return false" style="width: auto; display: inline-block;height: 100%; margin-left: 30px;" >
            <!-- 登录注册 -->
          <div id="WZ" v-if="authority==-2">
            <el-row style="margin-top: 11px;">
              <el-button type="primary" plain size="small" @click="gotoLogin('register')">注册</el-button>
              或
              <el-button type="text"  size="small" @click="gotoLogin('login')">登录</el-button>
            </el-row>
          </div>
          <div v-else>
            <el-dropdown @command="selected"  trigger="click">
              <span class="el-dropdown-link" style="cursor: pointer;">
                <div id="tx" style="display: flex; align-items: center; ">
                    <span style="margin-top: 10px;"><el-avatar :src="avatarURL"></el-avatar></span>
                    <span style="font-size:17px; margin-left: 10px;">{{username}}</span>
                </div>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="a">个人中心</el-dropdown-item>
                <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<style scoped>
.horizontal-container {
  display: flex;
  flex-direction: row;
  width: 1440px;
  margin: auto;
  
  
}
.logo_div:hover{
    cursor: pointer;
  }
.dh{
  border-bottom: 3px solid #E4E7ED; 
  text-align: center;
  background-color: #FFFFFF;
  width: 100%;
}

#lg {
  margin-left: 5%;
  margin-right: 20px;
  margin-top: 3px;
}


#ml {
  margin-top: 4px;
  margin-left: 3%;
  width: 67%;

}
#WZ{
 margin-left: 0px;
 height: 100%;
}
#tx {
  margin: auto;
  width: auto;


}

#name {
  margin-left: 10px;
}

/* 添加自定义标签栏样式 */
.custom-tab {
  padding: 12px 20px;
  color: #409EFF;
  cursor: pointer;
  transition: color 0.3s;
}

.custom-tab.is-active {
  color: #409EFF;
}

/* 添加使 ml 宽度增加的样式 */
.wide-ml {
  width: 70%; /* 根据需要调整宽度值 */
}
</style>

<style scoped>
  ::v-deep .demo-avatar{
    width: auto;
    display: inline-block
  }
  ::v-deep .el-tabs__header{
      margin: 0;
  }
  ::v-deep .el-tabs__nav-scroll{
    height: 100%;
  }
  ::v-deep .el-tabs__item{
    height: 100%;
    padding-bottom: 7px;
    font-size: 17px;
    margin-top: 5px;
    padding-bottom: 0;
  }
  ::v-deep .el-tabs--top{
    height: 100%;
  }
  ::v-deep .el-tabs__header {
    height: 100%;
  }
  ::v-deep .el-tabs__nav-wrap{
    height: 100%;
  }
  ::v-deep .el-tabs__nav{
    height: 100%;
  }
  ::v-deep .el-tabs__active-bar{
    height: 3px;
  }
  ::v-deep .el-tabs__nav-wrap::after{
    display: none;
  }
  ::v-deep .el-divider--vertical{
    height: 2em;
    margin-right: 20px;
    margin-left: 20px;
    margin-top: 13px;
    width: 1px;
  }


</style>

<script>
import Logo from './Logo.vue';
import Avatar from './Avatar.vue';
import Cookies from 'js-cookie';

export default {
  data() {
    return {
      avatarURL: "",
      path: '',
      authority: -2 ,
      activeName: 'list',
      username: '', 
      flag : true
    };
  },

  components: {
    Logo,
    Avatar,
  },
  methods: {
    selectTab(tab, event) {
      if(this.activeName=='AdminManager'){

      } else if(this.activeName=='questionsManager'){
        this.$router.push('/question',()=>{})
      } else {
        this.$router.push('/home', ()=>{})
      }
    },
    selected(command) {
      if(command="command"){
        Cookies.remove('token')
        Cookies.remove('id')
        Cookies.remove('name')
        Cookies.remove('authority') 
        this.$router.go(0)
      }
    },

    // 跳转到login页面
    gotoLogin(status) {
      this.$router.push({
        path: '/login',
        query: {status:status}
      })
    },
    gotoHome() {
        this.$router.push('/home', ()=>{})
    },
  },

  mounted(){
      //设置username
      this.username = Cookies.get("name")
      var authority = Cookies.get("authority");
      if(authority){
        this.authority =  authority
      }
      //获取头像
      var id = Cookies.get("id");
      if(id =='undefined' || id ==null){
        this.avatarURL = '';
      }
      else {
        this.$axios.get("/user/avatar/"+id).
        then((response)=>{
          this.avatarURL = response.data.data
        })
        .catch((err)=>{
          console.log("头像请求错误",err);
        })
      }

      //设置标题栏状态
      var path = window.location.pathname
      if(path=='/question'){
        this.activeName = 'questionsManager';
      } else if(path=='AdminManager') {
        this.activeName = 'AdminManager';
      }



  }

};



</script> 