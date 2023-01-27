import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import BasicButton from "../../components/BasicButton";

function itemreviewList() {
  return (
    <Mypagehead
      title={"내 후기"}
      subtitle={"후기 작성은 배송완료 후 n개월 이내 가능합니다."}
      line={true}
      filltap={true}>      
    </Mypagehead>
  );
}

export default itemreviewList;
