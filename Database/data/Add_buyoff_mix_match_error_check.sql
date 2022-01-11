declare
V_DUMMY VARCHAR2(10);
begin
      Select 1 into V_DUMMY from PWUST_GLOBAL_CONFIG TT WHERE TT.PARAMETER_NAME = 'BUYOFF_MIX_MATCH_CHECK_ENABLED';
Exception 
      when no_data_found then
        INSERT INTO PWUST_GLOBAL_CONFIG    (PARAMETER_NAME, 
                                            PARAMETER_VALUE, 
                                            PARAMETER_DESC, 
                                            UPDT_USERID, 
                                            TIME_STAMP, 
                                            ACTION)
          VALUES  ('BUYOFF_MIX_MATCH_CHECK_ENABLED',
                   'Y',  
                   'Error check for buyoff value of NAND and Accept uniqueness in one grid',  
                   'SFMFG',  
                    SYSDATE, 
                   'INSERTED');
end;
/
commit;
