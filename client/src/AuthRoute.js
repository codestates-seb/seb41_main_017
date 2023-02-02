import * as React from "react";
import {useLocation,Navigate,} from "react-router-dom";


let AuthContext = React.createContext(null);
function getLocalStorage(){
    return JSON.parse(localStorage.getItem("token"))?.authorization;
}

export function RequireAuth({ children }) {
    let auth = React.useContext(AuthContext);
    let location = useLocation();

    if (!auth.isLogin()) {
      return <Navigate to="/login" state={{ from: location }} replace />;
    }
    return children;
}

export function AuthProvider({ children }) {
  let isLogin = () => {
    return getLocalStorage() !== undefined;
  };
  let value = { isLogin };
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}







