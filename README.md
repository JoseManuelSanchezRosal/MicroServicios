
# ✈️ Sistema de Reservas para Agencia de Viajes

Este proyecto es una aplicación **monolítica** desarrollada con **Spring Boot**, que implementa una **API REST** para gestionar reservas de vuelos y hoteles. El sistema controla la disponibilidad de plazas de vuelo y permite vincular reservas a hoteles y vuelos.

---

## 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Base de datos: PostgreSQL / MySQL
- Maven
- GitHub
- Frontend básico (HTML, CSS, JS)

---

## 🧱 Estructura del proyecto

```
src/main/java/com/Agencia/reservas
│
├── controller
│   ├── HotelController.java
│   ├── VueloController.java
│   └── ReservaController.java
|
├── dto
│   ├── MessageDTO.java
│   ├── ReservaDTO.java
│
├── model
│   ├── Hotel.java
│   ├── Vuelo.java
│   └── Reserva.java
|
├── repository
│   ├── HotelRepository.java
│   ├── VueloRepository.java
│   └── ReservaRepository.java
│
└── MicroservicioAgendaApplication.java
```

---

## 📄 Entidades

### 🏨 Hotel
- `id`: Long
- `nombre`: String
- `categoria`: String
- `precio`: double
- `disponibilidad`: boolean

### ✈️ Vuelo
- `id`: Long
- `compania`: String
- `fecha`: String
- `precio`: double
- `plazasDisponibles`: int

### 📦 Reserva
- `id`: Long
- `usuario`: String
- `dni`: String
- `vuelo`: Vuelo (relación ManyToOne)
- `hotel`: Hotel (relación ManyToOne)

---

## 🔁 Operaciones REST


| Entidad  | GET (listar / detalle) | POST (crear) | PUT (actualizar) | DELETE (eliminar) |
|----------|------------------------|--------------|------------------|-------------------|
| Hotel    | ✅                     | ✅          | ✅               | ✅               |
| Vuelo    | ✅                     | ✅          | ✅               | ✅               |
| Reserva  | ✅                     | ✅          | ✅               | ✅               |

> 🔒 Si un vuelo no tiene plazas disponibles, la API lanza un error HTTP 400.

---

## ⚙️ Funcionamiento

- Al crear una reserva, se valida la disponibilidad de plazas en el vuelo.
- Si hay plazas disponibles, se descuenta una y se guarda la reserva.
- Si no hay plazas, se lanza una excepción con código 400.

---

## 🌐 Frontend (opcional)

Interfaz simple con:

- Cabecera: `"Bienvenido a Mi Agencia"`
- Botones:
  - `Ver Hoteles`
  - `Ver Vuelos`

Archivos incluidos:
- `index.html`
- `index.css`
- `globals.css`
- `utils.css`
- `script.js`

---

## 🚀 Instrucciones para ejecutar

1. Clona el repositorio:

```bash
git clone https://github.com/JoseManuelSanchezRosal/MicroServicios.git
```

2. Configura tu base de datos en `application.properties`

3. Introduce Vuelos y Hoteles: `proporcionados con documento JSON para Postman Agencias.postman_collection`

4. Ejecuta la aplicación.

5. Ejecuta el Front para listar vuelos y hoteles.

## 📑 Autor

Proyecto desarrollado por José Manuel Sánchez Rosal como parte del módulo de **Programación** en el CFGS de Desarrollo de Aplicaciones Multiplataforma.

---

## ✅ Estado

✅ Proyecto funcional y ejecutable directamente tras clonar y configurar la base de datos.
