# Guia de Exemplos Práticos - CSV-Processor

## Exemplos de Uso

### 1. Arquivo de Entrada Válido

**Arquivo**: `src/com/data/file.csv`
```csv
id,name,price,category,quantity
1,Notebook Dell,2500.00,Informática,5
2,Mouse Logitech,45.90,Periféricos,20
3,Teclado Mecânico,189.99,Periféricos,8
4,Monitor 24",899.00,Monitores,3
```

**Resultado Esperado**:
- Todos os registros processados com sucesso
- Arquivo `valid.csv` contém todos os 4 itens
- Arquivo `error.csv` vazio
- Console mostra 0 erros

### 2. Arquivo com Dados Inválidos

**Arquivo**: `src/com/data/file.csv`
```csv
id,name,price,category,quantity
1,Produto A,29.99,Eletrônicos,10
abc,Produto B,15.50,Casa,5
3,,45.00,Jardim,2
4,Produto D,-10.50,Casa,8
5,Produto E,25.00,,15
6,Produto F,30.00,Decoração,-5
```

**Análise dos Erros**:
- **Linha 2**: ID inválido (`abc` não é número)
- **Linha 3**: Nome vazio
- **Linha 4**: Preço negativo (-10.50)
- **Linha 5**: Categoria vazia
- **Linha 6**: Quantidade negativa (-5)

**Resultado Esperado**:
- `valid.csv`: Apenas linha 1 (Produto A)
- `error.csv`: Linhas 2-6 com dados recuperáveis
- Console: 5 erros reportados

### 3. Saída no Console - Exemplo Detalhado

```
arquivo : file.csv
Item{id=1
, name='Produto A'
, price=29.99
, category='Eletrônicos'
, quantity=10
}

<---- <> ---->
Error no id : abc
 <--<!>--> Error <id> Formato incompativél - Formato desejado : Long

<---- <> ---->
Error no item : Produto C
 <--<!>--> Nome está vazio

<---- <> ---->
Error no id : 4
 <--<!>--> Preço está negativo - inválido

<---- <> ---->
Quantidade de itens que retornaram erro : 5
id's que retornaram null : [2, 4, 6]
itens que retornaram null : [Produto C]
```

## Cenários de Teste

### Teste 1: Dados Completamente Válidos
```csv
id,name,price,category,quantity
1,Item A,10.50,Cat1,5
2,Item B,20.75,Cat2,10
```
**Expectativa**: Todos os dados em `valid.csv`, `error.csv` vazio.

### Teste 2: Campos Vazios
```csv
id,name,price,category,quantity
,,,,
1,,10.50,Cat1,5
2,Item B,,Cat2,10
```
**Expectativa**: 
- Linha 1: Todos os campos com erro
- Linha 2: Nome vazio
- Linha 3: Preço vazio

### Teste 3: Tipos Incorretos
```csv
id,name,price,category,quantity
abc,Item A,xyz,Cat1,def
1.5,Item B,20.75,Cat2,10.5
```
**Expectativa**:
- Linha 1: ID, preço e quantidade com formato incorreto
- Linha 2: ID como 1 (truncado), quantidade como 10 (truncado)

### Teste 4: Valores Negativos
```csv
id,name,price,category,quantity
-1,Item A,10.50,Cat1,5
1,Item B,-20.75,Cat2,10
2,Item C,30.00,Cat3,-5
```
**Expectativa**:
- Linha 1: ID negativo
- Linha 2: Preço negativo
- Linha 3: Quantidade negativa

## Estrutura dos Arquivos de Saída

### valid.csv
```csv
id,name,price,category,quantity
1,Produto A,29.99,Eletrônicos,10
2,Produto B,15.50,Casa,5
```

### error.csv
```csv
id,name,price,category,quantity
0,Error,0.0,Error,0
3,Produto C,45.0,Jardim,2
4,Produto D,0.0,Casa,8
```

## Casos Extremos

### Arquivo Vazio
**Entrada**: Apenas cabeçalho
```csv
id,name,price,category,quantity
```
**Resultado**: Ambos arquivos de saída vazios (apenas cabeçalho)

### Arquivo Muito Grande
**Comportamento**: Processamento linha por linha garante eficiência de memória

### Caracteres Especiais
**Entrada**:
```csv
id,name,price,category,quantity
1,"Produto, com vírgula",29.99,Eletrônicos,10
```
**Nota**: Aspas duplas podem causar problemas na divisão por vírgula

## Dicas de Troubleshooting

1. **Erro de arquivo não encontrado**: Verificar se `file.csv` existe em `src/com/data/`
2. **Dados não aparecem**: Verificar formato do cabeçalho (deve ser exato)
3. **Números não reconhecidos**: Usar ponto (.) como separador decimal
4. **Campos com vírgula**: Evitar vírgulas nos dados ou usar delimitador diferente
