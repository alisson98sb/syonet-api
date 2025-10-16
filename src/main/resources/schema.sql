-- USERS
create table if not exists users (
    id            bigserial primary key,
    name          varchar(120) not null,
    email         varchar(200) not null unique,
    password_hash varchar(255) not null,
    created_at    timestamp not null default now()
);

-- ITEMS
create table if not exists items (
    id         bigserial primary key,
    name       varchar(200) not null,
    sku        varchar(100),
    quantity   integer not null default 0,
    created_at timestamp not null default now(),
    unique (sku)
);

-- MOVEMENTS
create table if not exists movements (
    id             bigserial primary key,
    item_id        bigint not null references items(id) on delete cascade,
    type           varchar(8) not null check (type in ('IN','OUT')),
    amount         integer not null check (amount > 0),
    balance_after  integer not null,
    user_id        bigint,
    note           varchar(255),
    created_at     timestamp not null default now()
);

create index if not exists idx_items_name on items(name);
create index if not exists idx_mov_item_created on movements(item_id, created_at desc);
