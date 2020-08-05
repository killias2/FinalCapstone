<template>
    <div class="home">
        
        <h1>Welcome to TournaMint!</h1>
        <img alt="Mint Leaves" src="../assets/mintleaf.jpeg">
        <p>You are an authenticated user!</p>
        
    </div>
</template>

<script>


export default {
  name: "home"
}
</script>

<style>
html {
    margin-left: 200px;
}
img {
    display: block;
    margin-left: auto;
    margin-right: auto;
    width: 50%
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