
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6271D92501C4547AE05387971F0A7D28.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6271D92501C4547AE05387971F0A7D28';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUE_MFI_1000958';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Clone of MFI_1000958 - SMRO_WOE_206';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 764
  Height = 200
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
    Height = 21
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
    Top = 27
    Width = 494
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
    Highlighting.Strings = (
      ''@Row~Red~Default~IS_OUTSIDE_LIMIT = ''#39''Y''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ4: TsfCommandButton
    Left = 65
    Top = 4
    Width = 152
    Height = 20
    Hint = ''Display Data Collections''
    Caption = ''Display Data Collections''
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
      
        ''Desc=Display Data Collections,Priv=,Visible=,TagValue=,Action=Si'' +
        ''deNote,SqlID=DataCollectionValues,Params(''#39''ORDER_ID = :ORDER_ID,''#39 +
        '',''#39''OPER_KEY = :OPER_KEY,''#39'',''#39''STEP_KEY = :STEP_KEY,''#39'',DAT_COL_ID=:DAT'' +
        ''_COL_ID)'')
  end
  object _OBJ5: TsfCommandButton
    Left = 220
    Top = 4
    Width = 152
    Height = 20
    Hint = ''Show DC Summary''
    Caption = ''Show DC Summary''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Show DC Summary,Priv=,Visible=,TagValue=,FKey=,Action=Invok'' +
        ''e,Tool=Tabular Report,ReportToolId=MFI_WID_DCSummary,Params(orde'' +
        ''r_id = :order_id)'')
  end
  object OrderDatColTabSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_OrderDatColTabSel''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~''
      ''_OBJ4~''
      ''_OBJ5~'')
    ParamsSQLSourceName = ''@ToolScope''
    RefreshedSQLSourceName = ''OrderDatColSel''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''IMAGE~1~ ~N~N~False~False~True~N~~@StdMarkup(IMAGE)~@Command(Dis'' +
        ''play Slide)~~Default~N~''
      ''OPER_NO~5~Oper No; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''STEP_NO~5~Step No; ~N~N~N~N~N~N~~~~~Default~N~~''
      
        ''DAT_COL_TYPE~14~Data Collection Type; ~N~N~N~N~N~N~~~~~Default~N'' +
        ''~~''
      
        ''DAT_COL_TITLE~14~Data Collection Title; ~N~N~N~N~N~N~~~~~Default'' +
        ''~N~~''
      ''DAT_COL_UOM~10~UOM; ~N~N~N~N~N~N~~~~~Default~N~~''
      
        ''DCVALUE~10~Collected;Value~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~''
      ''COMMENTS~10~Comments~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''FILE_NAME~15~Template File; ~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      ''LOWER_LIMIT~6~LSL; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''TARGET_VALUE~6~Target;Value~N~N~N~N~N~N~~~~~Default~N~~''
      ''UPPER_LIMIT~6~USL; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''DAT_COL_CERT~10~Required Cert; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''OPTIONAL_FLAG~1~Optional?; ~N~N~False~N~False~N~~~~~Default~N~~''
      ''ORIENTATION_FLAG~10~Orientation; ~N~N~N~N~N~N~~~~~Default~N~~''
      ''CROSS_ORDER_FLAG~4~Cross;Order?~N~N~N~N~N~N~~~~~Default~N~~''
      
        ''AUDIT_FLAG~7~Over Inspection;Req''#39''d?~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~''
      
        ''VALID_RESULT_TYPE~15~Validation Result Type; ~N~N~False~False~Fa'' +
        ''lse~N~~~~~Default~N~~''
      
        ''DISPLAY_LINE_NO~3~Display;Line No~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~''
      
        ''UPDT_USERID~10~Update User ID; ~N~N~N~N~N~N~MFI_2869~~~~Default~'' +
        ''N~~''
      ''TIME_STAMP~15~Update Time; ~N~N~N~N~N~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''DISPLAY_LINE_NO~Edit''
      ''ORIENTATION_FLAG~Edit''
      ''OPTIONAL_FLAG~Edit''
      ''DAT_COL_TITLE~Edit''
      ''DAT_COL_CERT~Edit''
      ''TIME_STAMP~Edit''
      ''UPDT_USERID~Edit''
      ''DAT_COL_UOM~Edit'')
    PrintTitle = ''Data Collection''
    TestParamValues.Strings = (
      ''order_id=MFI_6226841198AA423AE0440003BA041A64'')
    OtherCommands.Strings = (
      
        ''Desc=Export to Excel,Priv={},Visible={},TagValue=,FKey=,ParamsSr'' +
        ''c=@Default,Action=Print,Name=file.CSV,Params()''
      
        ''Desc=D/C Alteration History,Priv={},Visible={},TagValue=,FKey=,P'' +
        ''aramsSrc=@Default,Action=Invoke,Tool=TabReport,ReportToolId=Data'' +
        ''CollectionAlterationHistory,Params(''#39''ORDER_ID = :ORDER_ID,''#39'',DAT_C'' +
        ''OL_ID = :DAT_COL_ID)''
      
        ''Desc=D/C History,Priv=,Action=Invoke,Tool=TabReport,ReportToolId'' +
        ''=ParametricDataCollectionHist,Params(''#39''ORDER_ID = :ORDER_ID,''#39'',DAT'' +
        ''_COL_ID = :DAT_COL_ID)''
      ''Desc=-,Priv=,Visible=,TagValue=''
      
        ''Desc=Display X-Bar Chart,Priv=,Visible=,TagValue=,Action=UDV,UDV'' +
        ''Type=Insert,UDVID=MFI_34259''
      
        ''Desc=Display R Chart,Priv=,Visible=,TagValue=,Action=UDV,UDVType'' +
        ''=Insert,UDVID=MFI_34260''
      
        ''Desc=Display X-Bar R Chart,Priv={},Visible={},TagValue=,FKey=,Pa'' +
        ''ramsSrc=@Default,Action=UDV,UDVType=Insert,UDVID=MFI_914F18F0DAE'' +
        ''F476890DA22EC0C24B075''
      
        ''Desc=Display Slide,Priv={},Visible=''#39''{SLIDE_ID<>''#39#39#39#39''}''#39'',TagValue=,'' +
        ''FKey=,ParamsSrc=@Default,Action=SideNote,SqlID=,Params(''#39''@SubConf'' +
        ''ig=Slide,''#39'',''#39''OBJECT_ID=:SLIDE_ID,''#39'',''#39''SLIDE_REF_ID=:SLIDE_EMBEDDED_'' +
        ''REF_ID,''#39'',''#39''ORDER_ID = :ORDER_ID,''#39'',''#39''OPER_KEY = :OPER_KEY,''#39'',STEP_KE'' +
        ''Y = :STEP_KEY)'')
    DataDefinitions.Strings = (
      ''IMAGE''
      ''OPER_NO''
      ''STEP_NO''
      ''DAT_COL_TYPE''
      ''DAT_COL_TITLE''
      ''DAT_COL_UOM''
      ''FILE_NAME''
      ''LOWER_LIMIT''
      ''TARGET_VALUE''
      ''UPPER_LIMIT''
      ''DAT_COL_CERT''
      ''OPTIONAL_FLAG''
      ''ORIENTATION_FLAG''
      ''CROSS_ORDER_FLAG''
      ''AUDIT_FLAG''
      ''VALID_RESULT_TYPE''
      ''DISPLAY_LINE_NO''
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

