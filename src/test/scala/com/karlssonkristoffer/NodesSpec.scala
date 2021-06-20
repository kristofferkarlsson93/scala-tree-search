package com.karlssonkristoffer

import com.karlssonkristoffer.Generators._


class NodesSpec extends Spec {

  "Nodes.getCommonNodeNamesExceptBepa" - {
    "trees with only 1 level" - {
      "should return an empty list when the three only contain nodes with name 'Bepa'" in {
        val nodeInfo = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("Bepa"))
        val tree = Tree(nodeInfo, Seq.empty)

        val result = Nodes.getCommonNodeNamesExceptBepa(tree, tree)
        result shouldBe Seq.empty
      }
      "should return all nodes from tree A and B when both trees has node names that is not 'Bepa'" in {
        val nodeInfoA = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("test"))
        val nodeInfoB = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("test"))
        val treeA = Tree(nodeInfoA, Seq.empty)
        val treeB = Tree(nodeInfoB, Seq.empty)

        val result = Nodes.getCommonNodeNamesExceptBepa(treeA, treeB)
        result.length shouldBe 2
      }
      "should return results with the name 'bepa' (lower case)" in {
        val nodeInfo = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("bepa"))
        val treeA = Tree(nodeInfo, Seq.empty)
        val treeB = Tree(nodeInfo, Seq.empty)

        val result = Nodes.getCommonNodeNamesExceptBepa(treeA, treeB)
        result.length shouldBe 2
      }
    }
    "trees with multiple levels" - {
      "should find all nodes when none contains 'Bepa'" in {
        val tree = Tree(NodeInfo(Cost(0), NodeName("First")), Seq(
          Tree(NodeInfo(Cost(0), NodeName("Second")), Seq(
            Tree(NodeInfo(Cost(0), NodeName("Third")), Seq.empty)
          ))
        ))
        val result = Nodes.getCommonNodeNamesExceptBepa(tree, Tree(NodeInfo(Cost(0), NodeName("Other tree")), Seq.empty))
        result.length shouldBe 4
      }
      "should work with TypeB as well" in {
        val tree = Tree(TypeB(Cost(0), NodeName("First"), Seq(
          TypeB(Cost(0), NodeName("Second"), Seq(
            TypeB(Cost(0), NodeName("Third"), Seq.empty)
          )))
        ))
        val result = Nodes.getCommonNodeNamesExceptBepa(tree, Tree(NodeInfo(Cost(0), NodeName("Other tree")), Seq.empty))
        result.length shouldBe 4
      }
    }
  }
}
