
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_B8B9DD6972C2B19BE05387971F0A86D7.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_B8B9DD6972C2B19BE05387971F0A86D7';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_SFOR_SfwidOrderSub_REPAIR';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 1842';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 800
  Height = 390
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = 0
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
  object SFOR_SfwidOrderSubject: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SfwidOrderSubject''
    DeleteSqlId = ''MFI_WID_OrderSubjectDel''
    KeyFieldNames = ''subject_no''
    LinkedControls.Strings = (
      ''_OBJ3~''
      ''_OBJ7~''
      ''_OBJ8~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''SFOR_SFWID_ORDER_INFO''
    PublishParams = True
    SelectedFields.Strings = (

        ''INCLUDED_FLAG~10~Included/Excluded; ~N~N~N~N~N~N~~~~~Default~N~~'' +
        ''~''
      ''STANDARD_FLAG~6~Standard?; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''SUBJECT_STATUS~7~Task Group;Status~N~N~N~N~N~N~~~~~Default~N~~~''
      ''SUBJECT_NO~7~Task Group No; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''SUBJECT_REV~7~Task Group Rev; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''TITLE~30~Task Group Title;~N~N~N~N~N~N~~~~~Default~N~~~''
      ''AUTHORITY~30~Authority~N~N~False~False~False~N~~~~~Default~N~~~''
      ''DISC_ID~6~Disc ID; ~N~N~False~False~False~N~~~~~Default~N~~~''
      ''NOTES~30~Notes; ~N~N~False~False~False~N~~~~~Default~N~~~''

        ''DISC_LINE_NO~5~Line No; ~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''

        ''UPDT_USERID~10~Update UserID; ~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~~''

        ''TIME_STAMP~18~Update Time; ~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''SUBJECT_NO~Edit''
      ''SUBJECT_REV~Edit''
      ''INCLUDED_FLAG~Edit'')
    DeleteConfirmationMsg =
      ''Deleting will unassociate the operations from the Task group. Do'' +
      '' you want to continue ?''
    DataDefinitions.Strings = (
      ''INCLUDED_FLAG''
      ''STANDARD_FLAG''
      ''SUBJECT_STATUS''
      ''SUBJECT_NO''
      ''SUBJECT_REV''
      ''TITLE''
      ''NOTES''
      ''DISC_ID''
      ''DISC_LINE_NO''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object SFOR_SfwidOperSubject: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SfwidOperSubject''
    LinkedControls.Strings = (
      ''_OBJ5~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''SFOR_SfwidOrderSubject''
    PublishParams = True
    SelectedFields.Strings = (

        ''INCLUDED_FLAG~10~Included/Excluded; ~N~N~False~False~False~N~~~~'' +
        ''~Default~N~~''
      ''STANDARD_FLAG~6~Standard?; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''OPER_NO~6~Oper No; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''OPER_TITLE~20~Oper Title; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''OPER_STATUS~15~Oper Status; ~N~N~N~N~N~N~~~~~Default~N~~''

        ''UPDT_USERID~20~Update UserID; ~N~N~N~N~N~N~MFI_2869~~~~Default~N'' +
        ''~~''
      ''TIME_STAMP~18~Update Time; ~N~N~N~N~N~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''INCLUDED~Edit''
      ''STANDARD_FLAG~Edit''
      ''INCLUDED_FLAG~Edit''
      ''OPER_STATUS~Edit'')
    DataDefinitions.Strings = (
      ''INCLUDED_FLAG''
      ''STANDARD_FLAG''
      ''OPER_NO''
      ''OPER_TITLE''
      ''OPER_STATUS''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object SFOR_SFWID_ORDER_INFO: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SFWID_ORDER_INFO''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_ID~0~ORDER_ID~N~N~N~N~N~N~~~~~Default~''
      ''PLAN_ID~0~PLAN_ID~N~N~N~N~N~N~~~~~Default~''
      ''PLAN_REVISION~0~PLAN_REVISION~N~N~N~N~N~N~~~~~Default~''
      ''PLAN_UPDT_NO~0~PLAN_UPDT_NO~N~N~N~N~N~N~~~~~Default~''
      ''ORDER_CUST_ID~0~ORDER_CUST_ID~N~N~N~N~N~N~~~~~Default~''
      ''PART_NO~0~PART_NO~N~N~False~False~False~N~~~~~Default~'')
    SelectedFieldsEditControl.Strings = (
      ''MRO_PART_NO~Edit''
      ''PLAN_ID~Edit''
      ''PLAN_REVISION~Edit''
      ''PLAN_UPDT_NO~Edit''
      ''MODULE_CODE~Edit''
      ''ENGINE_TYPE~Edit''
      ''ENGINE_MODEL~Edit''
      ''ACT_NO~Edit''
      ''ORDER_CUST_ID~Edit'')
    DataDefinitions.Strings = (
      ''ORDER_ID''
      ''PLAN_ID''
      ''PLAN_REVISION''
      ''PLAN_UPDT_NO''
      ''ORDER_CUST_ID'')
    ConsolidatedQuery = False
  end
  object _OBJ1: TsfDBGrid
    Left = 2
    Top = 28
    Width = 467
    Height = 150
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
    Highlighting.Strings = (
      ''@Row~Green~Bold~INCLUDED_FLAG=''#39''INCLUDED''#39'' and STANDARD_FLAG=''#39''Y''#39
      ''@Row~Blue~Bold~INCLUDED_FLAG=''#39''INCLUDED''#39'' and STANDARD_FLAG =''#39''N''#39
      ''@Row~Red~Bold~SUBJECT_STATUS = ''#39''HOLD''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ2: TsfLabel
    Left = 155
    Top = 11
    Width = 59
    Height = 13
    Caption = ''Task Groups''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ3: TsfUDVNavigator
    Left = 2
    Top = 6
    Width = 153
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Delete ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object _OBJ4: TsfDBGrid
    Left = 2
    Top = 210
    Width = 467
    Height = 150
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 2
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Highlighting.Strings = (

        ''@Row~Green~Default~INCLUDED_FLAG = ''#39''INCLUDED''#39'' and OPER_STATUS=''#39''C'' +
        ''LOSE''#39
      ''@Row~Blue~Bold~INCLUDED_FLAG = ''#39''INCLUDED''#39'' and STANDARD_FLAG=''#39''N''#39
      ''@Row~Green~Bold~INCLUDED_FLAG=''#39''INCLUDED''#39'' and STANDARD_FLAG=''#39''Y''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ5: TsfUDVNavigator
    Left = 2
    Top = 188
    Width = 134
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 3
    TabStop = True
  end
  object _OBJ6: TsfLabel
    Left = 140
    Top = 190
    Width = 105
    Height = 13
    Caption = ''Task Group Operation''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ7: TsfCommandButton
    Left = 225
    Top = 8
    Width = 75
    Height = 18
    Hint = ''Include''
    Caption = ''Include''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    TabStop = True
    OtherCommands.Strings = (

        ''Desc=Include,Priv=''#39''INCLUDED_FLAG=''#39#39''EXCLUDED''#39#39#39'',Visible=,TagValue'' +
        ''=,FKey=,Action=UDV,UDVType=GroupInsert,FieldName=subject_no,''#39''Del'' +
        ''im=,''#39'',UDVID=SFOR_4703'')
  end
  object _OBJ8: TsfCommandButton
    Left = 305
    Top = 8
    Width = 83
    Height = 18
    Hint = ''Exclude''
    Caption = ''Exclude''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    TabStop = True
    OtherCommands.Strings = (

        ''Desc=Exclude,Priv=''#39''{INCLUDED_FLAG = ''#39#39''INCLUDED''#39#39''}''#39'',Visible={},Ta'' +
        ''gValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=GroupInsert,'' +
        ''FieldName=subject_no,''#39''Delim=,''#39'',UDVID=SFOR_4704'')
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

