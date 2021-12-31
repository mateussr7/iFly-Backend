SELECT *, CASE
           WHEN p.id is not null then 'passageiro'
           WHEN ea.id is not null then 'empresa aerea'
           WHEN a.id is not null then 'admin'
           end as tipo
FROM usuario u
         LEFT JOIN passageiro p ON p.id = u.id
         LEFT JOIN empresa_aerea ea ON ea.id = u.id
         LEFT JOIN administrador a ON a.id = u.id