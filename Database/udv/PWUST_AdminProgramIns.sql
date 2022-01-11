
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5CE16B87699DE12DE05387971F0A67C0.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5CE16B87699DE12DE05387971F0A67C0';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_AdminProgramIns';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect126';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFCORE';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 758
  Height = 427
  AutoScroll = False
  Caption = ''Insert Program''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = 0
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
  object _OBJ1: TsfDBInputEdit
    Left = 135
    Top = 44
    Width = 143
    Height = 25
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
    Caption = ''Program*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ2: TsfDBInputEdit
    Left = 135
    Top = 77
    Width = 396
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
    EntryType = etAlphaNumeric
    Caption = ''Description''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object AdminProgramInput: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''PROGRAM_DESC''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=255''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PROGRAM''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''OBSOLETE_RECORD_FLAG''
      '' Update=N''
      '' Insert=N''
      '' PopulateForInsert=Y''
      '' MaxLength=1''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' Hidden=N''
      '' Required=N''
      ''UCF_PROGRAM_VCH1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PROGRAM_VCH2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PROGRAM_FLAG1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_PROGRAM_VCH3''
      ''UCF_PROGRAM_VCH4''
      ''UCF_PROGRAM_VCH5''
      ''UCF_PROGRAM_VCH6''
      ''UCF_PROGRAM_VCH7''
      ''UCF_PROGRAM_VCH8''
      ''UCF_PROGRAM_VCH9''
      ''UCF_PROGRAM_VCH10''
      ''UCF_PROGRAM_VCH11''
      ''UCF_PROGRAM_VCH12''
      ''UCF_PROGRAM_VCH13''
      ''UCF_PROGRAM_VCH14''
      ''UCF_PROGRAM_VCH15''
      ''UCF_PROGRAM_NUM1''
      ''UCF_PROGRAM_NUM2''
      ''UCF_PROGRAM_NUM3''
      ''UCF_PROGRAM_NUM4''
      ''UCF_PROGRAM_NUM5''
      ''UCF_PROGRAM_DATE1''
      ''UCF_PROGRAM_DATE2''
      ''UCF_PROGRAM_DATE3''
      ''UCF_PROGRAM_DATE4''
      ''UCF_PROGRAM_DATE5''
      ''UCF_PROGRAM_FLAG2''
      ''UCF_PROGRAM_FLAG3''
      ''UCF_PROGRAM_FLAG4''
      ''UCF_PROGRAM_FLAG5''
      ''UCF_PROGRAM_VCH255_1''
      ''UCF_PROGRAM_VCH255_2''
      ''UCF_PROGRAM_VCH255_3''
      ''UCF_PROGRAM_VCH4000_1''
      ''UCF_PROGRAM_VCH4000_2'')
    InputFieldsEditControlSSL.Strings = (
      ''UCF_PROGRAM_VCH1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PROGRAM_VCH2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_PROGRAM_FLAG1''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''PROGRAM_DESC''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PROGRAM''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Format=1''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    InsertSqlId = ''AdminProgramInsert''
    UpdateSqlId = ''AdminProgramUpdate''
    ParamsSQLSourceName = ''@ToolScope''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ1~PROGRAM''
      ''_OBJ2~PROGRAM_DESC''
      ''UCF_PROGRAM_VCH1~UCF_PROGRAM_VCH1''
      ''UCF_PROGRAM_VCH2~UCF_PROGRAM_VCH2''
      ''ucf_program_flag1~UCF_PROGRAM_FLAG1'')
    DataDefinitions.Strings = (
      ''PROGRAM_DESC=''
      ''PROGRAM=''
      ''OBSOLETE_RECORD_FLAG=''
      ''UCF_PROGRAM_VCH1=''
      ''UCF_PROGRAM_VCH2=''
      ''UCF_PROGRAM_FLAG1=''
      ''PROGRAM=''
      ''OBSOLETE_RECORD_FLAG''
      ''UCF_PROGRAM_VCH3''
      ''UCF_PROGRAM_VCH4''
      ''UCF_PROGRAM_VCH5''
      ''UCF_PROGRAM_VCH6''
      ''UCF_PROGRAM_VCH7''
      ''UCF_PROGRAM_VCH8''
      ''UCF_PROGRAM_VCH9''
      ''UCF_PROGRAM_VCH10''
      ''UCF_PROGRAM_VCH11''
      ''UCF_PROGRAM_VCH12''
      ''UCF_PROGRAM_VCH13''
      ''UCF_PROGRAM_VCH14''
      ''UCF_PROGRAM_VCH15''
      ''UCF_PROGRAM_NUM1''
      ''UCF_PROGRAM_NUM2''
      ''UCF_PROGRAM_NUM3''
      ''UCF_PROGRAM_NUM4''
      ''UCF_PROGRAM_NUM5''
      ''UCF_PROGRAM_DATE1''
      ''UCF_PROGRAM_DATE2''
      ''UCF_PROGRAM_DATE3''
      ''UCF_PROGRAM_DATE4''
      ''UCF_PROGRAM_DATE5''
      ''UCF_PROGRAM_FLAG2''
      ''UCF_PROGRAM_FLAG3''
      ''UCF_PROGRAM_FLAG4''
      ''UCF_PROGRAM_FLAG5''
      ''UCF_PROGRAM_VCH255_1''
      ''UCF_PROGRAM_VCH255_2''
      ''UCF_PROGRAM_VCH255_3''
      ''UCF_PROGRAM_VCH4000_1''
      ''UCF_PROGRAM_VCH4000_2'')
    PassthroughParamsForInsert = False
  end
  object UCF_PROGRAM_VCH1: TsfDBInputEdit
    Left = 135
    Top = 110
    Width = 145
    Height = 25
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
    Caption = ''Lab''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object UCF_PROGRAM_VCH2: TsfDBInputEdit
    Left = 135
    Top = 143
    Width = 147
    Height = 25
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
  object ucf_program_flag1: TsfDBInputEdit
    Left = 135
    Top = 176
    Width = 40
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    EntryType = etAlphaNumeric
    Caption = ''Program FLAG1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object TOVCController
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

