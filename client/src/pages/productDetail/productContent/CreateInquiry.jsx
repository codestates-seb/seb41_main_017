import { useState } from "react";
import styled from "styled-components";
import BasicButton from "../../../components/BasicButton";

import DeleteButton from "../../../components/DeleteButton";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 800px;
  height: 690px;
  padding: 30px;
  background: #ffffff;
`;

const Header = styled.div`
  display: flex;
  justify-content: space-between;
  padding-bottom: 22px;
  border-bottom: 1px solid rgb(244, 244, 244);
  letter-spacing: -1px;

  .title {
    font-size: 24px;
    font-weight: 500;
    line-height: 30px;
    color: rgb(51, 51, 51);
  }
`;

const ProductInfo = styled.div`
  display: flex;
  padding: 16px 0px;
  border-bottom: 1px solid rgb(244, 244, 244);

  .product_image {
    flex: 1 1 0%;
    display: flex;
    -webkit-box-pack: start;
    justify-content: flex-start;

    img {
      width: 72px;
      height: 72px;
    }
  }

  .product_title {
    flex: 6.5 1 0%;
    display: flex;
    align-items: center;
    font-size: 16px;
    font-weight: 500;
    line-height: 22px;
    color: rgb(51, 51, 51);
    text-overflow: ellipsis;
  }
`;

const InquiryTitle = styled.div`
  display: flex;
  margin-top: 16px;

  .text {
    width: 100px;
    padding-top: 15px;
    padding-left: 15px;
    font-size: 14px;
    font-weight: 500;
    color: rgb(51, 51, 51);
  }

  .title_input {
    flex: 6.5 1 0%;
    position: relative;
    height: 42px;

    input {
      height: 44px;
      padding: 0px 11px 0px 15px;
      width: 100%;
      border-radius: 4px;
      border: 1px solid rgb(221, 221, 221);
      font-weight: 400;
      font-size: 14px;
      line-height: 40px;
      color: rgb(51, 51, 51);
      ::placeholder {
        color: #cccccc;
      }
    }
  }
`;

const InquiryContent = styled.div`
  display: flex;
  margin-top: 16px;

  .text {
    width: 100px;
    padding-top: 15px;
    padding-left: 15px;
    font-size: 14px;
    font-weight: 500;
    color: rgb(51, 51, 51);
  }

  .text_area {
    flex: 6.5 1 0%;
    position: relative;

    textarea {
      width: 100%;
      height: 260px;
      position: relative;
      display: flex;
      flex-direction: column;
      background-color: rgb(255, 255, 255);
      border: 1px solid rgb(221, 221, 221);
      border-radius: 4px;
      padding: 15px 16px;
      font-size: 14px;
      line-height: 21px;
      z-index: 1;
      color: rgb(51, 51, 51);

      ::placeholder {
        color: #cccccc;
      }
    }
  }
`;

const ButtonWrapper = styled.div`
  display: flex;
  justify-content: center;
  gap: 8px;
  border-top: 1px solid rgb(244, 244, 244);
  text-align: center;
  padding-top: 20px;
  margin-top: 20px;

  a {
    border: ${({ isActive }) => (isActive ? null : "1px solid #ddd")};
    background-color: ${({ isActive }) => (isActive ? null : "#ddd")};
    color: ${({ isActive }) => (isActive ? null : "#ffffff")};
    cursor: ${({ isActive }) => (isActive ? "pointer" : "default")};
  }
`;

function CreateInquiry({ data, setIsOpen }) {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const isActive = title !== "" && content !== "";

  return (
    <Container>
      <Header>
        <div className="title">상품 문의하기</div>
        <div onClick={() => setIsOpen(false)}>
          <DeleteButton />
        </div>
      </Header>

      <ProductInfo>
        <div className="product_image">
          <img src={data.image} />
        </div>
        <div className="product_title">{data.name}</div>
      </ProductInfo>

      <InquiryTitle>
        <div className="text">제목</div>
        <div className="title_input">
          <input placeholder="제목을 입력해주세요." value={title} onChange={({ target }) => setTitle(target.value)}></input>
        </div>
      </InquiryTitle>

      <InquiryContent>
        <div className="text">내용</div>
        <div className="text_area">
          <textarea placeholder="내용을 입력해주세요.(최대 3,000자)" value={content} onChange={({ target }) => setContent(target.value)}></textarea>
        </div>
      </InquiryContent>

      <ButtonWrapper isActive={isActive}>
        <BasicButton children={"문의하기"} font={14} p_width={50} p_height={15} />
      </ButtonWrapper>
    </Container>
  );
}

export default CreateInquiry;
