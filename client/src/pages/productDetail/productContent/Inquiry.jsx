import styled from "styled-components";

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

  tr {
    height: 58px;
    border-bottom: 1px solid #ddd;
  }

  .title,
  .author,
  .created_date,
  .status {
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
    text-align: center;
  }
`;

function Inquiry() {
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
          <TableBody>
            <tr>
              <td className="title">상품 문의 제목 유통기한 언제까지인가요</td>
              <td className="author">작성자</td>
              <td className="created_date">2023.01.01</td>
              <td className="status">확인중</td>
            </tr>
            <tr>
              <td className="title">상품 문의 제목 유통기한 언제까지인가요</td>
              <td className="author">작성자</td>
              <td className="created_date">2023.01.01</td>
              <td className="status">확인중</td>
            </tr>
          </TableBody>
        </InquiryTable>
      </div>
    </>
  );
}

export default Inquiry;
