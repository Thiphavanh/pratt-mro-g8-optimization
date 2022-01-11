
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_8CB72D91E66687AFE05387971F0ABC6C.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_8CB72D91E66687AFE05387971F0ABC6C';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_ReleasePackageProcessPlanTab2';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 319.';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 755
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
    Left = 5
    Top = 4
    Width = 153
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Delete ''
      ''InsertUpdateDelete ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ2: TsfDBGrid
    Left = 5
    Top = 27
    Width = 745
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
    Highlighting.Strings = (
      ''@Row~Gray~Default~OBSOLETE_FLAG = ''#39''Y'')
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
      
        #39''Desc=Move Plan(s)''#39'',Priv={},Visible={},TagValue=,FKey=,ParamsSrc'' +
        ''=@Default,Action=UDV,UDVType=InputMatrix,UDVID=MFI_381FB4579B557'' +
        ''0F8E0440003BA041A64'')
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
    ParentFont = False
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
      
        ''Desc=Create Revision,Priv=''#39''{REV_STATUS <> ''#39#39''OBSOLETE''#39#39''}''#39'',Visible'' +
        ''={},TagValue=,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlS'' +
        ''ourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_PLG_SHOWP'' +
        ''LANREVUDV'')
  end
  object _OBJ5: TsfCommandButton
    Left = 649
    Top = 4
    Width = 96
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
        ''=,ParamsSrc=@Default,Action=UDV,UDVType=InputMatrix,UDVID=MFI_C7'' +
        ''C836C56349486B90D17951B66CB4B0'')
  end
  object _OBJ6: TsfCommandButton
    Left = 548
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
  object PwpPlansSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_ReleasePackagePlanSel2''
    DeleteSqlId = ''MFI_PLG_DISPATCHPLANDEL''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''MOVE_PLANS~''
      ''DIS_PLAN~''
      ''_OBJ2~''
      ''_OBJ3~''
      ''_OBJ4~''
      ''_OBJ6~'')
    ParamsSQLSourceName = ''@ToolScope, @CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      ''PLAN_NO~12~Plan No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''PLAN_VERSION~4~Plan Ver~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PLAN_REVISION~4~Plan Rev~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PLAN_TYPE~8~Plan Type~N~N~False~False~False~N~~~~~Default~N~~~''
      ''ITEM_NO~12~Item No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''ITEM_REV~4~Item Rev~N~N~False~False~False~N~~~~~Default~N~~~''
      ''ITEM_TYPE~5~Item Type~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''ITEM_SUBTYPE~12~Item Subtype~N~N~False~False~True~N~~~~~Default~'' +
        ''N~~~''
      ''PROGRAM~6~Program~N~N~N~N~True~N~~~~~Default~N~~~''
      
        ''REV_STATUS~10~Plan Status~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''OBSOLETE_FLAG~2~Obsolete?~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''PLAN_TITLE~12~Plan Title ~N~N~False~False~True~N~~~~~Default~Y~~'' +
        ''~''
      
        ''PWP_ID~10~From Release Package~N~N~True~False~True~N~~~~~Default'' +
        ''~N~~~''
      ''PWP_STATUS~8~Status~N~N~True~False~True~N~~~~~Default~N~~~''
      
        ''TO_PWP_ID~10~To Release Package~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      
        ''PLAN_ALTERATIONS~0~PLAN_ALTERATIONS~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~~~''
      ''PLAN_ID~0~PLAN_ID~N~N~True~False~True~N~~~~~Default~N~~~''
      
        ''CHANGE_TYPE~8~Change Type~N~N~False~False~True~N~~~~~Default~N~~'' +
        ''~''
      ''CHANGE_NO~8~Change No~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''UPDT_USERID~10~Update User ID~N~N~N~N~True~N~MFI_2869~~~~Default'' +
        ''~N~~~''
      ''TIME_STAMP~18~Update Time~N~N~N~N~True~N~~~~~Default~N~~~''
      ''MRO_FLAG~0~MRO_FLAG~N~N~True~False~True~N~~~~~False~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''CHANGE_DOC_ID~Edit''
      ''STATUS~Edit''
      ''PLAN_NUMBER~Edit''
      ''ITEM_NO~Edit''
      ''ITEM_TYPE~Edit''
      ''PROGRAM~Edit''
      ''PLAN_REVISION~Edit''
      ''REV_STATUS~Edit''
      ''PLAN_TITLE~Edit''
      ''TO_PWP_ID~Edit''
      ''PLAN_NO~Edit''
      ''OBSOLETE_FLAG~Edit'')
    TestParamValues.Strings = (
      ''PWP_ID=SUNG'')
    InsertUDVId = ''MFI_1003829''
    InsUpdDelUDVId = ''MFI_7A4A9B30C2624E5A8368762C751674BC''
    InsUpdDelEnabledExpr = ''@HasPriv(''#39''PLAN_CREATE''#39'') AND PLAN_ID <> ''#39#39
    OtherCommands.Strings = (
      
        ''Desc=Display Work Plan Revision,Priv=,Visible=,TagValue=,FKey=,A'' +
        ''ction=Invoke,Tool=PrPlg Instructions,ReportToolId=,Params(''#39''PLAN_'' +
        ''ID = :PLAN_ID,''#39'',''#39''PLAN_VERSION = :PLAN_VERSION,''#39'',''#39''PLAN_REVISION ='' +
        '' :PLAN_REVISION,''#39'',''#39''PLAN_ALTERATIONS = :PLAN_ALTERATIONS,''#39'',@Invok'' +
        ''eToolMode=EDITMODES.Edit_PL)'')
    DataDefinitions.Strings = (
      ''PLAN_NO''
      ''PLAN_VERSION''
      ''PLAN_REVISION''
      ''PLAN_TYPE''
      ''ITEM_NO''
      ''ITEM_REV''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''PROGRAM''
      ''REV_STATUS''
      ''OBSOLETE_FLAG''
      ''PLAN_TITLE''
      ''PWP_ID''
      ''PWP_STATUS''
      ''TO_PWP_ID''
      ''PLAN_ALTERATIONS''
      ''PLAN_ID''
      ''CHANGE_TYPE''
      ''CHANGE_NO''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''MRO_FLAG'')
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

