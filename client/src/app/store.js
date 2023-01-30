import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import productsInfoReducer from "./reducer/productId2Pay";
import storage from "redux-persist/lib/storage";
import { persistReducer } from "redux-persist";

const persistConfig = {
  key: "root",
  storage,
};

const persistedReducer = persistReducer(persistConfig, productsInfoReducer);

export const store = configureStore({
  reducer: {
    productsInfo: persistedReducer,
  },
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  }),
});
