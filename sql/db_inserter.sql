USE bsystem;

DELIMITER #
CREATE PROCEDURE insert_into_rates()
BEGIN
	DECLARE rates_count INT DEFAULT 5;
    DECLARE rate_counter INT DEFAULT 0;
    
    START TRANSACTION;
    WHILE rate_counter < rates_count DO
		INSERT INTO rates(rate) VALUES(CONCAT('rate', rate_counter));
        SET rate_counter = rate_counter + 1;
	END WHILE;
    COMMIT;
END #

call insert_into_rates();

DELIMITER #
CREATE PROCEDURE insert_into_clients()
BEGIN
	DECLARE clients_count INT DEFAULT 100;
    DECLARE client_counter INT DEFAULT 0;
    
    START TRANSACTION;
    WHILE client_counter < clients_count DO
		INSERT INTO clients(client_login, client_email, client_surname, client_balance, rate_id)
			VALUES
            (
				CONCAT('login', client_counter),
                CONCAT('login', client_counter, '@gmail.com'),
                CONCAT('surname', client_counter),
                FLOOR(RAND()*(999999-1+1)+1),
                FLOOR(RAND()*(5-1+1)+1)
            );
		SET client_counter = client_counter + 1;
	END WHILE;
    COMMIT;
END #

call insert_into_clients();



