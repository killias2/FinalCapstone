import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  newTournament(tournament) {
    return http.post('/tournaments', tournament);
  },

  viewTournaments() {
    return http.get('/tournaments');
  },

  getAllMatches(tournamentId) {
    return http.get(`/tournaments/${tournamentId}/matches`);
  },
  
  getAllTeams(tournamentId){
    return http.get(`/tournaments/${tournamentId}/teams`);
  },

  getTournament(tournamentID) {
    return http.get(`/tournaments/${tournamentID}`, tournamentID);
  },

  getAllGames() {
    return http.get('/games');
  }, 

  getUsersTournaments(organizerID) {
    return http.get(`/tournament/my-tournaments/${organizerID}`);
  },

  getCurrentTournaments() {
    return http.get('/tournaments/current');
  },

  getFutureTournaments() {
    return http.get('/tournaments/upcoming');
  },
  
  getPastTournaments() {
    return http.get('/tournaments/past');
  },

  getMatchFromId(matchId){
    return http.get(`/matches/${matchId}/teams`);
  },
  generateBrackets(tournament) {
    return http.put('/tournaments', tournament);
  },
  completeMatch(match){
    return http.put(`/matches/${match.matchid}/complete`, match);
  }

}