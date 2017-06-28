USE master
GO


IF NOT EXISTS(SELECT * FROM sys.databases
			  WHERE  name = 'projeto_clinica')
CREATE DATABASE projeto_clinica
GO

USE projeto_clinica
GO

CREATE TABLE forma_de_pagamento 
( 
    id_forma_pagamento			INT					PRIMARY KEY		IDENTITY, 
    descricao					VARCHAR(255)		NOT NULL, 
    ativo						BIT 				NOT NULL
) 
GO

CREATE TABLE receitas 
( 
    id_receita					INT					PRIMARY KEY		IDENTITY, 
    id_consulta					INT 				NOT NULL, 
    olho_esquerdo				INT 				NOT NULL, 
    olho_direito				INT 				NOT NULL,
	ativo						BIT					NOT NULL
) 
GO

CREATE TABLE consultas 
( 
	id_consulta					INT					PRIMARY KEY		IDENTITY, 
	id_cliente					INT 				NOT NULL, 
	id_medico					INT 				NOT NULL, 
	data						DATE 				NOT NULL, 
	horario						TIME 				NOT NULL, 
	valor						DECIMAL(9, 2)		NOT NULL, 
	[status]					CHAR, 
	ativo						BIT 				NOT NULL
) 
GO

CREATE TABLE enderecos 
( 
    id_endereco					INT					PRIMARY KEY		IDENTITY, 
    cep							CHAR(255), 
    uf							CHAR(2), 
    cidade						VARCHAR(255), 
    bairro						VARCHAR(255), 
    logradouro					VARCHAR(255), 
    complemento					VARCHAR(255), 
    numero						VARCHAR(255), 
    ativo						BIT 				NOT NULL
) 
GO

CREATE TABLE logins 
( 
    id_login					INT					PRIMARY KEY		IDENTITY, 
    id_funcionario				INT 				NOT NULL, 
    usuario						VARCHAR(64) 		NOT NULL, 
    senha						VARCHAR(64) 		NOT NULL, 
    ativo						BIT 				NOT NULL
) 
GO

CREATE TABLE pagamentos 
( 
    id_pagamento				INT					PRIMARY KEY		IDENTITY, 
    id_forma_pagamento			INT 				NOT NULL, 
    id_consulta					INT 				NOT NULL, 
    data						DATE 				NOT NULL, 
    valor						DECIMAL(9, 2) 		NOT NULL,
    qtd_parcela					TINYINT 			NOT NULL, 
    ativo						INT 				NOT NULL, 
    FOREIGN KEY(id_forma_pagamento)					REFERENCES forma_de_pagamento	(id_forma_pagamento), 
    FOREIGN KEY(id_consulta)						REFERENCES consultas			(id_consulta) 
) 
GO

CREATE TABLE diagnosticos 
( 
    id_diagnostico				INT					PRIMARY KEY		IDENTITY, 
    id_categoria				INT 				NOT NULL, 
    esferico					DECIMAL(4, 2), 
    cilindro					DECIMAL(4, 2), 
    adicao						DECIMAL(4, 2), 
    eixo						DECIMAL(4, 2), 
    ativo						BIT 				NOT NULL
) 
GO

CREATE TABLE categorias 
( 
    id_categoria				INT					PRIMARY KEY		IDENTITY, 
    descricao					VARCHAR(255) 		NOT NULL, 
    ativo						BIT 
) 
GO

CREATE TABLE cargos 
( 
    id_cargo					INT					PRIMARY KEY		IDENTITY, 
    descricao					VARCHAR(255)		NOT NULL,
    ativo						BIT					NOT NULL
) 
GO

CREATE TABLE funcionarios 
( 
    id_funcionario				INT					PRIMARY KEY		IDENTITY, 
    id_endereco					INT, 
    id_cargo					INT 				NOT NULL, 
    nome						VARCHAR(255) 		NOT NULL, 
    cpf							CHAR(11), 
    genero						CHAR 				NOT NULL, 
    data_de_nascimento			DATE, 
    celular						VARCHAR(255), 
    telefone					VARCHAR(255), 
    email						VARCHAR(255), 
    salario						DECIMAL(9, 2), 
    data_de_admissao			DATE, 
    ativo						BIT 				NOT NULL, 
    FOREIGN KEY(id_endereco)	REFERENCES enderecos (id_endereco), 
    FOREIGN KEY(id_cargo)		REFERENCES cargos	 (id_cargo) 
) 
GO

CREATE TABLE clientes 
( 
    id_cliente					INT					PRIMARY KEY		IDENTITY, 
    id_endereco					INT, 
    nome						VARCHAR(255) 		NOT NULL, 
    cpf							CHAR(11), 
    genero						CHAR 				NOT NULL, 
    data_de_nascimento			DATE, 
    celular						VARCHAR(255), 
    telefone					VARCHAR(255), 
    email						VARCHAR(255), 
    ativo						BIT 				NOT NULL, 
    FOREIGN KEY(id_endereco)	REFERENCES enderecos (id_endereco) 
) 
GO

ALTER TABLE receitas 
  ADD FOREIGN KEY(id_consulta)	  REFERENCES consultas (id_consulta) 
GO

ALTER TABLE receitas 
  ADD FOREIGN KEY(olho_esquerdo)  REFERENCES diagnosticos (id_diagnostico) 
GO

ALTER TABLE receitas 
  ADD FOREIGN KEY(olho_direito)	  REFERENCES diagnosticos (id_diagnostico) 
GO

ALTER TABLE consultas 
  ADD FOREIGN KEY(id_cliente)	  REFERENCES clientes (id_cliente) 
GO

ALTER TABLE consultas 
  ADD FOREIGN KEY(id_medico)	  REFERENCES funcionarios (id_funcionario) 
GO

ALTER TABLE logins 
  ADD FOREIGN KEY(id_funcionario) REFERENCES funcionarios (id_funcionario) 
GO

ALTER TABLE diagnosticos 
  ADD FOREIGN KEY(id_categoria)	  REFERENCES categorias (id_categoria) 
GO


--- *** INSERTS ***

INSERT INTO forma_de_pagamento
	(descricao, ativo)
VALUES
	('DINHEIRO', 1),
	('CHEQUE', 1),
	('CARTAO DE DEBITO', 1),
	('CARTAO DE CREDITO', 1),
	('DEPOSITO', 1),
	('BOLETO', 1)
GO


INSERT INTO enderecos 
	(cep, uf, cidade, bairro, logradouro, complemento, numero, ativo)
VALUES
	('20510-041','RJ','RIO DE JANEIRO','ANDARAI','PONTES CORRIA ','','112', 1),
	('05734-150','SP','SAO PAULO','VILA ANDRADE','NELSON GAMA DE OLIVEIRA ','APT 41B','550', 1),
	('01230-000','SP','SÃO PAULO','SANTA CECÍLIA','RUA DOUTOR ALBUQUERQUE LINS','LADO PAR','640', 1),
	('05641-030','SP','SÃO PAULO','VILA SUZANA','RUA DOMINGOS LOPES DA SILVA','','800', 1),
	('37410-000','MG','TRÊS CORAÇÕES','CENTRO','AV. PRESIDENTE DUTRA','','155', 1),
	('08676-050','SP','SUZANO','VILA FIGUEIRA','RUA ALFREDO BATISTA PIZZOLATO','','115', 1), 
	('03517-000','SP','SÃO PAULO','VILA MATILDE','RUA ANTONIETA DE MORAIS','','530', 1),
	('05302-030','SP','SÃO PAULO','VILA LEOPOLDINA','RUA NANUQUE','BB1 APTO 16','115', 1),
	('74843-440','GO','GOIÂNIA','JARDIM ATLÂNTICO','AVENIDA LEBLON','APTO 2204','01', 1),
	('69040-460','AM','MANAUS','DOM PEDRO I','RUA DIOGO DE M. FURTADO','','', 1)
GO


INSERT INTO cargos 
	(descricao, ativo)
VALUES
	('OFTALMOLOGISTA', 1),
	('RECEPCIONISTA', 1),
	('CAMAREIRA', 1),
	('GERENTE', 1),
	('ADMINISTRAÇÃO', 1),
	('AGENTE DE MANUTENÇÃO', 1),
	('GOVERNANTA', 1),
	('SERVIÇOS GERAIS', 1),
	('PORTEIRO', 1),
	('GARÇOM', 1)
GO



INSERT INTO clientes
	(id_endereco, nome, cpf, genero, data_de_nascimento, celular, telefone, email, ativo)
VALUES
	(1, 'MAURICIO VIRGINIO PIRES', '','M','10/10/1994','99999 2564','3065 5043','VIRGINIOPIRES@IG.COM.BR', 1),
	(2, 'CARLOS EDUARDO SOMBRA HOLANDA','','M','10/02/1990','996 19 0335','9662 6113','LARISSAANDRADES@HOTMAIL.COM', 1),
	(3, 'MILLER MARTINEZ DINIZ','','M','20/03/1989','99603 4280','000000000','MILLERDIIZ@GMAIL.COM', 1),
	(4, 'DANUZA VARELLA','','F','14/10/1990','99507 4844','26112465','FVARELLA@GLOBO.COM', 1),
	(5, 'JOSE DE FATIMA SILVA','','M','15/10/1990','000000000','000000000','ANINHA_ESCALA@HOTMAIL.COM', 1)
GO


INSERT INTO funcionarios
	(id_endereco, id_cargo, nome, genero, data_de_nascimento, celular, telefone, email, salario, data_de_admissao,	ativo)
VALUES
	(6,  1,'WALDEMAR FERNANDES JR','M','10/10/1990','972834885','98344 4132','WALDOFERNANDES@IG.COM.BR', 1500,'30/06/2017', 1),
	(7,  1,'HENRIQUE ALMEIDA','M','10/10/1990','997243528','36642120','HENRISANALISE@GMAIL.COM','1500','30/06/2017', 1),
	(9,  1,'ALINE SANTOS BERARDINELLI','F','10/10/1990','98116 4785 ','9 8135 1111','SAN.ALINE@GMAIL.COM','1500','30/06/2017', 1),
	(8,  2,'JOSE LECIO VIEIRA DA SILVA','M','10/10/1990','96669 - 6140','4513 5827','LECIOVIEIRA@HOTMAIL.COM','1500','30/06/2017', 1),
	(10, 2,'ANGELA PORTELLA','F','10/10/1990','9-91511404','9 97460528','ANGELAPORTELLA@GMAIL.COM','1500','30/06/2017', 1)
GO


INSERT INTO consultas
	(id_medico, id_cliente, data, horario, valor, status, ativo)
VALUES
	(1, 1, GETDATE(), '09:00', 150, 'D', 1),
	(2, 2, GETDATE(), '09:30', 150, 'D', 1),
	(3, 3, GETDATE(), '10:00', 150, 'D', 1),
	(1, 4, GETDATE(), '10:50', 150, 'D', 1),
	(2, 5, GETDATE(), '12:20', 150, 'D', 1)
GO

INSERT INTO pagamentos
	(id_forma_pagamento, id_consulta, data, valor, qtd_parcela, ativo)
VALUES
	(1, 1, GETDATE(), 150, 1, 1),
	(2, 2, GETDATE(), 320, 1, 1),
	(3, 3, GETDATE(), 580, 1, 1),
	(4, 4, GETDATE(), 1230, 1, 1),
	(5, 5, GETDATE(), 1230, 1, 1)
GO