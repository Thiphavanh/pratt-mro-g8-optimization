
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_87EC61E828D299DEE05387971F0A1B24.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_87EC61E828D299DEE05387971F0A1B24';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanStepDatCol';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_PLG_313 - Clone of PlanStepDatCol (MF1_1000058)';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.2.0.20180714~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
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
  object Nav: TsfUDVNavigator
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
  object _OBJ7: TsfDBGrid
    Left = 4
    Top = 26
    Width = 968
    Height = 80
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
  object EmbedDatColSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''EmbedDatColSelect''
    DeleteSqlId = ''PlanStepDatColDel''
    LinkedControls.Strings = (
      ''_OBJ7~''
      ''Nav~'')
    ParamsSQLSourceName = ''@DragAndDrop,@CurrentMarker,DropRefIdAsRefId''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''IMAGE~1~ ~N~N~False~False~True~N~~@StdMarkup(IMAGE)~@Command(Dis'' +
        ''play Slide)~~Default~N~~~''
      
        ''CALC_DC_FLAG~1~ ~N~N~False~False~True~N~~@StdMarkUp(CALC_DC_FLAG'' +
        '')~@Command(CALCULATED_DC)~~Default~N~~~''
      
        ''UCF_STEP_DC_FLAG1~5~ROM;Incl?~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      ''DAT_COL_ID~0~DAT_COL_ID~N~N~True~False~True~N~~~~~Default~N~~~''
      
        ''DAT_COL_TYPE~12~DC Type; ~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''DAT_COL_TITLE_DISP~28~DC Title; ~N~N~False~False~True~N~~~~~Defa'' +
        ''ult~Y~~~''
      
        ''DAT_COL_TITLE~15~DC Title; ~N~N~True~False~False~N~~~~~Default~N'' +
        ''~~~''
      ''DAT_COL_UOM~3~UOM; ~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''UCF_STEP_DC_VCH255_1~10~Engineer Remarks~N~N~False~False~False~N'' +
        ''~~~~~Default~N~~~''
      ''LOWER_LIMIT~3~LSL; ~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''TARGET_VALUE~5~Target;Value~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~''
      ''UPPER_LIMIT~3~USL; ~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''NUM_DECIMAL_DIGITS~2~Decimal;Digits~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~~~''
      
        ''DAT_COL_CERT~10~Req''#39''d Cert; ~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''FILE_DISP~10~Template File~N~N~True~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''FILE1~10~New File~N~N~True~False~False~N~~~~~Default~N~~~''
      ''OPTIONAL_FLAG~3~Opt?; ~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''orientation_flag~10~Orientation~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''CROSS_ORDER_FLAG~1~Cross;Order?~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~~~''
      ''CALC_FLAG~1~Calculated?~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''VARIABLE_NAME~10~Variable Name~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UCF_STEP_DC_FLAG2~5~Serviceable;Limit;Used~N~N~False~False~False'' +
        ''~N~~~~~Default~N~~~''
      
        ''DISPLAY_LINE_NO~0~Display Line No; ~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''VISIBILITY~5~Variable;Visibility~N~N~True~False~True~N~~~~~Defau'' +
        ''lt~N~~~''
      ''UPDT_USERID~0~UPDT_USERID~N~N~True~False~True~N~~~~~Default~N~~~''
      ''TIME_STAMP~0~TIME_STAMP~N~N~True~False~True~N~~~~~Default~N~~~''
      ''OPER_KEY~0~OPER_KEY~N~N~True~False~True~N~~~~~Default~N~~~''
      ''STEP_KEY~0~STEP_KEY~N~N~True~False~True~N~~~~~Default~N~~~''
      
        ''STEP_UPDT_NO~0~STEP_UPDT_NO~N~N~True~False~True~N~~~~~Default~N~'' +
        ''~~''
      ''LAST_ACTION~0~LAST_ACTION~N~N~True~False~True~N~~~~~Default~N~~~''
      
        ''DAT_COL_LIMIT_ID~0~DAT_COL_LIMIT_ID~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~~~''
      
        ''AUDIT_FLAG~9~Over Inspection;Req''#39''d?~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''STD_DATCOL_ID~0~STD_DATCOL_ID~N~N~True~False~True~N~~~~~Default~'' +
        ''N~~~''
      
        ''VALID_RESULT_TYPE~15~Validation;ResultType~N~N~True~False~False~'' +
        ''N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''orientation_flag~Edit''
      ''UPDT_USERID~Edit''
      ''NUM_DECIMAL_DIGITS~Edit''
      ''OPTIONAL_FLAG~Edit''
      ''CROSS_ORDER_FLAG~Edit''
      ''CALC_FLAG~Edit''
      ''VARIABLE_NAME~Edit''
      ''DAT_COL_TITLE~Edit''
      ''DAT_COL_UOM~Edit''
      ''DISPLAY_LINE_NO~Edit''
      ''AUDIT_FLAG~Edit''
      ''DAT_COL_LIMIT_ID~Edit''
      ''FILE1~Edit''
      ''IMAGE~Edit''
      ''UCF_STEP_DC_FLAG1~CheckBox=1~0''
      ''UCF_STEP_DC_FLAG2~CheckBox=1~0'')
    TestParamValues.Strings = (
      ''plan_id=PWUST_D81FD580A5A9DEDDABA54C2A5E13285F''
      ''plan_revision=1''
      ''plan_version=1''
      ''plan_alterations=0''
      ''oper_key=413364''
      ''step_key=''
      ''ref_id=PWUST_7D53D2C1B4ECD244E05387971F0A98EA''
      ''dat_col_uom=''
      ''std_datcol_id='')
    InputUDVId = ''MF1_1000061''
    InsertUDVId = ''MF1_1000061''
    InsUpdDelUDVId = ''MF1_1000061''
    OtherCommands.Strings = (
      
        ''Desc=CALCULATED_DC,Priv={},Visible=''#39''{CALC_DC_FLAG=''#39#39''CALCULATED_D'' +
        ''C''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=SideNote,SqlID=M'' +
        ''FI_PLG_CDCSideNote,Params(''#39''PLAN_ID=:PLAN_ID,''#39'',''#39''OPER_KEY=:OPER_KE'' +
        ''Y,''#39'',''#39''STEP_KEY=:STEP_KEY,''#39'',''#39''STEP_UPDT_NO=:STEP_UPDT_NO,''#39'',''#39''DAT_COL'' +
        ''_ID=:DAT_COL_ID,''#39'',''#39''DAT_COL_TYPE=:DAT_COL_TYPE,''#39'',''#39''DAT_COL_TITLE=:'' +
        ''DAT_COL_TITLE,''#39'',''#39''LOWER_LIMIT=:LOWER_LIMIT,''#39'',''#39''TARGET_VALUE=:TARGE'' +
        ''T_VALUE,''#39'',''#39''NUM_DECIMAL_DIGITS=:NUM_DECIMAL_DIGITS,''#39'',''#39''DAT_COL_CER'' +
        ''T=:DAT_COL_CERT,''#39'',''#39''BLOCK_ID=:BLOCK_ID,''#39'',''#39''DAT_COL_UOM = :DAT_COL_'' +
        ''UOM,''#39'',''#39''STD_DATCOL_ID = :STD_DATCOL_ID,''#39'',''#39''SCOPE_PLAN_ID:SCOPE_PLA'' +
        ''N_ID,''#39'',''#39''SCOPE_PLAN_VERSION=:SCOPE_PLAN_VERSION,''#39'',''#39''SCOPE_PLAN_REV'' +
        ''ISION=:SCOPE_PLAN_REVISION,''#39'',SCOPE_PLAN_ALTERATIONS=:SCOPE_PLAN_'' +
        ''ALTERATIONS)''
      
        ''Desc=Display Slide,Priv=,Visible=''#39''SLIDE_ID<>''#39#39#39#39#39'',TagValue=,FKey'' +
        ''=,Action=SideNote,SqlID=,Params(''#39''@SubConfig=Slide,''#39'',''#39''OBJECT_ID=:'' +
        ''SLIDE_ID,''#39'',SLIDE_REF_ID=:SLIDE_EMBEDDED_REF_ID)'')
    DropCommands.Strings = (
      
        ''Data Collection~MFI_D2D3CE6CF05841ECBF3A2576372ED6D0~EmbedDatCol'' +
        ''SelectOper~InputMatrix~Insert~Insert''
      
        ''@InternalDrop~MFI_56FE149F98AE46388DFE7DE6E763B93F~EmbedDatColSe'' +
        ''lectOper~Insert~~Insert''
      
        ''@ExternalDrop~~EmbedDatColSelectOper,PlanResourceDatcolSel~ExecD'' +
        ''ragSourceCmd~~Insert'')
    DataDefinitions.Strings = (
      ''IMAGE''
      ''CALC_DC_FLAG''
      ''DAT_COL_ID''
      ''DAT_COL_TYPE''
      ''DAT_COL_TITLE_DISP''
      ''DAT_COL_TITLE''
      ''DAT_COL_UOM''
      ''LOWER_LIMIT''
      ''TARGET_VALUE''
      ''UPPER_LIMIT''
      ''NUM_DECIMAL_DIGITS''
      ''DAT_COL_CERT''
      ''FILE_DISP''
      ''FILE1''
      ''OPTIONAL_FLAG''
      ''orientation_flag''
      ''CROSS_ORDER_FLAG''
      ''CALC_FLAG''
      ''VARIABLE_NAME''
      ''DISPLAY_LINE_NO''
      ''VISIBILITY''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''OPER_KEY''
      ''STEP_KEY''
      ''STEP_UPDT_NO''
      ''LAST_ACTION''
      ''DAT_COL_LIMIT_ID''
      ''AUDIT_FLAG''
      ''STD_DATCOL_ID''
      ''VALID_RESULT_TYPE'')
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

