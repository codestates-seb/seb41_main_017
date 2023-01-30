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

function CategoryList({ category, checkedCategoryCodes, setCheckedCategoryCodes }) {
  const [isChecked, setIsChecked] = useState(false);

  const handleCheckButtonClick = ({ target }) => {
    setIsChecked(!isChecked);

    const code = target.closest("li").dataset.code;
    if (!checkedCategoryCodes.includes(code)) {
      setCheckedCategoryCodes([...checkedCategoryCodes, code]);

      return;
    }
    setCheckedCategoryCodes(checkedCategoryCodes.filter((element) => element !== code));
  };

  return (
    <Container onClick={handleCheckButtonClick} data-code={category.categoryCode}>
      <CheckBox isChecked={isChecked} size="18px" />
      <span>{category.name}</span>
    </Container>
  );
}

export default CategoryList;
