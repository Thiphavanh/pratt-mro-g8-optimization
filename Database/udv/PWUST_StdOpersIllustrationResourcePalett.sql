
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_CFD1EFA7B217DEFBE05387971F0A8245.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_CFD1EFA7B217DEFBE05387971F0A8245';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_StdOpersIllustrationResourcePalett';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 1906';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
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
  object EmbedSelectedIllustration: TsfCommandButton
    Left = 100
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
    TabOrder = 1
    OtherCommands.Strings = (
      
        ''Desc=Insert as Illustration,Priv=''#39''{OBJECT_ID<>''#39#39#39#39''}''#39'',Visible={},'' +
        ''TagValue=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=''
      
        ''Desc=Insert as Link,Priv=''#39''{OBJECT_ID<>''#39#39#39#39''}''#39'',Visible={},TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=DragDrop,DropCmd=EmbedMMLink'')
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
    Width = 77
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''View ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 2
  end
  object _OBJ2: TsfCommandButton
    Left = 275
    Top = 5
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
    TabOrder = 3
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
  object SelectImageList: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_PLG_Latest_FileAndIllustrationStdOperDisplay''
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
      ''OPTIONAL_FLAG~Edit'')
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

