package org.study.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

/**
 * 임의의 문자열 혹은 "ping" 메시지를 받으면 "pong" 메시지를 보내는 퐁액터
 * @author Baekjun Lim
 */
public class PongActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef ping; 
	
	public PongActor(ActorRef ping) {
		this.ping = ping;
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
	       	String msg = (String)message;
	       	log.info("Pong received {}", msg);
	       	ping.tell("pong", getSelf());
            //Thread.sleep(1000); // 실전에서는 절대 금물!!!
		}
	}

	@Override
	public void postStop() throws Exception {
		log.info("postStop");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		log.info("preRestart");
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		log.info("postRestart");
	}
	
}
