CREATE table customers (
  id              BIGINT,
  NAME            varchar(100),
  customerclass   varchar(1),
  created_at      timestamp,
  modified_at     timestamp,
  is_active       BOOLEAN
)
;