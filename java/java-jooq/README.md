# JOOQ

## 1. 기초데이터 생성

### 1.1 MYSQL DB, 사용자, 권한 설정

```javascript
create database db_jooq;
create user 'jooq'@'%' identified by 'pray';
grant all privileges on db_jooq.* to 'jooq'@'%';
```

### 1.2 Table 생성

```javascript
CREATE TABLE `author` (
  `id` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```

### 1.3 데이터 삽입

```javascript
INSERT INTO author(id, first_name, last_name) VALUES (0, 'Mr', 'Dj');
INSERT INTO author(id, first_name, last_name) VALUES (1, 'Eyes', 'Khepri');
```