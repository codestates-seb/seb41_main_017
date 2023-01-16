import { useState } from "react";
import styled from "styled-components";

import DeleteButton from "../../../components/DeleteButton";
import { ReactComponent as Star } from "../../../assets/star.svg";

const RootContainer = styled.div`
  position: fixed;
  z-index: 1300;
  inset: 0px;
`;

const RootBackDrop = styled.div`
  opacity: 1;
  transition: opacity 225ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  inset: 0px;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: -1;
`;

const ScrollPaper = styled.div`
  opacity: 1;
  transition: opacity 225ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
  height: 100%;
  outline: 0px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Paper = styled.div`
  display: flex;
  overflow: hidden;
  flex-direction: column;
  max-width: none;
  width: 954px;
  background: transparent;
`;

const Modal = styled.div`
  width: 800px;
  margin: 0px auto;
  background-color: #ffffff;
  border-radius: 12px;
`;

const Header = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-bottom: 8px;
  margin: 29px 30px 20px;
  border-bottom: 1px solid rgb(244, 244, 244);
  letter-spacing: -1px;
  vertical-align: middle;

  span {
    display: inline;
    padding: 0px;
    font-weight: 500;
    font-size: 24px;
    line-height: 30px;
    letter-spacing: -0.5px;
    color: rgb(51, 51, 51);
    vertical-align: baseline;
  }
`;

const ContentRoot = styled.div`
  padding: 0px 30px 30px;
  overflow: hidden;
`;

const ContentContainer = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr auto;
  gap: 20px;
`;

const ImageContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;

  span {
    box-sizing: border-box;
    display: inline-block;
    overflow: hidden;
    width: 375px;
    height: 488px;
    background: none;
    opacity: 1;
    border: 0px;
    margin: 0px;
    padding: 0px;
    position: relative;
  }

  .img-list {
    display: flex;
    justify-content: start;
    align-items: center;
    gap: 2px;
    max-width: 375px;

    button {
      position: relative;
      flex-shrink: 0;
      width: 45px;
      height: 45px;
      overflow: hidden;
    }
  }
`;

const Content = styled.div`
  width: 100%;

  .username {
    margin-top: 5px;
    font-weight: 500;
    font-size: 16px;
    display: flex;
    align-items: center;
  }

  .content {
    max-height: 343px;
    padding: 10px 10px 0px 0px;
    margin-right: -10px;
    overflow: hidden;
    word-break: break-word;
    white-space: pre-line;
    font-weight: 400;
    font-size: 14px;
    line-height: 19px;
    color: rgb(51, 51, 51);
  }

  .footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    padding-top: 14px;

    span {
      font-size: 12px;
      line-height: 16px;
      color: rgb(153, 153, 153);
    }
  }
`;

function ReviewModal({ modalOpen, setModalOpen }) {
  const data = {
    username: "닉네임2",
    srcArr: [
      "https://img-cf.kurly.com/shop/data/goods/1657528646107l0.jpg",
      "https://s3.amazonaws.com/static.neostack.com/img/react-slick/abstract04.jpg",
      "https://s3.amazonaws.com/static.neostack.com/img/react-slick/abstract03.jpg",
    ],
    content: "상품 정말 너무 좋네요. 완전 잘쓰고 있어요 배송 늦어서 별 하나 안뺌",
    createdAt: "2023.01.16",
    point: 4,
  };

  const [imgSrc, setImgSrc] = useState(data && data.srcArr[0]);

  return (
    <>
      {modalOpen && (
        <RootContainer>
          <RootBackDrop />
          <ScrollPaper>
            <Paper>
              <Modal>
                <Header>
                  <span>사진 후기</span>
                  <div onClick={() => setModalOpen(false)}>
                    <DeleteButton />
                  </div>
                </Header>
                <ContentRoot>
                  <ContentContainer>
                    <ImageContainer>
                      <span>
                        <img src={imgSrc} />
                      </span>
                      <div className="img-list">
                        {data.srcArr.map((element, index) => {
                          return (
                            <button key={index}>
                              <img src={element} onClick={({ target }) => setImgSrc(target.src)} />
                            </button>
                          );
                        })}
                      </div>
                    </ImageContainer>
                    <Content>
                      <div className="username">{data.username}</div>
                      {new Array(5)
                        .fill(null)
                        .map((_, index) => index < data.point)
                        .map((point) =>
                          point ? <Star width="20px" fill="#ff6767" key={Math.random()} /> : <Star width="20px" fill="#ddd" key={Math.random()} />
                        )}
                      <p className="content">{data.content}</p>
                      <footer className="footer">
                        <span>{data.createdAt}</span>
                      </footer>
                    </Content>
                  </ContentContainer>
                </ContentRoot>
              </Modal>
            </Paper>
          </ScrollPaper>
        </RootContainer>
      )}
    </>
  );
}

export default ReviewModal;
