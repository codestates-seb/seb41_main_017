import { useEffect, useState } from "react";
import axios from "axios";
import styled from "styled-components";
import BasicButton from "../../components/BasicButton";
import CheckBox from "../../components/CheckBox";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import Postmodal from "../../components/Postmodal";
import Guidance from "../../components/Guidance";
import PatchModal from "../../components/PatchModal";




const Classlist = styled.div`
  .check_box {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-basis: 60px;
    margin-right: 10px;
    label {
      margin: 0;
    }
  }

  .list_content {
    flex: 1;
    .list_in_top {
      padding-bottom: 15px;
      font-size: 15px;
      span {
        color: #ff6767;
        padding-right: 5px;
      }
    }

    .list_in_mid {
      padding-bottom: 15px;
      span {
        padding-right: 15px;
      }
      p {
        padding-top: 5px;
      }
    }

    .list_in_footer {
      & span:first-child {
        color: #676767;
      }

      span {
        padding-right: 15px;
      }
    }
  }

  .list_button {
    flex-basis: 120px;
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 5px;
  }
`;

function Addressset() {
  const [addresList, setAddresList] = useState([]);
  const [keys, setKeys] = useState(0);
  const [ischeckd, setIscheckd] = useState(false);
  const [isdelete, setIsdelete] = useState(false);
  const [isfetch, setIsfetch] = useState(false);


  
  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_URL}/destination`, {
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      })
      .then((res) => setAddresList(res.data.data))
      .then((erros) => erros);
  }, []);

  
  const isOpen = (e, state) => {
    setKeys(e);
    switch (state) {
      case `ischeckd`:
        setIscheckd(!ischeckd);
        break;
      case `isdelete`:
        setIsdelete(!isdelete);
        break;
      case `isfetch`:
        setIsfetch(!isfetch);
        break;
      default:
        console.log("defalut");
    }
  };

  const changeValue = (id, state) => {
    if (state === "ischeckd") {
      axios.patch(
        `${process.env.REACT_APP_URL}/destination/${id}/representative`,
        undefined,
        {
          headers: {
            authorization: JSON.parse(localStorage.getItem("token"))
              .authorization,
          },
        }
      ).then(() => window.location.reload());
      setIscheckd(!ischeckd);
    }
    if (state === "isdelete") {
      axios.delete(`${process.env.REACT_APP_URL}/destination/${id}`, {
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      }).then(() => window.location.reload());
      setIsdelete(!isdelete);
    }
  };
 
  return (
    <Mypagehead title={"배송지 설정"} side_title={<Postmodal />}>
      <Classlist>
        {addresList.map((user) => {
          return (
            <ListLayout
              key={user.id}
              radius={"5"}
              display={"flex"}
              justifyContent={"space-between"}
              padding_width={"10px"}
              padding_height={"20px"}
            >
              <div className="check_box">
                <CheckBox
                  isChecked={user.defaultSelect}
                  onClick={() => isOpen(user.id, "ischeckd")}
                />
                {ischeckd ? (
                  <Guidance
                    text={"기본배송지를 변경 하시겠습니까?"}
                    close={() => setIscheckd(!ischeckd)}
                    ok={() => changeValue(keys, "ischeckd")}
                  />
                ) : null}
              </div>
              <div className="list_content">
                <div className="list_in_top">
                  <span>{`${user.destinationName}`}</span>
                  {user.defaultSelect === true ? (
                    <BasicButton radius={"5"} p_width={"10"} p_height={"1"}>
                      {"기본"}
                    </BasicButton>
                  ) : null}
                </div>
                <div className="list_in_mid">
                  <span>{`${user.receiverName}`}</span>
                  <span>{`${user.receiverPhoneNumber}`}</span>
                  <p>{`${user.address}`}</p>
                </div>
              </div>
              <div className="list_button">
                <BasicButton
                  p_width={"15"}
                  p_height={"5"}
                  onClick={() => isOpen(user.id, "isfetch")}
                >
                  수정
                </BasicButton>
                {isfetch ? (
                  <PatchModal
                    close={() => setIsfetch(!isfetch)}
                    data={addresList.filter((el) => el.id === keys)}
                  />
                ) : null}

                <BasicButton
                  p_width={"15"}
                  p_height={"5"}
                  onClick={() => isOpen(user.id, "isdelete")}
                >
                  삭제
                </BasicButton>
                {isdelete ? (
                  addresList.length === 1 ? (
                    <Guidance
                      text={"배송지는 1개 이상이여야 합니다."}
                      close={() => setIsdelete(!isdelete)}
                    />
                  ) : (
                    <Guidance
                      text={"해당 배송지를 삭제 하시겠습니까?"}
                      close={() => setIsdelete(!isdelete)}
                      ok={() => changeValue(keys, "isdelete")}
                    />
                  )
                ) : null}
              </div>
            </ListLayout>
          );
        })}
      </Classlist>
    </Mypagehead>
  );
}

export default Addressset;
