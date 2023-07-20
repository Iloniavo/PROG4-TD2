-- Insérer des données dans la table "employee"
INSERT INTO employee (id, first_name, last_name, serial_number, birthday, pic_url, sex, location, pro_email, ps_email, function, social_category, dependent_children, cnaps_serial_number, hire_date, resignation_date)
VALUES
    (1, 'John', 'Doe', 'EMP123', '1990-05-15', null, 'H', 'Paris', 'john.doe@company.com', 'john.doe.personal@company.com', 'Software Engineer', 'M1', 0, '1234567890', '2023-07-01', '2024-07-01'),
    (2, 'Jane', 'Smith', 'EMP456', '1985-10-20', null, 'F', 'New York', 'jane.smith@company.com', NULL, 'Product Manager', 'M2', 2, '0987654321', '2022-12-15', '2023-12-15');

-- Insérer des données dans la table "identity_card"
INSERT INTO identity_card (id, number, employee_id)
VALUES
    (1, 'IC123456', 1),
    (2, 'IC789012', 2);

-- Insérer des données dans la table "phone_number"
INSERT INTO phone_number (id, number, owner)
VALUES
    (1, '1234567890', 1),
    (2, '9876543210', 2),
    (3, '5555555555', 2);