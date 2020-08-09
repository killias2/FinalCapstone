<template>
  <div>
    <home-page />
    <user-nav />
    <sidebar />
    <add-teams />

  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'

import AddTeams from '../components/AddTeams'
import UserNav from "../components/UserNav"

export default {
  components: {
    HomePage,
    Sidebar,
    AddTeams,
    UserNav
  }
};
</script>