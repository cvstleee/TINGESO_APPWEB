INSERT INTO public.costumer (age, monthly_income, email, last_name, name, rut) 
VALUES (30, 5000, 'johndoe@example.com', 'Doe', 'John', '12345678-9');

INSERT INTO public.employee (email, first_name, last_name) 
VALUES ('jane.smith@example.com', 'Jane', 'Smith');

INSERT INTO public.credit_evaluation (antiquity, appropiate_age, historydicom, relationship_debt_income, relationship_fee_income, savings_capacity, costumer_id, credit_request_id, employee_id, status_evaluation) 
VALUES (true, true, false, true, false, true, 1, 1, 1, 'Pending');

INSERT INTO public.credit_request (administration_fee, credit_amount, deadline, fire_insurance, interest_rate_month, interest_rate_year, life_insurance, month_cost, month_debth, total_cost, costumer_id, credit_evaluation_id, employee_id, type) 
VALUES (1000, 20000, 12, 300, 1.5, 18.0, 200, 1500, 1200, 23000, 1, 1, 1, 'Primera Vivienda');



