Projeto DimDimApp - Checkpoint 3 DevOps
Este projeto consiste na implementação de um ambiente conteinerizado para a aplicação "DimDim", atendendo aos requisitos de automação, isolamento e persistência de dados. A solução foi implantada em ambiente de nuvem (Microsoft Azure), utilizando Docker e Docker Compose.

1. Requisitos Técnicos Atendidos
O projeto atende aos seguintes requisitos obrigatórios solicitados:

Arquitetura de Containers: Implementação de dois containers distintos; um para a aplicação Spring Boot (com Dockerfile personalizado) e outro para o banco de dados MySQL (imagem oficial).

Persistência de Dados: Configuração de Volume Nomeado para garantir a persistência dos dados do banco de dados.

Segurança: A aplicação Java é executada utilizando um usuário não-root (jv-user), seguindo as melhores práticas de isolamento e segurança.

Rede: Os containers operam na mesma rede Docker (cp3_devops_network), garantindo a comunicação interna entre a aplicação e o banco.

Automação: O ambiente é orquestrado através do Docker Compose, permitindo a subida de todo o ecossistema com um único comando.

2. Instruções de Execução (How To)
Para rodar o projeto na máquina virtual (Azure), siga os passos abaixo a partir do terminal de acesso à VM:

Passo 1: Clonar o repositório
Acesse o diretório de sua preferência e clone o código fonte:

Bash
git clone https://github.com/joaovendrameto05/CP3_DEVOPS.git
cd CP3_DEVOPS
Passo 2: Executar o ambiente
Utilize o Docker Compose para realizar o build da imagem personalizada e subir os serviços em background:

Bash
sudo docker compose up -d --build
Passo 3: Aguardar inicialização
Aguarde aproximadamente 30 segundos para que o banco de dados MySQL finalize a criação das tabelas e a aplicação Java estabeleça a conexão com o banco.

3. Testes e Validação
Para validar o funcionamento da aplicação, utilize os comandos abaixo no terminal da VM:

Validação de Segurança (Usuário Não-Root)
Verifique se a aplicação está rodando com o usuário jv-user:

Bash
sudo docker container exec app-dimdim-563665 whoami
Verifique o diretório de trabalho:

Bash
sudo docker container exec app-dimdim-563665 pwd
Validação do CRUD (API REST)
Para testar a criação de um novo lançamento (POST):

Bash
curl -X POST http://localhost:8080/lancamentos \
-H "Content-Type: application/json" \
-d '{"descricao": "Teste CP3 Fiap", "valor": 1000.00}'
Para consultar os lançamentos salvos (GET):

Bash
curl -X GET http://localhost:8080/lancamentos
Validação de Persistência
Para provar que os dados são persistidos via Volume Nomeado:

Derrube o ambiente: sudo docker compose down

Suba o ambiente novamente: sudo docker compose up -d

Execute o curl de GET novamente. Os dados deverão retornar, comprovando a eficácia do volume.

Validação Direta no Banco de Dados
Para verificar o registro diretamente na tabela do MySQL:

Bash
sudo docker container exec -it db-mysql-563665 mysql -uroot -proot dimdim_db -e "SELECT * FROM lancamento;"
4. Notas Técnicas
A imagem da aplicação é personalizada via Dockerfile, utilizando a imagem eclipse-temurin:21-jdk para o runtime.

O arquivo docker-compose.yml define as dependências entre os serviços e a rede dedicada.

A persistência de dados ocorre através de volumes gerenciados pelo Docker, garantindo que o ciclo de vida dos containers não apague as informações do banco.
