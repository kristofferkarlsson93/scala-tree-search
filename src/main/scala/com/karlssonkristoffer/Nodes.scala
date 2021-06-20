package com.karlssonkristoffer

object Nodes {

  def getCommonNodeNamesExceptBepa(firstTree: Tree, secondTree: Tree): Seq[NodeName] = {

    def treeNodesToSeq(trees: Seq[Tree], nodeList: Seq[NodeInfo]): Seq[NodeInfo] = {
      if (trees.isEmpty) nodeList
      else {
        trees.flatMap { (childTree: Tree) =>
          treeNodesToSeq(
            childTree.trees,
            Seq.concat(nodeList, Seq(childTree.nodeInfo))
          )
        }
      }
    }

    val firstRest = treeNodesToSeq(firstTree.trees, Seq.empty).map(_.nodeInfoName)
    val secondRest = treeNodesToSeq(secondTree.trees, Seq.empty).map(_.nodeInfoName)

    val firstTreeResult = firstTree.nodeInfo.nodeInfoName
    val secondTreeResult = secondTree.nodeInfo.nodeInfoName

    Seq(firstTreeResult, secondTreeResult).concat(firstRest).concat(secondRest).filterNot(_.name == "Bepa")
  }
}
