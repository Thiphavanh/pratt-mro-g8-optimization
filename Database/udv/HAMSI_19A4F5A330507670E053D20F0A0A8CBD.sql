set define off
declare
v_folder_id varchar2(40) :='HAMSI_A36C8B4A07FC5D74E0400A0AD20F28E1';
v_udv_id varchar2(85) :='HAMSI_19A4F5A330507670E053D20F0A0A8CBD';
v_udv_tag varchar2(40) :='HAMSI_WID_OrderSecGrpReqHdrApprovalPnl';
v_udv_type varchar2(7) :='PANEL';
v_udv_desc varchar2(255) :='Paar Order Sec Grp Request Hdr For Approval Panel';
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
  Height = 400
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
    Width = 20
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh '')
    ShowHint = True
    TabOrder = 11
  end
  object requestId: TsfDBEdit
    Left = 114
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
    Left = 114
    Top = 346
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
    Caption = ''Created Date:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planNo: TsfDBEdit
    Left = 114
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
  object planVersion: TsfDBEdit
    Left = 329
    Top = 40
    Width = 60
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
    Caption = ''Order Status:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object planTitle: TsfDBEdit
    Left = 114
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
    TabOrder = 3
    Caption = ''Order Title:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object itemNo: TsfDBEdit
    Left = 114
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
    TabOrder = 4
    Caption = ''Item No:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object itemRev: TsfDBEdit
    Left = 273
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
    TabOrder = 5
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = [fsUnderline]
    Hidden = False
  end
  object subType: TsfDBEdit
    Left = 393
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
    TabOrder = 6
    Caption = ''Subtype:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object itemTitle: TsfDBEdit
    Left = 114
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
    TabOrder = 7
    Caption = ''Item Title:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object requestReason: TsfDBMemo
    Left = 114
    Top = 250
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
    TabOrder = 8
    MemoCaption = ''Request Reason:''
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object slashLabel: TsfLabel
    Left = 251
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
    Left = 389
    Top = 346
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
    TabOrder = 10
    Caption = ''Reply Required Date:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ1: TsfDBEdit
    Left = 114
    Top = 144
    Width = 121
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 12
    Caption = ''Schedule Start:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ2: TsfDBEdit
    Left = 393
    Top = 144
    Width = 121
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 13
    Caption = ''Schedule End:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ3: TsfDBEdit
    Left = 114
    Top = 170
    Width = 121
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 14
    Caption = ''Actual Start:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ4: TsfDBEdit
    Left = 393
    Top = 168
    Width = 121
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 15
    Caption = ''Actual End:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ5: TsfDBEdit
    Left = 114
    Top = 197
    Width = 121
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 16
    Caption = ''Bom No / Rev''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ6: TsfDBEdit
    Left = 273
    Top = 196
    Width = 56
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 17
    Caption = ''/    ''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ7: TsfDBEdit
    Left = 114
    Top = 223
    Width = 121
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 18
    Caption = ''Work Location:''
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''MS Sans Serif''
    CaptionFont.Style = []
    Hidden = False
  end
  object _OBJ8: TsfDBEdit
    Left = 265
    Top = 223
    Width = 249
    Height = 19
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = False
    TabOrder = 19
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clWindowText
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
    Hidden = False
  end
  object LatestOrdSecGrpRequestSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''HAMSI_WID_PaarReqHdrForApprovalByIdQry''
    LinkedControls.Strings = (
      ''headerNavigator~''
      ''requestId~REQUEST_ID''
      ''planNo~ORDER_NO''
      ''planVersion~ORDER_STATUS''
      ''planTitle~ORDER_TITLE''
      ''itemNo~ITEM_NO''
      ''itemRev~ITEM_REV''
      ''subType~ITEM_SUBTYPE''
      ''itemTitle~ITEM_TITLE''
      ''_OBJ1~SCHED_START_DATE''
      ''_OBJ2~sCHED_END_DATE''
      ''_OBJ3~ACTUAL_START_DATE''
      ''_OBJ4~aCTUAL_END_DATE''
      ''_OBJ5~BOM_NO''
      ''_OBJ6~BOM_REV''
      ''_OBJ7~FACILITY_ID''
      ''_OBJ8~fACILITY_DESC''
      ''requestReason~REQUEST_REASON''
      ''createdDate~REQUEST_CREATION_DATE''
      ''requestReplyDate~REPLY_REQUIRED_DATE'')
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
      
        ''ITEM_SUBTYPE~0~ITEM_SUBTYPE~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''
      
        ''ACTUAL_END_DATE~0~ACTUAL_END_DATE~N~N~False~False~False~N~~~~~De'' +
        ''fault~N~''
      
        ''ACTUAL_START_DATE~0~ACTUAL_START_DATE~N~N~False~False~False~N~~~'' +
        ''~~Default~N~''
      ''BOM_NO~0~BOM_NO~N~N~False~False~False~N~~~~~Default~N~''
      ''BOM_REV~0~BOM_REV~N~N~False~False~False~N~~~~~Default~N~''
      
        ''FACILITY_DESC~0~FACILITY_DESC~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~''
      ''FACILITY_ID~0~FACILITY_ID~N~N~False~False~False~N~~~~~Default~N~''
      ''ITEM_NO~0~ITEM_NO~N~N~False~False~False~N~~~~~Default~N~''
      ''ITEM_REV~0~ITEM_REV~N~N~False~False~False~N~~~~~Default~N~''
      ''ITEM_TITLE~0~ITEM_TITLE~N~N~False~False~False~N~~~~~Default~N~''
      ''ITEM_TYPE~0~ITEM_TYPE~N~N~False~False~False~N~~~~~Default~N~''
      ''LAST_ACTION~0~LAST_ACTION~N~N~False~False~False~N~~~~~Default~N~''
      ''ORDER_NO~0~ORDER_NO~N~N~False~False~False~N~~~~~Default~N~''
      
        ''ORDER_STATUS~0~ORDER_STATUS~N~N~False~False~False~N~~~~~Default~'' +
        ''N~''
      ''ORDER_TITLE~0~ORDER_TITLE~N~N~False~False~False~N~~~~~Default~N~''
      
        ''PRIOR_REQUEST_ID~0~PRIOR_REQUEST_ID~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      
        ''REQUEST_STATUS~0~REQUEST_STATUS~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''REQUEST_USERID~0~REQUEST_USERID~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''SCHED_END_DATE~0~SCHED_END_DATE~N~N~False~False~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''SCHED_START_DATE~0~SCHED_START_DATE~N~N~False~False~False~N~~~~~'' +
        ''Default~N~''
      ''TIME_STAMP~0~TIME_STAMP~N~N~False~False~False~N~~~~~Default~N~''
      ''UPDT_USERID~0~UPDT_USERID~N~N~False~False~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''SECURITY_GROUP~Edit''
      ''REQUEST_CREATION_DATE~Edit''
      ''REQUEST_ID~Edit''
      ''LAST_ACTION~Edit'')
    UpdateEnabledExpr = ''REQUEST_STATUS=''POUND39_HOLDER''OPEN''POUND39_HOLDER
    DataDefinitions.Strings = (
      ''REQUEST_ID''
      ''REQUEST_CREATION_DATE''
      ''REQUEST_REASON''
      ''REPLY_REQUIRED_DATE''
      ''ITEM_SUBTYPE'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''LatestOrdSecGrpRequestSel''
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

