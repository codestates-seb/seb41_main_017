import styled from "styled-components";
import { BsFillPersonFill, BsCart4, BsList, BsSearch } from "react-icons/bs";
import { useLocation } from "react-router-dom";

const Layout = styled.div`
  // 화면사이즈 수정 ---
  width: 100%;
  margin: 0 auto;
  // 화면사이즈 수정 ---
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
        width: 40px;
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
      width: 400px;
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
    border-bottom: 1px solid #a9a9a9;
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
            color: ${({ pathname }) => (pathname.includes("/new-product") ? "#ff6767" : null)};
          }

          .best_product {
            color: ${({ pathname }) => (pathname.includes("/best-product") ? "#ff6767" : null)};
          }

          span {
            &:hover {
              color: #ff6767;
            }
          }
        }

        .category {
          display: flex;
          align-items: center;
        }
      }
    }
  }
`;

function Header() {
  const { pathname } = useLocation();

  return (
    <Layout pathname={pathname}>
      <div className="top flex">
        <div>
          <a href="/login">로그인 / 회원가입</a>
        </div>

        <div>
          <a href="/service">고객센터</a>
        </div>
      </div>
      <div className="mid flex">
        <div className="logo">
          <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/DaangnMarket_logo.png/800px-DaangnMarket_logo.png" alt="logo"></img>
        </div>
        <div className="serach">
          <input placeholder="검색어를 입력해주세요"></input>
          <button>
            <BsSearch />
          </button>
        </div>
        <div className="myIcons flex">
          <div className="icons">
            <a href="/mypage">
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
            <li className="category">
              <span>
                <BsList size="25" />
              </span>
              <span>카테고리</span>
            </li>
            <li>
              <a href="/">
                <span className="home">홈</span>
              </a>
            </li>
            <li>
              <a href="/new-product">
                <span className="new_product">신상품</span>
              </a>
            </li>
            <li>
              <a href="/best-product">
                <span className="best_product">베스트</span>
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
