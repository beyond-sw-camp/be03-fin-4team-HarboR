/**
 * axios setup to use mock service
 */

import axios from 'axios';
const token = localStorage.getItem('token');

const axiosServices = axios.create({
  baseURL: 'http://localhost:3000',
  headers: {
    Authorization: `Bearer ${token}`,
    'Content-Type': 'application/json'
  }
});

export const setClientHeaders = (token: string | null) => {
  axiosServices.interceptors.request.use(function (config) {
    config.headers.Authorization = `Bearer ${token}`;
    return config;
  });
};

export const setContentTypeHeaders = (type: string) => {
  axiosServices.interceptors.request.use(function (config) {
    config.headers['Content-Type'] = type;
    return config;
  });
};

// interceptor for http
axiosServices.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.status == 401) localStorage.clear();
    Promise.reject((error.response && error.response.data) || 'Wrong Services');
  }
);

export default axiosServices;
