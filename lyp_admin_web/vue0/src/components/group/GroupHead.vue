<template>
  <div>
    <el-header :height="bodyHeight"
              style="background: #e8874a;
              border-radius: 4px;
              box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);">
      <el-row justify="end" type="flex">
        <el-col>
          <el-tooltip>
            <span slot="content">{{username}}</span>
            <el-avatar :src="avatarImage.src" v-if="avatarImage.src !== ''"></el-avatar>
            <el-avatar :icon="avatarImage.icon" v-else-if="avatarImage.icon !== ''"></el-avatar>
            <el-avatar v-else-if="avatarImage.name !== ''">{{avatarImage.name}}</el-avatar>
            <el-avatar icon="el-icon-user-solid" v-else></el-avatar>
          </el-tooltip>
        </el-col>
        <el-col :span="1">
          <el-tooltip content="设置">
            <el-button @click="setterCenterVisible = true" icon="el-icon-setting">设置</el-button>
          </el-tooltip>
        </el-col>
      </el-row>
    </el-header>

    <el-dialog
      :visible.sync="setterCenterVisible"
      center
      title="设置"
      width="50%">
      <div>
      </div>
      <span class="dialog-footer" slot="footer">
        <el-button @click="setterCenterVisible = false">取 消</el-button>
        <el-button @click="setterCenterVisible = false" type="primary">保 存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "GroupHead",
        data() {
            return {
                avatarImage: {
                    src: "",
                    icon: "",
                    name: ""
                },
                username: "",
                setterCenterVisible: false
            }
        },
        props: {
            bodyHeight: ""
        }
    }
</script>

<style scoped>

</style>
