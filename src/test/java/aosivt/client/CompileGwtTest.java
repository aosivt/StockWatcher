package aosivt.client;

import com.google.gwt.junit.client.GWTTestCase;

public class CompileGwtTest extends GWTTestCase {
  
  @Override
  public String getModuleName() {
    return "aosivt.StockWatcher";
  }

  public void testSandbox() {
    assertTrue(true);
  }
  
}
