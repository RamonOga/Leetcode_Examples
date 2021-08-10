delete from person
where id not in(select min(p.id)
from(select * from person) p
group by p.email);