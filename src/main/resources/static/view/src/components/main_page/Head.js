/**
 * Created by Mina on 06.05.2019.
 */

import React, {Component} from 'react';
import './styles/head.css'
import Button from '../../../node_modules/@material-ui/core/Button'
import Modal from '../../../node_modules/react-responsive-modal'

class Head extends Component {

    state = {
        login: "",
        signUpLogin: "",
        isOpenModal: false,
        loginLogoutData: {loggedInUser: '', authenticated: false},
        autErr: ""
    };

    handleLogin = (event) => {
        this.setState({login: event.target.value})
    };

    handleLoginSignUp = (event) => {
        this.setState({signUpLogin: event.target.value})
    };

    handleSubmit = () => {

    };

    onOpenModal = () => {
        this.setState({isOpenModal: true})
    };

    onCloseModal = () => {
        this.setState({isOpenModal: false})
    };

    signOut = () => {
        fetch("http://localhost:8080/cinema/rest/signout", {
                method: 'GET',
                credentials: 'include'
            }
        ).then(result => {
            return result.json();
        }).then(data => this.setState({loginLogoutData: data}));
    };

    loginSubmit = () => {
        fetch("http://localhost:8080/cinema/rest/login", {
                method: 'GET',
                credentials: 'include',
                headers: {
                    "X-Requested-With": "XMLHttpRequest",
                    'Authorization': 'Basic ' + btoa(this.state.login + ":1"),
                }
            }
        ).then(result => {
            return this.checkStatus(result, 'User not found!')
        }).then(data => {
            this.setState({loginLogoutData: data})
        });
    };

     checkStatus (result, user) {
        if (result.status === 401) {
            result = JSON.stringify({loggedInUser: user, authenticated: false});
            return JSON.parse(result);
        }
        else {
            return result.json();
        }
    }

    componentDidMount() {
        fetch("http://localhost:8080/cinema/rest/checkauth", {
            credentials: 'include',
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        })
            .then(result => {
              return this.checkStatus(result, '')
            }).then(data => this.setState({loginLogoutData: data}));
    }

    greeting() {
        const isLoggedIn = this.state.loginLogoutData.authenticated;
        const user = this.state.loginLogoutData.loggedInUser;
        if (isLoggedIn) {
            return (
                <div style={{display: 'table'}}>
                    <div style={{display: 'table-cell'}}>
                        <label>You are welcome, {this.state.loginLogoutData.loggedInUser} !</label>
                    </div>
                    <div className="head-sign-in-button">
                        <Button variant="outlined" color="secondary" onClick={this.signOut}>
                            Sing out
                        </Button>
                    </div>
                </div>
            )
        }
        else {
            return (
                <div style={{height: '40px'}}>
                    <div>
                        <div className="head-login-item">
                            <input value={this.state.login} onChange={this.handleLogin} placeholder="Login"
                                   className="input-field"/>
                        </div>
                        <div className="head-sign-in-button">
                            <Button variant="outlined" color="primary" onClick={this.loginSubmit}>
                                Sing in
                            </Button>
                        </div>
                    </div>
                    {user === 'User not found!' && isLoggedIn === false &&
                        <div >
                            <label className="user-not-found-error">User not found!</label>
                        </div>
                    }
                </div>
            )
        }
    }

    render() {
        return (
            <div className="head-block">
                {this.greeting()}
                <div className="head-sign-up-button">
                    <Button variant="outlined" onClick={this.onOpenModal}>Sing up</Button>
                </div>
                <Modal open={this.state.isOpenModal} onClose={this.onCloseModal}
                       showCloseIcon={false} classNames={{modal: 'modal-body'}}>
                    <div>
                        <div className="sign-up-title">
                            <h2>Registration</h2>
                        </div>
                        <div className="modal-input-block">
                            <div >
                                <input value={this.state.signUpLogin} onChange={this.handleLoginSignUp}
                                       className="input-field"/>
                            </div>
                        </div>
                        <div className="modal-button-block">
                            <div className="modal-sign-up-button">
                                <Button variant="contained" color="primary">Sign Up</Button>
                            </div>
                            <div className="modal-cancel-button">
                                <Button variant="contained" color="secondary" onClick={this.onCloseModal}>
                                    Cancel
                                </Button>
                            </div>
                        </div>
                    </div>
                </Modal>
            </div>
        )
    }
}

export default Head;