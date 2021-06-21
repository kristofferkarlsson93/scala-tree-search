package com.karlssonkristoffer

import com.karlssonkristoffer.Generators._

class NodesSpec extends Spec {

  "Nodes.getCommonNodeNamesExceptBepa" - {
    "trees with only 1 level" - {
      "should return an empty list when the threes only contain nodes with name 'Bepa'" in {
        val nodeInfo = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("Bepa"))
        val tree = Tree(nodeInfo, Seq.empty)

        val result = Nodes.getCommonNodeNamesExceptBepa(tree, tree)
        result shouldBe Seq.empty
      }
      "should return matching 1 node name when first node name in each three match" in {
        val nodeInfoA = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("test"))
        val nodeInfoB = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("test"))
        val treeA = Tree(nodeInfoA, Seq.empty)
        val treeB = Tree(nodeInfoB, Seq.empty)

        val result = Nodes.getCommonNodeNamesExceptBepa(treeA, treeB)
        result.length shouldBe 1
      }
      "should return results with the name 'bepa' (lower case) when both trees has that node" in {
        val nodeInfo = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("bepa"))
        val treeA = Tree(nodeInfo, Seq.empty)
        val treeB = Tree(nodeInfo, Seq.empty)

        val result = Nodes.getCommonNodeNamesExceptBepa(treeA, treeB)
        result.length shouldBe 1
      }
      "should return an empty list when tree A and B has no nodes with same name" in {
        val nodeInfoA = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("A"))
        val nodeInfoB = randomOf(GenNodeInfo).copy(nodeInfoName = NodeName("B"))
        val treeA = Tree(nodeInfoA, Seq.empty)
        val treeB = Tree(nodeInfoB, Seq.empty)

        val result = Nodes.getCommonNodeNamesExceptBepa(treeA, treeB)
        result.length shouldBe 0
      }
    }
    "trees with multiple levels" - {
      "should return the matching node name when first tree and second tree both contains 'Third'" in {
        val treeA = Tree(
          NodeInfo(Cost(0), NodeName("First")),
          Seq(
            Tree(
              NodeInfo(Cost(0), NodeName("Second")),
              Seq(
                Tree(NodeInfo(Cost(0), NodeName("Third")), Seq.empty)
              )
            )
          )
        )
        val treeB = Tree(
          NodeInfo(Cost(0), NodeName("A")),
          Seq(
            Tree(
              NodeInfo(Cost(0), NodeName("B")),
              Seq(
                Tree(NodeInfo(Cost(0), NodeName("Third")), Seq.empty)
              )
            )
          )
        )
        val result = Nodes.getCommonNodeNamesExceptBepa(treeA, treeB)
        result.length shouldBe 1
        result.head.name shouldBe "Third"
      }
      "should return an empty list when tree A has two nodes with same name" in {
        val treeA = Tree(
          NodeInfo(Cost(0), NodeName("First")),
          Seq(
            Tree(
              NodeInfo(Cost(0), NodeName("Second")),
              Seq(
                Tree(NodeInfo(Cost(0), NodeName("Second")), Seq.empty)
              )
            )
          )
        )
        val treeB = Tree(NodeInfo(Cost(0), NodeName("A")), Seq.empty)
        val result = Nodes.getCommonNodeNamesExceptBepa(treeA, treeB)
        result.length shouldBe 0
      }
      "should work with TypeB as well" in {
        val treeA = Tree(
          TypeB(
            Cost(0),
            NodeName("First"),
            Seq(
              TypeB(
                Cost(0),
                NodeName("Second"),
                Seq(
                  TypeB(Cost(0), NodeName("Third"), Seq.empty)
                )
              )
            )
          )
        )
        val treeB = Tree(
          TypeB(
            Cost(0),
            NodeName("A"),
            Seq(
              TypeB(
                Cost(0),
                NodeName("B"),
                Seq(
                  TypeB(Cost(0), NodeName("Third"), Seq.empty)
                )
              )
            )
          )
        )
        val result = Nodes.getCommonNodeNamesExceptBepa(treeA, treeB)
        result.length shouldBe 1
      }
    }
  }
}
