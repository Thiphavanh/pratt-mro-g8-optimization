
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5AB1A7D4AD41C993E05387971F0A27DC.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5AB1A7D4AD41C993E05387971F0A27DC';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_ExportControl_JC_Valid';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SRMO_EXPT_201';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFFND';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 550
  Height = 300
  AutoScroll = False
  Caption = ''Validate Plan JC''
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
  object _OBJ1: TsfLabel
    Left = 147
    Top = 59
    Width = 217
    Height = 16
    Caption = '' Is the Current Plan JC still Valid? ''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = [fsBold]
    ShowHint = False
  end
  object _OBJ5: TScriptButton
    Left = 120
    Top = 196
    Width = 94
    Height = 32
    Caption = ''Yes''
    Visible = True
    sfButtonKind = sbkCustom
    GotoStatement = ''ExportCert''
    ExecuteUdv = True
  end
  object _OBJ6: TScriptButton
    Left = 277
    Top = 196
    Width = 94
    Height = 32
    Caption = ''No''
    Visible = True
    sfButtonKind = sbkCustom
    GotoStatement = ''NewRTCDecision''
    ExecuteUdv = True
  end
  object _OBJ7: TsfDBInputEdit
    Left = 47
    Top = 109
    Width = 88
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 0
    EntryType = etAlphaNumeric
    Caption = ''Jurisdiction''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = [fsBold]
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ8: TsfDBInputEdit
    Left = 158
    Top = 109
    Width = 108
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 1
    EntryType = etAlphaNumeric
    Caption = ''Classification''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = [fsBold]
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ9: TsfDBInputEdit
    Left = 289
    Top = 109
    Width = 88
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 2
    EntryType = etAlphaNumeric
    Caption = ''SME''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = [fsBold]
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ10: TsfDBInputEdit
    Left = 400
    Top = 109
    Width = 108
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = ''Record Number''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = [fsBold]
    Hidden = False
    EnterForOkay = False
  end
  object GetJCPassThruTrans: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''NULL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''JURISD''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''CLASSIF''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''SME''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''REC_NO''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
      ''JURISD''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CLASSIF''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SME''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''NULL''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''REC_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    InsertSqlId = ''PWUST_NULL_SQL''
    ParamsSQLSourceName = ''GetJCData''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ7~JURISD''
      ''_OBJ8~CLASSIF''
      ''_OBJ10~REC_NO''
      ''_OBJ9~SME'')
    DataDefinitions.Strings = (
      ''NULL='')
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

