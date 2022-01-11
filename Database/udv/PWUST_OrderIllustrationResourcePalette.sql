
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_CFD1F55C38D5DF4AE05387971F0A36A0.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_CFD1F55C38D5DF4AE05387971F0A36A0';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderIllustrationResourcePalette';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 1906';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 300
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
  object billNavigator: TsfUDVNavigator
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
    TabOrder = 0
  end
  object BillOfResourcesGrid: TsfDBGrid
    Left = 4
    Top = 45
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
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object IllustrationNavigator: TsfUDVNavigator
    Left = 4
    Top = 210
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
  object IllustrationGrid: TsfDBGrid
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
    TabOrder = 3
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
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
  object EmbedSelectIllustration: TsfCommandButton
    Left = 71
    Top = 5
    Width = 150
    Height = 21
    Hint = ''Illustration''
    Caption = ''Embed Selected Illustration''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Insert as Illustration,Priv=''#39''{OBJECT_ID<>''#39#39#39#39''}''#39'',Visible={},'' +
        ''TagValue=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=''
      
        ''Desc=Insert as Link,Priv=''#39''{OBJECT_ID<>''#39#39#39#39''}''#39'',Visible={},TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=EmbedMMLink'')
  end
  object embedselectedIllustration1: TsfCommandButton
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
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Insert as Illustration,Priv=''#39''{OBJECT_ID<>''#39#39#39#39''}''#39'',Visible={},'' +
        ''TagValue=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=''
      
        ''Desc=Insert as Link,Priv=''#39''{OBJECT_ID<>''#39#39#39#39''}''#39'',Visible={},TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=EmbedMMLink'')
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
  object MSG_OBJ: TsfDBEdit
    Left = 236
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
    TabOrder = 7
    Text = ''''
    Caption = ''''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clRed
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object IllustrationBillOfResource: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_SELECTORDERILLUSTRATIONINFO''
    LinkedControls.Strings = (
      ''billNavigator~''
      ''BillOfResourcesGrid~''
      ''EmbedSelectIllustration~'')
    ParamsSQLSourceName = ''@ToolScope,@CURRENTMARKER''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''AUTHOR_ICON~1~ ~N~N~False~False~True~N~~@StdMarkup(AUTHOR_ICON)~'' +
        ''~~Default~N~''
      ''OBJECT_TAG~10~Tag~N~N~False~False~False~N~~~~~Default~N~''
      
        ''OBJECT_DESC~10~Description~N~N~False~False~False~N~~~~~Default~N'' +
        ''~''
      ''OBJECT_REV~6~Rev~N~N~False~False~False~N~~~~~Default~N~''
      ''SOURCE~8~Source~N~N~False~False~False~N~~~~~Default~N~''
      
        ''CLASSIFICATION~10~Classification~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~''
      ''STATUS~8~Status~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''CLASSIFICATION~Edit''
      ''AUTHOR_ICON~Edit'')
    TestParamValues.Strings = (
      ''Order_Id=MFI_F43EC645E006938A3732411298C091FE''
      ''OPER_NO=10'')
    InputUDVId = ''MFI_30C1AEC78A974523AF2780E934485928''
    InsertUDVId = ''MFI_30C1AEC78A974523AF2780E934485928''
    InsUpdDelUDVId = ''MFI_30C1AEC78A974523AF2780E934485928''
    DropCommands.Strings = (
      
        ''Illustration~MFI_30C1AEC78A974523AF2780E934485928~IllustrationBi'' +
        ''llOfResource~InputMatrix~~Default''
      
        ''EmbedMMLink~MFI_30C1AEC78A974523AF2780E934485928~IllustrationBil'' +
        ''lOfResources~InputMatrix~~Default'')
    DataDefinitions.Strings = (
      ''AUTHOR_ICON''
      ''OBJECT_TAG''
      ''OBJECT_DESC''
      ''OBJECT_REV''
      ''SOURCE''
      ''CLASSIFICATION''
      ''STATUS'')
    ConsolidatedQuery = False
    FilterUpdateSqlId = ''MFI_INSERTORDERCONTEXTFILTER''
    FilterSelectSqlId = ''MFI_SELECTORDERLASTFILTERCONTEXT''
  end
  object SelectImageList: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_Latest_FileAndIllustrationOrderDisplay''
    LinkedControls.Strings = (
      ''IllustrationGrid~''
      ''IllustrationNavigator~''
      ''embedselectedIllustration1~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''@ToolScope,@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      ''OBJECT_TAG~10~Tag~N~N~False~False~False~N~~~~~Default~N~''
      
        ''OBJECT_DESC~10~Description~N~N~False~False~False~N~~~~~Default~N'' +
        ''~''
      ''OBJECT_REV~6~Rev~N~N~False~False~False~N~~~~~Default~N~''
      ''TYPE~8~Type~N~N~False~False~False~N~~~~~Default~N~''
      
        ''CLASSIFICATION~10~Classification ~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      ''STATUS~8~Status~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''CLASSIFICATION~Edit''
      ''TYPE~Edit''
      ''OBJECT_TAG~Edit'')
    InputUDVId = ''MFI_B47AA9C3B2074C0D94CDDBD2B2275A74''
    InsertUDVId = ''MFI_B47AA9C3B2074C0D94CDDBD2B2275A74''
    InsUpdDelUDVId = ''MFI_B47AA9C3B2074C0D94CDDBD2B2275A74''
    DropCommands.Strings = (
      
        ''Illustration~MFI_B47AA9C3B2074C0D94CDDBD2B2275A74~SelectImageLis'' +
        ''t~InputMatrix~~Default''
      
        ''Drawings~~~InsertControl~~Default~InsertControlName=Illustration'' +
        '';InsertControlCommand=SelectImageList.Drawing''
      
        ''Process Specs~~~InsertControl~~Default~InsertControlName=Illustr'' +
        ''ation;InsertControlCommand=SelectImageList.Process Spec''
      
        ''3D Models~~~InsertControl~~Default~InsertControlName=Illustratio'' +
        ''n;InsertControlCommand=SelectImageList.3D Model''
      
        ''DNC~~~InsertControl~~Default~InsertControlName=Illustration;Inse'' +
        ''rtControlCommand=SelectImageList.DNC''
      
        ''Other~~~InsertControl~~Default~InsertControlName=Illustration;In'' +
        ''sertControlCommand=SelectImageList.Other''
      
        ''EmbedMMLink~MFI_B47AA9C3B2074C0D94CDDBD2B2275A74~SelectImageList'' +
        ''~InputMatrix~~Default'')
    ViewOptions.Strings = (
      ''ALL Revisions=MFI_PLG_FILEANDILLUSTRATIONDISPLAY'')
    DataDefinitions.Strings = (
      ''OBJECT_TAG''
      ''OBJECT_DESC''
      ''OBJECT_REV''
      ''TYPE''
      ''CLASSIFICATION''
      ''STATUS'')
    ConsolidatedQuery = False
    FilterUpdateSqlId = ''MFI_INSERTORDERCONTEXTFILTER''
    FilterSelectSqlId = ''MFI_SELECTORDERLASTFILTERCONTEXT''
  end
  object BOMMessage: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_MultipleMBOMSelect''
    LinkedControls.Strings = (
      ''MSG_OBJ~WARNING_MESSAGE'')
    ParamsSQLSourceName = ''IllustrationBillOfResource''
    SelectedFields.Strings = (
      
        ''WARNING_MESSAGE~0~WARNING_MESSAGE~N~N~WARNING_MESSAGE = ''#39''N''#39''~Fals'' +
        ''e~False~N~~~~~Default~N~'')
    TestParamValues.Strings = (
      ''WARNING_MESSAGE=N'')
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

