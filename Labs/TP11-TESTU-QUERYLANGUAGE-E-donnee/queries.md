### Sum of the saldo of all internal accounts;
```jpaql
select 
    sum(saldo)
from 
    InternalAccount
```

### Number of operations associated to the internal account that has id 33
```jpaql
select 
    ia.operations.size 
from 
    InternalAccount ia 
where 
    ia.id = 33
```

### Show the descriptions of internal accounts and external accounts
```jpaql
select 
    description 
from 
    Account
```

### Show the average amount of operations associated with all internal accounts of Tony Parker
```jpaql
select 
    avg(oper.amount) 
from 
    InternalAccount ia, 
    in(ia.owners) owner, 
    in(ia.operations) oper 
where 
    owner.lastname = 'PARKER' 
    and owner.firstname = 'TONY'
```