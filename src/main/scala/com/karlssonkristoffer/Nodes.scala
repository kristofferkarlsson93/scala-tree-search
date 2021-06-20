package com.karlssonkristoffer

object Nodes {

  def getCommonNodeNamesExceptBepa(firstTree: Tree, secondTree: Tree): Seq[NodeName] = {
    if (firstTree.nodeInfo.nodeInfoName.name != "Bepa") {
      Seq(firstTree.nodeInfo.nodeInfoName)
    } else Seq.empty
  }
}
