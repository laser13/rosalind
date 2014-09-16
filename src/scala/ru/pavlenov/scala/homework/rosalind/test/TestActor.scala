package ru.pavlenov.scala.homework.rosalind.test

import akka.actor.{ActorLogging, Actor}
import akka.actor.Actor.Receive

/**
 * ⓭ + 29
 * Какой сам? by Pavlenov Semen 06.09.14.
 */
class TestActor extends Actor with ActorLogging{

  override def receive: Receive = {

    case TestMessage(str) => sender() ! str

  }
}

case class TestMessage(str: String)
