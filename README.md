# Bookshop REST API

A REST API for managing a bookshop catalogue: books and their authors, with validation, search, sorting and pagination.

Originally written as a university project. I rebuilt it from scratch and extended it (clean HTTP status codes, validation, search, pagination, a separate authors table with a relation to books).

## Tech stack

- Java 25
- Spring Boot 3.5 (Web, Data JPA, Validation)
- Hibernate / JPA
- MySQL 8
- Maven

## Features

- CRUD for books and authors
- Correct HTTP status codes (`201 Created`, `204 No Content`, `400`, `404`)
- Input validation with readable error messages (`400 Bad Request` with a field → message map)
- Search books by title (partial match, case-insensitive)
- Sorting and pagination via query parameters
- Many-to-one relation: each book belongs to an author; the author is checked on save/edit (`404` if it does not exist)

## Project structure

```
src/main/java/com/siedge/app/Bookshop
├── Controller   REST endpoints, global validation error handler
├── Models       JPA entities (BooksBerezovskyi, AuthorBerezovskyi)
└── Repo         Spring Data JPA repositories
```

## Running locally

1. Install JDK 25 and MySQL 8.
2. Create an empty schema:
   ```sql
   CREATE DATABASE crudbooks;
   ```
3. Check the connection settings in `src/main/resources/application.properties` (default: user `root`, password `root`, port `3306`). Change them to match your MySQL setup.
4. Start the app:
   ```
   ./mvnw spring-boot:run
   ```
   (on Windows: `mvnw.cmd spring-boot:run`) or run `Application` from your IDE.

The tables are created automatically on first start. The API is available at `http://localhost:8080`.

## Endpoints

### Books

| Method | URL | Description | Success |
|---|---|---|---|
| GET | `/books` | List books. Optional: `title`, `page`, `size`, `sort` | 200 |
| GET | `/books/{id}` | Get one book | 200 |
| POST | `/save` | Create a book | 201 |
| PUT | `/edit/{id}` | Update a book | 200 |
| DELETE | `/delete/{id}` | Delete a book | 204 |

### Authors

| Method | URL | Description | Success |
|---|---|---|---|
| GET | `/authors` | List authors | 200 |
| POST | `/authors` | Create an author | 201 |

## Examples

Create an author:

```
POST /authors
{ "name": "Robert Martin" }
```

Create a book for that author:

```
POST /save
{
  "titleBerezovskyi": "Clean Code",
  "authorBerezovskyi": { "id": 1 },
  "quantityBerezovskyi": 5,
  "priceBerezovskyi": 120
}
```

Search, sort and paginate:

```
GET /books?title=clean&page=0&size=5&sort=priceBerezovskyi,desc
```

Validation error (`400 Bad Request`):

```json
{
  "titleBerezovskyi": "Title must not be empty",
  "priceBerezovskyi": "Price must be greater than 0"
}
```

Missing author (`404 Not Found`):

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Author with id 999 not found",
  "path": "/save"
}
```

## Notes and possible improvements

- Database credentials are kept in `application.properties` for local development only; they should be moved to environment variables for any real deployment.
- Prices are stored as integers; `BigDecimal` would be the right choice for real money.
- Planned: Swagger/OpenAPI documentation, automated tests, endpoints for editing and deleting authors.
