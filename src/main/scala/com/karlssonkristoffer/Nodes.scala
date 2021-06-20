package com.karlssonkristoffer

object Nodes {

  def getCommonNodeNamesExceptBepa(firstTree: Tree, secondTree: Tree): Seq[NodeName] = {
    val firstTreeResult = firstTree.nodeInfo.nodeInfoName
    val secondTreeResult = secondTree.nodeInfo.nodeInfoName
    Seq(firstTreeResult, secondTreeResult).filterNot(_.name == "Bepa")
  }
}
