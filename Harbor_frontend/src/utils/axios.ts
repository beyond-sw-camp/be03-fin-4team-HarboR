/**
 * axios setup to use mock service
 */

import axios from 'axios';

const axiosServices = axios.create();

axiosServices.interceptors.request.use()

export const setClientHeaders = (token: string | null) => {
  axiosServices.interceptors.request.use(function (config) {
    config.headers.Authorization = `Bearer ${token}`;
    return config;
  });
};

// interceptor for http
axiosServices.interceptors.response.use(
  (response) => response,
  (error) => Promise.reject((error.response && error.response.data) || 'Wrong Services')
);

export default axiosServices;
