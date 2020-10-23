<template>
    <div class="TopNav-container">
        <top-nav></top-nav>
        <search></search>
        <div class="app-main">
            <transition mode="out-in" name="fade-transform">
                <keep-alive>
                    <router-view name="steps"/>
                </keep-alive>
            </transition>
            <transition mode="out-in" name="fade-transform">
                <keep-alive>
                    <router-view name="left"/>
                </keep-alive>
            </transition>
            <transition mode="out-in" name="fade-transform">
                <keep-alive>
                    <router-view/>
                </keep-alive>
            </transition>
        </div>
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

        created() {
        },
        mounted() {
        },
        methods: {},
    };
</script>
<style>
    .app-main{

    }
    .app-main:after,.app-main:before{
        content: '';
        clear: both;
        display: block;
    }
</style>
