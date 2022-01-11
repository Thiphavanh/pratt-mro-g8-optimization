
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6A88C0CC210CFC2EE05387971F0A7AAD.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6A88C0CC210CFC2EE05387971F0A7AAD';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_StdOperUpdateTask';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 680';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 295
  AutoScroll = False
  Caption = ''Update  Status''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctSingle
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
    Left = 138
    Top = 138
    Width = 171
    Height = 24
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
    Caption = ''Status''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object StdOperUpdateTask: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''STATUS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=12''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''TASK_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''NOTES''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''TASK_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''QUEUE_TYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=N''
      '' Required=N''
      ''@TEXTMODIFIED''
      ''DOC_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''ASGND_DEPARTMENT_ID''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''ASGND_CENTER_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
      ''NOTES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''TASK_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''TASK_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''QUEUE_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''DOC_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STATUS''
      '' CtrlType=Lookup''
      ''  SqlId=PL_VALID_STATE_CHG''
      ''  ParamsSrc=@TOOLSCOPE,PlanTaskUpdtIDSel''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   TO_STATUS=''
      ''  Style=Short''
      ''   Static=N''
      ''ASGND_DEPARTMENT_ID''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_DEPTIDLOOKUP''
      ''  ParamsSrc=@ToolScope,@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   TO_STATUS=''
      ''  Style=Short''
      ''   Static=N''
      ''ASGND_CENTER_ID''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_CENTIDLOOKUP''
      ''  ParamsSrc=@ToolScope,@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   TO_STATUS=''
      ''  Style=Short''
      ''   Static=N'')
    InsertSqlId = ''PLAN_TASK_UPDATE''
    UpdateSqlId = ''PWUST_PLAN_TASK_UPDATE''
    ParamsSQLSourceName = ''@ToolScope,PlanTaskUpdtIDSel''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ2~STATUS''
      ''_OBJ8~TASK_ID''
      ''_OBJ4~NOTES''
      ''QUEUE_TYPE~QUEUE_DEF''
      ''_OBJ11~STDOPER_REV'')
    DataDefinitions.Strings = (
      ''STATUS=''
      ''TASK_ID=''
      ''NOTES=''
      ''TASK_TYPE=''
      ''QUEUE_TYPE=''
      ''TASK_TYPE''
      ''@TEXTMODIFIED''
      ''DOC_TYPE=''
      ''ASGND_DEPARTMENT_ID=''
      ''ASGND_CENTER_ID='')
  end
  object _OBJ4: TsfDBInputMemo
    Left = 138
    Top = 181
    Width = 441
    Height = 77
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    TabOrder = 2
    UseDockManager = True
    Version = ''1.7.7.6''
    Buffered = False
    StatusBar.Font.Charset = DEFAULT_CHARSET
    StatusBar.Font.Color = clWindowText
    StatusBar.Font.Height = -11
    StatusBar.Font.Name = ''Tahoma''
    StatusBar.Font.Style = []
    Ctl3d = True
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    WordWrap = False
    ParentCtl3D = False
    FullHeight = 77
  end
  object _OBJ8: TsfDBInputEdit
    Left = 444
    Top = 57
    Width = 1
    Height = 19
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
    Caption = ''_OBJ8''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object PlanTaskUpdtIDSel: TsfTransactionParamSource
    SelectSqlId = ''PlanTaskUpdtIDSel''
    ParamsSQLSourceName = ''@TOOLSCOPE,@AuxParams''
    PublishParams = True
  end
  object QUEUE_TYPE: TsfDBInputEdit
    Left = 138
    Top = 95
    Width = 171
    Height = 24
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 0
    EntryType = etAlphaNumeric
    Caption = ''Queue Type''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ11: TsfDBInputEdit
    Left = 138
    Top = 52
    Width = 60
    Height = 24
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    EntryType = etAlphaNumeric
    Caption = ''Std Oper Revision''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ12: TsfLabel
    Left = 100
    Top = 181
    Width = 28
    Height = 13
    Alignment = taRightJustify
    Caption = ''Notes''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
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

