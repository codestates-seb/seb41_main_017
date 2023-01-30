import styled from "styled-components";
import logo from "../../../src/assets/logo.jpg";
import githubLogo from "../../assets/github-logo.png";

const Page = styled.div`
  box-shadow: 0 -3px 4px 0 rgb(0 0 0 / 7%);

  flex-basis: 150px;
  .footer_container {
    height: 200px;
    max-width: 1000px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin: 0 auto;

    .left_box {
      .logo {
        width: 150px;
      }
    }

    .team_name {
      .service_box {
        margin-left: 10px;
      }

      .service_number {
        margin-top: 10px;
      }

      .github {
        width: 20px;
        margin-bottom: 10px;
        margin-left: 10 px;
      }

      .top_name {
        display: flex;
        text-align: center;
        font-size: 10px;
        margin-top: 20px;
      }

      .bottom_name {
        display: flex;
        font-size: 10px;
      }

      p {
        margin-bottom: 10px;
        display: flex;
        align-items: center;
        margin-right: 10px;
        margin-left: 10px;
      }
    }
  }
`;

function Footer() {
  return (
    <Page>
      <div className="footer_container">
        <div className="left_box">
          <img className="logo" src={logo}></img>
        </div>
        <div className="right_box">
          <div className="team_name">
            <div className="service_box">
              <div>고객센터</div>
              <div className="service_number">1717-1717</div>
            </div>

            <div className="top_name">
              <p>장준익 </p>
              <a href="https://github.com/JangIkIk">
                <img className="github" src={githubLogo}></img>
              </a>{" "}
              <p>전성훈 </p>
              <a href="https://github.com/jsh3418">
                <img className="github" src={githubLogo}></img>
              </a>{" "}
              <p>최준호 </p>
              <a href="https://github.com/junpotatoes">
                <img className="github" src={githubLogo}></img>
              </a>
            </div>

            <div className="bottom_name">
              <p>김진수 </p>
              <a href="https://github.com/frontLine-kim">
                <img className="github" src={githubLogo}></img>
              </a>{" "}
              <p>배용현 </p>
              <a href="https://github.com/Baeyonghyeon">
                <img className="github" src={githubLogo}></img>
              </a>{" "}
              <p>허석진 </p>
              <a href="https://github.com/sGOM">
                <img className="github" src={githubLogo}></img>
              </a>
            </div>
          </div>
        </div>
      </div>
    </Page>
  );
}

export default Footer;
