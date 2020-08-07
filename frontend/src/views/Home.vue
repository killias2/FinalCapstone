<template>
  <div>
    <home-page />
    <sidebar />
    <bracket />
  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'
import bracket from '../components/Bracket';

export default {
  components: {
    HomePage,
    Sidebar,
    bracket
  }
};
</script>