<template>
    <div>
        <sidebar />
        <tournament />
    </div>
</template>

<script>
import Tournament from "../components/Tournaments"
import Sidebar from '../components/Sidebar'

export default {
    components: {
        Tournament,
        Sidebar
    }
};
</script>