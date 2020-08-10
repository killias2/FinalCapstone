<template>
    <div class="sidebar">
        <aside>
            <router-link :to="{name: 'tournaments-current'}" tag="button" class="button">Upcoming Tournaments</router-link>
            <router-link :to="{name: 'tournaments-past'}" tag="button" class="button">Past Tournaments</router-link>
            <router-link :to="{name: 'new-tournament'}" tag="button" class="button">Create Tournament</router-link>
        </aside>
    </div>
</template>

<style scoped>

    .button {
        text-align: center;
        width: 150px;
        height: 100px;
        display: block;
        margin: 20px;
    }
    .sidebar {
        height: 400px;
        width: 200px;
        /* position:fixed; */
        /* margin-top: 200px; */
        margin-top: 10px;
        /* margin-left: 20px; */
        z-index: 1;
        /* top: 0;
        left: 0; */
        background-color: rgb(8, 69, 97);
        overflow-x: hidden;
        padding-top: 10px;
        border-radius: 25px;
    }
    aside {
        text-decoration: none;
        font-size: 18px;
        color: #818181;
        display: block;
    }
    .button:hover {
        background: blueviolet;
    }

</style>