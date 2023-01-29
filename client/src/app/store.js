import { configureStore } from "@reduxjs/toolkit";
import productsInfoReducer from "./reducer/productId2Pay";

export const store = configureStore({
  reducer: {
    productsInfo: productsInfoReducer,
  },
});
