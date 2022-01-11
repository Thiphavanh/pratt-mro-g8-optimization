
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5667B5C888A0CF9BE05387971F0A3BFC.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5667B5C888A0CF9BE05387971F0A3BFC';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_MigInputMatrix';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_CV_202';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 700
  Height = 250
  AutoScroll = False
  Caption = ''Migrate Plans from MRO G5 to MRO G8''
  CachedTransaction = True
  TransactionType = ttCached
  IMSqlTransactionName = ''_OBJ2''
  IMSqlSourceName = ''_OBJ1''
  IMScanAction = saIMAccept
  IMForceTransactions = ftInsert
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = True
  UdvControlType = uctInputMatrix
  ParentNavigator = True
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  SizeToEndOfLine = False
  AutoRefresh = arDefault
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 700
  MaxHeight = 250
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = False
  BottomMargin = -1
  object _OBJ3: TsfDBInputEdit
    Left = 18
    Top = 21
    Width = 225
    Height = 25
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
    Caption = ''Release Package''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ4: TsfDBInputEdit
    Left = 250
    Top = 21
    Width = 225
    Height = 25
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
    Caption = ''Description''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ1: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_MigMatrixListData''
    ParamsSQLSourceName = ''@Toolscope''
    SelectedFields.Strings = (
      ''PLAN_ID~6~PLAN_ID~N~N~False~False~False~N~~~~~Default~N~~''
      ''PART_NO~15~PART_NO~N~N~False~False~False~N~~~~~Default~N~~''
      ''PLAN_TITLE~15~PLAN_TITLE~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''ENGINE_TYPE~10~ENGINE_TYPE~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~''
      
        ''MODULE_CODE~10~MODULE_CODE~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~''
      ''PLAN_TYPE~10~PLAN_TYPE~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''EXPORT_JURISDICTION~10~EXPORT_JURISDICTION~N~N~False~False~False'' +
        ''~N~~~~~Default~N~~''
      
        ''EXPORT_CLASSIFICATION~10~EXPORT_CLASSIFICATION~N~N~False~False~F'' +
        ''alse~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''MODEL~Edit''
      ''PLG_GROUP~Edit''
      ''PART_TYPE~Edit''
      ''ENGINE_TYPE~Edit''
      ''EXPORT_CLASSIFICATION~Edit''
      ''PART_NO~Edit''
      ''EXPORT_JURISDICTION~Edit'')
    DataDefinitions.Strings = (
      ''PLAN_ID''
      ''PLAN_TITLE''
      ''ENGINE_TYPE''
      ''MODULE_CODE''
      ''PLAN_TYPE''
      ''EXPORT_JURISDICTION''
      ''EXPORT_CLASSIFICATION'')
    ConsolidatedQuery = False
  end
  object _OBJ2: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''PLAN_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''CHANGE_DOC_TYPE''
      ''CHANGE_DOC_ID'')
    InputFieldsEditControlSSL.Strings = (
      ''PLAN_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    InsertSqlId = ''PWUST_PLAN_MIG_EXEC_MATRIX''
    UpdateSqlId = ''PWUST_PLAN_MIG_EXEC_MATRIX''
    ParamsSQLSourceName = ''@ToolScope,_OBJ1''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ3~CHANGE_DOC_ID''
      ''_OBJ4~PWP_DESC'')
    DataDefinitions.Strings = (
      ''PLAN_ID=''
      ''CHANGE_DOC_TYPE''
      ''CHANGE_DOC_ID'')
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

