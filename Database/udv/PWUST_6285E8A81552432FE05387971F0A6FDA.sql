
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_6285E8A81552432FE05387971F0A6FDA.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_6285E8A81552432FE05387971F0A6FDA';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUE_MFI_1009547';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Clone of MFI_1009547 - SMRO_WOE_206';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 470
  Height = 450
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
  DesignSize = (
    466
    446)
  object _OBJ5: TsfDBGrid
    Left = 4
    Top = 4
    Width = 450
    Height = 115
    TabStop = False
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
  object _OBJ8: TsfCommandButton
    Left = 232
    Top = 129
    Width = 74
    Height = 21
    Hint = ''Collect Data''
    Caption = ''Collect Data''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Collect Data,''#39''Priv={DC_STATUS_DISP=''#39#39''Y''#39#39'' AND CALC_DC_FLAG=''#39 +
        #39''N''#39#39'' AND ALT_MODE_STATUS=''#39#39#39#39'' AND (SERIAL_OPER_STATUS=''#39#39''IN QUEUE'' +
        #39#39''  OR SERIAL_OPER_STATUS=''#39#39''ACTIVE''#39#39'') AND (GROUP_JOB_NO =''#39#39#39#39'' OR'' +
        '' GROUP_JOB_STATUS = ''#39#39''ACTIVE''#39#39'' OR GROUP_JOB_STATUS = ''#39#39''IN QUEUE''#39 +
        #39'')}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UD'' +
        ''VType=GroupUpdate,FieldName=DAT_COL_ID,Delim=;,UDVID=MFI_12E616C'' +
        ''6F1BC046CE0440003BA560E35'')
  end
  object _OBJ21: TsfDBGrid
    Left = 4
    Top = 157
    Width = 460
    Height = 115
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
    Highlighting.Strings = (
      ''@Row~Red~Default~outside=''#39''Y''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object serial_values: TsfDBGrid
    Left = 4
    Top = 311
    Width = 460
    Height = 129
    TabStop = False
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
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ28: TsfUDVNavigator
    Left = 4
    Top = 288
    Width = 39
    Height = 21
    Hidden = ''true''
    VisibleButtons.Strings = (
      ''Delete ''
      ''Edit '')
    ShowHint = True
    TabOrder = 3
  end
  object _OBJ29: TsfCommandButton
    Left = 384
    Top = 281
    Width = 74
    Height = 21
    Hint = ''Override''
    Caption = ''Override''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Override,''#39''Priv={OVERRIDE_OK=''#39#39''Y''#39#39'' AND ALT_MODE_STATUS=''#39#39#39#39'' '' +
        ''AND (SERIAL_OPER_STATUS=''#39#39''IN QUEUE''#39#39''  OR SERIAL_OPER_STATUS=''#39#39''AC'' +
        ''TIVE''#39#39''  OR SERIAL_OPER_STATUS=''#39#39''COMPLETE''#39#39'')}''#39'',Visible={FALSE},Ta'' +
        ''gValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID'' +
        ''=MFI_14FB70EDCDAB2D57E0440003BA560E35'')
  end
  object SKIP: TsfCommandButton
    Left = 308
    Top = 129
    Width = 74
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
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Skip,''#39''Priv={DC_STATUS_DISP = ''#39#39''Y''#39#39'' AND (DAT_COL_STATUS <> ''#39 +
        #39''COMPLETE''#39#39'' AND DAT_COL_STATUS <> ''#39#39''SKIP''#39#39'') AND SKIP_STATUS=''#39#39''SK'' +
        ''IP''#39#39'' AND ALT_MODE_STATUS=''#39#39#39#39'' AND (SERIAL_OPER_STATUS=''#39#39''IN QUEUE'' +
        #39#39''  OR SERIAL_OPER_STATUS=''#39#39''ACTIVE''#39#39'') AND (GROUP_JOB_STATUS <> ''#39 +
        #39''COMPLETE''#39#39'' AND GROUP_JOB_STATUS <> ''#39#39''CANCEL''#39#39'')}''#39'',Visible={},Tag'' +
        ''Value=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID='' +
        ''MFI_140EC42F435E5940E0440003BA560E35'')
  end
  object CALCULATE: TsfCommandButton
    Left = 384
    Top = 129
    Width = 74
    Height = 21
    Hint = ''Calculate''
    Caption = ''Calculate''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (
      
        ''Desc=Calculate,''#39''Priv=DC_STATUS_DISP=''#39#39''Y''#39#39'' AND CALC_DC_FLAG = ''#39#39''Y'' +
        #39#39'' AND ALT_MODE_STATUS=''#39#39#39#39'' AND (SERIAL_OPER_STATUS=''#39#39''IN QUEUE''#39#39 +
        ''  OR SERIAL_OPER_STATUS=''#39#39''ACTIVE''#39#39'')''#39'',Visible=,TagValue=,FKey=,Ac'' +
        ''tion=UDV,UDVType=Update,UDVID=MFI_4335AA65C2F76121E0440003BA041A'' +
        ''64'')
  end
  object _OBJ30: TsfCommandButton
    Left = 80
    Top = 129
    Width = 74
    Height = 21
    Hint = ''R Chart''
    Caption = ''R Chart''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 8
    OtherCommands.Strings = (
      
        ''Desc=R Chart,Priv=''#39''SPC_FLAG = ''#39#39''Y''#39#39#39'',Visible=,TagValue=,FKey=,Ac'' +
        ''tion=UDV,UDVType=Insert,UDVID=MFI_34260'')
  end
  object _OBJ31: TsfCommandButton
    Left = 4
    Top = 129
    Width = 74
    Height = 21
    Hint = ''X Bar Chart''
    Caption = ''X Bar Chart''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 9
    OtherCommands.Strings = (
      
        ''Desc=X Bar Chart,Priv=''#39''SPC_FLAG = ''#39#39''Y''#39#39#39'',Visible=,TagValue=,FKey'' +
        ''=,Action=UDV,UDVType=Insert,UDVID=MFI_34259'')
  end
  object _OBJ32: TsfCommandButton
    Left = 308
    Top = 281
    Width = 74
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
    TabOrder = 10
    OtherCommands.Strings = (
      
        ''Desc=View File,Priv={},''#39''Visible={(FORMAT=''#39#39''Template File''#39#39'' OR FO'' +
        ''RMAT = ''#39#39''File''#39#39'') AND DCVALUE <> ''#39#39''N/A''#39#39''}''#39'',TagValue=,FKey=,Params'' +
        ''Src=@Default,Action=SideNote,SqlID=,Params(''#39''@SubConfig=Slide,''#39'',O'' +
        ''BJECT_ID=:DCVALUE)'')
  end
  object DUMMY_HIDDEN: TsfDBEdit
    Left = 451
    Top = 11
    Width = 10
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 11
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = True
  end
  object _OBJ33: TsfCommandButton
    Left = 156
    Top = 129
    Width = 74
    Height = 21
    Hint = ''X Bar R Chart''
    Caption = ''X Bar R Chart''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 12
    OtherCommands.Strings = (
      
        ''Desc=X Bar R Chart,Priv=''#39''{SPC_FLAG = ''#39#39''Y''#39#39''}''#39'',Visible={},TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Insert,UDVID=MFI_9'' +
        ''14F18F0DAEF476890DA22EC0C24B075'')
  end
  object OrderOperDatColSerials: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderSerialsAllOper''
    LinkedControls.Strings = (
      ''_OBJ5~'')
    ParamsSQLSourceName = ''@CurrentMarker''
    RefreshedSQLSourceName = ''OrderOperDatColSerialValue''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''ORDER_NO~12~Work Order No~N~N~ORDER_CONTROL_TYPE <> ''#39''NONE''#39'' AND G'' +
        ''ROUP_JOB_NO = ''#39#39''~N~ORDER_CONTROL_TYPE <> ''#39''NONE''#39'' AND GROUP_JOB_NO'' +
        '' = ''#39#39''~N~~~~~Default~N~''
      
        ''OPER_NO~5~Oper No~N~N~GROUP_JOB_NO = ''#39#39''~False~GROUP_JOB_NO = ''#39#39''~'' +
        ''N~~~~~Default~N~''
      
        ''SERIAL_NO~8~Serial No~N~N~(ORDER_CONTROL_TYPE=''#39''LOT1''#39'' OR ORDER_CO'' +
        ''NTROL_TYPE=''#39''LOT''#39'' OR ORDER_CONTROL_TYPE=''#39''NONE''#39'') AND GROUP_JOB_NO '' +
        ''= ''#39#39''~N~(ORDER_CONTROL_TYPE=''#39''LOT1''#39'' OR ORDER_CONTROL_TYPE=''#39''LOT''#39'' OR'' +
        '' ORDER_CONTROL_TYPE=''#39''NONE''#39'') AND GROUP_JOB_NO = ''#39#39''~N~~~~~Default~'' +
        ''N~''
      
        ''LOT_NO~8~Lot No~N~N~(ORDER_CONTROL_TYPE<>''#39''LOT''#39'' AND  ORDER_CONTRO'' +
        ''L_TYPE<>''#39''LOT1''#39'') AND GROUP_JOB_NO = ''#39#39''~N~(ORDER_CONTROL_TYPE<>''#39''LO'' +
        ''T''#39'' AND  ORDER_CONTROL_TYPE<>''#39''LOT1''#39'') AND GROUP_JOB_NO = ''#39#39''~N~~~~~'' +
        ''Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''ORDER_NO~Edit''
      ''OPER_NO~Edit''
      ''LOT_NO~Edit''
      ''SERIAL_NO~Edit'')
    TestParamValues.Strings = (
      ''step_key=1''
      ''oper_key=1''
      ''plan_id=1''
      ''plan_version=1''
      ''plan_revision=1''
      ''plan_alterations=1''
      ''alt_id=1''
      ''order_id=1''
      ''dat_col_id=1''
      ''oper_no=1''
      ''group_job_no=1'')
    DataDefinitions.Strings = (
      ''ORDER_NO''
      ''LOT_NO''
      ''SERIAL_NO'')
    ConsolidatedQuery = False
  end
  object GridStatusSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_DatColGridStatusSelect''
    ParamsSQLSourceName = ''OrderOperDatColSerials''
    PublishParams = True
    ConsolidatedQuery = False
  end
  object DatColSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_OrderOperDatSerialSel''
    KeyFieldNames = ''dat_col_id''
    LinkedControls.Strings = (
      ''_OBJ8~''
      ''SKIP~''
      ''_OBJ21~''
      ''CALCULATE~''
      ''_OBJ30~''
      ''_OBJ31~''
      ''_OBJ33~'')
    ParamsSQLSourceName = ''OrderOperDatColSerials,GridStatusSelect''
    RefreshedSQLSourceName = ''OrderOperDatColSerialValue''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''DAT_COL_TYPE~10~Data Collection;Type~N~N~False~N~False~N~~~~~Def'' +
        ''ault~N~''
      
        ''DAT_COL_TITLE_DISP~8~Data Collection;Title~N~N~False~False~True~'' +
        ''N~~~~~Default~N~''
      ''DAT_COL_UOM~5~UOM~N~N~False~N~False~N~~~~~Default~N~''
      
        ''DAT_COL_TITLE~8~Data Collection;Title~N~N~True~N~False~N~~~~~Def'' +
        ''ault~N~''
      ''LOWER_LIMIT~8~LSL~N~N~True~N~False~N~~~~~Default~N~''
      ''TARGET_VALUE~8~Target;Value~N~N~True~N~False~N~~~~~Default~N~''
      ''UPPER_LIMIT~8~USL~N~N~True~N~False~N~~~~~Default~N~''
      ''DAT_COL_CERT~8~Req''#39''d;Cert~N~N~True~N~False~N~~~~~Default~N~''
      ''CALC_DC_FLAG~1~Calculated?~N~N~True~False~False~N~~~~~Default~N~''
      ''OPTIONAL_FLAG~1~Optional?~N~N~True~N~False~N~~~~~Default~N~''
      
        ''ORIENTATION_FLAG~10~Orientation~N~N~True~N~False~N~~~~~Default~N'' +
        ''~''
      
        ''CROSS_ORDER_FLAG~1~Cross;Order?~N~N~True~N~False~N~~~~~Default~N'' +
        ''~''
      ''DAT_COL_ID~0~DAT_COL_ID~N~N~True~N~True~N~~~~~Default~N~''
      
        ''VARIABLE_NAME~10~Variable;Name~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~''
      ''VISIBILITY~5~Visibility~N~N~False~False~False~N~~~~~Default~N~''
      ''DAT_COL_STATUS~5~Status~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_SRL_OPER_DC_NUM1~Edit''
      ''UCF_SRL_OPER_DC_FLAG2~Edit''
      ''UCF_SRL_OPER_DC_FLAG5~Edit''
      ''UCF_SRL_OPER_DC_VCH11~Edit''
      ''LOWER_LIMIT~Edit''
      ''DAT_COL_TITLE_DISP~Edit''
      ''TARGET_VALUE~Edit''
      ''DAT_COL_ID~Edit'')
    TestParamValues.Strings = (
      ''order_id=1''
      ''oper_key=1''
      ''step_key=1''
      ''dat_col_id=1'')
    OtherCommands.Strings = (
      
        ''Desc=X-Bar Chart,Priv=''#39''{SPC_FLAG = ''#39#39''Y''#39#39''}''#39'',Visible={},TagValue=,'' +
        ''FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Insert,UDVID=MFI_342'' +
        ''59''
      
        ''Desc=R Chart,Priv=''#39''SPC_FLAG = ''#39#39''Y''#39#39#39'',Visible=,TagValue=,FKey=,Ac'' +
        ''tion=UDV,UDVType=Insert,UDVID=MFI_34260''
      
        ''Desc=X-Bar R Chart,Priv=''#39''{SPC_FLAG = ''#39#39''Y''#39#39''}''#39'',Visible={},TagValue'' +
        ''=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Insert,UDVID=MFI_9'' +
        ''14F18F0DAEF476890DA22EC0C24B075'')
    DataDefinitions.Strings = (
      ''DAT_COL_TYPE''
      ''DAT_COL_TITLE_DISP''
      ''DAT_COL_UOM''
      ''DAT_COL_TITLE''
      ''LOWER_LIMIT''
      ''TARGET_VALUE''
      ''UPPER_LIMIT''
      ''DAT_COL_CERT''
      ''CALC_DC_FLAG''
      ''OPTIONAL_FLAG''
      ''ORIENTATION_FLAG''
      ''CROSS_ORDER_FLAG''
      ''DAT_COL_ID''
      ''VARIABLE_NAME''
      ''VISIBILITY'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''DatColSelect''
  end
  object OrderOperDatColSerialValue: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_DC_HISTORY''
    DeleteSqlId = ''MFI_WID_ORDEROPERDATCOLEXEPREDEL''
    KeyFieldNames = ''dat_col_id''
    LinkedControls.Strings = (
      ''_OBJ32~''
      ''_OBJ29~''
      ''serial_values~''
      ''_OBJ28~'')
    ParamsSQLSourceName = ''DatColSelect''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''DC_STATUS~1~ ~N~N~False~False~False~N~~@StdMarkup(DC_STATUS)~~~D'' +
        ''efault~N~~''
      
        ''LCL~5~LCL~N~N~FORMAT = ''#39''File''#39''~False~FORMAT = ''#39''File''#39''~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''XBAR_CPL_VALUE~5~CPL~N~N~FORMAT = ''#39''File''#39'' OR FORMAT = ''#39''Accept/Rej'' +
        ''ect''#39''~N~FORMAT = ''#39''File''#39''~N~~~~~Default~N~~''
      
        ''DCVALUE~5~Value~N~N~FORMAT = ''#39''File''#39'' OR FORMAT = ''#39''Accept/Reject''#39'' '' +
        ''OR FORMAT = ''#39''Template File''#39''~N~FORMAT = ''#39''File''#39'' OR FORMAT = ''#39''Templ'' +
        ''ate File''#39''~N~~~~~Default~N~~''
      
        ''COMPLETE_QTY~6~Complete;Qty~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~''
      ''MACHINE_ID~5~Machine;Id~N~N~False~False~False~N~~~~~Default~N~~''
      ''MACHINE_NO~5~Machine;No~N~N~False~False~False~N~~~~~Default~N~~''
      
        ''FILE_NAME~15~File~N~N~FORMAT <> ''#39''File''#39'' AND FORMAT <> ''#39''Template F'' +
        ''ile''#39''~False~FORMAT <> ''#39''File''#39'' AND FORMAT <> ''#39''Template File''#39''~N~~~~~'' +
        ''Default~N~~''
      
        ''ACCEPT_REJECT~5~Accept/Reject~N~N~FORMAT <> ''#39''Accept/Reject''#39''~Fals'' +
        ''e~FORMAT <> ''#39''Accept/Reject''#39''~N~~~~~Default~N~~''
      
        ''UCL~5~UCL~N~N~FORMAT = ''#39''File''#39''~False~FORMAT = ''#39''File''#39''~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''XBAR_CPU_VALUE~5~CPU~N~N~FORMAT = ''#39''File''#39'' OR FORMAT = ''#39''Accept/Rej'' +
        ''ect''#39''~N~FORMAT = ''#39''File''#39'' OR FORMAT = ''#39''Accept/Reject''#39''~N~~~~~Default'' +
        ''~N~~''
      
        ''LOWER_LIMIT~5~LSL~N~N~FORMAT = ''#39''File''#39'' OR FORMAT = ''#39''Accept/Reject'' +
        #39''~False~FORMAT = ''#39''File''#39'' OR FORMAT = ''#39''Accept/Reject''#39''~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''UPPER_LIMIT~5~USL~N~N~FORMAT = ''#39''File''#39'' OR FORMAT = ''#39''Accept/Reject'' +
        #39''~False~FORMAT = ''#39''File''#39'' OR FORMAT = ''#39''Accept/Reject''#39''~N~~~~~Defaul'' +
        ''t~N~~''
      ''OUTSIDE~10~Outside;Limits?~N~N~True~N~False~N~~~~~Default~N~~''
      ''COMMENTS~8~Comments~N~N~False~N~False~N~~~~~Default~N~~''
      ''LAST_ACTION~6~Action~N~N~False~N~False~N~~~~~Default~N~~''
      
        ''UPDT_USERID~8~Update;User ID~N~N~False~N~False~N~MFI_2869~~~~Def'' +
        ''ault~N~~''
      ''TIME_STAMP~12~Update;Time~N~N~False~N~False~N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH1~50~Ucf Srl Oper DC Vch1~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH2~50~Ucf Srl Oper DC Vch2~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH3~50~Ucf Srl Oper DC Vch3~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH4~50~Ucf Srl Oper DC Vch4~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH5~50~Ucf Srl Oper DC Vch5~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH6~50~Ucf Srl Oper DC Vch6~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH7~50~Ucf Srl Oper DC Vch7~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH8~50~Ucf Srl Oper DC Vch8~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH9~50~Ucf Srl Oper DC Vch9~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH10~50~Ucf Srl Oper DC Vch10~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH11~50~Ucf Srl Oper DC Vch11~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH12~50~Ucf Srl Oper DC Vch12~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH13~50~Ucf Srl Oper DC Vch13~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH14~50~Ucf Srl Oper DC Vch14~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_VCH15~50~Ucf Srl Oper DC Vch15~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_NUM1~10~Ucf Srl Oper DC Num1~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_NUM2~10~Ucf Srl Oper DC Num2~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_NUM3~10~Ucf Srl Oper DC Num3~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_NUM4~10~Ucf Srl Oper DC Num4~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_NUM5~10~Ucf Srl Oper DC Num5~N~N~True~N~False~N~'' +
        ''~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_FLAG1~1~Ucf Srl Oper DC Flag1~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_FLAG2~1~Ucf Srl Oper DC Flag2~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_FLAG3~1~Ucf Srl Oper DC Flag3~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_FLAG4~1~Ucf Srl Oper DC Flag4~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_FLAG5~1~Ucf Srl Oper DC Flag5~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_DATE1~18~Ucf Srl Oper DC Date1~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_DATE2~18~Ucf Srl Oper DC Date2~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_DATE3~18~Ucf Srl Oper DC Date3~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_DATE4~18~Ucf Srl Oper DC Date4~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~''
      
        ''UCF_SRL_OPER_DC_DATE5~18~Ucf Srl Oper DC Date5~N~N~True~N~False~'' +
        ''N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_SRL_OPER_DC_DATE5~Edit''
      ''UCF_SRL_OPER_DC_DATE3~Edit''
      ''UCF_SRL_OPER_DC_DATE2~Edit''
      ''UCF_SRL_OPER_DC_DATE1~Edit''
      ''UCF_SRL_OPER_DC_FLAG5~Edit''
      ''UCF_SRL_OPER_DC_FLAG4~Edit''
      ''UCF_SRL_OPER_DC_FLAG3~Edit''
      ''UCF_SRL_OPER_DC_FLAG2~Edit''
      ''UCF_SRL_OPER_DC_FLAG1~Edit''
      ''UCF_SRL_OPER_DC_NUM3~Edit''
      ''UCF_SRL_OPER_DC_NUM2~Edit''
      ''UCF_SRL_OPER_DC_NUM1~Edit''
      ''UCF_SRL_OPER_DC_VCH15~Edit''
      ''UCF_SRL_OPER_DC_VCH14~Edit''
      ''UCF_SRL_OPER_DC_VCH13~Edit''
      ''UCF_SRL_OPER_DC_VCH11~Edit''
      ''UCF_SRL_OPER_DC_VCH10~Edit''
      ''UCF_SRL_OPER_DC_VCH9~Edit''
      ''UCF_SRL_OPER_DC_VCH8~Edit''
      ''UCF_SRL_OPER_DC_VCH7~Edit''
      ''MACHINE_ID~Edit''
      ''MACHINE_NO~Edit''
      ''UPPER_LIMIT~Edit''
      ''FILE_NAME~Edit''
      ''XBAR_CPL_VALUE~Edit''
      ''ACCEPT_REJECT~Edit''
      ''UCL~Edit''
      ''DCVALUE~Edit''
      ''COMMENTS~Edit''
      ''LAST_ACTION~Edit''
      ''UPDT_USERID~Edit'')
    TestParamValues.Strings = (
      ''MI=1''
      ''SS=1''
      ''order_id=1''
      ''oper_key=1''
      ''step_key=1''
      ''lot_id=1''
      ''serial_id=1''
      ''dat_col_id='')
    InputUDVId = ''MFI_1010042''
    InsertEnabledExpr = 
      ''ORDER_STATUS<>''#39''CLOSE''#39'' AND GROUP_JOB_STATUS <> ''#39''COMPLETE''#39'' AND GRO'' +
      ''UP_JOB_STATUS <> ''#39''CANCEL''#39
    UpdateEnabledExpr = 
      ''ORDER_STATUS<>''#39''CLOSE''#39'' AND GROUP_JOB_STATUS <> ''#39''COMPLETE''#39'' AND GRO'' +
      ''UP_JOB_STATUS <> ''#39''CANCEL''#39
    DeleteEnabledExpr = 
      ''FORMAT = ''#39''Template File''#39'' AND ORDER_STATUS<>''#39''CLOSE''#39'' AND GROUP_JOB'' +
      ''_STATUS <> ''#39''COMPLETE''#39'' AND GROUP_JOB_STATUS <> ''#39''CANCEL''#39
    InsUpdDelEnabledExpr = 
      ''ORDER_STATUS<>''#39''CLOSE''#39'' AND GROUP_JOB_STATUS <> ''#39''COMPLETE''#39'' AND GRO'' +
      ''UP_JOB_STATUS <> ''#39''CANCEL''#39
    DataDefinitions.Strings = (
      ''DC_STATUS''
      ''LCL''
      ''XBAR_CPL_VALUE''
      ''DCVALUE''
      ''COMPLETE_QTY''
      ''MACHINE_ID''
      ''MACHINE_NO''
      ''FILE_NAME''
      ''UCL''
      ''ACCEPT_REJECT''
      ''XBAR_CPU_VALUE''
      ''LOWER_LIMIT''
      ''UPPER_LIMIT''
      ''OUTSIDE''
      ''COMMENTS''
      ''LAST_ACTION''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''UCF_SRL_OPER_DC_VCH1''
      ''UCF_SRL_OPER_DC_VCH2''
      ''UCF_SRL_OPER_DC_VCH3''
      ''UCF_SRL_OPER_DC_VCH4''
      ''UCF_SRL_OPER_DC_VCH5''
      ''UCF_SRL_OPER_DC_VCH6''
      ''UCF_SRL_OPER_DC_VCH7''
      ''UCF_SRL_OPER_DC_VCH8''
      ''UCF_SRL_OPER_DC_VCH9''
      ''UCF_SRL_OPER_DC_VCH10''
      ''UCF_SRL_OPER_DC_VCH11''
      ''UCF_SRL_OPER_DC_VCH12''
      ''UCF_SRL_OPER_DC_VCH13''
      ''UCF_SRL_OPER_DC_VCH14''
      ''UCF_SRL_OPER_DC_VCH15''
      ''UCF_SRL_OPER_DC_NUM1''
      ''UCF_SRL_OPER_DC_NUM2''
      ''UCF_SRL_OPER_DC_NUM3''
      ''UCF_SRL_OPER_DC_NUM4''
      ''UCF_SRL_OPER_DC_NUM5''
      ''UCF_SRL_OPER_DC_FLAG1''
      ''UCF_SRL_OPER_DC_FLAG2''
      ''UCF_SRL_OPER_DC_FLAG3''
      ''UCF_SRL_OPER_DC_FLAG4''
      ''UCF_SRL_OPER_DC_FLAG5''
      ''UCF_SRL_OPER_DC_DATE1''
      ''UCF_SRL_OPER_DC_DATE2''
      ''UCF_SRL_OPER_DC_DATE3''
      ''UCF_SRL_OPER_DC_DATE4''
      ''UCF_SRL_OPER_DC_DATE5'')
    ConsolidatedQuery = False
  end
  object SelectDummyCalledFrom2: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_SelectDummyCalledFrom''
    ParamsSQLSourceName = ''@CurrentMarker''
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

