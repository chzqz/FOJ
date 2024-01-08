<template>
    <div id="BD">
      <div class="container">
        <div class="tit">注册</div>
       
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="邮箱" prop="age">
               <el-input v-model.number="ruleForm.age"></el-input>
                <el-form-item label="密码" prop="pass">
             <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
         </el-form-item>
              <el-form-item label="确认密码" prop="checkPass">
    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
  </el-form-item>

  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
       
      </div>
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
  </template>
  
  <script>
  export default {
    data() {
      var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('邮箱不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            if (value < 18) {
              callback(new Error('必须年满18岁'));
            } else {
              callback();
            }
          }
        }, 1000);
      };
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
        ruleForm: {
          pass: '',
          checkPass: '',
          age: ''
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          age: [
            { validator: checkAge, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
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
    width: 600px;
    height: 700px;
    /*阴影*/
    box-shadow: 0 5px 20px rgba(0,0,0,0.1);
}
#CetText{
   margin-left: -20%;
   line-height:10px;
}

.container .tit{
    font-size: 26px;
    
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
