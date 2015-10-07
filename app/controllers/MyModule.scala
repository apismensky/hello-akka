package controllers

import akka.actor.{ActorRef, ActorSystem}
import com.google.inject.name.Named
import com.google.inject.{Inject, Singleton, AbstractModule}
import controllers.HelloActor.Hello
import play.api.libs.concurrent.AkkaGuiceSupport

class MyModule extends AbstractModule with AkkaGuiceSupport  {
  override def configure(): Unit = {
    bindActor[HelloActor]("helloActor")
    bind(classOf[MessageInit]).asEagerSingleton()
  }
}

@Singleton
class MessageInit @Inject()(actorSystem: ActorSystem,
                            @Named("helloActor") helloActor: ActorRef) {
  println("Starting messages...")
  helloActor ! Hello
}
