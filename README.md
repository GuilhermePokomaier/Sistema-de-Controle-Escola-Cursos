# Sistema de Controle Escola/Cursos

Aplicação de console em **Java** para facilitar a gestão acadêmica. Funciona como um portal em que alunos e professores se cadastram, os professores criam cursos, publicam atividades e lançam notas, e os alunos se matriculam e acompanham suas atividades e notas em um único ambiente.

O projeto foi desenvolvido usando apenas os conceitos básicos da linguagem: **métodos, arrays, herança e polimorfismo**.

---

## Funcionalidades

### Acesso ao sistema
- Cadastro de usuário (aluno ou professor) com nome, senha, idade e CPF
- Login por nome e senha
- Bloqueio de CPF duplicado

### Professor
- Criar cursos
- Ver seus cursos e os alunos matriculados em cada um
- Publicar atividades (título e descrição) em um curso
- Lançar notas (de 0 a 10) para os alunos de um curso

### Aluno
- Matricular-se em cursos disponíveis
- Ver os cursos em que está matriculado
- Ver as atividades publicadas nos seus cursos
- Ver suas notas e a média geral

---

## Estrutura do projeto

| Arquivo | Responsabilidade |
|---|---|
| `Index.java` | Ponto de entrada: menu inicial, cadastro e login |
| `Usuario.java` | Classe base abstrata com os dados comuns (nome, senha, idade, CPF) |
| `Aluno.java` | Herda de `Usuario`; menu e ações do aluno |
| `Professor.java` | Herda de `Usuario`; menu e ações do professor |
| `Curso.java` | Dados do curso e lista (array) de alunos matriculados |
| `Atividade.java` | Atividade publicada em um curso |
| `Nota.java` | Nota de um aluno em uma avaliação de um curso |
| `Sistema.java` | Guarda todos os dados em arrays e oferece os métodos de busca e cadastro |
| `Entrada.java` | Leitura do teclado com tratamento de entradas inválidas |

---

## Conceitos aplicados

- **Arrays:** usuários, cursos, atividades, notas e a lista de alunos de cada curso são armazenados em arrays de tamanho fixo.
- **Métodos:** a lógica é dividida em métodos pequenos (cadastrar, logar, matricular, lançar nota, etc.).
- **Herança:** `Aluno` e `Professor` estendem `Usuario` e reaproveitam seus atributos e getters.
- **Polimorfismo:** `exibirPerfil()` e `exibirMenu()` são sobrescritos em `Aluno` e `Professor`. Depois do login, o `Index` chama `usuario.exibirMenu(sistema)` sem saber o tipo do usuário, e cada classe responde com o seu próprio menu.

---

## Limites do sistema

| Item | Capacidade |
|---|---|
| Usuários | 100 |
| Cursos | 50 |
| Alunos por curso | 50 |
| Atividades | 200 |
| Notas | 500 |

---

## Como executar

**Requisito:** JDK 8 ou superior.

### Pelo IntelliJ IDEA
1. Abra a pasta do projeto.
2. Execute a classe `Index` (método `main`).

### Pelo terminal
```
javac -encoding UTF-8 *.java
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 Index
```
No Windows, rode `chcp 65001` antes para o console exibir os acentos corretamente.

### Acentuação no IntelliJ
1. Em *File > Settings > Editor > File Encodings*, defina **UTF-8** em "Global Encoding" e "Project Encoding".
2. Em *Run > Edit Configurations*, adicione em "VM options":
   ```
   -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8
   ```
   (em JDK anterior ao 19, use `-Dsun.stdout.encoding=UTF-8` no lugar do segundo parâmetro)

---

## Exemplo de fluxo de uso

1. Cadastrar um **professor** e um **aluno** (opção `2` do menu inicial).
2. Fazer login como professor, criar um curso e publicar uma atividade.
3. Fazer login como aluno e matricular-se no curso; conferir as atividades.
4. Fazer login como professor e lançar uma nota para o aluno.
5. Fazer login como aluno e consultar as notas e a média.

---

## Limitações conhecidas

- Os dados ficam **apenas na memória**: ao encerrar o programa, tudo é perdido.
- As senhas são armazenadas **em texto puro**, o que é aceitável apenas para fins didáticos.
- O login usa nome e senha; dois usuários com o mesmo nome e a mesma senha não são distinguidos.
- Não há edição ou exclusão de cursos, atividades e notas.

## Possíveis melhorias

- Persistência dos dados em arquivo ou banco de dados
- Criptografia (hash) das senhas
- Edição e remoção de cursos, atividades e notas
- Média por curso e situação de aprovação
- Interface gráfica

---

**Autor:** Guilherme
