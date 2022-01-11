
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5AB1A7D4AD35C993E05387971F0A27DC.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5AB1A7D4AD35C993E05387971F0A27DC';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_CommEmbedPreReply';
v_udv_type sfcore_udv_lib.udv_type%type  :='INPUT';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SRMO_EXPT_201';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFFND';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 300
  Height = 100
  AutoScroll = False
  AutoAccept = True
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
  object CommSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''CommFromSel''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    ConsolidatedQuery = False
  end
  object PreReply: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''COMM_ID''
      ''UCF_COMM_VCH1''
      ''UCF_COMM_VCH2''
      ''UCF_COMM_VCH3''
      ''UCF_COMM_VCH4''
      ''UCF_COMM_VCH5''
      ''UCF_COMM_VCH6''
      ''UCF_COMM_VCH7''
      ''UCF_COMM_VCH8''
      ''UCF_COMM_VCH9''
      ''UCF_COMM_VCH10''
      ''UCF_COMM_VCH11''
      ''UCF_COMM_VCH12''
      ''UCF_COMM_VCH13''
      ''UCF_COMM_VCH14''
      ''UCF_COMM_VCH15''
      ''UCF_COMM_NUM1''
      ''UCF_COMM_NUM2''
      ''UCF_COMM_NUM3''
      ''UCF_COMM_NUM4''
      ''UCF_COMM_NUM5''
      ''UCF_COMM_FLAG1''
      ''UCF_COMM_FLAG2''
      ''UCF_COMM_FLAG3''
      ''UCF_COMM_FLAG4''
      ''UCF_COMM_FLAG5''
      ''UCF_COMM_DATE1''
      ''UCF_COMM_DATE2''
      ''UCF_COMM_DATE3''
      ''UCF_COMM_DATE4''
      ''UCF_COMM_DATE5'')
    UpdateSqlId = ''PWUST_CommReplyCmdPre''
    ParamsSQLSourceName = ''@ToolScope, CommSel''
    RefreshParams = False
    LinkedControls.Strings = (
      ''commid~COMM_ID''
      ''curuser~@USERID'')
    DataDefinitions.Strings = (
      ''COMM_ID''
      ''UCF_COMM_VCH1''
      ''UCF_COMM_VCH2''
      ''UCF_COMM_VCH3''
      ''UCF_COMM_VCH4''
      ''UCF_COMM_VCH5''
      ''UCF_COMM_VCH6''
      ''UCF_COMM_VCH7''
      ''UCF_COMM_VCH8''
      ''UCF_COMM_VCH9''
      ''UCF_COMM_VCH10''
      ''UCF_COMM_VCH11''
      ''UCF_COMM_VCH12''
      ''UCF_COMM_VCH13''
      ''UCF_COMM_VCH14''
      ''UCF_COMM_VCH15''
      ''UCF_COMM_NUM1''
      ''UCF_COMM_NUM2''
      ''UCF_COMM_NUM3''
      ''UCF_COMM_NUM4''
      ''UCF_COMM_NUM5''
      ''UCF_COMM_FLAG1''
      ''UCF_COMM_FLAG2''
      ''UCF_COMM_FLAG3''
      ''UCF_COMM_FLAG4''
      ''UCF_COMM_FLAG5''
      ''UCF_COMM_DATE1''
      ''UCF_COMM_DATE2''
      ''UCF_COMM_DATE3''
      ''UCF_COMM_DATE4''
      ''UCF_COMM_DATE5'')
  end
  object commid: TsfDBInputEdit
    Left = 51
    Top = 21
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
  object curuser: TsfDBInputEdit
    Left = 51
    Top = 58
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
    TabOrder = 1
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

