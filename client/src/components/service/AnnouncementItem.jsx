import { Link } from "react-router-dom";
import styled from "styled-components";
import Dummy from "./data.json";

const Page = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ItemCotainer = styled.div`
  width: 900px;
  height: 60px;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
  p {
    margin-left: 15px;
  }
`;

const Notice = styled.div`
  width: 15%;
`;
const Title = styled.div`
  width: 65%;
`;
const Writter = styled.div`
  width: 10%;
  text-align: center;
`;

const Time = styled.div`
  width: 10%;
  text-align: center;
`;

function AnnouncementItem() {
  // const data = [
  //   {
  //     id: 1,
  //     notice: 1,
  //     title: "공지사항입니다.",
  //     writter: "운영자",
  //     time: "2023.01.09",
  //   },
  //   {
  //     id: 2,
  //     notice: 2,
  //     title: "공지사항2입니다.",
  //     writter: "운영자",
  //     time: "2023.01.11",
  //   },
  //   {
  //     id: 3,
  //     notice: 3,
  //     title: "공지사항3입니다.",
  //     writter: "운영자",
  //     time: "2023.01.12",
  //   },
  // ];
  // console.log(data);
  return (
    <Page>
      <div>
        {Dummy.Announcement.reverse().map((el) => {
          return (
            <ItemCotainer key={el.id}>
              <Notice>
                <p>{el.notice}</p>
              </Notice>
              <Title>
                {/* <Link to={"/announcement/data/${data.id}"}> */}
                <p>{el.title}</p>
                {/* </Link> */}
              </Title>
              <Writter>{el.writter}</Writter>
              <Time>{el.time}</Time>
            </ItemCotainer>
          );
        })}
      </div>
    </Page>
  );
}

export default AnnouncementItem;
