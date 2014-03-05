package akka.prime;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.prime.message.Result;

/**
 * Created by thecodemaker on 3/3/14.
 */
public class PrimeListener extends UntypedActor {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

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
