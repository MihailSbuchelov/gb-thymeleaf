insert into account_user (password, username, firstname, lastname, account_non_expired, account_non_locked,
                          credentials_non_expired, enabled)
values ('$2a$10$SRtq4NfujTWNyixTvyrgMO0sz9yuDDUikFNyF7Y2oe807VYuDqexi', 'user', 'Иван', 'Иванов', true, true, true, true),
('$2a$10$3olGfDkZcQ9Bx4GBFAa85uQKu7HcSXeDSWEftba22vKIZuhM.o/G2', 'admin', 'Петр', 'Иванов', true, true, true, true);

insert into authority (role)
values ('USER'),
       ('ADMIN');

insert into user_authority (user_id, authority_id)
values (1, 1),
       (2, 2);