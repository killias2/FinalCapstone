<template>
  <div>
    <home-page />
    <sidebar />
    <bracket />
    <rybracket />
  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'
import rybracket from '../components/RyBracket'
import Bracket from '../components/Bracket'

export default {
  components: {
    HomePage,
    Sidebar,
    rybracket,
    Bracket
  }
};
</script>