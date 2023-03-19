import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    rows:[]
};

const tableSlice = createSlice({
    name: "tableSlice",
    initialState,
    reducers:{
        setRows: (state, action) => {
            state.rows = action.payload;
        },
        clearRows: (state) => {
            state.rows = [];
        },
        addRow: (state, action) => {
            state.rows.push(action.payload);
        }
    }
})
export const {setRows, clearRows, addRow} = tableSlice.actions;

export const selectRows = (state) => state.table.rows;

export default tableSlice.reducer;