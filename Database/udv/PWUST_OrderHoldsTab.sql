
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_66D9450036158E04E05387971F0A7BC0.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_66D9450036158E04E05387971F0A7BC0';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderHoldsTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 376;R2Upgrade defect1029; Defect 1219';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.0.20190207~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 397
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
  object _OBJ1: TsfUDVNavigator
    Left = 4
    Top = 3
    Width = 77
    Height = 21
    Hidden = ''false''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Edit ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ2: TsfDBGrid
    Left = 4
    Top = 26
    Width = 578
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
  object _OBJ3: TsfCommandButton
    Left = 147
    Top = 3
    Width = 100
    Height = 21
    Caption = ''Create Hold''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Create Work Order Hold,Priv=''#39''{ORDER_ID<>''#39#39#39#39'' AND ORDER_STAT'' +
        ''US<>''#39#39''CLOSE''#39#39''}''#39'',Visible={},TagValue=ORDER STOP,FKey=,ParamsSrc=@'' +
        ''Default,Action=UDV,UDVType=Insert,UDVID=PWUST_79EFF26E69A66581E0'' +
        ''5387971F0AFC26''
      
        ''Desc=Create Operation Hold,Priv=''#39''{ORDER_ID<>''#39#39#39#39'' AND ORDER_STATU'' +
        ''S<>''#39#39''CLOSE''#39#39''}''#39'',Visible={},TagValue=OPER STOP,FKey=,ParamsSrc=@De'' +
        ''fault,Action=UDV,UDVType=Insert,UDVID=PWUST_66DA74FA1B5D94EAE053'' +
        ''87971F0A9F96''
      
        ''Desc=Create Unit Hold,Priv=''#39''{ORDER_ID<>''#39#39#39#39'' AND ORDER_STATUS<>''#39#39 +
        ''CLOSE''#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Action=U'' +
        ''DV,UDVType=Insert,UDVID=MFI_1010020''
      
        ''Desc=Create Over Inspection Hold,Priv=''#39''{ORDER_ID<>''#39#39#39#39'' AND ORDER'' +
        ''_STATUS<>''#39#39''CLOSE''#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Defau'' +
        ''lt,Action=ExecSql,ParamsSqlSourceName=OrderHoldTabSelect,Refresh'' +
        ''edSQLSourceName=,SqlID=MFI_OVERINSPECTIONHOLDPREINS'')
  end
  object _OBJ4: TsfCommandButton
    Left = 209
    Top = 3
    Width = 100
    Height = 21
    Hint = ''Close Hold''
    Caption = ''Close Hold''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Close Hold,''#39''Priv={HOLD_ID<>''#39#39#39#39'' AND HOLD_STATUS<>''#39#39''CLOSED''#39#39 +
        '' AND @haspriv(''#39#39''PW_ORDER_HOLD_CLOSE''#39#39'')}''#39'',Visible={},TagValue=,FK'' +
        ''ey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName=OrderH'' +
        ''oldTabSelect,RefreshedSQLSourceName=,SqlID=MFI_Wid_CloseHoldManu'' +
        ''ally'')
  end
  object _OBJ5: TsfCommandButton
    Left = 85
    Top = 3
    Width = 100
    Height = 21
    Hint = ''History''
    Caption = ''History''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=History,Priv=''#39''{HOLD_ID<>''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,P'' +
        ''aramsSrc=@Default,Action=Invoke,Tool=Tabular Report,ReportToolId'' +
        ''=HoldHistory,Params(''#39''order_id = :order_id,''#39'',order_no = :order_no'' +
        '')'')
  end
  object _OBJ6: TsfCommandButton
    Left = 271
    Top = 3
    Width = 100
    Height = 21
    Hint = ''In Work''
    Caption = ''In Work''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=In Work,Priv=,Visible=''#39''HOLD_STATUS=''#39#39''OPEN''#39#39#39'',TagValue=,FKey'' +
        ''=,Action=UDV,UDVType=Update,UDVID=MFI_784FE2666D2641EC90210A5B35'' +
        ''BA7B8F'')
  end
  object _OBJ7: TsfDBGrid
    Left = 585
    Top = 26
    Width = 150
    Height = 157
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 6
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ8: TsfUDVNavigator
    Left = 585
    Top = 3
    Width = 39
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 7
  end
  object displayBtn: TsfCommandButton
    Left = 334
    Top = 3
    Width = 100
    Height = 21
    Caption = ''Display''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 8
    OtherCommands.Strings = (
      
        ''Desc=Work Order,Priv=''#39''{HOLD_TYPE = ''#39#39''WORK ORDER LIEN''#39#39'' OR HOLD_T'' +
        ''YPE = ''#39#39''WORK ORDER INHERITED LIEN''#39#39''}''#39'',Visible={},TagValue=,FKey='' +
        '',ParamsSrc=@Default,Action=Invoke,Tool=MfgInstructions,ReportToo'' +
        ''lId=,Params(''#39''ORDER_ID = :HOLD_ORDER_ID,@InvokeToolMode=EDITMODES'' +
        ''.Edit_PL''#39'')''
      
        ''Desc=Discrepancy,Priv=''#39''{HOLD_TYPE = ''#39#39''DISCREPANCY LIEN''#39#39'' OR HOLD'' +
        ''_TYPE = ''#39#39''INHERITED DISCREPANCY LIEN''#39#39''}''#39'',Visible={},TagValue=,FK'' +
        ''ey=,ParamsSrc=@Default,Action=Invoke,Tool=DiscrepancyItem,Report'' +
        ''ToolId=,Params(''#39''DISC_ID=:HOLD_DISC_ID,''#39'',''#39''DISC_LINE_NO=:HOLD_DISC'' +
        ''_LINE_NO,''#39'',''#39''@InvokeToolMode=@GetQueryValue\(MFI_QA_DiscrepancyTo'' +
        ''olInvokeMode\)''#39'')''
      
        ''Desc=Over Inspection Hold,Priv=''#39''{HOLD_TYPE = ''#39#39''OVER INSPECTION H'' +
        ''OLD''#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Action=Sid'' +
        ''eNote,SqlID=MFI_OrderOperDatColSideText,Params(''#39''ORDER_ID=:ORDER_'' +
        ''ID,''#39'',''#39''OPER_KEY=:OPER_KEY,''#39'',HOLD_ID=:HOLD_ID)'')
  end
  object OrderHoldTabSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderHoldTabSelect''
    KeyFieldNames = ''HOLD_ID''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''_OBJ4~''
      ''_OBJ5~''
      ''_OBJ6~''
      ''_OBJ3~''
      ''displayBtn~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''OPER_NO~4~Oper No~N~N~N~N~N~N~~~~~Default~N~''
      ''STOP_TYPE~13~Stop Type~N~N~N~N~N~N~~~~~Default~N~''
      ''HOLD_STATUS~10~Hold Status~N~N~N~N~N~N~~~~~Default~N~''
      ''HOLD_TYPE~14~Hold Type~N~N~N~N~N~N~~~~~Default~N~''
      ''UPDT_USERID~10~Update User ID~N~N~N~N~N~N~MFI_2869~~~~Default~N~''
      ''TIME_STAMP~18~Update Time~N~N~N~N~N~N~~~~~Default~N~''
      ''HOLD_REF1~20~Ref1~N~N~N~N~N~N~~~~~Default~N~''
      ''HOLD_REF2~8~Ref2~N~N~N~N~N~N~~~~~Default~N~''
      ''HOLD_REF3~8~Ref3~N~N~N~N~N~N~~~~~Default~N~''
      ''REF_DESCRIPTION~30~Ref Description~N~N~N~N~N~N~~~~~Default~N~''
      ''QTY_SHORT~4~Qty Short~N~N~N~N~N~N~~~~~Default~N~''
      ''SCHED_START_DATE~18~Sched Start Date~N~N~N~N~N~N~~~~~Default~N~''
      ''SCHED_END_DATE~18~Sched End Date~N~N~N~N~N~N~~~~~Default~N~''
      ''NOTES~32~Notes~N~N~N~N~N~N~~~~~Default~N~''
      
        ''UCF_HOLD_VCH1~30~Ucf Hold Vch1~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_HOLD_VCH2~30~Ucf Hold Vch2~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_HOLD_VCH3~30~Uc Hold Vch3~N~N~True~False~False~N~~~~~Default'' +
        ''~N~''
      
        ''UCF_HOLD_VCH4~30~Ucf Hold Vch4~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_HOLD_VCH5~30~Ucf Hold Vch5~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_HOLD_VCH6~30~Ucf Hold Vch6~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_HOLD_VCH7~30~Ucf Hold Vch7~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_HOLD_VCH8~30~Ucf Hold Vch8~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_HOLD_VCH9~30~Ucf Hold Vch9~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''UCF_HOLD_VCH10~30~Ucf Hold Vch10~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_VCH11~30~Ucf Hold Vch11~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_VCH12~30~Ucf Hold Vch12~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_VCH13~30~Ucf Hold Vch13~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_VCH14~30~Ucf Hold Vch14~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_VCH15~30~Ucf Hold Vch15~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_NUM1~8~Ucf Hold Number1~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_NUM2~8~Ucf Hold Number2~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_NUM3~8~Ucf Hold Number3~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_NUM4~8~Ucf Hold Number4~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_NUM5~8~Ucf Hold Number5~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_FLAG1~1~Ucf Hold Flag1~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~''
      
        ''UCF_HOLD_FLAG2~1~Ucf Hold Flag2~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~''
      
        ''UCF_HOLD_FLAG3~1~Ucf Hold Flag3~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~''
      
        ''UCF_HOLD_FLAG4~1~Ucf Hold Flag4~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~''
      
        ''UCF_HOLD_FLAG5~1~Ucf Hold Flag5~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~''
      
        ''UCF_HOLD_DATE1~18~Ucf Hold Date1~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_DATE2~18~Ucf Hold Date2~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_DATE3~18~Ucf Hold Date3~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_DATE4~18~Ucf Hold Date4~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UCF_HOLD_DATE5~18~Ucf Hold Date5~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~'')
    SelectedFieldsEditControl.Strings = (
      ''STOP_TYPE~Edit''
      ''HOLD_STATUS~Edit''
      ''UPDT_USERID~Edit''
      ''HOLD_REF2~Edit''
      ''HOLD_REF3~Edit''
      ''REF_DESCRIPTION~Edit''
      ''SCHED_START_DATE~Edit''
      ''SCHED_END_DATE~Edit''
      ''UCF_HOLD_VCH1~Edit''
      ''UCF_HOLD_VCH2~Edit''
      ''UCF_HOLD_VCH3~Edit''
      ''UCF_HOLD_VCH5~Edit''
      ''UCF_HOLD_VCH8~Edit''
      ''UCF_HOLD_VCH10~Edit''
      ''UCF_HOLD_VCH12~Edit''
      ''UCF_HOLD_VCH13~Edit''
      ''UCF_HOLD_VCH14~Edit''
      ''UCF_HOLD_VCH15~Edit''
      ''UCF_HOLD_NUM2~Edit''
      ''UCF_HOLD_FLAG2~Edit''
      ''UCF_HOLD_FLAG3~Edit''
      ''UCF_HOLD_FLAG4~Edit''
      ''UCF_HOLD_FLAG5~Edit''
      ''UCF_HOLD_DATE2~Edit''
      ''OPER_NO~Edit'')
    PrintTitle = ''Holds''
    InputUDVId = ''MFI_1010020''
    InsertEnabledExpr = ''ORDER_STATUS<>''#39''CLOSE''#39
    UpdateEnabledExpr = ''ORDER_STATUS<>''#39''CLOSE''#39
    DeleteEnabledExpr = ''ORDER_STATUS<>''#39''CLOSE''#39
    DataDefinitions.Strings = (
      ''OPER_NO''
      ''STOP_TYPE''
      ''HOLD_STATUS''
      ''HOLD_TYPE''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''HOLD_REF1''
      ''HOLD_REF2''
      ''HOLD_REF3''
      ''REF_DESCRIPTION''
      ''QTY_SHORT''
      ''SCHED_START_DATE''
      ''SCHED_END_DATE''
      ''NOTES''
      ''UCF_HOLD_VCH1''
      ''UCF_HOLD_VCH2''
      ''UCF_HOLD_VCH3''
      ''UCF_HOLD_VCH4''
      ''UCF_HOLD_VCH5''
      ''UCF_HOLD_VCH6''
      ''UCF_HOLD_VCH7''
      ''UCF_HOLD_VCH8''
      ''UCF_HOLD_VCH9''
      ''UCF_HOLD_VCH10''
      ''UCF_HOLD_VCH11''
      ''UCF_HOLD_VCH12''
      ''UCF_HOLD_VCH13''
      ''UCF_HOLD_VCH14''
      ''UCF_HOLD_VCH15''
      ''UCF_HOLD_NUM1''
      ''UCF_HOLD_NUM2''
      ''UCF_HOLD_NUM3''
      ''UCF_HOLD_NUM4''
      ''UCF_HOLD_NUM5''
      ''UCF_HOLD_FLAG1''
      ''UCF_HOLD_FLAG2''
      ''UCF_HOLD_FLAG3''
      ''UCF_HOLD_FLAG4''
      ''UCF_HOLD_FLAG5''
      ''UCF_HOLD_DATE1''
      ''UCF_HOLD_DATE2''
      ''UCF_HOLD_DATE3''
      ''UCF_HOLD_DATE4''
      ''UCF_HOLD_DATE5'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''OrderHoldTabSelect''
  end
  object RespResourceTypeSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_OrderHoldRespResourceTypeSel''
    LinkedControls.Strings = (
      ''_OBJ7~''
      ''_OBJ8~'')
    ParamsSQLSourceName = ''OrderHoldTabSelect''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''RESP_RESOURCE_TYPE~9~Responsible;Resource Type~N~N~False~False~F'' +
        ''alse~N~~~~~Default~N~''
      
        ''RESP_RESOURCE_NO~12~Responsible;Resource No~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''RESP_RESOURCE_TYPE~Edit'')
    DataDefinitions.Strings = (
      ''RESP_RESOURCE_TYPE''
      ''RESP_RESOURCE_NO'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''RespResourceTypeSelect''
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

