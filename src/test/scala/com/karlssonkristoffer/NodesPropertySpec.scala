package com.karlssonkristoffer

import com.karlssonkristoffer.Generators.{intBetween1And100, randomOf, GenTreeOfDept}
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class NodesPropertySpec extends Spec with ScalaCheckPropertyChecks {

  "getCommonNodeNamesExceptBepa" - {
    "for any 2 trees should return common nodes" in {
      forAll(GenTreeOfDept(randomOf(intBetween1And100)), GenTreeOfDept(randomOf(intBetween1And100))) { (firstTree, secondTree) =>
        val secondTreeHasCommonNodeWithFirstTree = {
          val secondTreeNodes = secondTree.listNodes().map(_.nodeInfoName)
          firstTree.listNodes().map(_.nodeInfoName).exists(n => secondTreeNodes.contains(n))
        }
        whenever(secondTreeHasCommonNodeWithFirstTree) {
          true shouldBe true
        }
      }
    }
  }

}
