import React, { createContext, Component } from 'react'
import ModuleService from '@/app/service/model/ModuleService'

const Context = createContext();

class Modules extends Component {

    state = {
        entity: {},
        list: []
    }

    saveOrUpdate = async () => {
        
    }

    find = async () => {

    }
 
    render(){
        const ctx = {
            entity: this.state.entity,
            list: this.state.list,
            saveOrUpdate = this.saveOrUpdate,
            find = this.find,
        }

        return (
            <Context.Provider value={ctx}>
                { this.props.children }
            </Context.Provider>
        )
    }
}