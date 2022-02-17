<template>
  <div>
    <Header></Header>

    <div class="mblog">
      <h2>{{ blog.title }}</h2>
      <el-link v-if="ownBlog">
        <el-button @click="edit(blog.id)" type="primary" icon="el-icon-edit" circle></el-button>
      </el-link>
      <el-link v-if="ownBlog">
        <el-button @click="deleteBlog" type="danger" icon="el-icon-delete" circle></el-button>
      </el-link>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>
  </div>
</template>

<script>
import "github-markdown-css";
import Header from "../components/Header";
export default {
  name: "BlogDetail",
  components: { Header },
  data() {
    return {
      blog: {
        id: "",
        title: "",
        content: ""
      },
      ownBlog: false
    };
  },
  methods: {
    edit(blogId) {
      const _this = this;
      _this.$router.push("/blog/" + blogId + "/edit");
    },
    deleteBlog() {
      const _this = this;
      this.$axios.delete("/blog/deleteBlog/" + this.blog.id).then(res => {
        if (res.data.code === 200) {
          _this.$router.push("/blogs");
        }
      });
    }
  },
  created() {
    const _this = this;
    const blogId = this.$route.params.blogId;
    this.$axios.get("/blog/query/" + blogId).then(res => {
      const blog = res.data.data.blog;
      _this.blog.id = blog.id;
      _this.blog.title = blog.title;
      // 使用markdown渲染内容
      var MardownIt = require("markdown-it");
      var md = new MardownIt();
      var result = md.render(blog.content);

      _this.blog.content = result;
      _this.ownBlog = blog.userId === _this.$store.getters.getUser.id;
    });
  }
};
</script>

<style scoped>
.mblog {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-height: 700px;
  padding: 20px 15px;
}
</style>