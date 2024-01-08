<template>
    <div>
      <!-- <el-row :gutter="20">
        <el-col :span="6">
        </el-col>
      </el-row> -->
      <div class="a">
        <div id="TGCS">
            <el-statistic
              group-separator=","
              :precision="2"
              :value="value2"
              :title="title1"
            ></el-statistic>
          </div>
          <div id="CSCS">
            <el-statistic
              group-separator=","
              :precision="2"
              :value="value2"
              :title="title2"
            ></el-statistic>
          </div>
      </div>
    </div>
  </template>
<script>
  export default {
    data() {
      return {
        like: true,
        value1: 4154.564,
        value2: 1333,
        title1: "通过次数",
        title2: "尝试次数",
      };
    },
  };
</script>
<style scoped>
    .a{
        margin-top:30px;
    }
 
  #CSCS{
   margin-top: 30px;
  }

</style>