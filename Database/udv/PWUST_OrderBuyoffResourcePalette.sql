
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_7985CC13731B9E4AE05387971F0A96D2.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_7985CC13731B9E4AE05387971F0A96D2';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderBuyoffResourcePalette';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202 Defect 793';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
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
  object Buyoff_Navigator: TsfUDVNavigator
    Left = 4
    Top = 5
    Width = 58
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object Buyoff_Grig: TsfDBGrid
    Left = 4
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
    Left = 151
    Top = 5
    Width = 150
    Height = 21
    Hint = ''Buyoff''
    Caption = ''Embed Selected Buyoff/Cert''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Buyoff,Priv=''#39''{BUYOFF_TYPE<>''#39#39#39#39''}''#39'',Visible={},TagValue=,FKey'' +
        ''=,ParamsSrc=@Default,Action=DragDrop,DropCmd='')
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
  object WIDOperBuy_ALT: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_BUYOFFRESOURCEPALETTESELECT''
    LinkedControls.Strings = (
      ''Buyoff_Grig~''
      ''Buyoff_Navigator~''
      ''EmbedBuyoff~'')
    ParamsSQLSourceName = ''@CurrentMarker,@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''BUYOFF_TYPE~10~Buyoff Type~N~N~False~True~False~N~~~~~Default~N~'' +
        ''~''
      
        ''BUYOFF_TITLE~25~Buyoff Title~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      ''BUYOFF_CERT~12~Req''#39''d Cert~N~N~True~False~False~N~~~~~Default~N~~''
      ''OPTIONAL_FLAG~1~Opt?~N~N~True~False~False~N~~~~~Default~N~~''
      
        ''CROSS_ORDER_FLAG~5~Cross;Order?~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''DESCRIPTION~20~Description~N~N~False~False~True~N~~~~~Default~N~'' +
        ''~'')
    SelectedFieldsEditControl.Strings = (
      ''CROSS_ORDER_FLAG~Edit''
      ''BUYOFF_TYPE~Edit'')
    PrintTitle = ''Buyoffs''
    TestParamValues.Strings = (
      ''@whereClause=''#39#39
      ''PLAN_ID=MFI_1'')
    InputUDVId = ''MFI_1003629''
    InsertUDVId = ''MFI_1003629''
    InsUpdDelUDVId = ''MFI_1003629''
    DropCommands.Strings = (
      ''Buyoff~MFI_1003629~WIDOperBuy_ALT~InputMatrix~'')
    DataDefinitions.Strings = (
      ''BUYOFF_TYPE''
      ''BUYOFF_TITLE''
      ''BUYOFF_CERT''
      ''OPTIONAL_FLAG''
      ''CROSS_ORDER_FLAG''
      ''DESCRIPTION'')
    ConsolidatedQuery = False
    FilterUpdateSqlId = ''MFI_INSERTORDERCONTEXTFILTER''
    FilterSelectSqlId = ''MFI_SELECTORDERLASTFILTERCONTEXT''
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

