import styled from "styled-components";

const Container = styled.div`
  margin-top: 20px;

  dl {
    width: 100%;
    display: flex;
    align-items: start;
    padding: 17px 0 18px;
    border-top: 1px solid #f4f4f4;
    font-size: 14px;
    letter-spacing: -0.5px;
  }

  dt {
    color: #666;
    font-weight: 400;
    width: 128px;
    height: 100%;
    line-height: 19px;
  }

  dd {
    display: flex;
    flex: 1;
    flex-direction: column;

    p {
      line-height: 19px;
      white-space: pre-line;
    }
  }
`;

function ProductDataList({ data }) {
  const dataArr = [
    ["배송", data.shipping],
    ["판매자", data.seller],
    ["포장 타입", data.packaging],
    ["판매 단위", data.unit],
    ["중량/용량", data.weight],
    ["원산지", data.countryOfOrigin],
    ["알레르기정보", data.allergyInfo],
  ];

  const DataList = ({ title, content }) => {
    return (
      <dl>
        <dt className="title">{title}</dt>
        <dd>
          <p>{content}</p>
        </dd>
      </dl>
    );
  };

  return (
    <Container>
      {dataArr.map((element, index) => {
        const [title, content] = element;

        return <DataList key={index} title={title} content={content} />;
      })}
    </Container>
  );
}

export default ProductDataList;
