import { createSlice } from "@reduxjs/toolkit";

export const productsInfoSlice = createSlice({
  name: "productsInfo",
  initialState: { info: { ids: [], totalPrice: 0 } },
  reducers: {
    setInfo: (state, action) => {
      state.info = action.payload;
    },
  },
});

export const { setInfo } = productsInfoSlice.actions;

export default productsInfoSlice.reducer;
