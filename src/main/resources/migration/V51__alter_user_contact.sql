alter table contact add user_id int null;
alter table contact add constraint fk_contact_user foreign key (user_id) references user(id);
alter table user drop  constraint fk_user_contact;