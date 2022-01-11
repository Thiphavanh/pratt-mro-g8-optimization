
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_62E7E823A73E9CA4E05387971F0A6130.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_62E7E823A73E9CA4E05387971F0A6130';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_MultipleBadgeSwipe';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 450
  Height = 300
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Swipe Badge''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctSingle
  RequireLogonExpression = ''False''
  AutoFillEnabled = False
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  SizeToEndOfLine = False
  AutoRefresh = arDefault
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 450
  MaxHeight = 300
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = False
  BottomMargin = -1
  object TYPE_USERID: TsfCommandButton
    Left = 180
    Top = 65
    Width = 70
    Height = 25
    Caption = ''Type ID''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 0
    OtherCommands.Strings = (
      
        ''Desc=TypeUseridMultiple,Priv=''#39''{TYPE_FLAG = ''#39#39''ON''#39#39''}''#39'',Visible={},T'' +
        ''agValue=,FKey=,Action=BranchTo,BranchTo=TypeUseridMultiple'')
  end
  object BEVEL: TsfBevel
    Left = 0
    Top = 0
    Width = 450
    Height = 300
    Shape = bsFrame
    HighColor = ''0xA0A0A0''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object CLOCK_NO_LIST: TsfDBInputMemo
    Left = 180
    Top = 100
    Width = 70
    Height = 137
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    TabOrder = 1
    UseDockManager = True
    Version = ''2.5.1.0''
    Buffered = False
    Caption = ''''
    StatusBar.Font.Charset = DEFAULT_CHARSET
    StatusBar.Font.Color = clWindowText
    StatusBar.Font.Height = -11
    StatusBar.Font.Name = ''Tahoma''
    StatusBar.Font.Style = []
    Ctl3d = True
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    FullHeight = 137
  end
  object SWIPE_BADGE: TsfDBInputEdit
    Left = 180
    Top = 25
    Width = 70
    Height = 30
    BorderColor = clBlack
    EditType = etPassword
    EmptyTextStyle = []
    CanUndo = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 2
    EntryType = etAlphaNumeric
    Caption = '' Swipe Badge''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
    PasswordMode = True
  end
  object Done: TsfInputCheckBox
    Left = 270
    Top = 219
    Width = 168
    Height = 20
    Caption = ''Done Entering Multiple Workers''
    ReturnIsTab = False
    TabOrder = 3
    Version = ''1.4.0.3''
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Alignment = taLeftJustify
    Hidden = False
    PersistManualValues = False
  end
  object RFIDTest: TsfCommandButton
    Left = 55
    Top = 58
    Width = 100
    Height = 41
    Hint = ''Other commands''
    Caption = ''RFIDTest''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=TestRFID,Priv={},Visible={},TagValue=,FKey=,ParamsSrc=@Defa'' +
        ''ult,Action=ExecSql,ParamsSqlSourceName=@ToolScope,RefreshedSQLSo'' +
        ''urceName=,SqlID=PWUSD_RFIDPingCall'')
  end
  object SwipeBadge: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''SWIPED_BADGE_ID''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''CLOCK_NO_LIST''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Required=False''
      '' Hidden=False''
      ''MULTI_WORKER_FLAG''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MULTI_WORKER_FLAG = ''#39''Y''#39'' OR CHECKBOX = ''#39''OFF''#39
      '' Required=False''
      ''DONE_FLAG''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MULTI_WORKER_FLAG = ''#39''N''#39'' OR CHECKBOX = ''#39''OFF''#39
      '' Required=False''
      ''NULL'')
    InputFieldsEditControlSSL.Strings = (
      ''SWIPED_BADGE_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DONE_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''MULTI_WORKER_FLAG''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''CLOCK_NO_LIST''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    InsertSqlId = ''PWUST_NULL_SQL''
    ParamsSQLSourceName = ''@ScriptParams''
    RefreshParams = False
    LinkedControls.Strings = (
      ''TYPE_USERID~''
      ''CLOCK_NO_LIST~CLOCK_NO_LIST''
      ''SWIPE_BADGE~SWIPED_BADGE_ID''
      ''Done~DONE_FLAG'')
    DataDefinitions.Strings = (
      ''NULL'')
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

