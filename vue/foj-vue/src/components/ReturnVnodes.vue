
<template>
  <div id="container">
    <div style="width: 100%; ">
      <div style="width: 100%; ">
        <span :id="result.data.status==0 ? 'success':'fail'"  style="font-weight: 900; font-size: 24px;">{{result.data.status==0 ?  '通过':'未通过'}}</span>
        <div style="margin-top: 10px;">
            <span>{{ result.data.username}}</span>
            <span> 提交于 {{ result.data.sub_time}}</span>
        </div>
      </div>
    </div>
    <el-divider></el-divider>
    <div>
      <el-descriptions >
        <el-descriptions-item label="运行时间">{{ result.data.time !=null ?  result.data.time:'N/A'}}</el-descriptions-item>
        <el-descriptions-item label="所耗内存">{{ result.data.memory!=null ?  result.data.memory:'N/A' }}</el-descriptions-item>
        <el-descriptions-item label="语言">{{ result.data.language }}</el-descriptions-item>
    </el-descriptions>
    </div>
    <el-divider content-position="left">代码</el-divider>
    <div>
      <AceJavascripttest  style="max-height: 250px; color: transparent; "  :value=result.data.code :theme=theme readOnly='true' :LA="lang"
                ></AceJavascripttest>
    </div>
    <el-divider content-position="left">错误信息</el-divider>
    <div>
      <el-input :type="'textarea'" :value="result.data.errorMessage" :disabled="true" :autosize="{ minRows: 2, maxRows: 10 }"  resize="none" ></el-input>
    </div>
  </div>
</template>
<script>
import Vuetext from '../components/Vuetext.vue'
import AceJavascripttest from './AceJavascripttest.vue';
export default {
    props:{
       question: {
        type: Object
       },
       result: {
        type:Object,
       },
       lang: {
        type: String,
       }
    },
    data() {
      return {
      }
    },
    return:{


    },
    components: {
      AceJavascripttest,
    
    },
  };
</script>

<style scoped>
  #container{
    width: 100%px;
  }

  #success{
    color: rgb(8, 163, 104);
  }
  #fail{
    color: rgb(221, 0, 0);
  }
</style>

