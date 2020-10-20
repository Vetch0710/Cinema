<template>
    <div>
        <side-bar/>
        <nav-bar/>
    </div>
</template>

<script>
    import SideBar from "@/components/managerlayouts/components/SideBar";
    import NavBar from "@/components/managerlayouts/components/NavBar";
    export default {
        name: "index",
        components: {
            SideBar,
            NavBar,
            // ErrorLog,
            // FullScreenBar,
            // ThemeBar,
        },
    }
</script>

<style scoped>

</style>