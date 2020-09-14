import Vue from 'vue'
import Router from 'vue-router'
import Index from "../components/index/Index";
import ChatWindow from "../components/chat/ChatWindow";
import GroupIndex from "../components/group/GroupIndex";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/public/chat-list',
      name: 'ChatWindow',
      component: ChatWindow
    },
    {
      path: '/public/group',
      name: 'GroupIndex',
      component: GroupIndex
    }
  ]
})
