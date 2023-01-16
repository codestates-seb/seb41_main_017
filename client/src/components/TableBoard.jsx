import styled from "styled-components";
import { useMemo } from "react";
import { useTable } from "react-table";

const Layout = styled.table`
  font-size: 15px;
  display: flex;
  flex-direction: column;
  text-align:center;
  padding:10px;

  .header {
    width: 100%;
    border-top: 1px solid #aeaeae;
    border-bottom: 1px solid #aeaeae;
    padding: 6px 10px;
    
  }

  .body {
    padding: 6px 10px;
  }

  .division_header {
    display: flex;
    justify-content: space-between;

    th:first-child{
        text-align:left;
    }

    th:last-child{
        flex:2;
    }

    th{
        flex:1;
    }
  }

  .division_body{
    display: flex;
    justify-content: space-between;
    margin-bottom:10px;

    td:first-child{
        text-align:left;
    }
    td:last-child{
        flex:2;
    }

    td{
        flex:1;
    }
  }
`;

function TableBoard() {
  
  const columns = useMemo(() => [
    {
      Header: "시간",
      accessor: "time",
    },
    {
      Header: "위치",
      accessor: "info",
    },
    {
      Header: "상태",
      accessor: "state",
    },
  ]);

  
  const data = useMemo(() => [
    {
      time: "23/01/01 12:20:31",
      info: "이천",
      state: "입고전",
    },
    {
      time: "23/01/02 07:20:31",
      info: "곤지암",
      state: "간선상차",
    },
    {
      time: "23/01/03 02:20:31",
      info: "덕양Bsub",
      state: "택배사 도착",
    },
    {
      time: "23/01/04 07:20:31",
      info: "도내동",
      state: "배송중(배송기사 : 최주노 010-1234-4567)",
    },
    {
      time: "23/01/04 12:20:31",
      info: "",
      state: "배송완료",
    },
   
  ]);


  const { 
    getTableProps, 
    getTableBodyProps, 
    prepareRow, 
    headerGroups, 
    rows } =
    useTable({ columns, data });

  return (
    <Layout {...getTableProps}>
      <thead className="header">
        {headerGroups.map((headerGroup) => (
          <tr {...headerGroup.getHeaderGroupProps()} className="division_header">
            {headerGroup.headers.map((column) => (
              <th {...column.getHeaderProps()} className="
              hedaer_Font" >{column.render("Header")}</th>
            ))}
          </tr>
        ))}
      </thead>
      <tbody {...getTableBodyProps()} className="body">
        {rows.map((row, i) => {
          prepareRow(row);
          return (
            <tr {...row.getRowProps()} className="division_body">
              {row.cells.map((cell) => {
                return <td {...cell.getCellProps()} className="
                body_Font">{cell.render("Cell")}</td>;
              })}
            </tr>
          );
        })}
      </tbody>
    </Layout>
  );
}

export default TableBoard;
