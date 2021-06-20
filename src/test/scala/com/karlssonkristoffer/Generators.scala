package com.karlssonkristoffer

import org.scalacheck._
import Gen._
import org.scalacheck.rng.Seed
import org.scalacheck.ScalacheckShapeless._
import org.scalacheck.derive.Recursive

object Generators {
  val GenNodeName: Gen[NodeName] = for {
    name <- Gen.alphaStr
  } yield NodeName(name)

  val GenCost: Gen[Cost] = for {
    cost <- Gen.posNum[Float]
  } yield Cost(cost)

  val GenNodeInfo: Gen[NodeInfo] = for {
    cost <- GenCost
    name <- GenNodeName
  } yield NodeInfo(cost, name)


//
//  val GenEmptyTree: Gen[Tree] = for {
//    nodeInfo <- GenNodeInfo
//  } yield Tree(nodeInfo, Seq.empty)
//
//  val treeRecursive: Recursive[Tree] = derive.Recursive[Tree](GenEmptyTree)

  def randomOf[T](generator: Gen[T]): T = generator.pureApply(Parameters.default, Seed.random())
}
