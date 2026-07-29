DROP DATABASE IF EXISTS spoti_pobre;

CREATE DATABASE spoti_pobre;

\c spoti_pobre;

CREATE TABLE usuario (
    id serial primary key,
    email character varying(200) unique not null,
    senha character varying(200) not null,
    nome character varying(200) not null,
    data_nascimento date
);

CREATE TABLE playlist (
    id serial primary key,
    nome text not null,
    data_hora_criacao timestamp default current_timestamp,
    publica boolean default true
);

CREATE TABLE usuario_playlist (
    usuario_id integer references usuario (id),
    playlist_id integer references playlist (id),
    dono boolean default false,
    colaborador boolean default false,
    primary key (usuario_id, playlist_id)
);

CREATE TABLE musica (
    id serial primary key,
    nome text not null,
    duracao time,
    data_hora_lancamento timestamp default current_timestamp
);

CREATE TABLE playlist_musica (
    playlist_id integer references playlist (id),
    musica_id integer references musica (id),
    primary key (playlist_id, musica_id)
);


CREATE TABLE reproducao (
    musica_id integer references musica (id),
    usuario_id integer references usuario (id),
    quando timestamp default current_timestamp,
    primary key (musica_id, usuario_id)
);  

CREATE TABLE genero (
    id serial primary key,
    nome text not null
);

CREATE TABLE album (
    id serial primary key,
    titulo text not null,
    data_lancamento date,
    genero_id integer references genero (id)
);

CREATE TABLE album_musica (
    album_id integer references album (id),
    musica_id integer references musica (id),
    primary key (album_id, musica_id)
);

CREATE TABLE artista (
    id serial primary key,
    nome text not null
);

CREATE TABLE album_artista (
    album_id integer references album (id),
    artista_id integer references artista(id),
    primary key (album_id, artista_id)
);


-- 1. GENERO
INSERT INTO genero (id, nome) VALUES
(1, 'Samba'),
(2, 'MPB'),
(3, 'Sertanejo'),
(4, 'Bossa Nova'),
(5, 'Rock Nacional'),
(6, 'Pagode'),
(7, 'Funk'),
(8, 'Forró'),
(9, 'Rap Nacional'),
(10, 'Axé'),
(11, 'Tropicalismo'),
(12, 'Choro'),
(13, 'Maracatu'),
(14, 'Baião'),
(15, 'Frevo'),
(16, 'Samba-Enredo'),
(17, 'Pop Rock Brasil'),
(18, 'Reggae Nacional'),
(19, 'Nova MPB'),
(20, 'Sertanejo Universitário');

-- 2. ARTISTA
INSERT INTO artista (id, nome) VALUES
(1, 'Caetano Veloso'),
(2, 'Gilberto Gil'),
(3, 'Elis Regina'),
(4, 'Chico Buarque'),
(5, 'Cartola'),
(6, 'Tom Jobim'),
(7, 'Milton Nascimento'),
(8, 'Tim Maia'),
(9, 'Legião Urbana'),
(10, 'Charlie Brown Jr.'),
(11, 'Racionais MC''s'),
(12, 'Marisa Monte'),
(13, 'Chitãozinho & Xororó'),
(14, 'Zeca Pagodinho'),
(15, 'Luiz Gonzaga'),
(16, 'Rita Lee'),
(17, 'Jorge Ben Jor'),
(18, 'Djavan'),
(19, 'Gal Costa'),
(20, 'Cazuza');

-- 3. ALBUM
INSERT INTO album (id, titulo, data_lancamento, genero_id) VALUES
(1, 'Clube da Esquina', '1972-03-01', 2),
(2, 'Transa', '1972-01-01', 11),
(3, 'Elis & Tom', '1974-08-01', 4),
(4, 'Cartola', '1976-01-01', 1),
(5, 'A Tábua de Esmeralda', '1974-05-15', 2),
(6, 'Sobrevivendo no Inferno', '1997-12-20', 9),
(7, 'Dois', '1986-04-20', 5),
(8, 'Preço Curto... Prazo Longo', '1999-03-06', 17),
(9, 'Racional Vol. 1', '1975-01-01', 2),
(10, 'Construção', '1971-12-01', 2),
(11, 'Evidências', '1989-12-01', 3),
(12, 'Zeca Pagodinho Ao Vivo', '1999-10-10', 6),
(13, 'O Canto da Ema', '1956-06-01', 14),
(14, 'Fruto Proibido', '1975-06-01', 5),
(15, 'Luz', '1982-08-10', 2),
(16, 'MM', '1989-01-15', 2),
(17, 'Refazenda', '1975-05-01', 2),
(18, 'Faia D''Água', '1979-04-12', 1),
(19, 'Ideologia', '1988-04-01', 17),
(20, 'Tropicalia ou Panis et Circencis', '1968-07-01', 11);

-- 4. MUSICA
INSERT INTO musica (id, nome, duracao, data_hora_lancamento) VALUES
(1, 'Tudo O Que Você Podia Ser', '00:02:56', '1972-03-01 00:00:00'),
(2, 'You Don''t Know Me', '00:03:50', '1972-01-01 00:00:00'),
(3, 'Águas de Março', '00:03:32', '1974-08-01 00:00:00'),
(4, 'O Mundo É Um Moinho', '00:03:54', '1976-01-01 00:00:00'),
(5, 'Os Alquimistas Estão Chegando os Alquimistas', '00:03:15', '1974-05-15 00:00:00'),
(6, 'Diário de um Detento', '00:07:31', '1997-12-20 00:00:00'),
(7, 'Tempo Perdido', '00:05:00', '1986-04-20 00:00:00'),
(8, 'Zica do Bagui', '00:03:05', '1999-03-06 00:00:00'),
(9, 'Imunização Racional (Que Beleza)', '00:05:10', '1975-01-01 00:00:00'),
(10, 'Construção', '00:06:24', '1971-12-01 00:00:00'),
(11, 'Evidências', '00:04:39', '1989-12-01 00:00:00'),
(12, 'Deixa a Vida Me Levar', '00:04:35', '1999-10-10 00:00:00'),
(13, 'Asa Branca', '00:03:04', '1956-06-01 00:00:00'),
(14, 'O Ovelha Negra', '00:05:07', '1975-06-01 00:00:00'),
(15, 'Sina', '00:05:33', '1982-08-10 00:00:00'),
(16, 'Bem Que Se Quis', '00:02:55', '1989-01-15 00:00:00'),
(17, 'Refazenda', '00:03:20', '1975-05-01 00:00:00'),
(18, 'Garota de Ipanema', '00:05:19', '1964-03-01 00:00:00'),
(19, 'Ideologia', '00:04:06', '1988-04-01 00:00:00'),
(20, 'Alegria, Alegria', '00:02:43', '1968-07-01 00:00:00');

-- 5. ALBUM_ARTISTA
INSERT INTO album_artista (album_id, artista_id) VALUES
(1, 7),
(2, 1),
(3, 3),
(3, 6),
(4, 5),
(5, 17),
(6, 11),
(7, 9),
(8, 10),
(9, 8),
(10, 4),
(11, 13),
(12, 14),
(13, 15),
(14, 16),
(15, 18),
(16, 12),
(17, 2),
(18, 6),
(19, 20);

-- 6. ALBUM_MUSICA
INSERT INTO album_musica (album_id, musica_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(3, 18),
(19, 19),
(20, 20);

-- 7. USUARIO
INSERT INTO usuario (id, email, senha, nome, data_nascimento) VALUES
(1, 'joao.silva@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'João Silva', '1995-03-15'),
(2, 'maria.oliveira@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Maria Oliveira', '1998-07-22'),
(3, 'carlos.eduardo@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Carlos Eduardo', '1990-11-05'),
(4, 'ana.beatriz@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Ana Beatriz', '2001-01-30'),
(5, 'lucas.mendonca@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Lucas Mendonça', '1997-09-12'),
(6, 'jessica.lima@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Jéssica Lima', '1993-05-18'),
(7, 'rodrigo.santos@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Rodrigo Santos', '1988-12-01'),
(8, 'camila.rocha@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Camila Rocha', '2002-04-10'),
(9, 'felipe.alves@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Felipe Alves', '1996-08-25'),
(10, 'larissa.ferreira@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Larissa Ferreira', '1999-02-14'),
(11, 'bruno.costa@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Bruno Costa', '1992-06-30'),
(12, 'patricia.gomes@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Patrícia Gomes', '1985-10-20'),
(13, 'gabriel.souza@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Gabriel Souza', '2000-11-11'),
(14, 'amanda.martins@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Amanda Martins', '1994-03-08'),
(15, 'rafael.barbosa@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Rafael Barbosa', '1991-07-04'),
(16, 'fernanda.lima@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Fernanda Lima', '1997-12-24'),
(17, 'diego.ribeiro@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Diego Ribeiro', '1989-01-19'),
(18, 'juliana.carvalho@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Juliana Carvalho', '2003-05-02'),
(19, 'marcelo.dias@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Marcelo Dias', '1996-10-15'),
(20, 'vanessa.nunes@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjO', 'Vanessa Nunes', '1998-08-09');

-- 8. PLAYLIST
INSERT INTO playlist (id, nome, data_hora_criacao, publica) VALUES
(1, 'MPB Essencial', '2023-01-10 14:00:00', true),
(2, 'Clássicos do Samba', '2023-02-15 18:30:00', true),
(3, 'Rock Nacional Anos 80 e 90', '2023-03-01 08:00:00', true),
(4, 'Bossa Nova & Café', '2023-03-12 21:15:00', true),
(5, 'Rap e Poesia do Brasil', '2023-04-05 10:45:00', true),
(6, 'Churrasco com Sertanejo', '2023-04-20 16:20:00', true),
(7, 'Pagode Retrô', '2023-05-01 23:00:00', true),
(8, 'Grandes Vozes Femininas da MPB', '2023-05-18 09:30:00', true),
(9, 'Viagem no Tempo: Anos 70', '2023-06-02 11:10:00', true),
(10, 'Samba de Raiz', '2023-06-25 20:00:00', true),
(11, 'Forró Pé de Serra', '2023-07-04 07:00:00', true),
(12, 'Nostalgia Brasil', '2023-07-19 13:40:00', true),
(13, 'Clube da Esquina & Amigos', '2023-08-08 17:50:00', true),
(14, 'Músicas para Trabalhar', '2023-08-30 22:10:00', false),
(15, 'Brasil Secreto', '2023-09-12 12:00:00', false),
(16, 'Para Cantar na Estrada', '2023-10-01 15:30:00', true),
(17, 'Baladas Românticas Nacionais', '2023-10-22 06:30:00', true),
(18, 'Música Popular da Bahia', '2023-11-05 19:00:00', true),
(19, 'Soul & Funk Raiz BR', '2023-11-28 01:20:00', true),
(20, 'Festival MPB Ao Vivo', '2023-12-15 14:15:00', true);

-- 9. USUARIO_PLAYLIST
INSERT INTO usuario_playlist (usuario_id, playlist_id, dono, colaborador) VALUES
(1, 1, true, false),
(2, 2, true, false),
(3, 3, true, false),
(4, 4, true, false),
(5, 5, true, false),
(6, 6, true, false),
(7, 7, true, false),
(8, 8, true, false),
(9, 9, true, false),
(10, 10, true, false),
(11, 11, true, false),
(12, 12, true, false),
(13, 13, true, false),
(14, 14, true, false),
(15, 15, true, false),
(1, 2, false, true),
(2, 1, false, true),
(3, 7, false, true),
(4, 8, false, true),
(5, 3, false, true);

-- 10. PLAYLIST_MUSICA
INSERT INTO playlist_musica (playlist_id, musica_id) VALUES
(1, 3),
(1, 10),
(1, 15),
(2, 4),
(2, 12),
(3, 7),
(3, 8),
(3, 14),
(3, 19),
(4, 3),
(4, 18),
(5, 6),
(6, 11),
(7, 12),
(8, 3),
(8, 14),
(8, 16),
(9, 1),
(9, 2),
(9, 5);

-- 11. REPRODUCAO
INSERT INTO reproducao (musica_id, usuario_id, quando) VALUES
(1, 1, '2026-07-01 10:00:00'),
(3, 1, '2026-07-01 10:05:00'),
(11, 2, '2026-07-02 18:30:00'),
(7, 3, '2026-07-03 14:15:00'),
(18, 4, '2026-07-03 20:00:00'),
(6, 5, '2026-07-04 09:45:00'),
(12, 6, '2026-07-05 22:10:00'),
(16, 7, '2026-07-06 11:20:00'),
(4, 8, '2026-07-07 16:05:00'),
(8, 9, '2026-07-08 19:50:00'),
(10, 10, '2026-07-09 23:30:00'),
(13, 11, '2026-07-10 08:00:00'),
(14, 12, '2026-07-11 17:15:00'),
(15, 13, '2026-07-12 13:40:00'),
(19, 14, '2026-07-13 15:00:00'),
(20, 15, '2026-07-14 21:00:00'),
(9, 16, '2026-07-15 10:30:00'),
(17, 17, '2026-07-16 12:25:00'),
(5, 18, '2026-07-17 18:45:00'),
(2, 19, '2026-07-18 01:00:00');


select usuario.id, usuario.email, usuario.nome, musica.nome, reproducao.quando FROM usuario INNER JOIN reproducao ON usuario.id = reproducao.usuario_id INNER JOIN musica ON musica.id = reproducao.musica_id where usuario.id = 1;

