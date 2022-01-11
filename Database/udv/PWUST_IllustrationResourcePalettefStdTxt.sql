
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_CFFF34EBA5DB5293E05387971F0A1C5D.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_CFFF34EBA5DB5293E05387971F0A1C5D';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_IllustrationResourcePalettefStdTxt';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 1906';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFFND';
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
  object IllustrationNavigator: TsfUDVNavigator
    Left = 4
    Top = -3
    Width = 77
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''View ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 0
  end
  object IllustrationGrid: TsfDBGrid
    Left = 4
    Top = 20
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
  object Library: TsfLabel
    Left = 5
    Top = 12
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
  object EmbedSelectedIllustration: TsfCommandButton
    Left = 151
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
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Insert as Illustration,Priv=''#39''{MM_OBJECT_ID<>''#39#39#39#39''}''#39'',Visible='' +
        ''{},TagValue=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=''
      
        ''Desc=Insert as Link,Priv=''#39''{MM_OBJECT_ID<>''#39#39#39#39''}''#39'',Visible={},TagVa'' +
        ''lue=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=EmbedMMLin'' +
        ''k'')
  end
  object SelectImageList: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_LatestFileAndIllustrationDisplayStdTxt''
    LinkedControls.Strings = (
      ''IllustrationGrid~''
      ''IllustrationNavigator~''
      ''EmbedSelectedIllustration''
      ''EmbedSelectedIllustration~'')
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
      ''STATUS~Edit''
      ''OBJECT_TAG~Edit''
      ''TYPE~Edit'')
    InputUDVId = ''MFI_3A9D63C0969F4786B0E967E9C189DAAF''
    InsertUDVId = ''MFI_3A9D63C0969F4786B0E967E9C189DAAF''
    InsUpdDelUDVId = ''MFI_3A9D63C0969F4786B0E967E9C189DAAF''
    DropCommands.Strings = (
      
        ''Illustration~MFI_3A9D63C0969F4786B0E967E9C189DAAF~SelectImageLis'' +
        ''t~InputMatrix~~Default''
      
        ''EmbedMMLink~MFI_3A9D63C0969F4786B0E967E9C189DAAF~SelectImageList'' +
        ''~InputMatrix~~Default'')
    ViewOptions.Strings = (
      ''ALL Revisions=MFI_PLG_FILEANDILLUSTRATIONDISPLAYSTDTEXT'')
    DataDefinitions.Strings = (
      ''OBJECT_TAG''
      ''OBJECT_DESC''
      ''OBJECT_REV''
      ''TYPE''
      ''CLASSIFICATION''
      ''STATUS'')
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

