<template>
    <div>
        
        <sidebar />
        <tournament />
        <user-nav />
    </div>
</template>

<script>
import Tournament from "../components/Tournaments"
import Sidebar from '../components/Sidebar'
import UserNav from '../components/UserNav'

export default {
    components: {
        Tournament,
        Sidebar,
        UserNav
    }
};
</script>