/**
 * Created by Mina on 08.08.2019.
 */

import React, {Component} from 'react'
import '@reach/menu-button/styles.css'
import {GoGear} from 'react-icons/go'
import {IoIosCheckmarkCircle, IoIosCloseCircle} from 'react-icons/io'
import {MdDelete, MdDeleteForever, MdModeEdit} from 'react-icons/md'
import DropdownMenu, { NestedDropdownMenu } from 'react-dd-menu';
import 'react-dd-menu/dist/react-dd-menu.css'
import 'react-notifications/lib/notifications.css'
import Calendar from 'react-calendar'
import Moment from 'moment'
import {NotificationContainer, NotificationManager} from 'react-notifications';

class SettingsBlock extends Component {

    constructor(props){
        super(props)
        this.state = {
            isMenuOpen: false,
            loggedInUser: {login: '', role: ''},
            filmDates: [],
            newDate: new Date()
        }
    }

    toggle = () => {
        this.setState({isMenuOpen: !this.state.isMenuOpen});
        fetch(`http://localhost:8080/cinema/rest/films/${this.props.filmTitle}`, {
            method: "GET",
            credentials: 'include',
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        }).then(result => {
            return result.json();
        }).then(data => {
            this.setState({filmDates: data.dates});
        })
    };

    close = () => {
        this.setState({isMenuOpen: false});
    };

    manualClose = () => {
        NotificationManager.warning('Canceled');
        this.setState({isMenuOpen: false});
    };

    handleDeleteFilm = () => {
           var film = this.props.filmTitle;
            fetch(`http://localhost:8080/cinema/rest/films/${film}`, {
                method: "DELETE",
                credentials: 'include',
                headers: {
                    "X-Requested-With": "XMLHttpRequest"
                }
            }).then(result => {
                return result.text();
            }).then(data => {
                NotificationManager.success("Film - '" + film + "' was deleted");
                this.props.deletedFilm(film);
            })
    };

    handleDeleteDate = (id, date) => {
        fetch(`http://localhost:8080/cinema/rest/dates/${id}`, {
            method: "DELETE",
            credentials: 'include',
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        }).then(result => {
            return result.text();
        }).then(data => {
            this.setState({isMenuOpen: false})
            NotificationManager.success("Date '" + date + "' was removed");
            this.props.deletedDate(id);
           });
    };

    handleDate = (date) => {
        this.setState({newDate: date, isMenuOpen:true})
    };

    handleAddDate = () => {
        var date = this.state.newDate;
        var formattedDate = Moment(date).format('DD-MM-YYYY');
        var title = this.props.filmTitle;

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
                this.setState({isMenuOpen: false});
                if (data.includes('already')) {
                    NotificationManager.error("Date - " + formattedDate + " already exists!");
                }
                else {
                    NotificationManager.success("Date '" + formattedDate + "' was added");
                    this.props.addedDate(this.props.filmIndex, this.props.filmTitle);
                }
        })
    }

    confirmOrDelayBtnBlock (properties, index) {
       return ( <NestedDropdownMenu {...properties} key={index}>
            <li className="dd-menu-items">
                <div className="md-done-btn-block">
                    <button className="md-done-btn" onClick={properties.dateID === undefined ? this.handleDeleteFilm :
                        () => {this.handleDeleteDate(properties.dateID, properties.formattedDate)}}>
                        <IoIosCheckmarkCircle size="2.5em" color="green"/>
                    </button>
                </div>
                <div className="md-close-btn-block">
                    <button className="md-close-btn" onClick={this.manualClose}>
                        <IoIosCloseCircle size="2.5em" color="yellow"/>
                    </button>
                </div>
            </li>
        </NestedDropdownMenu>)
    }

    render () {
        const menuOptions = {
            isOpen: this.state.isMenuOpen,
            close: this.close,
            toggle: <button type="button" onClick={this.toggle} className="film-button" style={{borderLeft: 'none'}}>
                        <GoGear size="1.2em" className="gear" style={{verticalAlign: 'middle'}}/>
                    </button>,
             align: 'left',
            delay: 150,
            closeOnInsideClick: false
        };

        const deleteDateLabelOptions = {
            toggle: <label className="label-opts" onClick={() => {}}><MdDelete className="label-btn" size="1.2em"/></label>,
            animate: true,
            nested: 'reverse',
            delay: 150
        };

        const deleteFilmLabelOptions = {
            toggle: <label className="label-opts"><MdDeleteForever className="label-btn" color="red" size="1.2em"/></label>,
            animate: true,
            nested: 'reverse',
            delay: 150
        };

        const editFilmLabelOptions = {
            toggle: <label className="label-opts"><MdModeEdit className="label-btn" color="blue" size="1.2em"/></label>,
            animate: true,
            nested: 'reverse',
            delay: 150
        };

        const deleteAllFilmOptions = {
            toggle: <label className="label-menu">Sure to delete film with all dates?</label>,
            animate: true,
            nested: 'reverse',
            delay: 150
        };

        var filmDates = this.state.filmDates.map((date, index) =>(
            {
                toggle: <label>{date.formattedDate}</label>,
                animate: true,
                nested: 'reverse',
                delay: 150,
                dateID: date.id,
                formattedDate: date.formattedDate
            }
        ));

        return (
            <div>
                <DropdownMenu {...menuOptions}>
                    <NestedDropdownMenu {...editFilmLabelOptions}>
                        <li><label className="label-menu">Choose date to add...</label></li>
                        <li role="separator" className="separator" />
                        <li>
                            <div>
                                <Calendar value={this.state.newDate} minDate={new Date(2019, 6, 1)} maxDate={new Date(2021, 6, 1)}
                                      onChange={this.handleDate}/>
                                <div className="add-date-btn-block">
                                    <div className="md-done-btn-block" onClick={this.handleAddDate}>
                                        <button className="md-done-btn">
                                            <IoIosCheckmarkCircle size="2.5em" color="green"/>
                                        </button>
                                    </div>
                                    <div className="md-close-btn-block">
                                        <button className="md-close-btn" onClick={this.manualClose}>
                                            <IoIosCloseCircle size="2.5em" color="yellow"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </NestedDropdownMenu>
                    <NestedDropdownMenu {...deleteDateLabelOptions}>
                        <li><label className="label-menu">Choose date to remove...</label></li>
                        <li role="separator" className="separator" />
                        {filmDates.map((properties, index) =>(
                            this.confirmOrDelayBtnBlock(properties, index)
                        ))}
                    </NestedDropdownMenu>
                    <NestedDropdownMenu {...deleteFilmLabelOptions}>
                        {this.confirmOrDelayBtnBlock(deleteAllFilmOptions, 0)}
                    </NestedDropdownMenu>
                </DropdownMenu>
                <NotificationContainer leaveTimeout={7000}/>
            </div>
        )
    }
}

export default SettingsBlock;