SELECT sum(price) as price
from   customers c
  join purchases p
on c.id = p.customer_id
where customerclass = 'A'
;