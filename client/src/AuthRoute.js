import * as React from "react";
import {useLocation,Navigate,} from "react-router-dom";

// 전역변수 생성
let AuthContext = React.createContext(null);

// 토큰여부확인
function getLocalStorage(){
    return JSON.parse(localStorage.getItem("token"))?.authorization;
}


// 전역변수 사용방법
// export const useAuth = () => {
//     return React.useContext(AuthContext);
// };


// 해당 컴포넌트안의 모든 자식들을 체크한다
export function RequireAuth({ children }) {
    let auth = React.useContext(AuthContext);
    let location = useLocation();

    if (!auth.isLogin()) {
      return <Navigate to="/login" state={{ from: location }} replace />;
    }
    return children;
}

// 해당 컴포넌트안의 모든 자식들을 체크한다
export function AuthProvider({ children }) {

    // 로그인여부 : 토큰있나없나
  let isLogin = () => {
    return getLocalStorage() !== undefined;
  };

  let value = { isLogin };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}







