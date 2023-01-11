import "./App.css";
import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";
import Header from "./components/Header";
import Mypage from "./pages/mypage/index";
import Login from "./pages/sign/login";

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
  // const calculator = ["AC","+/-","%","÷",7,8,9,"X",4,5,6,"-",1,2,3,"+",0,".","="];

  return (
    <>
      <GlobalStyle />
      <Header />
      {/* <Mypage /> */}
      <Login></Login>
    </>
  );
}

export default App;
