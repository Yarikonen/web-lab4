import React from 'react';
import { createRoot } from 'react-dom/client';
import { Provider } from 'react-redux';
import { store } from './app/store';
import reportWebVitals from './reportWebVitals';
import './index.css';
import { Route, BrowserRouter, Routes } from 'react-router-dom';
import PrivateRoute from "./components/PrivateRoute";
import LoginCard from "./components/LoginCard";
import {goToLogin, goToMain, tryToGenNewToken} from "./utils";
import App from "./App";

const container = document.getElementById('root');
const root = createRoot(container);

root.render(
  <BrowserRouter>
      <Provider store={store}>
          <Routes>
              <Route path='/login' element={<LoginCard/>} />
              <Route path='/' element={<PrivateRoute element={<App />} accessFunction={tryToGenNewToken} onFail={() => goToLogin()}/>} />





          </Routes>



      </Provider>

  </BrowserRouter>
);



// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
