import { combineReducers } from 'redux';
import login from "./loginReducer";
import counter from "./counterReducer";
import routerReducer from 'react-router-redux'

export default combineReducers({
     login,
     counter
});