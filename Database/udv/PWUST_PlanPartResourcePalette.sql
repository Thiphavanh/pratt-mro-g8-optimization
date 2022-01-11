
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_72EDE26B5816DD0CE05387971F0AB6D2.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_72EDE26B5816DD0CE05387971F0AB6D2';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanPartResourcePalette';
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
  object BOM_Navigator: TsfUDVNavigator
    Left = 3
    Top = 15
    Width = 77
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''View ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object Part_Grig: TsfDBGrid
    Left = 3
    Top = 255
    Width = 494
    Height = 170
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
  object PlanEmbedBuyoff: TsfCommandButton
    Left = 100
    Top = 220
    Width = 150
    Height = 21
    Hint = ''Part''
    Caption = ''Embed Selected Parts''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Part,Priv=''#39''{COMP_PART_NO<>''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey='' +
        '',ParamsSrc=@Default,Action=DragDrop,DropCmd='')
  end
  object Library: TsfLabel
    Left = 4
    Top = 240
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
  object BillOfResources: TsfDBGrid
    Left = 3
    Top = 55
    Width = 494
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
    ExplorerLevelField = ''LVL''
    ExplorerIdFields = ''EXPLOR_FIELD''
    ExplorerParentFields = ''BOM_LINE_ID''
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ1: TsfLabel
    Left = 3
    Top = 38
    Width = 80
    Height = 13
    Caption = ''Bill Of Resources''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object PartLibraryNavigator: TsfUDVNavigator
    Left = 3
    Top = 220
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 5
  end
  object _OBJ2: TsfDBEdit
    Left = 262
    Top = 12
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
    TabOrder = 3
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clRed
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object embedPart_resource: TsfCommandButton
    Left = 100
    Top = 15
    Width = 150
    Height = 21
    Hint = ''Part''
    Caption = ''Embed Selected Parts''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Part,Priv=''#39''{LVL=''#39#39''1''#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSr'' +
        ''c=@Default,Action=DragDrop,DropCmd='')
  end
  object PlanMBOMSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PlanMBOMSelect''
    LinkedControls.Strings = (
      ''BOM_Navigator''
      ''embedPart_resource~''
      ''BillOfResources~'')
    ParamsSQLSourceName = ''@CurrentMarker,@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''BOM_LINE_NO~6~Line No~N~N~False~False~False~N~~~~~Default~N~''
      
        ''QTY_AUTHOR_ICON~2~ ~N~N~False~False~True~N~~@StdMarkup(QTY_AUTHO'' +
        ''R_ICON)~~~Default~N~''
      ''MBOM_PART_NO~10~Part;No~N~N~False~False~False~N~~~~~Default~N~''
      ''MBOM_PART_CHG~5~Part;Rev~N~N~False~False~False~N~~~~~Default~N~''
      
        ''REF_DES~4~Reference;Designator~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~''
      
        ''ALT_GROUP~5~Alternate Group~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''
      ''ITEM_QTY~8~Qty~N~N~False~False~False~N~~~~~Default~N~''
      ''USE_QTY~4~Qty Used~N~N~False~False~False~N~~~~~Default~N~''
      ''REMOVE_QTY~4~Qty Removed~N~N~False~False~False~N~~~~~Default~N~''
      
        ''AVAILABLE_QTY~4~Un-Authored;Qty~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      ''STD_PART_NO~10~Std Part;No~N~N~False~False~True~N~~~~~Default~N~''
      ''STOCK_UOM~4~UOM~N~N~False~False~False~N~~~~~Default~N~''
      
        ''PHANTOM_KIT_PART_NO~10~Phantom/Kit;Part No~N~N~False~False~True~'' +
        ''N~~~~~Default~N~''
      ''FIND_NO~5~Find;No~N~N~False~False~False~N~~~~~Default~N~''
      ''SERIAL_FLAG~4~Serial;No?~N~N~False~False~False~N~~~~~Default~N~''
      ''LOT_FLAG~4~Lot;No?~N~N~False~False~False~N~~~~~Default~N~''
      ''EXP_FLAG~4~Exp;Date?~N~N~False~False~False~N~~~~~Default~N~''
      ''SPOOL_FLAG~4~Spool;No?~N~N~False~False~False~N~~~~~Default~N~''
      ''UNIT_TYPE~10~Effectivity;~N~N~False~False~False~N~~~~~Default~N~''
      ''EFF_FROM~8~Effective;From~N~N~False~False~False~N~~~~~Default~N~''
      ''EFF_THRU~8~Effective;Thru~N~N~False~False~False~N~~~~~Default~N~''
      
        ''EFF_FROM_DATE~8~Effective;From Date~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      
        ''EFF_THRU_DATE~8~Effective;Thru Date~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      
        ''STANDARD_PART_FLAG~5~Std ;Part?~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~'')
    SelectedFieldsEditControl.Strings = (
      ''MBOM_PART_CHG~Edit''
      ''STANDARD_PART_FLAG~Edit''
      ''QTY_AUTHOR_ICON~Edit''
      ''LOT_FLAG~Edit''
      ''EXP_FLAG~Edit''
      ''STOCK_UOM~Edit''
      ''REF_DES~Edit''
      ''SERIAL_FLAG~Edit''
      ''AVAILABLE_QTY~Edit''
      ''ITEM_QTY~Edit'')
    TestParamValues.Strings = (
      ''PLAN_ID=P''
      ''OPER_KEY=1''
      ''OPER_UPDT_NO=2''
      ''PLND_WORK_LOC=A''
      ''PROGRAM=S''
      ''PLAN_REVISION=1''
      ''PLAN_VERSION=1''
      ''PLAN_ALTERATIONS=0''
      ''ITEM_ID2=1''
      ''MFG_BOM_CHG=1''
      ''PLAN_UPDT_NO=1''
      ''OPER_NO=1''
      ''PART_NO=1''
      ''ITEM_TYPE=1''
      ''PART_CHG=1''
      ''ITEM_SUBTYPE=1''
      ''BOM_ID=i''
      ''BOM_NO=1''
      ''EFF_FROM=q''
      ''SFF_THRU=d'')
    InputUDVId = ''MFI_3EB4740A35744432AC9C1CDF1900C962''
    InsertUDVId = ''MFI_3EB4740A35744432AC9C1CDF1900C962''
    InsUpdDelUDVId = ''MFI_3EB4740A35744432AC9C1CDF1900C962''
    DragEnabledExpr = ''@GetParamSourceValue(PlanMBOMSelect,''#39''LVL''#39'')=''#39''1''#39
    DropCommands.Strings = (
      
        ''Insert Part~MFI_3EB4740A35744432AC9C1CDF1900C962~PlanMBOMSelect~'' +
        ''InputMatrix~'')
    ViewOptions.Strings = (
      ''Line View=MFI_SFPL_PLANMBOMSELECTFORLISTVIEW'')
    DataDefinitions.Strings = (
      ''BOM_LINE_NO''
      ''QTY_AUTHOR_ICON''
      ''MBOM_PART_NO''
      ''MBOM_PART_CHG''
      ''REF_DES''
      ''ALT_GROUP''
      ''ITEM_QTY''
      ''USE_QTY''
      ''REMOVE_QTY''
      ''AVAILABLE_QTY''
      ''STD_PART_NO''
      ''STOCK_UOM''
      ''PHANTOM_KIT_PART_NO''
      ''FIND_NO''
      ''SERIAL_FLAG''
      ''LOT_FLAG''
      ''EXP_FLAG''
      ''SPOOL_FLAG''
      ''UNIT_TYPE''
      ''EFF_FROM''
      ''EFF_THRU''
      ''EFF_FROM_DATE''
      ''EFF_THRU_DATE''
      ''STANDARD_PART_FLAG'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''PlanMBOMSelect''
    FilterUpdateSqlId = ''MFI_INSERTCONTEXTFILTER''
    FilterSelectSqlId = ''MFI_SELECTLASTFILTERCONTEXT''
  end
  object RealPlanEmbPartsSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_PlanPartInfoDrag''
    KeyFieldNames = ''COMP_PART_NO,COMP_PART_CHG''
    LinkedControls.Strings = (
      ''Part_Grig''
      ''PartLibraryNavigator~''
      ''PlanEmbedBuyoff''
      ''Part_Grig~''
      ''PlanEmbedBuyoff~'')
    ParamsSQLSourceName = ''@ToolScope,@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      ''PART_ACTION~5~Part;Action~N~N~True~False~False~N~~~~~Default~N~~''
      ''COMP_PART_NO~10~Part;No~N~N~False~False~False~N~~~~~Default~N~~''
      ''COMP_PART_CHG~3~Part;Rev~N~N~False~False~False~N~~~~~Default~N~~''
      ''PART_TITLE~15~Title~N~N~False~False~False~N~~~~~Default~Y~''
      
        ''ITEM_SUBTYPE~3~Part;Subtype~N~N~True~False~True~N~~~~~Default~N~'' +
        ''~''
      
        ''ITEM_SUB_TYPE~3~Part;Subtype~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''REMOVE_ACTION~0~Remove;Action~N~N~True~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''UTILIZATION_RULE~7~Utilization Rule~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~''
      
        ''TRACKABLE_FLAG~3~Display in;Unit Status?~N~N~False~False~False~N'' +
        ''~~~~~Default~N~~''
      ''PROGRAM~7~Program~N~N~False~False~True~N~~~~~Default~N~~''
      ''WORK_LOC~7~Work Location~N~N~False~False~True~N~~~~~Default~N~~''
      ''REF_DES~3~Ref;Des~N~N~True~False~False~N~~~~~Default~N~~''
      
        ''REF_DES_PREF_RANK~5~Ref Des;Rank~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~~''
      ''ITEM_QTY~3~Qty~N~N~True~False~False~N~~~~~Default~N~~''
      ''STOCK_UOM~3~UOM~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''STANDARD_PART_FLAG~3~Std Part;No?~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~''
      ''FIND_NO~3~Find;No~N~N~True~False~False~N~~~~~Default~N~~''
      
        ''BOM_LINE_NO~15~BOM;Line No~N~N~True~False~False~N~~~~~Default~N~'' +
        ''~''
      ''OPTIONAL_FLAG~3~Opt~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''OVER_CONSUMPTION_FLAG~0~Overconsume;/Overremove?~N~N~True~False~'' +
        ''False~N~~~~~Default~N~~''
      ''SERIAL_FLAG~3~Serial;No?~N~N~False~False~False~N~~~~~Default~N~~''
      ''LOT_FLAG~3~Lot;No?~N~N~False~False~False~N~~~~~Default~N~~''
      ''EXP_FLAG~3~Exp;Date?~N~N~False~False~False~N~~~~~Default~N~~''
      ''SPOOL_FLAG~3~Spool;No?~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''UID_ITEM_FLAG~5~UID Item;Flag~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''UID_ENTRY_NAME~8~UID Entry;Name~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~''
      ''ITEM_NOTES~8~Notes~N~N~True~False~False~N~~~~~Default~N~~''
      ''UNIT_TYPE~5~Unit;Type~N~N~True~False~False~N~~~~~Default~N~~''
      
        ''EFF_FROM~8~Effectivity;From~N~N~True~False~False~N~~~~~Default~N'' +
        ''~~''
      
        ''EFF_THRU~8~Effectivity;Thru~N~N~True~False~False~N~~~~~Default~N'' +
        ''~~''
      
        ''EFF_FROM_DATE~12~Effectivity;From Date~N~N~True~False~False~N~~~'' +
        ''~~Default~N~~''
      
        ''EFF_THRU_DATE~12~Effectivity;Thru Date~N~N~True~False~False~N~~~'' +
        ''~~Default~N~~''
      
        ''DISPLAY_LINE_NO~0~Display;Line No~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~~''
      
        ''CROSS_ORDER_FLAG~0~Cross;Order?~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''ORIENTATION_FLAG~0~Orientation;Flag~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~~''
      
        ''REPLACEMENT_PART_NO~8~Replacement;Part No ~N~N~True~False~False~'' +
        ''N~~~~~Default~N~~''
      
        ''REPLACEMENT_PART_CHG~8~Replacement;Part Rev ~N~N~True~False~Fals'' +
        ''e~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''UID_ENTRY_NAME~Edit''
      ''REF_DES_PREF_RANK~Edit''
      ''PROGRAM~Edit''
      ''WORK_LOC~Edit''
      ''REF_DES~Edit''
      ''DRAG_PART_TITLE~Edit''
      ''PART_NO~Edit''
      ''SERIAL_FLAG~Edit''
      ''OPTIONAL_FLAG~Edit''
      ''STANDARD_PART_FLAG~Edit''
      ''CROSS_ORDER_FLAG~Edit''
      ''COMP_PART_NO~Edit'')
    PrintTitle = ''Parts''
    TestParamValues.Strings = (
      ''PLAN_ID=MFI_5DB6011236ECF0069764BAD76D7E0038''
      ''PLAN_VERSION=1''
      ''PLAN_REVISION=1''
      ''PLAN_ALTERATIONS=0''
      ''PLND_WORK_LOC=L''
      ''PROGRAM=P''
      ''OPER_KEY=1''
      ''OPER_UPDT_NO=1''
      ''PLAN_UPDT_NO=1''
      ''OPER_NO=1''
      ''PART_NO=1''
      ''PART_CHG=1''
      ''ITEM_TYPE=1''
      ''ITEM_SUBTYPE=1'')
    InputUDVId = ''PWUST_737E6A992CFAC996E05387971F0A20E9''
    InsertUDVId = ''PWUST_737E6A992CFAC996E05387971F0A20E9''
    InsUpdDelUDVId = ''PWUST_737E6A992CFAC996E05387971F0A20E9''
    DropCommands.Strings = (
      ''Insert Part~PWUST_737E6A992CFAC996E05387971F0A20E9~PlanEmbPartsSelect~InputMatrix~~Default'')
    DataDefinitions.Strings = (
      ''PART_ACTION''
      ''COMP_PART_NO''
      ''COMP_PART_CHG''
      ''PART_TITLE''
      ''ITEM_SUBTYPE''
      ''ITEM_SUB_TYPE''
      ''REMOVE_ACTION''
      ''UTILIZATION_RULE''
      ''TRACKABLE_FLAG''
      ''PROGRAM''
      ''WORK_LOC''
      ''REF_DES''
      ''REF_DES_PREF_RANK''
      ''ITEM_QTY''
      ''STOCK_UOM''
      ''STANDARD_PART_FLAG''
      ''FIND_NO''
      ''BOM_LINE_NO''
      ''OPTIONAL_FLAG''
      ''OVER_CONSUMPTION_FLAG''
      ''SERIAL_FLAG''
      ''LOT_FLAG''
      ''EXP_FLAG''
      ''SPOOL_FLAG''
      ''UID_ITEM_FLAG''
      ''UID_ENTRY_NAME''
      ''ITEM_NOTES''
      ''UNIT_TYPE''
      ''EFF_FROM''
      ''EFF_THRU''
      ''EFF_FROM_DATE''
      ''EFF_THRU_DATE''
      ''DISPLAY_LINE_NO''
      ''CROSS_ORDER_FLAG''
      ''ORIENTATION_FLAG''
      ''REPLACEMENT_PART_NO''
      ''REPLACEMENT_PART_CHG'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''RealPlanEmbPartsSelect''
    FilterUpdateSqlId = ''MFI_INSERTCONTEXTFILTER''
    FilterSelectSqlId = ''MFI_SELECTLASTFILTERCONTEXT''
  end
  object MultipleMBOMSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_MultipleMBOMSelect''
    LinkedControls.Strings = (
      ''_OBJ2~MESSAGE_TEXT'')
    ParamsSQLSourceName = ''PlanMBOMSelect''
    SelectedFields.Strings = (
      
        ''WARNING_MESSAGE~50~WARNING_MESSAGE~N~N~True~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''MESSAGE_TEXT~0~MESSAGE_TEXT~N~N~WARNING_MESSAGE = ''#39''N''#39'' OR WARNING'' +
        ''_MESSAGE = ''#39#39''~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''MUL_MBOM_FLAG~Edit''
      ''WARNING_MESSAGE~Edit''
      ''MESSAGE_TEXT~Edit'')
    TestParamValues.Strings = (
      ''PLAN_ID=p''
      ''PLAN_UPDT_NO=1'')
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

