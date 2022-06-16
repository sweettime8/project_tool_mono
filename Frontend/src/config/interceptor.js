import axios from 'axios';
import * as CONFIG from "@/config/index";
import { Message } from 'element-ui';
import Vue from 'vue'
// Create axios instance
const service = axios.create({
    baseURL: CONFIG.API_URL,
    timeout: 10000,   //request time out
})

// Request intercepter
service.interceptors.request.use(
    config => {
        const token = Vue.$cookies.get('accessToken');
        if (token) {
            config.headers['Authorization'] = 'Bearer ' + token; // Set JWT token
        }
        return config;
    },
    error => {
        // Do something with request error
        console.log(error);
        Promise.reject(error);
    }
)

// response pre-processing
service.interceptors.response.use(
    response => {            
      return response;
    },
    error => {
      let message = error.message;
      if (error.response.data && error.response.data.errors) {
        message = error.response.data.errors;
      } else if (error.response.data && error.response.data.error) {
        message = error.response.data.error;
      }
  
      Message({
        message: message,
        type: 'error',
        duration: 5 * 1000,
      });
      return Promise.reject(error);
    }
  );
  
  export default service;