package LDAP;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.NameAlreadyBoundException;
import javax.naming.directory.*;
import java.util.*;

public class MakeRoot {

        //final static String rootContext = "ou=groups";
        
        public static void main( String[] args ) {
        		DirContext ctx = null;
        		// set up environment to access the server
                Properties env = new Properties();
                env.put( Context.INITIAL_CONTEXT_FACTORY,
                         "com.sun.jndi.ldap.LdapCtxFactory" );
                env.put( Context.PROVIDER_URL, args[0]);
                env.put( Context.SECURITY_PRINCIPAL, args[1] );
                env.put( Context.SECURITY_CREDENTIALS, args[2] );
                Attributes attrs = new BasicAttributes(true); // ignore case               
                Attribute objclass = new BasicAttribute("ou=newou");
                objclass.add("top");
                objclass.add("organizationalUnit");
                attrs.put(objclass);
                //attrs.put("ou", "newou");
                try {
                    // obtain initial directory context using the environment
                    ctx = new InitialDirContext( env );
                    DirContext schema = ctx.getSchema("");
                    //ctx.bind("ou=newou,dc=mms,dc=com", null, attrs);
                    //ctx.createSubcontext("ou=newou", attrs);
                    schema.createSubcontext("ou=newou,dc=mms,dc=com", attrs);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}               
               catch(Exception e){
                   // now, create the root context, which is just a subcontext
                   // of this initial directory context.
            	   e.printStackTrace();
               }
               
        }
}