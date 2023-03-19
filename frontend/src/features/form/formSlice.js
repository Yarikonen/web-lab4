import {createSlice} from "@reduxjs/toolkit";
const initialState = {
    x:undefined,
    y:undefined,
    r:1,
    errorMessage : "",
    isTable : false
}


const formSlice= createSlice({
        name: "formSlice",
        initialState,
        reducers: {
            setX: (state, action)=>{
                state.x = action.payload;
            },
            setY: (state, action)=>{
                state.y = action.payload;
            },
            setR: (state, action)=>{
                state.r = action.payload;
            },
            setError: (state, action)=>{
                state.errorMessage=action.payload;
            },
            setIsTable: (state, action)=>{
                state.isTable=action.payload
            }
        }
    }

)

export const {setX, setY, setR, setIsTable, setError} = formSlice.actions

export const selectX = (state) => state.form.x;
export const selectY = (state) => state.form.y;
export const selectR = (state) => state.form.r;
export const selectError = (state) => state.form.errorMessage
export const selectIsTable = (state) => state.form.isTable

export default formSlice.reducer;