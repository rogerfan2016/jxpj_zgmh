--巡查任务中增加状态控制巡查任务
-- Add/modify columns 
alter table XC_TASK add ZT char(1);
alter table XC_TASK add RWJB VARCHAR2(32);
alter table XC_TASK add RWBM VARCHAR2(32);
alter table XC_TASK add RWMC VARCHAR2(100);
alter table XC_TASK add PJDXLX VARCHAR2(32);
alter table XC_TASK add PJDX VARCHAR2(1000);

-- Add comments to the columns 
comment on column XC_TASK.ZT
  is '状态（0停用；1启用）';
comment on column XC_TASK.RWJB
  is '任务级别(school学校；dept学院)';
comment on column XC_TASK.RWBM
  is '任务部门';
comment on column XC_TASK.RWMC
  is '任务名称';
comment on column XC_TASK.PJDXLX
  is '评价对象类型(lesson课程、person人员)';
comment on column XC_TASK.PJDX
  is '评价对象';

--巡查评价结果增加字段
-- Add/modify columns 
alter table XC_TASK_RESULT add DCR VARCHAR2(32);
alter table XC_TASK_RESULT add ZF number;
alter table XC_TASK_RESULT add DCSJ date;
-- Add comments to the columns 
comment on column XC_TASK_RESULT.DCR
  is '调查人';
comment on column XC_TASK_RESULT.ZF
  is '总得分';
comment on column XC_TASK_RESULT.DCSJ
  is '调查时间';

--评价配置表大改造，表结构大变
-- Create table
create table XC_CONFIG
(
  KEY        VARCHAR2(32) not null,
  VAL        VARCHAR2(500),
  APPEND     VARCHAR2(500),
  TYPE       VARCHAR2(32) not null,
  YWJB       VARCHAR2(32) not null,
  YWBM       VARCHAR2(32),
  WJID       VARCHAR2(500),
  PERSON     VARCHAR2(1000),
  OBJECT     VARCHAR2(1000),
  START_TIME DATE,
  END_TIME   DATE,
  REMARK     VARCHAR2(1000),
  STATUS     CHAR(1)
)
tablespace ZF
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table XC_CONFIG
  is '巡查管理 配置表';
-- Add comments to the columns 
comment on column XC_CONFIG.KEY
  is '配置名';
comment on column XC_CONFIG.VAL
  is '配置值(被评价人员关系SQL)';
comment on column XC_CONFIG.APPEND
  is '配置追加信息(评价人员关系SQL)';
comment on column XC_CONFIG.TYPE
  is '业务代码';
comment on column XC_CONFIG.YWJB
  is '业务级别(school校级；dept院级)';
comment on column XC_CONFIG.YWBM
  is '业务部门';
comment on column XC_CONFIG.WJID
  is '问卷ID(一个或多个)';
comment on column XC_CONFIG.PERSON
  is '评价人员(一个或多个)';
comment on column XC_CONFIG.OBJECT
  is '评价对象(一个或多个)';
comment on column XC_CONFIG.START_TIME
  is '评价开始时间';
comment on column XC_CONFIG.END_TIME
  is '评价结束时间';
comment on column XC_CONFIG.REMARK
  is '备注';
comment on column XC_CONFIG.STATUS
  is '状态(1正常；0删除)';

-- Create/Recreate primary, unique and foreign key constraints 
alter table XC_CONFIG
  add constraint PK_CONFIG_TYPE_YWJB_YWBM primary key (TYPE, YWJB, YWBM)
  using index 
  tablespace ZF
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

--巡查任务结果增加课程相关信息字段
-- Add/modify columns 
alter table XC_TASK_RESULT add SKSJ VARCHAR2(32);
alter table XC_TASK_RESULT add kcmc VARCHAR2(100);
alter table XC_TASK_RESULT add skdd VARCHAR2(32);
alter table XC_TASK_RESULT add kcjc VARCHAR2(32);
alter table XC_TASK_RESULT add kkxy VARCHAR2(32);
-- Add comments to the columns 
comment on column XC_TASK_RESULT.SKSJ
  is '上课时间';
comment on column XC_TASK_RESULT.kcmc
  is '课程名称';
comment on column XC_TASK_RESULT.skdd
  is '上课地点';
comment on column XC_TASK_RESULT.kcjc
  is '课程节次';
comment on column XC_TASK_RESULT.kkxy
  is '开课学院';
  
-----------------------------------------------------------------------------
--创建学生结课评价汇总视图
CREATE OR REPLACE VIEW VIEW_JXPJ_XSJKPJ AS
SELECT t.xn,
       t.xq,
       t.taskid,
       t.gh,
       t2.xm,
       t2.xzb,
       t2.zymc,
       t2.xy,
       (SELECT COUNT(1)
          FROM xc_task_result t1
         WHERE t.id = t1.memberid
           AND t.gh = t1.dcr) pjzsl,
       (SELECT COUNT(1)
          FROM xc_task_result t1
         WHERE t.id = t1.memberid
           AND t.gh = t1.dcr
           AND t1.dcsj IS NOT NULL) ypjsl,
       (SELECT COUNT(1)
          FROM xc_task_result t1
         WHERE t.id = t1.memberid
           AND t.gh = t1.dcr
           AND t1.dcsj IS NULL) wpjsl
  FROM xc_task_member t
  LEFT JOIN jxpj_jcsj_xsxx t2
    ON t.gh = t2.xh
/
----------------------------------------------------
-- Add/modify columns 
alter table XC_TASK_MEMBER add xn VARCHAR2(32);
alter table XC_TASK_MEMBER add xq VARCHAR2(32);
-- Add comments to the columns 
comment on column XC_TASK_MEMBER.xn
  is '学年';
comment on column XC_TASK_MEMBER.xq
  is '学期';

-- Add/modify columns 
alter table XC_TASK_RESULT add kcdm VARCHAR2(32);
alter table XC_TASK_RESULT add jsxm VARCHAR2(32);
-- Add comments to the columns 
comment on column XC_TASK_RESULT.kcdm
  is '课程代码';
comment on column XC_TASK_RESULT.jsxm
  is '教师姓名';

--关闭触发器
alter table wjdc_djrffb disable all triggers;
/