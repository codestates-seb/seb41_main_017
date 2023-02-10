import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";

import ScrollTop from "../../components/ScrollTop";
import ProductAtf from "./productAtf/index";
import ProductContent from "./productContent/index";

const Container = styled.div`
  max-width: 1050px;
  margin: 44px auto;
  font-size: 14px;
`;

function ProductDetail() {
  const { id } = useParams();
  const [quantity, setQuantity] = useState(1);
  const [totalPrice, setTotalPrice] = useState(0);
  const [data, setData] = useState({});

  useEffect(() => {
    if (data.data) {
      setTotalPrice((quantity * data.data.price).toLocaleString());
    }
  }, [quantity]);

  useEffect(() => {
    const getData = async () => {
      try {
        const { data } = await axios.get(`${process.env.REACT_APP_URL}/product/${id}`);

        setData(data);
        setTotalPrice(data.data.price.toLocaleString());
      } catch (error) {
        console.log(`Error: ${error}`);
      }
    };

    getData();
  }, []);

  return (
    <Container>
      <ProductAtf data={data} quantity={quantity} setQuantity={setQuantity} totalPrice={totalPrice} />
      <ProductContent data={data} quantity={quantity} setQuantity={setQuantity} totalPrice={totalPrice} />
      <ScrollTop />
    </Container>
  );
}

export default ProductDetail;
