<template>
  <div>
    <home-page />
    <user-nav />
    <sidebar />
    <bracket />
    <!-- <add-teams /> -->

  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'
import UserNav from "../components/UserNav"

// import AddTeams from '../components/AddTeams'

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