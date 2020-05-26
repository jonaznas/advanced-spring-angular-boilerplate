<template>
    <div class="container">
        <h3>Index page</h3>
        <div class="row">
            <div class="col-lg-3">
                <label>
                    Username
                    <input type="text" v-model="username" class="form-control"/>
                </label>
            </div>
            <div class="col-lg-3">
                <label>
                    Password
                    <input type="password" v-model="password" class="form-control"/>
                </label>
            </div>
            <div class="col-lg-2">
                <button class="btn btn-primary" v-on:click="doLogin">Log in</button>
            </div>
        </div>
    </div>
</template>

<script>
  import * as socket from '~/plugins/socket.io'

  export default {
    data:       () => ({
      username: null,
      password: null
    }),
    components: {
      Logo
    },
    methods:    {
      doLogin() {
        console.log('----------')
        console.log(`Username ${this.username}`)
        console.log(`Password ${this.password}`)
        this.$store.commit("authentication/set", {token: "abc"})
        console.log(`Authenticated: ${this.$store.state.authentication.authenticated}`)
        console.log(`Token: ${this.$store.state.authentication.token}`)
        console.log('----------')
        socket.request(this.$store, 'test', {})
      }
    }
  }
</script>
