import axios from "axios";
import React, { useEffect, useState } from "react";
import styled from "styled-components";
import BASE_URL from "../../constants/BASE_URL";
import BasicButton from "../BasicButton";
import OrderInfo from "./OrderInfo";
import ShipInfo from "./ShipInfo";
import PayInfo from "./PayInfo";
import InfoCheck from "./InfoCheck";
import { useSelector } from "react-redux";

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
  const productsInfo = useSelector((state) => state.productsInfo);

  console.log(productsInfo);
  return (
    <Container>
      <TitleContainer>
        <h2>결제하기</h2>
      </TitleContainer>
      <OrderInfo />
      <ShipInfo />
      <PayInfo />
      <InfoCheck />

      <ButtonWrapper>
        <BasicButton children={"결제하기"} font={"20"} radius={"5"} p_height={"14"} p_width={"150"} />
      </ButtonWrapper>
    </Container>
  );
}

export default Pay;
