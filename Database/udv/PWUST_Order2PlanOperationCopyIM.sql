
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_B3ECF6525402A83DE053EF9F1F0AD942.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_B3ECF6525402A83DE053EF9F1F0AD942';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_Order2PlanOperationCopyIM';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Copy Operation(s) from Order to Plan thru IM (MFI_18383); Defect 1811';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.0.20190207~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 640
  Height = 130
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Copy Order Operations''
  CachedTransaction = True
  TransactionType = ttCached
  IMSqlTransactionName = ''OrderToPlanOperCopyIns''
  IMSqlSourceName = ''OrderToPlanOperCopy''
  IMScanAction = saIMAccept
  IMForceTransactions = ftUpdate
  IMBatchCommit = True
  IMAllowHeaderChanges = True
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  IMEndExecSqlId = ''MFI_FND_EVENTSAFTERCOPYPLANOPERATION''
  UdvControlType = uctInputMatrix
  ParentNavigator = True
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
  object _OBJ1: TsfLabel
    Left = 14
    Top = 35
    Width = 110
    Height = 13
    Caption = ''Copy Operations from:''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlue
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object SourceOrderNo: TsfDBInputEdit
    Left = 14
    Top = 76
    Width = 156
    Height = 24
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
    Caption = ''Order No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object FromPartNo: TsfDBInputEdit
    Left = 179
    Top = 76
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
    Caption = ''Item No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object FromPartChg: TsfDBInputEdit
    Left = 338
    Top = 76
    Width = 76
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
    TabOrder = 2
    EntryType = etAlphaNumeric
    Caption = ''Item Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ6: TsfDBInputEdit
    Left = 423
    Top = 76
    Width = 80
    Height = 25
    BorderColor = clBlack
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
    Caption = ''Item Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ7: TsfDBInputEdit
    Left = 512
    Top = 76
    Width = 100
    Height = 25
    BorderColor = clBlack
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    EntryType = etAlphaNumeric
    Caption = ''Item Subtype''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OrderToPlanOperCopyIns: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''TO_PLAN_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''TO_PLAN_VERSION''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=MRO_FLAG = ''#39''Y''#39
      '' Required=False''
      '' PersistManualValues=Y''
      ''TO_PLAN_REVISION''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''COPY_PL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''TO_PLAN_ALTERATIONS''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''FROM_OPER_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=10''
      '' PersistManualValues=Y''
      ''TO_OPER_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=10''
      '' PersistManualValues=Y''
      ''FROM_ORDER_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''TO_OPER_TITLE''
      '' Update=STD_OPER_FLAG=''#39''Y''#39
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=255''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''TOPARTNO''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MRO_FLAG <> ''#39''Y''#39
      '' Required=False''
      ''TO_ITEM_TYPE''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''TO_ITEM_SUBTYPE''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''EXE_ORDER''
      '' Update=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=0''
      '' PersistManualValues=Y''
      '' Hidden=DISPLAY_SEQUENCE<>''#39''Execution Order''#39
      '' Required=DISPLAY_SEQUENCE=''#39''Execution Order''#39
      '' Insert=False''
      '' MaxLength=10'')
    InputFieldsEditControlSSL.Strings = (
      ''COPY_PL''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''TO_PLAN_VERSION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TO_PLAN_REVISION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TO_PLAN_ALTERATIONS''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_ORDER_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TOPARTNO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TO_ITEM_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TO_ITEM_SUBTYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TO_OPER_TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_OPER_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TO_OPER_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TO_PLAN_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''EXE_ORDER''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    UpdateSqlId = ''PWUST_OrderToPlanOperCopyIns''
    ParamsSQLSourceName = ''@ToolScope,@AuxParams''
    RefreshParams = False
    LinkedControls.Strings = (
      ''SourceOrderNo~FROM_ORDER_NO''
      ''FromPartNo~FROMPARTNO''
      ''FromPartChg~FROM_PART_CHG''
      ''_OBJ6~FROM_ITEM_TYPE''
      ''_OBJ7~FROM_ITEM_SUBTYPE'')
    DataDefinitions.Strings = (
      ''TO_PLAN_ID=''
      ''TO_PLAN_VERSION=''
      ''TO_PLAN_REVISION=''
      ''COPY_PL=''
      ''TO_PLAN_ALTERATIONS=''
      ''FROM_OPER_NO=''
      ''TO_OPER_NO=''
      ''FROM_ORDER_ID=''
      ''TO_OPER_TITLE=''
      ''EXE_ORDER='')
  end
  object OrderToPlanOperCopy: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderToPlanOperCopy''
    ParamsSQLSourceName = ''@AUXPARAMS''
    PublishParams = True
    SelectedFields.Strings = (
      ''FROM_OPER_NO~10~From Oper No~N~N~N~N~N~N~~~~~Default~N~''
      ''TO_OPER_NO~10~To Oper No~N~N~N~Y~N~N~~~~~Default~N~''
      ''TO_OPER_TITLE~20~To Oper Title~N~N~N~N~N~N~~~~~Default~N~''
      ''HAS_PL~1~Has;P/L?~N~N~N~N~N~N~~~~~Default~N~''
      ''COPY_PL~1~Copy;P/L?~N~N~N~N~N~N~~~~~Default~N~''
      ''STD_OPER_FLAG~1~Std;Oper?~N~N~N~N~N~N~~~~~Default~N~''
      
        ''EXE_ORDER~0~Oper;Sequence No~N~N~DISPLAY_SEQUENCE<>''#39''Execution Or'' +
        ''der''#39''~DISPLAY_SEQUENCE=''#39''Execution Order''#39''~DISPLAY_SEQUENCE<>''#39''Execu'' +
        ''tion Order''#39''~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''TARGET_OPER_NO~Edit'')
    TestParamValues.Strings = (
      ''STARTING_TARGET_OPER=''
      ''OPER_INCR=''
      ''oper_no_list=010''
      ''from_order_id=MFI_7A12D2415F295E7FE7ED297FADD7B97''
      ''EXE_ORDER=null'')
    DataDefinitions.Strings = (
      ''FROM_OPER_NO''
      ''TO_OPER_NO''
      ''TO_OPER_TITLE''
      ''HAS_PL''
      ''COPY_PL''
      ''STD_OPER_FLAG''
      ''EXE_ORDER'')
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

