INSERT INTO temporada (any_t) VALUES (2024);
INSERT INTO temporada (any_t) VALUES (2023);


INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ('Cadet A','H',2024,4);
INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ('Cadet B','D',2024,4);
INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ('Cadet C','M',2024,4);
INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ('Juvenil A','M',2024,5);
INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ('Senior A','H',2024,6);
INSERT INTO equip (nom,tipus,temporada,categoria) VALUES ('Cadet A','H',2023,4);

INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Jordi','Lopez','H',TO_DATE('2011-04-10', 'YYYY-MM-DD'),'72136429B','ES21 0234 0505 7417 0646 1254',2025,'Carrer 1','08700','Igualada');
INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Julian','Llopis','H',TO_DATE('2010-01-12', 'YYYY-MM-DD'),'68583679W','ES08 9000 2886 6762 6127 3727',2025,'Carrer 2','08700','Igualada');
INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Orlando','Benavente','H',TO_DATE('2010-05-10', 'YYYY-MM-DD'),'48892039L','ES22 0118 9271 5136 3844 9444',2025,'Carrer 3','08700','Igualada');

INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Salvadora','Machado','D',TO_DATE('2011-05-13', 'YYYY-MM-DD'),'82721284J','ES62 3123 1159 9185 4267 6139',2025,'Carrer 4','08700','Igualada');
INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Filomena','Melian','D',TO_DATE('2010-03-01', 'YYYY-MM-DD'),'82022044L','ES65 0043 0232 3238 2832 5764',2025,'Carrer 5','08700','Igualada');
INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Marina','Valiente','D',TO_DATE('2010-01-21', 'YYYY-MM-DD'),'23771518Y','ES76 2031 3029 1171 4238 5776',2025,'Carrer 6','08700','Igualada');

INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (1,1,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (1,2,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (1,3,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (2,4,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (2,5,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (2,6,'T');

INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (3,1,'C');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (3,2,'C');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (3,3,'C');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (3,4,'C');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (3,5,'C');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (3,6,'C');

INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Cristobal','Cabeza','H',TO_DATE('2008-04-10', 'YYYY-MM-DD'),'19556985Q','ES48 3108 6623 0435 7246 8951',2025,'Carrer 7','08700','Igualada');
INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Saray','Pedraza','D',TO_DATE('2008-03-12', 'YYYY-MM-DD'),'64947204B','ES37 2055 8368 0100 5931 6464',2025,'Carrer 8','08700','Igualada');
INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Abdelaziz','Mercado','H',TO_DATE('2008-05-12', 'YYYY-MM-DD'),'23052739T','ES66 3023 6767 5687 4765 8492',2025,'Carrer 9','08700','Igualada');

INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (4,7,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (4,8,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (4,9,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (4,3,'C');

INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Antonio','Muriel','H',TO_DATE('2005-04-10', 'YYYY-MM-DD'),'06391888G','ES39 0233 0402 9856 7350 3648',2025,'Carrer 10','08700','Igualada');
INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Cayetano','Pacheco','H',TO_DATE('2005-01-12', 'YYYY-MM-DD'),'17810176B','ES31 2071 4728 6609 1920 6074',2025,'Carrer 11','08700','Igualada');
INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES ('Unai','Alves','H',TO_DATE('2005-05-10', 'YYYY-MM-DD'),'88479307D','ES95 2053 0764 0126 2156 4587',2025,'Carrer 12','08700','Igualada');

INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (5,10,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (5,11,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (5,12,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (5,7,'C');




INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (6,1,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (6,2,'T');
INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (6,3,'T');


BEGIN
    -- Eliminar un equip que tingui jugadors
    BEGIN
        DELETE FROM nombre_tabla WHERE condición;
        DBMS_OUTPUT.PUT_LINE('Eliminat');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END;

    -- Un jugador sigui titular de 2 equips
    BEGIN
        INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) 
        VALUES (5,1,'T');
        DBMS_OUTPUT.PUT_LINE('2 titulars.');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END;

    --Afegir un jugador en equip de categoria inferior a la que correspon per edat

    BEGIN
        INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) 
        VALUES (1,11,'C');
        DBMS_OUTPUT.PUT_LINE('Categoria inferior.');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END;

    --Afegir un jugador en equip incompatible per sexe

    BEGIN
        INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) 
        VALUES (2,2,'C');
        DBMS_OUTPUT.PUT_LINE('Sexe incorrece.');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END;

END;

