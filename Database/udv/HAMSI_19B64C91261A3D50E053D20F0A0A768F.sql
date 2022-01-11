set define off
declare
v_folder_id varchar2(40) :='HAMSI_0910B86429E34BFEE053D20F0A0A3E96';
v_udv_id varchar2(85) :='HAMSI_19B64C91261A3D50E053D20F0A0A768F';
v_udv_tag varchar2(40) :='HAMSI_WID_PaarReqHdrPnl';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='Paar Request Hdr Panel';
v_state varchar2(10) :='';
v_load_ref varchar2(255) :='';
v_tool_version varchar2(40) :='2.2.18.0.20121003~2.2';
v_object_rev varchar2(22) :='';
v_owner_group varchar2(22) :='';
v_stype varchar2(32) :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 600
  Height = 320
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
  object headerNavigator: TsfUDVNavigator
    Left = 8
    Top = 10
    Width = 39
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Edit ''
      ''Refresh '')
    ShowHint = True
    TabOrder = 10
  end
  object requestId: TsfDBEdit
    Left = 148
    Top = 13
    Width = 275
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 0
    Caption = ''Request Id:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object createdDate: TsfDBEdit
    Left = 148
    Top = 269
    Width = 125
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 8
    Caption = ''Created Date:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planNo: TsfDBEdit
    Left = 148
    Top = 40
    Width = 130
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 1
    Caption = ''Order No:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planTitle: TsfDBEdit
    Left = 148
    Top = 66
    Width = 400
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 2
    Caption = ''Order Title:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object itemNo: TsfDBEdit
    Left = 148
    Top = 92
    Width = 121
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 3
    Caption = ''Item No:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object itemRev: TsfDBEdit
    Left = 307
    Top = 92
    Width = 56
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = [fsUnderline]
    ParentFont = True
    ShowHint = False
    TabOrder = 4
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = [fsUnderline]
    Hidden = False
  end
  object subType: TsfDBEdit
    Left = 427
    Top = 92
    Width = 121
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 5
    Caption = ''Subtype:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object itemTitle: TsfDBEdit
    Left = 148
    Top = 117
    Width = 400
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 6
    Caption = ''Item Title:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object requestReason: TsfDBMemo
    Left = 148
    Top = 173
    Width = 400
    Height = 91
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssBoth
    ShowHint = False
    TabOrder = 7
    MemoCaption = ''Request Reason:''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object slashLabel: TsfLabel
    Left = 285
    Top = 95
    Width = 4
    Height = 13
    Caption = ''/''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
  end
  object requestReplyDate: TsfDBEdit
    Left = 423
    Top = 269
    Width = 125
    Height = 19
    FocusColor = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 9
    Caption = ''Reply Required Date:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object work_loc: TsfDBEdit
    Left = 148
    Top = 146
    Width = 161
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 11
    Caption = ''Facility Id''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object work_loc_title: TsfDBEdit
    Left = 392
    Top = 146
    Width = 156
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 12
    Caption = ''Facility Desc''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object LatestRequestSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_WID_PAARREQHDRBYIDQRY''
    LinkedControls.Strings = (
      ''headerNavigator~''
      ''requestId~REQUEST_ID''
      ''createdDate~REQUEST_CREATION_DATE''
      ''itemNo~PART_NO''
      ''itemRev~PART_CHG''
      ''subType~ITEM_SUBTYPE''
      ''itemTitle~PART_TITLE''
      ''requestReason~REQUEST_REASON''
      ''requestReplyDate~REPLY_REQUIRED_DATE''
      ''planNo~ORDER_NO''
      ''planTitle~PLAN_TITLE''
      ''work_loc~ASGND_WORK_LOC''
      ''work_loc_title~LOC_TITLE'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''REQUEST_ID~0~REQUEST_ID~N~N~False~False~False~N~~~~~Default~N~''
      
        ''REQUEST_CREATION_DATE~0~REQUEST_CREATION_DATE~N~N~False~False~Fa'' +
        ''lse~N~~~~~Default~N~''
      
        ''REQUEST_REASON~0~REQUEST_REASON~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''REPLY_REQUIRED_DATE~0~REPLY_REQUIRED_DATE~N~N~False~False~False~'' +
        ''N~~~~~Default~N~''
      ''PART_TITLE~0~PART_TITLE~N~N~False~False~False~N~~~~~Default~N~''
      ''PART_NO~0~PART_NO~N~N~False~False~False~N~~~~~Default~N~''
      ''PART_CHG~0~PART_CHG~N~N~False~False~False~N~~~~~Default~N~''
      ''PLAN_TITLE~0~PLAN_TITLE~N~N~False~False~False~N~~~~~Default~N~''
      
        ''ITEM_SUBTYPE~0~ITEM_SUBTYPE~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''
      
        ''SECURITY_GROUP~0~SECURITY_GROUP~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      ''ORDER_ID~0~ORDER_ID~N~N~False~False~False~N~~~~~Default~N~''
      ''ORDER_NO~0~ORDER_NO~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''REQUEST_ID~Edit''
      ''REQUEST_CREATION_DATE~Edit''
      ''SECURITY_GROUP~Edit''
      ''ORDER_ID~Edit'')
    InputUDVId = ''HAMSI_19B64C9126243D50E053D20F0A0A768F''
    UpdateEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DataDefinitions.Strings = (
      ''REQUEST_ID''
      ''REQUEST_CREATION_DATE''
      ''REQUEST_REASON''
      ''REPLY_REQUIRED_DATE''
      ''PART_TITLE''
      ''PART_NO''
      ''PART_CHG''
      ''PLAN_TITLE''
      ''ITEM_SUBTYPE''
      ''SECURITY_GROUP'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''LatestRequestSel''
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

