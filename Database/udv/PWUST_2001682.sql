
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_68B2CA983914F154E05387971F0A055A.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_68B2CA983914F154E05387971F0A055A';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_2001682';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Pre Insert of Part/Clone of MFI_2001682/Defect 365';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 310
  AutoScroll = False
  Caption = 
    ''@Lookup(@TransactionType,''#39''Insert:Insert,Update:Create''#39'',''#39''Insert''#39'')'' +
    '' + ''#39'' ''#39''+ @Lookup(@SqlSourceName,''#39''PART:Part,TOOL:Tool,PROCEDURE:Pr'' +
    ''ocedure,MACHINE:Machine,AUDITPROCESS:Procedure''#39'',''#39''Item''#39'') + ''#39'' ''#39''+@L'' +
    ''ookup(@TransactionType,''#39''Update:Revision''#39'',''#39#39'')''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctSingle
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  SizeToEndOfLine = False
  AutoRefresh = arDisabled
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 0
  MaxHeight = 0
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = False
  BottomMargin = -1
  object ItemType: TsfDBInputEdit
    Left = 124
    Top = 154
    Width = 140
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = ''Item Type*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object ItemNo: TsfDBInputEdit
    Left = 125
    Top = 50
    Width = 260
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 0
    EntryType = etAlphaNumeric
    Caption = 
      ''@Lookup(@SqlSourceName,''#39''PART:Part No,TOOL:Tool No,PROCEDURE:Proc'' +
      ''edure No,MACHINE:Machine No,AUDITPROCESS:Procedure No''#39'',''#39''Item No''#39 +
      '')''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object ItemRev: TsfDBInputEdit
    Left = 125
    Top = 85
    Width = 140
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
    EntryType = etAlphaNumeric
    Caption = 
      ''@Lookup(@SqlSourceName,''#39''PART:Part Rev,TOOL:Tool Rev,PROCEDURE:Pr'' +
      ''ocedure Rev,MACHINE:Machine Rev,AUDITPROCESS:Procedure Rev''#39'',''#39''Ite'' +
      ''m Rev''#39'')''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ1: TsfDBInputEdit
    Left = 124
    Top = 119
    Width = 121
    Height = 25
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 2
    EntryType = etAlphaNumeric
    Caption = ''Work Location*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object ItemListPreIns: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''ITEM_NO_NEW''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=50''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''ITEM_REV_NEW''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=Y''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''@SQLSOURCENAME''
      '' Update=@SQLSOURCENAME<>''#39''SELECT_ITEM_TYPE''#39
      '' Insert=@SQLSOURCENAME<>''#39''SELECT_ITEM_TYPE''#39
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=@SQLSOURCENAME<>''#39''SELECT_ITEM_TYPE''#39
      '' Required=@SQLSOURCENAME=''#39''SELECT_ITEM_TYPE''#39
      ''ASGND_WORK_LOC''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=40'')
    InputFieldsEditControlSSL.Strings = (
      ''@SQLSOURCENAME''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_ITEM_TYPE_DEF_SEL''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=Y''
      ''ITEM_REV_NEW''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_NO_NEW''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_PLG_PartNoLookup''
      ''  ParamsSrc=@TOOLSCOPE''
      ''  Validate=N''
      ''  DefaultToSingle=N''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   ITEM_NO_NEW=Item No''
      ''   TITLE=Title''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''ASGND_WORK_LOC''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_Pl_WorkLocSel''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords'')
    InsertSqlId = ''PWUST_ItemListPreIns''
    UpdateSqlId = ''PWUST_ItemListPreIns''
    ParamsSQLSourceName = ''@ToolScope,@AuxParams,PWUST_SEL_USER_WORK_LOC''
    RefreshParams = False
    LinkedControls.Strings = (
      ''ItemType~@SQLSOURCENAME''
      ''ItemRev~ITEM_REV_NEW''
      ''ItemNo~ITEM_NO_NEW''
      ''_OBJ1~ASGND_WORK_LOC'')
    DataDefinitions.Strings = (
      ''ITEM_NO_NEW=''
      ''ITEM_REV_NEW=''
      ''@SQLSOURCENAME=''
      ''ASGND_WORK_LOC='')
  end
  object PWUST_SEL_USER_WORK_LOC: TsfTransactionParamSource
    SelectSqlId = ''PWUST_Pl_WorkLocSel''
  end
  object TOVCController
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

