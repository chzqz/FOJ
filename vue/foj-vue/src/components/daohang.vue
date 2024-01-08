 <template>
  <div class="dh">
    <div class="horizontal-container">
      <div id="lg"><Login></Login></div>
      <div id="ml" class="wide-ml">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          
         <el-tab-pane label="题单" name="first"></el-tab-pane>
          <el-tab-pane label="个人主页" name="second"></el-tab-pane>
   
          <template slot-scope="props">
            <div class="custom-tab" :class="{ 'is-active': props.isActive }">{{ props.label }}</div>
          </template>
         
        </el-tabs>

      </div>
      <div id="WZ">
      <el-button type="text">登录</el-button>
      <el-button type="text">注册</el-button>
    </div>
      <div id="tx"><Avatar></Avatar></div>

      <div id="name"><span v-if="username">{{ username }}</span></div>
    </div>
  </div>
</template>

<style scoped>
.horizontal-container {
  display: flex;
  flex-direction: row;
}

#lg {
  margin-top: 1%;
  margin-left: 13%;
}

#ml {
  margin-top: 1%;
  margin-left: 3%;
  width: 700px;
}
#WZ{
 margin-top: 1%;
 margin-left: 150px;
}
#tx {
  margin-top: 1%;
  margin-left: 60px;
}

#name {
  margin-top: 2%;
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

<script>
import Login from './Login.vue';
import Avatar from './Avatar.vue';

export default {
  data() {
    return {
      activeName: 'second',
      username: '111111',
    
    };
  },

  components: {
    Login,
    Avatar,
  },
  methods: {
    handleClick(tab) {
      console.log(tab);
    

    }
  },
};
</script> 