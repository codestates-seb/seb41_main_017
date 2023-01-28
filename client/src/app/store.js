import { configureStore } from "@reduxjs/toolkit";
import productIdsReducer from "./reducer/productId2Pay";

export const store = configureStore({
  reducer: {
    productIds: productIdsReducer,
  },
});
