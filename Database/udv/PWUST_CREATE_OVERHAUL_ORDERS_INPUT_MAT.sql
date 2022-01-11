
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5DE17AF441ABFB35E05387971F0A59E2.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5DE17AF441ABFB35E05387971F0A59E2';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_CREATE_OVERHAUL_ORDERS_INPUT_MAT';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_eWORRK defect 412/defect 1267';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 800
  Height = 300
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Create Overhaul Orders''
  TransactionType = ttCached
  IMSqlSourceName = ''PWUST_OVERHAUL_WORK_ORDERS_DISPATCH''
  IMScanAction = saIMAccept
  IMBlankRowValuesHavePrecedence = True
  IMAllowHeaderChanges = True
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = True
  IMEndExecSqlId = ''PWUST_BuildIMOrders''
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
  object _OBJ2: TsfDBInputEdit
    Left = 22
    Top = 19
    Width = 121
    Height = 25
    BorderColor = clBlack
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
  object _OBJ3: TsfDBInputEdit
    Left = 160
    Top = 19
    Width = 121
    Height = 25
    BorderColor = clBlack
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
  object _OBJ4: TsfDBInputEdit
    Left = 294
    Top = 19
    Width = 121
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 2
    EntryType = etAlphaNumeric
    Caption = ''Engine Model''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ5: TsfDBInputEdit
    Left = 428
    Top = 19
    Width = 121
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = ''Work Loc''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object overhaul_input_matrix: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''NEW_SALES_ORDER''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''NEW_ENGINE_TYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False''
      ''NEW_ENGINE_MODEL''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False''
      ''NEW_WORK_SCOPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False''
      ''PARTNO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''NEW_SUP_NET_ACT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False''
      ''NEW_SUB_NET_ACT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False''
      ''NEW_CUSTOMER_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False''
      ''NEW_PLANED_ORDER''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=N''
      ''NEW_SAP_WORK_LOC''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=N''
      '' Hidden=False''
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
      ''PARTNO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''NEW_ENGINE_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_SEL_ENGINE_TYPE''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''NEW_ENGINE_MODEL''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_SEL_ENGINE_MODEL''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''NEW_SAP_WORK_LOC''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_Pl_WorkLocSel_2''
      ''  Validate=N''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=Y''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=Y''
      ''NEW_SUP_NET_ACT''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_SEL_sup_net''
      ''  Validate=N''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=Y''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''NEW_SUB_NET_ACT''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_SEL_SUB_NET''
      ''  Validate=N''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=Y''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''NEW_CUSTOMER_NO''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_SEL_CUST_ID''
      ''  Validate=N''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=Y''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''NEW_WORK_SCOPE''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_SEL_WORKSCOPE_DEF_1''
      ''  Validate=N''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''NEW_SALES_ORDER''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PWUST_SEL_SALES_ORDER''
      ''  Validate=N''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''NEW_PLANED_ORDER''
      '' CtrlType=List''
      ''  Values=Y~N'')
    InsertSqlId = ''PWUST_createMroOverhaulOrders_1''
    ParamsSQLSourceName = ''@Auxparams''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ2~NEW_SALES_ORDER''
      ''_OBJ3~NEW_ENGINE_TYPE''
      ''_OBJ4~NEW_ENGINE_MODEL''
      ''_OBJ5~NEW_SAP_WORK_LOC'')
    MultiRecordTransaction = mruGroup
    DataDefinitions.Strings = (
      ''NEW_SALES_ORDER=''
      ''NEW_ENGINE_TYPE=''
      ''NEW_ENGINE_MODEL=''
      ''NEW_WORK_SCOPE=''
      ''PARTNO=''
      ''NEW_SUP_NET_ACT=''
      ''NEW_SUB_NET_ACT=''
      ''NEW_CUSTOMER_NO=''
      ''NEW_PLANED_ORDER=''
      ''NEW_SAP_WORK_LOC='')
    PassthroughParamsForInsert = False
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

