/**
 * Created by Mina on 13.05.2019.
 */
import React from 'react'
import { rubberBand} from '../../../../node_modules/react-animations'
import Radium, {StyleRoot}  from '../../../../node_modules/radium'

export const styles = {
    rubber: {
        animation: 'x 1.5s',
        animationName: Radium.keyframes(rubberBand, 'rubberBand')
    }
};

export function printWarn (warning) {
    if (warning !== '') {
        return (
            <StyleRoot>
                <div className="block-warn" style={styles.rubber}>
                    <label style={{verticalAlign: 'middle'}}>{warning}</label>
                </div>
            </StyleRoot>
        )
    }
}