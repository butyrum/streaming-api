Breno, o projeto está no ar! O seu repositório streaming-api já reflete o esforço de ter superado erros complexos de persistência e regras de negócio.

Como seu conselheiro, preparei um README profissional que destaca não apenas o código, mas a sua capacidade de resolver problemas reais de arquitetura (como o erro de Detached Entity e a exclusão em cascata).

Copie o conteúdo abaixo para o seu arquivo README.md:

🎬 Streaming API - Case de Gestão de Assinaturas e Conteúdo
Esta API foi desenvolvida como parte de um projeto de backend focado em regras de negócio complexas, integridade de dados e segurança de conteúdo. O sistema gerencia assinantes, seus dependentes e o consumo de mídia baseado em perfis etários e planos de assinatura.

🚀 Desafios Técnicos Superados
Durante o desenvolvimento, foram aplicadas soluções para problemas críticos de persistência e lógica:

Gestão de Estados JPA: Resolução de erros de Detached Entity ao persistir novos dependentes, garantindo o vínculo correto com o assinante pai.

Integridade Referencial: Implementação de Exclusão em Cascata via Hibernate (@OnDelete), garantindo que a remoção de um assinante limpe automaticamente todos os dependentes órfãos no banco de dados.

Automatização de Perfis: Criação de lógica interna para atribuição automática de perfis (INFANTIL, ADOLESCENTE, ADULTO) baseada exclusivamente na idade do usuário.

🛠️ Regras de Negócio Implementadas
O sistema foi blindado para seguir as diretrizes rigorosas do modelo de negócio:

Limites por Plano: * BASIC: 0 dependentes.

STANDARD: Até 2 dependentes.

PREMIUM: Até 4 dependentes. O sistema valida e bloqueia tentativas de cadastro que excedam esses limites.

Segurança de Conteúdo: Bloqueio automático de visualização caso a classificação etária do filme seja superior à permitida pelo perfil do usuário.

Auditoria de Consumo: Registro detalhado de histórico de visualização com data, hora e metadados do filme.

🧪 Como Testar
A API expõe endpoints para gestão completa:

POST /usuarios: Cadastro de assinantes (+18 anos) e dependentes.

POST /streaming/{idUsuario}/assistir/{idFilme}: Simulação de visualização com validação etária.

GET /streaming/{idUsuario}/historico: Consulta de histórico de consumo.

💻 Tecnologias
Java 17+

Spring Boot 3

Spring Data JPA

H2 Database

Lombok
