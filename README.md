# Biblioteca Multitemático

Este é um projeto de terminal desenvolvido em Java com fins acadêmicos. Ele simula uma pequena biblioteca onde é possível cadastrar **temas** e **obras** relacionadas, armazenando os dados localmente em arquivos `.txt`.

---

## Funcionalidades

- Armazenamento de temas e obras em arquivos `.txt`
- Listagem de temas e suas obras
- Adição e remoção de temas
- Adição e remoção de obras
- Salvamento automático ao encerrar o programa

---

## Tecnologias Utilizadas

- **Java (versão 8 ou superior)**
- Armazenamento em **arquivos `.txt`**
- Execução via **CLI (linha de comando)**

---

## Estrutura do Projeto

```
├── Biblioteca/
│   ├── ArquivoUtils.java  # Classe utilitária para leitura e escrita dos arquivos
│   ├── Obra.java          # Classe representando uma obra (título, conteúdo e tema)
│   └── Tema.java          # Classe representando um tema
├── temas.txt              # Arquivo com os temas cadastrados
├── obras.txt              # Arquivo com as obras cadastradas
└── Main.java              # Classe principal com os menus de interação
```

---

## Como Executar

1. Compile todos os arquivos `.java`:

```bash
javac Main.java Biblioteca/*.java
```

2. Execute o programa:

```bash
java Main
```

---

## Projeto Acadêmico

Este projeto foi desenvolvido como parte da disciplina de programação.

---

## Observações

- O projeto utiliza apenas entrada e saída via terminal (CLI).
- As funcionalidades são simples e focadas no aprendizado de:
  - Estruturas de dados (listas)
  - Manipulação de arquivos
  - Lógica de programação com menus interativos

---

## Autor

Matheus Schingiry da Silva

---
