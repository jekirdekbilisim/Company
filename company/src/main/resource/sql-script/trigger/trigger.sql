DROP TRIGGER IF EXISTS before_insert_company;

DELIMITER //
CREATE TRIGGER before_insert_company
    BEFORE INSERT ON company 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_company;

DELIMITER //
CREATE TRIGGER before_update_company
    BEFORE UPDATE ON company 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_country;

DELIMITER //
CREATE TRIGGER before_insert_country
    BEFORE INSERT ON country 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_country;

DELIMITER //
CREATE TRIGGER before_update_country
    BEFORE UPDATE ON country 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_director;

DELIMITER //
CREATE TRIGGER before_insert_director
    BEFORE INSERT ON director 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_director;

DELIMITER //
CREATE TRIGGER before_update_director
    BEFORE UPDATE ON director 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_document_meta_data;

DELIMITER //
CREATE TRIGGER before_insert_document_meta_data
    BEFORE INSERT ON document_meta_data 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_document_meta_data;

DELIMITER //
CREATE TRIGGER before_update_document_meta_data
    BEFORE UPDATE ON document_meta_data 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_document_type;

DELIMITER //
CREATE TRIGGER before_insert_document_type
    BEFORE INSERT ON document_type 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_document_type;

DELIMITER //
CREATE TRIGGER before_update_document_type
    BEFORE UPDATE ON document_type 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_inspector;

DELIMITER //
CREATE TRIGGER before_insert_inspector
    BEFORE INSERT ON inspector 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_inspector;

DELIMITER //
CREATE TRIGGER before_update_inspector
    BEFORE UPDATE ON inspector 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_menu;

DELIMITER //
CREATE TRIGGER before_insert_menu
    BEFORE INSERT ON menu 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_menu;

DELIMITER //
CREATE TRIGGER before_update_menu
    BEFORE UPDATE ON menu 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_meta_data;

DELIMITER //
CREATE TRIGGER before_insert_meta_data
    BEFORE INSERT ON meta_data 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_meta_data;

DELIMITER //
CREATE TRIGGER before_update_meta_data
    BEFORE UPDATE ON meta_data 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_privilege;

DELIMITER //
CREATE TRIGGER before_insert_privilege
    BEFORE INSERT ON privilege 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_privilege;

DELIMITER //
CREATE TRIGGER before_update_privilege
    BEFORE UPDATE ON privilege 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_role;

DELIMITER //
CREATE TRIGGER before_insert_role
    BEFORE INSERT ON role 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_role;

DELIMITER //
CREATE TRIGGER before_update_role
    BEFORE UPDATE ON role 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_role_privilege;

DELIMITER //
CREATE TRIGGER before_insert_role_privilege
    BEFORE INSERT ON role_privilege 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_role_privilege;

DELIMITER //
CREATE TRIGGER before_update_role_privilege
    BEFORE UPDATE ON role_privilege 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_user;

DELIMITER //
CREATE TRIGGER before_insert_user
    BEFORE INSERT ON user 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_user;

DELIMITER //
CREATE TRIGGER before_update_user
    BEFORE UPDATE ON user 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_user_role;

DELIMITER //
CREATE TRIGGER before_insert_user_role
    BEFORE INSERT ON user_role 
	FOR EACH ROW
    BEGIN
		IF NEW.create_date is null THEN         
			SET NEW.create_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;

DROP TRIGGER IF EXISTS before_update_user_role;

DELIMITER //
CREATE TRIGGER before_update_user_role
    BEFORE UPDATE ON user_role 
	FOR EACH ROW
    BEGIN
		IF NEW.last_update_date is null THEN         
			SET NEW.last_update_date = now();
        END IF;
	IF NEW.status is null THEN         
			SET NEW.status = 'A';
        END IF;
    END//
DELIMITER ;
