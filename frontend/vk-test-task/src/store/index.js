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
      alert(payload)
      state.userLogin = payload
    },
    SET_PASSWORD: (state, payload) => {
      this.state.userPassword = payload
    }
  },
  actions: {
  },
  modules: {
  }
})
