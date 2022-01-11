
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_8F9F7F7C90353E3EE05387971F0AD606.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_8F9F7F7C90353E3EE05387971F0AD606';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderSerialsTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_307';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 200
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
    Top = 26
    Width = 730
    Height = 160
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
    Top = 4
    Width = 96
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Edit ''
      ''Refresh ''
      ''Other commands ''
      ''Filter '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object UPDATE_CURRENT_LOC_DEPT_CENTER: TsfCommandButton
    Left = 380
    Top = 5
    Width = 180
    Height = 20
    Hint = ''Update Current Loc/Dept/Center''
    Caption = ''Update Current Loc/Dept/Center''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Update Current Loc/Dept/Center,Priv=''#39''{SERIAL_STATUS <> ''#39#39''SC'' +
        ''RAP''#39#39'' AND SERIAL_STATUS <> ''#39#39''COMPLETE''#39#39'' AND SERIAL_STATUS <> ''#39#39''S'' +
        ''TOP''#39#39'' AND SERIAL_STATUS <> ''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,Par'' +
        ''amsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_71E7425283A6'' +
        ''4773B6AAB9463D29C738'')
  end
  object _OBJ3: TsfCommandButton
    Left = 215
    Top = 5
    Width = 150
    Height = 21
    Hint = ''Insert Serial through Scan''
    Caption = ''Insert Serial through Scan''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Insert Serial through Scan,Priv=''#39''UID_ITEM_FLAG <> ''#39#39''N''#39#39'' AND'' +
        '' UID_ITEM_FLAG <> ''#39#39#39#39#39'',Visible=,TagValue=,FKey=,Action=UDV,UDVT'' +
        ''ype=Insert,UDVID=MFI_4725B437227D4FB9902287DAA6E5577B'')
  end
  object _OBJ4: TsfCommandButton
    Left = 575
    Top = 5
    Width = 160
    Height = 21
    Hint = ''Update Finish Part No/Rev''
    Caption = ''Update Finish Part No/Rev''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Update Finish Part No/Rev,Priv=''#39''{SERIAL_ID<> ''#39#39#39#39''}''#39'',Visible'' +
        ''=''#39''{INSTRUCTIONS_TYPE <> ''#39#39''MRO-Upgrade''#39#39''}''#39'',TagValue=,FKey=,Params'' +
        ''Src=@Default,Action=UDV,UDVType=Update,UDVID=MFI_5417979B21634AB'' +
        ''18877E0BBF62FC80B'')
  end
  object _OBJ5: TsfCommandButton
    Left = 120
    Top = 5
    Width = 80
    Height = 21
    Hint = ''Delete Serials''
    Caption = ''Delete Serials''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Delete Serials,Priv={False},Visible={},TagValue=,FKey=,Para'' +
        ''msSrc=@Default,Action=UDV,UDVType=GroupUpdate,FieldName=SERIAL_I'' +
        ''D,Delim=;,UDVID=MFI_E73CFD87AF7348B8A4F85250D0844FB6'')
  end
  object OrderLotsSerialSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderLotsSerialsSel''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''UPDATE_CURRENT_LOC_DEPT_CENTER''
      ''UPDATE_CURRENT_LOC_DEPT_CENTER~''
      ''_OBJ4~''
      ''_OBJ5~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''OrderLotsSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''SERIAL_NO~15~Serial No; ~N~N~N~N~N~N~~~~~Default~N~''
      
        ''UID_ENTRY_NAME~6~UID Entry;Name~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UID_ITEM_FLAG~5~UID Item;Flag~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UID_COMPOSITE~6~UID;Composite~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''CURRENT_OPERATIONS~10~Current Operation(s); ~N~N~N~N~N~N~~~~~Def'' +
        ''ault~N~''
      ''SERIAL_STATUS~15~Serial Status; ~N~N~N~N~N~N~~~~~Default~N~''
      ''DECISION_PENDING~6~Decision;Pending~N~N~N~N~N~N~~~~~Default~N~''
      
        ''SERIAL_HOLD_STATUS~15~Serial Hold Status; ~N~N~N~N~N~N~~~~~Defau'' +
        ''lt~N~''
      
        ''ALIAS_PART_NO~15~Finish Part No; ~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''ALIAS_PART_CHG~15~Finish Part Rev; ~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      
        ''LATEST_UPGRADE_PART_NO~12~Latest Upgrade Part No; ~N~N~False~Fal'' +
        ''se~False~N~~~~~Default~N~''
      
        ''LATEST_UPGRADE_PART_CHG~12~Latest Upgrade Part Rev; ~N~N~False~F'' +
        ''alse~False~N~~~~~Default~N~''
      
        ''ACTUAL_START_DATE~14~Actual Start Date; ~N~N~N~N~N~N~~~~~Default'' +
        ''~N~''
      ''ACTUAL_END_DATE~14~Actual End date; ~N~N~N~N~N~N~~~~~Default~N~''
      ''STATUS_CHG_NOTES~20~Status Notes; ~N~N~N~N~N~N~~~~~Default~N~''
      
        ''UPDT_USERID~10~Update UserId; ~N~N~N~N~N~N~MFI_2869~~~~Default~N'' +
        ''~''
      ''TIME_STAMP~18~Update Time; ~N~N~N~N~N~N~~~~~Default~N~''
      ''UID_LABEL~25~UID Label; ~N~N~False~False~False~N~~~~~Default~N~''
      
        ''ALIAS_ITEM_ID~0~ALIAS_ITEM_ID~N~N~True~False~True~N~~~~~Default~'' +
        ''N~''
      
        ''LATEST_UPGRADE_ITEM_ID~0~LATEST_UPGRADE_ITEM_ID~N~N~True~False~T'' +
        ''rue~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''NVL(C.ORDER_LIEN_FLAG,''#39''N''#39'')~Edit''
      ''Current operations~Edit''
      ''SERIAL_CONDITION~Edit''
      ''MERGED_DISC_LIEN_FLAG~Edit''
      ''MERGED_ORDER_LIEN_FLAG~Edit''
      ''UID_COMPOSITE~Edit''
      ''UID_ENTRY_NAME~Edit''
      ''SERIAL_STATUS~Edit''
      ''DECISION_PENDING~Edit''
      ''SERIAL_HOLD_STATUS~Edit''
      ''ACTUAL_START_DATE~Edit''
      ''ACTUAL_END_DATE~Edit''
      ''STATUS_CHG_NOTES~Edit''
      ''TIME_STAMP~Edit''
      ''UID_LABEL~Edit'')
    PrintTitle = ''Unit Serials''
    TestParamValues.Strings = (
      ''ORDER_ID=MFI_AE429D2689098A0E88023F72DCB0F8C9''
      ''ORDER_CONTROL_TYPE=SERIAL1'')
    InputUDVId = ''PWUST_8EE7F49084F96BDAE05387971F0A9215''
    InsertUDVId = ''MFI_1010326''
    InsertEnabledExpr = ''False''
    UpdateEnabledExpr = ''@HasPriv(''#39''PW_SERIAL_UPDT''#39'')''
    OtherCommands.Strings = (
      
        ''Desc=Display Unit Information,Priv={},Visible={},TagValue=,FKey='' +
        '',ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName=OrderLots'' +
        ''SerialSel,RefreshedSQLSourceName=,SqlID=MFI_WID_LAUNCHUNITINFO''
      
        ''Desc=Serial History,Priv={},Visible={},TagValue=,FKey=,ParamsSrc'' +
        ''=@Default,Action=Invoke,Tool=TabReport,ReportToolId=SerialHistor'' +
        ''y,Params(''#39''ORDER_ID=:ORDER_ID,''#39'',''#39''SERIAL_ID= :SERIAL_ID,''#39'',SERIAL_N'' +
        ''O=:SERIAL_NO)''
      
        ''Desc=Display UID 2D Matrix,Priv=''#39''{UID_LABEL<>''#39#39#39#39''}''#39'',Visible={},T'' +
        ''agValue=,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSource'' +
        ''Name=OrderLotsSerialSel,RefreshedSQLSourceName=,SqlID=MFI_WID_LA'' +
        ''UNCHUID2DMATRIXREPORT''
      
        ''Desc=Export UID Data,Priv=''#39''{UID_LABEL<>''#39#39#39#39''}''#39'',Visible={},TagValu'' +
        ''e=,FKey=,ParamsSrc=@Default,Action=Print,Name=File.CSV,Params()'')
    DataDefinitions.Strings = (
      ''SERIAL_NO''
      ''UID_ITEM_FLAG''
      ''UID_ENTRY_NAME''
      ''UID_COMPOSITE''
      ''CURRENT_OPERATIONS''
      ''SERIAL_STATUS''
      ''DECISION_PENDING''
      ''SERIAL_HOLD_STATUS''
      ''ALIAS_PART_NO''
      ''ALIAS_PART_CHG''
      ''LATEST_UPGRADE_PART_NO''
      ''LATEST_UPGRADE_PART_CHG''
      ''ACTUAL_START_DATE''
      ''ACTUAL_END_DATE''
      ''STATUS_CHG_NOTES''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''UID_LABEL''
      ''ALIAS_ITEM_ID''
      ''LATEST_UPGRADE_ITEM_ID'')
    ConsolidatedQuery = False
  end
  object OrderLotsSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderLotsSel''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''LOT_NO~0~LOT_NO~N~N~N~N~N~N~~''
      ''LOT_QTY~0~LOT_QTY~N~N~N~N~N~N~~''
      ''LOT_IN_PROCESS_QTY~0~LOT_IN_PROCESS_QTY~N~N~N~N~N~N~~''
      ''LOT_COMPLETE_QTY~0~LOT_COMPLETE_QTY~N~N~N~N~N~N~~''
      ''LOT_SCRAP_QTY~0~LOT_SCRAP_QTY~N~N~N~N~N~N~~''
      ''LOT_STOP_QTY~0~LOT_STOP_QTY~N~N~N~N~N~N~~''
      ''SPLIT_FLAG~0~SPLIT_FLAG~N~N~N~N~N~N~~''
      ''LOT_ID~0~LOT_ID~N~N~N~N~N~N~~''
      ''ORIG_LOT_ID~0~ORIG_LOT_ID~N~N~N~N~N~N~~''
      ''PARENT_LOT_ID~0~PARENT_LOT_ID~N~N~N~N~N~N~~''
      ''ORDER_ID~0~ORDER_ID~N~N~N~N~N~N~~'')
    TestParamValues.Strings = (
      ''ORDER_ID=MFI_D5FF4D9BA6C3FC6C7F27DD2E4BBB1359''
      ''ORDER_CONTROL_TYPE=Upgrade'')
    DataDefinitions.Strings = (
      ''LOT_NO''
      ''LOT_QTY''
      ''LOT_IN_PROCESS_QTY''
      ''LOT_COMPLETE_QTY''
      ''LOT_SCRAP_QTY''
      ''LOT_STOP_QTY''
      ''SPLIT_FLAG''
      ''LOT_ID''
      ''ORIG_LOT_ID''
      ''PARENT_LOT_ID''
      ''ORDER_ID'')
    ConsolidatedQuery = False
  end
  object UidInfoSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_UidInfoSel''
    LinkedControls.Strings = (
      ''_OBJ3~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''ENTERPRISE_ID~0~ENTERPRISE_ID~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~''
      
        ''DATA_QUALIFIER~0~DATA_QUALIFIER~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~''
      
        ''CONSTRUCT_TYPE~0~CONSTRUCT_TYPE~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~''
      
        ''ISSUE_AGENCY_CODE~0~ISSUE_AGENCY_CODE~N~N~False~False~False~N~~~'' +
        ''~~Default~''
      
        ''UID_ENTRY_NAME~0~UID_ENTRY_NAME~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~''
      
        ''UID_ITEM_FLAG~0~UID_ITEM_FLAG~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~'')
    TestParamValues.Strings = (
      ''ORDER_ID=MFI_3732827C58499A45416BE29D23E0C2D'')
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

