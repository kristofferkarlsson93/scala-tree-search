package com.karlssonkristoffer

import com.karlssonkristoffer.Generators._


class NodesSpec extends Spec {

  "Nodes.getCommonNodeNamesExceptBepa" - {
    "should return an empty list when getting an empty tree" in {
      val nodeInfo = randomOf(genNodeInfo)
      val tree = Tree(nodeInfo, Seq.empty)

      val result = Nodes.getCommonNodeNamesExceptBepa(tree, tree)
      result shouldBe Seq.empty
    }
  }
}
