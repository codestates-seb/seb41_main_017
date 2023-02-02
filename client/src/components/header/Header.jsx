import axios from "axios";
import styled from "styled-components";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

import { BsFillPersonFill, BsCart4, BsList, BsSearch } from "react-icons/bs";
import logo from "../../assets/fitlogo.png";

const Layout = styled.div`
  width: 100%;
  margin: 0 auto;
  height: 158px;

  .flex {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .top {
    padding: 7px 10px;
    gap: 15px;
    justify-content: flex-end;
    background-color: #ffc3b1;
  }

  .mid {
    margin-top: 20px;

    .logo {
      margin-right: 10px;
      img {
        width: 100px;
        margin-left: -70px;
      }
    }

    .myIcons {
      gap: 10px;
    }

    .icons {
      display: flex;
      flex-direction: column;
      align-items: center;

      .icon {
        font-size: 30px;
        margin-bottom: 2px;
      }
    }

    .serach {
      display: flex;
      border: 1px solid #ffc3b1;
      width: 500px;
      height: 48px;
      border-radius: 10px;
      justify-content: space-around;
      align-items: center;
      margin: 0 10px;

      input {
        flex: 5;
        border: none;
        margin-left: 10px;
      }

      button {
        flex: 1;
        font-size: 20px;
        color: #ffc3b1;
      }
    }
  }

  .bottom {
    // border-bottom: 1px solid #a9a9a9;
    box-shadow: 0 3px 4px 0 rgb(0 0 0 / 7%);

    margin-top: 20px;

    .GNB {
      width: 1050px;
      margin: 0 auto;
      // flex: 1;

      ul {
        display: flex;
        justify-content: space-around;
        align-items: center;

        li {
          flex: 1;
          text-align: center;
          padding: 5px 5px 5px 0;

          .home {
            color: ${({ pathname }) => (pathname === "/" ? "#ff6767" : null)};
          }

          .new_product {
            color: ${({ pathname }) => (pathname.includes("/newproduct") ? "#ff6767" : null)};
          }

          .best_products {
            color: ${({ pathname }) => (pathname.includes("/bestproducts") ? "#ff6767" : null)};
          }

          span {
            &:hover {
              color: #ff6767;
            }
          }
        }

        .category_container {
          display: flex;
          align-items: center;
          cursor: pointer;

          &:hover .category {
            color: #ff6767;
          }

          &:hover .drop_down_container {
            display: block;
          }

          .drop_down_container {
            max-height: calc(95vh - 55px);
            min-height: 200px;
            position: absolute;
            display: flex;
            top: 146px;
            padding-top: 10px;
            display: none;

            .drop_down {
              position: relative;
              z-index: 21;
              border: 1px solid rgb(221, 221, 221);
              background-color: rgb(255, 255, 255);
              display: flex;
            }

            .category_detail {
              background-color: #f1f3f5;

              ul {
                background-color: #f1f3f5;
              }
            }

            ul {
              overflow-y: auto;
              background-color: rgb(255, 255, 255);
              cursor: pointer;
              display: flex;
              flex-direction: column;

              li {
                padding: 0px !important;
                width: 247px;
                text-align: start;

                a {
                  padding: 10px 0px 10px 15px;
                  width: 100%;
                  height: 100%;
                  display: block;
                }

                &:hover a {
                  color: #ff6767;
                  background-color: #f1f3f5;
                }
              }
            }
          }
        }
      }
    }
  }
`;

const CategoryList = styled.li`
  color: ${({ ishover }) => (ishover ? "#ff6767" : null)};
  background-color: ${({ ishover }) => (ishover ? "#f1f3f5" : null)};

  a {
    padding: 10px 0px 10px 15px;
    width: 100%;
    height: 100%;
    display: block;
    color: ${({ ishover }) => (ishover ? "#ff6767" : null)};
  }
`;

function Header() {
  const { pathname } = useLocation();
  const navigate = useNavigate();
  const [searchText, setSearchText] = useState("");
  const [categories, setCategories] = useState([]);
  const [categoryDetails, setCategoryDetails] = useState([]);
  const [categoryDetailCodes, setCategoryDetailCodes] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(null);
  const isLogin = localStorage.getItem("token");


  const handleSearchProductSubmit = (event) => {
    event.preventDefault();
    const text = searchText.trim();

    if (text === "") return;

    navigate(`/search?keyword=${text}`);
  };

  useEffect(() => {
    const getCategories = async () => {
      const { data } = await axios.get(`${process.env.REACT_APP_URL}/category`);

      return data;
    };

    const getCategoryDetails = async (codes) => {
      const data = await Promise.all(codes.map((code) => axios.get(`${process.env.REACT_APP_URL}/category/categorydetail/${code}`)));

      return data;
    };

    (async () => {
      try {
        const data = await getCategories();
        setCategories(data);

        const codes = data.data.map((element) => element.categoryCode);
        const categoryDetails = await getCategoryDetails(codes);
        setCategoryDetails(categoryDetails);
      } catch (error) {
        console.error(error);
      }
    })();
  }, []);

  const handleCategoriesMouseOver = ({ target }) => {
    setCategoryDetailCodes(categoryDetails[target.dataset.index].data.data[0].categoryDetails);
    setCurrentIndex(target.dataset.index);
  };

  const handleCategoriesMouseLeave = () => {
    setCategoryDetailCodes([]);
    setCurrentIndex(null);
  };

  const handleClickLogoutBtn = (e) => {
    e.preventDefault();
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <Layout pathname={pathname}>
      <div className="top flex">
        <div>
          {isLogin ? (
            <a onClick={handleClickLogoutBtn} href="/">
              로그아웃
            </a>
          ) : (
            <a href="/login">로그인 / 회원가입</a>
          )}
        </div>

        <div>
          <a href="/service">고객센터</a>
        </div>
      </div>
      <div className="mid flex">
        <div className="logo">
          <a href="/">
            <img src={logo} alt="logo"></img>
          </a>
        </div>
        <form className="serach" onSubmit={handleSearchProductSubmit}>
          <input placeholder="검색어를 입력해주세요" onChange={({ target }) => setSearchText(target.value)}></input>
          <button>
            <BsSearch />
          </button>
        </form>
        <div className="myIcons flex">
          <div className="icons">
            <a href="/mypage/userInfo">
              <BsFillPersonFill className="icon" />
            </a>
          </div>
          <div className="icons">
            <a href="/cart">
              <BsCart4 className="icon" />
            </a>
          </div>
        </div>
      </div>
      <div className="bottom flex">
        <div className="GNB">
          <ul>
            <li className="category_container">
              <span className="category">
                <BsList size="25" />
              </span>
              <span className="category">카테고리</span>
              <div className="drop_down_container">
                <div className="drop_down" onMouseLeave={handleCategoriesMouseLeave}>
                  <div>
                    <ul>
                      {categories.data &&
                        categories.data.map((category, index) => (
                          <CategoryList onMouseOver={handleCategoriesMouseOver} data-index={index} ishover={index == currentIndex} key={index}>
                            {
                              <Link to={"/category/" + category.categoryCode} data-index={index}>
                                {category.name}
                              </Link>
                            }
                          </CategoryList>
                        ))}
                    </ul>
                  </div>
                  <div className="category_detail">
                    <ul>
                      {categoryDetailCodes.map((category, index) => (
                        <li key={index}>
                          {
                            <Link to={"/category/" + category.categoryDetailCode} data-index={index}>
                              {category.name}
                            </Link>
                          }
                        </li>
                      ))}
                    </ul>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <a href="/">
                <span className="home">홈</span>
              </a>
            </li>
            <li>
              <a href="/collections/newproduct">
                <span className="new_product">신상품</span>
              </a>
            </li>
            <li>
              <a href="/collections/bestproducts">
                <span className="best_products">베스트</span>
              </a>
            </li>
            <li>이벤트</li>
          </ul>
        </div>
      </div>
    </Layout>
  );
}

export default Header;
