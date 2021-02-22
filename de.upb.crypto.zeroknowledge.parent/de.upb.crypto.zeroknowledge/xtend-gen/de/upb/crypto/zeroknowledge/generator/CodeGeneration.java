package de.upb.crypto.zeroknowledge.generator;

import com.google.common.base.Objects;
import de.upb.crypto.zeroknowledge.model.BranchState;
import de.upb.crypto.zeroknowledge.model.ModelHelper;
import de.upb.crypto.zeroknowledge.model.ModelPrinter;
import de.upb.crypto.zeroknowledge.type.Type;
import de.upb.crypto.zeroknowledge.type.TypeInference;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Argument;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Brackets;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Comparison;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Conjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Disjunction;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Expression;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionCall;
import de.upb.crypto.zeroknowledge.zeroKnowledge.FunctionDefinition;
import de.upb.crypto.zeroknowledge.zeroKnowledge.LocalVariable;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Model;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Negative;
import de.upb.crypto.zeroknowledge.zeroKnowledge.NumberLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Parameter;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Power;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Product;
import de.upb.crypto.zeroknowledge.zeroKnowledge.StringLiteral;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Sum;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Tuple;
import de.upb.crypto.zeroknowledge.zeroKnowledge.Variable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class CodeGeneration {
  private String generatedCode;
  
  private HashMap<EObject, Type> types;
  
  private HashMap<EObject, Integer> sizes;
  
  private HashSet<String> variables;
  
  private HashSet<String> numberLiterals;
  
  private HashSet<String> stringLiterals;
  
  private StringBuilder codeBuilder;
  
  private StringBuilder importBuilder;
  
  private StringBuilder functionBuilder;
  
  private StringBuilder exponentVariableBuilder;
  
  private StringBuilder groupVariableBuilder;
  
  private StringBuilder numberLiteralBuilder;
  
  private StringBuilder stringLiteralBuilder;
  
  private int stringLiteralCount;
  
  private String OPERATOR_MULTIPLICATION = "*";
  
  private String OPERATOR_DIVISION = "/";
  
  private String OPERATOR_EQUAL = "=";
  
  private String OPERATOR_INEQUAL = "!=";
  
  private String OPERATOR_LESS = "<";
  
  private String OPERATOR_GREATER = ">";
  
  private String OPERATOR_LESSEQUAL = "<=";
  
  private String OPERATOR_GREATEREQUAL = ">=";
  
  private String NEWLINE = "\n";
  
  private String INDENT = "  ";
  
  public CodeGeneration(final Model model) {
    this.performGeneration(model, false);
  }
  
  public CodeGeneration(final Model model, final boolean inline) {
    this.performGeneration(model, inline);
  }
  
  public void performGeneration(final Model model, final boolean inlineFunctions) {
    HashSet<String> _hashSet = new HashSet<String>();
    this.variables = _hashSet;
    HashSet<String> _hashSet_1 = new HashSet<String>();
    this.numberLiterals = _hashSet_1;
    HashSet<String> _hashSet_2 = new HashSet<String>();
    this.stringLiterals = _hashSet_2;
    StringBuilder _stringBuilder = new StringBuilder();
    this.codeBuilder = _stringBuilder;
    StringBuilder _stringBuilder_1 = new StringBuilder();
    this.importBuilder = _stringBuilder_1;
    StringBuilder _stringBuilder_2 = new StringBuilder();
    this.functionBuilder = _stringBuilder_2;
    StringBuilder _stringBuilder_3 = new StringBuilder();
    this.exponentVariableBuilder = _stringBuilder_3;
    StringBuilder _stringBuilder_4 = new StringBuilder();
    this.groupVariableBuilder = _stringBuilder_4;
    StringBuilder _stringBuilder_5 = new StringBuilder();
    this.numberLiteralBuilder = _stringBuilder_5;
    StringBuilder _stringBuilder_6 = new StringBuilder();
    this.stringLiteralBuilder = _stringBuilder_6;
    ModelPrinter.print(model);
    if (inlineFunctions) {
      ModelHelper.inlineFunctions(model);
    }
    ModelHelper.normalizeNegatives(model);
    TypeInference.inferTypes(model);
    this.types = TypeInference.getTypes();
    this.sizes = TypeInference.getSizes();
    this.generateImports();
    BranchState _branchState = new BranchState();
    this.generateFunctions(model, _branchState);
    BranchState _branchState_1 = new BranchState();
    final String code = this.generateCode(model, _branchState_1);
    this.codeBuilder.append(this.importBuilder);
    int _length = this.importBuilder.length();
    boolean _tripleNotEquals = (_length != 0);
    if (_tripleNotEquals) {
      this.codeBuilder.append(this.NEWLINE);
    }
    this.codeBuilder.append(this.functionBuilder);
    int _length_1 = this.functionBuilder.length();
    boolean _tripleNotEquals_1 = (_length_1 != 0);
    if (_tripleNotEquals_1) {
      this.codeBuilder.append(this.NEWLINE);
    }
    this.codeBuilder.append(this.numberLiteralBuilder);
    int _length_2 = this.numberLiteralBuilder.length();
    boolean _tripleNotEquals_2 = (_length_2 != 0);
    if (_tripleNotEquals_2) {
      this.codeBuilder.append(this.NEWLINE);
    }
    this.codeBuilder.append(this.stringLiteralBuilder);
    int _length_3 = this.stringLiteralBuilder.length();
    boolean _tripleNotEquals_3 = (_length_3 != 0);
    if (_tripleNotEquals_3) {
      this.codeBuilder.append(this.NEWLINE);
    }
    this.codeBuilder.append(this.exponentVariableBuilder);
    int _length_4 = this.exponentVariableBuilder.length();
    boolean _tripleNotEquals_4 = (_length_4 != 0);
    if (_tripleNotEquals_4) {
      this.codeBuilder.append(this.NEWLINE);
    }
    this.codeBuilder.append(this.groupVariableBuilder);
    int _length_5 = this.groupVariableBuilder.length();
    boolean _tripleNotEquals_5 = (_length_5 != 0);
    if (_tripleNotEquals_5) {
      this.codeBuilder.append(this.NEWLINE);
    }
    this.codeBuilder.append(code);
    this.generatedCode = this.codeBuilder.toString();
    System.out.println(this.generatedCode);
  }
  
  public String getCode() {
    return this.generatedCode;
  }
  
  public void generateImports() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import de.upb.crypto.math.expressions.*;");
    _builder.newLine();
    _builder.append("import static de.upb.crypto.zeroknowledge.helpers.PredefinedFunctions;");
    _builder.newLine();
    this.importBuilder.append(_builder);
  }
  
  public void generateFunctions(final Model model, final BranchState state) {
    EList<FunctionDefinition> _functions = model.getFunctions();
    for (final FunctionDefinition function : _functions) {
      boolean _containsKey = this.types.containsKey(function);
      if (_containsKey) {
        final String returnType = Type.toString(this.types.get(function));
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("private static ");
        _builder.append(returnType);
        _builder.append(" ");
        String _name = function.getName();
        _builder.append(_name);
        _builder.append("(");
        {
          EList<Parameter> _parameters = function.getParameterList().getParameters();
          boolean _hasElements = false;
          for(final Parameter parameter : _parameters) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            String _string = Type.toString(this.types.get(parameter));
            _builder.append(_string);
            _builder.append(" ");
            String _name_1 = parameter.getName();
            _builder.append(_name_1);
          }
        }
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("return ");
        String _generateCode = this.generateCode(function.getBody(), state);
        _builder.append(_generateCode, "  ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        this.functionBuilder.append(_builder);
      }
    }
  }
  
  protected String _generateCode(final Model model, final BranchState state) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private static boolean proof() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return ");
    String _generateCode = this.generateCode(model.getProof(), state);
    _builder.append(_generateCode, "\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  protected String _generateCode(final Conjunction conjunction, final BranchState state) {
    final String left = this.generateCode(conjunction.getLeft(), state);
    final String right = this.generateCode(conjunction.getRight(), state);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(left);
    _builder.newLineIfNotEmpty();
    _builder.append(".and(");
    _builder.append(right);
    _builder.append(")");
    return _builder.toString();
  }
  
  protected String _generateCode(final Disjunction disjunction, final BranchState state) {
    final String left = this.generateCode(disjunction.getLeft(), state);
    final String right = this.generateCode(disjunction.getRight(), state);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(left);
    _builder.append(".or(");
    _builder.append(right);
    _builder.append(")");
    return _builder.toString();
  }
  
  protected String _generateCode(final Comparison comparison, final BranchState state) {
    final String left = this.generateCode(comparison.getLeft(), state);
    final String right = this.generateCode(comparison.getRight(), state);
    String operator = null;
    String _operation = comparison.getOperation();
    boolean _matched = false;
    if (Objects.equal(_operation, this.OPERATOR_EQUAL)) {
      _matched=true;
      operator = "equals";
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_INEQUAL)) {
        _matched=true;
        operator = "notequals";
      }
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_LESS)) {
        _matched=true;
        operator = "lessthan";
      }
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_GREATER)) {
        _matched=true;
        operator = "greaterthan";
      }
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_LESSEQUAL)) {
        _matched=true;
        operator = "lessthanequal";
      }
    }
    if (!_matched) {
      if (Objects.equal(_operation, this.OPERATOR_GREATEREQUAL)) {
        _matched=true;
        operator = "greaterthanequal";
      }
    }
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(left);
    _builder.append(".");
    _builder.append(operator);
    _builder.append("(");
    _builder.append(right);
    _builder.append(")");
    return _builder.toString();
  }
  
  protected String _generateCode(final Sum sum, final BranchState state) {
    final BranchState newState = new BranchState(state);
    final String left = this.generateCode(sum.getLeft(), newState);
    final String right = this.generateCode(sum.getRight(), newState);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(left);
    _builder.append(".add(");
    _builder.append(right);
    _builder.append(")");
    return _builder.toString();
  }
  
  protected String _generateCode(final Product product, final BranchState state) {
    final String left = this.generateCode(product.getLeft(), state);
    final String right = this.generateCode(product.getRight(), state);
    String _operation = product.getOperation();
    boolean _tripleEquals = (_operation == this.OPERATOR_MULTIPLICATION);
    if (_tripleEquals) {
      Type _get = this.types.get(product);
      boolean _tripleEquals_1 = (_get == Type.EXPONENT);
      if (_tripleEquals_1) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(left);
        _builder.append(".mul(");
        _builder.append(right);
        _builder.append(")");
        return _builder.toString();
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(left);
        _builder_1.append(".op(");
        _builder_1.append(right);
        _builder_1.append(")");
        return _builder_1.toString();
      }
    } else {
      Type _get_1 = this.types.get(product);
      boolean _tripleEquals_2 = (_get_1 == Type.EXPONENT);
      if (_tripleEquals_2) {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append(left);
        _builder_2.append(".mul(");
        _builder_2.append(right);
        _builder_2.append(".inv())");
        return _builder_2.toString();
      } else {
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append(left);
        _builder_3.append(".op(");
        _builder_3.append(right);
        _builder_3.append(".inv())");
        return _builder_3.toString();
      }
    }
  }
  
  protected String _generateCode(final Power power, final BranchState state) {
    final String left = this.generateCode(power.getLeft(), state);
    final String right = this.generateCode(power.getRight(), state);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(left);
    _builder.append(".pow(");
    _builder.append(right);
    _builder.append(")");
    return _builder.toString();
  }
  
  protected String _generateCode(final StringLiteral string, final BranchState state) {
    int _plusPlus = this.stringLiteralCount++;
    final String name = ("stringLiteral" + Integer.valueOf(_plusPlus));
    final String value = string.getValue();
    boolean _contains = this.stringLiterals.contains(value);
    boolean _not = (!_contains);
    if (_not) {
      this.stringLiterals.add(value);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("String ");
      _builder.append(name);
      _builder.append(" = ");
      _builder.append(value);
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      this.stringLiteralBuilder.append(_builder);
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("value");
    return _builder_1.toString();
  }
  
  protected String _generateCode(final Tuple node, final BranchState state) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Expression> _elements = node.getElements();
      boolean _hasElements = false;
      for(final Expression element : _elements) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        String _generateCode = this.generateCode(element, state);
        _builder.append(_generateCode);
      }
    }
    return _builder.toString();
  }
  
  protected String _generateCode(final Negative node, final BranchState state) {
    final String term = this.generateCode(node.getTerm(), state);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(term);
    _builder.append(".neg()");
    return _builder.toString();
  }
  
  protected String _generateCode(final FunctionCall call, final BranchState state) {
    final String name = ModelHelper.convertToJavaName(call.getName());
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(name);
    _builder.append("(");
    {
      EList<Expression> _arguments = call.getArguments();
      boolean _hasElements = false;
      for(final Expression argument : _arguments) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "");
        }
        String _generateCode = this.generateCode(argument, state);
        _builder.append(_generateCode);
      }
    }
    _builder.append(")");
    return _builder.toString();
  }
  
  protected String _generateCode(final Argument argument, final BranchState state) {
    return this.generateCode(argument.getExpression(), state);
  }
  
  protected String _generateCode(final Variable variable, final BranchState state) {
    final String name = ModelHelper.convertToJavaName(variable.getName());
    boolean _contains = this.variables.contains(name);
    boolean _not = (!_contains);
    if (_not) {
      this.variables.add(name);
      Type _get = this.types.get(variable);
      boolean _tripleEquals = (_get == Type.EXPONENT);
      if (_tripleEquals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("ExponentVariableExpr ");
        _builder.append(name);
        _builder.append(" = new ExponentVariableExpr(\"");
        _builder.append(name);
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        this.exponentVariableBuilder.append(_builder);
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("GroupVariableExpr ");
        _builder_1.append(name);
        _builder_1.append(" = new GroupVariableExpr(\"");
        _builder_1.append(name);
        _builder_1.append("\");");
        _builder_1.newLineIfNotEmpty();
        this.groupVariableBuilder.append(_builder_1);
      }
    }
    return name;
  }
  
  protected String _generateCode(final LocalVariable variable, final BranchState state) {
    final String name = ModelHelper.convertToJavaName(variable.getName());
    return name;
  }
  
  protected String _generateCode(final NumberLiteral number, final BranchState state) {
    int _value = number.getValue();
    final String name = ("val_" + Integer.valueOf(_value));
    boolean _contains = this.numberLiterals.contains(name);
    boolean _not = (!_contains);
    if (_not) {
      this.numberLiterals.add(name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("ExponentLiteralExpr ");
      _builder.append(name);
      _builder.append(" = new ExponentLiteralExpr(");
      int _value_1 = number.getValue();
      _builder.append(_value_1);
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      this.numberLiteralBuilder.append(_builder);
    }
    return name;
  }
  
  protected String _generateCode(final Brackets brackets, final BranchState state) {
    return this.generateCode(brackets.getContent(), state);
  }
  
  public String generateCode(final EObject variable, final BranchState state) {
    if (variable instanceof LocalVariable) {
      return _generateCode((LocalVariable)variable, state);
    } else if (variable instanceof Argument) {
      return _generateCode((Argument)variable, state);
    } else if (variable instanceof Brackets) {
      return _generateCode((Brackets)variable, state);
    } else if (variable instanceof Comparison) {
      return _generateCode((Comparison)variable, state);
    } else if (variable instanceof Conjunction) {
      return _generateCode((Conjunction)variable, state);
    } else if (variable instanceof Disjunction) {
      return _generateCode((Disjunction)variable, state);
    } else if (variable instanceof FunctionCall) {
      return _generateCode((FunctionCall)variable, state);
    } else if (variable instanceof Negative) {
      return _generateCode((Negative)variable, state);
    } else if (variable instanceof NumberLiteral) {
      return _generateCode((NumberLiteral)variable, state);
    } else if (variable instanceof Power) {
      return _generateCode((Power)variable, state);
    } else if (variable instanceof Product) {
      return _generateCode((Product)variable, state);
    } else if (variable instanceof StringLiteral) {
      return _generateCode((StringLiteral)variable, state);
    } else if (variable instanceof Sum) {
      return _generateCode((Sum)variable, state);
    } else if (variable instanceof Tuple) {
      return _generateCode((Tuple)variable, state);
    } else if (variable instanceof Variable) {
      return _generateCode((Variable)variable, state);
    } else if (variable instanceof Model) {
      return _generateCode((Model)variable, state);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(variable, state).toString());
    }
  }
}
