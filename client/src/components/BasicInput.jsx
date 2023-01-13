import styled from "styled-components";
// 해당컴포넌트는 div를 감싸서 써야 글씨가 정렬됨
const BasicInput = ({
  children,
  font,
  radius,
  p_height,
  p_width,
  type,
  placeholder,
  defaultValue,
}) => {
  const styles = {
    font,
    radius,
    p_height,
    p_width,
    type,
    placeholder,
    defaultValue,
  };
  return (
    <>
      {/* <CustomLabel></CustomLabel> */}
      <CustomInput {...styles}>{children}</CustomInput>
    </>
  );
};

BasicInput.defaultProps = {};

const CustomInput = styled.input.attrs((props) => ({
  type: props.type,
  placeholder: props.placeholder,
  defaultValue: props.defaultValue,
}))`
  display: inline-block;
  width: 100%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-bottom: 10px;
`;
export default BasicInput;
