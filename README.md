# 삼성SDS 멀티캠퍼스 파이널 프로젝트



## 디지털 사이니지를 활용한 사용자 맞춤형 도시 광고 서비스

### 제안 배경

- 현대의 광고산업에서 빅데이터 기반 사용자 맞춤형 광고가 온라인 위주로 발전하고 있는 반면 오프라인 거점광고는 여전히 효율적인 소비자 타겟팅이 부족한 상태
- 구간별 교통량을 측정한 자료를 분석해본 결과 거점 별로 연령, 성별, 직업, 가구 형태 등에 따른 유의미한 기호집단형성이 되어 있음을 발견함
- 현재 비효율적으로 진행되고 있는 오프라인 거점광고에 기호집단 관련 빅데이터 분석을 도입하면 보다 효율적인 광고집행이 이루어질 수 있음

### 제안 목적

- 목표 1: 사거리 전광판 광고 주변 교통량 및 운전자의 정보를 분석하여 광고 집행 시 주변 운전자들의 기호에 맞는 광고를 송출
- 목표 2: 운전자의 어플리케이션, 차량 Lcd모니터, 광고주 관리자페이지를 연동하여 실시간으로 차량의 상태 및 운전자의 기호를 광고판에 반영
- 목표 3: 소비자의 기호를 정제된 로그로 변환하여 Decision tree 알고리즘으로 분석, 광고송출 내용 및 순서등에 반영

### 기대효과

- 경제적 측면

  - 광고의 노출도 향상으로 인한 수익률 증가 예상
  - 소비자 표적 광고 연결을 통해 소비활동 증가 예상
  - 광고 노출도 : 해당 지역의 유동차량/유동인구
  - 광고 평균 비용 : 주요 지역 약 800/하루 20초 100회이상 노출 기준

- 산업적 측면

  - 커넥티드 카에서 수집한 정보를 활용한 데이터 분석 모델을 구축
  - 커넥티드 카 이외의 차량에서도 인포메틱 삽입을 통해 커넥티드 카와 동일한 데이터 분석 서비스를 제공받을 수 있음

- 기술적 측면

  - 데이터 수집 모델의 구축을 통해 운전자뿐만 아닌 보행자까지 양방향의 정보 전달이 가능
  - 다수의 ECU를 사용하여 ECU들과 협조-제어가 가능한 통합 제어를 기대

- 서비스 측면

  - 수요자 중심의 서비스 질 향상
  - 고객의 이용만족도 개선

  

### 구성 아키텍처

![구성도 업데이트](https://user-images.githubusercontent.com/36683607/78957320-0453eb80-7b20-11ea-9c9d-4e66f710dae5.png)


### 개발방안(알고리즘의 구현 요소)

- Decision Tree를 활용한 전광판 광고 송출
  - 정제된 로그를 이용하여 광고를 송출하는 순서를 변경
  - 광고 접속률의 상향평준화를 위한 광고 재생시간 가중치 조절

### 데모 화면

> 관리자 페이지
>
> <img width="1280" alt="main" src="https://user-images.githubusercontent.com/36683607/78957622-d1f6be00-7b20-11ea-8e39-baf8f2ad6468.png">
>
> <img width="1278" alt="userlist" src="https://user-images.githubusercontent.com/36683607/78957319-03bb5500-7b20-11ea-9d48-4cab5da84e2b.png">
>
> <img width="1277" alt="chart1" src="https://user-images.githubusercontent.com/36683607/78957571-a83d9700-7b20-11ea-8eb2-ab90d508c07f.png">
>
> <img width="1279" alt="chart2" src="https://user-images.githubusercontent.com/36683607/78957331-074edc00-7b20-11ea-9035-6f16f2e13725.png">
>
> <img width="1280" alt="maps" src="https://user-images.githubusercontent.com/36683607/78957313-00c06480-7b20-11ea-9ce5-3f3023435be9.png">
>
> <img width="1277" alt="adlist" src="https://user-images.githubusercontent.com/36683607/78957322-04ec8200-7b20-11ea-8b7a-aa1a1d2176c2.png">
>
> <img width="1276" alt="sn" src="https://user-images.githubusercontent.com/36683607/78957316-0322be80-7b20-11ea-92a7-027d9fbdadfa.png">
>
> 클라이언트 앱
>
> ![capp1](https://user-images.githubusercontent.com/36683607/78957328-061daf00-7b20-11ea-88da-032b4bbab84e.jpg)
>
> ![capp2](https://user-images.githubusercontent.com/36683607/78957576-aa9ff100-7b20-11ea-823b-910c10dfa508.jpg)
>
> 인포매틱스
>
> ![app1](https://user-images.githubusercontent.com/36683607/78957325-05851880-7b20-11ea-875e-04ec5a99879d.jpg)
>
> ![app2](https://user-images.githubusercontent.com/36683607/78957326-061daf00-7b20-11ea-84cb-cdd1a4842bd6.jpg)

### 수행일정

<img width="470" alt="t" src="https://user-images.githubusercontent.com/36683607/78957317-03bb5500-7b20-11ea-80c9-68e0b6e5ef4f.PNG">

### 기타(알고리즘, Decision Tree)

<img width="419" alt="dt" src="https://user-images.githubusercontent.com/36683607/78957332-07e77280-7b20-11ea-8bbf-1e26bf9a29db.PNG">

### 결론

당사는 고객사 업무를 수행하여 기호에 맞는 광고 송출, 더 많은 광고 부가 수익 창출 등을 통해 판매 주기를 향상하기 위한 귀하의 노력을 지원하기 위해 최선을 다할 것입니다. 

당사는 향후 남은 과제를 해결하고 효율적인 IT 지원 솔루션을 제공하여 파트너 관계를 유지할 준비가 되어 있습니다. 

감사합니다.

OSTB
