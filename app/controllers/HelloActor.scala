package controllers

import akka.actor.{ActorSystem, Actor}

import com.google.inject.Inject
import controllers.HelloActor.Hello
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class HelloActor @Inject() (actorSystem: ActorSystem) extends Actor {
  override def receive: Receive = {
    case Hello =>
      try {
        println(s"Just received Hello message. Scheduling a new message...")
        // other commands
      }
      catch {
        case c: Exception => c.printStackTrace()
      }
      actorSystem.scheduler.scheduleOnce(5.seconds, self, Hello)
    case _ => throw new IllegalStateException("Unknown message type")
  }
}

object HelloActor {
  case object Hello
}
