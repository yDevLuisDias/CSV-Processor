

<div align="center">
  <h1>CSV-Processor</h1>
  <h2 style="color: red; font-size: 0.8em;">yDevLuisDias</h2>
</div>

---

## Introdução ao Projeto

O CSV-Processor é uma aplicação Java desenvolvida para processar arquivos CSV contendo dados de itens, realizando validações dos dados e separando registros válidos dos inválidos em arquivos distintos. Este projeto demonstra conceitos fundamentais de:

- Manipulação de arquivos CSV em Java
- Validação de dados com tratamento de exceções
- Padrão de arquitetura em camadas (Service, Model)
- Exportação de dados processados
- Boas práticas de programação orientada a objetos

## Desenvolvimento e Fontes de Conhecimento

### 🤖 Classes Desenvolvidas com Auxílio de IA
Durante o desenvolvimento deste projeto, as seguintes classes foram criadas com assistência de Inteligência Artificial:

- **📄 Arquivos de Documentação (.md)**:
  - `README.md` - Documentação principal
  - `API-Documentation.md` - Documentação técnica das classes
  - `Exemplos-Praticos.md` - Guia de exemplos e casos de uso
  - `Troubleshooting-Melhorias.md` - Guia de resolução de problemas

- **🔧 Classe de Serviço**:
  - `ExportService.java` - Serviço responsável pela exportação de dados para arquivos CSV

### 📚 Fontes de Conhecimento Utilizadas
A maior parte do conteúdo técnico e conhecimento absorvido para desenvolver esta aplicação foi obtido através de:

- **Stack Overflow** - Principal fonte para resolução de problemas específicos e boas práticas
- **Phind** - IA especializada em pesquisas técnicas que forneceu insights valiosos sobre:
  - Manipulação de arquivos em Java
  - Padrões de validação de dados
  - Estruturação de projetos Java
  - Tratamento de exceções
  - Boas práticas de programação

Essas ferramentas foram fundamentais para acelerar o desenvolvimento e garantir a qualidade do código implementado.

## Funcionalidades Principais

### 🔍 Leitura de Arquivos CSV
- **Localização**: `src/com/data/file.csv`
- **Formato esperado**: `id,name,price,category,quantity`
- **Processamento linha por linha** com BufferedReader para eficiência
- **Tratamento de cabeçalho** automaticamente ignorado

### ✅ Validação Rigorosa de Dados
- **ID**: Deve ser um número Long positivo e não nulo
- **Nome**: Não pode estar vazio ou em branco
- **Preço**: Deve ser um número Double positivo
- **Categoria**: Campo obrigatório, não pode estar vazio
- **Quantidade**: Deve ser um número Integer positivo

### 📊 Exportação Inteligente
- **Arquivo de dados válidos**: `src/com/data/valid.csv`
  - Contém todos os registros que passaram nas validações
- **Arquivo de erros**: `src/com/data/error.csv`
  - Registros com problemas, mas com dados parciais recuperáveis
- **Relatório detalhado** no console com estatísticas de processamento

### 🛡️ Tratamento de Erros
- **Captura de exceções** NumberFormatException para tipos inválidos
- **Logs detalhados** de erros encontrados
- **Recuperação de dados** quando possível (valores padrão para campos inválidos)
- **Contabilização** de registros com problemas

## Arquitetura do Projeto

```
src/com/
├── Main.java                    # Ponto de entrada da aplicação
├── model/
│   └── ItemsEntity.java        # Entidade representando um item
├── service/
│   ├── ItemsService.java       # Serviço principal de processamento
│   ├── ValidationsService.java # Serviço de validação de dados
│   └── ExportService.java      # Serviço de exportação de arquivos
└── data/
    ├── file.csv               # Arquivo de entrada (dados originais)
    ├── valid.csv              # Arquivo de saída (dados válidos)
    └── error.csv              # Arquivo de saída (dados com erros)
```

### 📋 Descrição das Classes

#### `ItemsEntity.java`
**Responsabilidade**: Modelo de dados para representar um item
- **Atributos**: id (Long), name (String), price (Double), category (String), quantity (Integer)
- **Métodos**: Getters, Setters, `toLine()` para exportação CSV, `toString()` para debug

#### `ItemsService.java`
**Responsabilidade**: Orquestração do processamento principal
- **Método principal**: `readItems()` - coordena leitura, validação e exportação
- **Método auxiliar**: `getItemsEntity()` - converte linha CSV em objeto
- **Dependências**: ValidationsService, ExportService

#### `ValidationsService.java`
**Responsabilidade**: Validação rigorosa dos dados
- **Método principal**: `validationItems()` - retorna lista de erros encontrados
- **Método auxiliar**: `getErrorItems()` - cria objeto com dados parciais recuperados
- **Validações específicas** para cada campo com mensagens descritivas

#### `ExportService.java`
**Responsabilidade**: Exportação de dados processados
- **Métodos**: `validPath()`, `invalidPath()` para diferentes tipos de arquivo
- **Método comum**: `exportValidationData()` - escreve dados no formato CSV
- **Inclui cabeçalho** automaticamente nos arquivos de saída

## Como Executar o Código

### Pré-requisitos
- **Java 11 ou superior** instalado
- **Arquivo de entrada** `file.csv` na pasta `src/com/data/`

### Formato do Arquivo de Entrada
Certifique-se de que o arquivo `src/com/data/file.csv` siga o formato:
```csv
id,name,price,category,quantity
1,Produto A,29.99,Eletrônicos,10
2,Produto B,15.50,Casa,5
```

### Passos para Execução

1. **Navegue até o diretório do projeto**:
   ```bash
   cd /home/costa/Desktop/eng-dados/CSV-Processor
   ```

2. **Compile o projeto**:
   ```bash
   javac -d bin src/com/**/*.java
   ```

3. **Execute a aplicação**:
   ```bash
   java -cp bin com.Main
   ```

### Resultado da Execução

Após a execução, você encontrará:
- **Console**: Relatório detalhado com estatísticas e erros
- **`src/com/data/valid.csv`**: Registros válidos processados
- **`src/com/data/error.csv`**: Registros com problemas, mas dados recuperáveis

### Exemplo de Saída no Console
```
arquivo : file.csv
Item{id=1, name='Produto A', price=29.99, category='Eletrônicos', quantity=10}

<---- <> ---->
Error no id : 0
 <--<!>--> Id está vazio
 <--<!>--> Preço está negativo - inválido

Quantidade de itens que retornaram erro : 1
id's que retornaram null : [2]
```

## Características Técnicas

- **Linguagem**: Java
- **Padrão de Arquitetura**: Service Layer Pattern
- **Tratamento de I/O**: BufferedReader/BufferedWriter para performance
- **Validação**: Defensive Programming com múltiplas verificações
- **Logging**: Output detalhado para debugging e monitoramento
