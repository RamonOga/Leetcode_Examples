create table users (
    id serial primary key,
    name varchar(255)
             );
create table meeting (
    id serial primary key,
    date varchar(255),
    place varchar(255)
);

create table users_meeting (
    id serial primary key,
    user_id int references users(id),
    meeting_id int references meeting(id),
    offer int
);