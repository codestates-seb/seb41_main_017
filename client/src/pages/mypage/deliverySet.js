import styled from "styled-components";
import Mypagebtn from "../../components/BasicButton";
import { BsCheckCircle } from "react-icons/bs";

const Layout = styled.div`
  margin: 0 80px;

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 3px;

    .cerate {
        display:flex;
        align-items: center;
        gap: 3px;
        font-size:15px;
    }
  }

`;

const List = styled.div`
    border: 1px solid #d7d7d7;
    display: flex;
    align-items: center;
    padding: 10px 20px;

    .save{
        font-size: 30px;
        padding-right: 20px;
    }

    .listDetail{
        flex:1;
        display:flex;
        flex-direction: column;
        gap: 10px;
    }

    .title{
        display:flex;
        gap:5px;

        span{
            color: red;
            vertical-align: text-bottom;
        }

    }

    .listBtns{
        display:flex;
        gap: 5px;
    }

`;

function Addressset() {
  return (
    <Layout>
      <div className="header">
        <div>
          <span>배송지 설정</span>
        </div>
        <div className="cerate">
          <Mypagebtn  radius={50} p_height={1} p_width={4}>+
          </Mypagebtn>
          <h5>배송지 추가</h5>
        </div>
      </div>
      <List>
          <div className="save">
            <BsCheckCircle />
          </div>
          <div className="listDetail">
            <div className="title">
              <div><span>배송지 이름입니다</span></div>
              <div><Mypagebtn radius={2} p_width={10}>기본</Mypagebtn></div>
            </div>
            <div>최준호 010-000-000</div>
            <div>oo시 oo구 oo동 oooooooo</div>
            <div>
              <div>배송메세지</div>
              <div>문앞에 놔주세요</div>
            </div>
          </div>
          <div className="listBtns">
           <Mypagebtn>수정</Mypagebtn>
           <Mypagebtn>삭제</Mypagebtn>
          </div>
      </List>
    </Layout>
  );
}

export default Addressset;
