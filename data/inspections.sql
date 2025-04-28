--drop database "dojo.spring" with force;
create new database "dojo.spring" with owner 'postgres';

select pg_database_size('dojo.spring') as "Bytes", pg_size_pretty(pg_database_size('dojo.spring')) as "Readable";

select count(id)
from transactions as t;

select
    d.id,
    d.name,
    d.code,
    d.address
from
    departments as d;

select
    id,
    amount,
    department_id
from
    transactions as t;

select
    d."name",
    t.amount,
    d.address,
    d.code
from 
    transactions as t
inner join departments as d on
    t.department_id = d.id
where
    t.amount is not null
order by
    d."name" asc;