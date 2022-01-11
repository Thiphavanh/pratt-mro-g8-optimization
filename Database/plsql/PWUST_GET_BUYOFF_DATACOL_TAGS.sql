CREATE OR REPLACE FUNCTION PWUST_GET_BUYOFF_DATACOL_TAGS(VI_TEXT CLOB)
  RETURN CLOB AS

  -- *********************************************************************************
  -- Created By:  Raeann Thorpe - SMRO_RPT_201 - External Delivery Report
  -- Description: Extract and Return only the Data Collections and Buyoffs from the text fields
  -- 2020-09-03  BPolak  Changed V_RETURN_TEXT to a clob in order to prevent an overflow issue
  -- *********************************************************************************
  --
  --
  V_TEXT CLOB;
  -- V_RETURN_TEXT CLOB;
  V_COUNT NUMBER;
  V_RETURN_TEXT CLOB;
  -- Buyoff tag
  V_BUYOFF_TAG1 VARCHAR2(17) := '<IMG "MFI_1002728';
  V_BUYOFF_TAG1_BEGIN_POS NUMBER;
  V_BUYOFF_TAG1_END_POS NUMBER;

  V_BUYOFF_TAG2 VARCHAR2(17) := '<IMG "MF1_1000529';
  V_BUYOFF_TAG2_BEGIN_POS NUMBER;
  V_BUYOFF_TAG2_END_POS NUMBER;

  V_BUYOFF_TAG3 VARCHAR2(17) := '<IMG "MF1_1000039';
  V_BUYOFF_TAG3_BEGIN_POS NUMBER;
  V_BUYOFF_TAG3_END_POS NUMBER;

  -- Data Collection Tag
  V_DATACOL_TAG1 VARCHAR2(17) := '<IMG "MFI_1002729';
  V_DATACOL_TAG1_BEGIN_POS NUMBER;
  V_DATACOL_TAG1_END_POS NUMBER;

  V_DATACOL_TAG2 VARCHAR2(17) := '<IMG "MF1_1000058';
  V_DATACOL_TAG2_BEGIN_POS NUMBER;
  V_DATACOL_TAG2_END_POS NUMBER;

  V_DATACOL_TAG3 VARCHAR2(17) := '<IMG "MF1_1000540';
  V_DATACOL_TAG3_BEGIN_POS NUMBER;
  V_DATACOL_TAG3_END_POS NUMBER;

  -- END TAG
  V_END_OF_TAG VARCHAR2(8) := ').@UDV">';

BEGIN

  --
  V_TEXT := VI_TEXT;
  V_COUNT := 1;

  --
  IF V_TEXT IS NOT NULL THEN
    --   DBMS_OUTPUT.PUT_LINE(' V_TEXT =' || V_TEXT);

    LOOP
      --   DBMS_OUTPUT.PUT_LINE('V_COUNT=' || V_COUNT);

      --DATA COLLECTIONS - TAG 1 '<IMG "MFI_1002729'
      IF INSTR(V_TEXT, V_DATACOL_TAG1, 1, V_COUNT) > 0 THEN
        V_DATACOL_TAG1_BEGIN_POS := INSTR(V_TEXT, V_DATACOL_TAG1, 1, V_COUNT);
        V_DATACOL_TAG1_END_POS := INSTR(V_TEXT, V_END_OF_TAG, V_DATACOL_TAG1_BEGIN_POS) +
                                  LENGTH(V_END_OF_TAG);

        -- ADD TO THE RETURN CLOB
        IF V_RETURN_TEXT IS NOT NULL THEN
          V_RETURN_TEXT := V_RETURN_TEXT ||
                           SUBSTR(V_TEXT, V_DATACOL_TAG1_BEGIN_POS, V_DATACOL_TAG1_END_POS -
                                   V_DATACOL_TAG1_BEGIN_POS);
        ELSE
          V_RETURN_TEXT := SUBSTR(V_TEXT, V_DATACOL_TAG1_BEGIN_POS, V_DATACOL_TAG1_END_POS -
                                   V_DATACOL_TAG1_BEGIN_POS);
        END IF;
      END IF;

      --DATA COLLECTIONS - TAG 2 '<IMG "MF1_1000058'
      IF INSTR(V_TEXT, V_DATACOL_TAG2, 1, V_COUNT) > 0 THEN
        V_DATACOL_TAG2_BEGIN_POS := INSTR(V_TEXT, V_DATACOL_TAG2, 1, V_COUNT);
        V_DATACOL_TAG2_END_POS := INSTR(V_TEXT, V_END_OF_TAG, V_DATACOL_TAG2_BEGIN_POS) +
                                  LENGTH(V_END_OF_TAG);

        IF V_RETURN_TEXT IS NOT NULL THEN
          V_RETURN_TEXT := V_RETURN_TEXT ||
                           SUBSTR(V_TEXT, V_DATACOL_TAG2_BEGIN_POS, V_DATACOL_TAG2_END_POS -
                                   V_DATACOL_TAG2_BEGIN_POS);
        ELSE
          V_RETURN_TEXT := SUBSTR(V_TEXT, V_DATACOL_TAG2_BEGIN_POS, V_DATACOL_TAG2_END_POS -
                                   V_DATACOL_TAG2_BEGIN_POS);
        END IF;
      END IF;

      --DATA COLLECTIONS - TAG 3 '<IMG "MF1_1000540'
      IF INSTR(V_TEXT, V_DATACOL_TAG3, 1, V_COUNT) > 0 THEN
        V_DATACOL_TAG3_BEGIN_POS := INSTR(V_TEXT, V_DATACOL_TAG3, 1, V_COUNT);
        V_DATACOL_TAG3_END_POS := INSTR(V_TEXT, V_END_OF_TAG, V_DATACOL_TAG3_BEGIN_POS) +
                                  LENGTH(V_END_OF_TAG);

        IF V_RETURN_TEXT IS NOT NULL THEN
          V_RETURN_TEXT := V_RETURN_TEXT ||
                           SUBSTR(V_TEXT, V_DATACOL_TAG3_BEGIN_POS, V_DATACOL_TAG3_END_POS -
                                   V_DATACOL_TAG3_BEGIN_POS);
        ELSE
          V_RETURN_TEXT := SUBSTR(V_TEXT, V_DATACOL_TAG3_BEGIN_POS, V_DATACOL_TAG3_END_POS -
                                   V_DATACOL_TAG3_BEGIN_POS);
        END IF;
      END IF;

      -- BUYOFFS - TAG 1    '<IMG "MFI_1002728'
      IF INSTR(V_TEXT, V_BUYOFF_TAG1, 1, V_COUNT) > 0 THEN
        V_BUYOFF_TAG1_BEGIN_POS := INSTR(V_TEXT, V_BUYOFF_TAG1, 1, V_COUNT);
        V_BUYOFF_TAG1_END_POS := INSTR(V_TEXT, V_END_OF_TAG, V_BUYOFF_TAG1_BEGIN_POS) +
                                 LENGTH(V_END_OF_TAG);

        -- ADD TO THE RETURN CLOB
        IF V_RETURN_TEXT IS NOT NULL THEN
          V_RETURN_TEXT := V_RETURN_TEXT ||
                           SUBSTR(V_TEXT, V_BUYOFF_TAG1_BEGIN_POS, V_BUYOFF_TAG1_END_POS -
                                   V_BUYOFF_TAG1_BEGIN_POS);
        ELSE
          V_RETURN_TEXT := SUBSTR(V_TEXT, V_BUYOFF_TAG1_BEGIN_POS, V_BUYOFF_TAG1_END_POS -
                                   V_BUYOFF_TAG1_BEGIN_POS);
        END IF;
      END IF;

      -- BUYOFFS - TAG 2  '<IMG "MF1_1000529'
      IF INSTR(V_TEXT, V_BUYOFF_TAG2, 1, V_COUNT) > 0 THEN
        V_BUYOFF_TAG2_BEGIN_POS := INSTR(V_TEXT, V_BUYOFF_TAG2, 1, V_COUNT);
        V_BUYOFF_TAG2_END_POS := INSTR(V_TEXT, V_END_OF_TAG, V_BUYOFF_TAG2_BEGIN_POS) +
                                 LENGTH(V_END_OF_TAG);

        -- ADD TO THE RETURN CLOB
        IF V_RETURN_TEXT IS NOT NULL THEN
          V_RETURN_TEXT := V_RETURN_TEXT ||
                           SUBSTR(V_TEXT, V_BUYOFF_TAG2_BEGIN_POS, V_BUYOFF_TAG2_END_POS -
                                   V_BUYOFF_TAG2_BEGIN_POS);
        ELSE
          V_RETURN_TEXT := SUBSTR(V_TEXT, V_BUYOFF_TAG2_BEGIN_POS, V_BUYOFF_TAG2_END_POS -
                                   V_BUYOFF_TAG2_BEGIN_POS);
        END IF;
      END IF;

      --- BUYOFFS - TAG 3 '<IMG "MF1_1000039'
      IF INSTR(V_TEXT, V_BUYOFF_TAG3, 1, V_COUNT) > 0 THEN
        V_BUYOFF_TAG3_BEGIN_POS := INSTR(V_TEXT, V_BUYOFF_TAG3, 1, V_COUNT);
        V_BUYOFF_TAG3_END_POS := INSTR(V_TEXT, V_END_OF_TAG, V_BUYOFF_TAG3_BEGIN_POS) +
                                 LENGTH(V_END_OF_TAG);

        -- ADD TO THE RETURN CLOB
        IF V_RETURN_TEXT IS NOT NULL THEN
          V_RETURN_TEXT := V_RETURN_TEXT ||
                           SUBSTR(V_TEXT, V_BUYOFF_TAG3_BEGIN_POS, V_BUYOFF_TAG3_END_POS -
                                   V_BUYOFF_TAG3_BEGIN_POS);
        ELSE
          V_RETURN_TEXT := SUBSTR(V_TEXT, V_BUYOFF_TAG3_BEGIN_POS, V_BUYOFF_TAG3_END_POS -
                                   V_BUYOFF_TAG3_BEGIN_POS);
        END IF;
      END IF;
      --
      V_COUNT := V_COUNT + 1;
      ---------------------------------------THIS NEEDS UPDATE------------------------------------------------
      EXIT WHEN(INSTR(V_TEXT, V_BUYOFF_TAG1, 1, V_COUNT) = 0 AND
                INSTR(V_TEXT, V_BUYOFF_TAG2, 1, V_COUNT) = 0 AND
                INSTR(V_TEXT, V_BUYOFF_TAG3, 1, V_COUNT) = 0 AND
                INSTR(V_TEXT, V_DATACOL_TAG1, 1, V_COUNT) = 0 AND
                INSTR(V_TEXT, V_DATACOL_TAG2, 1, V_COUNT) = 0 AND
                INSTR(V_TEXT, V_DATACOL_TAG3, 1, V_COUNT) = 0);

      V_BUYOFF_TAG1_BEGIN_POS := NULL;
      V_BUYOFF_TAG1_END_POS := NULL;
      V_BUYOFF_TAG2_BEGIN_POS := NULL;
      V_BUYOFF_TAG2_END_POS := NULL;
      V_BUYOFF_TAG3_BEGIN_POS := NULL;
      V_BUYOFF_TAG3_END_POS := NULL;
      V_DATACOL_TAG1_BEGIN_POS := NULL;
      V_DATACOL_TAG1_END_POS := NULL;
      V_DATACOL_TAG2_BEGIN_POS := NULL;
      V_DATACOL_TAG2_END_POS := NULL;
      V_DATACOL_TAG3_BEGIN_POS := NULL;
      V_DATACOL_TAG3_END_POS := NULL;
    END LOOP;
    --
  END IF;
  -- The V_RETURN_TEXT has a CHR(46) '.' concatenated to it
  -- because sometimes it was not returning the data collection and Buyoff Tags
  -- to the Delivery Package External Report.  This makes it work all the time.
  -- BIZARRE, I know.
  -- DO NOT REMOVE
  RETURN V_RETURN_TEXT || CHR(46);
  --
END PWUST_GET_BUYOFF_DATACOL_TAGS;
/