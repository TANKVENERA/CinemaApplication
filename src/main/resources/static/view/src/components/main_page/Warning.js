/**
 * Created by Mina on 23.05.2019.
 */

import React, {Component} from 'react'
import {rubberBand} from '../../../node_modules/react-animations'
import Radium, {StyleRoot}  from '../../../node_modules/radium'

export const styles = {
    rubber: {
        animation: 'x 3s',
        animationName: Radium.keyframes(rubberBand, 'rubberBand')
    }
};

class Warning extends Component {

    render() {
        var warn = this.props.passedWarn;

            return (
                <StyleRoot>
                    <div className="block-warn" style={styles.rubber}>
                        <label style={{color: this.props.passedColor}} >{warn}</label>
                    </div>
                </StyleRoot>
            )

    }

}

export default Warning;