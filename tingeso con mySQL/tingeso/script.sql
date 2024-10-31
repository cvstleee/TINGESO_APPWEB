-- Insertar en costumer
INSERT INTO prestabancodb.costumer (id, age, email, last_name, monthly_income, name, rut) VALUES (1, 30, 'email1@example.com', 'Doe', 3000, 'John', '12345678-9');

-- Insertar en employee
INSERT INTO prestabancodb.employee (id, email, first_name, last_name) VALUES (1, 'employee1@example.com', 'Emily', 'Davis');

-- Insertar en credit_request
INSERT INTO prestabancodb.credit_request (id, administration_fee, credit_amount, deadline, doc_requirements, fire_insurance, interest_rate_month, interest_rate_year, life_insurance, max_amount, month_cost, month_debth, total_cost, type, costumer_id) VALUES (1, 1500, 20000, 12, 'ID required', 300, 5, 60, 200, 1000, 1500, 100, 2100, 'personal', 1);

-- Insertar en credit_evaluation
INSERT INTO prestabancodb.credit_evaluation (id , antiquity , historydicom , relationship_debt_income , relationship_fee_income , savings_capacity , status_evaluation , costumer_id , credit_request_id , employee_id) VALUES (1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1);

-- Insertar en saving_account
INSERT INTO prestabancodb.saving_account (id , antiquity_balance , balance_required , deposits , history , retiraments , status_saving_capacity,costumer_id) VALUES(1 ,1 ,1000 ,1 ,1 ,0 ,1,NULL);

-- Insertar en costumer
INSERT INTO prestabancodb.costumer (id, age, email, last_name, monthly_income, name, rut) VALUES
                                                                                              (4, 30, 'email1@example.com', 'Doe', 3000, 'John', '12345678-9'),
                                                                                              (2, 25, 'email2@example.com', 'Smith', 2500, 'Jane', '98765432-1'),
                                                                                              (3, 40, 'email3@example.com', 'Johnson', 5000, 'Alice', '13579246-3');

-- Insertar en employee
INSERT INTO prestabancodb.employee (id, email, first_name, last_name) VALUES
                                                                          (4, 'employee1@example.com', 'Emily', 'Davis'),
                                                                          (2, 'employee2@example.com', 'Michael', 'Brown'),
                                                                          (3, 'employee3@example.com', 'Chris', 'Garcia');

-- Insertar en credit_request
INSERT INTO prestabancodb.credit_request (id, administration_fee, credit_amount, deadline, doc_requirements, fire_insurance, interest_rate_month, interest_rate_year, life_insurance, max_amount, month_cost, month_debth, total_cost, type, costumer_id) VALUES
                                                                                                                                                                                                                                                              (4, 1500, 20000, 12, 'ID required', 300, 5, 60, 200, 1000, 1500, 100, 2100, 'personal', 4),
                                                                                                                                                                                                                                                              (2, 2000, 10000, 24, 'ID required', 400, 6, 72, 300, 2000, 500, 200, 4500,'home', 2),
                                                                                                                                                                                                                                                              (3, 2500, 15000, 36, 'ID required', 500, 4.5, 54, 250, 1500, 600, 300 ,5500,'auto',3);

-- Insertar en credit_evaluation
INSERT INTO prestabancodb.credit_evaluation (id , antiquity , historydicom , relationship_debt_income , relationship_fee_income , savings_capacity , status_evaluation , costumer_id , credit_request_id , employee_id) VALUES
                                                                                                                                                                                                                            (4 , b'1' , b'1' , b'1' , b'1' , b'1' , b'1' , 4 , 4 , 4),
                                                                                                                                                                                                                            (2 , b'0' , b'1' , b'0' , b'1' , b'1' , b'0' , 2 , 2 , 2),
                                                                                                                                                                                                                            (3 , b'1' , b'0' , b'1' , b'1' , b'0' , b'1' , 3 , NULL , NULL);

-- Insertar en document
INSERT INTO prestabancodb.document (id,title,type,costumer_id,Credit_request_id) VALUES
                                                                                     (1,'Document A','Type A',4,NULL),
                                                                                     (2,'Document B','Type B',2,NULL),
                                                                                     (3,'Document C','Type C',3,NULL);

-- Insertar en saving_account
INSERT INTO prestabancodb.saving_account (id , antiquity_balance , balance_required , deposits , history , retiraments , status_saving_capacity,costumer_id) VALUES
                                                                                                                                                                 (4,b'1',1000,b'1',b'1',b'0',b'1',4),
                                                                                                                                                                 (2,b'0',1500,b'0',b'1',b'0',b'0',2),
                                                                                                                                                                 (3,b'1',2000,b'1',b'0',b'1',b'1',3);