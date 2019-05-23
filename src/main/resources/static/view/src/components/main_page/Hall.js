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

    handleClick = (index) => {
        var userOrder = this.state.userOrder;
        if (userOrder.includes(index)){
          userOrder = userOrder.filter(i => {return i !== index});
        }
        else  if (userOrder.length > 2) {
            this.props.warning('Max allowed seat quantity in one order is 3!', 'red')
        }
        else {
            userOrder.push(index);
        }
        this.setState({userOrder: userOrder});
    };

    handleCreateOrder = (e) => {
        e.stopPropagation()
        fetch('http://localhost:8080/cinema/rest/makeOrder', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                "X-Requested-With": "XMLHttpRequest"
            },
            body: JSON.stringify({
                film: this.props.film,
                seats : this.state.userOrder,
                dateIndex: this.props.dateIndex
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
        for (var i = 1; i <= 100; i++) {
            row.push(i);
        }
        return row;
    };

    componentDidUpdate(prevProps) {
        if (prevProps.dateIndex !== this.props.dateIndex || prevProps.film !== this.props.film) {
            this.setState({userOrder: []})
        }
    }

    render(){
        const userOrder = this.state.userOrder;
        const successOrder = this.state.successOrder;
        const blockSeats = this.props.tickets.map((ticket) =>(ticket.seatnumber));
        const style = (seat) => {
            if (blockSeats.includes(seat) || successOrder.includes(seat)) {
                return "seat-chosen"
            }
            else if (userOrder.includes(seat) ) {
                return "seat-ordered"
            }
            else
                return "seat"
        };

        return (
            <div>
                <div style={{width: '350px'}}>
                    {this.state.seats.map((seat) => (
                        <div key={seat} className="seat-block" >
                            <RadioButton disabled={successOrder.includes(seat) || blockSeats.includes(seat) ? true : false}
                                         className={style(seat)}
                                         onClick={() => this.handleClick(seat)} />
                        </div>
                    ))}
                </div>
                <div>
                    { userOrder.length > 0 &&
                        <StyleRoot>
                            <div className="order-button" style={styles.bounceIn}>
                                <Fab variant="extended" aria-label="Delete" onClick={(e) => this.handleCreateOrder(e)}>
                                    Make order
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