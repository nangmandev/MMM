insert into food_category(name, color) values ('KOREA' , 'FF0000'), ('JAPAN', '11CF00'),  ('CHINA', '0029FF'), ('WESTERN', 'FFB800');
insert into mbti (mbti_name) values ('EI'), ('NS'), ('TF'), ('JP'), ('MINT'), ('PINE'), ('DIE');


# 먹적
insert into mukjuk (mukjuk_id, name, context, image_src) values 
(1, '한식 입문자', '한식을 3회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/korea-1.svg'),
(2, '한식 초심자', '한식을 10회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/korea-2.svg'),
(3, '한식 애호가', '한식을 30회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/korea-3.svg'),
(4, '한식 중독자', '한식을 100회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/korea-4.svg'),
(5, '일식 입문자', '일식을 3회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/japan-1.svg'),
(6, '일식 초심자', '일식을 10회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/japan-2.svg'),
(7, '일식 애호가', '일식을 30회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/japan-3.svg'),
(8, '일식 중독자', '일식을 100회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/japan-4.svg'),
(9, '중식 입문자', '중식을 3회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/china-1.svg'),
(10, '중식 초심자', '중식을 10회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/china-2.svg'),
(11, '중식 애호가', '중식을 30회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/china-3.svg'),
(12, '중식 중독자', '중식을 100회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/china-4.svg'),
(13, '양식 입문자', '양식을 3회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/western-1.svg'),
(14, '양식 초심자', '양식을 10회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/western-2.svg'),
(15, '양식 애호가', '양식을 30회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/western-3.svg'),
(16, '양식 중독자', '양식을 100회 먹기', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/western-4.svg'),
(17, '맵찔이', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/e100.svg'),
(18, '맵당당', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/i100.svg'),
(19, '건강맨', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/f100.svg'),
(20, '쳐묵맨', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/t100.svg'),
(21, '쌀 애호가', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/s100.svg'),
(22, '면 애호가', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/n100.svg'),
(23, '응애', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/j100.svg'),
(24, '으른', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/p100.svg'),
(25, '이탈리안', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/pine0.svg'),
(26, '하와이안', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/pine100.svg'),
(27, '난 죽음을 택하겠다', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/die100.svg'),
(28, '거를 타선이 없는', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/die0.svg'),
(29, '치약을 왜 먹죠?', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/mint0.svg'),
(30, '민초단', '비밀~', 'https://dx2sqphjsuclj.cloudfront.net/mukjuk/mint100.svg')
;


insert into food (food_id, name, image, food_category_id) values (1,'백반', 'https://dx2sqphjsuclj.cloudfront.net/food/1.svg', 1);
insert into food (food_id, name, image, food_category_id) values (2,'김밥', 'https://dx2sqphjsuclj.cloudfront.net/food/2.svg', 1);
insert into food (food_id, name, image, food_category_id) values (3,'김치볶음밥', 'https://dx2sqphjsuclj.cloudfront.net/food/3.svg', 1);
insert into food (food_id, name, image, food_category_id) values (4,'낙지덮밥', 'https://dx2sqphjsuclj.cloudfront.net/food/4.svg', 1);
insert into food (food_id, name, image, food_category_id) values (5,'돌솥비빔밥', 'https://dx2sqphjsuclj.cloudfront.net/food/5.svg', 1);
insert into food (food_id, name, image, food_category_id) values (6,'돼지국밥', 'https://dx2sqphjsuclj.cloudfront.net/food/6.svg', 1);
insert into food (food_id, name, image, food_category_id) values (7,'비빔밥', 'https://dx2sqphjsuclj.cloudfront.net/food/7.svg', 1);
insert into food (food_id, name, image, food_category_id) values (8,'쌈밥', 'https://dx2sqphjsuclj.cloudfront.net/food/8.svg', 1);
insert into food (food_id, name, image, food_category_id) values (9,'알밥', 'https://dx2sqphjsuclj.cloudfront.net/food/9.svg', 1);
insert into food (food_id, name, image, food_category_id) values (10,'육회비빔밥', 'https://dx2sqphjsuclj.cloudfront.net/food/10.svg', 1);
insert into food (food_id, name, image, food_category_id) values (11,'콩나물국밥', 'https://dx2sqphjsuclj.cloudfront.net/food/11.svg', 1);
insert into food (food_id, name, image, food_category_id) values (12,'회덮밥', 'https://dx2sqphjsuclj.cloudfront.net/food/12.svg', 1);
insert into food (food_id, name, image, food_category_id) values (13,'수제비', 'https://dx2sqphjsuclj.cloudfront.net/food/13.svg', 1);
insert into food (food_id, name, image, food_category_id) values (14,'고기국수', 'https://dx2sqphjsuclj.cloudfront.net/food/14.svg', 1);
insert into food (food_id, name, image, food_category_id) values (15,'김치말이국수', 'https://dx2sqphjsuclj.cloudfront.net/food/15.svg', 1);
insert into food (food_id, name, image, food_category_id) values (16,'칼국수', 'https://dx2sqphjsuclj.cloudfront.net/food/16.svg', 1);
insert into food (food_id, name, image, food_category_id) values (17,'막국수', 'https://dx2sqphjsuclj.cloudfront.net/food/17.svg', 1);
insert into food (food_id, name, image, food_category_id) values (18,'냉면', 'https://dx2sqphjsuclj.cloudfront.net/food/18.svg', 1);
insert into food (food_id, name, image, food_category_id) values (19,'평양냉면', 'https://dx2sqphjsuclj.cloudfront.net/food/19.svg', 1);
insert into food (food_id, name, image, food_category_id) values (20,'잔치국수', 'https://dx2sqphjsuclj.cloudfront.net/food/20.svg', 1);
insert into food (food_id, name, image, food_category_id) values (21,'초계국수', 'https://dx2sqphjsuclj.cloudfront.net/food/21.svg', 1);
insert into food (food_id, name, image, food_category_id) values (22,'콩국수', 'https://dx2sqphjsuclj.cloudfront.net/food/22.svg', 1);
insert into food (food_id, name, image, food_category_id) values (23,'육개장', 'https://dx2sqphjsuclj.cloudfront.net/food/23.svg', 1);
insert into food (food_id, name, image, food_category_id) values (24,'순댓국', 'https://dx2sqphjsuclj.cloudfront.net/food/24.svg', 1);
insert into food (food_id, name, image, food_category_id) values (25,'해장국', 'https://dx2sqphjsuclj.cloudfront.net/food/25.svg', 1);
insert into food (food_id, name, image, food_category_id) values (26,'뼈해장국', 'https://dx2sqphjsuclj.cloudfront.net/food/26.svg', 1);
insert into food (food_id, name, image, food_category_id) values (27,'갈비탕', 'https://dx2sqphjsuclj.cloudfront.net/food/27.svg', 1);
insert into food (food_id, name, image, food_category_id) values (28,'곰탕', 'https://dx2sqphjsuclj.cloudfront.net/food/28.svg', 1);
insert into food (food_id, name, image, food_category_id) values (29,'매운탕', 'https://dx2sqphjsuclj.cloudfront.net/food/29.svg', 1);
insert into food (food_id, name, image, food_category_id) values (30,'내장탕', 'https://dx2sqphjsuclj.cloudfront.net/food/30.svg', 1);
insert into food (food_id, name, image, food_category_id) values (31,'삼계탕', 'https://dx2sqphjsuclj.cloudfront.net/food/31.svg', 1);
insert into food (food_id, name, image, food_category_id) values (32,'설렁탕', 'https://dx2sqphjsuclj.cloudfront.net/food/32.svg', 1);
insert into food (food_id, name, image, food_category_id) values (33,'추어탕', 'https://dx2sqphjsuclj.cloudfront.net/food/33.svg', 1);
insert into food (food_id, name, image, food_category_id) values (34,'고추장찌개', 'https://dx2sqphjsuclj.cloudfront.net/food/34.svg', 1);
insert into food (food_id, name, image, food_category_id) values (35,'김치찌개', 'https://dx2sqphjsuclj.cloudfront.net/food/35.svg', 1);
insert into food (food_id, name, image, food_category_id) values (36,'동태찌개', 'https://dx2sqphjsuclj.cloudfront.net/food/36.svg', 1);
insert into food (food_id, name, image, food_category_id) values (37,'된장찌개', 'https://dx2sqphjsuclj.cloudfront.net/food/37.svg', 1);
insert into food (food_id, name, image, food_category_id) values (38,'부대찌개', 'https://dx2sqphjsuclj.cloudfront.net/food/38.svg', 1);
insert into food (food_id, name, image, food_category_id) values (39,'순두부찌개', 'https://dx2sqphjsuclj.cloudfront.net/food/39.svg', 1);
insert into food (food_id, name, image, food_category_id) values (40,'청국장찌개', 'https://dx2sqphjsuclj.cloudfront.net/food/40.svg', 1);
insert into food (food_id, name, image, food_category_id) values (41,'닭백숙', 'https://dx2sqphjsuclj.cloudfront.net/food/41.svg', 1);
insert into food (food_id, name, image, food_category_id) values (42,'등갈비묵은지찜', 'https://dx2sqphjsuclj.cloudfront.net/food/42.svg', 1);
insert into food (food_id, name, image, food_category_id) values (43,'매운갈비찜', 'https://dx2sqphjsuclj.cloudfront.net/food/43.svg', 1);
insert into food (food_id, name, image, food_category_id) values (44,'보쌈', 'https://dx2sqphjsuclj.cloudfront.net/food/44.svg', 1);
insert into food (food_id, name, image, food_category_id) values (45,'아귀찜', 'https://dx2sqphjsuclj.cloudfront.net/food/45.svg', 1);
insert into food (food_id, name, image, food_category_id) values (46,'안동찜닭', 'https://dx2sqphjsuclj.cloudfront.net/food/46.svg', 1);
insert into food (food_id, name, image, food_category_id) values (47,'떡볶이', 'https://dx2sqphjsuclj.cloudfront.net/food/47.svg', 1);
insert into food (food_id, name, image, food_category_id) values (48,'닭갈비', 'https://dx2sqphjsuclj.cloudfront.net/food/48.svg', 1);
insert into food (food_id, name, image, food_category_id) values (49,'닭볶음탕', 'https://dx2sqphjsuclj.cloudfront.net/food/49.svg', 1);
insert into food (food_id, name, image, food_category_id) values (50,'제육볶음', 'https://dx2sqphjsuclj.cloudfront.net/food/50.svg', 1);
insert into food (food_id, name, image, food_category_id) values (51,'쭈꾸미볶음', 'https://dx2sqphjsuclj.cloudfront.net/food/51.svg', 1);
insert into food (food_id, name, image, food_category_id) values (52,'갈치조림', 'https://dx2sqphjsuclj.cloudfront.net/food/52.svg', 1);
insert into food (food_id, name, image, food_category_id) values (53,'고등어조림', 'https://dx2sqphjsuclj.cloudfront.net/food/53.svg', 1);
insert into food (food_id, name, image, food_category_id) values (54,'곱창전골', 'https://dx2sqphjsuclj.cloudfront.net/food/54.svg', 1);
insert into food (food_id, name, image, food_category_id) values (55,'만두전골', 'https://dx2sqphjsuclj.cloudfront.net/food/55.svg', 1);
insert into food (food_id, name, image, food_category_id) values (56,'생선구이', 'https://dx2sqphjsuclj.cloudfront.net/food/56.svg', 1);
insert into food (food_id, name, image, food_category_id) values (57,'곱창', 'https://dx2sqphjsuclj.cloudfront.net/food/57.svg', 1);
insert into food (food_id, name, image, food_category_id) values (58,'삼겹살', 'https://dx2sqphjsuclj.cloudfront.net/food/58.svg', 1);
insert into food (food_id, name, image, food_category_id) values (59,'불고기', 'https://dx2sqphjsuclj.cloudfront.net/food/59.svg', 1);
insert into food (food_id, name, image, food_category_id) values (60,'숯불닭갈비', 'https://dx2sqphjsuclj.cloudfront.net/food/60.svg', 1);
insert into food (food_id, name, image, food_category_id) values (61,'양념갈비', 'https://dx2sqphjsuclj.cloudfront.net/food/61.svg', 1);
insert into food (food_id, name, image, food_category_id) values (62,'간장게장', 'https://dx2sqphjsuclj.cloudfront.net/food/62.svg', 1);
insert into food (food_id, name, image, food_category_id) values (63,'만두', 'https://dx2sqphjsuclj.cloudfront.net/food/63.svg', 1);
insert into food (food_id, name, image, food_category_id) values (64,'만둣국', 'https://dx2sqphjsuclj.cloudfront.net/food/64.svg', 2);
insert into food (food_id, name, image, food_category_id) values (65,'볶음밥', 'https://dx2sqphjsuclj.cloudfront.net/food/65.svg', 2);
insert into food (food_id, name, image, food_category_id) values (66,'짜장밥', 'https://dx2sqphjsuclj.cloudfront.net/food/66.svg', 2);
insert into food (food_id, name, image, food_category_id) values (67,'짬뽕밥', 'https://dx2sqphjsuclj.cloudfront.net/food/67.svg', 2);
insert into food (food_id, name, image, food_category_id) values (68,'중화비빔밥', 'https://dx2sqphjsuclj.cloudfront.net/food/68.svg', 2);
insert into food (food_id, name, image, food_category_id) values (69,'마파두부덮밥', 'https://dx2sqphjsuclj.cloudfront.net/food/69.svg', 2);
insert into food (food_id, name, image, food_category_id) values (70,'잡채밥', 'https://dx2sqphjsuclj.cloudfront.net/food/70.svg', 2);
insert into food (food_id, name, image, food_category_id) values (71,'짜장면', 'https://dx2sqphjsuclj.cloudfront.net/food/71.svg', 2);
insert into food (food_id, name, image, food_category_id) values (72,'짬뽕', 'https://dx2sqphjsuclj.cloudfront.net/food/72.svg', 2);
insert into food (food_id, name, image, food_category_id) values (73,'중국냉면', 'https://dx2sqphjsuclj.cloudfront.net/food/73.svg', 2);
insert into food (food_id, name, image, food_category_id) values (74,'탕수육', 'https://dx2sqphjsuclj.cloudfront.net/food/74.svg', 2);
insert into food (food_id, name, image, food_category_id) values (75,'김치피자탕수육', 'https://dx2sqphjsuclj.cloudfront.net/food/75.svg', 2);
insert into food (food_id, name, image, food_category_id) values (76,'꿔바로우', 'https://dx2sqphjsuclj.cloudfront.net/food/76.svg', 2);
insert into food (food_id, name, image, food_category_id) values (77,'마라탕', 'https://dx2sqphjsuclj.cloudfront.net/food/77.svg', 3);
insert into food (food_id, name, image, food_category_id) values (78,'스시', 'https://dx2sqphjsuclj.cloudfront.net/food/78.svg', 3);
insert into food (food_id, name, image, food_category_id) values (79,'가츠동', 'https://dx2sqphjsuclj.cloudfront.net/food/79.svg', 3);
insert into food (food_id, name, image, food_category_id) values (80,'규동', 'https://dx2sqphjsuclj.cloudfront.net/food/80.svg', 3);
insert into food (food_id, name, image, food_category_id) values (81,'텐동', 'https://dx2sqphjsuclj.cloudfront.net/food/81.svg', 3);
insert into food (food_id, name, image, food_category_id) values (82,'우동', 'https://dx2sqphjsuclj.cloudfront.net/food/82.svg', 3);
insert into food (food_id, name, image, food_category_id) values (83,'소바', 'https://dx2sqphjsuclj.cloudfront.net/food/83.svg', 3);
insert into food (food_id, name, image, food_category_id) values (84,'라멘', 'https://dx2sqphjsuclj.cloudfront.net/food/84.svg', 3);
insert into food (food_id, name, image, food_category_id) values (85,'돈카츠', 'https://dx2sqphjsuclj.cloudfront.net/food/85.svg', 3);
insert into food (food_id, name, image, food_category_id) values (86,'샤브샤브', 'https://dx2sqphjsuclj.cloudfront.net/food/86.svg', 3);
insert into food (food_id, name, image, food_category_id) values (87,'오므라이스', 'https://dx2sqphjsuclj.cloudfront.net/food/87.svg', 3);
insert into food (food_id, name, image, food_category_id) values (88,'카레라이스', 'https://dx2sqphjsuclj.cloudfront.net/food/88.svg', 4);
insert into food (food_id, name, image, food_category_id) values (89,'경양식 돈가스', 'https://dx2sqphjsuclj.cloudfront.net/food/89.svg', 4);
insert into food (food_id, name, image, food_category_id) values (90,'함박 스테이크', 'https://dx2sqphjsuclj.cloudfront.net/food/90.svg', 4);
insert into food (food_id, name, image, food_category_id) values (91,'햄버거', 'https://dx2sqphjsuclj.cloudfront.net/food/91.svg', 4);
insert into food (food_id, name, image, food_category_id) values (92,'베트남 쌀국수', 'https://dx2sqphjsuclj.cloudfront.net/food/92.svg', 4);
insert into food (food_id, name, image, food_category_id) values (93,'샌드위치', 'https://dx2sqphjsuclj.cloudfront.net/food/93.svg', 4);
insert into food (food_id, name, image, food_category_id) values (94,'피자', 'https://dx2sqphjsuclj.cloudfront.net/food/94.svg', 4);
insert into food (food_id, name, image, food_category_id) values (95,'토마토 파스타', 'https://dx2sqphjsuclj.cloudfront.net/food/95.svg', 4);
insert into food (food_id, name, image, food_category_id) values (96,'까르보나라', 'https://dx2sqphjsuclj.cloudfront.net/food/96.svg', 4);
insert into food (food_id, name, image, food_category_id) values (97,'알리오 올리오', 'https://dx2sqphjsuclj.cloudfront.net/food/97.svg', 4);

insert into weather(weather_id, name) values(1,'비'), (2,'무더위');
insert into food_weather(food_weather_id, weather_id, food_id) values (1, 1, 13),(2, 1, 15),(3, 2, 18),(4, 2, 19),(5, 2, 21),(6, 2, 22);

insert into food_mbti (food_id, mbti_id, score, food_mbti_id) values (1 , 1 , 25, 1) ,(1 , 2 , 92, 2) ,(1 , 3 , 70, 3) ,(1 , 4 , 71, 4) ,(2 , 1 , 5, 5) ,(2 , 2 , 96, 6) ,(2 , 3 , 76, 7) ,(2 , 4 , 45, 8) ,(3 , 1 , 45, 9) ,(3 , 2 , 98, 10) ,(3 , 3 , 57, 11) ,(3 , 4 , 52, 12) ,(4 , 1 , 61, 13) ,(4 , 2 , 97, 14) ,(4 , 3 , 64, 15) ,(4 , 4 , 66, 16) ,(5 , 1 , 29, 17) ,(5 , 2 , 96, 18) ,(5 , 3 , 74, 19) ,(5 , 4 , 68, 20) ,(6 , 1 , 18, 21) ,(6 , 2 , 94, 22) ,(6 , 3 , 63, 23) ,(6 , 4 , 79, 24) ,(7 , 1 , 31, 25) ,(7 , 2 , 95, 26) ,(7 , 3 , 72, 27) ,(7 , 4 , 65, 28) ,(8 , 1 , 17, 29) ,(8 , 2 , 95, 30) ,(8 , 3 , 78, 31) ,(8 , 4 , 70, 32) ,(9 , 1 , 21, 33) ,(9 , 2 , 96, 34) ,(9 , 3 , 67, 35) ,(9 , 4 , 58, 36) ,(10 , 1 , 21, 37) ,(10 , 2 , 96, 38) ,(10 , 3 , 68, 39) ,(10 , 4 , 58, 40) ,(11 , 1 , 16, 41) ,(11 , 2 , 95, 42) ,(11 , 3 , 75, 43) ,(11 , 4 , 75, 44) ,(12 , 1 , 18, 45) ,(12 , 2 , 97, 46) ,(12 , 3 , 63, 47) ,(12 , 4 , 63, 48) ,(13 , 1 , 18, 49) ,(13 , 2 , 22, 50) ,(13 , 3 , 58, 51) ,(13 , 4 , 63, 52) ,(14 , 1 , 5, 53) ,(14 , 2 , 2, 54) ,(14 , 3 , 57, 55) ,(14 , 4 , 62, 56) ,(15 , 1 , 28, 57) ,(15 , 2 , 7, 58) ,(15 , 3 , 55, 59) ,(15 , 4 , 66, 60) ,(16 , 1 , 5, 61) ,(16 , 2 , 2, 62) ,(16 , 3 , 60, 63) ,(16 , 4 , 62, 64) ,(17 , 1 , 25, 65) ,(17 , 2 , 2, 66) ,(17 , 3 , 60, 67) ,(17 , 4 , 61, 68) ,(18 , 1 , 16, 69) ,(18 , 2 , 0, 70) ,(18 , 3 , 55, 71) ,(18 , 4 , 55, 72) ,(19 , 1 , 0, 73) ,(19 , 2 , 0, 74) ,(19 , 3 , 68, 75) ,(19 , 4 , 83, 76) ,(20 , 1 , 8, 77) ,(20 , 2 , 0, 78) ,(20 , 3 , 61, 79) ,(20 , 4 , 62, 80) ,(21 , 1 , 5, 81) ,(21 , 2 , 0, 82) ,(21 , 3 , 66, 83) ,(21 , 4 , 70, 84) ,(22 , 1 , 0, 85) ,(22 , 2 , 0, 86) ,(22 , 3 , 77, 87) ,(22 , 4 , 77, 88) ,(23 , 1 , 55, 89) ,(23 , 2 , 90, 90) ,(23 , 3 , 63, 91) ,(23 , 4 , 71, 92) ,(24 , 1 , 23, 93) ,(24 , 2 , 90, 94) ,(24 , 3 , 60, 95) ,(24 , 4 , 71, 96) ,(25 , 1 , 40, 97) ,(25 , 2 , 92, 98) ,(25 , 3 , 65, 99) ,(25 , 4 , 77, 100) ,(26 , 1 , 40, 101) ,(26 , 2 , 90, 102) ,(26 , 3 , 67, 103) ,(26 , 4 , 76, 104) ,(27 , 1 , 5, 105) ,(27 , 2 , 85, 106) ,(27 , 3 , 70, 107) ,(27 , 4 , 70, 108) ,(28 , 1 , 5, 109) ,(28 , 2 , 85, 110) ,(28 , 3 , 72, 111) ,(28 , 4 , 73, 112) ,(29 , 1 , 60, 113) ,(29 , 2 , 65, 114) ,(29 , 3 , 58, 115) ,(29 , 4 , 73, 116) ,(30 , 1 , 45, 117) ,(30 , 2 , 75, 118) ,(30 , 3 , 62, 119) ,(30 , 4 , 85, 120) ,(31 , 1 , 7, 121) ,(31 , 2 , 92, 122) ,(31 , 3 , 78, 123) ,(31 , 4 , 72, 124) ,(32 , 1 , 2, 125) ,(32 , 2 , 87, 126) ,(32 , 3 , 76, 127) ,(32 , 4 , 68, 128) ,(33 , 1 , 18, 129) ,(33 , 2 , 92, 130) ,(33 , 3 , 75, 131) ,(33 , 4 , 86, 132) ,(34 , 1 , 50, 133) ,(34 , 2 , 95, 134) ,(34 , 3 , 60, 135) ,(34 , 4 , 71, 136) ,(35 , 1 , 52, 137) ,(35 , 2 , 93, 138) ,(35 , 3 , 67, 139) ,(35 , 4 , 61, 140) ,(36 , 1 , 40, 141) ,(36 , 2 , 92, 142) ,(36 , 3 , 69, 143) ,(36 , 4 , 71, 144) ,(37 , 1 , 16, 145) ,(37 , 2 , 95, 146) ,(37 , 3 , 75, 147) ,(37 , 4 , 65, 148) ,(38 , 1 , 45, 149) ,(38 , 2 , 66, 150) ,(38 , 3 , 56, 151) ,(38 , 4 , 50, 152) ,(39 , 1 , 45, 153) ,(39 , 2 , 95, 154) ,(39 , 3 , 67, 155) ,(39 , 4 , 62, 156) ,(40 , 1 , 25, 157) ,(40 , 2 , 95, 158) ,(40 , 3 , 79, 159) ,(40 , 4 , 83, 160) ,(41 , 1 , 2, 161) ,(41 , 2 , 90, 162) ,(41 , 3 , 85, 163) ,(41 , 4 , 70, 164) ,(42 , 1 , 38, 165) ,(42 , 2 , 85, 166) ,(42 , 3 , 56, 167) ,(42 , 4 , 61, 168) ,(43 , 1 , 72, 169) ,(43 , 2 , 82, 170) ,(43 , 3 , 53, 171) ,(43 , 4 , 56, 172) ,(44 , 1 , 2, 173) ,(44 , 2 , 67, 174) ,(44 , 3 , 69, 175) ,(44 , 4 , 61, 176) ,(45 , 1 , 58, 177) ,(45 , 2 , 80, 178) ,(45 , 3 , 74, 179) ,(45 , 4 , 90, 180) ,(46 , 1 , 20, 181) ,(46 , 2 , 45, 182) ,(46 , 3 , 57, 183) ,(46 , 4 , 52, 184) ,(47 , 1 , 60, 185) ,(47 , 2 , 20, 186) ,(47 , 3 , 28, 187) ,(47 , 4 , 22, 188) ,(48 , 1 , 47, 189) ,(48 , 2 , 45, 190) ,(48 , 3 , 52, 191) ,(48 , 4 , 42, 192) ,(49 , 1 , 48, 193) ,(49 , 2 , 75, 194) ,(49 , 3 , 60, 195) ,(49 , 4 , 54, 196) ,(50 , 1 , 46, 197) ,(50 , 2 , 95, 198) ,(50 , 3 , 57, 199) ,(50 , 4 , 74, 200) ,(51 , 1 , 62, 201) ,(51 , 2 , 92, 202) ,(51 , 3 , 55, 203) ,(51 , 4 , 72, 204) ,(52 , 1 , 40, 205) ,(52 , 2 , 95, 206) ,(52 , 3 , 66, 207) ,(52 , 4 , 78, 208) ,(53 , 1 , 40, 209) ,(53 , 2 , 95, 210) ,(53 , 3 , 68, 211) ,(53 , 4 , 78, 212) ,(54 , 1 , 53, 213) ,(54 , 2 , 57, 214) ,(54 , 3 , 40, 215) ,(54 , 4 , 63, 216) ,(55 , 1 , 30, 217) ,(55 , 2 , 72, 218) ,(55 , 3 , 56, 219) ,(55 , 4 , 62, 220) ,(56 , 1 , 0, 221) ,(56 , 2 , 92, 222) ,(56 , 3 , 79, 223) ,(56 , 4 , 81, 224) ,(57 , 1 , 45, 225) ,(57 , 2 , 65, 226) ,(57 , 3 , 32, 227) ,(57 , 4 , 51, 228) ,(58 , 1 , 0, 229) ,(58 , 2 , 92, 230) ,(58 , 3 , 37, 231) ,(58 , 4 , 54, 232) ,(59 , 1 , 12, 233) ,(59 , 2 , 92, 234) ,(59 , 3 , 52, 235) ,(59 , 4 , 56, 236) ,(60 , 1 , 48, 237) ,(60 , 2 , 62, 238) ,(60 , 3 , 48, 239) ,(60 , 4 , 45, 240) ,(61 , 1 , 18, 241) ,(61 , 2 , 85, 242) ,(61 , 3 , 38, 243) ,(61 , 4 , 47, 244) ,(62 , 1 , 6, 245) ,(62 , 2 , 97, 246) ,(62 , 3 , 55, 247) ,(62 , 4 , 75, 248) ,(63 , 1 , 13, 249) ,(63 , 2 , 22, 250) ,(63 , 3 , 47, 251) ,(63 , 4 , 48, 252) ,(64 , 1 , 13, 253) ,(64 , 2 , 67, 254) ,(64 , 3 , 52, 255) ,(64 , 4 , 62, 256) ,(65 , 1 , 17, 257) ,(65 , 2 , 97, 258) ,(65 , 3 , 45, 259) ,(65 , 4 , 46, 260) ,(66 , 1 , 7, 261) ,(66 , 2 , 97, 262) ,(66 , 3 , 37, 263) ,(66 , 4 , 48, 264) ,(67 , 1 , 58, 265) ,(67 , 2 , 92, 266) ,(67 , 3 , 35, 267) ,(67 , 4 , 55, 268) ,(68 , 1 , 48, 269) ,(68 , 2 , 95, 270) ,(68 , 3 , 42, 271) ,(68 , 4 , 56, 272) ,(69 , 1 , 58, 273) ,(69 , 2 , 97, 274) ,(69 , 3 , 47, 275) ,(69 , 4 , 57, 276) ,(70 , 1 , 22, 277) ,(70 , 2 , 85, 278) ,(70 , 3 , 51, 279) ,(70 , 4 , 70, 280) ,(71 , 1 , 5, 281) ,(71 , 2 , 0, 282) ,(71 , 3 , 38, 283) ,(71 , 4 , 40, 284) ,(72 , 1 , 58, 285) ,(72 , 2 , 5, 286) ,(72 , 3 , 37, 287) ,(72 , 4 , 50, 288) ,(73 , 1 , 8, 289) ,(73 , 2 , 0, 290) ,(73 , 3 , 42, 291) ,(73 , 4 , 68, 292) ,(74 , 1 , 5, 293) ,(74 , 2 , 15, 294) ,(74 , 3 , 27, 295) ,(74 , 4 , 35, 296) ,(75 , 1 , 41, 297) ,(75 , 2 , 17, 298) ,(75 , 3 , 25, 299) ,(75 , 4 , 20, 300) ,(76 , 1 , 12, 301) ,(76 , 2 , 12, 302) ,(76 , 3 , 30, 303) ,(76 , 4 , 47, 304) ,(77 , 1 , 72, 305) ,(77 , 2 , 40, 306) ,(77 , 3 , 27, 307) ,(77 , 4 , 17, 308) ,(78 , 1 , 2, 309) ,(78 , 2 , 97, 310) ,(78 , 3 , 78, 311) ,(78 , 4 , 60, 312) ,(79 , 1 , 1, 313) ,(79 , 2 , 100, 314) ,(79 , 3 , 50, 315) ,(79 , 4 , 52, 316) ,(80 , 1 , 1, 317) ,(80 , 2 , 100, 318) ,(80 , 3 , 56, 319) ,(80 , 4 , 50, 320) ,(81 , 1 , 0, 321) ,(81 , 2 , 100, 322) ,(81 , 3 , 41, 323) ,(81 , 4 , 26, 324) ,(82 , 1 , 3, 325) ,(82 , 2 , 0, 326) ,(82 , 3 , 50, 327) ,(82 , 4 , 37, 328) ,(83 , 1 , 2, 329) ,(83 , 2 , 0, 330) ,(83 , 3 , 53, 331) ,(83 , 4 , 37, 332) ,(84 , 1 , 42, 333) ,(84 , 2 , 5, 334) ,(84 , 3 , 36, 335) ,(84 , 4 , 52, 336) ,(85 , 1 , 5, 337) ,(85 , 2 , 60, 338) ,(85 , 3 , 28, 339) ,(85 , 4 , 38, 340) ,(86 , 1 , 12, 341) ,(86 , 2 , 50, 342) ,(86 , 3 , 70, 343) ,(86 , 4 , 66, 344) ,(87 , 1 , 2, 345) ,(87 , 2 , 97, 346) ,(87 , 3 , 65, 347) ,(87 , 4 , 25, 348) ,(88 , 1 , 27, 349) ,(88 , 2 , 97, 350) ,(88 , 3 , 68, 351) ,(88 , 4 , 37, 352) ,(89 , 1 , 6, 353) ,(89 , 2 , 62, 354) ,(89 , 3 , 31, 355) ,(89 , 4 , 37, 356) ,(90 , 1 , 3, 357) ,(90 , 2 , 65, 358) ,(90 , 3 , 43, 359) ,(90 , 4 , 28, 360) ,(91 , 1 , 3, 361) ,(91 , 2 , 1, 362) ,(91 , 3 , 25, 363) ,(91 , 4 , 21, 364) ,(92 , 1 , 10, 365) ,(92 , 2 , 3, 366) ,(92 , 3 , 68, 367) ,(92 , 4 , 63, 368) ,(93 , 1 , 0, 369) ,(93 , 2 , 3, 370) ,(93 , 3 , 68, 371) ,(93 , 4 , 36, 372) ,(94 , 1 , 25, 373) ,(94 , 2 , 10, 374) ,(94 , 3 , 48, 375) ,(94 , 4 , 23, 376) ,(95 , 1 , 6, 377) ,(95 , 2 , 6, 378) ,(95 , 3 , 53, 379) ,(95 , 4 , 26, 380) ,(96 , 1 , 3, 381) ,(96 , 2 , 10, 382) ,(96 , 3 , 46, 383) ,(96 , 4 , 21, 384) ,(97 , 1 , 2, 385) ,(97 , 2 , 0, 386) ,(97 , 3 , 70, 387) ,(97 , 4 , 50, 388) ;

insert into question (question_id, content, image_src, mbti_id)
values (1,'다음 중 어떤 음식의 맵기를 가장 좋아하시나요?', null, 1),
       (2,'엽떡 몇 단계를 선호하시나요?', null, 1),
       (3,'불닭먹을 때, 우유가 호옥~~시이 필요하시나요?', null, 1),
       (4,'삼겹살과 가장 진정한 짝꿍은?', null, 2),
       (5,'닭갈비와 같이 무엇을 드실래요?', null, 2),
       (6,'돈까스 사이드로 당신의 픽은?', null, 2),
       (7,'점심메뉴, 당신의 선택은?', null, 3),
       (8,'삼겹살 먹을 때 쌈을 싸드시나요?', null, 3),
       (9,'여기서 손이 가는 음식 가지수는 몇개인가요?', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q9.png', 3),
       (10,'둘 중에 뭐가 더 끌리시나요?', null, 4),
       (11,'닭 중의 닭은?', null, 4),
       (12,'아이스크림계의 근본은?', null, 4),
       (13,'당신은 민초파십니까?', null, 5),
       (14,'무엇이 더 끌리시나요?', null, 6),
       (15,'무엇을 더 선호하시나요?', null, 7);
              


              
insert into answer (question_id, score, content, image_src)
values (1, 0, '설렁탕', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q1_a1.svg'),
       (1, 2, '진라면 순한맛', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q1_a2.svg'),
       (1, 5, '김치찌개', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q1_a3.svg'),
       (1, 8, '마라탕', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q1_a4.svg'),
       (1, 10, '매운닭발', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q1_a5.svg'),
       (2, 0, '착한맛', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q2_a1.svg'),
       (2, 2, '초보맛', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q2_a2.svg'),
       (2, 5, '덜매운맛', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q2_a3.svg'),
       (2, 8, '오리지널', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q2_a4.svg'),
       (2, 10, '매운맛', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q2_a5.svg'),
       (3, 0, '필요하다', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q3_a1.svg'),
       (3, 7, '필요없다', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q3_a2.svg'),
       (4, 10, '볶음밥', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q4_a2.png'),
            (4, 0, '냉면', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q4_a1.png'),
       (5, 10, '볶음밥', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q5_a1.png'),
       (5, 0, '면사리', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q5_a2.png'),
       (6, 10, '미니알밥', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q6_a1.png'),
       (6, 0, '미니우동', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q6_a2.png'),
       (7, 10, '10층', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q7_a1.png'),
       (7, 0, '20층', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q7_a2.png'),
       (8, 10, 'YES', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q8_a1.png'),
       (8, 0, 'NO', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q8_a2.png'),
       (9, 1, '0~2개', null),
       (9, 3, '3~4개', null),
       (9, 5, '5~6개', null),
       (9, 7, '7~8개', null),
       (9, 10, '전부 다', null),
       (10, 8, '국밥', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q10_a1.png'),
       (10, 3, '돈까스', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q10_a2.png'),
       (11, 3, '치킨', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q11_a1.png'),
       (11, 8, '통닭', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q11_a2.png'),
       (12, 2, '베라', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q12_a1.png'),
       (12, 9, '아맛나, 비비빅, 누가바, 보석바', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q12_a2.png'),
       (13, 10, '민초파', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q13_a1.png'),
       (13, 0, '반민초파', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q13_a2.png'),
       (14, 0, '페퍼로니 피자', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q14_a1.png'),
       (14, 10, '파인애플 피자', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q14_a2.png'),
       (15, 0, '맥콜', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q15_a1.svg'),
       (15, 3, '솔의눈', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q15_a2.svg'),
       (15, 6, '실론티', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q15_a3.svg'),
       (15, 8, '데자와', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q15_a4.svg'),
       (15, 10, '사약', 'https://dx2sqphjsuclj.cloudfront.net/mbti/q15_a5.svg');
       
