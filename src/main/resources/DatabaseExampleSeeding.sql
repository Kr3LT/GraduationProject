-- Seeding role is needed for running. Everything else is for examples
insert into roles (id, name) values
(1,'ROLE_ADMIN'),
(2,'ROLE_PROVIDER'),
(3,'ROLE_CUSTOMER'),
(4,'ROLE_CUSTOMER_CORPORATE'),
(5,'ROLE_CUSTOMER_RETAIL');
-- PASSWORD FOR ALL SEEDING USER IS qwerty123

INSERT INTO users (id, username, password)
VALUES (1, 'admin', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);

INSERT INTO users (id, username, password)
VALUES (2, 'provider', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO provider (id_provider)
VALUES (2);
INSERT INTO users_roles (user_id, role_id)
VALUES (2, 2);

INSERT INTO users (id, username, password)
VALUES (3, 'customer_r', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO customer (id_customer)
VALUES (3);
INSERT INTO retail_customers (id_customer)
VALUES (3);
INSERT INTO users_roles (user_id, role_id)
VALUES (3, 3);
INSERT INTO users_roles (user_id, role_id)
VALUES (3, 5);

INSERT INTO users (id, username, password)
VALUES (4, 'customer_c', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO customer (id_customer)
VALUES (4);
INSERT INTO corporate_customers (id_customer, vat_number, company_name)
VALUES (4, '123456789', 'Company name');
INSERT INTO users_roles (user_id, role_id)
VALUES (4, 3);
INSERT INTO users_roles (user_id, role_id)
VALUES (4, 4);

INSERT INTO works (id, name, duration, price, editable, target, description)
VALUES (1, 'English lesson', 60, 100.00, true, 'retail',
        'This is english lesson with duration 60 minutes and price 100000 VND');

INSERT INTO works_providers
VALUES (2, 1);
INSERT INTO working_plans
VALUES (2,
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeriodsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeriodsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeriodsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeriodsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeriodsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeriodsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeriodsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}');
