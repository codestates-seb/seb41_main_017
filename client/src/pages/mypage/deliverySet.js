import styled from "styled-components";
import Mypagebtn from "../../components/BasicButton";
import CheckBox from "../../components/CheckBox";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import Modalbutton from "../../components/Modalbutton";



const Classlist = styled.div`
  .check_box {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-basis: 60px;
    margin-right:10px;
    label {
      margin: 0;
    }
  }

  .list_content {
    flex: 1;
    .list_in_top{
      padding-bottom: 15px;
      font-size: 15px;
      span{
        color:#FF6767;
        padding-right: 5px;
      }
    }

    .list_in_mid{
      padding-bottom: 15px;
      span{
        padding-right: 15px;
      }
      p{
        padding-top:5px;
      }
    }

    .list_in_footer{

      & span:first-child{
        color:#676767;
      }

      span{
        padding-right: 15px;
      }
    }
  }

  .list_button{
    flex-basis: 120px;
    display:flex;
    justify-content:center;
    align-items:center;
    gap: 5px;
  }
`;

function Addressset() {


  return (
    <Mypagehead title={"배송지 설정"} side_title={<Modalbutton text={"배송지 추가"}/>}>
      <Classlist>
        <ListLayout
          radius={"5"}
          display={"flex"}
          justifyContent={"space-between"}
          padding_width={"10px"}
          padding_height={"20px"}
        >
          <div className="check_box">
            <CheckBox isChecked={true}></CheckBox>
          </div>
          <div className="list_content">
            <div className="list_in_top">
              <span>{"배송지 이름입니다"}</span>
              <Mypagebtn radius={"5"} p_width={"10"} p_height={"1"}>
                {"기본"}
              </Mypagebtn>
            </div>
            <div className="list_in_mid">
              <span>{"최준호"}</span>
              <span>{"010-0000-0000"}</span>
              <p>{"경기도 수원시 권선구 세류1동 256 - 32 201호"}</p>
            </div>
            <div className="list_in_footer">
              <span>{"배송메세지"}</span>
              <span>{"문 앞에 놔주세요"}</span>
            </div>
          </div>
          <div className="list_button">
            <Mypagebtn p_width={"15"} p_height={"5"}>
              수정
            </Mypagebtn>
            <Mypagebtn p_width={"15"} p_height={"5"}>
              삭제
            </Mypagebtn>
          </div>
        </ListLayout>
      </Classlist>
    </Mypagehead>
  );
}

export default Addressset;
