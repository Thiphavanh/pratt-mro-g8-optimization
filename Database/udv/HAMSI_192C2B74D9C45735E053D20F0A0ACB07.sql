set define off
declare
v_folder_id varchar2(40) :='HAMSI_A36C8B4A07FC5D74E0400A0AD20F28E1';
v_udv_id varchar2(85) :='HAMSI_192C2B74D9C45735E053D20F0A0ACB07';
v_udv_tag varchar2(40) :='HAMSI_CreatStdAlnWOLoc';
v_udv_type varchar2(7) :='INPUT';
v_udv_desc varchar2(255) :='Cloned From MFI_7251';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFPL';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 746
  Height = 420
  AutoScroll = False
  Caption = ''Create Unplanned Order - Program/Location''
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
  DynamicFieldAttributes = False
  BottomMargin = -1
  object _OBJ2: TsfDBInputEdit
    Left = 165
    Top = 43
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 0
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
    Left = 500
    Top = 139
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
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
    Left = 165
    Top = 75
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 2
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
    Left = 500
    Top = 43
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
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
    Left = 500
    Top = 107
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 3
    EntryType = etAlphaNumeric
    Caption = ''Order Number*''
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
    Left = 500
    Top = 203
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 8
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
    Left = 500
    Top = 235
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 9
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
    Left = 166
    Top = 238
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 11
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
    Left = 166
    Top = 142
    Width = 125
    Height = 93
    Color = 14211288
    ColCount = 1
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssVertical
    TabOrder = 7
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
    Left = 105
    Top = 381
    Width = 73
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
    Left = 376
    Top = 419
    Width = 73
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
    Caption = ''NUM2''
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
    Left = 376
    Top = 381
    Width = 73
    Height = 24
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
  object _OBJ43: TsfDBInputEdit
    Left = 518
    Top = 381
    Width = 73
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
  object _OBJ44: TsfDBInputEdit
    Left = 550
    Top = 419
    Width = 41
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 22
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
    Left = 228
    Top = 381
    Width = 73
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
  object _OBJ46: TsfDBInputEdit
    Left = 105
    Top = 419
    Width = 73
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
  object _OBJ47: TsfDBInputEdit
    Left = 228
    Top = 419
    Width = 73
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
    Caption = ''NUM1''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    EnterForOkay = False
  end
  object _OBJ49: TsfDBInputEdit
    Left = 500
    Top = 171
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 6
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
  object OWP_ID: TsfDBInputEdit
    Left = 500
    Top = 267
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 10
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
  object customer: TsfDBInputEdit
    Left = 166
    Top = 270
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 13
    EntryType = etAlphaNumeric
    Caption = ''Customer''
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
    Left = 500
    Top = 299
    Width = 125
    Height = 25
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
  object customerOrderNo: TsfDBInputEdit
    Left = 500
    Top = 331
    Width = 125
    Height = 25
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
    Left = 165
    Top = 107
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 5
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
    Left = 165
    Top = 3
    Width = 460
    Height = 33
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
    TabOrder = 23
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
    ReadOnly = False
  end
  object _OBJ50: TsfLabel
    Left = 73
    Top = 14
    Width = 86
    Height = 13
    Caption = ''Display Sequence''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ShowHint = False
  end
  object _OBJ51: TsfDBInputEdit
    Left = 500
    Top = 75
    Width = 125
    Height = 25
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 24
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
  object OrderCreateStdAln: TsfSqlTransaction
    InputFieldsSSL.Strings = (
      ''ORDER_QTY''
      '' Update=True''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      '' DefaultValue=1''
      '' MaxLength=11''
      '' PersistManualValues=Y''
      ''PROGRAM''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      '' MaxLength=30''
      ''ORDER_TYPE''
      '' Update=False''
      '' Insert=False''
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
      '' Hidden=N''
      '' Required=N''
      ''PART_NO''
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      '' MaxLength=30''
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
      '' Hidden=N''
      '' Required=N''
      ''WO_SERIAL_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=False''
      ''WO_LOT_FLAG''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=N''
      '' Required=N''
      ''WO_LOT_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=Y''
      '' Hidden=N''
      '' Required=N''
      '' MaxLength=30''
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
      '' Insert=False''
      '' PopulateForInsert=N''
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
      '' Update=True''
      '' Insert=True''
      '' PopulateForInsert=Y''
      '' CharCase=N''
      '' Hidden=False''
      '' Required=True''
      '' MaxLength=30''
      '' PersistManualValues=Y''
      ''VC1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''VC2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''VC3''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''VC4''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''VC5''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''F1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''NUM1''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=True''
      '' Required=False''
      '' PersistManualValues=Y''
      ''NUM2''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' Hidden=Y''
      '' Required=N''
      ''OBSOLETE_RECORD_FLAG''
      ''NOTES''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
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
      ''UCF_ORDER_VCH8''
      ''UCF_ORDER_VCH9''
      ''UCF_ORDER_VCH10''
      ''UCF_ORDER_VCH11''
      ''UCF_ORDER_VCH12''
      ''UCF_ORDER_VCH13''
      ''UCF_ORDER_VCH14''
      ''UCF_ORDER_VCH15''
      ''UCF_ORDER_NUM3''
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
      ''REPLACEMENT_ACTION''
      ''AS_WORKED_BOM_ID''
      ''RELATED_ORDER_ID''
      ''DISC_ID''
      ''DISC_LINE_NO''
      ''DISPLAY_SEQUENCE''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' CharCase=N''
      '' DefaultValue=Auto Parallel''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''BOM_NO''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
      '' MaxLength=60''
      '' CharCase=N''
      '' PersistManualValues=Y''
      '' Hidden=False''
      '' Required=False''
      ''MFG_BOM_REV''
      ''WORK_FLOW''
      '' Update=False''
      '' Insert=False''
      '' PopulateForInsert=N''
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
      ''VC2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''VC3''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''VC4''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''VC5''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''F1''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''NUM2''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''WO_LOT_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''WO_LOT_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''SCHED_END_DT''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=MM/DD/YYYY''
      ''  Decimal=0''
      ''  Currency=N''
      ''PART_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''PART_CHG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''PROGRAM''
      '' CtrlType=Lookup''
      ''  SqlId=Pl_ProgramDefSel''
      ''  ParamsSrc=ItemDesc_ProgramLocation''
      ''  Validate=Y''
      ''  Style=Long''
      ''WO_SERIAL_FLAG''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''CUSTOMER''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_FND_CUSTOMERIDSEL''
      ''  Validate=Y''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  Style=Short''
      ''   Static=N''
      ''NOTES''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''OWP_ID''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_WID_OrdWorkPkgSel''
      ''  Validate=N''
      ''  DefaultToSingle=N''
      ''  IMLookupFilter=N''
      ''  Style=Long''
      ''   FilterDefaults''
      ''    FilterCase=N''
      ''    PartialWordMatch=Y''
      ''    FindMultipleWords=Y''
      ''    DelimitedBy=,''
      ''    SearchType=stAnyWords''
      ''ORDER_NO''
      '' CtrlType=Lookup''
      ''  SqlId=MFI_WID_WONumberMaskLkup''
      ''  ParamsSrc=@UDVUserFieldValues''
      ''  Validate=N''
      ''  DefaultToSingle=Y''
      ''  IMLookupFilter=N''
      ''  Style=Short''
      ''   Static=N''
      ''ITEM_TYPE''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''ITEM_SUBTYPE''
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
      ''CONTRACT''
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
      ''CUSTOMER_ORDER_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
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
      ''ITEM_ID''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
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
      ''SCHED_START_DT''
      '' CtrlType=Edit''
      ''  DataType=Date''
      ''  Format=MM/DD/YYYY''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''BOM_NO''
      '' CtrlType=Edit''
      ''  DataType=Alphanumeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  TimeAlgorithm=Now''
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
      ''ORDER_QTY''
      '' CtrlType=Edit''
      ''  DataType=Numeric''
      ''  Decimal=0''
      ''  Currency=N''
      ''  AllowManaging=N''
      ''  SelectFolder=N''
      ''WORK_LOC''
      '' CtrlType=Lookup''
      ''  SqlId=Pl_WorkLocSel3''
      ''  ParamsSrc=ItemDesc_ProgramLocation''
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
    ParamsSQLSourceName = ''itemdesc_programlocation''
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
      ''_OBJ45~VC2''
      ''_OBJ42~VC3''
      ''_OBJ43~VC4''
     ';
p2 clob :=' ''_OBJ46~VC5''
      ''_OBJ47~NUM1''
      ''_OBJ41~NUM2''
      ''_OBJ44~F1''
      ''_OBJ49~WORK_LOC''
      ''OWP_ID~OWP_ID''
      ''customer~CUSTOMER''
      ''contract~CONTRACT''
      ''customerOrderNo~CUSTOMER_ORDER_NO''
      ''ItemSubtype~ITEM_SUBTYPE''
      ''DispSeq~DISPLAY_SEQUENCE''
      ''_OBJ51~WORK_FLOW'')
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
      ''F1=''
      ''NUM1=''
      ''NUM2=''
      ''OBSOLETE_RECORD_FLAG''
      ''NOTES=''
      ''OWP_ID=''
      ''UCF_ORDER_VCH6=''
      ''UCF_ORDER_VCH7''
      ''UCF_ORDER_VCH8''
      ''UCF_ORDER_VCH9''
      ''UCF_ORDER_VCH10''
      ''UCF_ORDER_VCH11''
      ''UCF_ORDER_VCH12''
      ''UCF_ORDER_VCH13''
      ''UCF_ORDER_VCH14''
      ''UCF_ORDER_VCH15''
      ''UCF_ORDER_NUM3''
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
      ''ITEM_TYPE=''
      ''ITEM_SUBTYPE=''
      ''REPLACEMENT_ACTION''
      ''AS_WORKED_BOM_ID''
      ''RELATED_ORDER_ID''
      ''DISC_ID''
      ''DISC_LINE_NO''
      ''DISPLAY_SEQUENCE=''
      ''BOM_NO=''
      ''MFG_BOM_REV''
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

