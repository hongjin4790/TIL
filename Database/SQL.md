# SQL

## SQL(Structured Query Language)
- 관계 데이터베이스를 위한 구조화된 표준 질의 언어

### 사용방식
- 대화식 SQL: 데이터베이스 관리 시스템에 직접 접근해 질의를 작성하여 실행
- 삽입 SQL: 프로그래밍 언어로 작성된 응용 프로그램에 삽입

### SQL의 유형
- 데이터 정의어(Data Definition Language, DDL): 데이터베이스 객채들을 생성/삭제/수정
- 데이터 조작어(Data Manipulation Language, DML): 데이터베이스 객채들을 생성/삭제/수정
- 트랜잭션 제어어(Transaction Control Language, TCL): 트랜잭션을 처리하는 명령
- 데이터 제어어(Data Control Language, DCL): 객체에 대한 권한을 할당하거나 회수

|유형|명령어|내용|
|------|:---:|:---:|
|데이터 정의어|CREATE<br>DROP<br>ALTER<br>RENAME<br>|객체를 생성<br>객체를 삭제<br>객체를 변경<br>객체이름을 변경<br>|
|데이터 조작어|SELECT<br>INSERT<br>UPDATE<br>DELETE<br>|데이터를 조회<br>데이터를 추가<br>저장된 데이터를 수정<br>데이터를 삭제<br>|
|트랜잰션 제어어|COMMIT<br>ROLLBACK<br>|변경된 데이터를 적용<br>변경된 데이터를 이전 상태로 되돌림<br>|
|데이터 제어어|GRANT<br>REVOKE<br>|객체에 대한 권한을 할당<br>할당된 권한을 회수|


### SQL의 형식
- 세미콜론으로 문장의 끝을 표시
- 대소문자를 구분하지 않음

## 데이터 정의어(DDL)

### 테이블을 생성하는 구문
- CREATE TABLE 테이블 이름(구성 요소1, 구성요소 2)
- CREATE TABLE 구문의 구성 요소
  - 속성 정의: 속성 이름 데이터 타입 NOT NULL DEFAULT 기본 값 
  - 키 정의: PRIMARY KEY(속성 리스트), FOREIGN KEY(속성 리스트) REFERENCES 테이블 이름(속성 리스트) ON DELETE 옵션 ON UPDATE 옵션
- 제약 요건
  - UNIQUE(속성 리스트), CONSTRAINTS 제약 조건 이름, CHECK(제약 조건)

- NOT NULL: 속성이 NULL 값을 허용하지 않음
- DEFAULT 기본값: 속성의 기본값을 지정
- 데이터 타입: 문자열이나 날짜 데이터는 작은 따옴표로 묶어서 표현(대소문자를 구분)
- PRIMARY KEY: 기본키를 지정하는 키워드이고, 반드시 NOT NULL
- UNIQUE
    - 대체키를 지정하는 키워드
    - 유일성을 가지며 기본키와 달리 NULL값이 허용됨
- FOREIGN KEY
    - 외래키를 지정
- REFERENCES
    - 키워드 다음에 제시 연관된 테이블의 속성을 지정
- ON DELETE/UPDATE
    - 참조되는 테이블의 투플이 삭제 또는 변경시 처리방법을 지정
    - ON DELETE/UPDATE NO ACTION: 투플을 삭제/변경하지 못하게 함
    - ON DELETE/UPDATE CASCADE: 관련 투플을 함께 삭제/변경함
    - ON DELETE/UPDATE SET NULL: 삭제/변경한 투플의 외래키 값을 NULL로 변경함
    - ON DELETE/UPDATE SET DEFAULT: 삭제/변경한 투플의 외래키 값을 미리 지정한 기본 값으로 변경함

- CHECK
    - 데이터 무결성 제약조건
    - CONSTRAINT 키워드로 이름을 부여 가능
    - 예) CHECK(재고량 >= 0 AND 재고량 <= 10000)
    
### 테이블의 속성을 변경하는 구문
- ALTER TABLE | 테이블 이름 | 구성요소;
- ALTER TABLE 구문의 구성 요소
    - 속성 추가: ADD, 속성 이름, 데이터 타입, NOT NULL, DEFAULT 기본 값
    - 속성 삭제: DROP COLUMN 속성이름
    - 제약 조건 추가: ADD CONSTRAINT 제약조건 이름 CHECK(제약 조건)
    - 제약 조건 삭제: DROP CONSTRAINT 제약 조건 이름

### 테이블을 삭제하는 구문
- DROP TABLE 테이블이름;
- 관련된 외래키 제약조건이 있으면 해당 조건을 삭제해야 함


## 데이터 조작어(DML)

### 원하는 결과의 속성을 테이블로 반환하는 구문(SELECT)
- SELECT 속성 양식 리스트 FROM 테이블 리스트 WHERE 조건식 GROUP BY 속성 리스트 HAVING 조건식 ORDER BY 속성 리스트 ASC|DESC

- 집계함수(Aggregate Function)
    - 속성값을 위한 통계 함수
    - 열함수(Column Function)
    - NULL 값은 제외하고 계산
    - SELECT 절이나 HAVING 절에서만 사용 가능
    - Ex) SELECT AVG(Level) FROM PLAYER;

|집계함수|의미|
|------|:---:|
|COUNT|속성 값의 개수|
|MAX|속성 값의 최대값|
|MIN|속성 값의 최소값|
|SUM|속성 값의 합계|
|AVG|속성 값의 평균|

### 조건식을 만족하는 데이터만 테이블 형태 반환
- WHERE 조건식

|종류|연산자|의미|
|------|:---:|:---:|
|비교|속성 값의 개수|속성값의 크기를 비교<br>Ex) WHERE Level > 5;|
|논리|속성 값의 최대값|조건식들 간에 논리적 연산<br>Ex) WHERE Kill > 5 AND Death < 3;|
|부분 검색|속성 값의 최소값|부분 조건으로 검색<br>(%은 0개 이상의 문자와 매치 / _은 1개 문자와 매치)<br>Ex) WHERE Hero LIKE ‘Ta%’; : Taric, Talon, Taliyah와 매치<br>Ex) WHERE Hero LIKE ‘Z__’; : Zed, Zoe와 매치, Zyra와는 매치 안됨|
|NULL 비교|속성 값의 합계|속성이 NULL 값이면 True <br>(NULL 값의 경우는 비교 연산자로 비교할 수 없음)
|

### 지정된 속성들을 기준으로 정렬
- ORDER BY
    - 먼저 지정된 속성이 우선순위
    - 오름차순 : ASC
    - 내림차순 : DESC
    - NULL 값은 가장 높은 순위로 가정
### 지정된 속성들의 값을 그룹화
- GROUP BY
    - 지정된 속성들의 값이 같은 투플을 모아 그룹
    - GROUP BY 에 지정된 속성 외의 속성은 SELECT 에서 사용 불가, 단 집계함수로는 가능
- HAVING
    - 그룹에 대한 조건식
    - 그룹을 나누는 기준이 되는 속성을 SELECT 절에도 작성하는 것이 좋음

### 여러 테이블에 대한 조인 검색
- FROM 구문에 여러 테이블을 지정
- 테이블 이름.속성 형식으로 명시 가능
    - Ex)Player.Level
    - 속성 이름이 유일할 경우, 테이블 이름은 생략 가능
- WHERE 구문에 조인 조건식을 지정
    - 테이블 간에 조인 속성의 도메인은 동일해야 함
    - Ex) WHERE Player.ID = Team.Player_ID
    - 주로 조인 속성으로 외래키를 사용


### 투플을 삽입하는 구문(INSERT)
- INSERT INTO 테이블 이름(속성 리스트) VALUES(속성값 리스트 ) or 부속 질의문;
- VALUES 구문에서 속성 리스트와 속성값 리스트는 순서대로 일대일 대응
- 속성 리스트를 생략하면 속성값 리스트 순서대로 삽입

### 테이블의 저장된 데이터를 수정하는 구문
- UPDATE 테이블 이름 SET 속성=속성값, 속성= 속성값, where 조건식 ;

### 테이블의 저장된 데이터를 삭제하는 구문
- DELETE FROM 테이블 이름 WHERE 조건식;
- 조건식이 없는 경우, 모든 투플을 삭제하고 빈 테이블이 됨

## 뷰(VIEW)
### 뷰(view)
- 기본 테이블을 원하는 형태로 가공하여 제공
### 뷰의 목적
- 데이터 접근 편의성 증가
    - 검색 질의문 작성 편리
    - GROUP BY, 집계 함수, 조인 등을 이용해 뷰를 미리 만들어 놓으면, 복잡한
SQL 문을 작성하지 않아도 SELECT 절과 FROM 절만으로도 원하는 데이터의 검색이 가능

- 보안 강화
    - 특정 사용자 또는 특정 데이터만 접근을 허가하도록 뷰를 생성

### 뷰를 생성하는 구문
- CREATE VIEW 뷰 이름(속성리스트) AS 부속 질의문 [WITH CHECK OPTION];
- 뷰 생성의 부속 질의문에서는 ORDER BY는 사용 불가
- WITH CHECK OPTION
    - 뷰의 연산을 위한 제약조건을 지정
    - 부속 질의문에서 정의된 조건에 위배되면 연산 불가

### 뷰를 삭제하는 구문
- DROP VIEW 뷰 이름;
- 원래 테이블은 영향을 받지 않음
- 뷰를 삭제하려면 관련된 제약조건을 먼저 삭제해야 함

### SELECT/INSERT/UPDATE/DELETE
- 뷰는 데이터 조작어의 대상으로 사용가능
- 변경이 가능한 경우, 뷰에 대한 데이터 변경 연산은 기본 테이블의 데이터를 변경
- 변경 불가능한 뷰
    - 기본 테이블의 기본키 속성이 포함되지 않은 뷰
    - 집계 함수로 계산된 내용이 포함된 뷰
    - DISTICNT / GROUP BY 구문이 포함된 뷰
    - 여러 테이블을 조인하여 정의한 뷰는 변경이 불가능한 경우가 많음

## 데이터 제어어(DCL)

### 접근제어(Access Control)
- 계정이 발급된 사용자가 로그인에 성공했을 경우에만 데이터베이스에 접근 허용
- 각 사용자는 허용된 권한 내에서만 데이터베이스를 사용
    - 로그인에 성공한 사용자도 데이터베이스 사용 범위와 수행 가능한 작업이 제한됨
    - 보안을 위한 데이터 단위는 데이터베이스 전체부터 특정 테이블의 특정행과 열 위치에 있는 특정 데이터 값까지 다양함

- 데이터베이스의 모든 객체는 객체를 생성한 사용자만 사용 권한을 가짐
    - 데이터베이스 객체의 소유자는 필요에 따라 SQL 문을 이용해 다른 사용자에게
사용 권한을 부여하거나 취소할 수 있음


### 사용자에게 사용 권한을 부여하는 구문(GRANT)
- GRANT 사용 권한 ON 객체 TO 사용자/PUBLIC [WITH GRANT OPTION]

- 주요 사용 권한
    - INSERT / DELETE / UPDATE / SELECT / REFERENCES
    - REFERENCES : 외래키 제약조건을 정의할 수 있는 권한
    - UPDATE나 SELECT는 테이블의 일부 속성에 대한 권한 부여도 가능
    - 데이터 정의어(DDL)에 대한 권한을 부여할 때는 객체 생략 가능

- PUBLIC
    - 모든 사용자에게 권한을 부여
- WITH GRANT OPTION
    - 자신이 부여받은 권한을 다른 사용자에게도 부여할 수 있음

### 부여된 사용 권한을 취소하는 구문(REVOKE)
- REVOKE 사용권한 ON 객체 FROM 사용자/PUBLIC CASCADE/RESTRICT;
- CASCADE
    - 다른 사용자에게 부여한 권한까지 연쇄적으로 취소
- RESTRICT
    - 해당 사용자의 권한만 취소

### 역할(Role)
- 여러 권한을 묶어 놓은 그룹
- 역할을 개인별로 부여하는 것이 아니라 소속된 조직 단위로 부여되는 경향이 존재
- CREATE ROLE 역할이름;
- GRANT 사용 권한 ON 객체 TO 사용자;
- GRANT 역할 이름 TO 사용자;
- REVOKE 역할 이름 FROM 사용자;
- DROP ROLE 역할 이름;

