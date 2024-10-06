import React, { SyntheticEvent, useState, useRef } from "react";
import logo from '../logo.svg';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

interface UserLoginProps {
    onUserLogin: (username: string) => void
}

function UserLogin({ onUserLogin }: UserLoginProps) {
    const [userName, setUserName] = useState('');
    const usernameRef = useRef();

    const userLogin = (event: SyntheticEvent) => {
        event.preventDefault();
        onUserLogin(userName);
    }

    return (<header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div className="login-wrapper">
            <h1>Welcome to Stock App</h1>
            <Form onSubmit={userLogin}>
                <Form.Group className="mb-3">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" placeholder="Enter username" onChange={(evt) => { setUserName(evt.target.value) }} />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Login
                </Button>
            </Form>


        </div>
    </header>);
}

export default UserLogin;