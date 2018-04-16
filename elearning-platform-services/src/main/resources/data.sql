INSERT INTO instructors (id, first_name,last_name, password, email, date_created)
VALUES (1, 'Jack', 'Bauer', 'qwerty1234', 'jackbauer@gmail.com','2018-04-12');

INSERT INTO customers (id, username, password, full_name, email, address, phone_number)
VALUES (1, 'carolinedietmar', 'abcd1234', 'Caroline Dietmar', 'carolinedietmar@gmail.com', 'Str. Nufarului', '0720123456');

INSERT INTO courses (id, course_type, name, price, customer_id, instructor_id)
VALUES (1, 'IT', 'Angular 2 for beginners', '99.9', 1, 1);

