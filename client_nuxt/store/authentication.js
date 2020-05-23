export default {
  state:     () => ({
    authenticated: false,
    token:         null
  }),
  mutations: {
    set(state, {token}) {
      state.authenticated = true
      state.token         = token
    },
    delete(state) {
      state.authenticated = false
      state.token         = null
    }
  }
}
