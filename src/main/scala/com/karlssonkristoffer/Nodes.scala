package com.karlssonkristoffer

object Nodes {

  def getCommonNodeNamesExceptBepa(firstTree: Tree, secondTree: Tree): Seq[NodeName] = {

    def treeNodesToSeq(tree: Tree, nodeList: Seq[NodeInfo]): Seq[NodeInfo] = {
      if (tree.trees.isEmpty) nodeList :+ tree.nodeInfo
      else {
        tree.trees.flatMap(child => treeNodesToSeq(child, nodeList :+ tree.nodeInfo))
      }
    }

    val firstTreesNodes = treeNodesToSeq(firstTree, Seq.empty)
    val secondTreesNodes = treeNodesToSeq(secondTree, Seq.empty)

    Seq.concat(firstTreesNodes, secondTreesNodes)
      .map(_.nodeInfoName)
      .filterNot(_.name == "Bepa")
  }
}
