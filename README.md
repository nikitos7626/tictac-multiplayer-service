# Tic-Tac-Toe Game & Auth Service

Бэкенд-сервис для многопользовательской игры в «Крестики-нолики» (Tic-Tac-Toe) с поддержкой матчей между игроками (PvP) и игры против компьютера, встроенной системой регистрации и аутентификации пользователей и персистентным хранением игровых сессий в PostgreSQL.

Разработано на **Java 21** и **Spring Boot 3**.

---

## 🛠 Стек технологий

- **Язык программирования:** Java 21
- **Фреймворк:** Spring Boot 3.3.3
  - Spring Web (REST API)
  - Spring Security (контроль доступа и кастомные фильтры безопасности)
  - Spring Data JPA (работа с реляционными базами данных)
- **База данных:** PostgreSQL, Hibernate ORM
- **Сборка и управление зависимостями:** Gradle (Kotlin DSL)
- **Тестирование:** JUnit 5, Spring Boot Starter Test

---

## 🚀 Архитектура и функциональность

Проект спроектирован по принципам чистой слоистой архитектуры (Clean / Layered Architecture) с разделением ответственности:

```
src/tic-tac/src/main/java/space/tictac/
├── domain/                    # Доменный слой (бизнес-логика)
│   ├── model/                 # Доменные сущности (Board, Game, User, Player, GameResult)
│   ├── repository/            # Интерфейсы репозиториев данных
│   └── service/               # Сервисы бизнес-логики (GameService, UserService, GameRules)
└── web/                       # Слой веб-контроллеров и безопасности
    ├── controller/            # REST-контроллеры (AuthController, GameController, UserController)
    ├── mapper/                # DTO мапперы (GameDtoMapper, UserDtoMapper, BoardDtoMapper)
    ├── model/                 # DTO запросов и ответов (CreateGameRequest, MoveRequest, SignUpRequest...)
    └── security/              # Фильтр аутентификации (AuthFilter)
```

### Ключевые возможности:

1. **Аутентификация и безопасность:**
   - Регистрация новых пользователей (`/auth/signUp`) с валидацией уникальности логина.
   - Аутентификация пользователя (`/auth/login`) через заголовок `Authorization`.
   - Компонент `AuthFilter` на уровне цепочки сервлетов Spring Security проверяет валидность сессии пользователя и наполняет `SecurityContextHolder` для защищенных эндпоинтов.

2. **Игровая логика (Tic-Tac-Toe):**
   - Валидация очередности ходов и корректности изменения доски.
   - Определение условий победы (3 в ряд по горизонтали, вертикали или диагоналям) и ничьей (`GameRules`).
   - Режим игры с ботом (автоматический ответный ход компьютера).
   - Мультиплеерный режим с поддержкой ожидания в лобби и присоединения второго игрока.

3. **База данных:**
   - Персистентное хранение учетных записей пользователей и состояний партий в PostgreSQL через Spring Data JPA.
   - Автоматическая синхронизация схемы таблиц при помощи Hibernate.

---

## 📡 Спецификация REST API

### 1. Аутентификация (`/auth`)

| Метод | Эндпоинт | Описание | Заголовки / Тело | Ответ |
| :--- | :--- | :--- | :--- | :--- |
| `POST` | `/auth/signUp` | Регистрация пользователя | `{"login": "string", "password": "string"}` | `200 OK` / `400 Bad Request` |
| `POST` | `/auth/login` | Вход в систему | `Authorization: Basic <credentials>` | `200 OK` (UUID пользователя) / `401 Unauthorized` |

### 2. Пользователи (`/user`)

| Метод | Эндпоинт | Описание | Заголовки | Ответ |
| :--- | :--- | :--- | :--- | :--- |
| `GET` | `/user/{uuid}` | Получение данных профиля пользователя | `Authorization: ...` | `200 OK` (`UserDto`) / `404 Not Found` |

### 3. Управление играми (`/game`)

| Метод | Эндпоинт | Описание | Параметры / Тело | Ответ |
| :--- | :--- | :--- | :--- | :--- |
| `POST` | `/game` | Создать новую игру | `{"vsComputer": true\|false}` | `200 OK` (`GameDto`) |
| `GET` | `/game` | Список открытых игр в лобби | — | `200 OK` (Массив `GameDto`) |
| `GET` | `/game/{uuid}` | Получить состояние игры по UUID | — | `200 OK` (`GameDto`) / `404 Not Found` |
| `POST` | `/game/{uuid}/join` | Присоединиться к открытой игре | — | `200 OK` (`GameDto`) |
| `POST` | `/game/{uuid}` | Сделать ход в игре | `{"board": [...]}` | `200 OK` (`GameDto`) / `400 Bad Request` |

---

## ⚙️ Установка и локальный запуск

### Предварительные требования

- **JDK 21** или выше.
- Запущенный инстанс **PostgreSQL** (локально или в Docker).

### 1. Настройка подключения к базе данных

Отредактируйте параметры подключения в файле `src/tic-tac/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tictac
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 2. Сборка и запуск приложения

Перейдите в директорию проекта `src/tic-tac` и выполните:

**На Linux / macOS:**
```bash
cd src/tic-tac
./gradlew bootRun
```

**На Windows:**
```powershell
cd src/tic-tac
.\gradlew.bat bootRun
```

Приложение запустится на порту `8080`.

### 3. Запуск тестов

```bash
cd src/tic-tac
./gradlew test
```

---

## 📄 Лицензия

Проект распространяется под лицензией [MIT](LICENSE).
