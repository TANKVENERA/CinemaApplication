/**
 * Created by Mina on 28.04.2019.
 */

import React, {Component} from 'react'
import SwipeableViews from '../../../node_modules/react-swipeable-views'
import Hall from './Hall'
import './styles/films.css'

class Films extends Component {
    state = {
        index: 0,
        films: this.printFilms()
    };

    handleClick = (index) => {
        console.log('CURENT-INDEX', index);
        this.setState({index: index });
    };

    printFilms () {
        let films =[]
        for (var i = 0; i < 4; i++) {
            films.push(i);
        }
        return films;
    }

    render() {

        return (
            <div className="main-block">
                <div className="film-block">
                    {this.state.films.map(filmIndex => (
                        <div key={filmIndex}>
                            <button type="button" className="film-button" onClick={(i) => this.handleClick(filmIndex)}>TERMINATOR</button>
                        </div>
                    ))}
                </div>
                <div className="hall-block">
                    <SwipeableViews index={this.state.index}>
                        {this.state.films.map(i => (
                            <div key={i}>
                                <Hall />
                            </div>
                        ))}
                    </SwipeableViews>
                </div>
            </div>
        )
    }
}

export default Films
