
# Sistema de Gestão de Medições

Este projeto é uma aplicação de gestão de medições em obras, permitindo o controle de medições por obras, bacias e itens. O sistema implementa autenticação baseada em JWT para garantir o controle de acesso.

## Funcionalidades

- Gerenciamento de obras, bacias e itens.
- Controle de medições com datas e competências.
- Autenticação com JWT (JSON Web Token).
- Permissões de acesso baseadas em papéis de usuários (ADMIN, COORDENADOR, ENGENHEIRO, TÉCNICO, AUDITOR).

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.3
- Spring Security
- Spring Data JPA
- JWT para autenticação
- Banco de dados relacional com JPA/Hibernate

## Configuração do Projeto

### Dependências Necessárias

Adicione as seguintes dependências ao arquivo `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot, Web, JPA, Security -->
    <dependency>...</dependency>
    <!-- JWT -->
    <dependency>...</dependency>
    <!-- Lombok -->
    <dependency>...</dependency>
</dependencies>
```

### Estrutura do Projeto

O projeto está estruturado em pacotes que seguem a arquitetura MVC:

- `controller` - Controladores REST
- `dto` - Objetos de Transferência de Dados
- `model` - Entidades JPA
- `repository` - Repositórios Spring Data JPA
- `service` - Lógica de negócio e regras
- `security` - Classes para autenticação e autorização
- `config` - Configurações de segurança

---

## Configurações

### AppConfig.java

Define configurações como `RestTemplate` e `WebClient`.

```java
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

---

## Controllers

Os controladores são responsáveis por expor os endpoints REST.

### HomeController.java

Controla a página principal da aplicação:

```java
@GetMapping("/")
public String home() {
    return "redirect:/index.html";
}
```

### PessoaController.java

Controla o cadastro, atualização e exclusão de pessoas:

```java
@PostMapping("/create")
public ResponseEntity<?> createPessoa(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
    personService.createPerson(personRequestDTO);
    return ResponseEntity.ok().build();
}
```

### ObraController.java

Gerencia as obras, com operações de criação e consulta:

```java
@PostMapping("/create")
public ResponseEntity<?> criarObra(@Valid @RequestBody ObraDTO obraDTO) {
    obraService.criarObra(obraDTO);
    return ResponseEntity.ok(obraDTO);
}
```

### BaciaController.java

Gerencia as bacias de uma obra:

```java
@PostMapping("/create")
public ResponseEntity<?> criarBacia(@Valid @RequestBody BaciaDTO baciaDTO) {
    baciaService.criarBacia(baciaDTO);
    return ResponseEntity.ok(baciaDTO);
}
```

---

## Data Transfer Objects (DTOs)

Os DTOs transferem dados entre o cliente e o servidor.

### PersonRequestDTO.java

DTO para criação de uma pessoa:

```java
@NotBlank(message = "O nome não pode ser nulo")
private String name;
```

### MedicaoDTO.java

Representa os dados de uma medição:

```java
@NotNull
private LocalDate dataInicio;
```

---

## Repositórios

Os repositórios são interfaces que lidam com a persistência dos dados.

### PersonRepository.java

```java
Optional<Person> findByEmail(String email);
void deleteByEmail(String email);
```

### MedicaoRepository.java

```java
boolean existsByContratoObraAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(String contratoObra, LocalDate dataFim, LocalDate dataInicio);
```

---

## Serviços

Os serviços encapsulam a lógica de negócio.

### PersonService.java

```java
@Transactional
public ResponseEntity<?> createPerson(@Valid PersonRequestDTO personRequestDTO) {
    // Lógica para criar uma pessoa
}
```

### MedicaoService.java

```java
@Transactional
public MedicaoDTO createMedicao(MedicaoDTO medicaoDTO) {
    // Lógica para criar uma medição
}
```

---

## Segurança e Autenticação

### SecurityConfig.java

Configura o Spring Security com JWT:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // Configuração de segurança
}
```

### JwtTokenProvider.java

Gera e valida os tokens JWT:

```java
public String generateToken(String username, Role role) {
    // Gera o token JWT
}
```

### JwtAuthenticationFilter.java

Filtra as requisições para validar o token JWT:

```java
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
    // Lógica para extrair e validar o token JWT
}
```

### AuthController.java

Controla o processo de login e geração de token JWT:

```java
@PostMapping("/login")
public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
    // Autenticação e geração de token
}
```

---

## Executando o Projeto

1. Clone o repositório.
2. Configure o banco de dados no arquivo `application.properties`.
3. Compile e execute a aplicação usando o Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Acesse a aplicação no navegador via `http://localhost:8080`.

---

## Licença

Este projeto está licenciado sob os termos da licença MIT.
