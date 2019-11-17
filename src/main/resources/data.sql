INSERT INTO CUSTOMER (first_name, last_name)
VALUES ('carlos', 'estorta');

INSERT INTO ADDRESS (city, state, street_name, street_number, zip, customer_id)
VALUES ('new york', 'DC','little to','44','19856',1);

INSERT INTO ACCOUNT (account_type, balance, customer_id, nickname, rewards)
VALUES ('SAVINGS', 5468.32,1,'shiny pennies',99999);

INSERT INTO DEPOSIT ( amount, description, medium, payee_id, status, transaction_date, type)
VALUES ( 600, 'stripper bills', 'BALANCE', 1, 'COMPLETED', '12/24/1989', 'DEPOSIT');

INSERT INTO WITHDRAWAL ( amount, description, medium, payer_id, status, transaction_date, type)
VALUES ( 50, 'corner fees', 'BALANCE', 1, 'PENDING', '02/09/1979', 'WITHDRAWAL');

INSERT INTO BILL (account_id, creation_date, nickname, payee, payment_amount, recurring_date, status,upcoming_payment_date, payment_date)
VALUES (1, '07/10/2018', 'pimp fees', 'Bunny Services LLC', 900, '10/20/2050', 'Recurring', '12/10/2019', '11/10/2019');
