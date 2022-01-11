
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_59A40D3A7EF493FCE05387971F0A9803.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_59A40D3A7EF493FCE05387971F0A9803';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_SFOR_SfplPlanSub_UnObs';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_PLG_205,1849';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 696
  Height = 104
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Un-Obsolete Task Group?''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = 0
  IMPopulateFromParams = False
  UdvControlType = uctSingle
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
  object _OBJ2: TsfDBInputEdit
    Left = 109
    Top = 38
    Width = 150
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 0
    EntryType = etAlphaNumeric
    Caption = ''Task Group No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object SFOR_SfplPlanSub_Obs: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''PLAN_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''PLAN_UPDT_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''SUBJECT_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''SUBJECT_REV''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N'')
    InputFieldsEditControlSSL.Strings = (
      ''PLAN_UPDT_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''PLAN_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''SUBJECT_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''SUBJECT_REV''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N'')
    UpdateSqlId = ''PWUST_SFOR_SfplPlanSub_UnObs''
    ParamsSQLSourceName = ''PWUST_SFOR_SfplPlanSubjectSel''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ2~SUBJECT_NO''
      ''_OBJ3~SUBJECT_REV'')
    RefreshedSQLSourceName = 
      ''SFOR_SfplPlanSubjectOperSel, PWUST_TaskGroupWorkScopeSel, PWUST_'' +
      ''TaskGroupCustomerSel, PWUST_TaskGroupCustomerExceptionSel, PWUST'' +
      ''_TaskGroupSuperiorNetworkSel, PWUST_TaskGroupSubNetworkSel''
    DataDefinitions.Strings = (
      ''PLAN_ID=''
      ''PLAN_UPDT_NO=''
      ''SUBJECT_NO=''
      ''SUBJECT_REV='')
  end
  object _OBJ3: TsfDBInputEdit
    Left = 109
    Top = 68
    Width = 150
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
    EntryType = etAlphaNumeric
    Caption = ''Task Group Rev''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
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

