<template>
  <div>
    <home-page />
    <user-nav />
    <sidebar />

  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'
import UserNav from "../components/UserNav"

export default {
  components: {
    HomePage,
    Sidebar,
    UserNav
    // ,
    // AddTeams
  }
};
</script>