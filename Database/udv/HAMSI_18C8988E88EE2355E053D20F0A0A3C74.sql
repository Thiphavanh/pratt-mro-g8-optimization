set define off
declare
v_folder_id varchar2(40) :='HAMSI_A36C8B4A07FC5D74E0400A0AD20F28E1';
v_udv_id varchar2(85) :='HAMSI_18C8988E88EE2355E053D20F0A0A3C74';
v_udv_tag varchar2(40) :='HAMSI_ApprovalPermissionSecGrpProfileIM';
v_udv_type varchar2(7) :='INPMTX';
v_udv_desc varchar2(255) :='Input Matrix For Security Group Profile in Approval Permission Panel';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 700
  Height = 300
  AutoScroll = False
  Caption = '':@TransactionType Security Group''
  TransactionType = ttCached
  IMSqlTransactionName = ''SecurityGroupProfileInsUpd''
  IMSqlSourceName = ''SecurityGroupProfile''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMFixedColCount = -1
  IMPopulateFromParams = False
  IMDeleteSqlId = ''HAMSI_APPROVALPERMISSIONPROFILEDEL''
  IMBatchTransactions = True
  IMPopulateFromTransactionParams = True
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
  object DefaultDateSel: TsfTransactionParamSource
    SelectSqlId = ''HAMSI_DefaultEffectiveDatesSecGrpIMSel''
    PublishParams = True
  end
  object SecurityGroupProfileInsUpd: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''REQUEST_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''PMN_TYPE_ENTITY''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''PMN_EFF_FROM_DATE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''PMN_EFF_THRU_DATE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''PMN_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=SECURITY GROUP''
      '' MaxLength=30''
      ''SECURITY_GROUP_DESC''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''PMN_STATUS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      '' DefaultValue=Permit''
      ''AUTH_ID'')
    InputFieldsEditControlSSL.Strings = (
      ''PMN_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SECURITY_GROUP_DESC''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PMN_STATUS''
      '' CtrlType=List''
      ''  Values=Permit~Deny''
      ''PMN_TYPE_ENTITY''
      '' CtrlType=Lookup''
      ''  SqlId=HAMSI_SECURITYGROUPPROFILEIMSECURITYGROUPLOOKUPEXT''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   SECURITY_GROUP=Security Group(15)''
      ''   SECURITY_GROUP_DESC=Security Group Description(20)''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''REQUEST_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PMN_EFF_THRU_DATE''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=Windows Default''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PMN_EFF_FROM_DATE''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=Windows Default''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    InsertSqlId = ''HAMSI_APPROVALPERMISSIONPROFILEINS''
    UpdateSqlId = ''HAMSI_APPROVALPERMISSIONPROFILEUPD''
    ParamsSQLSourceName = ''@ToolScope, DefaultDateSel''
    RefreshParams = False
    DataDefinitions.Strings = (
      ''REQUEST_ID=''
      ''PMN_TYPE_ENTITY=''
      ''PMN_EFF_FROM_DATE=''
      ''PMN_EFF_THRU_DATE=''
      ''PMN_TYPE=''
      ''PMN_STATUS=''
      ''AUTH_ID'')
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

