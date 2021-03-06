
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_56735C6668555830E05387971F0AC1D4.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_56735C6668555830E05387971F0AC1D4';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_ReleasePackageProcessPlanTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='org udv id = MFI_E0A7ED96D0274DD5B303CF3FC448EBC2,SMRO_PLG_301;Defect1589;Defect1625';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 846
  Height = 235
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
    Top = 4
    Width = 134
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Delete ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ2: TsfDBGrid
    Left = 4
    Top = 27
    Width = 830
    Height = 200
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
  object MOVE_PLANS: TsfCommandButton
    Left = 363
    Top = 4
    Width = 87
    Height = 21
    Hint = ''Move Plan(s)''
    Caption = ''Move Plan(s)''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        #39''Desc=Move Plan(s)''#39'',Priv=,Visible=,TagValue=,Action=UDV,UDVType='' +
        ''InputMatrix,UDVID=MFI_381FB4579B5570F8E0440003BA041A64'')
  end
  object DIS_PLAN: TsfCommandButton
    Left = 455
    Top = 4
    Width = 87
    Height = 21
    Hint = ''Display Plan''
    Caption = ''Display Plan''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Display Plan,Priv=,Visible=,TagValue=,FKey=,Action=Invoke,T'' +
        ''ool=PrPlg Instructions,ReportToolId=,Params(''#39''PLAN_ID = :PLAN_ID,'' +
        #39'',''#39''PLAN_VERSION = :PLAN_VERSION,''#39'',''#39''PLAN_REVISION = :PLAN_REVISIO'' +
        ''N,''#39'',''#39''PLAN_ALTERATIONS = :PLAN_ALTERATIONS,''#39'',@InvokeToolMode=EDIT'' +
        ''MODES.Edit_PL)'')
  end
  object _OBJ3: TsfCommandButton
    Left = 143
    Top = 4
    Width = 110
    Height = 21
    Hint = ''Create Process Plan''
    Caption = ''Create Process Plan''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Create Process Plan,Priv=''#39''{PWP_ID<>''#39#39#39#39''}''#39'',Visible={},TagVal'' +
        ''ue=PROCESS_PLAN_CREATE,FKey=,ParamsSrc=@Default,Action=ExecSql,P'' +
        ''aramsSqlSourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_'' +
        ''SQA_SHOWPLANSUDV'')
  end
  object _OBJ4: TsfCommandButton
    Left = 258
    Top = 4
    Width = 100
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
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Create Revision,Priv={},Visible={},TagValue=,FKey=,ParamsSr'' +
        ''c=@Default,Action=ExecSql,ParamsSqlSourceName=@Toolscope,Refresh'' +
        ''edSQLSourceName=,SqlID=MFI_PLG_SHOWPLANREVUDV'')
  end
  object _OBJ5: TsfCommandButton
    Left = 547
    Top = 4
    Width = 88
    Height = 21
    Hint = ''Import Plan''
    Caption = ''Import Plan''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Import Plan,Priv=''#39''{FILE <> ''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey'' +
        ''=,Action=UDV,UDVType=InputMatrix,UDVID=MFI_C7C836C56349486B90D17'' +
        ''951B66CB4B0'')
  end
  object _OBJ6: TsfCommandButton
    Left = 731
    Top = 4
    Width = 96
    Height = 21
    Hint = ''Display Change''
    Caption = ''Display Change''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (
      
        ''Desc=Display Change,Priv=''#39''{CHANGE_NO <> ''#39#39#39#39''}''#39'',Visible={},TagVal'' +
        ''ue=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=ChangeRequestIns'' +
        ''tructions,ReportToolId=,Params(''#39''CHANGE_NO=:CHANGE_NO,@InvokeTool'' +
        ''Mode=EDITMODES.Edit_PL''#39'')'')
  end
  object _OBJ7: TsfCommandButton
    Left = 639
    Top = 4
    Width = 88
    Height = 21
    Caption = ''Migrate Plans''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 8
    OtherCommands.Strings = (
      
        ''Desc=Exec,''#39''Priv={@HasPriv(''#39#39''PLAN_MIGRATE''#39#39'')}''#39'',Visible={},TagValu'' +
        ''e=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=InputMatrix,UDVID'' +
        ''=PWUST_5667B5C888A0CF9BE05387971F0A3BFC'')
  end
  object PwpPlansSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_MFI_PLG_ReleasePackagePlanSel''
    DeleteSqlId = ''MFI_PLG_DISPATCHPLANDEL''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''MOVE_PLANS~''
      ''DIS_PLAN~''
      ''_OBJ2~''
      ''_OBJ3~''
      ''_OBJ4~''
      ''_OBJ6~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''PLAN_NO~12~Plan No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PLAN_TYPE~8~Plan Type~N~N~False~False~True~N~~~~~Default~N~~~''
      ''ITEM_NO~12~Item No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PROGRAM~6~Engine Type~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_VCH3~10~Module Code~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''REV_STATUS~10~Plan Status~N~N~False~False~True~N~~~~~Default~N~~'' +
        ''~''
      
        ''PLAN_TITLE~12~Plan Title ~N~N~False~False~False~N~~~~~Default~Y~'' +
        ''~~''
      ''PLAN_VERSION~4~Plan Ver~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PLAN_REVISION~4~Plan Rev~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''CHANGE_TYPE~8~Change Type~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''UPDT_USERID~10~Update User ID~N~N~N~N~True~N~MFI_2869~~~~Default'' +
        ''~N~~~''
      ''TIME_STAMP~18~Update Time~N~N~N~N~True~N~~~~~Default~N~~~''
      ''MFG_BOM_CHG~10~MBOM Rev~N~N~False~False~False~N~~~~~Default~N~~~''
      ''BOM_NO~10~MBOM No~N~N~False~False~False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''CHANGE_DOC_ID~Edit''
      ''STATUS~Edit''
      ''PLAN_NUMBER~Edit''
      ''PART_CHG~Edit''
      ''ITEM_NO~Edit''
      ''CHANGE_TYPE~Edit''
      ''UPDT_USERID~Edit''
      ''MFG_BOM_CHG~Edit'')
    TestParamValues.Strings = (
      ''PWP_ID=SUNG'')
    InsertUDVId = ''MFI_1003829''
    OtherCommands.Strings = (
      
        ''Desc=Display Work Plan Revision,Priv={},Visible={},TagValue=,FKe'' +
        ''y=,ParamsSrc=@Default,Action=Invoke,Tool=PrPlg Instructions,Repo'' +
        ''rtToolId=,Params(''#39''PLAN_ID = :PLAN_ID,''#39'',''#39''PLAN_VERSION = :PLAN_VER'' +
        ''SION,''#39'',''#39''PLAN_REVISION = :PLAN_REVISION,''#39'',''#39''PLAN_ALTERATIONS = :PL'' +
        ''AN_ALTERATIONS,''#39'',@InvokeToolMode=EDITMODES.Edit_PL)'')
    DataDefinitions.Strings = (
      ''PLAN_NO''
      ''PLAN_TYPE''
      ''ITEM_NO''
      ''PROGRAM''
      ''UCF_PLAN_VCH3''
      ''REV_STATUS''
      ''PLAN_TITLE''
      ''PLAN_VERSION''
      ''PLAN_REVISION''
      ''CHANGE_TYPE''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''MFG_BOM_CHG''
      ''BOM_NO'')
    ConsolidatedQuery = False
  end
  object FileList: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_ImportPlanList''
    LinkedControls.Strings = (
      ''_OBJ5~'')
    PublishParams = True
    SelectedFields.Strings = (
      ''FILE~0~FILE~N~N~False~False~False~N~~~~~Default~N~''
      ''SIZE~0~SIZE~N~N~False~False~False~N~~~~~Default~N~''
      ''MODIFIED~0~MODIFIED~N~N~False~False~False~N~~~~~Default~N~''
      ''SELECTED~0~SELECTED~N~N~False~False~False~N~~~~~Default~N~'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''FileList''
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

