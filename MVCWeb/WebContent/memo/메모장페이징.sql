
����¡ ó��

1. �� �Խñ� ���� �˾Ƴ���.
  
  SELECT count(idx) cnt from memo; //30��

2. �� ������ �� ������ ��� ������ ���� (�������� �� 5�� ��� �����ֱ�)
=> DAO���� ����
   int pageSize =5;
   
3. ���������� ��������.   
  26 ~30  ==> 6������  (26,27,28,29), 30
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

4. jsp���������� ������ �׺���̼��� ������ (pageCount����ŭ)
   ��ũ�� �� �� ���� ������ ������(cpage)�� �Ķ���ͷ� �ɾ�����.
   

5. DB���� �ش� �������� �����͸� ��� �����´�.

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

