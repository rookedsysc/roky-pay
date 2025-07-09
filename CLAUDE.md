# Roky-Pay 프로젝트 컨벤션 및 아키텍처 가이드

## 1. 프로젝트 구조

### 1.1 전체 구조
- **Multi-module Gradle 프로젝트**
  - `membership-service`: 회원 관리 서비스
  - `banking-service`: 뱅킹 서비스
  - `infra`: 인프라 설정 (Docker Compose)

### 1.2 아키텍처 패턴
**헥사고날 아키텍처 (Ports and Adapters)**를 사용합니다.

```
membership-service/
├── adapter/
│   ├── in/           # 인바운드 어댑터 (Controller, Request/Response DTO)
│   │   └── web/
│   └── out/          # 아웃바운드 어댑터 (Persistence, External API)
│       └── persistence/
├── application/
│   ├── port/
│   │   ├── in/       # 인바운드 포트 (UseCase 인터페이스)
│   │   └── out/      # 아웃바운드 포트 (Repository 인터페이스)
│   └── service/      # UseCase 구현체
├── domain/           # 도메인 모델
├── common/           # 공통 유틸리티
└── aop/             # AOP 설정
```

## 2. 네이밍 규칙

### 2.1 패키지 네이밍
- **소문자 사용**: `org.rookedsysc.membershipservice`
- **서비스별 패키지**: `membershipservice`, `bankingservice`
- **예약어 처리**: `in`, `out` 같은 Kotlin 예약어는 백틱(`)으로 감싸기

### 2.2 클래스 네이밍
- **Controller**: `{기능}Controller` (예: `RegisterMembershipController`)
- **UseCase Interface**: `{기능}Usecase` (예: `RegisterMembershipUsecase`)
- **Service Implementation**: `{기능}Service` (예: `RegisterMembershipService`)
- **Port Interface**: 
  - Inbound: `{기능}Usecase`
  - Outbound: `{기능}Port` (예: `RegisterMembershipPort`)
- **Adapter**: `{타입}Adapter` (예: `MembershipPersistenceAdapter`)
- **DTO**:
  - Request: `{기능}Request` (예: `RegisterMembershipRequest`)
  - Command: `{기능}Command` (예: `RegisterMembershipCommand`)
- **Entity**: `{도메인}JpaEntity` (예: `MembershipJpaEntity`)
- **Repository**: `{도메인}JpaRepository` (예: `MembershipJpaRepository`)

### 2.3 파일 네이밍
- **Kotlin 파일**: PascalCase 사용 (클래스명과 동일)
- **리소스 파일**: kebab-case 사용 (예: `application.yaml`)

## 3. 코드 컨벤션

### 3.1 Kotlin 스타일
- **들여쓰기**: 4 스페이스
- **클래스 멤버 순서**:
  1. 상수 (companion object)
  2. 프로퍼티
  3. 생성자
  4. init 블록
  5. 메소드

### 3.2 의존성 주입
- **생성자 주입** 사용
- `val` 키워드로 불변성 보장

```kotlin
@Service
class RegisterMembershipService(
    private val registerMembershipPort: RegisterMembershipPort,
    private val membershipMapper: MembershipMapper
) : RegisterMembershipUsecase {
    // ...
}
```

### 3.3 Validation
- Jakarta Bean Validation 사용
- 도메인 모델에서 `ValidationUtils.validate(this)` 호출
- 어노테이션: `@NotBlank`, `@Email`, `@AssertTrue` 등

### 3.4 예외 처리
- 명시적 예외 던지기: `throw IllegalArgumentException("message")`
- null 처리: Elvis 연산자 활용 (`?:`)

## 4. 테스트 컨벤션

### 4.1 테스트 구조
- 동일한 패키지 구조 유지
- Mock 객체는 별도 클래스로 분리 (예: `MockMemberShip`)

### 4.2 테스트 네이밍
- **테스트 클래스**: `{대상클래스}Test`
- **테스트 메소드**: 
  - `@DisplayName` 어노테이션으로 한글 설명
  - 백틱으로 영어 메소드명 작성 가능

```kotlin
@Test
@DisplayName("정상적인 Command가 입력될 경우 Membership 생성 성공")
fun `registerMembership should success if valid command is given`() {
    // given
    // when
    // then
}
```

### 4.3 테스트 프레임워크
- **단위 테스트**: JUnit5 + MockK
- **통합 테스트**: `@SpringBootTest` + `@AutoConfigureMockMvc`
- **Assertion**: AssertJ 사용 (`assertThat`)

## 5. 빌드 및 배포

### 5.1 Gradle 설정
- **Kotlin DSL** 사용 (`build.gradle.kts`)
- **Multi-module 설정**: 루트의 `subprojects` 블록에서 공통 설정
- **Java 17** 사용

### 5.2 의존성 관리
주요 의존성:
- Spring Boot 3.3.5
- Kotlin 1.9.25
- MySQL Connector
- Flyway (DB 마이그레이션)
- SpringDoc OpenAPI (API 문서화)

### 5.3 Docker
- 서비스별 Dockerfile 제공
- Palantir Docker 플러그인으로 이미지 빌드
- Docker Compose로 전체 서비스 오케스트레이션

## 6. 인프라 설정

### 6.1 데이터베이스
- **MySQL 8.4.3** 사용
- Flyway로 스키마 버전 관리
- JPA DDL 모드: `validate`

### 6.2 환경 변수
- `DB_IP`: 데이터베이스 호스트
- 프로파일별 설정 가능 (`SPRING_PROFILES_ACTIVE`)

### 6.3 포트 설정
- membership-service: 8080
- banking-service: 8081
- MySQL: 3306

## 7. API 설계

### 7.1 REST API 컨벤션
- **Base Path**: `/membership`, `/banking`
- **엔드포인트**: `/{resource}/{action}` (예: `/membership/register`)

### 7.2 API 문서화
- SpringDoc OpenAPI 사용
- `@Operation`, `@ApiResponses` 어노테이션으로 문서화

## 8. 공통 유틸리티

### 8.1 상수 관리
- `constant` 패키지에 상수 클래스 정의
- object 클래스로 싱글톤 구현

### 8.2 AOP
- 커스텀 어노테이션으로 횡단 관심사 처리
- 예: `@MembershipTag`

## 9. 보안 및 검증

### 9.1 입력 검증
- Controller 레벨: `@Valid` 어노테이션
- Domain 레벨: init 블록에서 검증

### 9.2 민감 정보
- 환경 변수로 관리
- application.yaml에 하드코딩 금지

## 10. 개발 가이드라인

### 10.1 커밋 메시지
- 명확하고 구체적인 메시지 작성
- 기능별로 작은 단위로 커밋

### 10.2 코드 리뷰
- 헥사고날 아키텍처 원칙 준수 확인
- 테스트 커버리지 확인
- 네이밍 컨벤션 준수 확인