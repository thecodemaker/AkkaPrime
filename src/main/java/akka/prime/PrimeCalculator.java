package akka.prime;

import akka.actor.*;
import akka.prime.message.NumberRangeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by thecodemaker on 3/3/14.
 */
public class PrimeCalculator {

    private final Logger logger = LoggerFactory.getLogger(PrimeCalculator.class);

    public void calculate( long startNumber, long endNumber ) {

        logger.debug("PrimeCalculator(startNumber: {}, endNumber: {})", startNumber, endNumber);

        // Create our ActorSystem, which owns and configures the classes
        ActorSystem actorSystem = ActorSystem.create( "primeCalculator" );

        // Create our listener
        final ActorRef primeListener = actorSystem.actorOf( new Props( PrimeListener.class ), "primeListener" );

        // Create the PrimeMaster: we need to define an UntypedActorFactory so that we can control
        // how PrimeMaster instances are created (pass in the number of workers and listener reference
        ActorRef primeMaster = actorSystem.actorOf( new Props( new UntypedActorFactory() {
            public UntypedActor create() {
                return new PrimeMaster( 10, primeListener );
            }
        }), "primeMaster" );

        // Start the calculation
        primeMaster.tell( new NumberRangeMessage( startNumber, endNumber ), null);
    }
}
