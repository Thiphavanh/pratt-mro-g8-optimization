
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_8F610828CAD76EFBE05387971F0AC29E.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_8F610828CAD76EFBE05387971F0AC29E';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_DiscItemDisplacement';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_QA_201; Defect 1321;1723';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 608
  Height = 80
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = 0
  IMPopulateFromParams = False
  UdvControlType = uctPanel
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acAsIs
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
  object _OBJ2: TsfDBEdit
    Left = 4
    Top = 45
    Width = 142
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 0
    Text = ''''
    Caption = ''Disposition Order No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ3: TsfDBEdit
    Left = 308
    Top = 45
    Width = 142
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 1
    Text = ''''
    Caption = ''Append to Oper No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ4: TsfDBEdit
    Left = 460
    Top = 45
    Width = 142
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 2
    Text = ''''
    Caption = ''Append to Section''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ5: TsfUDVNavigator
    Left = 4
    Top = 5
    Width = 20
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Edit '')
    ShowHint = True
    TabOrder = 3
    TabStop = True
  end
  object _OBJ6: TsfDBEdit
    Left = 156
    Top = 45
    Width = 142
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    Text = ''''
    Caption = ''Disposition Order Status''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ7: TsfCommandButton
    Left = 30
    Top = 5
    Width = 180
    Height = 21
    Hint = ''Display Disposition Work Order''
    Caption = ''Display Disposition Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Display Disposition Work Order,Priv=''#39''{DISP_ORDER_ID<>''#39#39#39#39''}''#39 +
        '',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Action=Invoke,Too'' +
        ''l=MfgInstructions,ReportToolId=,Params(''#39''ORDER_ID = :DISP_ORDER_I'' +
        ''D,''#39'',@InvokeToolMode=EDITMODES.Edit_PL)'')
  end
  object InfoSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_DiscItemDispInfoSel''
    LinkedControls.Strings = (
      ''_OBJ2~ORDER_NO''
      ''_OBJ4~STEP_NO''
      ''_OBJ5~''
      ''_OBJ3~APPEND_TO_OPER_NO''
      ''_OBJ6~ORDER_STATUS''
      ''_OBJ7~'')
    ParamsSQLSourceName = ''@currentmarker, DispInstrTypeSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_NO~0~ORDER_NO~N~N~False~False~False~N~~~~~Default~N~''
      
        ''STEP_NO~0~STEP_NO~N~N~@GetParamSourceValue(DispInstrTypeSel:@Ins'' +
        ''tanceSerial,''#39''DISP_INSTR_TYPE''#39'') <> ''#39''Append to Operation''#39''~False~Fa'' +
        ''lse~N~~~~~Default~N~''
      
        ''OPER_OPER_KEY~0~OPER_OPER_KEY~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''STEP_STEP_KEY~0~STEP_STEP_KEY~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''DISP_INSTR_TYPE~0~DISP_INSTR_TYPE~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''APPEND_TO_OPER_NO~0~APPEND_TO_OPER_NO~N~N~@GetParamSourceValue(D'' +
        ''ispInstrTypeSel:@InstanceSerial,''#39''DISP_INSTR_TYPE''#39'') <> ''#39''Append to'' +
        '' Operation''#39''~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''STEP_STEP_KEY~Edit''
      ''APPEND_TO_OPER_NO~Edit''
      ''STEP_NO~Edit'')
    TestParamValues.Strings = (
      ''disc_id=DISC0014145''
      ''disc_line_no=1'')
    InputUDVId = ''MFI_99B3D96C13D34ACE96E66AE2320D4641''
    InsertEnabledExpr = 
      ''(DISP_ORDER_ID = ''#39#39'' OR DISP_INSTR_TYPE = ''#39''Append to Operation''#39'') '' +
      ''AND DISC_LINE_STATUS<>''#39''DISPOSITIONED''#39'' AND DISC_LINE_STATUS <>''#39''IM'' +
      ''PLEMENTED''#39
    UpdateEnabledExpr = 
      ''(DISP_ORDER_ID = ''#39#39'' OR DISP_INSTR_TYPE = ''#39''Append to Operation''#39'') '' +
      ''AND DISC_LINE_STATUS<>''#39''DISPOSITIONED''#39'' AND DISC_LINE_STATUS <>''#39''IM'' +
      ''PLEMENTED''#39
    DataDefinitions.Strings = (
      ''ORDER_NO''
      ''STEP_NO''
      ''OPER_OPER_KEY''
      ''STEP_STEP_KEY''
      ''DISP_INSTR_TYPE''
      ''APPEND_TO_OPER_NO'')
    ConsolidatedQuery = False
  end
  object DispInstrTypeSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_DispInstrTypeSel''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''DISP_INSTR_TYPE~0~DISP_INSTR_TYPE~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~'')
    TestParamValues.Strings = (
      ''disc_id=DISC0014145''
      ''disc_line_no=1'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''DispInstrTypeSel''
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

