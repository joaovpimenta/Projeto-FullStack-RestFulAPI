INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '123456');

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');
INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot Rest API', 'Back-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) 
VALUES('Dúvida', 'Erro ao criar projeto', '2012-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) 
VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) 
VALUES('Como criar um endpoint com uma lista de tópicos', 'é simples assim', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 3);

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) 
VALUES('Como criar um service', 'Não sei criar um service', '2022-06-05 20:00:00', 'NAO_RESPONDIDO', 1, 3);



