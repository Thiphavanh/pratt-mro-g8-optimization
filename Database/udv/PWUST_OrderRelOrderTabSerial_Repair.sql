
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_873616B66C0099A1E05387971F0AE4D5.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_873616B66C0099A1E05387971F0AE4D5';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderRelOrderTabSerial_Repair';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 1509, defect 1392,1144';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 700
  Height = 286
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
    Left = 0
    Top = 8
    Width = 208
    Height = 201
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
  object _OBJ2: TsfDBGrid
    Left = 190
    Top = 30
    Width = 550
    Height = 157
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ3: TsfUDVNavigator
    Left = 0
    Top = -12
    Width = 39
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 2
    TabStop = True
  end
  object _OBJ4: TsfUDVNavigator
    Left = 200
    Top = 6
    Width = 115
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 3
    TabStop = True
  end
  object _OBJ5: TsfLabel
    Left = 331
    Top = 8
    Width = 107
    Height = 13
    Caption = ''Repair Related Orders''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ6: TsfLabel
    Left = 56
    Top = 8
    Width = 66
    Height = 13
    Caption = ''Serial Number''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ7: TsfCommandButton
    Left = 448
    Top = 6
    Width = 100
    Height = 21
    Hint = ''Display Work Order''
    Caption = ''Display Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Display Work Order,Priv={},Visible={},TagValue=,FKey=,Param'' +
        ''sSrc=@Default,Action=Invoke,Tool=MfgInstructions,ReportToolId=,P'' +
        ''arams(''#39''ORDER_ID = :ORDER_ID,@InvokeToolMode=EDITMODES.Edit_PL''#39'')'')
  end
  object OrderSerialSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderSerialSel''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ3~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''SERIAL_NO~10~Serial No~N~N~N~N~False~N~~~~~Default~N~''
      ''LOT_NO~10~Lot No~N~N~N~N~False~N~~~~~Default~Y~'')
    SelectedFieldsEditControl.Strings = (
      ''SERIAL_NO~Edit'')
    DataDefinitions.Strings = (
      ''SERIAL_NO''
      ''LOT_NO'')
    ConsolidatedQuery = False
  end
  object OrderRelatedOrderSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_OrderRelatedOrderSelRepair''
    LinkedControls.Strings = (
      ''_OBJ4~''
      ''_OBJ2~''
      ''_OBJ7~'')
    ParamsSQLSourceName = ''@ToolScope,OrderSerialSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_TYPE~8~Order Type~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''SUB_ORDER_TYPE~20~Sub-Order Type~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      ''PW_ORDER_NO~20~Order No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''NOTE_TEXT~10~Notes~N~N~False~False~False~N~~~~~Default~N~~~''
      ''ORDER_QTY~6~Order Qty~N~N~N~N~False~N~~~~~Default~N~~~''
      ''ORDER_STATUS~12~Order Status~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''ORDER_HOLD_STATUS~12~Order Hold Status~N~N~N~N~False~N~~~~~Defau'' +
        ''lt~N~~~''
      
        ''UPDT_USERID~12~Update User Id~N~N~N~N~False~N~MFI_2869~~~~Defaul'' +
        ''t~N~~~''
      ''TIME_STAMP~15~Update Time~N~N~N~N~False~N~~~~~Default~Y~~~''
      
        ''ASASGND_WORK_LOC~0~ASASGND_WORK_LOC~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      ''ORDER_ID~0~ORDER_ID~N~N~True~False~False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''ORDER_STATUS~Edit''
      ''TIME_STAMP~Edit''
      ''OPER_NO~Edit''
      ''ORDER_TYPE~Edit'')
    TestParamValues.Strings = (
      ''ORDER_ID=PWUST_A5173907229570513A8FFBAF46929E9E''
      ''SALES_ORDER=01234567890123456789''
      ''PART_NO=12345''
      ''UCF_ORDER_VCH12=''
      ''REPAIR_MULTI_ORDER_KEY=1'')
    OtherCommands.Strings = (
      
        ''Desc=Display Order,Priv={RECORD_FOUND = ''#39''Y''#39''},Visible={},TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName=Or'' +
        ''derRelatedOrderSel,RefreshedSQLSourceName=,SqlID=MFI_WID_ORDERRE'' +
        ''LATEDORDERDISP'')
    DataDefinitions.Strings = (
      ''ORDER_TYPE''
      ''SUB_ORDER_TYPE''
      ''PW_ORDER_NO''
      ''ORDER_QTY''
      ''ORDER_STATUS''
      ''ORDER_HOLD_STATUS''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''ASASGND_WORK_LOC''
      ''SECURITY_GROUP''
      ''ORDER_ID''
      ''NOTE_TEXT'')
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

