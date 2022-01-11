
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6272CFE38B9F5468E05387971F0A5BE0.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6272CFE38B9F5468E05387971F0A5BE0';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUE_MF1_1000987';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Clone of MF1_1000987 - SMRO_WOE_206; SMRO_WOE_306;Defect1589;Defect 1799';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.0.20190207~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1002
  Height = 397
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
  AlignContents = acParentWidth
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
  DesignSize = (
    998
    393)
  object _OBJ11: TsfDBGrid
    Left = 4
    Top = 32
    Width = 978
    Height = 115
    Anchors = [akLeft, akTop, akRight]
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
  object COLLECT_DATA: TsfCommandButton
    Left = 6
    Top = 9
    Width = 84
    Height = 21
    Hint = ''Collect Data''
    Caption = ''Collect''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 1
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Collect Data,''#39''Priv={DC_STATUS_DISP<>''#39#39''PENDING''#39#39'' AND ( WID_O'' +
        ''PER_STATUS=''#39#39''ACTIVE''#39#39'' OR WID_OPER_STATUS=''#39#39''IN QUEUE''#39#39'' ) AND (GRO'' +
        ''UP_JOB_NO = ''#39#39#39#39'' OR (GROUP_JOB_NO <> ''#39#39#39#39'' AND  GROUP_JOB_STATUS '' +
        ''= ''#39#39''ACTIVE''#39#39''))}''#39'',Visible=''#39''{ORDER_STATUS<>''#39#39''CLOSE''#39#39''}''#39'',TagValue=,F'' +
        ''Key=,ParamsSrc=@Default,Action=UDV,UDVType=GroupUpdate,FieldName'' +
        ''=DAT_COL_ID,Delim=;,UDVID=MFI_35836'')
  end
  object COPY_DATA_COLLECTION: TsfCommandButton
    Left = 94
    Top = 9
    Width = 84
    Height = 21
    Hint = ''Copy Data Collection''
    Caption = ''Copy''
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
      
        ''Desc=Copy Data Collection,''#39''Priv={DC_STATUS_DISP<>''#39#39''PENDING''#39#39'' AND'' +
        '' CALC_DC_FLAG = ''#39#39''N''#39#39'' AND ( WID_OPER_STATUS=''#39#39''ACTIVE''#39#39'' OR WID_OP'' +
        ''ER_STATUS=''#39#39''IN QUEUE''#39#39'' ) AND FORMAT<>''#39#39''Template File''#39#39'' AND 1 = 2'' +
        ''}''#39'',Visible=''#39''{ORDER_STATUS<>''#39#39''CLOSE''#39#39'' AND GROUP_JOB_NO=''#39#39#39#39''}''#39'',Tag'' +
        ''Value=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Insert,UDVID='' +
        ''MFI_11B69EFC446F25ADE0440003BA560E35'')
  end
  object DISPLAY_DC: TsfCommandButton
    Left = 183
    Top = 9
    Width = 84
    Height = 21
    Hint = ''Display History for Data Collections''
    Caption = ''Display''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Display Data Collections,Priv={},Visible={},TagValue=,FKey='' +
        '',ParamsSrc=@Default,Action=SideNote,SqlID=OrderOperDatColSideTex'' +
        ''t,Params(''#39''ORDER_ID=:ORDER_ID,''#39'',''#39''OPER_KEY=:OPER_KEY,''#39'',''#39''STEP_KEY=:'' +
        ''STEP_KEY,''#39'',DAT_COL_ID=:DAT_COL_ID)'')
  end
  object _OBJ12: TsfCommandButton
    Left = 272
    Top = 9
    Width = 84
    Height = 21
    Hint = ''Skip''
    Caption = ''Skip''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Skip,Priv=''#39''DC_STATUS_DISP<>''#39#39''PENDING''#39#39'' AND SKIP_STATUS=''#39#39''SK'' +
        ''IP''#39#39#39'',Visible=''#39''ORDER_STATUS<>''#39#39''CLOSE''#39#39#39'',TagValue=,FKey=,Action=U'' +
        ''DV,UDVType=Update,UDVID=MFI_135CA59EC5F601DBE0440003BA560E35'')
  end
  object _OBJ13: TsfCommandButton
    Left = 361
    Top = 9
    Width = 84
    Height = 21
    Hint = ''View File''
    Caption = ''View File''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=View File,Priv=,Visible=''#39''IS_FILE_DC =''#39#39''Y''#39#39#39'',TagValue=,FKey='' +
        '',Action=ExecSql,ParamsSqlSourceName=OrderOperDatColSerSel,Refres'' +
        ''hedSQLSourceName=,SqlID=MFI_WID_ViewFileDatCol'')
  end
  object OrderOperDatColSerSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''WidOperDatColSel''
    LinkedControls.Strings = (
      ''COLLECT_DATA''
      ''_OBJ11~''
      ''COPY_DATA_COLLECTION~''
      ''DISPLAY_DC''
      ''_OBJ12~''
      ''_OBJ13~''
      ''COLLECT_DATA~'')
    ParamsSQLSourceName = ''@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''DC_OUTSIDE_DISP~1~ ~N~N~False~False~False~N~~@StdMarkup(DC_OUTSI'' +
        ''DE_DISP)~~~Default~N~~~''
      
        ''DC_STATUS_DISP~1~         ~N~N~N~N~N~N~~@StdMarkup(DC_STATUS_DIS'' +
        ''P)~@Command(Collect Data)~~Default~N~~~''
      
        ''UCF_OPER_DC_FLAG1~3~ROM;Incl?~N~N~ORDER_TYPE<>''#39''REPAIR''#39''~False~ORD'' +
        ''ER_TYPE<>''#39''REPAIR''#39''~N~~~~~Default~N~~~''
      
        ''DAT_COL_TYPE~12~Data Collection;Type~N~N~False~N~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''DAT_COL_TITLE~28~Data Collection;Title~N~N~N~N~N~N~~~~~Default~Y'' +
        ''~~~''
      ''DAT_COL_UOM~3~UOM; ~N~N~False~N~False~N~~~~~Default~N~~~''
      
        ''AUDIT_FLAG~1~Over Inspection;Req''#39''d?~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''UCF_OPER_DC_VCH255_1~10~Engineer Cmts~N~N~ORDER_TYPE<>''#39''REPAIR''#39''~F'' +
        ''alse~ORDER_TYPE<>''#39''REPAIR''#39''~N~~~~~Default~N~~~''
      ''LOWER_LIMIT~3~LSL; ~N~N~False~N~False~N~~~~~Default~N~~~''
      ''TARGET_VALUE~5~Target;Value~N~N~False~N~False~N~~~~~Default~N~~~''
      
        ''VALUE~5~Value; ~N~N~order_control_type=''#39''SERIAL''#39'' OR order_control'' +
        ''_type=''#39''SERIAL-LOT''#39''~False~False~N~~~~~Default~N~~~''
      ''UPPER_LIMIT~3~USL; ~N~N~False~N~False~N~~~~~Default~N~~~''
      ''DAT_COL_CERT~10~Req''#39''d Cert; ~N~N~N~N~N~N~~~~~Default~N~~~''
      ''OPTIONAL_FLAG~3~Opt?; ~N~N~False~N~False~N~~~~~Default~N~~~''
      
        ''COMMENTS~10~Comments; ~N~N~true~False~False~N~MFI_2B059437B1B25C'' +
        ''55E0440003BA041A64~~~~Default~N~~~''
      ''LCL~3~LCL; ~N~N~True~False~False~N~~~~~Default~N~~~''
      ''UCL~3~UCL; ~N~N~True~False~False~N~~~~~Default~N~~~''
      
        ''UCF_OPER_DC_FLAG2~3~Serviceable;Limit;Used~N~N~ORDER_TYPE<>''#39''REPA'' +
        ''IR''#39''~False~ORDER_TYPE<>''#39''REPAIR''#39''~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''XBAR_CPU_VALUE~Edit''
      ''OPTIONAL_FLAG~Edit''
      ''COMMENTS~Edit''
      ''LCL~Edit''
      ''DC_OUTSIDE_DISP~Edit''
      ''UCF_OPER_DC_FLAG1~Edit''
      ''DAT_COL_TYPE~Edit''
      ''DAT_COL_TITLE~Edit''
      ''DAT_COL_UOM~Edit''
      ''AUDIT_FLAG~Edit''
      ''UCF_OPER_DC_VCH255_1~Edit''
      ''VALUE~Edit''
      ''UPPER_LIMIT~Edit'')
    TestParamValues.Strings = (
      ''order_control_type=NONE''
      ''order_id=E1046E3B3B44F6254C1006FD4423203C''
      ''oper_key=443340''
      ''step_key=-1''
      ''ref_id=PWUST_876ED44457DF1B3EE05387971F0A3EAA''
      ''@NavLevelFieldName=OPER_NO''
      ''@ConsolidatedQueryType=Instance''
      ''GROUP_JOB_NO='')
    OtherCommands.Strings = (
      
        ''Desc=Collect Data,Priv=''#39''{DC_STATUS_DISP<>''#39#39''SKIP_DataCollection''#39#39 +
        ''}''#39'',Visible=''#39''{DC_STATUS_DISP<>''#39#39''PENDING''#39#39''}''#39'',TagValue=,FKey=,''#39''Para'' +
        ''msSrc=@Default, SelectDummyCalledFrom''#39'',Action=UDV,UDVType=Update'' +
        '',UDVID=MFI_23935''
      
        ''Desc=Display Slide,Priv={},Visible=''#39''{SLIDE_ID<>''#39#39#39#39''}''#39'',TagValue=,'' +
        ''FKey=,ParamsSrc=@Default,Action=SideNote,SqlID=,Params(''#39''@SubConf'' +
        ''ig=Slide,''#39'',''#39''OBJECT_ID=:SLIDE_ID,''#39'',''#39''SLIDE_REF_ID=:SLIDE_EMBEDDED_'' +
        ''REF_ID,''#39'',''#39''ORDER_ID = :ORDER_ID,''#39'',''#39''OPER_KEY = :OPER_KEY,''#39'',STEP_KE'' +
        ''Y = :STEP_KEY)'')
    DataDefinitions.Strings = (
      ''DC_OUTSIDE_DISP''
      ''DC_STATUS_DISP''
      ''UCF_OPER_DC_FLAG1''
      ''DAT_COL_TYPE''
      ''DAT_COL_TITLE''
      ''DAT_COL_UOM''
      ''UCF_OPER_DC_VCH255_1''
      ''LOWER_LIMIT''
      ''TARGET_VALUE''
      ''VALUE''
      ''UPPER_LIMIT''
      ''DAT_COL_CERT''
      ''OPTIONAL_FLAG''
      ''COMMENTS''
      ''LCL''
      ''UCL''
      ''UCF_OPER_DC_FLAG2''
      ''AUDIT_FLAG'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''OrderOperDatColSerSel''
  end
  object SelectDummyCalledFrom: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_SELECTDUMMYCALLEDFROM''
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

