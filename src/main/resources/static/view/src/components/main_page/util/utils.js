/**
 * Created by Mina on 13.05.2019.
 */

export function checkStatus (result, user) {
    if (result.status === 401) {
        result = JSON.stringify({loggedInUser: user, authenticated: false});
        return JSON.parse(result);
    }
    else {
        return result.json();
    }
}

