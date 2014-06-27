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
		 Attribute att= new BasicAttribute("objectclass");
		 att.add("dc=mms,dc=com");
		 att.add("ou=brooklyn");
		 att.add("cn=Tim");
		 DirContext	ctx = new InitialDirContext( env );
	     // Specify attributes for the schema object
	     Attributes attrs = new BasicAttributes(true); // Ignore case
	     //attrs.put("NUMERICOID", "2.5.6.5.1.1");
	     attrs.put(att);
	     //attrs.put("givenname", "jack");
	     
	     //For OrganizationalUnit/ou
	     //attrs.put("objectClass", "organizationalUnit");
	     
	     //For Users/cn objectClass should be
	     attrs.put("objectClass", "inetOrgPerson");
	     
	     //For Users/cn add this extra attributes
	     attrs.put("cn", "Tim");
	     attrs.put("sn","Tim");

	     DirContext newClass = ctx.createSubcontext("cn=Tim,ou=brooklyn,dc=mms,dc=com", attrs);
	     }
	     catch(Exception ex){
	    	 ex.printStackTrace();
	     }
	}
}