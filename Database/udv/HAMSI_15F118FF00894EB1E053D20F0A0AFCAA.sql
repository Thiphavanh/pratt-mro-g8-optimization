set define off
declare
v_folder_id varchar2(40) :='HAMSI_0910B86429E34BFEE053D20F0A0A3E96';
v_udv_id varchar2(85) :='HAMSI_15F118FF00894EB1E053D20F0A0AFCAA';
v_udv_tag varchar2(40) :='HAMSI_WorkPlanSecurityGroupIns';
v_udv_type varchar2(7) :='INPMTX';
v_udv_desc varchar2(255) :='Cloned from MFI_318C0424DC994673842641505361248F';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 300
  AutoScroll = False
  Caption = ''Insert Work Plan Security Group''
  TransactionType = ttCached
  IMSqlTransactionName = ''WorkPlanSecurityGroupIns''
  IMSqlSourceName = ''PlanSecurityGroupSel''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctInputMatrix
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  AutoRefresh = arDefault
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 0
  MaxHeight = 0
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = False
  BottomMargin = -1
  object WorkPlanSecurityGroupIns: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''SECURITY_GROUP''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''PLAN_NO''
      ''PLAN_ID''
      ''PLAN_UPDT_NO'')
    InputFieldsEditControlSSL.Strings = (
      ''SECURITY_GROUP''
      '' CtrlType=Lookup''
      ''  SqlId=HAMSI_FND_WORKPLANSECURITYGROUPLOOKUP''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   SECURITY_GROUP=Security Group''
      ''   SECURITY_GROUP_DESC=Description''
      ''   SECURITY_GROUP_TYPE=Security Group Type''
      ''   EXPIRATION_DATE=Expiration Date''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords'')
    InsertSqlId = ''HAMSI_FND_WorkPlanSecurityGroupIns''
    ParamsSQLSourceName = ''@ToolScope, PlanSecurityGroupSel''
    RefreshParams = False
    RefreshedSQLSourceName = ''PlanSecurityGroupSel''
    DataDefinitions.Strings = (
      ''SECURITY_GROUP=''
      ''PLAN_NO''
      ''PLAN_ID''
      ''PLAN_UPDT_NO'')
  end
end';
v_iclob clob;
begin
v_iclob := p1;
begin
insert into sfcore_udv_lib(udv_id,udv_tag,updt_userid,time_stamp,udv_type,stype,udv_desc,state,load_ref,tool_version,object_rev,owner_group,udv_definition)
    values(v_udv_id,v_udv_tag,user,sysdate,v_udv_type,v_stype,v_udv_desc,v_state,v_load_ref,v_tool_version,v_object_rev,v_owner_group,
    replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),'POUND39_HOLDER',chr(35)||chr(51)||chr(57)));
commit;
exception when dup_val_on_index then
  update sfcore_udv_lib
  set udv_definition=replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),'POUND39_HOLDER',chr(35)||chr(51)||chr(57)),updt_userid=user,time_stamp=sysdate,
      tool_version=v_tool_version,udv_tag=v_udv_tag,udv_desc = v_udv_desc,state=v_state,object_rev=v_object_rev,owner_group=v_owner_group,
      udv_type=v_udv_type,stype=v_stype
  where udv_id=v_udv_id;
  commit;
end;
begin
if v_folder_id is not null then
insert into sfcore_udv_folder(udv_id,folder_id,updt_userid,time_stamp)
values(v_udv_id,v_folder_id,user,sysdate);
commit;
end if;
exception 
when others then null;
end;
end;
/

set define on

