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
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom"
import axios from 'axios';

const Layout = styled.div`
  padding-top: 15px;
  width:100%;
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

function Mypage () {


  const [user, setUser] = useState({});

  useEffect(()=>{


    //임시영역 -> 추후에 프로필 조회하거나해서 해당유저에 대한 토큰을써야함
    const data = async () => {
      const res = await axios.post(`${process.env.REACT_APP_URL}/users/signin`,
      {
        "username" : "jangusername",
        "password" : "jangpassword"
      });
      localStorage.setItem("accessToken",res.headers.authorization)
    }
    data();
    //------------------------------------------------------------

    axios.get(`${process.env.REACT_APP_URL}/users`,{
      headers:{
        Authorization : localStorage.getItem("accessToken"),
      }
    })
    .then(res => setUser(res.data.data))
    .then(erros => erros)
  },[]);



// 마이페이지 첫화면에 내정보 페이자가 동시에 렌더 되도록 설정해야함
  const list = {
    "내정보": {
      'userInfo': <Userinfo/>
    },
    "배송지 설정": {
      'addressSet': <DeliverySet/>
    },
    "배송 조회": {
      'deliveryLook': <DeliveryLook/>
    },
    "주문 목록 조회": {
      'orderitem': <Orderitem/>
    },
    "내 쿠폰": {
      'coupon': <Coupon/>
    },
    "내 포인트": {
      'point': <Point/>
    },
    "자주 산 상품": {
      'buyitem': <Buyitem/>
    },
    "찜한 상품": {
      'selectItem': <Selectitem/>
    },
    "작성한 후기": {
      'itemreviewList': <ItemreviewList/>,
      children:[
        {"itemreviewList/write" : (<ItemreviewWrite/>)},
      ]
    },
    "내 문의": {
      'inquiry': <Inquiry/>
    }

};
  return (
    <Layout>
      <Mycard>
        <div className="account_Box">
          <div className="acName">
            <span>{`${user.name} 님의 회원카드`}</span>
            <div>
              <BasicButton href={"/mypage"} radius={12}>
                내 정보 수정
              </BasicButton>
            </div>
          </div>
          <div className="ac_Event">
            <div className="event">
              <a href="/point">
                <span>내 포인트</span>
                <span>
                  {`${user.point}P`}
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
              <span>0</span>
            </div>
            <BasicButton radius={10} href={"/mypage/deliveryLook"}>
              배송조회
            </BasicButton>
          </div>
          <div>
            <h2>주문 목록</h2>
            <div className="count">
              <span>0</span>
            </div>
            <BasicButton radius={10} href={"/mypage/orderitem"}>
              주문 목록 조회
            </BasicButton>
          </div>
          <div>
            <h2>찜한 상품</h2>
            <div className="count">
              <span>0</span>
            </div>
            <BasicButton radius={10} href={"/mypage/selectItem"}>
              조회
            </BasicButton>
          </div>
          <div>
            <h2>자주 산 상품</h2>
            <div className="count">
              <span>0</span>
            </div>
            <BasicButton radius={10} href={"/mypage/buyitem"}>
              조회
            </BasicButton>
          </div>
        </div>
      </Mycard>
      <Tab 
      list={list} 
      title="마이 페이지" 
      flex={1}
      />
    </Layout>
  );
}

export default Mypage;
