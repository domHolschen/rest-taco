delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;
insert into Ingredient (id, name, type)
                values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
                values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
                values ('BLCO', 'Blue Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
                values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('CHCK', 'Chicken', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('IMPB', 'Impossible Plant-based', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('FAJT', 'Fajita Veggies', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('JLPN', 'Jalapeno', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('PCKO', 'Pickled Onions', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
                values ('QSFR', 'Queso Fresco', 'CHEESE');
insert into Ingredient (id, name, type)
                values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
                values ('MISA', 'Mild Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
                values ('MESA', 'Medium Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
                values ('SLVR', 'Salsa Verde', 'SAUCE');
insert into Ingredient (id, name, type)
                values ('PCSL', 'Pico de Gallo', 'SAUCE');
insert into Ingredient (id, name, type)
                values ('RTSA', 'Roasted Tomatillo', 'SAUCE');
insert into Ingredient (id, name, type)
                values ('SRCR', 'Sour Cream', 'SAUCE');