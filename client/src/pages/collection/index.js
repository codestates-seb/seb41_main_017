import { useState } from "react";
import { useLocation } from "react-router-dom";

import styled from "styled-components";

import CheckBox from "../../components/CheckBox";
import MainBanner from "../../components/MainBanner";

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
`;

function Collection() {
  const [isFisheryChecked, setIsFisheryChecked] = useState(false);
  const [isNoodlesChecked, setNoodlesChecked] = useState(false);
  const [isMeatAndEggsChecked, setIsMeatAndEggsChecked] = useState(false);
  const [isFruitsAndVegetablesChecked, setIsFruitsAndVegetablesChecked] = useState(false);
  const [isDrinkChecked, setIsDrinkChecked] = useState(false);
  const [isGrainChecked, setIsGrainChecked] = useState(false);
  const [isSnackAndBreadChecked, setIsSnackAndBreadChecked] = useState(false);

  const { pathname } = useLocation();
  const pageMap = {
    "/new-product": "신상품",
    "/best-product": "베스트",
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
      </Content>
    </>
  );
}

export default Collection;
