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
    ?????????: {
      userInfo: <Userinfo />,
    },
    "????????? ??????": {
      deliverySet: <DeliverySet />,
    },
    "?????? ??????": {
      deliveryLook: <DeliveryLook />,
    },
    "?????? ?????? ??????": {
      orderitem: <Orderitem />,
    },
    "??? ??????": {
      coupon: <Coupon />,
    },
    "??? ?????????": {
      point: <Point />,
    },
    "?????? ??? ??????": {
      buyitem: <Buyitem />,
    },
    "?????? ??????": {
      selectItem: <Selectitem />,
    },
    "????????? ??????": {
      itemreviewList: <ItemreviewList />,
      children: [{ "itemreviewList/write": <ItemreviewWrite /> }],
    },
    "??? ??????": {
      inquiry: <Inquiry />,
    },
  };


  
  return (
    <Layout>
      <Mycard>
        <div className="account_Box">
          <div className="acName">
            <span>{`${user.name} ?????? ????????????`}</span>
            <div>
              <BasicButton href={"/mypage/userinfo"} radius={12}>
                ??? ?????? ??????
              </BasicButton>
            </div>
          </div>
          <div className="ac_Event">
            <div className="event">
              <a href="/point">
                <span>??? ?????????</span>
                <span>
                  {`${user.point === undefined ? "0" : user.point}P`}
                  <GoChevronRight />
                </span>
              </a>
            </div>
            <div className="event">
              <a href="/coupon">
                <span>??? ??????</span>
                <span>
                  0???
                  <GoChevronRight />
                </span>
              </a>
            </div>
          </div>
        </div>
        <div className="product">
          <div>
            <h2>?????????</h2>
            <div className="count">
              <span>{userData?.shippingCount}</span>
            </div>
            <BasicButton radius={10} href={"/mypage/deliveryLook"}>
              ????????????
            </BasicButton>
          </div>
          <div>
            <h2>?????? ??????</h2>
            <div className="count">
              <span>{userData?.orderCount}</span>
            </div>
            <BasicButton radius={10} href={"/mypage/orderitem"}>
              ?????? ?????? ??????
            </BasicButton>
          </div>
          <div>
            <h2>?????? ??????</h2>
            <div className="count">
              <span>{userData?.productLikeCount}</span>
            </div>
            <BasicButton radius={10} href={"/mypage/selectItem"}>
              ??????
            </BasicButton>
          </div>
          <div>
            <h2>?????? ??? ??????</h2>
            <div className="count">
              <span>{userData?.frequentlyOrderedProductCount}</span>
            </div>
            <BasicButton radius={10} href={"/mypage/buyitem"}>
              ??????
            </BasicButton>
          </div>
        </div>
      </Mycard>
      <Tab list={list} title="?????? ?????????" flex={1} />
    </Layout>
  );
}

export default Mypage;
