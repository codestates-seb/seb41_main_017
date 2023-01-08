import styled from "styled-components";
import MainBanner from "../components/MainBanner";
import ProductItemListSlider from "../components/ProductItemListSlider";

const Container = styled.div`
  min-width: 1050px;
`;

const TodayRecommendProducts = styled.div`
  max-width: 1009px;
  margin: 40px auto;
`;

const Title = styled.div`
  font-style: normal;
  font-weight: 700;
  font-size: 16px;
  line-height: 23px;
  color: #fd6c40;
  padding: 10px;
`;

function Main() {
  return (
    <Container>
      <MainBanner></MainBanner>
      <TodayRecommendProducts>
        <Title>이달의 추천 상품</Title>
        <ProductItemListSlider></ProductItemListSlider>
      </TodayRecommendProducts>
      <TodayRecommendProducts>
        <Title>이달의 신상품</Title>
        <ProductItemListSlider></ProductItemListSlider>
      </TodayRecommendProducts>
    </Container>
  );
}

export default Main;
