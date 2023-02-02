import axios from "axios";
import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { loadTossPayments } from "@tosspayments/payment-sdk";
import { useSelector } from "react-redux";

import BasicButton from "../BasicButton";
import OrderInfo from "./OrderInfo";
import ShipInfo from "./ShipInfo";
import PayInfo from "./PayInfo";
import InfoCheck from "./InfoCheck";
import { ModalTitle, ModalText, CloseButton, ButtonWrapper, ModalWrapper, SignupFailureContainer } from "../../styles/signupStyle";

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

function Pay() {
  const productIds = useSelector((state) => state.productsInfo);
  const [data, setData] = useState([]);
  const [filterData, setFilterData] = useState("");
  const [userData, setUserData] = useState("");
  const [price, setPrice] = useState(productIds);
  const [rejectModal, setRecjectModal] = useState(false);
  const [reject, setReject] = useState(false);

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
      productIds: productIds.info.ids,
      address: filterData[0].address,
      receiverName: filterData[0].receiverName,
      receiverPhoneNumber: filterData[0].receiverPhoneNumber,
    };

    const clientKey = "test_ck_jkYG57Eba3G7GdKlLJL3pWDOxmA1";

    if (reject === false) {
      setRecjectModal(true);
    } else {
      const res = await axios.post(`${process.env.REACT_APP_URL}/payments`, data, header);

      const tossPayments = await loadTossPayments(clientKey);

      tossPayments.requestPayment(res.data.data.payType, {
        amount: res.data.data.amount,
        orderId: res.data.data.orderId,
        orderName: res.data.data.orderName,
        customerName: "test",
        successUrl: "http://localhost:3000/pay/successpage",
        failUrl: "http://localhost:3000/pay/failpage",
      });
    }
  };

  // 배송지 정보 (배송지 수령인, 수령인 번호, 수령인 주소)
  const fetchData = () => {
    axios
      .get(`${process.env.REACT_APP_URL}/destination`, {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token")).authorization,
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
      .get(`${process.env.REACT_APP_URL}/users`, {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token")).authorization,
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
      <PayInfo price={price} />
      <InfoCheck setReject={setReject} reject={reject} />

      {rejectModal && (
        <ModalWrapper>
          <SignupFailureContainer>
            <ModalTitle>결제 실패</ModalTitle>
            <ModalText>이용약관과 개인정보 수집 이용동의를 모두 동의해주세요.</ModalText>
          </SignupFailureContainer>
          <CloseButton onClick={() => setRecjectModal(false)}>닫기</CloseButton>
        </ModalWrapper>
      )}

      <ButtonWrapper onClick={handlePayBtnClick}>
        <BasicButton children={"결제하기"} font={"20"} radius={"5"} p_height={"14"} p_width={"150"} />
      </ButtonWrapper>
    </Container>
  );
}

export default Pay;
