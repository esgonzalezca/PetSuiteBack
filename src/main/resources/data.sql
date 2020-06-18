insert into info_user (user, e_mail, name, password, phone, role) values ('nicolas', 'ncontrerasn@unal.edu.co', 'Nicolás Contreras', '123','3125896475', 'ROLE_CLIENT');
insert into info_user (user, e_mail, name, password, phone, role) values ('sergio','esgonzalezca@unal.edu.co', 'Sergio GOnzález', '123', '3215732023', 'ROLE_CLIENT');
insert into info_user (user, e_mail, name, password, phone, role) values ('juan','jlozanoc@unal.edu.co', 'Juan Lozano', '123', '3140122037', 'ROLE_CLIENT');
insert into info_user (user, e_mail, name, password, phone, role) values ('hubert', 'htovars@unal.edu.co', 'Hubert Tovar', '123', '3217785643', 'ROLE_CLIENT');
insert into info_user (user, e_mail, name, password, phone, role) values ('diego', 'arvelasquezva@unal.edu.co', 'Diego Velásquez', '123', '3165989635', 'ROLE_CLIENT');
insert into info_user (user, e_mail, name, password, phone, role) values ('jose', 'jmcarvajalj@unal.edu.co', 'José Carvajal', '123', '3204545856', 'ROLE_CLIENT');

insert into info_user (user, e_mail, name, password, phone, role) values ('joaquin','jchaves@gmail.com', 'Joaquín Chavez', '456', '3190122837', 'ROLE_DOGWALKER');
insert into info_user (user, e_mail, name, password, phone, role) values ('erika','ebarcelo@gmail.com', 'Erika Barcelo', '456', '3194899658', 'ROLE_DOGWALKER');
insert into info_user (user, e_mail, name, password, phone, role) values ('bibiana','bcarreno@gmail.com', 'Bibiana Carreño', '456', '3197856989', 'ROLE_DOGWALKER');
insert into info_user (user, e_mail, name, password, phone, role) values ('valentina','vsaavedra@gmail.com', 'Valentina Saavedra', '456', '3217857489', 'ROLE_DOGWALKER');
insert into info_user (user, e_mail, name, password, phone, role) values ('andres','abonilla@gmail.com', 'Andrés Bonilla', '456', '3197875200', 'ROLE_DOGWALKER');
insert into info_user (user, e_mail, name, password, phone, role) values ('andrea','asanchez@gmail.com', 'Andrea Sánchez', '456', '3192304889', 'ROLE_DOGWALKER');
insert into info_user (user, e_mail, name, password, phone, role) values ('juana','jcarreno@gmail.com', 'Juana Carreño', '456', '3197856989', 'ROLE_DOGWALKER');
insert into info_user (user, e_mail, name, password, phone, role) values ('samuel','scastellanos@gmail.com', 'Samuel Castellanos', '456', '3218966989', 'ROLE_DOGWALKER');

insert into info_user (user, e_mail, name, password, phone, role) values ('mascohouse', 'mascohouse@gmail.com', 'Masco House', '789', '3169987854', 'ROLE_DOGDAYCARE');
insert into info_user (user, e_mail, name, password, phone, role) values ('petfamily', 'mascohouse@gmail.com', 'Pet Family Hotel', '789', '3102653269', 'ROLE_DOGDAYCARE');
insert into info_user (user, e_mail, name, password, phone, role) values ('puppyland', 'puppyland@gmail.com', 'Puppy Land Colombia', '789', '3142654568', 'ROLE_DOGDAYCARE');
insert into info_user (user, e_mail, name, password, phone, role) values ('hogarritas', 'hogarritas@gmail.com', 'Hogarritas', '789', '317650068', 'ROLE_DOGDAYCARE');
insert into info_user (user, e_mail, name, password, phone, role) values ('tierradeperros', 'tierradeperros@gmail.com', 'Tierra de Perros', '789', '319653377', 'ROLE_DOGDAYCARE');


insert into client (client_address, user) values ('Carrera 7 # 84 - 72', 'nicolas');
insert into client (client_address, user) values ('Carrera 44 # 22 B-09', 'sergio');
insert into client (client_address, user) values ('Avenida La Esperanza # 43A - 90', 'juan');
insert into client (client_address, user) values ('Calle 119 # 6 - 06', 'hubert');
insert into client (client_address, user) values ('Carrera 6 # 119 - 24', 'diego');
insert into client (client_address, user) values ('Carrera 6a # 117 - 02', 'jose');

insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('nicolas', 2, 45, 'Otelo', 'Es tranquilo', 'Beagle', 14);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('nicolas', 3, 15, 'Luna', 'Ladra mucho', 'Criollo', 15);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('nicolas', 4, 45, 'Lucas', 'Siempre tiene hambre', 'Puddle', 9);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('nicolas', 5, 15, 'Lupe', 'Tiene epilepsia', 'Dálmata', 8);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('sergio', 6, 45, 'Miel', 'Tiene otitis', 'Shnautzer', 7);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('sergio', 7, 15, 'Banana', 'Tiene dermatitis', 'Bull dog', 5);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('sergio', 8, 45, 'Rayo', 'Debe comer cada 3 horas', 'Pug', 35);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('juan', 9, 15, 'Pepa', 'Le tiene miedo a los camiones', 'Labrador', 39);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('hubert', 10, 45, 'Pepe', 'Se orina cuando se emociona', 'Bull terrier', 42);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('hubert', 4, 15, 'Tato', 'Es propenso a escaparse', 'Husky', 19);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('diego', 5, 45, 'Diablo', 'Es agresivo', 'Pit bull', 23);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('diego', 6, 15, 'Copito', 'Es muy fuerte', 'Chigüagüa', 25);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('jose', 7, 15, 'Tata', 'Tiende a deprimirse', 'Akita', 19);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('jose', 8, 45, 'Max', 'Le gusta jugar ', 'Pastor alemán', 32);
insert into dog (user, dog_age, dog_height, dog_name, dog_notes, dog_race, dog_weight) values ('jose', 9, 15, 'Violeta', 'Le gusta comerse cosas del suelo', 'Criollo', 14);

insert into dog_walker (dog_walker_score, user) values (4, 'joaquin');
insert into dog_walker (dog_walker_score, user) values (4, 'erika');
insert into dog_walker (dog_walker_score, user) values (4, 'bibiana');
insert into dog_walker (dog_walker_score, user) values (4, 'valentina');
insert into dog_walker (dog_walker_score, user) values (4, 'andres');
insert into dog_walker (dog_walker_score, user) values (4, 'andrea');
insert into dog_walker (dog_walker_score, user) values (4, 'juana');
insert into dog_walker (dog_walker_score, user) values (4, 'samuel');

insert into dog_daycare value ('Carrera 32a # 25b - 75', 20000, 4, 10000, true, 'mascohouse');
insert into dog_daycare value ('Avenida carrera 50 # 56b - 96', 13000, 4,1000, false, 'petfamily');
insert into dog_daycare value ('Calle 61b # 4b - 10 Este', 19000, 4, 8000, true, 'puppyland');
insert into dog_daycare value ('Carrera 71d # 75a - 1', 18000, 4, 6000, false, 'hogarritas');
insert into dog_daycare value ('Vereda El Líbano, La Calera', 32000, 4, 50000, true, 'tierradeperros');

insert into dog_daycare_service value(null,'Baño con shampoo de jojoba y semillas de chía', 'Baño', '13000','mascohouse');
insert into dog_daycare_service value(null,'Masaje para relajamiento y distención muscular. Dura media hora', 'Masaje', '10000','mascohouse');
insert into dog_daycare_service value(null,'En pastilla o gel', 'Antipulgas', '14500','mascohouse');
insert into dog_daycare_service value(null,'En pastillas', 'Desparasitante', '19500','mascohouse');
insert into dog_daycare_service value(null,'Corte y limado', 'Corte de uñas', '8500','mascohouse');



insert into dog_daycare_service value(null,'En gel', 'Antipulgas', '12500','puppyland');
insert into dog_daycare_service value(null,'En pastillas', 'Desparasitante', '19500','puppyland');
insert into dog_daycare_service value(null,'Corte y limado', 'Corte de uñas', '6000','puppyland');
insert into dog_daycare_service value(null,'Corte adaptado a la raza de la mascota y a los gustos del deuño', 'Corte de pelo', '15000','puppyland');

insert into dog_daycare_service value(null,'Acompañado de nuestros mejores guías, incluye refrigerio salvaje para la mascota', 'Paseo en el bosque', '8500','hogarritas');
insert into dog_daycare_service value(null,'Aprende las 10 mejores poses para competir en la pasarela', 'Entrenamiento pasarela', '50000','hogarritas');

insert into dog_daycare_service value(null,'Masaje para relajamiento de frío y calor, con esencia de kumquat', 'Masaje', '15000','tierradeperros');
insert into dog_daycare_service value(null,'Para perros que requieran mucho trabajo físico. Se usan chalecos dependiendo del peso de la mascota', 'Entrenamiento con peso', '13000','tierradeperros');
insert into dog_daycare_service value(null,'Aprende a pararse y caminar en 2 patas, dar la mano, y recoger el periódico', 'Entrenamiento de trucos', '32000','tierradeperros');
insert into dog_daycare_service value(null,'Aprende a detectar anomalías graves en el pulso cardíaco del acompañante y activa la señal de alerta en su collar', 'Entrenamiento de primeros auxilios', '25000','tierradeperros');



insert into walk_petition (dog_id, price, user, walk_petition_address, walk_petition_date_time, walk_petition_duration, walk_petition_notes, walk_petition_walker_user) values (1, null,'nicolas', 'Carrera 32a # 25b - 75', '2020-06-15T08:00:00.000', 60, 'Recoger en la portería', 'joaquin');
insert into walk_petition (dog_id, price, user, walk_petition_address, walk_petition_date_time, walk_petition_duration, walk_petition_notes, walk_petition_walker_user) values (2, null,'nicolas', 'Carrera 32a # 25b - 75', '2020-06-15T09:00:00.000', 90, 'Llamar a la casa 45', 'erika');
insert into walk_petition (dog_id, price, user, walk_petition_address, walk_petition_date_time, walk_petition_duration, walk_petition_notes, walk_petition_walker_user) values (5, null,'sergio', 'Avenida carrera 50 # 56b - 96', '2020-06-16T10:00:00.000', 120, 'Llamar al apartmaneto 301', 'bibiana');