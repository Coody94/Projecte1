
--No Funciona 


--Afegir fotos
-- Permisos si son necesaris 
--GRANT CREATE ANY DIRECTORY TO CLUB;

--CREATE OR REPLACE DIRECTORY DIRECTORIO_IMAGENES AS 'C:/data/images';



DECLARE
    v_bfile BFILE;
    v_blob  BLOB;
BEGIN
    -- Abrir el archivo de imagen desde una ruta
    v_bfile := BFILENAME('DIRECTORIO_IMAGENES', 'user1.PNG');
    DBMS_LOB.fileopen(v_bfile, DBMS_LOB.file_readonly);

    -- Inicializar un BLOB temporal
    DBMS_LOB.createtemporary(v_blob, TRUE);

    -- Cargar el archivo de imagen en el campo BLOB
    DBMS_LOB.loadfromfile(v_blob, v_bfile, DBMS_LOB.getlength(v_bfile));
    DBMS_LOB.fileclose(v_bfile);

    -- Actualizar el campo BLOB en la tabla jugador
    UPDATE jugador
    SET foto = v_blob
    WHERE id = 2;

    -- Liberar el BLOB temporal
    DBMS_LOB.freetemporary(v_blob);
END;
/