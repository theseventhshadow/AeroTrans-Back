# Despliegue en EC2

## Requisitos

- Una instancia EC2 con Docker Engine y Docker Compose v2.
- Un Security Group que permita el puerto del BFF (`8080` por defecto) desde el front.
- Los puertos `8081` y `8082` no deben exponerse públicamente; solo son útiles para diagnóstico desde la instancia.

## Configuración

Desde la raíz del repositorio:

```bash
cp .env.example .env
chmod 600 .env
nano .env
```

Cambia las dos contraseñas por valores fuertes. El archivo `.env` está excluido de Git y Compose lo carga automáticamente.

## Arranque

```bash
docker compose build
docker compose up -d
docker compose ps
docker compose logs -f bff
```

El front debe consumir el BFF mediante `http://<IP-o-DNS-de-EC2>:8080`. Dentro de la red Docker, el BFF puede resolver los servicios como `http://catalogs:8080` y `http://transfers:8080`.

## Operación

```bash
docker compose pull catalogs-db transfers-db
docker compose up -d --build
docker compose logs --tail=100 catalogs transfers
docker compose down
```

`docker compose down` no elimina los datos. Los datos viven en los volúmenes `catalogs-db-data` y `transfers-db-data`.

Para una copia lógica de cada base:

```bash
docker compose exec -T catalogs-db sh -c 'pg_dump -U "$POSTGRES_USER" "$POSTGRES_DB"' > catalogs-backup.sql
docker compose exec -T transfers-db sh -c 'pg_dump -U "$POSTGRES_USER" "$POSTGRES_DB"' > transfers-backup.sql
```

En producción conviene sustituir `SPRING_JPA_HIBERNATE_DDL_AUTO=update` por `validate` después de incorporar migraciones versionadas.