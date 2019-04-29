/**
 * Created by Mina on 27.04.2019.
 */

import React, {Component} from 'react'
import {RadioGroup, RadioButton} from '../../../node_modules/react-custom-radio'

class Hall extends Component {
    constructor(){
        super();
        this.state = {
            seats: this.printSeats()
        }
    }

    handleClick = (index, flag) => {
       const toUpdate = this.state.seats;
        toUpdate[index] = {index: index, flag: !flag};
        this.setState({seats: toUpdate});
    };

    printSeats = () => {
        const row = [];
        for (var i = 0; i < 100; i++) {
            row.push({index: i, flag: false});
        }
        return row;
    };

    render(){
        return (
            <div style={{width: '450px'}}>
                {this.state.seats.map((seat) => (
                     <div key={seat.index} className="seat-block" >
                        <RadioButton  className={seat.flag === true ? "seat-chosen" : "seat"} onClick={() => this.handleClick(seat.index, seat.flag)}/>
                     </div>
                ))}
            </div>
        );
    }
}

export default Hall;