
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_BB13DAEA03288018E05387971F0AE6D2.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_BB13DAEA03288018E05387971F0AE6D2';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_SFOR_3490';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='DEFECT 1055';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 650
  Height = 500
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
    Left = 2
    Top = 27
    Width = 318
    Height = 150
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
    Left = 2
    Top = 4
    Width = 96
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object Operations: TsfLabel
    Left = 124
    Top = 9
    Width = 53
    Height = 13
    Caption = ''Operations''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ3: TsfDBGrid
    Left = 3
    Top = 210
    Width = 647
    Height = 150
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 2
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Highlighting.Strings = (
      ''@Row~Gray~Default~OBSOLETE_RECORD_FLAG=''#39''Y''#39
      ''@Row~Red~Default~SUBJECT_STATUS=''#39''HOLD''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ4: TsfUDVNavigator
    Left = 3
    Top = 187
    Width = 96
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 3
    TabStop = True
  end
  object _OBJ5: TsfLabel
    Left = 252
    Top = 192
    Width = 110
    Height = 13
    Caption = ''Operation Task Groups''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ11: TsfDBGrid
    Left = 331
    Top = 27
    Width = 318
    Height = 150
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
  object _OBJ12: TsfLabel
    Left = 412
    Top = 9
    Width = 133
    Height = 13
    Caption = ''Operation Revisions History''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ13: TsfCommandButton
    Left = 335
    Top = 8
    Width = 80
    Height = 16
    Hint = ''Remove File''
    Caption = ''Remove File''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Remove File,Priv={IMAGE=''#39''Image''#39'' AND REV_STATUS<>''#39''PLAN COMPL'' +
        ''ETE''#39'' AND REV_STATUS<>''#39''PLAN RELEASED''#39''},Visible={},TagValue=,FKey='' +
        '',''#39''ParamsSrc=@Default,OperationRevisions''#39'',Action=ExecSql,ParamsSq'' +
        ''lSourceName=PlanOperations,''#39''RefreshedSQLSourceName=PlanOperation'' +
        ''s,OperationRevisions''#39'',SqlID=MFI_PLG_OPER_REMOVE_FILE'')
  end
  object _OBJ14: TsfCommandButton
    Left = 202
    Top = 8
    Width = 125
    Height = 16
    Hint = ''Operation Precedence''
    Caption = ''Display Relationships''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Operation Precedence,Priv=,Visible=,TagValue=,FKey=,Action='' +
        ''SideNote,SqlID=MFI_PLG_OperPrecedenceOperationSidePanel,Params(''#39 +
        ''PLAN_ID=:PLAN_ID,''#39'',''#39''OPER_KEY=:OPER_KEY,''#39'',OPER_UPDT_NO=:OPER_UPDT'' +
        ''_NO)'')
  end
  object PlanOperations: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_OPERSEL''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''_OBJ14~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''OPER_NO~4~Oper;No~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OPER_UPDT_NO~4~Oper;Rev~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''STATUS~5~ ~N~N~False~False~False~N~~@StdMarkup(STATUS)~~~Default'' +
        ''~N~~~''
      
        ''SUBJECT_NO_DISP~5~Task Group~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      ''PLND_WORK_CENTER~30~Work Center; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OPER_TITLE~80~Oper Title; ~N~N~N~N~N~N~~~~~Default~Y~~~''
      
        ''OPER_CHANGE_LEVEL~10~Change;Significance~N~N~False~False~False~N'' +
        ''~~~~~Default~N~~~''
      
        ''UNIT_PROCESSING~10~Unit;Processing~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~~''
      ''UPDT_USERID~30~Update UserID; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''TIME_STAMP~18~Update Time; ~N~N~N~N~N~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''PLND_WORK_CENTER~Edit''
      ''STATUS~Edit''
      ''OPER_UPDT_NO~Edit''
      ''OPER_NO~Edit'')
    TestParamValues.Strings = (
      ''PLAN_ID=MFI_C3A3C54E67884B4F5A585CCC45F4B319''
      ''PLAN_VERSION=1''
      ''PLAN_REVISION=1''
      ''PLAN_ALTERATIONS=0''
      ''SCOPE_PLAN_ID=1'')
    DataDefinitions.Strings = (
      ''OPER_NO''
      ''OPER_UPDT_NO''
      ''SUBJECT_NO_DISP''
      ''STATUS''
      ''PLND_WORK_CENTER''
      ''OPER_TITLE''
      ''OPER_CHANGE_LEVEL''
      ''UNIT_PROCESSING''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object SFOR_SfplOperSubjectSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SfplOperSubjectSel''
    LinkedControls.Strings = (
      ''_OBJ3~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''PlanOperations''
    PublishParams = True
    SelectedFields.Strings = (
      ''SUBJECT_NO~4~Task Group;No~N~N~N~N~N~N~~~~~Default~N~~~''
      ''SUBJECT_REV~4~Task Group;Rev~N~N~N~N~N~N~~~~~Default~N~~~''
      ''TITLE~50~Task Group Title~N~N~N~N~N~N~~~~~Default~Y~~~''
      ''UPDT_USERID~13~Update UserID; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''TIME_STAMP~18~Update Time; ~N~N~N~N~N~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''SUBJECT_NO~Edit'')
    DataDefinitions.Strings = (
      ''SUBJECT_NO''
      ''SUBJECT_REV''
      ''TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object SFOR_SfplPlanSubjectPartSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SfplPlanSubjectPartSel''
    ParamsSQLSourceName = ''SFOR_SfplOperSubjectSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''PART_NO~12~Part No~N~N~N~N~N~N~~~~~Default~N~''
      ''PART_TITLE~80~Part Description~N~N~N~N~N~N~~~~~Default~Y~''
      ''UPDT_USERID~30~Update;UserID~N~N~N~N~N~N~~~~~Default~N~''
      ''TIME_STAMP~18~Update;Time~N~N~N~N~N~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''UPDT_USERID~Edit'')
    TestParamValues.Strings = (
      ''SCOPE_PLAN_ID=MFI_C3A3C54E67884B4F5A585CCC45F4B319''
      ''PLAN_UPDT_NO=1''
      ''SUBJECT_NO=1'')
    DataDefinitions.Strings = (
      ''PART_NO''
      ''PART_TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object OperationRevisions: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_OPERATIONREVISIONSEL''
    LinkedControls.Strings = (
      ''_OBJ13~''
      ''_OBJ11~'')
    ParamsSQLSourceName = ''@ToolScope,PlanOperations''
    SelectedFields.Strings = (
      
        ''IMAGE~2~ ~N~N~False~False~False~N~~@StdMarkup(IMAGE)~@Command(CO'' +
        ''MMAND)~~Default~N~~''
      ''PLAN_REVISION~4~Plan;Rev~N~N~False~False~False~N~~~~~Default~N~~''
      ''OPER_NO~4~Oper;No~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''OPER_TITLE~30~Oper Title; ~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~''
      ''OPER_UPDT_NO~4~Oper;Rev~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''UPDT_USERID~15~Update UserID; ~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''TIME_STAMP~18~Update Time; ~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~'')
    SelectedFieldsEditControl.Strings = (
      ''TIME_STAMP~Edit''
      ''OPER_TITLE~Edit''
      ''IMAGE~Edit''
      ''PLAN_REVISION~Edit'')
    TestParamValues.Strings = (
      ''PLAN_ID=MFI_C3A3C54E67884B4F5A585CCC45F4B319''
      ''OPER_KEY=1602472'')
    OtherCommands.Strings = (
      
        ''Desc=Add Attachment,Priv={@ToolState<>''#39''ViewModes.View''#39'' AND IMAGE'' +
        ''=''#39''LabelPlus''#39''},Visible={},TagValue=,FKey=,ParamsSrc=@Default,Acti'' +
        ''on=UDV,UDVType=Insert,UDVID=MFI_768EE0785FD7373CE04400144FA7B7D2''
      
        ''Desc=Display,Priv={},Visible={IMAGE=''#39''Image''#39''},TagValue=,FKey=,''#39''Pa'' +
        ''ramsSrc=@Default,OperationRevisions''#39'',Action=SideNote,SqlID=,Para'' +
        ''ms(''#39''@SubConfig=:SUB_CONFIG,''#39'',''#39''OBJECT_ID=:OBJECT_ID,''#39'',''#39''SLIDE_REF_'' +
        ''ID=,''#39'',''#39''TESTPARAM1=TASK_GROUP_OPERATION+:PLAN_ID+:PLAN_VERSION+:P'' +
        ''LAN_REVISION+:PLAN_ALTERATIONS+:OPER_NO+:OPER_KEY+:OPER_UPDT_NO+'' +
        '':LOW_NAV_LVL,''#39'',@BlockCaption=Slide)''
      
        ''Desc=Dummy,Priv=,Visible=1<>1,TagValue=,FKey=,Action=ExecSql,Par'' +
        ''amsSqlSourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=dummy'')
    DataDefinitions.Strings = (
      ''OBJECT_ID''
      ''IMAGE''
      ''COMMAND''
      ''PLAN_REVISION''
      ''OPER_NO''
      ''OPER_TITLE''
      ''OPER_UPDT_NO''
      ''UPDT_USERID''
      ''TIME_STAMP'')
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

