create table device_var(name varchar(50), value varchar(65535), primary key (name));
create table workflow_var(uuid varchar(40), name varchar(50), value varchar(65535), primary key (uuid, name));
create table workflow(uuid varchar(40), name varchar(255), author varchar(255), rev integer, notes varchar(255), enabled boolean, staring_from bigint, ending_on bigint, xml varchar(65535), primary key (uuid));
create table log(agent_id varchar(30), workflow_id varchar(40), name varchar(50), value varchar(65535), timestamp bigint, message_id varchar(40), j integer default -1, k integer default -1); 