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
        isInitial: true,
        dateIndex: 0,
        films: [],
        dates: []
    };

    componentWillMount() {
        fetch(`http://localhost:8080/cinema/rest/films`)
            .then(result => {
                return result.json();
            })
            .then(data => this.setState({films: data}));
    }

    handleClick = (index, uniqueFilm) => {
        fetch(`http://localhost:8080/cinema/rest/dates/?film=${uniqueFilm}`)
            .then(result => {
                return result.json();
            }).then(data => this.setState({dates: data, index: index, isInitial: false}));
    };

    handleDatesChange = (date, index) => {
        this.setState({dateIndex: index})
    };

    dateBlock(dates) {
        const dateBlock = dates.map((date, index) => (
            <div key={index} style={{display: 'inline-block'}}>
                <button type="button" className="film-button" onClick={() => this.handleDatesChange(date.filmdate, index)}>
                    {date.filmdate}
                </button>
            </div>
        ));
        return dateBlock
    }

    render() {
        let dates = this.state.dates;
        let filmIndex = this.state.index;
        let dateIndex = this.state.dateIndex;
        let isInitial = this.state.isInitial;
        return (
            <div className="main-block">
                <div className="film-block">
                    {this.state.films.map((film, index) => (
                        <div key={index}>
                            <button type="button" className="film-button" onClick={() => this.handleClick(index, film.title)}>
                                {film.title}
                            </button>
                        </div>))
                    }
                </div>

                <div className="hall-block">
                    <SwipeableViews index={filmIndex}>
                        {this.state.films.map((film, index) => (
                            <div key={index}>
                                <div >
                                    {this.dateBlock(filmIndex === 0 && isInitial ? [] : dates)}
                                </div>
                                <div>
                                    <SwipeableViews index={dateIndex}>
                                        {(filmIndex === 0  && isInitial ? [] : dates).map((date, index) => (
                                        <div key={index}>
                                            <Hall
                                                tickets={filmIndex === 0 && isInitial ? [] : dates[dateIndex].tickets}/>
                                        </div>))}
                                    </SwipeableViews>
                                </div>
                            </div>
                        ))}
                    </SwipeableViews>
                </div>
            </div>
        )
    }
}

export default Films
