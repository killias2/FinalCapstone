<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th class="teamName">Team Name</th>
                </tr>
            </thead>
            <tbody v-for="team in teams" v-bind:key="team.id">
                <td>{{team.teamName}}</td>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
  props: {
      tournament: Object,
      teams: Array,
  }
}
</script>

<style>

    table {
        font-family: 'Open Sans', sans-serif;
        width: 300px;
        border-collapse: collapse;
        border: 3px solid #44475C;
        margin: 10px 10px 0 10px;
        background-color: rgba(8, 69, 97, 0.9);
    }

    table th {
        text-transform: uppercase;
        text-align: left;
        background: #44475C;
        color: #FFF;
        cursor: pointer;
        border: 3px solid #44475C;
        padding: 8px;
        min-width: 30px;
    }
    table td {
        text-align: left;
        padding: 8px;
        border: 1px solid #44475C;
        border-right: 2px solid #7D82A8;
    }
    .seed {
        text-align: center;
    }
    .teamName {
        text-align: center;
    }
    </style>