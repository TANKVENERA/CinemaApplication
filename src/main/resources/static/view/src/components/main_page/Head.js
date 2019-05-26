/**
 * Created by Mina on 06.05.2019.
 */

import React, {Component} from 'react';
import './styles/head.css'
import Button from '../../../node_modules/@material-ui/core/Button'
import DeleteIcon from '../../../node_modules/@material-ui/icons/Delete'
import Fab from '../../../node_modules/@material-ui/core/Fab'
import Modal from '../../../node_modules/react-responsive-modal'


class Head extends Component {
    constructor(props){
        super(props);
        this.state = {
            login: "",
            signUpLogin: "",
            isOpenModal: false,
            isOpenOrdersModal: false,
            loggedInUser: {login: ''},
            authErr: "",
            userOrdersData: []
        }
    }


    handleLogin = (event) => {
        this.setState({login: event.target.value})
    };

    handleLoginSignUp = (event) => {
        this.setState({signUpLogin: event.target.value})
    };

    handleSubmitUser = () => {
        fetch(`http://localhost:8080/cinema/rest/register/?login=${this.state.signUpLogin}`, {
        }).then(result => { return result.text()})
            .then(data => {const flag = !data.includes('successfully');
                           this.setState({isOpenModal: flag});
                           this.props.warn(data, flag ? 'red' : 'blue')
            })
    };

    onOpenModal = () => {
        this.setState({isOpenModal: true})
    };

    handleOrders = (user) => {
        fetch(`http://localhost:8080/cinema/rest/listOrders/?login=${user}`, {
            method: "GET",
            credentials: 'include',
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        }).then(result => {
            return result.json();
        }).then(data => this.setState({userOrdersData: data, isOpenOrdersModal: true}));
    };

    onCloseModal = () => {
        this.setState({isOpenModal: false})
    };

    onCloseOrdersModal = () => {
        this.setState({isOpenOrdersModal: false})
    };

    handleDeleteOrder = (title, date, seat, login) => {
        fetch(`http://localhost:8080/cinema/rest/delete/?title=${title}&date=${date}&seat=${seat}&login=${login}`, {
            method: "GET",
            credentials: 'include',
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        }).then(result => {
            return result.json();
        }).then(data => {this.setState({userOrdersData: data});
                         this.props.warn('Order was successfully removed!', 'green')});
    };

    signOut = () => {
        fetch("http://localhost:8080/cinema/rest/signout", {
                method: 'GET',
                credentials: 'include'
            }
        ).then(result => {
            return result.json();
        }).then(data => this.setState({loggedInUser: data}));
    };

    handleLoginUser = () => {
        fetch("http://localhost:8080/cinema/rest/login", {
                method: 'GET',
                credentials: 'include',
                headers: {
                    "X-Requested-With": "XMLHttpRequest",
                    'Authorization': 'Basic ' + btoa(this.state.login + ":1"),
                }
            }
        ).then(result => {
            return result.json()
        }).then(data =>
            this.setState({loggedInUser: data, authErr: data.login === '' ? 'User not found!' : ''})
        );
    };

    componentDidMount() {

        fetch("http://localhost:8080/cinema/rest/checkauth", {
            credentials: 'include',
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        })
            .then(result => {
              return result.json()
            }).then(data =>{
                this.setState({loggedInUser: data, authErr: ''})});
    }

    greeting() {
        const user = this.state.loggedInUser.login;
        if (user !== '') {
            return (
                <div style={{display: 'table'}}>
                    <div className="user-sign-in">
                        <label>You are welcome, {this.state.loggedInUser.login} !</label>
                    </div>
                    <div className="head-sign-in-button">
                        <Button variant="outlined" color="secondary" onClick={this.signOut}>
                            Sing out
                        </Button>
                    </div>
                    <div className="head-sign-in-button">
                        <Button variant="outlined" color="primary" onClick={() => this.handleOrders(user)}>
                            My Orders
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
                            <Button variant="outlined" color="primary" onClick={this.handleLoginUser}>
                                Sing in
                            </Button>
                        </div>
                    </div>
                    <div >
                        <label className="user-not-found-error">{this.state.authErr}</label>
                    </div>
                </div>
            )
        }
    }

    printUserOrdersData(userOrders) {
        return (userOrders.map((order, index) => (
            <div key={index} style={{display: 'table', paddingBottom: '10px'}}>
                <div style={{display: 'table-cell', width: '350px'}}>
                    <label>film: {order.title}, date: {order.filmDate}, seat: {order.seat}</label>
                </div>
                <div style={{display: 'table-cell'}}>
                    <Fab aria-label="Delete" className="delete-icon" >
                        <DeleteIcon fontSize="small"
                                    onClick={() => this.handleDeleteOrder(order.title, order.filmDate, order.seat,
                                        this.state.loggedInUser.login)}/>
                    </Fab>
                </div>
            </div>)
        ))
    }

    render() {
        return (
            <div className="head-block">
                {this.greeting()}
                {this.state.loggedInUser.login === '' &&
                    <div className="head-sign-up-button">
                        <Button variant="outlined" onClick={this.onOpenModal}>Sign up</Button>
                    </div>
                }
                <Modal open={this.state.isOpenModal} onClose={this.onCloseModal}
                       showCloseIcon={false} classNames={{modal: 'modal-sign-up-body'}}>
                    <div>
                        <div className="sign-up-title">
                            <h2>Registration</h2>
                        </div>
                        <div className="modal-input-block">
                            <input value={this.state.signUpLogin} onChange={this.handleLoginSignUp}
                                   className="input-field"/>
                        </div>
                        <div className="modal-button-block">
                            <div className="modal-sign-up-button">
                                <Button variant="contained" color="primary" onClick={this.handleSubmitUser}>Sign Up</Button>
                            </div>
                            <div className="modal-cancel-button">
                                <Button variant="contained" color="secondary" onClick={this.onCloseModal}>
                                    Cancel
                                </Button>
                            </div>
                        </div>
                    </div>
                </Modal>
                <Modal open={this.state.isOpenOrdersModal} onClose={this.onCloseOrdersModal}
                       showCloseIcon={false} classNames={{modal: 'modal-orders-body'}}>
                    <div>
                        <div>
                            {this.printUserOrdersData(this.state.userOrdersData)}
                        </div>
                        <div style={{paddingLeft: '40%'}}>
                            <Button variant="outlined" color="secondary" onClick={this.onCloseOrdersModal}>
                                Exit
                            </Button>
                        </div>

                    </div>
                </Modal>
            </div>
        )
    }
}

export default Head;