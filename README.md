# RequisitionFlow

Este projeto tem como objetivo **processar requisições HTTP simuladas** utilizando os **padrões de design Chain of Responsibility** e **Command**. Vou explicar como esses padrões se encaixam no funcionamento do projeto e o que ele faz.

### Objetivo do Projeto

O projeto simula o processamento de requisições HTTP, executando as seguintes tarefas em sequência:

1. **Verificação de Autenticação**: Verifica se a requisição tem um token de autenticação válido. Este é um processo que garante que o usuário ou serviço que está fazendo a requisição tenha permissão para acessar os recursos da aplicação.

2. **Verificação de Permissões**: Após a autenticação ser validada, é verificado se o usuário tem as permissões necessárias para executar a operação solicitada (por exemplo, verificar se o usuário tem o papel de "admin" ou "user" no sistema).

3. **Transformação do Corpo da Requisição (JSON para Map)**: O corpo da requisição, que está no formato JSON, é transformado em um `Map<String, String>`. Esse mapeamento permite que os dados do corpo da requisição sejam processados facilmente em outras partes do sistema.

### Como isso é feito:

#### 1. Padrão Chain of Responsibility

O padrão **Chain of Responsibility** é utilizado para tratar as requisições de maneira encadeada, onde cada "handler" (tratador) pode realizar uma parte do processo e, caso não consiga realizar, passa a requisição para o próximo "handler". Neste caso, temos uma cadeia de responsabilidades que verifica a autenticação, as permissões e transforma o corpo da requisição. Cada uma dessas verificações será realizada por um **handler** específico.

* **AuthenticationHandler**: Responsável por verificar se a requisição possui um token de autenticação válido.
* **PermissionHandler**: Responsável por verificar se o usuário tem permissões para executar a ação.
* **BodyTransformationHandler**: Responsável por transformar o corpo JSON da requisição em um `Map<String, String>`.

A requisição passa por cada um desses handlers, que podem decidir se devem continuar a cadeia ou não (por exemplo, se a autenticação falhar, o processamento pode ser interrompido).

#### 2. Padrão Command

O padrão **Command** é usado para encapsular ações em objetos. Cada uma dessas ações, como verificação de autenticação, permissões e transformação do corpo, é representada por um **comando**.

* Cada operação (como autenticar, verificar permissões ou transformar o corpo da requisição) é encapsulada em um objeto de comando.
* Os objetos de comando são executados quando são acionados pelos manipuladores da cadeia, simplificando a execução de ações complexas de maneira desacoplada.

### Resumo do Fluxo de Processamento:

1. **Criação da Requisição**: A requisição simulada é criada, com cabeçalhos (como `Authorization` e `Role`) e corpo (em formato JSON).

2. **Autenticação**: O primeiro manipulador da cadeia (`AuthenticationHandler`) verifica se a requisição contém um token de autenticação válido. Se a autenticação falhar, o processamento é interrompido.

3. **Permissões**: O segundo manipulador (`PermissionHandler`) verifica se o usuário tem permissões suficientes para realizar a ação solicitada. Se as permissões não forem suficientes, o processamento também é interrompido.

4. **Transformação do Corpo**: Se as etapas anteriores forem bem-sucedidas, o terceiro manipulador (`BodyTransformationHandler`) transforma o corpo JSON da requisição em um `Map<String, String>` e armazena esse mapa no atributo `attributes` da requisição.

5. **Finalização**: Após todos os manipuladores, a requisição contém as informações necessárias no formato desejado e pode ser processada ou retornada ao cliente com sucesso.

### Exemplo de Funcionamento:

1. **Requisição Recebida**:

    * Cabeçalhos: `Authorization: Bearer token_valid`, `Role: admin`
    * Corpo: `{"key": "value"}`

2. **Cadeia de Responsabilidade**:

    * **Autenticação**: Verifica se o token está presente e é válido.
    * **Permissões**: Verifica se o usuário tem o papel de `admin`.
    * **Transformação do Corpo**: Converte o corpo JSON em `{"key": "value"}` para um `Map<String, String>` com `{"key": "value"}`.

3. **Resultado**:

    * A requisição tem os atributos transformados e está pronta para ser processada ou retornada.

### Benefícios:

* **Desacoplamento**: O uso de Chain of Responsibility e Command promove a separação de preocupações, tornando o código mais modular, escalável e fácil de manter.
* **Flexibilidade**: É fácil adicionar novos manipuladores ou comandos à cadeia sem afetar o restante do código.

Este é um projeto básico que utiliza esses dois padrões para organizar e processar requisições de maneira eficiente e organizada.
