package com.karlssonkristoffer

import com.karlssonkristoffer.Generators._


class NodesSpec extends Spec {

  "Nodes.getCommonNodeNamesExceptBepa" - {
    "should return an empty list when the three only contain nodes with name 'Bepa'" in {
      val nodeInfo = randomOf(genNodeInfo).copy(nodeInfoName = NodeName("Bepa"))
      val tree = Tree(nodeInfo, Seq.empty)

      val result = Nodes.getCommonNodeNamesExceptBepa(tree, tree)
      result shouldBe Seq.empty
    }
    "should return all nodes from tree A when all tree A nodes are called 'test' and tree B is empty" in {
      val nodeInfoA = randomOf(genNodeInfo).copy(nodeInfoName = NodeName("test"))
      val nodeInfoB = randomOf(genNodeInfo)
      val treeA = Tree(nodeInfoA, Seq.empty)
      val treeB = Tree(nodeInfoB, Seq.empty)

      val result = Nodes.getCommonNodeNamesExceptBepa(treeA, treeB)
      result.length shouldBe 1
    }
  }
}
