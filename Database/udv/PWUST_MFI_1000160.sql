
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_630787090F8F9496E05387971F0A03D3.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_630787090F8F9496E05387971F0A03D3';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_MFI_1000160';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Part Serial Lot InfoTop UDV, Delivery Package Top UDV SMRO_RPT_201;Defect655';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1260
  Height = 75
  AutoScroll = False
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
  AlignContents = acHorizontal
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
  object _OBJ1: TsfDBEdit
    Left = 400
    Top = 11
    Width = 170
    Height = 23
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 1
    Caption = ''Outgoing Sales Order No''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ2: TsfCommandButton
    Left = 80
    Top = 11
    Width = 180
    Height = 22
    Hint = ''Select Sales Order No''
    Caption = ''Select Sales Order No''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Select Sales Order No,Priv={},Visible={},TagValue=,FKey=,Pa'' +
        ''ramsSrc=@Default,Action=UDV,UDVType=Insert,UDVID=PWUST_630781ABB'' +
        ''3549685E05387971F0A2F85'')
  end
  object _OBJ9: TsfUDVNavigator
    Left = 8
    Top = 11
    Width = 39
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Edit ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ11: TsfBevel
    Left = 2
    Top = 3
    Width = 1252
    Height = 60
    Shape = bsFrame
    Hidden = ''False''
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object DISPLAY_REPORT: TsfCommandButton
    Left = 906
    Top = 11
    Width = 180
    Height = 22
    Hint = ''Reports''
    Caption = ''Reports''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        #39''Desc=Internal PDF of Order(s)''#39'',Priv={},Visible={},TagValue=,FKe'' +
        ''y=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName=@ToolSc'' +
        ''ope,RefreshedSQLSourceName=,SqlID=PWUST_INTERNALORDERREPORT''
      
        #39''Desc=External PDF of Order(s)''#39'',Priv={},Visible={},TagValue=,FKe'' +
        ''y=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName=@ToolSc'' +
        ''ope,RefreshedSQLSourceName=,SqlID=PWUST_EXTERNALORDERREPORT''
      
        ''Desc=Internal PDF of Delivery Package,Priv={},Visible={},TagValu'' +
        ''e=,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName=@'' +
        ''ToolScope,RefreshedSQLSourceName=,SqlID=PWUST_INTDELIVERYPACKAGE'' +
        ''REPORT''
      
        ''Desc=External PDF of Delivery Package,Priv={},Visible={},TagValu'' +
        ''e=,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName=@'' +
        ''ToolScope,RefreshedSQLSourceName=,SqlID=PWUST_EXTDELIBERYPACKAGE'' +
        ''REPORT'')
  end
  object OutGoingSaleOrder: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PartInfoPartSel''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''DISPLAY_REPORT~''
      ''_OBJ9~''
      ''_OBJ1~UCF_ORDER_VCH12'')
    ParamsSQLSourceName = ''DummySaleOrder,@ToolScope,@AuxParams''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''UCF_ORDER_VCH12~0~Outgoing Sales Order No~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~''
      ''PART_NO~0~PART_NO~N~N~False~False~False~N~~~~~Default~N~~''
      ''ITEM_TYPE~0~ITEM_TYPE~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''ITEM_SUBTYPE~0~ITEM_SUBTYPE~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~''
      ''SERIAL_NO~0~SERIAL_NO~N~N~False~False~False~N~~~~~Default~N~~''
      ''LOT_NO~0~LOT_NO~N~N~False~False~False~N~~~~~Default~N~~''
      ''SERIAL_ID~0~SERIAL_ID~N~N~False~False~False~N~~~~~Default~N~~''
      ''LOT_ID~0~LOT_ID~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''AS_WORKED_ITEM_STATUS~0~AS_WORKED_ITEM_STATUS~N~N~False~False~Fa'' +
        ''lse~N~~~~~Default~N~~''
      ''ORDER_TYPE~0~ORDER_TYPE~N~N~False~False~False~N~~~~~Default~N~~''
      ''LOT_QTY~0~LOT_QTY~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''LOT_SCRAP_QTY~0~LOT_SCRAP_QTY~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''LOT_STOP_QTY~0~LOT_STOP_QTY~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~''
      ''ORDER_ID~0~ORDER_ID~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''DISCREPANCY_TAB~0~DISCREPANCY_TAB~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~''
      ''HOLD_TAB~0~HOLD_TAB~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''AS_WORKED_BOM_TAB~0~AS_WORKED_BOM_TAB~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~''
      
        ''SCHED_END_DATE~0~SCHED_END_DATE~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~''
      
        ''ASSIGNED_TO~0~ASSIGNED_TO~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~''
      ''CONDITION~0~CONDITION~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''ACCUMULATED_FLIGHT_HOURS~0~ACCUMULATED_FLIGHT_HOURS~N~N~False~Fa'' +
        ''lse~False~N~~~~~Default~N~~''
      
        ''ACCUMULATED_FLIGHT_CYCLES~0~ACCUMULATED_FLIGHT_CYCLES~N~N~False~'' +
        ''False~False~N~~~~~Default~N~~''
      
        ''EXPIRATION_DATE~0~EXPIRATION_DATE~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~''
      
        ''SERIAL_FLAG~0~SERIAL_FLAG~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~'')
    SelectedFieldsEditControl.Strings = (
      ''ASSIGNED_TO~Edit'')
    TestParamValues.Strings = (
      ''UCF_ORDER_VCH12=TESTING''
      ''PART_NO=GLENN_OH_TEST''
      ''ORDER_ID=PWUST_CDD7FED6E5F6C84511F1A221A526FA87'')
    DataDefinitions.Strings = (
      ''UCF_ORDER_VCH12''
      ''PART_NO''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''SERIAL_NO''
      ''LOT_NO''
      ''SERIAL_ID''
      ''LOT_ID''
      ''AS_WORKED_ITEM_STATUS''
      ''ORDER_TYPE''
      ''LOT_QTY''
      ''LOT_SCRAP_QTY''
      ''LOT_STOP_QTY''
      ''ORDER_ID''
      ''DISCREPANCY_TAB''
      ''HOLD_TAB''
      ''AS_WORKED_BOM_TAB''
      ''SECURITY_GROUP''
      ''SCHED_END_DATE''
      ''ASSIGNED_TO''
      ''CONDITION''
      ''ACCUMULATED_FLIGHT_HOURS''
      ''ACCUMULATED_FLIGHT_CYCLES''
      ''EXPIRATION_DATE''
      ''SERIAL_FLAG'')
    ConsolidatedQuery = False
  end
  object DummyDataSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_DummyDataSelUnitInfo''
    ConsolidatedQuery = False
  end
  object DummySaleOrder: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_DummySaleUnit''
    ParamsSQLSourceName = ''@ToolScope,@AuxParams''
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

