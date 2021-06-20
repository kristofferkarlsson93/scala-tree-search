package com.karlssonkristoffer

import scala.annotation.tailrec

case class Cost(value: Float)

case class NodeName(name: String)

case class NodeInfo(cost: Cost, nodeInfoName: NodeName)

case class TypeB(cost: Cost, nodeName: NodeName, children: Seq[TypeB]) {
  def toTree: Tree = Tree(NodeInfo(cost, nodeName), children.map(_.toTree))
}

case class Tree(nodeInfo: NodeInfo, trees: Seq[Tree])

object Tree {
  @tailrec
  def apply(nodeInfo: NodeInfo, trees: Seq[Tree]): Tree = {
    Tree(nodeInfo, trees)
  }

  def apply(typeB: TypeB): Tree = typeB.toTree
}