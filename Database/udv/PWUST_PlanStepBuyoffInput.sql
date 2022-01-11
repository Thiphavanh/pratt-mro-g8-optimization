
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5E1B37B0C9498525E05387971F0A744F.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5E1B37B0C9498525E05387971F0A744F';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_PlanStepBuyoffInput';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPMTX';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202 Defect 134;Defect 144;Defect 697';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 906
  Height = 397
  AutoScroll = False
  Caption = 
    ''@Lookup(@TransactionType,''#39''InsUpdDel:Insert/Update/Delete Buyoffs'' +
    '',Insert:Insert Buyoffs,Update:Update Buyoffs,InputMatrix:Insert '' +
    ''Buyoffs''#39'',''#39''Insert/Update/Delete Buyoffs''#39'')''
  TransactionType = ttCached
  IMSqlTransactionName = ''EmbedBuyoffInsert''
  IMSqlSourceName = ''EmbedBuyoffSelect''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = -1
  IMPopulateFromParams = True
  IMEndExecSqlId = ''MFI_PLG_CheckStepBuyoff''
  IMPopulateFromTransactionParams = True
  UdvControlType = uctInputMatrix
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
  object PLAN_ID: TsfDBInputEdit
    Left = 244
    Top = 10
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
    TabOrder = 0
    EntryType = etAlphaNumeric
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object EmbedBuyoffInsert: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''BUYOFF_TYPE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=10''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''PLAN_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=50''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''PLAN_VERSION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''PLAN_REVISION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''PLAN_ALTERATIONS''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''BUYOFF_CERT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=30''
      '' PersistManualValues=Y''
      ''OPER_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''STEP_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''OPER_KEY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      '' MaxLength=5''
      ''STEP_KEY''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      '' MaxLength=5''
      ''STEP_UPDT_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      '' MaxLength=5''
      ''LOW_NAV_LEVEL''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      '' MaxLength=5''
      ''OPTIONAL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=1''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=N''
      '' DefaultValue=N''
      ''CROSS_ORDER_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=5''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''REF_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''BUYOFF_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=0''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=N''
      ''@BLOCKID''
      ''UCF_STEP_BUYOFF_VCH1''
      ''UCF_STEP_BUYOFF_VCH2''
      ''UCF_STEP_BUYOFF_VCH3''
      ''UCF_STEP_BUYOFF_VCH4''
      ''UCF_STEP_BUYOFF_VCH5''
      ''UCF_STEP_BUYOFF_VCH6''
      ''UCF_STEP_BUYOFF_VCH7''
      ''UCF_STEP_BUYOFF_VCH8''
      ''UCF_STEP_BUYOFF_VCH9''
      ''UCF_STEP_BUYOFF_VCH10''
      ''UCF_STEP_BUYOFF_VCH11''
      ''UCF_STEP_BUYOFF_VCH12''
      ''UCF_STEP_BUYOFF_VCH13''
      ''UCF_STEP_BUYOFF_VCH14''
      ''UCF_STEP_BUYOFF_VCH15''
      ''UCF_STEP_BUYOFF_NUM1''
      ''UCF_STEP_BUYOFF_NUM2''
      ''UCF_STEP_BUYOFF_NUM3''
      ''UCF_STEP_BUYOFF_NUM4''
      ''UCF_STEP_BUYOFF_NUM5''
      ''UCF_STEP_BUYOFF_DATE1''
      ''UCF_STEP_BUYOFF_DATE2''
      ''UCF_STEP_BUYOFF_DATE3''
      ''UCF_STEP_BUYOFF_DATE4''
      ''UCF_STEP_BUYOFF_DATE5''
      ''UCF_STEP_BUYOFF_FLAG1''
      ''UCF_STEP_BUYOFF_FLAG2''
      ''UCF_STEP_BUYOFF_FLAG3''
      ''UCF_STEP_BUYOFF_FLAG4''
      ''UCF_STEP_BUYOFF_FLAG5''
      ''UCF_STEP_BUYOFF_VCH255_1''
      ''UCF_STEP_BUYOFF_VCH255_2''
      ''UCF_STEP_BUYOFF_VCH255_3''
      ''UCF_STEP_BUYOFF_VCH4000_1''
      ''UCF_STEP_BUYOFF_VCH4000_2''
      ''BUYOFF_TITLE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' MaxLength=255''
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
      ''STEP_UPDT_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''STEP_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''LOW_NAV_LEVEL''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPTIONAL_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''REF_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''BUYOFF_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_VERSION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_REVISION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PLAN_ALTERATIONS''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OPER_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CROSS_ORDER_FLAG''
      '' CtrlType=List''
      ''  Values=N~Y''
      ''BUYOFF_TITLE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''BUYOFF_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_BUYOFF_TYPES''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  ForceLookup=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   BUYOFF_TYPE=Buyoff Type''
      ''   BUYOFF_TITLE=Buyoff Title''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''BUYOFF_CERT''
      '' CtrlType=Lookup''
      ''  SqlId=PWUST_PlanCertLookup''
      ''  ParamsSrc=EmbedBuyoffSelect''
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
    InsertSqlId = ''EmbedBuyoffInsert''
    UpdateSqlId = ''EmbedBuyoffEditUpdate''
    ParamsSQLSourceName = ''EmbedBuyoffSelect,PlanOperFlagSel,InitBuyoffTitle''
    RefreshParams = False
    LinkedControls.Strings = (
      ''PLAN_ID~PLAN_ID'')
    DataDefinitions.Strings = (
      ''BUYOFF_TYPE=''
      ''PLAN_ID=''
      ''PLAN_VERSION=''
      ''PLAN_REVISION=''
      ''PLAN_ALTERATIONS=''
      ''BUYOFF_CERT=''
      ''OPER_NO=''
      ''STEP_NO=''
      ''OPER_KEY=''
      ''STEP_KEY=''
      ''STEP_UPDT_NO=''
      ''LOW_NAV_LEVEL=''
      ''OPTIONAL_FLAG=''
      ''CROSS_ORDER_FLAG=''
      ''REF_ID=''
      ''BUYOFF_ID=''
      ''PLAN_ID''
      ''PLAN_ALTERATIONS''
      ''OPER_NO''
      ''LOW_NAV_LEVEL''
      ''REF_ID''
      ''BUYOFF_ID''
      ''@BLOCKID''
      ''UCF_STEP_BUYOFF_VCH1''
      ''UCF_STEP_BUYOFF_VCH2''
      ''UCF_STEP_BUYOFF_VCH3''
      ''UCF_STEP_BUYOFF_VCH4''
      ''UCF_STEP_BUYOFF_VCH5''
      ''UCF_STEP_BUYOFF_VCH6''
      ''UCF_STEP_BUYOFF_VCH7''
      ''UCF_STEP_BUYOFF_VCH8''
      ''UCF_STEP_BUYOFF_VCH9''
      ''UCF_STEP_BUYOFF_VCH10''
      ''UCF_STEP_BUYOFF_VCH11''
      ''UCF_STEP_BUYOFF_VCH12''
      ''UCF_STEP_BUYOFF_VCH13''
      ''UCF_STEP_BUYOFF_VCH14''
      ''UCF_STEP_BUYOFF_VCH15''
      ''UCF_STEP_BUYOFF_NUM1''
      ''UCF_STEP_BUYOFF_NUM2''
      ''UCF_STEP_BUYOFF_NUM3''
      ''UCF_STEP_BUYOFF_NUM4''
      ''UCF_STEP_BUYOFF_NUM5''
      ''UCF_STEP_BUYOFF_DATE1''
      ''UCF_STEP_BUYOFF_DATE2''
      ''UCF_STEP_BUYOFF_DATE3''
      ''UCF_STEP_BUYOFF_DATE4''
      ''UCF_STEP_BUYOFF_DATE5''
      ''UCF_STEP_BUYOFF_FLAG1''
      ''UCF_STEP_BUYOFF_FLAG2''
      ''UCF_STEP_BUYOFF_FLAG3''
      ''UCF_STEP_BUYOFF_FLAG4''
      ''UCF_STEP_BUYOFF_FLAG5''
      ''UCF_STEP_BUYOFF_VCH255_1''
      ''UCF_STEP_BUYOFF_VCH255_2''
      ''UCF_STEP_BUYOFF_VCH255_3''
      ''UCF_STEP_BUYOFF_VCH4000_1''
      ''UCF_STEP_BUYOFF_VCH4000_2''
      ''BUYOFF_TITLE='')
  end
  object PlanOperFlagSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PlanOperFlagSel''
    ParamsSQLSourceName = ''EmbedBuyoffSelect''
    PublishParams = True
    ConsolidatedQuery = False
  end
  object InitBuyoffTitle: TsfTransactionParamSource
    SelectSqlId = ''PWUST_InitBuyoffTitle''
    PublishParams = True
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

