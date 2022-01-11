set define off
declare
v_folder_id varchar2(40) :='HAMSI_0910B86429E34BFEE053D20F0A0A3E96';
v_udv_id varchar2(85) :='HAMSI_176F9F269BC57753E053D20F0A0AA6AB';
v_udv_tag varchar2(40) :='HAMSI_RestrictedPlanImport';
v_udv_type varchar2(7) :='INPMTX';
v_udv_desc varchar2(255) :='Cloned From MFI_C7C836C56349486B90D17951B66CB4B0';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20130124~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 400
  Height = 300
  Caption = ''Import Plan''
  TransactionType = ttCached
  IMSqlTransactionName = ''ImportPlanFile''
  IMSqlSourceName = ''PlanFileList''
  IMScanAction = saIMAccept
  IMAllowRowReedit = False
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
  object PlanFileList: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_ImportPlanList''
    PublishParams = True
    SelectedFields.Strings = (
      ''SELECTED~8~Selected~N~N~False~False~False~N~~~~~Default~N~'' 
      ''RESTRICTED~10~Restrict Access~N~N~False~False~NOT @HasPriv(''POUND39_HOLDER''CAN_CREATE_PAAR_REQUEST''POUND39_HOLDER'')~N~~~~~Default~N~''
      ''FILE~20~Filename~N~N~False~False~False~N~~~~~Default~N~''
      ''MODIFIED~14~File Modified~N~N~False~False~False~N~~~~~Default~N~''
      ''SIZE~10~File Size~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''SELECTED~CheckBox=1~0'')
    DataDefinitions.Strings = (
      ''SELECTED''
      ''RESTRICTED''
      ''FILE''
      ''MODIFIED''
      ''SIZE'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''PlanFileList''
  end
  object ImportPlanFile: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''FILE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''PWP_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False''
      ''SELECTED''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''RESTRICTED''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
      ''PWP_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FILE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SELECTED''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''RESTRICTED''
      '' CtrlType=List''
      ''  Values=Y~N'')
    UpdateSqlId = ''HAMSI_PLG_IMPORTPLAN''
    ParamsSQLSourceName = ''@ToolScope''
    RefreshParams = False
    RefreshedSQLSourceName = ''PwpPlansSel''
    DataDefinitions.Strings = (
      ''FILE=''
      ''PWP_ID=''
      ''RESTRICTED='')
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

