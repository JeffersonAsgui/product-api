# Product-Api

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

### Build application
- `mvn install`

### Run unit and integration tests
- `mvn test`

### Create a mysql local instance to run the application locally
- Start mysql container and access it's CLI

```shell
docker run -p 3306:3306 -d --name mysql8 -e MYSQL_ROOT_PASSWORD=password mysql/mysql-server:8.0.16
docker exec -it mysql8 bash
mysql -uroot -ppassword
```

- Create local database and user with admin privileges
```shell
CREATE DATABASE api_product_db;
CREATE USER 'user_api_product'@'%' IDENTIFIED BY 'pass_api_product';
GRANT ALL PRIVILEGES ON api_product_db.* TO 'user_api_product'@'%';
```

### Create a redis local instance
- Start redis container and access it's CLI

```shell
docker run -it --name redis -p 6379:6379 redis:5.0.3
```

- Alternative you can edit [application-local.yml](src/main/resources/application-local.yml) **host** to point to the dev database
