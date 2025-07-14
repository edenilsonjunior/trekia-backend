/*
1. Introdução
Este documento descreve os requisitos e os casos de uso do sistema de Planejamento de Viagem. O objetivo do sistema é permitir que usuários planejem suas viagens com base em dados reais, como previsão do tempo e conversão de moedas, integrando-se com APIs públicas.
2. Visão Geral do Sistema
O sistema permite que os usuários cadastrem viagens, consultem clima e moeda dos destinos, criem roteiros e visualizem um dashboard com informações úteis. As funcionalidades são acessadas através de uma aplicação Angular, com backend em Spring e banco de dados Oracle.

*/

-- ========================================
-- 1. Criação do usuário e permissões
-- ========================================
CREATE USER trekia_user IDENTIFIED BY senha123;

GRANT CONNECT, RESOURCE TO trekia_user;

ALTER USER trekia_user QUOTA UNLIMITED ON USERS;

-- ========================================
-- 2. Criação das tabelas
-- ========================================
CREATE TABLE tb_users (
    id                      NUMBER NOT NULL,
    name                    VARCHAR2(100) NOT NULL,
    email                   VARCHAR2(100) UNIQUE NOT NULL,
    password                VARCHAR2(255) NOT NULL,
    created_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE trip (
    id                      NUMBER NOT NULL,
    user_id                 NUMBER NOT NULL,
    title                   VARCHAR2(30) NOT NULL,
    description             VARCHAR2(120),
    start_date              DATE NOT NULL,
    end_date                DATE NOT NULL,
    created_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT pk_trip PRIMARY KEY (id),
    CONSTRAINT fk_trip_user FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

CREATE TABLE check_item (
    id                      NUMBER NOT NULL,
    trip_id                 NUMBER NOT NULL,
    description             VARCHAR2(120) NOT NULL,
    is_checked              NUMBER(1),

    CONSTRAINT pk_checkitem PRIMARY KEY (id),
    CONSTRAINT fk_checkitem_trip FOREIGN KEY (trip_id) REFERENCES trip(id)
);

CREATE TABLE schedule (
    id                      NUMBER NOT NULL,
    trip_id                 NUMBER NOT NULL,
    planned_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    title                   VARCHAR2(120) NOT NULL,
    description             VARCHAR2(120),
    latitude                DECIMAL(8,6) NOT NULL,
    longitude               DECIMAL(9,6) NOT NULL,
    current_local_balance   DECIMAL NOT NULL,
    currency_code           CHAR(3) NOT NULL,
    min_temperature         DECIMAL(3,1) NOT NULL,
    max_temperature         DECIMAL(3,1) NOT NULL,
    precipitation_chance    NUMBER NOT NULL,

    CONSTRAINT pk_schedule PRIMARY KEY (id),
    CONSTRAINT fk_schedule_trip FOREIGN KEY (trip_id) REFERENCES trip(id)
);

CREATE TABLE trip_media (
    id                      NUMBER NOT NULL,
    trip_id                 NUMBER NOT NULL,
    media                   BLOB,
    description             VARCHAR2(100),
    uploaded_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT pk_tripmedia PRIMARY KEY (id),
    CONSTRAINT fk_tripmedia_trip FOREIGN KEY (trip_id) REFERENCES trip(Id)
);

-- SEQUENCES E TRIGGERS PARA AUTO INCREMENT

-- SEQUENCES
CREATE SEQUENCE tb_users_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE SEQUENCE trip_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE SEQUENCE check_item_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE SEQUENCE schedule_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE SEQUENCE trip_media_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- TRIGGERS

CREATE OR REPLACE TRIGGER tb_users_bir
BEFORE INSERT ON tb_users
FOR EACH ROW
BEGIN
  IF :NEW.id IS NULL THEN
    SELECT tb_users_seq.NEXTVAL INTO :NEW.id FROM dual;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER trip_bir
BEFORE INSERT ON trip
FOR EACH ROW
BEGIN
  IF :NEW.id IS NULL THEN
    SELECT trip_seq.NEXTVAL INTO :NEW.id FROM dual;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER check_item_bir
BEFORE INSERT ON check_item
FOR EACH ROW
BEGIN
  IF :NEW.id IS NULL THEN
    SELECT check_item_seq.NEXTVAL INTO :NEW.id FROM dual;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER schedule_bir
BEFORE INSERT ON schedule
FOR EACH ROW
BEGIN
  IF :NEW.id IS NULL THEN
    SELECT schedule_seq.NEXTVAL INTO :NEW.id FROM dual;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER trip_media_bir
BEFORE INSERT ON trip_media
FOR EACH ROW
BEGIN
  IF :NEW.id IS NULL THEN
    SELECT trip_media_seq.NEXTVAL INTO :NEW.id FROM dual;
  END IF;
END;
/
