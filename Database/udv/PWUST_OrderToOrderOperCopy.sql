
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6EBD764A795084AEE05387971F0A2777.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6EBD764A795084AEE05387971F0A2777';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderToOrderOperCopy';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Clone of MFI_18105; Defect 805,1044';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.2.0.20180714~2.4';
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
  Caption = ''Work Order - Copy Operations''
  CachedTransaction = True
  TransactionType = ttCached
  IMSqlTransactionName = ''OrderToOrderOperCopyIns''
  IMSqlSourceName = ''OrderToOrderOperCopy''
  IMScanAction = saIMAccept
  IMForceTransactions = ftUpdate
  IMBatchCommit = True
  IMAllowHeaderChanges = True
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = False
  IMBeginExecSqlId = ''MFI_WID_EVENTSAFTERCOPYOPERATION''
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
  object SourcePartNo: TsfDBInputEdit
    Left = 179
    Top = 23
    Width = 171
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
  object _OBJ3: TsfDBInputEdit
    Left = 14
    Top = 23
    Width = 156
    Height = 24
    BorderColor = clBlack
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
    Caption = ''Order No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ4: TsfDBInputEdit
    Left = 301
    Top = 23
    Width = 64
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
    TabOrder = 2
    EntryType = etAlphaNumeric
    Caption = ''Order No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ5: TsfDBInputEdit
    Left = 359
    Top = 23
    Width = 76
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
    Caption = ''Item Rev''
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
    Left = 444
    Top = 23
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
    TabOrder = 4
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
  object _OBJ9: TsfDBInputEdit
    Left = 533
    Top = 23
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
    TabOrder = 5
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
  object OrderToOrderOperCopyIns: TSfSqlTransaction
    InputFieldsSSL.Strings = (
      ''COPY_PL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''TO_OPER_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      '' MaxLength=10''
      ''FROM_ORDER_NO''
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
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''TO_ORDER_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''TO_OPER_TITLE''
      '' Update=STD_OPER_FLAG=''#39''Y''#39
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=255''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
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
      '' Required=False''
      ''EXE_ORDER''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=DISPLAY_SEQUENCE<>''#39''Execution Order''#39
      '' Required=DISPLAY_SEQUENCE=''#39''Execution Order''#39
      '' MaxLength=10''
      ''UCF_ORDER_OPER_VCH2''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=ORDER_TYPE=''#39''REPAIR''#39
      '' Required=False''
      ''UCF_ORDER_OPER_VCH3''
      '' AuxField=Y''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=ORDER_TYPE=''#39''REPAIR''#39
      '' Required=False''
      ''ORDER_ID'')
    InputFieldsEditControlSSL.Strings = (
      ''TO_OPER_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''FROM_ORDER_NO''
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
      ''TO_ORDER_NO''
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
      ''EXE_ORDER''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''COPY_PL''
      '' CtrlType=List''
      ''  Values=Y~N''
      ''UCF_ORDER_OPER_VCH2''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PWUST_sel_sup_net_add_oper''
      ''  ParamsSrc=@ToolScope''
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
      ''    SearchType=stAnyWords''
      ''UCF_ORDER_OPER_VCH3''
      '' CtrlType=Lookup''
      ''  Currency=N''
      ''  SqlId=PWUST_SEL_SUB_NET_ADD_OPER''
      ''  ParamsSrc=@ToolScope''
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
    UpdateSqlId = ''PWUST_OrderToOrderOperCopyIns''
    ParamsSQLSourceName = ''@ToolScope,@AuxParams''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ3~FROM_ORDER_NO''
      ''_OBJ4~TO_ORDER_NO''
      ''SourcePartNo~FROM_PART_NO''
      ''_OBJ5~From_Part_Chg''
      ''_OBJ8~FROM_ITEM_TYPE''
      ''_OBJ9~FROM_ITEM_SUBTYPE'')
    DataDefinitions.Strings = (
      ''COPY_PL=''
      ''TO_OPER_NO=''
      ''FROM_ORDER_NO=''
      ''FROM_OPER_NO=''
      ''TO_ORDER_NO=''
      ''TO_OPER_TITLE=''
      ''EXE_ORDER=''
      ''ORDER_ID''
      ''UCF_ORDER_OPER_VCH2=''
      ''UCF_ORDER_OPER_VCH3='')
  end
  object OrderToOrderOperCopy: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_OrderToOrderOperCopy''
    ParamsSQLSourceName = ''@ToolScope, @ScriptParams, @AUXPARAMS, _OBJ10''
    PublishParams = True
    SelectedFields.Strings = (
      ''FROM_OPER_NO~10~From Oper No~N~N~N~N~N~N~~~~~Default~N~~~''
      ''TO_OPER_NO~10~To Oper No~N~N~N~Y~N~N~~~~~Default~N~~~''
      ''TO_OPER_TITLE~20~To Oper Title~N~N~N~N~N~N~~~~~Default~N~~~''
      ''HAS_PL~1~Has;P/L?~N~N~N~N~N~N~~~~~Default~N~~~''
      ''COPY_PL~1~Copy;P/L?~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''STD_OPER_FLAG~1~Std;Oper?~N~N~False~N~MRO_FLAG=''#39''Y''#39''~N~~~~~Default'' +
        ''~N~~~''
      
        ''EXE_ORDER~0~Oper;Sequence No~N~N~DISPLAY_SEQUENCE<>''#39''Execution Or'' +
        ''der''#39''~DISPLAY_SEQUENCE=''#39''Execution Order''#39''~DISPLAY_SEQUENCE<>''#39''Execu'' +
        ''tion Order''#39''~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH2~4~SUPER NET~N~N~False~True~ORDER_TYPE=''#39''REPAI'' +
        ''R''#39''~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_OPER_VCH3~4~SUBNET~N~N~False~True~ORDER_TYPE=''#39''REPAIR''#39''~'' +
        ''N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''STD_OPER_FLAG~Edit''
      ''UCF_ORDER_OPER_VCH2~Edit'')
    TestParamValues.Strings = (
      ''STARTING_TARGET_OPER=100''
      ''OPER_INCR=5''
      ''plan_id=1742''
      ''plan_version=1''
      ''plan_revision=1''
      ''plan_alterations=0''
      ''oper_no_list=0010''
      ''from_plan_id=1''
      ''from_plan_version=1''
      ''from_plan_revision=2''
      ''from_plan_alterations=2''
      ''from_order_no=123456789''
      ''TARGET_OPER=0010''
      ''INCR_BY=0''
      ''mro_flag=Y''
      ''EXE_ORDER=0'')
    DataDefinitions.Strings = (
      ''FROM_OPER_NO''
      ''TO_OPER_NO''
      ''TO_OPER_TITLE''
      ''HAS_PL''
      ''COPY_PL''
      ''STD_OPER_FLAG''
      ''EXE_ORDER''
      ''UCF_ORDER_OPER_VCH2''
      ''UCF_ORDER_OPER_VCH3'')
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

