import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import styled from "styled-components";
import ProductItem from "../../components/ProductItem";
import BASE_URL from "../../constants/BASE_URL";

const Container = styled.div`
  margin-top: 50px;
  margin-bottom: 75px;
  position: relative;
  font-size: 14px;
  font-weight: 500;

  .product_list {
    width: 100%;
    display: grid;
    grid-template-columns: repeat(auto-fill, 240px);
    gap: 31px 18px;
  }

  .product_list_header {
    margin-left: 10px;
  }
`;

const PageHeader = styled.h3`
  margin-top: 50px;
  margin-bottom: 50px;
  font-weight: 500;
  font-size: 28px;
  color: rgb(51, 51, 51);
  line-height: 35px;
  text-align: center;

  .search_keyword {
    color: #ff6767;
  }
`;

function Search() {
  const location = useLocation();
  const [data, setData] = useState("");

  useEffect(() => {
    const getData = async () => {
      try {
        const { data } = await axios.get(`${BASE_URL}/search${location.search}`);

        setData(data);
      } catch (error) {
        console.log(`Error: ${error}`);
      }
    };

    getData();
  }, [location]);

  return (
    <Container>
      <PageHeader>
        '<span className="search_keyword">{decodeURIComponent(location.search.slice(9))}</span>'에 대한 검색결과
      </PageHeader>
      <div className="product_list_header">
        <div className="product_list_count">{`총 ${data && data.data.length}건`}</div>
      </div>
      <div className="product_list">
        {data &&
          data.data.map((element) => (
            <ProductItem id={element.id} imgUrl={element.productImageDtos[0]?.imgUrl} name={element.name} price={element.price} key={Math.random()} />
          ))}
      </div>
    </Container>
  );
}

export default Search;
