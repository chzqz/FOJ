<template>
  <div id="srh">
    <div id="nd1">
      <el-select v-model="value1" placeholder="请选择"  @change="dfhange1">
        <el-option
          v-for="item in options"
          :key="item.value1"
          :label="item.label1"
          :value="item.value1"
         
          >
          
        </el-option>
      </el-select>
    </div>
    <div id="nd2">
      <el-select v-model="value2" multiple placeholder="请选择" @change="dfhange2">
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
        { value1: '选项1', label1: '简单' },
        { value1: '选项2', label1: '中等' },
        { value1: '选项3', label1: '困难' },
      ],
      options2: [
        { value2: '选项1', label2: '困难' },
        { value2: '选项2', label2: '困难' },
        { value2: '选项3', label2: '困难1' },
        { value2: '选项4', label2: '困难' },
      ],
      value1: '',
      value2: [],
      input: '',
    };
  },
  methods: {
    dfhange1(){

    }

  }
};
</script>

<style>
#srh {
  display: flex;
  flex-direction: row;
}
#nd1 {
  width: 10%;
}
#nd2 {
  width: 30%;
  margin-left: 2%;
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
