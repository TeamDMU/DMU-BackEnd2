# DMU-BackEnd2

## DB 설계

## Rest API 설계

## 구현 중 어려웠던 상황 및 극복
### 크롤링 라이브러리의 문제
자바의 크롤링 방법은 크게 두 가지의 라이브러리를 사용할 수 있다는 것을 확인할 수 있었다.
- Jsoup
- Selenium

크롤링을 하는데에 이 두 라이브러리의 큰 차이는 다음과 같다.
- Jsoup은 정적인 HTML을 수집하는데, Selenium은 동적인 데이터도 수집 가능하다.
- Jsoup은 라이브러리를 추가하고 바로 사용할 수 있지만, Selenium은 크롬 드라이버가 별도로 필요하다.

사실 처음에는 Selenium을 사용하여 개발을 시작했다. <br/>
이유는 학교 공지사항 페이지를 자동으로 넘겨가면서 크롤링을 할 수 있을 것 같았기 때문이다. <br/> 

하지만 아래와 같은 이유들 때문에 오류들을 해결하다가 크롤링 라이브러리를 바꾸게 되었다. <br/>
- Jsoup을 사용하더라도 충분히 원하는 결과를 얻을 수 있고, 속도도 Selenium보다 빠르다.
- Selenium 설정의 오류
  - 크롬 드라이버의 버전이 현재 가장 최신 버전의 크롬을 지원하지 않는다.
  - 크롬의 버전을 다운그레이드 하려고 했으나 자동 업데이트를 막지 못했다.

이러한 문제를 겪으며 개발에 들어가기 앞서 만들려는 기능이 무엇이고, 어떻게 구현해야 하는지를 신중하게 생각해야겠다고 느꼈다.

---

### 성능과 가독성의 Trade In
주어진 정보를 가지고 url을 만드는 urlBuilder라는 메서드가 Parser클래스에서 사용되는 것을 볼 수 있다. <br/>
여러 String 값을 통해 하나의 url을 만드는 과정이다 보니 String.format() or StringBuilder 등 여러 방법이 존재했다. <br/>
만약 성능을 중요시 한다면 StringBuilder를 사용할 수 있었고, 가독성을 원한다면 String.format()을 사용할 수 있었다. <br/>

성능과 가독성의 Trade In을 위해서 아래와 같은 두가지 테스트를 진행해보았다.
- 첫 번째는 전체 데이터를 가져오는 작업
- 두 번째는 최근 정보들을 가져오는 작업

첫 번째 테스트 결과
- String.format() -> 약 5분 소요
- StringBuilder() -> 약 4분 20초 소요
- 40초 가량 13%의 성능 향상

두 번째 테스트 결과
- String.format(), StringBuilder() 둘 다 약 10초 소요
- 작업의 양이 적다면 성능의 이점이 없음

이를 통해 초기 데이터베이스를 구성하는데에 성능을 향상 시킬수 있지만, 운영중에는 성능의 이점을 누릴수 없다는 것을 확인할 수 있었다. <br/>
따라서 서비스 운영과 유지 보수적인 측면에서 가독성이 더 우위를 점한다고 판단하였고, 가독성을 가져오는 것으로 결정하였다.
