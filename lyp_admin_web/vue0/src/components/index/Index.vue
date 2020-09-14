<template>
  <div>
    <el-row :align="'middle'" :justify="'center'" :type="'flex'">
      <el-image :src="require('../../assets/logo.png')"></el-image>
    </el-row>
    <el-row :align="'middle'" :justify="'center'" :type="'flex'">
      <div style="border-radius: 4px;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);background: white;">
        <el-tabs @tab-click="handleClick" type="card" v-model="activeName">
          <el-tab-pane label="游客模式" name="first">
            <div style="height: 300px">
              <el-row :align="'middle'" :justify="'center'" :type="'flex'">
                <el-button :loading="firstLoading.status" @click="firstClick()" plain type="primary">
                  {{firstLoading.msg}}
                </el-button>
              </el-row>
            </div>
          </el-tab-pane>
          <el-tab-pane label="注册账号" name="second">
            <LogonPage></LogonPage>
          </el-tab-pane>
          <el-tab-pane label="登录账号" name="third">
            <LoginPage></LoginPage>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-row>
  </div>
</template>
<script>

    import LoginPage from "./LoginPage";
    import LogonPage from "./LogonPage";

    export default {
        name: "Index",
        components: {LogonPage, LoginPage},
        data() {
            return {
                firstLoading: {
                    status: false,
                    msg: "点击进入(游客模式)"
                },
                activeName: "first"
            }
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            },
            async firstClick() {
                this.firstLoading.status = true;
                this.firstLoading.msg = "正在进入中";
                await this.$message('当前为游客状态');
                if (localStorage.getItem("userId")) {
                    console.log(localStorage.getItem("userId"));
                    await this.$message("游客id:" + localStorage.getItem("userId"));

                    await this.$router.push({path: '/public/group'});
                } else {
                    this.$http.get("http://localhost:8080/user/uuid").then((response) => {
                        console.log(response.data);
                        this.$message("游客id:" + response.data);
                        localStorage.setItem("userId", response.data);

                        this.$router.push({path: '/public/group'});
                    }).catch((error) => {
                        console.log("错误");
                        console.log(error);
                        this.$notify.error({
                            title: '网络连接错误',
                            message: '无法连接到服务器'
                        });
                    });
                }
            }
        },
        async created() {
            if (!WebSocket) {
                await this.$message.error("您的浏览器不支持websocket 请使用 chrome firefox edge 以获得最佳体验");
            } else {
                await this.$message.success("浏览器支持websocket 可以正常使用");
            }

            let item = localStorage.getItem("userId");
            if (item) {
                await this.$message.success("欢迎回来,游客:" + item);
            }

            this.$http.post("http://localhost:8080/user/login").then((response)=>{
                if (response.data === "已经登陆" || response.data === "自动登录"){
                    localStorage.removeItem("userId");
                    this.$router.push({path: '/public/group'});
                }
            }).catch((error) => {
                console.log("错误");
                console.log(error);
                this.$notify.error({
                    title: '网络连接错误',
                    message: '无法连接到服务器'
                });
            });
        }
    }
</script>

<style scoped>
</style>
