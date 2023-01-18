import React from "react";

import styled from "styled-components";

import ProductSelection from "../productAtf/ProductSelection";
import Nav from "./Nav";
import Review from "./Review";
import Inquiry from "./Inquiry";

const Container = styled.div`
  display: flex;
  margin-top: 50px;
`;

const DetailContainer = styled.div`
  width: 750px;

  img {
    width: 100%;
    height: auto;
  }
`;

function ProductContent({ data, quantity, setQuantity, totalPrice }) {
  return (
    <Container>
      <DetailContainer>
        <Nav />

        <img id="detail" src="https://user-images.githubusercontent.com/57666791/212521455-cac803e3-3159-4c0a-bb8c-b1889afd1bbc.jpeg"></img>

        <Review />

        <Inquiry data={data} />
      </DetailContainer>
      <ProductSelection
        name={data.name}
        priceToLocaleString={data.priceToLocaleString}
        quantity={quantity}
        setQuantity={setQuantity}
        totalPrice={totalPrice}
      />
    </Container>
  );
}

export default ProductContent;
