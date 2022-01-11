
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_7652421712EF25A7E053EF9F1F0A454A.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_7652421712EF25A7E053EF9F1F0A454A';
v_udv_tag sfcore_udv_lib.udv_tag%type :='pwust_OrderOperConfUpdt';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 980 ';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 458
  Height = 311
  AutoScroll = False
  Caption = ''Update Operation Conf Data''
  TransactionType = ttCached
  IMSqlTransactionName = ''OrderOperHdrAltUpd''
  IMSqlSourceName = ''OrderOperUpdIMSel''
  IMScanAction = saIMAccept
  IMBatchCommit = True
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctInputMatrix
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  SizeToEndOfLine = False
  AutoRefresh = arDisabled
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 0
  MaxHeight = 0
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = True
  BottomMargin = -1
  object _OBJ1: TsfDBInputEdit
    Left = 26
    Top = 26
    Width = 185
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 0
    EntryType = etAlphaNumeric
    Caption = ''Sales Order''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ2: TsfDBInputEdit
    Left = 225
    Top = 25
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
    EntryType = etAlphaNumeric
    Caption = ''Engine Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OrderOperUpdIMSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''pwust_OrderOperConfSel''
    ParamsSQLSourceName = ''@ToolScope, @ScriptParams''
    PublishParams = True
    SelectedFields.Strings = (
      ''OPER_NO~4~Oper No~N~N~N~Y~N~N~~~~~Default~N~~''
      
        ''OPER_STATUS~10~Oper Status~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~''
      ''CONF_NUM~10~Conf Number~N~N~False~False~False~N~~~~~Default~N~~''
      ''SUP_NET_NUM~8~Sup Net~N~N~False~False~False~N~~~~~Default~N~~''
      ''SUB_NET_NUM~8~Sub Net~N~N~False~False~False~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''PLAN_ASGND_WORK_LOC~Edit''
      ''MUST_ISSUE_PARTS_FLAG~CheckBox=1~0''
      ''CROSS_ORDER_FLAG~CheckBox=1~0''
      ''OPER_CHANGE_LEVEL~Edit''
      ''SEQ_STEPS_FLAG~Edit''
      ''OPER_KEY~Edit''
      ''UCF_ORDER_OPER_DATE1~Edit''
      ''UCF_ORDER_OPER_NUM2~Edit''
      ''UCF_ORDER_OPER_VCH2~Edit''
      ''UCF_ORDER_OPER_VCH1~Edit''
      ''UCF_PLAN_OPER_FLAG2~Edit''
      ''UCF_PLAN_OPER_FLAG1~Edit''
      ''UCF_PLAN_OPER_VCH5~Edit''
      ''UCF_PLAN_OPER_VCH4~Edit''
      ''UCF_PLAN_OPER_VCH3~Edit''
      ''UCF_PLAN_OPER_VCH2~Edit''
      ''UCF_PLAN_OPER_VCH1~Edit''
      ''SUPPLIER_CODE~Edit''
      ''OPER_TITLE~Edit''
      ''ASGND_WORK_DEPT~Edit''
      ''OSP_DAYS~Edit''
      ''UCF_ORDER_OPER_NUM1~Edit''
      ''UCF_PLAN_OPER_NUM2~Edit''
      ''OSP_FLAG~Edit''
      ''EXE_ORDER~Edit''
      ''AUTO_CYCLE_FLAG~Edit''
      ''UNITS_PER_CYCLE~Edit''
      ''REPORT_ID_DISP~Edit''
      ''RECONCILE_SCRAP~Edit''
      ''UNITS_PER_CYCLE_ACTUAL~Edit''
      ''ORIENTATION_FLAG~Edit''
      ''UCF_PLAN_OPER_NUM1~Edit''
      ''OPER_NO~Edit''
      ''SUB_NET_NUM~Edit'')
    TestParamValues.Strings = (
      ''ORDER_ID=1'')
    DataDefinitions.Strings = (
      ''OPER_NO''
      ''CONF_NUM''
      ''SUP_NET_NUM''
      ''SUB_NET_NUM'')
    ConsolidatedQuery = False
  end
  object OrderOperHdrAltUpd: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''ORDER_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''part_no''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''part_chg''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''REPORT_ID_DISP''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''OPER_TYPE_ROLE''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' DependentFields=TEST_TYPE''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''SUP_NET_NUM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''SUB_NET_NUM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''OPER_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''SALES_ORDER'')
    InputFieldsEditControlSSL.Strings = (
      ''part_chg''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''part_no''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''REPORT_ID_DISP''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_LabelReportLookup''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   REPORT_ID_DISP=Report Id Disp''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''OPER_TYPE_ROLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SUP_NET_NUM''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_ORDEROPERSUPSEL''
      ''  ParamsSrc=@toolscope''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''OPER_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORDER_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SUB_NET_NUM''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_ORDEROPERSUBSEL''
      ''  ParamsSrc=@toolscope''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N'')
    UpdateSqlId = ''pwust_OrderOperConfUpd''
    ParamsSQLSourceName = ''@ToolScope''
    RefreshParams = False
    MultiRecordTransaction = mruGroup
    DataDefinitions.Strings = (
      ''ORDER_ID=''
      ''SUP_NET_NUM=''
      ''SUB_NET_NUM=''
      ''OPER_NO=''
      ''SALES_ORDER'')
  end
  object PWSaleOrderEngTypeSel: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''SALES_ORDER''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''SECURITY_GROUP''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''PROGRAM''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
      ''SECURITY_GROUP''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SALES_ORDER''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PROGRAM''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    InsertSqlId = ''pwust_orderSalesOrderEngTypeSel''
    UpdateSqlId = ''pwust_orderSalesOrderEngTypeSel''
    ParamsSQLSourceName = ''@ToolScope''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ1~SALES_ORDER''
      ''_OBJ2~PROGRAM'')
    DataDefinitions.Strings = (
      ''SALES_ORDER=''
      ''SECURITY_GROUP='')
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

