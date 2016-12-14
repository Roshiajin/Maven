-- Create table
create table TEST
(
  id   NUMBER(10) not null,
  name VARCHAR2(100),
  info VARCHAR2(2000)
);

-- Create sequence
create sequence SEQ_TEST
minvalue 1
maxvalue 999999999
start with 121
increment by 1;

create or replace trigger test_b_i
  before insert
  on test
  for each row
declare
  -- local variables here
begin
  :new.id := seq_test.nextval;
end test_b_i;

create or replace package testpkg is

  procedure insert_test (p_name in varchar2, p_info in varchar2, p_id out number);

end testpkg;

create or replace package body testpkg is

  procedure insert_test (p_name in varchar2, p_info in varchar2, p_id out number) is
  begin
    insert into test(name, info) values (p_name, p_info) returning id into p_id;

  end insert_test;

end testpkg;
/