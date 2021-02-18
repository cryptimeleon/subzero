package de.upb.crypto.zeroknowledge.latex;

import com.google.common.base.Objects;
import de.upb.crypto.zeroknowledge.model.ModelHelper;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Argument;
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * Converts a syntax tree to valid LaTeX output
 * 
 * Precondition: the Model object used to create the LatexPreview object
 * must be free of validation errors
 */
@SuppressWarnings("all")
public class LatexPreview {
  private String latexCode;
  
  private StringBuilder builder;
  
  private static final String CONJUNCTION = "\\land";
  
  private static final String DISJUNCTION = "\\lor";
  
  private static final String EQUAL = "=";
  
  private static final String INEQUAL = "\\neq";
  
  private static final String LESS = "<";
  
  private static final String GREATER = ">";
  
  private static final String LESSEQUAL = "\\leq";
  
  private static final String GREATEREQUAL = "\\geq";
  
  private static final String ADDITION = "+";
  
  private static final String SUBTRACTION = "-";
  
  private static final String MULTIPLICATION = "\\cdot";
  
  private static final String DIVISION = "\\frac";
  
  private static final String EXPONENTIATION = "^";
  
  private static final String NEGATION = "-";
  
  private static final String NEWLINE = "\\\\";
  
  private static final String SPACE = " ";
  
  private static final String COMMA = ",";
  
  private static final String COLON = ":";
  
  private static final String SEMICOLON = ";";
  
  private static final String QUOTE = "\'";
  
  private static final String LEFTPAREN = "(";
  
  private static final String RIGHTPAREN = ")";
  
  private static final String LEFTBRACE = "{";
  
  private static final String RIGHTBRACE = "}";
  
  private static final String DELIMITER = "$$";
  
  private static final String OPERATOR_ADDITION = "+";
  
  private static final String OPERATOR_SUBTRACTION = "-";
  
  private static final String OPERATOR_MULTIPLICATION = "*";
  
  private static final String OPERATOR_DIVISION = "/";
  
  private static final String OPERATOR_EXPONENTIATION = "^";
  
  private static final String OPERATOR_EQUAL = "=";
  
  private static final String OPERATOR_INEQUAL = "!=";
  
  private static final String OPERATOR_LESS = "<";
  
  private static final String OPERATOR_GREATER = ">";
  
  private static final String OPERATOR_LESSEQUAL = "<=";
  
  private static final String OPERATOR_GREATEREQUAL = ">=";
  
  private int openBraces = 0;
  
  private boolean inlineFunctions;
  
  public LatexPreview(final Model model) {
    this.constructLatexPreview(model, false);
  }
  
  public LatexPreview(final Model model, final boolean inline) {
    this.constructLatexPreview(model, inline);
  }
  
  private void constructLatexPreview(final Model model, final boolean inline) {
    StringBuilder _stringBuilder = new StringBuilder();
    this.builder = _stringBuilder;
    this.inlineFunctions = inline;
    if (inline) {
      ModelHelper.inlineFunctions(model);
    }
    this.builder.append(LatexPreview.DELIMITER);
    this.generateLatex(model);
    this.builder.append(LatexPreview.DELIMITER);
    this.latexCode = this.builder.toString();
  }
  
  public String getRawLatex() {
    return this.latexCode;
  }
  
  private String formatIdentifier(final String name) {
    int _indexOf = name.indexOf("_");
    boolean _greaterThan = (_indexOf > 0);
    if (_greaterThan) {
      int _length = name.length();
      int _minus = (_length - 1);
      char _charAt = name.charAt(_minus);
      boolean _equals = Objects.equal(Character.valueOf(_charAt), "\'");
      if (_equals) {
        return name.replace("_", "_{").replaceFirst("\'", "}\'");
      } else {
        String _replace = name.replace("_", "_{");
        return (_replace + "}");
      }
    }
    return name;
  }
  
  private void generateBraces(final EObject node) {
    this.builder.append(LatexPreview.LEFTBRACE);
    this.generateLatex(node);
    this.builder.append(LatexPreview.RIGHTBRACE);
  }
  
  private void generateOperator(final String operator) {
    this.builder.append(LatexPreview.SPACE);
    this.builder.append(operator);
    this.builder.append(LatexPreview.SPACE);
  }
  
  private void generateList(final EList<? extends EObject> items) {
    this.builder.append(LatexPreview.LEFTPAREN);
    boolean isFirstItem = true;
    for (final EObject item : items) {
      {
        if (isFirstItem) {
          isFirstItem = false;
        } else {
          this.builder.append(LatexPreview.COMMA);
        }
        this.generateLatex(item);
      }
    }
    this.builder.append(LatexPreview.RIGHTPAREN);
  }
  
  private void _generateLatex(final EObject node) {
    System.err.println("Unhandled EObject type in Latex generation function");
  }
  
  private void _generateLatex(final Model model) {
    if ((!this.inlineFunctions)) {
      EList<FunctionDefinition> _functions = model.getFunctions();
      for (final FunctionDefinition function : _functions) {
        {
          this.generateLatex(function);
          this.builder.append(LatexPreview.NEWLINE);
          this.builder.append(LatexPreview.NEWLINE);
        }
      }
    }
    this.generateLatex(model.getWitnessList());
    this.builder.append(LatexPreview.SEMICOLON);
    this.builder.append(LatexPreview.NEWLINE);
    this.generateLatex(model.getProof());
  }
  
  private void _generateLatex(final FunctionDefinition function) {
    this.builder.append(function.getName());
    this.generateLatex(function.getParameterList());
    this.builder.append(LatexPreview.COLON);
    this.builder.append(LatexPreview.NEWLINE);
    this.generateLatex(function.getBody());
  }
  
  private void _generateLatex(final ParameterList parameterList) {
    this.generateList(parameterList.getParameters());
  }
  
  private void _generateLatex(final Parameter parameter) {
    this.builder.append(parameter.getName());
  }
  
  private void _generateLatex(final WitnessList witnessList) {
    this.generateList(witnessList.getWitnesses());
  }
  
  private void _generateLatex(final Witness witness) {
    this.builder.append(this.formatIdentifier(witness.getName()));
  }
  
  private void _generateLatex(final Conjunction conjunction) {
    this.generateLatex(conjunction.getLeft());
    this.generateOperator(LatexPreview.CONJUNCTION);
    this.generateLatex(conjunction.getRight());
  }
  
  private void _generateLatex(final Disjunction disjunction) {
    this.generateLatex(disjunction.getLeft());
    this.generateOperator(LatexPreview.DISJUNCTION);
    this.generateLatex(disjunction.getRight());
  }
  
  private void _generateLatex(final Comparison comparison) {
    this.generateLatex(comparison.getLeft());
    String _operation = comparison.getOperation();
    if (_operation != null) {
      switch (_operation) {
        case LatexPreview.OPERATOR_EQUAL:
          this.generateOperator(LatexPreview.EQUAL);
          break;
        case LatexPreview.OPERATOR_INEQUAL:
          this.generateOperator(LatexPreview.INEQUAL);
          break;
        case LatexPreview.OPERATOR_LESS:
          this.generateOperator(LatexPreview.LESS);
          break;
        case LatexPreview.OPERATOR_GREATER:
          this.generateOperator(LatexPreview.GREATER);
          break;
        case LatexPreview.OPERATOR_LESSEQUAL:
          this.generateOperator(LatexPreview.LESSEQUAL);
          break;
        case LatexPreview.OPERATOR_GREATEREQUAL:
          this.generateOperator(LatexPreview.GREATEREQUAL);
          break;
      }
    }
    this.generateLatex(comparison.getRight());
  }
  
  private void _generateLatex(final Sum sum) {
    this.generateLatex(sum.getLeft());
    String _operation = sum.getOperation();
    boolean _equals = Objects.equal(_operation, LatexPreview.OPERATOR_ADDITION);
    if (_equals) {
      this.generateOperator(LatexPreview.ADDITION);
    } else {
      this.generateOperator(LatexPreview.SUBTRACTION);
    }
    this.generateLatex(sum.getRight());
  }
  
  private void _generateLatex(final Product product) {
    String _operation = product.getOperation();
    boolean _equals = Objects.equal(_operation, LatexPreview.OPERATOR_MULTIPLICATION);
    if (_equals) {
      this.generateLatex(product.getLeft());
      this.generateOperator(LatexPreview.MULTIPLICATION);
      this.generateLatex(product.getRight());
    } else {
      this.builder.append(LatexPreview.SPACE);
      this.builder.append(LatexPreview.DIVISION);
      this.generateBraces(product.getLeft());
      this.generateBraces(product.getRight());
    }
  }
  
  private void _generateLatex(final Power power) {
    this.generateLatex(power.getLeft());
    this.generateOperator(LatexPreview.EXPONENTIATION);
    this.openBraces++;
    this.builder.append(LatexPreview.LEFTBRACE);
    this.generateLatex(power.getRight());
    EObject _eContainer = power.eContainer();
    boolean _not = (!(_eContainer instanceof Power));
    if (_not) {
      while ((this.openBraces > 0)) {
        {
          this.builder.append(LatexPreview.RIGHTBRACE);
          this.openBraces--;
        }
      }
    }
  }
  
  private void _generateLatex(final StringLiteral literal) {
    this.builder.append(literal.getValue());
  }
  
  private void _generateLatex(final Tuple tuple) {
    this.generateList(tuple.getElements());
  }
  
  private void _generateLatex(final Negative negative) {
    this.builder.append(LatexPreview.SPACE);
    this.builder.append(LatexPreview.NEGATION);
    this.generateBraces(negative.getTerm());
  }
  
  private void _generateLatex(final FunctionCall call) {
    this.builder.append(call.getName());
    this.generateList(call.getArguments());
  }
  
  private void _generateLatex(final Argument argument) {
    this.generateLatex(argument.getExpression());
  }
  
  private void _generateLatex(final Variable variable) {
    this.builder.append(this.formatIdentifier(variable.getName()));
  }
  
  private void _generateLatex(final NumberLiteral literal) {
    this.builder.append(literal.getValue());
  }
  
  private void _generateLatex(final Brackets brackets) {
    this.builder.append(LatexPreview.LEFTPAREN);
    this.generateLatex(brackets.getContent());
    this.builder.append(LatexPreview.RIGHTPAREN);
  }
  
  private void generateLatex(final EObject argument) {
    if (argument instanceof Argument) {
      _generateLatex((Argument)argument);
      return;
    } else if (argument instanceof Brackets) {
      _generateLatex((Brackets)argument);
      return;
    } else if (argument instanceof Comparison) {
      _generateLatex((Comparison)argument);
      return;
    } else if (argument instanceof Conjunction) {
      _generateLatex((Conjunction)argument);
      return;
    } else if (argument instanceof Disjunction) {
      _generateLatex((Disjunction)argument);
      return;
    } else if (argument instanceof FunctionCall) {
      _generateLatex((FunctionCall)argument);
      return;
    } else if (argument instanceof Negative) {
      _generateLatex((Negative)argument);
      return;
    } else if (argument instanceof NumberLiteral) {
      _generateLatex((NumberLiteral)argument);
      return;
    } else if (argument instanceof Power) {
      _generateLatex((Power)argument);
      return;
    } else if (argument instanceof Product) {
      _generateLatex((Product)argument);
      return;
    } else if (argument instanceof StringLiteral) {
      _generateLatex((StringLiteral)argument);
      return;
    } else if (argument instanceof Sum) {
      _generateLatex((Sum)argument);
      return;
    } else if (argument instanceof Tuple) {
      _generateLatex((Tuple)argument);
      return;
    } else if (argument instanceof Variable) {
      _generateLatex((Variable)argument);
      return;
    } else if (argument instanceof FunctionDefinition) {
      _generateLatex((FunctionDefinition)argument);
      return;
    } else if (argument instanceof Model) {
      _generateLatex((Model)argument);
      return;
    } else if (argument instanceof Parameter) {
      _generateLatex((Parameter)argument);
      return;
    } else if (argument instanceof ParameterList) {
      _generateLatex((ParameterList)argument);
      return;
    } else if (argument instanceof Witness) {
      _generateLatex((Witness)argument);
      return;
    } else if (argument instanceof WitnessList) {
      _generateLatex((WitnessList)argument);
      return;
    } else if (argument != null) {
      _generateLatex(argument);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(argument).toString());
    }
  }
}
