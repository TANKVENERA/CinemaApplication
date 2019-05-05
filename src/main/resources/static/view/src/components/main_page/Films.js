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
        this.setState({index: index, dateIndex: 0});
        let currDate = [];
        this.state.films.map(film => {
                if (uniqueFilm === film.title) {
                    currDate.push({film: uniqueFilm, date: film.date, tickets: film.tickets})
                }
                return ''
            }
        );
        this.setState({dates: currDate});
    };

    handleDatesChange = (date, index) => {
        this.setState({dateIndex: index})
    };

    uniqueFilms() {
        let uniqueFilms = [];
        this.state.films.map(film => {
            if (uniqueFilms.indexOf(film.title) === -1) {
                uniqueFilms.push(film.title);
            }
            return ''
        });
        return uniqueFilms;
    }

    firstDateBlock() {
        let firstFilm = this.state.films[0].title;
        let firstFilmDates = [];
        this.state.films.map(film => {
            if (firstFilm === film.title) {
                firstFilmDates.push({film: firstFilm, date: film.date, tickets: film.tickets})
            }
            return ''
        });
        return firstFilmDates
    };

    dateBlock(dates) {
        const dateBlock = dates.map((date, index) => (
            <div key={index} style={{display: 'inline-block'}}>
                <button type="button" className="film-button" onClick={() => this.handleDatesChange(date.date, index)}>
                    {date.date}
                </button>
            </div>
        ));
        return dateBlock
    }

    render() {

        let dates = this.state.dates;
        let filmIndex = this.state.index;
        let dateIndex = this.state.dateIndex;
        console.log('FILM: ', filmIndex, 'DATE', this.state.dateIndex, 'TICKETS:', this.state.dates)
        return (
            <div className="main-block">
                <div className="film-block">
                    {this.uniqueFilms().map((film, index) => (
                            <div key={index}>
                                <button type="button" className="film-button" onClick={() => this.handleClick(index, film)}>
                                    {film}
                                </button>
                            </div>
                        )
                    )
                    }
                </div>

                <div className="hall-block">
                    <SwipeableViews index={filmIndex}>
                        {
                            this.uniqueFilms().map((film, index) => (
                                <div key={index}>
                                    <div >
                                        {this.dateBlock(filmIndex === 0 ? this.firstDateBlock() : dates)}
                                    </div>
                                    <div>
                                        <SwipeableViews index={dateIndex}>
                                            {
                                                (filmIndex === 0 ? this.firstDateBlock() : dates).map((date, index) => (
                                                        <div key={index}>
                                                            <Hall tickets={filmIndex === 0 ? this.firstDateBlock()[dateIndex].tickets : dates[dateIndex].tickets}/>
                                                        </div>
                                                    )
                                                )
                                            }
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
