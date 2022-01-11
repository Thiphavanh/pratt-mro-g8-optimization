
set define off
prompt ---------------------------------------;
prompt Executing ... PWMROI_C2BA875A20034C13B32F6A57EF75D7FF.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWMROI_C2BA875A20034C13B32F6A57EF75D7FF';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWMROI_ORDER_SUB_AUTH';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='defect 1890,1924';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 900
  Height = 300
  HelpContext = ''''
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
  object _OBJ1: TsfDBGrid
    Left = 8
    Top = 26
    Width = 882
    Height = 255
    TabStop = False
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
  object _OBJ2: TsfUDVNavigator
    Left = 17
    Top = 3
    Width = 210
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''InsertUpdateDelete ''
      ''Refresh ''
      ''Filter ''
      ''Filter History '')
    ShowHint = True
    TabOrder = 1
  end
  object ordersubjauthsel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWMROI_ORDER_SUBJ_SEL''
    DeleteSqlId = ''PWMROI_DELETE_ORDERSUBJ_AUTH''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''SUBJECT_NO~6~Task Group~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''SUBJECT_REV~4~Task Group Rev~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''SUBJECT_DESCRIPTION~12~Task Desc~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''AUTHORITY_TYPE~6~Auth Type~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''AUTHORITY_DETAIL~15~Auth Detail~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~~''
      ''MANUAL_NO~10~Manual No~N~N~False~False~False~N~~~~~Default~N~~~''
      ''MANUAL_REV~4~Manual Rev~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''MANUAL_PROVIDER~10~Manual Provider~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''MANUAL_TYPE~8~Manual Type~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~~''
      
        ''MANUAL_SECTION~10~Manual Section~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''MANUAL_RELEASE_DATE~10~Release Date~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      ''SB_PART_NO~8~SB Part No~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''AUTHORITY_REV~8~Authority Rev~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''AUTHORITY_REV_DATE~10~Authority Rev Date~N~N~False~False~False~N'' +
        ''~~~~~Default~N~~~''
      ''AUTHORITY~10~Auth Notes~N~N~False~False~True~N~~~~~Default~N~~~''
      
        ''ARC_AUTHORITY~12~Arc Authority~N~N~False~False~True~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UPDT_USERID~20~Update User~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''TIME_STAMP~20~Time Stamp~N~N~False~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''LAST_ACTION~10~Last Action~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      ''ORDER_ID~0~ORDER_ID~N~N~True~False~True~N~~~~~Default~N~~~''
      ''SEQNO~0~SEQNO~N~N~True~False~True~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''SB_PART_NO~Edit''
      ''AUTHORITY_REV~Edit''
      ''AUTHORITY_REV_DATE~Edit''
      ''UPDT_USERID~Edit''
      ''AUTHORITY~Edit''
      ''SUBJECT_REV~Edit''
      ''AUTHORITY_DETAIL~Edit''
      ''MANUAL_NO~Edit''
      ''MANUAL_REV~Edit''
      ''MANUAL_PROVIDER~Edit''
      ''SUBJECT_NO~Edit'')
    TestParamValues.Strings = (
      ''ORDER_ID=PWMROI_41B39FC2B76212FAD6DAB220C03842B8'')
    InputUDVId = ''PWMROI_AB44B948E2F144CF96FFD0009DA61C88''
    InsertUDVId = ''PWMROI_AB44B948E2F144CF96FFD0009DA61C88''
    InsUpdDelUDVId = ''PWMROI_AB44B948E2F144CF96FFD0009DA61C88''
    DataDefinitions.Strings = (
      ''SUBJECT_NO''
      ''SUBJECT_REV''
      ''SUBJECT_DESCRIPTION''
      ''AUTHORITY_TYPE''
      ''AUTHORITY_DETAIL''
      ''MANUAL_NO''
      ''MANUAL_REV''
      ''MANUAL_PROVIDER''
      ''MANUAL_TYPE''
      ''MANUAL_SECTION''
      ''SB_PART_NO''
      ''AUTHORITY_REV''
      ''AUTHORITY_REV_DATE''
      ''AUTHORITY''
      ''ARC_AUTHORITY''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''LAST_ACTION''
      ''ORDER_ID''
      ''SEQNO'')
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

