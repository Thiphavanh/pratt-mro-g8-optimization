
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_AD340DFB443C4AD29BCB9913C3F4D8C7.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWMROI_3701A7AFD2A14650970AA2A632A73570';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWMROI_AD340DFB443C4AD29BCB9913C3F4D8C7';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWMROI_SfplPlanSubjectSel';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.0.1.20180226~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 900
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
    Width = 440
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
    Highlighting.Strings = (
      ''@Row~Gray~Default~OBSOLETE_RECORD_FLAG=''#39''Y''#39
      ''@Row~Red~Default~SUBJECT_STATUS=''#39''HOLD''#39)
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
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''Refresh ''
      ''Other commands '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object _OBJ3: TsfDBGrid
    Left = 2
    Top = 210
    Width = 440
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
    Left = 2
    Top = 187
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 3
    TabStop = True
  end
  object _OBJ5: TsfDBGrid
    Left = 450
    Top = 210
    Width = 440
    Height = 150
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
    Highlighting.Strings = (
      ''@Row~Gray~Default~OBSOLETE_RECORD_FLAG=''#39''Y''#39
      ''@Row~Red~Default~SUBJECT_STATUS=''#39''HOLD''#39)
    Hidden = 
      ''(@GetParamSourceValue(@ToolScope,''#39''INST_TYPE''#39'') <> ''#39''MRO-Upgrade''#39'') '' +
      ''AND (@GetParamSourceValue(@ToolScope,''#39''INST_TYPE''#39'') <> ''#39''MRO-Part''#39'')''
    RowSelection = True
  end
  object _OBJ6: TsfUDVNavigator
    Left = 450
    Top = 187
    Width = 58
    Height = 21
    Hidden = ''-''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 5
    TabStop = True
  end
  object _OBJ7: TsfLabel
    Left = 123
    Top = 10
    Width = 59
    Height = 13
    Caption = ''Task Groups''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ8: TsfLabel
    Left = 87
    Top = 190
    Width = 110
    Height = 13
    Caption = ''Task Group Operations''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ10: TsfCommandButton
    Left = 259
    Top = 4
    Width = 74
    Height = 21
    Hint = ''Hold''
    Caption = ''Hold''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Hold,Priv=,Visible=''#39''SUBJECT_STATUS<>''#39#39''HOLD''#39#39#39'',TagValue=,FKe'' +
        ''y=,Action=UDV,UDVType=Update,UDVID=SFOR_137'')
  end
  object _OBJ12: TsfLabel
    Left = 450
    Top = 10
    Width = 102
    Height = 13
    Caption = ''Task Group Revisions''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ13: TsfDBGrid
    Left = 450
    Top = 27
    Width = 440
    Height = 150
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 7
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
  object _OBJ14: TsfCommandButton
    Left = 620
    Top = 4
    Width = 74
    Height = 21
    Hint = ''Remove File''
    Caption = ''Remove File''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 8
    OtherCommands.Strings = (
      
        ''Desc=Remove File,Priv={IMAGE=''#39''Image''#39'' AND REV_STATUS<>''#39''PLAN COMPL'' +
        ''ETE''#39'' AND REV_STATUS<>''#39''PLAN RELEASED''#39''},Visible={},TagValue=,FKey='' +
        '',''#39''ParamsSrc=@Default,MFI_PLG_SFOR_TASK_GROUPS_REV''#39'',Action=ExecSq'' +
        ''l,ParamsSqlSourceName=SFOR_SfplPlanSubjectSel,''#39''RefreshedSQLSourc'' +
        ''eName=SFOR_SfplPlanSubjectSel, MFI_PLG_SFOR_TASK_GROUPS_REV''#39'',Sql'' +
        ''ID=MFI_PLG_SUBJECT_REMOVE_FILE'')
  end
  object _OBJ15: TsfCommandButton
    Left = 259
    Top = 4
    Width = 74
    Height = 21
    Hint = ''Un-Hold''
    Caption = ''Un-Hold''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 9
    OtherCommands.Strings = (
      
        ''Desc=Un-Hold,Priv={},Visible=''#39''{SUBJECT_STATUS=''#39#39''HOLD''#39#39''}''#39'',TagValu'' +
        ''e=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=SFOR'' +
        ''_137'')
  end
  object _OBJ16: TsfBevel
    Left = 450
    Top = 197
    Width = 300
    Height = 183
    Shape = bsSpacer
    Hidden = 
      ''(@GetParamSourceValue(@ToolScope,''#39''INST_TYPE''#39'') <> ''#39''MRO-Upgrade''#39'') '' +
      ''AND (@GetParamSourceValue(@ToolScope,''#39''INST_TYPE''#39'') <> ''#39''MRO-Part''#39'')''
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object _OBJ17: TsfLabel
    Left = 510
    Top = 190
    Width = 390
    Height = 13
    Caption = 
      ''@Lookup(INST_TYPE,''#39''MRO-Upgrade:Starting Item No/Rev''#39'',''#39''Task Group'' +
      '' Item No''#39'')''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ18: TsfCommandButton
    Left = 620
    Top = 187
    Width = 135
    Height = 21
    Hint = ''Copy Task Group Part''
    Caption = ''Copy Task Group Item''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 10
    OtherCommands.Strings = (
      
        ''Desc=Copy Task Group Part,Priv={},Visible=''#39''{INST_TYPE = ''#39#39''MRO-Pa'' +
        ''rt''#39#39'' OR INST_TYPE = ''#39#39''MRO-Upgrade''#39#39''}''#39'',TagValue=,FKey=,Action=UDV'' +
        '',UDVType=Update,UDVID=MFI_227E67D1DF5D42D091BE1D02F7CFA36E'')
  end
  object SFOR_SfplPlanSubjectSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWMROI_SfplPlanSubject_Sel''
    DeleteSqlId = ''SFOR_SfplPlanSubjectDel''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''_OBJ10~''
      ''_OBJ15~''
      ''_OBJ18~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope''
    RefreshedSQLSourceName = ''MFI_PLG_SFOR_TASK_GROUPS_REV''
    PublishParams = True
    SelectedFields.Strings = (
      ''SUBJECT_NO~6~Task Group;No~N~N~N~N~N~N~~~~~Default~N~~~''
      ''SUBJECT_REV~6~Task Group;Rev~N~N~N~N~N~N~~~~~Default~N~~~''
      ''SUBJECT_STATUS~6~Task Group;Status~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''IS_DEFAULT~1~Auto;Include?~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''STATUS~1~ ~N~N~False~False~False~N~~@StdMarkup(STATUS)~~~Default'' +
        ''~N~~~''
      
        ''TITLE~29~Task Group Description~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      ''AUTHORITY~12~Auth Notes~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''UPDT_USERID~10~Update;User ID~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''TIME_STAMP~13~Update;Time~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~'')
    SelectedFieldsEditControl.Strings = (
      ''TIME_STAMP~Edit''
      ''TITLE~Edit'')
    TestParamValues.Strings = (
      ''scope_plan_id=1''
      ''plan_id=1''
      ''plan_version=1''
      ''plan_alterations=0''
      ''plan_revision=1'')
    InputUDVId = ''MFI_177ACB03EBC64A8BB6AA9BEEA9FB8CF5''
    InsertUDVId = ''MFI_177ACB03EBC64A8BB6AA9BEEA9FB8CF5''
    OtherCommands.Strings = (
      
        ''Desc=Task Group Copy,Priv={},Visible={},TagValue=,FKey=,ParamsSr'' +
        ''c=@Default,Action=UDV,UDVType=Update,UDVID=SFOR_41''
      
        ''Desc=Obsolete Task Group,Priv={},Visible=''#39''{OBSOLETE_RECORD_FLAG<'' +
        ''>''#39#39''Y''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=SFOR_SfplPlanSubjectSel,Actio'' +
        ''n=UDV,UDVType=Update,UDVID=SFOR_55''
      
        ''Desc=Un-Obsolete Task Group,Priv={},Visible=''#39''{OBSOLETE_RECORD_FL'' +
        ''AG=''#39#39''Y''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=SFOR_SfplPlanSubjectSel,Act'' +
        ''ion=UDV,UDVType=Update,UDVID=SFOR_56''
      
        ''Desc=Task Group HOLD History Report,Priv={},Visible={},TagValue='' +
        '',FKey=,ParamsSrc=@Default,Action=Invoke,Tool=TabReport,ReportToo'' +
        ''lId=SFOR_Subject_Hold_History,Params(''#39''PLAN_ID=:PLAN_ID,''#39'',''#39''PLAN_U'' +
        ''PDT_NO=:PLAN_UPDT_NO,''#39'',SUBJECT_NO=:SUBJECT_NO)'')
    DataDefinitions.Strings = (
      ''SUBJECT_NO''
      ''SUBJECT_REV''
      ''SUBJECT_STATUS''
      ''IS_DEFAULT''
      ''STATUS''
      ''TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object SFOR_SfplPlanSubjectOperSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SfplPlanSubjectOperSel''
    DeleteSqlId = ''SFOR_SfplPlanSubjectOperDel''
    LinkedControls.Strings = (
      ''_OBJ3~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''SFOR_SfplPlanSubjectSel''
    RefreshedSQLSourceName = ''SFOR_SfplPlanSubjectSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''OPER_NO~6~Oper No~N~N~N~N~N~N~~~~~Default~N~''
      ''OPER_UPDT_NO~4~Oper Rev~N~N~N~N~N~N~~~~~Default~N~''
      ''OPER_TITLE~31~Oper Title~N~N~N~N~N~N~~~~~Default~N~''
      ''UPDT_USERID~12~Update User ID~N~N~N~N~N~N~~~~~Default~N~''
      ''TIME_STAMP~23~Update Time~N~N~N~N~N~N~~~~~Default~N~'')
    InsertUDVId = ''SFOR_23''
    DataDefinitions.Strings = (
      ''OPER_NO''
      ''OPER_UPDT_NO''
      ''OPER_TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object SFOR_SfplPlanSubjectPartSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SfplPlanSubjectPartSel''
    DeleteSqlId = ''SFOR_SfplPlanSubjectPartDel''
    LinkedControls.Strings = (
      ''_OBJ6~''
      ''_OBJ5~'')
    ParamsSQLSourceName = ''SFOR_SfplPlanSubjectSel''
    RefreshedSQLSourceName = ''SFOR_SfplPlanSubjectSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''PART_NO~12~Task Group;Item No~N~N~N~N~N~N~~~~~Default~N~~''
      
        ''PART_REV~4~Task Group;Item Rev~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      ''PART_TITLE~26~Title ~N~N~N~N~N~N~~~~~Default~N~~''
      ''UPDT_USERID~8~Update User ID~N~N~N~N~True~N~~~~~Default~N~~''
      ''TIME_STAMP~11~Update Time~N~N~False~N~True~N~~~~~Default~Y~~'')
    SelectedFieldsEditControl.Strings = (
      ''PART_TITLE~Edit''
      ''TIME_STAMP~Edit''
      ''PART_REV~Edit''
      ''part_no~Edit'')
    TestParamValues.Strings = (
      ''SCOPE_PLAN_ID=1''
      ''PLAN_UPDT_NO=1''
      ''SUBJECT_NO=1'')
    InsertUDVId = ''MFI_78BFAB552E175BB1E04400144FA7B7D2''
    DataDefinitions.Strings = (
      ''PART_NO''
      ''PART_REV''
      ''PART_TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object MFI_PLG_SFOR_TASK_GROUPS_REV: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_SFOR_TASK_GROUPS_REV''
    LinkedControls.Strings = (
      ''_OBJ14~''
      ''_OBJ13~'')
    ParamsSQLSourceName = ''SFOR_SfplPlanSubjectSel''
    SelectedFields.Strings = (
      
        ''SUBJECT_NO~5~Task Group;No~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~''
      
        ''SUBJECT_REV~5~Task Group;Rev~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      ''PLAN_REVISION~4~Plan;Rev~N~N~False~False~False~N~~~~~Default~N~~''
      ''TITLE~10~Title; ~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''SUBJECT_STATUS~8~Task Group;Status~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~''
      
        ''IMAGE~1~ ~N~N~False~False~False~N~~@StdMarkup(IMAGE)~@Command(CO'' +
        ''MMAND)~~Default~N~~''
      
        ''UPDT_USERID~8~Update;UserID~N~N~False~False~True~N~~~~~Default~N'' +
        ''~~''
      ''TIME_STAMP~12~Update;Time~N~N~False~False~True~N~~~~~Default~Y~~'')
    SelectedFieldsEditControl.Strings = (
      ''TITLE~Edit''
      ''PLAN_REVISION~Edit''
      ''IMAGE~Edit''
      ''TIME_STAMP~Edit'')
    OtherCommands.Strings = (
      
        ''Desc=Add Attachment,Priv={},Visible={},TagValue=,FKey=,ParamsSrc'' +
        ''=@Default,Action=UDV,UDVType=Insert,UDVID=MFI_76A4FE6BF0230211E0'' +
        ''4400144FA7B7D2''
      
        ''Desc=Display,Priv={},Visible={IMAGE=''#39''Image''#39''},TagValue=,FKey=,''#39''Pa'' +
        ''ramsSrc=@Default,MFI_PLG_SFOR_TASK_GROUPS_REV''#39'',Action=SideNote,S'' +
        ''qlID=,Params(''#39''@SubConfig=:SUB_CONFIG,''#39'',''#39''OBJECT_ID=:OBJECT_ID,''#39'',''#39 +
        ''SLIDE_REF_ID=,''#39'',''#39''TESTPARAM1=TASK_GROUP+:PLAN_ID+:PLAN_VERSION+:P'' +
        ''LAN_REVISION+:PLAN_ALTERATIONS+:SUBJECT_NO,''#39'',@BlockCaption=Slide'' +
        '')''
      
        ''Desc=Dummy,Priv={},Visible={1<>1},TagValue=,FKey=,ParamsSrc=@Def'' +
        ''ault,Action=ExecSql,ParamsSqlSourceName=@ToolScope,RefreshedSQLS'' +
        ''ourceName=,SqlID=dummy'')
    DataDefinitions.Strings = (
      ''SUBJECT_NO''
      ''SUBJECT_REV''
      ''PLAN_REVISION''
      ''TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''SUBJECT_STATUS''
      ''IMAGE'')
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

