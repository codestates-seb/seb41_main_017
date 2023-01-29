import { useState } from "react";
import styled from "styled-components";

import CheckBox from "../../components/CheckBox";

const Container = styled.li`
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
`;

function CategoryList({ category }) {
  const [isChecked, setIsChecked] = useState(false);

  const handleCheckButtonClick = () => {
    setIsChecked(!isChecked);
  };

  return (
    <Container onClick={handleCheckButtonClick}>
      <CheckBox isChecked={isChecked} size="18px" />
      <span>{category.text}</span>
    </Container>
  );
}

export default CategoryList;
