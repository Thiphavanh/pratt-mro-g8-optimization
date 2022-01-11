
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6DD6EAE7D6313D5CE05387971F0A4260.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6DD6EAE7D6313D5CE05387971F0A4260';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperBuyofROReport';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect817;Defect849';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 820
  Height = 300
  AutoScroll = False
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
    Left = 4
    Top = 8
    Width = 800
    Height = 200
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
  object BuyoffAlt: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_MFI_WID_RptOrderBuyoffSel''
    LinkedControls.Strings = (
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@CurrentMarker,@AuxParams''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''BUYOFF_STATUS~13~Buyoff Status~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''BUYOFF_TITLE~12~Buyoff;Title~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      ''BUYOFF_CERT~8~Req Cert~N~N~False~False~False~N~~~~~Default~N~~''
      ''USER_NAME~8~Usr;Name~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''UPDT_USERID~8~Upd;Usr~N~N~order_control_type=''#39''SERIAL''#39'' OR order_c'' +
        ''ontrol_type=''#39''SERIAL-LOT''#39''~False~order_control_type=''#39''SERIAL''#39'' OR or'' +
        ''der_control_type=''#39''SERIAL-LOT''#39''~N~~~~~Default~N~~''
      ''TIME_STAMP~10~Upd Time~N~N~False~False~False~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''ACCEPT~Edit''
      ''BUYOFF_TITLE~Edit''
      ''BUYOFF_STATUS~Edit''
      ''TIME_STAMP~Edit'')
    TestParamValues.Strings = (
      ''ORDER_ID=4476E3C006F16879F3518112D1F0B69''
      ''OPER_KEY=26675''
      ''STEP_KEY=28272''
      ''REF_ID=PWUST_636220FEEFD8E320E05387971F0AAF0A'')
    OtherCommands.Strings = (
      
        ''Desc=eeweeew,Priv=,Visible=,TagValue=,Action=SideNote,SqlID=Buyo'' +
        ''ffSidePanel,Params(ORDER_ID=:ORDER_ID)'')
    DataDefinitions.Strings = (
      ''BUYOFF_STATUS''
      ''BUYOFF_TITLE''
      ''USER_NAME''
      ''UPDT_USERID''
      ''TIME_STAMP'')
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

