import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  changePassword(passwordChange, id) {
    return http.put(`/users/${id}/changepassword`, passwordChange)
  }

}