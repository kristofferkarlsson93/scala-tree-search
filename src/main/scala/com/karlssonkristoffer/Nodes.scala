package com.karlssonkristoffer

object Nodes {

  def getCommonNodeNamesExceptBepa(firstTree: Tree, secondTree: Tree): Seq[NodeName] = {

    def treeNodesToSeq(tree: Tree, nodeList: Seq[NodeInfo]): Seq[NodeInfo] = {
      if (tree.branches.isEmpty) nodeList :+ tree.nodeInfo
      else {
        tree.branches.flatMap(branch => treeNodesToSeq(branch, nodeList :+ tree.nodeInfo))
      }
    }

    val uniqueNodesFromFirstTree = treeNodesToSeq(firstTree, Seq.empty).distinct
    val uniqueNodesFromSecondTree = treeNodesToSeq(secondTree, Seq.empty).distinct

    val duplicateNodeNames = Seq
      .concat(uniqueNodesFromFirstTree, uniqueNodesFromSecondTree)
      .groupBy(_.nodeInfoName)
      .collect { case (nodeName, nodesWithName) if nodesWithName.length > 1 => nodeName }
      .toSeq

    duplicateNodeNames
      .filterNot(_.name == "Bepa")
  }
}
