import styled from "styled-components";
import { GoChevronRight } from "react-icons/go";
import { Route, Routes, BrowserRouter, NavLink } from "react-router-dom";
import Itemreview from "./itemreview";
import Orderitem from "./orderitem";
import ProductInquire from "./productInquire";
import Userinfo from "./userinfo";
import UserinfoEdit from "./userinfoEdit";
import Wish from "./wish";

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
      // button{
      //   width: 77px;
      // }
    }

    .acEvent {
      justify-content: left;
      background-color: #fff7f5;
      width: 195px;
      padding: 10px;
      border-radius: 10px;

      .event {
        margin: 10px;
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: space-between;

        & span:last-child {
          color: red;
        }
      }
    }
  }

  .product {
    display: flex;
    flex: 1;
    align-items: center;

    .count{
      color: white;
      font-size: 50px;
      margin: 20px 0;
    }

    & > div {
      flex: 1;
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

      li{
        flex:1;
        display:flex;

        button{
          flex:1;
          margin: 0 2px;
        }

      }
      
    }
    
  }

  .content {
    border-top: 1px solid #D7D7D7;
    margin-top: 10px;
    padding: 10px;
  }
`;

const Colbtn = styled.button`
  background-color: #ffffff;
  border: 1px solid #ff6767;
  border-radius: 10px;
  padding: 4px 8px;
  // width: 100%;
  color: #ff6767;
  cursor: pointer;
  font-size: 12px;
  flex-shrink: 0;

  a{
    color: #ff6767;
  }
`;

function Mypage() {
  const tap = [
    { "내 정보": "/userinfoEdit" },
    { "배송지 설정": "#" },
    { "배송 조회": "#" },
    { "주문 목록 조회": "/orderitem" },
    { "내 쿠폰": "#" },
    { "내 포인트": "#" },
    { "자주 산 상품": "#" },
    { "최근 본 상품": "#" },
    { "작성한 후기": "/productInquire" },
    { "내 문의": "/userinfo" },
    
  
    // 회원카드 옆의 개인정보 수정은 내정보와 같은 페이지인가 ?
    //  -> { "개인 정보 수정": "/wish" }
    // 찜한상품 보기 카테고리가 없음
    // -> { "찜한 상품": "/itemreview" }
    // 카테고리에 맞는 파일들을 만들어야함 ~~~~
  ];

  return (
    <BrowserRouter>
      <Mycard>
        <div className="accountBox">
          <div className="acName">
            <span>ID님의 회원카드</span>
            <Colbtn>내 정보 수정</Colbtn>
          </div>
          <div className="acEvent">
            <div className="event">
              <span>내 포인트</span>
              <span>
                0P
                <GoChevronRight />
              </span>
            </div>
            <div className="event">
              <span>내 쿠폰</span>
              <span>
                0장
                <GoChevronRight />
              </span>
            </div>
          </div>
        </div>
        <div className="product">
          <div>
            <h2>배송중</h2>
            <div className="count">
              <span>0</span>
            </div>
            <Colbtn><a>배송조회</a></Colbtn>
          </div>
          <div>
            <h2>주문 목록</h2>
            <div className="count">
              <span>0</span>
            </div>
            <Colbtn><a>주문 목록 조회</a></Colbtn>
          </div>
          <div>
            <h2>최근 본 상품</h2>
            <div className="count">
              <span>0</span>
            </div>
            <Colbtn><a>조회</a></Colbtn>
          </div>
          <div>
            <h2>자주 산 상품</h2>
            <div className="count">
              <span>0</span>
            </div>
            <Colbtn><a>조회</a></Colbtn>
          </div>
        </div>
      </Mycard>
      <Tap>
        <div className="nav">
          <h3>마이페이지</h3>
          <ul>
            {tap.map((el, idx) => {
              return (
               <li>
                 <Colbtn key={idx}>
                  <NavLink
                    to={Object.values(el)[0]}
                    className={({ isActive }) => (isActive ? "clickCo" : null)}
                  >
                    {Object.keys(el)}
                  </NavLink>
                </Colbtn>
               </li>
              );
            })}
          </ul>
        </div>
        <div className="content">
          <Routes>
            {/*작업용 추후 삭제  */}
            <Route path="/" element={<Orderitem />} />
            <Route path="/orderitem" element={<Orderitem />} />
            <Route path="/itemreview" element={<Itemreview />} />
            <Route path="/productInquire" element={<ProductInquire />} />
            <Route path="/userinfo" element={<Userinfo />} />
            <Route path="/userinfoEdit" element={<UserinfoEdit />} />
            <Route path="/wish" element={<Wish />} />
          </Routes>
        </div>
      </Tap>
    </BrowserRouter>
  );
}

export default Mypage;
