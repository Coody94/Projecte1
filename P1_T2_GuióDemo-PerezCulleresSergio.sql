INSERT INTO temporada (any_t) VALUES (2024);

INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ("Cadet A","H",2024,4);
INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ("Cadet B","D",2024,4);
INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ("Cadet C","M",2024,4);
INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ("Juvenil A","M",2024,5);
INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ("Senior A","H",2024,6);

INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca) VALUES ("Pepe","gotera","H",TO_DATE('2000-04-10', 'YYYY-MM-DD'),"72136429B","ES21 0234 0505 7417 0646 1254",2024,"Carrer 1");
