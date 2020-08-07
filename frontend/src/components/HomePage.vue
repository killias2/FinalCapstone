<template>
    <div class="home">
        <div class="banner">
            <h1>Welcome to TournaMint!</h1>
        </div>
        <div class="body">
            <img alt="Soccer Ball" src="../assets/soccerball.webp">
            <p>You are an authenticated user!</p>
        </div>
    </div>
</template>

<script>


export default {
  name: "home"
}
</script>

<style>
.body {
    margin-left: 200px;

}
.banner {
    margin-left: 0px;
    margin-right: 200px;
}
img {
    display: block;
    margin-left: auto;
    margin-right: auto;
    width: 50%;
    border-radius: 50px;
}
p {
    text-align: center;
    color: red;
    font-weight: bolder;
    text-shadow: 2px;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 50px;
}
    
</style>