INSERT INTO INSTRUCTORS (ID, FIRSTNAME, LASTNAME, PASSWORD, EMAIL, DATE_CREATED)
VALUES (1, 'Jack', 'Bauer', 'qwerty1234', 'jackbauer@gmail.com', '2018-04-12');

INSERT INTO INSTRUCTORS (ID, FIRSTNAME, LASTNAME, PASSWORD, EMAIL, DATE_CREATED)
VALUES (2, 'Bogdan', 'George', 'test1234', 'bg@gmail.com', '2018-05-17');

INSERT INTO CUSTOMERS (ID, FULLNAME, USERNAME, PASSWORD, EMAIL, ADDRESS, PHONE_NUMBER)
VALUES (2, 'Caroline Dietmar', 'abcd1234', 'test', 'carolinedietmar@gmail.com', 'Str. Nufarului', '0720123456');

INSERT INTO CUSTOMERS (ID, FULLNAME, USERNAME, PASSWORD, EMAIL, ADDRESS, PHONE_NUMBER)
VALUES (1, 'Sam Patrick', 'sampatrick', 'testpassword', 'sampatrick@gmail.com', 'Str. Nufarului', '0720123456');

INSERT INTO COURSES (ID, NAME, COURSE_TYPE, PRICE, INSTRUCTOR_ID)
VALUES (1, 'IT', 'Angular 2 for beginners', '99.9', '1');

INSERT INTO COURSES (ID, NAME, COURSE_TYPE, PRICE, INSTRUCTOR_ID)
VALUES (2, 'IT', 'PHP', '10', '2');

INSERT INTO COURSES_CUSTOMER (COURSES_ID, CUSTOMER_ID)
VALUES ('1', '1');

INSERT INTO COURSES_CUSTOMER (COURSES_ID, CUSTOMER_ID)
VALUES ('2', '2');
