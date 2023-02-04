import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import BasicButton from "../../../components/BasicButton";
import axios from "axios";

import InquiryDetail from "./InquiryDetail";
import ModalComponent from "./ModalComponent";
import CreateInquiry from "./CreateInquiry";
import Pagination from "../../../components/Pagination";
import icon from "../../../assets/docs-icon.png";

const Header = styled.div`
  padding: 72px 10px 10px 10px;
  display: flex;
  justify-content: space-between;

  .header_text {
    font-size: 20px;
  }
`;

const InquiryTable = styled.table`
  width: 100%;
`;

const TableHead = styled.thead`
  height: 58px;
  border-top: 2px solid #333;
  border-bottom: 1px solid #333;
  font-weight: 600;

  tr {
    height: 58px;
    border-bottom: 1px solid #ddd;
  }

  .title {
    vertical-align: middle;
  }

  .author,
  .created_date,
  .status {
    width: 140px;
    vertical-align: middle;
  }
`;

const TableBody = styled.tbody`
  font-weight: 500;
  letter-spacing: -0.5px;
  color: #333;

  tr {
    height: 58px;
    border-bottom: 1px solid #ddd;
  }

  .title {
    padding-left: 10px;
    cursor: pointer;
  }

  .title,
  .author,
  .created_date,
  .status {
    vertical-align: middle;
  }

  .author,
  .created_date,
  .status {
    width: 140px;
    text-align: center;
  }
`;

const WriteInquiryButtonWrapper = styled.div`
  margin: 20px;
  display: flex;
  justify-content: flex-end;
`;

const NoInquiryDataWrapper = styled.div`
  margin: 100px 300px 0px 300px;
  opacity: 0.3;

  .text {
    margin-top: 20px;
    text-align: center;
  }
`;

function Inquiry({ data }) {
  const [isOpen, setIsOpen] = useState(false);
  const [inquiryData, setInquiryData] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const { id } = useParams();

  useEffect(() => {
    (async () => {
      const { data } = await axios.get(`${process.env.REACT_APP_URL}/product/${id}/inquiry?page=${currentPage}`);

      setInquiryData(data);
    })();
  }, [currentPage]);

  return (
    <div id="inquiry">
      <Header>
        <div className="header_text">상품 문의</div>
      </Header>
      <div>
        <InquiryTable>
          <TableHead>
            <tr>
              <th className="title">제목</th>
              <th className="author">작성자</th>
              <th className="created_date">작성일</th>
              <th className="status">답변 상태</th>
            </tr>
          </TableHead>
          <TableBody>
            {inquiryData.data && inquiryData.data.map((element, index) => <InquiryDetail data={data} element={element} key={index} />)}
          </TableBody>
        </InquiryTable>
        {inquiryData.data && inquiryData.data.length ? null : (
          <NoInquiryDataWrapper>
            <img src={icon} />
            <div className="text">상품 문의가 없습니다.</div>
          </NoInquiryDataWrapper>
        )}
        <Pagination pageInfo={inquiryData.pageInfo} currentPage={currentPage} setCurrentPage={setCurrentPage} scrollTop={false} />
        <WriteInquiryButtonWrapper>
          <BasicButton children={"문의하기"} p_width={15} p_height={10} onClick={() => setIsOpen(true)} />
        </WriteInquiryButtonWrapper>
        {isOpen ? (
          <ModalComponent
            component={
              <CreateInquiry id={data.data.id} imgUrl={data.data.productImageDtos?.[0]?.imgUrl} name={data.data.name} setIsOpen={setIsOpen} />
            }
          />
        ) : null}
      </div>
    </div>
  );
}

export default Inquiry;
