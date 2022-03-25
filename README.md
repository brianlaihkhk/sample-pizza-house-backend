# Coding sample
Coding sample - Pizza House - Submitted by Brian Lai

### Features

- JWT encrypt between services, pass by Authorization header
- Session token and user uuid pass by header for session authenication
- UUID for distributed system
- Build and docker script for containerization

### Framework

- Hibernate orm
- JPA Persistance framework
- JPA Criteria API
- Spring-boot
- Spring Exception handler
- slf4j
- jax-rs
- jjwt
- jasypt (Hibernate & Spring-boot)

### Folder

- Test Data : Data for testing in passing json
- PizzaService : Java module in handling user submittion data
- Documentation : Documentation
- Setup : Setup script for database initialization
- OrderConfirmationService : Java module in saving confirmed orders
- Common : Java module for common library
- Deploy : Deployment script for maven and docker

### Technical Assessment Requirement

- Java is required as coding language
- A service should be created to generate a pizza order message with name, quantity and price (the values used do not matter) and send that message to the second service.
- A service should be created to receive that order message and store it in a database.
- The database does not need to be persistent, H2 can be used.
- Calls should be secured.
- Calls between services should use REST.
- Logging / documentation and testing are expected as part of the solution.


### Prerequsite

- For more details please refer to Documentation folder

- [AWS solution]
   - AWS IAM (Permission setup)
   - AWS VPC (Network connection capability)
   - AWS RDS MySQL (or equivalent)
   - AWS ECS / EKS (For container orchestration)
   - AWS-cli or AWS CloudFormation
   - Maven 

- [non-AWS solution]
   - Tomcat / Weblogic (non-container deployment)
   - MySQL (or other SQL database)
   - Maven
   - (Optional) Docker
   - (Optional) Docker swarm, Kubernetes or equivalent (For container orchestration) 

### Time limit

- 48 hours

### Contact
- Linkedin : https://linkedin.com/in/brianlaihkhk/
- Github : https://github.com/brianlaihkhk/
