
<template>
  <div>
    <span style="color: rgb(8, 163, 104); font-weight: 900;">通过</span>
    <div>
      <div>
        <div>运行时间</div>
        <span>{{ time }}</span>
        <!-- <span>{{ AnswerData.data.time }}</span> -->
      </div>
      <div>
        <div>所耗内存</div>
        <span>{{ memory }}</span>
        <!-- <span>{{ AnswerData.data.memory }}</span> -->
      </div>
      <div>
        <span>{{ language }}</span>
        <!-- <span>{{ AnswerData.data.language }}</span> -->
      </div>
      <el-input :type="'textarea'" :value="code" :disabled="true"></el-input>
      <!-- <el-input :type="'textarea'" :value="AnswerData.data.code" :disabled="true"></el-input> -->
      <div>
        <span>{{ sub_time }}</span>
        <!-- <span>{{ AnswerData.data.sub_time }}</span> -->
      </div>
    </div>
  </div>
</template>
<script>
import Vuetext from '../components/Vuetext.vue'

export default {
    props:{
       time:{
        type:Number,
       },
       memory:{
        type:Number,
       },
       language:{
        type:String,
       },
       code:{
        type:String,
       },
       sub_time:{
        type:String,
       }
    },
    return:{

    },
    components: {
    
    
    },
  };
</script>

<style>

</style>

