/**
 * Created by Mina on 26.04.2019.
 */

import React, {Component} from 'react'
import './styles/hall.css'
import Films from './Films'
import Head from './Head'
import Warning from './Warning'

class Main extends Component {
    constructor(){
        super();
        this.state = {
            warning: '',
            color: ''
        }
    }

 handleWarningParam = (warning, color) => {
     this.setState({warning: warning, color: color});
     setTimeout(() => this.setState({warning: ''}), 3000);
 };

  render() {
      return (<div>
                  <div>
                      <Head warn={(warning, color) => this.handleWarningParam(warning, color)}/>
                  </div>
                  <div>
                      <Films warn={(warning, color) => this.handleWarningParam(warning, color)}/>
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
