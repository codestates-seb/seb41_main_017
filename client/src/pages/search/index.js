import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
import styled from "styled-components";

import Pagination from "../../components/Pagination";
import ProductItem from "../../components/ProductItem";

import productNone from "../../assets/product_none.png";

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

  .category_details {
    display: grid;
    grid-template-columns: repeat(4, 180px);
    gap: 16px 83px;
    overflow: hidden;
    margin-top: 28px;
    padding: 30px 40px;
    border: 1px solid rgb(226, 226, 226);
    line-height: 20px;
    font-size: 15px;
    text-align: start;
  }
`;

const CategoryList = styled.li`
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

  a {
    color: ${({ datacode, code }) => (datacode === code ? "#ff6767" : "black")};
    font-weight: ${({ datacode, code }) => (datacode === code ? 700 : 400)};

    &:hover {
      color: #ff6767;
      font-weight: 700;
    }
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

const ProductNoneImageWrapper = styled.div`
  margin-top: 50px;
  text-align: center;
`;

function Search() {
  const location = useLocation();
  const { code } = useParams();

  const [data, setData] = useState("");
  const [categoryData, setCategoryData] = useState([]);
  const [sort, setSort] = useState("newest");
  const [currentPage, setCurrentPage] = useState(0);

  useEffect(() => {
    const query = {
      size: 20,
      page: currentPage,
      sorted_type: sort,
    };
    const queryString = Object.entries(query).reduce((acc, [key, value]) => (value ? `${acc}&${key}=${value}` : acc), "");
    const getProductData = async () => {
      const { data } = await axios.get(`${process.env.REACT_APP_URL}${location.pathname}${!!location.search ? location.search : "?"}${queryString}`);

      return data;
    };

    const getCategoryData = async () => {
      const { data } = await axios.get(`${process.env.REACT_APP_URL}/category/categorydetail/${code.slice(0, 3)}`);

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

        setData(sortedData);

        if (code) {
          const categoryData = await getCategoryData();
          setCategoryData(categoryData);
        }
      } catch (error) {
        console.error(error);
      }
    })();
  }, [location, sort, currentPage]);

  const handleSortListClick = ({ target }) => {
    const id = target.closest("li")?.dataset.id;

    if (!id) return;

    setSort(target.closest("li").dataset.id);
  };

  return (
    <Container>
      <PageHeader>
        {location.search ? (
          <div>
            '<span className="search_keyword">{decodeURIComponent(location.search.slice(9))}</span>'에 대한 검색결과
          </div>
        ) : (
          <>
            <div>{categoryData.data && categoryData.data[0].name}</div>
            <ul className="category_details">
              <CategoryList code={code} datacode={categoryData.data && categoryData.data[0].categoryCode}>
                <Link to={`/category/${categoryData.data && categoryData.data[0].categoryCode}`}>전체보기</Link>
              </CategoryList>
              {categoryData.data &&
                categoryData.data[0].categoryDetails.map((category, index) => {
                  return (
                    <CategoryList code={code} datacode={category.categoryDetailCode} key={index}>
                      <Link to={"/category/" + category.categoryDetailCode}>{category.name}</Link>
                    </CategoryList>
                  );
                })}
            </ul>
          </>
        )}
      </PageHeader>
      <div className="product_list_header">
        <div className="product_list_count">{`총 ${data && data.pageInfo.totalElements}건`}</div>
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
      {data && data.data.length ? null : (
        <ProductNoneImageWrapper>
          <img src={productNone} />
        </ProductNoneImageWrapper>
      )}
      <Pagination pageInfo={data && data.pageInfo} currentPage={currentPage} setCurrentPage={setCurrentPage} scrollTop={true} />
    </Container>
  );
}

export default Search;
