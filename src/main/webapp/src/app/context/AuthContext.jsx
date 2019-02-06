import React, { Component, createContext } from 'react'

import ApiClientService from '../service/ApiClientService';
import UserService from '@/app/service/UserService'
import Messages from '@/components/common/Messages'

const context = createContext()

export const AuthConsumer = context.Consumer;
const AuthProvider = context.Provider

const LOGGED_USER = '_usuarioLogado';
const LOGGED_USER_TOKEN = '_authToken';

class AuthContextProvider extends Component{

    state = {
        user: {},
        authenticated : false,
    }

    constructor(){
        super()
        this.userService = new UserService();
    }
    
    login = (user, token) => {
        localStorage.setItem( LOGGED_USER, JSON.stringify(user) );
        localStorage.setItem( LOGGED_USER_TOKEN, token );

        ApiClientService.setToken(token)
        console.log('login user' ,user)
        this.setState({ ...this.state, authenticated: true, user})
    }

    logout = () => {
        console.log("loginout")
        localStorage.removeItem(LOGGED_USER)
        localStorage.removeItem(LOGGED_USER_TOKEN)
        
        this.setState({ ...this.state, authenticated: false})
    }

    validateSession = async () => {
        const sessionToken = localStorage.getItem( LOGGED_USER_TOKEN )

        console.log('Token recuperado do localstorage ', sessionToken )
      
        if( sessionToken ){            
            console.log('Token existe >>>>> ', sessionToken)

            try{
                const resp = await this.userService.validate( sessionToken )
                console.log('validate response ', resp)
                const { user, token } = resp.data 
                return {
                    user,
                    token
                }
            }catch( err ){
                console.log('deu erro ', err)
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
            this.login( user, token)
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