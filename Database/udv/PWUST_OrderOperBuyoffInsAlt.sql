
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6A394C0A830D07FBE05387971F0A7014.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6A394C0A830D07FBE05387971F0A7014';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperBuyoffInsAlt';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202;Defect 305;Defect 697';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 881
  Height = 397
  AutoScroll = False
  Caption = 
    ''@Lookup(@TransactionType,''#39''InsUpdDel:Insert/Update/Delete Buyoffs'' +
    '',Insert:Insert Buyoffs,Update:Update Buyoffs,InputMatrix:Insert '' +
    ''Buyoffs''#39'',''#39''Insert/Update/Delete Buyoffs''#39'')''
  TransactionType = ttCached
  IMSqlTransactionName = ''OrderOperBuyoffIns''
  IMSqlSourceName = ''WIDOperBuy_ALT''
  IMScanAction = saIMAccept
  IMForceTransactions = ftUpdate
  IMBatchCommit = True
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMNoSelectionLoadsAllExpr = 
    ''@Lookup(@TransactionType,''#39''InsUpdDel:true,InputMatrix:false''#39'',''#39''fal'' +
    ''se''#39'')''
  IMFixedColCount = -1
  IMPopulateFromParams = True
  IMDeleteSqlId = ''MFI_WID_OrderOperBuyoffDelInputMatrix''
  IMEndExecSqlId = ''MFI_WID_CheckOrderBuyoff''
  IMPopulateFromTransactionParams = True
  UdvControlType = uctInputMatrix
  RequireLogonExpression = ''False''
  IgnoreAuxParams = True
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
  DynamicFieldAttributes = True
  BottomMargin = -1
  object ORDER_ID: TsfDBInputEdit
    Left = 495
    Top = 15
    Width = 121
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
    Caption = ''Order ID''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object STEP_KEY: TsfDBInputEdit
    Left = 683
    Top = 15
    Width = 121
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
    Caption = ''Step Key''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OPER_KEY: TsfDBInputEdit
    Left = 495
    Top = 51
    Width = 121
    Height = 25
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
    Caption = ''Oper Key''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object REF_ID: TsfDBInputEdit
    Left = 685
    Top = 51
    Width = 121
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
    Caption = ''Ref ID''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OrderOperBuyoffIns: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''ORDER_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OPER_KEY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''BUYOFF_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=15''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''BUYOFF_CERT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''STEP_KEY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      '' MaxLength=0''
      ''@TOOLSTATE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      '' MaxLength=0''
      ''REF_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      '' MaxLength=0''
      ''OPTIONAL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' DefaultValue=N''
      '' Hidden=False''
      '' Required=N''
      '' MaxLength=1''
      ''CROSS_ORDER_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=1''
      '' PersistManualValues=Y''
      ''BUYOFF_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      '' MaxLength=0''
      ''@BLOCKID''
      ''UCF_OPER_BUYOFF_VCH1''
      ''UCF_OPER_BUYOFF_VCH2''
      ''UCF_OPER_BUYOFF_VCH3''
      ''UCF_OPER_BUYOFF_VCH4''
      ''UCF_OPER_BUYOFF_VCH5''
      ''UCF_OPER_BUYOFF_VCH6''
      ''UCF_OPER_BUYOFF_VCH7''
      ''UCF_OPER_BUYOFF_VCH8''
      ''UCF_OPER_BUYOFF_VCH9''
      ''UCF_OPER_BUYOFF_VCH10''
      ''UCF_OPER_BUYOFF_VCH11''
      ''UCF_OPER_BUYOFF_VCH12''
      ''UCF_OPER_BUYOFF_VCH13''
      ''UCF_OPER_BUYOFF_VCH14''
      ''UCF_OPER_BUYOFF_VCH15''
      ''UCF_OPER_BUYOFF_NUM1''
      ''UCF_OPER_BUYOFF_NUM2''
      ''UCF_OPER_BUYOFF_NUM3''
      ''UCF_OPER_BUYOFF_NUM4''
      ''UCF_OPER_BUYOFF_NUM5''
      ''UCF_OPER_BUYOFF_DATE1''
      ''UCF_OPER_BUYOFF_DATE2''
      ''UCF_OPER_BUYOFF_DATE3''
      ''UCF_OPER_BUYOFF_DATE4''
      ''UCF_OPER_BUYOFF_DATE5''
      ''UCF_OPER_BUYOFF_FLAG1''
      ''UCF_OPER_BUYOFF_FLAG2''
      ''UCF_OPER_BUYOFF_FLAG3''
      ''UCF_OPER_BUYOFF_FLAG4''
      ''UCF_OPER_BUYOFF_FLAG5''
      ''UCF_OPER_BUYOFF_VCH255_1''
      ''UCF_OPER_BUYOFF_VCH255_2''
      ''UCF_OPER_BUYOFF_VCH255_3''
      ''UCF_OPER_BUYOFF_VCH4000_1''
      ''UCF_OPER_BUYOFF_VCH4000_2''
      ''BUYOFF_TITLE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' MaxLength=255''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''CALLED_FROM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False'')
    InputFieldsEditControlSSL.Strings = (
      ''OPER_KEY''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STEP_KEY''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''@TOOLSTATE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''REF_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPTIONAL_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''BUYOFF_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CROSS_ORDER_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''CALLED_FROM''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''BUYOFF_TITLE''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=AsIs''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORDER_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''BUYOFF_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_BUYOFF_TYPES''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
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
      ''BUYOFF_CERT''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_OrderCertLookup''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
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
    InsertSqlId = ''sfwid_alt_oper_buyoff_ins''
    UpdateSqlId = ''MFI_SFWID_ALT_OPER_BUYOFF_INSUPD_PRE''
    ParamsSQLSourceName = 
      ''WIDOperBuy_ALT,@DragAndDrop,OrderOperFlagSel,@ToolScope,@Current'' +
      ''Marker,_OBJ1''
    RefreshParams = False
    LinkedControls.Strings = (
      ''ORDER_ID~ORDER_ID''
      ''OPER_KEY~OPER_KEY''
      ''STEP_KEY~STEP_KEY''
      ''REF_ID~REF_ID'')
    MultiRecordTransaction = mruTrue
    RefreshedSQLSourceName = ''WIDOperBuy_ALT''
    DataDefinitions.Strings = (
      ''ORDER_ID=''
      ''OPER_KEY=''
      ''BUYOFF_TYPE=''
      ''BUYOFF_CERT=''
      ''STEP_KEY=''
      ''@TOOLSTATE=''
      ''REF_ID=''
      ''OPTIONAL_FLAG=''
      ''CROSS_ORDER_FLAG=''
      ''BUYOFF_ID=''
      ''CROSS_ORDER_FLAG=''
      ''BUYOFF_ID''
      ''@BLOCKID''
      ''UCF_OPER_BUYOFF_VCH1''
      ''UCF_OPER_BUYOFF_VCH2''
      ''UCF_OPER_BUYOFF_VCH3''
      ''UCF_OPER_BUYOFF_VCH4''
      ''UCF_OPER_BUYOFF_VCH5''
      ''UCF_OPER_BUYOFF_VCH6''
      ''UCF_OPER_BUYOFF_VCH7''
      ''UCF_OPER_BUYOFF_VCH8''
      ''UCF_OPER_BUYOFF_VCH9''
      ''UCF_OPER_BUYOFF_VCH10''
      ''UCF_OPER_BUYOFF_VCH11''
      ''UCF_OPER_BUYOFF_VCH12''
      ''UCF_OPER_BUYOFF_VCH13''
      ''UCF_OPER_BUYOFF_VCH14''
      ''UCF_OPER_BUYOFF_VCH15''
      ''UCF_OPER_BUYOFF_NUM1''
      ''UCF_OPER_BUYOFF_NUM2''
      ''UCF_OPER_BUYOFF_NUM3''
      ''UCF_OPER_BUYOFF_NUM4''
      ''UCF_OPER_BUYOFF_NUM5''
      ''UCF_OPER_BUYOFF_DATE1''
      ''UCF_OPER_BUYOFF_DATE2''
      ''UCF_OPER_BUYOFF_DATE3''
      ''UCF_OPER_BUYOFF_DATE4''
      ''UCF_OPER_BUYOFF_DATE5''
      ''UCF_OPER_BUYOFF_FLAG1''
      ''UCF_OPER_BUYOFF_FLAG2''
      ''UCF_OPER_BUYOFF_FLAG3''
      ''UCF_OPER_BUYOFF_FLAG4''
      ''UCF_OPER_BUYOFF_FLAG5''
      ''UCF_OPER_BUYOFF_VCH255_1''
      ''UCF_OPER_BUYOFF_VCH255_2''
      ''UCF_OPER_BUYOFF_VCH255_3''
      ''UCF_OPER_BUYOFF_VCH4000_1''
      ''UCF_OPER_BUYOFF_VCH4000_2''
      ''BUYOFF_TITLE=''
      ''CALLED_FROM='')
  end
  object OrderOperFlagSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_OrderOperFlagsSel''
    ParamsSQLSourceName = ''WIDOperBuy_ALT''
    PublishParams = True
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

