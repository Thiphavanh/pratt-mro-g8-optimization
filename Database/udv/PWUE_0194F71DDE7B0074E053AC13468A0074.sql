set define off
declare
v_folder_id varchar2(40) :='PWUE_F3C1092EA7C90070E043AC13468A0070';
v_udv_id varchar2(85) :='PWUE_0194F71DDE7B0074E053AC13468A0074';
v_udv_tag varchar2(40) :='PWUE_OrderHeaderTab';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='SAEE_WOE_30';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.19.0.20131008~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1000
  Height = 370
  AutoScroll = False
  IMScanAction = saIMAccept
  IMAllowHeaderChanges = False
  IMFixedColCount = -1
  IMPopulateFromParams = False
  UdvControlType = uctPanel
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
  object _OBJ3: TsfDBEdit
    Left = 151
    Top = 73
    Width = 100
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 15
    Caption = ''Main Work Location''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ4: TsfDBEdit
    Left = 778
    Top = 191
    Width = 93
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 18
    Caption = ''Customer ID''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ5: TsfDBEdit
    Left = 6
    Top = 306
    Width = 49
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 11
    Caption = ''Order Qty''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ6: TsfDBEdit
    Left = 472
    Top = 307
    Width = 87
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 12
    Caption = ''Scrap Qty''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ8: TsfDBEdit
    Left = 504
    Top = 193
    Width = 160
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 2
    Caption = ''Sched Priority''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ9: TsfDBEdit
    Left = 220
    Top = 194
    Width = 73
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 16
    Caption = ''Initial Stores''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ10: TsfDBEdit
    Left = 297
    Top = 193
    Width = 84
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 17
    Caption = ''Final Stores''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ11: TsfDBEdit
    Left = 387
    Top = 193
    Width = 112
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 28
    Caption = ''Contract No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ12: TsfDBEdit
    Left = 160
    Top = 270
    Width = 58
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 14
    Caption = ''Was Split?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ13: TsfDBEdit
    Left = 287
    Top = 37
    Width = 230
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 8
    Caption = ''Order Title''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ14: TsfDBEdit
    Left = 776
    Top = 268
    Width = 86
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 13
    Caption = ''UOM''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ15: TsfDBEdit
    Left = 467
    Top = 73
    Width = 88
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 10
    Caption = ''Model''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ17: TsfLabel
    Left = 968
    Top = 150
    Width = 4
    Height = 13
    Alignment = taRightJustify
    Caption = ''.''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ18: TsfUDVNavigator
    Left = 4
    Top = 4
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Edit ''
      ''Refresh ''
      ''Other commands '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ19: TsfDBEdit
    Left = 684
    Top = 269
    Width = 80
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 3
    Caption = ''End Unit No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ20: TsfDBEdit
    Left = 189
    Top = 339
    Width = 118
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 23
    Caption = ''Customer Order No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ21: TsfDBEdit
    Left = 192
    Top = 37
    Width = 93
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 1
    Caption = ''Order No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ22: TsfDBEdit
    Left = 482
    Top = 234
    Width = 51
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 19
    Caption = ''Serial No?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ23: TsfDBEdit
    Left = 416
    Top = 234
    Width = 49
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 24
    Caption = ''Lot No?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ24: TsfDBEdit
    Left = 6
    Top = 147
    Width = 115
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 20
    Caption = ''Sched Start Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ25: TsfDBEdit
    Left = 130
    Top = 147
    Width = 124
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 27
    Caption = ''Sched End Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ26: TsfDBEdit
    Left = 264
    Top = 147
    Width = 124
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 21
    Caption = ''Revised Start Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ27: TsfDBEdit
    Left = 397
    Top = 147
    Width = 124
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 26
    Caption = ''Revised End Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ28: TsfDBEdit
    Left = 4
    Top = 231
    Width = 135
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 22
    Caption = ''Actual Start Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ29: TsfDBEdit
    Left = 145
    Top = 231
    Width = 147
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 25
    Caption = ''Actual End Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ30: TsfDBEdit
    Left = 61
    Top = 305
    Width = 84
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 4
    Caption = ''Plan Item No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ31: TsfDBEdit
    Left = 5
    Top = 340
    Width = 72
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 5
    Caption = ''Plan Item Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ32: TsfDBEdit
    Left = 6
    Top = 73
    Width = 67
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 6
    Caption = ''Plan Ver''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ33: TsfDBEdit
    Left = 77
    Top = 73
    Width = 70
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 7
    Caption = ''Plan Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ36: TsfDBEdit
    Left = 876
    Top = 269
    Width = 94
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 9
    Caption = ''End Unit Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ38: TsfDBEdit
    Left = 106
    Top = 37
    Width = 84
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 29
    Caption = ''MBOM Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ40: TsfDBEdit
    Left = 275
    Top = 308
    Width = 90
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 30
    Caption = ''Stop Qty''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ41: TsfDBEdit
    Left = 93
    Top = 187
    Width = 90
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 31
    Caption = ''Complete Qty''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ42: TsfDBEdit
    Left = 6
    Top = 186
    Width = 84
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 32
    Caption = ''In Process Qty''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ43: TsfDBEdit
    Left = 519
    Top = 37
    Width = 77
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 33
    Caption = ''Build''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ44: TsfDBEdit
    Left = 279
    Top = 110
    Width = 108
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 34
    Caption = ''Module CSN''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ45: TsfDBEdit
    Left = 255
    Top = 73
    Width = 114
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 35
    Caption = ''Work Center''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ46: TsfDBEdit
    Left = 559
    Top = 73
    Width = 50
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 36
    Caption = ''Mod Code''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ47: TsfDBEdit
    Left = 387
    Top = 307
    Width = 80
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 37
    Caption = ''Plan VCH4''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ48: TsfDBEdit
    Left = 613
    Top = 73
    Width = 92
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 38
    Caption = ''AFS Checkoff''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ49: TsfDBEdit
    Left = 565
    Top = 307
    Width = 99
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 39
    Caption = ''Plan VCH6''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ50: TsfDBEdit
    Left = 830
    Top = 307
    Width = 140
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 40
    Caption = ''Plan VCH8''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ51: TsfDBEdit
    Left = 824
    Top = 343
    Width = 71
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 41
    Caption = ''Plan Flag1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ52: TsfDBEdit
    Left = 903
    Top = 343
    Width = 68
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 42
    Caption = ''Plan Flag2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ53: TsfDBEdit
    Left = 509
    Top = 343
    Width = 70
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 43
    Caption = ''Order Num1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ54: TsfDBEdit
    Left = 432
    Top = 343
    Width = 74
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 44
    Caption = ''Order Flag1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ55: TsfDBEdit
    Left = 582
    Top = 343
    Width = 83
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 45
    Caption = ''Order Num2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ56: TsfDBEdit
    Left = 373
    Top = 73
    Width = 90
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 46
    Caption = ''Engine Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ57: TsfDBEdit
    Left = 191
    Top = 110
    Width = 80
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 47
    Caption = ''Config Engine''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ58: TsfDBEdit
    Left = 88
    Top = 110
    Width = 95
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 48
    Caption = ''SAP Order No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ59: TsfDBEdit
    Left = 94
    Top = 345
    Width = 80
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 49
    Caption = ''Order VCH2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ60: TsfDBEdit
    Left = 6
    Top = 110
    Width = 74
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 50
    Caption = ''SAP Prefix''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ61: TsfDBEdit
    Left = 671
    Top = 343
    Width = 70
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 51
    Caption = ''Plan Num1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ62: TsfDBEdit
    Left = 748
    Top = 343
    Width = 70
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 52
    Caption = ''Plan Num2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object Plan_type: TsfDBEdit
    Left = 904
    Top = 229
    Width = 63
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 53
    Caption = ''Plan Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ63: TsfCommandButton
    Left = 211
    Top = 4
    Width = 100
    Height = 23
    Hint = ''Additional Order Info''
    Caption = ''Additional Order Info''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 55
    TabStop = True
    OtherCommands.Strings = (

        ''Desc=Additional Order Info,Priv=,Action=SideNote,SqlID=OrderInfo'' +
        ''Sel,Params(ORDER_ID =:ORDER_ID)'')
  end
  object LTA_SEND_FLAG: TsfDBEdit
    Left = 148
    Top = 304
    Width = 121
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 54
    Caption = ''Send Labor Flag''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object Update_Build_Part: TsfCommandButton
    Left = 75
    Top = 4
    Width = 125
    Height = 20
    Hint = ''Update Build Part''
    Caption = ''Update Build Item''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 56
    TabStop = True
    OtherCommands.Strings = (

        ''Desc=Update Build Part,''#39''Priv=@ToolState = ''#39#39''EditModes.Edit_PL''#39#39'' '' +
        ''AND (ALTERATION_LEVEL=''#39#39''ORDER''#39#39'' OR ALTERATION_LEVEL=''#39#39''DISPOSITIO'' +
        ''N''#39#39''  OR ALTERATION_LEVEL= ''#39#39''SUPERCEDE''#39#39'' )''#39'',Visible=,TagValue=,FK'' +
        ''ey=,Action=UDV,UDVType=Insert,UDVID=MFI_C547A28CDEAC4CAC9C68D824'' +
        ''755C3D79'')
  end
  object PLAN_ITEM_TYPE: TsfDBEdit
    Left = 688
    Top = 195
    Width = 85
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 57';
p2 clob :='
    Caption = ''Plan Item Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object Condition: TsfDBEdit
    Left = 880
    Top = 191
    Width = 86
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 58
    Caption = ''Condition''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UID_ITEM_FLAG: TsfDBEdit
    Left = 557
    Top = 231
    Width = 107
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 59
    Caption = ''UID Item Flag''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UID_ENTRY_NAME: TsfDBEdit
    Left = 668
    Top = 231
    Width = 125
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 60
    Caption = ''UID Entry Name''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object ISSUE_AGENCY_CODE: TsfDBEdit
    Left = 798
    Top = 231
    Width = 103
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 61
    Caption = ''Issuing Agency Code''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object ENTERPRISE_ID: TsfDBEdit
    Left = 4
    Top = 269
    Width = 68
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 62
    Caption = ''Enterprise ID''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object DATA_QUALIFIER: TsfDBEdit
    Left = 220
    Top = 269
    Width = 70
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 63
    Caption = ''Data Qualifier''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object CONSTRUCT_TYPE: TsfDBEdit
    Left = 297
    Top = 269
    Width = 130
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 64
    Caption = ''Construct Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ64: TsfDBEdit
    Left = 6
    Top = 37
    Width = 98
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 65
    Caption = ''BOM No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ65: TsfDBEdit
    Left = 557
    Top = 269
    Width = 107
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 66
    Caption = ''FAI Oper Required''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ66: TsfDBEdit
    Left = 72
    Top = 270
    Width = 88
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 67
    Caption = ''Plan Item Subtype''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ67: TsfDBEdit
    Left = 780
    Top = 37
    Width = 96
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 68
    Caption = ''Plan No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object DispSeq: TsfDBEdit
    Left = 302
    Top = 231
    Width = 101
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 69
    Caption = ''Display Sequence''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object WBS_PROJECT: TsfDBEdit
    Left = 431
    Top = 269
    Width = 121
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 70
    Caption = ''WBS Project''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ69: TsfDBEdit
    Left = 672
    Top = 37
    Width = 106
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 71
    Caption = ''Eng/Module Serial No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ73: TsfDBEdit
    Left = 398
    Top = 110
    Width = 96
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 72
    Caption = ''Due Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ68: TsfDBEdit
    Left = 599
    Top = 37
    Width = 69
    Height = 22
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 73
    Caption = ''SOC''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object chgNote: TsfDBMemo
    Left = 544
    Top = 110
    Width = 337
    Height = 54
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssBoth
    ShowHint = False
    TabOrder = 74
    MemoCaption = ''Chg Level Notes''
    CaptionPos = cpAbove
    CaptionFont.Charset = ANSI_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object PlanPartInfoSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_SelectPlanItemInfo''
    LinkedControls.Strings = (
      ''_OBJ30~PLAN_PART_NO''
      ''_OBJ31~PLAN_PART_CHG''
      ''_OBJ66~PLAN_ITEM_SUBTYPE''
      ''PLAN_ITEM_TYPE~PLAN_ITEM_TYPE'')
    ParamsSQLSourceName = ''OrderHeaderTabSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''PLAN_PART_NO~0~PLAN_PART_NO~N~N~True~False~True~N~~~~~Default~N~''

        ''PLAN_PART_CHG~0~PLAN_PART_CHG~N~N~True~False~True~N~~~~~Default~'' +
        ''N~''

        ''PLAN_ITEM_TYPE~0~PLAN_ITEM_TYPE~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~''

        ''PLAN_ITEM_SUBTYPE~0~PLAN_ITEM_SUBTYPE~N~N~True~False~True~N~~~~~'' +
        ''Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''PART_NO~Edit''
      ''PLAN_PART_CHG~Edit''
      ''PLAN_ITEM_TYPE~Edit''
      ''PLAN_ITEM_SUBTYPE~Edit'')
    DataDefinitions.Strings = (
      ''PLAN_PART_NO''
      ''PLAN_PART_CHG''
      ''PLAN_ITEM_TYPE''
      ''PLAN_ITEM_SUBTYPE'')
    ConsolidatedQuery = False
  end
  object OrderHeaderTabSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUE_OrderHeaderTabSel''
    LinkedControls.Strings = (
      ''_OBJ1~ORDER_STATUS''
      ''_OBJ11~CONTRACT_NO''
      ''_OBJ13~PLAN_TITLE''
      ''_OBJ14~ORDER_UOM''
      ''_OBJ19~UNIT_NO''
      ''_OBJ18~''
      ''_OBJ33~PLAN_REVISION''
      ''_OBJ32~PLAN_VERSION''
      ''_OBJ36~UNIT_TYPE''
      ''_OBJ21~ORDER_NO''
      ''_OBJ8~SCHED_PRIORITY''
      ''_OBJ45~UCF_PLAN_VCH2''
      ''_OBJ47~UCF_PLAN_VCH4''
      ''_OBJ49~UCF_PLAN_VCH6''
      ''_OBJ44~UCF_PLAN_VCH7''
      ''_OBJ50~UCF_PLAN_VCH8''
      ''_OBJ51~UCF_PLAN_FLAG1''
      ''_OBJ52~UCF_PLAN_FLAG2''
      ''_OBJ54~UCF_ORDER_FLAG1''
      ''_OBJ53~UCF_ORDER_NUM1''
      ''_OBJ55~UCF_ORDER_NUM2''
      ''_OBJ59~UCF_ORDER_VCH2''
      ''_OBJ57~UCF_ORDER_VCH3''
      ''_OBJ58~UCF_ORDER_VCH4''
      ''_OBJ60~UCF_ORDER_VCH5''
      ''_OBJ4~ORDER_CUST_ID''
      ''_OBJ20~CUSTOMER_ORDER_NO''
      ''_OBJ22~SERIAL_FLAG''
      ''_OBJ12~SPLIT_FLAG''
      ''_OBJ23~LOT_FLAG''
      ''_OBJ9~INITIAL_STORES''
      ''_OBJ10~FINAL_STORES''
      ''_OBJ3~ASGND_WORK_LOC''
      ''_OBJ5~ORDER_QTY''
      ''_OBJ6~ORDER_SCRAP_QTY''
      ''_OBJ41~ORDER_COMPLETE_QTY''
      ''_OBJ40~ORDER_STOP_QTY''
      ''_OBJ42~ORDER_INPROCESS_QTY''
      ''_OBJ61~UCF_PLAN_NUM1''
      ''_OBJ62~UCF_PLAN_NUM2''
      ''_OBJ24~SCHED_START_DATE''
      ''_OBJ25~SCHED_END_DATE''
      ''_OBJ28~ACTUAL_START_DATE''
      ''_OBJ29~ACTUAL_END_DATE''
      ''_OBJ26~REVISED_START_DATE''
      ''_OBJ27~REVISED_END_DATE''
      ''Plan_type~PLAN_TYPE''
      ''_OBJ63~''
      ''LTA_SEND_FLAG~LTA_SEND_FLAG''
      ''Update_Build_Part~''
      ''Condition~CONDITION''
      ''_OBJ65~FAI_OPER_REQD''
      ''_OBJ67~PLAN_NO''
      ''DispSeq~DISPLAY_SEQUENCE''
      ''WBS_PROJECT~ACCOUNT_LABOR''
      ''_OBJ73~UCF_ORDER_DATE1''
      ''_OBJ69~SERIAL_NO''
      ''_OBJ38~MFG_BOM_CHG''
      ''_OBJ48~UCF_PLAN_VCH5''
      ''_OBJ46~UCF_PLAN_VCH3''
      ''_OBJ64~BOM_NO''
      ''~UCF_PLAN_VCH4000_1''
      ''chgNote~UCF_PLAN_VCH4000_1'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (

        ''MFG_BOM_CHG~4~MFG_BOM_CHG~N~N~MRO_FLAG = ''#39''Y''#39'' AND ORDER_PURPOSE ='' +
        '' ''#39''Part''#39''~N~N~N~~~~~Default~N~''
      ''ORDER_NO~40~ORDER_NO~N~N~N~N~N~N~~~~~Default~N~''
      ''PLAN_TITLE~80~PLAN_TITLE~N~N~N~N~N~N~~~~~Default~N~''
      ''BUILD_INCR~8~BUILD_INCR~N~N~False~False~False~N~~~~~Default~N~''
      ''UCF_ORDER_VCH7~20~SOC~N~N~False~False~False~N~~~~~Default~N~''

        ''SERIAL_NO~10~Eng/Module Serial No~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      ''PLAN_NO~40~PLAN_NO~N~N~False~False~False~N~~~~~Default~N~''
      ''PLAN_VERSION~10~PLAN_VERSION~N~N~N~N~N~N~~~~~Default~N~''
      ''PLAN_REVISION~10~PLAN_REVISION~N~N~N~N~N~N~~~~~Default~N~''
      ''ASGND_WORK_LOC~30~ASGND_WORK_LOC~N~N~N~N~N~N~~~~~Default~N~''
      ''UCF_PLAN_VCH2~30~Work Center~N~N~False~N~False~N~~~~~Default~N~''
      ''UCF_ORDER_VCH1~50~Engine Type~N~N~False~N~False~N~~~~~Default~N~''
      ''MODEL~20~MODEL~N~N~N~N~N~N~~~~~Default~N~''
      ''SCHED_START_DATE~18~SCHED_START_DATE~N~N~N~N~N~N~~~~~Default~N~''
      ''SCHED_END_DATE~18~SCHED_END_DATE~N~N~N~N~N~N~~~~~Default~N~''

        ''UCF_ORDER_DATE1~18~Due Date~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''

        ''REVISED_START_DATE~18~REVISED_START_DATE~N~N~N~N~N~N~~~~~Default'' +
        ''~N~''
      ''REVISED_END_DATE~18~REVISED_END_DATE~N~N~N~N~N~N~~~~~Default~N~''
      ''ORDER_ID~40~ORDER_ID~N~N~True~N~True~N~~~~~Default~N~''
      ''PLAN_ID~40~PLAN_ID~N~N~True~N~True~N~~~~~Default~N~''
      
        ''PLAN_ALTERATIONS~10~PLAN_ALTERATIONS~N~N~True~N~True~N~~~~~Defau'' +
        ''lt~N~''
      ''PLAN_UPDT_NO~10~PLAN_UPDT_NO~N~N~True~N~True~N~~~~~Default~N~''

        ''UPDT_USERID~30~UPDT_USERID~N~N~True~N~True~N~MFI_2869~~~~Default'' +
        ''~N~''
      ''TIME_STAMP~18~TIME_STAMP~N~N~True~N~True~N~~~~~Default~N~''
      ''ORDER_STATUS~20~ORDER_STATUS~N~N~True~N~True~N~~~~~Default~N~''

        ''ORDER_HOLD_STATUS~20~ORDER_HOLD_STATUS~N~N~True~N~True~N~~~~~Def'' +
        ''ault~N~''
      ''ORDER_CUST_ID~40~ORDER_CUST_ID~N~N~True~N~True~N~~~~~Default~N~''

        ''PARENT_ORDER_ID~40~PARENT_ORDER_ID~N~N~True~N~True~N~~~~~Default'' +
        ''~N~''
      ''SCHED_PRIORITY~4~SCHED_PRIORITY~N~N~True~N~True~N~~~~~Default~N~''

        ''INITIAL_STORES~20~INITIAL_STORES~N~N~True~N~True~N~~~~~Default~N'' +
        ''~''
      ''FINAL_STORES~20~FINAL_STORES~N~N~True~N~True~N~~~~~Default~N~''
      ''CONTRACT_NO~30~CONTRACT_NO~N~N~True~N~True~N~~~~~Default~N~''
      ''ORIG_ORDER_ID~40~ORIG_ORDER_ID~N~N~True~N~True~N~~~~~Default~N~''

        ''SUPERCEDED_ORDER_ID~40~SUPERCEDED_ORDER_ID~N~N~True~N~True~N~~~~'' +
        ''~Default~N~''
      ''SPLIT_FLAG~1~SPLIT_FLAG~N~N~True~N~True~N~~~~~Default~N~''

        ''ACTUAL_START_DATE~18~ACTUAL_START_DATE~N~N~True~N~True~N~~~~~Def'' +
        ''ault~N~''

        ''ACTUAL_END_DATE~18~ACTUAL_END_DATE~N~N~True~N~True~N~~~~~Default'' +
        ''~N~''
      ''UNIT_NO~30~UNIT_NO~N~N~True~N~True~N~~~~~Default~N~''

        ''CUSTOMER_ORDER_NO~30~CUSTOMER_ORDER_NO~N~N~True~N~True~N~~~~~Def'' +
        ''ault~N~''
      ''PLAN_TYPE~20~Plan Type~N~N~True~N~True~N~~~~~Default~N~''
      ''ALTER_TYPE~20~ALTER_TYPE~N~N~True~N~True~N~~~~~Default~N~''

        ''SUPERCEDES_ORDER~30~SUPERCEDES_ORDER~N~N~True~N~True~N~~~~~Defau'' +
        ''lt~N~''

        ''STATUS_CHG_NOTES~255~STATUS_CHG_NOTES~N~N~True~N~True~N~~~~~Defa'' +
        ''ult~N~''
      ''SERIAL_FLAG~1~SERIAL_FLAG~N~N~True~N~True~N~~~~~Default~N~''
      ''LOT_FLAG~1~LOT_FLAG~N~N~True~N~True~N~~~~~Default~N~''
      ''PART_NO~50~PART_NO~N~N~False~N~False~N~~~~~Default~N~''
      ''PART_CHG~4~PART_CHG~N~N~True~N~True~N~~~~~Default~N~''
      ''ITEM_TYPE~8~ITEM_TYPE~N~N~True~False~True~N~~~~~Default~N~''

        ''ITEM_SUBTYPE~12~ITEM_SUBTYPE~N~N~True~False~True~N~~~~~Default~N'' +
        ''~''
      ''ORDER_UOM~10~ORDER_UOM~N~N~True~N~True~N~~~~~Default~N~''
      ''UNIT_TYPE~30~UNIT_TYPE~N~N~True~N~True~N~~~~~Default~N~''
      ''ALT_ID~40~ALT_ID~N~N~True~N~True~N~~~~~Default~N~''
      ''ALT_COUNT~10~ALT_COUNT~N~N~True~N~True~N~~~~~Default~N~''
      ''ALT_STATUS~20~ALT_STATUS~N~N~True~N~True~N~~~~~Default~N~''
      ''ENG_PART_NO~50~ENG_PART_NO~N~N~True~N~True~N~~~~~Default~N~''
      ''ENG_PART_CHG~4~ENG_PART_CHG~N~N~True~N~True~N~~~~~Default~N~''
      ''MFG_INDEX_NO~10~MFG_INDEX_NO~N~N~True~N~True~N~~~~~Default~N~''
      ''UCF_PLAN_FLAG1~1~UCF_PLAN_FLAG1~N~N~Y~N~True~N~~~~~Default~N~''
      ''UCF_ORDER_VCH2~50~UCF_ORDER_VCH2~N~N~Y~N~True~N~~~~~Default~N~''
      ''UCF_ORDER_NUM1~10~UCF_ORDER_NUM1~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_ORDER_FLAG1~1~UCF_ORDER_FLAG1~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_PLAN_VCH3~4~Mod Code~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_PLAN_VCH4~15~Use Level~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_PLAN_VCH5~10~AFS Checkoff~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_PLAN_VCH6~50~Source;Document~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_PLAN_VCH7~25~Module CSN~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_PLAN_VCH8~50~UCF_PLAN_VCH8~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_PLAN_FLAG2~1~UCF_PLAN_FLAG2~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_ORDER_VCH3~10~UCF_ORDER_VCH3~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_ORDER_VCH4~10~UCF_ORDER_VCH4~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_ORDER_VCH5~10~UCF_ORDER_VCH5~N~N~False~N~N~N~~~~~Default~N~''
      ''UCF_ORDER_NUM2~10~UCF_ORDER_NUM2~N~N~Y~N~N~N~~~~~Default~N~''

        ''NEEDS_REVIEW_FLAG~1~NEEDS_REVIEW_FLAG~N~N~True~N~N~N~~~~~Default'' +
        ''~N~''

        ''STATUS_CHG_REASON~10~STATUS_CHG_REASON~N~N~True~N~N~N~~~~~Defaul'' +
        ''t~N~''
      ''ORDER_QTY~10~ORDER_QTY~N~N~True~N~N~N~~~~~Default~N~''

        ''ORDER_SCRAP_QTY~10~ORDER_SCRAP_QTY~N~N~True~N~True~N~~~~~Default'' +
        ''~N~''

        ''ORDER_COMPLETE_QTY~10~ORDER_COMPLETE_QTY~N~N~True~N~True~N~~~~~D'' +
        ''efault~N~''

        ''ORDER_STOP_QTY~10~ORDER_STOP_QTY~N~N~True~N~True~N~~~~~Default~N'' +
        ''~''

        ''ORDER_INPROCESS_QTY~10~ORDER_INPROCESS_QTY~N~N~True~N~True~N~~~~'' +
        ''~Default~N~''
      ''UCF_PLAN_NUM1~10~Sigma;Category~N~N~Y~N~N~N~~~~~Default~N~''
      ''UCF_PLAN_NUM2~10~UCF_PLAN_NUM2~N~N~Y~N~N~N~~~~~Default~N~''
      ''LTA_SEND_FLAG~1~Send Labor Flag~N~N~True~N~True~N~~~~~Default~N~''

        ''DISPLAY_SEQUENCE~0~DISPLAY_SEQUENCE~N~N~True~False~True~N~~~~~De'' +
        ''fault~N~''

        ''FAI_OPER_REQD~0~FAI Oper Required~N~N~True~False~True~N~~~~~Defa'' +
        ''ult~N~''

        ''ACCOUNT_LABOR~0~ACCOUNT_LABOR~N~N~True~False~True~N~~~~~Default~'' +
        ''N~''
      ''CONDITION~0~CONDITION~N~N~True~False~True~N~~~~~Default~N~''
      ''BOM_NO~25~Bom No~N~N~False~False~False~N~~~~~Default~N~''

        ''UCF_PLAN_VCH4000_1~50~Chg Level Notes~N~N~False~False~False~N~~~'' +
        ''~~Default~N~'')
    PrintTitle = ''Header''
    InputUDVId = ''MFI_1003329''
    OtherCommands.Strings = (

        ''Desc=Order History,Priv=,Visible=,TagValue=,FKey=,Action=Invoke,'' +
        ''Tool=TabReport,ReportToolId=OrderHistory,Params(ORDER_ID =:ORDER'' +
        ''_ID)'')
    DataDefinitions.Strings = (
      ''MFG_BOM_CHG''
      ''ORDER_NO''
      ''PLAN_TITLE''
      ''BUILD_INCR''
      ''UCF_ORDER_VCH7''
      ''SERIAL_NO''
      ''PLAN_NO''
      ''PLAN_VERSION''
      ''PLAN_REVISION''
      ''ASGND_WORK_LOC''
      ''UCF_PLAN_VCH2''
      ''UCF_ORDER_VCH1''
      ''MODEL''
      ''SCHED_START_DATE''
      ''SCHED_END_DATE''
      ''UCF_ORDER_DATE1''
      ''REVISED_START_DATE''
      ''REVISED_END_DATE''
      ''ORDER_ID''
      ''PLAN_ID''
      ''PLAN_ALTERATIONS''
      ''PLAN_UPDT_NO''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''ORDER_STATUS''
      ''ORDER_HOLD_STATUS''
      ''ORDER_CUST_ID''
      ''PARENT_ORDER_ID''
      ''SCHED_PRIORITY''
      ''INITIAL_STORES''
      ''FINAL_STORES''
      ''CONTRACT_NO''
      ''ORIG_ORDER_ID''
      ''SUPERCEDED_ORDER_ID''
      ''SPLIT_FLAG''
      ''ACTUAL_START_DATE''
      ''ACTUAL_END_DATE''
      ''UNIT_NO''
      ''CUSTOMER_ORDER_NO''
      ''PLAN_TYPE''
      ''ALTER_TYPE''
      ''SUPERCEDES_ORDER''
      ''STATUS_CHG_NOTES''
      ''SERIAL_FLAG''
      ''LOT_FLAG''
      ''PART_NO''
      ''PART_CHG''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''ORDER_UOM''
      ''UNIT_TYPE''
      ''ALT_ID''
      ''ALT_COUNT''
      ''ALT_STATUS''
      ''ENG_PART_NO''
      ''ENG_PART_CHG''
      ''MFG_INDEX_NO''
      ''UCF_PLAN_FLAG1''
      ''UCF_ORDER_VCH2''
      ''UCF_ORDER_NUM1''
      ''UCF_ORDER_FLAG1''
      ''UCF_PLAN_VCH3''
      ''UCF_PLAN_VCH4''
      ''UCF_PLAN_VCH5''
      ''UCF_PLAN_VCH6''
      ''UCF_PLAN_VCH7''
      ''UCF_PLAN_VCH8''
      ''UCF_PLAN_FLAG2''
      ''UCF_ORDER_VCH3''
      ''UCF_ORDER_VCH4''
      ''UCF_ORDER_VCH5''
      ''UCF_ORDER_NUM2''
      ''NEEDS_REVIEW_FLAG''
      ''STATUS_CHG_REASON''
      ''ORDER_QTY''
      ''ORDER_SCRAP_QTY''
      ''ORDER_COMPLETE_QTY''
      ''ORDER_STOP_QTY''
      ''ORDER_INPROCESS_QTY''
      ''UCF_PLAN_NUM1''
      ''UCF_PLAN_NUM2''
      ''LTA_SEND_FLAG''
      ''DISPLAY_SEQUENCE''
      ''FAI_OPER_REQD''
      ''ACCOUNT_LABOR''
      ''CONDITION''
      ''BOM_NO''
      ''UCF_PLAN_VCH4000_1'')
    ConsolidatedQuery = False
  end
  object UidInfoSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_UidInfoSel''
    LinkedControls.Strings = (
      ''UID_ITEM_FLAG~UID_ITEM_FLAG''
      ''UID_ENTRY_NAME~UID_ENTRY_NAME''
      ''ISSUE_AGENCY_CODE~ISSUE_AGENCY_CODE''
      ''ENTERPRISE_ID~ENTERPRISE_ID''
      ''DATA_QUALIFIER~DATA_QUALIFIER''
      ''CONSTRUCT_TYPE~CONSTRUCT_TYPE'')
    ParamsSQLSourceName = ''OrderHeaderTabSel''
    SelectedFields.Strings = (

        ''UID_ENTRY_NAME~0~UID_ENTRY_NAME~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~''

        ''UID_ITEM_FLAG~0~UID_ITEM_FLAG~N~N~True~False~True~N~~~~~Default~'' +
        ''N~''

        ''ENTERPRISE_ID~0~ENTERPRISE_ID~N~N~True~False~True~N~~~~~Default~'' +
        ''N~''

        ''DATA_QUALIFIER~0~DATA_QUALIFIER~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~''

        ''CONSTRUCT_TYPE~0~CONSTRUCT_TYPE~N~N~True~False~True~N~~~~~Defaul'' +
        ''t~N~''

        ''ISSUE_AGENCY_CODE~0~ISSUE_AGENCY_CODE~N~N~True~False~True~N~~~~~'' +
        ''Default~N~''

        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~'')
    SelectedFieldsEditControl.Strings = (
      ''SECURITY_GROUP~Edit'')
    DataDefinitions.Strings = (
      ''UID_ENTRY_NAME''
      ''UID_ITEM_FLAG''
      ''ENTERPRISE_ID''
      ''DATA_QUALIFIER''
      ''CONSTRUCT_TYPE''
      ''ISSUE_AGENCY_CODE''
      ''SECURITY_GROUP'')
    ConsolidatedQuery = False
  end
  object Model_SocSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUE_OrderHeaderTabSel2''
    LinkedControls.Strings = (
      ''_OBJ15~MODEL''
      ''_OBJ68~SOC''
      ''_OBJ43~BLD''
      ''_OBJ56~ENG_TYPE'')
    ParamsSQLSourceName = ''OrderHeaderTabSel''
    PublishParams = True
    SelectedFields.Strings = (
      ''MFG_BOM_CHG~0~MFG_BOM_CHG~N~N~False~False~False~N~~~~~Default~N~''
      ''MODEL~15~MODEL~N~N~False~False~False~N~~~~~Default~N~''
      ''SOC~4~SOC~N~N~False~False~False~N~~~~~Default~N~''

        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      ''BLD~4~Bld~N~N~False~False~False~N~~~~~Default~N~''
      ''ENG_TYPE~10~Eng Type~N~N~False~False~False~N~~~~~Default~N~'')
    DataDefinitions.Strings = (
      ''MFG_BOM_CHG''
      ''MODEL''
      ''SOC''
      ''SECURITY_GROUP'')
    ConsolidatedQuery = False
  end
  object TOVCController
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
