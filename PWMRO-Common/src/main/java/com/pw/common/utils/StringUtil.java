/*
* This unpublished work, first created in 2017 and updated thereafter,
*  is protected under the copyright laws of the United States and of
*  other countries throughout the world.  Use, disassembly, reproduction,
*  distribution, etc. without the express written consent of United
*  Technologies Corporation is prohibited.  Copyright United Technologies
*  Corporation 2017.  All rights reserved.  Information contained herein
*  is proprietary and confidential and improper use or disclosure may
*  result in civil and penal sanctions.
*
*  File:    StringUtil.java
* 
*  Created: 2017-08-08
* 
*  Author:  c079222
* 
*  Revision History
* 
*  Date      	Who                	Description
*  ----------	---------------	---------------	---------------------------------------------------
* 2017-08-08    c079222		    Initial Release XXXXXX
* 2017-09-30    c079222         Changed encrypt and decrypt from sun.misc.BASE64Encoder/sun.misc.BASE64Decoder
*                               to java.util.Base64
*/
package com.pw.common.utils;

import java.text.DecimalFormatSymbols;
import java.util.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
//import sun.misc.BASE64Encoder;
//import sun.misc.BASE64Decoder;
import javax.crypto.spec.DESKeySpec;

public class StringUtil {
    
    /** Creates a new instance of StringUtil */

	public StringUtil() {

    }
	
    public static String fixString(String inStr, char fill, int size){
        
        StringBuffer sbf = new StringBuffer(size);
        for (int y=1;y<size+1; y++){
            sbf.append(fill);
        }
        sbf.replace(0, inStr.length()-1, inStr);
        String nstr= sbf.substring(0,size);
        return nstr;
    }
    public static String revFixString(String inStr, char fill, int size){
        
        StringBuffer sbf = new StringBuffer(size);
        for (int y=1;y<size+1; y++){
            sbf.append(fill);
        }
        sbf.replace(size-inStr.length(),size-1, inStr);
        String nstr= sbf.substring(0,size);
        return nstr;
    }
    public static String revFixString(int inNum, char fill, int size){
        String inStr = Integer.toString(inNum);
        StringBuffer sbf = new StringBuffer(size);
        for (int y=1;y<size+1; y++){
            sbf.append(fill);
        }
        sbf.replace(size-inStr.length(),size-1, inStr);
        String nstr= sbf.substring(0,size);
        return nstr;
    }
    
    public static String getDate(String inStr){
        Calendar cal;
        int year;
        int month;
        int day;
        StringTokenizer str;
        
        str = new StringTokenizer(inStr,"/");
        month = Integer.parseInt(str.nextToken());
        day = Integer.parseInt(str.nextToken());
        year = Integer.parseInt(str.nextToken());
        month=(month-1);
        StringBuffer dateString = new StringBuffer(1); 
        
            
        
        cal = Calendar.getInstance();
        cal.clear();
        cal.set(year,month,day);
        // StringBuffer outStr = new StringBuffer(1); // not being used.
        // String yearString = new Integer(cal.get(Calendar.YEAR)+1000).toString();
		String yearString = Integer.toString( cal.get( Calendar.YEAR ) );
        dateString.append(yearString.substring(2));
        // String dayString = new Integer(cal.get(Calendar.DAY_OF_YEAR)+1000).toString();
		String dayString = Integer.toString( cal.get( Calendar.DAY_OF_YEAR ) + 1000 );
        dateString.append(dayString.substring(1));
        
        
        String dateStr = dateString.toString();
        return dateStr;
    }
        
	public static boolean isNumeric(String s) {
		  if (s == null) {
			  return false;
		  } else if ("".equalsIgnoreCase(s)) {
			  return false;
		  } else {
			  final char[] numbers = s.toCharArray();
			  for (int x = 0; x < numbers.length; x++) {      
				  final char c = numbers[x];
				  if ((c >= '0') && (c <= '9')) continue;
				  return false; // invalid
			  }
			  return true; // valid
		  }
		}

/*
 * SEPL_OP_03
 * 
 * Author: DM Lyga - 2012-08-06
 * 
 */
	public static boolean isValidFloat(String s) {
		  boolean ptFound = false;
		  final char[] numbers = s.toCharArray();
		  for (int x = 0; x < numbers.length; x++) {      
			final char c = numbers[x];
			if ((c >= '0') && (c <= '9'))
			{	continue;
			}
			else if ((c == '.') && !ptFound)
			{	ptFound = true;
				continue;
			}
			return false; // invalid
		  }
		  return true; // valid
		}

// 2013-02-12 SEPL_OP_13, Defect 797
	public static String maxLeft(String text, int len)
	{
		String returnText;
		
		// return the 'len' left-most characters from text or full string if 'len' > string length
		if (text == null || text.isEmpty())
		{
			returnText = text;
		}
		else
		{
			int i = text.length();
			int j = len;
			if (i < len)
				j = i;
			
			returnText = text.substring(0, j);
		}
		
		return returnText;
	}
// 2013-02-12 SEPL_OP_13, Defect 797
	
	public static String Left(String text, int length)
	{
		  return text.substring(0, length);
	}


	public static String Right(String text, int length)
	{
//		  return text.substring(text.length() - length, length);
		return text.substring(text.length()-length);
	}  

	/**
	* Reallocates an array with a new size, and copies the contents
	* of the old array to the new array.
	* @param oldArray  the old array, to be reallocated.
	* @param newSize   the new array size.
	* @return          A new array with the same contents.
	*/
	public static Object resizeArray (Object oldArray, int newSize) {
	   int oldSize = java.lang.reflect.Array.getLength(oldArray);
	   Class elementType = oldArray.getClass().getComponentType();
	   Object newArray = java.lang.reflect.Array.newInstance(
			 elementType,newSize);
	   int preserveLength = Math.min(oldSize,newSize);
	   if (preserveLength > 0)
		  System.arraycopy (oldArray,0,newArray,0,preserveLength);
	   return newArray; 
	}

	/** IIF function
	 * 
	 * @author m112328
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
     public static String iif(boolean condition, String trueValue, String falseValue) {
     	String returnValue = "";
     	if (condition) {
     		returnValue = trueValue;
     	} else {
     		returnValue = falseValue;
     	}
     	return returnValue;
     }
     
	/**
	 * If Java 1.4 is unavailable, the following technique may be used.
	 *
	 * @param aInput is the original String which may contain substring aOldPattern
	 * @param aOldPattern is the non-empty substring which is to be replaced
	 * @param aNewPattern is the replacement for aOldPattern
	 */
	 public static String ReplaceStr(
	   final String aInput,
	   final String aOldPattern,
	   final String aNewPattern
	 ){
		if ( aOldPattern.length() == 0 ) {
		   throw new IllegalArgumentException("Old pattern must have content.");
		}

		final StringBuffer result = new StringBuffer(1);
		//startIdx and idxOld delimit various chunks of aInput; these
		//chunks always end where aOldPattern begins
		int startIdx = 0;
		int idxOld = 0;
		while ((idxOld = aInput.indexOf(aOldPattern, startIdx)) >= 0) {
		  //grab a part of aInput which does not include aOldPattern
		  result.append( aInput.substring(startIdx, idxOld) );
		  //add aNewPattern to take place of aOldPattern
		  result.append( aNewPattern );

		  //reset the startIdx to just after the current match, to see
		  //if there are any further matches
		  startIdx = idxOld + aOldPattern.length();
		}
		//the final chunk will go to the end of aInput
		result.append( aInput.substring(startIdx) );
		return result.toString();
	 }

	 public static boolean isStringNumeric( String str )    ///2012-12-14 SIQA_TR_T7
	 {
	     DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
	     char localeMinusSign = currentLocaleSymbols.getMinusSign();

	     if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != localeMinusSign ) return false;

	     boolean isDecimalSeparatorFound = false;
	     char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

	     for ( char c : str.substring( 1 ).toCharArray() )
	     {
	         if ( !Character.isDigit( c ) )
	         {
	             if ( c == localeDecimalSeparator && !isDecimalSeparatorFound )
	             {
	                 isDecimalSeparatorFound = true;
	                 continue;
	             }
	             return false;
	         }
	     }
	     return true;
	 }
     
	 
	 public static String encrypt(String str) {
		 String encrypedStr = "";
			try {
				DESKeySpec keySpec = new DESKeySpec("yeK7ganimulos".getBytes("UTF8"));
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				SecretKey key = keyFactory.generateSecret(keySpec);
				//BASE64Encoder base64encoder = new BASE64Encoder();
				//BASE64Decoder base64decoder = new BASE64Decoder();
				Base64.Encoder base64encoder = Base64.getEncoder();

	        	// ENCODE plainTextPassword String
	        	byte[] cleartext = str.getBytes("UTF8");      

	        	Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread safe
	        	cipher.init(Cipher.ENCRYPT_MODE, key);
	        	//encrypedStr = base64encoder.encode(cipher.doFinal(cleartext));
	        	encrypedStr = base64encoder.encodeToString(cipher.doFinal(cleartext));
	        	
			} 
			catch (InvalidKeySpecException e) {
				System.out.println("Invalid Key Spec:" + e.getMessage());
			}
			catch (NoSuchAlgorithmException e) {
				System.out.println("No Such Algorithm:" + e.getMessage());
			}
			catch (InvalidKeyException e) {
				System.out.println("Invalid Key:" + e.getMessage());
			} catch (UnsupportedEncodingException e) {
				System.out.println("Unsupported Encoding Exception:" + e.getMessage());
			} catch (NoSuchPaddingException e) {
					System.out.println("No Such Padding:" + e.getMessage());
	        } catch (javax.crypto.BadPaddingException e) {
	        } catch (IllegalBlockSizeException e) {
	        } catch (java.io.IOException e) {
	        }
			
			return encrypedStr;
	    }

	    public static String decrypt(String str) {
			 String decryptedStr = "";
				try {
					DESKeySpec keySpec = new DESKeySpec("yeK7ganimulos".getBytes("UTF8"));
					SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
					SecretKey key = keyFactory.generateSecret(keySpec);
					//BASE64Encoder base64encoder = new BASE64Encoder();
					//BASE64Decoder base64decoder = new BASE64Decoder();

					//byte[] encrypedBytes = base64decoder.decodeBuffer(str);
					byte[] encrypedBytes = Base64.getDecoder().decode(str);

					Cipher cipher = Cipher.getInstance("DES");// cipher is not thread safe
					cipher.init(Cipher.DECRYPT_MODE, key);
					byte[] plainTextBytes = (cipher.doFinal(encrypedBytes));
					decryptedStr = new String(plainTextBytes, "UTF8");

					
				}  
				catch (InvalidKeySpecException e) {
					System.out.println("Invalid Key Spec:" + e.getMessage());
				}
				catch (NoSuchAlgorithmException e) {
					System.out.println("No Such Algorithm:" + e.getMessage());
				}
				catch (InvalidKeyException e) {
					System.out.println("Invalid Key:" + e.getMessage());
				} catch (UnsupportedEncodingException e) {
					System.out.println("Unsupported Encoding Exception:" + e.getMessage());
				} catch (NoSuchPaddingException e) {
						System.out.println("No Such Padding:" + e.getMessage());
		        } catch (javax.crypto.BadPaddingException e) {
		        } catch (IllegalBlockSizeException e) {
		        } catch (java.io.IOException e) {
		        }
				
				return decryptedStr;

	    }
	 
	    public static String padRight(String s, int n) {
	        return String.format("%1$-" + n + "s", s);  
	   }

	   public static String padLeft(String s, int n) {
	       return String.format("%1$" + n + "s", s);  
	   }

	   /**
	   * Utility method to replace the string from StringBuilder.
	   * @param sb          the StringBuilder object.
	   * @param toReplace   the String that should be replaced.
	   * @param replacement the String that has to be replaced by.
	   * 
	   */
	   public static void replaceString(StringBuilder sb,
	                                    String toReplace,
	                                    String replacement) {      
	       int index = -1;
	       while ((index = sb.lastIndexOf(toReplace)) != -1) {
	           sb.replace(index, index + toReplace.length(), replacement);
	       }
	   }
	   
	   public static byte[] hexStringToByteArray(String s) {
		    int len = s.length();
		    byte[] data = new byte[len / 2];
		    for (int i = 0; i < len; i += 2) {
		        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
		                             + Character.digit(s.charAt(i+1), 16));
		    }
		    return data;
		}
	   
}