<template>
  <div v-html="apply">
      {{ apply }}
    </div>
</template>

<script>
export default {
  data() {
    return {
      apply: "",
    }
  },
  mounted() {
    let form = this.$route.query.htmls;
    this.apply = form;
    console.log(this.apply);
    this.$nextTick(() => {
      document.forms[0].submit();
    });
  },
  methods: {

  },
  components: {

  }
}
</script>

<style>

</style>
