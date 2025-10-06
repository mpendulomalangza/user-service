alter table user
    add `user_type` enum('gmail','internal')  DEFAULT 'internal';

alter table user
    add unique key `u_user` (user_type,user_name);
alter table user change `user_name` `username` varchar (200) not null;


