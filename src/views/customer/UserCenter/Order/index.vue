<template>
  <div class="Order-container">Order：{{ $route.params.type }}</div>
</template>

<script>
  export default {
    name: "Order",
    components: {},
    data() {
      return {};
    },
    created() {
      console.log(
        this.$route.params.type + ":::::::oooooooooooooooooooooooooo"
      );
    },
    mounted() {},
    methods: {},
  };
</script>
