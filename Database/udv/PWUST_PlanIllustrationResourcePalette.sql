
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_CFD1F8EEBEFCDF42E05387971F0A362E.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_CFD1F8EEBEFCDF42E05387971F0A362E';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanIllustrationResourcePalette';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='DEFECT 1906';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFFND';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 550
  Height = 500
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
  BottomMargin = 15
  object BOM_Navigator: TsfUDVNavigator
    Left = 4
    Top = 5
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
    Left = 4
    Top = 245
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
  object EmbedSelectedIllustration: TsfCommandButton
    Left = 151
    Top = 210
    Width = 150
    Height = 21
    Hint = ''Embed Selected Illustration''
    Caption = ''Embed Selected Illustration''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Insert as Illustration,Priv=''#39''{OBJECT_ID<>''#39#39#39#39''}''#39'',Visible={},'' +
        ''TagValue=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=''
      
        ''Desc=Insert as Link,Priv=''#39''{OBJECT_ID<>''#39#39#39#39''}''#39'',Visible={},TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=EmbedMMLink'')
  end
  object Library: TsfLabel
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
  object BillOfResources: TsfDBGrid
    Left = 4
    Top = 45
    Width = 380
    Height = 150
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
    ExplorerLevelField = ''LVL''
    ExplorerIdFields = ''EXPLOR_FIELD''
    ExplorerParentFields = ''BOM_LINE_ID''
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ1: TsfLabel
    Left = 4
    Top = 28
    Width = 50
    Height = 13
    Caption = ''Resources''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object PartLibraryNavigator: TsfUDVNavigator
    Left = 4
    Top = 210
    Width = 77
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''View ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 4
  end
  object EmbedSelectedIllustration1: TsfCommandButton
    Left = 89
    Top = 5
    Width = 150
    Height = 21
    Hint = ''Embed Selected Illustration''
    Caption = ''Embed Selected Illustration''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Insert as Illustration,Priv=''#39''{LVL=''#39#39''1''#39#39''}''#39'',Visible={},TagVal'' +
        ''ue=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=''
      
        ''Desc=Insert as Link,Priv={},Visible=''#39''{LVL=''#39#39''1''#39#39'' AND OBJECT_ID <>'' +
        '' ''#39#39#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropC'' +
        ''md=EmbedMMLink'')
  end
  object _OBJ2: TsfCommandButton
    Left = 333
    Top = 210
    Width = 130
    Height = 21
    Hint = ''Embed Illustration''
    Caption = ''Embed New Illustration''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Drawings,Priv={},Visible={},TagValue=Drawings,FKey=,ParamsS'' +
        ''rc=@Default,Action=DragDrop,DropCmd=Drawings''
      
        ''Desc=Process Specs,Priv={},Visible={},TagValue=Process Specs,FKe'' +
        ''y=,ParamsSrc=@Default,Action=DragDrop,DropCmd=Process Specs''
      
        ''Desc=3D Models,Priv={},Visible={},TagValue=3D Models,FKey=,Param'' +
        ''sSrc=@Default,Action=DragDrop,DropCmd=3D Models''
      
        ''Desc=Other,Priv={},Visible={},TagValue=Other,FKey=,ParamsSrc=@De'' +
        ''fault,Action=DragDrop,DropCmd=Other'')
  end
  object ImageList: TsfDBImageList
    Left = 385
    Top = 39
    Width = 200
    Height = 155
    DataSource = GetThumbnail._Source
    Enabled = False
    TabOrder = 7
    ShowHint = True
    ColumnWidth = 200
    MaxRows = 4
    GraphicMaxHeight = 140
    AltColumnWidth = 0
    AltGraphicMaxHeight = 0
  end
  object ACTIONS_OBJ: TsfCommandButton
    Left = 244
    Top = 5
    Width = 65
    Height = 21
    Caption = ''Actions''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 8
    MoreCommandsSqlId = ''MFI_SFPL_ActionTypeSel''
  end
  object MSG_OBJ: TsfDBEdit
    Left = 318
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
    TabOrder = 9
    Text = ''''
    Caption = ''''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clRed
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object PlanIllBoRSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLANILLUSTRATIONINFODRAG''
    KeyFieldNames = ''EXPLOR_FIELD''
    LinkedControls.Strings = (
      ''BillOfResources~''
      ''BOM_Navigator''
      ''EmbedSelectedIllustration1''
      ''BOM_Navigator~''
      ''ACTIONS_OBJ~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''BOM_LINE_NO~6~Line No~N~N~False~False~True~N~~~~~Default~N~''
      
        ''AUTHOR_ICON~1~ ~N~N~False~False~True~N~~@StdMarkup(AUTHOR_ICON)~'' +
        ''~~Default~N~''
      ''OBJECT_TAG~9~Tag~N~N~False~False~False~N~~~~~Default~N~''
      ''OBJECT_DESC~9~Description~N~N~False~False~False~N~~~~~Default~N~''
      ''OBJECT_REV~4~Rev~N~N~False~False~False~N~~~~~Default~N~''
      ''SOURCE~9~Source~N~N~False~False~False~N~~~~~Default~N~''
      ''STD_PART_NO~10~Std Part;No~N~N~False~False~True~N~~~~~Default~N~''
      
        ''CLASSIFICATION~8~Classification~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      ''STATUS~10~Status~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PART_NO~Edit''
      ''UID_ENTRY_NAME~Edit''
      ''ITEM_SUBTYPE~Edit''
      ''STATUS~Edit''
      ''AUTHOR_ICON~Edit''
      ''OBJECT_DESC~Edit'')
    TestParamValues.Strings = (
      ''PLAN_ID=1''
      ''OPER_KEY=1''
      ''OPER_UPDT_NO=2''
      ''PLND_WORK_LOC=A''
      ''PROGRAM=S''
      ''PLAN_REVISION=1''
      ''PLAN_VERSION=1''
      ''PLAN_ALTERATIONS=0''
      ''ITEM_ID2=1''
      ''MFG_BOM_CHG=1''
      ''BOM_NO=1''
      ''ITEM_ID=1''
      ''PLAN_UPDT_NO=1''
      ''OPER_NO=010'')
    InputUDVId = ''MFI_508EA0FA0A0A44838B280A1EB32C48F5''
    InsertUDVId = ''MFI_508EA0FA0A0A44838B280A1EB32C48F5''
    InsUpdDelUDVId = ''MFI_508EA0FA0A0A44838B280A1EB32C48F5''
    DragEnabledExpr = ''@GetParamSourceValue(PlanIllBoRSelect,''#39''LVL''#39'')=''#39''1''#39
    DropCommands.Strings = (
      
        ''Illustration~MFI_508EA0FA0A0A44838B280A1EB32C48F5~PlanIllBoRSele'' +
        ''ct~InputMatrix~~Default''
      
        ''EmbedMMLink~MFI_508EA0FA0A0A44838B280A1EB32C48F5~PlanIllBoRSelec'' +
        ''t~InputMatrix~~Default'')
    ViewOptions.Strings = (
      ''Line View=MFI_PLANILLUSTRATIONINFODRAGFORLINEVIEW'')
    DataDefinitions.Strings = (
      ''BOM_LINE_NO''
      ''AUTHOR_ICON''
      ''OBJECT_TAG''
      ''OBJECT_DESC''
      ''OBJECT_REV''
      ''SOURCE''
      ''STD_PART_NO''
      ''CLASSIFICATION''
      ''STATUS''
      ''WARNING_MESSAGE'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''PlanIllBoRSelect''
    FilterUpdateSqlId = ''MFI_INSERTCONTEXTFILTER''
    FilterSelectSqlId = ''MFI_SELECTLASTFILTERCONTEXT''
  end
  object SelectImageList: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_Latest_FileAndIllustrationPlanDisplay''
    LinkedControls.Strings = (
      ''Part_Grig''
      ''PartLibraryNavigator~''
      ''Part_Grig~''
      ''EmbedSelectedIllustration~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''@ToolScope,@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      ''OBJECT_TAG~10~Tag~N~N~False~False~False~N~~~~~Default~N~''
      
        ''OBJECT_DESC~12~Description~N~N~False~False~False~N~~~~~Default~N'' +
        ''~''
      ''OBJECT_REV~6~Rev~N~N~False~False~False~N~~~~~Default~N~''
      ''TYPE~8~Type~N~N~False~False~False~N~~~~~Default~N~''
      
        ''CLASSIFICATION~8~Classification~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      ''STATUS~10~Status~N~N~False~False~False~N~~~~~Default~N~'')
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
      ''PART_ACTION~Edit''
      ''STANDARD_PART_FLAG~Edit''
      ''OPTIONAL_FLAG~Edit''
      ''OBJECT_TAG~Edit''
      ''CLASSIFICATION~Edit'')
    PrintTitle = ''Parts''
    TestParamValues.Strings = (
      ''PLND_WORK_LOC=L''
      ''PROGRAM=P''
      ''PLAN_ID=MFI_EC7C2096C052E1D66C6333E6C6A2FE83''
      ''OPER_KEY=276330''
      ''PLAN_VERSION=1''
      ''PLAN_REVISION=1''
      ''PLAN_ALTERATIONS=0''
      ''OPER_NO=10''
      ''PLAN_UPDT_NO=1'')
    InputUDVId = ''MFI_C48705DE29C64574A8AE2B02F0AB07F0''
    InsertUDVId = ''MFI_C48705DE29C64574A8AE2B02F0AB07F0''
    InsUpdDelUDVId = ''MFI_C48705DE29C64574A8AE2B02F0AB07F0''
    DropCommands.Strings = (
      
        ''Illustration~MFI_C48705DE29C64574A8AE2B02F0AB07F0~SelectImageLis'' +
        ''t~InputMatrix~~Default''
      
        ''Drawings~~~InsertControl~~Default~InsertControlName=Illustration'' +
        '';InsertControlCommand=SelectImageList.Drawing''
      
        ''Process Specs~~~InsertControl~~Default~InsertControlName=Illustr'' +
        ''ation;InsertControlCommand=SelectImageList.Process Specs''
      
        ''3D Models~~~InsertControl~~Default~InsertControlName=Illustratio'' +
        ''n;InsertControlCommand=SelectImageList.3D Model''
      
        ''DNC~~~InsertControl~~Default~InsertControlName=Illustration;Inse'' +
        ''rtControlCommand=SelectImageList.DNC''
      
        ''Other~~~InsertControl~~Default~InsertControlName=Illustration;In'' +
        ''sertControlCommand=SelectImageList.Other''
      
        ''EmbedMMLink~MFI_C48705DE29C64574A8AE2B02F0AB07F0~SelectImageList'' +
        ''~InputMatrix~~Default'')
    ViewOptions.Strings = (
      ''All Revisions=MFI_PLG_PLANFILEANDILLUSTRATIONDISPLAY'')
    DataDefinitions.Strings = (
      ''OBJECT_TAG''
      ''OBJECT_DESC''
      ''OBJECT_REV''
      ''FILE_TYPE''
      ''CLASSIFICATION''
      ''STATUS'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''SelectImageList''
    FilterUpdateSqlId = ''MFI_INSERTCONTEXTFILTER''
    FilterSelectSqlId = ''MFI_SELECTLASTFILTERCONTEXT''
  end
  object GetThumbnail: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_SELECTILLUSTRATIONTHUMBNAIL''
    LinkedControls.Strings = (
      ''ImageList~THUMBNAIL'')
    ParamsSQLSourceName = ''PlanIllBoRSelect''
    PublishParams = True
    SelectedFields.Strings = (
      ''THUMBNAIL~0~THUMBNAIL~N~N~False~False~False~N~~~~~Default~N~''
      ''OBJECT_TAG_DISP~0~Tag:~N~N~False~False~False~N~~~~~Default~N~''
      ''OBJECT_REV_DISP~0~Rev:~N~N~False~False~False~N~~~~~Default~N~''
      ''STATUS_DISP~0~Status:~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''OBJECT_TAG_DISP~Edit''
      ''OBJECT_REV_DISP~Edit''
      ''STATUS_DISP~Edit''
      ''THUMBNAIL~Edit'')
    DataDefinitions.Strings = (
      ''THUMBNAIL''
      ''OBJECT_TAG_DISP''
      ''OBJECT_REV_DISP''
      ''STATUS_DISP''
      ''SECURITY_GROUP'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''GetThumbnail''
  end
  object BOMMessage: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_MULTIPLEMBOMSELECT''
    LinkedControls.Strings = (
      ''MSG_OBJ~WARNING_MESSAGE'')
    ParamsSQLSourceName = ''PlanIllBoRSelect''
    SelectedFields.Strings = (
      
        ''WARNING_MESSAGE~0~WARNING_MESSAGE~N~N~WARNING_MESSAGE = ''#39''N''#39'' OR W'' +
        ''ARNING_MESSAGE = ''#39#39''~False~False~N~~~~~Default~N~'')
    TestParamValues.Strings = (
      ''WARNING_MESSAGE=N'')
    DataDefinitions.Strings = (
      ''WARNING_MESSAGE'')
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

