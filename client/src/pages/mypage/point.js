import Mypagehead from "../../components/MypageHead";
import styled from "styled-components";

const Layout = styled.div`
    img{
        width:100%;
    }
`;

function Point() {
  return (
    <Mypagehead title={"내 포인트"} subtitle={"준비 중 입니다..."}>
      <Layout>
        <img
          src="https://www.dhu.ac.kr/HOME/dhubio/data/designImages/dhubio201912162141475df77b8b6f60a"
          alt="준비중 페이지"
        ></img>
      </Layout>
    </Mypagehead>
  );
}

export default Point;
