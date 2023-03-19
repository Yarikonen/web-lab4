import {createSlice} from "@reduxjs/toolkit";
const initialState = {
    login: undefined,
    password: undefined,
    repeatedPassword: undefined,
    isLogin: true,
    errorMessage: undefined

}

const authSlice = createSlice(
    {
        name: "authSlice",
        initialState,
        reducers:{
            setLogin: (state, action)=> {
                state.login=action.payload
            },
            setPassword: (state, action)=> {
                state.password=action.payload
            },
            setRepeatedPassword: (state, action)=>{
                state.repeatedPassword=action.payload
            },
            setToLogin:(state, action)=>{
                state.isLogin=action.payload
            },
            setErrorMessage:(state, action)=>{
                state.errorMessage=action.payload
            }


        }
    }
)

export const selectLogin = (state) => (state.auth.login)
export const selectPassword = (state) => (state.auth.password)
export const selectRepeatedPassword = (state) => (state.auth.repeatedPassword)
export const selectIsLogin = (state) => (state.auth.isLogin)
export const selectErrorMessage = (state) => (state.auth.errorMessage)

export const {setLogin, setPassword, setRepeatedPassword, setToLogin, setErrorMessage} = authSlice.actions

export default authSlice.reducer