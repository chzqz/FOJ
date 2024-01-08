 <template>
    <div id="BD">
      <div style="width:1440px; 
      display: flex;
      justify-content: center;
      align-items: center;">
      <div class="container">
        <div class="tit">登录</div>
        <input type="text" placeholder="账号" v-model="username">
        <input type="password" placeholder="密码" v-model="password">
        <button @click="login">登录</button>
        <span>没有账号？<router-link to="/register">去注册</router-link></span>
      </div>
      <router-view></router-view>
      <div class="square">
        <ul>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
        </ul>
      </div>
      <div class="circle">
        <ul>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
        </ul>
      </div>
    </div>
  </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    data() {
      return {
        username: '',
        password: '',
        msg:' '
      };
    },
    methods: {
      login() {
        // 在这里实现登录逻辑
        console.log('登录按钮被点击');
        let url='http://localhost:8080/login';
        const params = {
        username:this.username,
        password:this.password
        };
        console.log(params);
     
        axios.post(url,
                params
        ).then((response)=> {
          console.log(response);
          
          var code=response.data.code
         if(code!=200) this.wornning(response.data.msg)
         else{
          let date = new Date(); //获取当前时间
          date.setTime(date.getTime() + 365 * 24 * 3600 * 1000); //格式化为cookie识别的时间
          document.cookie = "token" + "=" + response.data.data.token + ";expires=" + date.toGMTString();  //设置cookie
          document.cookie = "id" + "=" + response.data.data.id + ";expires=" + date.toGMTString();  //设置cookie
          document.cookie = "name" + "=" + response.data.data.name + ";expires=" + date.toGMTString();  //设置cookie
          document.cookie = "authority" + "=" + response.data.data.authority + ";expires=" + date.toGMTString();  //设置cookie
          console.log(document.cookie);
          this.$router.push({ name:'Home' });
          // query:{ name:‘word’, age:‘11’ } 

        }
        }).catch(function(error) {
          console.log("错误："+ error);
        });
      
      },
      wornning(msg){
        this.$message({
          message:msg,
          type: 'warning'
        })
      }
    }
    
  }

  </script>
  


<style scoped>
*{
    /*初始化*/
    margin: 0;                      
    padding: 0;
}
#BD {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(200deg, #b3d4f7, #e0faf2); /* 修改这里的颜色值 */
  overflow: hidden;
}
.container{
    /* margin-left: 38%;
    margin-top: 8%; */
   
    z-index: 1;
    background-color: #fff;
    border-radius: 15px;
    display: flex;
    /*垂直配列*/
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 350px;
    height: 500px;
    /*阴影*/
    box-shadow: 0 5px 20px rgba(0,0,0,0.1);
}
.container .tit{
    font-size: 26px;
    margin: 65px auto 70px auto;
}
.container input{
    width: 280px;
    height: 30px;
    text-indent: 8px;
    border: none;
    border-bottom: 1px solid #ddd;
    outline: none;
    margin: 12px auto;
}
.container button{
    width: 280px;
    height: 40px;
    margin: 35px auto 40px auto;
    border: none;
    background: linear-gradient(-200deg,#e6e6e6,#aac2ee);
    color: #fff;
    font-weight: bold;
    letter-spacing: 8px;
    border-radius: 10px;
    cursor: pointer;
    /*动画过渡*/
    transition: 0.5s;
}

.container button:hover{
    background: linear-gradient(-200deg,#aac2ee,#e6e6e6);
    background-position-x: -280px;
}
.container span{
    font-size: 14px;
}
.container a{
    color: rgb(228, 224, 228);
    text-decoration: none;
}

ul li{
    position: absolute;
    border: 1px solid #fff;
    background-color: #ffffff;
    width: 30px;
    height: 30px;
    list-style: none;
    opacity: 0;
}
.square li{
    top: 40vh;
    left: 60vw;
    animation: square 10s linear infinite;
}
.square li:nth-child(2){
    top: 80vh;
    left: 10vm;
    /*动画延时时间*/
    animation-delay: 2s;
}
.square li:nth-child(3){
    top: 80vh;
    left: 85vm;
    /*动画延时时间*/
    animation-delay: 4s;
}

.square li:nth-child(4){
    top: 10vh;
    left: 70vm;
    /*动画延时时间*/
    animation-delay: 6s;
}

.square li:nth-child(5){
    top: 10vh;
    left: 10vm;
    /*动画延时时间*/
    animation-delay: 8s;
}

.circle li{
    bottom: 0;
    left: 15vw;
    animation: circle 10s linear infinite;
}
.circle li:nth-child(2){
    left: 35vw;
    animation-delay: 2s;
}
.circle li:nth-child(3){
    left: 55vw;
    animation-delay: 4s;
}
.circle li:nth-child(4){
    left: 75vw;
    animation-delay: 6s;
}
.circle li:nth-child(5){
    left: 90vw;
    animation-delay: 8s;
}

@keyframes square {
    0%{
       transform: scale(0) rotate(0deg);
        opacity: 0;
    }
    100%{
        transform: scale(0) rotate(1000deg);
        opacity: 0;
    }

}
@keyframes circle{
    0%{
        transform: scale(0) rotate(0deg);
        opacity: 1;
        bottom: 0;
        border-radius: 0;
    }
    100%{
        transform: scale(0) rotate(1000deg);
        opacity: 0;
        bottom: 90vh;
        border-radius: 50%;
    }

}


</style> 
