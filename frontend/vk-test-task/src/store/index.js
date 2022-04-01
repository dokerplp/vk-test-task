import { createStore } from 'vuex'

async function postData (url, data) {
  return await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    body: JSON.stringify(data)
  })
}

export default createStore({
  state: {
    isUserAuth: false,
    userLogin: String,
    userPassword: String
  },
  getters: {
    AUTH: state => {
      return state.isUserAuth
    },
    LOGIN: state => {
      return state.userLogin
    },
    PASS: state => {
      return state.userPassword
    }
  },
  mutations: {
    SET_AUTH: (state, payload) => {
      state.isUserAuth = payload
    },
    SET_LOGIN: (state, payload) => {
      state.userLogin = payload
    },
    SET_PASSWORD: (state, payload) => {
      state.userPassword = payload
    }
  },
  actions: {
    SIGN_IN: (context, name) => {
      postData('/api/sign-in', name)
        .then(response => response.json())
        .then(data => {
          if (data) {
            context.commit('SET_LOGIN', name.login)
            context.commit('SET_PASSWORD', name.pass)
            context.commit('SET_AUTH', true)
          }
          console.log(data)
        })
    },
    SIGN_UP: (context, name) => {
      postData('/api/sign-up', name)
        .then(response => response.json())
        .then(data => {
          if (data) {
            context.commit('SET_LOGIN', name.login)
            context.commit('SET_PASSWORD', name.pass)
            context.commit('SET_AUTH', true)
          }
          console.log(data)
        })
    }
  },
  modules: {
  }
})
