
set define off
prompt ---------------------------------------;
prompt Executing ... MFI_22438.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='OB1_1000385';
v_udv_id sfcore_udv_lib.udv_id%type  :='MFI_22438';
v_udv_tag sfcore_udv_lib.udv_tag%type :='WID_AUX_VIEW';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1350
  Height = 50
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
  object signon: TsfCommandButton
    Left = 76
    Top = 4
    Width = 63
    Height = 35
    Hint = ''Oper Sign On''
    Caption = ''Sign On''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 0
    OtherCommands.Strings = (
      
        ''Desc=Oper Sign On,Priv=''#39''{OPER_STATUS=''#39#39''ACTIVE''#39#39'' OR OPER_STATUS=''#39 +
        #39''IN QUEUE''#39#39'' AND ORDER_ID <> ''#39#39#39#39''}''#39'',''#39''Visible={SIGNON=''#39#39''Y''#39#39'' AND (M'' +
        ''RO_FLAG=''#39#39''N''#39#39'' OR ORDER_STATUS<>''#39#39''PENDING''#39#39'') AND SIGNOFF_ALL_OVER'' +
        ''IDE_FLAG=''#39#39''N''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,U'' +
        ''DVType=Insert,UDVID=MF1_1001020'')
  end
  object signoff: TsfCommandButton
    Left = 76
    Top = 4
    Width = 63
    Height = 35
    Hint = ''Oper Sign Off''
    Caption = ''Sign Off''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 1
    OtherCommands.Strings = (
      
        ''Desc=Oper Sign Off,Priv=''#39''{OPER_STATUS=''#39#39''ACTIVE''#39#39'' OR OPER_STATUS='' +
        #39#39''IN QUEUE''#39#39'' AND ORDER_ID <> ''#39#39#39#39''}''#39'',''#39''Visible={SIGNON=''#39#39''N''#39#39'' AND ('' +
        ''MRO_FLAG=''#39#39''N''#39#39'' OR ORDER_STATUS<>''#39#39''PENDING''#39#39'') AND SIGNOFF_ALL_OVE'' +
        ''RIDE_FLAG=''#39#39''N''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,'' +
        ''UDVType=Insert,UDVID=MF1_1001020'')
  end
  object Display: TsfCommandButton
    Left = 261
    Top = 4
    Width = 60
    Height = 35
    Hint = ''Display''
    Caption = ''Display''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    OtherCommands.Strings = (
      
        ''Desc=Unit Information,Priv=''#39''{ORDER_ID <> ''#39#39#39#39''}''#39'',''#39''Visible={ORDER_'' +
        ''ID <> ''#39#39#39#39'' AND (SERIAL_NO <> ''#39#39''N/A''#39#39'' OR LOT_NO <> ''#39#39''N/A''#39#39'')}''#39'',Tag'' +
        ''Value=,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourceNa'' +
        ''me=OrderUnitInfoSelect,RefreshedSQLSourceName=,SqlID=MFI_WID_LAU'' +
        ''NCHUNITINFO''
      
        ''Desc=Display Operation  Sequence,Priv=''#39''{ORDER_ID <> ''#39#39#39#39'' AND DIS'' +
        ''PLAY_SEQUENCE=''#39#39''Flow Diagram''#39#39''}''#39'',Visible=''#39''{MRO_FLAG=''#39#39''N''#39#39'' AND  D'' +
        ''ISPLAY_SEQUENCE=''#39#39''Flow Diagram''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@De'' +
        ''fault,Action=Invoke,Tool=FlowDiagrams.OrderOFD,ReportToolId=,Par'' +
        ''ams(ORDER_ID = :ORDER_ID)''
      
        ''Desc=Display Operation Sequence,Priv=''#39''{ORDER_ID <> ''#39#39#39#39'' AND DISP'' +
        ''LAY_SEQUENCE=''#39#39''Execution Order''#39#39''}''#39'',Visible=''#39''{ORDER_ID <> ''#39#39#39#39'' AN'' +
        ''D DISPLAY_SEQUENCE=''#39#39''Execution Order''#39#39''}''#39'',TagValue=,FKey=,ParamsS'' +
        ''rc=@Default,Action=Invoke,Tool=FlowDiagrams.OrderSwimLanes,Repor'' +
        ''tToolId=,Params(ORDER_ID = :ORDER_ID)'')
  end
  object InitiateDisc: TsfCommandButton
    Left = 144
    Top = 4
    Width = 112
    Height = 35
    Hint = ''Create Discrepancy''
    Caption = ''Initiate Discrepancy''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Create Discrepancy,''#39''Priv={OPER_NO <> ''#39#39#39#39'' AND (OPER_STATUS<'' +
        ''>''#39#39''CLOSE''#39#39'' AND OPER_STATUS<>''#39#39''EXCLUDE''#39#39'' AND OPER_STATUS<>''#39#39''CANCE'' +
        ''L''#39#39'') AND ORDER_ID <> ''#39#39#39#39''}''#39'',Visible=''#39''{MRO_FLAG=''#39#39''N''#39#39'' OR ORDER_ST'' +
        ''ATUS<>''#39#39''PENDING''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=UD'' +
        ''V,UDVType=Insert,UDVID=MFI_3570'')
  end
  object nexttask: TsfCommandButton
    Left = 6
    Top = 4
    Width = 65
    Height = 35
    Hint = ''Next Task''
    Caption = ''Next''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clGreen
    Font.Height = -21
    Font.Name = ''Tahoma''
    Font.Style = [fsBold]
    ParentFont = False
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Next Task,Priv=''#39''{OPER_NO <> ''#39#39#39#39'' AND OPER_STATUS<>''#39#39''PENDING'' +
        #39#39'' AND OPER_STATUS<>''#39#39''CLOSE''#39#39'' AND OPER_STATUS<>''#39#39''CANCEL''#39#39'' AND OR'' +
        ''DER_ID <> ''#39#39#39#39'' AND OPER_STATUS<>''#39#39''EXCLUDE''#39#39'' AND OPERATION_OVERLA'' +
        ''P_FLAG<>''#39#39''Y''#39#39''}''#39'',Visible=''#39''{MRO_FLAG=''#39#39''N''#39#39'' OR ORDER_STATUS<>''#39#39''PEND'' +
        ''ING''#39#39'' AND OPERATION_OVERLAP_FLAG<>''#39#39''Y''#39#39''}''#39'',TagValue=,FKey=,Params'' +
        ''Src=@Default,Action=ExecSql,''#39''ParamsSqlSourceName=@ToolScope,@Coo'' +
        ''kies''#39'',RefreshedSQLSourceName=,SqlID=MFI_WID_START_NEXT_TASK'')
  end
  object _OBJ3: TsfCommandButton
    Left = 875
    Top = 4
    Width = 74
    Height = 35
    Caption = ''Select Units''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Select Units for Execution,Priv=''#39''OPER_STATUS<>''#39#39''PENDING''#39#39'' A'' +
        ''ND ORDER_ID <> ''#39#39#39#39'' AND ORDER_STATUS<>''#39#39''CLOSE''#39#39#39'',''#39''Visible=(ORDER'' +
        ''_CONTROL_TYPE=''#39#39''SERIAL''#39#39'' OR ORDER_CONTROL_TYPE=''#39#39''SERIAL-LOT''#39#39'') A'' +
        ''ND ORDER_STATUS<>''#39#39''PENDING''#39#39#39'',TagValue=,FKey=,Action=UDV,UDVType'' +
        ''=Insert,UDVID=MFI_5602BC939F43450DE0440003BA041A64'')
  end
  object ReleaseOrder: TsfCommandButton
    Left = 77
    Top = 4
    Width = 83
    Height = 35
    Hint = ''Release Order''
    Caption = ''Release Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Release Order,Priv=''#39''{ORDER_ID <> ''#39#39#39#39''}''#39'',Visible=''#39''{MRO_FLAG='' +
        #39#39''Y''#39#39'' AND ORDER_STATUS=''#39#39''PENDING''#39#39''}''#39'',TagValue=,FKey=,Action=UDV,'' +
        ''UDVType=Insert,UDVID=MFI_7A2833F44F151DCCE04400144FA7B7D2'')
  end
  object StartAlt: TsfCommandButton
    Left = 167
    Top = 4
    Width = 88
    Height = 35
    Hint = ''Start Alteration''
    Caption = ''Start Alteration''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (
      
        ''Desc=Start Alteration,Priv=''#39''{ORDER_ID <> ''#39#39#39#39''}''#39'',Visible=''#39''{MRO_FL'' +
        ''AG=''#39#39''Y''#39#39'' AND ORDER_STATUS=''#39#39''PENDING''#39#39'' AND OPERATION_OVERLAP_FLAG'' +
        ''<>''#39#39''Y''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=ExecSql,Para'' +
        ''msSqlSourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_PLG'' +
        ''_SHOWORDERALTERATIONUDV'')
  end
  object PrintWorkOrder: TsfCommandButton
    Left = 399
    Top = 4
    Width = 125
    Height = 35
    Hint = ''Print Work Order''
    Caption = ''Print Work Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 8
    OtherCommands.Strings = (
      
        ''Desc=Print Work Order,Priv=''#39''{ORDER_ID <> ''#39#39#39#39''}''#39'',Visible=''#39''{MRO_FL'' +
        ''AG=''#39#39''Y''#39#39'' AND ORDER_STATUS=''#39#39''PENDING''#39#39''}''#39'',TagValue=,FKey=,Action=I'' +
        ''nvoke,Tool=Reporting,ReportToolId=,Params(''#39''OBJECT_ID=''#39#39''MFI_35555'' +
        #39#39'',''#39'',''#39''ORDER_ID=:ORDER_ID,''#39'',@UserId=:@UserId)'')
  end
  object ApproveScopeChg: TsfCommandButton
    Left = 397
    Top = 4
    Width = 129
    Height = 35
    Hint = ''Approve Scope Changes''
    Caption = ''Approve Scope Changes''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 9
    OtherCommands.Strings = (
      
        ''Desc=Approve Scope Changes,Priv=''#39''{WORK_SCOPE_HOLD <>''#39#39#39#39'' AND ORD'' +
        ''ER_ID <> ''#39#39#39#39''}''#39'',Visible=''#39''{MRO_FLAG=''#39#39''Y''#39#39'' AND WORK_SCOPE_HOLD = ''#39 +
        #39''-1''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=In'' +
        ''sert,UDVID=MFI_78BEF091A2A04CE1E04400144FA7B7D2'')
  end
  object _OBJ9: TsfBevel
    Left = 74
    Top = 4
    Width = 410
    Height = 35
    Shape = bsSpacer
    Hidden = ''False''
    HighColor = ''0x808080''
    LowColor = ''0xFFFFFF''
    LineWidth = 1
  end
  object _OBJ13: TsfDBEdit
    Left = 951
    Top = 4
    Width = 160
    Height = 35
    EmptyTextStyle = []
    AutoSelect = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 10
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ14: TsfCommandButton
    Left = 532
    Top = 4
    Width = 95
    Height = 35
    Caption = ''Need Assistance''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 11
    OtherCommands.Strings = (
      
        ''Desc=Create Part Delay Hold,''#39''Priv={@haspriv(''#39#39''ORDER_HOLD_CREATE''#39 +
        #39'') AND @GetParamSourceValue(@ToolScope,''#39#39''ORDER_ID''#39#39'') <> ''#39#39#39#39'' AND'' +
        '' @GetParamSourceValue(@ToolScope,''#39#39''ORDER_STATUS''#39#39'') <> ''#39#39''CLOSE''#39#39''}'' +
        #39'',Visible={},TagValue=PART,FKey=,Action=UDV,UDVType=Insert,UDVID'' +
        ''=MFI_5A52C5DC2D1245BFA12CC678B55FD6AF''
      
        ''Desc=Create Tool Delay Hold,''#39''Priv={@haspriv(''#39#39''ORDER_HOLD_CREATE''#39 +
        #39'') AND @GetParamSourceValue(@ToolScope,''#39#39''ORDER_ID''#39#39'') <> ''#39#39#39#39'' AND'' +
        '' @GetParamSourceValue(@ToolScope,''#39#39''ORDER_STATUS''#39#39'') <> ''#39#39''CLOSE''#39#39''}'' +
        #39'',Visible={},TagValue=TOOL,FKey=,Action=UDV,UDVType=Insert,UDVID'' +
        ''=MFI_5A52C5DC2D1245BFA12CC678B55FD6AF''
      
        ''Desc=Create Help Hold,''#39''Priv={@haspriv(''#39#39''ORDER_HOLD_CREATE''#39#39'') AND'' +
        '' @GetParamSourceValue(@ToolScope,''#39#39''ORDER_ID''#39#39'') <> ''#39#39#39#39'' AND @GetP'' +
        ''aramSourceValue(@ToolScope,''#39#39''ORDER_STATUS''#39#39'') <> ''#39#39''CLOSE''#39#39''}''#39'',Visi'' +
        ''ble={},TagValue=,FKey=,Action=UDV,UDVType=Insert,UDVID=MFI_1B1BD'' +
        ''E6CE6004E10AB6567C13B9C1D19'')
  end
  object Partial_Complete: TsfCommandButton
    Left = 633
    Top = 4
    Width = 100
    Height = 35
    Hint = ''Partial Completion''
    Caption = ''Partial Completion''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 12
    OtherCommands.Strings = (
      
        ''Desc=Partial Completion,''#39''Priv={(OPER_NO <> ''#39#39#39#39'' AND (OPER_STATUS'' +
        ''<>''#39#39''CLOSE''#39#39'' AND OPER_STATUS<>''#39#39''EXCLUDE''#39#39'' AND OPER_STATUS<>''#39#39''CANC'' +
        ''EL''#39#39'') AND ORDER_ID <> ''#39#39#39#39'' AND ORDER_STATUS <> ''#39#39''CLOSE''#39#39'') AND OP'' +
        ''ERATION_OVERLAP_FLAG<>''#39#39''Y''#39#39''}''#39'',Visible={},TagValue=,FKey=,Action='' +
        ''UDV,UDVType=Insert,UDVID=MFI_FBA12B1652C84C569A7454B6EF7AA09B'')
  end
  object SWIM_LANES: TsfCommandButton
    Left = 739
    Top = 4
    Width = 160
    Height = 35
    Hint = ''Swim Lanes''
    Caption = ''Display Operation Sequence''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 13
    OtherCommands.Strings = (
      
        ''Desc=Swim Lanes,Priv={},Visible=''#39''{MRO_FLAG=''#39#39''Y''#39#39'' AND ORDER_STATU'' +
        ''S=''#39#39''PENDING''#39#39'' AND ORDER_ID<>''#39#39#39#39'' AND DISPLAY_SEQUENCE=''#39#39''Executio'' +
        ''n Order''#39#39'' AND OPERATION_OVERLAP_FLAG<>''#39#39''Y''#39#39''}''#39'',TagValue=,FKey=,Ac'' +
        ''tion=Invoke,Tool=FlowDiagrams.OrderSwimLanes,ReportToolId=,Param'' +
        ''s(ORDER_ID=:ORDER_ID)'')
  end
  object CompleteBatch: TsfCommandButton
    Left = 633
    Top = 4
    Width = 100
    Height = 35
    Hint = ''Complete Unit''
    Caption = ''Complete Cycle''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 14
    OtherCommands.Strings = (
      
        ''Desc=Complete Unit,''#39''Priv={UNIT_PROCESSING=''#39#39''Cycle''#39#39'' AND AUTO_CYC'' +
        ''LE_FLAG=''#39#39''N''#39#39'' AND OPER_NO <> ''#39#39#39#39'' AND (OPER_STATUS<>''#39#39''CLOSE''#39#39'' AN'' +
        ''D OPER_STATUS<>''#39#39''EXCLUDE''#39#39'' AND OPER_STATUS<>''#39#39''CANCEL''#39#39'') AND ORDE'' +
        ''R_ID <> ''#39#39#39#39'' AND ORDER_STATUS <> ''#39#39''CLOSE''#39#39''}''#39'',Visible=''#39''{UNIT_PROC'' +
        ''ESSING=''#39#39''Cycle''#39#39'' AND AUTO_CYCLE_FLAG=''#39#39''N''#39#39''}''#39'',TagValue=,FKey=,Par'' +
        ''amsSrc=@Default,Action=UDV,UDVType=Insert,UDVID=MFI_26BA015BA77B'' +
        ''483ABD89BF1A0B8D8C6E'')
  end
  object Partial_Complete_NoBatch: TsfCommandButton
    Left = 633
    Top = 4
    Width = 100
    Height = 35
    Hint = ''Partial Complete''
    Caption = ''Partial Complete''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 15
    OtherCommands.Strings = (
      
        ''Desc=Partial Complete,''#39''Priv={OPERATION_OVERLAP_FLAG=''#39#39''Y''#39#39'' AND UN'' +
        ''IT_PROCESSING<>''#39#39''Cycle''#39#39'' AND OPER_NO <> ''#39#39#39#39'' AND (OPER_STATUS<>''#39 +
        #39''CLOSE''#39#39'' AND OPER_STATUS<>''#39#39''PENDING''#39#39'' AND OPER_STATUS<>''#39#39''EXCLUDE'' +
        #39#39'' AND OPER_STATUS<>''#39#39''CANCEL''#39#39'') AND ORDER_ID <> ''#39#39#39#39'' AND ORDER_S'' +
        ''TATUS <> ''#39#39''CLOSE''#39#39''}''#39'',Visible=''#39''{OPERATION_OVERLAP_FLAG=''#39#39''Y''#39#39'' AND '' +
        ''UNIT_PROCESSING<>''#39#39''Cycle''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,'' +
        ''Action=UDV,UDVType=Update,UDVID=MFI_4B1FB46255554CCB875F09AE4F39'' +
        ''3095'')
  end
  object Complete_operation: TsfCommandButton
    Left = 6
    Top = 4
    Width = 65
    Height = 35
    Hint = ''Complete Operation''
    Caption = ''Complete''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -12
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 16
    OtherCommands.Strings = (
      
        ''Desc=Complete Operation,''#39''Priv={OPER_NO <> ''#39#39#39#39'' AND (OPER_STATUS '' +
        ''= ''#39#39''ACTIVE''#39#39'' OR OPER_STATUS = ''#39#39''IN QUEUE''#39#39'') AND ORDER_ID <> ''#39#39#39#39 +
        '' AND OPERATION_OVERLAP_FLAG=''#39#39''Y''#39#39''}''#39'',Visible=''#39''{OPERATION_OVERLAP_'' +
        ''FLAG=''#39#39''Y''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=ExecSql,P'' +
        ''aramsSqlSourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_'' +
        ''WID_CompleteOperationWithReconcileScrap'')
  end
  object Scrap: TsfCommandButton
    Left = 397
    Top = 4
    Width = 129
    Height = 35
    Hint = ''Scrap Units''
    Caption = ''Scrap Units''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 17
    OtherCommands.Strings = (
      
        ''Desc=Scrap,''#39''Priv={OPER_NO <> ''#39#39#39#39'' AND (OPER_STATUS = ''#39#39''ACTIVE''#39#39'' '' +
        ''OR OPER_STATUS = ''#39#39''IN QUEUE''#39#39'') AND ORDER_ID <> ''#39#39#39#39'' AND ORDER_ST'' +
        ''ATUS<>''#39#39''PENDING''#39#39''}''#39'',''#39''Visible={WORK_SCOPE_HOLD =''#39#39#39#39'' AND (OPER_ST'' +
        ''ATUS = ''#39#39''ACTIVE''#39#39'' OR OPER_STATUS = ''#39#39''IN QUEUE''#39#39'')}''#39'',TagValue=,FKe'' +
        ''y=,ParamsSrc=@Default,Action=UDV,UDVType=Insert,UDVID=MFI_128774'' +
        ''93CED647DC9B01D4DD0C73F69E'')
  end
  object labelPrint: TsfCommandButton
    Left = 328
    Top = 4
    Width = 63
    Height = 35
    Caption = ''Print Label''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 18
    OtherCommands.Strings = (
      
        ''Desc=Print Label,Priv=''#39''{REPORT_ID <> ''#39#39#39#39'' AND OPER_NO <> ''#39#39#39#39'' AN'' +
        ''D ORDER_ID <> ''#39#39#39#39''}''#39'',Visible={1=0},TagValue=,FKey=,ParamsSrc=@De'' +
        ''fault,Action=Invoke,Tool=Reporting,ReportToolId=,Params(''#39''OBJECT_'' +
        ''ID=''#39#39''MFI_AD1C991ED4C048F3B696F59FB58C3EF7''#39#39'',''#39'',''#39''@REPORT=''#39#39''Label R'' +
        ''eport''#39#39'',''#39'',''#39''ORDER_ID=:ORDER_ID,''#39'',OPER_KEY=:OPER_KEY)'')
  end
  object _OBJ15: TsfCommandButton
    Left = 739
    Top = 4
    Width = 130
    Height = 35
    Caption = ''Display Inspection Order''
    Font.Charset = ANSI_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 19
    OtherCommands.Strings = (
      
        ''Desc=Display Inspection Order,Priv={},Visible=''#39''{INSP_ORDER_ID <>'' +
        '' ''#39#39#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=In'' +
        ''sert,UDVID=MFI_3BD92F558CC540B0BECADCD13673D86F'')
  end
  object _OBJ16: TsfCommandButton
    Left = 1115
    Top = 4
    Width = 70
    Height = 35
    Caption = ''Collapse All''
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    ShowHint = True
    TabOrder = 20
    OtherCommands.Strings = (
      
        ''Desc=collapseAll,Priv={},Visible=''#39''{@ClientType <> ''#39#39''Web''#39#39''}''#39'',TagV'' +
        ''alue=Collapse,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlS'' +
        ''ourceName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_SFWID_COL'' +
        ''LAPSE_ALL'')
  end
  object expandAll: TsfCommandButton
    Left = 1191
    Top = 4
    Width = 70
    Height = 35
    Caption = ''Expand All''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 21
    OtherCommands.Strings = (
      
        ''Desc=expandAll,Priv={},Visible=''#39''{@ClientType <> ''#39#39''Web''#39#39''}''#39'',TagVal'' +
        ''ue=Expand,FKey=,ParamsSrc=@Default,Action=ExecSql,ParamsSqlSourc'' +
        ''eName=@ToolScope,RefreshedSQLSourceName=,SqlID=MFI_SFWID_COLLAPS'' +
        ''E_ALL'')
  end
  object SignOnOverride: TsfCommandButton
    Left = 76
    Top = 4
    Width = 63
    Height = 35
    Hint = ''Operation Sign On''
    Caption = ''Sign On''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 22
    OtherCommands.Strings = (
      
        ''Desc=Sign On Override All,Priv=''#39''{OPER_STATUS=''#39#39''ACTIVE''#39#39'' OR OPER_'' +
        ''STATUS=''#39#39''IN QUEUE''#39#39'' AND ORDER_ID <> ''#39#39#39#39''}''#39'',''#39''Visible={SIGNON=''#39#39''Y''#39 +
        #39'' AND (MRO_FLAG=''#39#39''N''#39#39'' OR ORDER_STATUS<>''#39#39''PENDING''#39#39'') AND SIGNOFF_'' +
        ''ALL_OVERIDE_FLAG=''#39#39''Y''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Acti'' +
        ''on=ExecSql,ParamsSqlSourceName=dummy,RefreshedSQLSourceName=,Sql'' +
        ''ID=SfwidSignOnOffOverideIU'')
  end
  object SignoffOverride: TsfCommandButton
    Left = 76
    Top = 4
    Width = 63
    Height = 35
    Caption = ''Sign Off''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 23
    OtherCommands.Strings = (
      
        ''Desc=Sign Off Override All,Priv=''#39''{OPER_STATUS=''#39#39''ACTIVE''#39#39'' OR OPER'' +
        ''_STATUS=''#39#39''IN QUEUE''#39#39'' AND ORDER_ID <> ''#39#39#39#39''}''#39'',''#39''Visible={SIGNON=''#39#39''N'' +
        #39#39'' AND (MRO_FLAG=''#39#39''N''#39#39'' OR ORDER_STATUS<>''#39#39''PENDING''#39#39'') AND SIGNOFF'' +
        ''_ALL_OVERIDE_FLAG=''#39#39''Y''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Act'' +
        ''ion=ExecSql,ParamsSqlSourceName=dummy,RefreshedSQLSourceName=,Sq'' +
        ''lID=SfwidSignOnOffOverideIU'')
  end
  object dummy: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderOperInfo4Buttons''
    LinkedControls.Strings = (
      ''signon''
      ''signoff''
      ''Display''
      ''signoff~''
      ''InitiateDisc''
      ''ReleaseOrder''
      ''SWIM_LANES~''
      ''Partial_Complete''
      ''nexttask~''
      ''signon~''
      ''Partial_Complete_NoBatch~''
      ''CompleteBatch~''
      ''Complete_operation~''
      ''labelPrint~''
      ''SignOnOverride~''
      ''SignoffOverride~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''SIGNON~0~SIGNON~N~N~True~N~N~N~~~~''
      ''OPER_OPT_FLAG~0~OPER_OPT_FLAG~N~N~N~N~N~N~~~~''
      ''OPER_STATUS~0~OPER_STATUS~N~N~N~N~N~N~~~~''
      ''ORDER_ID~0~ORDER_ID~N~N~N~N~N~N~~~~''
      
        ''SIGNOFF_ALL_OVERIDE_FLAG~0~SIGNOFF_ALL_OVERIDE_FLAG~N~N~Y~N~N~N~'' +
        ''~~~''
      
        ''CURRENT_GROUP~0~CURRENT_GROUP~N~N~ORDER_CONTROL_TYPE<>''#39''SERIAL''#39'' A'' +
        ''ND ORDER_CONTROL_TYPE<>''#39''SERIAL-LOT''#39''~False~False~N~~~~'')
    SelectedFieldsEditControl.Strings = (
      ''ORDER_ID~Edit''
      ''SIGNON~Edit''
      ''OPER_STATUS~Edit'')
    DataDefinitions.Strings = (
      ''SIGNON''
      ''OPER_OPT_FLAG''
      ''OPER_STATUS''
      ''ORDER_ID''
      ''SIGNOFF_ALL_OVERIDE_FLAG''
      ''CURRENT_GROUP'')
    ConsolidatedQuery = False
  end
  object MFI_WID_WORK_SCOPE_HOLD_EXISTS: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_WORK_SCOPE_HOLD_EXISTS''
    LinkedControls.Strings = (
      ''ApproveScopeChg''
      ''Scrap~'')
    ParamsSQLSourceName = ''@ToolScope''
    SelectedFields.Strings = (
      
        ''WORK_SCOPE_HOLD~0~WORK_SCOPE_HOLD~N~N~False~False~False~N~~~~~De'' +
        ''fault~'')
    TestParamValues.Strings = (
      ''ORDER_ID='')
    ConsolidatedQuery = False
  end
  object displayCurrentUnits: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_GetCurrentUnits''
    LinkedControls.Strings = (
      ''_OBJ3~''
      ''_OBJ13~SERIAL_NO'')
    ParamsSQLSourceName = ''@ToolScope, dummy''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''SERIAL_NO~0~SERIAL_NO~N~N~(ORDER_CONTROL_TYPE<>''#39''SERIAL''#39'' AND ORDE'' +
        ''R_CONTROL_TYPE<>''#39''SERIAL-LOT''#39'') OR ORDER_STATUS=''#39''PENDING''#39''~False~(O'' +
        ''RDER_CONTROL_TYPE<>''#39''SERIAL''#39'' AND ORDER_CONTROL_TYPE<>''#39''SERIAL-LOT''#39 +
        '') OR ORDER_STATUS=''#39''PENDING''#39''~N~~~~~Default~''
      
        ''SELECTED_UNITS~0~SELECTED_UNITS~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~''
      
        ''SUBSELECTED_FLAG~0~SUBSELECTED_FLAG~N~N~False~False~False~N~~~~~'' +
        ''Default~'')
    TestParamValues.Strings = (
      ''ORDER_ID=''
      ''OPER_KEY=''
      ''STEP_KEY='')
    DataDefinitions.Strings = (
      ''SERIAL_NO''
      ''SELECTED_UNITS''
      ''SUBSELECTED_FLAG'')
    ConsolidatedQuery = False
  end
  object OrderUnitInfoSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_OrderUnitInfoSelect''
    LinkedControls.Strings = (
      ''Display~'')
    ParamsSQLSourceName = ''@ToolScope,displayCurrentUnits''
    PublishParams = True
    SelectedFields.Strings = (
      ''SERIAL_NO~0~SERIAL_NO~N~N~False~False~False~N~~~~~Default~''
      ''LOT_NO~0~LOT_NO~N~N~False~False~False~N~~~~~Default~'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''OrderUnitInfoSelect''
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

