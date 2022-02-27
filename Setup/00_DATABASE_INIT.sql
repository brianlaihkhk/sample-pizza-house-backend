CREATE DATABASE PIZZA_HOUSE;

CREATE USER 'pizzaservice'@'localhost' IDENTIFIED BY 'BlueMonday#';
CREATE USER 'confirmationservice'@'localhost' IDENTIFIED BY 'HelloWorld!';

GRANT SELECT, INSERT, DELETE, UPDATE, EXECUTE ON PIZZA_HOUSE.* TO 'pizzaservice'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE, EXECUTE ON PIZZA_HOUSE.* TO 'confirmationservice'@'localhost';

FLUSH PRIVILEGES;