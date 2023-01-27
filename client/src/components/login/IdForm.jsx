import styled from "styled-components";

const EmailForm = ({ setLoginId }) => {
  return (
    <EmailBlock>
      <EmailInput
        onChange={(e) => setLoginId(e.target.value)}
        placeholder="아이디를 입력해주세요"
      />
    </EmailBlock>
  );
};

const EmailBlock = styled.div`
  margin: 6px 0;
  width: 100%;
`;

const EmailInput = styled.input.attrs({
  type: "text",
  require,
})`
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
`;

export default EmailForm;
