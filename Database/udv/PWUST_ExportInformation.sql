
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_5AB1AB68AEE8C98FE05387971F0ABAD8.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_5AB1AB68AEE8C98FE05387971F0ABAD8';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_ExportInformation';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
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
  Width = 624
  Height = 325
  AutoScroll = False
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMNoSelectionLoadsAll = etDefault
  IMFixedColCount = 0
  IMPopulateFromParams = False
  UdvControlType = uctPanel
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acAsIs
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
  object _OBJ1: TsfDBEdit
    Left = 26
    Top = 60
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 0
    Caption = ''Export;Jurisdiction''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ2: TsfDBEdit
    Left = 187
    Top = 60
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 1
    Caption = ''Export;Classification''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ3: TsfDBEdit
    Left = 348
    Top = 60
    Width = 66
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 2
    Caption = ''SME''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ4: TsfDBEdit
    Left = 454
    Top = 60
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 3
    Caption = ''eClass record Number''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ5: TsfDBEdit
    Left = 26
    Top = 128
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 4
    Caption = ''RTC Flag''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ6: TsfDBEdit
    Left = 187
    Top = 128
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = False
    TabOrder = 5
    Caption = ''Export Cert;Statement ID''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -13
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    Hidden = False
  end
  object JCInfoSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_ExportControl_JCInfo''
    LinkedControls.Strings = (
      ''_OBJ1~UCF_COMM_VCH8''
      ''_OBJ2~UCF_COMM_VCH9''
      ''_OBJ4~UCF_COMM_NUM3''
      ''_OBJ5~UCF_COMM_FLAG3''
      ''_OBJ6~UCF_COMM_VCH10''
      ''_OBJ3~UCF_COMM_NUM2'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''UCF_COMM_NUM3~20~UCF_COMM_NUM3~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''UCF_COMM_VCH8~20~UCF_COMM_VCH8~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''UCF_COMM_VCH9~20~UCF_COMM_VCH9~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''UCF_COMM_FLAG3~10~UCF_COMM_FLAG3~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~~''
      
        ''UCF_COMM_VCH10~0~UCF_COMM_VCH10~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~''
      
        ''UCF_COMM_NUM2~0~UCF_COMM_NUM2~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''COMM_STATUS~Edit''
      ''SUBJECT~Edit''
      ''UCF_COMM_VCH8~Edit''
      ''UCF_COMM_NUM3~Edit'')
    TestParamValues.Strings = (
      ''comm_id=343''
      ''TASK_ID=PWUE_44074FF744C47D62EE3A83DD31ACB91E'')
    DataDefinitions.Strings = (
      ''UCF_COMM_NUM3''
      ''UCF_COMM_VCH8''
      ''UCF_COMM_VCH9''
      ''UCF_COMM_FLAG3''
      ''UCF_COMM_VCH10'')
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

