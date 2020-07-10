/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/1/13 9:27:48                            */
/*==============================================================*/


drop table if exists sys_datasource;

drop table if exists sys_dept;

drop table if exists sys_job;

drop table if exists sys_log;

drop table if exists sys_menu;

drop table if exists sys_role;

drop table if exists sys_role_menu;

drop table if exists sys_user;

drop table if exists ������Ϣ��;

drop table if exists ������Ϣ��2;

/*==============================================================*/
/* Table: sys_datasource                                        */
/*==============================================================*/
create table sys_datasource
(
   id                   varchar(32) not null comment 'id',
   name                 varchar(100) comment '����',
   db_type              varchar(100) comment '���ݿ�����',
   host                 varchar(100) comment '��������ַ',
   port                 varchar(10) comment '�˿ں�',
   username             varchar(100) comment '�û���',
   password             varchar(200) comment '����',
   db_name              varchar(100) comment '���ݿ�����',
   create_id            varchar(32) comment '������ID',
   create_name          varchar(64) comment '����������',
   create_time          timestamp comment '����ʱ��',
   update_id            varchar(32) comment '�޸���ID',
   update_name          varchar(64) comment '�޸�������',
   update_time          timestamp comment '�޸�ʱ��',
   deleted_flag         tinyint comment 'ɾ�����: 0δɾ�� 1��ɾ��',
   primary key (id)
);

alter table sys_datasource comment '����Դ��';

/*==============================================================*/
/* Table: sys_dept                                              */
/*==============================================================*/
create table sys_dept
(
   id                   varchar(32) not null comment '����ID',
   name                 varchar(50) comment '��������',
   parent_id            varchar(32) comment '�ϼ�����ID',
   sort                 int comment '����',
   remark               varchar(200) comment '��ע',
   create_id            varchar(32) comment '������ID',
   create_name          varchar(64) comment '����������',
   create_time          datetime comment '����ʱ��',
   update_id            varchar(32) comment '�޸���ID',
   update_name          varchar(64) comment '�޸�������',
   update_time          datetime comment '�޸�ʱ��',
   deleted_flag         tinyint comment 'ɾ�����: 0-δɾ�� 1-��ɾ��',
   primary key (id)
);

alter table sys_dept comment '���ű�';

/*==============================================================*/
/* Table: sys_job                                               */
/*==============================================================*/
create table sys_job
(
   id                   varchar(32) not null comment '��λID',
   name                 varchar(50) comment '��������',
   dept_id              varchar(32) comment '����ID',
   sort                 int comment '����',
   remark               varchar(200) comment '��ע',
   create_id            varchar(32) comment '������ID',
   create_name          varchar(64) comment '����������',
   create_time          datetime comment '����ʱ��',
   update_id            varchar(32) comment '�޸���ID',
   update_name          varchar(64) comment '�޸�������',
   update_time          datetime comment '�޸�ʱ��',
   deleted_flag         tinyint comment 'ɾ�����: 0-δɾ�� 1-��ɾ��',
   primary key (id)
);

alter table sys_job comment '��λ��';

/*==============================================================*/
/* Table: sys_log                                               */
/*==============================================================*/
create table sys_log
(
   id                   varchar(32) not null comment 'ID',
   request_ip           varchar(100) comment '����IP',
   ip_location          varchar(200) comment 'IP������',
   type                 tinyint comment '�������ͣ�1 ������¼ 2 �쳣��¼',
   user_id              varchar(32) comment '������ID',
   username             varchar(100) comment '����������',
   description          varchar(500) comment '��������',
   action_method        varchar(100) comment '����Ŀ�귽��',
   action_url           varchar(200) comment '����url',
   params               varchar(1000) comment '�������',
   browser              varchar(100) comment '�����',
   operating_system     varchar(100) comment '����ϵͳ',
   class_path           varchar(100) comment '��·��',
   request_method       varchar(100) comment '���󷽷�',
   operate_type         varchar(50) comment '�������ͣ�1 ��ѯ/��ȡ 2 ��� 3 �޸� 4 ɾ��',
   start_time           datetime comment '��ʼʱ��',
   finish_time          datetime comment '���ʱ��',
   consuming_time       bigint comment '����ʱ��',
   ex_detail            text comment '�쳣������Ϣ ��ջ��Ϣ',
   ex_desc              varchar(200) comment '�쳣����',
   primary key (id)
);

alter table sys_log comment '������־��¼��';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(100) comment '����',
   parent_id            varchar(32) comment '����ID',
   iframe               tinyint comment '�Ƿ�������0=�� 1=��',
   sort                 int comment '����',
   icon                 varchar(200) comment 'ͼ��',
   path                 varchar(255) comment '���ӵ�ַ',
   cache                tinyint comment '�Ƿ񻺴棺0=�� 1=��',
   hidden               tinyint comment '�Ƿ����أ�0=�� 1=��',
   component_name       varchar(100) comment '�������',
   component_path       varchar(255) comment '���·��',
   type                 tinyint comment '���ͣ�1=Ŀ¼ 2=�˵� 3=��ť',
   permission           varchar(200) comment 'Ȩ�ޱ�ʶ',
   create_id            varchar(32) comment '������ID',
   create_name          varchar(64) comment '����������',
   create_time          datetime comment '����ʱ��',
   update_id            varchar(32) comment '�޸���ID',
   update_name          varchar(64) comment '�޸�������',
   update_time          datetime comment '�޸�ʱ��',
   deleted_flag         tinyint comment 'ɾ����ǣ� 0=δɾ�� 1=��ɾ��',
   primary key (id)
);

alter table sys_menu comment '�˵���';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   varchar(32) not null comment '��λID',
   name                 varchar(50) comment '��ɫ����',
   code                 varchar(100) comment '��ɫ��ʶ',
   sort                 int comment '����',
   remark               varchar(200) comment '��ע',
   create_id            varchar(32) comment '������ID',
   create_name          varchar(64) comment '����������',
   create_time          datetime comment '����ʱ��',
   update_id            varchar(32) comment '�޸���ID',
   update_name          varchar(64) comment '�޸�������',
   update_time          datetime comment '�޸�ʱ��',
   deleted_flag         tinyint comment 'ɾ�����: 0-δɾ�� 1-��ɾ��',
   primary key (id)
);

alter table sys_role comment '��ɫ��';

/*==============================================================*/
/* Table: sys_role_menu                                         */
/*==============================================================*/
create table sys_role_menu
(
   id                   varchar(32) not null comment 'ID',
   role_id              varchar(32) comment '��ɫID',
   menu_id              varchar(32) comment '�˵�ID',
   primary key (id)
);

alter table sys_role_menu comment '��ɫ�˵���';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   varchar(32) not null comment '�û�ID',
   username             varchar(50) comment '�û���',
   password             varchar(200) comment '����',
   dept_id              varchar(32) comment '����ID',
   job_id               varchar(32) comment '��λID',
   email                varchar(255) comment '����',
   phone                varchar(20) comment '�ֻ���',
   avatar               varchar(255) comment 'ͷ��',
   status               char(10) comment '״̬��0-���� 1-����',
   create_id            varchar(32) comment '������ID',
   create_name          varchar(64) comment '����������',
   create_time          datetime comment '����ʱ��',
   update_id            varchar(32) comment '�޸���ID',
   update_name          varchar(64) comment '�޸�������',
   update_time          datetime comment '�޸�ʱ��',
   deleted_flag         tinyint comment 'ɾ�����: 0-δɾ�� 1-��ɾ��',
   primary key (id)
);

alter table sys_user comment '�û���';

/*==============================================================*/
/* Table: ������Ϣ��                                                 */
/*==============================================================*/
create table ������Ϣ��
(
   create_id            varchar(32) comment '������ID',
   create_name          varchar(64) comment '����������',
   create_time          datetime comment '����ʱ��',
   update_id            varchar(32) comment '�޸���ID',
   update_name          varchar(64) comment '�޸�������',
   update_time          datetime comment '�޸�ʱ��',
   deleted_flag         tinyint comment 'ɾ����ǣ�0=δɾ�� 1=��ɾ��'
);

/*==============================================================*/
/* Table: ������Ϣ��2                                                */
/*==============================================================*/
create table ������Ϣ��2
(
   create_id            varchar(32) comment '������ID',
   create_name          varchar(64) comment '����������',
   create_time          datetime comment '����ʱ��',
   update_id            varchar(32) comment '�޸���ID',
   update_name          varchar(64) comment '�޸�������',
   update_time          datetime comment '�޸�ʱ��',
   deleted_flag         tinyint comment 'ɾ�����: 0δɾ�� 1��ɾ��'
);

