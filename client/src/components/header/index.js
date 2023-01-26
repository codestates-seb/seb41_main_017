import LoginHeader from "./LoginHeader";
import LogoutHeader from "./LogoutHeader";
function Header() {
  const isLogin = localStorage.getItem("token");

  return <>{isLogin ? <LoginHeader /> : <LogoutHeader />}</>;
}

export default Header;
