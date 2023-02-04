import { useEffect, useState } from "react";
import axios from "axios";
import styled from "styled-components";
import { GoChevronRight } from "react-icons/go";
import BasicButton from "../../components/BasicButton";
import Tab from "../../components/Tab";
import Userinfo from "./userinfo";
import DeliverySet from "./deliverySet";
import DeliveryLook from "./deliveryLook";
import Orderitem from "./orderitem";
import Coupon from "./coupon";
import Point from "./point";
import Buyitem from "./buyItem";
import Selectitem from "./selectItem";
import ItemreviewList from "./itemreviewList";
import Inquiry from "./inquiry";
import ItemreviewWrite from "./itemreviewWrite";

const Layout = styled.div`
  padding-top: 15px;
  width: 100%;
  height: 100%;
  margin: 0 auto;
`;
const Mycard = styled.div`
  background-color: rgb(255, 224, 214);
  width: 1050px;
  margin: 0 auto;
  display: flex;
  text-align: center;
  margin-bottom: 20px;

  .account_Box {
    padding: 10px 0 0 30px;
    flex-basis: 300px;
    height: 212px;
    display: flex;
    flex-direction: column;

    .acName {
      display: flex;
      align-items: center;
      gap: 10px;
      margin: 15px 0 35px 0;
      flex-wrap: wrap;
      & > span {
        font-size: 18px;
      }
    }

    .ac_Event {
      justify-content: left;
      background-color: #fff7f5;
      width: 195px;
      padding: 10px;
      border-radius: 10px;

      .event {
        margin: 10px;

        a {
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

    & > div {
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    .count {
      color: white;
      font-size: 50px;
      margin: 20px 0;
    }
  }
`;

function Mypage() {
  const [user, setUser] = useState({});
  const [userData, setUserData] = useState(0);
  
  
  

  // "username": "id2",
  // "password": "!@#123password"
  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_URL}/users`, {
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      })
      .then((res) => setUser(res.data.data));

      axios.get(`${process.env.REACT_APP_URL}/mypage/count`,{
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      }).then((res)=> setUserData(res.data.data))
  }, []);

  
  const list = {
    내정보: {
      userInfo: <Userinfo />,
    },
    "배송지 설정": {
      deliverySet: <DeliverySet />,
    },
    "배송 조회": {
      deliveryLook: <DeliveryLook />,
    },
    "주문 목록 조회": {
      orderitem: <Orderitem />,
    },
    "내 쿠폰": {
      coupon: <Coupon />,
    },
    "내 포인트": {
      point: <Point />,
    },
    "자주 산 상품": {
      buyitem: <Buyitem />,
    },
    "찜한 상품": {
      selectItem: <Selectitem />,
    },
    "작성한 후기": {
      itemreviewList: <ItemreviewList />,
      children: [{ "itemreviewList/write": <ItemreviewWrite /> }],
    },
    "내 문의": {
      inquiry: <Inquiry />,
    },
  };


  
  return (
    <Layout>
      <Mycard>
        <div className="account_Box">
          <div className="acName">
            <span>{`${user.name} 님의 회원카드`}</span>
            <div>
              <BasicButton href={"/mypage/userinfo"} radius={12}>
                내 정보 수정
              </BasicButton>
            </div>
          </div>
          <div className="ac_Event">
            <div className="event">
              <a href="/point">
                <span>내 포인트</span>
                <span>
                  {`${user.point === undefined ? "0" : user.point}P`}
                  <GoChevronRight />
                </span>
              </a>
            </div>
            <div className="event">
              <a href="/coupon">
                <span>내 쿠폰</span>
                <span>
                  0장
                  <GoChevronRight />
                </span>
              </a>
            </div>
          </div>
        </div>
        <div className="product">
          <div>
            <h2>배송중</h2>
            <div className="count">
              <span>{userData?.shippingCount}</span>
            </div>
            <BasicButton radius={10} href={"/mypage/deliveryLook"}>
              배송조회
            </BasicButton>
          </div>
          <div>
            <h2>주문 목록</h2>
            <div className="count">
              <span>{userData?.orderCount}</span>
            </div>
            <BasicButton radius={10} href={"/mypage/orderitem"}>
              주문 목록 조회
            </BasicButton>
          </div>
          <div>
            <h2>찜한 상품</h2>
            <div className="count">
              <span>{userData?.productLikeCount}</span>
            </div>
            <BasicButton radius={10} href={"/mypage/selectItem"}>
              조회
            </BasicButton>
          </div>
          <div>
            <h2>자주 산 상품</h2>
            <div className="count">
              <span>{userData?.frequentlyOrderedProductCount}</span>
            </div>
            <BasicButton radius={10} href={"/mypage/buyitem"}>
              조회
            </BasicButton>
          </div>
        </div>
      </Mycard>
      <Tab list={list} title="마이 페이지" flex={1} />
    </Layout>
  );
}

export default Mypage;
