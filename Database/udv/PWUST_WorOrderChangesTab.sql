
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_CD2708DD243C776AE05387971F0AC82E.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_CD2708DD243C776AE05387971F0AC82E';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_WorOrderChangesTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='WorOrderChangesTab - DEFECT 1976';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 900
  Height = 240
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
  AutoScrollbars = False
  DynamicFieldAttributes = False
  BottomMargin = -1
  object AlterationInfo: TsfDBGrid
    Left = 4
    Top = 26
    Width = 550
    Height = 209
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
    Highlighting.Strings = (
      ''@Row~Red~Default~alt_status <> ''#39''COMPLETE''#39)
    Hidden = ''False''
    RowSelection = True
  end
  object Operations: TsfDBGrid
    Left = 559
    Top = 20
    Width = 187
    Height = 80
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
  object _OBJ1: TsfLabel
    Left = 660
    Top = 3
    Width = 53
    Height = 13
    Caption = ''Operations''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
  end
  object _OBJ3: TsfUDVNavigator
    Left = 4
    Top = 3
    Width = 58
    Height = 21
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Edit ''
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 2
    TabStop = True
  end
  object REMFILE: TsfCommandButton
    Left = 211
    Top = 3
    Width = 105
    Height = 21
    Hint = ''Remove File''
    Caption = ''Remove Attachment''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Remove File,Priv=''#39''@CurrentEditBlock = ''#39#39#39#39'' AND IMAGE=''#39#39''Imag'' +
        ''e''#39#39'' and ALT_STATUS = ''#39#39''IN PROCESS''#39#39#39'',Visible=,TagValue=,FKey=,Ac'' +
        ''tion=ExecSql,ParamsSqlSourceName=OrderAlterationsInfo,''#39''Refreshed'' +
        ''SQLSourceName=OrderAlterationsInfo, OrderAltOperations, MFI_WID_'' +
        ''WORK_SCOPE_HOLD_EXISTS''#39'',SqlID=MFI_WID_RemoveAlterationFile'')
  end
  object _OBJ4: TsfCommandButton
    Left = 140
    Top = 3
    Width = 100
    Height = 21
    Hint = ''Add Attachment''
    Caption = ''Add Attachment''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Add Attachment,Priv=''#39''@ToolState<>''#39#39''ViewModes.View_Alt''#39#39'' AND'' +
        '' IMAGE=''#39#39''LabelPlus''#39#39'' AND ALT_STATUS = ''#39#39''IN PROCESS''#39#39#39'',Visible=,T'' +
        ''agValue=,FKey=,Action=UDV,UDVType=Insert,UDVID=MFI_48B405B668024'' +
        ''D73AD85AB5C3C6B7D0F'')
  end
  object _OBJ5: TsfCommandButton
    Left = 70
    Top = 3
    Width = 100
    Height = 21
    Caption = ''Compare Order''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Compare Order,Priv=,Visible=,TagValue=,FKey=,Action=UDV,UDV'' +
        ''Type=Update,UDVID=MFI_5FB5449D18134B138A985B9D0FAA8B0E'')
  end
  object _OBJ6: TsfCommandButton
    Left = 286
    Top = 3
    Width = 100
    Height = 21
    Hint = ''Work Order - Start Alteration''
    Caption = ''Start Alteration''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Work Order - Start Alteration,''#39''Priv={@hasPriv(''#39#39''ORDER_AUTHO'' +
        ''RING''#39#39'') AND @hasPriv(''#39#39''PWUST_ALTERATION''#39#39'')}''#39'',''#39''Visible={ORDER_STA'' +
        ''TUS <> ''#39#39''CLOSE''#39#39'' AND (ALTERATION_TYPE = ''#39#39#39#39'' OR ALTERATION_TYPE '' +
        ''= ''#39#39''OPERATION''#39#39'')}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Action=Exe'' +
        ''cSql,ParamsSqlSourceName=@ToolScope,RefreshedSQLSourceName=,SqlI'' +
        ''D=MFI_PLG_SHOWORDERALTERATIONUDV'')
  end
  object _OBJ7: TsfCommandButton
    Left = 355
    Top = 3
    Width = 140
    Height = 21
    Hint = ''WO Revision BIS Logs''
    Caption = ''Order Revision Send Logs''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (
      
        ''Desc=WO Revision BIS Logs,Priv={},Visible=''#39''{@Lic_ENABLE_XML=''#39#39''YE'' +
        ''S''#39#39'' AND @ToolType=''#39#39''Instructions.MfgInstructions''#39#39''}''#39'',TagValue=,F'' +
        ''Key=,ParamsSrc=@Default,Action=Invoke,Tool=Tabular Report,Report'' +
        ''ToolId=MFI_WOREVISIONBISLOGS,Params(ORDER_ID = :ORDER_ID)'')
  end
  object _OBJ8: TsfCommandButton
    Left = 435
    Top = 3
    Width = 170
    Height = 21
    Hint = ''WO Revision Delta BIS Logs''
    Caption = ''Order Revision Delta Send Logs''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 8
    OtherCommands.Strings = (
      
        ''Desc=WO Revision Delta BIS Logs,Priv={},Visible=''#39''{@Lic_ENABLE_XM'' +
        ''L=''#39#39''YES''#39#39'' AND @ToolType=''#39#39''Instructions.MfgInstructions''#39#39''}''#39'',TagVa'' +
        ''lue=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=Tabular Report,'' +
        ''ReportToolId=MFI_WOREVISIONDELTABISLOGS,Params(ORDER_ID = :ORDER'' +
        ''_ID)'')
  end
  object Discrepancies: TsfLabel
    Left = 660
    Top = 113
    Width = 70
    Height = 15
    Caption = ''Discrepancies''
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    AutoSize = False
    ShowHint = False
  end
  object DIInfo: TsfDBGrid
    Left = 559
    Top = 135
    Width = 187
    Height = 100
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = True
    TabOrder = 9
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object DI_NAVIGATOR: TsfUDVNavigator
    Left = 559
    Top = 113
    Width = 39
    Height = 20
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 10
  end
  object OrderAlterationsInfo: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_OrderChangesSel''
    LinkedControls.Strings = (
      ''_OBJ3~''
      ''REMFILE~''
      ''_OBJ4~''
      ''_OBJ7~''
      ''_OBJ8~''
      ''AlterationInfo~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''DISP_ALT_NO~12~Alteration No; ~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~''
      
        ''IMAGE~1~ ~N~N~False~False~True~N~~@StdMarkup(IMAGE)~@Command(COM'' +
        ''MAND)~~Default~N~''
      
        ''ALT_STATUS~10~Alteration Status; ~N~N~False~N~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''ALT_REASON~30~Alteration Reason; ~N~N~False~N~False~N~~~~~Defaul'' +
        ''t~N~''
      
        ''ALTER_TYPE~8~Alteration Level; ~N~N~False~N~False~N~~~~~Default~'' +
        ''N~''
      
        ''ASSIGNED_TO_USERID~8~Assigned To; ~N~N~False~N~False~N~~~~~Defau'' +
        ''lt~N~''
      ''START_DATE~12~Start Date; ~N~N~False~N~False~N~~~~~Default~N~''
      
        ''DATE_COMPLETED~12~Completion Date; ~N~N~False~N~False~N~~~~~Defa'' +
        ''ult~N~''
      
        ''UPDT_USERID~12~Update UserID; ~N~N~False~False~False~N~MFI_2869~'' +
        ''~~~Default~N~''
      ''UCF_ALT_VCH1~5~UCF Alt Vch1~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH2~5~UCF Alt Vch2~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH3~5~UCF Alt Vch3~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH4~5~UCF Alt Vch4~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH5~5~UCF Alt Vch5~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH6~5~UCF Alt Vch6~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH7~5~UCF Alt Vch7~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH8~5~UCF Alt Vch8~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH9~5~UCF Alt Vch9~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH10~5~UCF Alt Vch10~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH11~5~UCF Alt Vch11~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH12~5~UCF Alt Vch12~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH13~5~UCF Alt Vch13~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH14~5~UCF Alt Vch14~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_VCH15~5~UCF Alt Vch15~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_NUM1~5~UCF Alt Num1~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_NUM2~5~UCF Alt Num2~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_NUM3~5~UCF Alt Num3~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_NUM4~5~UCF Alt Num4~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_NUM5~5~UCF Alt Num5~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_FLAG1~5~UCF Alt Flag1~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_FLAG2~5~UCF Alt Flag2~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_FLAG3~5~UCF Alt Flag3~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_FLAG4~5~UCF Alt Flag4~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_FLAG5~5~UCF Alt Flag5~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_DATE1~5~UCF Alt Date1~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_DATE2~5~UCF Alt Date2~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_DATE3~5~UCF Alt Date3~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_DATE4~5~UCF Alt Date4~N~N~True~N~False~N~~~~~Default~N~''
      ''UCF_ALT_DATE5~5~UCF Alt Date5~N~N~True~N~False~N~~~~~Default~N~'')
    SelectedFieldsEditControl.Strings = (
      ''@TextModified~Edit''
      ''CHG_AUTH_NUM~Edit''
      ''UCF_ALT_DATE5~Edit''
      ''UCF_ALT_DATE4~Edit''
      ''UCF_ALT_DATE3~Edit''
      ''UCF_ALT_DATE1~Edit''
      ''UCF_ALT_FLAG5~Edit''
      ''UCF_ALT_FLAG4~Edit''
      ''UCF_ALT_FLAG3~Edit''
      ''UCF_ALT_FLAG2~Edit''
      ''UCF_ALT_FLAG1~Edit''
      ''UCF_ALT_NUM5~Edit''
      ''UCF_ALT_NUM4~Edit''
      ''UCF_ALT_NUM3~Edit''
      ''UCF_ALT_NUM2~Edit''
      ''UCF_ALT_NUM1~Edit''
      ''UCF_ALT_VCH7~Edit''
      ''ALT_REASON~Edit''
      ''ALTER_TYPE~Edit''
      ''START_DATE~Edit''
      ''DISP_ALT_NO~Edit''
      ''UPDT_USERID~Edit'')
    TestParamValues.Strings = (
      ''ORDER_NO=FSD''
      ''@ToolState=EditModes.Edit_PL''
      ''ORDER_ID=MFI_AE429D2689098A0E88023F72DCB0F8C9'')
    InputUDVId = ''MFI_1003889''
    OtherCommands.Strings = (
      
        ''Desc=Display,Priv={},Visible=''#39''{IMAGE=''#39#39''Image''#39#39''}''#39'',TagValue=,FKey='' +
        '',ParamsSrc=@Default,Action=SideNote,SqlID=,Params(''#39''@SubConfig=:S'' +
        ''LIDE_MODE,''#39'',''#39''OBJECT_ID=:OBJECT_ID,''#39'',''#39''SLIDE_REF_ID=,''#39'',''#39''TESTPARAM1'' +
        ''=''#39#39''WO_ALT''#39#39''+:ALTERATION_ID,''#39'',@BlockCaption=Slide)''
      
        ''Desc=Add Attachment,Priv=''#39''{@ToolState<>''#39#39''ViewModes.View_Alt''#39#39'' AN'' +
        ''D IMAGE=''#39#39''LabelPlus''#39#39''}''#39'',Visible=''#39''{IMAGE=''#39#39''LabelPlus''#39#39'' and ALT_ST'' +
        ''ATUS = ''#39#39''IN PROCESS''#39#39''}''#39'',TagValue=,FKey=,ParamsSrc=@Default,Actio'' +
        ''n=UDV,UDVType=Insert,UDVID=MFI_48B405B668024D73AD85AB5C3C6B7D0F'')
    DataDefinitions.Strings = (
      ''DISP_ALT_NO''
      ''IMAGE''
      ''ALT_STATUS''
      ''ALT_REASON''
      ''ALTER_TYPE''
      ''ASSIGNED_TO_USERID''
      ''START_DATE''
      ''DATE_COMPLETED''
      ''UPDT_USERID''
      ''UCF_ALT_VCH1''
      ''UCF_ALT_VCH2''
      ''UCF_ALT_VCH3''
      ''UCF_ALT_VCH4''
      ''UCF_ALT_VCH5''
      ''UCF_ALT_VCH6''
      ''UCF_ALT_VCH7''
      ''UCF_ALT_VCH8''
      ''UCF_ALT_VCH9''
      ''UCF_ALT_VCH10''
      ''UCF_ALT_VCH11''
      ''UCF_ALT_VCH12''
      ''UCF_ALT_VCH13''
      ''UCF_ALT_VCH14''
      ''UCF_ALT_VCH15''
      ''UCF_ALT_NUM1''
      ''UCF_ALT_NUM2''
      ''UCF_ALT_NUM3''
      ''UCF_ALT_NUM4''
      ''UCF_ALT_NUM5''
      ''UCF_ALT_FLAG1''
      ''UCF_ALT_FLAG2''
      ''UCF_ALT_FLAG3''
      ''UCF_ALT_FLAG4''
      ''UCF_ALT_FLAG5''
      ''UCF_ALT_DATE1''
      ''UCF_ALT_DATE2''
      ''UCF_ALT_DATE3''
      ''UCF_ALT_DATE4''
      ''UCF_ALT_DATE5'')
    ConsolidatedQuery = False
  end
  object OrderAltOperations: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_OrderAltOperations''
    LinkedControls.Strings = (
      ''Operations~'')
    ParamsSQLSourceName = ''OrderAlterationsInfo''
    PublishParams = True
    SelectedFields.Strings = (
      ''OPER_NO~5~Oper No; ~N~N~False~N~False~N~~~~~Default~''
      ''TITLE~10~Title; ~N~N~False~N~False~N~~~~~Default~'')
    SelectedFieldsEditControl.Strings = (
      ''TITLE~Edit'')
    DataDefinitions.Strings = (
      ''OPER_NO''
      ''TITLE'')
    ConsolidatedQuery = False
  end
  object MFI_WID_WORK_SCOPE_HOLD_EXISTS: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_WID_WORK_SCOPE_HOLD_EXISTS''
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    TestParamValues.Strings = (
      ''ORDER_ID=MFI_AE429D2689098A0E88023F72DCB0F8C9'')
    ConsolidatedQuery = False
  end
  object ToFromQueueTypes: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_PLG_ToFromQueueTypes''
    ParamsSQLSourceName = ''@ToolScope,OrderAlterationsInfo''
    PublishParams = True
    ConsolidatedQuery = False
  end
  object AlterationTypeSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_AlterationTypeSelect''
    LinkedControls.Strings = (
      ''_OBJ6~'')
    ParamsSQLSourceName = ''OrderAlterationsInfo''
    PublishParams = True
    TestParamValues.Strings = (
      ''order_id='')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''AlterationTypeSelect''
  end
  object DiscrepancyInfo: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''MFI_SFQA_AlterationDiscrepancies''
    LinkedControls.Strings = (
      ''DIInfo~''
      ''DI_NAVIGATOR~'')
    ParamsSQLSourceName = ''OrderAlterationsInfo''
    SelectedFields.Strings = (
      
        ''DI_DISPLAY~20~Disc ID, Line No~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~Y~~~'')
    SelectedFieldsEditControl.Strings = (
      ''DISC_ID~Edit''
      ''DI_DISPLAY~Edit'')
    DataDefinitions.Strings = (
      ''DI_DISPLAY'')
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

