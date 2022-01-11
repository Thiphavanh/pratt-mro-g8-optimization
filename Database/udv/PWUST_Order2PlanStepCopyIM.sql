
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_B61FEAEACB8B92E7E053EF9F1F0A2839.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_B61FEAEACB8B92E7E053EF9F1F0A2839';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_Order2PlanStepCopyIM';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Copy of Copy Step(s) from Order to Plan thru IM (MFI_18677); Defect 1819';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.0.20190207~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 650
  Height = 130
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''Copy Order Steps''
  CachedTransaction = True
  TransactionType = ttCached
  IMSqlTransactionName = ''OrderToPlanStepCopyIns''
  IMSqlSourceName = ''OrderToPlanStepCopy''
  IMScanAction = saIMAccept
  IMForceTransactions = ftUpdate
  IMBatchCommit = True
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  IMEndExecSqlId = ''MFI_FND_EVENTSAFTERCOPYSTEP''
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
    Left = 10
    Top = 12
    Width = 84
    Height = 13
    Caption = ''Copy Steps from:''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlue
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object SourceOrderNo: TsfDBInputEdit
    Left = 10
    Top = 47
    Width = 159
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
    Left = 176
    Top = 47
    Width = 120
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
  object PartChg: TsfDBInputEdit
    Left = 301
    Top = 47
    Width = 77
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
  object _OBJ3: TsfDBInputEdit
    Left = 383
    Top = 47
    Width = 66
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
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = ''Item Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ5: TsfDBInputEdit
    Left = 556
    Top = 47
    Width = 70
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
    TabOrder = 5
    EntryType = etAlphaNumeric
    Caption = ''Oper No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ8: TsfDBInputEdit
    Left = 452
    Top = 47
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
  object OrderToPlanStepCopyIns: TSfSqlTransaction
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
      '' Hidden=N''
      '' Required=N''
      ''TO_PLAN_ALTERATIONS''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''FROM_OPER_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=10''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''TO_OPER_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=N''
      '' Required=N''
      ''FROM_STEP_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' MaxLength=10''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''TO_STEP_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=10''
      '' CharCase=Y''
      '' Hidden=N''
      '' Required=Y''
      ''FROM_ORDER_ID''
      ''TO_STEP_TITLE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=255''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''COPY_PL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''EXE_ORDER''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True''
      ''PLAN_NUMBER''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MRO_FLAG <> ''#39''Y''#39
      '' Required=False''
      ''TOPARTNO''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=MRO_FLAG = ''#39''Y''#39
      '' Required=False''
      ''FROM_ITEM_TYPE''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''FROM_ITEM_SUBTYPE''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
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
      ''FROM_OPER_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_STEP_NO''
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
      ''TO_STEP_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''COPY_PL''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''EXE_ORDER''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_NUMBER''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''TO_PLAN_VERSION''
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
      ''FROM_ITEM_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_ITEM_SUBTYPE''
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
      ''TO_STEP_TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N'')
    UpdateSqlId = ''PWUST_OrderToPlanStepCopyIns''
    ParamsSQLSourceName = ''@ToolScope,@AuxParams''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ5~FROM_OPER_NO''
      ''SourceOrderNo~FROM_ORDER_NO''
      ''FromPartNo~FROMPARTNO''
      ''PartChg~FROM_PART_CHG''
      ''_OBJ3~FROM_ITEM_TYPE''
      ''_OBJ8~FROM_ITEM_SUBTYPE'')
    DataDefinitions.Strings = (
      ''TO_PLAN_ID=''
      ''TO_PLAN_VERSION=''
      ''TO_PLAN_REVISION=''
      ''TO_PLAN_ALTERATIONS=''
      ''FROM_OPER_NO=''
      ''TO_OPER_NO=''
      ''FROM_STEP_NO=''
      ''TO_STEP_NO=''
      ''FROM_ORDER_ID''
      ''TO_STEP_TITLE=''
      ''COPY_PL=''
      ''EXE_ORDER='')
  end
  object OrderToPlanStepCopy: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderToPlanStepCopy''
    ParamsSQLSourceName = ''@AUXPARAMS''
    PublishParams = True
    SelectedFields.Strings = (
      ''FROM_STEP_NO~10~From;Step No~N~N~N~N~N~N~~~~~Default~N~''
      ''TO_STEP_NO~10~To Step No~N~N~N~Y~N~N~~~~~Default~N~''
      ''TO_STEP_TITLE~20~To Step Title~N~N~N~N~N~N~~~~~Default~N~''
      ''HAS_PARTS~1~Has;P/L?~N~N~False~N~False~N~~~~~Default~N~''
      ''COPY_PL~1~Copy;P/L?~N~N~False~N~False~N~~~~~Default~N~''
      ''STD_OPER_FLAG~1~Std Oper?~N~N~False~N~False~N~~~~~Default~N~''
      
        ''EXE_ORDER~5~Execution;Order~N~N~False~False~False~N~~~~~Default~'' +
        ''N~'')
    SelectedFieldsEditControl.Strings = (
      ''STEP_TITLE~Edit''
      ''TO_STEP_NO~Edit'')
    TestParamValues.Strings = (
      ''plan_id=MFI_B6824283FC739FE2AC5E8E8833ACEF74''
      ''plan_version=1''
      ''plan_revision=1''
      ''plan_alterations=0''
      ''from_plan_id=''
      ''from_plan_version=''
      ''from_plan_revision=''
      ''from_plan_alterations=''
      ''STARTING_TARGET_STEP=''
      ''STEP_INCR=''
      ''step_no_list=1''
      ''from_oper_no=10''
      ''from_order_id=MFI_D111F861BF2C1074299A79F2EA99BB02'')
    DataDefinitions.Strings = (
      ''FROM_STEP_NO''
      ''TO_STEP_NO''
      ''TO_STEP_TITLE''
      ''HAS_PARTS''
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

