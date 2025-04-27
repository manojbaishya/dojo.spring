select department_id,
       department_name,
       department_code,
       department_address
from departments;

delete
from departments
where department_id is not null;
