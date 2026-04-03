# WIL (스프링 시큐리티 스터디 2주차)

---

## 1. 기본 SecurityFilterChain

Spring Security는 별도의 설정을 하지 않아도 기본 보안 설정이 자동으로 적용된다.(로그인 창 나옴)  
이는 `SpringBootWebSecurityConfiguration`에서 `DefaultSecurityFilterChain`이 빈으로 등록되기 때문이다.

아무 설정을 하지 않아도 로그인 폼이 뜨는 이유는 기본 필터 체인이 이미 동작하고 있기 때문이다.

--> Spring Security 설정을 위해서는 `SecurityFilterChain` 빈을 직접 정의하는 것으로 시작해야 한다.  
(원하는 인증/인가 방식으로 커스터마이징)

---

## 2. 세션 기반 인증 (Session Authentication)

세션 기반 인증은 서버가 사용자 상태를 기억하는 **Stateful 방식**이다.

강의에서 학교 축제 입장 과정에 비유해서 설명해주셨다.

- 사용자는 로그인 요청을 보낸다.
- 서버는 인증 후 세션을 생성하고, 세션 ID(`JSESSIONID`)를 발급한다.
- 사용자는 이후 요청마다 이 세션 ID를 함께 보낸다.
- 서버는 세션 저장소에서 해당 ID를 조회하여 인증 여부를 확인한다.

즉, 인증 정보 자체를 매번 보내는 것이 아니라,  
세션 ID를 통해 서버에 저장된 인증 정보를 참조하는 방식이다.

---

## 3. 로그인 동작 흐름

- 로그인 요청을 Authentication Token으로 변환
- AuthenticationManager를 통해 인증 시도
- 인증 성공 시 Authentication 객체 생성
- SecurityContext에 인증 정보 저장
- 세션 생성 + JSESSIONID 발급
- 세션에 인증 정보 저장

이 과정을 통해 이후 요청에서는 세션 기반으로 인증이 유지된다.

---

## 4. AuthenticationManager

AuthenticationManager는 실제 인증을 처리하는 핵심 컴포넌트이다.

내부적으로는 다음 빈을 찾아서 인증 로직에 사용한다.

- UserDetailsService
- PasswordEncoder

즉, 개발자가 직접 인증 로직을 구현하기보다는,  
필요한 빈을 등록해두면 AuthenticationManager가 이를 활용해서 인증을 수행하는 구조다.

---

## 5. HttpSecurity와 설정 구조

HttpSecurity는 SecurityFilterChain을 구성할 때 사용하는 핵심 객체이다.

⚠️여러 SecurityFilterChain 간에 HttpSecurity 상태가 공유되면 안 된다.
그래서 Spring에서는 HttpSecurity를 prototype scope 빈으로 생성한다.

--> 각 필터 체인이 서로 독립적인 설정을 가질 수 있게 된다.

---

## 6. ROLE_ 접두사를 사용하는 이유

Spring Security에서 권한(Role)을 다룰 때 `ROLE_` prefix를 붙이는 이유는  
권한(Role)과 일반 권한(Authority)을 구분하기 위해서이다.




Spring 내부에서는 다음과 같은 규칙이 존재한다:

- `hasRole("USER")` → 내부적으로 `ROLE_USER`로 변환됨
- `hasAuthority("ROLE_USER")` → 그대로 비교

즉, Role 기반 메서드(`hasRole`)는 자동으로 `ROLE_`을 붙이기 때문에,  
Role 값 자체를 저장할 때도 `ROLE_`을 붙여야 일관성이 맞는다.

이를 통해
- Role과 Authority를 명확히 구분할 수 있고
- Spring Security의 기본 동작과 자연스럽게 맞아떨어진다

[ROLE_ 접두사를 사용하는 이유 참고](https://velog.io/@bdt6246/Spring-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%EC%97%90%EC%84%9C-DB%EC%97%90-role%EC%9D%84-%EC%A0%80%EC%9E%A5%ED%95%A0-%EB%95%8C-%EC%95%9E%EC%97%90-ROLE-%EC%9D%84-%EB%B6%99%EC%9D%B4%EB%8A%94-%EC%9D%B4%EC%9C%A0)

### Role vs Authority 차이

- **Authority**
    - 사용자가 가진 **권한 자체 (permission)**
    - 문자열 그대로 비교됨
    - 예: `"READ"`, `"WRITE"`, `"ROLE_USER"`

- **Role**
    - Authority의 한 종류로, **그룹 개념의 권한**
    - 내부적으로 `ROLE_` prefix가 붙어서 처리됨
    - 예: `"USER"` → `"ROLE_USER"`

---

### 정리

- Authority = 개별 권한 (더 작은 단위)
- Role = Authority를 묶은 개념 (그룹/역할)
- Spring Security에서는 Role도 결국 Authority로 변환되어 처리된다