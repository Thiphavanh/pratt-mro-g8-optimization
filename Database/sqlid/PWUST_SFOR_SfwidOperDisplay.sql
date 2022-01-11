
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_8B0E6E707E869288E05387971F0A0F7D.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_8B0E6E707E869288E05387971F0A0F7D';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_SFOR_SfwidOperDisplay';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 1218';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 800
  Height = 390
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
    Left = 2
    Top = 26
    Width = 750
    Height = 150
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
    Highlighting.Strings = (
      ''@Row~Green~Default~INCLUDED = ''#39''INCLUDED''#39'' and OPER_STATUS=''#39''CLOSE''#39
      ''@Row~Blue~Bold~INCLUDED = ''#39''INCLUDED''#39'' and STANDARD_FLAG = ''#39''N''#39
      ''@Row~Green~Bold~INCLUDED=''#39''INCLUDED''#39'' and STANDARD_FLAG=''#39''Y''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ2: TsfUDVNavigator
    Left = 2
    Top = 4
    Width = 115
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 1
    TabStop = True
  end
  object _OBJ3: TsfLabel
    Left = 130
    Top = 8
    Width = 53
    Height = 13
    Caption = ''Operations''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ4: TsfDBGrid
    Left = 2
    Top = 204
    Width = 750
    Height = 150
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 2
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Highlighting.Strings = (
      
        ''@Row~Blue~Bold~INCLUDED_FLAG = ''#39''INCLUDED''#39'' and STANDARD_FLAG = ''#39''N'' +
        #39
      
        ''@Row~Green~Bold~INCLUDED_FLAG = ''#39''INCLUDED''#39'' and STANDARD_FLAG = ''#39 +
        ''Y''#39
      ''@Row~Red~Bold~SUBJECT_STATUS = ''#39''HOLD''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ5: TsfUDVNavigator
    Left = 2
    Top = 182
    Width = 115
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 3
    TabStop = True
  end
  object _OBJ9: TsfLabel
    Left = 130
    Top = 186
    Width = 105
    Height = 13
    Caption = ''Operation Task Group''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ10: TsfCommandButton
    Left = 245
    Top = 4
    Width = 75
    Height = 20
    Hint = ''Include''
    Caption = ''Include''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Include,''#39''Priv={INCLUDED=''#39#39''EXCLUDED''#39#39'' AND @haspriv(''#39#39''PW_ORDE'' +
        ''R_OPER_INC''#39#39'')}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Ac'' +
        ''tion=UDV,UDVType=GroupInsert,FieldName=LOCAL_OPER_KEY,''#39''Delim=,''#39'','' +
        ''UDVID=SFOR_279'')
  end
  object _OBJ11: TsfCommandButton
    Left = 326
    Top = 4
    Width = 75
    Height = 20
    Hint = ''Exclude''
    Caption = ''Exclude''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Exclude,''#39''Priv={INCLUDED <> ''#39#39''EXCLUDED''#39#39'' AND @haspriv(''#39#39''PW_O'' +
        ''RDER_OPER_INC''#39#39'')}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default'' +
        '',Action=UDV,UDVType=GroupInsert,FieldName=LOCAL_OPER_KEY,''#39''Delim='' +
        '',''#39'',UDVID=SFOR_280'')
  end
  object _OBJ12: TsfCommandButton
    Left = 407
    Top = 4
    Width = 120
    Height = 20
    Hint = ''Operation Precedence''
    Caption = ''Display Relationships''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Operation Precedence,Priv=''#39''ORDER_ID<>''#39#39#39#39#39'',Visible=,TagValu'' +
        ''e=,FKey=,Action=SideNote,SqlID=MFI_WID_OPERATIONPRECEDENCESIDEPA'' +
        ''NEL,Params(''#39''ORDER_ID = :ORDER_ID,''#39'',''#39''OPER_KEY = :OPER_KEY,''#39'',''#39''OPER'' +
        ''_NO = :OPER_NO,''#39'',''#39''@ToolState = :@ToolState,''#39'',''#39''ALTERATION_LEVEL ='' +
        '' :ALTERATION_LEVEL,''#39'',CALLED_FROM = Operation)'')
  end
  object _OBJ13: TsfCommandButton
    Left = 531
    Top = 4
    Width = 75
    Height = 20
    Hint = ''Defer Work''
    Caption = ''Defer Work''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (
      
        ''Desc=Defer Work,Priv=,Visible=''#39''DEFER_WORK_FLAG=''#39#39''Y''#39#39#39'',TagValue=,'' +
        ''FKey=,Action=UDV,UDVType=Update,UDVID=MFI_E104CD05FD3A4B50A4FCEF'' +
        ''B25655692A'')
  end
  object SFOR_SfwidOperSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SfwidOperSelect''
    KeyFieldNames = ''OPER_NO''
    LinkedControls.Strings = (
      ''_OBJ2~''
      ''_OBJ11~''
      ''_OBJ10~''
      ''_OBJ12~''
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''INCLUDED~10~Included/Excluded; ~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      ''STANDARD_FLAG~6~Standard?; ~N~N~N~N~N~N~~~~~Default~N~''
      ''OPER_NO~5~Oper No; ~N~N~N~N~N~N~~~~~Default~N~''
      ''TITLE~10~Oper Title; ~N~N~False~False~False~N~~~~~Default~N~''
      
        ''ASGND_WORK_LOC~25~Work Location; ~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''ASGND_WORK_DEPT~25~Work Dept; ~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~''
      ''ASGND_WORK_CENTER~25~Work Center; ~N~N~N~N~N~N~~~~~Default~N~''
      ''OPER_STATUS~10~Oper Status; ~N~N~N~N~N~N~~~~~Default~N~''
      
        ''UNIT_PROCESSING~10~Unit Processing; ~N~N~False~False~False~N~~~~'' +
        ''~Default~N~''
      ''NOTES~30~Notes; ~N~N~False~False~False~N~~~~~Default~N~''
      ''DISC_ID~6~Disc ID; ~N~N~False~False~False~N~~~~~Default~N~''
      ''DISC_LINE_NO~5~Line No; ~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''INCLUDED_FLAG~Edit''
      ''STANDARD_FLAG~Edit''
      ''OPER_NO~Edit'')
    TestParamValues.Strings = (
      ''order_id=1'')
    OtherCommands.Strings = (
      
        ''Desc=Exclude Oper,Priv=''#39''REL_ALT_TYPE=''#39#39''ORDER''#39#39#39'',Visible=,TagValu'' +
        ''e=,FKey=,Action=UDV,UDVType=GroupUpdate,FieldName=LOCAL_OPER_KEY'' +
        '',''#39''Delim=,''#39'',UDVID=SFOR_280''
      
        ''Desc=Include Oper,Priv=''#39''REL_ALT_TYPE=''#39#39''ORDER''#39#39#39'',Visible=,TagValu'' +
        ''e=,FKey=,Action=UDV,UDVType=GroupUpdate,FieldName=LOCAL_OPER_KEY'' +
        '',''#39''Delim=,''#39'',UDVID=SFOR_279'')
    DataDefinitions.Strings = (
      ''INCLUDED''
      ''STANDARD_FLAG''
      ''OPER_NO''
      ''TITLE''
      ''ASGND_WORK_LOC''
      ''ASGND_WORK_DEPT''
      ''ASGND_WORK_CENTER''
      ''OPER_STATUS''
      ''UNIT_PROCESSING''
      ''NOTES''
      ''DISC_ID''
      ''DISC_LINE_NO'')
    ConsolidatedQuery = False
  end
  object SFOR_SfwidSubjectOper: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''SFOR_SfwidSubjectOper''
    LinkedControls.Strings = (
      ''_OBJ5~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''SFOR_SfwidOperSelect''
    PublishParams = True
    SelectedFields.Strings = (
      ''INCLUDED_FLAG~12~Included/Excluded; ~N~N~N~N~N~N~~~~~Default~N~''
      ''STANDARD_FLAG~6~Standard?; ~N~N~N~N~N~N~~~~~Default~N~''
      ''SUBJECT_NO~10~Task Group No; ~N~N~N~N~N~N~~~~~Default~N~''
      ''SUBJECT_REV~10~Task Group Rev; ~N~N~N~N~N~N~~~~~Default~N~''
      ''TITLE~20~Title; ~N~N~N~N~N~N~~~~~Default~N~''
      
        ''UPDT_USERID~25~Update UserID; ~N~N~N~N~N~N~MFI_2869~~~~Default~N'' +
        ''~''
      ''TIME_STAMP~18~Update Time; ~N~N~N~N~N~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''SUBJECT_REV~Edit'')
    DataDefinitions.Strings = (
      ''INCLUDED_FLAG''
      ''STANDARD_FLAG''
      ''SUBJECT_NO''
      ''SUBJECT_REV''
      ''TITLE''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object DeferWorkFlagSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_DeferWorkEnableFlagSel''
    LinkedControls.Strings = (
      ''_OBJ13~'')
    ParamsSQLSourceName = ''SFOR_SfwidOperSelect''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''DEFER_WORK_FLAG~ ~DEFER_WORK_FLAG~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~'')
    SelectedFieldsEditControl.Strings = (
      ''DEFER_WORK_FLAG~Edit'')
    DataDefinitions.Strings = (
      ''DEFER_WORK_FLAG'')
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

