// import {createSlice} from "@reduxjs/toolkit";
//
// const initialState = [
//     { username : null },
//     { password : null }
// ]
//
//
// const LoginSlice = createSlice({
//     name : 'login',
//     initialState : initialState,
//     reducers : {
//         // Push will not mutate inside createSlice (Immer.js)
//         submitHandler(state, action) {
//             state.push(action.payload)
//         }
//     }
// });
// export const selectLogin = (state) => state.login;
//
// export const { submitHandler } = LoginSlice.actions;
//
// export default LoginSlice.reducer;
