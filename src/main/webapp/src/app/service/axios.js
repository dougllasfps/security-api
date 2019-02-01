const axios = require('axios')

const baseUrl = process.env.REACT_APP_BASE_API_URL

module.exports = axios.create({baseURL:baseUrl})