import React from "react";
import styled from "styled-components";

const Nav = styled.nav`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const PageUl = styled.ul`
  list-style: none;
  text-align: center;
  border-radius: 5px;
  color: black;
  padding: 1px;
  margin-top: 50px;
  display: inline-block;
  background-color: #ffeaea;
`;

const PageLi = styled.li`
  display: inline-block;
  font-size: 17px;
  padding: 5px;
  border-radius: 5px;
  width: 25px;
  color: ${({ number, currentPage }) => (number && number === currentPage ? "white" : "black")};
  background-color: ${({ number, currentPage }) => (number && number === currentPage ? "#ff6767" : null)};

  &:hover {
    cursor: pointer;
    color: white;
    background-color: #ff6767;
  }
  &:focus::after {
    color: white;
    background-color: #ff6767;
  }
`;

const PageSpan = styled.span`
  &:hover::after,
  &:focus::after {
    border-radius: 100%;
    color: white;
    background-color: #263a6c;
  }
`;

function Pagination({ pageInfo, currentPage, setCurrentPage, scrollTop }) {
  const pageNumbers = Array.from({ length: pageInfo?.totalPages }, (_, index) => index + 1);

  const scroll2Top = () => {
    if (scrollTop) {
      window.scrollTo(0, 0);
    }
  };

  const handlePageButtonClick = (number) => {
    setCurrentPage(number - 1);
    scroll2Top();
  };

  const handleDecreaseButtonClick = () => {
    const decreasedNumber = currentPage - 1;

    if (decreasedNumber < 0) return;

    setCurrentPage(decreasedNumber);
    scroll2Top();
  };

  const handleIncreaseButtonClick = () => {
    const increasedNumber = currentPage + 1;

    if (increasedNumber >= pageInfo.totalPages) return;

    setCurrentPage(increasedNumber);
    scroll2Top();
  };

  return (
    <Nav>
      {pageInfo && pageInfo.totalPages ? (
        <PageUl>
          <PageLi onClick={handleDecreaseButtonClick}>
            <PageSpan>{`<`}</PageSpan>
          </PageLi>
          {pageNumbers.map((number) => (
            <PageLi onClick={() => handlePageButtonClick(number)} number={number} currentPage={currentPage + 1} key={number}>
              <PageSpan>{number}</PageSpan>
            </PageLi>
          ))}
          <PageLi onClick={handleIncreaseButtonClick}>
            <PageSpan>{`>`}</PageSpan>
          </PageLi>
        </PageUl>
      ) : null}
    </Nav>
  );
}

export default Pagination;
