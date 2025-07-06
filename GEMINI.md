# Development Rules

이 문서는 **roky-pay** 프로젝트의 원활한 개발 및 협업을 위한 규칙을 정의합니다.

## 1. 아키텍처 (Architecture)

### 1.1. Multi-module & MSA

본 프로젝트는 **멀티 모듈(Multi-module)** 프로젝트로 구성되어 있으며, **마이크로서비스 아키텍처(MSA)**를 지향합니다. 각 모듈(e.g., `membership-service`, `banking-service`)은 독립적으로 개발, 빌드, 배포될 수 있는 개별 서비스입니다.

- **Root Project**: 전체 모듈을 관리하며, 공통 빌드 로직을 포함할 수 있습니다.
- **Sub-projects (Services)**: 각 비즈니스 도메인에 해당하는 마이크로서비스입니다. 독립적인 기술 스택과 데이터베이스를 가질 수 있습니다.
- **`infra`**: `docker-compose.yaml`을 통해 로컬 환경에서 여러 서비스들을 통합 실행하고 관리합니다.

### 1.2. 헥사고날 아키텍처 (Hexagonal Architecture)

각 서비스는 **헥사고날 아키텍처(Ports and Adapters Architecture)**를 따릅니다. 이는 비즈니스 로직(Domain)을 외부 기술(Adapter)로부터 분리하여 유연하고 테스트하기 쉬운 구조를 만드는 것을 목표로 합니다.

```
+---------------------------------------------------------------------------------+
|                                     Adapter                                     |
|                                                                                 |
|  +-----------------------------+       +-------------------------------------+  |
|  |       Inbound Adapter       |       |          Outbound Adapter           |  |
|  | (e.g., Web Controller)      |------>| (e.g., Persistence, Message Queue)  |  |
|  +-----------------------------+       +-------------------------------------+  |
|                  ^                                        |                     |
|                  |                                        v                     |
+------------------|----------------------------------------|---------------------+
                   |               Application              |
                   |                                        |
                   |  +-----------------------------------+  |
                   |  |      Inbound Port (Usecase)     |  |
                   |  +-----------------------------------+  |
                   |                  ^                      |
                   |                  |                      |
                   |  +-----------------------------------+  |
                   |  |          Service (Impl)           |  |
                   |  +-----------------------------------+  |
                   |                  |                      |
                   |                  v                      |
                   |  +-----------------------------------+  |
                   |  |      Outbound Port (Interface)    |  |
                   |  +-----------------------------------+  |
                   |                                        |
+------------------|----------------------------------------|---------------------+
|                  v                                        v                     |
| +-----------------------------------------------------------------------------+ |
| |                                   Domain                                    | |
| |                         (Core Business Logic, Entity)                       | |
| +-----------------------------------------------------------------------------+ |
+---------------------------------------------------------------------------------+

```

- **Domain**: 시스템의 가장 핵심이 되는 비즈니스 로직과 데이터를 포함합니다. 외부 세계에 대한 의존성이 없는 순수한 코드 영역입니다.
- **Application (Use Cases)**: 사용자의 요청을 처리하는 비즈니스 흐름을 정의합니다.
  - **Inbound Port (`Usecase` interface)**: 애플리케이션이 외부(Adapter)에 제공하는 기능의 명세입니다.
  - **Outbound Port (interface)**: 애플리케이션이 외부 시스템(DB, API 등)과 연동하기 위한 기능 명세입니다.
  - **Service**: Inbound Port를 구현하고, 비즈니스 로직을 수행하며 Outbound Port를 호출합니다.
- **Adapter**: 외부 세계와 상호작용하는 기술적인 부분을 담당합니다.
  - **Inbound Adapter**: 외부의 요청을 Inbound Port로 전달합니다. (e.g., Spring Web Controller)
  - **Outbound Adapter**: Outbound Port의 구현체로, 실제 외부 시스템과 통신합니다. (e.g., Spring Data JPA Persistence Adapter)

## 2. 기술 스택 (Technology Stack)

- **Language**: Kotlin
- **Framework**: Spring Boot 3.x, Spring Data JPA
- **Database**: MySQL
- **Build Tool**: Gradle
- **API Documentation**: Swagger (Springdoc OpenAPI)
- **Test**: JUnit 5, MockK, AssertJ
- **Containerization**: Docker, Docker Compose

## 3. 코드 작성 규칙 (Coding Conventions)

### 3.1. 네이밍 규칙 (Naming Conventions)

- **Controller**: `[Domain]Controller` (e.g., `RegisterMembershipController`)
- **Usecase (Inbound Port)**: `[Action][Domain]Usecase` (e.g., `RegisterMembershipUsecase`)
- **Service (Usecase Impl)**: `[Action][Domain]Service` (e.g., `RegisterMembershipService`)
- **Persistence Port (Outbound Port)**: `[Action][Domain]Port` (e.g., `RegisterMembershipPort`)
- **Persistence Adapter**: `[Domain]PersistenceAdapter` (e.g., `MembershipPersistenceAdapter`)
- **DTO**: `[Action]Request`, `[Domain]Response` (e.g., `RegisterMembershipRequest`)
- **Domain Entity**: `[Domain]` (e.g., `Membership`)
- **Test Class**: `[ClassName]Test` (e.g., `RegisterMembershipServiceTest`)

### 3.2. 불변성 (Immutability)

- `data class`와 `val`을 사용하여 DTO와 도메인 객체를 불변으로 유지하는 것을 지향합니다.
- 도메인 객체의 상태를 변경해야 할 경우, `modify()`와 같은 메서드를 통해 기존 객체를 변경하는 대신 새로운 상태를 가진 새 객체를 생성하여 반환합니다.

```kotlin
// GOOD
class MemberShip(...) {
    fun modify(command: ModifyMembershipCommand): MemberShip {
        return MemberShip(
            //...
            name = command.name ?: this.name,
            //...
        )
    }
}
```

### 3.3. 유효성 검사 (Validation)

- **DTO**: Controller 단에서 `jakarta.validation` 애노테이션 (`@Valid`, `@NotBlank` 등)을 사용하여 1차적으로 요청 값을 검증합니다.
- **Domain**: 도메인 객체의 `init` 블록이나 생성자에서 비즈니스 규칙에 대한 유효성을 검증하여, 도메인 객체가 항상 유효한 상태임을 보장합니다.

```kotlin
// DTO
data class RegisterMembershipRequest(
    @field:NotBlank
    val name: String,
    // ...
)

// Domain Object
class MemberShip(...) {
    init {
        // 자체 비즈니스 규칙 검증
        ValidationUtils.validate(this)
    }
}
```

### 3.4. 테스트 코드 (Test Code)

- **Frameworks**: `JUnit 5`를 기본 테스트 프레임워크로, `MockK`를 모킹 라이브러리로, `AssertJ`를 Assertion 라이브러리로 사용합니다.
- **`@DisplayName`**: 모든 테스트 메서드에는 `@DisplayName`을 사용하여 테스트의 목적을 한국어로 명확하게 서술합니다.
- **Given-When-Then**: 테스트 코드 내에 `// given`, `// when`, `// then` 주석을 사용하여 테스트의 구조를 명확히 합니다.

```kotlin
@Test
@DisplayName("정상적인 Command가 입력될 경우 Membership 생성 성공")
fun `registerMembership should success if valid command is given`() {
    // given
    val command = MockRegisterMembershipCommand.create(...)

    // when
    val result = registerMembershipUsecase.registerMembership(command)

    // then
    assertThat(result.name).isEqualTo(expect.name)
}
```

### 3.5. API 문서화 (API Documentation)

- `springdoc-openapi` (Swagger) 라이브러리를 사용합니다.
- Controller의 각 API 엔드포인트에 `@Operation`과 `@ApiResponses` 애노테이션을 사용하여 API 명세를 상세히 작성합니다.

## 4. Git 및 협업 규칙 (Git & Collaboration)

- `[type]: [subject]` 형식으로 작성합니다.
- **type**: `feat`(기능), `fix`(버그), `modify`(수정), `docs`(문서), `style`(서식), `refactor`(리팩토링), `test`(테스트), `chore`(기타)
- **subject**: 50자 이내로 간결하게 작성하며, 동사 원형으로 시작합니다.

```
feat: 회원가입 기능 추가
fix: 로그인 시 발생하는 NPE 버그 수정
```

