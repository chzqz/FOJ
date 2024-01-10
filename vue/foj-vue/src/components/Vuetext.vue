<template>
    <div>
  <div class="NR">题目名：<span><el-input  type="textarea"    placeholder="请输入内容"  v-model="textarea"></el-input></span></div>
  <div class="NR">题目描述：<span><el-input  type="textarea"    placeholder="请输入内容"  v-model="textarea"></el-input></span></div>
  <div class="NR">提示：<span><el-input  type="textarea"    placeholder="请输入内容"  v-model="textarea"></el-input></span></div>
  <div class="NR">最大运行时间(ms)：<span><el-input  type="textarea"    placeholder="请输入内容"  v-model="textarea"></el-input></span></div>
  <div class="NR">最大运行内存(MB)：<span><el-input  type="textarea"    placeholder="请输入内容"  v-model="textarea"></el-input></span></div>
  <div class="NR">最大线程数：<span><el-input  type="textarea"    placeholder="请输入内容"  v-model="textarea"></el-input></span></div>
  <div class="NR">	标签id列表：<span><el-input  type="textarea"    placeholder="请输入内容"  v-model="textarea"></el-input></span></div>
  <div><el-switch v-model="value1" active-text="题目开放" > </el-switch>
  <el-select v-model="value" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
</div>
<div>
  <el-upload
  class="upload-demo"
  action="https://jsonplaceholder.typicode.com/posts/"
  :on-change="handleChange"
  :file-list="fileList">
  <el-button size="small" type="primary">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
</el-upload>
</div>
</div>

</template>

<script>
export default {
    data()
    {
        return{
            value1: true,
        value2: true,

        options: [{
          value: '选项1',
          label: '简单'
        }, {
          value: '选项2',
          label: '普通'
        }, {
          value: '选项3',
          label: '困难'
        }],
        value: ''
        }
    }
}
</script>

<style>
.NR{
  display: flex;
}
</style>