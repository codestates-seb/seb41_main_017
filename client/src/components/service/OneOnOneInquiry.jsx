import styled from "styled-components";
import BasicInput from "../BasicInput";
import BasicButton from "../BasicButton";
const InquiryCotainer = styled.div`
  width: 900px;

  h2 {
    font-size: 20px;
    width: 100%;
  }
  .content_container {
    width: 100%;
    margin-top: 20px;
  }

  .btn_container {
    display: flex;
    align-item: center;
    justify-content: right;
  }
`;

function OneOnOneInquiry() {
  return (
    <InquiryCotainer>
      <div className="content_container">
        <h2>문의 작성</h2>
      </div>
      <div className="content_container">
        <BasicInput
          label={"글 제목"}
          width={"100%"}
          star={"*"}
          placeholder="제목을 입력해주세요"
        ></BasicInput>
      </div>
      <div className="content_container">
        <BasicInput
          label={"글 내용"}
          width={"100%"}
          star={"*"}
          placeholder="내용을 입력해주세요"
        ></BasicInput>
      </div>
      <div className="btn_container">
        <BasicButton p_width={"20"} p_height={"7"}>
          문의 작성
        </BasicButton>{" "}
      </div>
    </InquiryCotainer>
  );
}

export default OneOnOneInquiry;
