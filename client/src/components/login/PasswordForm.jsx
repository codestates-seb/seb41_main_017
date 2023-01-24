import styled from "styled-components";

const PasswordBlock = styled.div`
  width: 100%;
  margin: 6px 0;
`;

const PasswordInput = styled.input.attrs({
  type: "password",
})`
  display: inline-block;
  padding: 7px 9px;
  width: 100%;
  height: 50px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-bottom: 5px;
`;

function PasswordForm({ setLoginPassword }) {
  return (
    <PasswordBlock>
      <PasswordInput
        onChange={(e) => setLoginPassword(e.target.value)}
        placeholder="비밀번호를 입력해주세요"
      />
    </PasswordBlock>
  );
}

export default PasswordForm;
