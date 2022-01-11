
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_7B1C7E673361310EE05387971F0A1367.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_7B1C7E673361310EE05387971F0A1367';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_QAAuxUDV';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 1028';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.2.0.20180714~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1260
  Height = 50
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
  object CLAIM_TASK: TsfCommandButton
    Left = 74
    Top = 4
    Width = 90
    Height = 35
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
      
        ''Desc=Claim Task,Priv=''#39''{ASSIGNED_TO_USERID<>@USERID AND DISC_LINE'' +
        ''_STATUS <> ''#39#39''DISPOSITIONED''#39#39'' AND DISC_LINE_STATUS <> ''#39#39''CANCEL''#39#39'' '' +
        ''AND DISC_LINE_STATUS <> ''#39#39''IMPLEMENTED''#39#39''}''#39'',Visible={},TagValue=,F'' +
        ''Key=,Action=ExecSql,ParamsSqlSourceName=DiscTaskTabSel,''#39''Refreshe'' +
        ''dSQLSourceName=DiscTaskTabSel, DiscTaskSelect''#39'',SqlID=WID_OrderAl'' +
        ''tTaskClaim'')
  end
  object release_task: TsfCommandButton
    Left = 169
    Top = 4
    Width = 90
    Height = 35
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
      
        ''Desc=Release Task,Priv=''#39''{ASSIGNED_TO_USERID = @USERID AND DISC_L'' +
        ''INE_STATUS <> ''#39#39''DISPOSITIONED''#39#39'' AND DISC_LINE_STATUS <> ''#39#39''CANCEL'' +
        #39#39'' AND DISC_LINE_STATUS <> ''#39#39''IMPLEMENTED''#39#39''}''#39'',Visible={},TagValue'' +
        ''=,FKey=,Action=ExecSql,ParamsSqlSourceName=DiscTaskTabSel,''#39''Refre'' +
        ''shedSQLSourceName=DiscTaskTabSel, DiscTaskSelect''#39'',SqlID=WID_Orde'' +
        ''rAltTaskRelease'')
  end
  object COMPLETE_TASK: TsfCommandButton
    Left = 264
    Top = 4
    Width = 90
    Height = 35
    Hint = ''Complete Task''
    Caption = ''Complete Task''
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
      
        ''Desc=Complete Task,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND IS_ACCEPT'' +
        ''_REJECT_QUEUE = ''#39#39''N''#39#39'' AND DISC_LINE_STATUS <> ''#39#39#39#39'' AND DISC_LINE'' +
        ''_STATUS <> ''#39#39''CANCEL''#39#39'' AND DISC_LINE_STATUS <> ''#39#39''DISPOSITIONED''#39#39'' '' +
        ''AND DISC_LINE_STATUS <> ''#39#39''IMPLEMENTED''#39#39''}''#39'',Visible={},TagValue=CO'' +
        ''MPLETE_TASK,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,U'' +
        ''DVID=MFI_59DE10D711984DB8B3DA93F97009154D'')
  end
  object RETURN_TO: TsfCommandButton
    Left = 359
    Top = 4
    Width = 90
    Height = 35
    Hint = ''Return To''
    Caption = ''Return To''
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
      
        ''Desc=Return To,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND IS_ACCEPT_REJ'' +
        ''ECT_QUEUE = ''#39#39''N''#39#39'' AND DISC_LINE_STATUS <> START_QUEUE_TYPE AND D'' +
        ''ISC_LINE_STATUS <> ''#39#39#39#39'' AND DISC_LINE_STATUS <> ''#39#39''CANCEL''#39#39''  AND '' +
        ''DISC_LINE_STATUS <> ''#39#39''DISPOSITIONED''#39#39'' AND DISC_LINE_STATUS <> ''#39#39 +
        ''IMPLEMENTED''#39#39''}''#39'',Visible={},TagValue=RETURN_TO,FKey=,ParamsSrc=@D'' +
        ''efault,Action=UDV,UDVType=Insert,UDVID=MFI_59DE10D711984DB8B3DA9'' +
        ''3F97009154D'')
  end
  object VERSION: TsfCommandButton
    Left = 549
    Top = 4
    Width = 90
    Height = 35
    Hint = ''Split Discrepancy''
    Caption = ''Split''
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
      
        ''Desc=Split Discrepancy,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND AFFEC'' +
        ''TED_QTY > ''#39#39''1''#39#39'' AND DISP_TYPE=''#39#39#39#39''}''#39'',Visible={},TagValue=SPLIT,F'' +
        ''Key=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_59DE'' +
        ''10D711984DB8B3DA93F97009154D'')
  end
  object OFD: TsfCommandButton
    Left = 644
    Top = 4
    Width = 90
    Height = 35
    Hint = ''Cancel Discrepancy''
    Caption = ''Cancel''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Cancel Discrepancy,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND DISC'' +
        ''_LINE_STATUS <> ''#39#39''DISPOSITIONED''#39#39'' AND DISC_LINE_STATUS <> ''#39#39''CANC'' +
        ''EL''#39#39''}''#39'',Visible={},TagValue=,FKey=,Action=UDV,UDVType=Insert,UDVI'' +
        ''D=MFI_57D0FF067F852FC4E0440003BA041A64'')
  end
  object _OBJ1: TsfCommandButton
    Left = 454
    Top = 4
    Width = 90
    Height = 35
    Hint = ''Copy Discrepancy''
    Caption = ''Copy''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Copy Discrepancy,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' and AFFECT'' +
        ''ED_QTY <> ''#39#39''0''#39#39''}''#39'',Visible={},TagValue=COPY,FKey=,ParamsSrc=@Defa'' +
        ''ult,Action=UDV,UDVType=Insert,UDVID=MFI_59DE10D711984DB8B3DA93F9'' +
        ''7009154D'')
  end
  object _OBJ2: TsfCommandButton
    Left = 738
    Top = 4
    Width = 90
    Height = 35
    Hint = ''Reopen Discrepancy''
    Caption = ''Reopen''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Reopen Discrepancy,''#39''Priv={@CurrentEditBlock = ''#39#39#39#39'' AND (DIS'' +
        ''C_LINE_STATUS = ''#39#39''DISPOSITIONED''#39#39'' OR DISC_LINE_STATUS = ''#39#39''CANCEL'' +
        #39#39'' OR DISC_LINE_STATUS = ''#39#39''IMPLEMENTED''#39#39'')  and AFFECTED_QTY <> ''#39 +
        #39''0''#39#39''}''#39'',Visible={},TagValue=REOPEN,FKey=,ParamsSrc=@Default,Actio'' +
        ''n=UDV,UDVType=Insert,UDVID=MFI_1011149'')
  end
  object CreateCA: TsfCommandButton
    Left = 835
    Top = 4
    Width = 100
    Height = 35
    Hint = ''Create CA''
    Caption = ''Corrective Action''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 8
    OtherCommands.Strings = (
      
        ''Desc=Create Corrective Action,''#39''Priv={@haspriv(''#39#39''CA_CREATE''#39#39'') AND'' +
        '' (CURRENT_QUEUE =''#39#39#39#39'' OR @haspriv(CURRENT_QUEUE)) AND @CurrentEd'' +
        ''itBlock = ''#39#39#39#39'' AND (DISC_LINE_STATUS <> ''#39#39''CANCEL''#39#39'') AND CA_ID = '' +
        #39#39#39#39'' AND CA_REQUEST_ID = ''#39#39#39#39''}''#39'',''#39''Visible={@GetQueryValue(MFI_CAE'' +
        ''DITMODESEL)<>''#39#39''Hidden''#39#39''}''#39'',TagValue=CORRECTIVEACTION,FKey=,Params'' +
        ''Src=@Default,Action=UDV,UDVType=Insert,UDVID=MFI_B7431CE03D204B5'' +
        ''EB666B418EF640992''
      
        ''Desc=Create Request,''#39''Priv={@haspriv(''#39#39''CA_REQUEST_CREATE''#39#39'') AND ('' +
        ''CURRENT_QUEUE =''#39#39#39#39'' OR @haspriv(CURRENT_QUEUE)) AND @CurrentEdit'' +
        ''Block = ''#39#39#39#39'' AND (DISC_LINE_STATUS <> ''#39#39''CANCEL''#39#39'') AND CA_ID = ''#39#39 +
        #39#39'' AND CA_REQUEST_ID = ''#39#39#39#39''}''#39'',''#39''Visible={@GetQueryValue(MFI_CAEDI'' +
        ''TMODESEL)<>''#39#39''Hidden''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Actio'' +
        ''n=UDV,UDVType=Insert,UDVID=MFI_65F15DDEFDDC452AA70E8D3B3B344854'')
  end
  object Display: TsfCommandButton
    Left = 939
    Top = 4
    Width = 120
    Height = 35
    Hint = ''Display''
    Caption = ''Display''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 9
    OtherCommands.Strings = (
      
        ''Desc=Print AS9131 Report,''#39''Priv={@haspriv(''#39#39''SFQA_AS9131_DI_REPORT'' +
        #39#39'') AND (DISC_INSTRUCTIONS_TYPE <> ''#39#39''USER''#39#39'' AND DISC_INSTRUCTION'' +
        ''S_TYPE <> ''#39#39''AUDIT''#39#39'')}''#39'',Visible=''#39''{@ClientType <> ''#39#39''Web''#39#39''}''#39'',TagVal'' +
        ''ue=,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName='' +
        ''@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_QA_DiscrepancyItemA'' +
        ''S9131RptDisp''
      
        ''Desc=Print Transport Document,Priv=''#39''{DISC_INSTRUCTIONS_TYPE <> ''#39 +
        #39''USER''#39#39'' AND DISC_INSTRUCTIONS_TYPE <> ''#39#39''AUDIT''#39#39'' AND ORDER_ID<>''#39#39 +
        #39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Action=ExecSq'' +
        ''l,ParamsSqlSourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=M'' +
        ''FI_SFQA_PRINTORDERTRANSPORTDOCPRE''
      
        ''Desc=Display Order,''#39''Priv={(DISC_INSTRUCTIONS_TYPE = ''#39#39''WO''#39#39'' OR DI'' +
        ''SC_INSTRUCTIONS_TYPE = ''#39#39''USER''#39#39'') AND ORDER_ID<>''#39#39#39#39''}''#39'',''#39''Visible={'' +
        ''(DISC_INSTRUCTIONS_TYPE = ''#39#39''WO''#39#39'' OR DISC_INSTRUCTIONS_TYPE = ''#39#39''U'' +
        ''SER''#39#39'') AND ORDER_ID<>''#39#39#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,A'' +
        ''ction=Invoke,Tool=MfgInstructions,ReportToolId=,Params(''#39''ORDER_ID'' +
        '' = :ORDER_ID,''#39'',@InvokeToolMode=EDITMODES.Edit_PL)''
      
        ''Desc=Display Inspection Order,Priv=''#39''{DISC_INSTRUCTIONS_TYPE = ''#39#39 +
        ''SQA''#39#39'' AND INSP_ORDER_ID <> ''#39#39#39#39''}''#39'',Visible=''#39''{DISC_INSTRUCTIONS_TY'' +
        ''PE = ''#39#39''SQA''#39#39'' AND INSP_ORDER_ID <> ''#39#39#39#39''}''#39'',TagValue=,FKey=,ParamsS'' +
        ''rc=@Default,Action=Invoke,Tool=InspOrderInstructions,ReportToolI'' +
        ''d=,Params(INSP_ORDER_ID=:INSP_ORDER_ID)''
      
        ''Desc=Display Audit,Priv=''#39''{DISC_INSTRUCTIONS_TYPE = ''#39#39''AUDIT''#39#39''}''#39'',V'' +
        ''isible=''#39''{DISC_INSTRUCTIONS_TYPE = ''#39#39''AUDIT''#39#39''}''#39'',TagValue=,FKey=,Pa'' +
        ''ramsSrc=@Default,Action=Invoke,Tool=AuditOrderInstructions,Repor'' +
        ''tToolId=,Params(INSP_ORDER_ID=:INSP_ORDER_ID)''
      
        ''Desc=Display CA,Priv={},Visible=''#39''{CA_ID <> ''#39#39#39#39''}''#39'',TagValue=,FKey'' +
        ''=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceName=@ToolSco'' +
        ''pe,RefreshedSQLSourceName=,SqlID=MFI_QA_DISPLAYCASOLUTIONACTIONP'' +
        ''LAN'')
  end
  object ACCEPT: TsfCommandButton
    Left = 264
    Top = 4
    Width = 90
    Height = 35
    Hint = ''Accept''
    Caption = ''Accept''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 10
    OtherCommands.Strings = (
      
        ''Desc=Accept,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39''}''#39'',Visible=''#39''{IS_ACCEP'' +
        ''T_REJECT_QUEUE = ''#39#39''Y''#39#39'' AND DISC_LINE_STATUS <> ''#39#39#39#39'' AND DISC_LIN'' +
        ''E_STATUS <> ''#39#39''CANCEL''#39#39'' AND DISC_LINE_STATUS <> ''#39#39''DISPOSITIONED''#39#39 +
        '' AND DISC_LINE_STATUS <> ''#39#39''IMPLEMENTED''#39#39''}''#39'',TagValue=ACCEPT,FKey='' +
        '',ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_59DE10D7'' +
        ''11984DB8B3DA93F97009154D'')
  end
  object REJECT: TsfCommandButton
    Left = 359
    Top = 4
    Width = 90
    Height = 35
    Hint = ''Reject''
    Caption = ''Reject''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 11
    OtherCommands.Strings = (
      
        ''Desc=Reject,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND IS_REJECT_QUEUE '' +
        ''= ''#39#39''Y''#39#39''}''#39'',Visible=''#39''{IS_ACCEPT_REJECT_QUEUE = ''#39#39''Y''#39#39'' AND DISC_LINE'' +
        ''_STATUS <> START_QUEUE_TYPE AND DISC_LINE_STATUS <> ''#39#39#39#39'' AND DIS'' +
        ''C_LINE_STATUS <> ''#39#39''CANCEL''#39#39''  AND DISC_LINE_STATUS <> ''#39#39''DISPOSITI'' +
        ''ONED''#39#39'' AND DISC_LINE_STATUS <> ''#39#39''IMPLEMENTED''#39#39''}''#39'',TagValue=REJECT'' +
        '',FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_59'' +
        ''DE10D711984DB8B3DA93F97009154D'')
  end
  object Nexttask: TsfCommandButton
    Left = 6
    Top = 4
    Width = 65
    Height = 35
    Hint = ''Next Task''
    Caption = ''Next''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clGreen
    Font.Height = -21
    Font.Name = ''Tahoma''
    Font.Style = [fsBold]
    ParentFont = False
    ShowHint = True
    TabOrder = 12
    OtherCommands.Strings = (
      
        ''Desc=Execute Next,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND DISC_LINE_'' +
        ''STATUS <> ''#39#39''DISPOSITIONED''#39#39'' AND DISC_LINE_STATUS <> ''#39#39''CANCEL''#39#39'' A'' +
        ''ND DISC_LINE_STATUS <> ''#39#39''IMPLEMENTED''#39#39'' AND @ToolState <>''#39#39''ViewMo'' +
        ''des.View''#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Actio'' +
        ''n=ExecSql,ParamsSqlSourceName=@ToolScope,RefreshedSQLSourceName='' +
        '',SqlID=MFI_DI_START_NEXT_TASK'')
  end
  object CollapseAll: TsfCommandButton
    Left = 1063
    Top = 4
    Width = 90
    Height = 35
    Caption = ''Collapse All''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 13
    OtherCommands.Strings = (
      
        ''Desc=collapseAll,Priv={},Visible=''#39''{@ClientType <> ''#39#39''Web''#39#39''}''#39'',TagV'' +
        ''alue=Collapse,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlS'' +
        ''ourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_SFQA_COLL'' +
        ''APSE_ALL'')
  end
  object ExpandAll: TsfCommandButton
    Left = 1157
    Top = 4
    Width = 90
    Height = 35
    Caption = ''Expand All''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 14
    OtherCommands.Strings = (
      
        ''Desc=ExpandAll,Priv={},Visible=''#39''{@ClientType <> ''#39#39''Web''#39#39''}''#39'',TagVal'' +
        ''ue=Expand,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourc'' +
        ''eName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_SFQA_COLLAPSE'' +
        ''_ALL'')
  end
  object DiscItemsSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''DiscItemSel''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''VERSION~''
      ''OFD~''
      ''_OBJ2~''
      ''Display''
      ''ACCEPT''
      ''REJECT''
      ''CreateCA~'')
    ParamsSQLSourceName = ''CurrentQueueSelect,@ToolScope''
    PublishParams = True
    InputUDVId = ''MFI_1005714''
    ConsolidatedQuery = False
  end
  object DiscTaskTabSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_QA_DiscTaskSelect''
    LinkedControls.Strings = (
      ''CLAIM_TASK~''
      ''release_task~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''TASK_ID~0~TASK_ID~N~N~False~False~False~N~~~~''
      ''STATUS~0~STATUS~N~N~False~False~False~N~~~~''
      ''TASK_TYPE~0~TASK_TYPE~N~N~False~False~False~N~~~~''
      ''QUEUE_TYPE~0~QUEUE_TYPE~N~N~False~False~False~N~~~~''
      ''UPDT_USERID~0~UPDT_USERID~N~N~False~False~False~N~~~~''
      ''NOTES~0~NOTES~N~N~False~False~False~N~~~~'')
    SelectedFieldsEditControl.Strings = (
      ''NOTES~Edit'')
    DataDefinitions.Strings = (
      ''TASK_ID''
      ''STATUS''
      ''TASK_TYPE''
      ''QUEUE_TYPE''
      ''UPDT_USERID''
      ''NOTES'')
    ConsolidatedQuery = False
  end
  object ToFromQueueTypes: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_ToFromQueueTypes''
    LinkedControls.Strings = (
      ''COMPLETE_TASK~''
      ''RETURN_TO~''
      ''ACCEPT~''
      ''REJECT~'')
    ParamsSQLSourceName = ''@ToolScope, DiscTaskTabSel, DiscItemsSelect''
    PublishParams = True
    ConsolidatedQuery = False
  end
  object CurrentQueueSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_SQA_DISCREPANCYCURRENTQUEUESEL''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''CURRENT_QUEUE~0~CURRENT_QUEUE~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~'')
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

