import styled from "styled-components";
import MainBanner from "../components/MainBanner";
import ProductItemSlider from "../components/ProductItemSlider";

const Container = styled.div`
  min-width: 1050px;
`;

export const TodayRecommendProducts = styled.div`
  max-width: 1009px;
  margin: 40px auto;
`;

export const Title = styled.div`
  font-weight: 700;
  font-size: 16px;
  line-height: 23px;
  color: #fd6c40;
  padding: 10px;
`;

export function Main() {
  return (
    <Container>
      <MainBanner />

      <TodayRecommendProducts>
        <Title>이달의 추천 상품</Title>
        <ProductItemSlider />
      </TodayRecommendProducts>

      <TodayRecommendProducts>
        <Title>이달의 신상품</Title>
        <ProductItemSlider />
      </TodayRecommendProducts>
    </Container>
  );
}
