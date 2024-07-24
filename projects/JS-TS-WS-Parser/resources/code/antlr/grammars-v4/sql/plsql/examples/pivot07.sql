select *
from   (select customer_id, product_code, quantity
        from   pivot_test)
pivot  (sum(quantity) as sum_quantity for (product_code) in ('a' as a, 'b' as b, 'c' as c))
order by customer_id
