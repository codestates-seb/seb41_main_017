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
      size: 100,
    };
    const queryString = Object.entries(query).reduce((acc, [key, value]) => `${acc}&${key}=${value}`, "");

    const getData = async () => {
      try {
        const { data } = await axios.get(`${BASE_URL}/collections/newproduct?${queryString}`);

        setData(data);
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
          <div className="product_list">{data && data.data.map((element) => <ProductItem element={element} key={Math.random()} />)}</div>
        </div>
      </Content>
    </>
  );
}

export default Collection;
