
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_72EE348625B9EA25E05387971F0A1F9A.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_72EE348625B9EA25E05387971F0A1F9A';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanToolResourcePalette';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 936/933.';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 550
  Height = 500
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
  BottomMargin = 15
  object _OBJ2: TsfLabel
    Left = 4
    Top = 28
    Width = 78
    Height = 13
    Caption = ''Bill of Resources''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object Bill_of_Resources: TsfDBGrid
    Left = 4
    Top = 245
    Width = 494
    Height = 150
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 0
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object Tool_Lib_Navigator: TsfUDVNavigator
    Left = 4
    Top = 210
    Width = 58
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 6
  end
  object _OBJ3: TsfCommandButton
    Left = 100
    Top = 210
    Width = 150
    Height = 21
    Hint = ''Tool''
    Caption = ''Embed Selected Tools''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (
      
        ''Desc=Tool,Priv=''#39''{TOOL_NO<>''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,Para'' +
        ''msSrc=@Default,Action=DragDrop,DropCmd='')
  end
  object _OBJ4: TsfLabel
    Left = 5
    Top = 230
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
  object _OBJ5: TsfDBGrid
    Left = 4
    Top = 245
    Width = 494
    Height = 170
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ7: TsfDBGrid
    Left = 4
    Top = 40
    Width = 494
    Height = 150
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 5
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    ExplorerLevelField = ''LVL''
    ExplorerIdFields = ''EXPLOR_FIELD''
    ExplorerParentFields = ''BOM_LINE_ID''
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ8: TsfUDVNavigator
    Left = 9
    Top = 5
    Width = 77
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''View ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 2
  end
  object _OBJ9: TsfDBEdit
    Left = 262
    Top = 5
    Width = 256
    Height = 24
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clRed
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clRed
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object embedTool_resource: TsfCommandButton
    Left = 100
    Top = 5
    Width = 150
    Height = 21
    Hint = ''Tool''
    Caption = ''Embed Selected Tools''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Tool,Priv=''#39''{LVL=''#39#39''1''#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSr'' +
        ''c=@Default,Action=DragDrop,DropCmd='')
  end
  object Oper_Tool_Select: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_RealPlanToolInfoDrag''
    LinkedControls.Strings = (
      ''_OBJ5~''
      ''Tool_Lib_Navigator~''
      ''_OBJ3~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''TOOL_NO~12~Tool;No~N~N~False~False~False~N~~~~~Default~N~~''
      ''TOOL_REV~5~Tool;Rev~N~N~False~False~False~N~~~~~Default~N~~''
      ''TOOL_TITLE~15~Tool Title~N~N~False~False~False~N~~~~~Default~N~~''
      ''ITEM_TYPE~5~Type~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''ITEM_SUBTYPE~5~Tool;Subtype~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~''
      ''TOOL_NOTES~5~Tool;Notes~N~N~True~False~False~N~~~~~Default~N~~''
      ''QTY~2~Qty~N~N~True~False~False~N~~~~~Default~N~~''
      
        ''OVERUSE_FLAG~3~Overuse/;Underuse?~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~~''
      ''OPTIONAL_FLAG~2~Opt~N~N~False~False~False~N~~~~~Default~N~~''
      ''SERIAL_FLAG~3~Serial;No?~N~N~False~False~False~N~~~~~Default~N~~''
      ''EXP_FLAG~3~Exp;Date?~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''DISPLAY_LINE_NO~5~Display;Line No~N~N~True~False~False~N~~~~~Def'' +
        ''ault~Y~~''
      
        ''SECURITY_GROUP~0~Security;Group~N~N~False~False~True~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''CROSS_ORDER_FLAG~0~Cross;Order?~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''ORIENTATION_FLAG~0~Orientation;Flag~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''TOOL_TITLE~Edit''
      ''ITEM_TYPE~Edit''
      ''ITEM_SUBTYPE~Edit''
      ''TOOL_NOTES~Edit''
      ''QTY~Edit''
      ''OVERUSE_FLAG~Edit''
      ''OPTIONAL_FLAG~Edit''
      ''SERIAL_FLAG~Edit''
      ''EXP_FLAG~Edit''
      ''DISPLAY_LINE_NO~Edit'')
    TestParamValues.Strings = (
      ''PLAN_ID=MFI_247610EEAB12C1D1DDA716843417CFB7''
      ''OPER_KEY=1''
      ''OPER_UPDT_NO=1''
      ''PLAN_VERSION=1''
      ''PLAN_REVISION=1''
      ''PLAN_ALTERATIONS=0'')
    InputUDVId = ''PWUST_737E800E4064C988E05387971F0A54DB''
    InsertUDVId = ''PWUST_737E800E4064C988E05387971F0A54DB''
    InsUpdDelUDVId = ''PWUST_737E800E4064C988E05387971F0A54DB''
    DropCommands.Strings = (
      ''Tool~PWUST_737E800E4064C988E05387971F0A54DB~Oper_Tool_Select~InputMatrix~'')
    DataDefinitions.Strings = (
      ''TOOL_NO''
      ''TOOL_REV''
      ''TOOL_TITLE''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''TOOL_NOTES''
      ''QTY''
      ''OVERUSE_FLAG''
      ''OPTIONAL_FLAG''
      ''SERIAL_FLAG''
      ''EXP_FLAG''
      ''DISPLAY_LINE_NO''
      ''SECURITY_GROUP''
      ''CROSS_ORDER_FLAG''
      ''ORIENTATION_FLAG'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''Oper_Tool_Select''
    FilterUpdateSqlId = ''MFI_INSERTCONTEXTFILTER''
    FilterSelectSqlId = ''MFI_SELECTLASTFILTERCONTEXT''
  end
  object PlanMBOMToolSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PlanMBOMToolSel''
    LinkedControls.Strings = (
      ''Bill_of_Resources~''
      ''_OBJ7~''
      ''_OBJ8~''
      ''embedTool_resource~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''BOM_LINE_NO~6~Line No~N~N~False~False~False~N~~~~~Default~N~''
      
        ''QTY_AUTHOR_ICON~2~ ~N~N~False~False~True~N~~@StdMarkup(QTY_AUTHO'' +
        ''R_ICON)~~~Default~N~''
      ''PART_NO~10~Tool;No~N~N~False~False~False~N~~~~~Default~N~''
      ''PART_CHG~2~Tool;Rev~N~N~False~False~False~N~~~~~Default~N~''
      
        ''MBOM_PART_TITLE~10~Tool;Title~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''MBOM_ITEM_SUBTYPE~7~Tool;Subtype~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~''
      ''SOURCE~15~Source~N~N~False~False~False~N~~~~~Default~N~''
      ''ITEM_QTY~3~Qty~N~N~False~False~False~N~~~~~Default~N~''
      
        ''AVAILABLE_QTY~4~Un-Authored;Qty~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      ''STD_PART_NO~10~Std Part;No~N~N~False~False~True~N~~~~~Default~N~''
      ''STOCK_UOM~10~UOM~N~N~False~False~False~N~~~~~Default~N~''
      ''SERIAL_FLAG~2~Serial;No?~N~N~False~False~False~N~~~~~Default~N~''
      ''EXP_FLAG~2~Exp;Date?~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PART_CHG~Edit''
      ''AVAILABLE_QTY~Edit''
      ''SERIAL_FLAG~Edit''
      ''QTY_AUTHOR_ICON~Edit''
      ''BOM_LINE_NO~Edit'')
    TestParamValues.Strings = (
      ''PLAN_ID=MFI_F2FB2ABDBADB76D9EE54ED55465C1377''
      ''PLAN_VERSION=1''
      ''PLAN_REVISION=1''
      ''PLAN_UPDT_NO=1''
      ''PLAN_ALTERATIONS=0''
      ''BOM_ID=MFI_8E47044A2B1357E72E70CC4EB599637A''
      ''MFG_BOM_CHG=1''
      ''OPER_NO=10''
      ''OPER_KEY=281405''
      ''OPER_UPDT_NO=1'')
    InputUDVId = ''MFI_3AF78A79187042EC9CFD840ED02F6DEB''
    InsertUDVId = ''MFI_3AF78A79187042EC9CFD840ED02F6DEB''
    InsUpdDelUDVId = ''MFI_3AF78A79187042EC9CFD840ED02F6DEB''
    DragEnabledExpr = ''@GetParamSourceValue(PlanMBOMToolSelect,''#39''LVL''#39'')=''#39''1''#39
    DropCommands.Strings = (
      
        ''Tool~MFI_3AF78A79187042EC9CFD840ED02F6DEB~PlanMBOMToolSelect~Inp'' +
        ''utMatrix~~Default'')
    ViewOptions.Strings = (
      ''Line View=MFI_PLG_PLANMBOMTOOLLINEVIEWSEL'')
    DataDefinitions.Strings = (
      ''BOM_LINE_NO''
      ''QTY_AUTHOR_ICON''
      ''PART_NO''
      ''PART_CHG''
      ''MBOM_PART_TITLE''
      ''MBOM_ITEM_SUBTYPE''
      ''SOURCE''
      ''ITEM_QTY''
      ''AVAILABLE_QTY''
      ''STOCK_UOM''
      ''SERIAL_FLAG''
      ''EXP_FLAG'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''PlanMBOMToolSelect''
  end
  object MultipleMBOMExist: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_MultipleMBOMSelect''
    LinkedControls.Strings = (
      ''_OBJ9~MESSAGE_TEXT'')
    ParamsSQLSourceName = ''PlanMBOMToolSelect''
    SelectedFields.Strings = (
      
        ''WARNING_MESSAGE~50~WARNING_MESSAGE~N~N~True~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''MESSAGE_TEXT~0~MESSAGE_TEXT~N~N~WARNING_MESSAGE = ''#39''N''#39'' OR WARNING'' +
        ''_MESSAGE = ''#39#39''~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''WARNING_MESSAGE~Edit''
      ''MESSAGE_TEXT~Edit'')
    DataDefinitions.Strings = (
      ''WARNING_MESSAGE''
      ''MESSAGE_TEXT'')
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

