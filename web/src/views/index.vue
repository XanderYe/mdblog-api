<template>
  <mu-container>
    <mu-card class="mu-shadow mu-hoverable" v-for="article in articleList" :key="article.id" style="margin-bottom: 16px">
      <div class=" mu-card-title-container mu-typo">
        <div class="mu-card-title">
          <router-link :to="{name: 'article-detail', query: {id: article.id}}">{{article.title}}</router-link>
        </div>
        <div class="mu-card-sub-title">
          {{article.createTime}}
        </div>
      </div>
      <mu-card-text class="mu-typo" v-html="article.content" v-highlight>
      </mu-card-text>
      <mu-card-actions>
        <mu-button flat :to="{name: 'article-detail', query: {id: article.id}}">继续阅读</mu-button>
      </mu-card-actions>
    </mu-card>

    <mu-flex justify-content="end" style="padding-bottom: 16px;" v-if="articleList.length > 0">
      <mu-pagination raised circle :page-size="pageSize" :total="total" :current.sync="page"
                     @change="changePage"></mu-pagination>
    </mu-flex>
  </mu-container>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        topicId: null,
        articleList: [],
        page: 1,
        total: 1,
        pageSize: 5,
      }
    },
    methods: {
      changePage() {
        this.getArticleList();
        // 滚动到顶部
        document.getElementById("app").scrollTop = 0;
      },
      getArticleList() {
        this.$requests.get("/article/getRecentArticles", {
          topicId: this.topicId,
          page: this.page,
          rows: this.pageSize
        }).then((res) => {
          if (res.data.code === 0) {
            this.articleList = res.data.data.list;
            this.total = res.data.data.total;
          }
        })
      },
      reload() {
        document.getElementById("app").scrollTop = 0;
        this.topicId = this.$route.query.id;
        this.page = 1;
        this.getArticleList();
      }
    },
    created() {
      this.reload();
    },
    activated() {
      if(this.topicId !== this.$route.query.id) {
        this.reload();
      } else {
        // 恢复滚动条位置
        document.getElementById("app").scrollTop = parseInt(sessionStorage.getItem("scroll"));
      }
    },
    watch: {
      '$route': function (to, from) {
        if (to.path === from.path) {
          // 切换左侧导航栏时刷新数据
          this.reload();
        }
      }
    }
  }
</script>

<style scoped>
  .md-card {
    margin-bottom: 10px;
  }
</style>
