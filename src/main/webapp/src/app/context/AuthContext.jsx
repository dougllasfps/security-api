import React, { Component, createContext } from 'react'

import ApiClientService from '../service/ApiClientService';
import UserService from '@/app/service/UserService'
import Messages from '@/components/common/Messages'

const context = createContext()

export const AuthConsumer = context.Consumer;
const AuthProvider = context.Provider

const LOGGED_USER = '_logged_user';
const LOGGED_USER_TOKEN = '_logged_user_btoken';

class AuthContextProvider extends Component{

    state = {
        user: {},
        authenticated : false,
    }

    constructor(){
        super()
        this.userService = new UserService()
    }
    
    login = (user, token) => {
        localStorage.setItem( LOGGED_USER, JSON.stringify(user) );
        localStorage.setItem( LOGGED_USER_TOKEN, token );
        ApiClientService.setToken(token)
        console.log(user)
        this.setState({ ...this.state, authenticated: true, user})
    }

    logout = () => {
        localStorage.removeItem(LOGGED_USER)
        localStorage.removeItem(LOGGED_USER_TOKEN)
        
        this.setState({ ...this.state, authenticated: false})
    }

    validateSession = async () => {
        const sessionToken = localStorage.getItem( LOGGED_USER_TOKEN ) || null

        console.log(` validando sessao com o token `, sessionToken)
        
        if( sessionToken ){
            try{
                const resp = await this.userService.validate( sessionToken )
                const {user, token} = resp.data 

                console.log(` validação: `, JSON.stringify(user))
                return {
                    user,
                    token
                }
            }catch(err){
                Messages.error('erro '+ err )
                return {}
            }
        }

        return {}
    }

    async componentDidMount(){
       const { token, user } = await this.validateSession()     
       
       console.log('token validado: ' , token)

       if(!token){
            this.logout()
       }else{
            this.login( token, user )
       }
    }
        
    render(){
        return(
            <AuthProvider value={{
                login: this.login,
                logout: this.logout,
                authenticated: this.state.authenticated,
                loggedUser: this.state.user
            }}>
                {this.props.children}
            </AuthProvider>
        )
    }
}

export default AuthContextProvider