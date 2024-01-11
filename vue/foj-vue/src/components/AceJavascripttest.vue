<template>
  <div class="codeEditBox" :style="{height: height + 'px'}">
  <aceEditor ref="editor"
       :value="value"
       :lang="LA"
       :theme="theme"
       :options="options"
       @init="initEditor"
       v-bind="config">
  </aceEditor> 
 

</div>
</template>


<script>
//引入vue2-ace-editor
import aceEditor from 'vue2-ace-editor'
//引入ace 后续修改自定义标签用到
import ace from 'brace'
//代码提示
import 'brace/ext/language_tools'
import 'brace/mode/javascript'
import 'brace/snippets/javascript'
import 'brace/mode/c_cpp'
import 'brace/snippets/c_cpp'
import 'brace/mode/java'
import 'brace/snippets/java'
import 'brace/mode/python'
import 'brace/snippets/python'
//搜索
import 'brace/ext/searchbox'
//主题
//白底色 带高亮
import 'brace/theme/chrome'
import 'brace/theme/xcode'
//白底色黑字  不带高亮
import 'brace/theme/github'
//黑底色
import 'brace/theme/tomorrow_night_eighties'
//蓝底色
import 'brace/theme/tomorrow_night_blue'
//黑底色
import 'brace/theme/vibrant_ink'

export default {
  name: 'Editor',
  components: {
    aceEditor
  },
  props: {
    value: {
      type: String,
      default: ''
    },
    height: {
      type: Number,
      default: 300
    },
    readOnly: {
      type: Boolean,
      default: false,
    },
    theme: {
      type: String,
      default: 'xcode'
    },
   
    config: {
      type: Object,
      default: () => {
        return {
          fontSize: 16,
          
        }
      }
    },
    LA:{
        type:String,
        default:'c_cpp',
    }
  },
  computed: {
    options() {
      console.log(333,this.LA)
      return {
        lang:this.LA,
        enableBasicAutocompletion: true,//启动代码补全功能
        enableSnippets: true,//启动代码段
        showPrintMargin: false,//显示打印边距
        fontSize: this.config.fontSize,//字体大小
        enableLiveAutocompletion: true,//启用实时自动完成
        readOnly: this.readOnly,//只读
        showInvisibles:true,
        tooltipFollowsMouse:true ,
        
      }
    }
  },
  methods: {
    initEditor(editor) {
      //自定义标签
      const that = this
          const completer = {
              getCompletions: function (editors, session, pos, prefix, callback) {
                  that.setCompleteData(editors, session, pos, prefix, callback)
              }
          }
          const lnTools = ace.acequire('ace/ext/language_tools')
          lnTools.addCompleter(completer)
      // 监听值的变化
      editor.getSession().on('change', () => {
        this.$emit('change', editor.getValue())
      })
    },
    getValue() {//获取值
      return this.$refs.editor.editor.getValue()
      
    },
    setValue(value) {//赋值
      this.$refs.editor.editor.session.setValue(value)
    },
    clear() {//清空
      this.$refs.editor.editor.session.setValue('')
    },
    setCompleteData (editor, session, pos, prefix, callback) {
                const data = [
                    {
                        meta: '方法名', // 描述
                        caption: 'get', // 展示出的名字（一般与value值相同）
                        value: 'function get(){}', // 数据值
                        score: 1 // 权重 数值越大 提示越靠前
                    },
                    {
                        meta: '方法名',
                        caption: 'set',
                        value: 'function set(){}',
                        score: 2
                    }
                ]
                if (prefix.length === 0) {
                    return callback(null, [])
                } else {
                    return callback(null, data)
                }
      },
      Printf() {
         
         console.log(111+this.getValue());
      }
  },
  // watch: {
  //   LA: function(newLang, oldLang) {
  //     // 每当 LA 的值变化时，更新 aceEditor 组件的 lang 属性
  //     this.$refs.editor.setOptions({
  //       lang: newLang
  //     });
  //   }
  // },
  
}
</script>