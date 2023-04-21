delete from origem_cliente where id > 0;

insert into origem_cliente(status, nome)
SELECT distinct 'T', i.origem_cliente
  FROM oticaver.import_cliente i
 where i.origem_cliente is not null;

insert into profissao(status, nome)
SELECT distinct 'T', i.profissao
  FROM oticaver.import_cliente i
 where i.profissao is not null;

INSERT INTO cliente
(nome,apelido_fantasia,celular2,codigo_externo,codigo_importacao,
 cpf_cnpj,data_cadastro,data_nascimento,endereco,idade,nome_razao_social,nome_mae,
 nome_pai,observacao,rg,sexo,suframa,telefone_comercial,telefone_residencial,
 loja_id,origem_cliente,profissao_id,bairro,celular,cep,cidade,complemento,email,numero,status)
select ic.nome_razao_social,
	   ic.apelido_nome_fantasia,
       ic.celular1,
       ic.codigo_externo,
       ic.codigo_importacao,
	   ic.documento,
       ic.data_cadastro,
       ic.dt_nascimento,
       ic.endereco,
       ic.idade,
       ic.nome_razao_social,
       ic.nome_mae,
	   ic.nome_pai,ic.observacao,
	   ic.rg_ie,ic.sexo,ic.suframa,ic.telefone,ic.telefone1,l.id,oc.id,p.id,ic.bairro,ic.celular,ic.cep,ic.cidade,ic.complemento,
	   ic.email,ic.numero,'T'
  from import_cliente ic 
  left join origem_cliente oc on oc.nome = ic.origem_cliente
  left join profissao p on p.nome = ic.profissao
  left join loja l on l.nome = ic.cadastrado_em;

update cliente c 
   set c.tipo_pessoa = 'CNPJ' 
 where c.id > 0 and length(c.cpf_cnpj) > 14;

update cliente c 
   set c.tipo_pessoa = 'CPF' 
 where c.id > 0 and length(c.cpf_cnpj) = 14;

insert into grife(nome,status)
select distinct ip.grife,'T' from import_produto ip where ip.grife is not null;

insert into grupo_produto(nome,status)
select distinct ip.grupo,'T' from import_produto ip where ip.grupo is not null;

insert into fornecedor(nome_fantasia,status)
select distinct ip.fornecedor,'T' from import_produto ip where ip.fornecedor is not null;

insert into unidade(sigla,status)
select distinct ip.unidade,'T' from import_produto ip where ip.unidade is not null;

INSERT INTO produto(codigo_gtin,controlar_estoque,data_cadastro,nome,referencia,status,venda_somente_os,
		fornecedor,grife,grupo_produto,unidade)
SELECT  distinct 
		i.codigo_gtin,
		case when i.controlar_estoque = 'SIM' then 'T' else 'F' end,
		i.data_cadastro,
		i.descricao,
	    i.referencia,
		'T',
		case when i.venda_somente_com_os = 'SIM' then 'T' else 'F' end,
		f.id,
		g.id,
		gp.id,
		u.id
  FROM oticaver.import_produto i
  LEFT JOIN UNIDADE U ON U.sigla = i.unidade
  LEFT JOIN fornecedor f on f.nome_fantasia = i.fornecedor
  LEFT JOIN grupo_produto gp on gp.nome = i.grupo
  LEFT JOIN grife g on g.nome = i.grife;

select * From import_cliente

