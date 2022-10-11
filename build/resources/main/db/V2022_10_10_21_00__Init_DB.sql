create table public."category"(
    id serial primary key not null,
    title varchar(50)
    "source" text;
);

create unique index category_id_uindex on public."category"(id);

create table public.menu(
    id serial primary key not null,
    title varchar(50),
    price int,
    is_available boolean,
    description text,
    mass int,
    image_source text,
    category_id int,
    constraint menu_category_id_fk foreign key (category_id) references "category"(id)
);

create unique index menu_id_uindex on public.menu(id);

create table public."table"(
    id serial primary key not null,
    is_free boolean
);

create unique index table_id_uindex on public."table"(id);

create table public."order"(
    id serial primary key not null,
    client text,
    menu_id int ,
    constraint order_menu_id_fk foreign key (menu_id) references menu(id);,
    table_id int,
    constraint order_table_id_fk foreign key (table_id) references "table"(id);
);

create unique index order_id_uindex on public."order"(id);