# Coding assessment
Coding assessment - Pizza House - Submitted by Brian Lai (brianlaihkhk@gmail.com)

### Features

- Framework used : Hibernate orm, JPA Persistance framework + Criteria API, Spring-boot, Spring MVC, Spring Exception handler, slf4j, jax-rs, jjwt, jasypt (Hibernate & Spring-boot)
- JWT encrypt between services, pass by header
- Session token and user uuid pass by header for session authenication
- UUID for distributed system

### Folder

- Test Data : Data for testing in passing json
- PizzaService : Java module in handling user submittion data
- Documentation : Documentation
- Setup : Setup script for database initialization
- OrderConfirmationService : Java module in saving confirmed orders
- Common : Java module for common library
- Deploy : Deployment script for maven and docker

### Requirement

- Java is required as coding language
- A service should be created to generate a pizza order message with name, quantity and price (the values used do not matter) and send that message to the second service.
- A service should be created to receive that order message and store it in a database.
- The database does not need to be persistent, H2 can be used.
- Calls should be secured.
- Calls between services should use REST.
- Logging / documentation and testing are expected as part of the solution.

### Time limit

- 48 hours

### Contact
- Linkedin : https://www.linkedin.com/in/brianlaihkhk/
- brianlaihkhk@gmail.com
