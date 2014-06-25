package LDAP;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;

/**
 * 
 * @author adminuser
 *
 */
public class LdapBasicExample {

    public static void main(String[] args) {
        String userName = "AWUser1";
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://108.168.183.180/cn=admin,dc=mms,dc=com");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=mms,dc=com");
        env.put(Context.SECURITY_CREDENTIALS, "ibm4ever");

        DirContext ctx = null;
        NamingEnumeration results = null;
        try {
            ctx = new InitialDirContext(env);
            System.out.println("Get name in namespace:::::::::: "+ctx.getNameInNamespace());  
            //Attributes attr1 = ctx.getAttributes("cn=alice,ou=users,dc=mms,dc=com");
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            //results = ctx.search("", "(objectClass=*)", controls);
            results = ctx.search("", "(objectClass=*)", controls);
        	if(results.hasMoreElements()){
        		while (results.hasMore()) {
                    SearchResult searchResult = (SearchResult) results.next();
                    Attributes attributes = searchResult.getAttributes();
                    System.out.println("Attributes.............."+ attributes); 
                    System.out.println(" CN = " + attributes.get("cn"));
                }
        	}else{
        		System.out.println("No results found");
        	}
            
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                }
            }
        }
    }
}