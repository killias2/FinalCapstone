<template>
  <div>
    <home-page />
    <user-nav />
    <sidebar />
    <bracket />
  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'
import Bracket from '../components/Bracket'
import UserNav from "../components/UserNav"

export default {
  components: {
    HomePage,
    Sidebar,
    Bracket,
    UserNav
  }
};
</script>