import {setErrorMessage} from "./features/auth/authSlice";
import {setError} from "./features/form/formSlice";
import {setRows} from "./features/rows/tableSlice";


export function addAccessTokenToLocalStorage(token){
    window.localStorage.setItem("access-jwt-token", token)
}
export const serverPath = "http://localhost:8080"
export function addRefreshTokenToLocalStorage(token){
    window.localStorage.setItem("refresh-jwt-token", token)
}
export function addLoginToLocalStorage(login){
    window.localStorage.setItem("login", login)
}

export async function tryToGenNewToken(){
    let booling = false;
    await validateAccessToken().then( async (result) => {
        if (result) {
            booling = true
        }
        else{
            await refreshAccessToken().then(result => {
                booling = result
            })
        }
        }).catch(async ()=>

        await refreshAccessToken().then(result => {
            booling = result
        })
    )
    return booling;

}
export async function validateAccessToken(){

    let accessToken = window.localStorage.getItem("access-jwt-token")
    let booling = false;
    console.log(accessToken)
    if (!accessToken){

        return false;
    }

    await fetch(serverPath+"/api/validateAccessToken?accessToken=" + accessToken, {
        method:"GET",
    }).then(
        async (result)=>{
            if(result.ok){
                booling =true;
            }
        }
    ).catch(()=>{
        booling=false;
    })

    return booling;
}
export function goToLogin() {
    window.location.replace("/login");
}
export function goToMain() {
    window.location.replace("/");
}
export async function refreshAccessToken(){
    let accessToken = window.localStorage.getItem("refresh-jwt-token")
    let login = window.localStorage.getItem("login")
    let isHaveAccess = false;
    if (!accessToken){
        return false;

    }

    await fetch(serverPath+"/api/refreshToken?refreshToken=" + accessToken + "&login=" + login, {
        method:"GET",

    }).then(
        async (result)=>{

            if(result.ok){
                await result.text().then(
                    (text) => {
                        console.log("aboba")
                        addAccessTokenToLocalStorage(JSON.parse(text).accessToken)
                        addRefreshTokenToLocalStorage(JSON.parse(text).refreshToken)
                        isHaveAccess =  true

                    }
                )
            }
            isHaveAccess=false;
        }
    ).catch(()=>{
        isHaveAccess=false;
        }
    )
    return isHaveAccess
}

export async function sendDot(dispatch, x, y, r){
    await fetch(serverPath + "/api/dots/newDot" , {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": window.localStorage.getItem("access-jwt-token")
        },
        body: JSON.stringify({ x: x, y: y, r: r, userName:window.localStorage.getItem("login") })
    }).then((res)=>{
        if(res.ok){
            getDots(dispatch);
        }
    })
        .catch(()=>{
            if (tryToGenNewToken()) {
                console.log("aboba")
                sendDot(dispatch, x, y, r)

            } else {
                goToLogin()
                dispatch(setErrorMessage("Your JWT token expired, please authorize"))
            }



    })
}
export async function getDots(dispatch){
    fetch(serverPath + "/api/dots" , {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": window.localStorage.getItem("access-jwt-token")
        },
    })
        .then(
            (result) => {
                if (result.ok) {

                    result.text().then(
                        (text) => { dispatch(setRows(JSON.parse(text))); }

                    );
                }

            })
        .catch(() => {

                if (tryToGenNewToken()) {
                    getDots(dispatch)
                } else {
                    goToLogin()
                    dispatch(setErrorMessage("Your JWT token expired, please authorize"))
                }

        });
}
export async function clear(dispatch){
    fetch(serverPath + "/api/dots/clear" , {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": window.localStorage.getItem("access-jwt-token")
        },
        body : window.localStorage.getItem("login")
    })
        .then((res)=>{
            if(res.ok){
                console.log("aboba")
                getDots(dispatch);
            }
        })
        .catch(() => {

            if (tryToGenNewToken()) {
                getDots(dispatch)
            } else {
                goToLogin()
                dispatch(setErrorMessage("Your JWT token expired, please authorize"))
            }

        });
}