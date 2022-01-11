
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_936262125DF81CB6E05387971F0A2DFD.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_936262125DF81CB6E05387971F0A2DFD';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_CreateMultipleROMReportsIM';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_RPT_306, 1487';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.0.20190207~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFRPT';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 300
  HelpContext = ''''
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Create ROM Report''
  TransactionType = ttCached
  IMSqlTransactionName = ''CreateROMReports''
  IMSqlSourceName = ''ROMReportInfo''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = True
  IMEndExecSqlId = ''PWUST_LaunchMultipleROMReports''
  IMPopulateFromTransactionParams = True
  UdvControlType = uctInputMatrix
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  SizeToEndOfLine = False
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
  object CreateROMReports: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''@USERID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''SERIAL_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''SALES_ORDER''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True'')
    InputFieldsEditControlSSL.Strings = (
      ''@USERID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SERIAL_NO''
      '' CtrlType=Lookup''
      ''  Decimal=0''
      ''  Currency=N''
      ''  SqlId=PWUST_ROMReportSerialNoSel''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   SERIAL_NO=Serial Number''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''SALES_ORDER''
      '' CtrlType=Lookup''
      ''  Decimal=0''
      ''  Currency=N''
      ''  SqlId=PWUSD_PWUST_ROMRPTHISTORYSALESORDERSELIM''
      ''  Validate=N''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   SALES_ORDER=Sales Order''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords'')
    InsertSqlId = ''PWUST_LaunchMultipleROMReports''
    UpdateSqlId = ''PWUST_LaunchMultipleROMReports''
    ParamsSQLSourceName = ''@ToolScope''
    RefreshParams = False
    MultiRecordTransaction = mruGroup
    DataDefinitions.Strings = (
      ''SERIAL_NO=''
      ''SALES_ORDER=''
      ''@USERID='')
  end
  object ROMReportInfo: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_CreateMultipleROMSelect''
    ParamsSQLSourceName = ''@ToolScope,@ScriptParams,@Auxparams''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''SERIAL_NO~20~Serial Number~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''SALES_ORDER~25~Sales Order~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~'')
    SelectedFieldsEditControl.Strings = (
      ''SERIAL_NO~Edit'')
    DataDefinitions.Strings = (
      ''SERIAL_NO''
      ''SALES_ORDER'')
    ConsolidatedQuery = False
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
  begin
    update sfcore_udv_lib
    set udv_definition=replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),'POUND39_HOLDER',chr(35)||chr(51)||chr(57)),updt_userid=user,time_stamp=sysdate,
      tool_version=v_tool_version,udv_tag=v_udv_tag,udv_desc = v_udv_desc,state=v_state,object_rev=v_object_rev,owner_group=v_owner_group,
      udv_type=v_udv_type,stype=v_stype
    where udv_id=v_udv_id;
    commit;
  exception when others then
  	 sffnd_show_execution_message('DML',v_udv_id, SQLCODE,SQLERRM,' update sfcore_udv_lib ');
  end;
when others then
  sffnd_show_execution_message('DML',v_udv_id, SQLCODE,SQLERRM,' insert into sfcore_udv_lib ');
end;
begin
 if v_folder_id is not null then
  insert into sfcore_udv_folder(udv_id,folder_id,updt_userid,time_stamp)
  values(v_udv_id,v_folder_id,user,sysdate);
  commit;
 end if;
exception when others then 
 sffnd_show_execution_message('DML',v_udv_id, SQLCODE,SQLERRM,' insert into sfcore_udv_folder ');
end;
end;
/

set define on

