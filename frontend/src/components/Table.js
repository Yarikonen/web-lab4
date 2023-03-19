import Table from 'react-bootstrap/Table';
import {shallowEqual, useDispatch, useSelector} from "react-redux";

import {selectRows} from "../features/rows/tableSlice";

function TableR() {
    const dots = useSelector(selectRows, shallowEqual)
    console.log(window.localStorage.getItem("login"))
    return (
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>#</th>
                <th>Birth time</th>
                <th>Execution time</th>
                <th>Status</th>
                <th>Owner</th>
                <th>X</th>
                <th>Y</th>
                <th>R</th>

            </tr>
            </thead>
            <tbody>
            {dots.map(x=>(
                <tr style={{backgroundColor:x.userName===window.localStorage.getItem("login") ? "#e3a1b370" : "none" }}>
                    <td>{x.id}</td>
                    <td>{x.birthTime}</td>
                    <td>{x.exTime}</td>
                    <td>{x.hit}</td>
                    <td> {x.userName} </td>
                    <td>{x.x}</td>
                    <td>{x.y}</td>
                    <td>{x.r}</td>

                </tr>
            ))}
            </tbody>
        </Table>
    );
}

export default TableR;