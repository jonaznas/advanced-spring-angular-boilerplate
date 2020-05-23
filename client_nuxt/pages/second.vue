<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-6">
        <h4 class="text-right">second</h4>
      </div>
      <div class="col-lg-6">
        <h4>page</h4>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-2">
        <button v-on:click="sendToHome" class="btn btn-primary">Back to home</button>
      </div>
      <div class="col-lg-2">
        <button v-on:click="test" class="btn btn-primary">Test</button>
      </div>
      <div class="col-lg-3">
        <label>
          {{ testinput }}
          <input type="text" class="form-control" v-model="testinput" />
        </label>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    middleware: 'guard/authenticated',
    data() {
      return {
        testinput: null
      }
    },
    methods:  {
      sendToHome() {
        this.$router.push("/")
      },
      test() {
        console.log(this.testinput)
      }
    },
    created() {
      this.testinput = "Welcome"
    },
    destroyed() {
      console.log("Bye")
    }
  }
</script>
