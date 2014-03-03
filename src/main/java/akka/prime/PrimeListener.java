package akka.prime;

import akka.actor.UntypedActor;
import akka.prime.message.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by thecodemaker on 3/3/14.
 */
public class PrimeListener extends UntypedActor {

    private final Logger logger = LoggerFactory.getLogger(PrimeListener.class);

    @Override
    public void onReceive(Object message) throws Exception {
        if( message instanceof Result) {
            Result result = ( Result )message;

            StringBuilder s = new StringBuilder();
            for( Long value : result.getResults() ) {
                s.append(value + ",");
            }
            logger.debug("Results: {}", s);
            // Exit
            getContext().system().shutdown();
        } else {
            unhandled( message );
        }
    }
}
