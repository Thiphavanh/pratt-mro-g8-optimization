
set define off
prompt ---------------------------------------;
prompt Executing ... MFI_A86CDF9FD0B341C793BD17DA1F4FF3F6.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='FOUNDATION';
v_udv_id sfcore_udv_lib.udv_id%type  :='MFI_A86CDF9FD0B341C793BD17DA1F4FF3F6';
v_udv_tag sfcore_udv_lib.udv_tag%type :='WOAltAuxUDV';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Auxiliary UDV for Work Order Alteration';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1200
  Height = 35
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
  DesignSize = (
    1196
    31)
  object CLAIM_TASK: TsfCommandButton
    Left = 5
    Top = 4
    Width = 90
    Height = 21
    Hint = ''Claim Task''
    Caption = ''Claim Task''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 0
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Claim Task,Priv=''#39''CLAIM_REMAINED <> ''#39#39''0''#39#39#39'',Visible=,TagValue'' +
        ''=CLAIM,FKey=,Action=UDV,UDVType=Insert,UDVID=MFI_A403A752E969444'' +
        ''BB231475458486CF3'')
  end
  object RELEASE_TASK: TsfCommandButton
    Left = 105
    Top = 4
    Width = 90
    Height = 21
    Hint = ''Release Task''
    Caption = ''Release Task''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 1
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Release Task,Priv=''#39''RELEASE_COUNT <> ''#39#39''0''#39#39#39'',Visible=,TagValu'' +
        ''e=RELEASE,FKey=,Action=UDV,UDVType=Insert,UDVID=MFI_A403A752E969'' +
        ''444BB231475458486CF3'')
  end
  object RETURN_TO: TsfCommandButton
    Left = 305
    Top = 4
    Width = 90
    Height = 21
    Hint = ''Return To Prior Task''
    Caption = ''Return To''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Return To Prior Task,Priv=''#39''{RETURN_QUEUE_REMAINEND <> ''#39#39''0''#39#39 +
        '' AND @CurrentEditBlock = ''#39#39#39#39'' AND ORDER_ID<>''#39#39#39#39''}''#39'',Visible={},Ta'' +
        ''gValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Insert,UDVID'' +
        ''=MFI_151E17C3446B0966E0440003BA560E35'')
  end
  object COMPLETE_TASK: TsfCommandButton
    Left = 205
    Top = 4
    Width = 90
    Height = 21
    Hint = ''Complete Task''
    Caption = ''Complete Task''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Complete Task,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND ORDER_ID<'' +
        ''>''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Action=UDV'' +
        '',UDVType=Insert,UDVID=MFI_1003850'')
  end
  object DisplayOFD: TsfCommandButton
    Left = 405
    Top = 4
    Width = 160
    Height = 21
    Hint = ''Display OFD''
    Caption = ''Display Operation Sequence''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Display OFD,Priv=,Visible=''#39''MRO_FLAG<>''#39#39''Y''#39#39'' AND DISPLAY_SEQU'' +
        ''ENCE=''#39#39''Flow Diagram''#39#39#39'',TagValue=,FKey=,Action=Invoke,Tool=FlowDi'' +
        ''agrams.OrderOFD,ReportToolId=,Params(ORDER_ID = :ORDER_ID,SERIAL'' +
        ''_FLAG = :SERIAL_FLAG,ORDER_NO = :ORDER_NO,@InvokeToolMode=:TOOL_'' +
        ''MODE)'')
  end
  object OPER_PRECEDENCE: TsfCommandButton
    Left = 572
    Top = 4
    Width = 140
    Height = 21
    Hint = ''Operation Precedence''
    Caption = ''Operation Precedence''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Operation Precedence,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND OR'' +
        ''DER_ID<>''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Act'' +
        ''ion=SideNote,SqlID=MFI_WID_OPERATIONPRECEDENCESIDEPANEL,Params(''#39 +
        ''ORDER_ID = :ORDER_ID,''#39'',''#39''OPER_KEY = :OPER_KEY,''#39'',''#39''@ToolState = :@T'' +
        ''oolState,''#39'',''#39''ALTERATION_LEVEL = :ALTERATION_LEVEL,''#39'',CALLED_FROM ='' +
        '' OrderAlterations)'')
  end
  object ACCEPT_BUTTON: TsfCommandButton
    Left = 205
    Top = 0
    Width = 90
    Height = 7
    Hint = ''Accept''
    Anchors = [akLeft, akBottom]
    Caption = ''Accept''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Accept,Priv=''#39''@CurrentEditBlock = ''#39#39#39#39#39'',Visible=''#39''IS_ACCEPT_R'' +
        ''EJECT_QUEUE = ''#39#39''Y''#39#39'' AND CURRENT_STATUS <> ''#39#39#39#39'' AND ALTERATION_LE'' +
        ''VEL <> ''#39#39#39#39'' AND ALT_STATUS <> ''#39#39''COMPLETE''#39#39'' AND ALT_STATUS <> ''#39#39#39 +
        #39#39'',TagValue=ACCEPT,FKey=,Action=UDV,UDVType=Update,UDVID=MFI_A7D'' +
        ''C8A84F31D494C9B59B3B7B22B80DB'')
  end
  object REJECT_BUTTON: TsfCommandButton
    Left = 305
    Top = 0
    Width = 90
    Height = 7
    Hint = ''Reject''
    Anchors = [akLeft, akBottom]
    Caption = ''Reject''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (
      
        ''Desc=Reject,Priv=''#39''@CurrentEditBlock = ''#39#39#39#39#39'',Visible=''#39''IS_ACCEPT_R'' +
        ''EJECT_QUEUE = ''#39#39''Y''#39#39'' AND CURRENT_STATUS <> ''#39#39#39#39'' AND CURRENT_STATU'' +
        ''S <> START_QUEUE_TYPE AND ALTERATION_LEVEL <> ''#39#39#39#39'' AND ALT_STATU'' +
        ''S <> ''#39#39''COMPLETE''#39#39'' AND ALT_STATUS <> ''#39#39#39#39#39'',TagValue=REJECT,FKey=,'' +
        ''Action=UDV,UDVType=Update,UDVID=MFI_A7DC8A84F31D494C9B59B3B7B22B'' +
        ''80DB'')
  end
  object _OBJ1: TsfCommandButton
    Left = 720
    Top = 4
    Width = 90
    Height = 21
    Caption = ''Compare Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 8
    OtherCommands.Strings = (
      
        ''Desc=Compare Order,Priv=,Visible=,TagValue=,FKey=,Action=UDV,UDV'' +
        ''Type=UdvScript,UDVID=MFI_5FB5449D18134B138A985B9D0FAA8B0E'')
  end
  object DispSwimLanes: TsfCommandButton
    Left = 405
    Top = 4
    Width = 160
    Height = 21
    Caption = ''Display Operation Sequence''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 9
    OtherCommands.Strings = (
      
        ''Desc=Display Swim Lanes,Priv={},Visible=''#39''{DISPLAY_SEQUENCE=''#39#39''Exe'' +
        ''cution Order''#39#39''}''#39'',TagValue=,FKey=,Action=Invoke,Tool=FlowDiagrams'' +
        ''.OrderSwimLanes,ReportToolId=,Params(ORDER_ID = :ORDER_ID,SERIAL'' +
        ''_FLAG = :SERIAL_FLAG,ORDER_NO = :ORDER_NO,@InvokeToolMode=:TOOL_'' +
        ''MODE)'')
  end
  object _OBJ2: TsfCommandButton
    Left = 832
    Top = 4
    Width = 130
    Height = 21
    Caption = ''Display Inspection Order''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 10
    OtherCommands.Strings = (
      
        ''Desc=Display Inspection Order,Priv={},Visible=''#39''{INSP_ORDER_ID <>'' +
        '' ''#39#39#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=In'' +
        ''sert,UDVID=MFI_3BD92F558CC540B0BECADCD13673D86F'')
  end
  object _OBJ3: TsfCommandButton
    Left = 975
    Top = 4
    Width = 100
    Height = 21
    Caption = ''Collapse All''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 11
    OtherCommands.Strings = (
      
        ''Desc=collapseAll,Priv={},Visible=''#39''{@ClientType <> ''#39#39''Web''#39#39''}''#39'',TagV'' +
        ''alue=Collapse,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlS'' +
        ''ourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_SFWID_COL'' +
        ''LAPSE_ALL'')
  end
  object ExpandAll: TsfCommandButton
    Left = 1088
    Top = 4
    Width = 100
    Height = 21
    Caption = ''Expand All''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 12
    OtherCommands.Strings = (
      
        ''Desc=expandAll,Priv={},Visible=''#39''{@ClientType <> ''#39#39''Web''#39#39''}''#39'',TagVal'' +
        ''ue=Expand,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourc'' +
        ''eName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_SFWID_COLLAPS'' +
        ''E_ALL'')
  end
  object MFI_WID_WORK_SCOPE_HOLD_EXISTS: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_WORK_SCOPE_HOLD_EXISTS''
    ParamsSQLSourceName = ''@ToolScope''
    ConsolidatedQuery = False
  end
  object ToFromQueueTypes: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_ToFromQueueTypes''
    LinkedControls.Strings = (
      ''REJECT_BUTTON~''
      ''ACCEPT_BUTTON~''
      ''COMPLETE_TASK~''
      ''OPER_PRECEDENCE~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    ConsolidatedQuery = False
  end
  object OrderAlterationClaim: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_ORDERCHANGESCLAIMREMAIN''
    LinkedControls.Strings = (
      ''CLAIM_TASK~'')
    ParamsSQLSourceName = ''@ToolScope''
    ConsolidatedQuery = False
  end
  object OrderAlterationRelease: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_ORDERCHANGESRELEASE''
    LinkedControls.Strings = (
      ''RELEASE_TASK~'')
    ParamsSQLSourceName = ''@ToolScope''
    ConsolidatedQuery = False
  end
  object ReturnToPrior: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_ORDERCHANGESRETURNTOPRIORREMAIN''
    LinkedControls.Strings = (
      ''RETURN_TO~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    ConsolidatedQuery = False
  end
  object ToolModeSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_TOOLMODESELECT''
    LinkedControls.Strings = (
      ''DispSwimLanes~''
      ''DisplayOFD~'')
    ParamsSQLSourceName = ''@ToolScope''
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

