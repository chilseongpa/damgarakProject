**■  작업 전** 

1. Main Branch(Damgarak) Checkout
    - [브랜치]-[Damgarak] 더블클릭(왼쪽에 동그란원 생김)
2. 패치(fetch)
    - [메인 브랜치] 체크아웃 되어있는지 확인 후
    - [패치] 클릭-확인클릭
3. Pull
    - [Damgarak] 체크아웃 되어있는지 확인 후
    - [Pull] 클릭-확인클릭
4. 개인용 Branch Checkout
    - [브랜치]-[개인이름 브랜치(생성 예정)] 더블클릭(왼쪽에 동그란원 생김)
    - [Damgarak 브랜치] 우클릭
    - [현재브랜치로 Damgarack 병합] 클릭

**■ 작업 후**

1. staging(add작업)
    - 좌측상단 [동그란 커밋] 클릭
    - 하단 [모두 스테이지에 올리기] 클릭
2. 커밋(commit작업)
    - 우측 변경사항 확인 후
    - 하단 커밋메시지 간결하게 작성, 아래 커밋메시지 규칙 읽어보길 권장
        - 참조: https://velog.io/@chojs28/Git-%EC%BB%A4%EB%B0%8B-%EB%A9%94%EC%8B%9C%EC%A7%80-%EA%B7%9C%EC%B9%99
    - 우측하단 [네모난 커밋] 클릭
3. Push
    - Push 클릭
    - **(* 중요 *)** 반드시 본인 명의 브랜치만 선택(다른사람 명의 브랜치나, Damgarack 선택 시 재앙발생)
    - 확인 클릭
    - 깃허브 사이트 접속하여 본인의 개인용 원격브랜치에 작업사항들이 저장되었는지 확인
4. 병합 요청(Pull-Request)
    - 깃허브-우측상단 메뉴-Your organizations-[Damgarak]-Damgarack-[Damgarak]-[New pull request]
    - base:Damgarack<- compare:개인용브랜치
    - [Create pull request]
    - 디스크립션에 어떤 작업 진행했는지 상세하게 기입
    - [Create pull request] 리퀘스트했다고 카톡에 공유
    - 작업내용 리뷰 후 approve
    - Damgarack 브랜치에 merge(병합) 진행
    
    ※ 참고) 병합 요청은 매번할 필요까진 없음
