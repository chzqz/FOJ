<template>
        <el-menu 
          default-active="1-4-1" 
          class="el-menu-vertical-demo" 
          @open="handleOpen" 
          @close="handleClose" 
          :collapse="isCollapse"
          text-color="#12171C"
          active-text-color="#0F1419"
        >
        <img class="biao" src="../img/biaoti.png">
        <el-menu-item @click="clickMenu(item)" v-for="item in menuData" :key="item.name" :index="item.name" class="menu">
          <i :class="`el-icon-${item.icon}`"></i>
        <span slot="title" >{{item.label}}</span>
        </el-menu-item>
        <el-button type="info" round class="button" @click="centerDialogVisible=true">Post</el-button>
        <div class="dialog-Post">
        <el-dialog class="round-dialog"
            title="提示"
            :visible.sync="centerDialogVisible"
            width="40%"
            height="60%"
            center>
            <span>需要注意的是内容是默认不居中的</span>
            <span slot="footer" class="dialog-footer">
              <el-button @click="centerDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
      </div>
      <div class="content">
          <el-dropdown>
          <span class="el-dropdown-link">
              <el-button round class="custom-button">
                <img src="../img/qaq.png" class="asst">
                <div class="text">
                <p>大户爱上太空</p>
                <p>23514@qq.com</p>
              </div>
              </el-button>
          </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item >Add an existing account</el-dropdown-item>
              <el-dropdown-item @click.native="GoToPage">Log out 23514@qq.com</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-menu>
</template>
<style lang="less" scoped> 
  .asst{
    width: 50px;
    height: 50px;
    border-radius: 100%;
    margin-left: -161px;
    margin-top: -8px;
  }
  .text{
    margin-left: 10px;
    margin-top: -60px;
  }
  /deep/.custom-button{
    width: 240px;
    height: 63px;
  }
  .content{
    margin-top: 100px;
  }
  .el-dropdown-link {
      cursor: pointer;
      color: #409EFF;
    }
    .el-icon-more {
      font-size: 12px;
      margin-left: 10px;
      margin-top: -80px;
    }
    .biao{
      width: 155px;
      border-radius: 50%;
      margin-top: 20px;
    }
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 250px;
        min-height: 400px;
    }
    .el-menu{
      height: 100vh;
      text-align: center;
      width: 200px;
    }
    .menu{
      height: 80px;
      font-size: large;
    }
    .button{
      background-color: #1A8CD8;
      width: 165px;
    }
    /deep/ .round-dialog{
      border-radius: 50px;
    }
</style>

<script>
  export default {
    data() {
      return {
        centerDialogVisible: false,
        isCollapse: false,
        menuData:[
          {
            path:"/",
            name:"home",
            label:"Home",
            icon:"s-home",
            url:"Home/home",
          },
          {
            path:"/Explore",
            name:"explore",
            label:"Explore",
            icon:"search",
            url:"Explore/explore",
          },
          {
            path:"/Message",
            name:"message",
            label:"Message",
            icon:"chat-dot-round",
            url:"Message/message",
          },
          {
            path:"/Profile",
            name:"profile",
            label:"Profile",
            icon:"s-custom",
             url:"Profile/profile",
          }
        ]        
      };
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      clickMenu(item){
        console.log(item)
        if(this.$route.path!==item.path && !(this.$route.path==='/home' && (item.path ==='/'))){
            this.$router.push(item.path)
        }
      },
      GoToPage(){
        this.$router.push('/login');
      }
    },
    computed:{
      noChildren(){
        return this.menuData.filter(item=>!item.Children)
      }
    }
  }
</script>