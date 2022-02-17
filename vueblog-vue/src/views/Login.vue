<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/header.jpg" alt="not found" />
      </div>
      <el-form
        ref="loginFormRef"
        :rules="loginRules"
        :model="loginForm"
        class="login_form"
        label-width="0"
      >
        <el-form-item prop="username">
          <el-input
            placeholder="请输入用户名"
            v-model="loginForm.username"
            prefix-icon="iconfont icon-denglu"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            placeholder="请输入密码"
            v-model="loginForm.password"
            prefix-icon="iconfont icon-mima"
            type="password"
          ></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button type="info" @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      // 表单数据
      loginForm: {
        username: "", //Deng
        password: "" //admin
      },
      // 验证表单数据
      loginRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 1,
            max: 12,
            message: "长度在 1 到 12 个字符",
            trigger: "blur"
          }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 1, max: 16, message: "密码为 1~16 位", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    resetForm() {
      this.$refs.loginFormRef.resetFields();
    },
    submitForm() {
      this.$refs.loginFormRef.validate(valid => {
        if (valid) {
          const _this = this;
          this.$axios.post("/user/login", this.loginForm).then(response => {
            // const jwt = res.headers["authorization"];
            // const userInfo = res.data.data;
            // 把数据共享出去
            _this.$store.commit("SET_TOKEN", response.data.data.token);
            _this.$store.commit("SET_USERINFO", response.data.data.userInfo);
            // 获取
            // console.log(_this.$store.getters.getUser);
            _this.$router.push("/blogs");
          });
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style>
.login_container {
  background: url("../assets/bg17.jpg") repeat scroll 0% 0%;
  background-size: auto;
  height: 100%;
  background-size: cover !important;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.avatar_box {
  width: 130px;
  height: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: lightblue;
}

img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background-color: #eee;
}

.btns {
  display: flex;
  justify-content: center;
}

.btns button {
  width: 200px;
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 10px;
  box-sizing: border-box;
}
</style>