import axios from "axios";
import React, { useEffect, useState } from "react";
import styled from "styled-components";
import BASE_URL from "../../constants/BASE_URL";
import BasicButton from "../BasicButton";
import OrderInfo from "./OrderInfo";
import ShipInfo from "./ShipInfo";
import PayInfo from "./PayInfo";
import InfoCheck from "./InfoCheck";
import { loadTossPayments } from "@tosspayments/payment-sdk";

const Container = styled.div`
  max-width: 1050px;
  margin: 44px auto;
  padding: 0 50px;
`;

const TitleContainer = styled.div`
  display: flex;
  width: 100%;
  border-bottom: 1px solid black;
  padding: 5px;

  svg {
    margin-right: 5px;
  }

  h2 {
    padding-top: 4px;
    align-self: center;
    font-size: 24px;
  }
`;

const ButtonWrapper = styled.div`
  display: flex;
  justify-content: center;
  gap: 8px;
  border-top: 1px solid rgb(244, 244, 244);
  text-align: center;
  padding-top: 20px;
  margin-top: 20px;

  a {
    background-color: #ffffff;
    border-radius: 3px;
  }
`;

function Pay() {
  const ProductIds = localStorage.getItem("productIds");
  const [data, setData] = useState([]);
  const [filterData, setFilterData] = useState("");
  const [userData, setUserData] = useState("");

  const handlePayBtnClick = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem("token");
    const parse = JSON.parse(token);
    const header = {
      headers: {
        "Content-Type": `application/json`,
        authorization: parse.authorization,
      },
    };

    let data = {
      payType: "CARD",
      productIds: ProductIds.slice(1, ProductIds.length - 1).split(","),
      address: filterData[0].address,
      receiverName: filterData[0].receiverName,
      receiverPhoneNumber: filterData[0].receiverPhoneNumber,
    };

    const clientKey = "test_ck_jkYG57Eba3G7GdKlLJL3pWDOxmA1";
    const res = await axios.post(`${BASE_URL}/payments`, data, header);

    const tossPayments = await loadTossPayments(clientKey);

    tossPayments.requestPayment(res.data.data.payType, {
      amount: res.data.data.amount,
      orderId: res.data.data.orderId,
      orderName: res.data.data.orderName,
      customerName: "test",
      successUrl: res.data.data.successUrl,
      failUrl: res.data.data.failUrl,
    });
  };

  // 배송지 정보 (배송지 수령인, 수령인 번호, 수령인 주소)
  const fetchData = () => {
    axios
      .get(`${BASE_URL}/destination`, {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      })
      .then((res) => {
        setData(res.data.data);
        setFilterData(res.data.data.filter((el) => el.defaultSelect === true));
      })
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    fetchData();
  }, []);

  // 유저 정보 (사용자 이름, 사용자 번호, 사용자 이메일)
  const userFetchData = () => {
    axios
      .get(`${BASE_URL}/users`, {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      })
      .then((res) => setUserData(res.data.data))
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    userFetchData();
  }, []);

  return (
    <Container>
      <TitleContainer>
        <h2>결제하기</h2>
      </TitleContainer>
      <OrderInfo userData={userData} />
      <ShipInfo filterData={{ filterData }} />
      <PayInfo />
      <InfoCheck />

      <ButtonWrapper onClick={handlePayBtnClick}>
        <BasicButton
          children={"결제하기"}
          font={"20"}
          radius={"5"}
          p_height={"14"}
          p_width={"150"}
        />
      </ButtonWrapper>
    </Container>
  );
}

export default Pay;
