/**
 * Created by Mina on 06.05.2019.
 */

import React, {Component} from 'react';
import './styles/head.css'
import Button from '../../../node_modules/@material-ui/core/Button'
import DeleteIcon from '../../../node_modules/@material-ui/icons/Delete'
import EditIcon from '../../../node_modules/@material-ui/icons/Edit'
import Fab from '../../../node_modules/@material-ui/core/Fab'
import Modal from '../../../node_modules/react-responsive-modal'
import Hall from './Hall'


class Head extends Component {
    constructor(props){
        super(props);
        this.state = {
            login: "",
            signUpLogin: "",
            isOpenModal: false,
            isOpenOrdersModal: false,
            isOpenUpdateOrdersModal: false,
            loggedInUser: {login: ''},
            authErr: "",
            userOrdersData: [],
            bookedSeats: [],
            seatsToUpdate: [],
            title: '',
            filmdate: '',
            ticketID: ''
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
        fetch(`http://localhost:8080/cinema/rest/listorders/?login=${user}`, {
            method: "GET",
            credentials: 'include',
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        }).then(result => {
            return result.json();
        }).then(data =>    {
                this.setState({userOrdersData: data, isOpenOrdersModal: true})
        });
    };

    onCloseModal = () => {
        this.setState({isOpenModal: false})
    };

    onCloseOrdersModal = () => {
        this.setState({isOpenOrdersModal: false})
    };

    onCloseUpdateOrdersModal = () => {
        this.setState({isOpenUpdateOrdersModal: false})
    };

    handleDeleteOrder = (ticket, login) => {
        fetch(`http://localhost:8080/cinema/rest/delete/?ticket=${ticket}&login=${login}`, {
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

    handleUpdateOrder = (title, date, seats, ticketID) => {
        this.setState({title: title, filmdate: date, ticketID: ticketID})
        fetch(`http://localhost:8080/cinema/rest/dates/?film=${title}&date=${date}`, {
            method: "GET",
            credentials: 'include',
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        }).then(result => {return result.json();
        }).then(data => {
            console.log('PULIA',data);
            var filtered = data.tickets.filter((ticket) =>{
                if (!seats.includes(ticket.seatnumber)) {
                    return ticket
                }
                    return ''
            });
            this.setState({bookedSeats: filtered})
        });
        this.setState({isOpenUpdateOrdersModal: true, seatsToUpdate: seats})
    };

    signOut = () => {
        fetch("http://localhost:8080/cinema/rest/signout", {
                method: 'GET',
                credentials: 'include'
            }
        ).then(result => {
            return result.json();
        }).then(data => this.setState({loggedInUser: data}));

        /** refresh browser **/
        window.location.reload();
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
        }).then(result => {
                return result.json()
            }).then(data => {
            this.setState({loggedInUser: data, authErr: ''})
        });
    }

    parseTickets(userOrders) {
        var tickets = [];
        var ticketID = [];
        var seats = [];

        for (var i = 0; i < userOrders.length; i++) {
            if (!ticketID.includes(userOrders[i].ticket)) {
                ticketID.push(userOrders[i].ticket)
            }
        }

        for (var m = 0; m < ticketID.length; m++) {
            for (var j = 0; j < userOrders.length; j++) {
                var order = userOrders[j];
                var ticket;
                var title;
                var filmDate;
                if (ticketID[m] === order.ticket) {
                    seats.push(userOrders[j].seat);
                    ticket = order.ticket;
                    title = order.title;
                    var dateAndTime = new Date(order.filmDate);
                    filmDate = dateAndTime.toLocaleString().replace(', ', 'T').replace(/\./g, '-');
                }
                if (j === userOrders.length - 1) {
                    tickets.push({title: title, filmDate: filmDate, ticket: ticket, seats: seats});
                    seats = [];
                }
            }
        }
        return tickets;
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
        var tickets = this.parseTickets(userOrders);
        return (tickets.map((ticket, index) => (

            <div key={index} style={{display: 'table', paddingBottom: '10px'}}>
                <div style={{display: 'table-cell', width: '600px'}}>
                    <label>{index + 1}. </label>
                    <label>ID: {ticket.ticket}, </label>
                    <label>film: {ticket.title}, </label>
                    <label>date: {ticket.filmDate}, </label>
                    <label>row and seat: </label>
                    {ticket.seats.map((seat, i) => (
                     <label key={i}>{Math.round(seat/10 + 1)}/{seat%10} </label>
                    ))}
                </div>
                <div style={{display: 'table-cell', paddingLeft: '10px'}}>
                    <Fab className="delete-icon" color="primary">
                        <EditIcon fontSize="small"
                                  onClick={() => this.handleUpdateOrder(ticket.title, ticket.filmDate, ticket.seats, ticket.ticket)}/>
                    </Fab>
                </div>
                <div style={{display: 'table-cell', paddingLeft: '10px'}}>
                    <Fab className="delete-icon" >
                        <DeleteIcon fontSize="small"
                                    onClick={() => this.handleDeleteOrder(ticket.ticket, this.state.loggedInUser.login)}/>
                    </Fab>
                </div>
            </div>)
        ))
    }

    throwWarning = (warn, color) => {
        this.props.warn(warn, color)
    };

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
                <Modal open={this.state.isOpenUpdateOrdersModal} onClose={this.onCloseUpdateOrdersModal}
                       classNames={{modal: 'modal-orders-body'}}>
                    <div>
                       <div style={{textAlign: 'center', width: '100%'}} >Updating ticket:</div>
                       <div style={{font: 'Arial'}}>ID: {this.state.ticketID}</div>
                       <div>film: {this.state.title}</div>
                       <div>date: {this.state.filmdate}</div>
                      <Hall tickets={this.state.bookedSeats}
                            ticketID={this.state.ticketID}
                            film={this.state.title}
                            dateIndex={this.state.filmdate}
                            seatsToUpdate={this.state.seatsToUpdate}
                            warning={(warn, color)=> this.throwWarning(warn, color)}/>
                    </div>
                </Modal>
            </div>
        )
    }
}

export default Head;