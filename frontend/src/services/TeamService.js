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
  getWinnerList() {
    return http.get('/teams/winners');
  },
  getTeamsByGmId(generalManagerId) {
    return http.get(`/teams/${generalManagerId}`);
  }


}