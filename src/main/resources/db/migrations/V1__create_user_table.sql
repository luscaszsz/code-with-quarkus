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


CREATE TABLE simulacao (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    clienteId TEXT NOT NULL,
    produtoNome TEXT NOT NULL,
    tipoProduto TEXT NOT NULL,
    valorInvestido TEXT NOT NULL,
    prazoMeses INTEGER NOT NULL,
    rentabilidadeAplicada NUMERIC NOT NULL,
    valorFinal NUMERIC NOT NULL,
    dataSimulacao TEXT NOT NULL
);

INSERT INTO produto
(nome, tipoProduto, rentabilidadeAnual, risco, prazoMinMeses, prazoMaxMeses, valorMin, valorMax)
VALUES
('CDB - Banco Box-Alpha-M&A Médio Prazo 12,5%', 'CDB', 0.125, 'baixo', 6, 24, 10.00, 5000.00),
('CDB - Banco Box-Beta-Comercial Liquidez 11,8%', 'CDB', 0.118, 'baixo', 1, 60, 1.00, 10000.00),
('LCI - Imobiliarias Sul-Sudeste Isenção 10,8%', 'LCI', 0.108, 'baixo', 12, 36, 50.00, 3000.00),
('LCA - AgroMax Pessoa Física 11,5%', 'LCA', 0.115, 'baixo', 9, 24, 20.00, 2500.00),
('Debênture - Infra&Energia Box-Brasil 14,5%', 'DEBENTURE', 0.145, 'médio', 24, 84, 100.00, 20000.00),
('Fundo Multimercado - Estratégia Box-ClientePlus', 'FUNDO', 0.18, 'alto', 12, 120, 150.00, 10000.00),
('CRI - ImobilMax Box-Prime 16%', 'CRI', 0.16, 'médio', 18, 60, 50.00, 15000.00);