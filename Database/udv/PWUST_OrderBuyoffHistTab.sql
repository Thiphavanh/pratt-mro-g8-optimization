
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_681804F253EC5C5AE05387971F0A86CE.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_681804F253EC5C5AE05387971F0A86CE';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderBuyoffHistTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202;Defect 694;SMRO_WOE_302';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.2.0.20180714~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 279
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
  BottomMargin = -1
  object _OBJ1: TsfUDVNavigator
    Left = 3
    Top = 4
    Width = 58
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Other commands ''
      ''Filter '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ2: TsfDBGrid
    Left = 3
    Top = 26
    Width = 494
    Height = 157
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object OrderBuyoffHistTabSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_OrderBuyoffHistTab''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''OPER_NO~6~Oper No; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''STEP_NO~6~Step No; ~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''SERIAL_NO~8~Serial No~N~N~ORDER_TYPE=''#39''OVERHAUL''#39''~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''BUYOFF_TITLE~20~Buyoff Title; ~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~~''
      
        ''BUYOFF_STATUS~10~Buyoff Status; ~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''USER_NAME~20~User Name; ~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''UPDT_USERID~12~Update User ID; ~N~N~N~N~N~N~MFI_2869~~~~Default~'' +
        ''N~~~''
      ''TIME_STAMP~18~Update Time; ~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''PERCENT_COMPLETE~5~% Complete; ~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      ''BUYOFF_CERT~14~Required Cert; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''COMMENTS~30~Comments; ~N~N~False~False~False~N~~~~~Default~N~~~''
      ''OPTIONAL_FLAG~5~Optional?; ~N~N~False~N~False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''PERCENT_COMPLETE~Edit''
      ''TIME_STAMP~Edit''
      ''COMMENTS~Edit''
      ''OPTIONAL_FLAG~Edit''
      ''STEP_NO~Edit''
      ''OPER_NO~Edit''
      ''SERIAL_NO~Edit'')
    PrintTitle = ''Buyoffs''
    TestParamValues.Strings = (
      ''ORDER_ID=686D688827FCBC6B9AD35A66A18609D7'')
    OtherCommands.Strings = (
      
        ''Desc=Export to Excel,Priv={},Visible={},TagValue=,FKey=,ParamsSr'' +
        ''c=@Default,Action=Print,Name=File,Params()'')
    DataDefinitions.Strings = (
      ''OPER_NO''
      ''STEP_NO''
      ''SERIAL_NO''
      ''BUYOFF_TITLE''
      ''BUYOFF_STATUS''
      ''USER_NAME''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''PERCENT_COMPLETE''
      ''BUYOFF_CERT''
      ''COMMENTS''
      ''OPTIONAL_FLAG'')
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

