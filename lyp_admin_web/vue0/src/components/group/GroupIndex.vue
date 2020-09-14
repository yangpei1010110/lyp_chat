<template>
  <div>
    <GroupHead body-height="42px"></GroupHead>
    <GroupMain style="padding-top: 12px"></GroupMain>
  </div>
</template>

<script>
    import GroupHead from "./GroupHead";
    import GroupMain from "./GroupMain";

    export default {
        name: "GroupIndex",
        components: {GroupMain, GroupHead}
    }
</script>

<style scoped>

</style>
