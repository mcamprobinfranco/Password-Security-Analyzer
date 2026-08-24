# 🔐 Password Analyzer

Aplicación web full-stack que analiza la seguridad de una contraseña en tiempo real: calcula su entropía, detecta patrones débiles y ofrece recomendaciones de mejora. Incluye comparación de contraseñas, generador seguro, historial local y modo oscuro.

Proyecto desarrollado para practicar un flujo de trabajo profesional con Git (ramas, Pull Requests, resolución de conflictos) además de Spring Boot, React y Docker.

## ✨ Funcionalidades

- **Análisis de contraseñas**: longitud, composición (mayúsculas/minúsculas/números/símbolos), entropía aproximada, detección de patrones comunes y repeticiones, con sugerencias de mejora personalizadas.
- **Comparación**: analiza dos contraseñas en paralelo e indica cuál es más fuerte.
- **Generador seguro**: crea contraseñas aleatorias con longitud y composición configurables.
- **Historial local**: guarda las últimas 10 acciones (análisis, generación, comparación) en el navegador — **nunca se almacena la contraseña en texto plano**, solo sus métricas de fortaleza.
- **Modo oscuro**: con persistencia de preferencia.

## 🛠️ Stack técnico

| Capa | Tecnología |
|---|---|
| Backend | Java 21, Spring Boot, Maven |
| Frontend | React, Vite |
| Contenedores | Docker, Docker Compose, nginx |

## 🚀 Cómo ejecutarlo

### Opción 1: con Docker (recomendado)

Requiere tener [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado y en ejecución.

```bash
docker compose up --build
```

- Frontend: http://localhost:5173
- Backend: http://localhost:8080

Para detener:

```bash
docker compose down
```

### Opción 2: en local, sin Docker

**Backend** (requiere Java 21 y Maven):

```bash
cd backend/password-analyzer
./mvnw spring-boot:run
```

Levanta la API en `http://localhost:8080`.

**Frontend** (requiere Node.js):

```bash
cd frontend
npm install
npm run dev
```

Levanta la app en `http://localhost:5173`.

## 📡 Endpoints de la API

| Método | Endpoint | Descripción |
|---|---|---|
| `POST` | `/api/passwords/analyze` | Analiza una contraseña |
| `POST` | `/api/passwords/compare` | Compara dos contraseñas |
| `POST` | `/api/passwords/generate` | Genera una contraseña segura |

Ejemplo de petición a `/analyze`:

```json
{
  "password": "Hola1234!"
}
```

Respuesta:

```json
{
  "length": 9,
  "lowercaseCount": 4,
  "uppercaseCount": 1,
  "digitCount": 4,
  "symbolCount": 0,
  "entropy": 41.2,
  "hasRepeatedChars": false,
  "detectedPatterns": [],
  "strengthLevel": "MEDIUM",
  "suggestions": ["Añade símbolos (!@#$...)"]
}
```

## 🔒 Consideraciones de seguridad

Este proyecto es una herramienta educativa/demostrativa de análisis de contraseñas, con algunas decisiones de diseño pensadas desde una perspectiva de seguridad:

- Las contraseñas nunca se persisten en el backend ni en el historial del frontend — el análisis es *stateless*, se calcula y se devuelve, sin guardar el valor original en ningún punto.
- La entropía se calcula de forma aproximada, asumiendo espacio de búsqueda uniforme según los tipos de caracteres presentes — no sustituye a herramientas más avanzadas como `zxcvbn`, que modelan patrones lingüísticos y de teclado reales.
- El generador utiliza `SecureRandom` (Java) en el backend, adecuado para generación de valores criptográficamente aleatorios, en lugar de generadores pseudoaleatorios estándar.

## 📂 Estructura del proyecto

password-analyzer/

├──backend/password-analyzer/ # API REST (Spring Boot)

├── frontend/ # Interfaz (React + Vite)

├── docker-compose.yml

└── README.md

## 🌱 Flujo de desarrollo

El proyecto se ha construido siguiendo un flujo basado en Git Flow simplificado: rama `main` estable, `develop` como integración, y una rama `feature/` por cada funcionalidad, cerrada mediante Pull Request.

## 👤 Autor

Martín — [LinkedIn](www.linkedin.com/in/martín-camprobin-franco-a5801b383) · [GitHub](https://github.com/mcamprobinfranco)