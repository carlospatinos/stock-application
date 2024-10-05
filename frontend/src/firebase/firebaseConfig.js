import { initializeApp } from "firebase/app";

import { getMessaging } from "firebase/messaging";

console.log("REACT_APP_API_KEY: " + process.env.REACT_APP_API_KEY);
//Firebase Config values imported from .env file
const firebaseConfig = {
    apiKey: process.env.REACT_APP_API_KEY,
    authDomain: process.env.REACT_APP_AUTH_DOMAIN,
    projectId: process.env.REACT_APP_PROJECT_ID,
    storageBucket: process.env.REACT_APP_STORAGE_BUCKET,
    messagingSenderId: process.env.REACT_APP_MESSAGING_SENDER_ID,
    appId: process.env.REACT_APP_ID,
    // measurementId: process.env.REACT_APP_MEASUREMENT_ID,
};

// const firebaseConfig = {
//     apiKey: "AIzaSyCoxklwgqyeOFXmhtX6pNV0GHNf2WvXJkU",
//     authDomain: "frontend-pushapp.firebaseapp.com",
//     projectId: "frontend-pushapp",
//     storageBucket: "frontend-pushapp.appspot.com",
//     messagingSenderId: "158124670011",
//     appId: "1:158124670011:web:c29c89ca6ee234ebee701e",
//     // measurementId: process.env.REACT_APP_APP_MEASUREMENT_ID,
// };

console.log(firebaseConfig);

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Messaging service
export const messaging = getMessaging(app);
