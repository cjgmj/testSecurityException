-- Password 'admin'
INSERT INTO USERS (username, password) VALUES ('admin', '$2a$10$xYBziKsVY2AlKopF.qftjeUlsfxUJgnCXxPHiCEFOfduEdguPGmHO');
-- Password 'user'
INSERT INTO USERS (username, password) VALUES ('user', '$2a$10$qAGbd964pOUo22xxLxCGZOXtwZ1fjT9cAD4fIIH2kEOr.0aC5rIuK');

INSERT INTO ROLES (authority) VALUES ('USER');
INSERT INTO ROLES (authority) VALUES ('ADMIN');

INSERT INTO USERS_ROLES (user_id, role_id) VALUES (1, 1);
INSERT INTO USERS_ROLES (user_id, role_id) VALUES (1, 2);
INSERT INTO USERS_ROLES (user_id, role_id) VALUES (2, 1);