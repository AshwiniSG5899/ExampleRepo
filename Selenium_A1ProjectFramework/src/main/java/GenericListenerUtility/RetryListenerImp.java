package GenericListenerUtility;

import org.testng.ITestResult;

public class RetryListenerImp {

	int Count=0;
	int limitCount=5;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean retry(ITestResult result) {
		if(Count<limitCount)
		{
			Count++;
			return true;
		}
		else {
		return false;
		}
	}
}
