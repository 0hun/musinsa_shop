# musinsa-shop
+  카테고리별 브랜드를 관리하는 서비스를 제공하는 서버입니다.
+ 브랜드 및 카테고리별 상품 조회 기능을 직접 구현함으로써 대규모 트래픽에도 견고한 어플레이션을 구현할 수 있드록 하고 있습니다.

## 기능
+ 모든 카테고리의 상품을 브랜드별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가 조회
+ 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회
+ 각 카테고리 이름으로 최소, 최대 가격 조회
+ 브랜드 상품 가격 추가 API
+ 브랜드 상품 가격 업데이트 API
+ 브랜드 상품가격 삭제 API

### 사용 기술
+ Spring Boot, Gradle, Mysql, Java8, RestApi, Jpa

### 기술적인 집중 요소
+ 객체지향의 기본 원리와 spring의 IOC/DI , AOP, PSA 활용과 의미 있는 코드 작성
+ 라이브러리 및 기능 추가 시 이유있는 선택과 사용 목적 고려

### 브렌치 관리 전략
Git Flow를 사용하여 branch를 관리   
모든 branch는 pull request 리뷰 완료후 merge   


+ master: 개발, 테스트 완료후 검증이 완료된 코드가 있는 branch
+ develop: 개발이 끝난후 issue branch를 merge
+ issue(feature): develop에서 새로운 기능을 개발 진행
+ release: issue에서 develop으로 merge하여 master에 merge전 배포하여 테스트를 진행
+ hot-fix: release, master에서 발생한 버그를 수정

> #### 브렌치 관리 전략 참고 문헌
+ https://riptutorial.com/ko/git/example/4182/gitflow-%EC%9B%8C%ED%81%AC-%ED%94%8C%EB%A1%9C%EC%9A%B0
