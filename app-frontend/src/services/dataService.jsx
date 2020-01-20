import axios from 'axios';

import { navigation } from '../config/path';

const METHODS = {
    GET: 'GET',
    POST: 'POST'
}

const BASE_URL = process.env.REACT_APP_BASE_URL;

const CONFIG = {
    'signin': {
        method: METHODS.POST,
        url: `${BASE_URL}/login`,
        autheticated: false
    },
}

const makeRequest = (httpCall, payload = {}) => {
    return new Promise(async (resolve, reject) => {
        const requestData = CONFIG[httpCall];

        if (!requestData) {
            return reject('No config');
        }

        if (requestData.autheticated) {
            const token = localStorage.getItem('token');

            if (!token) {
                window.location.replace(navigation.login);
                return reject('No token');
            }

            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        }

        const response = await axios({
            method: requestData.method,
            url: requestData.url,
            data: payload,
        })

        return resolve(response.data);
    })

}

export default makeRequest;