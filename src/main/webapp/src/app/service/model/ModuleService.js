import ApiClientService from '@/app/service/ApiClientService'

const SERVICE_URL = '/modules'

class ModuleService extends ApiClientService {
    
    constructor(){
        super(SERVICE_URL)
    }

    save = async (module) => {
        return await this.asyncPost('', module)
    }

    find = async(query) => {
        return await super.get(query);
    }

}

export default ModuleService