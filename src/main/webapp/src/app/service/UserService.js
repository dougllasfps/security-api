import ApiClientService from './ApiClientService'

const SERVICE_URL = '/users'

export default class UserService extends ApiClientService{
    constructor(){
        super(SERVICE_URL)
    }

    auth = async (credentials) => {
        const query = `/auth?username=${credentials.username}&password=${credentials.password}`
        return await this.post(query )
    }
}