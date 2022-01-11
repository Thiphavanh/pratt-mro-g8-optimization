
set define off
prompt ---------------------------------------;
prompt Executing ... @IBASET_@Menu.sql
prompt ---------------------------------------;
declare
v_user_type sfcore_inplace_cfg_data.user_type%type  :='@IBASET';
v_cfg_type sfcore_inplace_cfg_data.cfg_type%type  :='@Menu';
v_desc sfcore_inplace_cfg_data.description%type  :='';
v_action varchar2(100) :='DML';
v_calling_object varchar2(100) := '@IBASET_@Menu.sql';
v_sql_code varchar2(200) := 'insert into SFCORE_INPLACE_CFG_DATA (USER_TYPE,CFG_TYPE,';
v_error_code varchar2(40) ;
v_error_msg  varchar2(1000) ;
p1 clob :='menu
 Process_Planning
  MenuItem
   Caption=Release Packages - All
   Item=Release Packages - All
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Process Planning Tasks - In Queue and Active
   Item=Process Planning Tasks - In Queue and Active
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order Alteration Tasks - In Process
   Item=Work Order Alteration Tasks - In Process
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Bills of Material
   Item=Bills of Material
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Parts
   Item=Parts
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Tools
   Item=Tools
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_TOOL'') AND '||chr(64)||'HasPriv(''TOOL_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Procedures
   Item=Procedures
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Machines
   Item=Machines
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Library - Files
   Item=Library - Files
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''PWUST_STD_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Library - Standard Operation
   Item=Library - Standard Operation
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Library - Standard Text
   Item=Library - Standard Text
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Library - Illustrations
   Item=Library - Illustrations
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''PWUST_STD_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Library - Standard Data Collections
   Item=Library - Standard Data Collections
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''PWUST_STD_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Compare Work Plan Revisions
   Item=Compare Work Plan Revisions
   List=
   Tool=
   Priv=DISPATCH_ALL
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_C97431E5031A41FCB6B61F8DA62E15D5,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
  MenuItem
   Caption=Data Collection Variables
   Item=Data Collection Variables
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DATCOL_VARIABLE'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Changes - In Process
   Item=Changes - In Process
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''CHANGE_REQUEST_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Release Planning
   Item=Release Planning
   List=DefinableData.ReleasePlanning
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''DISPATCH_ALL'')  AND '||chr(64)||'HasPriv(''CHANGE_REQUEST_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Part Status
   Item=Part Status
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_ALL'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Planner Supervisor Home Page
   Item=Planner Supervisor
   List=DefinableData.PlannerSupervisor
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''PLANNER_SUPERVISOR'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Planner Home Page
   Item=Planner
   List=DefinableData.Planner
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''PLANNER_HOMEPAGE'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=PAAR Requests
   Item=PAAR Requests
   List=Process Planning
   Tool=Dispatch
   Priv=DISPATCH_PRPLG
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Standard Network
   Item=Standard Network
   List=Process Planning
   Tool=Dispatch
   Priv=
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Create Plan Item No
   Item=Create Plan Item No
   List=
   Tool=
   Priv='||chr(64)||'HasPriv(''PW_PLAN_ITEM_NO'')
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=PWUST_57973BB56FEFE826E05387971F0A4AF3,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
  MenuItem
   Caption=Manuals
   Item=Manuals
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''PW_MANUALS_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Manual Providers
   Item=Manual Providers
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''PW_MANUAL_PROV_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Manual Types
   Item=Manual Types
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''PW_MANUAL_TYPES_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Authority Types
   Item=Authority Types
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''PW_AUTHORITY_TYPES_VIEW'')
   Command=
   Params=
   FKey=
 Inspection_Planning
  MenuItem
   Caption=Release Packages - All
   Item=Release Packages - All
   List=
   Tool=
   Priv=DISPATCH_PRPLG
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=PWUST_57973BB56FEFE826E05387971F0A4AF3,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
  MenuItem
   Caption=Inspection Planning Tasks - In Queue and Active
   Item=Inspection Planning Tasks - In Queue and Active
   List=Inspection Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Suppliers
   Item=Suppliers
   List=Inspection Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSPPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Test Inspection Orders
   Item=Test Inspection Orders
   List=Inspection Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSPPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Bills of Material
   Item=Bills of Material
   List=Inspection Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Tools
   Item=Tools
   List=Inspection Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Library - Standard Data Collections
   Item=Library - Standard Data Collections
   List=Inspection Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Changes - In Process
   Item=Changes - In Process
   List=Inspection Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''CHANGE_REQUEST_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Release Planning
   Item=Release Planning
   List=DefinableData.ReleasePlanning
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''DISPATCH_ALL'')  AND '||chr(64)||'HasPriv(''CHANGE_REQUEST_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Part Status
   Item=Part Status
   List=Inspection Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_ALL'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Requirement Groups
   Item=Requirement Groups
   List=Inspection Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSPPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Create Plan Item No
   Item=Create Plan Item No
   List=Process Planning
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''PW_PLAN_ITEM_NO'')
   Command=
   Params=
   FKey=
 Audit
  MenuItem
   Caption=Audit Processes
   Item=Audit Processes
   List=Audits
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Audit Planning Tasks - In Queue and Active
   Item=Audit Planning Tasks - In Queue and Active
   List=Audits
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_AUDIT'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Audit Planning Tasks - Complete
   Item=Audit Planning Tasks - Complete
   List=Audits
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_AUDIT'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Audits - Pending
   Item=Audits - Pending
   List=Audits
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_AUDIT'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Audit Execution Tasks - In Queue and Active
   Item=Audit Execution Tasks - In Queue and Active
   List=Audits
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_AUDIT'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Audit Findings - In Process and Complete
   Item=Audit Findings - In Process and Complete
   List=Audits
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_AUDIT'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Audit Corrective Actions - In Process
   Item=Audit Corrective Actions - In Process
   List=Audits
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_AUDIT'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Audit Corrective Action Items - In Process
   Item=Audit Corrective Action Items - In Process
   List=Audits
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_AUDIT'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Audit Results
   Item=Audit Results
   List=Audits
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_AUDIT'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Create Audit
   Item=Create Audit
   List=
   Tool=
   Priv=AUDIT_ORDER_CREATE
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_8D839C22BE2541AD8B58E88CDAC787C9,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
  MenuItem
   Caption=Auditor Portal
   Item=Auditor Portal
   List=
   Tool=
   Priv=DISPATCH_AUDIT
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_06AB2A5C2C924CFFB1DDD4E820697BBE,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
 Production_Control
  MenuItem
   Caption=Work Plan/Work Order Release Control
   Item=Work Plan/Work Order Release Control
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order Create
   Item=Work Order Create
   List=
   Tool=
   Priv=ORDER_CREATE
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_76762C69469631E5E04400144FA7B7D2,'||chr(64)||'SQLTransaction=INSERT,'||chr(64)||'Tag=CREATE_WO_FROM_MENU))
   Params=
   FKey=
  MenuItem
   Caption=Work Order Requests
   Item=Work Order Requests
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''ORDER_REQUEST_CREATE'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Center Management - WO Operations - In Queue and Active
   Item=Work Center Management - WO Operations - In Queue and Active
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order - Request Part Issue
   Item=Work Order - Request Part Issue
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order - Issue Parts
   Item=Work Order - Issue Parts
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''INVENTORY_CONTROL'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order Operations - Out of Control
   Item=Work Order Operations - Out of Control
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Part Shortage by Work Order
   Item=Part Shortage by Work Order
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Part Shortage by Part
   Item=Part Shortage by Part
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Delivery Package
   Item=Delivery Package
   List=DefinableData.DeliveryPackage
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''DISPATCH_ALL'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Units - Completed, Stopped, Scrapped
   Item=Units - Completed, Stopped, Scrapped
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Issued Tool Status
   Item=Issued Tool Status
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFGSUP'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Management Reports
   Item=Management Reports
   List=
   Tool=
   Priv=DISPATCH_MFG
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_7E158969AD3A46D3E04400144FA7B7D2,'||chr(64)||'SQLTransaction=Update))
   Params=
   FKey=
  MenuItem
   Caption=Data Collection Variables
   Item=Data Collection Variables
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DATCOL_VARIABLE'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=External Data Collections
   Item=External Data Collections
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Assistance Requests - Part Delay, Tool Delay, & Help Hold
   Item=Assistance Requests - Part Delay, Tool Delay, & Help Hold
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''ORDER_HOLD_CREATE'') AND '||chr(64)||'HasPriv(''ORDER_HOLD_CLOSE'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order Operations - Open Over Inspection Holds
   Item=Work Order Operations - Open Over Inspection Holds
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_OVERINSPECTION'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Manufacturing Inspection Orders - In Queue and Active
   Item=Manufacturing Inspection Orders - In Queue and Active
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSP_ORDER'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Orders by End Unit
   Item=Work Orders by End Unit
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order Operations by End Unit
   Item=Work Order Operations by End Unit
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=End Units
   Item=End Units
   List=Production Control
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=PAAR Requests
   Item=PAAR Requests
   List=Production Control
   Tool=Dispatch
   Priv=CAN_VIEW_ALL_PAAR_REQUESTS
   Command=
   Params=
   FKey=
 Operations
  MenuItem
   Caption=Work Packages - All
   Item=Work Packages - All
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Center Operations - Group Jobs
   Item=Work Center Operations - Group Jobs
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Center Production Activity
   Item='||chr(64)||'na
   List=DefinableData.WorkCenProdAct
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''DISPATCH_ALL'') and '||chr(64)||'GetQueryValue(MFI_FND_MiGlobalParamGet)=''Y''
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order Operations - User Work Center
   Item=Work Order Operations - User Work Center
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order Operations by Serial/Lot - User Work Center
   Item=Work Order Operations by Serial/Lot - User Work Center
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order Operations - In Queue and Active
   Item=Work Order Operations - In Queue and Active
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Order Operations by Serial/Lot - In Queue and Active
   Item=Work Order Operations by Serial/Lot - In Queue and Active
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Orders - In Queue and Active
   Item=Work Orders - In Queue and Active
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Orders - Pending
   Item=Work Orders - Pending
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Orders on Split Hold
   Item=Work Orders on Split Hold
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Sign On/Off Activities
   Item=Sign On/Off Activities
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''ORDER_SIGNON'') and '||chr(64)||'GetQueryValue(MFI_FND_LtaEnabledFlag)=''Y''
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Sign On/Off Accounts
   Item=Sign On/Off Accounts
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''ORDER_SIGNON'') and '||chr(64)||'GetQueryValue(MFI_FND_LtaEnabledFlag)=''Y''
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Clock In/Clock Out
   Item=Clock In/Clock Out
   List=Operations
   Tool=Dispatch
   Priv=DISPATCH_MFG
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_495F534A86ED7277E0440003BA041A64,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
  MenuItem
   Caption=Inspection Buyoffs - Manufacturing - Open and Reject
   Item=Inspection Buyoffs - Manufacturing - Open and Reject
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Scan Build Unit For Work Execution
   Item=Scan Build Unit For Work Execution
   List=
   Tool=
   Priv=DISPATCH_MFG
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_13792F73DBF1411E8DDE77FB1CD67F41,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
  MenuItem
   Caption=Shift Notes Summary
   Item=Shift Notes Summary
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Order Hold Summary
   Item=Order Hold Summary
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Grouped Jobs
   Item=Grouped Jobs
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Instructions Read Acknowledgement - Summary
   Item=Instructions Read Acknowledgement - Summary
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Shop Floor Supervisor Home Page
   Item=Shop Floor Supervisor
   List=DefinableData.ShopFloorSupervisor
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''SHOPFLOOR_SUPERVISOR_HOMEPAGE'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Shop Floor User Home Page
   Item=Shop Floor User
   List=DefinableData.ShopFloorUser
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''SHOPFLOOR_USER_HOMEPAGE'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Overhaul Work Orders - All Orders
   Item=Overhaul Work Orders - All Orders
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Overhaul Work Orders - Shop Orders
   Item=Overhaul Work Orders - Shop Orders
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Repair Work Orders - All';
p2 clob :=' Orders
   Item=Repair Work Orders - All Orders
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Repair Work Orders - Shop Orders
   Item=Repair Work Orders - Shop Orders
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Repair Work Order Interface Data
   Item=Repair Work Order Interface Data
   List=Operations
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Repair Work Orders - Delivery
   Item=Repair Work Orders - Delivery
   List=Operations
   Tool=Dispatch
   Priv=
   Command=
   Params=
   FKey=
  MenuItem
   Caption=General Procedures
   Item=General Procedures
   List=Operations
   Tool=Dispatch
   Priv=PW_GENERAL_PROCEDURES
   Command=
   Params=
   FKey=
 Archiving
  MenuItem
   Caption=Completed Work Orders - Create Archive Batch
   Item=Completed Work Orders - Create Archive Batch
   List=Archiving
   Tool=Dispatch
   Priv=ORDER_ARCHIVE
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Archive Batch Management
   Item=Archive Batch Management
   List=Archiving
   Tool=Dispatch
   Priv=
   Command=
   Params=
   FKey=
 Quality_Assurance
  MenuItem
   Caption=Inspection Buyoffs - Inspection - Open and Reject
   Item=Inspection Buyoffs - Inspection - Open and Reject
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSP_BUYOFFS'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Discrepancy Item Tasks - In Process
   Item=Discrepancy Item Tasks - In Process
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Discrepancy Item Tasks by Rejecting Location
   Item=Discrepancy Item Tasks by Rejecting Location
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Discrepancy Item Tasks by Responsible Location
   Item=Discrepancy Item Tasks by Responsible Location
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Discrepancy Records - In Process
   Item=Discrepancy Records - In Process
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Corrective Action Requests - In Queue
   Item=Corrective Action Requests - In Queue
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Corrective Action Tasks - In Process
   Item=Corrective Action Tasks - In Process
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Corrective Action - Action Items - In Process
   Item=Corrective Action - Action Items - In Process
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Quality Dashboards and Reports
   Item=Quality Dashboards and Reports
   List=DefinableData.MgmtReportPanel
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''MES_REPORTS'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Current Discrepancies on Item
   Item=Current Discrepancies on Item
   List=DefinableData.RelatedDisc
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''DISPATCH_ALL'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=User Findings - In Process and Complete
   Item=User Findings - In Process and Complete
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_USER_FINDING'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=PO/PO Line Items
   Item=PO/PO Line Items
   List=Quality Assurance
   Tool=Dispatch
   Priv=SFSQA_PO_DISPATCH
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Inspection Orders - In Queue and Active
   Item=Inspection Orders - In Queue and Active
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSP_ORDER'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Inspection Orders - Receiving - In Queue and Active
   Item=Inspection Orders - Receiving - In Queue and Active
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSP_ORDER'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Inspection Orders - Steps - In Queue and Active
   Item=Inspection Orders - Steps - In Queue and Active
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSP_ORDER'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Units - Completed, Stopped, Scrapped, Cancelled, and Returned
   Item=Units - Completed, Stopped, Scrapped, Cancelled, and Returned
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSP_ORDER'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Open Requests for Lien Authorization
   Item=Open Requests for Lien Authorization
   List=Quality Assurance
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSP_ORDER'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Supplier Portal
   Item=Supplier Portal
   List=
   Tool=
   Priv=SUPPLIER_PORTAL
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_6F9A6399F2614A3B93B6745B7924FEFF,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
  MenuItem
   Caption=Quality User Home Page
   Item=Quality User
   List=DefinableData.QualityUser
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''QUALITY_HOMEPAGE'')
   Command=
   Params=
   FKey=
 Engineering
  MenuItem
   Caption=Discrepancy Disposition Tasks - In Queue and Active
   Item=Discrepancy Disposition Tasks - In Queue and Active
   List=Engineering
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_ENG'')
   Command=
   Params=
   FKey=
 Supplier_Management
  MenuItem
   Caption=Supplier Management Dashboard
   Item=Supplier Management Dashboard
   List=
   Tool=
   Priv=DISPATCH_SUPP
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_8E9FDBAEBB7D4EC68758507BE16C986C,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
 Customer
  MenuItem
   Caption=Inspection Buyoffs - Customer - Open and Reject
   Item=Inspection Buyoffs - Customer - Open and Reject
   List=Customer
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_CUST'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Discrepancy Disposition Tasks - In Queue and Active
   Item=Discrepancy Disposition Tasks - In Queue and Active
   List=Customer
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_CUST'')
   Command=
   Params=
   FKey=
 Tooling_Management
  MenuItem
   Caption=Tool Kitting
   Item=Tool Kitting
   List=Tooling Management
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_TOOL'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Tool Serials Missing
   Item=Tool Serials Missing
   List=Tooling Management
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_TOOL'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Tool Serials In Repair
   Item=Tool Serials In Repair
   List=Tooling Management
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_TOOL'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Tool Serials Cycle Time For Service
   Item=Tool Serials Cycle Time For Service
   List=Tooling Management
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_TOOL'')
   Command=
   Params=
   FKey=
 Communications
  MenuItem
   Caption=Inbox
   Item=Inbox
   List=Communications
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_COMM'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Drafts
   Item=Drafts
   List=Communications
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_COMM'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Sent
   Item=Sent
   List=Communications
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_COMM'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Completed
   Item=Completed
   List=Communications
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_COMM'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Error Log
   Item=Error Log
   List=Communications
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_COMM'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Asynchronous Transactions
   Item=Asynchronous Transactions
   List=Communications
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_COMM'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=BIS Transaction Logs
   Item=BIS Transaction Logs
   List=Communications
   Tool=Dispatch
   Priv='||chr(64)||'Lic_ENABLE_XML=''YES'' and '||chr(64)||'hasPriv(''DISPATCH_COMM'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=BIS Reply Logs
   Item=BIS Reply Logs
   List=Communications
   Tool=Dispatch
   Priv='||chr(64)||'Lic_ENABLE_XML=''YES'' and '||chr(64)||'hasPriv(''DISPATCH_COMM'')
   Command=
   Params=
   FKey=
 Search_Lists
  MenuItem
   Caption=Find Work Order by Part/Serial/Lot
   Item=Find Work Order by Part/Serial/Lot
   List=
   Tool=
   Priv=DISPATCH_MFG
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=MFI_74C95E9B62924DE0E04400144FA7B7D2,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
  MenuItem
   Caption=ROM Report
   Item=AAAAA ROM Report
   List=
   Tool=
   Priv=
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=PWUST_8473705FDF8015ECE05387971F0A623A,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
  MenuItem
   Caption=Find Process Plan
   Item=Find Process Plan
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Work Order
   Item=Find Work Order
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Work Order by Serial/Lot
   Item=Find Work Order by Serial/Lot
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_MFG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Discrepancy Record
   Item=Find Discrepancy Record
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Discrepancy Item
   Item=Find Discrepancy Item
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Discrepancy Item by Serial/Lot
   Item=Find Discrepancy Item by Serial/Lot
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Corrective Action
   Item=Find Corrective Action
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Corrective Action - Action Items
   Item=Find Corrective Action - Action Items
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Corrective Action Request
   Item=Find Corrective Action Request
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_QA'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Communications
   Item=Find Communications
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Inspection Definition
   Item=Find Inspection Definition
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Inspection Plan
   Item=Find Inspection Plan
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Inspection Order
   Item=Find Inspection Order
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSP_ORDER'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Inspection Order by Serial/Lot
   Item=Find Inspection Order by Serial/Lot
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_INSP_ORDER'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Audit
   Item=Find Audit
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_AUDIT'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Audit Plan
   Item=Find Audit Plan
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_PRPLG'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find User Findings
   Item=Find User Findings
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''DISPATCH_USER_FINDING'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find Changes
   Item=Find Changes
   List=Search Lists
   Tool=Dispatch
   Priv='||chr(64)||'HasPriv(''CHANGE_REQUEST_VIEW'')
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Find MRO Plan by Task Group Item
   Item=Find MRO Plan by Task Group Item
   List=Search Lists
   Tool=Dispatch
   Priv=DISPATCH_PRPLG
   Command=
   Params=
   FKey=
 PAAR_Approval
  MenuItem
   Caption=Plan - Requests for Approval
   Item=Plan - Requests for Approval
   List=PAAR Approval
   Tool=Dispatch
   Priv=CAN_VIEW_PAAR_APPROVAL
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Order - Requests for Approval
   Item=Order - Requests for Approval
   List=PAAR Approval
   Tool=Dispatch
   Priv=CAN_VIEW_PAAR_APPROVAL
   Command=
   Params=
   FKey=
admin
 Manufacturing_Intelligence
  MenuItem
   Caption=Schedule Definitions
   Item=Schedule Definitions
   List=Manufacturing Intelligence
   Tool=SysAdmin
   Priv='||chr(64)||'HasPriv(''MI_CONFIG'') and '||chr(64)||'GetQueryValue(MFI_FND_MiGlobalParamGet)=''Y''
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Operations Targets, Scores and Ratings
   Item=Operations Targets, Scores and Ratings
   List=DefinableData.TargetsScoresAndRatings
   Tool=Tools
   Priv='||chr(64)||'HasPriv(''MI_CONFIG'') and '||chr(64)||'GetQueryValue(MFI_FND_MiGlobalParamGet)=''Y''
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Center Schedules
   Item=Work Center Schedules
   List=Manufacturing Intelligence
   Tool=SysAdmin
   Priv='||chr(64)||'HasPriv(''MI_CONFIG'') and '||chr(64)||'GetQueryValue(MFI_FND_MiGlobalParamGet)=''Y''
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Solumina Instances
   Item=Solumina Instances
   List=Manufacturing Intelligence
   Tool=SysAdmin
   Priv='||chr(64)||'HasPriv(''MI_CONFIG'') and '||chr(64)||'GetQueryValue(MFI_FND_MiGlobalParamGet)=''Y''
   Command=
   Params=
   FKey=
 Planning_and_Operations
  MenuItem
   Caption=Configuration
   Item=Configuration
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Customers
   Item=Customers
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Effectivity/Unit Types
   Item=Effectivity/Unit Types
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Hold Types
   Item=Hold Types
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Item Types and Subtypes
   Item=Item Types and Subtypes
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Models
   Item=Models
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Note Types
   Item=Note Types
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Operation Types
   Item=Operation Types
   List=Planning and Operations
   Tool=SysAdmin
   Priv=LOOKUP_OPERTYPE_UPDATE
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Overhaul Work Scope Codes
   Item=Overhaul Work Scope Codes
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Part Disposition Types
   Item=Part Disposition Types
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Process Types
   Item=Process Types
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Test Category Types
   Item=Test Category Types
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=UID Entry
   Item=UID Entry
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Unit Conditions
   Item=Unit Conditions
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Units of Measure
   Item=Units of Measure
   List=Planning and Operations
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
 Discrepancy
  MenuItem
   Caption=Component Disposition Types
   Item=Component Disposition Types
   List=Discrepancy
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Discrepancy Categories
   Item=Discrepancy Categories
   List=Discrepancy
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Discrepancy Causes
   Item=Discrepancy Causes
   List=Discrepancy
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Discrepancy Defects
   Item=Discrepancy Defects
   List=Discrepancy
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Discrepancy Symptoms
   Item=Discrepancy Symptoms
   List=Discrepancy
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Disposition Document Types
   Item=Disposition Document Types
   List=Discrepancy
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Disposition Types
   Item=Disposition Types
   List=Discrepancy
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
 Organization_Management
  MenuItem
   Caption=Companies
   Item=Companies
   List=Organization Management
   Tool=SysAdmin
   Priv=LOOKUP_COMPANY_UPDATE
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Engineering Groups
   Item=Engineering Groups
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Issuing Agency
   Item=Issuing Agency
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Labor and Time Accounts
   Item=Labor and Time Accounts
   List=Organization Management
   Tool=SysAdmin
   Priv='||chr(64)||'HasPriv(''SYSADMIN_VIEW'') and '||chr(64)||'GetQueryValue(MFI_FND_LtaEnabledFlag)=''Y''
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Labor Sign On Types
   Item=Labor Sign On Types
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Labor Type
   Item=Labor Type
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Planning Groups
   Item=Planning Groups
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Programs
   Item=Programs
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Projects
   Item=Pr';
p3 clob :='ojects
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Package Activity
   Item=Package Activity
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Center Types
   Item=Work Center Types
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Work Loc, Dept, Center
   Item=Work Loc, Dept, Center
   List=Organization Management
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
 Corrective_Action
  MenuItem
   Caption=Action Item Types
   Item=Action Item Types
   List=Corrective Action
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Corrective Action Codes
   Item=Corrective Action Codes
   List=Corrective Action
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Request Document Types
   Item=Request Document Types
   List=Corrective Action
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Request Types
   Item=Request Types
   List=Corrective Action
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Risk Detectability Rating
   Item=Risk Detectability Rating
   List=Corrective Action
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Risk Likelihood Rating
   Item=Risk Likelihood Rating
   List=Corrective Action
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Risk Severity Rating
   Item=Risk Severity Rating
   List=Corrective Action
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Source Groups
   Item=Source Groups
   List=Corrective Action
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
 Supplier_Quality_Maintenance
  MenuItem
   Caption=Charge Codes
   Item=Charge Codes
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Commodities
   Item=Commodities
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Contact Types
   Item=Contact Types
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Inspection Item Classes
   Item=Inspection Item Classes
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Inspection Item Names
   Item=Inspection Item Names
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Inspection Item Types
   Item=Inspection Item Types
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Inspection Methods
   Item=Inspection Methods
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Inspection Steps
   Item=Inspection Steps
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Requirement Group Types
   Item=Requirement Group Types
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Severity States
   Item=Severity States
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Supplier Categories
   Item=Supplier Categories
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Validation Result Types
   Item=Validation Result Types
   List=Supplier Quality Maintenance
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
 Sampling
  MenuItem
   Caption=Attribute Sampling Plans
   Item=Attribute Sampling Plans
   List=Sampling
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Control Plans
   Item=Control Plans
   List=Sampling
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Severity Switching Rules
   Item=Severity Switching Rules
   List=Sampling
   Tool=SysAdmin
   Priv=SYSADMIN_VIEW
   Command=
   Params=
   FKey=
 Solumina_Configuration_Tables
  MenuItem
   Caption=Communication Queues
   Item=Communication Queues
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Document Types and Subtypes
   Item=Document Types and Subtypes
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Global Parameters
   Item=Global Parameters
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Messages
   Item=Messages
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Number Generator
   Item=Number Generator
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Queue Types
   Item=Queue Types
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Reporting
   Item=Reporting
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Report Types
   Item=Report Types
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Report Maintenance
   Item=Report Maintenance
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Language Support
   Item=Language Support
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Alerts
   Item=Alerts
   List=Solumina Configuration Tables
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Encrypt/Decrypt - DXC
   Item=Encrypt/Decrypt - DXC
   List=
   Tool=
   Priv=DXC_DEVELOPER
   Command=CommandEvent(ExecTransaction('||chr(64)||'Udv_Id=PWUST_DA163DB0357300FCE043AC13468700FC,'||chr(64)||'SQLTransaction=INSERT))
   Params=
   FKey=
 User_Management
  MenuItem
   Caption=Certs
   Item=Certs
   List=User Management
   Tool=SysAdmin
   Priv=USER_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Privs
   Item=Privs
   List=User Management
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Roles
   Item=Roles
   List=User Management
   Tool=SysAdmin
   Priv=CONFIG_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Security Groups
   Item=Security Groups
   List=User Management
   Tool=SysAdmin
   Priv=PW_SECURITY_MENU
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Security Group Types
   Item=Security Group Types
   List=User Management
   Tool=SysAdmin
   Priv=USER_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Skill Categories
   Item=Skill Categories
   List=User Management
   Tool=SysAdmin
   Priv=USER_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Skill Types
   Item=Skill Types
   List=User Management
   Tool=SysAdmin
   Priv=USER_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=User Classes
   Item=User Classes
   List=User Management
   Tool=SysAdmin
   Priv=USER_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Users
   Item=Users
   List=User Management
   Tool=SysAdmin
   Priv=USER_VIEW
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Users Security Groups
   Item=Users Security Groups
   List=User Management
   Tool=SysAdmin
   Priv='||chr(64)||'HasPriv(''PW_SECURITY_MENU'') and '||chr(64)||'GetQueryValue(SECURITYGROUPFLAGGET)=''Y''
   Command=
   Params=
   FKey=
  MenuItem
   Caption=Levels
   Item=Levels
   List=User Management
   Tool=SysAdmin
   Priv=USER_VIEW
   Command=
   Params=
   FKey=
';
v_iclob clob := p1||p2||p3;
cdp1 clob :='789CED5D4B73DB38B6DEDFAAF90F5C4D3A55C944B2DE0B5785E2C3D66D495444C9E9AC54B404DB9CD0A4868F74EBFEFA0148490440802245DA4457DD6C62E11C90DF87E739C001F80ADCE81FFF232D7C6F0B8260B3702CD7B5DD679824CDA068128257F4';
cdp2 clob :='B7A458FBD0F6DCDB257080150069616D7F5ACF20903E4BB2E3C42A48374F3EB583F0F6F822097F91B4F23CE756B583BD156E5FE294856FFFBAFD7A6F05E88FDF3EA8137321AF94FBCD62B998DE7DF89840F25E5F2D77779B64B07CEB3548FED67F07875B';
cdp3 clob :='2E031A81B4B2829F08E7C495BE452002127CA8246F43FB17487995CE2508DBEF9EFF5332FC1DF0613D84C0B7502A0EFE0831655A2A87202CC7B6E30492F724CD2C88D8B6B006C91609821B6A8658D1A73F05C1875E86E14B7FD6816F6518D30F1F2579AE';
cdp4 clob :='4AA914256E1E26DAF71A3AF92EF2015EBA649A20453CB3B62FB68BE32452044139B51F7DCB3FC00140B71D1C2C4B5001F3E2FBDA5C6DCC955A431B48A19921CC6EF93BC9D81FC73316018E96703570C6B9027F85B944CE0AC27198384E14844929335B53';
cdp5 clob :='5641DC56A55AA1051FE73860CBE593AF2C0837986D6FF9408A4D0004435A825F764072BAA4147349B1A798CF2D499E4E0994C7FFB55FC00D7FD3FE02DB956FB9811597CF6F5FD7BB5F9BC9EE76A64F36CA68D0EDB4B55EABD396BB6D5D19F7C7FDB63E54';
cdp6 clob :='E5FE8DD6EEA9BD4F5FCD6F532CF3ED646E6ACBD5C78F25CB81AA23E9C18286C32331EEE5AB54E96FB088D014282F27F278AA55ACD017CB4D8C7096A5C7955680AFDCCBF33B6DB3D4BEAD35D82EABB7C9B34381C3205D0D5C124357C193EDA2DA40B5F4AF';
cdp7 clob :='A35E96476ACEF0C63CD852A185429928B55344661F1A20C288B205F1C43A86F0984E25A4E8BDD03130233845C24EEFF9D2BDF78ABCBD67DC57CA68F1AAE6A84929E657CE622ACFE7DA7263AE17DAF261621ACB7A38F1895C405F02F3BD31D316F25DC55E';
cdp8 clob :='BD90E5251C74FF138180F01E32C9A5DB0C39D957C1789EEFE620FC13CE14294CA6A434D24AA3A20FA02F98CC5D4828CD3D6C50E408B9D31A3E156F50456F262B6DB6991B5425179AE592D9BC37180D3AE371AFAF6BBA36BCE96BAD5E6708D3DA7A4BEECA';
cdp9 clob :='7AA7AE796E66B991E510AE479A50C926D9CCE4F95A9E9A358C8E0926343FFDB277C0CFA0A52475C086CDDF78A80FFAEAB00759D8696A2D90573F165A1DC52D47E18BE7DBE18186CD1254432EAF57F7C672B2FA7105F8891BEC13D3EBEDD72C738DD9EC48';
cdp10 clob :='2960474F4BABE4B2E73519E33263642C69ACD4E06F42BB62EFD8C4984126D50415D6CAA2FA5A1B9CB6B1669D2CC1E28B6F7CB94834AE5B8A15A2B9E4AF760A01F1ED561F84A057C55FBD9AC0FFBBAC6FE9B2566F5795BD56E413D93E7885F3B174E77BD1';
cdp11 clob :='3EC06B8429136944BDDE5DB9DA32CB7163B8E0E5686787396625949E3A2D6956660531F85810BCFF18740454CA4C2A99E77A7EF25A9DACDE841F5AD67540789914A1D83C13046901DC1D31CE3225CD6395903710D1FBE9979A55F14C0230D4EDB8C0C949';
cdp12 clob :='3AC6C96B60057308C04DF17C1FC4A51D173A349F38864A71751159C51C4A52E3E51180DF120491136628E0C98DA23CCEE0E7E99398D7D354EE4A440C61632C556DB951969ABCD208348537D786EAB033526E6EC6DA4DAFDB96D5E1B837D48643459595C1';
cdp13 clob :='70A08CEA5A8B882979BEB4F0FC10F70E19E905B61211F9EBF8B6FAF2F846EE2937CAE8A6ABE8FAB8ADAA6A571BDEB4FAA3C178AC95E70B1BFE2E4AD6A414CF0D7DCFE116C279F3F40B166E75F20EB0CC58485611ED93D5778441C89A0A3B4B5A32452623';
cdp14 clob :='E25675F5863DE80FFA374A7FD4ED8FFA68F7586B75BBAD56BBDBD5E5C178A0DE702AFAD3D795F57C9BBC78F3DDD8E84B63B69969F375E9164F541ABD6BC31356AACAA4D04EEE5EC2A1860A556051439C33CBB59E1397E9B3F4DD48E3652E1A34959F524F';
cdp15 clob :='039FE9F535EFCFA76A9362F777120411BBB1731545E414A393A8F0C45C8D4A2C26F3076DBE32963F368A315F2D8D8A0E3F069468554614A2E547F6F85A4C5F90CA4A965A5ED054F90CA4C783945248695D5212950B4AC86171160B825F050E1C9EFCC369';
cdp16 clob :='FF2885CE943096FB4E7AB85A99E5BE2AE8D76EE2299F9CADDD27C90CBDFD3EFE63EB5BE8AF9451616D41EA261EA576F11B33CB951C596DC8CDF5A2F2F6F169865C82BD478CC51CD9658B1902BBD28CD2DABDE1A83F92D58EDCEDAB9D8B66D47ABF83D65D';
cdp17 clob :='73C177D755609DE177DA5FD0D0712D27678F265F45905E2407010462B95B70B651D1DA1A1A98E1D0651D3E259DE8F8F73FA57BE0ECA57BCFC1068E6A8FA8C118BE37A6EAD912A6B647708DA961D6612AB3AC893D7025038EF3F82E2EA2C836B1CAE4ADA7';
cdp18 clob :='9D180FDA12ED5868CA6A62CCAB87BE3CC18120F2D1DA7166DBFA92A3707DF67A8A021543B2A652575B0890E9A241AC680A655578564190DECF6E9317D8E4A90AC2EB84091F8D89244170960EF72C815791E7F16E30B22337E889A74503B318E2B496F39B';
cdp19 clob :='0F2FF48A278CA9900F7FF7267F5CA720C6E1788B58FA5FEF911EB30B280BC4096B24F1D069878794CF57D74AF1928E0A7A067C04CA0EF3A50C8A8625A0B1FAEB1D08E1C8ED1F1E2C2702BF21D3529FAB9B997DE7788F961313822A1F6F3FFCF8F016D3F0';
cdp20 clob :='3A802958615C9E7C993944A84DD6486BC691555FA65E7805D742F985645E64E1B144262139D2B57305E3A28F10867F899AFD5BD427335C812F1608379C2BCCBD6387946397AFD2307ED37E862E83FBC5787A3A4D7436BE909023BF0A79E2489A93BB39F2';
cdp21 clob :='9FF2A6BB696869F1BCBAD31DEBB9F2644712D97A911B726962D2BF1749C5F1B63F6117FF92FC6144F87E395B568260F515B2EEA8A7F73A5D79D8D7D4C1CD6090AC90B53A63B9D56DCBFDEE1B44F38FA383F7F484460DD2633EAE18A0BA59827F832D5652';
cdp22 clob :='57E76EBA2F6F2D84D87612174DD2BDA369728E8FC21A7C31DD375C2D6D7706A31B7DD051C77ABBDB6E6B4355D506037DDC56D4FE40EFB6EB6A0BE68BFD144A732F84EE931941703E66C9F3840DD7646295A039220B99236B1871ECC9811DE5F865521B46';
cdp23 clob :='097B76E847C750B325B09005F4D3F5FE84E3EF79A33D53E06533353D0ABC787B49773CEFD22164A662CA80F46B9172AC5BEA24B2796F2CF4A961E067916B3AE28BA18F7DB07C8248E522B5B35251526BB3B613CB68E5FAC58A1C89B47465C7C91C732AA6';
cdp24 clob :='DAF408C6061957494142B46EC38C96606FD97E81FA29A228221766DD14D2148D0DB47243E0436B0DC49B86396C589AA2B141257E8A7FB85031845A091E95667EE0C2573812EB42318EAC04B2C5F7CD9D36D796C991734553D74BADE0CABBEC6F5FA0BF9A';
cdp25 clob :='73ECF91CA34115E329B4387E0090C667488923553657123C8DA3E1B14D1C4779A9DC4F1EB42A954280C002095316B91AC5111703F92DB21C3B3C6CE420887CB4C59D67A1657D2F2CB1ACDB76316B4CF5884F22F095DD0C1DAF7F18BA6E560C95B283AD0F';
cdp26 clob :='BBB6BB3D24A7D5F22EA22CAA5C0BC76FF29B307B3C1C6B03B9D5536F4B5D3E573A97F05C833D1CF26C687C9664CBC9271CDF25D87AFEAE4893E56A0AC1297B82070BCB39ADA2E3B342517541D9E50D3485B505E5F6B9C8C1ADF2D984607B7ABF6A052F8F';
cdp27 clob :='9E853A5432C551F19005F418CEF1ECF9354C5416960BB04809AE7B3CD3CCCD525B18CB55C5A95089607DB8A1940E1936887750CE9A49B55D54635F1F006DB51DCA5380528DB1C468A9A2CC09D032196A698EF19A863E99AB9379D5A81CE3CBC280A05C90';
cdp28 clob :='F49F941453521EBDA99BDFE4CDC2D89CC057415B3A00AF74C85D2D46660D11772CE0702A06B1AD7F0DEB2299852F013304FBABEAFC5246A19817399DF0096A43848E83FE4CA68830F2DDB2871CF21F2354A9C47EE1D95C7BF27C080FA61CEF73FB3FCA48';
cdp29 clob :='2FAC2D14C7D3B55E9953C52C0177DBCF5C2F16D3099C21D0FC2E5F7949715F1FC9FDCE68A4DFF4DB5DB9331E75C6FD41B7371E8C6EBABAA6EB75EDFC9D8A9EB73F80CB7986C251A7E0D6C0B7B53C45B7F595DD13D0DC673819023F6FB10AF79C50CBF102';
cdp30 clob :='9BB6C4F903D7B599E312A1D09568BC5A711BE2D40C37E452547E5BC6CEF89CCD5A46CBE6AA5DDEDE460DFEBA563ED446BA3A96B5F178A07635A53F1CF486BDD660ACB5FBCA68D857CAB772250A42D88AFD22F35ABA0276CA557AE9EC42C6B8EC704825DA';
cdp31 clob :='85B2362BDE09D15C6F7807CAE8A9B0B315E90AF199A0DFED3024C2E532A931F2E363E9C5DEE21C92EF9154A9B6E428611C5F1948333B08B2B0595221E14F9015803661380448B9901494C3D601D2CA8653230A0032D1CEFE1670F8E428BF3F39A410B9F6';
cdp32 clob :='F6C2D18A89FBE8FD858F73A79F4957CE3CA44C873666B38A63986F3DE12B34D86F11E099C47E9149EC0D350CEDEC77A4F8C82411406ABE8F7C030F1BE0C8241140CAC1C1DDBEF89EEB4581845923F86D50B92A2290184F4C1C182A600C3F575A16FAD4DE';
cdp33 clob :='428B161D7ADEFC319BDE7EF8A1991F92E0DE97372205E70FE7C0A043A58B42C40496BF7DD92038FC21192D66E21118C7FB2ABEA4A72E52AE0575DFF24C7F5719F5B4D1B87F039D53556B15BC1AA9B4AFBA3466C7A578ACE3A17F1225E1722DCF2FB9617D';
cdp34 clob :='D81D7406AD9EAEEAC356BBA729F80DEBFD9B8E5C17C3B82EF1FB56A96ACE8862A6499392CE4DEA7D2FE9A2DA1FB761D682B772E410A3B714EC54ACFED43893EC5631C581A350157DD50DB90C3672BF8A2F1612786E2B2AA02C04A9CCD62EC5832D17133A';
cdp35 clob :='B52B7D89094B5D5062C735F58B8C703D41A8644C2F1C7F4628CACC862DFBC5ABDE36A36BF0750464C1302B58520191B3EC0BA6B82AF6BAF693980873278B02CA4291A3EE1BA6D2AA42ADE15EE41411ABE1530251DA3C11D04241CECAAAA2AE2F9A2519CB';
cdp36 clob :='93EFABD0233C967A25E0DA3F3F12039B2D8DE4BB17B0A7A1BD8DE37D2B0C7BF49266295AD77C2331BE5947DEEF7DEF97C5BF143A86F899DC7CC7331DC38B2E6925D700A1DB8188E44B3700251817E8D377D4D677C9CAA1AEBBE5F1B8ACF66E44ACDDAB8D';
cdp37 clob :='C611E214F4061D9E711CFB19E445F49BDB17B08B1C80990FF8C17B9E34A6465F7246BE2EE1691E02F9882E1B8B3841D7E5EA93BB77BE5907BB4F6465F9CF200C505C8CE78363CCA51592A35F097D468CC2314B92417677B8FA8558CD86CA07BF69E9D402';
cdp38 clob :='38F74591E2BF75AB303D2782902C6409C5173DE2FD802DFB9BF03D7D026703B536056E3C533CF7C97E8E7C2AB22A9B9C0C70A70FEC20F0AC636359F2E60F5356679364BCAB5267A7DD7E3C4E9C486A1CA1F6F4048E37947D89AF72A0BE4A9A236F1C7B7C';
cdp39 clob :='8B0185974A6B1C63BCAE15E389DF6E468F2109384FA171F4336F0788EF17A7BF1BC786AEDDA06B9F4A6B1CE3F975345096E05AB453C3F87D0D1DD785B644DFF9DDAC172AFD2D8FB2B08923F5D032D8A3CFA0EC0802B92A8D177C72F1311E9C45967F8EBC';
cdp40 clob :='79ECC7AD2C1A7226B971A4F12773152B04CF9E9FF99E354FD838EAF544953437C48FC39349CD2374E36F80B93BDADF610984409B7C76185841E403126E46F28E78B10D26BE89E6BDEE3DF778048C375C5C524ADC2AF26D6F5EEEF8F6D9B19F1197EFE5C8';
cdp41 clob :='05C01B057CAC51200A4EE82A43F397031417368ED43CBCEEA163C1814A489BC27AEE38AAB78DE2787AAA9B5DD0691C770EDC774369F8CF967B3C275424C41B0D5D30033D9AA549C9752AD8539921B85CB35331660B79FEA306AB133B2892F98C3447762D';
cdp42 clob :='F6FADCBB20881026192DA8E0B7BE65D31BC73AB51E3D3F9E70E328ECEC35A6790AD7A2C72E3EC379BCEBBDA609AFE325AD7427E60905A92E0488067B4E6B1CE3D98CA3FB2B4BD03C5ADF7B46BA184C3C45047CFF26AD0D22A5797CC97724189F34604A1A';
cdp43 clob :='C78B6F0450BD9E2D1203F1D4DB7E82B6E53EFCC4FEAA005BFE8ED8D398AA4D1AEFC63E1F90C68ED135C0161D63D159F1746FBE569F8914A396BCF2141AC47DFAEA29CFA8CE910B809A03560C8C76F01376B410BED97AB4E3C3DEC9E628863757A569EC53';
cdp44 clob :='FB2770EC17CFDBB181B3E54DA336C12F68E8F30A9B256D10B1E945FE1664EC9F6CF2BB603C1F833F5D9938B36C3843B8B997262A2F28022033DAD1A94910CFE950FCE9F603EA05EF304ABFBE7A3BEA331374A22850DDD0DA6686B86CB21870B100CB7852';
cdp45 clob :='561C2B20D6E8F214C4A430B75EF308A46231E1530D872F160EFE0C842FDE8E8D1D9709073CBE8E89093B9588011A594AB69F5C4B92845B324C299E821814CE5339FA3A3411CEC4100802F90480B5E1C2138A01FD01BE769778864B10444E6662CA53786F';
cdp46 clob :='0AA6F5BA77F2EE3392C3D0B71FA3109C55E33032DCC1CCD348F8602F79176BC0F71C1A6536F9FDA1A5DDED4F3BDCBEA0925A922195B91A6F09F814D8B82182FB36ABF3D7C8B946E2F9D05472190F652D32A50991532825F146297D238F5D1224597DBF89';
cdp47 clob :='70D33941639794846092C4832619A0878C0741B24542A09E8120B088531A448A1018E7D1EB231C8693EF23841EB640C894088139B9128B9A72E844219026D725902B0F44924028B3461F952A1256DA5CC011D33221704F2DF73942FB1AC8F2212ED7604A';
cdp48 clob :='84C02C3B80B8B81BFB2D043ECDDDFA877DF84505F1FFE8B3337F28295CBE987F4DCB1FCA46D51EB4298A8025A095B8C74495DBFD8E3A6E757A834EABA52B5AABDB919576A7DB1F0ED0EFF2F798A0E388850234C8FA52C8EA8A0F3516DBC9894F2B56DF83';
cdp49 clob :='B47F111B90A79F65D1D435747884219AFE6C088F09B6516C0967567C1982B21817DF37A6A6AC97E812D899365FD707949E27B8D2461A9DF9D3761CB64BCD923488912E462AB11164F1FB32ABB599D4C6B051A09A462371BB305F5C166D1AF544776976E0';
cdp50 clob :='D349E56E69AC17FA54BEBBD356D5C39EA0D38E1FDEC17EBF6DE9FF17AB37BF1B';
v_idata_clob clob;
v_ctxt_blob blob;
v_full_ctxt_blob blob;
v_ctkey_blob blob;
cdk1 clob :='3343334139454438354237314331434335423032463941413032353537353943';
begin
 begin
  dbms_lob.createtemporary(v_ctxt_blob, false);
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp1));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp2));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp3));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp4));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp5));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp6));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp7));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp8));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp9));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp10));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp11));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp12));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp13));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp14));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp15));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp16));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp17));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp18));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp19));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp20));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp21));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp22));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp23));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp24));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp25));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp26));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp27));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp28));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp29));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp30));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp31));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp32));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp33));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp34));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp35));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp36));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp37));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp38));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp39));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp40));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp41));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp42));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp43));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp44));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp45));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp46));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp47));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp48));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp49));
  dbms_lob.append(v_ctxt_blob, hextoraw(cdp50));
  v_full_ctxt_blob := v_ctxt_blob;
  dbms_lob.freetemporary(v_ctxt_blob);
 end;
v_ctkey_blob := hextoraw(cdk1);
begin
insert into sfcore_user_type_def (user_type,updt_userid,
                      time_stamp)
    values(v_user_type,user,sysdate);
commit;
exception when dup_val_on_index then   null; 
end;
begin
insert into SFCORE_INPLACE_CFG_DATA (USER_TYPE,CFG_TYPE,description,context_text,context_data,CONTEXT_DATA_KEY,updt_userid,time_stamp)
    values(v_user_type,v_cfg_type,v_desc,v_iclob,v_full_ctxt_blob,v_ctkey_blob,user,sysdate);
commit;
exception when dup_val_on_index then
 v_sql_code := 'update SFCORE_INPLACE_CFG_DATA';
  update SFCORE_INPLACE_CFG_DATA
  set context_text=v_iclob,time_stamp=sysdate,updt_userid=user,description=v_desc,
  context_data = v_full_ctxt_blob, CONTEXT_DATA_KEY = v_ctkey_blob
  where user_type=v_user_type and cfg_type=v_cfg_type;
  commit;
end;
exception when others then  
  v_error_code := SQLCODE;  v_error_msg := SQLERRM; 
 SFFND_SHOW_EXECUTION_MESSAGE(v_action,v_calling_object,v_error_code,v_error_msg,v_sql_code);
end;
/

set define on

