
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_825749D4EBBD6CDBE05387971F0A8797.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_825749D4EBBD6CDBE05387971F0A8797';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperDatColAlt';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Clone of MFI_1002729 - SMRO_WOE_306';
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
  Height = 719
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
    715)
  object _OBJ1: TsfDBGrid
    Left = 4
    Top = 26
    Width = 956
    Height = 86
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
  object _OBJ2: TsfUDVNavigator
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
    TabOrder = 1
    TabStop = True
  end
  object OrderOperDcEmbed: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_OrderOperDCSel''
    DeleteSqlId = ''OrderOperDatColDel''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''@DragAndDrop,@ToolScope,@CurrentMarker,DropRefIdAsRefId''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''CALC_DC_FLAG~1~ ~N~N~False~False~True~N~~@StdMarkup(CALC_DC_FLAG'' +
        '')~@Command(CALCULATED_DC)~~Default~N~~~''
      
        ''IMAGE~1~ ~N~N~False~False~True~N~~@StdMarkup(IMAGE)~@Command(Dis'' +
        ''play Slide)~~Default~N~~~''
      
        ''UCF_OPER_DC_FLAG1~5~ROM;Incl?~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''DAT_COL_TYPE~12~Data Collection;Type~N~N~N~N~N~N~~~~~Default~N~~'' +
        ''~''
      
        ''DAT_COL_TITLE_DISP~28~Data Collection Title~N~N~False~False~True'' +
        ''~N~~~~~Default~Y~~~''
      
        ''DAT_COL_TITLE~15~Data Collection Title~N~N~True~N~N~N~~~~~Defaul'' +
        ''t~N~~~''
      ''DAT_COL_UOM~3~UOM~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''UCF_OPER_DC_VCH255_1~10~Engineer Remarks~N~N~False~False~False~N'' +
        ''~~~~~Default~N~~~''
      ''LOWER_LIMIT~3~LSL; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''TARGET_VALUE~5~Target;Value~N~N~N~N~N~N~~~~~Default~N~~~''
      ''UPPER_LIMIT~3~USL; ~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''NUM_DECIMAL_DIGITS~5~Decimal;Digits~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~~~''
      
        ''VARIABLE_NAME~13~Variable Name; ~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      ''DAT_COL_CERT~10~Req''#39''d Cert; ~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''FILE_DISP~10~Template File; ~N~N~True~False~False~N~~~~~Default~'' +
        ''N~~~''
      ''FILE1~10~New File; ~N~N~True~False~False~N~~~~~Default~N~~~''
      ''OPTIONAL_FLAG~3~Opt?; ~N~N~False~N~False~N~~~~~Default~N~~~''
      
        ''ORIENTATION_FLAG~7~Orientation~N~N~True~N~True~N~~~~~Default~N~~'' +
        ''~''
      
        ''CROSS_ORDER_FLAG~4~Cross;Order?~N~N~True~N~True~N~~~~~Default~N~'' +
        ''~~''
      ''CALC_FLAG~1~Calculated?~N~N~True~False~False~N~~~~~Default~N~~~''
      ''VISIBILITY~5~Visibility~N~N~True~False~True~N~~~~~Default~N~~~''
      
        ''DISPLAY_LINE_NO~5~Display;Line No~N~N~Y~N~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''AUDIT_FLAG~9~Over Inspection;Req''#39''d?~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''VALID_RESULT_TYPE~15~Validation;Result Type~N~N~True~False~False'' +
        ''~N~~~~~Default~N~~~''
      
        ''UCF_OPER_DC_FLAG2~5~Serviceable;Limit;Used~N~N~False~False~False'' +
        ''~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_OPER_DC_FLAG1~CheckBox=1~0''
      ''VALID_RESULT_TYPE~Edit''
      ''AUDIT_FLAG~Edit''
      ''CALC_FLAG~Edit''
      ''NUM_DECIMAL_DIGITS~Edit''
      ''TARGET_VALUE~Edit''
      ''CROSS_ORDER_FLAG~Edit''
      ''UCF_OPER_DC_FLAG2~CheckBox=1~0'')
    TestParamValues.Strings = (
      ''order_control_type=NONE''
      ''order_id=6A87D7CA2E327D39E203750EAD216931''
      ''oper_key=409362''
      ''step_key=-1''
      ''ref_id=PWUST_7F326E0D8BDB9AE0E05387971F0A7957'')
    InputUDVId = ''MFI_1003689''
    InsertUDVId = ''MFI_1003689''
    InsUpdDelUDVId = ''MFI_1003689''
    OtherCommands.Strings = (
      
        ''Desc=CALCULATED_DC,Priv={},Visible=''#39''{CALC_DC_FLAG=''#39#39''CALCULATED_D'' +
        ''C''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=SideNote,SqlID=M'' +
        ''FI_WID_CDCSideNote,Params(''#39''ORDER_ID=:ORDER_ID,''#39'',''#39''OPER_KEY=:OPER_'' +
        ''KEY,''#39'',''#39''STEP_KEY=:STEP_KEY,''#39'',''#39''STEP_UPDT_NO=:STEP_UPDT_NO,''#39'',''#39''DAT_C'' +
        ''OL_ID=:DAT_COL_ID,''#39'',''#39''DAT_COL_TYPE=:DAT_COL_TYPE,''#39'',''#39''DAT_COL_TITLE'' +
        ''=:DAT_COL_TITLE,''#39'',''#39''LOWER_LIMIT=:LOWER_LIMIT,''#39'',''#39''TARGET_VALUE=:TAR'' +
        ''GET_VALUE,''#39'',''#39''NUM_DECIMAL_DIGITS=:NUM_DECIMAL_DIGITS,''#39'',''#39''DAT_COL_C'' +
        ''ERT=:DAT_COL_CERT,''#39'',''#39''STATUS=:ALT_STATUS,''#39'',''#39''DAT_COL_UOM = :DAT_CO'' +
        ''L_UOM,''#39'',STD_DATCOL_ID = :STD_DATCOL_ID)''
      
        ''Desc=Display Slide,Priv={},Visible=''#39''{SLIDE_ID<>''#39#39#39#39''}''#39'',TagValue=,'' +
        ''FKey=,ParamsSrc=@Default,Action=SideNote,SqlID=,Params(''#39''@SubConf'' +
        ''ig=Slide,''#39'',''#39''OBJECT_ID=:SLIDE_ID,''#39'',''#39''SLIDE_REF_ID=:SLIDE_EMBEDDED_'' +
        ''REF_ID,''#39'',''#39''ORDER_ID = :ORDER_ID,''#39'',''#39''OPER_KEY = :OPER_KEY,''#39'',STEP_KE'' +
        ''Y = :STEP_KEY)'')
    DropCommands.Strings = (
      
        ''Data Collection~MFI_C0DCDEC4EE924BD684FF0EF75400F3C6~OrderOperDc'' +
        ''Embed~InputMatrix~~Insert''
      
        ''@InternalDrop~MFI_5315C6D9AE2143168A2F46B84C1B7084~OrderOperDcEm'' +
        ''bed~Insert~~Insert''
      
        ''@ExternalDrop~~OrderOperDcEmbed,OrderResourceDatcolSel~ExecDragS'' +
        ''ourceCmd~~Insert'')
    DataDefinitions.Strings = (
      ''CALC_DC_FLAG''
      ''IMAGE''
      ''UCF_OPER_DC_FLAG1''
      ''DAT_COL_TYPE''
      ''DAT_COL_TITLE_DISP''
      ''DAT_COL_TITLE''
      ''DAT_COL_UOM''
      ''LOWER_LIMIT''
      ''TARGET_VALUE''
      ''UPPER_LIMIT''
      ''NUM_DECIMAL_DIGITS''
      ''VARIABLE_NAME''
      ''DAT_COL_CERT''
      ''FILE_DISP''
      ''FILE1''
      ''OPTIONAL_FLAG''
      ''ORIENTATION_FLAG''
      ''CROSS_ORDER_FLAG''
      ''CALC_FLAG''
      ''VISIBILITY''
      ''DISPLAY_LINE_NO''
      ''AUDIT_FLAG''
      ''VALID_RESULT_TYPE'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''OrderOperDcEmbed''
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

