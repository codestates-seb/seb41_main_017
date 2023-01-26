import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import styled from "styled-components";
import ProductItem from "../../components/ProductItem";
import BASE_URL from "../../constants/BASE_URL";

const Container = styled.div`
  margin-top: 50px;
  margin-bottom: 75px;
  position: relative;
  font-size: 14px;
  font-weight: 500;

  .product_list {
    width: 100%;
    display: grid;
    grid-template-columns: repeat(auto-fill, 240px);
    gap: 31px 18px;
  }

  .product_list_header {
    margin-left: 10px;
  }
`;

const PageHeader = styled.h3`
  margin-top: 50px;
  margin-bottom: 50px;
  font-weight: 500;
  font-size: 28px;
  color: rgb(51, 51, 51);
  line-height: 35px;
  text-align: center;

  span {
    color: #ff6767;
  }
`;

function Search() {
  const location = useLocation();

  const [data, setData] = useState("");

  useEffect(() => {
    const getData = async () => {
      try {
        const { data } = await axios.get(`${BASE_URL}/search${location.search}`);

        setData(data);
      } catch (error) {
        console.log(`Error: ${error}`);
      }
    };

    getData();
  }, [location]);

  const productArr = [
    {
      id: 0,
      name: "[비비드키친] 저칼로리 숯불매콤치킨 소스",
      price: 3980,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1657781547680l0.jpg",
    },
    {
      id: 1,
      name: "[파제르 핀란드 시그니처 코촐릿 4종]",
      price: 12000,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1607930413465l0.jpg",
    },
    {
      id: 2,
      name: "[카스0.0] 논알콜 음료 8캔 & 헌터즈트러플 감자칩 2개",
      price: 9990,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1658317867100l0.jpg",
    },
    {
      id: 3,
      name: "한뿌리 수삼 45g",
      price: 3490,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1576555719180l0.jpg",
    },
    {
      id: 4,
      name: "[순백수] 하노키 우디 디퓨저 세트 (본품 150ml)",
      price: 26900,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1655455835441l0.jpeg",
    },
    {
      id: 5,
      name: "[상하목장] 얼려먹는 아이스크림 초코",
      price: 5200,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/16499051856l0.jpg",
    },
    {
      id: 6,
      name: "[창화당] 육즙 팡팡 소룡포",
      price: 8830,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1548292714543l0.jpg",
    },
    {
      id: 7,
      name: "[만토바] 비보 오가닉 엑스트버진 스프레이 화이트트러플향 200ml",
      price: 3980,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1641272547982l0.jpg",
    },
    {
      id: 8,
      name: "[설치배송][LG전자] 울트라 HDTV (스탠드형)",
      price: 633800,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1634802297108l0.jpg",
    },
    {
      id: 9,
      name: "[하선정] 김밥 단무지",
      price: 2380,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1657529753879l0.jpg",
    },
    {
      id: 10,
      name: "[제니튼] 닥터제니 저불소 어린이 치약 60g",
      price: 6300,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/16505299970l0.jpg",
    },
  ];

  return (
    <Container>
      <PageHeader>
        '<span>{decodeURIComponent(location.search.slice(9))}</span>'에 대한 검색결과
      </PageHeader>
      <div className="product_list_header">
        <div className="product_list_count">{`총 10건`}</div>
      </div>
      <div className="product_list">
        {data &&
          productArr.map((element) => (
            <ProductItem id={element.id} imgUrl={element.image} name={element.name} price={element.price} key={Math.random()} />
          ))}
      </div>
    </Container>
  );
}

export default Search;
