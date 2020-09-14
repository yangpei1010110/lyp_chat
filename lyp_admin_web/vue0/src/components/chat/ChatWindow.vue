<template>
  <div>
    <ChatHead :chat-content="backContent" :chat-title="backName">

    </ChatHead>
    <ChatContentWindow>

    </ChatContentWindow>
    <ChatSendWindow>

    </ChatSendWindow>
  </div>
</template>

<script>
    import ChatContentWindow from "./ChatContentWindow";
    import ChatSendWindow from "./ChatSendWindow";
    import ChatHead from "./ChatHead";

    export default {
        name: "ChatWindow",
        components: {ChatHead, ChatSendWindow, ChatContentWindow},
        props: {
            backName: String,
            backContent: String
        }
    }
</script>

<style scoped>

</style>
