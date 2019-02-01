import axios from './axios'

export default class ApiClientService{

    constructor(apiUrl){
        this.apiUrl = apiUrl;
    }

    get = async (query) => {
        return await axios.get(`${this.apiUrl}${query}`)
    }

    post = async (url, params) => {
        return await axios.post(`${this.apiUrl}${url}`, params)
    }

    put = async (object) => {
        const {id} = object
        return await axios.put(`${this.apiUrl}/${id}`, object)
    }

    delete = async (id) => {
        return await axios.delete(`${this.apiUrl}/${id}`)
    }

    static setToken( token ){
        axios.default.headers.commom['Authorization'] = `Bearer ${token}`
    }
}