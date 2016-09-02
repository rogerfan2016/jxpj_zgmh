-- Add/modify columns 
alter table JXPJ_SSPJ_SKKQGLB modify PJID VARCHAR2(50);
-- Add/modify columns 
alter table JXPJ_SSPJ_XSPJ modify PJID VARCHAR2(50);
-- Add/modify columns 
alter table JXPJ_JCSJ_KCBXX add KKXY VARCHAR2(32);
alter table JXPJ_JCSJ_KCBXX add KCFL VARCHAR2(32);
alter table JXPJ_JCSJ_KCBXX add rklsxm VARCHAR2(32);
alter table JXPJ_JCSJ_KCBXX add ksjc VARCHAR2(32);
alter table JXPJ_JCSJ_KCBXX add skcd VARCHAR2(32);
-- Add comments to the columns 
comment on column JXPJ_JCSJ_KCBXX.ksjc
  is '开始节次';
comment on column JXPJ_JCSJ_KCBXX.skcd
  is '上课长度';
comment on column JXPJ_JCSJ_KCBXX.rklsxm
  is '任课老师姓名';
comment on column JXPJ_JCSJ_KCBXX.KKXY
  is '开课学院';
comment on column JXPJ_JCSJ_KCBXX.KCFL
  is '课程分类';

-- Add/modify columns 
alter table JXPJ_JSHP_TKGL add TKLX VARCHAR2(32);
alter table JXPJ_JSHP_TKGL add SHR VARCHAR2(32);
alter table JXPJ_JSHP_TKGL add KKXY VARCHAR2(32);
alter table JXPJ_JSHP_TKGL add SHSJ date;
alter table JXPJ_JSHP_TKGL add KCJC number;
-- Add comments to the columns 
comment on column JXPJ_JSHP_TKGL.KCJC
  is '课程节次';
comment on column JXPJ_JSHP_TKGL.TKLX
  is '听课类型（dept院级；school校级）';
comment on column JXPJ_JSHP_TKGL.SHR
  is '审核人';
comment on column JXPJ_JSHP_TKGL.KKXY
  is '开课学院';
comment on column JXPJ_JSHP_TKGL.SHSJ
  is '审核时间';
  
-- Add/modify columns 
alter table JXPJ_JBPZ_WJPZB add KCFL VARCHAR2(32);
-- Add comments to the columns 
comment on column JXPJ_JBPZ_WJPZB.KCFL
  is '课程分类';

--===================================================
--新建课程节次时间对应表（每一节课上课开始结束时间）
-- Create table
create table JXPJ_JCSJ_KCJCSJ
(
  XN   VARCHAR2(32),
  XQ   VARCHAR2(32),
  KCJC VARCHAR2(32),
  KSSJ VARCHAR2(32),
  JSSJ VARCHAR2(32)
);
-- Add comments to the table 
comment on table JXPJ_JCSJ_KCJCSJ
  is '教学评价_基础数据_课程节次时间';
-- Add comments to the columns 
comment on column JXPJ_JCSJ_KCJCSJ.XN
  is '学年';
comment on column JXPJ_JCSJ_KCJCSJ.XQ
  is '学期';
comment on column JXPJ_JCSJ_KCJCSJ.KCJC
  is '课程节次';
comment on column JXPJ_JCSJ_KCJCSJ.KSSJ
  is '上课开始时间';
comment on column JXPJ_JCSJ_KCJCSJ.JSSJ
  is '上课结束时间';
  
--初始化课程节次时间对应数据
insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '1', '08:00:00', '08:45:00');

insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '2', '09:00:00', '09:45:00');

insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '3', '10:05:00', '10:50:00');

insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '4', '11:05:00', '11:50:00');

insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '5', '13:30:00', '14:15:00');

insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '6', '14:30:00', '15:15:00');

insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '7', '15:35:00', '16:20:00');

insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '8', '16:35:00', '17:20:00');

insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '9', '18:30:00', '19:15:00');

insert into jxpj_jcsj_kcjcsj (XN, XQ, KCJC, KSSJ, JSSJ)
values ('2015-2016', '2', '10', '19:30:00', '20:15:00');

commit;

--课程表增加每一节开始结束时间
-- Add/modify columns 
alter table JXPJ_JCSJ_KCBXX add KSSJ VARCHAR2(32);
alter table JXPJ_JCSJ_KCBXX add JSSJ VARCHAR2(32);
-- Add comments to the columns 
comment on column JXPJ_JCSJ_KCBXX.KSSJ
  is '开始时间';
comment on column JXPJ_JCSJ_KCBXX.JSSJ
  is '结束时间';

-------------------------------------------------------------------
--创建评价课表视图
CREATE OR REPLACE VIEW VIEW_JXPJ_JSKCJLB AS
SELECT (substr(t.xn, 3, 2) || t.xq || substr(t1.nyr, 6, 2) ||
       substr(t1.nyr, 9, 2) || substr(t.skbh, 15, 8) || t.jsbh || t.gh ||
       t.kssj || (CASE
         WHEN t.dsz = '单' THEN
          '1'
         WHEN t.dsz = '双' THEN
          '2'
         ELSE
          '0'
       END) || substr(t.skbh, 31, 2) || t.kssj) globalid,
       t.xn,t.xq,t.jsbh,t.jsmc,t.gh,t.jsxm,t.xqj,t.dsz,t.ksz,t.jsz,t.skbh,t.kcmc,t1.nyr,t.bjid,
       (CASE
         WHEN t.skcd = '1' THEN
          '第' || t.kssj || '节'
         WHEN t.skcd = '2' THEN
          '第' || t.kssj || '，' || (to_number(t.kssj)+1) || '节'
         WHEN t.skcd = '4' THEN
          '第' || t.kssj || '，' || (to_number(t.kssj)+1) || '，' || (to_number(t.kssj)+2) || '，' || (to_number(t.kssj)+3) || '节'
         ELSE
          '第1-8节'
       END) kcjc,
       (SELECT t1.nyr || ' ' || k1.kssj
          FROM jxpj_jcsj_kcjcsj k1
         WHERE k1.kcjc = t.kssj
           AND ROWNUM = 1) kssj,
       (SELECT t1.nyr || ' ' || k2.jssj
          FROM jxpj_jcsj_kcjcsj k2
         WHERE k2.kcjc = t.kssj
           AND ROWNUM = 1) jssj
  FROM jxpj_jcsj_jskcsjb t, jxpj_jcsj_rqkszb t1
 WHERE t.xn = t1.xn
   AND t1.djz BETWEEN t.ksz AND t.jsz
   AND t.xqj = t1.xqj;
/
-------------------------------------------------------------------  
--修改同步课表的存储过程
create or replace procedure Syn_Curriculum_Data (
out_code out varchar2,
out_message out varchar2
)
as
begin
  declare
  noFoundErr exception;
  v_kcjc number;
  v_year number;
  v_month number;
  v_xn varchar2(10);
  v_xq number;

  --从教务DB中取得课程信息
  cursor cur is
  select t1.*, t2.nyr
    from jxpj_jcsj_jskcsjb t1,
         jxpj_jcsj_rqkszb t2
   where t1.xn = t2.xn
     and t2.djz between t1.ksz and t1.jsz
     and t1.xqj = t2.xqj
     and t1.xn = v_xn
     and t1.xq = v_xq;

  val cur%rowtype;

  begin
    select
      to_number(substr(to_char(sysdate, 'yyyyMMdd'), 1, 4)),
      to_number(substr(to_char(sysdate, 'yyyyMMdd'), 5, 2))
    into v_year, v_month
      from dual;

    v_xn := '2015-2016';
      v_xq := 2;
    /*if v_month >= 9 then
      v_xn := v_year || '-' || (v_year + 1);
      v_xq := 1;
    else
      v_xn := (v_year - 1) || '-' || v_year;
      v_xq := 2;
    end if;*/

    open cur;

    if cur%notfound then
      raise noFoundErr;
    end if;

    --删除原数据
    delete from jxpj_jcsj_kcbxx WHERE substr(kcid,2,11) = v_xn || '-' || v_xq;

    loop
      fetch cur into val;

      if (cur%found) then
        v_kcjc := 0;
        loop
          if v_kcjc >= val.skcd then
            exit;
          end if;
          --插入课程信息表
          insert into jxpj_jcsj_kcbxx (
            globalid,
            kcid,
            kcmc,
            kcsj,
            kssj,
            jssj,
            kcjc,
            ksjc,
            skcd,
            dsz,
            kkxy,
            kcfl,
            rklsgh,
            rklsxm,
            skdd,
            bjid,
            sszyid
          ) values (
            (substr(val.xn,3,2) || val.xq || substr(val.nyr,6,2) || substr(val.nyr,9,2) || substr(val.skbh,15,8) || val.jsbh || val.gh || val.kssj || (CASE WHEN val.dsz = '单' THEN '1' when val.dsz = '双' THEN '2' ELSE '0' end) || substr(val.skbh,31,2) || (val.kssj + v_kcjc) ),
            val.skbh,
            val.kcmc,
            val.nyr,
            (SELECT k.kssj FROM jxpj_jcsj_kcjcsj k WHERE k.kcjc=(val.kssj + v_kcjc) AND k.xn=v_xn AND k.xq=v_xq),
            (SELECT k.jssj FROM jxpj_jcsj_kcjcsj k WHERE k.kcjc=(val.kssj + v_kcjc) AND k.xn=v_xn AND k.xq=v_xq),
            (val.kssj + v_kcjc),
            val.kssj,
            val.skcd,
            val.dsz,
            (SELECT z.bmdm FROM jxpj_jcsj_jxrwb j LEFT JOIN hrm_bzgl_zzjgb z ON z.bmmc=j.kkxy WHERE j.xkkh=val.skbh AND rownum = 1),
            (SELECT fldm FROM jxpj_jcsj_kcflxx f WHERE substr(val.skbh,15,8)=f.kcdm AND rownum = 1),
            val.gh,
            val.jsxm,
            val.jsmc,
            val.bjid,
            ''
          );
          v_kcjc := v_kcjc + 1;
        end loop;

      else
        exit;
      end if;

    end loop;
    close cur;

    out_code := '0';
    out_message := '同步成功';
    commit;
  exception
    when noFoundErr then
      out_code := '1';
      out_message := '没有抓取到数据';
      if cur%isopen then
        close cur;
      end if;
      rollback;
    when others then
      out_code := sqlcode;
      out_message := sqlerrm;
      if cur%isopen then
        close cur;
      end if;
      rollback;
  end;

end;
/

------增加第二个课程分类-------
-- Add/modify columns 
alter table JXPJ_JCSJ_KCFLXX add fldm2 VARCHAR2(32);
alter table JXPJ_JCSJ_KCFLXX add sjdw VARCHAR2(32);
alter table JXPJ_JCSJ_KCFLXX add kkxy VARCHAR2(32);
-- Add comments to the columns 
comment on column JXPJ_JCSJ_KCFLXX.kkxy
  is '开课学院';
comment on column JXPJ_JCSJ_KCFLXX.sjdw
  is '三级单位';
comment on column JXPJ_JCSJ_KCFLXX.fldm2
  is '分类代码2';
-- Create/Recreate primary, unique and foreign key constraints 
alter table JXPJ_JCSJ_KCFLXX
  add constraint pk_jxpj_kcflxx_kcdm primary key (KCDM);
  
-----------------------------------------------------
-- Create table
drop table JXPJ_JCSJ_KCFLXX;
create table JXPJ_JCSJ_KCFLXX
(
  kcdm   VARCHAR2(32) not null,
  kczwmc VARCHAR2(100),
  fldm   VARCHAR2(32),
  fldm2  VARCHAR2(32),
  sjdw   VARCHAR2(32),
  kkxy   VARCHAR2(32)
);
-- Add comments to the table 
comment on table JXPJ_JCSJ_KCFLXX
  is '教学评价_基础数据_课程分类信息';
-- Add comments to the columns 
comment on column JXPJ_JCSJ_KCFLXX.kcdm
  is '课程代码';
comment on column JXPJ_JCSJ_KCFLXX.kczwmc
  is '课程名称';
comment on column JXPJ_JCSJ_KCFLXX.fldm
  is '分类代码';
comment on column JXPJ_JCSJ_KCFLXX.fldm2
  is '分类代码2';
comment on column JXPJ_JCSJ_KCFLXX.sjdw
  is '三级单位';
comment on column JXPJ_JCSJ_KCFLXX.kkxy
  is '开课学院';
-- Create/Recreate primary, unique and foreign key constraints 
alter table JXPJ_JCSJ_KCFLXX
  add constraint PK_JXPJ_KCFLXX_KCDM primary key (KCDM);
