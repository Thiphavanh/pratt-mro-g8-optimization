
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_D0EABCFAF5E039AAE05387971F0A046B.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_D0EABCFAF5E039AAE05387971F0A046B';
v_udv_tag sfcore_udv_lib.udv_tag%type :='Copy of MF1_1001327 - OrderDiscTab';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='Order Discrepancies - Defect 2013';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 488
  Height = 183
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
  AlignContents = acHorizontal
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
    Top = 8
    Width = 39
    Height = 18
    Hidden = ''False''
    VisibleButtons.Strings = (
      ''Refresh ''
      ''Filter '')
    ShowHint = True
    TabOrder = 0
    TabStop = True
  end
  object _OBJ2: TsfDBGrid
    Left = 4
    Top = 28
    Width = 474
    Height = 168
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
  object _OBJ4: TsfCommandButton
    Left = 67
    Top = 4
    Width = 164
    Height = 21
    Hint = ''Display Discrepancy Item''
    Caption = ''Display Discrepancy Item''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 2
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Display Discrepancy Item,Priv=,Visible=,TagValue=,FKey=,Act'' +
        ''ion=Invoke,Tool=DiscrepancyItem,ReportToolId=,Params(''#39''DISC_ID=:D'' +
        ''ISC_ID,''#39'',''#39''DISC_LINE_NO=:DISC_LINE_NO,''#39'',''#39''@InvokeToolMode=@GetQuer'' +
        ''yValue\(MFI_QA_DiscrepancyToolInvokeMode\)''#39'')'')
  end
  object _OBJ5: TsfCommandButton
    Left = 163
    Top = 4
    Width = 164
    Height = 21
    Hint = ''Display U-Chart''
    Caption = ''Display U-Chart''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 3
    OtherCommands.Strings = (
      
        ''Desc=Display U-Chart,Priv=,Visible=,TagValue=,FKey=,Action=UDV,U'' +
        ''DVType=Insert,UDVID=MFI_34250'')
  end
  object _OBJ6: TsfCommandButton
    Left = 255
    Top = 4
    Width = 164
    Height = 21
    Hint = ''Set Sampling Start Date''
    Caption = ''Set Sampling Start Date''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 4
    OtherCommands.Strings = (
      
        ''Desc=Set Sampling Start Date,Priv=''#39''{DISC_ID<>''#39#39#39#39''}''#39'',Visible={},T'' +
        ''agValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVI'' +
        ''D=MFI_33692'')
  end
  object _OBJ7: TsfCommandButton
    Left = 365
    Top = 4
    Width = 150
    Height = 21
    Hint = ''Item Disposition BIS Logs''
    Caption = ''Item Disposition Send Logs''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 5
    OtherCommands.Strings = (
      
        ''Desc=Item Disposition BIS Logs,Priv={},Visible=''#39''{@Lic_ENABLE_XML'' +
        ''=''#39#39''YES''#39#39'' AND @ToolType=''#39#39''Instructions.MfgInstructions''#39#39''}''#39'',TagVal'' +
        ''ue=,FKey=,ParamsSrc=@Default,Action=Invoke,Tool=Tabular Report,R'' +
        ''eportToolId=MFI_ITEMDISPOSITIONBISLOGS,Params(ITEM_ID = :ITEM_ID'' +
        '')'')
  end
  object OrderDiscTabSelect: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_OrderDiscTabSelect''
    LinkedControls.Strings = (
      ''_OBJ1~''
      ''_OBJ2~''
      ''_OBJ4~''
      ''_OBJ5~''
      ''_OBJ6~''
      ''_OBJ7~'')
    ParamsSQLSourceName = ''@ToolScope''
    PublishParams = True
    SelectedFields.Strings = (
      ''OPER_NO~4~Oper;No~N~N~N~N~False~N~~~~~Default~N~~~''
      ''DISC_ID~10~Disc ID; ~N~N~N~N~False~N~~~~~Default~N~~~''
      ''DISC_LINE_NO~3~Line;No~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''DISC_LINE_STATUS~12~Disc Line Status; ~N~N~N~N~False~N~~~~~Defau'' +
        ''lt~N~~~''
      ''PART_NO~12~Item No; ~N~N~N~N~False~N~~~~~Default~N~~~''
      ''ITEM_TYPE~8~Item Type; ~N~N~False~False~False~N~~~~~Default~N~~~''
      
        ''ITEM_SUBTYPE~12~Item Subtype; ~N~N~False~False~False~N~~~~~Defau'' +
        ''lt~N~~~''
      ''ROUTE_TYPE~8~Work Flow; ~N~N~N~N~False~N~~~~~Default~N~~~''
      ''DISP_TYPE~10~Disposition Type; ~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''DISP_INSTR_TYPE~14~Disposition;Instructions?~N~N~N~N~False~N~~~~'' +
        ''~Default~N~~~''
      ''AFFECTED_QTY~4~Affected;Qty~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''REJECT_COMPONENT_FLAG~1~Reject;Components?~N~N~N~N~False~N~~~~~D'' +
        ''efault~N~~~''
      ''DISC_CATEGORY~4~Disc;Cat~N~N~N~N~False~N~~~~~Default~N~~~''
      ''CA_FLAG~1~CA?; ~N~N~N~N~False~N~~~~~Default~N~~~''
      ''CA_ID~10~CA ID; ~N~N~N~N~False~N~~~~~Default~N~~~''
      
        ''REJECT_WORK_LOC~10~Reject Work Loc; ~N~N~N~N~False~N~~~~~Default'' +
        ''~N~~~''
      
        ''REJECT_WORK_DEPT~10~Reject Work Dept; ~N~N~N~N~False~N~~~~~Defau'' +
        ''lt~N~~~''
      
        ''REJECT_WORK_CENTER~10~Reject Work Center; ~N~N~N~N~False~N~~~~~D'' +
        ''efault~N~~~''
      
        ''DISC_LINE_TITLE~10~Discrepant Feature~N~N~False~False~False~N~~~'' +
        ''~~Default~N~~~''
      
        ''UPDT_USERID~10~Update User ID; ~N~N~N~N~False~N~MFI_2869~~~~Defa'' +
        ''ult~N~~~''
      ''TIME_STAMP~18~Update Time; ~N~N~N~N~False~N~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''DISC_ID~Edit''
      ''DISC_LINE_STATUS~Edit''
      ''UPDT_USERID~Edit''
      ''ITEM_TYPE~Edit''
      ''PART_NO~Edit''
      ''REJECT_WORK_CENTER~Edit'')
    PrintTitle = ''Discrepancies''
    TestParamValues.Strings = (
      ''order_id=PWUST_A4CC54509398B09FF3D137301BB595D3'')
    OtherCommands.Strings = (
      
        ''Desc=Display Discrepancy Item,Priv={},Visible={},TagValue=,FKey='' +
        '',Action=Invoke,Tool=DiscrepancyItem,ReportToolId=,Params(''#39''DISC_I'' +
        ''D=:DISC_ID,''#39'',''#39''DISC_LINE_NO=:DISC_LINE_NO,''#39'',''#39''@InvokeToolMode=@Get'' +
        ''QueryValue\(MFI_QA_DiscrepancyToolInvokeMode\)''#39'')'')
    DataDefinitions.Strings = (
      ''OPER_NO''
      ''DISC_ID''
      ''DISC_LINE_NO''
      ''DISC_LINE_STATUS''
      ''PART_NO''
      ''ITEM_TYPE''
      ''ITEM_SUBTYPE''
      ''ROUTE_TYPE''
      ''DISP_TYPE''
      ''DISP_INSTR_TYPE''
      ''AFFECTED_QTY''
      ''REJECT_COMPONENT_FLAG''
      ''DISC_CATEGORY''
      ''CA_FLAG''
      ''CA_ID''
      ''REJECT_WORK_LOC''
      ''REJECT_WORK_DEPT''
      ''REJECT_WORK_CENTER''
      ''UPDT_USERID''
      ''TIME_STAMP''
      ''DISC_LINE_TITLE'')
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

