package de.upb.crypto.zeroknowledge.helpers;

import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Comparison;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Conjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative;
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter;
import de.upb.crypto.zeroknowledge.zeroKnowledge.ParameterList;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Power;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Product;
import de.upb.crypto.zeroknowledge.zeroKnowledge.StringLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Witness;
import de.upb.crypto.zeroknowledge.zeroKnowledge.WitnessList;
import java.util.Arrays;
import org.eclipse.emf.ecore.EObject;

@SuppressWarnings("all")
public class BranchState {
  private int depth;
  
  private EObject parent;
  
  private boolean inProof;
  
  private boolean inFunctionDefinition;
  
  private boolean inConjunction;
  
  private boolean inDisjunction;
  
  private boolean inComparison;
  
  private boolean inSum;
  
  private boolean inProduct;
  
  private boolean inPower;
  
  private boolean inPowerRightBranch;
  
  private boolean inNegative;
  
  private boolean inFunctionCall;
  
  private boolean comparisonBeforePropositional;
  
  public BranchState() {
    this.depth = 0;
    this.parent = null;
    this.inProof = false;
    this.inFunctionDefinition = false;
    this.inConjunction = false;
    this.inDisjunction = false;
    this.inComparison = false;
    this.inSum = false;
    this.inProduct = false;
    this.inPower = false;
    this.inPowerRightBranch = false;
    this.inNegative = false;
    this.inFunctionCall = false;
    this.comparisonBeforePropositional = false;
  }
  
  public BranchState(final BranchState state) {
    this.depth = state.depth;
    this.parent = state.parent;
    this.inProof = state.inProof;
    this.inFunctionDefinition = state.inFunctionDefinition;
    this.inConjunction = state.inConjunction;
    this.inDisjunction = state.inDisjunction;
    this.inComparison = state.inComparison;
    this.inSum = state.inSum;
    this.inProduct = state.inProduct;
    this.inPower = state.inPower;
    this.inPowerRightBranch = state.inPowerRightBranch;
    this.inNegative = state.inNegative;
    this.inFunctionCall = state.inFunctionCall;
    this.comparisonBeforePropositional = state.comparisonBeforePropositional;
  }
  
  public int getDepth() {
    return this.depth;
  }
  
  public void setDepth(final int depth) {
    this.depth = depth;
  }
  
  public EObject getParent() {
    return this.parent;
  }
  
  public void setParent(final EObject parent) {
    this.parent = parent;
  }
  
  public boolean hasProofAncestor() {
    return this.inProof;
  }
  
  public boolean hasFunctionDefinitionAncestor() {
    return this.inFunctionDefinition;
  }
  
  public boolean hasConjunctionAncestor() {
    return this.inConjunction;
  }
  
  public boolean hasDisjunctionAncestor() {
    return this.inDisjunction;
  }
  
  public boolean hasComparisonAncestor() {
    return this.inComparison;
  }
  
  public boolean hasSumAncestor() {
    return this.inSum;
  }
  
  public boolean hasProductAncestor() {
    return this.inProduct;
  }
  
  public boolean hasPowerAncestor() {
    return this.inPower;
  }
  
  public boolean isInPowerRightBranch() {
    return this.inPowerRightBranch;
  }
  
  public boolean hasNegativeAncestor() {
    return this.inNegative;
  }
  
  public boolean hasFunctionCallAncestor() {
    return this.inFunctionCall;
  }
  
  public void setProofAncestor(final boolean inProof) {
    this.inProof = inProof;
  }
  
  public void setFunctionDefinitionAncestor(final boolean inFunctionDefinition) {
    this.inFunctionDefinition = inFunctionDefinition;
  }
  
  public void setConjunctionAncestor(final boolean inConjunction) {
    this.inConjunction = inConjunction;
  }
  
  public void setDisjunctionAncestor(final boolean inDisjunction) {
    this.inDisjunction = inDisjunction;
  }
  
  public void setComparisonAncestor(final boolean inComparison) {
    this.inComparison = inComparison;
  }
  
  public void setSumAncestor(final boolean inSum) {
    this.inSum = inSum;
  }
  
  public void setProductAncestor(final boolean inProduct) {
    this.inProduct = inProduct;
  }
  
  public void setPowerAncestor(final boolean inPower) {
    this.inPower = inPower;
  }
  
  public void setInPowerRightBranch(final boolean inPowerRightBranch) {
    this.inPowerRightBranch = inPowerRightBranch;
  }
  
  public void setNegativeAncestor(final boolean inNegative) {
    this.inNegative = inNegative;
  }
  
  public void setFunctionCallAncestor(final boolean inFunctionCall) {
    this.inFunctionCall = inFunctionCall;
  }
  
  public void updateGeneral(final EObject node) {
    this.depth++;
    this.parent = node;
  }
  
  protected void _updateState(final Model model) {
    this.updateGeneral(model);
  }
  
  protected void _updateState(final FunctionDefinition function) {
    this.updateGeneral(function);
    this.setFunctionDefinitionAncestor(true);
  }
  
  protected void _updateState(final ParameterList parameterList) {
    this.updateGeneral(parameterList);
  }
  
  protected void _updateState(final Parameter parameter) {
    this.updateGeneral(parameter);
  }
  
  protected void _updateState(final WitnessList witnessList) {
    this.updateGeneral(witnessList);
  }
  
  protected void _updateState(final Witness witness) {
    this.updateGeneral(witness);
  }
  
  protected void _updateState(final Conjunction conjunction) {
    this.updateGeneral(conjunction);
    this.setConjunctionAncestor(true);
  }
  
  protected void _updateState(final Disjunction disjunction) {
    this.updateGeneral(disjunction);
    this.setDisjunctionAncestor(true);
  }
  
  protected void _updateState(final Comparison comparison) {
    this.updateGeneral(comparison);
    this.setComparisonAncestor(true);
  }
  
  protected void _updateState(final Sum sum) {
    this.updateGeneral(sum);
    this.setSumAncestor(true);
  }
  
  protected void _updateState(final Product product) {
    this.updateGeneral(product);
    this.setProductAncestor(true);
  }
  
  protected void _updateState(final Power power) {
    this.updateGeneral(power);
    this.setPowerAncestor(true);
  }
  
  protected void _updateState(final StringLiteral stringLiteral) {
    this.updateGeneral(stringLiteral);
  }
  
  protected void _updateState(final Tuple tuple) {
    this.updateGeneral(tuple);
  }
  
  protected void _updateState(final Negative negative) {
    this.updateGeneral(negative);
    this.setNegativeAncestor(true);
  }
  
  protected void _updateState(final FunctionCall call) {
    this.updateGeneral(call);
  }
  
  protected void _updateState(final Variable variable) {
    this.updateGeneral(variable);
  }
  
  protected void _updateState(final NumberLiteral number) {
    this.updateGeneral(number);
  }
  
  protected void _updateState(final Brackets brackets) {
    this.updateGeneral(brackets);
  }
  
  public String getPackageLiteral() {
    return this.getPackageLiteral();
  }
  
  public void updateState(final EObject brackets) {
    if (brackets instanceof Brackets) {
      _updateState((Brackets)brackets);
      return;
    } else if (brackets instanceof Comparison) {
      _updateState((Comparison)brackets);
      return;
    } else if (brackets instanceof Conjunction) {
      _updateState((Conjunction)brackets);
      return;
    } else if (brackets instanceof Disjunction) {
      _updateState((Disjunction)brackets);
      return;
    } else if (brackets instanceof FunctionCall) {
      _updateState((FunctionCall)brackets);
      return;
    } else if (brackets instanceof Negative) {
      _updateState((Negative)brackets);
      return;
    } else if (brackets instanceof NumberLiteral) {
      _updateState((NumberLiteral)brackets);
      return;
    } else if (brackets instanceof Power) {
      _updateState((Power)brackets);
      return;
    } else if (brackets instanceof Product) {
      _updateState((Product)brackets);
      return;
    } else if (brackets instanceof StringLiteral) {
      _updateState((StringLiteral)brackets);
      return;
    } else if (brackets instanceof Sum) {
      _updateState((Sum)brackets);
      return;
    } else if (brackets instanceof Tuple) {
      _updateState((Tuple)brackets);
      return;
    } else if (brackets instanceof Variable) {
      _updateState((Variable)brackets);
      return;
    } else if (brackets instanceof FunctionDefinition) {
      _updateState((FunctionDefinition)brackets);
      return;
    } else if (brackets instanceof Model) {
      _updateState((Model)brackets);
      return;
    } else if (brackets instanceof Parameter) {
      _updateState((Parameter)brackets);
      return;
    } else if (brackets instanceof ParameterList) {
      _updateState((ParameterList)brackets);
      return;
    } else if (brackets instanceof Witness) {
      _updateState((Witness)brackets);
      return;
    } else if (brackets instanceof WitnessList) {
      _updateState((WitnessList)brackets);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(brackets).toString());
    }
  }
}
