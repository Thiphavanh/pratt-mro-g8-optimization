
set define off
declare
v_image_id varchar2(80) :='LABELPAARUSERPERMISSIONS';
v_image_id_displ varchar2(80) :='LabelPaarUserPermissions';
v_stype varchar2(32) :='';
v_client_type varchar2(10) :='Windows';
v_image_format varchar2(4) :='BMP';
v_source_file_name varchar2(255) :='C:\iBASEt\Subversion\projects\UTAS\SG\lag_db_sg\defaultdata\LabelPaarUserPermissions.bmp';
v_description varchar2(255) :='';
idp1 clob :='424D76290000000000003600000028000000AF00000014000000010018000000000040290000120B0000120B00000000000000000000664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A3666';
idp2 clob :='4A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A';
idp3 clob :='36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36';
idp4 clob :='664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A3666';
idp5 clob :='4A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A';
idp6 clob :='36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36664A36654935654935664A36664A36765B47AA927E0000006A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A';
idp7 clob :='6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A';
idp8 clob :='4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E';
idp9 clob :='3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A';
idp10 clob :='6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A';
idp11 clob :='4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A6A4E3A694D396A4E3A6A4E3A6A4E3A715642B49D';
idp12 clob :='89EFDCC7EFDCC70000006E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E';
idp13 clob :='6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E';
idp14 clob :='523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E52';
idp15 clob :='3E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E';
idp16 clob :='6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E523E6E';
idp17 clob :='523E6E523E6E523E6D513D6E523E6E523E6E523E8E7460E7D3BEEFDCC7EFDCC7EFDCC70000007257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257';
idp18 clob :='43725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743';
idp19 clob :='72574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372574372';
idp20 clob :='57437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257437257';
idp21 clob :='43725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743725743';
idp22 clob :='725743725743725743725743725743725743725743725743725743725743725743715642725743725743725743A08874EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7000000765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B4776';
idp23 clob :='5B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B';
idp24 clob :='47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47';
idp25 clob :='765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B4776';
idp26 clob :='5B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B';
idp27 clob :='47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47765B47755B47765B47765B47765B479B836FEFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC70000007A604C7A604C';
idp28 clob :='7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A';
idp29 clob :='604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A60';
idp30 clob :='4C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C';
idp31 clob :='7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A';
idp32 clob :='604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C7A604C89705BE8D4';
idp33 clob :='BFEFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC70000007F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F6551C18151FFFFE4FFFFFFFFFFFFC1E7FF7F6571AB6551FFFFC8';
idp34 clob :='FFFFFFFFFFFF95B6E47F6551C18151FFFFE4FFFFFFFFFFFFC1E7FFC18171C1E7E47F65717F65517F65517F65517F6551C18151C1E7E47F65717F65517F65517F65517F6551C18151FFFFE4FFFFFFFFFFFFC1E7FFC18171C1E7E47F65717F6551C18151C1';
idp35 clob :='E7E47F6571C18151C1E7E47F6571C18151C1E7E4C18171C1E7E4AB6571FFFFC8FFFFFFFFFFFF95B6E4AB6551FFFFC8FFFFFFFFFFFF95B6E4C18151C1E7E47F6571C18151FFFFE4FFFFFFD6FFFF7F6590C18151C1E7E47F65717F6551C18151C1E7E4AB65';
idp36 clob :='71FFFFC8FFFFFFFFFFFF95B6E47F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F6551';
idp37 clob :='7F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F65517F';
idp38 clob :='65517F65517F65517F65517F65517F65517F65517F6551CCB6A2EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC70000008369568369568369568369568369568369568369568369568369568369568369568369568369568369568369568369568369';
idp39 clob :='56836956ECFFCA8384AF836956836956ECB875AFCFFFC3845699B8CA836956996956ECE7AFAF84AFFFFFCA839ECA836956836956836956C38456C3E7E5836975836956836956836956836956C38456C3E7E5836975836956836956836956AF6956FFFFCA';
idp40 clob :='839ECA836956836956836956C38456C3E7E5836975836956C38456C3E7E5836975C38456C3E7E5836975C38456C3E7E5C38475C3E7E5C3847599B8CA836956996956ECE7AFC39EAF99B8CA836956996956ECE7AFC39EAFC3E7E5AF6975FFFFCA839ECA83';
idp41 clob :='6956ECB875AFCFFFC38456C3E7E5836975836956C38456C3E7E5C3847599B8CA836956996956ECE7AF8384AF8369568369568369568369568369568369568369568369568369568369568369568369568369568369568369568369568369568369568369';
idp42 clob :='56836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956836956';
idp43 clob :='8369568369568369568369568369568369568369568369568369568369568369568369568269558369568369568369569D8672EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7000000886F5B886F5B886F5B886F5B886F5B886F5B886F5B88';
idp44 clob :='6F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5BC6E8E6886F79886F5B886F5BC6895BC6E8E6886F79886F5BB16F5BFFFFCCD9FFFFC68995C6E8E6886F79886F5B886F5B886F5BC6895BC6E8E6886F79886F5B886F5B886F';
idp45 clob :='5B886F5BC6895BC6E8E6886F79886F5B886F5B886F5BC6895BC6E8E6886F79886F5B886F5B886F5BC6895BC6E8E6886F79886F5BC6895BC6E8E6886F79C6895BC6E8E6886F79C6895BC6E8E6C68979C6E8E6886F79886F5BB16F5BFFFFCCD9FFFF886F95';
idp46 clob :='886F5BB16F5BFFFFCCD9FFFFC68995C6E8E6C68979C6E8E6886F79886F5BC6895BD9FFE6C68995C6E8E6886F79886F5BC6895BC6E8E6886F79886F5BB16F5BFFFFCCD9FFFF886F95886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B88';
idp47 clob :='6F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F';
idp48 clob :='5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5B886F5BCEB9A5EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7';
idp49 clob :='EFDCC70000008C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C7360C8E9E78C737D8C73608C7360C88C60C8E9E7B4737DFFFFCDDAFFFF8C73998C7360C88C60FFFFE7FF';
idp50 clob :='FFFFFFFFFFFFFFFFDAFFFFC88C99C8E9E78C737D8C73608C73608C73608C7360C88C60FFFFE7FFFFFFFFFFFFC8E9FF8C737DC88C60FFFFE7FFFFFFFFFFFFFFFFFFDAFFFFC88C99C8E9E78C737D8C7360C88C60C8E9E78C737DC88C60C8E9E78C737DC88C';
idp51 clob :='60C8E9E7C88C7DC8E9E7B4737DFFFFCDDAFFFF8C73998C7360B47360FFFFCDDAFFFF8C73998C7360C88C60C8E9E7C88C7DC8E9E78C737D8C7360C88C60DAFFE7C88C99C8E9E78C737D8C7360C88C60C8E9E7B4737DFFFFCDDAFFFF8C73998C73608C7360';
idp52 clob :='8C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C';
idp53 clob :='73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608C73608B73608C73608C73608C73';
idp54 clob :='6098806DEFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7000000907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865C9EAE7907881907865907865';
idp55 clob :='C99065C9EAE7C99081DBFFE790789C907865DBD49CB6789CDBFFCF90789C907865C99065C9EAE7C99081FFFFE790A8CF907865907865907865907865C99065C9EAE7907881907865EDBE81C9EAFFB67881DBFFCF90789C907865C99065C9EAE7C99081FF';
idp56 clob :='FFE790A8CF907865C99065EDFFE79090B6DBA865EDFFFF9090B6DBA865C9EAFFC99081C9EAE7C99081DBFFE790789C907865DBD49CC9909CDBFFE790789C907865DBD49CC9909CC9EAE7B67881FFFFCF90A8CF907865EDBE81B6D4FFC99065FFFFE790A8';
idp57 clob :='CF907865DBA865C9EAFFC99081DBFFE790789C907865DBD49C90789C907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865907865';
idp58 clob :='90786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590786590';
idp59 clob :='7865907865907865907865907865907865907865907865907865907865BAA490EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7000000957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D';
idp60 clob :='6A957D6A957D6A957D6A957D6A957D6ACCEAE7957D85957D6A957D6ACC946ACCEAE7957D85EEC185FFFFFFFFFFFFCCEAFF957D85CC946AFFFFE7FFFFFFEEFFFF9594B8CC946ACCEAE7FFD5B8DDFFFF957D9E957D6A957D6ACC946ACCEAE7957D85957D6A';
idp61 clob :='B97D6ADDFFD0957D9ECC946AFFFFE7FFFFFFEEFFFF9594B8CC946ACCEAE7FFD5B8DDFFFFCC949EDDEAE7FFEAD0FFFFFFA8AAD0FFEAB8FFFFFF95AAD0CC946ACCEAE7957D85EEC185FFFFFFFFFFFFCCEAFF957D85EEC185FFFFFFFFFFFFCCEAFFCC9485CC';
idp62 clob :='EAE7957D85CC946AFFFFE7FFFFFFDDFFFF957D9ECC946ACCEAE7FFD5B8FFFFFFFFFFFF95AAD0957D6AEEC185FFFFFFFFFFFFCCEAFF957D85957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D';
idp63 clob :='6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A';
idp64 clob :='957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6A957D6AD2BEAAEFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC70000009A836F9A836F9A836F9A';
idp65 clob :='836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836FCEECE89A83899A836F9A836FCE996FCEECE89A83899A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A83';
idp66 clob :='6F9A836F9A836F9A836F9A836F9A836FCE996FCEECE89A83899A836FDEAF6FCEECFF9A83899A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F';
idp67 clob :='9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A';
idp68 clob :='836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A83';
idp69 clob :='6F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836F9A836FEAD6C1EFDCC7EFDCC7EFDCC7EFDCC7';
idp70 clob :='EFDCC7EFDCC7EFDCC7EFDCC7EFDCC70000009E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E8874D0ECE99E888D9E88749E8874D09D74D0ECE99E888D9E88749E88749E';
idp71 clob :='88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E8874D09D74FFFFE9FFFFFFFFFFFFE0FFFF9E88A59E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88';
idp72 clob :='749E88749E88749E88749E88749E8874D09D74D0ECE99E888D9E88749E88749E88749E88749E88749E88749E88749E88749E8874D09D74D0ECE99E888D9E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E8874';
idp73 clob :='9E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E';
idp74 clob :='88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E88749E87';
idp75 clob :='749E88749E88749E8874A8927EEFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7000000A38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7A';
idp76 clob :='A38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA3';
idp77 clob :='8D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D';
idp78 clob :='7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7A';
idp79 clob :='A38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA3';
idp80 clob :='8D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA38D7AA28D79A38D7AA38D7AA38D7ABFAB96EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7000000A8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA892';
idp81 clob :='7FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927F';
idp82 clob :='A8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8';
idp83 clob :='927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA892';
idp84 clob :='7FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927F';
idp85 clob :='A8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FA8927FD4C0ACEFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC700';
idp86 clob :='0000AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC97';
idp87 clob :='84AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784';
idp88 clob :='AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC';
idp89 clob :='9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC97';
idp90 clob :='84AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784AC9784B4A08CEFDCC7EFDCC7';
idp91 clob :='EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7000000B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B1';
idp92 clob :='9C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C';
idp93 clob :='89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89';
idp94 clob :='B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B1';
idp95 clob :='9C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C89B19C';
idp96 clob :='89B19C89B19C89B19C89B19C89B19C89B19C89DBC8B3EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7000000B6A38FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28F';
idp97 clob :='B5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5';
idp98 clob :='A28FB5A18EB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A18EB5A28FB5A2';
idp99 clob :='8FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28F';
idp100 clob :='B5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A18EB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5';
idp101 clob :='A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A28FB5A18EB5A28FB5A28FB5A28FCBB7A3EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7000000DDC9B5BAA794BAA794BAA794BAA7';
idp102 clob :='94BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794';
idp103 clob :='BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BA';
idp104 clob :='A794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA7';
idp105 clob :='94BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794';
idp106 clob :='BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BAA794BDAA97D7C4B0EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EFDCC7EF';
idp107 clob :='DCC7EFDCC7EFDCC7EFDCC7000000';
v_data_blob blob;
v_full_data_blob blob;
begin
 begin
  dbms_lob.createtemporary(v_data_blob, false);
  dbms_lob.append(v_data_blob, hextoraw(idp1));
  dbms_lob.append(v_data_blob, hextoraw(idp2));
  dbms_lob.append(v_data_blob, hextoraw(idp3));
  dbms_lob.append(v_data_blob, hextoraw(idp4));
  dbms_lob.append(v_data_blob, hextoraw(idp5));
  dbms_lob.append(v_data_blob, hextoraw(idp6));
  dbms_lob.append(v_data_blob, hextoraw(idp7));
  dbms_lob.append(v_data_blob, hextoraw(idp8));
  dbms_lob.append(v_data_blob, hextoraw(idp9));
  dbms_lob.append(v_data_blob, hextoraw(idp10));
  dbms_lob.append(v_data_blob, hextoraw(idp11));
  dbms_lob.append(v_data_blob, hextoraw(idp12));
  dbms_lob.append(v_data_blob, hextoraw(idp13));
  dbms_lob.append(v_data_blob, hextoraw(idp14));
  dbms_lob.append(v_data_blob, hextoraw(idp15));
  dbms_lob.append(v_data_blob, hextoraw(idp16));
  dbms_lob.append(v_data_blob, hextoraw(idp17));
  dbms_lob.append(v_data_blob, hextoraw(idp18));
  dbms_lob.append(v_data_blob, hextoraw(idp19));
  dbms_lob.append(v_data_blob, hextoraw(idp20));
  dbms_lob.append(v_data_blob, hextoraw(idp21));
  dbms_lob.append(v_data_blob, hextoraw(idp22));
  dbms_lob.append(v_data_blob, hextoraw(idp23));
  dbms_lob.append(v_data_blob, hextoraw(idp24));
  dbms_lob.append(v_data_blob, hextoraw(idp25));
  dbms_lob.append(v_data_blob, hextoraw(idp26));
  dbms_lob.append(v_data_blob, hextoraw(idp27));
  dbms_lob.append(v_data_blob, hextoraw(idp28));
  dbms_lob.append(v_data_blob, hextoraw(idp29));
  dbms_lob.append(v_data_blob, hextoraw(idp30));
  dbms_lob.append(v_data_blob, hextoraw(idp31));
  dbms_lob.append(v_data_blob, hextoraw(idp32));
  dbms_lob.append(v_data_blob, hextoraw(idp33));
  dbms_lob.append(v_data_blob, hextoraw(idp34));
  dbms_lob.append(v_data_blob, hextoraw(idp35));
  dbms_lob.append(v_data_blob, hextoraw(idp36));
  dbms_lob.append(v_data_blob, hextoraw(idp37));
  dbms_lob.append(v_data_blob, hextoraw(idp38));
  dbms_lob.append(v_data_blob, hextoraw(idp39));
  dbms_lob.append(v_data_blob, hextoraw(idp40));
  dbms_lob.append(v_data_blob, hextoraw(idp41));
  dbms_lob.append(v_data_blob, hextoraw(idp42));
  dbms_lob.append(v_data_blob, hextoraw(idp43));
  dbms_lob.append(v_data_blob, hextoraw(idp44));
  dbms_lob.append(v_data_blob, hextoraw(idp45));
  dbms_lob.append(v_data_blob, hextoraw(idp46));
  dbms_lob.append(v_data_blob, hextoraw(idp47));
  dbms_lob.append(v_data_blob, hextoraw(idp48));
  dbms_lob.append(v_data_blob, hextoraw(idp49));
  dbms_lob.append(v_data_blob, hextoraw(idp50));
  dbms_lob.append(v_data_blob, hextoraw(idp51));
  dbms_lob.append(v_data_blob, hextoraw(idp52));
  dbms_lob.append(v_data_blob, hextoraw(idp53));
  dbms_lob.append(v_data_blob, hextoraw(idp54));
  dbms_lob.append(v_data_blob, hextoraw(idp55));
  dbms_lob.append(v_data_blob, hextoraw(idp56));
  dbms_lob.append(v_data_blob, hextoraw(idp57));
  dbms_lob.append(v_data_blob, hextoraw(idp58));
  dbms_lob.append(v_data_blob, hextoraw(idp59));
  dbms_lob.append(v_data_blob, hextoraw(idp60));
  dbms_lob.append(v_data_blob, hextoraw(idp61));
  dbms_lob.append(v_data_blob, hextoraw(idp62));
  dbms_lob.append(v_data_blob, hextoraw(idp63));
  dbms_lob.append(v_data_blob, hextoraw(idp64));
  dbms_lob.append(v_data_blob, hextoraw(idp65));
  dbms_lob.append(v_data_blob, hextoraw(idp66));
  dbms_lob.append(v_data_blob, hextoraw(idp67));
  dbms_lob.append(v_data_blob, hextoraw(idp68));
  dbms_lob.append(v_data_blob, hextoraw(idp69));
  dbms_lob.append(v_data_blob, hextoraw(idp70));
  dbms_lob.append(v_data_blob, hextoraw(idp71));
  dbms_lob.append(v_data_blob, hextoraw(idp72));
  dbms_lob.append(v_data_blob, hextoraw(idp73));
  dbms_lob.append(v_data_blob, hextoraw(idp74));
  dbms_lob.append(v_data_blob, hextoraw(idp75));
  dbms_lob.append(v_data_blob, hextoraw(idp76));
  dbms_lob.append(v_data_blob, hextoraw(idp77));
  dbms_lob.append(v_data_blob, hextoraw(idp78));
  dbms_lob.append(v_data_blob, hextoraw(idp79));
  dbms_lob.append(v_data_blob, hextoraw(idp80));
  dbms_lob.append(v_data_blob, hextoraw(idp81));
  dbms_lob.append(v_data_blob, hextoraw(idp82));
  dbms_lob.append(v_data_blob, hextoraw(idp83));
  dbms_lob.append(v_data_blob, hextoraw(idp84));
  dbms_lob.append(v_data_blob, hextoraw(idp85));
  dbms_lob.append(v_data_blob, hextoraw(idp86));
  dbms_lob.append(v_data_blob, hextoraw(idp87));
  dbms_lob.append(v_data_blob, hextoraw(idp88));
  dbms_lob.append(v_data_blob, hextoraw(idp89));
  dbms_lob.append(v_data_blob, hextoraw(idp90));
  dbms_lob.append(v_data_blob, hextoraw(idp91));
  dbms_lob.append(v_data_blob, hextoraw(idp92));
  dbms_lob.append(v_data_blob, hextoraw(idp93));
  dbms_lob.append(v_data_blob, hextoraw(idp94));
  dbms_lob.append(v_data_blob, hextoraw(idp95));
  dbms_lob.append(v_data_blob, hextoraw(idp96));
  dbms_lob.append(v_data_blob, hextoraw(idp97));
  dbms_lob.append(v_data_blob, hextoraw(idp98));
  dbms_lob.append(v_data_blob, hextoraw(idp99));
  dbms_lob.append(v_data_blob, hextoraw(idp100));
  dbms_lob.append(v_data_blob, hextoraw(idp101));
  dbms_lob.append(v_data_blob, hextoraw(idp102));
  dbms_lob.append(v_data_blob, hextoraw(idp103));
  dbms_lob.append(v_data_blob, hextoraw(idp104));
  dbms_lob.append(v_data_blob, hextoraw(idp105));
  dbms_lob.append(v_data_blob, hextoraw(idp106));
  dbms_lob.append(v_data_blob, hextoraw(idp107));
  v_full_data_blob := v_data_blob;
  dbms_lob.freetemporary(v_data_blob);
 end;
begin
INSERT INTO SFCORE_GUI_GRAPHICS (IMAGE_ID,IMAGE_ID_DISPL,
                          STYPE,CLIENT_TYPE,SOURCE_FILE_NAME,
                          DESCRIPTION,IMAGE_FORMAT,
                          IMAGE_DATA,UPDT_USERID,TIME_STAMP)
    VALUES(V_IMAGE_ID,V_IMAGE_ID_DISPL,V_STYPE,V_CLIENT_TYPE,
            V_SOURCE_FILE_NAME,V_DESCRIPTION,V_IMAGE_FORMAT,
            V_FULL_DATA_BLOB,USER,SYSDATE);
commit;
exception when dup_val_on_index then
  UPDATE SFCORE_GUI_GRAPHICS
  SET IMAGE_ID_DISPL=V_IMAGE_ID_DISPL,TIME_STAMP=SYSDATE,UPDT_USERID=USER,
       DESCRIPTION=V_DESCRIPTION, CLIENT_TYPE = V_CLIENT_TYPE, 
      STYPE  = V_STYPE ,SOURCE_FILE_NAME = V_SOURCE_FILE_NAME,
       IMAGE_FORMAT = V_IMAGE_FORMAT,
       IMAGE_DATA = V_FULL_DATA_BLOB 
  WHERE IMAGE_ID=V_IMAGE_ID;
  commit;
end;
exception when others then null;
end;
/

set define on

