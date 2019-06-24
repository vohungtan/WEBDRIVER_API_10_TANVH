package testng;

import org.testng.annotations.Test;

public class Topic_02_Group {
  
  @Test(groups = "payment")
  public void TC_01() {
	  System.out.println("Run TESTCASE 01");
  }
  
  @Test(groups = "product")
  public void TC_02() {
	  System.out.println("Run TESTCASE 02");
  }
  
  @Test(groups = "product")
  public void TC_03() {
	  System.out.println("Run TESTCASE 03");
  }

  @Test(groups = "payment")
  public void TC_04() {
	  System.out.println("Run TESTCASE 04");
  }
  
  @Test(groups = "payment")
  public void TC_05() {
	  System.out.println("Run TESTCASE 05");
  }
  
  @Test(groups = "users")
  public void TC_06() {
	  System.out.println("Run TESTCASE 06");
  }
}
