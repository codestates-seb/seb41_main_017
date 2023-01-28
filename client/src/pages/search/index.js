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
    grid-template-columns: repeat(auto-fill, 195px);
    gap: 31px 18px;
  }

  .product_list_header {
    margin-left: 10px;
    display: flex;
    justify-content: space-between;
  }

  .product_filter {
    position: relative;
    display: flex;
    align-items: center;
    user-select: none;
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

const FilterList = styled.li.attrs(({ dataId }) => ({
  "data-id": dataId,
}))`
  margin-left: 8px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  font-size: 14px;
  color: rgb(153, 153, 153);
  cursor: pointer;

  color: ${({ dataId, sort }) => (dataId === sort ? "#ff6767" : "rgb(153, 153, 153)")};
`;

function Search() {
  const location = useLocation();
  const [data, setData] = useState("");
  const [categoryData, setCategoryData] = useState([]);
  const [sort, setSort] = useState("newest");

  useEffect(() => {
    const getProductData = async () => {
      const { data } = await axios.get(`${BASE_URL}${location.pathname}${location.search}`);

      return data;
    };

    const getCategoryData = async () => {
      const { data } = await axios.get(`${BASE_URL}/category/categorydetail/${location.pathname.split("/")[2].slice(3)}`);

      return data;
    };

    const dataSortByPrice = (data) => {
      if (sort === "newest") {
        return data;
      }

      if (sort === "lower") {
        data.data = data.data.sort((a, b) => a.price - b.price);

        return data;
      }

      if (sort === "higher") {
        data.data = data.data.sort((a, b) => b.price - a.price);

        return data;
      }
    };

    (async () => {
      try {
        const productData = await getProductData();
        const sortedData = dataSortByPrice(productData);
        const categoryData = await getCategoryData();

        setData(sortedData);
        setCategoryData(categoryData);
      } catch (error) {
        console.error(error);
      }
    })();
  }, [location, sort]);

  const handleSortListClick = ({ target }) => {
    setSort(target.dataset.id);
  };

  return (
    <Container>
      <PageHeader>
        '<span className="search_keyword">{decodeURIComponent(location.search.slice(9))}</span>'에 대한 검색결과
      </PageHeader>
      <div className="product_list_header">
        <div className="product_list_count">{`총 ${data && data.data.length}건`}</div>
        <ul className="product_filter" onClick={handleSortListClick}>
          <FilterList dataId="newest" sort={sort}>
            신상품순
          </FilterList>
          <FilterList dataId="lower" sort={sort}>
            낮은 가격순
          </FilterList>
          <FilterList dataId="higher" sort={sort}>
            높은 가격순
          </FilterList>
        </ul>
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
