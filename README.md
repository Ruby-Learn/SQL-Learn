## VIEW
- 하나 이상의 테이블을 합항녀 만든 가상의 테이블
- SELECT 문을 통해 얻은 최종 결과를 가상의 테이블로 정의하여 실제 테이블처럼 사용할 수 있도록 만든 데이터베이스 개체
    - 실제 데이터를 저장하지 않고 뷰를 생성할 때 사용한 SELECT 문의 정의를 DBMS 가 저장
        - JAVA 에서 변수에 값을 저장해두고 재사용하는 것과 같은 개념

### VIEW 의 생성, 수정, 삭제
- VIEW 생성 및 사용
    ```sql
    CREATE VIEW Vorders
    AS (
            SELECT  O.orderid, O.custid, C.name
            FROM    Orders O, Customer C
            WHERE   O.custid = C.custid
       );
       
    -- VIEW 를 통한 조회. 이 때, VIEW 는 SubQuery 와 같은 역할을 한다.
    SELECT  *
    FROM    Vorders;
    ```
- VIEW 수정
    ```sql
    CREATE OR REPLACE VIEW Vorders
    AS (
            -- 수정할 VIEW 의 내용
            SELECT  O.orderid, O.custid, C.name
            FROM    Orders O, Customer C
            WHERE   O.custid = C.custid
       );
    ```
- VIEW 삭제
    ```sql
    DROP VIEW Vorders;
    ```

### VIEW 의 장점
- 편의성
    - 미리 작성된 질의를 VIEW 로 정의해둠으로서 질의 재사용이 가능하며 해당 질의문이 필요할 때 간단하고 VIEW 를 통해 조회할 수 있다.
- 보안성
    - 원본 테이블에서 보안이 필요한 속성을 제외하고 VIEW 를 통해 새로운 가상의 테이블을 정의하여 사용자에게 제공함으로서 데이터 보안성을 높일 수 있다.
        - 이 때, 원본 테이블은 사용자게에 제공하지 않는다.
- 논리적 데이터 독립성
    - VIEW 를 정의하여 응용 프로그램이 사용하게 되면 개념 스키마에 정의된 테이블 구조가 변경되어도 응용 프로그램의 변경을 막아주기 때문에
      논리적 데이터 독립성을 제공한다.
        - API 에서 엔티티가 아닌 DTO 를 통해 클라이언트에게 데이터를 반환해주는 것과 같은 개념

### VIEW 의 단점
- 응용프로그램 쪽에서 VIEW 의 구조를 확인하기 위해서는 DB 를 확인해야함
- 응용프로그램에서 VIEW 에 사용된 질의를 관리할 수 없음