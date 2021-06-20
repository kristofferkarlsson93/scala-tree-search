package com.karlssonkristoffer

import org.scalacheck._
import Gen._
import org.scalacheck.rng.Seed

object Generators {
  val genNodeName: Gen[NodeName] = for {
    name <- Gen.alphaStr
  } yield NodeName(name)

  val genCost: Gen[Cost] = for {
    cost <- Gen.posNum[Float]
  } yield Cost(cost)

  val genNodeInfo: Gen[NodeInfo] = for {
    cost <- genCost
    name <- genNodeName
  } yield NodeInfo(cost, name)

  def randomOf[T](generator: Gen[T]): T = generator.pureApply(Parameters.default, Seed.random())
}
