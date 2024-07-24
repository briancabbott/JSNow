package org.antlr.v5.runtime.core.transition

import org.antlr.v5.runtime.core.context.SemanticContext
import org.antlr.v5.runtime.core.state.ATNState

/**
 * TODO: this is old comment
 *
 * A tree of semantic predicates from the grammar AST if `label == SEMPRED`.
 *
 * In the ATN, labels will always be exactly one predicate, but the DFA
 * may have to combine a bunch of them as it collects predicates from
 * multiple ATN configurations into a single DFA state.
 */
public class PredicateTransition(
    target: ATNState,
    public val ruleIndex: Int,
    public val predIndex: Int,
    public val isCtxDependent: Boolean,  // e.g., $i ref in pred
) : AbstractPredicateTransition(target) {
  override val serializationType: Int =
    PREDICATE

  override val isEpsilon: Boolean =
    true

  public val predicate: SemanticContext.Predicate
    get() = SemanticContext.Predicate(ruleIndex, predIndex, isCtxDependent)

  override fun matches(symbol: Int, minVocabSymbol: Int, maxVocabSymbol: Int): Boolean =
    false

  override fun toString(): String =
    "pred_$ruleIndex:$predIndex"
}