package HelloWorldServer;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.*;

import HelloWorldApp.Hello;
import HelloWorldApp.HelloHelper;

public class Main {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            HelloServant helloServant = new HelloServant();
            helloServant.setOrb(orb);
            Object ref = rootpoa.servant_to_reference(helloServant);
            Hello href = HelloHelper.narrow(ref);

            Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Hello";

            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, objRef);

            System.out.println("HelloWorldServer ready and waiting....");

            for (;;) {
                orb.run();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
