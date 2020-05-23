import io from 'socket.io-client'

export const socket = io(process.env.wsHost)

export const request = (store, event, data) => new Promise(resolve => {
  socket.emit(event, {
    token: store.state.authentication.token
  }, (callback) => resolve)
})
