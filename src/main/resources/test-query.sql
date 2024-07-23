-- Entity Graph or Join Fetch

explain analyze
select
    c1_0.id,
    c2_0.company_id,
    c2_0.id,
    c2_0.lang,
    c3_0.id,
    c3_0.code,
    u1_0.company_id,
    u1_1.id,
    u1_1.first_name
from
    company c1_0
left join
    company_name c2_0
        on c1_0.id=c2_0.company_id
left join
    country c3_0
        on c3_0.id=c1_0.country_id
left join
    (user_companies u1_0
join
    users u1_1
        on u1_1.id=u1_0.user_id)
            on c1_0.id=u1_0.company_id
where c1_0.id = '37140dc9-6ab4-450d-8757-38e2e708d760';
