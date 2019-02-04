import ApiClientService from './ApiClientService'

const SERVICE_URL = '/users'

export default class UserService extends ApiClientService {

    constructor(){
        super(SERVICE_URL)
    }

    auth = (credentials) => {
        const query = `/auth?username=${credentials.username}&password=${credentials.password}`
        return this.post( query )
    }

    validate = async (token) => {
        return await this.asyncPost(`/validateToken?token=${token}`)
    }
}