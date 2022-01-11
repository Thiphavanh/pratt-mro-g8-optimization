
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_63898D3FEB05FFEDE05387971F0A06D5.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_63898D3FEB05FFEDE05387971F0A06D5';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderHeaderTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 1406, 726, defect 663, defect 666, defect 748, SMRO_WOE_309, Defect 1486,defect 1392, Defect 945';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1100
  Height = 630
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
  object _OBJ3: TsfDBEdit
    Left = 167
    Top = 405
    Width = 145
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 47
    Text = ''''
    Caption = ''Planned for Work Location''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ5: TsfDBEdit
    Left = 3
    Top = 293
    Width = 90
    Height = 21
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 53
    Text = ''''
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
    Left = 408
    Top = 293
    Width = 90
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 54
    Text = ''''
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
    Left = 7
    Top = 153
    Width = 130
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 14
    Text = ''''
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
    Left = 979
    Top = 347
    Width = 115
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 57
    Text = ''''
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
    Left = 989
    Top = 348
    Width = 100
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 58
    Text = ''''
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
    Left = 839
    Top = 381
    Width = 111
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 62
    Text = ''''
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
    Left = 819
    Top = 320
    Width = 90
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 56
    Text = ''''
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
    Left = 198
    Top = 80
    Width = 200
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 2
    Text = ''''
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
    Left = 809
    Top = 383
    Width = 90
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 55
    Text = ''''
    Caption = ''Order UOM''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ15: TsfDBEdit
    Left = 318
    Top = 404
    Width = 100
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 48
    Text = ''''
    Caption = ''End Unit Model''
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
    Top = 148
    Width = 4
    Height = 13
    Alignment = taRightJustify
    Caption = ''.''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clSilver
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ18: TsfUDVNavigator
    Left = 4
    Top = 5
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
    Left = 918
    Top = 222
    Width = 90
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 49
    Text = ''''
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
    Left = 968
    Top = 153
    Width = 119
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 60
    Text = ''''
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
    Left = 6
    Top = 80
    Width = 185
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 1
    Text = ''''
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
    Left = 970
    Top = 392
    Width = 154
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 59
    Text = ''''
    Caption = ''Track Unit Serial No?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ23: TsfDBEdit
    Left = 959
    Top = 392
    Width = 150
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 61
    Text = ''''
    Caption = ''Track Unit Lot No?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ24: TsfDBEdit
    Left = 144
    Top = 153
    Width = 130
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 15
    Text = ''''
    Caption = ''Scheduled Start Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ25: TsfDBEdit
    Left = 281
    Top = 153
    Width = 130
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 16
    Text = ''''
    Caption = ''Scheduled End Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ26: TsfDBEdit
    Left = 692
    Top = 153
    Width = 130
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 19
    Text = ''''
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
    Left = 829
    Top = 153
    Width = 130
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 20
    Text = ''''
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
    Left = 418
    Top = 153
    Width = 130
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 17
    Text = ''''
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
    Left = 555
    Top = 153
    Width = 130
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 18
    Text = ''''
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
    Left = 6
    Top = 258
    Width = 154
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 35
    Text = ''''
    Caption = ''Item No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ31: TsfDBEdit
    Left = 168
    Top = 456
    Width = 72
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 51
    Text = ''''
    Caption = ''Item Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ32: TsfDBEdit
    Left = 640
    Top = 364
    Width = 47
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 45
    Text = ''''
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
    Left = 584
    Top = 365
    Width = 51
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 44
    Text = ''''
    Caption = ''Plan Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ34: TsfDBEdit
    Left = 983
    Top = 296
    Width = 150
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 50
    Text = ''''
    Caption = ''Project''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ36: TsfDBEdit
    Left = 398
    Top = 321
    Width = 100
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 52
    Text = ''''
    Caption = ''End Unit Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ37: TsfDBEdit
    Left = 6
    Top = 407
    Width = 154
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 46
    Text = ''''
    Caption = ''Engine Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ38: TsfDBEdit
    Left = 391
    Top = 259
    Width = 100
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 38
    Text = ''''
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
    Left = 301
    Top = 294
    Width = 97
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 63
    Text = ''''
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
    Left = 196
    Top = 294
    Width = 96
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 64
    Text = ''''
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
    Left = 101
    Top = 294
    Width = 88
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 65
    Text = ''''
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
    Left = 7
    Top = 569
    Width = 118
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 66
    Text = ''''
    Caption = ''Plan VCH1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ44: TsfDBEdit
    Left = 688
    Top = 569
    Width = 150
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 67
    Text = ''''
    Caption = ''Plan VCH7''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ45: TsfDBEdit
    Left = 132
    Top = 569
    Width = 136
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 68
    Text = ''''
    Caption = ''Plan VCH2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ46: TsfDBEdit
    Left = 275
    Top = 569
    Width = 114
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 69
    Text = ''''
    Caption = ''Plan VCH3''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ47: TsfDBEdit
    Left = 396
    Top = 569
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 70
    Text = ''''
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
    Left = 483
    Top = 569
    Width = 92
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 71
    Text = ''''
    Caption = ''Plan VCH5''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ49: TsfDBEdit
    Left = 582
    Top = 569
    Width = 99
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 72
    Text = ''''
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
    Left = 845
    Top = 569
    Width = 140
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 73
    Text = ''''
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
    Left = 838
    Top = 603
    Width = 71
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 74
    Text = ''''
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
    Left = 916
    Top = 603
    Width = 68
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 75
    Text = ''''
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
    Left = 517
    Top = 603
    Width = 70
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 76
    Text = ''''
    Caption = ''Order Num1''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ55: TsfDBEdit
    Left = 594
    Top = 603
    Width = 83
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 77
    Text = ''''
    Caption = ''Order Num2''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ61: TsfDBEdit
    Left = 684
    Top = 603
    Width = 70
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 78
    Text = ''''
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
    Left = 761
    Top = 603
    Width = 70
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 79
    Text = ''''
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
    Left = 6
    Top = 368
    Width = 150
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height =';
p2 clob :='-11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 41
    Text = ''''
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
    Left = 213
    Top = 4
    Width = 125
    Height = 20
    Hint = ''Additional Order Info''
    Caption = ''Additional Order Info''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 81
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Additional Order Info,Priv=,Action=SideNote,SqlID=OrderInfo'' +
        ''Sel,Params(ORDER_ID =:ORDER_ID)'')
  end
  object LTA_SEND_FLAG: TsfDBEdit
    Left = 976
    Top = 390
    Width = 115
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 80
    Text = ''''
    Caption = ''Labor Send Flag''
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
    TabOrder = 82
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Update Build Part,''#39''Priv={@ToolState = ''#39#39''EditModes.Edit_PL''#39#39 +
        '' AND (ALTERATION_LEVEL=''#39#39''ORDER''#39#39'' OR ALTERATION_LEVEL=''#39#39''DISPOSITI'' +
        ''ON''#39#39''  OR ALTERATION_LEVEL= ''#39#39''SUPERCEDE''#39#39'' )}''#39'',Visible=''#39''{ORDER_TYP'' +
        ''E <> ''#39#39''REPAIR''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,'' +
        ''UDVType=Insert,UDVID=MFI_C547A28CDEAC4CAC9C68D824755C3D79'')
  end
  object PLAN_ITEM_TYPE: TsfDBEdit
    Left = 165
    Top = 258
    Width = 108
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 36
    Text = ''''
    Caption = ''Item Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object Condition: TsfDBEdit
    Left = 964
    Top = 387
    Width = 145
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 83
    Text = ''''
    Caption = ''Item Condition at Completion''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UID_ITEM_FLAG: TsfDBEdit
    Left = 7
    Top = 530
    Width = 80
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 84
    Text = ''''
    Caption = ''Item UID Flag''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object UID_ENTRY_NAME: TsfDBEdit
    Left = 94
    Top = 530
    Width = 124
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 85
    Text = ''''
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
    Left = 225
    Top = 530
    Width = 172
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 86
    Text = ''''
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
    Left = 404
    Top = 530
    Width = 135
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 87
    Text = ''''
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
    Left = 546
    Top = 530
    Width = 155
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 88
    Text = ''''
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
    Left = 708
    Top = 530
    Width = 100
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 89
    Text = ''''
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
    Left = 875
    Top = 259
    Width = 100
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 39
    Text = ''''
    Caption = ''MBOM No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ66: TsfDBEdit
    Left = 278
    Top = 259
    Width = 108
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 37
    Text = ''''
    Caption = ''Item Subtype''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ67: TsfDBEdit
    Left = 171
    Top = 368
    Width = 150
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 42
    Text = ''''
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
    Left = 290
    Top = 185
    Width = 90
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 21
    Text = ''''
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
    Left = 200
    Top = 319
    Width = 96
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ParentShowHint = False
    ShowHint = True
    TabOrder = 90
    Text = ''''
    Caption = ''WBS Project''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object ContFlowFlag: TsfDBEdit
    Left = 301
    Top = 320
    Width = 90
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 91
    Text = ''''
    Caption = ''Operation Overlap?''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object aliasPart: TsfDBEdit
    Left = 71
    Top = 328
    Width = 119
    Height = 16
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 22
    Text = ''''
    Caption = ''Starting Part No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object aliasRev: TsfDBEdit
    Left = 725
    Top = 293
    Width = 111
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 23
    Text = ''''
    Caption = ''Starting Part Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object INCR_ORDER_QTY: TsfCommandButton
    Left = 351
    Top = 4
    Width = 125
    Height = 20
    Hint = ''Increase Order Qty''
    Caption = ''Increase Order Qty''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 92
    OtherCommands.Strings = (
      
        ''Desc=Increase Order Qty,Priv=''#39''{ORDER_ID <> ''#39#39#39#39''}''#39'',Visible=''#39''{ORDE'' +
        ''R_TYPE <> ''#39#39''REPAIR''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action'' +
        ''=UDV,UDVType=Update,UDVID=MFI_E7BB575ED1AD4ED5BB35F93C8D422B9F'')
  end
  object _OBJ69: TsfDBEdit
    Left = 328
    Top = 367
    Width = 250
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 43
    Text = ''''
    Caption = ''Plan Title''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ70: TsfBevel
    Left = 12
    Top = 345
    Width = 900
    Height = 2
    Shape = bsFrame
    HighColor = ''clBtnShadow''
    LowColor = ''$997050''
    LineWidth = 2
  end
  object _OBJ71: TsfBevel
    Left = 12
    Top = 58
    Width = 900
    Height = 2
    Shape = bsFrame
    HighColor = ''clBtnShadow''
    LowColor = ''$997050''
    LineWidth = 1
  end
  object _OBJ72: TsfDBEdit
    Left = 844
    Top = 291
    Width = 115
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 93
    Text = ''''
    Caption = ''Planning Group''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ73: TsfDBEdit
    Left = 977
    Top = 340
    Width = 154
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 94
    Text = ''''
    Caption = ''Planned Min Order Qty''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ74: TsfDBEdit
    Left = 658
    Top = 320
    Width = 150
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 95
    Text = ''''
    Caption = ''Planned Max Order Qty''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ75: TsfDBEdit
    Left = 508
    Top = 321
    Width = 145
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 96
    Text = ''''
    Caption = ''Mfg Index No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ76: TsfDBEdit
    Left = 477
    Top = 456
    Width = 115
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 97
    Text = ''''
    Caption = ''Item UOM''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ77: TsfDBEdit
    Left = 7
    Top = 493
    Width = 154
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 98
    Text = ''''
    Caption = ''Eng Item No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ78: TsfDBEdit
    Left = 168
    Top = 493
    Width = 150
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 99
    Text = ''''
    Caption = ''Eng Item Rev''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ79: TsfDBEdit
    Left = 325
    Top = 493
    Width = 145
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 100
    Text = ''''
    Caption = ''Eng Group''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ80: TsfStdMarkupImage
    Left = 12
    Top = 38
    Width = 200
    Height = 20
    Hint = ''Work Order Information''
    Picture.Data = {07544269746D617000000000}
    StdMarkup = ''LabelWorkOrderInformation''
  end
  object _OBJ81: TsfStdMarkupImage
    Left = 210
    Top = 326
    Width = 200
    Height = 20
    Hint = ''Work Plan Information''
    Picture.Data = {07544269746D617000000000}
    StdMarkup = ''LabelWorkPlanInformation''
  end
  object declaredLang: TsfDBEdit
    Left = 147
    Top = 222
    Width = 109
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 3
    Text = ''''
    Caption = ''Declared Language''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ83: TsfDBEdit
    Left = 781
    Top = 372
    Width = 150
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 101
    Text = ''''
    Caption = ''Configuration ''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object locationId: TsfDBEdit
    Left = 409
    Top = 185
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 24
    Text = ''''
    Caption = ''Location Id''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ84: TsfDBEdit
    Left = 6
    Top = 114
    Width = 125
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 7
    Text = ''''
    Caption = ''Commodity Jurisdiction''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ85: TsfDBEdit
    Left = 138
    Top = 114
    Width = 125
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 8
    Text = ''''
    Caption = ''Commodity Classification''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ86: TsfDBEdit
    Left = 405
    Top = 80
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    Text = ''''
    Caption = ''Order Status''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ87: TsfDBEdit
    Left = 533
    Top = 80
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 5
    Text = ''''
    Caption = ''Order Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ88: TsfDBEdit
    Left = 788
    Top = 80
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 6
    Text = ''''
    Caption = ''Sales Order''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ89: TsfDBEdit
    Left = 398
    Top = 114
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 9
    Text = ''''
    Caption = ''Customer ID''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ90: TsfDBEdit
    Left = 526
    Top = 114
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 10
    Text = ''''
    Caption = ''Customer Desc''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ91: TsfDBEdit
    Left = 654
    Top = 114
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 11
    Text = ''''
    Caption = ''Engine Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ92: TsfDBEdit
    Left = 782
    Top = 114
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 12
    Text = ''''
    Caption = ''Engine Model''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ93: TsfDBEdit
    Left = 559
    Top = 185
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 25
    Text = ''''
    Caption = ''Superior Network''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ94: TsfDBEdit
    Left = 702
    Top = 185
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 26
    Text = ''''
    Caption = ''Sub Network''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ95: TsfDBEdit
    Left = 832
    Top = 185
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 27
    Text = ''''
    Caption = ''Workscope''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ96: TsfDBEdit
    Left = 968
    Top = 80
    Width = 121
    Height = 19
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 13
    Text = ''''
    Caption = ''Parent Order No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ97: TsfDBEdit
    Left = 6
    Top = 222
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 28
    Text = ''''
    Caption = ''Engine Serial No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ98: TsfDBEdit
    Left = 270
    Top = 114
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 29
    Text = ''''
    Caption = ''SME''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ99: TsfDBEdit
    ';
p3 clob :='Left = 271
    Top = 222
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 30
    Text = ''''
    Caption = ''Order Created By''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ100: TsfDBEdit
    Left = 402
    Top = 222
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 31
    Text = ''''
    Caption = ''Order Create Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ101: TsfDBEdit
    Left = 530
    Top = 222
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 32
    Text = ''''
    Caption = ''Order Updated By''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    MoreInfoUdvId = ''MFI_2869''
    Hidden = False
  end
  object _OBJ102: TsfDBEdit
    Left = 661
    Top = 222
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 33
    Text = ''''
    Caption = ''Order Update Date''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ103: TsfDBEdit
    Left = 791
    Top = 222
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Solumina Ansi Y''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 34
    Text = ''''
    Caption = ''Reason For Change''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Solumina Ansi Y''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ104: TsfDBEdit
    Left = 496
    Top = 259
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 40
    Text = ''''
    Caption = ''Alt No''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ105: TsfDBEdit
    Left = 748
    Top = 259
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 102
    Text = ''''
    Caption = ''Module Code''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ106: TsfDBEdit
    Left = 622
    Top = 259
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 103
    Text = ''''
    Caption = ''Outgoing Part Number''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ107: TsfDBEdit
    Left = 660
    Top = 80
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 104
    Text = ''''
    Caption = ''Sub-Order Type''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object US_Jurisdiction: TsfDBEdit
    Left = 8
    Top = 185
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 105
    Text = ''''
    Caption = ''US Jurisdiction''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object US_Classification: TsfDBEdit
    Left = 149
    Top = 185
    Width = 121
    Height = 20
    EmptyTextStyle = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 106
    Text = ''''
    Caption = ''US Classification''
    CaptionPos = cpAbove
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object PlanPartInfoSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_SelectPlanItemInfo''
    LinkedControls.Strings = (
      ''_OBJ69~PLAN_TITLE''
      ''_OBJ73~PLND_MIN_ORDER_QTY''
      ''_OBJ74~PLND_MAX_ORDER_QTY'')
    ParamsSQLSourceName = ''OrderHeaderTabSel''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''PLAN_PART_NO~0~PLAN_PART_NO~N~N~False~False~False~N~~~~~Default~'' +
        ''N~~''
      
        ''PLAN_PART_CHG~0~PLAN_PART_CHG~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''PLAN_ITEM_TYPE~0~PLAN_ITEM_TYPE~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~''
      
        ''PLAN_ITEM_SUBTYPE~0~PLAN_ITEM_SUBTYPE~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~''
      
        ''PLND_MAX_ORDER_QTY~0~PLND_MAX_ORDER_QTY~N~N~True~False~False~N~~'' +
        ''~~~Default~N~~''
      
        ''PLND_MIN_ORDER_QTY~0~PLND_MIN_ORDER_QTY~N~N~True~False~False~N~~'' +
        ''~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''PART_NO~Edit''
      ''PLAN_PART_CHG~Edit''
      ''PLAN_ITEM_TYPE~Edit'')
    TestParamValues.Strings = (
      ''PLAN_ID=PWUST_691237B1B813144BAFA0560338671C3''
      ''PLAN_VERSION=''
      ''PLAN_REVISION=''
      ''PLAN_ALTERATION=0'')
    DataDefinitions.Strings = (
      ''PLAN_PART_NO''
      ''PLAN_PART_CHG''
      ''PLAN_ITEM_TYPE''
      ''PLAN_ITEM_SUBTYPE'')
    ConsolidatedQuery = False
  end
  object OrderHeaderTabSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_OrderHeaderTabSel''
    LinkedControls.Strings = (
      ''_OBJ11~CONTRACT_NO''
      ''_OBJ13~PLAN_TITLE''
      ''_OBJ15~MODEL''
      ''_OBJ18~''
      ''_OBJ33~PLAN_REVISION''
      ''_OBJ34~PROJECT''
      ''_OBJ36~UNIT_TYPE''
      ''_OBJ37~PROGRAM''
      ''_OBJ38~MFG_BOM_CHG''
      ''_OBJ21~ORDER_NO''
      ''_OBJ8~SCHED_PRIORITY''
      ''_OBJ43~UCF_PLAN_VCH1''
      ''_OBJ45~UCF_PLAN_VCH2''
      ''_OBJ46~UCF_PLAN_VCH3''
      ''_OBJ47~UCF_PLAN_VCH4''
      ''_OBJ48~UCF_PLAN_VCH5''
      ''_OBJ49~UCF_PLAN_VCH6''
      ''_OBJ44~UCF_PLAN_VCH7''
      ''_OBJ50~UCF_PLAN_VCH8''
      ''_OBJ51~UCF_PLAN_FLAG1''
      ''_OBJ52~UCF_PLAN_FLAG2''
      ''_OBJ53~UCF_ORDER_NUM1''
      ''_OBJ55~UCF_ORDER_NUM2''
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
      ''_OBJ64~BOM_NO''
      ''_OBJ67~PLAN_NO''
      ''DispSeq~DISPLAY_SEQUENCE''
      ''WBS_PROJECT~ACCOUNT_LABOR''
      ''aliasPart~ALIAS_PART_NO''
      ''aliasRev~ALIAS_PART_CHG''
      ''INCR_ORDER_QTY~''
      ''_OBJ72~PLG_GROUP''
      ''_OBJ75~MFG_INDEX_NO''
      ''_OBJ77~ENG_PART_NO''
      ''_OBJ78~ENG_PART_CHG''
      ''_OBJ79~ENG_GROUP''
      ''_OBJ32~PLAN_VERSION''
      ''declaredLang~LANGUAGE_NAME''
      ''_OBJ83~CONFIG_NAME''
      ''locationId~LOCATION_ID''
      ''_OBJ76~STOCK_UOM''
      ''_OBJ14~ORDER_UOM''
      ''_OBJ84~COMMODITY_JURISDICTION''
      ''_OBJ85~COMMODITY_CLASSIFICATION''
      ''_OBJ30~PART_NO''
      ''_OBJ31~PART_CHG''
      ''PLAN_ITEM_TYPE~ITEM_TYPE''
      ''_OBJ66~ITEM_SUBTYPE''
      ''ContFlowFlag~OPERATION_OVERLAP_FLAG''
      ''_OBJ19~UNIT_NO''
      ''_OBJ86~ORDER_STATUS''
      ''_OBJ87~ORDER_TYPE''
      ''_OBJ91~PROGRAM''
      ''_OBJ98~UCF_ORDER_FLAG1''
      ''_OBJ99~UCF_ORDER_VCH1''
      ''_OBJ101~UPDT_USERID''
      ''_OBJ102~TIME_STAMP''
      ''_OBJ103~UCF_ORDER_VCH4000_1''
      ''_OBJ104~ALT_NO''
      ''_OBJ100~UCF_ORDER_VCH7''
      ''_OBJ105~UCF_PLAN_VCH3''
      ''_OBJ88~SALES_ORDER''
      ''_OBJ92~ENGINE_MODEL''
      ''_OBJ93~SUPERIORNET''
      ''_OBJ94~SUBNET''
      ''_OBJ95~WORK_SCOPE''
      ''_OBJ97~SAP_SERIAL_NO''
      ''_OBJ89~CUSTOMER''
      ''_OBJ90~CUSTOMER_DESC''
      ''_OBJ96~PARENT_ORDER_NO''
      ''_OBJ106~OUTGOING_PART_NO''
      ''_OBJ107~PW_SUB_ORDER_TYPE''
      ''US_Jurisdiction~UCF_PLAN_VCH10''
      ''US_Classification~UCF_PLAN_VCH11'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_ID~40~ORDER_ID~N~N~N~N~N~N~~~~~Default~N~~~''
      ''ORDER_NO~40~ORDER_NO~N~N~False~N~N~N~~~~~Default~N~~~''
      ''PLAN_ID~40~PLAN_ID~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PLAN_VERSION~10~PLAN_VERSION~N~N~True~N~N~N~~~~~Default~N~~~''
      ''PLAN_REVISION~10~PLAN_REVISION~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''PLAN_ALTERATIONS~10~PLAN_ALTERATIONS~N~N~N~N~N~N~~~~~Default~N~~'' +
        ''~''
      ''PLAN_UPDT_NO~10~PLAN_UPDT_NO~N~N~N~N~N~N~~~~~Default~N~~~''
      ''UPDT_USERID~30~UPDT_USERID~N~N~N~N~N~N~MFI_2869~~~~Default~N~~~''
      ''TIME_STAMP~18~TIME_STAMP~N~N~N~N~N~N~~~~~Default~N~~~''
      ''ORDER_STATUS~20~ORDER_STATUS~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''ORDER_HOLD_STATUS~20~ORDER_HOLD_STATUS~N~N~N~N~N~N~~~~~Default~N'' +
        ''~~~''
      ''ASGND_WORK_LOC~30~ASGND_WORK_LOC~N~N~N~N~N~N~~~~~Default~N~~~''
      ''ORDER_CUST_ID~40~ORDER_CUST_ID~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PARENT_ORDER_ID~40~PARENT_ORDER_ID~N~N~N~N~N~N~~~~~Default~N~~~''
      ''SCHED_PRIORITY~4~SCHED_PRIORITY~N~N~N~N~N~N~~~~~Default~N~~~''
      ''INITIAL_STORES~20~INITIAL_STORES~N~N~True~N~N~N~~~~~Default~N~~~''
      ''FINAL_STORES~20~FINAL_STORES~N~N~True~N~N~N~~~~~Default~N~~~''
      ''CONTRACT_NO~30~CONTRACT_NO~N~N~True~N~N~N~~~~~Default~N~~~''
      ''ORIG_ORDER_ID~40~ORIG_ORDER_ID~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''SUPERCEDED_ORDER_ID~40~SUPERCEDED_ORDER_ID~N~N~N~N~N~N~~~~~Defau'' +
        ''lt~N~~~''
      ''SPLIT_FLAG~1~SPLIT_FLAG~N~N~True~N~N~N~~~~~Default~N~~~''
      
        ''SCHED_START_DATE~18~SCHED_START_DATE~N~N~N~N~N~N~~~~~Default~N~~'' +
        ''~''
      ''SCHED_END_DATE~18~SCHED_END_DATE~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''REVISED_START_DATE~18~REVISED_START_DATE~N~N~N~N~N~N~~~~~Default'' +
        ''~N~~~''
      
        ''REVISED_END_DATE~18~REVISED_END_DATE~N~N~N~N~N~N~~~~~Default~N~~'' +
        ''~''
      
        ''ACTUAL_START_DATE~18~ACTUAL_START_DATE~N~N~N~N~N~N~~~~~Default~N'' +
        ''~~~''
      ''ACTUAL_END_DATE~18~ACTUAL_END_DATE~N~N~N~N~N~N~~~~~Default~N~~~''
      ''UNIT_NO~30~UNIT_NO~N~N~True~N~N~N~~~~~Default~N~~~''
      
        ''CUSTOMER_ORDER_NO~30~CUSTOMER_ORDER_NO~N~N~True~N~N~N~~~~~Defaul'' +
        ''t~N~~~''
      ''PLAN_TYPE~20~Plan Type~N~N~N~N~N~N~~~~~Default~N~~~''
      ''ALTER_TYPE~20~ALTER_TYPE~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''SUPERCEDES_ORDER~30~SUPERCEDES_ORDER~N~N~N~N~N~N~~~~~Default~N~~'' +
        ''~''
      
        ''STATUS_CHG_NOTES~255~STATUS_CHG_NOTES~N~N~N~N~N~N~~~~~Default~N~'' +
        ''~~''
      ''SERIAL_FLAG~1~SERIAL_FLAG~N~N~True~N~N~N~~~~~Default~N~~~''
      ''LOT_FLAG~1~LOT_FLAG~N~N~True~N~N~N~~~~~Default~N~~~''
      ''ITEM_ID~40~ITEM_ID~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PART_NO~50~PART_NO~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PART_CHG~4~PART_CHG~N~N~True~N~N~N~~~~~Default~N~~~''
      ''ITEM_TYPE~8~ITEM_TYPE~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''ITEM_SUBTYPE~12~ITEM_SUBTYPE~N~N~False~False~False~N~~~~~Default'' +
        ''~N~~~''
      ''PROGRAM~30~PROGRAM~N~N~N~N~N~N~~~~~Default~N~~~''
      
        ''MFG_BOM_CHG~4~MFG_BOM_CHG~N~N~MRO_FLAG = ''#39''Y''#39'' AND ORDER_PURPOSE ='' +
        '' ''#39''Part''#39''~N~N~N~~~~~Default~N~~~''
      ''MODEL~20~MODEL~N~N~N~N~N~N~~~~~Default~N~~~''
      ''PLAN_TITLE~80~PLAN_TITLE~N~N~N~N~N~N~~~~~Default~N~~~''
      ''ORDER_UOM~10~ORDER_UOM~N~N~True~N~N~N~~~~~Default~N~~~''
      ''UNIT_TYPE~30~UNIT_TYPE~N~N~True~N~N~N~~~~~Default~N~~~''
      ''PROJECT~30~PROJECT~N~N~True~N~N~N~~~~~Default~N~~~''
      ''ALT_ID~40~ALT_ID~N~N~N~N~N~N~~~~~Default~N~~~''
      ''ALT_COUNT~10~ALT_COUNT~N~N~N~N~N~N~~~~~Default~N~~~''
      ''ALT_STATUS~20~ALT_STATUS~N~N~N~N~N~N~~~~~Default~N~~~''
      ''ENG_PART_NO~50~ENG_PART_NO~N~N~True~N~N~N~~~~~Default~N~~~''
      ''ENG_PART_CHG~4~ENG_PART_CHG~N~N~True~N~N~N~~~~~Default~N~~~''
      ''ENG_GROUP~20~ENG_GROUP~N~N~True~N~N~N~~~~~Default~N~~~''
      ''MFG_INDEX_NO~10~MFG_INDEX_NO~N~N~True~N~N~N~~~~~Default~N~~~''
      ''PLG_GROUP~20~PLG_GROUP~N~N~True~N~N~N~~~~~Default~N~~~''
      ''UCF_PLAN_VCH1~4~Classification;Code~N~N~Y~N~N~N~~~~~Default~N~~~''
      ''UCF_PLAN_VCH2~30~Mission~N~N~Y~N~N~N~~~~~Default~N~~~''
      ''UCF_PLAN_FLAG1~1~UCF_PLAN_FLAG1~N~N~Y~N~N~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_VCH1~50~UCF_ORDER_VCH1~N~N~False~N~N~N~~~~~Default~N~~'' +
        ''~''
      
        ''UCF_ORDER_VCH2~50~UCF_ORDER_VCH2~N~N~False~N~N~N~~~~~Default~N~~'' +
        ''~''
      ''UCF_ORDER_NUM1~10~UCF_ORDER_NUM1~N~N~Y~N~N~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_FLAG1~1~UCF_ORDER_FLAG1~N~N~False~N~N~N~~~~~Default~N~'' +
        ''~~''
      ''UCF_PLAN_VCH3~10~ADC''#39''s Badge No~N~N~True~N~N~N~~~~~Default~N~~~''
      ''UCF_PLAN_VCH4~15~Use Level~N~N~True~N~N~N~~~~~Default~N~~~''
      ''UCF_PLAN_VCH5~30~ADC Title Desc~N~N~True~N~N~N~~~~~Default~N~~~''
      ''UCF_PLAN_VCH6~50~Source;Document~N~N~Y~N~N~N~~~~~Default~N~~~''
      
        ''UCF_PLAN_VCH7~50~Source Doc Issue Date~N~N~Y~N~N~N~~~~~Default~N'' +
        ''~~~''
      ''UCF_PLAN_VCH8~50~UCF_PLAN_VCH8~N~N~Y~N~N~N~~~~~Default~N~~~''
      ''UCF_PLAN_FLAG2~1~UCF_PLAN_FLAG2~N~N~Y~N~N~N~~~~~Default~N~~~''
      ''UCF_ORDER_VCH3~50~UCF_ORDER_VCH3~N~N~True~N~N~N~~~~~Default~N~~~''
      
        ''UCF_ORDER_VCH4~50~UCF_ORDER_VCH4~N~N~False~N~N~N~~~~~Default~N~~'' +
        ''~''
      
        ''UCF_ORDER_VCH5~50~UCF_ORDER_VCH5~N~N~False~N~N~N~~~~~Default~N~~'' +
        ''~''
      ''UCF_ORDER_NUM2~10~UCF_ORDER_NUM2~N~N~Y~N~N~N~~~~~Default~N~~~''
      
        ''NEEDS_REVIEW_FLAG~1~NEEDS_REVIEW_FLAG~N~N~N~N~N~N~~~~~Default~N~'' +
        ''~~''
      
        ''STATUS_CHG_REASON~10~STATUS_CHG_REASON~N~N~N~N~N~N~~~~~Default~N'' +
        ''~~~''
      ''ORDER_QTY~10~ORDER_QTY~N~N~False~N~N~N~~~~~Default~N~~~''
      
        ''ORDER_SCRAP_QTY~10~ORDER_SCRAP_QTY~N~N~False~N~N~N~~~~~Default~N'' +
        ''~~~''
      
        ''ORDER_COMPLETE_QTY~10~ORDER_COMPLETE_QTY~N~N~False~N~N~N~~~~~Def'' +
        ''ault~N~~~''
      
        ''ORDER_STOP_QTY~10~ORDER_STOP_QTY~N~N~False~N~N~N~~~~~Default~N~~'' +
        ''~''
      
        ''ORDER_INPROCESS_QTY~10~ORDER_INPROCESS_QTY~N~N~False~N~N~N~~~~~D'' +
        ''efault~N~~~''
      ''UCF_PLAN_NUM1~10~Sigma;Category~N~N~Y~N~N~N~~~~~Default~N~~~''
      ''UCF_PLAN_NUM2~10~UCF_PLAN_NUM2~N~N~Y~N~N~N~~~~~Default~N~~~''
      ''LTA_SEND_FLAG~1~Send Labor Flag~N~N~True~N~N~N~~~~~Default~N~~~''
      
        ''BOM_NO~40~BOM_NO~N~N~INSTRUCTIONS_TYPE = ''#39''MRO-Part''#39''~False~False~'' +
        ''N~~~~~Default~N~~~''
      ''PLAN_NO~40~PLAN_NO~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''DISPLAY_SEQUENCE~0~DISPLAY_SEQUENCE~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''ALIAS_PART_CHG~18~Starting Part~N~N~INSTRUCTIONS_TYPE<>''#39''MRO-Upgr'' +
        ''ade''#39''~False~False~N~~~~~Default~N~~~''
      
        ''ALIAS_PART_NO~18~Staring Rev~N~N~INSTRUCTIONS_TYPE<>''#39''MRO-Upgrade'' +
        #39''~False~False~N~~~~~Default~N~~~''
      
        ''EXPLICIT_BOM_LINK_FLAG~1~EXPLICIT_BOM_LINK_FLAG~N~N~INSTRUCTIONS'' +
        ''_TYPE = ''#39''MRO-Part''#39''~False~False~N~~~~~Default~N~~~''
      
        ''LOCATION_ID~0~LOCATION_ID~N~N~False~False~True~N~~~~~Default~N~~'' +
        ''~''
      
        ''COMMODITY_JURISDICTION~40~Commodity Jurisdiction~N~N~False~False'' +
        ''~False~N~~~~~Default~N~~~''
      
        ''COMMODITY_CLASSIFICATION~40~Commodity Classification~N~N~False~F'' +
        ''alse~False~N~~~~~Default~N~~~''
      
        ''CONFIG_NAME~0~CONFIG_NAME~N~N~True~False~False~N~~~~~Default~N~~'' +
        ''~''
      
        ''ACCOUNT_LABOR~0~ACCOUNT_LABOR~N~N~True~False~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''OPERATION_OVERLAP_FLAG~0~OPERATION_OVERLAP_FLAG~N~N~True~False~F'' +
        ''alse~N~~~~~Default~N~~~''
      ''CONDITION~0~CONDITION~N~N~True~False~False~N~~~~~Default~N~~~''
      ''STOCK_UOM~0~STOCK_UOM~N~N~True~False~False~N~~~~~Default~N~~~''
      ''ALT_NO~0~ALT_NO~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''PARENT_ORDER_NO~0~PARENT_ORDER_NO~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~~~''
      
        ''OUTGOING_PART_NO~0~OUTGOING_PART_NO~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~''
      
        ''PW_SUB_ORDER_TYPE~10~Sub-Order Type~N~N~False~False~False~N~~~~~'' +
        ''Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''ACTION~Edit''
      ''PLAN_NO~Edit''
      ''PLAN_ALTERATIONS~Edit''
      ''PLAN_UPDT_NO~Edit''
      ''ORDER_HOLD_STATUS~Edit''
      ''ASGND_WORK_LOC~Edit''
      ''ORIG_ORDER_ID~Edit''
      ''SUPERCEDED_ORDER_ID~Edit''
      ''SCHED_END_DATE~Edit''
      ''REVISED_START_DATE~Edit''
      ''REVISED_END_DATE~Edit''
      ''ACTUAL_START_DATE~Edit''
      ''ACTUAL_END_DATE~Edit''
      ''PLAN_TYPE~Edit''
      ''ITEM_TYPE~Edit''
      ''UCF_PLAN_VCH2~Edit''
      ''UCF_PLAN_FLAG1~Edit''
      ''UCF_ORDER_NUM1~Edit''
      ''UCF_PLAN_FLAG2~Edit''
      ''NEEDS_REVIEW_FLAG~Edit''
      ''STATUS_CHG_REASON~Edit''
      ''SCHED_PRIORITY~Edit''
      ''UCF_ORDER_NUM2~Edit''
      ''DISPLAY_SEQUENCE~Edit''
      ''Declared_Language_Code~Edit''
      ''ORDER_CUST_ID~Edit''
      ''SPLIT_FLAG~Edit''
      ''PLAN_VERSION~Edit''
      ''FINAL_STORES~Edit''
      ''LOT_FLAG~Edit''
      ''SERIAL_FLAG~Edit''
      ''LTA_SEND_FLAG~Edit''
      ''ENG_GROUP~Edit''
      ''UCF_ORDER_VCH2~Edit''
      ''PARENT_ORDER_ID~Edit''
      ''UPDT_USERID~Edit''
      ''TIME_STAMP~Edit''
      ''UCF_ORDER_VCH4~Edit''
      ''ALIAS_PART_NO~Edit''
      ''UCF_PLAN_VCH6~Edit''
      ''UCF_ORDER_VCH3~Edit''
      ''ORDER_NO~Edit''
      ''CONFIG_NAME~Edit''
      ''ORDER_ID~Edit''
      ''ORDER_UOM~Edit'')
    PrintTitle = ''Header''
    TestParamValues.Strings = (
      ''ORDER_ID=PWUST_9DBE2A5CB34ABEFE76D642193CFC2623'')
    InputUDVId = ''MFI_1003329''
    OtherCommands.Strings = (
      
        ''Desc=Order History,Priv={},Visible={},TagValue=,FKey=,ParamsSrc='' +
        ''@Default,Action=Invoke,Tool=TabReport,ReportToolId=OrderHistory,'' +
        ''Params(ORDER_ID =:ORDER_ID)''
      
        ''Desc=Order Status Receive Logs,Priv={},Visible=''#39''{@Lic_ENABLE_XML'' +
        ''=''#39#39''YES''#39#39'' AND @ToolType=''#39#39''Instructions.MfgInstructions''#39#39''}''#39'',TagVal'' +
        ''ue=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=Tabular Report,R'' +
        ''eportToolId=MFI_INBOUNDWOSTATUSBISLOGS,Params(ORDER_ID = :ORDER_'' +
        ''ID)''
      
        ''Desc=Order Status Send Logs,Priv={},Visible=''#39''{@Lic_ENABLE_XML=''#39#39 +
        ''YES''#39#39'' AND @ToolType=''#39#39''Instructions.MfgInstructions''#39#39''}''#39'',TagValue='' +
        '',FKey=,ParamsSrc=@Default,Action=Invoke,Tool=Tabular Report,Repo'' +
        ''rtToolId=MFI_OUTBOUNDWOSTATUSBISLOGS,Params(ORDER_ID = :ORDER_ID'' +
        '')'')
    DataDefinitions.Strings = (
      ''ORDER_ID''
      ''ORDER_NO''
      ''PLAN_ID''
      ''PLAN_VERSION''
      ''PLAN_REVISION''
      ''PLAN_ALTERATIONS''
      ''PLAN_UPDT_NO''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''ORDER_STATUS''
      ''ORDER_HOLD_STATUS''
      ''ASGND_WORK_LOC''
      ''ORDER_CUST_ID''
      ''PARENT_ORDER_ID''
      ''SCHED_PRIORITY''
      ''INITIAL_STORES''
      ''FINAL_STORES''
      ''CONTRACT_NO''
      ''ORIG_ORDER_ID''
      ''SUPERCEDED_ORDER_ID''
      ''SPLIT_FLAG''
      ''SCHED_START_DATE''
      ''SCHED_END_DATE''
      ''REVISED_START_DATE''
      ''REVISED_END_DATE''
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
      ''ITEM_ID''
      ''PART_NO''
      ''PART_CHG''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''PROGRAM''
      ''MFG_BOM_CHG''
      ''MODEL''
      ''PLAN_TITLE''
      ''ORDER_UOM''
      ''UNIT_TYPE''
      ''PROJECT''
      ''ALT_ID''
      ''ALT_COUNT''
      ''ALT_STATUS''
      ''ENG_PART_NO''
      ''ENG_PART_CHG''
      ''ENG_GROUP''
      ''MFG_INDEX_NO''
      ''PLG_GROUP''
      ''UCF_PLAN_VCH1''
      ''UCF_PLAN_VCH2''
      ''UCF_PLAN_FLAG1''
      ''UCF_ORDER_VCH1''
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
      ''BOM_NO''
      ''PLAN_NO''
      ''DISPLAY_SEQUENCE''
      ''ALIAS_PART_CHG''
      ''ALIAS_PART_NO''
      ''EXPLICIT_BOM_LINK_FLAG''
      ''LOCATION_ID''
      ''COMMODITY_JURISDICTION''
      ''COMMODITY_CLASSIFICATION''
      ''CONFIG_NAME''
      ''ACCOUNT_LABOR''
      ''OPERATION_OVERLAP_FLAG''
      ''CONDITION''
      ''STOCK_UOM''
      ''ALT_NO''
      ''PARENT_ORDER_NO''
      ''OUTGOING_PART_NO''
      ''PW_SUB_ORDER_TYPE'')
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
    ParamsSQLSourceName = ''OrderHeaderTabSel,@ToolScope''
    SelectedFields.Strings = (
      
        ''UID_ENTRY_NAME~0~UID_ENTRY_NAME~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''UID_ITEM_FLAG~0~UID_ITEM_FLAG~N~N~True~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''ENTERPRISE_ID~0~ENTERPRISE_ID~N~N~True~False~False~N~~~~~Default'' +
        ''~N~~''
      
        ''DATA_QUALIFIER~0~DATA_QUALIFIER~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''CONSTRUCT_TYPE~0~CONSTRUCT_TYPE~N~N~True~False~False~N~~~~~Defau'' +
        ''lt~N~~''
      
        ''ISSUE_AGENCY_CODE~0~ISSUE_AGENCY_CODE~N~N~True~False~False~N~~~~'' +
        ''~Default~N~~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~~'')
    TestParamValues.Strings = (
      ''ORDER_ID=PWUST_9DBE2A5CB34ABEFE76D642193CFC2623'')
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
  object TOVCController
  end
end';
v_iclob clob;
begin
v_iclob := p1||p2||p3;
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

