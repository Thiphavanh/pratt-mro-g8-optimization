set define off
declare
v_folder_id varchar2(40) :='HAMSI_A36C8B4A07FC5D74E0400A0AD20F28E1';
v_udv_id varchar2(85) :='HAMSI_192C68402B8361A2E053D20F0A0A94B8';
v_udv_tag varchar2(40) :='HAMSI_CreateStdAlnWO';
v_udv_type varchar2(7) :='INPUT';
v_udv_desc varchar2(255) :='Cloned From MFI_7271';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 600
  AutoScroll = False
  Caption = ''Create Unplanned Order''
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctSingle
  RequireLogonExpression = ''False''
  AutoCancelTimeout = -1
  MultimediaRequired = mmNo
  MMPopulateForInsert = False
  AlignContents = acTop
  AutoRefresh = arDefault
  MinWidth = 0
  MinHeight = 0
  MaxWidth = 0
  MaxHeight = 0
  UserAbort = etDefault
  ProgressMeter = etDefault
  AutoScrollbars = True
  DynamicFieldAttributes = True
  BottomMargin = -1
  object _OBJ2: TsfDBInputEdit
    Left = 425
    Top = 82
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 2
    EntryType = etAlphaNumeric
    Caption = 
      ''@Lookup(ITEM_TYPE,''POUND39_HOLDER''PART:Part No,TOOL:Tool No,PROCESS:Process No,'' +
      ''MACHINE:Machine No''POUND39_HOLDER'',''POUND39_HOLDER''Item No''POUND39_HOLDER'')''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object ORDERQTY: TsfDBInputEdit
    Left = 95
    Top = 113
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 5
    EntryType = etAlphaNumeric
    Caption = ''Order Qty*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ14: TsfDBInputEdit
    Left = 425
    Top = 113
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = 
      ''@Lookup(ITEM_TYPE,''POUND39_HOLDER''PART:Part Rev,TOOL:Tool Rev,PROCESS:Process R'' +
      ''ev,MACHINE:Machine Rev''POUND39_HOLDER'',''POUND39_HOLDER''Item Rev''POUND39_HOLDER'')''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ30: TsfDBInputEdit
    Left = 95
    Top = 50
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 0
    EntryType = etAlphaNumeric
    Caption = ''Order Type*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ32: TsfDBInputEdit
    Left = 95
    Top = 82
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    EntryType = etAlphaNumeric
    Caption = ''Order No*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ34: TsfDBInputEdit
    Left = 425
    Top = 175
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 9
    EntryType = etAlphaNumeric
    Caption = ''Sched Start Date''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ35: TsfDBInputEdit
    Left = 425
    Top = 208
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 11
    EntryType = etAlphaNumeric
    Caption = ''Sched End Date''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ37: TsfDBInputEdit
    Left = 95
    Top = 208
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 8
    EntryType = etAlphaNumeric
    Caption = ''Lot No*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ39: tSFInputListGrid
    Left = 95
    Top = 242
    Width = 150
    Height = 121
    Color = 14211288
    ColCount = 1
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssVertical
    TabOrder = 10
    Delimiter = '';''
    ParseAvailableItems = True
    ReadOnly = False
    Caption = ''Serial No*''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object _OBJ40: TsfDBInputEdit
    Left = 95
    Top = 519
    Width = 72
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 15
    EntryType = etAlphaNumeric
    Caption = ''VC1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ41: TsfDBInputEdit
    Left = 216
    Top = 552
    Width = 72
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 20
    EntryType = etAlphaNumeric
    Caption = ''NM1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ42: TsfDBInputEdit
    Left = 216
    Top = 519
    Width = 72
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 16
    EntryType = etAlphaNumeric
    Caption = ''VC2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ43: TsfDBInputEdit
    Left = 329
    Top = 519
    Width = 72
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 17
    EntryType = etAlphaNumeric
    Caption = ''VC3''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ44: TsfDBInputEdit
    Left = 446
    Top = 552
    Width = 72
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 23
    EntryType = etAlphaNumeric
    Caption = ''F1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ45: TsfDBInputEdit
    Left = 95
    Top = 552
    Width = 72
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 19
    EntryType = etAlphaNumeric
    Caption = ''VC5''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ46: TsfDBInputEdit
    Left = 329
    Top = 552
    Width = 72
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 21
    EntryType = etAlphaNumeric
    Caption = ''NM2''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ47: TsfDBInputEdit
    Left = 446
    Top = 519
    Width = 72
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 18
    EntryType = etAlphaNumeric
    Caption = ''VC4''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ49: TsfLabel
    Left = 63
    Top = 408
    Width = 28
    Height = 13
    Caption = ''Notes''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object OWP_ID: TsfDBInputEdit
    Left = 95
    Top = 144
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 6
    EntryType = etAlphaNumeric
    Caption = ''Package''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object contract: TsfDBInputEdit
    Left = 425
    Top = 276
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 12
    EntryType = etAlphaNumeric
    Caption = ''Contract''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object customer: TsfDBInputEdit
    Left = 425
    Top = 308
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 13
    EntryType = etAlphaNumeric
    Caption = ''Customer No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object customerOrderNo: TsfDBInputEdit
    Left = 425
    Top = 340
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 14
    EntryType = etAlphaNumeric
    Caption = ''Customer Order No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object ItemSubtype: TsfDBInputEdit
    Left = 425
    Top = 144
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 7
    EntryType = etAlphaNumeric
    Caption = 
      ''@Lookup(ITEM_TYPE,''POUND39_HOLDER''PART:Part Subtype,TOOL:Tool Subtype,PROCESS:P'' +
      ''rocess Subtype,MACHINE:Machine Subtype''POUND39_HOLDER'',''POUND39_HOLDER''Item Subtype''POUND39_HOLDER'')''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object DispSeq: TsfInputRadioGroup
    Left = 95
    Top = 8
    Width = 480
    Height = 35
    Caption = ''Display Sequence''
    Columns = 4
    Ctl3D = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    Items.Strings = (
      ''Item 1''
      ''Item 2'')
    ParentCtl3D = False
    ParentFont = True
    TabOrder = 22
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    ReadOnly = False
  end
  object Bevel1: TsfBevel
    Left = 0
    Top = 5
    Width = 540
    Height = 453
    Shape = bsSpacer
  end
  object _OBJ48: TsfDBInputMemo
    Left = 95
    Top = 408
    Width = 481
    Height = 87
    BevelOuter = bvNone
    BorderStyle = bsSingle
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = False
    PopupMenu = UDVEditForm.UDVControlPopupMenu
    TabOrder = 24
    UseDockManager = True
    Version = ''1.7.7.6''
    Buffered = False
    StatusBar.Font.Charset = DEFAULT_CHARSET
    StatusBar.Font.Color = clWindowText
    StatusBar.Font.Height = -11
    StatusBar.Font.Name = ''Tahoma''
    StatusBar.Font.Style = []
    Ctl3d = True
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    FullHeight = 0
  end
  object _OBJ51: TsfDBInputEdit
    Left = 95
    Top = 175
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 25
    EntryType = etAlphaNumeric
    Caption = ''Mbom Rev''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ52: TsfDBInputEdit
    Left = 425
    Top = 242
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 26
    EntryType = etAlphaNumeric
    Caption = ''Mbom No''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ53: TsfDBInputEdit
    Left = 250
    Top = 50
    Width = 37
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 27
    EntryType = etAlphaNumeric
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = True
    EnterForOkay = False
  end
  object _OBJ54: TsfDBInputEdit
    Left = 425
    Top = 50
    Width = 150
    Height = 23
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
    EntryType = etAlphaNumeric
    Caption = ''Work Flow*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ55: TsfDBInputEdit
    Left = 425
    Top = 374
    Width = 150
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 28
    EntryType = etAlphaNumeric
    Caption = ''Work Location*''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlue
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object OrderCreateStdAln: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''ORDER_QTY''
      '' Update=False''
      '' Insert=RELATED_ORDER_ID <> ''POUND39_HOLDERPOUND39_HOLDER'' OR DISC_ID <>''POUND39_HOLDERPOUND39_HOLDER
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=11''
      '' PersistManualValues=Y''
      ''PROGRAM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''ORDER_TYPE''
      '' Update=False''
      '' Insert=DISC_ID<>''POUND39_HOLDERPOUND39_HOLDER
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      '' DependentFields=ORDER_NO''
      ''PART_CHG''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''PART_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=30''
      '' PersistManualValues=Y''
      ''SCHED_START_DT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''SCHED_END_DT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''WO_SERIAL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''WO_LOT_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''WO_LOT_NO''
      '' Update=False''
      '' Insert=RELATED_ORDER_ID <> ''POUND39_HOLDERPOUND39_HOLDER'' OR DISC_ID <> ''POUND39_HOLDERPOUND39_HOLDER
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=30''
      '' PersistManualValues=Y''
      ''ITEM_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''WO_SERIAL_NO''
      '' Update=False''
      '' Insert=RELATED_ORDER_ID <> ''POUND39_HOLDERPOUND39_HOLDER'' OR DISC_ID <>''POUND39_HOLDERPOUND39_HOLDER
      '' PopulateForInsert=Y''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' MaxLength=30''
      '' PersistManualValues=Y''
      ''ORDER_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=30''
      '' PersistManualValues=Y''
      ''WORK_LOC''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' PersistManualValues=Y''
      ''VC1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''VC2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''VC3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''VC4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''VC5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''NUM2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''F1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=N''
      ''OBSOLETE_RECORD_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''NOTES''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=4000''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''OWP_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' MaxLength=0''
      '' CharCase=Y''
      '' Hidden=False''
      '' Required=False''
      '' PersistManualValues=Y''
      ''UCF_ORDER_VCH6''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_VCH7''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_VCH8''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_VCH9''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_VCH10''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_VCH11''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_VCH12''
      ''UCF_ORDER_VCH13''
      ''UCF_ORDER_VCH14''
      ''UCF_ORDER_VCH15''
      ''UCF_ORDER_NUM3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''UCF_ORDER_NUM4''
      ''UCF_ORDER_NUM5''
      ''UCF_ORDER_DATE1''
      ''UCF_ORDER_DATE2''
      ''UCF_ORDER_DATE3''
      ''UCF_ORDER_DATE4''
      ''UCF_ORDER_DATE5''
      ''UCF_ORDER_FLAG2''
      ''UCF_ORDER_FLAG3''
      ''UCF_ORDER_FLAG4''
      ''UCF_ORDER_FLAG5''
      ''UCF_ORDER_VCH255_1''
      ''UCF_ORDER_VCH255_2''
      ''UCF_ORDER_VCH255_3''
      ''UCF_ORDER_VCH4000_1''
      ''UCF_ORDER_VCH4000_2''
      ''CUSTOMER''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''CUSTOMER_ORDER_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=40''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''CONTRACT''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=30''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''REPLACEMENT_ACTION''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''AS_WORKED_BOM_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''RELATED_ORDER_ID''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''DISC_ID''
      ''DISC_LINE_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''ITEM_TYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''ITEM_SUBTYPE''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''DISPLAY_SEQUENCE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=Auto Parallel''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''INSTRUCTIONS_TYPE''
      '' AuxField=Y''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Required=False''
      '' Hidden=True''
      ''BOM_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=INSTRUCTIONS_TYPE=''POUND39_HOLDER''MRO-Part''POUND39_HOLDER
      '' Required=False''
      '' DependentFields=MFG_BOM_REV''
      '' MaxLength=60''
      ''MFG_BOM_REV''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=INSTRUCTIONS_TYPE=''POUND39_HOLDER''MRO-Part''POUND39_HOLDER
      '' Required=False''
      ''WORK_FLOW''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=True'')
    InputFieldsEditControlSSL.Strings = (
      ''VC1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''VC2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''VC3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''VC4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''VC5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''NUM1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''F1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''RELATED_ORDER_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CONTRACT''
      '' CtrlType=Edit''
      ''';
p2 clob :='  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''NUM2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CUSTOMER_ORDER_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_VCH10''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OBSOLETE_RECORD_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_VCH8''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''NOTES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_VCH7''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_WID_ATTACHORDERMBOMLOOKUP''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''OWP_ID''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_WID_OrdWorkPkgSel''
      ''  Validate=N''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''UCF_ORDER_NUM3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISC_LINE_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_VCH9''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CUSTOMER''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_CUSTOMERIDSEL''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''BOM_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''INSTRUCTIONS_TYPE''
      '' CtrlType=Edit''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''DISPLAY_SEQUENCE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_DisplaySequenceLookUp''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''ITEM_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''REPLACEMENT_ACTION''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''AS_WORKED_BOM_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SCHED_START_DT''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=MM/DD/YYYY''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''WO_SERIAL_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORDER_NO''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_WID_WONumberMaskLkup''
      ''  ParamsSrc=@UDVUserFieldValues''
      ''  Validate=N''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''UCF_ORDER_VCH11''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''UCF_ORDER_VCH6''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_SUBTYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PROGRAM''
      '' CtrlType=Lookup''
      ''  SqlId=Pl_ProgramDefSel''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Short''
      ''   Static=N''
      ''PART_CHG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''PART_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''SCHED_END_DT''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=MM/DD/YYYY''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''WO_LOT_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''WO_LOT_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''WO_SERIAL_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ORDER_TYPE''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_WID_SelectOrderType''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   ORDER_TYPE=Order Type''
      ''   ORDER_TYPE_DESC=Description''
      ''   INSTRUCTIONS_TYPE=Instructions Type''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''WORK_FLOW''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_WID_OrderWorkFlowLkup''
      ''  ParamsSrc=@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   WORK_FLOW=Work Flow''
      ''   DESCRIPTION=Description''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''WORK_LOC''
      '' CtrlType=Lookup''
      ''  SqlId=HAMSI_FND_PAARUSERWORKLOCQRY''
      ''  ParamsSrc=@ToolScope''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''   WORK_LOC=Work Location''
      ''   LOC_TITLE=Title''
      ''  Style=Short''
      ''   Static=N''
      ''ORDER_QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''MFG_BOM_REV''
      '' CtrlType=Lookup''
      ''  SqlId=HAMSI_WID_UNPLANNEDORDERMBOMLOOKUP''
      ''  ParamsSrc=@UDVUserFieldValues''
      ''  Validate=Y''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  ShortLookupDelim=|''
      ''  VisibleFields''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords'')
    InsertSqlId = ''CreateStdAlnWO''
    UpdateSqlId = ''CreateStdAlnWO''
    ParamsSQLSourceName = ''@ToolScope,@Auxparams''
    RefreshParams = False
    LinkedControls.Strings = (
      ''_OBJ2~PART_NO''
      ''ORDERQTY~ORDER_QTY''
      ''_OBJ14~PART_CHG''
      ''_OBJ30~ORDER_TYPE''
      ''_OBJ35~SCHED_END_DT''
      ''_OBJ34~SCHED_START_DT''
      ''_OBJ37~WO_LOT_NO''
      ''_OBJ32~ORDER_NO''
      ''_OBJ39~WO_SERIAL_NO''
      ''_OBJ40~VC1''
      ''_OBJ42~VC2''
      ''_OBJ43~VC3''
      ''_OBJ47~VC4''
      ''_OBJ45~VC5''
      ''_OBJ41~NUM1''
      ''_OBJ46~NUM2''
      ''_OBJ44~F1''
      ''OWP_ID~OWP_ID''
      ''contract~CONTRACT''
      ''customer~CUSTOMER''
      ''customerOrderNo~CUSTOMER_ORDER_NO''
      ''ItemSubtype~ITEM_SUBTYPE''
      ''DispSeq~DISPLAY_SEQUENCE''
      ''_OBJ48~NOTES''
      ''_OBJ53~INSTRUCTIONS_TYPE''
      ''_OBJ52~BOM_NO''
      ''_OBJ51~MFG_BOM_REV''
      ''_OBJ54~WORK_FLOW''
      ''_OBJ55~WORK_LOC'')
    DataDefinitions.Strings = (
      ''ORDER_QTY=''
      ''PROGRAM=''
      ''ORDER_TYPE=''
      ''PART_CHG=''
      ''PART_NO=''
      ''SCHED_START_DT=''
      ''SCHED_END_DT=''
      ''WO_SERIAL_FLAG=''
      ''WO_LOT_FLAG=''
      ''WO_LOT_NO=''
      ''ITEM_ID=''
      ''WO_SERIAL_NO=''
      ''ORDER_NO=''
      ''WORK_LOC=''
      ''VC1=''
      ''VC2=''
      ''VC3=''
      ''VC4=''
      ''VC5=''
      ''NUM1=''
      ''NUM2=''
      ''F1=''
      ''OBSOLETE_RECORD_FLAG=''
      ''NOTES=''
      ''OWP_ID=''
      ''UCF_ORDER_VCH6=''
      ''UCF_ORDER_VCH7=''
      ''UCF_ORDER_VCH8=''
      ''UCF_ORDER_VCH9=''
      ''UCF_ORDER_VCH10=''
      ''UCF_ORDER_VCH11=''
      ''UCF_ORDER_VCH12''
      ''UCF_ORDER_VCH13''
      ''UCF_ORDER_VCH14''
      ''UCF_ORDER_VCH15''
      ''UCF_ORDER_NUM3=''
      ''UCF_ORDER_NUM4''
      ''UCF_ORDER_NUM5''
      ''UCF_ORDER_DATE1''
      ''UCF_ORDER_DATE2''
      ''UCF_ORDER_DATE3''
      ''UCF_ORDER_DATE4''
      ''UCF_ORDER_DATE5''
      ''UCF_ORDER_FLAG2''
      ''UCF_ORDER_FLAG3''
      ''UCF_ORDER_FLAG4''
      ''UCF_ORDER_FLAG5''
      ''UCF_ORDER_VCH255_1''
      ''UCF_ORDER_VCH255_2''
      ''UCF_ORDER_VCH255_3''
      ''UCF_ORDER_VCH4000_1''
      ''UCF_ORDER_VCH4000_2''
      ''CUSTOMER=''
      ''CUSTOMER_ORDER_NO=''
      ''CONTRACT=''
      ''REPLACEMENT_ACTION=''
      ''AS_WORKED_BOM_ID=''
      ''RELATED_ORDER_ID=''
      ''DISC_ID''
      ''DISC_LINE_NO=''
      ''ITEM_TYPE=''
      ''ITEM_SUBTYPE=''
      ''DISPLAY_SEQUENCE=''
      ''BOM_NO=''
      ''MFG_BOM_REV=''
      ''WORK_FLOW='')
  end
end';
v_iclob clob;
begin
v_iclob := p1||p2;
begin
insert into sfcore_udv_lib(udv_id,udv_tag,updt_userid,time_stamp,udv_type,stype,udv_desc,state,load_ref,tool_version,object_rev,owner_group,udv_definition)
    values(v_udv_id,v_udv_tag,user,sysdate,v_udv_type,v_stype,v_udv_desc,v_state,v_load_ref,v_tool_version,v_object_rev,v_owner_group,
    replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),'POUND39_HOLDER',chr(35)||chr(51)||chr(57)));
commit;
exception when dup_val_on_index then
  update sfcore_udv_lib
  set udv_definition=replace(replace(v_iclob,chr(39)||chr(39)||chr(39),chr(39)||chr(39)),'POUND39_HOLDER',chr(35)||chr(51)||chr(57)),updt_userid=user,time_stamp=sysdate,
      tool_version=v_tool_version,udv_tag=v_udv_tag,udv_desc = v_udv_desc,state=v_state,object_rev=v_object_rev,owner_group=v_owner_group,
      udv_type=v_udv_type,stype=v_stype
  where udv_id=v_udv_id;
  commit;
end;
begin
if v_folder_id is not null then
insert into sfcore_udv_folder(udv_id,folder_id,updt_userid,time_stamp)
values(v_udv_id,v_folder_id,user,sysdate);
commit;
end if;
exception 
when others then null;
end;
end;
/

set define on

