
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_52A97F49227B4F0590615D4C75930075.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWMROI_3701A7AFD2A14650970AA2A632A73570';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWMROI_52A97F49227B4F0590615D4C75930075';
v_udv_tag sfcore_udv_lib.udv_tag%type :='OrderOperBuyoffHistEmbUWH';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Buyoff History embedded in UWH report';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.0.1.20180226~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 980
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
  AlignContents = acParentWidth
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
    976
    393)
  object _OBJ11: TsfDBGrid
    Left = 4
    Top = 6
    Width = 956
    Height = 120
    Anchors = [akLeft, akTop, akRight]
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Options = [dgEditing, dgTitles, dgIndicator, dgColumnResize, dgColumnMove, dgColLines, dgRowLines, dgTabs, dgConfirmDelete, dgCancelOnExit, dgRowMove]
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
  object UnitWorkHistBuyoffSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWMROI_UnitWorkHistBuyoffSel''
    LinkedControls.Strings = (
      ''_OBJ11~'')
    ParamsSQLSourceName = ''@ToolScope,@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''BUYOFF_TYPE~12~Buyoff Type;~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''
      
        ''BUYOFF_TITLE~13~Buyoff Title; ~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~~''
      ''STATUS~6~Status; ~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''BUYOFF_CERT~10~Req''#39''d Cert~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''PERCENT_OR_QTY_COMPLETE~8~Complete~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~~''
      ''USER_NAME~9~User Name; ~N~N~False~False~False~N~~~~~Default~N~~~''
      ''COMMENTS~24~Comments; ~N~N~False~False~False~N~~~~~Default~Y~~~'')
    SelectedFieldsEditControl.Strings = (
      ''OPTIONAL_FLAG~Edit''
      ''IMAGE~Edit''
      ''STATUS~Edit''
      ''PERCENT_OR_QTY_COMPLETE~Edit''
      ''BUYOFF_CERT~Edit''
      ''COMMENTS~Edit'')
    TestParamValues.Strings = (
      ''@USERID=UTPAL''
      ''ORDER_ID=MFI_4A8DA102260DA90572672DF421248F7F''
      ''OPER_KEY=26199''
      ''STEP_KEY=-1''
      ''REF_ID=MFI_61E7F7C895DF44C9A2D41A1DB1E8DBD8''
      ''R_SERIAL_ID=1''
      ''R_LOT_ID=1'')
    DataDefinitions.Strings = (
      ''BUYOFF_TYPE''
      ''BUYOFF_TITLE''
      ''STATUS''
      ''BUYOFF_CERT''
      ''PERCENT_OR_QTY_COMPLETE''
      ''USER_NAME''
      ''COMMENTS'')
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

