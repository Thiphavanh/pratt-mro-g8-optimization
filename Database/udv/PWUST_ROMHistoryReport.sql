
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_849AFE9BF11FD8FCE05387971F0A2619.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_849AFE9BF11FD8FCE05387971F0A2619';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_ROMHistoryReport';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_RPT_306';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 975
  Height = 360
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctPanel
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
  object _OBJ1: TsfDBGrid
    Left = 8
    Top = 94
    Width = 950
    Height = 215
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ShowHint = True
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''MS Sans Serif''
    TitleFont.Style = [fsBold]
    Highlighting.Strings = (
      ''@Row~Default~Bold~print_flag=''#39''Y''#39
      ''@Row~Red~Default~print_flag=''#39''W''#39
      ''@Row~Red~Bold~print_flag=''#39''E''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ2: TsfUDVNavigator
    Left = 8
    Top = 71
    Width = 39
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object _OBJ3: TsfLabel
    Left = 318
    Top = 8
    Width = 359
    Height = 29
    Alignment = taCenter
    Caption = ''Repair Data Collection History''
    ParentFont = False
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -24
    Font.Name = ''Tahoma''
    Font.Style = [fsBold]
    ShowHint = False
  end
  object _OBJ4: TsfDBEdit
    Left = 282
    Top = 41
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 2
    Text = ''''
    Caption = ''Sales Order Number''
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = 12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = [fsBold]
    Hidden = False
  end
  object _OBJ5: TsfDBEdit
    Left = 514
    Top = 41
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 3
    Text = ''''
    Caption = ''Serial Number''
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = 12
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = [fsBold]
    Hidden = False
  end
  object PWUST_ROMHistorySelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_ROMHistorySelect''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope,@Auxparams''
    PublishParams = True
    SelectedFields.Strings = (
      ''PART_NO~20~Part Number~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''DAT_COL_TYPE~3~Data Collection;Type~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''DAT_COL_TITLE~15~Data Collection Title~N~N~False~False~False~N~~'' +
        ''~~~Default~N~~~''
      ''DC_TIME~12~Time~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''MEASUREMENT~15~Measurement~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''MEASUREMENT_REMARK~20~Measurement;Remark~N~N~False~False~False~N'' +
        ''~~~~~Default~N~~~''
      
        ''SERVICEABLE_LIMIT_USED~1~Serviceable;Limit Used~N~N~False~False~'' +
        ''False~N~~~~~Default~N~~~''
      ''ORDER_NO~20~SMOrder No~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OPER_NO~4~Oper No~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''ENGINEERING_REMARKS~25~Engineering;Remarks~N~N~False~False~False'' +
        ''~N~~~~~Default~N~~~''
      ''USERID~8~User~N~N~False~False~False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''DC_SERVICEABLE~Edit''
      ''CHARACTERISTIC_ID~Edit''
      ''OPER_NO~Edit''
      ''PART_NO~Edit'')
    TestParamValues.Strings = (
      ''ORDER_ID=PWUST_8573BF2E05BDA587E15C56DD0A0BD703''
      ''ORDER_NO=WO0000159''
      ''PLAN_ID=PWUST_691237B1B813144BAFA0560338671C3''
      ''PLAN_REV=10''
      ''PART_NO=DREW_TEST_SERIAL1''
      ''SERIAL_ID=PWUST_C886C4C0D83A4064E88FAE0C4CE34840'')
    DataDefinitions.Strings = (
      ''PART_NO''
      ''DAT_COL_TYPE''
      ''DAT_COL_TITLE''
      ''DC_TIME''
      ''MEASUREMENT''
      ''MEASUREMENT_REMARK''
      ''SERVICEABLE_LIMIT_USED''
      ''ORDER_NO''
      ''OPER_NO''
      ''ENGINEERING_REMARKS''
      ''USERID'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''PWUE_ROM_HISTORY''
  end
  object PWUST_ROMHistoryDummy: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_ROMHistoryDummy''
    LinkedControls.Strings = (
      ''_OBJ4~SALES_ORDER''
      ''_OBJ5~SERIAL_NO'')
    ParamsSQLSourceName = ''@ToolScope,@Auxparams''
    SelectedFields.Strings = (
      
        ''SALES_ORDER~20~SALES_ORDER~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      ''SERIAL_NO~20~SERIAL_NO~N~N~False~False~False~N~~~~~Default~N~~~''
      ''ORDER_ID~0~ORDER_ID~N~N~True~False~False~N~~~~~Default~N~~~''
      ''ORDER_NO~0~ORDER_NO~N~N~True~False~False~N~~~~~Default~N~~~''
      ''PLAN_ID~0~PLAN_ID~N~N~True~False~False~N~~~~~Default~N~~~''
      ''PLAN_REV~0~PLAN_REV~N~N~True~False~False~N~~~~~Default~N~~~''
      ''PART_NO~0~PART_NO~N~N~True~False~False~N~~~~~Default~N~~~''
      ''SERIAL_ID~0~SERIAL_ID~N~N~False~False~False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''SERIAL_NO~Edit'')
    TestParamValues.Strings = (
      ''sales_order=RAEANN''
      ''serial_no=ORDER159_SN''
      ''order_id=PWUST_8573BF2E05BDA587E15C56DD0A0BD703''
      ''order_no=WO0000159''
      ''plan_id=PWUST_691237B1B813144BAFA0560338671C3''
      ''plan_rev=10''
      ''part_no=DREW_TEST_SERIAL1''
      ''serial_id=sssssssssss'')
    DataDefinitions.Strings = (
      ''SALES_ORDER''
      ''SERIAL_NO''
      ''ORDER_ID''
      ''ORDER_NO''
      ''PLAN_ID''
      ''PLAN_REV''
      ''PART_NO'')
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

