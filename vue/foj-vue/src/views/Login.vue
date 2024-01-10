<template>
    <div id="BD">
      <div :id="reverseId" style="margin: auto;transition: 1s; width: 350px ;  transform-style: preserve-3d;">
        <!-- 登录页面 -->
        <div  class="login">
          <div class="container">
            <div class="logo"><logo width="100px" height="100px"></logo></div>
            <!-- <input type="text" placeholder="账号" v-model="username">
            <input type="password" placeholder="密码" v-model="password"> -->
            <div style="width: 250px; ">
              <el-input
                placeholder="请输入用户名"
                v-model="username"
                clearable>
              </el-input>
            </div>
            <div style="margin-top: 20px; width: 250px;">
              <el-input placeholder="请输入密码" v-model="password" show-password></el-input>
            </div>
            <button  class="loginbutton" @click="login">登录</button> 
            <span>没有账号？<el-button type="text"  native-type="button" @click="reverseCard">去注册</el-button></span>
          </div>
        </div>
        <!-- 注册页面 -->
        <div   class="register">
          <div class="container">
            
            <!-- <input type="text" placeholder="账号" v-model="username">
            <input type="password" placeholder="密码" v-model="password"> -->
            <!-- <div style="width: 250px; ">
              <el-input
                placeholder="请输入内容"
                v-model="username"
                clearable>
              </el-input>
            </div>
            <div style="margin-top: 20px; width: 250px;">
              <el-input placeholder="请输入密码" v-model="password" show-password></el-input>
            </div> -->
            
              <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item id="username" aria-autocomplete="user"  prop="username" style="margin: auto;"> 
                  <el-input type="username" v-model="ruleForm.username"  placeholder="用户名" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item  prop="email" style="margin: auto;"> 
                  <el-input type="email" v-model="ruleForm.email" placeholder="电子邮箱" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item  prop="pass" style="margin: auto;"> 
                  <el-input type="password" autocomplete="new-password" v-model="ruleForm.pass" placeholder="密码"  ></el-input>
                </el-form-item>
                <el-form-item  prop="checkPass">
                  <el-input type="password" autocomplete="new-password" v-model="ruleForm.checkPass" placeholder="确认密码"  ></el-input>
                </el-form-item>
              </el-form>
            
            <button  class="loginbutton" @click="register('ruleForm')">注册</button> 
            <span>已有账号？<el-button type="text"  native-type="button" @click="reverseCard">去登录</el-button></span>
          </div>
        </div>
      </div>
    </div>
</template>
  
<script>
import Logo from '@/components/Logo.vue';

  export default {
  components: { Logo },
    data() {
      // var checkAge = (rule, value, callback) => {
      //   if (!value) {
      //     return callback(new Error('年龄不能为空'));
      //   }
      //   setTimeout(() => {
      //     if (!Number.isInteger(value)) {
      //       callback(new Error('请输入数字值'));
      //     } else {
      //       if (value < 18) {
      //         callback(new Error('必须年满18岁'));
      //       } else {
      //         callback();
      //       }
      //     }
      //   }, 1000);
      // };
      var checkEmail = (rule, value, callback) =>{
        const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
        if (!value) {
          return callback(new Error('邮箱不能为空'))
        }
        if (mailReg.test(value)) {
            callback()
        } else {
          callback(new Error('请输入正确的邮箱格式'))
        }
        callback();
      }

      var checkName = (rule, value, callback) =>{
        if (!value) {
          return callback(new Error('用户名不能为空'))
        }
        callback();
      }

      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };

      return {
        username: '',
        password: '',
        msg:' ',
        status: 'login',
        reverseId: '',

        // 注册表单
        ruleForm: {
          pass: '',
          checkPass: '',
          username: '',
          email: ''
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          // age: [
          //   { validator: checkAge, trigger: 'blur' }
          // ]
          email: [
            {validator: checkEmail, trigger: 'blur'},
          ],
          username: [
            {validator: checkName, trigger: 'blur'},
          ]
        }
      };
    },
    methods: {
      async login() {
        // 在这里实现登录逻辑
        console.log('登录按钮被点击');
        let url='/login';
        const params = {
        username:this.username,
        password:this.password
        };
      
        await this.$axios.post(url,
                params
        ).then((response)=> {
          var code=response.data.code
          if(code===200) {
          let date = new Date(); //获取当前时间
          date.setTime(date.getTime() + 365 * 24 * 3600 * 1000); //格式化为cookie识别的时间
          document.cookie = "token" + "=" + response.data.data.token + ";expires=" + date.toGMTString();  //设置cookie
          document.cookie = "id" + "=" + response.data.data.id + ";expires=" + date.toGMTString();  //设置cookie
          document.cookie = "name" + "=" + response.data.data.name + ";expires=" + date.toGMTString();  //设置cookie
          document.cookie = "authority" + "=" + response.data.data.authority + ";expires=" + date.toGMTString();  //设置cookie

          this.$router.push({ name:'home' });
          // query:{ name:‘word’, age:‘11’ } 
          this.$router.go(0)

        }
        }).catch(function(error) {
          console.log("错误："+ error);
        });
        this.$dispatch('reload');
      },
      

      reverseCard() {

        if(this.reverseId=='reverse'){
          this.reverseId=''
            this.$router.push({
              path: '/login',
              query: {
                status: 'login'
              }
            }, ()=>{})
        }
        else{
          this.reverseId='reverse'
          this.$router.push({
              path: '/login',
              query: {
                status: 'register'
              }
            }, ()=>{})
        }
      },

      register(form) {
        this.$refs[form].validate((valid) => {
          if(valid) {
            //数据合格
            this.$axios.post('/regist',{
              name: this.ruleForm.username,
              email: this.ruleForm.email,
              password: this.ruleForm.pass,
            }).then((response)=>{
              if(response.data.code===200){
                this.$message({
                  message: '注册成功',
                  type: 'success'
                });
                this.reverseCard();
              }
            })

          } else {
            //数据不合格 
          }
        })
      },
    },

    mounted(){
      console.log(this.$route.query.status);
      if(this.$route.query.status=='register'){
        this.reverseId='reverse'
      }
      else this.reverseId=''
      
      console.log(this.reverseId);
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
  height: 100%;
  display: relative;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
}
.container{
    /* margin-left: 38%;
    margin-top: 8%; */
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    z-index: 1;
    background-color: #fff;
    border-radius: 15px;
    display: flex;
    /*垂直配列*/
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 350px;
    height: 470px;
    /*阴影*/
}

.logo{
  height: 20%;
  width: auto;
  padding-bottom: 40px;
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
.loginbutton{
    width: 250px;
    height: 40px;
    margin: 35px auto 40px auto;
    border: none;
    /* background: linear-gradient(-200deg,#e6e6e6,#aac2ee); */
    background: linear-gradient(90deg,#e0faf2,#7fb3fb,#e0faf2);
    color: #273378;
    font-weight: bold;
    font-size: 18px;
    letter-spacing: 8px;
    border-radius: 10px;
    cursor: pointer;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    /*动画过渡*/
    transition: 1s;
}

.loginbutton:hover{
    /* background: linear-gradient(-200deg,#aac2ee,#e6e6e6);
    background-position-x: -250px; */
    background: linear-gradient(90deg,#e0faf2,#7fb3fb,#e0faf2);
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    background-position-x: -500px;
    
}

::v-deep .el-input__inner{
  border-radius: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  transition: 0.5s;
}

::v-deep .el-input__inner:focus {
  transform: translateY(-3px)
}

.login{
  position: absolute;
}

.register{
  position: absolute;
  transform: rotateY(180deg);
  backface-visibility: hidden
}

#reverse {
  transform: perspective(500px) rotateY(180deg);     
}

::v-deep .el-form-item__content{
  margin: 0 !important;
  margin-top: 24px !important ;
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
