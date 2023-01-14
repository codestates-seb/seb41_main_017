import { BiHeartCircle } from "react-icons/bi";

function ProductHeader({ data }) {
  return (
    <>
      <div className="shipping">{data.shipping}</div>

      <div className="title-container">
        <h1>{data.name}</h1>
        <BiHeartCircle size="40px" />
      </div>

      <h2 className="content">{data.content}</h2>

      <h2 className="price">
        <span className="number">{data.price.toLocaleString()}</span>
        <span className="unit">원</span>
      </h2>
    </>
  );
}

export default ProductHeader;
