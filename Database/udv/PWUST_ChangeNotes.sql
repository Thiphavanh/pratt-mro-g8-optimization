
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5658B87029B85095E05387971F0A415F.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5658B87029B85095E05387971F0A415F';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_ChangeNotes';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_APP_201';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1058
  Height = 597
  AutoScroll = False
  Caption = ''Update Current Task Status''
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
  AutoRefresh = arRefresh
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
    Left = 87
    Top = 136
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
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ4: TsfDBInputMemo
    Left = 87
    Top = 175
    Width = 427
    Height = 141
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    PopupMenu = UDVEditForm.UDVControlPopupMenu
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
    MemoCaption = ''Change;Notes*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    WordWrap = False
    ParentCtl3D = False
    FullHeight = 141
  end
  object _OBJ8: TsfDBInputEdit
    Left = 87
    Top = 430
    Width = 172
    Height = 24
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = ''Task ID''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object QUEUE_TYPE: TsfDBInputEdit
    Left = 87
    Top = 97
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
  object _OBJ9: TsfDBInputEdit
    Left = 87
    Top = 58
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
    TabOrder = 4
    EntryType = etAlphaNumeric
    Caption = ''Item No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ10: TsfDBInputEdit
    Left = 342
    Top = 58
    Width = 51
    Height = 24
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 5
    EntryType = etAlphaNumeric
    Caption = ''Version''
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
    Left = 463
    Top = 58
    Width = 51
    Height = 24
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 6
    EntryType = etAlphaNumeric
    Caption = ''Revision''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ13: TsfDBInputEdit
    Left = 342
    Top = 97
    Width = 172
    Height = 24
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 7
    EntryType = etAlphaNumeric
    Caption = ''Item Type''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ14: TsfDBInputEdit
    Left = 342
    Top = 136
    Width = 172
    Height = 24
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 8
    EntryType = etAlphaNumeric
    Caption = ''Item Subtype''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object APPROVER_NOTIFICATION: tSFInputListGrid
    Left = 87
    Top = 331
    Width = 150
    Height = 75
    TabStop = False
    Color = clBtnFace
    ColCount = 2
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssNone
    TabOrder = 9
    Delimiter = '';''
    ParseAvailableItems = True
    AllowDuplicates = False
    ShowNew = True
    ReadOnly = False
    Caption = ''Approver;Noification;Selectino''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
  end
  object PlanTaskUpdate: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''STATUS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=12''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''TASK_ID''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=True''
      '' Required=False''
      ''TASK_TYPE''
      ''QUEUE_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      ''DOC_TYPE''
      ''PLAN_ID''
      ''PLAN_VERSION''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''PLAN_REVISION''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''PLAN_ALTERATIONS''
      ''REV_STATUS''
      ''CHANGE_LEVEL''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''@TEXTMODIFIED''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_PLAN_VCH4000_1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''NOTIFICATION_MATRIX''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=PLAN_REVISION=''#39''1''#39'' AND NOT @HASPRIV(''#39''DEV_EX''#39'')'')
    InputFieldsEditControlSSL.Strings = (
      ''QUEUE_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TASK_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_VERSION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_REVISION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CHANGE_LEVEL''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''@TEXTMODIFIED''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STATUS''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_ValidStatusChangeSel''
      
        ''  ParamsSrc=@TOOLSCOPE,PlanTaskUpdtIDSel,PlanRevStatus,@Auxparam'' +
        ''s''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''NOTIFICATION_MATRIX''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_SelectIndividualApprovers''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   PRIV=Privilege''
      ''   FULL_NAME=Full Name''
      ''   USERID=Userid''
      ''  Style=Short''
      ''   Static=N''
      ''UCF_PLAN_VCH4000_1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    UpdateSqlId = ''PWUST_MFI_PLAN_TASK_UPDATE_PRE''
    ParamsSQLSourceName = ''PlanTaskUpdtIDSel,PlanRevStatus,@ToolScope''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ2~STATUS''
      ''_OBJ8~TASK_ID''
      ''_OBJ9~PART_NO''
      ''_OBJ10~PLAN_VERSION''
      ''_OBJ11~PLAN_REVISION''
      ''_OBJ14~ITEM_SUBTYPE''
      ''_OBJ13~ITEM_TYPE''
      ''QUEUE_TYPE~QUEUE_DEF''
      ''_OBJ4~UCF_PLAN_VCH4000_1''
      ''APPROVER_NOTIFICATION~NOTIFICATION_MATRIX'')
    DataDefinitions.Strings = (
      ''STATUS=''
      ''TASK_TYPE''
      ''STATUS=''
      ''TASK_ID=''
      ''QUEUE_TYPE=''
      ''DOC_TYPE''
      ''PLAN_ID''
      ''PLAN_VERSION=''
      ''PLAN_REVISION=''
      ''PLAN_ALTERATIONS''
      ''REV_STATUS''
      ''@TEXTMODIFIED=''
      ''UCF_PLAN_VCH4000_1=''
      ''NOTIFICATION_MATRIX='')
  end
  object PlanTaskUpdtIDSel: TsfTransactionParamSource
    SelectSqlId = ''PWUST_PlanTaskUpdtIDSel''
    ParamsSQLSourceName = ''PlanRevStatus,@AuxParams''
    PublishParams = True
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

