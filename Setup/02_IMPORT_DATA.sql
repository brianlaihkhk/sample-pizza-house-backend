INSERT INTO USER (USER_NAME, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('brian', 'Lai', 'Brian', ''); -- Password = Hello
INSERT INTO USER (USER_NAME, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('janet', 'Chan', 'Janet', ''); -- Password = World
INSERT INTO USER (USER_NAME, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('Anna', 'Wong', 'Anna', ''); -- Password = Apple
INSERT INTO USER (USER_NAME, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('peter', 'Lee', 'Peter', ''); -- Password = Juice


INSERT INTO PIZZA_TYPE (NAME, DESCRIPTION)
VALUES ('Neapolitan Pizza', 'The true original, topped with sauce from fresh tomatoes olive oil and minimal mozzarella.'); 
INSERT INTO PIZZA_TYPE (NAME, DESCRIPTION)
VALUES ('Chicago Pizza', 'Prepared as the thick, classic deep-dish pizza. Thin-medium crust containing cornmeal or semolina.'); 
INSERT INTO PIZZA_TYPE (NAME, DESCRIPTION)
VALUES ('New-York Pizza', 'Large, foldable slices, crispy outer crust. Traditional toppings of tomato sauce and mozzarella cheese.'); 
INSERT INTO PIZZA_TYPE (NAME, DESCRIPTION)
VALUES ('California Pizza', 'A single-serving, thin crust pizza. Popular due to its creative, non-traditional toppings, like chicken, egg, artichokes, salmon, feta or goat cheese.'); 

INSERT INTO PIZZA_SIZE (NAME, DESCRIPTION, PRICE)
VALUES ('Round - Regular', 'Round - Regular Size', 99); 
INSERT INTO PIZZA_SIZE (NAME, DESCRIPTION, PRICE)
VALUES ('Round - Large', 'Round - Large Size', 129); 
INSERT INTO PIZZA_SIZE (NAME, DESCRIPTION, PRICE)
VALUES ('Square - Regular', 'Square - Regular Size', 119); 
INSERT INTO PIZZA_SIZE (NAME, DESCRIPTION, PRICE)
VALUES ('Square - Large', 'Square - Large Size', 149); 

INSERT INTO PIZZA_TOPPING (NAME, DESCRIPTION, PRICE)
VALUES ('Chicken', 'Chicken topping', 15); 
INSERT INTO PIZZA_TOPPING (NAME, DESCRIPTION, PRICE)
VALUES ('Egg', 'Egg topping', 15); 
INSERT INTO PIZZA_TOPPING (NAME, DESCRIPTION, PRICE)
VALUES ('Goat Cheese', 'Goat Cheese topping', 25); 
INSERT INTO PIZZA_TOPPING (NAME, DESCRIPTION, PRICE)
VALUES ('Salmon', 'Salmon topping', 25); 
INSERT INTO PIZZA_TOPPING (NAME, DESCRIPTION, PRICE)
VALUES ('Beef', 'Beef topping', 20); 