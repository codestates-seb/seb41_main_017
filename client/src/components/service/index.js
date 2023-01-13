import styled from "styled-components";
import Tap from "../Tap";
import Announcement from "./Announcement";
import ManyQuestion from "./ManyQuestion";
import OneOnOne from "./OneOnOne";

function ServiceHome() {
  const Page = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 40px;
  `;

  const list = [
    { 공지사항: ["/announcement", <Announcement />] },
    { "자주묻는 질문": ["/question", <ManyQuestion />] },
    { "1:1 문의": ["/one-on-one", <OneOnOne />] },
  ];

  return (
    <Page>
      <div>
        <Tap list={list} title="고객센터" />
      </div>
    </Page>
  );
}

export default ServiceHome;
