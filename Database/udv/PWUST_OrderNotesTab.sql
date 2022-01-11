
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_73F58D23B8A67AA2E05387971F0A225B.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_73F58D23B8A67AA2E05387971F0A225B';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderNotesTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Defect 943, Defect 973';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.3.1.11.20170423~2.3';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 795
  Height = 317
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
  object _OBJ1: TsfUDVNavigator
    Left = 4
    Top = 4
    Width = 172
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''First ''
      ''Last ''
      ''Previous ''
      ''Next ''
      ''Insert ''
      ''Delete ''
      ''Edit ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object OrderNoteGrid: TsfDBGrid
    Left = 4
    Top = 27
    Width = 529
    Height = 157
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
  object NoteText: TsfDBMemo
    Left = 535
    Top = 27
    Width = 250
    Height = 157
    Cursor = crIBeam
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlack
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ScrollBars = ssBoth
    ShowHint = True
    TabOrder = 2
    CaptionPos = cpLeft
    CaptionFont.Charset = DEFAULT_CHARSET
    CaptionFont.Color = clBlack
    CaptionFont.Height = -11
    CaptionFont.Name = ''Tahoma''
    CaptionFont.Style = []
  end
  object Close: TsfCommandButton
    Left = 188
    Top = 4
    Width = 145
    Height = 22
    Hint = ''Close''
    Caption = ''Close''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Close,Priv=STATUS_CHANGE_ORDER_NOTES,Visible=''#39''NOTE_STATUS=''#39 +
        #39''OPEN''#39#39#39'',TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceName=Orde'' +
        ''rNotesSel,RefreshedSQLSourceName=,SqlID=MFI_WID_CloseOrderNote'')
  end
  object Open: TsfCommandButton
    Left = 188
    Top = 4
    Width = 145
    Height = 22
    Hint = ''Open''
    Caption = ''Open''
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
      
        ''Desc=Open,Priv=STATUS_CHANGE_ORDER_NOTES,Visible=''#39''NOTE_STATUS=''#39#39 +
        ''CLOSED''#39#39#39'',TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceName=Ord'' +
        ''erNotesSel,RefreshedSQLSourceName=,SqlID=MFI_WID_OpenOrderNote'')
  end
  object History: TsfCommandButton
    Left = 335
    Top = 4
    Width = 145
    Height = 22
    Hint = ''Order Note History''
    Caption = ''History''
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
      
        ''Desc=Order Note History,Priv=,Visible=''#39''MRO_FLAG=''#39#39''N''#39#39#39'',TagValue='' +
        '',Action=Invoke,Tool=Tabular Report,ReportToolId=OrderNoteHistory'' +
        '',Params(''#39''ORDER_ID = :ORDER_ID,''#39'',NOTE_NO = :NOTE_NO)'')
  end
  object MRO_HISTORY: TsfCommandButton
    Left = 335
    Top = 4
    Width = 145
    Height = 22
    Hint = ''Order Note History''
    Caption = ''History''
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
      
        ''Desc=Order Note History,Priv=,Visible=''#39''MRO_FLAG=''#39#39''Y''#39#39#39'',TagValue='' +
        '',FKey=,Action=Invoke,Tool=Tabular Report,ReportToolId=SFOR_Order'' +
        ''NoteHistory,Params(''#39''ORDER_ID = :ORDER_ID,''#39'',''#39''NOTE_NO = :NOTE_NO,''#39 +
        '')'')
  end
  object Remove_Attachment: TsfCommandButton
    Left = 482
    Top = 5
    Width = 145
    Height = 21
    Hint = ''Remove Attachment''
    Caption = ''Remove Attachment''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (
      
        ''Desc=Remove Attachment,Priv=''#39''IMAGE=''#39#39''Image''#39#39'' AND NOTE_STATUS=''#39#39''O'' +
        ''PEN''#39#39#39'',Visible=,TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceNa'' +
        ''me=OrderNotesSel,RefreshedSQLSourceName=OrderNotesSel,SqlID=MFI_'' +
        ''WID_RemoveAttachmentForOrderNotes'')
  end
  object OrderNotesSel: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''OrderNotesSel''
    DeleteSqlId = ''OrderNoteDel''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''OrderNoteGrid~''
      ''Close~''
      ''Open~''
      ''History~''
      ''MRO_HISTORY~''
      ''Remove_Attachment~''
      ''NoteText~NOTE_TEXT_FOR_MEMO'')
    ParamsSQLSourceName = ''@Toolscope''
    PublishParams = True
    SelectedFields.Strings = (
      ''ORDER_ID~0~ORDER_ID~N~N~Y~N~N~N~~~~~Default~N~~''
      ''NOTE_NO~10~Note No~N~N~True~False~False~N~~~~~Default~N~~''
      ''OPER_NO~5~Oper No~N~N~False~False~False~N~~~~~Default~N~~''
      ''STEP_NO~5~Step No~N~N~False~False~False~N~~~~~Default~N~~''
      ''OPER_KEY~5~Oper_key~N~N~True~False~False~N~~~~~Default~N~~''
      ''STEP_KEY~5~Step_key~N~N~True~False~False~N~~~~~Default~N~~''
      
        ''IMAGE~3~     ~N~N~False~False~False~N~~@StdMarkup(IMAGE)~@Comman'' +
        ''d(COMMAND)~~Default~N~~''
      ''NOTE_TYPE~15~Note Type~N~N~N~N~N~N~~~~~Default~N~~''
      ''NOTE_TITLE~20~Note Title~N~N~False~False~False~N~~~~~Default~N~~''
      ''NOTE_TEXT~20~Note Text~N~N~False~N~N~N~~~~~Default~N~~''
      
        ''NOTE_STATUS~0~Note Status~N~N~False~False~False~N~~~~~Default~N~'' +
        ''~''
      
        ''UPDT_USERID~10~Update User ID~N~N~N~N~N~N~MFI_2869~~~~Default~N~'' +
        ''~''
      ''TIME_STAMP~20~Update Time~N~N~N~N~N~N~~~~~Default~N~~''
      ''NOTE_ID~10~Note ID~N~N~True~N~False~N~~~~~Default~N~~''
      
        ''UCF_ORDER_NOTE_VCH1~0~UCF_ORDER_NOTE_VCH1~N~N~Y~N~N~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''UCF_ORDER_NOTE_VCH2~0~UCF_ORDER_NOTE_VCH2~N~N~Y~N~N~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''UCF_ORDER_NOTE_VCH3~0~UCF_ORDER_NOTE_VCH3~N~N~Y~N~N~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''UCF_ORDER_NOTE_VCH4~0~UCF_ORDER_NOTE_VCH4~N~N~Y~N~N~N~~~~~Defaul'' +
        ''t~N~~''
      
        ''UCF_ORDER_NOTE_VCH5~0~UCF_ORDER_NOTE_VCH5~N~N~Y~N~N~N~~~~~Defaul'' +
        ''t~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''UCF_ORDER_NOTE_VCH1~Edit''
      ''UCF_ORDER_NOTE_VCH2~Edit''
      ''UCF_ORDER_NOTE_VCH3~Edit''
      ''UCF_ORDER_NOTE_VCH4~Edit''
      ''OPER_KEY~Edit''
      ''STEP_KEY~Edit''
      ''OPER_NO~Edit''
      ''NOTE_NO~Edit''
      ''ORDER_ID~Edit''
      ''IMAGE~Edit''
      ''NOTE_TYPE~Edit''
      ''UPDT_USERID~Edit''
      ''TIME_STAMP~Edit''
      ''NOTE_ID~Edit'')
    InputUDVId = ''MFI_19442''
    InsertUDVId = ''MFI_19279''
    OtherCommands.Strings = (
      
        ''Desc=Display Attachment,Priv=,Visible=''#39''IMAGE=''#39#39''Image''#39#39#39'',TagValue'' +
        ''=,FKey=,Action=SideNote,SqlID=,Params(''#39''@SubConfig=Slide,''#39'',''#39''OBJEC'' +
        ''T_ID=:OBJECT_ID,''#39'',''#39''SLIDE_REF_ID=,''#39'',@BlockCaption=Slide)''
      
        ''Desc=Attach File,Priv=,Visible=''#39''IMAGE=''#39#39''LabelPlus''#39#39#39'',TagValue=,F'' +
        ''Key=,Action=UDV,UDVType=Update,UDVID=MFI_CBB61DAC4EB440CBB74E84B'' +
        ''8FFFAE4E9'')
    DataDefinitions.Strings = (
      ''ORDER_ID''
      ''NOTE_NO''
      ''OPER_NO''
      ''STEP_NO''
      ''OPER_KEY''
      ''STEP_KEY''
      ''IMAGE''
      ''NOTE_TYPE''
      ''NOTE_TITLE''
      ''NOTE_TEXT''
      ''NOTE_STATUS''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''NOTE_ID''
      ''UCF_ORDER_NOTE_VCH1''
      ''UCF_ORDER_NOTE_VCH2''
      ''UCF_ORDER_NOTE_VCH3''
      ''UCF_ORDER_NOTE_VCH4''
      ''UCF_ORDER_NOTE_VCH5'')
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

