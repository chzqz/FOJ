<template>
  <div id="srh">
    <div id="nd1">
      <el-select v-model="selectionOptions.value1" placeholder="请选择"  @change="dfhange1">
        <el-option
          v-for="item in options"
          :key="item.value1"
          :label="item.label1"
          :value="item.value1">
        </el-option>
      </el-select>
    </div>
    <div id="nd2">
      <el-select v-model="selectionOptions.value2" multiple multiple-limit="3" placeholder="请选择" filterable >
        <el-option
          v-for="item in options2"
          :key="item.value2"
          :label="item.label2"
          :value="item.value2">
        </el-option>
      </el-select>
    </div>
    <div class=" input-box mb20">
	<input type="text" class="input" />
	<span class="span">搜</span>
</div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      options: [
        { value1: null, label1: '全部' },
        { value1: '0', label1: '简单' },
        { value1: '1', label1: '中等' },
        { value1: '2', label1: '困难' },
      ],
      options2: [
        { value2: '0', label2: '困难' },
        { value2: '1', label2: '困难' },
        { value2: '2', label2: '困难1' },
        { value2: '3', label2: '困难' },
      ],
      value1: '',
      value2: [],
      input: '',
      selectionOptions:{
        value1: '',
        value2: [],
        // 添加其他需要的参数
      }
    };
  },
  methods: {
    dfhange1(){
       console.log("gtygtygytygt"+this.selectionOptions)
      this.$emit("fromchild",this.selectionOptions);
    },
    toFather(){
       
      },
    

  }
};
</script>

<style scoped>
#srh {
  display: flex;
  flex-direction: row;
}
#nd1 {
  width: 100px;
  margin-left: 9px;
}
#nd2 {
  margin-left: 30px;
}
#nd2 > div {
  width: 300px;
}

.input-box {
	position: relative;
	display: inline-block;
  margin-left: auto;
  margin-right: 2%;
}

.input {
	padding: 0 40px 0 20px;
	width: 160px;
	height: 38px;
	font-size: 14px;
	border: 1px solid #eee;
	border-radius: 40px;
	background: #eee;
	transition: width .5s;
	transition-delay: .1s;
}

.span {
	position: absolute;
	top: 4px;
	right: 5px;
	width: 30px;
	height: 30px;
	line-height: 30px;
	padding: 0;
	color: #969696;
	text-align: center;
	background: #c2bfbf;
	border-radius: 50%;
	font-size: 15px;
	cursor: pointer;
}
.input:focus {
	width: 280px;
	outline: none;
	box-shadow: none;
}
	
.input:focus+.span {
	background-color: rgb(191, 224, 247);
	color: #fff;
}



</style>
