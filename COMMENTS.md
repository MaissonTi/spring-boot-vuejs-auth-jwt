# INSTRUÇÕES DE ENTREGA

### Decisão da arquitetura utilizada:

- **Backend**
  A arquitetura aplicada foi a Clean Architecture, a fim de facilitar o desenvolvimento do projeto, manutenção do código e a utilização de menos dependências. Foram utlizados tambem padrãos de projeto como Service Layer e Repository Pattern separando o projeto por niveis de camadas para que as classes repeitem o "Princípio da responsabilidade única".

- **Frontend**
  Não foi usado nenhum arquitetura especifica para o frontend, apenas foi estruturado em modulos buscando a simplicidade na alteração e adição de novos recursos.

### Lista de bibliotecas de terceiros utilizadas

- **Backend**
  lombok; flywaydb;

- **Frontend**
  axios;vue-toasted;vuelidate;

**Observação**: As demais lib's informadas no pom.xml e no package.json, são lib's "padrões" de um projeto

### O que você melhoraria se tivesse mais tempo

- **Backend e Frontend**
  Melhoria as exceções de erros para o client;

### Quais requisitos obrigatórios que não foram entregues

- **Backend e Frontend**
  Não foi finalizado todos os testes;
