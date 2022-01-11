
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_8488CEE5616BCCCCE05387971F0A6F89.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_8488CEE5616BCCCCE05387971F0A6F89';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_INT_MULTI_REPAIR_ORDER_DATA';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_310,Defect1395';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.0.20190207~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1000
  Height = 350
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
  object NAV: TsfUDVNavigator
    Left = 5
    Top = 13
    Width = 58
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 0
  end
  object _OBJ2: TsfDBGrid
    Left = 5
    Top = 44
    Width = 750
    Height = 298
    TabStop = False
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
  object CreateOrder: TsfCommandButton
    Left = 93
    Top = 10
    Width = 109
    Height = 20
    Hint = ''Create Repair Order''
    Caption = ''Create Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (

        ''Desc=Create Repair Order,Priv=''#39''{ORDER_ID=''#39#39#39#39'' and SERIAL_CNT <>''#39 +
        #39''0''#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@ToolScope,Action=Ex'' +
        ''ecSql,ParamsSqlSourceName=@ToolScope,RefreshedSQLSourceName=,Sql'' +
        ''ID=PWUST_CREATE_REPAIR_ORDER_SEL_UDV'')
  end
  object repair_order_serial_numbers: TsfDBGrid
    Left = 773
    Top = 45
    Width = 173
    Height = 298
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 3
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ3: TsfLabel
    Left = 825
    Top = 14
    Width = 86
    Height = 17
    Caption = ''Serial Numbers''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object _OBJ4: TsfUDVNavigator
    Left = 777
    Top = 12
    Width = 39
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 4
  end
  object DispOrder: TsfCommandButton
    Left = 219
    Top = 10
    Width = 109
    Height = 20
    Hint = ''Display Work Order''
    Caption = ''Display Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (

        ''Desc=Display Work Order,Priv={},Visible={},TagValue=,FKey=,Param'' +
        ''sSrc=@Default,Action=Invoke,Tool=Mfg Instructions,ReportToolId=,'' +
        ''Params(''#39''ORDER_ID=:ORDER_ID,''#39'',@InvokeToolMode=EDITMODES.Edit_PL)'')
  end
  object Repar_order_create_data: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_INT_REPAIR_WO_DATA_SELECET_BOTTOM''
    LinkedControls.Strings = (
      ''NAV''
      ''_OBJ2~''
      ''NAV~''
      ''CreateOrder~''
      ''DispOrder~'')
    ParamsSQLSourceName = ''@TOOLSCOPE''
    PublishParams = True
    SelectedFields.Strings = (

        ''SM_ORDER~15~Work Order No~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''

        ''INCOMING_MATERIAL~0~INCOMING_MATERIAL~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      ''ORDER_ID~40~Order ID~N~N~False~False~False~N~~~~~Default~N~~~''

        ''IDOC_NUMBER~0~IDOC_NUMBER~N~N~True~False~False~N~~~~~Default~N~~'' +
        ''~''
      ''TIME_STAMP~0~TIME_STAMP~N~N~True~False~False~N~~~~~Default~N~~~''
      ''QUANTITY~8~Quantity~N~N~False~False~False~N~~~~~Default~N~~~''

        ''ORDER_CREATE_DATE~10~Order Create Date~N~N~False~False~False~N~~'' +
        ''~~~Default~N~~~''

        ''CUSTOMER_NAME~15~Customer Name~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~~''

        ''NOTIFICATION~0~NOTIFICATION~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''

        ''CUSTOMER_NUMBER~10~Customer No~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~~''
      ''LOT_CODE~5~Lot code~N~N~False~False~False~N~~~~~Default~N~~~''

        ''SALES_DOC_ITEM~0~SALES_DOC_ITEM~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''

        ''SUPERIOR_SM_ORDER~0~SUPERIOR_SM_ORDER~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''

        ''PURCHASE_ORDER~0~PURCHASE_ORDER~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''

        ''SALES_ORDER~20~Sales Order~N~N~True~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''SERIAL_CNT~0~SERIAL_CNT~N~N~False~False~False~N~~~~~Default~N~~~''

        ''QUOTE_REQUIRED~0~QUOTE_REQUIRED~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''

        ''REQUIRED_DELIVERY_DATE~0~REQUIRED_DELIVERY_DATE~N~N~False~False~'' +
        ''False~N~~~~~Default~N~~~''

        ''ENGINE_MODEL~0~ENGINE_MODEL~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''

        ''ENGINE_SERIAL_NUMBER~0~ENGINE_SERIAL_NUMBER~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~~''

        ''ENGINE_SECTION~0~ENGINE_SECTION~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''

        ''ENGINE_SECTION_DESC~0~ENGINE_SECTION_DESC~N~N~False~False~False~'' +
        ''N~~~~~Default~N~~~''

        ''OUTGOING_MATERIAL~0~OUTGOING_MATERIAL~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''

        ''SOL_ORD_STATUS~0~SOL_ORD_STATUS~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''

        ''SOL_ERROR_TEXT~0~SOL_ERROR_TEXT~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''

        ''UPDT_USERID~0~UPDT_USERID~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''

        ''LAST_ACTION~0~LAST_ACTION~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~'')
    SelectedFieldsEditControl.Strings = (
      ''IDOC_NUMBER~Edit'')
    TestParamValues.Strings = (
      ''IDOC_NUMBER=1'')
    OtherCommands.Strings = (

        ''Desc=Display Order,Priv=''#39''{ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=Mfg Instructions,R'' +
        ''eportToolId=,Params(''#39''ORDER_ID=:ORDER_ID,''#39'',@InvokeToolMode=EDITMO'' +
        ''DES.Edit_PL)''

        ''Desc=Create Orders,Priv={},Visible={},TagValue=,FKey=,ParamsSrc='' +
        ''@Default,Action=UDV,UDVType=Insert,UDVID=PWUST_5D907818F49E6DB6E'' +
        ''05387971F0A5C0A''

        ''Desc=Create Sub Order,Priv={},Visible={},TagValue=,FKey=,ParamsS'' +
        ''rc=@Default''

        ''Desc=Export List to CSV,''#39''Priv={@HasPriv(''#39#39''EXPORT_DISPATCH''#39#39'') AND'' +
        '' ORDER_NO <> ''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Defaul'' +
        ''t,Action=Print,Name=File.CSV,Params()'')
    DataDefinitions.Strings = (
      ''IDOC_NUMBER''
      ''TIME_STAMP''
      ''NOTIFICATION''
      ''SM_ORDER''
      ''ORDER_ID''
      ''QUANTITY''
      ''ORDER_CREATE_DATE''
      ''CUSTOMER_NAME''
      ''CUSTOMER_NUMBER''
      ''LOT_CODE''
      ''SALES_DOC_ITEM''
      ''SUPERIOR_SM_ORDER''
      ''PURCHASE_ORDER''
      ''SALES_ORDER''
      ''SERIAL_CNT''
      ''QUOTE_REQUIRED''
      ''REQUIRED_DELIVERY_DATE''
      ''ENGINE_MODEL''
      ''ENGINE_SERIAL_NUMBER''
      ''ENGINE_SECTION''
      ''ENGINE_SECTION_DESC''
      ''INCOMING_MATERIAL''
      ''OUTGOING_MATERIAL''
      ''SOL_ORD_STATUS''
      ''SOL_ERROR_TEXT''
      ''UPDT_USERID''
      ''LAST_ACTION'')
    ConsolidatedQuery = False
  end
  object Repair_order_SN_data: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_INT_REPAIR_WO_SN_DATA''
    LinkedControls.Strings = (
      ''repair_order_serial_numbers~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''@TOOLSCOPE,Repar_order_create_data''
    PublishParams = True
    SelectedFields.Strings = (

        ''SERIAL_NUMBER~0~SERIAL_NUMBER~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~'')
    DataDefinitions.Strings = (
      ''SERIAL_NUMBER'')
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

