import java.lang.reflect.Field;
//this code shows how to modify a private fied by using reflection and how
//to block this by using a securitymanager class

class Fine {
	private String str="David";
	Fine() {
		//this will prevent reflection
		System.setSecurityManager(new MySecurityManager());
	}
}

class MySecurityManager extends SecurityManager {
	@Override
	public void checkPackageAccess(String pkg){

	         // don't allow the use of the reflection package
	         if(pkg.equals("java.lang.reflect")){
	             throw new SecurityException("Reflection is not allowed!");
	         }
	     }
	}
public class MyClass {
	public static void main(String[] args) throws Exception {
		Class clazz = Fine.class;
		Object cc = clazz.newInstance();
		System.out.println("Creating an object of this class");
		Field f1 = cc.getClass().getDeclaredField("str");
		f1.setAccessible(true);
		f1.set(cc, "reflecting on life");
		
	}

}
