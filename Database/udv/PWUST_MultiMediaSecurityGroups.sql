
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_C3F19351EA11AAB4E05387971F0A386F.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_C3F19351EA11AAB4E05387971F0A386F';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_MultiMediaSecurityGroups';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='DEFECT 1906';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 500
  Height = 200
  HelpContext = ''''
  AutoScroll = False
  DoubleBuffered = False
  ParentDoubleBuffered = False
  Caption = ''''
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
  AlignContents = acHorizontal
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
  object SEC_GRP: TsfUDVNavigator
    Left = 4
    Top = 3
    Width = 153
    Height = 21
    Hidden = ''@ToolId=''#39''SLIDE''#39
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Insert ''
      ''Delete ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 0
  end
  object _OBJ1: TsfDBGrid
    Left = 4
    Top = 26
    Width = 481
    Height = 120
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 1
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object INS_SEC_GRP: TsfCommandButton
    Left = 50
    Top = 3
    Width = 120
    Height = 21
    Hint = ''INS_SEC_GRO''
    Caption = ''Insert Security Group''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=INS_SEC_GRO,Priv={1=1},Visible={OBJECT_ID<>''#39#39'' AND @ToolId=''#39 +
        ''SLIDE''#39''},TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=In'' +
        ''sert,UDVID=MFI_858378CB7F9040C8AC4EA9552274D9A1'')
  end
  object OnlyForInstructions: TsfUDVNavigator
    Left = 4
    Top = 3
    Width = 191
    Height = 21
    Hidden = ''(NOT @HasPriv(''#39''PW_ILLUSTRATIONS_SEC_GROUP''#39''))''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''InsertUpdateDelete ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 3
  end
  object DEL_SEC_GRP: TsfCommandButton
    Left = 100
    Top = 3
    Width = 120
    Height = 21
    Hint = ''DEL_SEC_GRP''
    Caption = ''Delete Security Group''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=DEL_SEC_GRP,''#39''Priv={(NOT @HasPriv(''#39#39''PW_ILLUSTRATIONS_SEC_GRO'' +
        ''UP''#39#39'')) OR SEC_GRP<>''#39#39#39#39''}''#39'',Visible=''#39''{@ToolId=''#39#39''SLIDE''#39#39''}''#39'',TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=ExecSql,''#39''ParamsSqlSourceName=@'' +
        ''ToolScope,MmSecurityGroupSel''#39'',RefreshedSQLSourceName=MmSecurityG'' +
        ''roupSel,SqlID=MFI_ILLUSTRATIONSECURITYGROUPDEL'')
  end
  object MmSecurityGroupSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_FND_MMSECURITYGROUPSEL''
    DeleteSqlId = ''MFI_ILLUSTRATIONSECURITYGROUPDEL''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''SEC_GRP~''
      ''INS_SEC_GRP~''
      ''OnlyForInstructions~''
      ''DEL_SEC_GRP~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''SEC_GRP~15~Security Group~N~N~False~False~False~N~~~~~Default~N~''
      
        ''SECURITY_GROUP_DESC~15~Description~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~''
      
        ''SECURITY_GROUP_TYPE~15~Security Group Type~N~N~False~False~True~'' +
        ''N~~~~~Default~N~''
      
        ''EXPIRATION_DATE~18~Expiration Date~N~N~False~False~False~N~~~~~D'' +
        ''efault~N~''
      
        ''ISSUING_AGENCY~10~Issuing Agency~N~N~False~False~True~N~~~~~Defa'' +
        ''ult~N~''
      
        ''OBSOLETE_RECORD_FLAG~6~Obsolete?~N~N~False~False~False~N~~~~~Def'' +
        ''ault~N~''
      
        ''UPDT_USERID~15~Update User ID~N~N~False~False~True~N~~~~~Default'' +
        ''~N~''
      ''TIME_STAMP~15~Update Time~N~N~False~False~True~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''SECURITY_GROUP~Edit''
      ''SECURITY_GROUP_TYPE~Edit''
      ''OBSOLETE_RECORD_FLAG~Edit''
      ''SEC_GRP~Edit'')
    TestParamValues.Strings = (
      ''OBJECT_TAG=FILE_P1''
      ''OBJECT_ID=MFI_1B9F89F17BEE4E46A11B4F188B954804''
      ''OBJECT_TYPE=SLIDE''
      ''OBJECT_REV=2'')
    InputUDVId = ''MFI_858378CB7F9040C8AC4EA9552274D9A1''
    InsertEnabledExpr = ''@HasPriv(''#39''PW_ILLUSTRATIONS_SEC_GROUP''#39'')''
    UpdateEnabledExpr = ''@HasPriv(''#39''PW_ILLUSTRATIONS_SEC_GROUP''#39'')''
    DeleteEnabledExpr = ''@HasPriv(''#39''PW_ILLUSTRATIONS_SEC_GROUP''#39'')''
    InsUpdDelEnabledExpr = ''@HasPriv(''#39''PW_ILLUSTRATIONS_SEC_GROUP''#39'')''
    DataDefinitions.Strings = (
      ''SEC_GRP''
      ''SECURITY_GROUP_DESC''
      ''SECURITY_GROUP_TYPE''
      ''EXPIRATION_DATE''
      ''ISSUING_AGENCY''
      ''OBSOLETE_RECORD_FLAG''
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

