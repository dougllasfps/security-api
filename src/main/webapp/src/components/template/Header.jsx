import React from 'react'
import userLogo from '@/dependencies/images/user-silluete.png'
import withConsumer from '@/app/decorators/withConsumer'
import { AuthConsumer } from '@/app/context/AuthContext'


const Header = props => {
    return (
            <header className="main-header">
                <a href="/" className="logo">
                    <span className="logo-mini">{props.appMini || 'App'}</span>
                    <span className="logo-lg">
                        <i className={`fa fa-${props.logo ||'github-alt'}`}></i><b>  {props.appTitle || 'AppTitle'}</b>
                    </span>
                </a>
                <nav className="navbar navbar-static-top">
                    <a href className="sidebar-toggle" data-toggle="offcanvas"></a>
                    <div className="navbar-custom-menu">
                        <ul className="nav navbar-nav">
                            <li className="dropdown user user-menu">
                                <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                                <img src={userLogo} className="user-image" alt="User Image" />
                                <span className="hidden-xs">{ props.loggedUser ? props.loggedUser.name : 'Usuário desconhecido' }</span>
                                </a>
                                <ul className="dropdown-menu">
                                    <li className="user-header">
                                        <img src={userLogo} alt="U
                                        ser Image" />
                                        <p>
                                            { props.loggedUser ? props.loggedUser.name : 'Usuário desconhecido' }
                                            <small>{props.loggedUser.email}</small>
                                        </p>
                                    </li>
                    
                                    <li className="user-footer">
                                        <div className="pull-left">
                                        <a href="#" className="btn btn-default btn-flat">Perfil</a>
                                        </div>
                                        <div className="pull-right">
                                        <a href="#" onClick={e => props.logout()} className="btn btn-default btn-flat">Sair</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                    
                        </ul>
                </div>
            </nav>
        </header>
    )
}

export default withConsumer(Header, AuthConsumer)