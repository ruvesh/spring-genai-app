# GenAI Spring App

A GenAI application using Spring AI. User can ask generic questions to the AI, or use the recommendation APIs to ask for recommendations on Books. Other recommendations will be added in future releases.

### Configurations required
1. Create an *API Key* from *OpenAI*. Then, add the `OPEN_AI_API_KEY` to your environment variables or the `application.yml` directly. (Please do not commit the key as that is a security risk.)
2. Create a Mysql db: `spring-genai-app`, configure the `host:port`, `username` and `password` in `application.yml`.

### Deployment
- Run as spring-boot app locally, or deploy to any hosting service of choice
  - Version Requirements: Java - 17, Mysql - 8.x, SpringBoot - 3.4  
- context-path: /gen.ai
- port: 9402

### OpenAPI Swagger Conf
1. **API playground link:** {{baseURL}}/gen.ai/api-playground
<br/>*e.g.* localhost:9402/gen.ai/api-playground
2. **OpenAPI docs link:** {{baseURL}}/gen.ai/api-docs
<br/>*e.g.* localhost:9402/gen.ai/api-docs

---
## Powered By
* SpringBoot 3.4.1
* SpringAI 1.0.0-M4
* Java 17
* MySQL 8.0.31
* OpenAPI 3.0
* OpenAI Platform - https://platform.openai.com/docs/overview
---
   
