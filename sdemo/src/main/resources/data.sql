INSERT INTO user(`id`, `active`, `password`, `roles`, `user_name`) VALUES (1, 1, '$2a$10$q2jcgyOPYKhiqd4aIjcF6Ow/tOzbc60enbkBDNM0opp/U8p6N86NS', 'ADMIN', 'foo');
INSERT INTO RECIPE ( creation_date,suitable_for,veg,instructions) VALUES('2020-07-03 07:07:18.428',5,1,'Test instructions');
INSERT INTO ingredients(recipe_id, item) VALUES(1,'Turmaric');
INSERT INTO ingredients(recipe_id, item) VALUES(1,'Red Chiili');