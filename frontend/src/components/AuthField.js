import Form from 'react-bootstrap/Form';

import {
    selectErrorMessage,
    selectIsLogin,
    selectLogin,
    selectPassword,
    selectRepeatedPassword, setErrorMessage,
    setLogin,
    setPassword,
    setRepeatedPassword
} from "../features/auth/authSlice";
import {useSelector, useDispatch} from "react-redux";
import {Alert, Button, InputGroup, Stack} from "react-bootstrap";
import AlertDismissible from "./Alert";
import {
    addAccessTokenToLocalStorage,
    addLoginToLocalStorage,
    addRefreshTokenToLocalStorage, goToMain,
    serverPath
} from "../utils";

let loginError =""
let passwordError=""
let logininvalid=false
function AuthField() {
    const login = useSelector(selectLogin)
    const password = useSelector(selectPassword)
    const repeatedPassword = useSelector(selectRepeatedPassword)
    const isLogin = useSelector(selectIsLogin)
    const dispatch = useDispatch()
    const validateFields= () => {
        if(!isLoginCorrect(login)||!isPasswordCorrect(password)){
            return false;
        }
        return true;
    }
    const getTokenOnLogin = (e) => {
        e.preventDefault();
        if(!validateFields())
        {
            return
        }
        console.log(login, password)
        fetch( serverPath+ "/api/login" , {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ login: login, password: password })
        })
            .then((result)=>{
                if(result.ok){
                    result.text().then(
                        text=>{
                            addAccessTokenToLocalStorage(JSON.parse(text).accessToken)
                            addRefreshTokenToLocalStorage(JSON.parse(text).refreshToken)
                            addLoginToLocalStorage(login)
                            goToMain()
                        }

                    )
                }
                else{
                    return result.text().then(
                        text=>{
                            dispatch(setErrorMessage(JSON.parse(text).message))
                        }
                    )
                }
            } )
    }
    const getTokenOnRegister = (e)=> {
        e.preventDefault();
        if(!validateFields()||password!==repeatedPassword)
        {
            return
        }
        fetch( serverPath+ "/api/register" , {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ login: login, password: password })
        })
            .then((result)=>{
                if(result.ok){
                    result.text().then(
                        text=>{
                            addAccessTokenToLocalStorage(JSON.parse(text).accessToken)
                            addRefreshTokenToLocalStorage(JSON.parse(text).refreshToken)
                            addLoginToLocalStorage(login)
                        }

                    )
                }
                else{
                    return result.text().then(
                        text=>{
                            dispatch(setErrorMessage(JSON.parse(text).message))
                        }
                    )
                }
            } )
    }


        return(
            <Form>
                <Form.Group className="mb-3" controlId="validationCustomUsername">
                    <Form.Label>Login</Form.Label>
                    <Form.Control
                        onChange={(value)=> {
                            dispatch(setLogin(value.target.value))
                        } }
                        value ={login}
                        type="text"
                        placeholder="Enter login"
                        isInvalid={!isLoginCorrect(login)}
                    />
                    <Form.Control.Feedback type="invalid">
                        {loginError}
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className="mb-3" controlId="validationCustom02">
                    <Form.Label>Password</Form.Label>

                    <Form.Control
                        type="password"
                        placeholder="Password"
                        onChange={(value)=> {
                            dispatch(setPassword(value.target.value))
                        } }
                        isInvalid={!isPasswordCorrect(password)}
                    />
                    <Form.Control.Feedback type="invalid">
                        {loginError}
                    </Form.Control.Feedback>
                </Form.Group>
                    {!isLogin&&
                        <Form.Group className="mb-3" controlId="validationCustom02">
                        <Form.Label>Confirm your password</Form.Label>

                        <Form.Control
                            type="password"
                            placeholder="Repeat your password"
                            onChange={(value)=> {
                                dispatch(setRepeatedPassword(value.target.value))
                            } }
                            isInvalid={!isPasswordCorrect(password)||password!==repeatedPassword}
                        />
                        <Form.Control.Feedback type="invalid">
                            Passwords must be the same
                        </Form.Control.Feedback>
                        </Form.Group>
                    }

                <Stack gap={2} >
                    <Button type = "submit" variant="primary"

                            onClick={(e)=>{
                        if(isLogin){
                            getTokenOnLogin(e)
                        }
                        else{
                            getTokenOnRegister(e)
                        }
                    } } >{isLogin ? "Login" : "Register" } </Button>
                </Stack>
                <AlertDismissible error={(selectErrorMessage)} errorSet={() => dispatch(setErrorMessage(null))}/>
            </Form>
        )





}

function isLoginCorrect(str){
    if(str===undefined || str.length===0){
        loginError = "Your login must contain at least 1 symbol"
        return false
    }

    return true

}
function isPasswordCorrect(string){
    if (string===undefined || string.length<6){
        passwordError = "Password must contain at least 6 symbols"
        return false
    }
    else{
        return true
    }
}


    export default AuthField