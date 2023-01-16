import { useState } from "react";
import styled from "styled-components";

import ReviewModal from "./ReviewModal";
import { ReactComponent as Star } from "../../../assets/star.svg";

const Container = styled.div`
  width: 100%;
`;

const Header = styled.div`
  padding: 72px 10px 10px 10px;
  border-bottom: 1px solid #b3b3b3;
  display: flex;
  justify-content: space-between;

  .header_text {
    font-size: 20px;
  }

  .filter_buttons {
    display: flex;
    align-items: end;

    div {
      cursor: pointer;
      margin-left: 10px;
    }
  }
`;

const ReviewListContainer = styled.div`
  display: flex;
  min-height: 100px;
  margin: 20px;
  border-bottom: 1px solid #ddd;

  .reviewer_info {
    width: 120px;
  }

  .review-content {
    margin-bottom: 10px;
    width: 100%;
  }

  .review-image {
    width: 124px;
    height: 124px;
    margin-right: 10px;
  }
`;

function Review() {
  const [modalOpen, setModalOpen] = useState(false);

  const productReviewDtos = [
    {
      username: "닉네임1",
      createdAt: "2023.01.01",
      point: 4,
      content: "상품 정말 너무 좋네요. 완전 잘쓰고 있어요 배송 늦어서 별 하나 뺌",
      image: null,
    },
    {
      username: "닉네임2",
      createdAt: "2023.01.01",
      point: 5,
      content: "상품 정말 너무 좋네요. 완전 잘쓰고 있어요 배송 늦어서 별 하나 안뺌",
      image: [
        "https://img-cf.kurly.com/shop/data/goods/1657528646107l0.jpg",
        "https://s3.amazonaws.com/static.neostack.com/img/react-slick/abstract04.jpg",
        "https://s3.amazonaws.com/static.neostack.com/img/react-slick/abstract03.jpg",
      ],
    },
  ];

  return (
    <Container>
      <Header>
        <div id="review" className="header_text">
          상품 후기
        </div>
        <div className="filter_buttons">
          <div>최신순</div>
          <div>별점 높은순</div>
          <div>별점 낮은순</div>
        </div>
      </Header>
      {productReviewDtos.map((review, index) => {
        return (
          <ReviewListContainer key={index}>
            <div className="reviewer_info">
              <div>{review.username}</div>
              <div>{review.createdAt}</div>
            </div>

            <div className="review-content">
              <div>상품명</div>

              {new Array(5)
                .fill(null)
                .map((_, index) => index < review.point)
                .map((point) =>
                  point ? <Star width="20px" fill="#ff6767" key={Math.random()} /> : <Star width="20px" fill="#ddd" key={Math.random()} />
                )}

              <div className="review-content">{review.content}</div>

              {review.image?.map((src) => (
                <img className="review-image" src={src} onClick={() => setModalOpen(true)} key={Math.random()} />
              ))}
            </div>
            <ReviewModal modalOpen={modalOpen} setModalOpen={setModalOpen} />
          </ReviewListContainer>
        );
      })}
    </Container>
  );
}

export default Review;
