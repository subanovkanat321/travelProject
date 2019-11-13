INSERT INTO tour(description, location)
VALUES ('Самое большое озеро в Кыргызстане','Иссык-Куль'),
       ('Первая столица Казакстана', 'Алматы'),
       ('Самый древний город Кыргызстана', 'Ош');

INSERT INTO category(description, level)
VALUES ('5 star', 5),
       ('4 star', 4),
       ('3 star', 3);

INSERT INTO kindness(kindness)
VALUES ('Hotel'),
       ('Wifi'),
       ('Gid'),
       ('Car');

INSERT INTO category_kindness(category_id, kindness_id)
VALUES (1,1),
       (1,2),
       (1,3),
       (1,4),
       (2,1),
       (2,2),
       (2,3),
       (3,1),
       (3,2);

INSERT INTO price(price, category_id, tour)
VALUES (150,1,1),
       (130,2,1),
       (100,3,1),
       (200,1,2),
       (160,2,2),
       (140,3,2),
       (120,1,3),
       (100,2,3),
       (80,3,3);
