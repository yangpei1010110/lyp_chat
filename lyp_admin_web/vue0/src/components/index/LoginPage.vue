<template>
  <div>
    <el-form :model="accountData" :rules="accountDataRules" ref="accountData">
      <el-form-item label="登录账号" prop="username">
        <el-input v-model="accountData.username"></el-input>
      </el-form-item>
      <el-form-item label="登录密码" prop="password">
        <el-input type="password" v-model="accountData.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-image :src="accountData.verifyImageUrl" @click="freshVerify" ref="verifyImage"></el-image>
      <el-form-item label="验证码" prop="verifyString">
        <el-input v-model="accountData.verifyString" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('accountData')">提交</el-button>
        <el-button @click="resetForm('accountData')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

    export default {
        name: "LoginPage",
        data() {
            return {
                firstLoading: {
                    status: false,
                    msg: "登陆"
                },
                accountData: {
                    username: "",
                    password: "",
                    passwordAgain: "",
                    verifyString: "",
                    verifyImageUrl: "http://localhost:8080/user/get-verify-code?num="+Math.random()*10
                },
                telephoneData: {
                    phoneNumber: "",
                    verifyString: ""
                },
                accountDataRules: {
                    username: [{ required: true, message: '请输入账号', trigger: 'blur' },
                        {pattern: /^(\w){6,20}$/, message: '只能输入6-20个字母、数字、下划线'}],
                    password: [{ required: true, message: '请输入密码', trigger: 'blur' },
                        {pattern: /^(\w){6,20}$/, message: '只能输入6-20个字母、数字、下划线'}],
                    verifyString: [

                    ]
                },
                telephoneDataRules: {

                }
            }
        },
        methods: {
            async submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid){
                        this.firstLoading.status = true;
                        this.firstLoading.msg = "登陆中";

                        let data = new FormData();
                        data.append("username",this.accountData.username);
                        data.append("password",this.accountData.password);
                        data.append("verifyString",this.accountData.verifyString);

                        this.$http.post("http://localhost:8080/user/login",data).then((response) => {
                            this.$message.success(response.data);
                            if (response.data === "登录成功" || response.data === "已经登陆" || response.data === "自动登录"){
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
                    } else {
                        console.log("error submit!!");
                        return false;
                    }
                });
            },
            async resetForm(formName){
                this.$refs[formName].resetFields();
            },
            freshVerify(){
                this.$http.get("http://localhost:8080/user/update-verify-code").then((response) => {
                    this.$message.success(response.data);

                    let num=Math.ceil(Math.random()*10);//生成一个随机数（防止缓存）
                    this.accountData.verifyImageUrl = "http://localhost:8080/user/get-verify-code?num="+num;
                });
            }
        }
    }
</script>

<style scoped>

</style>
