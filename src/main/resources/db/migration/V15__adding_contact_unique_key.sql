alter table contact add constraint u_email unique (email_address);
alter table contact add constraint u_cellphone unique (cellphone_number);