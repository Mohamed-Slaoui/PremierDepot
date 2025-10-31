package HelloWorldServer;
import org.omg.CORBA.ORB;
import HelloWorldApp.HelloPOA;

public class HelloServant extends HelloPOA{
    private String message = "Hello world";
    private ORB orb;

    public void setOrb(ORB orb){
        this.orb = orb;
    }

    public String HelloMessage() {};
    public void HelloMessage(String newHelloMessage) {
        this.message = newHelloMessage;
    };
}
