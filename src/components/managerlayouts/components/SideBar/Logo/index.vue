<template>
    <div class="logo-container" >
        <router-link to="/">
            <!-- 这里是logo变更的位置 -->
            <el-image
                    style="width: 180px; height: 90px;margin-left: 20px"
                    :src="require('@/assets/logo2.png')"
                    :fit="'contain'"
            > <div slot="placeholder" class="image-slot"></div></el-image>
        </router-link>
    </div>
</template>
<script>
    export default {
        name: "Logo",
        data() {
            return {
                // title: this.$baseTitle,
            };
        },
        computed: {},
    };
</script>
<style></style>

