# 📌 Foro Hub API

API REST desarrollada con **Spring Boot** que implementa un sistema de foro con autenticación JWT, gestión de usuarios, cursos, tópicos y respuestas.  
El proyecto sigue una arquitectura en capas (`controller`, `domain`, `infra`), con validaciones personalizadas y configuración de seguridad.

---

## 🚀 Características

- Autenticación y autorización con **JWT**.
- CRUD de **tópicos** (crear, listar, obtener detalle, actualizar y eliminar).
- Asociación de tópicos con **cursos** y **usuarios**.
- Gestión de **respuestas** a tópicos.
- Validación para evitar tópicos duplicados.
- Manejo centralizado de excepciones.
- Seguridad configurada con **Spring Security** y filtros personalizados.

---

## 🗂 Estructura del proyecto

```
src/main/java/com/Alura/Foro/Hub
│
├── controller
│   ├── AutenticationController.java
│   └── TopicoController.java
│
├── domain
│   ├── curso
│   │   ├── Categoria.java
│   │   ├── Curso.java
│   │   └── CursoRepository.java
│   │
│   ├── respuesta
│   │   ├── Respuesta.java
│   │   └── RespuestaRepository.java
│   │
│   ├── topico
│   │   ├── AgregarTopico.java
│   │   ├── DatosActualizarTopico.java
│   │   ├── ListaTopico.java
│   │   ├── Topico.java
│   │   ├── TopicoRepository.java
│   │   ├── TopicoService.java
│   │   └── validaciones
│   │       ├── ValidadorDeTopicos.java
│   │       └── ValidarSiHayTopicoDuplicado.java
│   │
│   ├── usuario
│   │   ├── AutenticacionService.java
│   │   ├── DatosAutenticacion.java
│   │   ├── Usuario.java
│   │   └── UsuarioRepository.java
│   │
│   └── ValidacionTopico.java
│
├── infra
│   ├── exceptions
│   │   └── GestorDeErrores.java
│   │
│   └── security
│       ├── DatosTokenJWT.java
│       ├── SecuriryFilter.java
│       ├── SecurityConfigurations.java
│       └── TokenService.java
```

---

## ⚙️ Tecnologías utilizadas

- Java 17+
- Spring Boot 3+
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (Java Web Token) con Auth0
- Lombok
- Jakarta Validation
- Maven

---

## 🔑 Autenticación

La API utiliza **JWT** para autenticar a los usuarios.

### 1️⃣ Iniciar sesión
**Request**
```http
POST /login
Content-Type: application/json

{
  "correo": "usuario@ejemplo.com",
  "contrasena": "password"
}
```

**Response**
```json
{
  "token": "JWT_GENERADO"
}
```

El token debe enviarse en cada petición protegida usando el header `Authorization`:
```
Authorization: Bearer JWT_GENERADO
```

---

## 📌 Endpoints principales

### 🔹 Autenticación
- `POST /login` → Iniciar sesión y obtener un token JWT.

### 🔹 Tópicos
- `POST /topicos` → Crear un nuevo tópico.
- `GET /topicos` → Listar tópicos paginados.
- `GET /topicos/{id}` → Obtener detalles de un tópico.
- `PUT /topicos/{id}` → Actualizar un tópico.
- `DELETE /topicos/{id}` → Eliminar un tópico.

---

## ▶️ Ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/usuario/foro-hub.git
   cd foro-hub
   ```

2. Configurar la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   api.security.token.secret=clave_secreta
   ```

3. Compilar y ejecutar:
   ```bash
   mvn spring-boot:run
   ```

---

## 🛠 Validaciones implementadas

- No se permite registrar un tópico con el mismo **título** y **mensaje** que otro ya existente.
- Validación automática de campos obligatorios con **Jakarta Validation**.
- Manejo centralizado de errores mediante `GestorDeErrores`.

---

## 📄 Licencia
Este proyecto es de uso libre con fines educativos.
