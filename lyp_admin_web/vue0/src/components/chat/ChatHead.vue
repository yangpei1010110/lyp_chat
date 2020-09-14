<template>
  <div>
    <el-page-header :content="ChatContent" :title="ChatTitle" @back="goBack">
    </el-page-header>
  </div>
</template>

<script>
    export default {
        name: "ChatHead",
        methods: {
            goBack() {

            }
        },
        props: {
            ChatTitle: {
                type: String,
                default() {
                    return "返回"
                }
            },
            ChatContent: {
                type: String,
                default() {
                    return "未知聊天对象"
                }
            }
        }
    }
</script>

<style scoped>

</style>
