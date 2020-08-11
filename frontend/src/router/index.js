import Vue from 'vue'
import Router from 'vue-router'

import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import CreateTournament from '../views/CreateTournament'
import Tournament from '../views/Tournaments'
import MyTournament from '../views/MyTournament'
import TournamentDetails from '../views/TournamentDetails.vue'
import TournamentHQ from '../views/TournamentHQ'
import UserProfile from '../views/UserProfile.vue'
import ChangePassword from '../views/ChangePassword.vue'
import CurrentTournaments from '../views/CurrentTournaments.vue'
import PastTournaments from '../views/PastTournaments.vue'
import UpcomingTournaments from '../views/UpcomingTournaments.vue'



Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/new-tournament",
      name: "new-tournament",
      component: CreateTournament,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/tournaments",
      name: "tournament",
      component: Tournament
    },
    {
      path: "/tournaments/current",
      name: "tournaments-current",
      component: CurrentTournaments
    },
    {
      path: "/tournaments/past",
      name: "tournaments-past",
      component: PastTournaments
    },
    {
      path: "/tournaments/upcoming",
      name: "tournaments-upcoming",
      component: UpcomingTournaments
    },
    {
      path: '/tournaments/:id',
      name: 'tournament-details',
      component: TournamentDetails
    },
    {
      path: "/tournaments/:id",
      name: "my-tournament",
      component: MyTournament
    },
    {
      path: "/tournamentHQ/:id",
      name: 'tournamenthq',
      component: TournamentHQ
    },
    {
      path: "/users/:id",
      name: "user-profile",
      component: UserProfile
    },
    {
      path: "/users/:id/change-password",
      name: "password-change",
      component: ChangePassword
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
