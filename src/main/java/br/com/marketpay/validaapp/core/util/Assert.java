package br.com.marketpay.validaapp.core.util;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;


public class Assert {
	protected Assert() {
    }
	 static public void assertEquals(Object expected, Object actual)  {
		 try {
			 assertEquals(null, expected, actual);
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }
	 static public void assertEquals(String message, Object expected,
	            Object actual) throws Exception {
	        if (equalsRegardingNull(expected, actual)) {
	            return;
	        } else {
	            failNotEquals(message, expected, actual);
	        }
	    }
	  static boolean equalsRegardingNull(Object expected, Object actual) {
	        if (expected == null) {
	            return actual == null;
	        }

	        return isEquals(expected, actual);
	    }
	 static private void failNotEquals(String message, Object expected,
	            Object actual) throws Exception {
	        fail(format(message, expected, actual));
	    }
	  private static boolean isEquals(Object expected, Object actual) {
	        return expected.equals(actual);
	    }
	  static String format(String message, Object expected, Object actual) throws Exception {
	        String formatted = "";
	        String returno  = null;
	        if (message != null && !message.equals("")) {
	            formatted = message + " ";
	        }
	        String expectedString = String.valueOf(expected);
	        String actualString = String.valueOf(actual);
	        if (expectedString.equals(actualString)) {
	        	returno =  formatted + "Esperado: "
	                    + formatClassAndValue(expected, expectedString)
	                    + " mas retornou: " + formatClassAndValue(actual, actualString);
	        } else {
	        	returno =  formatted + "Esperado:[" + expectedString + "] mas retornou:["
	                    + actualString + "]";
	        	throw new Exception(returno);
	        }
	        return returno;
	    }
	  private static String formatClassAndValue(Object value, String valueString) {
	        String className = value == null ? "null" : value.getClass().getName();
	        return className + "[" + valueString + "]";
	    }
	  static public void fail(String message) {
	        if (message == null) {
	            throw new AssertionError();
	        }
	        throw new AssertionError(message);
	    }
	  static private void failNotNull(String message, Object actual) {
	        String formatted = "";
	        if (message != null) {
	            formatted = message + " ";
	        }
	        fail(formatted + "expected null, but was:<" + actual + ">");
	    }
	  static public void assertNotNull(String message, Object object) {
	        assertTrue(message, object != null);
	    }
	  static public void assertNull(String message, Object object) {
	        if (object == null) {
	            return;
	        }
	        failNotNull(message, object);
	    }
	  static public void assertTrue(String message, boolean condition) {
	        if (!condition) {
	            fail(message);
	        }
	    }
	  static public void assertFalse(String message, boolean condition) {
	        assertTrue(message, !condition);
	    }
	  public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
	        assertThat("", actual, matcher);
	    }
	  public static <T> void assertThat(String reason, T actual,
	            Matcher<? super T> matcher) {
	     try {
	    	  MatcherAssert.assertThat(reason, actual, matcher);
		} catch (AssertionError e) {
			Assert.fail(e.getMessage());
		}  
	  }
//	 public static void main(String[] args) {
//		 try {
//			 assertNotEquals("erro", "2", "2");
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	 }
	  static public void assertNotEquals(String message, Object unexpected,
	          Object actual) {
	      if (equalsRegardingNull(unexpected, actual)) {
	          failEquals(message, actual);
	      }
	  }
	  static public void failEquals(String message, Object actual) {
	      String formatted = "Os valores devem ser diferentes. ";
	      if (message != null) {
	          formatted = message + ". ";
	      }

	      formatted += "Atual: " + actual;
	      fail(formatted);
	  }
	
}
