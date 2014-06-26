package LDAP;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class AddNewObjectClass {

	public static void main(String[] args) {
		
		 Hashtable env = new Hashtable();
	     env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	     env.put(Context.PROVIDER_URL, args[0]);
	     env.put(Context.SECURITY_AUTHENTICATION, "simple");
	     env.put(Context.SECURITY_PRINCIPAL, args[1]);
	     env.put(Context.SECURITY_CREDENTIALS, args[2]);
		try {
		 
		 DirContext	ctx = new InitialDirContext( env );
	     // Specify attributes for the schema object
	     Attributes attrs = new BasicAttributes(true); // Ignore case
	     attrs.put("NUMERICOID", "2.5.6.5.1.1.1.1");
	     attrs.put("NAME", "newClass");
	     attrs.put("DESC", "for JNDITutorial example only");
	     attrs.put("SUP", "top");
	     attrs.put("STRUCTURAL", "true");
	     Attribute must = new BasicAttribute("MUST", "ck");
	     must.add("objectclass");
	     attrs.put(must);

	     // Get the schema tree root
	     DirContext schema = ctx.getSchema("");

	     // Add the new schema object for "fooObjectClass"
	     DirContext newClass = schema.createSubcontext("ClassDefinition/newClass", attrs);
		}
	     catch(Exception ex){
	    	 ex.printStackTrace();
	     }
	}
}
