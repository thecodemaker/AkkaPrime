package akka.prime;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.prime.message.NumberRangeMessage;
import akka.prime.message.Result;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActor;
import akka.testkit.TestProbe;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static akka.testkit.JavaTestKit.duration;
import static org.junit.Assert.assertEquals;

/**
 * User: thecodemaker
 * Date: 3/7/14
 * Time: 1:10 PM
 */
public class PrimeListenerTest {

    private static ActorSystem system;
    private static ActorRef primeWorker;
    private static TestProbe testProbe;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
        primeWorker = system.actorOf(Props.create(PrimeWorker.class), "primeWorker");
        testProbe = TestProbe.apply(system);
    }

    @AfterClass
    public static void teardown() {
        JavaTestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testOnReceiveNumberRangeMessage() throws Exception {

        primeWorker.tell(new NumberRangeMessage(1, 10), testProbe.ref());

        testProbe.expectMsgClass(Result.class);
        TestActor.Message message = testProbe.lastMessage();
        Result result = (Result) message.msg();
        assertEquals(result.getResults(), new ArrayList<>(Arrays.asList(1L, 2L, 3L, 5L, 7L)));
    }

    @Test
    public void testOnReceiveWrongTypeMessage() throws Exception {

        primeWorker.tell(new Object(), testProbe.ref());

        testProbe.expectNoMsg(duration("1 second"));
    }
}
