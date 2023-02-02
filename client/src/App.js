import "./App.css";
import reset from "styled-reset";
import { createGlobalStyle } from "styled-components";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import Header from "./components/header/Header";
import Mypage from "./pages/mypage/index";
import Login from "./pages/sign/login";
import ServiceHome from "./components/service/index";
import { Main } from "./pages";
import Cart from "./pages/cart";
import ProductDetail from "./pages/productDetail";
import Collection from "./pages/collection";
import Search from "./pages/search";
import Pay from "./components/pay";
import SuccessPayment from "./components/pay/SuccessPayment";
import FailPayment from "./components/pay/FailPayment";
import Footer from "./components/footer/Footer";
import {AuthProvider, RequireAuth} from "./AuthRoute";


const GlobalStyle = createGlobalStyle`
    ${reset};
    html,body{
        padding: 0;
        margin: 0;
      };


      * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
      
      }

    li{
      list-style:none;
    }

    img{
      border: none;
      vertical-align: top;
    }

  a {
    color: initial;
    text-decoration: none;
  }

  input,
  textarea {
    padding: 0;
    outline: none;
    border: 1px solid #000000;
    border-radius: 0;
    box-shadow: none;
  }

  button {
    padding: 0;
    border: none;
    border-radius: 0;
    box-shadow: none;
    background-color: inherit;
    cursor: pointer;
  }  
`;

function App() {
  return (
    <BrowserRouter>
    <AuthProvider>
      <GlobalStyle />
      <div id="container">
        <div id="header">
          <Header />
        </div>
        <main id="main">
          <Routes>
          <Route path="/mypage/*" element={
              <RequireAuth>
                <Mypage />
              </RequireAuth>
            }/>
            <Route path="/login" element={<Login />} />
            <Route path="/service/*" element={<ServiceHome />} />
            <Route path="/" element={<Main />} />
            <Route path="/cart" element={<Cart />} />
            <Route path="/product/:id" element={<ProductDetail />} />
            <Route path="/collections/:params" element={<Collection />} />
            <Route path="/search" element={<Search />} />
            <Route path="/category/:code" element={<Search />} />
            <Route path="/pay" element={<Pay />} />
            <Route path="/pay/successpage" element={<SuccessPayment />} />
            <Route path="/pay/failpage" element={<FailPayment />} />
          </Routes>
        </main>
        <footer id="footer"></footer>
        <Footer />
      </div>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
