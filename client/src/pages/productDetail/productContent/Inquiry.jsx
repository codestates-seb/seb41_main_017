import { useState } from "react";
import styled from "styled-components";
import BasicButton from "../../../components/BasicButton";

import InquiryDetail from "./InquiryDetail";
import ModalComponent from "./ModalComponent";
import CreateInquiry from "./CreateInquiry";

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

function Inquiry({ data }) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <>
      <Header>
        <div id="inquiry" className="header_text">
          상품 문의
        </div>
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
          <TableBody>{data.data && data.data.productInquiryDtos.map((element, index) => <InquiryDetail element={element} key={index} />)}</TableBody>
        </InquiryTable>
        <WriteInquiryButtonWrapper onClick={() => setIsOpen(true)}>
          <BasicButton children={"문의하기"} p_width={15} p_height={10} />
        </WriteInquiryButtonWrapper>
        {isOpen ? <ModalComponent component={<CreateInquiry data={data} setIsOpen={setIsOpen} />} /> : null}
      </div>
    </>
  );
}

export default Inquiry;
