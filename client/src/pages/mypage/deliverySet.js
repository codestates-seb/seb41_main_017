import styled from "styled-components";
import Mypagebtn from "../../components/BasicButton";
import CheckBox from "../../components/CheckBox"

const Layout = styled.div`
  margin: 0 80px;

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 5px;

    .cerate {
      display: flex;
      align-items: center;
      gap: 3px;
      font-size: 15px;
    }
  }
`;

const List = styled.div`
  margin-bottom: 20px;
  border: 1px solid #d7d7d7;
  display: flex;
  align-items: center;
  padding: 10px 20px;

  .save {
    font-size: 30px;
    padding-right: 20px;
  }

  .list_Detail {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items:flex-start;
    justify-content: center;
    padding: 8px 12px;

    .title {
      display: flex;
      gap: 10px;
  
      span {
        color: #ff6767;
        vertical-align: text-bottom;
      }
  
    }


    .user_Info{
      margin-top: 15px;
      margin-bottom: 5px;
      span{
        margin-right: 17px;
      }
    }
    .user_Address{
      font-size: 20px;
    }
    .user_Message{
      margin-top: 15px;
      span{
        margin-right: 17px;
      }
    }
  }
  .list_Btns {
    display: flex;
    gap: 10px;
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
          <Mypagebtn radius={50} p_height={3} p_width={5}>
            +
          </Mypagebtn>
          <h5>배송지 추가</h5>
        </div>
      </div>
      <List>
        <div className="save">
          <CheckBox isChecked={true}></CheckBox>
        </div>
        <div className="list_Detail">
          <div className="title">
            <div>
              <span>배송지 이름입니다</span>
            </div>
            <div>
              <Mypagebtn radius={5} p_width={10} p_height={1}>
                기본
              </Mypagebtn>
            </div>
          </div>
          <div className="user_Info"><span>최준호</span><span>010-000-000</span></div>
          <div className="user_Address"><span>경기도 수원시 권선구 세류1동 256-32 201호</span></div>
          <div className="user_Message">
            <span>배송메세지</span>
            <span>문 앞에 놔주세요</span>
          </div>
        </div>
        <div className="list_Btns">
          <Mypagebtn p_width={10} p_height={4}>
            수정
          </Mypagebtn>
          <Mypagebtn p_width={10} p_height={4}>
            삭제
          </Mypagebtn>
        </div>
      </List>
    </Layout>
  );
}

export default Addressset;
