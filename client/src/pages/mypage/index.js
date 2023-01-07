import styled from "styled-components";
import { GoChevronRight } from "react-icons/go";
import { Route, Routes, BrowserRouter, Link, NavLink} from "react-router-dom";
import Itemreview from "./itemreview";
import Orderitem from "./orderitem";
import ProductInquire from "./productInquire";
import Userinfo from "./userinfo";
import UserinfoEdit from "./userinfoEdit";
import Wish from "./wish";

const Layout = styled.div`
  margin: 0 auto;
  // 수정해야할부분 부모크기에 맞게
  width: 1050px;
  display: flex;
  gap: 20px;

  .nav {
    flex: 1;
    h3 {
      padding: 20px;
    }
    ul {
      border: 1px solid rgb(242, 242, 242);
    }
    a {
      display: flex;
      justify-content: space-between;
      font-weight: bold;
      align-items: center;
      padding: 10px;
      border-bottom: 1px solid rgb(242, 242, 242);
      margin-bottom: -1px;
      cursor: pointer;
      color: rgb(102, 102, 90);
      
    }
    .clickCo{
      background-color: rgb(250, 250, 250);
      color:rgb(95, 0, 128);
    }
  }

  a:hover,
  .light:hover{  
    color:rgb(95, 0, 128);
    background-color: rgb(250, 250, 250);
  }
  
  .content {
    flex: 4;
    padding-top:20px;
  }

`;



function Mypage() {
  const tap = [
    {"주문 내역":"/orderitem"},
    {"찜한 상품":"/itemreview"},
    {"상품 후기":"/productInquire"},
    {"상품 문의":"/userinfo"},
    {"유저 정보":"/userinfoEdit"},
    {"개인 정보 수정":"/wish"}
  ]

  return (
    <BrowserRouter>
      <Layout>
        <div className="nav">
          <h3>마이컬리</h3>
          <ul>
            {tap.map((el, idx)=>{
              return(
                <li key={idx}>
                  <NavLink 
                  to={Object.values(el)[0]}
                  className={({isActive}) => (isActive ? "clickCo" : null)}
                  >{Object.keys(el)}<GoChevronRight className="light" /></NavLink>
                </li>
              );
            })}
          </ul>
        </div>
        <div className="content">
          <Routes>
            {/* <Route path={tap[0]["주문내역"]} element={<Orderitem />} /> */}
            <Route path="/orderitem" element={<Orderitem />} />
            <Route path="/itemreview" element={<Itemreview />} />
            <Route path="/productInquire" element={<ProductInquire />} />
            <Route path="/userinfo" element={<Userinfo />} />
            <Route path="/userinfoEdit" element={<UserinfoEdit />} />
            <Route path="/wish" element={<Wish />} />
          </Routes>
        </div>
      </Layout>
    </BrowserRouter>
  );
}

export default Mypage;
