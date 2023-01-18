import React, { useState } from "react";
import styled from "styled-components";

const GenderRadio = ({ setCheck }) => {
  const [select, setSelect] = useState("male");
  setCheck(select);
  const handleSelectChange = (event) => {
    const value = event.target.value;
    setSelect(value);
  };
  return (
    <Wrapper>
      <Item>
        <RadioButton
          type="radio"
          name="radio"
          value="male"
          checked={select === "male"}
          onChange={(event) => handleSelectChange(event)}
        />
        <RadioButtonLabel />
        <div>남자</div>
      </Item>
      <Item>
        <RadioButton
          type="radio"
          name="radio"
          value="female"
          checked={select === "female"}
          onChange={(event) => handleSelectChange(event)}
        />
        <RadioButtonLabel />
        <div>여자</div>
      </Item>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  //   padding: 0px 16px 24px 16px;
  box-sizing: border-box;
`;

const Item = styled.div`
  display: flex;
  align-items: center;
  height: 48px;
  position: relative;
`;

const RadioButtonLabel = styled.label`
  position: absolute;
  top: 25%;
  left: 4px;
  width: 15px;
  height: 15px;
  margin-top: 3px;
  border-radius: 50%;
  background: white;
  border: 1px solid #bebebe;
  display: flex;
  align-items: center;
`;
const RadioButton = styled.input`
  opacity: 0;
  z-index: 1;
  border-radius: 50%;
  width: 24px;
  height: 24px;
  margin-right: 6px;

  &:hover ~ ${RadioButtonLabel} {
    background: #bebebe;
  }
  ${(props) =>
    props.checked &&
    ` 
    &:checked + ${RadioButtonLabel} {
      background: #C26D53;
      
    }
  `}
`;

export default GenderRadio;
