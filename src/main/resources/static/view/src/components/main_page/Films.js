/**
 * Created by Mina on 28.04.2019.
 */

import React, {Component} from 'react'
import SwipeableViews from '../../../node_modules/react-swipeable-views'
import Fab from '../../../node_modules/@material-ui/core/Fab'
import Hall from './Hall'

class Films extends Component {

    constructor(props){
        super(props)
        this.state = {
            index: 0,
            dateIndex: 0,
            isInitial: true,
            films: [],
            dates: [],
            currentFilm: '',
            isDateActive: {index: '', isActive: true}
        }
    }


    componentWillMount() {
        fetch(`http://localhost:8080/cinema/rest/films`).then(result => {
            return result.json();
        }).then(data => {
                this.setState({films: data})});
    }

    handleClick = (index, uniqueFilm) => {
        fetch(`http://localhost:8080/cinema/rest/films/${uniqueFilm}`, {
            method: "GET",
            credentials: 'include',
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        }).then(result => {
            if (!result.ok) {
                throw Error(result.status)
            }
            else {
                return result.json();
            }
        }).then(data => {
                this.setState({
                    dates: data.dates, index: index, isInitial: false, dateIndex: 0,
                    currentFilm: uniqueFilm
                });
            }
        ).catch(error => this.props.warn('Unauthorized!', 'red'));
    };

    handleDatesChange = (index) => {
        this.setState({dateIndex: index, isDateActive: {index: index, isActive: false}})
    };

    throwWarning = (warn, color) => {
        this.props.warn(warn, color)
    };

    dateBlock(dates) {
            const dateBlock = dates.map((date, index) => (
                <div key={index} className="date-button-block">
                    <button type="button" disabled={this.state.isDateActive.isActive === false &&
                                                    this.state.isDateActive.index === index ? true : false}
                            className="date-button date-style"
                            onClick={() => this.handleDatesChange(index)}>
                        {date.formattedDate}
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
                            <button type="button" className="film-button"
                                    onClick={() => this.handleClick(index, film.title)}>
                                {film.title}
                            </button>
                        </div>))}
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
                                        {(filmIndex === 0 && isInitial ? [] : dates).map((date, index) => (
                                            <div key={index}>
                                                <Hall
                                                    warning={(warn, color)=> this.throwWarning(warn, color)}
                                                    tickets={filmIndex === 0 && isInitial ? [] : dates[dateIndex].tickets}
                                                    dateID={date.id}
                                                    filmIndex={filmIndex}
                                                    film={this.state.currentFilm}
                                                />
                                            </div>))}
                                    </SwipeableViews>
                                </div>
                            </div>
                        ))}
                    </SwipeableViews>
                </div>
            </div>)
    }
}

export default Films
