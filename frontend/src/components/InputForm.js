import XCheckBox from "./XCheckBox";
import RChecks from "./RCheckBox";
import {Card, Form, Nav, Stack} from "react-bootstrap";

import {useDispatch, useSelector} from "react-redux";

import {selectError, selectIsTable, selectR, selectX, selectY, setIsTable} from "../features/form/formSlice";
import Graph from "./Graph";
import TableR from "./Table";
import InputForm2 from "./InputForm2";



function InputForm(){

    const isTable = useSelector(selectIsTable)
    const dispatch = useDispatch()



        return(
            <Card id = "formCard" className="mx-auto my-2%" style={{width:"652px"}}>
                <Card.Header>
                    <Nav variant="tabs" defaultActiveKey="#first">
                        <Nav.Item >
                            <Nav.Link onClick={()=>

                                dispatch(setIsTable(false))} href="#graph">Graph</Nav.Link>
                        </Nav.Item>
                        <Nav.Item >
                            <Nav.Link onClick={()=>dispatch(setIsTable(true))} href="#table">Table</Nav.Link>
                        </Nav.Item>
                    </Nav>

                </Card.Header>
                {isTable&&
                    <TableR/>}
                {!isTable&&<Graph/>}



                <InputForm2/>
            </Card>


        )



}
export default InputForm