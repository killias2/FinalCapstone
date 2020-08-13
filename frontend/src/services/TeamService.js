import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  viewTeams(id) {
    return http.get(`/tournaments/${id}/teams`);
  },

  addTeams(team) {
    return http.post('/teams', team);
  },

  removeTeams(team) {
      return http.delete(`/teams/${team.teamId}`);
  },

  getTeamsByGmId(generalManagerId) {
    return http.get(`/teams/${generalManagerId}`);
  }


}