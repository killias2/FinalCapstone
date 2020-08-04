<template>
  <div>
    <home-page />
    <sidebar />
  </div>
</template>

<script>
import HomePage from '../components/HomePage'
import Sidebar from '../components/Sidebar'

export default {
  components: {
    HomePage,
    Sidebar
  }
};
</script>
