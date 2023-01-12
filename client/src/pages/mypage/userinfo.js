import styled from "styled-components";

const Layout = styled.div`
    width: 500px;
    margin: 0 auto;
    text-align:left;

    input{
        width:100%;
        border: 1px solid #727272;
        border-radius:3px;
        height: 35px;
        padding:10px 20px;
        font-size: 15px;
    }

    .user{
    display:flex;
    align-items:center;
    margin:10px;


    .user_Label{
        flex-basis: 100px;
    }
    .user_Input{
          flex:1;
    }

    #userGender{
        width:80px;
        text-align: center;
    }

    #userYears{
        width: 80px;
        margin-right:10px;
        text-align: center;
    }

    .api_btns{
        // 임시 구별 색상
        border: 1px solid #727272; 
        align-self: stretch;
        border-radius:3px;
    }

    .address_Box{
        margin-right: 7px;
        display:flex;
        flex-direction: column;
        gap:5px;

    }

    }

  .btns{
    margin-top: 30px;
    text-align:center;
    button{
        background-color: #FFF7F5;
        border: 1px solid #ff6767;
        color: #ff6767;
        border-radius:4px;
        padding: 10px 30px;
        
    }
  }
`;

function Userinfo() {
  return (
    <Layout>
      <form>
        <div className="user">
          <div className="user_Label"><label htmlFor="userid">아이디</label></div>
          <div className="user_Input"><input type="text" id="userid"/></div>
        </div>
        <div className="user">
          <div className="user_Label"><label htmlFor="userPassword">비밀번호</label></div>
          <div className="user_Input"><input type="text" id="userPassword"/></div>
        </div>
        <div className="user">
          <div className="user_Label"><label htmlFor="userName">이름</label></div>
          <div className="user_Input"><input type="text" id="userName"/></div>
        </div>
        <div className="user">
          <div className="user_Label"><label htmlFor="userEmail">이메일</label></div>
          <div className="user_Input"><input type="text" id="userEmail"/></div>
        </div>
        <div className="user">
          <div className="user_Label"><label htmlFor="userPhone">연락처</label></div>
          <div className="user_Input"><input type="text" id="userPhone"/></div>
        </div>
        <div className="user">
          <div className="user_Label"><label htmlFor="userAddress">주소</label></div>
          <div className="user_Input">
            <div className="address_Box">
                <input type="text" id="userAddress"/>
                <input type="text" id="userAddress"/>
            </div>
          </div>
          <div className="api_btns"><button>배송지 설정</button></div>
        </div>
        <div className="user">
          <div className="user_Label"><label htmlFor="userGender">성별</label></div>
          <div className="user_Input"><input type="text" id="userGender"/></div>
        </div>
        <div className="user">
          <div className="user_Label"><label htmlFor="userYears">생년월일</label></div>
            <div className="user_Input">
                <input type="text" id="userYears"/>
                <input type="text" id="userYears"/>
                <input type="text" id="userYears"/>
            </div>
        </div>
        <div className="btns"><button type="submit">수정하기</button></div>
      </form>
    </Layout>
  );
}

export default Userinfo;
