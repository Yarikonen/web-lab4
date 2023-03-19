import { configureStore } from '@reduxjs/toolkit';
import formSlice from "../features/form/formSlice";
import tableSlice from "../features/rows/tableSlice";
import authSlice from "../features/auth/authSlice";
export const store = configureStore({
  reducer: {
    form: formSlice,
    auth: authSlice,
    table:tableSlice
  },
});
