<h1 align="center">API para cadastro de créditos</h1>
<br>

<div>
<p>Este projeto é uma API que foi desenvolvida como projeto final do bootcamp Kotlin para backend da DIO (Digital Innovation One) junto com a empresa NTT Data.</p>
</div>
<br>

## 🔨 Funcionalidades do projeto

- `Funcionalidade 1` `Cadastro de cliente`: A API precisa estar apto a cadastrar clientes. Para cadastrar um cliente, devem ser informados seu nome completo,  seu e-mail, seu CPF, seu salário, seu endereço e sua senha. Todos os campos são obrigatórios.
  
- `Funcionalidade 2` `Cadastro de crédito`: A API precisa estar apto a cadastrar créditos para os clientes. Para cadastrar um crédito, devem ser informados a id do cliente,  o valor do crédito, a data de vencimento da primeira parcela, o número de parcelas, e o status da solicitação, sendo esse limitado à : (IN_PROGRESS, APROVED E REJECT). Todos os campos são obrigatórios.
  
- `Funcionalidade 3` `Listagem de clientes`: A API deve possuir uma funcionalidade de listagem de clientes, na qual as seguintes informações, de cada um dos clientes cadastrados, deverão ser exibidas: Nome completo, E-mail, CPF, valor do salário, endereço e ID. A busca deve ser feita pela ID do cliente.

- `Funcionalidade 4` `Listagem de créditos por código do cliente`: A API deve possuir uma funcionalidade de listagem de crédito, na qual as seguintes informações, do cliente que está consultando, deverão ser exibidas: Id do crédito, valor do crédito, número de parcelas, status, e-mail do cliente, salario do cliente, essa busca deve ser feita pelo id do cliente.

- `Funcionalidade 5` `Listagem de créditos por código do crédito`: A API deve possuir uma funcionalidade de listagem de crédito, na qual as seguintes informações, do cliente que está consultando, deverão ser exibidas: Id do crédito, valor do crédito, número de parcelas, status, e-mail do cliente, salario do cliente, essa busca deve ser feita pelo código do crédito.

- `Funcionalidade 6` `Atualização de cadastro de cliente`: A API deve possuir uma funcionalidade de atualização para o cadastro de clientes, de acordo com a ID respectiva, os campos que podem ser atualizados são: Nome completo, valor do salário e endereço.

- `Funcionalidade 7` `Exclusão de clientes e de créditos`: A API deve possuir uma funcionalidade que permita a exclusão de clientes e de créditos cadastrados. A exclusão deve ser feita com base nos IDs respectivos.

 
## ✔️ Tecnologias utilizadas

<div align="left">
  <img src="https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white">
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white">
  <img src="https://img.shields.io/badge/Flyway-CC0200.svg?style=for-the-badge&logo=Flyway&logoColor=white">
  <img src="https://img.shields.io/badge/H2 Database-6DB33F.svg?style=for-the-badge">
</div>
<br>

## 🛠️ Ferramentas utilizadas

<div align="left">
   <img src="https://img.shields.io/badge/Insomnia-4000BF.svg?style=for-the-badge&logo=Insomnia&logoColor=white">
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white">
 <img src="https://img.shields.io/badge/Swagger-85EA2D.svg?style=for-the-badge&logo=Swagger&logoColor=black">
</div>

