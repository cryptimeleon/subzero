package de.upb.crypto.zeroknowledge.latex;

import com.google.common.base.Objects;
import de.upb.crypto.zeroknowledge.helpers.ModelHelper;
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

@SuppressWarnings("all")
public class LatexPreview {
  private String latexCode;
  
  private StringBuilder builder;
  
  private final String CONJUNCTION = "\\land";
  
  private final String DISJUNCTION = "\\lor";
  
  private final String EQUAL = "=";
  
  private final String INEQUAL = "\\neq";
  
  private final String LESS = "<";
  
  private final String GREATER = ">";
  
  private final String LESSEQUAL = "\\leq";
  
  private final String GREATEREQUAL = "\\geq";
  
  private final String ADDITION = "+";
  
  private final String SUBTRACTION = "-";
  
  private final String MULTIPLICATION = "\\cdot";
  
  private final String DIVISION = "\\frac";
  
  private final String EXPONENTIATION = "^";
  
  private final String NEGATION = "-";
  
  private final String NEWLINE = "\\\\";
  
  private final String SPACE = " ";
  
  private final String COMMA = ",";
  
  private final String COLON = ":";
  
  private final String SEMICOLON = ";";
  
  private final String QUOTE = "\'";
  
  private final String LEFTPAREN = "(";
  
  private final String RIGHTPAREN = ")";
  
  private final String LEFTBRACE = "{";
  
  private final String RIGHTBRACE = "}";
  
  private final String DELIMITER = "$$";
  
  private final String OPERATOR_ADDITION = "+";
  
  private final String OPERATOR_SUBTRACTION = "-";
  
  private final String OPERATOR_MULTIPLICATION = "*";
  
  private final String OPERATOR_DIVISION = "/";
  
  private final String OPERATOR_EXPONENTIATION = "^";
  
  private final String OPERATOR_EQUAL = "=";
  
  private final String OPERATOR_INEQUAL = "!=";
  
  private final String OPERATOR_LESS = "<";
  
  private final String OPERATOR_GREATER = ">";
  
  private final String OPERATOR_LESSEQUAL = "<=";
  
  private final String OPERATOR_GREATEREQUAL = ">=";
  
  private int openBraces = 0;
  
  private boolean inline;
  
  public LatexPreview(final Model model) {
    this.constructLatexPreview(model, false);
  }
  
  public LatexPreview(final Model model, final boolean inline) {
    this.constructLatexPreview(model, inline);
  }
  
  private void constructLatexPreview(final Model model, final boolean inline) {
    StringBuilder _stringBuilder = new StringBuilder();
    this.builder = _stringBuilder;
    this.inline = inline;
    if (inline) {
      ModelHelper.inlineFunctions(model);
    }
    this.builder.append(this.DELIMITER);
    this.generateLatex(model);
    this.builder.append(this.DELIMITER);
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
    this.builder.append(this.LEFTBRACE);
    this.generateLatex(node);
    this.builder.append(this.RIGHTBRACE);
  }
  
  private void generateOperator(final String operator) {
    this.builder.append(this.SPACE);
    this.builder.append(operator);
    this.builder.append(this.SPACE);
  }
  
  private void generateList(final EList<? extends EObject> items) {
    this.builder.append(this.LEFTPAREN);
    boolean firstItem = true;
    for (final EObject item : items) {
      {
        if (firstItem) {
          firstItem = false;
        } else {
          this.builder.append(this.COMMA);
        }
        this.generateLatex(item);
      }
    }
    this.builder.append(this.RIGHTPAREN);
  }
  
  private void _generateLatex(final EObject node) {
    System.err.println("Unhandled EObject type in Latex generation function");
  }
  
  private void _generateLatex(final Model model) {
    if ((!this.inline)) {
      EList<FunctionDefinition> _functions = model.getFunctions();
      for (final FunctionDefinition function : _functions) {
        {
          this.generateLatex(function);
          this.builder.append(this.NEWLINE);
          this.builder.append(this.NEWLINE);
        }
      }
    }
    this.generateLatex(model.getWitnessList());
    this.builder.append(this.SEMICOLON);
    this.builder.append(this.NEWLINE);
    this.generateLatex(model.getProof());
  }
  
  private void _generateLatex(final FunctionDefinition function) {
    this.builder.append(function.getName());
    this.generateLatex(function.getParameterList());
    this.builder.append(this.COLON);
    this.builder.append(this.NEWLINE);
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
    this.generateOperator(this.CONJUNCTION);
    this.generateLatex(conjunction.getRight());
  }
  
  private void _generateLatex(final Disjunction disjunction) {
    this.generateLatex(disjunction.getLeft());
    this.generateOperator(this.DISJUNCTION);
    this.generateLatex(disjunction.getRight());
  }
  
  private void _generateLatex(final Comparison comparison) {
    this.generateLatex(comparison.getLeft());
    String _operation = comparison.getOperation();
    boolean _matched = false;
    if (Objects.equal(_operation, this.OPERATOR_EQUAL)) {
      _matched=true;
      this.generateOperator(this.EQUAL);
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_INEQUAL)) {
        _matched=true;
        this.generateOperator(this.INEQUAL);
      }
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_LESS)) {
        _matched=true;
        this.generateOperator(this.LESS);
      }
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_GREATER)) {
        _matched=true;
        this.generateOperator(this.GREATER);
      }
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_LESSEQUAL)) {
        _matched=true;
        this.generateOperator(this.LESSEQUAL);
      }
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_GREATEREQUAL)) {
        _matched=true;
        this.generateOperator(this.GREATEREQUAL);
      }
    }
    this.generateLatex(comparison.getRight());
  }
  
  private void _generateLatex(final Sum sum) {
    this.generateLatex(sum.getLeft());
    String _operation = sum.getOperation();
    boolean _equals = Objects.equal(_operation, this.OPERATOR_ADDITION);
    if (_equals) {
      this.generateOperator(this.ADDITION);
    } else {
      this.generateOperator(this.SUBTRACTION);
    }
    this.generateLatex(sum.getRight());
  }
  
  private void _generateLatex(final Product product) {
    String _operation = product.getOperation();
    boolean _equals = Objects.equal(_operation, this.OPERATOR_MULTIPLICATION);
    if (_equals) {
      this.generateLatex(product.getLeft());
      this.generateOperator(this.MULTIPLICATION);
      this.generateLatex(product.getRight());
    } else {
      this.builder.append(this.SPACE);
      this.builder.append(this.DIVISION);
      this.generateBraces(product.getLeft());
      this.generateBraces(product.getRight());
    }
  }
  
  private void _generateLatex(final Power power) {
    this.generateLatex(power.getLeft());
    this.generateOperator(this.EXPONENTIATION);
    this.openBraces++;
    this.builder.append(this.LEFTBRACE);
    this.generateLatex(power.getRight());
    EObject _eContainer = power.eContainer();
    boolean _not = (!(_eContainer instanceof Power));
    if (_not) {
      while ((this.openBraces > 0)) {
        {
          this.builder.append(this.RIGHTBRACE);
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
    this.builder.append(this.SPACE);
    this.builder.append(this.NEGATION);
    this.generateBraces(negative.getTerm());
  }
  
  private void _generateLatex(final FunctionCall call) {
    this.builder.append(call.getName());
    this.generateList(call.getArguments());
  }
  
  private void _generateLatex(final Variable variable) {
    this.builder.append(this.formatIdentifier(variable.getName()));
  }
  
  private void _generateLatex(final NumberLiteral literal) {
    this.builder.append(literal.getValue());
  }
  
  private void _generateLatex(final Brackets brackets) {
    this.builder.append(this.LEFTPAREN);
    this.generateLatex(brackets.getContent());
    this.builder.append(this.RIGHTPAREN);
  }
  
  private void generateLatex(final EObject brackets) {
    if (brackets instanceof Brackets) {
      _generateLatex((Brackets)brackets);
      return;
    } else if (brackets instanceof Comparison) {
      _generateLatex((Comparison)brackets);
      return;
    } else if (brackets instanceof Conjunction) {
      _generateLatex((Conjunction)brackets);
      return;
    } else if (brackets instanceof Disjunction) {
      _generateLatex((Disjunction)brackets);
      return;
    } else if (brackets instanceof FunctionCall) {
      _generateLatex((FunctionCall)brackets);
      return;
    } else if (brackets instanceof Negative) {
      _generateLatex((Negative)brackets);
      return;
    } else if (brackets instanceof NumberLiteral) {
      _generateLatex((NumberLiteral)brackets);
      return;
    } else if (brackets instanceof Power) {
      _generateLatex((Power)brackets);
      return;
    } else if (brackets instanceof Product) {
      _generateLatex((Product)brackets);
      return;
    } else if (brackets instanceof StringLiteral) {
      _generateLatex((StringLiteral)brackets);
      return;
    } else if (brackets instanceof Sum) {
      _generateLatex((Sum)brackets);
      return;
    } else if (brackets instanceof Tuple) {
      _generateLatex((Tuple)brackets);
      return;
    } else if (brackets instanceof Variable) {
      _generateLatex((Variable)brackets);
      return;
    } else if (brackets instanceof FunctionDefinition) {
      _generateLatex((FunctionDefinition)brackets);
      return;
    } else if (brackets instanceof Model) {
      _generateLatex((Model)brackets);
      return;
    } else if (brackets instanceof Parameter) {
      _generateLatex((Parameter)brackets);
      return;
    } else if (brackets instanceof ParameterList) {
      _generateLatex((ParameterList)brackets);
      return;
    } else if (brackets instanceof Witness) {
      _generateLatex((Witness)brackets);
      return;
    } else if (brackets instanceof WitnessList) {
      _generateLatex((WitnessList)brackets);
      return;
    } else if (brackets != null) {
      _generateLatex(brackets);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(brackets).toString());
    }
  }
}
