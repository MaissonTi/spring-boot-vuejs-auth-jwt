CREATE TABLE public.users(
	id serial NOT NULL,
	name varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	password varchar(150) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
); 
 
CREATE TABLE public.permission(
	id serial NOT NULL,
	description varchar(50) NOT NULL,
	CONSTRAINT permission_pkey PRIMARY KEY (id)
); 

CREATE TABLE public.users_permission(
	id serial NOT NULL,
	id_users bigint NOT NULL,
	id_permission bigint NOT NULL,		
	CONSTRAINT users_permission_pkey PRIMARY KEY (id),
	CONSTRAINT users_permission_permission_id_foreign FOREIGN KEY (id_permission) REFERENCES public.permission(id) ON DELETE CASCADE,
	CONSTRAINT users_permission_users_id_foreign FOREIGN KEY (id_users) REFERENCES public.users(id) ON DELETE CASCADE
); 
 
 
 ---- DEFAULT VALUES
 
INSERT INTO public.users(id, name, email, password)	VALUES(1, 'Administrator', 'admin@test.com', '$2a$10$nFwyXJyc1e1ZfrExCdQILe2M3GswrPHRFCfDZLjf64mcQRy4LQzp6'); --password=123456
INSERT INTO public.users(id, name, email, password)	VALUES(2, 'Common users', 'commonusers@test.com', '$2a$10$nFwyXJyc1e1ZfrExCdQILe2M3GswrPHRFCfDZLjf64mcQRy4LQzp6'); --password=123456

INSERT INTO permission (id, description) values (1, 'ROLE_VIEW_STUDENT');
INSERT INTO permission (id, description) values (2, 'ROLE_CREATE_STUDENT');
INSERT INTO permission (id, description) values (3, 'ROLE_DELETE_STUDENT');
INSERT INTO permission (id, description) values (4, 'ROLE_UPDATE_STUDENT');

-- admin 
INSERT INTO users_permission (id_users, id_permission) values (1, 1);
INSERT INTO users_permission (id_users, id_permission) values (1, 2);
INSERT INTO users_permission (id_users, id_permission) values (1, 3);
INSERT INTO users_permission (id_users, id_permission) values (1, 4);

-- commonusers
INSERT INTO users_permission (id_users, id_permission) values (2, 1);