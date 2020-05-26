import Vue from 'vue'

export function testFilter(value) {
  return `${value} is filtered`
}

const filters = {testFilter}

Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

export default filters
