
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_635E425D301A4A89E05387971F0A921F.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_635E425D301A4A89E05387971F0A921F';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderBuyoffTabSerial';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202;Defect 763';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 279
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
  object _OBJ1: TsfUDVNavigator
    Left = 4
    Top = 4
    Width = 58
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Other commands ''
      ''Filter '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ2: TsfDBGrid
    Left = 4
    Top = 26
    Width = 502
    Height = 157
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
  object _OBJ4: TsfCommandButton
    Left = 200
    Top = 4
    Width = 138
    Height = 20
    Hint = ''Display Buyoffs''
    Caption = ''Display Buyoffs''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Display Buyoffs,Priv=,Visible=,TagValue=,Action=SideNote,Sq'' +
        ''lID=BuyoffSidePanel,Params(''#39''ORDER_ID  = :ORDER_ID,''#39'',BUYOFF_ID=:B'' +
        ''UYOFF_ID)'')
  end
  object ADDITIONAL_SIGNATURES: TsfDBGrid
    Left = 508
    Top = 26
    Width = 225
    Height = 157
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 3
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object PWUST_OrderBuyoffTabSerialSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_OrderBuyoffTabSerialSel''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~''
      ''_OBJ4~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''IMAGE~1~ ~N~N~False~False~True~N~~@StdMarkup(IMAGE)~@Command(Dis'' +
        ''play Slide)~~Default~N~~''
      ''OPER_NO~6~Oper No; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''STEP_NO~6~Step No; ~N~N~N~N~N~N~~~~~Default~N~~''
      
        ''BUYOFF_TITLE~20~Buyoff Title; ~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''BUYOFF_STATUS~10~Buyoff Status~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''USER_NAME~20~Buyoff;User Name~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''UPDT_USERID~12~Update User ID; ~N~N~N~N~N~N~MFI_2869~~~~Default~'' +
        ''N~~''
      ''TIME_STAMP~18~Update Time; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''BUYOFF_CERT~14~Required Cert; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''COMMENTS~20~Comments;~N~N~False~False~False~N~~~~~Default~N~~''
      ''OPTIONAL_FLAG~5~Optional?; ~N~N~False~N~False~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''OPTIONAL_FLAG~Edit''
      ''COMMENTS~Edit''
      ''BUYOFF_CERT~Edit''
      ''UPDT_USERID~Edit''
      ''BUYOFF_TITLE~Edit''
      ''OPER_NO~Edit'')
    PrintTitle = ''Buyoffs''
    OtherCommands.Strings = (
      
        ''Desc=Buyoff Alteration History,Priv={},Visible={},TagValue=,FKey'' +
        ''=,ParamsSrc=@Default,Action=Invoke,Tool=TabReport,ReportToolId=B'' +
        ''uyoffAlterationHistory,Params(''#39''ORDER_ID = :ORDER_ID,''#39'',BUYOFF_ID '' +
        ''= :BUYOFF_ID)''
      
        ''Desc=Buyoff History,Priv=,Visible=,TagValue=,Action=Invoke,Tool='' +
        ''TabReport,ReportToolId=BuyoffDataCollectionHistory,Params(''#39''ORDER'' +
        ''_ID = :ORDER_ID,''#39'',BUYOFF_ID = :BUYOFF_ID)''
      
        ''Desc=Display Slide,Priv={},Visible=''#39''{SLIDE_ID<>''#39#39#39#39''}''#39'',TagValue=,'' +
        ''FKey=,ParamsSrc=@Default,Action=SideNote,SqlID=,Params(''#39''@SubConf'' +
        ''ig=Slide,''#39'',''#39''OBJECT_ID=:SLIDE_ID,''#39'',''#39''SLIDE_REF_ID=:SLIDE_EMBEDDED_'' +
        ''REF_ID,''#39'',''#39''ORDER_ID = :ORDER_ID,''#39'',''#39''OPER_KEY = :OPER_KEY,''#39'',STEP_KE'' +
        ''Y = :STEP_KEY)'')
    DataDefinitions.Strings = (
      ''IMAGE''
      ''OPER_NO''
      ''STEP_NO''
      ''BUYOFF_TYPE''
      ''BUYOFF_TITLE''
      ''BUYOFF_STATUS''
      ''BUYOFF_CERT''
      ''COMMENTS''
      ''OPTIONAL_FLAG''
      ''USER_NAME''
      ''UPDT_USERID''
      ''TIME_STAMP'')
    ConsolidatedQuery = False
  end
  object SelAddtionalSignatures: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_SelectBuyoffSignatures''
    LinkedControls.Strings = (
      ''ADDITIONAL_SIGNATURES~'')
    ParamsSQLSourceName = ''@ToolScope,PWUST_OrderBuyoffTabSerialSel''
    SelectedFields.Strings = (
      
        ''SIGNOFF_NAME~12~Additional;Signoff Name~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~''
      
        ''SIGNOFF_USERID~12~Additional;Signoff Userid~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~''
      
        ''SIGNOFF_DATE~12~Additional;Signoff Timestap~N~N~False~False~Fals'' +
        ''e~N~~~~~Default~N~~'')
    TestParamValues.Strings = (
      ''ORDER_ID=PWUST_5450A641170B91E4A243358F64584939''
      ''OPER_KEY=11361''
      ''STEP_KEY=-1''
      ''BUYOFF_ID=PWUST_3ACEE63AF781ED44BA47D22297FB3327'')
    DataDefinitions.Strings = (
      ''SIGNOFF_NAME''
      ''SIGNOFF_USERID''
      ''SIGNOFF_DATE'')
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

