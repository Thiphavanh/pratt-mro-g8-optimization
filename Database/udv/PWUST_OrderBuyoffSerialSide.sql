
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_636167078ECBC5B9E05387971F0AA876.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_636167078ECBC5B9E05387971F0AA876';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderBuyoffSerialSide';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202';
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
  DesignSize = (
    466
    446)
  object _OBJ1: TsfDBGrid
    Left = 4
    Top = 4
    Width = 450
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
  object _OBJ4: TsfDBGrid
    Left = 4
    Top = 157
    Width = 460
    Height = 115
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
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Accept,''#39''Priv={(STATUS =''#39#39''REOPEN''#39#39'' OR STATUS =''#39#39''REJECT''#39#39'' OR '' +
        ''STATUS =''#39#39''OPEN''#39#39''  OR STATUS = ''#39#39''SKIP''#39#39'' OR STATUS = ''#39#39''PARTIAL''#39#39'') '' +
        ''AND ALT_MODE_STATUS=''#39#39#39#39'' AND (SERIAL_OPER_STATUS=''#39#39''IN QUEUE''#39#39''  O'' +
        ''R SERIAL_OPER_STATUS=''#39#39''ACTIVE''#39#39'') AND INCLUDED_IN_GROUPJOB<>''#39#39''Y''#39#39 +
        ''}''#39'',Visible=''#39''{GROUP_JOB_NO=''#39#39#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Defa'' +
        ''ult,Action=UDV,UDVType=Update,UDVID=MFI_1009872'')
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
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Reject,''#39''Priv={(STATUS = ''#39#39''OPEN''#39#39'' OR STATUS = ''#39#39''REOPEN''#39#39'' OR '' +
        ''STATUS = ''#39#39''SKIP''#39#39'' OR STATUS = ''#39#39''PARTIAL''#39#39'') AND ALT_MODE_STATUS=''#39 +
        #39#39#39'' AND (SERIAL_OPER_STATUS=''#39#39''IN QUEUE''#39#39''  OR SERIAL_OPER_STATUS='' +
        #39#39''ACTIVE''#39#39'') AND INCLUDED_IN_GROUPJOB<>''#39#39''Y''#39#39'' AND (GROUP_JOB_STATU'' +
        ''S=''#39#39''ACTIVE''#39#39'' OR GROUP_JOB_NO =''#39#39#39#39'')}''#39'',Visible={1=0},TagValue=,FK'' +
        ''ey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_10098'' +
        ''87'')
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
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Reopen,''#39''Priv={(oper_status=''#39#39''IN QUEUE''#39#39'' or oper_status=''#39#39''AC'' +
        ''TIVE''#39#39'') and (STATUS = ''#39#39''REJECT''#39#39'' OR STATUS = ''#39#39''ACCEPT''#39#39'' OR STATU'' +
        ''S = ''#39#39''PARTIAL''#39#39'') AND ALT_MODE_STATUS=''#39#39#39#39'' AND (SERIAL_OPER_STATU'' +
        ''S=''#39#39''IN QUEUE''#39#39''  OR SERIAL_OPER_STATUS=''#39#39''ACTIVE''#39#39'') AND INCLUDED_I'' +
        ''N_GROUPJOB<>''#39#39''Y''#39#39'' AND (GROUP_JOB_STATUS=''#39#39''ACTIVE''#39#39'' OR GROUP_JOB_'' +
        ''NO =''#39#39#39#39'')}''#39'',Visible={},TagValue=,FKey=,ParamsSrc=@Default,Action'' +
        ''=UDV,UDVType=Update,UDVID=MFI_1009929'')
  end
  object _OBJ11: TsfDBGrid
    Left = 4
    Top = 311
    Width = 460
    Height = 129
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
    TabStop = True
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
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Accept All,''#39''Priv={(STATUS =''#39#39''REOPEN''#39#39'' OR STATUS =''#39#39''REJECT''#39#39 +
        '' OR STATUS =''#39#39''OPEN''#39#39''  OR STATUS = ''#39#39''SKIP''#39#39'' OR STATUS = ''#39#39''PARTIAL'' +
        #39#39'') AND ALT_MODE_STATUS=''#39#39#39#39'' AND (SERIAL_OPER_STATUS=''#39#39''IN QUEUE''#39 +
        #39''  OR SERIAL_OPER_STATUS=''#39#39''ACTIVE''#39#39'')}''#39'',Visible={1=0},TagValue=,F'' +
        ''Key=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_905E'' +
        ''57FFD0074B9DBEC3D824191F26B3'')
  end
  object Skip: TsfCommandButton
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
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Skip,''#39''Priv={(STATUS = ''#39#39''OPEN''#39#39'' OR STATUS = ''#39#39''REOPEN''#39#39'' )  AN'' +
        ''D OPTIONAL_FLAG=''#39#39''Y''#39#39'' AND ALT_MODE_STATUS=''#39#39#39#39'' AND (SERIAL_OPER_'' +
        ''STATUS=''#39#39''IN QUEUE''#39#39''  OR SERIAL_OPER_STATUS=''#39#39''ACTIVE''#39#39'') AND INCLU'' +
        ''DED_IN_GROUPJOB<>''#39#39''Y''#39#39'' AND (GROUP_JOB_STATUS=''#39#39''ACTIVE''#39#39'' OR GROUP'' +
        ''_JOB_NO =''#39#39#39#39'')}''#39'',Visible={1=0},TagValue=,FKey=,ParamsSrc=@Defaul'' +
        ''t,Action=UDV,UDVType=Update,UDVID=MFI_14E95BBF2AA24D7AE0440003BA'' +
        ''560E35'')
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
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Partial,''#39''Priv={(STATUS =''#39#39''REOPEN''#39#39'' OR STATUS =''#39#39''OPEN''#39#39''  OR '' +
        ''STATUS = ''#39#39''SKIP''#39#39'' OR STATUS = ''#39#39''PARTIAL''#39#39'') AND ALT_MODE_STATUS=''#39 +
        #39#39#39'' AND (SERIAL_OPER_STATUS=''#39#39''IN QUEUE''#39#39''  OR SERIAL_OPER_STATUS='' +
        #39#39''ACTIVE''#39#39'') AND INCLUDED_IN_GROUPJOB<>''#39#39''Y''#39#39'' AND (GROUP_JOB_STATU'' +
        ''S=''#39#39''ACTIVE''#39#39'' OR GROUP_JOB_NO =''#39#39#39#39'')}''#39'',Visible={1=0},TagValue=,FK'' +
        ''ey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_54579'' +
        ''03B0F9D409DE0440003BA041A64'')
  end
  object AcceptForGroupJob: TsfCommandButton
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
      
        ''Desc=Accept All,''#39''Priv={(STATUS =''#39#39''REOPEN''#39#39'' OR STATUS =''#39#39''REJECT''#39#39 +
        '' OR STATUS =''#39#39''OPEN''#39#39''  OR STATUS = ''#39#39''SKIP''#39#39'' OR STATUS = ''#39#39''PARTIAL'' +
        #39#39'') AND ALT_MODE_STATUS=''#39#39#39#39'' AND (SERIAL_OPER_STATUS=''#39#39''IN QUEUE''#39 +
        #39''  OR SERIAL_OPER_STATUS=''#39#39''ACTIVE''#39#39'') AND INCLUDED_IN_GROUPJOB<>''#39 +
        #39''Y''#39#39'' AND (GROUP_JOB_STATUS=''#39#39''ACTIVE''#39#39'' OR GROUP_JOB_NO =''#39#39#39#39'')}''#39'',V'' +
        ''isible={1=0},TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVTy'' +
        ''pe=Update,UDVID=MFI_905E57FFD0074B9DBEC3D824191F26B3'')
  end
  object HIDDEN_DUMMY: TsfDBEdit
    Left = 443
    Top = 56
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
  object _OBJ14: TsfCommandButton
    Left = 82
    Top = 128
    Width = 75
    Height = 21
    Hint = ''Other commands''
    Caption = ''Buyoff Status''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 12
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
      ''ORDER_NO~Edit'')
    TestParamValues.Strings = (
      ''order_id=MFI_87C2B7E546E7721ED119B291B1E57C66''
      ''OPER_KEY=1646''
      ''group_job_no=0004567''
      ''oper_no=10'')
    DataDefinitions.Strings = (
      ''ORDER_NO''
      ''OPER_NO''
      ''SERIAL_NO''
      ''LOT_NO'')
    ConsolidatedQuery = False
  end
  object BuyoffExe1: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderSerialBuyoffs''
    LinkedControls.Strings = (
      ''_OBJ4~''
      ''_OBJ8~''
      ''_OBJ9~''
      ''Skip~''
      ''PARTIAL~''
      ''_OBJ10~''
      ''Accept_all~''
      ''AcceptForGroupJob~'')
    ParamsSQLSourceName = ''GroupJobNo,OrderBuyoffSrials''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''OPER_NO~4~Oper;No~N~N~FROM_TAB=''#39''N''#39'' OR GROUP_JOB_NO <>''#39#39''~N~N~N~~~'' +
        ''~~Default~N~''
      ''STEP_NO~4~Step;No~N~N~FROM_TAB=''#39''N''#39''~N~N~N~~~~~Default~N~''
      ''BUYOFF_TYPE~6~Buyoff;Type~N~N~N~N~N~N~~~~~Default~N~''
      
        ''BUYOFF_TITLE~20~Buyoff;Title~N~N~False~False~False~N~~~~~Default'' +
        ''~N~''
      ''BUYOFF_CERT~11~Required;Cert~N~N~N~N~N~N~~~~~Default~N~''
      ''OPTIONAL_FLAG~4~Optional?; ~N~N~False~N~False~N~~~~~Default~N~''
      
        ''CROSS_ORDER_FLAG~4~Cross;Order?~N~N~False~N~False~N~~~~~Default~'' +
        ''N~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_SRLOPBUYOFF_DATE2~Edit''
      ''UCF_SRLOPBUYOFF_FLAG4~Edit''
      ''BUYOFF_CERT~Edit''
      ''OPTIONAL_FLAG~Edit'')
    TestParamValues.Strings = (
      ''@USERID=USER1''
      ''ORDER_ID=MFI_87C2B7E546E7721ED119B291B1E57C66''
      ''OPER_KEY=1646''
      ''SERIAL_ID=MFI_B0C276881B77DCDBD49EB46FC36ADAB''
      ''LOT_ID=MFI_B965C9CB83BFEB4198FA32CC7DC61EC2'')
    InputUDVId = ''MFI_1280B6F87DFB208FE0440003BA560E35''
    DataDefinitions.Strings = (
      ''OPER_NO''
      ''STEP_NO''
      ''BUYOFF_TYPE''
      ''BUYOFF_TITLE''
      ''BUYOFF_CERT''
      ''OPTIONAL_FLAG''
      ''CROSS_ORDER_FLAG'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''BuyoffExe1''
  end
  object BuyoffSerialExe: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_WID_SerialBuyoffExe''
    LinkedControls.Strings = (
      ''_OBJ11~''
      ''_OBJ13~'')
    ParamsSQLSourceName = ''BuyoffExe1''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''BUYOFF_STATUS~7~Buyoff;Status~N~N~False~N~False~N~~~~~Default~N~'' +
        ''~''
      
        ''USERNAME~20~Buyoff;User Name~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''UPDT_USERID~10~Update;User ID~N~N~False~N~False~N~MFI_2869~~~~De'' +
        ''fault~N~~''
      
        ''PERCENT_QTY_COMPLETE~8~% Complete/;Complete Qty~N~N~False~N~Fals'' +
        ''e~N~~~~~Default~N~~''
      ''COMMENTS~25~Comments~N~N~False~N~False~N~~~~~Default~N~~''
      ''TIME_STAMP~14~Update;Time~N~N~False~N~False~N~~~~~Default~N~~''
      
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
      ''UCF_SRLOPBUYOFF_FLAG3~Edit''
      ''UCF_SRLOPBUYOFF_VCH11~Edit''
      ''UCF_SRLOPBUYOFF_DATE4~Edit''
      ''UPDT_USERID~Edit'')
    InputUDVId = ''MFI_1280B6F87DFB208FE0440003BA560E35''
    InsertEnabledExpr = ''ORDER_STATUS<>''#39''CLOSE''#39
    UpdateEnabledExpr = ''ORDER_STATUS<>''#39''CLOSE''#39
    DeleteEnabledExpr = ''ORDER_STATUS<>''#39''CLOSE''#39
    InsUpdDelEnabledExpr = ''ORDER_STATUS<>''#39''CLOSE''#39
    DataDefinitions.Strings = (
      ''BUYOFF_STATUS''
      ''PERCENT_QTY_COMPLETE''
      ''COMMENTS''
      ''UPDT_USERID''
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
      ''UCF_SRLOPBUYOFF_VCH11''
      ''UCF_SRLOPBUYOFF_VCH12''
      ''UCF_SRLOPBUYOFF_VCH13''
      ''UCF_SRLOPBUYOFF_VCH14''
      ''UCF_SRLOPBUYOFF_VCH15'')
    RowReadOnly = ''OrderSerialBuyoffs''
    ConsolidatedQuery = False
  end
  object GroupJobNo: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_GROUPJOBNOSEL''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''GroupJobNo''
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

