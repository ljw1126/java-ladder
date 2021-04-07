# 2단계 - 2단계 - 사다리(생성)

## 기능 요구사항

- 게임 참여하는 사람의 이름 최대 5글자 부여 가능
- 사다리 출력할 시, 사람의 이름도 같이 출력
- 사람의 이름을 5자 기준으로 출력하기 때문에 사다리 폭도 넓어져야 함
- 라인 겹치지 않도록 해야함


## 기능 목록
- 참여할 사람 이름 입력
    - 쉼표(,) 구분
    - 이름 당 5자
    - 유효성


- 사다리 높이 입력
    - 유효성 체크


- 사다리
    - 라인 List
    - 높이만큼 Line 추가
    - 유효성 
      - 최소 높이 1 이상


- 라인
    - 포인트 List
    - 사람 수 = 넓이
    - 유효성 
      - 최소 참여자 수 2명 이상

- 포인트
    - Point (true/false)


- 동작
    - 랜덤
    - 라인 겹침 방지
        - 이전 Point, 현재 Point 체크
 