<template>
  <div class="TopNav-container">
    <top-nav></top-nav>
    <search></search>
    <transition mode="out-in" name="fade-transform">
      <keep-alive>
        <router-view name="left" />
      </keep-alive>
    </transition>
    <transition mode="out-in" name="fade-transform">
      <keep-alive>
        <router-view />
      </keep-alive>
    </transition>
    <bottom></bottom>
  </div>
</template>
<script>
  import Search from "./Search";
  import TopNav from "./TopNav";
  import Bottom from "./Bottom";

  export default {
    name: "NavSearch",
    components: {
      Search,
      TopNav,
      Bottom
    },
    data() {
      return {
        // nodeEnv: process.env.NODE_ENV,
      };
    },
    computed: {},

    created() {},
    mounted() {},
    methods: {},
  };
</script>
<style></style>
