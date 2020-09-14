<template>
  <div>
    <el-row>
      <div v-if="isLeft">
        <el-col>
          <el-avatar
            :size="'large'"
            :src="headImage"
            v-if="isHaveHeadImage">
          </el-avatar>
          <el-avatar v-else>
            {{userName}}
          </el-avatar>
        </el-col>
        <el-col>
          <el-card shadow="hover">
            {{userMassage}}
          </el-card>
        </el-col>
      </div>
      <div v-else>
        <el-col>
          <el-card shadow="hover">
            {{userMassage}}
          </el-card>
        </el-col>
        <el-col>
          <el-avatar
            :size="'large'"
            :src="headImage"
            v-if="isHaveHeadImage">
          </el-avatar>
          <el-avatar v-else>
            {{userName}}
          </el-avatar>
        </el-col>
      </div>
    </el-row>
  </div>
</template>

<script>
    export default {
        name: "ChatRow",
        data() {
            return {
                isLeft: true,
                userName: "",
                userMassage: "",
                isHaveHeadImage: false,
                headImage: ""
            }
        },
        props: []
    }
</script>

<style scoped>

</style>
