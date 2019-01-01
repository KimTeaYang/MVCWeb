
페이징 처리

1. 총 게시글 수를 알아낸다.
  
  SELECT count(idx) cnt from memo; //30개

2. 한 페이지 당 보여줄 목록 갯수를 지정 (한페이지 당 5개 목록 보여주기)
=> DAO에서 지정
   int pageSize =5;
   
3. 페이지수를 연산하자.   
  26 ~30  ==> 6페이지  (26,27,28,29), 30
  31 ~35  ==> 7
  36~ 40  ==> 8
----------------------------------  
  if(totalCout%pageSize==0){
    pageCount= totalCount/pageSize;
  }else{
    pageCount=totalCount/pageSize +1;
  }
----------------------------------
===> pageCount=(totalCount-1)/pageSize +1;

4. jsp페이지에서 페이지 네비게이션을 만들자 (pageCount수만큼)
   링크를 걸 때 현재 보여줄 페이지(cpage)를 파라미터로 걸어주자.
   

5. DB에서 해당 페이지의 데이터를 끊어서 가져온다.

SELECT rownum, memo.* from memo ORDER BY IDX DESC;

select * from(
select rownum rn,a.* from (
select * from memo order by idx desc) a
)
where rn between 1 and 5;

----------------------------------------
cpage     pageSize    start      end (cpage*pageSize)
----------------------------------------
1         5            1          5
2                      6          10 
3                      11         15  
4                      16         20 
..
----------------------------------------

