<template>
  <div>
    <home-page />
    <sidebar />
    <add-teams />

  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'

import AddTeams from '../components/AddTeams'

export default {
  components: {
    HomePage,
    Sidebar,

    AddTeams
  }
};
</script>