import React, { useState } from 'react';
import Alert from 'react-bootstrap/Alert';
import Button from 'react-bootstrap/Button';
import {render} from "react-dom";
import {useDispatch, useSelector} from "react-redux";
import {selectErrorMessage, setErrorMessage} from "../features/auth/authSlice";

function AlertDismissible(props) {
    const error = useSelector(props.error)
    if (error) {
        return (
            <Alert variant="danger" className="small" onClose={props.errorSet} dismissible>
                <Alert.Heading> {error} </Alert.Heading>
            </Alert>
        );
    }

}

export default AlertDismissible
