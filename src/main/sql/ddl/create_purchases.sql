create table purchases(
  id            bigint,
  customer_id   bigint,
  price         decimal (8,2),
  item_name     varchar(50),
  purchased_at  timestamp,
  paid_at       timestamp
)
;