export default {
  mode:    'spa',
  head:    {
    title:     process.env.npm_package_name || '',
    bodyAttrs: {
      class: ''
    },
    meta:      [
      {charset: 'utf-8'},
      {name: 'viewport', content: 'width=device-width, initial-scale=1'},
      {hid: 'description', name: 'description', content: process.env.npm_package_description || ''}
    ],
    link:      [
      {rel: 'icon', type: 'image/x-icon', href: '/favicon.ico'}
    ]
  },
  env:     {
    wsHost: process.env.wsHost || 'http://localhost:8080' // SocketIO host
  },
  loading: {color: '#fff'},
  css:     [
    '~/css/style.scss'
  ],
  modules: [
    'bootstrap-vue/nuxt',
  ],
  plugins: [
    '~/plugins/startup'
  ]
}
