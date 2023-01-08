import styled from "styled-components";
import { GoChevronRight } from "react-icons/go";
import { Route, Routes, BrowserRouter, NavLink, Link } from "react-router-dom";
import Userinfo from "./userinfo";
import DeliverySet from "./deliverySet";
import DeliveryLook from "./deliveryLook";
import Orderitem from "./orderitem";
import Coupon from "./coupon";
import Point from "./point";
import Buyitem from "./buyItem";
import Selectitem from "./selectItem";
import Itemreview from "./itemreview";
import Inquiry from "./inquiry";

const Layout = styled.div`


.colbtn{
  display:flex;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;
  border: 1px solid #ff6767;
  border-radius: 10px;
  color: #ff6767;
  cursor: pointer;
  font-size: 12px;
  padding: 5px 8px 3px 8px;
  
}
`
const Mycard = styled.div`
  // background-color: #ffc3b1;
  background-color: rgb(255,224,214);
  // 부모와 맞게 수정
  width: 1050px;
  margin: 0 auto;
  display: flex;
  text-align: center;
  margin-top: 15px;

  .accountBox {
    padding: 10px 0 0 30px;
    flex-basis: 300px;
    height: 212px;
    display: flex;
    flex-direction: column;

    .acName {
      display: flex;
      gap: 10px;
      margin: 15px 0 35px 0;
      span {
        font-size: 24px;
      }
    }

    .acEvent {
      justify-content: left;
      background-color: #fff7f5;
      width: 195px;
      padding: 10px;
      border-radius: 10px;

      .event {
        margin: 10px;

        a{
          cursor: pointer;
          display: flex;
          align-items: center;
          justify-content: space-between;
        }

        & span:last-child {
          color: red;
        }
      }
    }
  }

  .product {
    display: flex;
    flex: 1;
    justify-content: space-evenly;
    align-items: center;

    & > div{
      display:flex;
      flex-direction: column;
      align-items: center;
    }

    .count{
      color: white;
      font-size: 50px;
      margin: 20px 0;
    }
  }

`;

const Tap = styled.div`
// 부모와 맞게 수정
  margin: 0 auto;
  width: 1050px;
  text-align: left;
  margin-top: 20px;

  .nav {
      h3{
        font-size: 20px;
      }

      ul{
        display:flex;
        margin-top: 10px;
        gap: 10px;

        li{
        
          flex:1;

        }

      }
    
  }

  .content {
      border-top: 1px solid #D7D7D7;
      margin-top: 10px;
      padding: 10px;
  }

  .active{
    color: white;
    background-color: #FF6767;
  }
`;




// const Colbtn = styled.button`
//   background-color: #ffffff;
//   border: 1px solid #ff6767;
//   border-radius: 10px;
//   color: #ff6767;
//   cursor: pointer;
//   font-size: 12px;
//   flex-shrink: 0;
//   position: relative;
//   overflow: hidden;

//   a{
//     padding: 3px 8px;
//     color: #ff6767;
//     position: relative;
//     font-size: 12px;
//     display:block;
//     top: 0;
//     left: 0;
//     bottom: 0;
//     right: 0;
//   }
// `;


function Mypage() {
  const tap = [
    { "내 정보": "/userinfo" },
    { "배송지 설정": "/addressSet" },
    { "배송 조회": "/deliveryLook" },
    { "주문 목록 조회": "/orderitem" },
    { "내 쿠폰": "/coupon" },
    { "내 포인트": "/point" },
    { "찜한 상품": "/selectItem" },
    { "자주 산 상품": "/buyitem" },
    { "작성한 후기": "/itemreview" },
    { "내 문의": "/inquiry" },
    
  
    // 회원카드 옆의 개인정보 수정은 내정보와 같은 페이지인가 ?
    //  -> { "개인 정보 수정": "/wish" }
    // 찜한상품 보기 카테고리가 없음
    // -> { "찜한 상품": "/itemreview" }
    // 카테고리에 맞는 파일들을 만들어야함 ~~~~
    // [삭제한파일들]
    /* 
    - item

    */
  ];

  return (
    <BrowserRouter>
      <Layout>
        <Mycard>
          <div className="accountBox">
            <div className="acName">
              <span>ID님의 회원카드</span>
              {/* <Link to='/userinfo' className="colbtn">내 정보 수정</Link> */}
              <a href='/userinfo' className="colbtn">내 정보 수정</a>
            </div>
            <div className="acEvent">
              <div className="event">
                <Link to='/point'>
                <span>내 포인트</span>
                <span>0P<GoChevronRight /></span>
                </Link>
              </div>
              <div className="event">
                <Link to='/coupon'>
                <span>내 쿠폰</span>
                <span>0장<GoChevronRight /></span>
                </Link>              
              </div>
            </div>
          </div>
          <div className="product">
            <div>
              <h2>배송중</h2>
              <div className="count">
                <span>0</span>
              </div>
              {/* <Link to='/deliveryLook' className="colbtn">배송조회</Link> */}
              <a href="/deliveryLook" className="colbtn">배송조회</a>
            </div>
            <div>
              <h2>주문 목록</h2>
              <div className="count">
                <span>0</span>
              </div>
              {/* <Link to='/orderitem' className="colbtn">주문 목록 조회</Link> */}
              <a href="/orderitem" className="colbtn">주문 목록 조회</a>
            </div>
            <div>
              <h2>찜한 상품</h2>
              <div className="count">
                <span>0</span>
              </div>
              {/* <Link to='/serachItem' className="colbtn">조회</Link> */}
              <a href="/serachItem" className="colbtn">조회</a>
            </div>
            <div>
              <h2>자주 산 상품</h2>
              <div className="count">
                <span>0</span>
              </div>
              {/* <Link to='/buyitem' className="colbtn">조회</Link> */}
              <a href="/buyitem" className="colbtn">조회</a>
            </div>
          </div>
        </Mycard>
        <Tap>
          <div className="nav">
            <h3>마이페이지</h3>
            <ul>
              {tap.map((el, idx) => {
                console.log(el)
                return (
                <li key={idx}>
                  {/* <Colbtn>
                    <NavLink 
                    to={Object.values(el)[0]}
                    className={({isActive})=> (isActive) ? "active" : ""}>
                      {Object.keys(el)}
                    </NavLink>
                  </Colbtn> */}
                    <NavLink 
                    to={Object.values(el)[0]}
                    className={`colbtn ${({isActive})=> (isActive) ? "active" : ""}`}>
                      {Object.keys(el)}
                    </NavLink>
                </li>
                );
              })}
            </ul>
          </div>
          <div className="content">
            <Routes>
              <Route path="/userinfo" element={<Userinfo />} />
              <Route path="/addressSet" element={<DeliverySet />} />
              <Route path="/deliveryLook" element={<DeliveryLook />} />
              <Route path="/orderitem" element={<Orderitem />} />
              <Route path="/coupon" element={<Coupon />} />
              <Route path="/point" element={<Point />} />
              <Route path="/buyitem" element={<Buyitem />} />
              <Route path="/selectItem" element={<Selectitem />} />
              <Route path="/itemreview" element={<Itemreview />} />
              <Route path="/inquiry" element={<Inquiry />} />
            </Routes>
          </div>
        </Tap>
      </Layout>
    </BrowserRouter>
  );
}

export default Mypage;
