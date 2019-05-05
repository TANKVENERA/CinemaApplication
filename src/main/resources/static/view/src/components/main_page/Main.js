/**
 * Created by Mina on 26.04.2019.
 */

import React, {Component} from 'react'
import './styles/hall.css'
import Films from './Films'

class Main extends Component {

  render() {
      return (<div>
           <div>Hi bot!</div>
          <div>
              <Films/>
          </div>
          {/*<div>*/}
              {/*<Hall/>*/}
          {/*</div>*/}
      </div>);
  }
}

export default Main
