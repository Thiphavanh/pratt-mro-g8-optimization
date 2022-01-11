CREATE OR REPLACE PROCEDURE PWUST_GET_NEXT_WO_NUM(VI_Sales_order VARCHAR,
                                                  VI_PART_NO     VARCHAR,
                                                  VO_NEXT_NUMBER OUT Number) IS
--
BEGIN
       BEGIN
          SELECT TO_NUMBER(NVL(MAX(T.UCF_ORDER_VCH11)+1,1))
          INTO VO_NEXT_NUMBER
          FROM SFWID_ORDER_DESC T
          WHERE T.UCF_ORDER_VCH2 IS NOT NULL
          AND T.UCF_ORDER_VCH11 IS NOT NULL
          AND T.UCF_ORDER_VCH2 like VI_Sales_order
          AND T.PART_NO = VI_PART_NO;
          --
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              VO_NEXT_NUMBER := 1;
          END;
END;
/
