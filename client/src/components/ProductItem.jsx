import styled from "styled-components";

const Item = styled.div`
  font-size: 14px;
  font-weight: 400;
  padding: 10px;

  .name,
  .price {
    margin-bottom: 5px;
  }

  img {
    width: 100%;
    margin-bottom: 6px;
  }
`;

function ProductItem({ element }) {
  return (
    <Item>
      <a>
        <img src={element.image}></img>
        <div>
          <h3 className="name">{element.name}</h3>
          <div className="price">{element.price.toLocaleString()}원</div>
        </div>
      </a>
    </Item>
  );
}

export default ProductItem;
