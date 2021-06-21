package com.karlssonkristoffer

import org.scalacheck._
import Gen._
import org.scalacheck.rng.Seed
import org.scalacheck.derive.Recursive

object Generators {

  val GenNodeName: Gen[NodeName] = for {
    name <- Gen.alphaStr
  } yield NodeName(name)

  val GenCost: Gen[Cost] = for {
    cost <- Gen.choose[Float](0, Float.MaxValue)
  } yield Cost(cost)

  val GenNodeInfo: Gen[NodeInfo] = for {
    cost <- GenCost
    name <- GenNodeName
  } yield NodeInfo(cost, name)

  val GenEmptyTree: Gen[Tree] = for {
    nodeInfo <- GenNodeInfo
  } yield Tree(nodeInfo, Seq.empty)

  def GenTreeOfDept(depth: Int): Gen[Tree] = {
    def go(limit: Int, res: Seq[Tree]): Gen[Tree] = {
      if (limit == 0) GenEmptyTree
      else
        for {
          nodeInfo <- GenNodeInfo
        } yield Tree(nodeInfo, Seq(randomOf(go(limit - 1, res))))
    }

    go(depth, Seq.empty)
  }

  val intBetween1And100: Gen[Int] = Gen.choose[Int](0, 100)

  def randomOf[T](generator: Gen[T]): T = generator.pureApply(Parameters.default, Seed.random())
}
