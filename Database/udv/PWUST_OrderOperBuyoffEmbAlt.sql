
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_7CF005CF81E1A50DE05387971F0A84D6.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_7CF005CF81E1A50DE05387971F0A84D6';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperBuyoffEmbAlt';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202 Defect 731, defect 1226.';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.2.0.20180714~2.4';
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
  object _OBJ10: TsfUDVNavigator
    Left = 4
    Top = 3
    Width = 96
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''InsertUpdateDelete ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ11: TsfDBGrid
    Left = 4
    Top = 26
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
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object WIDOperBuy_ALT: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderBuyoffSel''
    DeleteSqlId = ''OrderOperBuyoffDel''
    LinkedControls.Strings = (
      ''_OBJ10~''
      ''_OBJ11~'')
    ParamsSQLSourceName = ''@DragAndDrop,@ToolScope, @CurrentMarker ,DropRefIdAsRefId''
    PublishParams = True
    SelectedFields.Strings = (

        ''IMAGE~1~ ~N~N~False~False~True~N~~@StdMarkup(IMAGE)~@Command(''#39''Di'' +
        ''splay Slide''#39'')~~Default~N~~~''

        ''BUYOFF_TYPE~12~Buyoff Type; ~N~N~ALTERATION_LEVEL=''#39#39''~False~ALTER'' +
        ''ATION_LEVEL=''#39#39''~N~~~~~Default~N~~~''

        ''BUYOFF_TITLE~28~Buyoff Title; ~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~Y~~~''

        ''BUYOFF_CERT~10~Req''#39''d Cert; ~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''

        ''CROSS_ORDER_FLAG~5~Cross;Order?~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~~~''
      ''OPTIONAL_FLAG~3~Opt?; ~N~N~False~False~False~N~~~~~Default~N~~~''

        ''DISPLAY_LINE_NO~5~Display Line No~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''OPTIONAL_FLAG~Edit''
      ''IMAGE~Edit''
      ''DISPLAY_LINE_NO~Edit'')
    TestParamValues.Strings = (
      ''@USERID=UTPAL''
      ''ORDER_ID=MFI_4A8DA102260DA90572672DF421248F7F''
      ''OPER_KEY=26199''
      ''STEP_KEY=-1''
      ''REF_ID=MFI_61E7F7C895DF44C9A2D41A1DB1E8DBD8'')
    InputUDVId = ''MFI_1003629''
    InsertUDVId = ''MFI_1003629''
    InsUpdDelUDVId = ''MFI_1003629''
    OtherCommands.Strings = (

        ''Desc=Display Slide,Priv={},Visible=''#39''{SLIDE_ID<>''#39#39#39#39''}''#39'',TagValue=,'' +
        ''FKey=,ParamsSrc=@Default,Action=SideNote,SqlID=,Params(''#39''@SubConf'' +
        ''ig=Slide,''#39'',''#39''OBJECT_ID=:SLIDE_ID,''#39'',''#39''SLIDE_REF_ID=:SLIDE_EMBEDDED_'' +
        ''REF_ID,''#39'',''#39''ORDER_ID = :ORDER_ID,''#39'',''#39''OPER_KEY = :OPER_KEY,''#39'',STEP_KE'' +
        ''Y = :STEP_KEY)'')
    DropCommands.Strings = (

        ''Buyoff~MFI_F92C2209AEE24BDE9022EE7985802039~WIDOperBuy_ALT~Input'' +
        ''Matrix~~Default''

        ''@InternalDrop~MFI_1B719BB9E1444223A7429EC5775ABE2F~WIDOperBuy_AL'' +
        ''T~Insert~~Default''
      ''@ExternalDrop~~WIDOperBuy_ALT~ExecDragSourceCmd~~Default'')
    DataDefinitions.Strings = (
      ''IMAGE''
      ''BUYOFF_TYPE''
      ''BUYOFF_TITLE''
      ''BUYOFF_CERT''
      ''CROSS_ORDER_FLAG''
      ''OPTIONAL_FLAG''
      ''DISPLAY_LINE_NO'')
    ConsolidatedQuery = False
  end
  object DropRefIdAsRefId: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_SEL_DROP_REF_ID_AS_REF_ID''
    ParamsSQLSourceName = ''@DragAndDrop,@CurrentMarker''
    PublishParams = True
    DefaultParamValues.Strings = (
      ''DROP_REF_ID='')
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

