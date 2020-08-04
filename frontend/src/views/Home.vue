<template>
  <div>
    <home-page />
    <sidebar />
    <create-tournament />
  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'
import CreateTournament from '../components/CreateTournament'

export default {
  components: {
    HomePage,
    Sidebar,
    CreateTournament
  }
};
</script>