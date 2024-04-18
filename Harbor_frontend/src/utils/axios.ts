/**
 * axios setup to use mock service
 */

import axios from 'axios';
const token = localStorage.getItem('token');

const axiosServices = axios.create({
  baseURL: 'http://localhost:3000',
  headers: {
    'Authorization': `Bearer ${ token }`,
    'Content-Type': 'application/json'
  },
});


axiosServices.interceptors.request.use()

// interceptor for http
axiosServices.interceptors.response.use(
  (response) => response,
  (error) => Promise.reject((error.response && error.response.data) || 'Wrong Services')
);

export default axiosServices;
