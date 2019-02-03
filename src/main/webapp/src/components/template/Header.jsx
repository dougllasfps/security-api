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
                            <li className="dropdown messages-menu">
                                <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                                    <i className="fa fa-envelope-o"></i>
                                    <span className="label label-success">4</span>
                                </a>
                            <ul className="dropdown-menu">
                            <li className="header">You have 4 messages</li>
                            <li>
                                <ul className="menu">
                                <li>
                                    <a href="#">
                                    <div className="pull-left">
                                        <img src={userLogo} className="img-circle" alt="User Image" />
                                    </div>
                                    <h4>
                                        Support Team
                                        <small><i className="fa fa-clock-o"></i> 5 mins</small>
                                        </h4>
                                    <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                </ul>
                            </li>
                                <li className="footer"><a href="#">See All Messages</a></li>
                            </ul>
                    </li>

                    <li className="dropdown notifications-menu">
                        <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                        <i className="fa fa-bell-o"></i>
                        <span className="label label-warning">10</span>
                        </a>
                        <ul className="dropdown-menu">
                        <li className="header">You have 10 notifications</li>
                        <li>
                            <ul className="menu">
                            <li>
                                <a href="#">
                                <i className="fa fa-users text-aqua"></i> 5 new members joined today
                                </a>
                            </li>
                            </ul>
                        </li>
                        <li className="footer"><a href="#">View all</a></li>
                        </ul>
                    </li>
                    <li className="dropdown tasks-menu">
                        <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                        <i className="fa fa-flag-o"></i>
                        <span className="label label-danger">9</span>
                        </a>
                        <ul className="dropdown-menu">
                        <li className="header">You have 9 tasks</li>
                        <li>
                            <ul className="menu">
                            <li>
                                <a href="#">
                                <h3>
                                    Design some buttons
                                    <small className="pull-right">20%</small>
                                    </h3>
                                <div className="progress xs">
                                    <div className="progress-bar progress-bar-aqua" style={{width: '20%'}} role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                    <span className="sr-only">20% Complete</span>
                                    </div>
                                </div>
                                </a>    
                            </li>
                            </ul>
                        </li>
                        <li className="footer">
                            <a href="#">View all tasks</a>
                        </li>
                        </ul>
                    </li>
                    <li className="dropdown user user-menu">
                        <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                        <img src={userLogo} className="user-image" alt="User Image" />
                        <span className="hidden-xs">{ props.loggedUser ? props.loggedUser.name : 'Usuário desconhecido' }</span>
                        </a>
                        <ul className="dropdown-menu">
                        <li className="user-header">
                            <img src={userLogo} alt="User Image" />
                            <p>
                                { props.loggedUser ? props.loggedUser.name : 'Usuário desconhecido' }
                                <small>Member since Nov. 2012</small>
                            </p>
                        </li>
                        <li className="user-body">
                            <div className="row">
                            <div className="col-xs-4 text-center">
                                <a href="#">Followers</a>
                            </div>
                            <div className="col-xs-4 text-center">
                                <a href="#">Sales</a>
                            </div>
                            <div className="col-xs-4 text-center">
                                <a href="#">Friends</a>
                            </div>
                            </div>
                        </li>
                        <li className="user-footer">
                            <div className="pull-left">
                            <a href="#" className="btn btn-default btn-flat">Profile</a>
                            </div>
                            <div className="pull-right">
                            <a href="#" onClick={e => props.logout()} className="btn btn-default btn-flat">Sign out</a>
                            </div>
                        </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i className="fa fa-gears"></i></a>
                    </li>
                    </ul>
                </div>
            </nav>
            </header>
        )
}

export default withConsumer(Header, AuthConsumer)