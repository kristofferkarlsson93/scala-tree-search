package com.karlssonkristoffer

import com.karlssonkristoffer.Generators.{randomOf, GenNodeInfo}

class modelsSpec extends Spec {

  "Tree" - {
    ".find()" - {
      "should return None when tree only has 1 node and it does not match" in {
        val nodeInfo = randomOf(GenNodeInfo)
        val tree = Tree(nodeInfo, Seq.empty)
        val result = tree.find(NodeName("don't exist"))
        result shouldBe None
      }
      "should return the node info when tree only has 1 node and that match" in {
        val nodeInfo = randomOf(GenNodeInfo)
        val tree = Tree(nodeInfo, Seq.empty)
        val result = tree.find(nodeInfo.nodeInfoName)
        result.get shouldBe nodeInfo
      }
      "should return the node info when tree has several layers and 1 matching node" in {
        val nodeToFind = NodeInfo(Cost(0), NodeName("Second"))
        val tree = Tree(
          NodeInfo(Cost(0), NodeName("First")),
          Seq(
            Tree(
              nodeToFind,
              Seq(
                Tree(NodeInfo(Cost(0), NodeName("Third")), Seq.empty)
              )
            )
          )
        )
        val result = tree.find(nodeToFind.nodeInfoName)
        result.get shouldBe nodeToFind
      }
    }
  }
}
