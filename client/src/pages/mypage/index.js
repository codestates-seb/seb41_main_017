import styled from "styled-components";
import { GoChevronRight } from "react-icons/go";
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
import BasicButton from "../../components/BasicButton";
import Tap from "../../components/Tap";



// 보류
const Layout = styled.div``
const Mycard = styled.div`
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
      align-items:center;
      gap: 10px;
      margin: 15px 0 35px 0;
      & > span {
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


function Mypage() {

  const list = [
    {"내정보": ["/userinfo",<Userinfo/>]},
    {"배송지 설정": ["/addressSet",<DeliverySet/>]},
    {"배송 조회": ["/deliveryLook",<DeliveryLook/>]},
    {"주문 목록 조회": ["/orderitem",<Orderitem/>]},
    {"내 쿠폰": ["/coupon",<Coupon/>]},
    {"내 포인트": ["/point",<Point/>]},
    {"찜한 상품": ["/selectItem",<Selectitem/>]},
    {"자주 산 상품": ["/buyitem",<Buyitem/>]},
    {"작성한 후기": ["/itemreview",<Itemreview/>]},
    {"내 문의": ["/inquiry",<Inquiry/>]}
]

  return (
      <Layout>
        <Mycard>
          <div className="accountBox">
            <div className="acName">
              <span>ID님의 회원카드</span>
              <div>
                <BasicButton href={'/userinfo'} radius={12}>내 정보 수정</BasicButton>
              </div>
            </div>
            <div className="acEvent">
              <div className="event">
                <a href='/point'>
                <span>내 포인트</span>
                <span>0P<GoChevronRight /></span>
                </a>
              </div>
              <div className="event">
                <a href='/coupon'>
                <span>내 쿠폰</span>
                <span>0장<GoChevronRight /></span>
                </a>              
              </div>
            </div>
          </div>
          <div className="product">
            <div>
              <h2>배송중</h2>
              <div className="count">
                <span>0</span>
              </div>
              <BasicButton radius={10} href={"/deliveryLook"}>배송조회</BasicButton>
            </div>
            <div>
              <h2>주문 목록</h2>
              <div className="count">
                <span>0</span>
              </div>
              <BasicButton radius={10} href={"/orderitem"}>주문 목록 조회</BasicButton>
            </div>
            <div>
              <h2>찜한 상품</h2>
              <div className="count">
                <span>0</span>
              </div>
              <BasicButton radius={10} href={"/selectItem"}>조회</BasicButton>
            </div>
            <div>
              <h2>자주 산 상품</h2>
              <div className="count">
                <span>0</span>
              </div>
              <BasicButton radius={10} href={"/buyitem"}>조회</BasicButton>
            </div>
          </div>
        </Mycard>
        <Tap list={list} title="마이 페이지"/>
      </Layout>
  );
}

export default Mypage;
