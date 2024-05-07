/**
 * axios setup to use mock service
 */

import axios from 'axios';

const axiosServices = axios.create();

export const setClientHeaders = () => {
  const token = localStorage.getItem('token');
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
