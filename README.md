# Manual de Implantação

  O presente Manual contém as instruções necessárias para realizar a implantação deste repositório. Além disto, também possui exemplos e explicações sobre o sistema para melhor compreensão e usabilidade

# 1 O Projeto

  Este capítulo explicará sobre o Projeto em si, como ele está organizado e o que é necessário para executar o mesmo. Capítulo essencial pois se trata da configuração do mesmo para o melhor funcionamento.
  
# 1.1 A IDE

  O projeto requer uma IDE para ser acessado e inicializado. O mesmo poderia ter sido exportado como .jar, para uma execução mais simplificada, contudo seria dificultoso o processo para encerrar a API, além de não ser possível visualizar o código fonte. Assim sendo, abri-lo por uma IDE é a maneira mais eficiente de se executar o projeto. Pode utilizar qualquer IDE de sua preferência, particularmente recomendo a IntelliJ que foi a IDE utilizada para desenvolver este projeto.
  
  Assim sendo, baixe o projeto e abra-o em sua IDE de preferência.
  
# 1.2 Estrutura do Projeto

  Após realizar a abertura do projeto, você notará que o mesmo está padronizado pela Arquitetura MVC (Model, View, Controller). No pacote de Controller, você encontrará os End Points do sistema. Conforme requisitado, o projeto possui 2 métodos GET (um para buscar todas as notas, e um para buscar apenas uma nota), 1 método POST, 1 método PUT e 1 método DELETE sendo eles respectivamentes: getAllNotas, getNotaFilter, insertNotaFiscal, editNotaFiscal e deleteNotaFiscal. Os endpoints são a parte essencial da comunicação entre o back-end e as requisições feitas por um front-end ou consumidor de APIs.
  
  O pacote Model, contém os "modelos" do projeto, ou seja, as bases padrões de cada entidade. Este pacote contém a classe NotaFiscal, que se trata do modelo de uma Nota Fiscal e também da tabela para o Banco de Dados (abordaremos mais à frente), ErrorResponse que se trata de um modelo para Erros Customizados, ou seja, podemos definir o que queremos que seja exibido quando houver um erro, e por fim Nota Fiscal Specification que se trata de uma classe que extende a Interface Specification do framework JPA que tem o intuito de realizar consultas dinâmicas ao Banco de Dados. Esta classe é a responsável por realizar os filtros de uma Nota Fiscal. Além disto, o pacote Model possuí um pacote dentro dele, o Repository, que se trata de um pacote onde os Repositorys ficam: interfaces responsáveis pelas transações e seleções com o Banco de Dados.
  
  Já o pacote Service contém toda a lógica por trás do projeto. É nele que ocorrem todas as regras de negócio, chamadas aos repositorys, bem como outras funcionalidades do projeto. Os services sempre são chamados pelos endpoints na camadade Controller, então os mesmos não possuem autonomia para serem executados a menos que o controller chamem os mesmos.
  
  Além dos pacotes existe a classe Utils, uma classe criada por mim, com o objetivo de possuir alguns métodos úteis com algumas regras de negócio, que possam ser utilizados por todas as classes. Por fim, a clase Gerador de NFe Application é o "main" da aplicação. É a classe responsável por executar a iniciar a aplicação.

# 1.3 O Banco de Dados

  O Banco de Dados utilizado neste projeto foi o MySQL em sua versão 8.0.31. Além disto, utilizeo o HeidiSQL para Gerenciamento do mesmo, contudo, você pode utilizar o software de sua preferência desde que o mesmo aceite Banco de Dados MySQL. Neste repositório existe um arquivo intitulado scriptBancoDados.sql. Este arquivo contém o script para criação da database, bem como a tabela e alguns inserts que utilizei durante os testes da aplicação. No entanto devo ressaltar que como o projeto utiliza JPA com Hibernate, a tabela e seus campos são criados e atualizados automaticamente pelo framework. Assim sendo não é necessário a criação das tabelas e campos/colunas manualmente, o único requisito é que o Banco de Dados em si seja criado, ou seja, que a database seja criado. Sendo assim você pode criar o Banco de Dados através de seu software preferido ou então utilizar a linha SQL:

                    CREATE DATABASE IF NOT EXISTS `nfe_now` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
                    USE `nfe_now`;
    
  Importante ressaltar que o nome do Banco sempre deve ser nfe_now, caso contrário ocorrerá um erro ao qual o JPA não encontrará o vosso Banco de Dados. Este Banco está estruturado da seguinte forma:

    Tabela - notas_fiscais
    id (int): Campo responsável por ser o identificador e chave-primária da tabela;
    numero_nota_fiscal (varchar): Campo ao qual será inserido o número de nota fiscal contendo 9 caracteres podendo ir de 000000001 até 999999999. Este campo é gerado automaticamente pelo sistema, não sendo necessário a digitação do usuário no momento de inserção. Apenas no momento de edição o campo pode ser modificado.;
    numero_serie (varchar): Campo destinado ao número de série da Nota Fiscal podendo ir de 1 até 999;
    razao_social (varchar): Campo destino à razão social da empresa;
    cnpj (varchar): Campo destinado ao CNPJ da empresa;
    endereco (varchar): Campo destinado ao endereco da empresa;
    valor (double): Campo destinado ao valor da Nota Fiscal em questão. O valor máximo que pode ser registrado é de 999.999.999;

  Toda esta configuração do Banco de Dados foi realizada utilizando o JPA. No pacote Model, a classe Nota Fiscal citada anteriormente possui algumas anotações, dentre elas a @Entity, anotação responsável por informar ao JPA que aquele classe se trata de uma Entidade a ser modelada para o Banco de Dados. Com isto, o JPA entende que aquilo será uma tabela para o Banco de Dados e mapeia tudo o que tiver ali dentro, de acordo com as anotações como por exemplo: @Id, @Column, @GeneratedValue entre outros. Para realizar uma alteração na tabela, basta apenas alterar na classe respectiva que automaticamente o Banco será atualizado.
    
# 1.4 Configurando o Application.properties

  Por mais que seja relacionado ao Banco de Dados, resolvi separar este tópico para maior facilidade em procurá-lo. O Application.properties contém as informações sobre o Banco de Dados: sua URL, username, password, dialect e etc... Como estava trabalhando com localhost, a url de acesso ao meu Banco de Dados é a seguinte: "jdbc:mysql://localhost:3306/nfe_now". Ressalto que, meu usuário de acesso ao mesmo era simples pois se trata de um banco de testes, assim sendo possui um password simples. Contudo, isto deve ser modificado para o seu ambiente. Caso vá utilizar o Banco de Dados em outro local que não seja a sua própria máquina, deve-se modificar a URL. Além disto, mesmo que vá utilizar em sua própria máquina, caso tenha modificado a porta padrão do MySQL, deve-se alterar a mesma na URL.
  
  E por fim, deve-se alterar e configurar o vosso usuário para o acesso ao Banco de Dados com o seu username e password. Os demais campos que são: hibernate.ddl-uto e hibernate.dialect não é necessário nenhuma alteração.

# 1.5 Inicializando o Projeto

  Com todo o projeto configurado, você está apto a inicializar o mesmo. Abra a classe GeradorDeNFeApplication, a classe que possui o main da aplicação, e execute a mesma. O Springboot irá inicializar o API na porta 8080 em sua máquina. Para verificar se inicializou como deveria, abra o prompt de vossa IDE e verifique se a mensagem "Completed initialization in x ms" apareceu, sendo x um número qualquer. Com isto a API já estará funcionando localmente e você poderá consumi-la. Para encerrar a mesma, ou seja parar a sua execução, basta apenas encerrar a aplicação pela sua IDE.

# 2 Consumindo a API

  Com a API no ar e funcionando corretamente, você poderá utilizá-la e consumi-la da forma que preferir. Este capítulo abordará como consumir a API, o que enviar no body, header entre outros.

# 2.1 Plataforma de API

  Neste manual vamos utilizar o Postman para realizar todas as requisições, mas elas também podem ser feitas pelo Insomnia ou outro software para consumir APIs. A mesma também pode ser consumida por um front-end, contudo, o front-end precisará ser construído do zero. O Postman nada mais é do que um software para consumir APIs, no caso, testar as requisições e verificar se está funcionando como deveria. Ele é um software muito prático que evita a necessidade da criação de um Front-End para testar API, ou até mesmo precisar consumir a API pela URL do browser.
  
  Após abrir a vossa plataforma de API, você poderá usar os caminhos citados posteriormente para realizar os testes. Lembre-se sempre de verificar se o caminho está escrito corretamene, caso contrário, resultará em erro. Além disto, atente-se ao tipo de requisição que irá enviar: GET, POST, PUT ou DELETE para evitar outros erros.

# 2.2 Headers

  Para os métodos de POST e PUT, eles requerem um body. Este body deverá ser enviando como um JSON. Para isto, é importante configurar o Header do vosso software. Configure da seguinte forma: Key = Content-Type, Value = applicattion/json. Com isto, o software entenderá que deverá enviar um JSON para a API. Se estiver usando Postman, clique na aba "body" e depois "raw". Ao lado direito, ele provavelmente virá configurado como "Text". Mude para JSON. Se estiver no Insomnia, a primeira aba terá o nome de "body". Clique nela e mude para "JSON". Caso esteja utilizando outra plataforma não citada, consulte a documentação da mesma. Com isto sua Plataforma estará configurada para enviar Bodys em formato de JSON para a API.

# 2.3 Path Base

  A API possui um Path base, um caminho constante que deverá ser utilizado em todas as requisições. Este path deve ser colocado na URL da Plataforma de API. O Path é: http://localhost:8080/api/notas
  
  Com este caminho, você será capaz de complementá-lo caso necessário e realizar as requisições.
  
# 2.4 Requisições

  Este presente capítulo tem como objetivo mostrar e explicar sobre as requisições da aplicação: seus paths, params, bodys e entre outros.

# 2.4.1 Requisições GET

  A API possui duas requisições GET sendo elas:
  
    - getAllNotas;
    - getNotaFilter.

  Obs: Você pode passar os parâmetros das requisições na aba de "Params" ou na própria URL.
  
# 2.4.1.1 GET - getAllNotas

  Path: Path Base;
  
  Parâmetros: Esta requisição espera dois parâmetros sendo eles: actualPage (Página Atual) e itemsPerPage (Quantidade de Items por Página). Todos eles são "ints". Caso nenhum valor seja associado aos mesmos, eles assumem valor padrão de 0 e 10 respectivamentes. Estes parâmetros são para ordenação dos items de resposta em formato de páginas.
  
  Resultado Esperado: Um Iterable de Notas Fiscais com todos os registros do Banco de Dados. Caso não encontre nenhum registro, retornará um erro com status 404 de Notas Fiscais não encontradas.
  
  Exemplo de Requisição: http://localhost:8080/api/notas?actualPage=0&itemsPerPage=3
  
  Resultado:

                                                            [
                                                              {
                                                                  "id": 1,
                                                                  "numeroNotaFiscal": "000000005",
                                                                  "numeroSerie": "1",
                                                                  "razaoSocial": "Juliana e Maria Filmagens Ltda",
                                                                  "cnpj": "23482453000105",
                                                                  "endereco": "Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo",
                                                                  "valor": 300.85
                                                              },
                                                              {
                                                                  "id": 2,
                                                                  "numeroNotaFiscal": "000000001",
                                                                  "numeroSerie": "1",
                                                                  "razaoSocial": "Manoel e Tânia Casa Noturna Ltda",
                                                                  "cnpj": "11473681000121",
                                                                  "endereco": "Rua da Linhaça, 156 - Jardim Belva - Carapicuíba - São Paulo",
                                                                  "valor": 2850.25
                                                              },
                                                              {
                                                                  "id": 3,
                                                                  "numeroNotaFiscal": "000000002",
                                                                  "numeroSerie": "1",
                                                                  "razaoSocial": "Manoel e Tânia Casa Noturna Ltda",
                                                                  "cnpj": "11473681000121",
                                                                  "endereco": "Rua da Linhaça, 156 - Jardim Belva - Carapicuíba - São Paulo",
                                                                  "valor": 145.24
                                                              }
                                                            ]
                                                             
# 2.4.1.2 GET - getNotaFilter

  Path: /nota
  
  Parâmetros: Nesta requisição, você pode passar os seguintes parâmetros: numNF (Número da Nota Fiscal), numSerie (Número da Série), razaoSocial, cnpj, endereco, valor (Double), actualPage e itemsPerPage. Contudo, os parâmetros não são obrigatórios, ou seja, você pode preferir não passar valor nenhum em alguns parâmetros. Por exemplo, você pode passar um CNPJ e Razão Social apenas e deixar os demais nulos. A API irá filtrar apenas pelos parâmetros que você passar. Da mesma forma que a requisição anterior, os parâemtros actualPage e itemsPerPage assumem o valor padrão de 0 e 10 caso não sejam inseridos.
  
  Resultado Esperado: Este método pode retornar apenas 1 Nota Fiscal ou várias dependendo dos critérios de busca. Caso não encontre nenhum registro, retornará um erro com status 404 de Notas Fiscais não encontradas.
  
  Exemplo de Requisição: http://localhost:8080/api/notas/nota?numNF=000000001&numSerie=2&razaoSocial&cnpj=23482453000105&endereco&valor&actualPage&itemsPerPage

  Nesta requisição acima, estamos filtrando apenas pelo numNF,numSerie e cnpj.

  Resultado:

                                                          [
                                                            {
                                                                "id": 10,
                                                                "numeroNotaFiscal": "000000001",
                                                                "numeroSerie": "2",
                                                                "razaoSocial": "Juliana e Maria Filmagens Ltda",
                                                                "cnpj": "23482453000105",
                                                                "endereco": "Rua Maria Siqueira, 313 - Jardim Silveira, Barueri - São Paulo",
                                                                "valor": 415.15
                                                            }
                                                        ]

# 2.4.2 Requisição POST

  Path: /insert
  
  Parâmetros: Esta requisição é uma inserção o Banco de Dados, cadastrando uma nova NFe. Para tanto, você deverá passar um body em JSON para realizar tal cadastro. Os campos a serem informados no body são: numeroSerie, cnpj, razaoSocial, endereco e valor. Todos devem ser preenchidos, caso contrário resultará em erro. O ID e o Número da Nota Fiscal são gerados automaticamente. O Número da Nota Fiscal é gerado de acordo com o Número de Série informado. Por exemplo, caso exista uma Nota Fiscal do tipo 000000001-1, uma nova inserção com o Número de Série 1 resultará no novo registro 000000002-1. No entanto, caso não exista nenhuma nota com o número de série 2 e uma nova inserção seja realizada com este número de série, o novo registro será de 000000001-2 e assim por diante.
  
              O numeroSerie não pode exceder 999.
              A razaoSocial não deve exceder a quantidade de 80 caracteres.
              O cnj não pode conter menos do que 14 caracteres e deve ser válido.
              O endereco deve conter no máximo 125 caracteres.
              O valor minimo a ser informado é de 0.00 e o máximo 999.999.999.
              
  Resultado Esperado: Uma mensagem informando que a Nota Fiscal foi inserida com sucesso. Qualquer erro de validação resultará num erro de Status 400 ou 500 caso sejam erros relacionados ao Banco de Dados.

  Exemplo de Requisição:
  
                                                    http://localhost:8080/api/notas/insert
                                                    body {
                                                      "numeroSerie":"2",
                                                      "cnpj":"23482453000105",
                                                      "razaoSocial":"Cassino Birulinha",
                                                      "endereco":"Rua José de Alencar, 125, Jardim Novo Horizonte - São Paulo, São Paulo",
                                                      "valor":100.25
                                                    }
    
  Resultado:
  
                                                    Nota Fiscal inserida com Sucesso!

# 2.4.3 Requisição PUT

  Path: /edit
  
  Parâmetros: Esta requisição PUT tem como objetivo editar uma nota fiscal em específico. Para tanto é necessário passar os seguintes parâmetros no body: id,numeroNotaFiscal,numeroSerie,cnpj,razaoSocial,endereco,valor. Por se tratar de uma API simples, não há uma lógica por trás para atualizar apenas os campos modificados, por tanto, é necessário informar todos os campos com os seus valores já cadastrados e alterar apenas aquilo que for necessário.
  
  Resultado esperado: Uma mensagem informando que a Nota Fiscal foi atualizada com sucesso. Qualquer erro de validação resultará num erro de Status 400 ou 500 caso sejam erros relacionados ao Banco de Dados.
  
  Exemplo de requisição:
  
                                                          http://localhost:8080/api/notas/edit
                                                          body {
                                                            "id":20,
                                                            "numeroNotaFiscal": "000000005",
                                                            "numeroSerie":"2",
                                                            "cnpj":"23482453000105",
                                                            "razaoSocial":"Birulinhas Cassino 2",
                                                            "endereco":"Rua José de Alencar ,125, Jardim Novo Horizonte - São Paulo, São Paulo",
                                                            "valor":500.25
                                                          }
    
  Resultado:
    
                                                          Nota Fiscal Editada com Sucesso!

# 2.4.4 Requisição DELETE

  Path: /delete
  
  Parâmetros: Esta requisição DELETE tem como propósito excluir uma Nota Fiscal do Banco de Dados. Portanto, é necessário passar os seguintes parâmetros: numNota (Número da Nota Fiscal) e numSerie (Número da Série). Desta forma, a Nota Fiscal com estes números será encontrada e excluída.
  
  Resultado esperado: Uma mensagem informando que a Nota Fiscal foi excluída com sucesso. Caso não encontre a Nota Fiscal e questão, será retornado um erro com status 404 informando que não foi possível achar a Nota Fiscal.
  
  Exemplo de requisição: http://localhost:8080/api/notas/delete?numNota=000000005&numSerie=3
  
  Resultado: Nota Fiscal excluída com sucesso!

# 3 Conclusão

  Este projeto relativamente simples possuí todos os recursos necessários para o CRUD (Create,Read,Update e Delete) relacionado à Notas Fiscais. Existem muitos espaços para melhorias que podem ser desenvolvidas futuramente. No entanto, este Manual instruí a como Configurar o Projeto e como Utilizá-lo para procedimentos básicos, mas com o intuíto de demonstrar minhas capacidades de Desenvolvimento.
