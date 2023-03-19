import Form from "react-bootstrap/Form";
import {setLogin} from "../features/auth/authSlice";
import {useDispatch, useSelector} from "react-redux";
import {selectY, setY} from "../features/form/formSlice";

let inputError=""
function YInputField(){
    const y = useSelector(selectY)
    const dispatch = useDispatch()
    return(
        <Form.Group className="mb-3" controlId="validationCustomUsername">
            <Form.Label>Y input:</Form.Label>
            <Form.Control
                onChange={(value)=> {
                    dispatch(setY(clearInput(value.target.value)))
                } }
                value ={y}
                type="text"
                placeholder="Enter Y coordinate"
                isInvalid={!checkValidY(y)}
            />
            <Form.Control.Feedback type="invalid">
                {inputError}
            </Form.Control.Feedback>
        </Form.Group>
    )
}
function checkValidY(y){
    if  (y===undefined){
        inputError="Y is a number"
        return false;
    }
    if(isNaN(y)){
        inputError="Y is a number"
        return false;
    }
    if(y===""){
        inputError="Y is a number"
        return false;
    }
    if(y>=5||y<-5){
        inputError="Y must be in (-5;5)"
        return false;
    }
    return true;
}
function clearInput(sendingValue) {
    return sendingValue
        .replace(',', '.')
        .replace(' ', "")
        .replace('\t', "")
        .replace('\n', "")
        .replace('\r', "");
}
export default YInputField