*Projeto DimDimApp - Checkpoint 3 DevOps*

- *1. Descrição do Projeto*
  
Este projeto compreende o desenvolvimento e a implantação de uma aplicação financeira denominada "DimDimApp". O sistema foi estruturado para atender aos requisitos de automação, isolamento de ambiente e persistência de dados. A arquitetura é baseada em microsserviços conteinerizados, composta por uma aplicação Java (Spring Boot) e um banco de dados relacional (MySQL), orquestrados via Docker Compose em ambiente de nuvem (Microsoft Azure).


-------------------------------------------------------------------------------------|||-------------------------------------------------------------------------------------


- *2. Tecnologias Utilizadas*

Linguagem: Java (Spring Boot 3.2.5).
Banco de Dados: MySQL 8.0.
Orquestração: Docker Compose.
Infraestrutura: Microsoft Azure (Máquina Virtual Ubuntu).
Versionamento: Git e GitHub.
Segurança: Execução de containers com usuário não-privilegiado (non-root).


-------------------------------------------------------------------------------------|||-------------------------------------------------------------------------------------


- *3. Estrutura do Repositório*
O repositório está organizado conforme padrões de desenvolvimento:

/src: Código-fonte da aplicação Java.
/Dockerfile: Instruções para build da imagem personalizada da aplicação.
/docker-compose.yml: Orquestração dos serviços (App e Banco) e configuração de volumes/redes.
/pom.xml: Gerenciador de dependências Maven.


-------------------------------------------------------------------------------------|||-------------------------------------------------------------------------------------


- *4. Guia de Execução*
Siga os passos abaixo para implantar a solução no ambiente de nuvem:

- Pré-requisitos
Acesso via SSH à máquina virtual Ubuntu.
Docker e Docker Compose instalados no ambiente de destino.
Acesso de rede configurado para permitir tráfego na porta 8080.

- Passo 1: Clonagem do Repositório
Acesse o terminal da máquina virtual e execute os comandos:

Bash
git clone https://github.com/joaovendrameto05/CP3_DEVOPS.git
cd CP3_DEVOPS

- Passo 2: Inicialização dos Containers
Suba os serviços definidos no Docker Compose em modo background:

Bash
sudo docker compose up -d --build

-Passo 3: Validação da Aplicação
Aguarde a inicialização dos serviços (aproximadamente 30 segundos) e valide a operação:
- A. Verificação de Segurança (Usuário Não-Root)
O container deve estar executando com o usuário definido no Dockerfile (jv-user):
Bash
sudo docker container exec app-dimdim-563665 whoami

- B. Operações de CRUD (Testes de API)
- Para registrar um novo lançamento (POST):
Bash
curl -X POST http://localhost:8080/lancamentos \
-H "Content-Type: application/json" \
-d '{"descricao": "Teste CP3 Fiap", "valor": 1000.00}'

- Para listar os registros (GET):
Bash
curl -X GET http://localhost:8080/lancamentos

- C. Validação de Persistência (Volume Nomeado)
Para garantir que os dados não são perdidos com a reinicialização dos containers:

Derrube o ambiente: sudo docker compose down
Suba o ambiente: sudo docker compose up -d
Consulte novamente: curl -X GET http://localhost:8080/lancamentos
O dado inserido deve persistir, validando o uso de Volumes Nomeados.

- D. Validação Direta no Banco de Dados
Para consultar diretamente a tabela no banco MySQL:
Bash
sudo docker container exec -it db-mysql-563665 mysql -uroot -proot dimdim_db -e "SELECT * FROM lancamento;"


-------------------------------------------------------------------------------------|||-------------------------------------------------------------------------------------


- 5. Considerações de Implantação
Segurança: A aplicação foi configurada para ser executada com o usuário jv-user, seguindo o princípio do privilégio mínimo dentro do ambiente conteinerizado.

Persistência: Foi utilizado um volume nomeado (vol-mysql-563665) para mapear o diretório de dados do MySQL (/var/lib/mysql), garantindo a integridade dos dados mesmo em caso de falha ou recriação dos containers.
Rede: Os containers estão isolados em uma rede bridge dedicada (cp3_devops_network), permitindo que a aplicação se comunique com o banco apenas através do nome do serviço.

-------------------------------------------------------------------------------------|||-------------------------------------------------------------------------------------


Integrantes
João Victor Vendrameto - 563665 2TDSPV
Gabriel Ambrósio Saraiva 566552 - 2TDSPV
