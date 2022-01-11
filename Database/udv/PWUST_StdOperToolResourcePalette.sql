
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_7135CE7C42B8C518E05387971F0ABA78.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_7135CE7C42B8C518E05387971F0ABA78';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_StdOperToolResourcePalette';
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
  object Tool_Lib_Navigator: TsfUDVNavigator
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
  object _OBJ3: TsfCommandButton
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
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Tool,Priv=''#39''{TOOL_NO<>''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey=,Para'' +
        ''msSrc=@Default,Action=DragDrop,DropCmd='')
  end
  object _OBJ4: TsfLabel
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
  object _OBJ5: TsfDBGrid
    Left = 4
    Top = 40
    Width = 494
    Height = 400
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
  object Oper_Tool_Select: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_PlanToolInfoDrag''
    LinkedControls.Strings = (
      ''_OBJ5~''
      ''Tool_Lib_Navigator~''
      ''_OBJ3~'')
    ParamsSQLSourceName = ''@ToolScope,@PLAN_LOCATION_ID''
    PublishParams = True
    SelectedFields.Strings = (
      ''TOOL_NO~12~Tool;No~N~N~False~False~False~N~~~~~Default~N~''
      ''TOOL_REV~5~Tool;Rev~N~N~False~False~False~N~~~~~Default~N~''
      ''TOOL_TITLE~15~Tool Title~N~N~False~False~False~N~~~~~Default~N~''
      ''ITEM_TYPE~5~Type~N~N~False~False~False~N~~~~~Default~N~''
      
        ''ITEM_SUBTYPE~5~Item;Subtype~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''
      ''TOOL_NOTES~5~Tool;Notes~N~N~True~False~False~N~~~~~Default~N~''
      ''QTY~2~Qty~N~N~True~False~False~N~~~~~Default~N~''
      
        ''OVERUSE_FLAG~3~Overuse/;Underuse?~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~''
      ''OPTIONAL_FLAG~2~Opt~N~N~False~False~False~N~~~~~Default~N~''
      ''SERIAL_FLAG~3~Serial;No?~N~N~False~False~False~N~~~~~Default~N~''
      ''EXP_FLAG~3~Exp;Date?~N~N~False~False~False~N~~~~~Default~N~''
      
        ''DISPLAY_LINE_NO~5~Display;Line No~N~N~True~False~False~N~~~~~Def'' +
        ''ault~N~''
      
        ''SECURITY_GROUP~0~Security;Group~N~N~False~False~True~N~~~~~Defau'' +
        ''lt~Y~'')
    SelectedFieldsEditControl.Strings = (
      ''TOOL_REV~Edit'')
    TestParamValues.Strings = (
      ''PLAN_ID=PWUST_635A8ABB69DE5B162FC097FBA341213''
      ''OPER_KEY=4005''
      ''OPER_UPDT_NO=1''
      ''PLAN_LOCATION_ID=PWUST_7C0B5E6E1710EC2599D59E2D9E1C951A''
      ''PLAN_VERSION=1''
      ''PLAN_REVISION=1''
      ''PLAN_ALTERATIONS=0'')
    InputUDVId = ''MFI_24552''
    InsertUDVId = ''MFI_24552''
    InsUpdDelUDVId = ''MFI_24552''
    DropCommands.Strings = (
      ''Tool~MFI_24552~Oper_Tool_Select~InputMatrix~'')
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
      ''SECURITY_GROUP'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''Oper_Tool_Select''
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

