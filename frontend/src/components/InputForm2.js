import {useDispatch, useSelector} from "react-redux";
import {selectError, selectIsTable, selectR, selectX, selectY, setError} from "../features/form/formSlice";
import XCheckBox from "./XCheckBox";
import RChecks from "./RCheckBox";
import YInputField from "./YInputField";
import Button from "react-bootstrap/Button";
import {clear, getDots, sendDot} from "../utils";
import AlertDismissible from "./Alert";
import {Form} from "react-bootstrap";

function InputForm2() {

    const x = useSelector(selectX)
    const y = useSelector(selectY)
    const r = useSelector(selectR)
    const dispatch = useDispatch()
    getDots(dispatch);

    return(
        <Form>
            X inputs:
            <XCheckBox/>
            R inputs:
            <RChecks/>
            <YInputField/>
            <Button style={{width:"30%"}} type = "submit" variant="primary" onClick={(e) => { e.preventDefault(); sendDot(dispatch,x,y,r);}} >Send</Button>
            <Button style={{width:"30%"}} type = "submit" variant="outline-primary" onClick={(e) => { e.preventDefault(); clear(dispatch); }} >Clear</Button>
            <AlertDismissible error={selectError} errorSet={()=>dispatch(setError(null))} />
        </Form>
    )
}

export default InputForm2