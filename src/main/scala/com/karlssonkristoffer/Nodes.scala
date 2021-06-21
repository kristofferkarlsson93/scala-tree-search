package com.karlssonkristoffer

object Nodes {

  def getCommonNodeNamesExceptBepa(firstTree: Tree, secondTree: Tree): Seq[NodeName] = {

    val uniqueNodesFromFirstTree = firstTree.listNodes().distinct
    val uniqueNodesFromSecondTree = secondTree.listNodes().distinct

    val duplicateNodeNames = Seq
      .concat(uniqueNodesFromFirstTree, uniqueNodesFromSecondTree)
      .groupBy(_.nodeInfoName)
      .collect { case (nodeName, nodesWithName) if nodesWithName.length > 1 => nodeName }
      .toSeq

    duplicateNodeNames
      .filterNot(_.name == "Bepa")
  }
}
