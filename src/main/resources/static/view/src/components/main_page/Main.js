/**
 * Created by Mina on 26.04.2019.
 */

import React, {Component} from 'react'
import './styles/hall.css'
import './styles/films.css'
import './styles/head.css'
import Films from './Films'
import Head from './Head'
import Warning from './Warning'

class Main extends Component {
    constructor(){
        super();
        this.state = {
            warning: '',
            color: '',
            userRole: ''
        }
    }

 handleWarningParam = (warning, color) => {
     this.setState({warning: warning, color: color});
     setTimeout(() => this.setState({warning: ''}), 3000);
 };


 handleUserRole = (role) => {
     this.setState({userRole: role});
 }



    render() {
        return (<div>
            <div>
                <Head warn={(warning, color) => this.handleWarningParam(warning, color)}
                      userRole = {(role) => this.handleUserRole(role)}/>
            </div>
            <div>
                <Films warn={(warning, color) => this.handleWarningParam(warning, color)}
                       passedUserRole={this.state.userRole}/>
            </div>
            <div >
                {this.state.warning !== '' &&
                <Warning passedWarn={this.state.warning} passedColor={this.state.color}/>
                }

            </div>
        </div>);
    }
}

export default Main
