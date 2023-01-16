import ListLayout from "../../components/ListLayout";
import Mypagehead from "../../components/MypageHead";

function Orderitem(){
    return(
        <Mypagehead title={"주문 목록 조회"}>
            <ListLayout>
            </ListLayout>
        </Mypagehead>
    );
}


export default Orderitem;