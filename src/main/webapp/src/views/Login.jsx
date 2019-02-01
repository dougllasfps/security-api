import React from 'react'
import withConsumer from '@/app/decorators/withConsumer'
import {AuthConsumer} from '@/app/context/AuthContext'
import UserService from '@/app/service/UserService'

class Login extends React.Component{

    state = {
        username: '',
        password: ''
    }

    constructor(){
        super()
        this.service = new UserService()
    }

    onSubmit = async (e) => {
       e.preventDefault();
       try{
          const resp = await this.service.auth(this.state)
          console.log(resp)
       }catch(error) {
           console.log(error)
       }
    }

    handleChange = (e) => {
        const name = e.target.name
        const value = e.target.value

        this.setState({...this.state, [name]: value})
    }

    render(){
        return(
            <div className="login-box">
            <div className="login-logo">
                <b>My App</b>
            </div>
            
            <div className="login-box-body">
                <p className="login-box-msg">Entre para inicializar sua sessão</p>

                <form>

                    <div className="form-group has-feedback">
                        <input  onChange={e => this.handleChange(e)}
                                type="text" value={this.state.username}
                                className="form-control" placeholder="Login ou Email" name="username" />
                        <span className="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>

                    <div className="form-group has-feedback">
                        <input  onChange={e => this.handleChange(e)}
                                type="password" value={this.state.password}
                                className="form-control" placeholder="Senha" name="password"/>
                        <span className="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>

                    <div className="row">
                        <div className="col-xs-8">
                            <div>
                                <input type="checkbox" /> Lembrar
                            </div>
                        </div>
                        <div className="col-xs-4">
                            <button onClick={this.onSubmit} type="submit" className="btn btn-primary btn-block btn-flat">Entrar</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
        )
    }
}

export default withConsumer(Login, AuthConsumer)