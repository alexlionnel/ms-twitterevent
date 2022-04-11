create schema if not exists analytics authorization keycloak;

create table analytics.twitter_analytics
(
    id          uuid                                           not null,
    word        character varying collate pg_catalog."default" not null,
    word_count  bigint                                         not null,
    record_date time with time zone,
    constraint twitter_analytics_pkey primary key (id)
)
tablespace pg_default;

alter table analytics.twitter_analytics owner to keycloak;

create index indx_word_by_date on analytics.twitter_analytics using btree
    (
     word collate pg_catalog."default" asc nulls last,
     record_date desc nulls last
        )
    tablespace pg_default;