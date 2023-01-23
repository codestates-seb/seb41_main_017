import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

import styled from "styled-components";
import axios from "axios";

import CheckBox from "../../components/CheckBox";
import MainBanner from "../../components/MainBanner";
import BASE_URL from "../../constants/BASE_URL";
import ProductItem from "../../components/ProductItem";

const PageHeader = styled.h3`
  margin-top: 50px;
  font-weight: 500;
  font-size: 28px;
  color: rgb(51, 51, 51);
  line-height: 35px;
  letter-spacing: -1px;
  text-align: center;
`;

const Content = styled.div`
  margin-top: 50px;
  margin-bottom: 75px;
  position: relative;
  display: flex;

  .category {
    position: sticky;
    width: 180px;
    height: 100%;
    max-height: calc(100vh - 120px);
    top: 80px;
    margin-right: 47px;
    border-top: 1px solid #ddd;
    border-bottom: 1px solid #ddd;
    overflow: hidden scroll;
    background-color: #fff7f5;

    .category_title {
      margin: 10px 10px 20px 10px;
    }

    .category_list {
      margin: 10px;
      display: flex;
      align-items: center;
      cursor: pointer;

      svg {
        cursor: pointer;
      }

      span {
        padding-bottom: 2px;
        font-size: 14px;
      }
    }
  }

  .product_container {
    width: 100%;

    .product_list_header {
      padding-bottom: 20px;
      display: flex;
      align-items: center;
      justify-content: space-between;
      line-height: 20px;
    }

    .product_list_count {
      font-size: 14px;
      font-weight: 500;
    }

    .product_filter {
      position: relative;
      display: flex;
      align-items: center;
    }

    .product_filter_list {
      margin-left: 8px;
      display: flex;
      align-items: center;
      justify-content: flex-end;
      font-size: 14px;
      color: rgb(153, 153, 153);
      cursor: pointer;
    }

    .product_list {
      width: 100%;
      display: grid;
      grid-template-columns: repeat(auto-fill, 200px);
      gap: 31px 18px;
    }
  }
`;

const FilterList = styled.li.attrs(({ dataId }) => ({
  "data-id": dataId,
}))`
  margin-left: 8px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  font-size: 14px;
  color: rgb(153, 153, 153);
  cursor: pointer;

  color: ${({ dataId, sort }) => (dataId === sort ? "#ff6767" : "rgb(153, 153, 153)")};
`;

function Collection() {
  const [data, setData] = useState(null);
  const [sort, setSort] = useState("newest");

  const handleSortListClick = ({ target }) => {
    setSort(target.dataset.id);
  };

  useEffect(() => {
    const query = {
      sorted_type: sort,
    };
    const queryString = Object.entries(query).reduce((acc, [key, value]) => `${acc}&${key}=${value}`, "");

    const getData = async () => {
      try {
        const { data } = await axios.get(`${BASE_URL}/collections/newproduct?${queryString}`);

        setData(data);
        console.log(data);
      } catch (error) {
        console.log(`Error: ${error}`);
      }
    };

    getData();
  }, []);

  const [isFisheryChecked, setIsFisheryChecked] = useState(false);
  const [isNoodlesChecked, setNoodlesChecked] = useState(false);
  const [isMeatAndEggsChecked, setIsMeatAndEggsChecked] = useState(false);
  const [isFruitsAndVegetablesChecked, setIsFruitsAndVegetablesChecked] = useState(false);
  const [isDrinkChecked, setIsDrinkChecked] = useState(false);
  const [isGrainChecked, setIsGrainChecked] = useState(false);
  const [isSnackAndBreadChecked, setIsSnackAndBreadChecked] = useState(false);

  const { pathname } = useLocation();
  const pageMap = {
    "/collections/new-product": "신상품",
    "/collections/best-product": "베스트",
  };

  const isAllChecked =
    isFisheryChecked &&
    isNoodlesChecked &&
    isMeatAndEggsChecked &&
    isFruitsAndVegetablesChecked &&
    isDrinkChecked &&
    isGrainChecked &&
    isSnackAndBreadChecked;

  const categories = [
    {
      text: "전체",
      checked: isAllChecked,
      setChecked: () => {
        if (!isAllChecked) {
          setIsFisheryChecked(true);
          setNoodlesChecked(true);
          setIsMeatAndEggsChecked(true);
          setIsFruitsAndVegetablesChecked(true);
          setIsDrinkChecked(true);
          setIsGrainChecked(true);
          setIsSnackAndBreadChecked(true);

          return;
        }

        if (isAllChecked) {
          setIsFisheryChecked(false);
          setNoodlesChecked(false);
          setIsMeatAndEggsChecked(false);
          setIsFruitsAndVegetablesChecked(false);
          setIsDrinkChecked(false);
          setIsGrainChecked(false);
          setIsSnackAndBreadChecked(false);
        }
      },
    },
    { text: "수산·해산·건어물", checked: isFisheryChecked, setChecked: () => setIsFisheryChecked(!isFisheryChecked) },
    { text: "면·양념·오일", checked: isNoodlesChecked, setChecked: () => setNoodlesChecked(!isNoodlesChecked) },
    { text: "정육·계란", checked: isMeatAndEggsChecked, setChecked: () => setIsMeatAndEggsChecked(!isMeatAndEggsChecked) },
    { text: "과일·야채", checked: isFruitsAndVegetablesChecked, setChecked: () => setIsFruitsAndVegetablesChecked(!isFruitsAndVegetablesChecked) },
    { text: "생수·음료·우유", checked: isDrinkChecked, setChecked: () => setIsDrinkChecked(!isDrinkChecked) },
    { text: "견과·쌀", checked: isGrainChecked, setChecked: () => setIsGrainChecked(!isGrainChecked) },
    { text: "간식·과자·빵", checked: isSnackAndBreadChecked, setChecked: () => setIsSnackAndBreadChecked(!isSnackAndBreadChecked) },
  ];

  const CategoryList = ({ category }) => {
    return (
      <li className="category_list" onClick={category.setChecked}>
        <CheckBox isChecked={category.checked} size="18px" />
        <span>{category.text}</span>
      </li>
    );
  };

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
    <>
      <MainBanner />
      <PageHeader>{pageMap[pathname]}</PageHeader>
      <Content>
        <div className="category">
          <div className="category_title">카테고리</div>
          <ul>
            {categories.map((category, index) => (
              <CategoryList category={category} key={index} />
            ))}
          </ul>
        </div>
        <div className="product_container">
          <div className="product_list_header">
            <div className="product_list_count">{`총 ${data && data.pageInfo.totalElements}건`}</div>
            <ul className="product_filter" onClick={handleSortListClick}>
              <FilterList dataId="newest" sort={sort}>
                신상품순
              </FilterList>
              <FilterList dataId="lower" sort={sort}>
                낮은 가격순
              </FilterList>
              <FilterList dataId="higher" sort={sort}>
                높은 가격순
              </FilterList>
            </ul>
          </div>
          <div className="product_list">{data && productArr.map((element) => <ProductItem element={element} key={Math.random()} />)}</div>
        </div>
      </Content>
    </>
  );
}

export default Collection;
