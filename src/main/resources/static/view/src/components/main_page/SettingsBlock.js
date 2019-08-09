/**
 * Created by Mina on 08.08.2019.
 */

import React, {Component} from 'react'
import '@reach/menu-button/styles.css'
import {GoGear, GoTrashcan} from 'react-icons/go'
import {IoMdClose} from 'react-icons/io'
import {MdDelete, MdDeleteForever, MdModeEdit, MdDone, MdCancel} from 'react-icons/md'
import DropdownMenu, { NestedDropdownMenu } from 'react-dd-menu';
import 'react-dd-menu/dist/react-dd-menu.css'

class SettingsBlock extends Component {

    constructor(props){
        super(props)
        this.state = {
            isMenuOpen: false
        }
    }

    toggle = () => {
        this.setState({ isMenuOpen: !this.state.isMenuOpen });
    };

    close = () => {
        this.setState({ isMenuOpen: false });
    };

    confirmOrDelayBtnBlock (properties, index) {
       return ( <NestedDropdownMenu {...properties} key={index}>
            <li className="dd-menu-items">
                <div className="md-done-btn-block">
                    <button className="md-done-btn">
                        <MdDone size="2.5em" color="green"/>
                    </button>
                </div>
                <div className="md-close-btn-block">
                    <button className="md-close-btn">
                        <IoMdClose size="2.5em" color="yellow"/>
                    </button>
                </div>
            </li>
        </NestedDropdownMenu>)
    }

    render () {
        const menuOptions = {
            isOpen: this.state.isMenuOpen,
            close: this.close,
            toggle: <button type="button" onClick={this.toggle}>
                     <GoGear size="2em" className="gear"/>
                    </button>,
             align: 'left',
            delay: 50000000,
        };

        const nestedProps2 = {
            toggle: <label><MdDelete size="1.4em"/></label>,
            animate: true,
            nested: 'reverse',
            delay: 150,
        };

        const nestedProps3 = {
            toggle: <label><MdDeleteForever color="red" size="1.4em"/></label>,
            animate: true,
            nested: 'reverse',
            delay: 150,
        };

        const nestedProps4 = {
            toggle: <label><MdModeEdit color="blue" size="1.4em"/></label>,
            animate: true,
            nested: 'reverse',
            delay: 150,
        };

        const nestedProps5 = {
            toggle: <label>Sure to delete film with all dates?</label>,
            animate: true,
            nested: 'reverse',
            delay: 150,
        };

        var X = this.props.filmDates.map((date, index) =>(
            {
                toggle: <label>{date.formattedDate}</label>,
                animate: true,
                nested: 'reverse',
                delay: 150,
            }
        ));

        return (
            <div className="lololo">
                <DropdownMenu {...menuOptions}>
                    <NestedDropdownMenu {...nestedProps4}>
                        <li><label>Calendar</label></li>
                    </NestedDropdownMenu>
                    <NestedDropdownMenu {...nestedProps2}>
                        <li><label>Choose date to remove...</label></li>
                        <li role="separator" className="separator" />
                        {X.map((properies, index) =>(
                            this.confirmOrDelayBtnBlock(properies, index)
                        ))}
                    </NestedDropdownMenu>
                    <NestedDropdownMenu {...nestedProps3}>
                        {this.confirmOrDelayBtnBlock(nestedProps5, 0)}
                    </NestedDropdownMenu>
                </DropdownMenu>
            </div>
        )
    }



}

export default SettingsBlock;