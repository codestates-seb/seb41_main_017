import { useState } from "react";
import styled from "styled-components";

const InfoCheckContainer = styled.div`
  margin-top: 30px;
  width: 90%;
  border-bottom: 1px solid black;

  h2 {
    font-size: 16px;
    padding: 5px;
  }
`;

const InfoCheckBox = styled.div`
  .all_check {
    margin-left: 10px;
    margin-top: 20px;
  }

  .check_one {
    margin-left: 20px;
    margin-top: 10px;
  }
  .check_two {
    margin-left: 20px;
    margin-top: 5px;
  }
  .check_three {
    margin-left: 20px;
    margin-top: 5px;
  }
`;

function InfoCheck({ setReject, reject }) {
  const [allChecked, setAllChecked] = useState(false);
  const [termsChecked, setTermsChecked] = useState(false);
  const [privacyChecked, setPrivacyChecked] = useState(false);
  const [serviceChecked, setServiceChecked] = useState(false);

  function handleAllChecked() {
    setAllChecked(!allChecked);
    setTermsChecked(!allChecked);
    setPrivacyChecked(!allChecked);
    setServiceChecked(!allChecked);
    setReject(!reject);
  }

  return (
    <>
      <InfoCheckContainer>
        <h2>개인정보 수집 / 제공</h2>
      </InfoCheckContainer>
      <InfoCheckBox>
        <div className="all_check">
          <input
            type="checkbox"
            checked={allChecked}
            onChange={handleAllChecked}
          />
          <span className="CheckboxText"> 전체 동의하기</span>
        </div>

        <div className="check_one">
          <input
            type="checkbox"
            checked={termsChecked}
            onChange={() => setTermsChecked(!termsChecked)}
          />
          <span className="CheckboxText"> 이용약관 동의하기(필수)</span>
        </div>

        <div className="check_two">
          <input
            type="checkbox"
            checked={privacyChecked}
            onChange={() => setPrivacyChecked(!privacyChecked)}
          />
          <span className="CheckboxText"> 개인정보 수집 이용동의(필수)</span>
        </div>
        <div className="check_three">
          <input
            type="checkbox"
            checked={serviceChecked}
            onChange={() => setServiceChecked(!serviceChecked)}
          />
          <span className="CheckboxText">
            {" "}
            전자지급 결제대행 서비스 약관 동의(필수)
          </span>
        </div>
      </InfoCheckBox>
    </>
  );
}

export default InfoCheck;
