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
      {data.data &&
        Object.entries(data.data).map((element, index) => {
          const [title, content] = element;
          const productInfoTitle = {
            shipping: "배송",
            seller: "판매자",
            packaging: "포장방식",
            unit: "판매단위",
            weight: "중량/용량",
            countryOfOrigin: "원산지",
            allergyInfo: "알러지 정보",
          };

          if (content && productInfoTitle.hasOwnProperty(title)) {
            return <DataList title={productInfoTitle[title]} content={content} key={index} />;
          }
        })}
    </Container>
  );
}

export default ProductDataList;
