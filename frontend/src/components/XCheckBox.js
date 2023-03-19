import {Form} from "react-bootstrap";
import {useDispatch, useSelector} from "react-redux";
import {selectX, setX} from "../features/form/formSlice";

function XChecks() {
    const dispatch = useDispatch()
    const xValue = useSelector(selectX)
    return (

            <div key={'reverse-checkbox inline'}  className="mb-3" color="white">
            {[-2,-1.5,-1,-0.5,0,1,1.5,2].map((x) =>(

                <Form.Check
                    inline
                    key={x}
                    type="checkbox"
                    id={x}
                    label={x}
                    checked={xValue===x}
                    onChange={() => dispatch(setX(x))}
                />

            ))}
            </div>


    );
}
export default XChecks