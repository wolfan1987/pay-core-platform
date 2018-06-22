-- Create table
create table T_MQ_MESSAGE
(
  id          NUMBER(20) not null,
  topic       VARCHAR2(30) not null,
  keys        VARCHAR2(50) not null,
  tags        VARCHAR2(50) not null,
  message     VARCHAR2(300) not null,
  create_time DATE,
  update_time DATE
)
tablespace EPAY_DATA
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 64K
next 1M
minextents 1
maxextents unlimited
);
-- Add comments to the table 
comment on table T_MQ_MESSAGE
is 'mq发送消息持久化';
-- Add comments to the columns 
comment on column T_MQ_MESSAGE.topic
is '消息主题，一个应用尽量使用一个主题，可以采用消息子主题tags进行区分';
comment on column T_MQ_MESSAGE.keys
is '每个消息在业务层面的唯一标识码，方便将来定位丢失的消息。例如订单号';
comment on column T_MQ_MESSAGE.tags
is '消息子类型，消费方在订阅消息的时候，可以通过tags进行过滤';
comment on column T_MQ_MESSAGE.message
is '消息内容';
comment on column T_MQ_MESSAGE.create_time
is '创建时间';
comment on column T_MQ_MESSAGE.update_time
is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_MQ_MESSAGE
  add constraint T_MQ_MESSAGE_PK primary key (ID)
  using index
  tablespace EPAY_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
  initial 64K
  next 1M
  minextents 1
  maxextents unlimited
  );

-- Create sequence
create sequence T_MQ_MESSAGE_SEQ
minvalue 1
maxvalue 999999999999999999999999
start with 161
increment by 1
cache 20
order;