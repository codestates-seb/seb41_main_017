import { createSlice } from "@reduxjs/toolkit";

export const productIdsSlice = createSlice({
  name: "productIds",
  initialState: { ids: [] },
  reducers: {
    setProductIds: (state, action) => {
      state.ids = action.payload;
    },
  },
});

export const { setProductIds } = productIdsSlice.actions;

export default productIdsSlice.reducer;
