<template>
  <div>
    <Navbar />
    <nuxt />
  </div>
</template>

<script>
  import Navbar from '~/components/shared/Navbar.vue'

  export default {
    components: {
      Navbar
    }
  }
</script>
