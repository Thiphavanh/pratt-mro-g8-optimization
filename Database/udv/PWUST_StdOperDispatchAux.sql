
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_724FB1507F3279F5E05387971F0A6B22.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_724FB1507F3279F5E05387971F0A6B22';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_StdOperDispatchAux';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 897';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFFND';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 540
  Height = 40
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
  BottomMargin = -1
  object _OBJ1: TsfCommandButton
    Left = 4
    Top = 7
    Width = 160
    Height = 21
    Hint = ''Display Standard Operation''
    Caption = ''Display Standard Operation''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 0
    OtherCommands.Strings = (
      
        ''Desc=Display Standard Operation,Priv=''#39''{STDOPER_PLAN_ID<>''#39#39#39#39''}''#39'',V'' +
        ''isible={},TagValue=,FKey=,ParamsSrc=@ToolScope,Action=Invoke,Too'' +
        ''l=StdOper,ReportToolId=,Params(''#39''PLAN_ID=:PLAN_ID,''#39'',''#39''PLAN_VERSION'' +
        ''=:PLAN_VERSION,''#39'',''#39''PLAN_REVISION=:PLAN_REVISION,''#39'',''#39''PLAN_ALTERATIO'' +
        ''NS=:PLAN_ALTERATIONS,''#39'',@InvokeToolMode=EDITMODES.Edit_PL)'')
  end
  object _OBJ2: TsfCommandButton
    Left = 174
    Top = 7
    Width = 160
    Height = 21
    Caption = ''Move Standard Operation(s)''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 1
    OtherCommands.Strings = (
      
        #39''Desc=Move Standard Operation(s)''#39'',Priv={},Visible={},TagValue=,F'' +
        ''Key=,ParamsSrc=@Default,Action=UDV,UDVType=InputMatrix,UDVID=MFI'' +
        ''_AEC051E1088E449092BE8497D09D02FE'')
  end
  object COMPARE_STD_OPER: TsfCommandButton
    Left = 344
    Top = 7
    Width = 160
    Height = 21
    Caption = ''Compare Standard Operations''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Compare Standard Operations,Priv={},Visible={},TagValue=,FK'' +
        ''ey=,ParamsSrc=@Default,Action=UDV,UDVType=Insert,UDVID=MFI_7CC1D'' +
        ''9D70C3743FE958DF86D3655D4C3'')
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


set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_7135D5A39272C50DE05387971F0AFDCE.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_7135D5A39272C50DE05387971F0AFDCE';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_StdOperPartResourcePalette';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 897';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = -42
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
  object Part_Grig: TsfDBGrid
    Left = 3
    Top = 40
    Width = 494
    Height = 400
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
  object EmbedBuyoff: TsfCommandButton
    Left = 100
    Top = 5
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

        ''Desc=Part,Priv=''#39''{COMP_PART_NO<>''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey='' +
        '',ParamsSrc=@Default,Action=DragDrop,DropCmd='')
  end
  object Library: TsfLabel
    Left = 4
    Top = 27
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
    Left = 3
    Top = 5
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 1
  end
  object PlanEmbPartsSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_PartInfoDrag''
    KeyFieldNames = ''COMP_PART_NO,COMP_PART_CHG''
    LinkedControls.Strings = (
      ''Part_Grig''
      ''PartLibraryNavigator~''
      ''EmbedBuyoff~''
      ''Part_Grig~'')
    ParamsSQLSourceName = ''@ToolScope,@CurrentMarker,,@TestParamValues''
    PublishParams = True
    SelectedFields.Strings = (
      ''PART_ACTION~5~Part;Action~N~N~True~False~False~N~~~~~Default~N~''
      ''COMP_PART_NO~10~Part;No~N~N~False~False~False~N~~~~~Default~N~''
      ''COMP_PART_CHG~3~Part;Rev~N~N~False~False~False~N~~~~~Default~N~''
      ''PART_TITLE~15~Title~N~N~False~False~False~N~~~~~Default~Y''
      ''ITEM_SUBTYPE~3~Part;Subtype~N~N~True~False~True~N~~~~~Default~N~''

        ''ITEM_SUB_TYPE~3~Part;Subtype~N~N~False~False~False~N~~~~~Default'' +
        ''~N~''

        ''REMOVE_ACTION~0~Remove;Action~N~N~True~False~False~N~~~~~Default'' +
        ''~N~''

        ''UTILIZATION_RULE~7~Utilization Rule~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''

        ''TRACKABLE_FLAG~3~Display in;Unit Status?~N~N~False~False~False~N'' +
        ''~~~~~Default~N~''
      ''PROGRAM~7~Program~N~N~False~False~True~N~~~~~Default~N~''
      ''WORK_LOC~7~Work Location~N~N~False~False~True~N~~~~~Default~N~''
      ''REF_DES~3~Ref;Des~N~N~True~False~False~N~~~~~Default~N~''

        ''REF_DES_PREF_RANK~5~Ref Des;Rank~N~N~True~False~False~N~~~~~Defa'' +
        ''ult~N~''
      ''ITEM_QTY~3~Qty~N~N~True~False~False~N~~~~~Default~N~''
      ''STOCK_UOM~3~UOM~N~N~False~False~False~N~~~~~Default~N~''

        ''STANDARD_PART_FLAG~3~Std Part;No?~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      ''FIND_NO~3~Find;No~N~N~True~False~False~N~~~~~Default~N~''
      ''BOM_LINE_NO~15~BOM;Line No~N~N~True~False~False~N~~~~~Default~N~''
      ''OPTIONAL_FLAG~3~Opt~N~N~False~False~False~N~~~~~Default~N~''

        ''OVER_CONSUMPTION_FLAG~0~Overconsume;/Overremove?~N~N~True~False~'' +
        ''False~N~~~~~Default~N~''
      ''SERIAL_FLAG~3~Serial;No?~N~N~False~False~False~N~~~~~Default~N~''
      ''LOT_FLAG~3~Lot;No?~N~N~False~False~False~N~~~~~Default~N~''
      ''EXP_FLAG~3~Exp;Date?~N~N~False~False~False~N~~~~~Default~N~''
      ''SPOOL_FLAG~3~Spool;No?~N~N~False~False~False~N~~~~~Default~N~''

        ''UID_ITEM_FLAG~5~UID Item;Flag~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''

        ''UID_ENTRY_NAME~8~UID Entry;Name~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      ''ITEM_NOTES~8~Notes~N~N~True~False~False~N~~~~~Default~N~''
      ''UNIT_TYPE~5~Unit;Type~N~N~True~False~False~N~~~~~Default~N~''

        ''EFF_FROM~8~Effectivity;From~N~N~True~False~False~N~~~~~Default~N'' +
        ''~''

        ''EFF_THRU~8~Effectivity;Thru~N~N~True~False~False~N~~~~~Default~N'' +
        ''~''

        ''EFF_FROM_DATE~12~Effectivity;From Date~N~N~True~False~False~N~~~'' +
        ''~~Default~N~''

        ''EFF_THRU_DATE~12~Effectivity;Thru Date~N~N~True~False~False~N~~~'' +
        ''~~Default~N~''

        ''DISPLAY_LINE_NO~0~Display;Line No~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~''

        ''CROSS_ORDER_FLAG~0~CROSS_ORDER_FLAG~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~''

        ''ORIENTATION_FLAG~0~ORIENTATION_FLAG~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~''

        ''REPLACEMENT_PART_NO~8~Replacement;Part No ~N~N~True~False~False~'' +
        ''N~~~~~Default~N~''

        ''REPLACEMENT_PART_CHG~8~Replacement;Part Rev ~N~N~True~False~Fals'' +
        ''e~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''UID_ENTRY_NAME~Edit''
      ''REF_DES_PREF_RANK~Edit''
      ''PROGRAM~Edit''
      ''WORK_LOC~Edit''
      ''REF_DES~Edit''
      ''DRAG_PART_TITLE~Edit''
      ''PART_NO~Edit''
      ''SERIAL_FLAG~Edit''
      ''CROSS_ORDER_FLAG~Edit''
      ''OPTIONAL_FLAG~Edit''
      ''STANDARD_PART_FLAG~Edit'')
    PrintTitle = ''Parts''
    TestParamValues.Strings = (
      ''PLAN_ID=PWUST_635A8ABB69DE5B162FC097FBA341213''
      ''PLAN_VERSION=1''
      ''PLAN_REVISION=1''
      ''PLAN_ALTERATIONS=0''
      ''PLND_WORK_LOC=6100''
      ''PROGRAM=ETV25CHC''
      ''OPER_KEY=4005''
      ''OPER_UPDT_NO=1''
      ''PLAN_UPDT_NO=1''
      ''OPER_NO=1''
      ''PLAN_LOCATION_ID=PWUST_7C0B5E6E1710EC2599D59E2D9E1C951A''
      ''PART_CHG=1''
      ''ITEM_TYPE=1''
      ''ITEM_SUBTYPE=1'')
    InputUDVId = ''MFI_15041''
    InsertUDVId = ''MFI_15041''
    InsUpdDelUDVId = ''MFI_15041''
    DropCommands.Strings = (
      ''Insert Part~MFI_15041~PlanEmbPartsSelect~InputMatrix~~Default'')
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
    PublishedSQLSourceName = ''PlanEmbPartsSelect''
    FilterUpdateSqlId = ''MFI_INSERTCONTEXTFILTER''
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

