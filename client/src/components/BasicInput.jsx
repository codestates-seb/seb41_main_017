import styled from "styled-components";

const BasicInput = ({
  setValue2,
  setValue,
  star,
  label,
  password,
  address,
  children,
  font,
  radius,
  p_height,
  width,
  height,
  type,
  placeholder,
  defaultValue,
  onChange,
}) => {
  const styles = {
    font,
    radius,
    p_height,
    height,
    width,
    type,
    placeholder,
    defaultValue,
    onChange,
  };

  console.log("@@@@@다시넘어온값:", defaultValue);
  console.log(styles);

  return (
    <Container>
      <div className="LabelContainer">
        <CustomLabel>{label}</CustomLabel>
        <div className="essential">{star}</div>
      </div>
      <CustomInput
        onChange={({ target }) => setValue(target.value)}
        {...styles}
      >
        {children}
      </CustomInput>
      {password === "password" ? (
        <CustomInput
          onChange={({ target }) => setValue2(target.value)}
          {...styles}
          type={"password"}
          placeholder="비밀번호를 확인"
        >
          {children}
        </CustomInput>
      ) : null}
      {address === "address" ? (
        <CustomInput
          onChange={({ target }) => setValue2(target.value)}
          {...styles}
          placeholder="나머지 주소를 입력해주세요"
          width={"100%"}
          defaultValue={""}
        >
          {children}
        </CustomInput>
      ) : null}
    </Container>
  );
};
const Container = styled.div`
  width: 100%;
  margin-bottom: 15px;
  .LabelContainer {
    width: 100%;
    display: flex;
  }
  .essential {
    color: red;
    margin-left: 2px;
  }
`;

const CustomLabel = styled.label`
  display: flex;
  height: 20px;
  font-size: 16px;
`;

const CustomInput = styled.input.attrs((props) => ({
  type: props.type,
  placeholder: props.placeholder,
  defaultValue: props.defaultValue,
  width: props.width,
  onChange: props.onChange,
}))`
  display: inline-block;
  width: ${(props) => props.width};
  height: ${(props) => props.height};
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
