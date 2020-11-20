# airdnd

2020.08.03부터 2020.09.11까지 진행한 airbnb 클론 코딩 프로젝트입니다. 

<br>

## 구성원

[Front-end](https://github.com/DEVengersAssemble/airdnd-frontend): 김우정, 주한슬, 이하은, 김태진  
[Back-end](https://github.com/DEVengersAssemble/airdnd-backend): 윤우성, 이유진, 정재은, 최미래

<br>

## 백엔드 핵심 목표

- 프론트엔드와 백엔드 개발자들이 협업하여 프로젝트 진행
- 코딩 작업에 앞서 기능명세서, 데이터구조, 컴포넌트 분리 등 회의 및 기록 ([Figma](https://www.figma.com/file/jRtiFrdpJW6ebdKB4ozmYS/Airbnb?node-id=3%3A92))
- Putty를 이용하여 AWS EC2 가상컴퓨터에 서버 구축 및 MySQL을 사용한 데이터베이스 구축
- Flask, Python을 이용해 실제 Airbnb의 숙소 및 호스트 정보를 스크래핑
- 프론트단에서 요청인자들을 데이터베이스에서 추출하여 JSON형식으로 OUTPUT
- Spring Framework를 이용한 페이지별 기능 처리

<br>

## 페이지별 기능
### 서버 구현

> AWS EC2의 인스턴스와 탄력적 IP를 이용해 서버 구축  
Putty를 사용해 가상컴퓨터(Ubuntu)에 Java, tomcat8, mysql를 리눅스 명령어로 설치  
Spring을 이용한 기능 구현 코드를 Filezilla로 AWS EC2에 업로드하여 서버 구축  

### Airbnb 데이터 스크래핑

> 실제 Airbnb의 숙소 및 호스트 정보를 가지고 오기 위해 Selenium과 BeautifulSoup4를 사용하여 각 데이터들을 추출  
추출한 데이터들을 pymysql로 구축한 서버의 MySQL 데이터베이스에 저장  
원하는 지역의 숙소들을 스크랩하기 위해 Flask로 웹페이지를 구현  
(Airbnb에는 위도, 경도 데이터가 없기 때문에 숙소 위치를 구현하기 위해 Google geocoder api로 lat, lng을 추출하여 저장)

### 메인 페이지

- 회원가입 기능을 구현하기 위해 프론트에서 가져온 파라미터 값들을 Decoding 후 데이터베이스의 무결성 조건을 만족시 INSERT
- 로그인시 데이터베이스에서 회원정보를 확인 후 조건 일치 시 세션과 쿠키를 생성
- 쿠키 값은 Random과 StringBuffer를 사용해 로그인마다 세션 키를 다르게 생성한 값을 포함
- 로그아웃 시 쿠키에서 가져온 세션 키로 해당 세션을 불러와 제거 및 쿠키 expiration time 만료시킴
- 자동완성 기능은 Airbnb autocompletes api를 사용해 입력한 값에 따른 결과에서 필요한 데이터만 추출하여 Json 형식으로 전달

### 숙소 검색 페이지

- 숙소 검색 리스트 구현은 숙소의 여러 테이블을 참조한 VIEW를 생성해 필요한 데이터 출력
- 필터 구현은 DAO단에 파라미터를 넘겨 SQL문 조건절 활용해 구현
- 숙소를 지도에 표시하기 위해 각 숙소의 위도, 경도 값을 Google geocoder api로 구해 데이터베이스의 저장
- 지도의 보여줄 범위를 구현하기 위해 숙소들의 모든 lat, lng 값을 이용한 알고리즘 구현
- 최근 본 숙소의 데이터를 반환시키기 위해 쿠키를 이용한 최근 숙소의 idx 저장
- 가격 분포도는 2(만원) 단위로 이중 for문을 이용한 알고리즘 구현
- 저장된 쿠키들의 값으로 데이터베이스에 접근 후 recentHome안에 값들을 저장 후 Json형식으로 반환

### 숙소 상세 페이지

### 프로필 페이지

### 채팅 페이지

- 상황에 따른 메시지 분류
- 메시지 입력 화면 구성
- 숙소 세부 내용

<br>

## 백엔드 사용 스택

- Java
- Spring
- MySQL
- AWS EC2
- Python
- Flask

## 프론트엔드 사용 기술

- JavaScript
- React
- router
- redux
- redux-thunk
- Styled-Component
- socket.io
