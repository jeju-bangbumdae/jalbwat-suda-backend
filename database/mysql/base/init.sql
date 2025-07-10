-- spmple SQL script --
CREATE DATABASE IF NOT EXISTS jalbwat-suda;
USE jalbwat-suda;

-- USER TABLE
CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      actor VARCHAR(255) NOT NULL,
                      name VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      deleted_at TIMESTAMP DEFAULT NULL
);

-- QUESTION TABLE
CREATE TABLE question (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          question VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          deleted_at TIMESTAMP DEFAULT NULL
);

-- STORE TABLE
CREATE TABLE store (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT,
                       name VARCHAR(100) NOT NULL,
                       business_num VARCHAR(100) NOT NULL,
                       address VARCHAR(100) NOT NULL,
                       category VARCHAR(100) NOT NULL,
                       latitude DOUBLE NOT NULL,
                       longitude DOUBLE NOT NULL,
                       operation_time VARCHAR(255),
                       phone VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP DEFAULT NULL,
                       CONSTRAINT fk_store_user FOREIGN KEY (user_id) REFERENCES user(id)
);

-- GUEST_BOOK TABLE
CREATE TABLE guest_book (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            content VARCHAR(255) NOT NULL,
                            question_id INT,
                            answer VARCHAR(255),
                            user_id INT,
                            store_id INT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            deleted_at TIMESTAMP DEFAULT NULL,
                            CONSTRAINT fk_guestbook_question FOREIGN KEY (question_id) REFERENCES question(id),
                            CONSTRAINT fk_guestbook_user FOREIGN KEY (user_id) REFERENCES user(id),
                            CONSTRAINT fk_guestbook_store FOREIGN KEY (store_id) REFERENCES store(id)
);

GRANT ALL PRIVILEGES ON jalbwat-suda.* TO 'myuser'@'%' IDENTIFIED BY 'mypass123';
FLUSH PRIVILEGES;

INSERT INTO user (email, password, actor, name) VALUES
                                                    ('user1@example.com', 'hashed_password_1', 'customer', '김철수'),
                                                    ('user2@example.com', 'hashed_password_2', 'owner', '이영희'),
                                                    ('user3@example.com', 'hashed_password_3', 'customer', '박지민'),
                                                    ('user4@example.com', 'hashed_password_4', 'owner', '최현우'),
                                                    ('user5@example.com', 'hashed_password_5', 'customer', '정수아');


INSERT INTO question (question) VALUES
                                    ('다음에 오신다면 누구와 함께 오고싶으신가요?'),
                                    ('이 장소에서 기억이 남는 점은?'),
                                    ('누구랑 오셨나요?'),
                                    ('오늘의 날씨는 어떠셨나요?'),
                                    ('오늘의 기분은 어떠신가요?'),
                                    ('이곳의 첫인상은 어떠셨나요?');

INSERT INTO store (user_id, name, business_num, latitude, longitude, category, operation_time, address, phone) VALUES
                                                                                                                   (2, '카페 아늑', '123-45-67890', 37.5000, 127.0300, 'cafe', '매일 09:00-22:00', '서울시 강남구 테헤란로 123', '02-1234-5678'),
                                                                                                                   (4, '맛있는 식당', '987-65-43210', 37.5700, 126.9800, 'restaurant', '평일 11:00-21:00, 주말 11:00-22:00', '서울시 종로구 삼일대로 456', '02-9876-5432'),
                                                                                                                   (2, '빵집 달콤', '111-22-33333', 35.1500, 129.1300, 'cafe', '매일 08:00-20:00', '부산시 해운대구 마린시티2로 33', '051-1111-2222'),
                                                                                                                   (4, '이탈리안 키친', '444-55-66666', 33.4900, 126.5200, 'restaurant', '매일 11:30-22:00 (브레이크 15:00-17:00)', '제주도 제주시 노형로 77', '064-3333-4444'),
                                                                                                                   (1, '바다풍경식당', '496-28-59538', 33.389571, 126.255863, 'cafe', '매일 10:00 - 22:00', '제주특별자치도 제주시 한림읍 수원리 1565', '010-1600-6574'),
                                                                                                                   (1, '달빛카페', '263-97-86839', 33.485032, 126.391482, 'giftshop', '매일 12:00 - 18:00', '제주특별자치도 제주시 애월읍 구엄리 924', '010-2652-1863'),
                                                                                                                   (1, '소담소품', '943-24-54491', 33.279583, 126.327201, 'restaurant', '월~토 09:00 - 21:00', '제주특별자치도 서귀포시 안덕면 감산리 1136', '010-9141-9219'),
                                                                                                                   (1, '한라분식', '491-79-37929', 33.552501, 126.698374, 'restaurant', '수~일 08:30 - 21:30', '제주특별자치도 제주시 조천읍 북촌리 421', '010-8225-9286'),
                                                                                                                   (1, '돌담카페', '553-17-47534', 33.472211, 126.598025, 'restaurant', '매일 10:00 - 22:00', '제주특별자치도 제주시 봉개동 3147-3', '010-1577-9538'),
                                                                                                                   (1, '조용한소품가게', '598-36-63496', 33.341989, 126.843323, 'giftshop', '매일 12:00 - 18:00', '제주특별자치도 서귀포시 표선면 하천리 451', '010-3062-2427'),
                                                                                                                   (1, '옛날국수집', '469-25-47729', 33.519861, 126.573182, 'giftshop', '매일 12:00 - 18:00', '제주특별자치도 제주시 삼양1동 1603-4', '010-3565-4404'),
                                                                                                                   (1, '노을카페', '967-50-29198', 33.483637, 126.436781, 'cafe', '매일 10:00 - 22:00', '제주특별자치도 제주시 외도1동 716-1', '010-4850-8010'),
                                                                                                                   (1, '숲속소품', '662-37-14493', 33.48672, 126.483502, 'restaurant', '화~일 11:00 - 19:00', '제주특별자치도 제주시 노형동 2340-2', '010-4866-2524'),
                                                                                                                   (1, '바람의식당', '497-21-34211', 33.479892, 126.537938, 'restaurant', '매일 12:00 - 18:00', '제주특별자치도 제주시 오라3동 2638', '010-1759-6647'),
                                                                                                                   (1, '파란하늘카페', '196-55-82095', 33.47342, 126.549005, 'cafe', '화~일 11:00 - 19:00', '제주특별자치도 제주시 아라1동 2018-5', '010-1823-6078'),
                                                                                                                   (1, '산방소품', '955-77-15131', 33.212311, 126.25321, 'giftshop', '화~일 11:00 - 19:00', '제주특별자치도 서귀포시 대정읍 동일리 913', '010-5052-2480'),
                                                                                                                   (1, '삼다식당', '530-23-84229', 33.307417, 126.239524, 'cafe', '매일 10:00 - 22:00', '제주특별자치도 제주시 한경면 저지리 1924', '010-6672-4894'),
                                                                                                                   (1, '감귤카페', '649-69-31743', 33.294814, 126.751262, 'cafe', '화~일 11:00 - 19:00', '제주특별자치도 서귀포시 남원읍 수망리 345', '010-1293-5360'),
                                                                                                                   (1, '돌하르방소품', '928-41-70478', 33.243632, 126.421785, 'restaurant', '수~일 08:30 - 21:30', '제주특별자치도 서귀포시 색달동 1149-1', '010-7734-2276'),
                                                                                                                   (1, '모슬포밥상', '985-28-12067', 33.523018, 126.575236, 'giftshop', '화~일 11:00 - 19:00', '제주특별자치도 제주시 해안동 1406-1', '010-6562-7837'),
                                                                                                                   (1, '하늘소품가게', '506-61-67491', 33.51267, 126.579903, 'cafe', '수~일 08:30 - 21:30', '제주특별자치도 제주시 도련2동 1988-3', '010-1254-3836'),
                                                                                                                   (1, '비자림카페', '430-27-14214', 33.464157, 126.581265, 'cafe', '월~토 09:00 - 21:00', '제주특별자치도 제주시 영평동 2786-2', '010-7113-8745'),
                                                                                                                   (1, '고요한식당', '241-64-41692', 33.2502644, 126.5620224, 'cafe', '매일 12:00 - 18:00', '제주특별자치도 서귀포시 남원읍 남원로 42', '010-6258-3801'),
                                                                                                                   (1, '별빛소품', '745-98-51736', 33.330122, 126.855321, 'giftshop', '월~토 09:00 - 21:00', '제주특별자치도 서귀포시 표선면 세화리 123', '010-2575-2201');

-- Bulk Insert for GUEST_BOOK TABLE
-- question_id, user_id, store_id는 각각 해당 테이블의 id를 참조합니다.
INSERT INTO guest_book (content, question_id, answer, user_id, store_id) VALUES
                                                                             ('카페 분위기가 정말 좋고 편안했어요!', 1, '네, 고객님의 편안함을 위해 노력하고 있습니다.', 1, 1),
                                                                             ('음식이 너무 맛있어서 또 올 거예요!', 2, '감사합니다! 또 방문해주세요.', 3, 2),
                                                                             ('직원분들이 친절해서 기분 좋게 식사했습니다.', 3, '친절한 서비스는 저희의 최우선 가치입니다.', 5, 2),
                                                                             ('빵이 정말 신선하고 맛있었어요!', 2, '매일 아침 신선한 빵을 만듭니다.', 1, 3),
                                                                             ('재방문 의사 100%입니다!', 4, '다음에 또 뵙겠습니다!', 3, 1),
                                                                             ('가격도 합리적이고 양도 많아서 만족했어요.', 5, '만족하셨다니 기쁩니다.', 5, 4),
                                                                             ('조용하고 작업하기 좋은 카페였어요.', 1, '네, 작업하기 좋은 환경을 제공합니다.', 1, 1),
                                                                             ('파스타가 정말 일품이었어요!', 2, '저희 셰프의 특별 레시피입니다.', 3, 4),
                                                                             ('다음에 친구들과 또 올게요!', 4, '친구분들과 함께 방문해주세요!', 1, 2),
                                                                             ('케이크가 너무 예쁘고 맛있었어요!', 2, '저희 파티시에가 직접 만듭니다.', 5, 3);





