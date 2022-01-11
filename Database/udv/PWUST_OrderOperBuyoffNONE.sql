
set define off
prompt ---------------------------------------;
prompt Executing ... PWUST_620155A631A6955BE05387971F0A3B0E.sql
prompt ---------------------------------------;
declare
v_folder_id sfcore_udv_folder.folder_id%type :='PWUST_550FF0D87F4141A0E05387971F0AD3EA';
v_udv_id sfcore_udv_lib.udv_id%type  :='PWUST_620155A631A6955BE05387971F0A3B0E';
v_udv_tag sfcore_udv_lib.udv_tag%type :='PWUST_OrderOperBuyoffNONE';
v_udv_type sfcore_udv_lib.udv_type%type  :='PANEL';
v_udv_desc sfcore_udv_lib.udv_desc%type  :='SMRO_WOE_202;660;658;712;860;731;SMRO_WOE_302;1462;1683;1696;1534;1714;1720;1699;1576;1965';
v_state sfcore_udv_lib.state%type  :='';
v_load_ref sfcore_udv_lib.load_ref%type  :='';
v_tool_version sfcore_udv_lib.tool_version%type  :='2.4.4.3.20190514~2.4';
v_object_rev sfcore_udv_lib.object_rev%type  :='';
v_owner_group sfcore_udv_lib.owner_group%type  :='';
v_stype sfcore_udv_lib.stype%type  :='SFWID';
p1 clob :='object TCBScrollBox
  Left = 0
  Top = 0
  Width = 1500
  Height = 180
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
  AlignContents = acParentWidth
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
  object _OBJ10: TsfCommandButton
    Left = 4
    Top = 6
    Width = 84
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
    TabOrder = 1
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Accept All,''#39''Priv={ACCEPTALL=''#39#39''ACCEPTALL''#39#39'' AND ( WID_OPER_ST'' +
        ''ATUS=''#39#39''ACTIVE''#39#39'' OR WID_OPER_STATUS=''#39#39''IN QUEUE''#39#39'' )}''#39'',Visible={1=0'' +
        ''},TagValue=,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,U'' +
        ''DVID=MFI_1009867'')
  end
  object _OBJ11: TsfDBGrid
    Left = 4
    Top = 29
    Width = 950
    Height = 118
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
  object _OBJ12: TsfCommandButton
    Left = 182
    Top = 6
    Width = 84
    Height = 21
    Hint = ''Display Buyoffs''
    Caption = ''Display''
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
      
        ''Desc=Display Buyoffs,Priv=,Visible=,TagValue=,Action=SideNote,Sq'' +
        ''lID=WID_BuyoffSidePanelFromIns,Params(''#39''ORDER_ID=:ORDER_ID,''#39'',''#39''OPE'' +
        ''R_KEY=:OPER_KEY,''#39'',''#39''STEP_KEY=:STEP_KEY,''#39'',REF_ID=:REF_ID)'')
  end
  object _OBJ13: TsfCommandButton
    Left = 652
    Top = 7
    Width = 84
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
    TabOrder = 3
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Skip,Priv=''#39''{SKIP=''#39#39''SKIP''#39#39''}''#39'',Visible={1=0},TagValue=,FKey=,P'' +
        ''aramsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_14E95BBF2A'' +
        ''A24D7AE0440003BA560E35'')
  end
  object _OBJ14: TsfCommandButton
    Left = 93
    Top = 6
    Width = 84
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
    TabOrder = 4
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Reopen,Priv=''#39''{WID_OPER_STATUS=''#39#39''ACTIVE''#39#39'' OR WID_OPER_STATUS'' +
        ''=''#39#39''IN QUEUE''#39#39''}''#39'',Visible=''#39''{ORDER_STATUS<>''#39#39''CLOSE''#39#39''}''#39'',TagValue=,FK'' +
        ''ey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_10099'' +
        ''29'')
  end
  object Partial: TsfCommandButton
    Left = 537
    Top = 5
    Width = 84
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
    TabOrder = 5
    TabStop = True
    OtherCommands.Strings = (
      
        ''Desc=Partial,Priv=''#39''{PARTIAL=''#39#39''PARTIAL''#39#39'' AND WID_OPER_STATUS<>''#39#39''C'' +
        ''LOSE''#39#39''}''#39'',Visible={1=0},TagValue=,FKey=,ParamsSrc=@Default,Action'' +
        ''=UDV,UDVType=Update,UDVID=MFI_5457903B0F9D409DE0440003BA041A64'')
  end
  object SIGNATURES: TsfCommandButton
    Left = 384
    Top = 7
    Width = 116
    Height = 21
    Hint = ''Add Signature''
    Caption = ''Add Signature''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 6
    OtherCommands.Strings = (
      
        ''Desc=Add Signature,Priv=''#39''{ACTUAL_BUYOFF_STATUS= ''#39#39''ACCEPT''#39#39''}''#39'',Vis'' +
        ''ible={},TagValue=ADDSIG,FKey=,ParamsSrc=@Default,Action=UDV,UDVT'' +
        ''ype=Update,UDVID=PWUST_C93A9ABE68BC42B8E053EF9F1F0AA100'')
  end
  object _OBJ17: TsfCommandButton
    Left = 274
    Top = 6
    Width = 98
    Height = 21
    Hint = ''Buyoff Status''
    Caption = ''Buyoff Status''
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''MS Sans Serif''
    Font.Style = []
    ParentFont = True
    ShowHint = True
    TabOrder = 7
    OtherCommands.Strings = (
      
        ''Desc=N/A,''#39''Priv={PERCENT_QTY_COMPLETE <>''#39#39''100 %''#39#39'' AND ( WID_OPER_'' +
        ''STATUS=''#39#39''ACTIVE''#39#39'' OR WID_OPER_STATUS=''#39#39''IN QUEUE''#39#39'' )}''#39'',Visible=''#39''{'' +
        ''ORDER_STATUS<>''#39#39''CLOSE''#39#39''}''#39'',TagValue=N/A,FKey=,ParamsSrc=@Default,'' +
        ''Action=UDV,UDVType=Update,UDVID=PWUST_C96383E03328D65AE05387971F'' +
        ''0A30E1''
      
        ''Desc=N/D,''#39''Priv={PERCENT_QTY_COMPLETE <>''#39#39''100 %''#39#39''  AND ( WID_OPER'' +
        ''_STATUS=''#39#39''ACTIVE''#39#39'' OR WID_OPER_STATUS=''#39#39''IN QUEUE''#39#39'' )}''#39'',Visible=''#39 +
        ''{ORDER_STATUS<>''#39#39''CLOSE''#39#39''}''#39'',TagValue=N/D,FKey=,ParamsSrc=@Default'' +
        '',Action=UDV,UDVType=Update,UDVID=PWUST_C96383E03328D65AE05387971'' +
        ''F0A30E1''
      
        ''Desc=Training,Priv=''#39''{WID_OPER_STATUS=''#39#39''ACTIVE''#39#39'' OR WID_OPER_STAT'' +
        ''US=''#39#39''IN QUEUE''#39#39''}''#39'',Visible=''#39''{ORDER_STATUS<>''#39#39''CLOSE''#39#39''}''#39'',TagValue=T'' +
        ''RAINING,FKey=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID'' +
        ''=PWUST_C9B065C85294AA3CE05387971F0A1584''
      
        ''Desc=Partial,Priv=''#39''{PERCENT_QTY_COMPLETE <>''#39#39''100 %''#39#39'' AND WID_OPE'' +
        ''R_STATUS=''#39#39''ACTIVE''#39#39'' OR WID_OPER_STATUS=''#39#39''IN QUEUE''#39#39''}''#39'',Visible=''#39''{'' +
        ''ORDER_STATUS<>''#39#39''CLOSE''#39#39''}''#39'',TagValue=PARTIAL,FKey=,ParamsSrc=@Defa'' +
        ''ult,Action=UDV,UDVType=Update,UDVID=PWUST_C9605BA2ECC03045E05387'' +
        ''971F0A02AF'')
  end
  object SIGNOFF_GRID: TsfDBGrid
    Left = 960
    Top = 29
    Width = 400
    Height = 118
    TabStop = False
    ParentFont = True
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = ''Tahoma''
    Font.Style = []
    ShowHint = False
    TabOrder = 8
    TitleFont.Charset = DEFAULT_CHARSET
    TitleFont.Color = clWindowText
    TitleFont.Height = -11
    TitleFont.Name = ''Tahoma''
    TitleFont.Style = []
    Hidden = ''False''
    RowSelection = True
  end
  object GetSignoffs: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_SelectBuyoffSignatures''
    LinkedControls.Strings = (
      ''SIGNOFF_GRID''
      ''SIGNOFF_GRID~'')
    ParamsSQLSourceName = ''BuyoffExe''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''SIGNOFF_NAME~12~Additional;Signoff Name~N~N~False~False~False~N~'' +
        ''~~~~Default~N~~''
      
        ''SIGNOFF_USERID~12~Additional;Signoff User Id~N~N~False~False~Fal'' +
        ''se~N~~~~~Default~N~~''
      
        ''SIGNOFF_DATE~12~Additional;Signoff Timestamp~N~N~False~False~Fal'' +
        ''se~N~~~~~Default~N~~'')
    SelectedFieldsEditControl.Strings = (
      ''USERNAME~Edit''
      ''UPDT_USERID~Edit''
      ''SIGNOFF_NAME~Edit'')
    DataDefinitions.Strings = (
      ''SIGNOFF_DATE''
      ''SIGNOFF_NAME''
      ''SIGNOFF_USERID'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''GetSignoffs''
  end
  object BuyoffExe: TSfSqlSource
    SelectOnUserConnection = False
    SelectSqlId = ''PWUST_BuyoffNoneSel''
    LinkedControls.Strings = (
      ''_OBJ10~''
      ''_OBJ12~''
      ''_OBJ14~''
      ''_OBJ13~''
      ''Partial~''
      ''SIGNATURES''
      ''SIGNATURES~''
      ''_OBJ17~''
      ''_OBJ11~'')
    ParamsSQLSourceName = ''@CurrentMarker''
    RefreshedSQLSourceName = ''GetSignoffs''
    PublishParams = True
    SelectedFields.Strings = (
      
        ''IMAGE~1~ ~N~N~False~False~False~N~~@StdMarkup(IMAGE)~@Command(DI'' +
        ''Splay Slide)~~Default~N~~~''
      
        ''ACCEPT~1~ACCEPT~N~N~False~False~False~N~~@StdMarkup(ACCEPT)~@Com'' +
        ''mand(Accept)~~Default~N~~~''
      
        ''BUYOFF_TYPE~12~Buyoff Type;~N~N~True~False~True~N~~~~~Default~N~'' +
        ''~~''
      
        ''BUYOFF_TITLE~20~Buyoff Title;~N~N~False~False~False~N~~~~~Defaul'' +
        ''t~N~~~''
      
        ''UPDT_USERID~12~Update;User Id~N~N~false~False~False~N~MFI_2869~~'' +
        ''~~Default~N~~~''
      ''USERNAME~0~User;Name~N~N~False~False~False~N~~~~~Default~N~~~''
      ''BUYOFF_ID~0~BUYOFF_ID~N~N~True~False~True~N~~~~~Default~N~~~''
      
        ''PERCENT_QTY_COMPLETE~10~% Complete/;Complete Qty~N~N~GROUP_JOB_N'' +
        ''O<>''#39#39''~False~False~N~~~~~Default~N~~~''
      
        ''COMMENTS~20~Comments~N~N~True~False~ORDER_QTY<>''#39''1''#39'' AND SERIAL_FL'' +
        ''AG<>''#39''N''#39''~N~~~~~Default~N~~~''
      
        ''TIME_STAMP~12~Update Time;~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''BUYOFF_CERT~10~Req''#39''d Cert;~N~N~False~False~False~N~~~~~Default~N'' +
        ''~~~''
      
        ''OPTIONAL_FLAG~3~Opt?p;~N~N~False~False~ORDER_QTY=''#39''1''#39'' OR SERIAL_F'' +
        ''LAG=''#39''N''#39''~N~~~~~Default~N~~~''
      ''REF_ID~0~0~N~N~True~False~True~Y~~~~~Default~N~~~''
      ''OPER_KEY~0~0~N~N~True~False~True~Y~~~~~Default~N~~~''
      ''STEP_KEY~0~0~N~N~True~False~True~Y~~~~~Default~N~~~'')
    SelectedFieldsEditControl.Strings = (
      ''PERCENT_COMPLETE~Edit''
      ''REF_ID~Edit''
      ''BUYOFF_ID~Edit''
      ''COMMENTS~Edit''
      ''STEP_KEY~Edit''
      ''BUYOFF_CERT~Edit''
      ''BUYOFF_TYPE~Edit''
      ''ACCEPT~Edit''
      ''TIME_STAMP~Edit'')
    TestParamValues.Strings = (
      ''order_id=PWUST_DA89938CFB3A34E67FD166A077349D62''
      ''oper_key=714903''
      ''step_key=-1''
      ''ref_id=PWUST_A6923A303F422ABBE053EF9F1F0AD98E''
      ''@NavLevelFieldName=OPER_NO''
      ''GROUP_JOB_NO='')
    OtherCommands.Strings = (
      
        ''Desc=Reopen,Priv={},Visible=''#39''{REOPEN=''#39#39''REOPEN''#39#39''}''#39'',TagValue=,FKey'' +
        ''=,ParamsSrc=@Default,Action=UDV,UDVType=Update,UDVID=MFI_1009929''
      
        ''Desc=Reject,Priv=,Visible=''#39''REJECT=''#39#39''REJECT''#39#39#39'',TagValue=,FKey=,Ac'' +
        ''tion=UDV,UDVType=Update,UDVID=MFI_1009887''
      
        ''Desc=Accept,Priv=,Visible=''#39''ACCEPT=''#39#39''ACCEPT''#39#39#39'',TagValue=,FKey=,Ac'' +
        ''tion=UDV,UDVType=Update,UDVID=MFI_1009872''
      
        ''Desc=Skip,Priv=,Visible=''#39''SKIP=''#39#39''SKIP''#39#39#39'',TagValue=,FKey=,Action=U'' +
        ''DV,UDVType=Update,UDVID=MFI_14E95BBF2AA24D7AE0440003BA560E35''
      
        ''Desc=Partial,Priv=,Visible=''#39''PARTIAL=''#39#39''PARTIAL''#39#39#39'',TagValue=,FKey='' +
        '',Action=UDV,UDVType=Update,UDVID=MFI_5457903B0F9D409DE0440003BA0'' +
        ''41A64''
      
        ''Desc=Display Slide,Priv={},Visible=''#39''{SLIDE_ID<>''#39#39#39#39''}''#39'',TagValue=,'' +
        ''FKey=,ParamsSrc=@Default,Action=SideNote,SqlID=,Params(''#39''@SubConf'' +
        ''ig=Slide,''#39'',''#39''OBJECT_ID=:SLIDE_ID,''#39'',''#39''SLIDE_REF_ID=:SLIDE_EMBEDDED_'' +
        ''REF_ID,''#39'',''#39''ORDER_ID = :ORDER_ID,''#39'',''#39''OPER_KEY = :OPER_KEY,''#39'',STEP_KE'' +
        ''Y = :STEP_KEY)''
      
        ''Desc=SKIP_BUYOFF_CANBE_ACCEPTED,Priv=,Visible=''#39''ACCEPT=''#39#39''SKIP_BUY'' +
        ''OFF_CANBE_ACCEPTED''#39#39#39'',TagValue=,FKey=,Action=UDV,UDVType=Update,'' +
        ''UDVID=MFI_1009872''
      
        ''Desc=SKIP_BUYOFF,Priv=''#39''ACCEPT=''#39#39''ACCEPT''#39#39#39'',Visible=''#39''ACCEPT=''#39#39''SKIP'' +
        ''_BUYOFF''#39#39#39'',TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceName=,R'' +
        ''efreshedSQLSourceName=,SqlID=DUMMY''
      
        ''Desc=COMPLETEBUYOFF,Priv=''#39''ACCEPT=''#39#39''ACCEPT''#39#39#39'',Visible=''#39''ACCEPT=''#39#39''C'' +
        ''OMPLETEBUYOFF''#39#39#39'',TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceN'' +
        ''ame=,RefreshedSQLSourceName=,SqlID=DUMMY''
      
        ''Desc=NOT_REJECT,Priv=''#39''REJECT=''#39#39''REJECT''#39#39#39'',Visible=''#39''REJECT=''#39#39''NOT_R'' +
        ''EJECT''#39#39#39'',TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceName=,Ref'' +
        ''reshedSQLSourceName=,SqlID=DUMMY''
      
        ''Desc=REJECTED,Priv=''#39''REJECT=''#39#39''REJECT''#39#39#39'',Visible=''#39''REJECT=''#39#39''REJECTE'' +
        ''D''#39#39#39'',TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceName=,Refresh'' +
        ''edSQLSourceName=,SqlID=DUMMY''
      
        ''Desc=NOT_ACCEPT,Priv=''#39''ACCEPT=''#39#39''ACCEPT''#39#39#39'',Visible=''#39''ACCEPT=''#39#39''NOT_A'' +
        ''CCEPT''#39#39#39'',TagValue=,FKey=,Action=ExecSql,ParamsSqlSourceName=,Ref'' +
        ''reshedSQLSourceName=,SqlID=DUMMY'')
    DataDefinitions.Strings = (
      ''IMAGE''
      ''ACCEPT''
      ''BUYOFF_TYPE''
      ''BUYOFF_TITLE''
      ''UPDT_USERID''
      ''USERNAME''
      ''BUYOFF_ID''
      ''PERCENT_QTY_COMPLETE''
      ''COMMENTS''
      ''TIME_STAMP''
      ''BUYOFF_CERT''
      ''OPTIONAL_FLAG''
      ''REF_ID''
      ''OPER_KEY''
      ''STEP_KEY'')
    ConsolidatedQuery = False
    PublishedSQLSourceName = ''BuyoffExe''
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

