--seed de dados

CREATE TABLE produto (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    tipoProduto TEXT NOT NULL,        --CDB, LCI, LCA, ETC
    rentabilidadeAnual NUMERIC NOT NULL, --0.12
    risco TEXT NOT NULL,              --baixo, médio, alto
    prazoMinMeses INTEGER NOT NULL,
    prazoMaxMeses INTEGER NOT NULL,
    valorMin NUMERIC NOT NULL,
    valorMax NUMERIC NOT NULL
);


CREATE TABLE produtoproduto (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    clienteId TEXT NOT NULL,
    produtoNome TEXT NOT NULL,
    tipoProduto TEXT NOT NULL,
    valorInvestido TEXT NOT NULL,
    prazoMeses INTEGER NOT NULL,
    rentabilidadeAplicada NUMERIC NOT NULL,
    valorFinal NUMERIC NOT NULL,
    dataSimulacao TEXT NOT NULL,
);

INSERT INTO produto
(nome, tipoProduto, rentabilidadeAnual, risco, prazoMinMeses, prazoMaxMeses, valorMin, valorMax)
VALUES
('CDB - Banco Box-Alpha-M&A Médio Prazo 12,5%', 'CDB', 0.125, 'baixo', 6, 24, 1000, 500000),
('CDB - Banco Box-Beta-Comercial Liquidez 11,8%', 'CDB', 0.118, 'baixo', 1, 60, 100, 1000000),
('LCI - Imobiliarias Sul-Sudeste Isenção 10,8%', 'LCI', 0.108, 'baixo', 12, 36, 5000, 300000),
('LCA - AgroMax Pessoa Física 11,5%', 'LCA', 0.115, 'baixo', 9, 24, 2000, 250000),
('Debênture - Infra&Energia Box-Brasil 14,5%', 'DEBENTURE', 0.145, 'médio', 24, 84, 10000, 2000000),
('Fundo Multimercado - Estratégia Box-ClientePlus', 'FUNDO', 0.18, 'alto', 12, 120, 15000, 1000000),
('CRI - ImobilMax Box-Prime 16%', 'CRI', 0.16, 'médio', 18, 60, 5000, 1500000);