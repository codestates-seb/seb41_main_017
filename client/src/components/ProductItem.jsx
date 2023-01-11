import styled from "styled-components";

const Item = styled.div`
  width: 150px;
  margin: 0 auto;
  padding: 10px;
  font-size: 14px;
  font-weight: 400;

  .name {
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    height: 40px;
    line-height: 20px;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .name,
  .price {
    margin-bottom: 5px;
  }

  img {
    width: 100%;
    height: 150px;
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
