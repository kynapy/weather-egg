# AGENTS.md

## Layout

- `weather-egg-api/` — Spring Boot / Java 21 API (Maven, war packaging). Stores weather readings from the IoT device (MQTT → Postgres) and proxies LTA DataMall bus arrivals through a Hazelcast cache to protect the LTA quota.
- `weather-egg-ui/` — Next.js (App Router) dashboard. Static export (`output: 'export'`, `distDir: 'dist'`); all API fetching is client-side (React Query in `app/providers.tsx`). No tests.
- `weather-egg-iot/` — ESP8266 Arduino sketch publishing `{"temperature","humidity"}` JSON to MQTT every minute.
- Data flow: IoT → MQTT → API → Postgres; browser UI → API over HTTP directly (CORS configured in api `config/WebConfig.java`, allows `localhost:3000` only).

## Commands

- Full stack: `docker compose up --build` (needs root `.env` with `LTA_API_KEY` and `NEXT_PUBLIC_DATAMALL_API_KEY`). UI :3000, API :8080, Swagger UI at `:8080/swagger-ui/index.html`.
- API locally: `docker compose up -d db mqtt`, then `cd weather-egg-api && ./mvnw spring-boot:run`.
- API tests: `./mvnw test` — self-contained (H2 `test` profile; MQTT beans are `@Profile("!test")`, no services needed). Single test: `./mvnw test -Dtest=WeatherServiceTest`.
- UI verify: `cd weather-egg-ui && npm run build` (this is also the typecheck). Lint: `npx eslint app/` (no npm script exists).

## Gotchas

- API changes under Docker require `docker compose restart api` — sources are volume-mounted but nothing recompiles in-container until restart re-runs `mvn spring-boot:run`. UI hot-reloads on its own.
- UI must use `NEXT_PUBLIC_BACKEND_API_URL=http://localhost:8080` even in Docker (compose sets this) — the browser cannot resolve the internal `api:8080` hostname.
- Never send the LTA `AccountKey` header from the UI; the API injects it server-side (`LtaApiWebClientConfig`).
- `npx tsc --noEmit` checks nothing in the UI: `tsconfig.json` `include` omits `./app`. Rely on `next build`.
- UI ESLint config is Vite-derived; `react-refresh/only-export-components` false-positives on Next.js `metadata` exports (known pre-existing error in `app/layout.tsx`). Not a real failure.
- No DB migrations: `spring.jpa.hibernate.ddl-auto=update` auto-alters the schema on entity changes. `WeatherEntity.timestamp` is `OffsetDateTime` and must stay timezone-aware — the UI parses it in the browser (naive timestamps caused an 8h display offset).
- Real API keys/passwords are committed in `weather-egg-api/src/main/resources/application.properties` and `.env*` files (personal-project convention). Don't propagate them elsewhere; CI uses GitHub secrets instead.
- CI (`.github/workflows/maven.yml`) only builds/tests the API (`mvn -B package`, JDK 21). No UI CI — run `npm run build` before pushing UI changes.
- IoT: copy `arduino_secrets.example.h` to `arduino_secrets.h` before flashing.

## Other instruction files

- `.github/copilot-instructions.md` — user's preferred chat style (terse "caveman" mode; opt-out phrase: "normal mode"). Code, commits, and PRs are always written normally.
