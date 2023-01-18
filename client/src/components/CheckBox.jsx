import styled from "styled-components";
import { ReactComponent as CheckBoxIcon } from "../assets/check-box-icon.svg";

const Label = styled.label`
  margin-right: 12px;

  svg {
    width: ${({ size }) => size};
    height: ${({ size }) => size};

    g {
      fill: ${({ isChecked }) => (isChecked ? "#fd6c40" : "none")};
      stroke: ${({ isChecked }) => (isChecked ? "#ffffff" : "#DDD")};
    }

    circle {
      stroke: ${({ isChecked }) => (isChecked ? "#fd6c40" : "#DDD")};
    }
  }
`;

function CheckBox({ isChecked, size = "35px" }) {
  return (
    <Label isChecked={isChecked} size={size}>
      <CheckBoxIcon></CheckBoxIcon>
    </Label>
  );
}

export default CheckBox;
