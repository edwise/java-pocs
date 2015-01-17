create schema if not exists pocjooq;

use schema pocjooq;

create table if not exists book_character (id integer primary key, name varchar(30));

delete from book_character;
insert into book_character values (1, 'Saruman');
insert into book_character values (2, 'Gandalf');
insert into book_character values (3, 'Aragorn');
insert into book_character values (4, 'Samwise');
insert into book_character values (5, 'Frodo');

commit;