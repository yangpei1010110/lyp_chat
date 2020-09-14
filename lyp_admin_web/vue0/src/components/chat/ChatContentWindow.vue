<template>
  <div>
    <div v-for="ChatRowData of ChatRowDataList">
      <ChatRow>

      </ChatRow>
    </div>
  </div>
</template>

<script>
    import ChatRow from "./ChatRow";

    export default {
        name: "ChatContentWindow",
        components: {ChatRow},
        data() {
            return {
                ChatRowDataList: []
            }
        }
    }
</script>

<style scoped>

</style>
