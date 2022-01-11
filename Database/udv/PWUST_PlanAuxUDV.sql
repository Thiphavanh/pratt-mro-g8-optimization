
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_55E687F33FD25425E05387971F0A0663.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_55E687F33FD25425E05387971F0A0663';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanAuxUDV';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_EXPT_201';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1200
  Height = 50
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
  object CLAIM_TASK: TsfCommandButton
    Left = 6
    Top = 6
    Width = 95
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
      
        ''Desc=Claim Task,''#39''Priv={ASSIGNED_TO_USERID <> @GetSystemDataValue'' +
        ''(@USERID) AND REV_STATUS <> ''#39#39''PLAN COMPLETE''#39#39'' AND REV_STATUS <> '' +
        #39#39''PLAN RELEASED''#39#39'' AND PLAN_ID <>''#39#39#39#39''}''#39'',Visible={},TagValue=a,FKe'' +
        ''y=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_DB13C0'' +
        ''C02F8C4075982195C426717827'')
  end
  object release_task: TsfCommandButton
    Left = 110
    Top = 6
    Width = 95
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
      
        ''Desc=Release Task,''#39''Priv=ASSIGNED_TO_USERID = @GetSystemDataValue'' +
        ''(@USERID) AND REV_STATUS <> ''#39#39''PLAN COMPLETE''#39#39'' AND REV_STATUS <> '' +
        #39#39''PLAN RELEASED''#39#39'' AND PLAN_ID <>''#39#39#39#39#39'',Visible=,TagValue=a,FKey=,'' +
        ''Action=ExecSql,ParamsSqlSourceName=Plg_Task_AcIQ_Select,Refreshe'' +
        ''dSQLSourceName=Plg_Task_AcIQ_Select,SqlID=WID_OrderAltTaskReleas'' +
        ''e'')
  end
  object COMPLETE_TASK: TsfCommandButton
    Left = 214
    Top = 6
    Width = 95
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
    TabOrder = 2
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Complete Task,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND IS_ACCEPT'' +
        ''_REJECT_QUEUE = ''#39#39''N''#39#39'' AND CURRENT_STATUS <> ''#39#39#39#39'' AND CURRENT_STA'' +
        ''TUS <> ''#39#39''PLAN COMPLETE''#39#39'' AND CURRENT_STATUS <> ''#39#39''PLAN RELEASED''#39#39 +
        '' AND PLAN_ID <>''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Defa'' +
        ''ult,Action=UDV,UDVType=Update,UDVID=PWUST_5658B87029B85095E05387'' +
        ''971F0A415F'')
  end
  object ACCEPT: TsfCommandButton
    Left = 214
    Top = 6
    Width = 95
    Height = 21
    Hint = ''Accept''
    Caption = ''Accept''
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
      
        ''Desc=Accept,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND PLAN_ID <>''#39#39#39#39''}''#39 +
        '',Visible=''#39''{IS_ACCEPT_REJECT_QUEUE = ''#39#39''Y''#39#39'' AND CURRENT_STATUS <> '' +
        #39#39#39#39'' AND CURRENT_STATUS <> ''#39#39''PLAN COMPLETE''#39#39'' AND CURRENT_STATUS '' +
        ''<> ''#39#39''PLAN RELEASED''#39#39''}''#39'',TagValue=ACCEPT,FKey=,ParamsSrc=@Default,'' +
        ''Action=UDV,UDVType=Update,UDVID=MFI_B97BF527467F4EBCB385CA948138'' +
        ''3FD9'')
  end
  object RETURN_TO: TsfCommandButton
    Left = 318
    Top = 6
    Width = 95
    Height = 21
    Hint = ''Return To''
    Caption = ''Return To''
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
      
        ''Desc=Return To,Priv=''#39''@CurrentEditBlock = ''#39#39#39#39'' AND IS_ACCEPT_REJE'' +
        ''CT_QUEUE = ''#39#39''N''#39#39'' AND CURRENT_STATUS <> ''#39#39#39#39'' AND CURRENT_STATUS <'' +
        ''> START_QUEUE_TYPE AND CURRENT_STATUS <> ''#39#39''PLAN COMPLETE''#39#39'' AND C'' +
        ''URRENT_STATUS <> ''#39#39''PLAN RELEASED''#39#39'' AND PLAN_ID <>''#39#39#39#39#39'',Visible=,'' +
        ''TagValue=,FKey=,Action=UDV,UDVType=Insert,UDVID=MFI_1007343'')
  end
  object REJECT: TsfCommandButton
    Left = 318
    Top = 6
    Width = 95
    Height = 21
    Hint = ''Reject''
    Caption = ''Reject''
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
      
        ''Desc=Reject,Priv=''#39''{@CurrentEditBlock = ''#39#39#39#39'' AND PLAN_ID <>''#39#39#39#39'' A'' +
        ''ND IS_REJECT_QUEUE = ''#39#39''Y''#39#39''}''#39'',Visible=''#39''{IS_ACCEPT_REJECT_QUEUE = '' +
        #39#39''Y''#39#39'' AND CURRENT_STATUS <> ''#39#39#39#39'' AND CURRENT_STATUS <> START_QUE'' +
        ''UE_TYPE AND CURRENT_STATUS <> ''#39#39''PLAN COMPLETE''#39#39'' AND CURRENT_STAT'' +
        ''US <> ''#39#39''PLAN RELEASED''#39#39''}''#39'',TagValue=REJECT,FKey=,ParamsSrc=@Defau'' +
        ''lt,Action=UDV,UDVType=Update,UDVID=PWUST_5AEE015AD1E89CDDE053879'' +
        ''71F0A6755'')
  end
  object REVISION: TsfCommandButton
    Left = 422
    Top = 6
    Width = 95
    Height = 21
    Hint = ''Create Revision''
    Caption = ''Create Revision''
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
      
        ''Desc=Create Revision,Priv=''#39''REV_STATUS = ''#39#39''PLAN COMPLETE''#39#39'' OR REV'' +
        ''_STATUS = ''#39#39''PLAN RELEASED''#39#39'' AND PLAN_ID <>''#39#39#39#39#39'',Visible=,TagValu'' +
        ''e=,FKey=,Action=UDV,UDVType=Update,UDVID=MF1_1000073'')
  end
  object VERSION: TsfCommandButton
    Left = 526
    Top = 6
    Width = 95
    Height = 21
    Hint = ''Create Version''
    Caption = ''Create Version''
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
      
        ''Desc=Create Version,Priv=''#39''@CurrentEditBlock = ''#39#39#39#39'' AND MRO_FLAG='' +
        #39#39''N''#39#39'' AND PLAN_ID <>''#39#39#39#39#39'',Visible=''#39''MRO_FLAG=''#39#39''N''#39#39#39'',TagValue=,FKe'' +
        ''y=,Action=UDV,UDVType=Update,UDVID=MF1_1000080'')
  end
  object OFD: TsfCommandButton
    Left = 630
    Top = 6
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
    TabOrder = 8
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Display OFD,Priv=''#39''PLAN_ID <>''#39#39#39#39'' AND DISPLAY_SEQUENCE = ''#39#39''F'' +
        ''low Diagram''#39#39#39'',Visible=''#39''PLAN_ID <>''#39#39#39#39'' AND DISPLAY_SEQUENCE = ''#39#39 +
        ''Flow Diagram''#39#39#39'',TagValue=,FKey=,Action=Invoke,Tool=FlowDiagrams.'' +
        ''PlanOFD,ReportToolId=,Params(''#39''PLAN_ID=:PLAN_ID,PLAN_VERSION=:PLA'' +
        ''N_VERSION,PLAN_REVISION=:PLAN_REVISION,PLAN_ALTERATIONS=:PLAN_AL'' +
        ''TERATIONS,@InvokeToolmode=:TOOL_MODE,SERIAL_FLAG=:SERIAL_FLAG,PL'' +
        ''AN_NO=:PLAN_NO''#39'')'')
  end
  object DispSwimLane: TsfCommandButton
    Left = 630
    Top = 6
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
      
        ''Desc=Display Swim Lanes,Priv=''#39''PLAN_ID <>''#39#39#39#39'' AND DISPLAY_SEQUENC'' +
        ''E = ''#39#39''Execution Order''#39#39#39'',Visible=''#39''PLAN_ID <>''#39#39#39#39'' AND DISPLAY_SEQ'' +
        ''UENCE = ''#39#39''Execution Order''#39#39#39'',TagValue=,FKey=,Action=Invoke,Tool='' +
        ''FlowDiagrams.PlanSwimLanes,ReportToolId=,Params(''#39''PLAN_ID=:PLAN_I'' +
        ''D,PLAN_VERSION=:PLAN_VERSION,PLAN_REVISION=:PLAN_REVISION,PLAN_A'' +
        ''LTERATIONS=:PLAN_ALTERATIONS,@InvokeToolmode=:TOOL_MODE,SERIAL_F'' +
        ''LAG=:SERIAL_FLAG,PLAN_NO=:PLAN_NO''#39'')'')
  end
  object _OBJ1: TsfCommandButton
    Left = 800
    Top = 6
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
    TabOrder = 10
    OtherCommands.Strings = (
      
        ''Desc=collpseAll,Priv={},Visible=''#39''{@ClientType <> ''#39#39''Web''#39#39''}''#39'',TagVa'' +
        ''lue=Collapse,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSo'' +
        ''urceName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_SFPL_COLLA'' +
        ''PSE_ALL'')
  end
  object expandAllButton: TsfCommandButton
    Left = 910
    Top = 7
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
    TabOrder = 11
    OtherCommands.Strings = (
      
        ''Desc=ExpandAll,Priv={},Visible=''#39''{@ClientType <> ''#39#39''Web''#39#39''}''#39'',TagVal'' +
        ''ue=Expand,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourc'' +
        ''eName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_SFPL_COLLAPSE'' +
        ''_ALL'')
  end
  object Plg_Task_AcIQ_Select: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_AUX_UDV_SEL''
    LinkedControls.Strings = (
      ''CLAIM_TASK~''
      ''release_task~'')
    ParamsSQLSourceName = ''PlanRevStatus,@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''TASK_ID~0~TASK_ID~N~N~False~False~False~N~~~~''
      ''TASK_TYPE~0~TASK_TYPE~N~N~False~False~False~N~~~~''
      ''QUEUE_TYPE~0~QUEUE_TYPE~N~N~False~False~False~N~~~~''
      ''QUEUE_ID~0~QUEUE_ID~N~N~False~False~False~N~~~~''
      ''PRIORITY~0~PRIORITY~N~N~False~False~False~N~~~~''
      
        ''ASSIGNED_TO_USERID~0~ASSIGNED_TO_USERID~N~N~False~False~False~N~'' +
        ''~~~''
      ''STATUS~0~STATUS~N~N~False~False~False~N~~~~''
      ''NOTES~0~NOTES~N~N~False~False~False~N~~~~''
      ''SCH_START_DATE~0~SCH_START_DATE~N~N~False~False~False~N~~~~''
      ''SCH_END_DATE~0~SCH_END_DATE~N~N~False~False~False~N~~~~''
      ''SCH_DAYS~0~SCH_DAYS~N~N~False~False~False~N~~~~''
      ''REV_START_DATE~0~REV_START_DATE~N~N~False~False~False~N~~~~''
      ''REV_END_DATE~0~REV_END_DATE~N~N~False~False~False~N~~~~''
      ''REV_DAYS~0~REV_DAYS~N~N~False~False~False~N~~~~''
      ''UPDT_USERID~0~UPDT_USERID~N~N~False~False~False~N~~~~''
      ''TIME_STAMP~0~TIME_STAMP~N~N~False~False~False~N~~~~''
      ''CHANGE_DOC_TYPE~0~CHANGE_DOC_TYPE~N~N~False~False~False~N~~~~''
      ''CHANGE_DOC_ID~0~CHANGE_DOC_ID~N~N~False~False~False~N~~~~''
      ''PLAN_ID~0~PLAN_ID~N~N~False~False~False~N~~~~''
      ''PART_NO~0~PART_NO~N~N~False~False~False~N~~~~''
      ''PART_CHG~0~PART_CHG~N~N~False~False~False~N~~~~''
      ''PROGRAM~0~PROGRAM~N~N~False~False~False~N~~~~''
      ''PLAN_VERSION~0~PLAN_VERSION~N~N~False~False~False~N~~~~''
      ''PLAN_REVISION~0~PLAN_REVISION~N~N~False~False~False~N~~~~''
      ''PLAN_ALTERATIONS~0~PLAN_ALTERATIONS~N~N~False~False~False~N~~~~''
      ''LOW_NAV_LVL~0~LOW_NAV_LVL~N~N~False~False~False~N~~~~''
      ''NEW_PART_NO~0~NEW_PART_NO~N~N~False~False~False~N~~~~'')
    SelectedFieldsEditControl.Strings = (
      ''PLAN_ID~Edit''
      ''PART_NO~Edit''
      ''ASSIGNED_TO_USERID~Edit''
      ''NOTES~Edit'')
    TestParamValues.Strings = (
      ''='')
    DataDefinitions.Strings = (
      ''TASK_ID''
      ''TASK_TYPE''
      ''QUEUE_TYPE''
      ''QUEUE_ID''
      ''PRIORITY''
      ''ASSIGNED_TO_USERID''
      ''STATUS''
      ''NOTES''
      ''SCH_START_DATE''
      ''SCH_END_DATE''
      ''SCH_DAYS''
      ''REV_START_DATE''
      ''REV_END_DATE''
      ''REV_DAYS''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''CHANGE_DOC_TYPE''
      ''CHANGE_DOC_ID''
      ''PLAN_ID''
      ''PART_NO''
      ''PART_CHG''
      ''PROGRAM''
      ''PLAN_VERSION''
      ''PLAN_REVISION''
      ''PLAN_ALTERATIONS''
      ''LOW_NAV_LVL''
      ''NEW_PART_NO'')
    ConsolidatedQuery = False
  end
  object PlanRevStatus: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_PlanOFDTopSel''
    LinkedControls.Strings = (
      ''VERSION~''
      ''REVISION~''
      ''OFD~''
      ''DispSwimLane~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''PLAN_ID~0~PLAN_ID~N~N~False~False~False~N~~~~~Default~''
      ''PLAN_UPDT_NO~0~PLAN_UPDT_NO~N~N~False~False~False~N~~~~~Default~''
      ''PART_NO~0~PART_NO~N~N~False~False~False~N~~~~~Default~''
      ''ITEM_TYPE~0~ITEM_TYPE~N~N~False~False~False~N~~~~~Default~''
      ''ITEM_SUBTYPE~0~ITEM_SUBTYPE~N~N~False~False~False~N~~~~~Default~''
      ''PLAN_TITLE~0~PLAN_TITLE~N~N~False~False~False~N~~~~~Default~''
      ''PART_CHG~0~PART_CHG~N~N~False~False~False~N~~~~~Default~''
      ''PROGRAM~0~PROGRAM~N~N~False~False~False~N~~~~~Default~''
      ''PLAN_VERSION~0~PLAN_VERSION~N~N~False~False~False~N~~~~~Default~''
      
        ''PLAN_REVISION~0~PLAN_REVISION~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~''
      
        ''PLAN_ALTERATIONS~0~PLAN_ALTERATIONS~N~N~False~False~False~N~~~~~'' +
        ''Default~''
      ''PLAN_STATUS~0~PLAN_STATUS~N~N~False~False~False~N~~~~~Default~''
      ''REV_STATUS~0~REV_STATUS~N~N~False~False~False~N~~~~~Default~''
      
        ''NEEDS_REVIEW_FLAG~0~NEEDS_REVIEW_FLAG~N~N~False~False~False~N~~~'' +
        ''~~Default~''
      
        ''EXTERNAL_PLM_NO~0~EXTERNAL_PLM_NO~N~N~False~False~False~N~~~~~De'' +
        ''fault~''
      
        ''EXTERNAL_ERP_NO~0~EXTERNAL_ERP_NO~N~N~False~False~False~N~~~~~De'' +
        ''fault~''
      ''TOOL_MODE~0~TOOL_MODE~N~N~False~False~False~N~~~~~Default~'')
    DataDefinitions.Strings = (
      ''PLAN_ID''
      ''PLAN_UPDT_NO''
      ''PART_NO''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''PLAN_TITLE''
      ''PART_CHG''
      ''PROGRAM''
      ''PLAN_VERSION''
      ''PLAN_REVISION''
      ''PLAN_ALTERATIONS''
      ''PLAN_STATUS''
      ''REV_STATUS''
      ''NEEDS_REVIEW_FLAG''
      ''EXTERNAL_PLM_NO''
      ''EXTERNAL_ERP_NO''
      ''TOOL_MODE'')
    ConsolidatedQuery = False
  end
  object ToFromQueueTypes: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_ToFromQueueTypes''
    LinkedControls.Strings = (
      ''COMPLETE_TASK~''
      ''ACCEPT~''
      ''RETURN_TO~''
      ''REJECT~'')
    ParamsSQLSourceName = ''@ToolScope, PlanRevStatus''
    PublishParams = True
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

