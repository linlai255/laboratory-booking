use lab_booking_db;
alter table t_resource
    add column path varchar(250) not null default '' comment 'API path.' after type;
alter table t_resource
    add column route varchar(100) not null default '' comment 'page route.' after type;
