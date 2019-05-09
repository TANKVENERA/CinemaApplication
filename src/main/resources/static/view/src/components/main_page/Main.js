/**
 * Created by Mina on 26.04.2019.
 */

import React, {Component} from 'react'
import './styles/hall.css'
import Films from './Films'
import Head from './Head'

class Main extends Component {

  render() {
      return (<div>
           <div>
               <Head/>
           </div>
          <div>
              <Films/>
          </div>
      </div>);
  }
}

export default Main
