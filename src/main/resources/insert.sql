
insert into t_grupo (id_grupo,descricao,nome) values (1,'Administrador','ADMIN');

insert into t_permissao (id_permissao, nome) values (1, 'ALTERAR_GRUPO');
insert into t_permissao (id_permissao, nome) values (2, 'VISUALIZAR_GRUPO');
insert into t_permissao (id_permissao, nome) values (3, 'ALTERAR_USUARIO');
insert into t_permissao (id_permissao, nome) values (4, 'VISUALIZAR_USUARIO');

insert into t_grupo_permissao (id_grupo, id_permissao) values (1, 1);
insert into t_grupo_permissao (id_grupo, id_permissao) values (1, 2);
insert into t_grupo_permissao (id_grupo, id_permissao) values (1, 3);
insert into t_grupo_permissao (id_grupo, id_permissao) values (1, 4);

--senha admin
insert into t_usuario (id_usuario,nome,login,email,data_Cadastro,senha) values (1,'Administrador', 'admin', 'admin@gmail.com','2017-03-08','$2a$10$ILt0NMHZyYragqiCvhOfl.KuFKGRjqa8JscSbRtJ/0K4tkFodjPYG'); 

insert into t_usuario_grupo (id_usuario, id_grupo) values (1, 1);
