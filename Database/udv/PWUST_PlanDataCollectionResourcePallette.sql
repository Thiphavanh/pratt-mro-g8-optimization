
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_7FBD8E4128AE3812E05387971F0AA228.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_7FBD8E4128AE3812E05387971F0AA228';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanDataCollectionResourcePallette';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Clone of MFI_CF46CDCB49EA406BBFAF28983E72BA4F - SMRO_PLG_313';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.2.0.20180714~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 550
  Height = 500
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Library''
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
  BottomMargin = 15
  object Lib_Grig: TsfDBGrid
    Left = 4
    Top = 192
    Width = 494
    Height = 300
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
  object EmbedSelectedDataCollections: TsfCommandButton
    Left = 151
    Top = 157
    Width = 170
    Height = 21
    Hint = ''Data Collection''
    Caption = ''Embed Selected Data Collections''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 1
    OtherCommands.Strings = (
      
        ''Desc=Data Collection,Priv=''#39''{STD_DATCOL_ID<>''#39#39#39#39''}''#39'',Visible={},Tag'' +
        ''Value=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd='')
  end
  object Library: TsfLabel
    Left = 4
    Top = 179
    Width = 40
    Height = 13
    Caption = ''Library''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = [fsBold]
    ShowHint = False
  end
  object PartLibraryNavigator: TsfUDVNavigator
    Left = 4
    Top = 157
    Width = 58
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 2
  end
  object _OBJ1: TsfDBGrid
    Left = 4
    Top = 46
    Width = 494
    Height = 90
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 3
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ2: TsfLabel
    Left = 4
    Top = 27
    Width = 59
    Height = 13
    Caption = ''Resources''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = [fsBold]
    ShowHint = False
  end
  object _OBJ3: TsfUDVNavigator
    Left = 4
    Top = 6
    Width = 58
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 4
  end
  object EmbedSelectedPlanResourceDataCollections: TsfCommandButton
    Left = 151
    Top = 6
    Width = 170
    Height = 21
    Hint = ''Data Collection''
    Caption = ''Embed Selected Data Collections''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Data Collection,Priv=''#39''{STD_DATCOL_ID<>''#39#39#39#39''}''#39'',Visible={},Tag'' +
        ''Value=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd='')
  end
  object EmbedDatColSelectOper: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_MFI_SelectDataCollection''
    LinkedControls.Strings = (
      ''PartLibraryNavigator~''
      ''Lib_Grig''
      ''EmbedSelectedDataCollections''
      ''EmbedSelectedDataCollections~''
      ''Lib_Grig~'')
    ParamsSQLSourceName = ''@ToolScope,@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''UCF_STEP_DC_FLAG1~5~ROM;Incl?~N~N~True~False~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''DAT_COL_TYPE~13~Data Collection Type~N~N~False~False~False~N~~~~'' +
        ''~Default~Y~~~''
      
        ''DAT_COL_TITLE~18~Data Collection Title ~N~N~True~False~False~N~~'' +
        ''~~~Default~N~~~''
      ''DAT_COL_UOM~8~UOM~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''UCF_STEP_DC_VCH255_1~10~Engineer Remarks~N~N~True~False~False~N~'' +
        ''~~~~Default~N~~~''
      ''FORMAT~10~Format~N~N~False~False~True~N~~~~~Default~N~~~''
      ''LOWER_LIMIT~3~LSL;~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''TARGET_VALUE~5~Target;Value~N~N~True~False~False~N~~~~~Default~N'' +
        ''~~~''
      ''UPPER_LIMIT~3~USL;~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''DAT_COL_CERT~10~Req''#39''d Cert; ~N~N~True~False~False~N~~~~~Default~'' +
        ''N~~~''
      
        ''FILE_DISP~10~Template File~N~N~True~False~False~N~~~~~Default~N~'' +
        ''~~''
      ''FILE1~10~New File~N~N~True~False~False~N~~~~~Default~N~~~''
      ''CALC_FLAG~1~Calculated?~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''VARIABLE_NAME~13~Variable;Name~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      ''OPTIONAL_FLAG~3~Opt?; ~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''DISPLAY_LINE_NO~0~Display Line No; ~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''AUDIT_FLAG~9~Over Inspection;Req''#39''d?~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~~~''
      ''SPC_FLAG~3~SPC~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''SELECTABLE_DC_FLAG~5~Selectable?~N~N~True~False~True~N~~~~~Defau'' +
        ''lt~N~~~''
      
        ''VALID_RESULT_TYPE~15~Validation;Result Type~N~N~True~False~False'' +
        ''~N~~~~~Default~N~~~''
      
        ''UCF_STEP_DC_FLAG2~1~Serviceable;Limit;Used~N~N~True~False~False~'' +
        ''N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''UID_ENTRY_NAME~Edit''
      ''REF_DES_PREF_RANK~Edit''
      ''PROGRAM~Edit''
      ''WORK_LOC~Edit''
      ''REF_DES~Edit''
      ''DRAG_PART_TITLE~Edit''
      ''PART_NO~Edit''
      ''SERIAL_FLAG~Edit''
      ''PART_ACTION~Edit''
      ''STANDARD_PART_FLAG~Edit''
      ''CALC_DC_FLAG~Edit''
      ''AUDIT_FLAG~Edit''
      ''OPTIONAL_FLAG~Edit''
      ''VARIABLE_NAME~Edit''
      ''DISPLAY_LINE_NO~Edit''
      ''VALID_RESULT_TYPE~Edit''
      ''SPC_FLAG~Edit''
      ''TARGET_VALUE~Edit''
      ''FORMAT~Edit''
      ''DAT_COL_TYPE~Edit''
      ''UCF_STEP_DC_FLAG1~CheckBox=1~0''
      ''UCF_STEP_DC_FLAG2~CheckBox=1~0'')
    PrintTitle = ''Parts''
    TestParamValues.Strings = (
      ''PLND_WORK_LOC=CEC2''
      ''PROGRAM=''
      ''PLAN_ID=PWUST_D81FD580A5A9DEDDABA54C2A5E13285F''
      ''OPER_KEY=413364''
      ''PLAN_VERSION=1''
      ''PLAN_REVISION=1''
      ''PLAN_ALTERATIONS=0''
      ''OPER_NO=0010''
      ''PLAN_UPDT_NO=1'')
    InputUDVId = ''MF1_1000541''
    InsertUDVId = ''MF1_1000541''
    InsUpdDelUDVId = ''MF1_1000541''
    DropCommands.Strings = (
      
        ''Data Collection~MF1_1000541~EmbedDatColSelectOper~InputMatrix~~D'' +
        ''efault'')
    ViewOptions.Strings = (
      ''='')
    DataDefinitions.Strings = (
      ''UCF_STEP_DC_FLAG1''
      ''DAT_COL_TYPE''
      ''DAT_COL_TITLE''
      ''DAT_COL_UOM''
      ''FORMAT''
      ''LOWER_LIMIT''
      ''TARGET_VALUE''
      ''UPPER_LIMIT''
      ''DAT_COL_CERT''
      ''FILE_DISP''
      ''FILE1''
      ''CALC_FLAG''
      ''VARIABLE_NAME''
      ''OPTIONAL_FLAG''
      ''DISPLAY_LINE_NO''
      ''AUDIT_FLAG''
      ''SPC_FLAG''
      ''SELECTABLE_DC_FLAG''
      ''VALID_RESULT_TYPE'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''EmbedDatColSelectOper''
    FilterUpdateSqlId = ''MFI_INSERTCONTEXTFILTER''
    FilterSelectSqlId = ''MFI_SELECTLASTFILTERCONTEXT''
  end
  object PlanResourceDatcolSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PlanResourceDatColSelect''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ3~''
      ''EmbedSelectedPlanResourceDataCollections''
      ''EmbedSelectedPlanResourceDataCollections~'')
    ParamsSQLSourceName = ''@ToolScope,@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''DAT_COL_TYPE~12~Data Collection;Type~N~N~False~False~False~N~~~~'' +
        ''~Default~N~''
      
        ''DAT_COL_TYPE_DESC~20~Description~N~N~True~False~True~N~~~~~Defau'' +
        ''lt~N~''
      
        ''DAT_COL_TITLE~0~Data Collection;Title~N~N~False~False~False~N~~~'' +
        ''~~Default~Y~''
      ''DAT_COL_UOM~3~UOM; ~N~N~False~False~False~N~~~~~Default~N~''
      ''FORMAT~0~FORMAT~N~N~True~False~True~N~~~~~Default~N~''
      
        ''NUM_DECIMAL_DIGITS~0~NUM_DECIMAL_DIGITS~N~N~True~False~True~N~~~'' +
        ''~~Default~N~''
      ''LOWER_LIMIT~3~LSL; ~N~N~False~False~False~N~~~~~Default~N~''
      
        ''TARGET_VALUE~5~Target;Value~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''
      ''UPPER_LIMIT~3~USL; ~N~N~False~False~False~N~~~~~Default~N~''
      
        ''DAT_COL_CERT~10~Req''#39''d Cert; ~N~N~False~False~False~N~~~~~Default'' +
        ''~N~''
      ''FILE_DISP~10~Template File~N~N~True~False~False~N~~~~~Default~N~''
      ''FILE1~10~New File~N~N~True~False~False~N~~~~~Default~N~''
      ''CALC_FLAG~1~Calculated?; ~N~N~True~False~False~N~~~~~Default~N~''
      
        ''VARIABLE_NAME~13~Variable;Name~N~N~True~False~False~N~~~~~Defaul'' +
        ''t~N~''
      ''OPTIONAL_FLAG~3~Opt?; ~N~N~False~False~False~N~~~~~Default~N~''
      
        ''DISPLAY_LINE_NO~0~Display Line No; ~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~''
      
        ''AUDIT_FLAG~9~Over Inspection;Req''#39''d?~N~N~True~False~False~N~~~~~D'' +
        ''efault~N~''
      ''SPC_FLAG~0~SPC; ~N~N~True~False~True~N~~~~~Default~N~''
      
        ''SELECTABLE_DC_FLAG~5~Selectable?; ~N~N~True~False~True~N~~~~~Def'' +
        ''ault~N~''
      
        ''VALID_RESULT_TYPE~15~Validation;Result Type~N~N~True~False~False'' +
        ''~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''VALID_RESULT_TYPE~Edit''
      ''DAT_COL_TITLE~Edit''
      ''DAT_COL_TYPE_DESC~Edit'')
    TestParamValues.Strings = (
      ''BOM_ID=MFI_F66DECB31562015D643D45D4D8AE2D93''
      ''PART_NO=FND-25452_IPART''
      ''PART_CHG=A'')
    InputUDVId = ''MFI_1E48B275A4834DB881A57C41BC76D5E7''
    InsertUDVId = ''MFI_1E48B275A4834DB881A57C41BC76D5E7''
    InsUpdDelUDVId = ''MFI_1E48B275A4834DB881A57C41BC76D5E7''
    DropCommands.Strings = (
      
        ''Data Collection~MFI_1E48B275A4834DB881A57C41BC76D5E7~PlanResourc'' +
        ''eDatcolSel~InputMatrix~~Default'')
    DataDefinitions.Strings = (
      ''DAT_COL_TYPE''
      ''DAT_COL_TYPE_DESC''
      ''DAT_COL_TITLE''
      ''DAT_COL_UOM''
      ''FORMAT''
      ''NUM_DECIMAL_DIGITS''
      ''LOWER_LIMIT''
      ''TARGET_VALUE''
      ''UPPER_LIMIT''
      ''DAT_COL_CERT''
      ''CALC_FLAG''
      ''VARIABLE_NAME''
      ''OPTIONAL_FLAG''
      ''DISPLAY_LINE_NO''
      ''AUDIT_FLAG''
      ''SPC_FLAG''
      ''SELECTABLE_DC_FLAG''
      ''VALID_RESULT_TYPE''
      ''FILE_DISP''
      ''FILE1'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''PlanResourceDatcolSel''
    FilterUpdateSqlId = ''MFI_SELECTLASTFILTERCONTEXT''
    FilterSelectSqlId = ''MFI_SELECTLASTFILTERCONTEXT''
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

