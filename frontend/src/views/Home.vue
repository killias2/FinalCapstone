<template>
  <div>
    <home-page />
    <sidebar />
    <create-tournament />
    <bracket />
  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'
import CreateTournament from '../components/CreateTournament'
import bracket from '../components/Bracket';

export default {
  components: {
    HomePage,
    Sidebar,
    bracket,
    CreateTournament
  }
};
</script>