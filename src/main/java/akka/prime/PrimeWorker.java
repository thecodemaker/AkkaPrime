package akka.prime;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.prime.message.NumberRangeMessage;
import akka.prime.message.Result;

/**
 * Created by thecodemaker on 3/3/14.
 */
public class PrimeWorker extends UntypedActor {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof NumberRangeMessage) {
            NumberRangeMessage numberRangeMessage = ( NumberRangeMessage )message;
            logger.debug( "PrimeWorker receive(Number Rage: {} to {})", numberRangeMessage.getStartNumber(), numberRangeMessage.getEndNumber() );

            Result result = new Result();
            for(long l = numberRangeMessage.getStartNumber(); l <= numberRangeMessage.getEndNumber(); l++ ) {
                if( isPrime( l ) ) {
                    result.getResults().add( l );
                }
            }
            getSender().tell( result, getSelf() );
        } else {
            unhandled(message);
        }
    }

    private boolean isPrime(long n) {
        if( n == 1 || n == 2 || n == 3 ) {
            return true;
        }
        // Is n an even number?
        if( n % 2 == 0 ) {
            return false;
        }
        //if not, then just check the odds
        for( long i=3; i*i<=n; i+=2 ) {
            if( n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
