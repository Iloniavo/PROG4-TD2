-- Insérer des données dans la table "employee"
INSERT INTO employee (first_name, last_name, serial_number, birthday, pic_url, sex, location, pro_email, ps_email, function, social_category, dependent_children, cnaps_serial_number, hire_date, resignation_date, identity_card_id, identity_card_del_place, identity_card_del_date)
VALUES
    ('John', 'Doe', 'EMP123', '1990-05-15', null, 'HOMME', 'Paris', 'john.doe@company.com', 'john.doe.personal@company.com', 'Software Engineer', 'M1', 0, '1234567890', '2023-07-01', '2024-07-01', '123456789', 'Tana', '2023-07-01' ),
    ('Jane', 'Smith', 'EMP456', '1985-10-20', null, 'FEMME', 'New York', 'jane.smith@company.com', NULL, 'Product Manager', 'M2', 2, '0987654321', '2022-12-15', '2023-12-15', '987654321', 'Tana', '2013-07-04');


-- Insérer des données dans la table "phone_number"
INSERT INTO phone_number (number, owner_id)
VALUES
    ('1234567890', 1),
    ('9876543210', 2),
    ('5555555555', 2);
