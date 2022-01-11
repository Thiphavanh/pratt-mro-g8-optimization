
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6A40D571E8755443E05387971F0A3173.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6A40D571E8755443E05387971F0A3173';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_DP_DeliveryPackage';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_RPT_201; Defect 774; Defect 773; Defect 783';
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
  Height = 900
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
    Left = 330
    Top = 12
    Width = 170
    Height = 23
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -13
    Font.Name = ''Tahoma''
    Font.Style = [fsBold]
    ParentFont = False
    ParentShowHint = False
    ShowHint = True
    TabOrder = 1
    Caption = ''Sales Order No''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ2: TsfCommandButton
    Left = 94
    Top = 13
    Width = 115
    Height = 22
    Hint = ''Select Sales Order No''
    Caption = ''Select Sales Order No''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Select Sales Order No,Priv={},Visible={},TagValue=,FKey=,Pa'' +
        ''ramsSrc=@Default,Action=UDV,UDVType=Insert,UDVID=PWUST_6A3909E1F'' +
        ''814F6FBE05387971F0AC171'')
  end
  object _OBJ9: TsfUDVNavigator
    Left = 8
    Top = 15
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
    Height = 43
    Shape = bsFrame
    Hidden = ''False''
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object DISPLAY_REPORT: TsfCommandButton
    Left = 551
    Top = 13
    Width = 115
    Height = 22
    Hint = ''Reports''
    Caption = ''Reports''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        #39''Desc=Internal PDF of Order(s)''#39'',Priv={1=1},Visible={1=1},TagValu'' +
        ''e=1,FKey=,ParamsSrc=WorkOrderGrid,Action=UDV,UDVType=GroupInsert'' +
        '',FieldName=ORDER_NO,Delim=;,UDVID=PWUST_68B51C32A390F164E0538797'' +
        ''1F0ADA16''
      
        #39''Desc=External PDF of Order(s)''#39'',Priv={1=1},Visible={1=1},TagValu'' +
        ''e=2,FKey=,ParamsSrc=WorkOrderGrid,Action=UDV,UDVType=GroupInsert'' +
        '',FieldName=ORDER_NO,Delim=;,UDVID=PWUST_68B51C32A390F164E0538797'' +
        ''1F0ADA16''
      
        ''Desc=Internal PDF of Delivery Package,Priv={1=1},Visible={1=1},T'' +
        ''agValue=3,FKey=,ParamsSrc=WorkOrderGrid,Action=UDV,UDVType=Group'' +
        ''Insert,FieldName=ORDER_NO,Delim=;,UDVID=PWUST_68B51C32A390F164E0'' +
        ''5387971F0ADA16''
      
        ''Desc=External PDF of Delivery Package,Priv={1=1},Visible={1=1},T'' +
        ''agValue=4,FKey=,ParamsSrc=WorkOrderGrid,Action=UDV,UDVType=Group'' +
        ''Insert,FieldName=ORDER_NO,Delim=;,UDVID=PWUST_68B51C32A390F164E0'' +
        ''5387971F0ADA16'')
  end
  object _OBJ12: TsfDBGrid
    Left = 2
    Top = 52
    Width = 1252
    Height = 800
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 4
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object WorkOrderGrid: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_DP_GetWorkOrders''
    LinkedControls.Strings = (
      ''_OBJ9~''
      ''DISPLAY_REPORT~''
      ''_OBJ12~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_NO~0~;Order No~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''HOLD_STATUS~5~Hold;Status~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~''
      ''PART_NO~12~;Part No~N~N~False~False~False~N~~~~~Default~N~~''
      ''SERIAL_NO~12~;Serial No~N~N~False~False~False~N~~~~~Default~N~~''
      ''LOT_NO~10~;Lot No~N~N~False~False~False~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''WORK_ORDER_NO~Edit'')
    DataDefinitions.Strings = (
      ''ORDER_NO''
      ''HOLD_STATUS''
      ''PART_NO''
      ''SERIAL_NO''
      ''LOT_NO'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''WorkOrderGrid''
  end
  object SalesOrder_Sel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_DP_OutSalesOrder_Sel''
    LinkedControls.Strings = (
      ''_OBJ1~SALES_ORDER'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''UCF_ORDER_VCH12~0~UCF_ORDER_VCH12~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_ORDER_VCH12~Edit'')
    DataDefinitions.Strings = (
      ''UCF_ORDER_VCH12'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''SalesOrder_Sel''
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

