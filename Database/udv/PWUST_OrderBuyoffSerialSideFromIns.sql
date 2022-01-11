
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_629509A4E955206AE05387971F0ACCE3.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_629509A4E955206AE05387971F0ACCE3';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderBuyoffSerialSideFromIns';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202;Defect731';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
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
    Top = 4
    Width = 450
    Height = 115
    TabStop = False
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
  object _OBJ4: TsfDBGrid
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
    Hidden = ''False''
    RowSelection = True
  end
  object _OBJ8: TsfCommandButton
    Left = 8
    Top = 128
    Width = 65
    Height = 21
    Hint = ''Accept''
    Caption = ''Accept''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (

        ''Desc=Accept,''#39''Priv={(ACCEPT=''#39#39''ACCEPT''#39#39'' OR ACCEPT=''#39#39''SKIP_BUYOFF_CA'' +
        ''NBE_ACCEPTED''#39#39'') AND INCLUDED_IN_GROUPJOB<>''#39#39''Y''#39#39''}''#39'',Visible=''#39''{GROU'' +
        ''P_JOB_NO=''#39#39#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UD'' +
        ''VType=Update,UDVID=MFI_1009872'')
  end
  object _OBJ9: TsfCommandButton
    Left = 84
    Top = 128
    Width = 65
    Height = 21
    Hint = ''Reject''
    Caption = ''Reject''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (

        ''Desc=Reject,Priv=''#39''{REJECT=''#39#39''REJECT''#39#39'' AND INCLUDED_IN_GROUPJOB<>''#39 +
        #39''Y''#39#39''}''#39'',Visible={1=0},TagValue=,FKey=,ParamsSrc=@Default,Action=U'' +
        ''DV,UDVType=Update,UDVID=MFI_1009887'')
  end
  object _OBJ10: TsfCommandButton
    Left = 314
    Top = 128
    Width = 65
    Height = 21
    Hint = ''Reopen''
    Caption = ''Reopen''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (

        ''Desc=Reopen,''#39''Priv={REOPEN=''#39#39''REOPEN''#39#39'' AND ( WID_OPER_STATUS=''#39#39''ACT'' +
        ''IVE''#39#39'' OR WID_OPER_STATUS=''#39#39''IN QUEUE''#39#39'' ) AND INCLUDED_IN_GROUPJOB'' +
        ''<>''#39#39''Y''#39#39''}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Action=U'' +
        ''DV,UDVType=Update,UDVID=MFI_1009929'')
  end
  object _OBJ11: TsfDBGrid
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
  object _OBJ13: TsfUDVNavigator
    Left = 4
    Top = 287
    Width = 20
    Height = 22
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Edit '')
    ShowHint = True
    TabOrder = 3
  end
  object Accept_all: TsfCommandButton
    Left = 388
    Top = 128
    Width = 65
    Height = 21
    Hint = ''Accept All''
    Caption = ''Accept All''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (

        ''Desc=Accept All,''#39''Priv={ACCEPTALL=''#39#39''ACCEPTALL''#39#39'' AND ( WID_OPER_ST'' +
        ''ATUS=''#39#39''ACTIVE''#39#39'' OR WID_OPER_STATUS=''#39#39''IN QUEUE''#39#39'' ) AND INCLUDED_I'' +
        ''N_GROUPJOB<>''#39#39''Y''#39#39''}''#39'',Visible={1=0},TagValue=,FKey=,ParamsSrc=@Def'' +
        ''ault,Action=UDV,UDVType=Update,UDVID=MFI_905E57FFD0074B9DBEC3D82'' +
        ''4191F26B3'')
  end
  object _OBJ15: TsfCommandButton
    Left = 166
    Top = 128
    Width = 65
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
    TabOrder = 8
    OtherCommands.Strings = (

        ''Desc=Skip,Priv=''#39''{SKIP=''#39#39''SKIP''#39#39'' AND INCLUDED_IN_GROUPJOB<>''#39#39''Y''#39#39''}''#39 +
        '',Visible={1=0},TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDV'' +
        ''Type=Update,UDVID=MFI_14E95BBF2AA24D7AE0440003BA560E35'')
  end
  object PARTIAL: TsfCommandButton
    Left = 240
    Top = 128
    Width = 65
    Height = 21
    Hint = ''Partial''
    Caption = ''Partial''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 9
    OtherCommands.Strings = (

        ''Desc=Partial,Priv=''#39''{PARTIAL=''#39#39''PARTIAL''#39#39'' AND WID_OPER_STATUS<>''#39#39''C'' +
        ''LOSE''#39#39'' AND INCLUDED_IN_GROUPJOB<>''#39#39''Y''#39#39''}''#39'',Visible={1=0},TagValue='' +
        '',FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_54'' +
        ''57903B0F9D409DE0440003BA041A64'')
  end
  object AcceptGroupJob: TsfCommandButton
    Left = 8
    Top = 128
    Width = 65
    Height = 21
    Hint = ''Accept All''
    Caption = ''Accept All''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 10
    OtherCommands.Strings = (

        ''Desc=Accept All,''#39''Priv={ACCEPTALL=''#39#39''ACCEPTALL''#39#39'' AND ( WID_OPER_ST'' +
        ''ATUS=''#39#39''ACTIVE''#39#39'' OR WID_OPER_STATUS=''#39#39''IN QUEUE''#39#39'' )}''#39'',Visible={1=0'' +
        ''},TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,U'' +
        ''DVID=MFI_905E57FFD0074B9DBEC3D824191F26B3'')
  end
  object DUMMY_HIDDEN: TsfDBEdit
    Left = 450
    Top = 48
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
    Caption = ''Hidden Used as ref to avoid scrollbar''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = True
  end
  object _OBJ16: TsfCommandButton
    Left = 82
    Top = 128
    Width = 75
    Height = 21
    Caption = ''Buyoff Status''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 12
    OtherCommands.Strings = (

        ''Desc=N/A,Priv=''#39''{OPER_STATUS=''#39#39''IN QUEUE''#39#39'' OR OPER_STATUS=''#39#39''PENDIN'' +
        ''G''#39#39''}''#39'',Visible={},TagValue=N/A,FKey=,ParamsSrc=@Default,Action=UD'' +
        ''V,UDVType=Update,UDVID=PWUST_63EE4F28DC3E5840E05387971F0AB9A0''

        ''Desc=N/D,Priv=''#39''{OPER_STATUS=''#39#39''IN QUEUE''#39#39'' OR OPER_STATUS=''#39#39''PENDIN'' +
        ''G''#39#39''}''#39'',Visible={},TagValue=N/D,FKey=,ParamsSrc=@Default,Action=UD'' +
        ''V,UDVType=Update,UDVID=PWUST_63EE4F28DC3E5840E05387971F0AB9A0''

        ''Desc=Training,Priv={},Visible={},TagValue=,FKey=,ParamsSrc=@Defa'' +
        ''ult''

        ''Desc=Partial,Priv=''#39''{WID_OPER_STATUS<>''#39#39''CLOSE''#39#39''}''#39'',Visible=''#39''{ORDER'' +
        ''_STATUS<>''#39#39''CLOSE''#39#39'' AND GROUP_JOB_NO=''#39#39#39#39''}''#39'',TagValue=PARTIAL,FKey'' +
        ''=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_5457903'' +
        ''B0F9D409DE0440003BA041A64'')
  end
  object OrderBuyoffSrials: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderBuyoffSrials''
    LinkedControls.Strings = (
      ''_OBJ1~'')
    ParamsSQLSourceName = ''@CurrentMarker''
    PublishParams = True
    SelectedFields.Strings = (

        ''ORDER_NO~12~Work Order No~N~N~ORDER_CONTROL_TYPE<>''#39''NONE''#39'' AND GRO'' +
        ''UP_JOB_NO =''#39#39''~N~False~N~~~~~Default~N~''

        ''OPER_NO~5~Oper No~N~N~GROUP_JOB_NO =''#39#39''~False~False~N~~~~~Default'' +
        ''~N~''

        ''SERIAL_NO~8~Serial No~N~N~(ORDER_CONTROL_TYPE=''#39''NONE''#39''  OR ORDER_C'' +
        ''ONTROL_TYPE=''#39''LOT''#39'' OR ORDER_CONTROL_TYPE=''#39''LOT1''#39'') AND GROUP_JOB_NO'' +
        '' =''#39#39''~N~False~N~~~~~Default~N~''

        ''LOT_NO~8~Lot No~N~N~ORDER_CONTROL_TYPE<>''#39''LOT''#39'' AND  ORDER_CONTROL'' +
        ''_TYPE<>''#39''LOT1''#39'' AND GROUP_JOB_NO =''#39#39''~N~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''LOT_NO~Edit'')
    TestParamValues.Strings = (
      ''order_id=MFI_4A8DA102260DA90572672DF421248F7F''
      ''REF_ID=MFI_EC672E595202417A9E38F39264F1B172''
      ''serial_id=MFI_3FAF93DA4D4FF2FE05CBAD815C4F488''
      ''lot_id=MFI_EC4295AF63CCC4C5E8EFF4C36EB69076''
      ''@userid=UTPAL''
      ''oper_key=26201''
      ''step_key=22606''
      ''group_job_no=12345''
      ''oper_no=10'')
    DataDefinitions.Strings = (
      ''ORDER_NO''
      ''OPER_NO''
      ''SERIAL_NO''
      ''LOT_NO'')
    ConsolidatedQuery = False
  end
  object BuyoffExeSN: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_BuyoffNoneSelSidePanel''
    LinkedControls.Strings = (
      ''_OBJ9~''
      ''_OBJ8~''
      ''_OBJ10~''
      ''Accept_all~''
      ''_OBJ15~''
      ''_OBJ4~''
      ''PARTIAL~''
      ''AcceptGroupJob~'')
    ParamsSQLSourceName = ''OrderBuyoffSrials''
    PublishParams = True
    SelectedFields.Strings = (
      ''BUYOFF_TYPE~6~Buyoff;Type~N~N~True~N~True~N~~~~~Default~N~~''
      ''BUYOFF_CERT~12~Required;Cert~N~N~N~N~N~N~~~~~Default~N~~''
      ''OPTIONAL_FLAG~1~Optional?; ~N~N~False~N~False~N~~~~~Default~N~~''

        ''BUYOFF_TITLE~15~Buyoff;Title~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''

        ''CROSS_ORDER_FLAG~0~Cross;Order?~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_SRLOPBUYOFF_DATE2~Edit''
      ''UCF_SRLOPBUYOFF_FLAG4~Edit''
      ''BUYOFF_CERT~Edit''
      ''OPTIONAL_FLAG~Edit'')
    TestParamValues.Strings = (
      ''order_id=MFI_4A8DA102260DA90572672DF421248F7F''
      ''serial_id=MFI_3FAF93DA4D4FF2FE05CBAD815C4F488''
      ''lot_id=MFI_EC4295AF63CCC4C5E8EFF4C36EB69076''
      ''oper_key=26201''
      ''step_key=22606''
      ''@userid=UTPAL''
      ''REF_ID=MFI_EC672E595202417A9E38F39264F1B172''
      ''@ConsolidatedQueryType=Instance''
      ''@NavLevelFieldName=STEP'')
    InputUDVId = ''MFI_1280B6F87DFB208FE0440003BA560E35''
    DataDefinitions.Strings = (
      ''BUYOFF_TYPE''
      ''BUYOFF_CERT''
      ''OPTIONAL_FLAG''
      ''BUYOFF_TITLE''
      ''CROSS_ORDER_FLAG'')
    ConsolidatedQuery = False
  end
  object BuyoffSerialExe: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_WID_SerialBuyoffExe''
    LinkedControls.Strings = (
      ''_OBJ13~''
      ''_OBJ11~'')
    ParamsSQLSourceName = ''BuyoffExeSN''
    PublishParams = True
    SelectedFields.Strings = (

        ''BUYOFF_STATUS~5~Buyoff;Status~N~N~False~N~False~N~~~~~Default~N~'' +
        ''~''

        ''USERNAME~20~Buyoff;User Name~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''

        ''UPDT_USERID~5~Update;User ID~N~N~False~N~False~N~MFI_2869~~~~Def'' +
        ''ault~N~~''

        ''PERCENT_QTY_COMPLETE~6~% Complete/;Complete Qty~N~N~False~N~Fals'' +
        ''e~N~~~~~Default~N~~''
      ''COMMENTS~10~Comments~N~N~False~N~False~N~~~~~Default~N~~''
      ''TIME_STAMP~12~Update;Time~N~N~False~N~False~N~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_DATE1~0~UCF_SRLOPBUYOFF_DATE1~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_DATE2~0~UCF_SRLOPBUYOFF_DATE2~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_DATE3~0~UCF_SRLOPBUYOFF_DATE3~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_DATE4~0~UCF_SRLOPBUYOFF_DATE4~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_DATE5~0~UCF_SRLOPBUYOFF_DATE5~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_FLAG1~0~UCF_SRLOPBUYOFF_FLAG1~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_FLAG2~0~UCF_SRLOPBUYOFF_FLAG2~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_FLAG3~0~UCF_SRLOPBUYOFF_FLAG3~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_FLAG4~0~UCF_SRLOPBUYOFF_FLAG4~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_FLAG5~0~UCF_SRLOPBUYOFF_FLAG5~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_NUM1~0~UCF_SRLOPBUYOFF_NUM1~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_NUM2~0~UCF_SRLOPBUYOFF_NUM2~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_NUM3~0~UCF_SRLOPBUYOFF_NUM3~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_NUM4~0~UCF_SRLOPBUYOFF_NUM4~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_NUM5~0~UCF_SRLOPBUYOFF_NUM5~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH1~0~UCF_SRLOPBUYOFF_VCH1~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH2~0~UCF_SRLOPBUYOFF_VCH2~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH3~0~UCF_SRLOPBUYOFF_VCH3~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH4~0~UCF_SRLOPBUYOFF_VCH4~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH5~0~UCF_SRLOPBUYOFF_VCH5~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH6~0~UCF_SRLOPBUYOFF_VCH6~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH7~0~UCF_SRLOPBUYOFF_VCH7~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH8~0~UCF_SRLOPBUYOFF_VCH8~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH9~0~UCF_SRLOPBUYOFF_VCH9~N~N~True~N~False~N~~'' +
        ''~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH10~0~UCF_SRLOPBUYOFF_VCH10~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH11~0~UCF_SRLOPBUYOFF_VCH11~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH12~0~UCF_SRLOPBUYOFF_VCH12~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH13~0~UCF_SRLOPBUYOFF_VCH13~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH14~0~UCF_SRLOPBUYOFF_VCH14~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~''

        ''UCF_SRLOPBUYOFF_VCH15~0~UCF_SRLOPBUYOFF_VCH15~N~N~True~N~False~N'' +
        ''~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_SRLOPBUYOFF_DATE1~Edit''
      ''UCF_SRLOPBUYOFF_FLAG3~Edit''
      ''UCF_SRLOPBUYOFF_VCH11~Edit''
      ''UPDT_USERID~Edit'')
    TestParamValues.Strings = (
      ''buyoff_id=PWUST_D3755A88EB8B8F6E2D3022091A99936C''
      ''order_id=PWUST_78012F65EE0A15633505131C32E3DBE''
      ''oper_key=28989''
      ''step_key=-1''
      ''serial_id=PWUST_AB2CE2CEDCCB41EAA4F86A5C74DBCD70''
      ''lot_id=PWUST_2536E445771135F3C90BB0E226FD34BE''
      ''REF_ID=PWUST_6536BB31A653D039E05387971F0AB43B'')
    InputUDVId = ''MFI_1280B6F87DFB208FE0440003BA560E35''
    InsertEnabledExpr =
      ''ORDER_STATUS<>''#39''CLOSE''#39'' AND GROUP_JOB_STATUS <> ''#39''COMPLETE''#39'' AND GRO'' +
      ''UP_JOB_STATUS <> ''#39''CANCEL''#39
    UpdateEnabledExpr =
      ''ORDER_STATUS<>''#39''CLOSE''#39'' AND GROUP_JOB_STATUS <> ''#39''COMPLETE''#39'' AND GRO'' +
      ''UP_JOB_STATUS <> ''#39''CANCEL''#39
    DeleteEnabledExpr =
      ''ORDER_STATUS<>''#39''CLOSE''#39'' AND GROUP_JOB_STATUS <> ''#39''COMPLETE''#39'' AND GRO'' +
      ''UP_JOB_STATUS <> ''#39''CANCEL''#39
    InsUpdDelEnabledExpr =
      ''ORDER_STATUS<>''#39''CLOSE''#39'' AND GROUP_JOB_STATUS <> ''#39''COMPLETE''#39'' AND GRO'' +
      ''UP_JOB_STATUS <> ''#39''CANCEL''#39
    DataDefinitions.Strings = (
      ''BUYOFF_STATUS''
      ''USERNAME''
      ''UPDT_USERID''
      ''PERCENT_QTY_COMPLETE''
      ''COMMENTS''
      ''TIME_STAMP''
      ''UCF_SRLOPBUYOFF_DATE1''
      ''UCF_SRLOPBUYOFF_DATE2''
      ''UCF_SRLOPBUYOFF_DATE3''
      ''UCF_SRLOPBUYOFF_DATE4''
      ''UCF_SRLOPBUYOFF_DATE5''
      ''UCF_SRLOPBUYOFF_FLAG1''
      ''UCF_SRLOPBUYOFF_FLAG2''
      ''UCF_SRLOPBUYOFF_FLAG3''
      ''UCF_SRLOPBUYOFF_FLAG4''
      ''UCF_SRLOPBUYOFF_FLAG5''
      ''UCF_SRLOPBUYOFF_NUM1''
      ''UCF_SRLOPBUYOFF_NUM2''
      ''UCF_SRLOPBUYOFF_NUM3''
      ''UCF_SRLOPBUYOFF_NUM4''
      ''UCF_SRLOPBUYOFF_NUM5''
      ''UCF_SRLOPBUYOFF_VCH1''
      ''UCF_SRLOPBUYOFF_VCH2''
      ''UCF_SRLOPBUYOFF_VCH3''
      ''UCF_SRLOPBUYOFF_VCH4''
      ''UCF_SRLOPBUYOFF_VCH5''
      ''UCF_SRLOPBUYOFF_VCH6''
      ''UCF_SRLOPBUYOFF_VCH7''
      ''UCF_SRLOPBUYOFF_VCH8''
      ''UCF_SRLOPBUYOFF_VCH9''
      ''UCF_SRLOPBUYOFF_VCH10''
      ''UCF_SRLOPBUYOFF_VCH11''
      ''UCF_SRLOPBUYOFF_VCH12''
      ''UCF_SRLOPBUYOFF_VCH13''
      ''UCF_SRLOPBUYOFF_VCH14''
      ''UCF_SRLOPBUYOFF_VCH15'')
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

