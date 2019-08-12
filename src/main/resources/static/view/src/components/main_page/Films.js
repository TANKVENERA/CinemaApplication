/**
 * Created by Mina on 28.04.2019.
 */

import React, {Component} from 'react'
import SwipeableViews from '../../../node_modules/react-swipeable-views'
import Hall from './Hall'
import {FiPlus} from 'react-icons/fi'
import SettingsBlock from './SettingsBlock'
import Modal from 'react-responsive-modal'
import Calendar from 'react-calendar'
import Button from '@material-ui/core/Button'
import {NotificationContainer, NotificationManager} from 'react-notifications'
import Moment from 'moment'
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
            isDateActive: {index: 0, isActive: false},
            isFilmActive: {index: 0, isActive: true},
            drawer: false,
            isOpenAddFilmModal: false,
            filmTitleToAdd: '',
            newDate: new Date()
        }
    }

    componentWillMount() {
        fetch(`http://localhost:8080/cinema/rest/films`).then(result => {
            return result.json();
        }).then(data => {
                this.setState({films: data})});
    }

    handleUniqueFilm = (index, uniqueFilm) => {
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
                    currentFilm: uniqueFilm,
                    isFilmActive: {index: index, isActive: false},
                    isDateActive: {index: 0, isActive: false}

                });
            }
        ).catch(error => this.props.warn('Unauthorized!', 'red'));
    };

    handleUpdateFilm = () => {
        var date = this.state.newDate;
        var formattedDate = Moment(date).format('DD-MM-YYYY');
        var title = this.state.filmTitleToAdd;
        if (title !== '') {
            fetch(`http://localhost:8080/cinema/rest/films`, {
                method: "POST",
                credentials: 'include',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    "X-Requested-With": "XMLHttpRequest"
                },
                body: JSON.stringify({
                    title: title,
                    formattedDate: formattedDate
                })
            }).then(result => {
                return result.text()
            }).then(data => {

                if (data.includes('already')) {
                    NotificationManager.error("Film - '" + title + "' with date - '" + formattedDate + "' already exists!");
                }
                else {
                    NotificationManager.success(data);
                    fetch(`http://localhost:8080/cinema/rest/films`).then(result => {
                        return result.json();
                    }).then(data => {
                        this.setState({films: data})});
                }
            })
        }
        else {
            NotificationManager.error("Film title is not passed!");
        }
        this.setState({filmTitleToAdd: '', isOpenAddFilmModal: false});
    };

    handleDatesChange = (index) => {
        this.setState({dateIndex: index, isDateActive: {index: index, isActive: false}})
    };

    throwWarning = (warn, color) => {
        this.props.warn(warn, color)
    };

    handleDeletedFilm = (deletedFilm) => {
        var filteredFilms = this.state.films.filter((film) => {return film.title !== deletedFilm});
        this.setState({films: filteredFilms})
    };

    handleDeletedDate = (id) => {
        var filteredDates = this.state.dates.filter((date) => {return date.id !== id});
        this.setState({dates: filteredDates, dateIndex: 0, isDateActive: {index: 0, isActive: false}})
    }

    handleAddedDate = (index, title) => {
            fetch(`http://localhost:8080/cinema/rest/films/${title}`, {
                method: "GET",
                credentials: 'include',
                headers: {
                    "X-Requested-With": "XMLHttpRequest"
                }
            }).then(result => {
                return result.json();
            }).then(data => {
                    this.setState({
                        dates: data.dates, index: index, isInitial: false, dateIndex: 0,
                        isFilmActive: {index: index, isActive: false},
                        isDateActive: {index: 0, isActive: false}
                    });
                })
    };

    onOpenFilmsModal = () => {
        this.setState({isOpenAddFilmModal: true})
    };

    onCloseFilmsModal = () => {
        this.setState({isOpenAddFilmModal: false, filmTitleToAdd: ''})
    };

    handleFilmTitleToAdd = (event) => {
        this.setState({filmTitleToAdd: event.target.value})
    };

    handleDate = (date) => {
        this.setState({newDate: date})
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

    filmBlock () {
      var films = this.state.films;
      const filmBlock =  (<div className="film-block">
            {films.map((film, index) => (
                <div key={index} className="film-unit">
                    <div >
                        <button type="button" disabled={this.state.isFilmActive.isActive === false &&
                        this.state.isFilmActive.index === index ? true : false}
                                className="film-button"
                                onClick={() => this.handleUniqueFilm(index, film.title)}>
                            {film.title}
                        </button>
                    </div>
                    {this.props.passedUserRole === 'ADMIN' &&
                    <div>
                        <SettingsBlock filmTitle={film.title}
                                       filmIndex={index}
                                       deletedFilm={(film) => this.handleDeletedFilm(film)}
                                       deletedDate={(id) => this.handleDeletedDate(id)}
                                       addedDate={(index, title) => this.handleAddedDate(index, title)}/>
                    </div>}
                </div>))}
            {this.props.passedUserRole === 'ADMIN' &&
            <div className="film-unit">
                <button className="film-button" onClick={this.onOpenFilmsModal}>
                    <FiPlus size="1.2em" style={{verticalAlign: 'middle'}}/>
                </button>
            </div>

            }
        </div>);

        return filmBlock;
    }

    render() {
        let dates = this.state.dates;
        let filmIndex = this.state.index;
        let dateIndex = this.state.dateIndex;
        let isInitial = this.state.isInitial;
        return (
            <div className="main-block">
                {this.filmBlock()}
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
                                                    film={this.state.currentFilm}/>
                                            </div>))}
                                    </SwipeableViews>
                                </div>
                            </div>
                        ))}
                    </SwipeableViews>
                </div>
                <Modal open={this.state.isOpenAddFilmModal} onClose={this.onCloseFilmsModal}
                       showCloseIcon={false} classNames={{modal: 'modal-orders-body'}}>
                    <div >
                        <div style={{paddingBottom: '10px'}}>
                            <Calendar value={this.state.newDate} minDate={new Date(2019, 6, 1)} maxDate={new Date(2021, 6, 1)}
                                      onChange={this.handleDate}/>
                        </div>
                        <div>
                            <div>
                                <div className="head-login-item">
                                    <input value={this.state.filmTitleToAdd} onChange={this.handleFilmTitleToAdd} placeholder="Film title"
                                           className="input-field"/>
                                </div>
                                <div className="head-sign-in-button">
                                    <Button variant="outlined" color="primary" onClick={this.handleUpdateFilm}>
                                        Add/Update
                                    </Button>
                                </div>
                            </div>
                        </div>
                        <div style={{height: '90px'}}>
                            <div style={{paddingLeft: '40%', paddingTop: '30px'}}>
                                <Button variant="outlined" color="secondary" onClick={this.onCloseFilmsModal}>
                                    Exit
                                </Button>
                            </div>
                        </div>
                    </div>
                </Modal>
                <NotificationContainer leaveTimeout={7000}/>
            </div>)
    }
}

export default Films
