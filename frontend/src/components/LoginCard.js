import {setToLogin} from "../features/auth/authSlice";
import {useDispatch} from "react-redux";
import {Card, Nav} from "react-bootstrap";
import AuthField from "./AuthField";


function Login(){

    const dispatch = useDispatch()
    return (
        <Card id="loginCard" className="mx-auto my-2%"  style= {{width:'35%', height:"80%", background:"#F8F8FF"  } }>

            <Card.Img src= "https://sun9-39.userapi.com/impg/OaXfr5I1b-xK7Dh_UNh5P94b_bjDaTKqHZ9cXg/WK4yKKi_zzg.jpg?size=811x811&quality=96&sign=b2ab6b6671bbf9551b2b0448ae4c870d&type=album"/>
            <Card.Header>
                <Nav justify={true} variant="pills" defaultActiveKey="#first" >
                    <Nav.Item onClick={()=>dispatch(setToLogin(true))}>
                        <Nav.Link href="#first" >Login</Nav.Link>
                    </Nav.Item>
                    <Nav.Item onClick={()=>dispatch(setToLogin(false))}>
                        <Nav.Link href="#link" >Registration</Nav.Link>
                    </Nav.Item>
                </Nav>
            </Card.Header>
            <Card.Body>
                <AuthField/>
            </Card.Body>
            <Card.Footer  className="text-center">
                <a href="t.me/yarikonen" className="link-dark">Author</a>
            </Card.Footer>

        </Card>



    )


}
export default Login