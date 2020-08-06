<template>
  <div>
    <home-page />
    <sidebar />
    <add-teams />
    <bracket />
  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'
import bracket from '../components/Bracket'
import AddTeams from '../components/AddTeams'

export default {
  components: {
    HomePage,
    Sidebar,
    bracket,
    AddTeams
  }
};
</script>