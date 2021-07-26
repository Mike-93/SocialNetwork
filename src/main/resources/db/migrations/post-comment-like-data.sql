--liquibase formatted sql
--changeset nikolai:data-post-comment-like

ALTER TABLE post_comment DROP COLUMN time;

ALTER TABLE post_comment ADD time BIGINT;

INSERT INTO post_comment (post_id,parent_id,author_id,comment_text,is_blocked,"time")
    VALUES (3,null,6,'HTML нужно учить вместе с CSS',false,1627290651000),
           	   (1,null,6,'Чтобы выбрать, надо попробовать и понять, что ближе',false,1627290651000),
           	   (1,2,3,'Но с чего то надо начать',false,1627292851000),
           	   (1,3,6,'Надо начать с простого, с бесплатного видео урока, типа Hello World',false,1627299751000),
           	   (5,null,7,'Каждому своё',false,1627290651000),
           	   (5,5,6,'Мне больше нравиться Бэк',false,1627297651000),
           	   (6,null,2,'Давно пользуюсь, но вопросы ещё возникают',false,1627290651000),
           	   (13,null,5,'Idea огонь',false,1627290651000),
           	   (11,null,2,'Полезная вещ',false,1627290651000),
           	   (14,null,9,'Это вообще отдельная профессия',false,1627290651000);

ALTER TABLE post_like DROP COLUMN time;

ALTER TABLE post_like ADD time BIGINT;

INSERT INTO post_like ("time",person_id,post_id)
	VALUES ('1627290651000',1,4),
	       ('1627290651000',1,1),
	       ('1627290651000',1,7),
	       ('1627290651000',1,11),
	       ('1627290651000',2,3),
	       ('1627290651000',3,4),
	       ('1627290651000',6,1),
	       ('1627290651000',6,2),
	       ('1627290651000',6,6),
	       ('1627290651000',6,8),
	       ('1627290651000',8,3),
	       ('1627290651000',5,6),
	       ('1627290651000',5,8),
	       ('1627290651000',7,3),
	       ('1627290651000',9,3);

