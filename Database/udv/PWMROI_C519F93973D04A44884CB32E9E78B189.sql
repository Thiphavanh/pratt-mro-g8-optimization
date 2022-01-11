
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_C519F93973D04A44884CB32E9E78B189.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F6141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWMROI_C519F93973D04A44884CB32E9E78B189';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWMROI_RepairDeliveryOrderOpenTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Display WO''s Serial which are not delivered';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.0.1.20180226~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 300
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
    Left = 4
    Top = 25
    Width = 450
    Height = 200
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ2: TsfUDVNavigator
    Left = 4
    Top = 2
    Width = 134
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 1
  end
  object Diliver: TsfCommandButton
    Left = 143
    Top = 4
    Width = 100
    Height = 19
    Hint = ''Diliver''
    Caption = ''Deliver''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=DeliverSerial,Priv=''#39''{SERIAL_STATUS = ''#39#39''COMPLETE''#39#39''}''#39'',Visible'' +
        ''={},TagValue=FROM_TAB,FKey=,ParamsSrc=@Default,Action=ExecSql,''#39''P'' +
        ''aramsSqlSourceName=@ToolScope,repairWODeliveryOpen''#39'',RefreshedSQL'' +
        ''SourceName=,SqlID=PWMROI_initiateDelivery'')
  end
  object repairWODeliveryOpen: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWMROI_REPAIR_WO_DELIVERY_SERIAL_OPEN_SEL''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~''
      ''Diliver~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''SERIAL_NO~10~Serial No.~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SERIAL_STATUS~10~Serial No. Status~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''UCF_SERIAL_VCH10~10~Deliver Serial Status~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''
      ''SERIAL~0~SERIAL~N~N~True~False~True~N~~~~~Default~N~~~''
      
        ''ENGINE_MANUAL~10~Eng Manual~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''
      ''INTDT~10~Int. D/T~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''INT_RESPDT~10~Int. Resp D/T~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''
      
        ''INT_RESPONSE~10~Int. Response~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_SERIAL_VCH10~Edit''
      ''SERIAL_NO~Edit'')
    TestParamValues.Strings = (
      ''ORDER_ID=PWMROI_7B1FA346A8B3AAB26D0A45A694334D20'')
    DataDefinitions.Strings = (
      ''SERIAL_NO''
      ''SERIAL_STATUS''
      ''UCF_SERIAL_VCH10''
      ''SERIAL'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''repairWODeliveryOpen''
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

