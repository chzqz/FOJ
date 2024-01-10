<!-- MyPopup.vue -->

<template>
    <div>
      <!-- 弹窗的内容 -->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
      >
        <p>这是弹窗的内容</p>
        <el-button @click="closeDialog">关闭弹窗</el-button>
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        dialogVisible: false
      };
    },
    methods: {
      closeDialog() {
        this.dialogVisible = false;
      }
    }
  };
  </script>
  
  <style scoped>
  /* 可以放一些组件样式 */
  </style>
  