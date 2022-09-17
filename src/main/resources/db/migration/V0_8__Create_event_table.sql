create table if not exists "event"
(
    id_event                varchar
    constraint event_pk primary key default uuid_generate_v4(),
    event_name           varchar                  not null,
    event_responsible           varchar                  not null,
    constraint event_fk_user_id references "user"(id),
    event_descrciption        varchar       not null,
    place              varchar                 not null
    constraint event_fk_place_id references "place"(id),
    start_time              timestamp with time zone       not null,
    ending_time              timestamp with time zone                 not null
                                            );