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

export default productIdsSlice.reducer;
