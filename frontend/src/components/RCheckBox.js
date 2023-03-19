import {Form} from "react-bootstrap";
import {useDispatch, useSelector} from "react-redux";
import {selectR, setR} from "../features/form/formSlice";

function RChecks() {
    const dispatch = useDispatch()
    const xValue = useSelector(selectR)
    return (

            <div key={'inline-checkbox'}  className="mb-3" color="white">
                {[-2,-1.5,-1,-0.5,0,1,1.5,2].map((x) =>(

                    <Form.Check
                        inline
                        key={x}
                        type="checkbox"
                        id={x}
                        label={x}
                        checked={xValue===x}
                        onChange={() => dispatch(setR(x))}
                    />

                ))}
            </div>


    );
}
export default RChecks