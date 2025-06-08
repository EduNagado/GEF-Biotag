# GEF-Biotag

## 📋 Sobre o Projeto

O **GEF Biotag** é uma solução tecnológica pensada para responder de forma eficiente a situações de emergência e desastres naturais, como enchentes, deslizamentos de terra ou apagões em larga escala. Seu principal objetivo é oferecer um sistema integrado para a identificação, monitoramento e agrupamento de pessoas em áreas de risco, com foco na organização de abrigos e no apoio à gestão de crises.

A proposta gira em torno da distribuição de pulseiras inteligentes equipadas com sensores biométricos (como batimentos cardíacos), que são entregues preventivamente a moradores de regiões com histórico ou previsão de desastres. Essas pulseiras se conectam a dispositivos móveis ou pontos de coleta, permitindo que equipes de resgate, voluntários e autoridades monitorem sinais vitais básicos dos usuários em tempo real ou em sincronização posterior.

Além de auxiliar na triagem de saúde — priorizando pessoas com sinais de alerta ou condições especiais — a plataforma GEF Biotag também possibilita o cadastro individual dos cidadãos, associando-os a seus familiares ou grupos de convívio, facilitando o reencontro em abrigos e o acompanhamento em situações de deslocamento ou evacuação.

O sistema é composto por uma API REST central, responsável pelo registro e gerenciamento dos dados, uma interface de administração para visualização dos abrigos e do status das pessoas atendidas, além de um aplicativo mobile voltado para voluntários e equipes de campo.

Em resumo, o GEF Biotag transforma tecnologia em cuidado humanitário, oferecendo uma forma estruturada, segura e eficiente de lidar com as consequências sociais e logísticas de eventos extremos.

## 📦 Estrutura do Projeto
```
gef-biotag/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── gef_biotag/
│                   └── Biotag/
│                       ├── config/
│                       │   ├── CorsConfig.java
│                       │   ├── SecurityConfiguration.java
│                       │   ├── SecurityFilter.java
│                       │   └── SwaggerConfig.java
│                       │
│                       ├── controller/
│                       │   ├── AbrigoController.java
│                       │   ├── FuncionarioController.java
│                       │   └── PacienteController.java
│                       │
│                       ├── dto/
│                       │   ├── AbrigoDTO.java
│                       │   ├── BatimentoCardiacoDTO.java
│                       │   ├── DadosAtualizadoFuncionario.java
│                       │   ├── DadosAtualizadoPaciente.java
│                       │   ├── DadosDetalhadoAbrigo.java
│                       │   ├── DadosDetalhadoFuncionario.java
│                       │   ├── DadosDetalhadoPaciente.java
│                       │   ├── DadosDetalhandoPulseira.java
│                       │   ├── DadosListandoPaciente.java
│                       │   ├── DadosTokenJWT.java
│                       │   ├── DetalhesPulseiraDTO.java
│                       │   ├── FuncionarioDTO.java
│                       │   ├── LoginDTO.java
│                       │   ├── NfcDTO.java
│                       │   ├── PacienteDTO.java
│                       │   └── PulseiraDTO.java
│                       │
│                       ├── model/
│                       │   ├── Abrigo.java
│                       │   ├── BatimentoCardiaco.java
│                       │   ├── Cargo.java
│                       │   ├── Funcionario.java
│                       │   ├── NFC.java
│                       │   ├── Paciente.java
│                       │   └── Pulseira.java
│                       │
│                       ├── repository/
│                       │   ├── AbrigoRepository.java
│                       │   ├── FuncionarioRepository.java
│                       │   ├── PacienteRepository.java
│                       │   └── PulseiraRepository.java
│                       │
│                       ├── service/
│                       │   ├── AutenticacaoService.java
│                       │   ├── PulseiraService.java
│                       │   └── TokenService.java
│                       │
│                       └── BiotagApplication.java
│
├── resources/
│   └── application.properties
│
└── test/
```
## 🚀 Como Executar o Projeto 


###  Clone o Repositório
```bash
git clone https://github.com/EduNagado/GEF-Biotag.git
```
###  URL
```http
 http://localhost:8080/swagger-ui/index.html#/
```

## ☁️ Como Executar o Container

### Execute 
```bash
sudo docker pull edunagado/biotag:latest
```
## 📊 Endpoints da API

### 👥 Paciente 
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/paciente?page=0&size=10&sort=nome%2CASC` | Lista todos os paciente com paginação e filtro |
| GET | `/paciente/{id}` | Busca paciente por ID |
| POST | `paciente` | Cria novo paciente |
| PUT | `/paciente/{id}` | Atualiza paciente |
| DELETE | `/paciente{id}` | Remove paciente |

### 🏠 Abrigos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `abrigos` | Lista todos os abrigos |
| POST | `abrigos` | Cria novo abrigo |


### 👥 Funcionario 
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `funcionario` | Cria novo funcionario |
| POST | `/funcionario/login` | Fazer login com o funcionario |
| PUT | `/funcionario/{id}` | Atualiza funcionario |
| DELETE | `/funcionario{id}` | Remove funcionario |


## 🌟 Funcionalidades Inovadoras

### 1. **Monitoramento Proativo**
- Coleta automática de dados vitais via pulseiras
- Alertas em tempo real para situações críticas
- Histórico de monitoramento para análise médica

### 2. **Gestão Inteligente de Recursos**
- Controle de capacidade dos abrigos
- Alocação otimizada de usuários
- Rastreamento de ocupação em tempo real

### 3. **Diferentes Perfis de Usuário**
- **ADMINISTRADDOR**: Gestão completa do sistema
- **FUNCIONARIO**: Monitoramento médico, Cadastro de pacientes
- **Voluntario**: Usuários designado para o Abrigo


## 🚀 Roadmap Futuro

- [ ] **Notificações Push** para alertas críticos
- [ ] **Dashboard em tempo real** com métricas
- [ ] **Integração IoT** com mais dispositivos
- [ ] **Geolocalização** dos abrigos e usuários
- [ ] **Machine Learning** para predição de emergências
- [ ] **API de terceiros** para dados meteorológicos
      
## 📞 Contato

**Desenvolvedor**: Eduardo Nagado
**GitHub**: [EduNagado](https://github.com/EduNagado)  
**Projeto**: [GEF-BioTag](https://github.com/EduNagado/GEF-Biotag)

---

⚡ **GEF - Conectando tecnologia e humanidade em momentos críticos** ⚡

