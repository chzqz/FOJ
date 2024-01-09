 <template>
  <div class="dh">
    <div class="horizontal-container">
      <div id="lg" style="margin: :left;"><Logo></Logo></div>
      <el-divider direction="vertical"></el-divider>
      <div id="ml" class="wide-ml">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          
          <el-tab-pane label="题单" name="first" ></el-tab-pane>
          <el-tab-pane label="个人主页" name="second"></el-tab-pane>
          <el-tab-pane label="个人主页" name="three"></el-tab-pane>
   
          <template slot-scope="props">
            <div class="custom-tab" :class="{ 'is-active': props.isActive }">{{ props.label }}</div>
          </template>
        </el-tabs>
      </div >
      
      <div v-if="$route.path!='/login'" style="width: auto; display: inline-block;height: 100%;" >
        <div id="WZ" v-if="false">
          <el-button type="text">登录</el-button>
          
          <el-button type="text">注册</el-button>
        </div>
        <div v-if="true">



          <el-dropdown @command="selected"  trigger="click">
            <span class="el-dropdown-link" style="cursor: pointer;">
              <div id="tx" style="display: flex; align-items: center; ">
                  <span style="margin-top: 10px;"><el-avatar :src="avatarURL"></el-avatar></span>
                  <span style="font-size:17px; margin-left: 10px;">用户名</span>
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
</template>

<style scoped>
.horizontal-container {
  display: flex;
  flex-direction: row;
  width: 1440px;
  margin: auto;
  
  
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
 margin-left: 150px;
}
#tx {
  margin-left: 60px;
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
  }


</style>

<script>
import Logo from './Logo.vue';
import Avatar from './Avatar.vue';
import Cookies from 'js-cookie';
import axios from 'axios';

export default {
  data() {
    return {
      avatarURL: "http://localhost:8080/src/avatar/Snake.jpg",
      path: '',
      authority: -2 ,
      activeName: 'first',
      username: '111111',
    
    };
  },

  


  components: {
    Logo,
    Avatar,
  },
  methods: {
    handleClick(tab) {
      console.log(tab);
    },
    selected(command) {
      console.log(Cookies.get());
      if(command="command"){
        Cookies.remove('token')
        Cookies.remove('id')
        Cookies.remove('name')
        Cookies.remove('authority')
        this.$router.push('/login')
      }

    }
  },
  mounted() {
    console.log();
  this.$axios.get("/user/avatar/"+Cookies.get("id")).
  then((response)=>{
    console.log(response);
  })
  .catch((err)={

  })
  },
};



</script> 