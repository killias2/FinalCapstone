<template>
    <div class="sidebar">
        <aside>
            <router-link :to="{name: 'tournament'}" tag="button" class="button">Current Tournaments</router-link>
            <button type="button" class="button">Past Tournaments</button>
            <button type="button" class="button">Future Tournaments</button>
            <router-link :to="{name: 'new-tournament'}" tag="button" class="button">Create Tournament</router-link>
        </aside>
    </div>
</template>

<style scoped>

    .button {
        text-align: center;
        width: 150px;
        height: 50px;
        display: block;
        margin-left: 25px;
    }
    .sidebar {
        height: 350px;
        width: 200px;
        position:fixed;
        margin-top: 200px;
        margin-left: 20px;
        z-index: 1;
        top: 0;
        left: 0;
        background-color: rgb(8, 69, 97);
        overflow-x: hidden;
        padding-top: 20px;
        border-radius: 25px;
    }
    aside {
        margin-top: 25px;
        text-decoration: none;
        font-size: 25px;
        color: #818181;
        display: block;
    }
    .button:hover {
        background: blueviolet;
    }

</style>