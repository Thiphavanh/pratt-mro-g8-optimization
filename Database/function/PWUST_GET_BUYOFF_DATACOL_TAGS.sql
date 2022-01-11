CREATE OR REPLACE FUNCTION PWUST_GET_BUYOFF_DATACOL_TAGS(VI_TEXT CLOB)
  RETURN CLOB AS

  -- *********************************************************************************
  -- Created By:  Raeann Thorpe - SMRO_RPT_201 - External Delivery Report
  -- Description: Extract and Return only the Data Collections and Buyoffs from the text fields
  -- 2020-09-03  BPolak  Changed V_RETURN_TEXT to a clob in order to prevent an overflow issue
  -- 2021-05-06  BPolak  Rewrote function to preserve the order of the tags 
  -- *********************************************************************************
  --
  --
  V_TEXT CLOB;
  V_RETURN_TEXT CLOB;
  
  V_COUNT NUMBER;
  V_CURRENT_POS NUMBER;
  V_CURRENT_TAG_END_POS NUMBER;
  V_CURRENT_TAG VARCHAR2(17);
  
  V_TAG_BEGIN VARCHAR2(8) := '<IMG "MF';
  V_END_OF_TAG VARCHAR2(8) := ').@UDV">';
  
  -- Buyoff tags
  V_BUYOFF_TAG1 VARCHAR2(17) := '<IMG "MFI_1002728';
  V_BUYOFF_TAG2 VARCHAR2(17) := '<IMG "MF1_1000529';
  V_BUYOFF_TAG3 VARCHAR2(17) := '<IMG "MF1_1000039';

  -- Data Collection Tags
  V_DATACOL_TAG1 VARCHAR2(17) := '<IMG "MFI_1002729';
  V_DATACOL_TAG2 VARCHAR2(17) := '<IMG "MF1_1000058';
  V_DATACOL_TAG3 VARCHAR2(17) := '<IMG "MF1_1000540';


BEGIN

  --
  V_TEXT := VI_TEXT;
  V_COUNT := 1;
  V_CURRENT_POS := 1; 
  V_RETURN_TEXT := '';
  
  --
  IF V_TEXT IS NOT NULL THEN
    
      WHILE (INSTR(V_TEXT, V_TAG_BEGIN, 1, V_COUNT) > 0) LOOP
        
      --find the next occurance of <IMG "MF
      V_CURRENT_POS :=  INSTR(V_TEXT, V_TAG_BEGIN, 1, V_COUNT);  
      V_CURRENT_TAG :=  SUBSTR(V_TEXT, V_CURRENT_POS, 17);
      V_CURRENT_TAG_END_POS := INSTR(V_TEXT, V_END_OF_TAG, V_CURRENT_POS)+8;

      -- is it one of our guys? 
      -- if so, add it to our list
      IF V_CURRENT_TAG = V_BUYOFF_TAG1 THEN
        V_RETURN_TEXT := V_RETURN_TEXT || SUBSTR(V_TEXT, V_CURRENT_POS,(
        V_CURRENT_TAG_END_POS - V_CURRENT_POS));
      END IF;
      IF V_CURRENT_TAG = V_BUYOFF_TAG2 THEN
        V_RETURN_TEXT := V_RETURN_TEXT || SUBSTR(V_TEXT, V_CURRENT_POS,(
        V_CURRENT_TAG_END_POS - V_CURRENT_POS));
      END IF;
      IF V_CURRENT_TAG = V_BUYOFF_TAG3 THEN
        V_RETURN_TEXT := V_RETURN_TEXT || SUBSTR(V_TEXT, V_CURRENT_POS,(
        V_CURRENT_TAG_END_POS - V_CURRENT_POS));
      END IF;
      
      IF V_CURRENT_TAG = V_DATACOL_TAG1 THEN
        V_RETURN_TEXT := V_RETURN_TEXT || SUBSTR(V_TEXT, V_CURRENT_POS,(
        V_CURRENT_TAG_END_POS - V_CURRENT_POS));
      END IF;
      IF V_CURRENT_TAG = V_DATACOL_TAG2 THEN
        V_RETURN_TEXT := V_RETURN_TEXT || SUBSTR(V_TEXT, V_CURRENT_POS,(
        V_CURRENT_TAG_END_POS - V_CURRENT_POS));
      END IF;
      IF V_CURRENT_TAG = V_DATACOL_TAG3 THEN
        V_RETURN_TEXT := V_RETURN_TEXT || SUBSTR(V_TEXT, V_CURRENT_POS,(
        V_CURRENT_TAG_END_POS - V_CURRENT_POS));
      END IF;
      
      --increment our count 
      V_COUNT := V_COUNT + 1;
      
    END LOOP;
  END IF;  
  -- The V_RETURN_TEXT has a CHR(46) '.' concatenated to it
  -- because sometimes it was not returning the data collection and Buyofolzxghkf Tags
  -- to the Delivery Package External Report.  This makes it work all the time.
  -- BIZARRE, I know.
  -- DO NOT REMOVE
  RETURN V_RETURN_TEXT || CHR(46);
END PWUST_GET_BUYOFF_DATACOL_TEST;
/

GRANT EXECUTE on PWUST_GET_BUYOFF_DATACOL_TAGS to SF$CONNECTION_POOL;
