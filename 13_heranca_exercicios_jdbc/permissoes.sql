CREATE ROLE camaro LOGIN PASSWORD '111';
CREATE ROLE andressa LOGIN PASSWORD '222';
GRANT CONNECT ON DATABASE spoti_pobre TO camaro, andressa;
GRANT USAGE ON SCHEMA public TO camaro, andressa;
GRANT USAGE ON SCHEMA backup TO camaro, andressa;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO camaro;
GRANT SELECT ON ALL TABLES IN SCHEMA backup TO camaro;
GRANT SELECT, UPDATE ON ALL TABLES IN SCHEMA public TO andressa;
GRANT SELECT, UPDATE ON ALL TABLES IN SCHEMA backup TO andressa;

REVOKE UPDATE ON ALL TABLES IN SCHEMA public FROM andressa;
REVOKE UPDATE ON ALL TABLES IN SCHEMA backup FROM andressa;


CREATE ROLE jefferson LOGIN PASSWORD '111' IN ROLE camaro;
CREATE ROLE murilo LOGIN PASSWORD '222' CONNECTION LIMIT 2 IN ROLE camaro;

-- liberei a execucao para camaro - lembrando que jefferson e andressa - por padrao - tb n irao conseguir executar
GRANT EXECUTE ON FUNCTION hello_world TO camaro;

-- por padrao todos podem executar - mas aqui proibi todos
revoke execute on function hello_world from public;
-- proibido a execucao para o murilo (exclusivamente)
REVOKE EXECUTE ON FUNCTION hello_world FROM murilo;


