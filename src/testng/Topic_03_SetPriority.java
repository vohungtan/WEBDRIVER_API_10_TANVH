package testng;

import org.testng.annotations.Test;

public class Topic_03_SetPriority {
  
  @Test(groups = "payment", enabled = true, description = "Create new Customer")
  public void TC_01_CreateNewCustomer() {
	  System.out.println("Run TESTCASE 01");
  }
  
  @Test(groups = "product")
  public void TC_02_EditCustomer() {
	  System.out.println("Run TESTCASE 02");
  }
  
  @Test(groups = "product", description = "Create new Account")
  public void TC_03_CreateNewAccount() {
	  System.out.println("Run TESTCASE 03");
  }

  @Test(groups = "payment", description = "Edit Account")
  public void TC_04_EditAccount() {
	  System.out.println("Run TESTCASE 04");
  }
  
  @Test(groups = "payment", description = "Deposit")
  public void TC_05_Deposit() {
	  System.out.println("Run TESTCASE 05");
  }
  
  @Test(groups = "users")
  public void TC_06_TransferMoney() {
	  System.out.println("Run TESTCASE 06");
  }
}
