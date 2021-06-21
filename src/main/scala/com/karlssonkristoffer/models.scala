package com.karlssonkristoffer

import scala.annotation.tailrec

case class Cost(value: Float)

case class NodeName(name: String)

case class NodeInfo(cost: Cost, nodeInfoName: NodeName)

case class TypeB(cost: Cost, nodeName: NodeName, children: Seq[TypeB]) {
  def toTree: Tree = Tree(NodeInfo(cost, nodeName), children.map(_.toTree))
}

case class Tree(nodeInfo: NodeInfo, branches: Seq[Tree]) {

  def listNodes(): Seq[NodeInfo] = {
    def treeNodesToSeq(tree: Tree, nodeList: Seq[NodeInfo]): Seq[NodeInfo] = {
      if (tree.branches.isEmpty) nodeList :+ tree.nodeInfo
      else {
        tree.branches.flatMap(branch => treeNodesToSeq(branch, nodeList :+ tree.nodeInfo))
      }
    }
    treeNodesToSeq(this, Seq.empty)
  }

  def find(nodeName: NodeName): Option[NodeInfo] = {
    @tailrec
    def searchInBranches(nodeBranches: Seq[Tree]): Option[Tree] = {
      nodeBranches match {
        case Nil => None
        case head :: tail =>
          if (head.nodeInfo.nodeInfoName == nodeName) Some(head)
          else searchInBranches(tail ++ head.branches)
      }
    }
    searchInBranches(Seq(this)).map(_.nodeInfo)
  }
}

object Tree {

  def apply(typeB: TypeB): Tree = typeB.toTree
}
