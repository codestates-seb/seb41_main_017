import { BiHeartCircle } from "react-icons/bi";

function ProductHeader({ data }) {
  return (
    <>
      <div className="shipping">{data.data && data.data.shipping}</div>

      <div className="title-container">
        <h1>{data.data && data.data.name}</h1>
        <BiHeartCircle size="40px" />
      </div>

      <h2 className="content">{data.data && data.data.content}</h2>

      <h2 className="price">
        <span className="number">{data.data && data.data.price.toLocaleString()}</span>
        <span className="unit">원</span>
      </h2>
    </>
  );
}

export default ProductHeader;
