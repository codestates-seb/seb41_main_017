import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";

import ReviewModal from "./ReviewModal";
import ModalComponent from "./ModalComponent";
import { ReactComponent as Star } from "../../../assets/star.svg";
import Pagination from "../../../components/Pagination";
import icon from "../../../assets/docs-icon.png";

const Header = styled.div`
  padding: 72px 10px 10px 10px;
  border-bottom: 2px solid black;
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
    cursor: pointer;
  }
`;

const FilterList = styled.li.attrs(({ dataId }) => ({
  "data-id": dataId,
}))`
  margin-left: 8px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  font-size: 14px;
  color: rgb(153, 153, 153);
  cursor: pointer;

  color: ${({ dataId, sort }) => (dataId === sort ? "#ff6767" : "rgb(153, 153, 153)")};
`;

const NoReviewDataWrapper = styled.div`
  margin: 100px 300px 100px 300px;
  opacity: 0.3;

  .text {
    margin-top: 20px;
    text-align: center;
  }
`;

function Review({ productName }) {
  const [modalOpen, setModalOpen] = useState(false);
  const [sort, setSort] = useState("newest");
  const [data, setData] = useState(null);
  const [currentPage, setCurrentPage] = useState(0);
  const { id } = useParams();

  useEffect(() => {
    (async () => {
      const query = {
        sorted_type: sort,
        page: currentPage,
      };
      const queryString = Object.entries(query)
        .map(([key, value]) => `${key}=${value}`)
        .join("&");
      const { data } = await axios.get(`${process.env.REACT_APP_URL}/product/${id}/review?${queryString}`);

      setData(data);
    })();
  }, [currentPage, sort]);

  const handleFilterButtonClick = ({ target }) => {
    const id = target.closest("li")?.dataset.id;

    if (!id) return;

    setSort(target.closest("li").dataset.id);
  };

  return (
    <div id="review">
      <Header>
        <div className="header_text">상품 후기</div>
        <div className="filter_buttons" onClick={handleFilterButtonClick}>
          <FilterList dataId="newest" sort={sort}>
            최신순
          </FilterList>
          <FilterList dataId="higher" sort={sort}>
            별점 높은순
          </FilterList>
          <FilterList dataId="lower" sort={sort}>
            별점 낮은순
          </FilterList>
        </div>
      </Header>
      {data
        ? data.data.map((review, index) => {
            return (
              <ReviewListContainer key={index}>
                <div className="reviewer_info">
                  <div>{review.modifiedBy}</div>
                  <div>{review.modifiedAt.slice(0, 10)}</div>
                </div>

                <div className="review-content">
                  <div>{productName}</div>

                  {new Array(5)
                    .fill(null)
                    .map((_, index) => index < review.reviewStar)
                    .map((point) =>
                      point ? <Star width="20px" fill="#ff6767" key={Math.random()} /> : <Star width="20px" fill="#ddd" key={Math.random()} />
                    )}

                  <div className="review-content">{review.content}</div>

                  {review.productReviewImageDtos.map((src) => (
                    <img className="review-image" src={src.imgUrl} onClick={() => setModalOpen(true)} key={Math.random()} />
                  ))}
                </div>
                {modalOpen ? (
                  <ModalComponent
                    component={
                      <ReviewModal
                        setModalOpen={setModalOpen}
                        srcArr={review.productReviewImageDtos.map(({ imgUrl }) => imgUrl)}
                        content={review.content}
                        modifiedBy={review.modifiedBy}
                        modifiedAt={review.modifiedAt}
                        reviewStar={review.reviewStar}
                      />
                    }
                  />
                ) : null}
              </ReviewListContainer>
            );
          })
        : null}
      {data && data.data.length ? null : (
        <NoReviewDataWrapper>
          <img src={icon} />
          <div className="text">상품 후기가 없습니다.</div>
        </NoReviewDataWrapper>
      )}
      <Pagination pageInfo={data && data.pageInfo} currentPage={currentPage} setCurrentPage={setCurrentPage} scrollTop={false} />
    </div>
  );
}

export default Review;
