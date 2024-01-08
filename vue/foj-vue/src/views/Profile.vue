<template>
     <el-row>
    <el-col :span="16">
        <div class="premial">
            <div class="button-container">
            <el-button size="medium" text-algin="center" @click="show = true" round>For you</el-button>
            <el-button size="medium" @click="show = false" round>Following</el-button>
            </div>
        <div v-if="show">
            <el-card class="box-card" shadow="hover"> 
            <div class="proModule">
                <span>卡片名称</span>
                <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
            </div>
            <div v-for="o in 4" :key="o" class="text item">
                {{'列表内容 ' + o }}
            </div>
        </el-card>
        </div>
            <div class="text" v-else>
            <h2>Welcome to cup-cat</h2>
            <p>This is the best place to see what’s happening in </p>
            <p>your world. Find some people and topics to follow now.</p>
         </div>
        </div>
    </el-col>
    <el-col :span="8">
        <div class="search_container">
            <el-input type="text" class="search-input" placeholder="Search" suffix-icon="el-icon-search"></el-input>
        </div>
    </el-col>
</el-row>
</template>
<script>
export default {
    data() {
            return {}
    }
}
</script>
