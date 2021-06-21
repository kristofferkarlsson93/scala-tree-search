package com.karlssonkristoffer

object Nodes {

  def getCommonNodeNamesExceptBepa(firstTree: Tree, secondTree: Tree): Seq[NodeName] = {

    def treeNodesToSeq(tree: Tree, nodeList: Seq[NodeInfo]): Seq[NodeInfo] = {
      if (tree.branches.isEmpty) nodeList :+ tree.nodeInfo
      else {
        tree.branches.flatMap(child => treeNodesToSeq(child, nodeList :+ tree.nodeInfo))
      }
    }

    val firstTreesNodes = treeNodesToSeq(firstTree, Seq.empty)
    val secondTreesNodes = treeNodesToSeq(secondTree, Seq.empty)
    val allNodes = Seq.concat(firstTreesNodes, secondTreesNodes)
    
    val nodeNamesThatOccuresMoreThanOnce = allNodes.groupBy(_.nodeInfoName).collect {
      case (nodeName, nodesWithName) if nodesWithName.length > 1 => nodeName
    }.toSeq

    nodeNamesThatOccuresMoreThanOnce
      .filterNot(_.name == "Bepa")
  }
}
