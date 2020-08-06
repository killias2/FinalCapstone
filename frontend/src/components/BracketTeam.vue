<template>
    <div>

        <v-container>
            <v-layout>
        <v-card hover>
          <v-card-title>
            <h3>{{teamname}}</h3>
          </v-card-title>
        </v-card>
  
            </v-layout>
        </v-container>

    </div>
</template>

<script>
export default {
    name: 'BracketTeam',
    data() {
        return {
            teamname: 'bound team name goes here'
        }
    }
    
}
</script>

<style>

</style>
