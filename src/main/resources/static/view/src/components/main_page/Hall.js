/**
 * Created by Mina on 27.04.2019.
 */

import React, {Component} from 'react'
import {RadioButton} from '../../../node_modules/react-custom-radio'
import {bounceInDown} from '../../../node_modules/react-animations'
import Radium, {StyleRoot}  from '../../../node_modules/radium'
import Fab from '../../../node_modules/@material-ui/core/Fab'

const styles = {
    bounceIn: {
        animation: 'x 1s',
        animationName: Radium.keyframes(bounceInDown, 'bounceInDown')
    }
};

class Hall extends Component {

    constructor(props){
        super(props);
        this.state = {
            seats: this.printSeats(),
            userOrder: [],
            successOrder: []
        }
    }

    handleClick = (seat, row) => {
        var userOrder = this.state.userOrder;

        if (this.includeSeat(userOrder, seat, row)){
          userOrder = userOrder.filter((s) => {
              if (s.seatNmb === seat && s.rowNmb === row) {
                  return false;
              }
              else {
                  return true;
              }
          })

        }
        else  if (userOrder.length > 2) {
            this.props.warning('Max allowed seat quantity in one order is 3!', 'red')
        }
        else {
            userOrder.push({seatNmb: seat, rowNmb: row});
        }
        this.setState({userOrder: userOrder});
    };

    handleCreateOrder = (e) => {
        e.stopPropagation();
        var action = this.props.seatsToUpdate === undefined ? 'save' : 'update';
        fetch(`http://localhost:8080/cinema/rest/${action}`, {
            method: this.props.seatsToUpdate === undefined ? 'POST' : 'PUT',
            credentials: 'include',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "X-Requested-With": "XMLHttpRequest"
            },
            body: JSON.stringify({
                film: this.props.film,
                seats : this.state.userOrder,
                dateId: this.props.dateID,
                ticket: this.props.seatsToUpdate === undefined ? '' : this.props.ticketID
                })
            }).then(result => {
                if (result.status !== 200) {
                    this.props.warning('Error occured when saving order!', 'red')
                }
                else {
                    const success = this.state.successOrder;
                    success.push.apply(success, this.state.userOrder);
                    this.setState({successOrder: success , userOrder: []});
                    this.props.warning('Order was persisted successfully', 'blue')
                }
        });
    };

    printSeats () {
        const row = [];
        for (var i = 1; i <= 8; i++) {
            for (var j = 1; j <=12; j++) {
                row.push({seat: j, row: i});
            }
        }
        return row;
    };

    includeSeat(seats, seat, row) {
       return seats.filter((s) => (s.seatNmb === seat && s.rowNmb === row)).length === 1
    }

    componentDidUpdate(prevProps) {
        if (prevProps.dateID !== this.props.dateID || prevProps.film !== this.props.film) {
            this.setState({userOrder: []})
        }
    }

    componentDidMount() {
        this.setState({userOrder: this.props.seatsToUpdate === undefined ? [] : this.props.seatsToUpdate})
    }

    render(){
        const userOrder = this.state.userOrder;
        const successOrder = this.state.successOrder;

        const blockSeats = this.props.tickets.map((ticket) =>({seatNmb: ticket.seatnumber, rowNmb: ticket.row}));
        const style = (index, seat, row) => {

            if (this.includeSeat(successOrder, seat, row) || this.includeSeat(blockSeats, seat, row)) {
                return "seat-chosen"
            }
             if (this.includeSeat(userOrder, seat, row)) {
                return "seat-ordered"
            }
            else
                return "seat"
        };

        return (
            <div >
                <div style={{display: 'inline-flex'}}>
                    <div>
                        {this.state.seats.map((seat, index) => (
                            seat.seat%12 === 0 &&
                            <div key={index} style={{display: 'block', paddingRight: '15px', paddingBottom: '5px'}}>
                                <RadioButton disabled={true} className="seat-chosen">
                                    <label>{seat.row}</label>
                                </RadioButton>
                            </div>
                        ))}
                    </div>
                    <div style={{width: '420px'}}>
                        {this.state.seats.map((seat, index) => (
                            <div key={index} className="seat-block" >
                                <RadioButton disabled={this.includeSeat(successOrder, seat.seat, seat.row) ||
                                                       this.includeSeat(blockSeats, seat.seat, seat.row) ? true : false}
                                             className={style(index, seat.seat, seat.row)}
                                             onClick={() => this.handleClick(seat.seat, seat.row)} >
                                    <label>{seat.seat}</label>
                                </RadioButton>
                            </div>
                        ))}
                    </div>
                </div>
                <div>
                    { userOrder.length > 0 &&
                    <StyleRoot>
                        <div className="order-button" style={styles.bounceIn}>
                            <Fab variant="extended" aria-label="Delete" onClick={(e) => this.handleCreateOrder(e)}>
                                {this.props.seatsToUpdate === undefined ? 'Make order' : 'Update order'}
                            </Fab>
                        </div>
                    </StyleRoot>
                    }
                </div>
            </div>
        );
    }
}

export default Hall;