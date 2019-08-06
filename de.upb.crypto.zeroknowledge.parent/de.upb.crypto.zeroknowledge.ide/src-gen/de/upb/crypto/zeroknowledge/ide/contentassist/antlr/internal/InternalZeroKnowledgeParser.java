package de.upb.crypto.zeroknowledge.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import de.upb.crypto.zeroknowledge.services.ZeroKnowledgeGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalZeroKnowledgeParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_IDENTIFIER", "RULE_STRING_LITERAL", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WHITESPACE", "';'", "'{'", "'}'", "'('", "','", "')'", "'&'", "'|'", "'!='", "'='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", "'^'"
    };
    public static final int RULE_IDENTIFIER=4;
    public static final int RULE_STRING_LITERAL=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_WHITESPACE=9;
    public static final int T__10=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalZeroKnowledgeParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalZeroKnowledgeParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalZeroKnowledgeParser.tokenNames; }
    public String getGrammarFileName() { return "InternalZeroKnowledge.g"; }


    	private ZeroKnowledgeGrammarAccess grammarAccess;

    	public void setGrammarAccess(ZeroKnowledgeGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleModel"
    // InternalZeroKnowledge.g:54:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:55:1: ( ruleModel EOF )
            // InternalZeroKnowledge.g:56:1: ruleModel EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalZeroKnowledge.g:63:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:67:2: ( ( ( rule__Model__Group__0 ) ) )
            // InternalZeroKnowledge.g:68:2: ( ( rule__Model__Group__0 ) )
            {
            // InternalZeroKnowledge.g:68:2: ( ( rule__Model__Group__0 ) )
            // InternalZeroKnowledge.g:69:3: ( rule__Model__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:70:3: ( rule__Model__Group__0 )
            // InternalZeroKnowledge.g:70:4: rule__Model__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleFunctionDefinition"
    // InternalZeroKnowledge.g:79:1: entryRuleFunctionDefinition : ruleFunctionDefinition EOF ;
    public final void entryRuleFunctionDefinition() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:80:1: ( ruleFunctionDefinition EOF )
            // InternalZeroKnowledge.g:81:1: ruleFunctionDefinition EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleFunctionDefinition();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFunctionDefinition"


    // $ANTLR start "ruleFunctionDefinition"
    // InternalZeroKnowledge.g:88:1: ruleFunctionDefinition : ( ( rule__FunctionDefinition__Group__0 ) ) ;
    public final void ruleFunctionDefinition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:92:2: ( ( ( rule__FunctionDefinition__Group__0 ) ) )
            // InternalZeroKnowledge.g:93:2: ( ( rule__FunctionDefinition__Group__0 ) )
            {
            // InternalZeroKnowledge.g:93:2: ( ( rule__FunctionDefinition__Group__0 ) )
            // InternalZeroKnowledge.g:94:3: ( rule__FunctionDefinition__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:95:3: ( rule__FunctionDefinition__Group__0 )
            // InternalZeroKnowledge.g:95:4: rule__FunctionDefinition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFunctionDefinition"


    // $ANTLR start "entryRuleParameterList"
    // InternalZeroKnowledge.g:104:1: entryRuleParameterList : ruleParameterList EOF ;
    public final void entryRuleParameterList() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:105:1: ( ruleParameterList EOF )
            // InternalZeroKnowledge.g:106:1: ruleParameterList EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleParameterList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleParameterList"


    // $ANTLR start "ruleParameterList"
    // InternalZeroKnowledge.g:113:1: ruleParameterList : ( ( rule__ParameterList__Group__0 ) ) ;
    public final void ruleParameterList() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:117:2: ( ( ( rule__ParameterList__Group__0 ) ) )
            // InternalZeroKnowledge.g:118:2: ( ( rule__ParameterList__Group__0 ) )
            {
            // InternalZeroKnowledge.g:118:2: ( ( rule__ParameterList__Group__0 ) )
            // InternalZeroKnowledge.g:119:3: ( rule__ParameterList__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:120:3: ( rule__ParameterList__Group__0 )
            // InternalZeroKnowledge.g:120:4: rule__ParameterList__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParameterList"


    // $ANTLR start "entryRuleParameter"
    // InternalZeroKnowledge.g:129:1: entryRuleParameter : ruleParameter EOF ;
    public final void entryRuleParameter() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:130:1: ( ruleParameter EOF )
            // InternalZeroKnowledge.g:131:1: ruleParameter EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleParameter();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalZeroKnowledge.g:138:1: ruleParameter : ( ( rule__Parameter__NameAssignment ) ) ;
    public final void ruleParameter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:142:2: ( ( ( rule__Parameter__NameAssignment ) ) )
            // InternalZeroKnowledge.g:143:2: ( ( rule__Parameter__NameAssignment ) )
            {
            // InternalZeroKnowledge.g:143:2: ( ( rule__Parameter__NameAssignment ) )
            // InternalZeroKnowledge.g:144:3: ( rule__Parameter__NameAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterAccess().getNameAssignment()); 
            }
            // InternalZeroKnowledge.g:145:3: ( rule__Parameter__NameAssignment )
            // InternalZeroKnowledge.g:145:4: rule__Parameter__NameAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__NameAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterAccess().getNameAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleWitnessList"
    // InternalZeroKnowledge.g:154:1: entryRuleWitnessList : ruleWitnessList EOF ;
    public final void entryRuleWitnessList() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:155:1: ( ruleWitnessList EOF )
            // InternalZeroKnowledge.g:156:1: ruleWitnessList EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleWitnessList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWitnessList"


    // $ANTLR start "ruleWitnessList"
    // InternalZeroKnowledge.g:163:1: ruleWitnessList : ( ( rule__WitnessList__Group__0 ) ) ;
    public final void ruleWitnessList() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:167:2: ( ( ( rule__WitnessList__Group__0 ) ) )
            // InternalZeroKnowledge.g:168:2: ( ( rule__WitnessList__Group__0 ) )
            {
            // InternalZeroKnowledge.g:168:2: ( ( rule__WitnessList__Group__0 ) )
            // InternalZeroKnowledge.g:169:3: ( rule__WitnessList__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:170:3: ( rule__WitnessList__Group__0 )
            // InternalZeroKnowledge.g:170:4: rule__WitnessList__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWitnessList"


    // $ANTLR start "entryRuleWitness"
    // InternalZeroKnowledge.g:179:1: entryRuleWitness : ruleWitness EOF ;
    public final void entryRuleWitness() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:180:1: ( ruleWitness EOF )
            // InternalZeroKnowledge.g:181:1: ruleWitness EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleWitness();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWitness"


    // $ANTLR start "ruleWitness"
    // InternalZeroKnowledge.g:188:1: ruleWitness : ( ( rule__Witness__NameAssignment ) ) ;
    public final void ruleWitness() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:192:2: ( ( ( rule__Witness__NameAssignment ) ) )
            // InternalZeroKnowledge.g:193:2: ( ( rule__Witness__NameAssignment ) )
            {
            // InternalZeroKnowledge.g:193:2: ( ( rule__Witness__NameAssignment ) )
            // InternalZeroKnowledge.g:194:3: ( rule__Witness__NameAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessAccess().getNameAssignment()); 
            }
            // InternalZeroKnowledge.g:195:3: ( rule__Witness__NameAssignment )
            // InternalZeroKnowledge.g:195:4: rule__Witness__NameAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Witness__NameAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessAccess().getNameAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWitness"


    // $ANTLR start "entryRuleExpression"
    // InternalZeroKnowledge.g:204:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:205:1: ( ruleExpression EOF )
            // InternalZeroKnowledge.g:206:1: ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExpressionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalZeroKnowledge.g:213:1: ruleExpression : ( ruleConjunction ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:217:2: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:218:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:218:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:219:3: ruleConjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExpressionAccess().getConjunctionParserRuleCall()); 
            }
            pushFollow(FOLLOW_2);
            ruleConjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExpressionAccess().getConjunctionParserRuleCall()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleConjunction"
    // InternalZeroKnowledge.g:229:1: entryRuleConjunction : ruleConjunction EOF ;
    public final void entryRuleConjunction() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:230:1: ( ruleConjunction EOF )
            // InternalZeroKnowledge.g:231:1: ruleConjunction EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleConjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConjunction"


    // $ANTLR start "ruleConjunction"
    // InternalZeroKnowledge.g:238:1: ruleConjunction : ( ( rule__Conjunction__Group__0 ) ) ;
    public final void ruleConjunction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:242:2: ( ( ( rule__Conjunction__Group__0 ) ) )
            // InternalZeroKnowledge.g:243:2: ( ( rule__Conjunction__Group__0 ) )
            {
            // InternalZeroKnowledge.g:243:2: ( ( rule__Conjunction__Group__0 ) )
            // InternalZeroKnowledge.g:244:3: ( rule__Conjunction__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:245:3: ( rule__Conjunction__Group__0 )
            // InternalZeroKnowledge.g:245:4: rule__Conjunction__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Conjunction__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConjunction"


    // $ANTLR start "entryRuleDisjunction"
    // InternalZeroKnowledge.g:254:1: entryRuleDisjunction : ruleDisjunction EOF ;
    public final void entryRuleDisjunction() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:255:1: ( ruleDisjunction EOF )
            // InternalZeroKnowledge.g:256:1: ruleDisjunction EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleDisjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDisjunction"


    // $ANTLR start "ruleDisjunction"
    // InternalZeroKnowledge.g:263:1: ruleDisjunction : ( ( rule__Disjunction__Group__0 ) ) ;
    public final void ruleDisjunction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:267:2: ( ( ( rule__Disjunction__Group__0 ) ) )
            // InternalZeroKnowledge.g:268:2: ( ( rule__Disjunction__Group__0 ) )
            {
            // InternalZeroKnowledge.g:268:2: ( ( rule__Disjunction__Group__0 ) )
            // InternalZeroKnowledge.g:269:3: ( rule__Disjunction__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:270:3: ( rule__Disjunction__Group__0 )
            // InternalZeroKnowledge.g:270:4: rule__Disjunction__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDisjunction"


    // $ANTLR start "entryRuleComparison"
    // InternalZeroKnowledge.g:279:1: entryRuleComparison : ruleComparison EOF ;
    public final void entryRuleComparison() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:280:1: ( ruleComparison EOF )
            // InternalZeroKnowledge.g:281:1: ruleComparison EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleComparison();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleComparison"


    // $ANTLR start "ruleComparison"
    // InternalZeroKnowledge.g:288:1: ruleComparison : ( ( rule__Comparison__Group__0 ) ) ;
    public final void ruleComparison() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:292:2: ( ( ( rule__Comparison__Group__0 ) ) )
            // InternalZeroKnowledge.g:293:2: ( ( rule__Comparison__Group__0 ) )
            {
            // InternalZeroKnowledge.g:293:2: ( ( rule__Comparison__Group__0 ) )
            // InternalZeroKnowledge.g:294:3: ( rule__Comparison__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:295:3: ( rule__Comparison__Group__0 )
            // InternalZeroKnowledge.g:295:4: rule__Comparison__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleComparison"


    // $ANTLR start "entryRuleSum"
    // InternalZeroKnowledge.g:304:1: entryRuleSum : ruleSum EOF ;
    public final void entryRuleSum() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:305:1: ( ruleSum EOF )
            // InternalZeroKnowledge.g:306:1: ruleSum EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleSum();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSum"


    // $ANTLR start "ruleSum"
    // InternalZeroKnowledge.g:313:1: ruleSum : ( ( rule__Sum__Group__0 ) ) ;
    public final void ruleSum() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:317:2: ( ( ( rule__Sum__Group__0 ) ) )
            // InternalZeroKnowledge.g:318:2: ( ( rule__Sum__Group__0 ) )
            {
            // InternalZeroKnowledge.g:318:2: ( ( rule__Sum__Group__0 ) )
            // InternalZeroKnowledge.g:319:3: ( rule__Sum__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:320:3: ( rule__Sum__Group__0 )
            // InternalZeroKnowledge.g:320:4: rule__Sum__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Sum__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSum"


    // $ANTLR start "entryRuleProduct"
    // InternalZeroKnowledge.g:329:1: entryRuleProduct : ruleProduct EOF ;
    public final void entryRuleProduct() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:330:1: ( ruleProduct EOF )
            // InternalZeroKnowledge.g:331:1: ruleProduct EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleProduct();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleProduct"


    // $ANTLR start "ruleProduct"
    // InternalZeroKnowledge.g:338:1: ruleProduct : ( ( rule__Product__Group__0 ) ) ;
    public final void ruleProduct() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:342:2: ( ( ( rule__Product__Group__0 ) ) )
            // InternalZeroKnowledge.g:343:2: ( ( rule__Product__Group__0 ) )
            {
            // InternalZeroKnowledge.g:343:2: ( ( rule__Product__Group__0 ) )
            // InternalZeroKnowledge.g:344:3: ( rule__Product__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:345:3: ( rule__Product__Group__0 )
            // InternalZeroKnowledge.g:345:4: rule__Product__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Product__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleProduct"


    // $ANTLR start "entryRulePower"
    // InternalZeroKnowledge.g:354:1: entryRulePower : rulePower EOF ;
    public final void entryRulePower() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:355:1: ( rulePower EOF )
            // InternalZeroKnowledge.g:356:1: rulePower EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerRule()); 
            }
            pushFollow(FOLLOW_1);
            rulePower();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePower"


    // $ANTLR start "rulePower"
    // InternalZeroKnowledge.g:363:1: rulePower : ( ( rule__Power__Group__0 ) ) ;
    public final void rulePower() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:367:2: ( ( ( rule__Power__Group__0 ) ) )
            // InternalZeroKnowledge.g:368:2: ( ( rule__Power__Group__0 ) )
            {
            // InternalZeroKnowledge.g:368:2: ( ( rule__Power__Group__0 ) )
            // InternalZeroKnowledge.g:369:3: ( rule__Power__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:370:3: ( rule__Power__Group__0 )
            // InternalZeroKnowledge.g:370:4: rule__Power__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Power__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePower"


    // $ANTLR start "entryRuleConstruct"
    // InternalZeroKnowledge.g:379:1: entryRuleConstruct : ruleConstruct EOF ;
    public final void entryRuleConstruct() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:380:1: ( ruleConstruct EOF )
            // InternalZeroKnowledge.g:381:1: ruleConstruct EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConstructRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleConstruct();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConstructRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConstruct"


    // $ANTLR start "ruleConstruct"
    // InternalZeroKnowledge.g:388:1: ruleConstruct : ( ( rule__Construct__Alternatives ) ) ;
    public final void ruleConstruct() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:392:2: ( ( ( rule__Construct__Alternatives ) ) )
            // InternalZeroKnowledge.g:393:2: ( ( rule__Construct__Alternatives ) )
            {
            // InternalZeroKnowledge.g:393:2: ( ( rule__Construct__Alternatives ) )
            // InternalZeroKnowledge.g:394:3: ( rule__Construct__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConstructAccess().getAlternatives()); 
            }
            // InternalZeroKnowledge.g:395:3: ( rule__Construct__Alternatives )
            // InternalZeroKnowledge.g:395:4: rule__Construct__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Construct__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConstructAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConstruct"


    // $ANTLR start "entryRuleStringLiteral"
    // InternalZeroKnowledge.g:404:1: entryRuleStringLiteral : ruleStringLiteral EOF ;
    public final void entryRuleStringLiteral() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:405:1: ( ruleStringLiteral EOF )
            // InternalZeroKnowledge.g:406:1: ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleStringLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleStringLiteral"


    // $ANTLR start "ruleStringLiteral"
    // InternalZeroKnowledge.g:413:1: ruleStringLiteral : ( ( rule__StringLiteral__ValueAssignment ) ) ;
    public final void ruleStringLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:417:2: ( ( ( rule__StringLiteral__ValueAssignment ) ) )
            // InternalZeroKnowledge.g:418:2: ( ( rule__StringLiteral__ValueAssignment ) )
            {
            // InternalZeroKnowledge.g:418:2: ( ( rule__StringLiteral__ValueAssignment ) )
            // InternalZeroKnowledge.g:419:3: ( rule__StringLiteral__ValueAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringLiteralAccess().getValueAssignment()); 
            }
            // InternalZeroKnowledge.g:420:3: ( rule__StringLiteral__ValueAssignment )
            // InternalZeroKnowledge.g:420:4: rule__StringLiteral__ValueAssignment
            {
            pushFollow(FOLLOW_2);
            rule__StringLiteral__ValueAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringLiteralAccess().getValueAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStringLiteral"


    // $ANTLR start "entryRuleTuple"
    // InternalZeroKnowledge.g:429:1: entryRuleTuple : ruleTuple EOF ;
    public final void entryRuleTuple() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:430:1: ( ruleTuple EOF )
            // InternalZeroKnowledge.g:431:1: ruleTuple EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleTuple();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTuple"


    // $ANTLR start "ruleTuple"
    // InternalZeroKnowledge.g:438:1: ruleTuple : ( ( rule__Tuple__Group__0 ) ) ;
    public final void ruleTuple() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:442:2: ( ( ( rule__Tuple__Group__0 ) ) )
            // InternalZeroKnowledge.g:443:2: ( ( rule__Tuple__Group__0 ) )
            {
            // InternalZeroKnowledge.g:443:2: ( ( rule__Tuple__Group__0 ) )
            // InternalZeroKnowledge.g:444:3: ( rule__Tuple__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:445:3: ( rule__Tuple__Group__0 )
            // InternalZeroKnowledge.g:445:4: rule__Tuple__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTuple"


    // $ANTLR start "entryRuleNegative"
    // InternalZeroKnowledge.g:454:1: entryRuleNegative : ruleNegative EOF ;
    public final void entryRuleNegative() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:455:1: ( ruleNegative EOF )
            // InternalZeroKnowledge.g:456:1: ruleNegative EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleNegative();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNegativeRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNegative"


    // $ANTLR start "ruleNegative"
    // InternalZeroKnowledge.g:463:1: ruleNegative : ( ( rule__Negative__Alternatives ) ) ;
    public final void ruleNegative() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:467:2: ( ( ( rule__Negative__Alternatives ) ) )
            // InternalZeroKnowledge.g:468:2: ( ( rule__Negative__Alternatives ) )
            {
            // InternalZeroKnowledge.g:468:2: ( ( rule__Negative__Alternatives ) )
            // InternalZeroKnowledge.g:469:3: ( rule__Negative__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getAlternatives()); 
            }
            // InternalZeroKnowledge.g:470:3: ( rule__Negative__Alternatives )
            // InternalZeroKnowledge.g:470:4: rule__Negative__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Negative__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNegativeAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNegative"


    // $ANTLR start "entryRuleValue"
    // InternalZeroKnowledge.g:479:1: entryRuleValue : ruleValue EOF ;
    public final void entryRuleValue() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:480:1: ( ruleValue EOF )
            // InternalZeroKnowledge.g:481:1: ruleValue EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleValue();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getValueRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalZeroKnowledge.g:488:1: ruleValue : ( ( rule__Value__Alternatives ) ) ;
    public final void ruleValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:492:2: ( ( ( rule__Value__Alternatives ) ) )
            // InternalZeroKnowledge.g:493:2: ( ( rule__Value__Alternatives ) )
            {
            // InternalZeroKnowledge.g:493:2: ( ( rule__Value__Alternatives ) )
            // InternalZeroKnowledge.g:494:3: ( rule__Value__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueAccess().getAlternatives()); 
            }
            // InternalZeroKnowledge.g:495:3: ( rule__Value__Alternatives )
            // InternalZeroKnowledge.g:495:4: rule__Value__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Value__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getValueAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleFunctionCall"
    // InternalZeroKnowledge.g:504:1: entryRuleFunctionCall : ruleFunctionCall EOF ;
    public final void entryRuleFunctionCall() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:505:1: ( ruleFunctionCall EOF )
            // InternalZeroKnowledge.g:506:1: ruleFunctionCall EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleFunctionCall();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFunctionCall"


    // $ANTLR start "ruleFunctionCall"
    // InternalZeroKnowledge.g:513:1: ruleFunctionCall : ( ( rule__FunctionCall__Group__0 ) ) ;
    public final void ruleFunctionCall() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:517:2: ( ( ( rule__FunctionCall__Group__0 ) ) )
            // InternalZeroKnowledge.g:518:2: ( ( rule__FunctionCall__Group__0 ) )
            {
            // InternalZeroKnowledge.g:518:2: ( ( rule__FunctionCall__Group__0 ) )
            // InternalZeroKnowledge.g:519:3: ( rule__FunctionCall__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:520:3: ( rule__FunctionCall__Group__0 )
            // InternalZeroKnowledge.g:520:4: rule__FunctionCall__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFunctionCall"


    // $ANTLR start "entryRuleArgument"
    // InternalZeroKnowledge.g:529:1: entryRuleArgument : ruleArgument EOF ;
    public final void entryRuleArgument() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:530:1: ( ruleArgument EOF )
            // InternalZeroKnowledge.g:531:1: ruleArgument EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getArgumentRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleArgument();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getArgumentRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleArgument"


    // $ANTLR start "ruleArgument"
    // InternalZeroKnowledge.g:538:1: ruleArgument : ( ( rule__Argument__Group__0 ) ) ;
    public final void ruleArgument() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:542:2: ( ( ( rule__Argument__Group__0 ) ) )
            // InternalZeroKnowledge.g:543:2: ( ( rule__Argument__Group__0 ) )
            {
            // InternalZeroKnowledge.g:543:2: ( ( rule__Argument__Group__0 ) )
            // InternalZeroKnowledge.g:544:3: ( rule__Argument__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getArgumentAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:545:3: ( rule__Argument__Group__0 )
            // InternalZeroKnowledge.g:545:4: rule__Argument__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Argument__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getArgumentAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleArgument"


    // $ANTLR start "entryRuleVariable"
    // InternalZeroKnowledge.g:554:1: entryRuleVariable : ruleVariable EOF ;
    public final void entryRuleVariable() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:555:1: ( ruleVariable EOF )
            // InternalZeroKnowledge.g:556:1: ruleVariable EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getVariableRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleVariable();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getVariableRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVariable"


    // $ANTLR start "ruleVariable"
    // InternalZeroKnowledge.g:563:1: ruleVariable : ( ( rule__Variable__NameAssignment ) ) ;
    public final void ruleVariable() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:567:2: ( ( ( rule__Variable__NameAssignment ) ) )
            // InternalZeroKnowledge.g:568:2: ( ( rule__Variable__NameAssignment ) )
            {
            // InternalZeroKnowledge.g:568:2: ( ( rule__Variable__NameAssignment ) )
            // InternalZeroKnowledge.g:569:3: ( rule__Variable__NameAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getVariableAccess().getNameAssignment()); 
            }
            // InternalZeroKnowledge.g:570:3: ( rule__Variable__NameAssignment )
            // InternalZeroKnowledge.g:570:4: rule__Variable__NameAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Variable__NameAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getVariableAccess().getNameAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVariable"


    // $ANTLR start "entryRuleNumberLiteral"
    // InternalZeroKnowledge.g:579:1: entryRuleNumberLiteral : ruleNumberLiteral EOF ;
    public final void entryRuleNumberLiteral() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:580:1: ( ruleNumberLiteral EOF )
            // InternalZeroKnowledge.g:581:1: ruleNumberLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNumberLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleNumberLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNumberLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNumberLiteral"


    // $ANTLR start "ruleNumberLiteral"
    // InternalZeroKnowledge.g:588:1: ruleNumberLiteral : ( ( rule__NumberLiteral__ValueAssignment ) ) ;
    public final void ruleNumberLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:592:2: ( ( ( rule__NumberLiteral__ValueAssignment ) ) )
            // InternalZeroKnowledge.g:593:2: ( ( rule__NumberLiteral__ValueAssignment ) )
            {
            // InternalZeroKnowledge.g:593:2: ( ( rule__NumberLiteral__ValueAssignment ) )
            // InternalZeroKnowledge.g:594:3: ( rule__NumberLiteral__ValueAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNumberLiteralAccess().getValueAssignment()); 
            }
            // InternalZeroKnowledge.g:595:3: ( rule__NumberLiteral__ValueAssignment )
            // InternalZeroKnowledge.g:595:4: rule__NumberLiteral__ValueAssignment
            {
            pushFollow(FOLLOW_2);
            rule__NumberLiteral__ValueAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNumberLiteralAccess().getValueAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNumberLiteral"


    // $ANTLR start "entryRuleBrackets"
    // InternalZeroKnowledge.g:604:1: entryRuleBrackets : ruleBrackets EOF ;
    public final void entryRuleBrackets() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:605:1: ( ruleBrackets EOF )
            // InternalZeroKnowledge.g:606:1: ruleBrackets EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBracketsRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleBrackets();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBracketsRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBrackets"


    // $ANTLR start "ruleBrackets"
    // InternalZeroKnowledge.g:613:1: ruleBrackets : ( ( rule__Brackets__Group__0 ) ) ;
    public final void ruleBrackets() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:617:2: ( ( ( rule__Brackets__Group__0 ) ) )
            // InternalZeroKnowledge.g:618:2: ( ( rule__Brackets__Group__0 ) )
            {
            // InternalZeroKnowledge.g:618:2: ( ( rule__Brackets__Group__0 ) )
            // InternalZeroKnowledge.g:619:3: ( rule__Brackets__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBracketsAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:620:3: ( rule__Brackets__Group__0 )
            // InternalZeroKnowledge.g:620:4: rule__Brackets__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Brackets__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBracketsAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBrackets"


    // $ANTLR start "rule__Comparison__Alternatives_1_0"
    // InternalZeroKnowledge.g:628:1: rule__Comparison__Alternatives_1_0 : ( ( ( rule__Comparison__Group_1_0_0__0 ) ) | ( ( rule__Comparison__Group_1_0_1__0 ) ) | ( ( rule__Comparison__Group_1_0_2__0 ) ) | ( ( rule__Comparison__Group_1_0_3__0 ) ) | ( ( rule__Comparison__Group_1_0_4__0 ) ) | ( ( rule__Comparison__Group_1_0_5__0 ) ) );
    public final void rule__Comparison__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:632:1: ( ( ( rule__Comparison__Group_1_0_0__0 ) ) | ( ( rule__Comparison__Group_1_0_1__0 ) ) | ( ( rule__Comparison__Group_1_0_2__0 ) ) | ( ( rule__Comparison__Group_1_0_3__0 ) ) | ( ( rule__Comparison__Group_1_0_4__0 ) ) | ( ( rule__Comparison__Group_1_0_5__0 ) ) )
            int alt1=6;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt1=1;
                }
                break;
            case 19:
                {
                alt1=2;
                }
                break;
            case 20:
                {
                alt1=3;
                }
                break;
            case 21:
                {
                alt1=4;
                }
                break;
            case 22:
                {
                alt1=5;
                }
                break;
            case 23:
                {
                alt1=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalZeroKnowledge.g:633:2: ( ( rule__Comparison__Group_1_0_0__0 ) )
                    {
                    // InternalZeroKnowledge.g:633:2: ( ( rule__Comparison__Group_1_0_0__0 ) )
                    // InternalZeroKnowledge.g:634:3: ( rule__Comparison__Group_1_0_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_0()); 
                    }
                    // InternalZeroKnowledge.g:635:3: ( rule__Comparison__Group_1_0_0__0 )
                    // InternalZeroKnowledge.g:635:4: rule__Comparison__Group_1_0_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Comparison__Group_1_0_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getComparisonAccess().getGroup_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalZeroKnowledge.g:639:2: ( ( rule__Comparison__Group_1_0_1__0 ) )
                    {
                    // InternalZeroKnowledge.g:639:2: ( ( rule__Comparison__Group_1_0_1__0 ) )
                    // InternalZeroKnowledge.g:640:3: ( rule__Comparison__Group_1_0_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_1()); 
                    }
                    // InternalZeroKnowledge.g:641:3: ( rule__Comparison__Group_1_0_1__0 )
                    // InternalZeroKnowledge.g:641:4: rule__Comparison__Group_1_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Comparison__Group_1_0_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getComparisonAccess().getGroup_1_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalZeroKnowledge.g:645:2: ( ( rule__Comparison__Group_1_0_2__0 ) )
                    {
                    // InternalZeroKnowledge.g:645:2: ( ( rule__Comparison__Group_1_0_2__0 ) )
                    // InternalZeroKnowledge.g:646:3: ( rule__Comparison__Group_1_0_2__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_2()); 
                    }
                    // InternalZeroKnowledge.g:647:3: ( rule__Comparison__Group_1_0_2__0 )
                    // InternalZeroKnowledge.g:647:4: rule__Comparison__Group_1_0_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Comparison__Group_1_0_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getComparisonAccess().getGroup_1_0_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalZeroKnowledge.g:651:2: ( ( rule__Comparison__Group_1_0_3__0 ) )
                    {
                    // InternalZeroKnowledge.g:651:2: ( ( rule__Comparison__Group_1_0_3__0 ) )
                    // InternalZeroKnowledge.g:652:3: ( rule__Comparison__Group_1_0_3__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_3()); 
                    }
                    // InternalZeroKnowledge.g:653:3: ( rule__Comparison__Group_1_0_3__0 )
                    // InternalZeroKnowledge.g:653:4: rule__Comparison__Group_1_0_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Comparison__Group_1_0_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getComparisonAccess().getGroup_1_0_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalZeroKnowledge.g:657:2: ( ( rule__Comparison__Group_1_0_4__0 ) )
                    {
                    // InternalZeroKnowledge.g:657:2: ( ( rule__Comparison__Group_1_0_4__0 ) )
                    // InternalZeroKnowledge.g:658:3: ( rule__Comparison__Group_1_0_4__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_4()); 
                    }
                    // InternalZeroKnowledge.g:659:3: ( rule__Comparison__Group_1_0_4__0 )
                    // InternalZeroKnowledge.g:659:4: rule__Comparison__Group_1_0_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Comparison__Group_1_0_4__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getComparisonAccess().getGroup_1_0_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalZeroKnowledge.g:663:2: ( ( rule__Comparison__Group_1_0_5__0 ) )
                    {
                    // InternalZeroKnowledge.g:663:2: ( ( rule__Comparison__Group_1_0_5__0 ) )
                    // InternalZeroKnowledge.g:664:3: ( rule__Comparison__Group_1_0_5__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_5()); 
                    }
                    // InternalZeroKnowledge.g:665:3: ( rule__Comparison__Group_1_0_5__0 )
                    // InternalZeroKnowledge.g:665:4: rule__Comparison__Group_1_0_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Comparison__Group_1_0_5__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getComparisonAccess().getGroup_1_0_5()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Alternatives_1_0"


    // $ANTLR start "rule__Sum__Alternatives_1_0"
    // InternalZeroKnowledge.g:673:1: rule__Sum__Alternatives_1_0 : ( ( ( rule__Sum__Group_1_0_0__0 ) ) | ( ( rule__Sum__Group_1_0_1__0 ) ) );
    public final void rule__Sum__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:677:1: ( ( ( rule__Sum__Group_1_0_0__0 ) ) | ( ( rule__Sum__Group_1_0_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==24) ) {
                alt2=1;
            }
            else if ( (LA2_0==25) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalZeroKnowledge.g:678:2: ( ( rule__Sum__Group_1_0_0__0 ) )
                    {
                    // InternalZeroKnowledge.g:678:2: ( ( rule__Sum__Group_1_0_0__0 ) )
                    // InternalZeroKnowledge.g:679:3: ( rule__Sum__Group_1_0_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSumAccess().getGroup_1_0_0()); 
                    }
                    // InternalZeroKnowledge.g:680:3: ( rule__Sum__Group_1_0_0__0 )
                    // InternalZeroKnowledge.g:680:4: rule__Sum__Group_1_0_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Sum__Group_1_0_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSumAccess().getGroup_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalZeroKnowledge.g:684:2: ( ( rule__Sum__Group_1_0_1__0 ) )
                    {
                    // InternalZeroKnowledge.g:684:2: ( ( rule__Sum__Group_1_0_1__0 ) )
                    // InternalZeroKnowledge.g:685:3: ( rule__Sum__Group_1_0_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSumAccess().getGroup_1_0_1()); 
                    }
                    // InternalZeroKnowledge.g:686:3: ( rule__Sum__Group_1_0_1__0 )
                    // InternalZeroKnowledge.g:686:4: rule__Sum__Group_1_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Sum__Group_1_0_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSumAccess().getGroup_1_0_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Alternatives_1_0"


    // $ANTLR start "rule__Product__Alternatives_1_0"
    // InternalZeroKnowledge.g:694:1: rule__Product__Alternatives_1_0 : ( ( ( rule__Product__Group_1_0_0__0 ) ) | ( ( rule__Product__Group_1_0_1__0 ) ) );
    public final void rule__Product__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:698:1: ( ( ( rule__Product__Group_1_0_0__0 ) ) | ( ( rule__Product__Group_1_0_1__0 ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==26) ) {
                alt3=1;
            }
            else if ( (LA3_0==27) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalZeroKnowledge.g:699:2: ( ( rule__Product__Group_1_0_0__0 ) )
                    {
                    // InternalZeroKnowledge.g:699:2: ( ( rule__Product__Group_1_0_0__0 ) )
                    // InternalZeroKnowledge.g:700:3: ( rule__Product__Group_1_0_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getProductAccess().getGroup_1_0_0()); 
                    }
                    // InternalZeroKnowledge.g:701:3: ( rule__Product__Group_1_0_0__0 )
                    // InternalZeroKnowledge.g:701:4: rule__Product__Group_1_0_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Product__Group_1_0_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getProductAccess().getGroup_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalZeroKnowledge.g:705:2: ( ( rule__Product__Group_1_0_1__0 ) )
                    {
                    // InternalZeroKnowledge.g:705:2: ( ( rule__Product__Group_1_0_1__0 ) )
                    // InternalZeroKnowledge.g:706:3: ( rule__Product__Group_1_0_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getProductAccess().getGroup_1_0_1()); 
                    }
                    // InternalZeroKnowledge.g:707:3: ( rule__Product__Group_1_0_1__0 )
                    // InternalZeroKnowledge.g:707:4: rule__Product__Group_1_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Product__Group_1_0_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getProductAccess().getGroup_1_0_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Alternatives_1_0"


    // $ANTLR start "rule__Construct__Alternatives"
    // InternalZeroKnowledge.g:715:1: rule__Construct__Alternatives : ( ( ruleStringLiteral ) | ( ( ruleTuple ) ) | ( ruleNegative ) );
    public final void rule__Construct__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:719:1: ( ( ruleStringLiteral ) | ( ( ruleTuple ) ) | ( ruleNegative ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case RULE_STRING_LITERAL:
                {
                alt4=1;
                }
                break;
            case 13:
                {
                int LA4_2 = input.LA(2);

                if ( (synpred9_InternalZeroKnowledge()) ) {
                    alt4=2;
                }
                else if ( (true) ) {
                    alt4=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_IDENTIFIER:
            case RULE_INT:
            case 25:
                {
                alt4=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalZeroKnowledge.g:720:2: ( ruleStringLiteral )
                    {
                    // InternalZeroKnowledge.g:720:2: ( ruleStringLiteral )
                    // InternalZeroKnowledge.g:721:3: ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getConstructAccess().getStringLiteralParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleStringLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getConstructAccess().getStringLiteralParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalZeroKnowledge.g:726:2: ( ( ruleTuple ) )
                    {
                    // InternalZeroKnowledge.g:726:2: ( ( ruleTuple ) )
                    // InternalZeroKnowledge.g:727:3: ( ruleTuple )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getConstructAccess().getTupleParserRuleCall_1()); 
                    }
                    // InternalZeroKnowledge.g:728:3: ( ruleTuple )
                    // InternalZeroKnowledge.g:728:4: ruleTuple
                    {
                    pushFollow(FOLLOW_2);
                    ruleTuple();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getConstructAccess().getTupleParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalZeroKnowledge.g:732:2: ( ruleNegative )
                    {
                    // InternalZeroKnowledge.g:732:2: ( ruleNegative )
                    // InternalZeroKnowledge.g:733:3: ruleNegative
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getConstructAccess().getNegativeParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleNegative();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getConstructAccess().getNegativeParserRuleCall_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Construct__Alternatives"


    // $ANTLR start "rule__Negative__Alternatives"
    // InternalZeroKnowledge.g:742:1: rule__Negative__Alternatives : ( ( ( rule__Negative__Group_0__0 ) ) | ( ruleValue ) );
    public final void rule__Negative__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:746:1: ( ( ( rule__Negative__Group_0__0 ) ) | ( ruleValue ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==25) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_IDENTIFIER||LA5_0==RULE_INT||LA5_0==13) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalZeroKnowledge.g:747:2: ( ( rule__Negative__Group_0__0 ) )
                    {
                    // InternalZeroKnowledge.g:747:2: ( ( rule__Negative__Group_0__0 ) )
                    // InternalZeroKnowledge.g:748:3: ( rule__Negative__Group_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getNegativeAccess().getGroup_0()); 
                    }
                    // InternalZeroKnowledge.g:749:3: ( rule__Negative__Group_0__0 )
                    // InternalZeroKnowledge.g:749:4: rule__Negative__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Negative__Group_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getNegativeAccess().getGroup_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalZeroKnowledge.g:753:2: ( ruleValue )
                    {
                    // InternalZeroKnowledge.g:753:2: ( ruleValue )
                    // InternalZeroKnowledge.g:754:3: ruleValue
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getNegativeAccess().getValueParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleValue();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getNegativeAccess().getValueParserRuleCall_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Negative__Alternatives"


    // $ANTLR start "rule__Value__Alternatives"
    // InternalZeroKnowledge.g:763:1: rule__Value__Alternatives : ( ( ruleFunctionCall ) | ( ruleVariable ) | ( ruleNumberLiteral ) | ( ( rule__Value__Group_3__0 ) ) );
    public final void rule__Value__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:767:1: ( ( ruleFunctionCall ) | ( ruleVariable ) | ( ruleNumberLiteral ) | ( ( rule__Value__Group_3__0 ) ) )
            int alt6=4;
            switch ( input.LA(1) ) {
            case RULE_IDENTIFIER:
                {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==13) ) {
                    alt6=1;
                }
                else if ( (LA6_1==EOF||LA6_1==10||LA6_1==12||(LA6_1>=14 && LA6_1<=28)) ) {
                    alt6=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
                {
                alt6=3;
                }
                break;
            case 13:
                {
                alt6=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalZeroKnowledge.g:768:2: ( ruleFunctionCall )
                    {
                    // InternalZeroKnowledge.g:768:2: ( ruleFunctionCall )
                    // InternalZeroKnowledge.g:769:3: ruleFunctionCall
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getValueAccess().getFunctionCallParserRuleCall_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleFunctionCall();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getValueAccess().getFunctionCallParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalZeroKnowledge.g:774:2: ( ruleVariable )
                    {
                    // InternalZeroKnowledge.g:774:2: ( ruleVariable )
                    // InternalZeroKnowledge.g:775:3: ruleVariable
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getValueAccess().getVariableParserRuleCall_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleVariable();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getValueAccess().getVariableParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalZeroKnowledge.g:780:2: ( ruleNumberLiteral )
                    {
                    // InternalZeroKnowledge.g:780:2: ( ruleNumberLiteral )
                    // InternalZeroKnowledge.g:781:3: ruleNumberLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getValueAccess().getNumberLiteralParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleNumberLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getValueAccess().getNumberLiteralParserRuleCall_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalZeroKnowledge.g:786:2: ( ( rule__Value__Group_3__0 ) )
                    {
                    // InternalZeroKnowledge.g:786:2: ( ( rule__Value__Group_3__0 ) )
                    // InternalZeroKnowledge.g:787:3: ( rule__Value__Group_3__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getValueAccess().getGroup_3()); 
                    }
                    // InternalZeroKnowledge.g:788:3: ( rule__Value__Group_3__0 )
                    // InternalZeroKnowledge.g:788:4: rule__Value__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Value__Group_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getValueAccess().getGroup_3()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Alternatives"


    // $ANTLR start "rule__Model__Group__0"
    // InternalZeroKnowledge.g:796:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:800:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // InternalZeroKnowledge.g:801:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Model__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Model__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0"


    // $ANTLR start "rule__Model__Group__0__Impl"
    // InternalZeroKnowledge.g:808:1: rule__Model__Group__0__Impl : ( ( rule__Model__FunctionsAssignment_0 )* ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:812:1: ( ( ( rule__Model__FunctionsAssignment_0 )* ) )
            // InternalZeroKnowledge.g:813:1: ( ( rule__Model__FunctionsAssignment_0 )* )
            {
            // InternalZeroKnowledge.g:813:1: ( ( rule__Model__FunctionsAssignment_0 )* )
            // InternalZeroKnowledge.g:814:2: ( rule__Model__FunctionsAssignment_0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getFunctionsAssignment_0()); 
            }
            // InternalZeroKnowledge.g:815:2: ( rule__Model__FunctionsAssignment_0 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_IDENTIFIER) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalZeroKnowledge.g:815:3: rule__Model__FunctionsAssignment_0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Model__FunctionsAssignment_0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelAccess().getFunctionsAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0__Impl"


    // $ANTLR start "rule__Model__Group__1"
    // InternalZeroKnowledge.g:823:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:827:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // InternalZeroKnowledge.g:828:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Model__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Model__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1"


    // $ANTLR start "rule__Model__Group__1__Impl"
    // InternalZeroKnowledge.g:835:1: rule__Model__Group__1__Impl : ( ( rule__Model__WitnessListAssignment_1 ) ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:839:1: ( ( ( rule__Model__WitnessListAssignment_1 ) ) )
            // InternalZeroKnowledge.g:840:1: ( ( rule__Model__WitnessListAssignment_1 ) )
            {
            // InternalZeroKnowledge.g:840:1: ( ( rule__Model__WitnessListAssignment_1 ) )
            // InternalZeroKnowledge.g:841:2: ( rule__Model__WitnessListAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getWitnessListAssignment_1()); 
            }
            // InternalZeroKnowledge.g:842:2: ( rule__Model__WitnessListAssignment_1 )
            // InternalZeroKnowledge.g:842:3: rule__Model__WitnessListAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Model__WitnessListAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelAccess().getWitnessListAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1__Impl"


    // $ANTLR start "rule__Model__Group__2"
    // InternalZeroKnowledge.g:850:1: rule__Model__Group__2 : rule__Model__Group__2__Impl rule__Model__Group__3 ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:854:1: ( rule__Model__Group__2__Impl rule__Model__Group__3 )
            // InternalZeroKnowledge.g:855:2: rule__Model__Group__2__Impl rule__Model__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Model__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Model__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__2"


    // $ANTLR start "rule__Model__Group__2__Impl"
    // InternalZeroKnowledge.g:862:1: rule__Model__Group__2__Impl : ( ( ';' )? ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:866:1: ( ( ( ';' )? ) )
            // InternalZeroKnowledge.g:867:1: ( ( ';' )? )
            {
            // InternalZeroKnowledge.g:867:1: ( ( ';' )? )
            // InternalZeroKnowledge.g:868:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getSemicolonKeyword_2()); 
            }
            // InternalZeroKnowledge.g:869:2: ( ';' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==10) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalZeroKnowledge.g:869:3: ';'
                    {
                    match(input,10,FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelAccess().getSemicolonKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__2__Impl"


    // $ANTLR start "rule__Model__Group__3"
    // InternalZeroKnowledge.g:877:1: rule__Model__Group__3 : rule__Model__Group__3__Impl rule__Model__Group__4 ;
    public final void rule__Model__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:881:1: ( rule__Model__Group__3__Impl rule__Model__Group__4 )
            // InternalZeroKnowledge.g:882:2: rule__Model__Group__3__Impl rule__Model__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__Model__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Model__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__3"


    // $ANTLR start "rule__Model__Group__3__Impl"
    // InternalZeroKnowledge.g:889:1: rule__Model__Group__3__Impl : ( ( rule__Model__ProofAssignment_3 ) ) ;
    public final void rule__Model__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:893:1: ( ( ( rule__Model__ProofAssignment_3 ) ) )
            // InternalZeroKnowledge.g:894:1: ( ( rule__Model__ProofAssignment_3 ) )
            {
            // InternalZeroKnowledge.g:894:1: ( ( rule__Model__ProofAssignment_3 ) )
            // InternalZeroKnowledge.g:895:2: ( rule__Model__ProofAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getProofAssignment_3()); 
            }
            // InternalZeroKnowledge.g:896:2: ( rule__Model__ProofAssignment_3 )
            // InternalZeroKnowledge.g:896:3: rule__Model__ProofAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Model__ProofAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelAccess().getProofAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__3__Impl"


    // $ANTLR start "rule__Model__Group__4"
    // InternalZeroKnowledge.g:904:1: rule__Model__Group__4 : rule__Model__Group__4__Impl ;
    public final void rule__Model__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:908:1: ( rule__Model__Group__4__Impl )
            // InternalZeroKnowledge.g:909:2: rule__Model__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__4"


    // $ANTLR start "rule__Model__Group__4__Impl"
    // InternalZeroKnowledge.g:915:1: rule__Model__Group__4__Impl : ( ( ';' )? ) ;
    public final void rule__Model__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:919:1: ( ( ( ';' )? ) )
            // InternalZeroKnowledge.g:920:1: ( ( ';' )? )
            {
            // InternalZeroKnowledge.g:920:1: ( ( ';' )? )
            // InternalZeroKnowledge.g:921:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getSemicolonKeyword_4()); 
            }
            // InternalZeroKnowledge.g:922:2: ( ';' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==10) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalZeroKnowledge.g:922:3: ';'
                    {
                    match(input,10,FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelAccess().getSemicolonKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__4__Impl"


    // $ANTLR start "rule__FunctionDefinition__Group__0"
    // InternalZeroKnowledge.g:931:1: rule__FunctionDefinition__Group__0 : rule__FunctionDefinition__Group__0__Impl rule__FunctionDefinition__Group__1 ;
    public final void rule__FunctionDefinition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:935:1: ( rule__FunctionDefinition__Group__0__Impl rule__FunctionDefinition__Group__1 )
            // InternalZeroKnowledge.g:936:2: rule__FunctionDefinition__Group__0__Impl rule__FunctionDefinition__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__FunctionDefinition__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__0"


    // $ANTLR start "rule__FunctionDefinition__Group__0__Impl"
    // InternalZeroKnowledge.g:943:1: rule__FunctionDefinition__Group__0__Impl : ( ( rule__FunctionDefinition__NameAssignment_0 ) ) ;
    public final void rule__FunctionDefinition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:947:1: ( ( ( rule__FunctionDefinition__NameAssignment_0 ) ) )
            // InternalZeroKnowledge.g:948:1: ( ( rule__FunctionDefinition__NameAssignment_0 ) )
            {
            // InternalZeroKnowledge.g:948:1: ( ( rule__FunctionDefinition__NameAssignment_0 ) )
            // InternalZeroKnowledge.g:949:2: ( rule__FunctionDefinition__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getNameAssignment_0()); 
            }
            // InternalZeroKnowledge.g:950:2: ( rule__FunctionDefinition__NameAssignment_0 )
            // InternalZeroKnowledge.g:950:3: rule__FunctionDefinition__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__NameAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getNameAssignment_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__0__Impl"


    // $ANTLR start "rule__FunctionDefinition__Group__1"
    // InternalZeroKnowledge.g:958:1: rule__FunctionDefinition__Group__1 : rule__FunctionDefinition__Group__1__Impl rule__FunctionDefinition__Group__2 ;
    public final void rule__FunctionDefinition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:962:1: ( rule__FunctionDefinition__Group__1__Impl rule__FunctionDefinition__Group__2 )
            // InternalZeroKnowledge.g:963:2: rule__FunctionDefinition__Group__1__Impl rule__FunctionDefinition__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__FunctionDefinition__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__1"


    // $ANTLR start "rule__FunctionDefinition__Group__1__Impl"
    // InternalZeroKnowledge.g:970:1: rule__FunctionDefinition__Group__1__Impl : ( ( rule__FunctionDefinition__ParameterListAssignment_1 ) ) ;
    public final void rule__FunctionDefinition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:974:1: ( ( ( rule__FunctionDefinition__ParameterListAssignment_1 ) ) )
            // InternalZeroKnowledge.g:975:1: ( ( rule__FunctionDefinition__ParameterListAssignment_1 ) )
            {
            // InternalZeroKnowledge.g:975:1: ( ( rule__FunctionDefinition__ParameterListAssignment_1 ) )
            // InternalZeroKnowledge.g:976:2: ( rule__FunctionDefinition__ParameterListAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getParameterListAssignment_1()); 
            }
            // InternalZeroKnowledge.g:977:2: ( rule__FunctionDefinition__ParameterListAssignment_1 )
            // InternalZeroKnowledge.g:977:3: rule__FunctionDefinition__ParameterListAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__ParameterListAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getParameterListAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__1__Impl"


    // $ANTLR start "rule__FunctionDefinition__Group__2"
    // InternalZeroKnowledge.g:985:1: rule__FunctionDefinition__Group__2 : rule__FunctionDefinition__Group__2__Impl rule__FunctionDefinition__Group__3 ;
    public final void rule__FunctionDefinition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:989:1: ( rule__FunctionDefinition__Group__2__Impl rule__FunctionDefinition__Group__3 )
            // InternalZeroKnowledge.g:990:2: rule__FunctionDefinition__Group__2__Impl rule__FunctionDefinition__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__FunctionDefinition__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__2"


    // $ANTLR start "rule__FunctionDefinition__Group__2__Impl"
    // InternalZeroKnowledge.g:997:1: rule__FunctionDefinition__Group__2__Impl : ( '{' ) ;
    public final void rule__FunctionDefinition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1001:1: ( ( '{' ) )
            // InternalZeroKnowledge.g:1002:1: ( '{' )
            {
            // InternalZeroKnowledge.g:1002:1: ( '{' )
            // InternalZeroKnowledge.g:1003:2: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getLeftCurlyBracketKeyword_2()); 
            }
            match(input,11,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getLeftCurlyBracketKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__2__Impl"


    // $ANTLR start "rule__FunctionDefinition__Group__3"
    // InternalZeroKnowledge.g:1012:1: rule__FunctionDefinition__Group__3 : rule__FunctionDefinition__Group__3__Impl rule__FunctionDefinition__Group__4 ;
    public final void rule__FunctionDefinition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1016:1: ( rule__FunctionDefinition__Group__3__Impl rule__FunctionDefinition__Group__4 )
            // InternalZeroKnowledge.g:1017:2: rule__FunctionDefinition__Group__3__Impl rule__FunctionDefinition__Group__4
            {
            pushFollow(FOLLOW_8);
            rule__FunctionDefinition__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__3"


    // $ANTLR start "rule__FunctionDefinition__Group__3__Impl"
    // InternalZeroKnowledge.g:1024:1: rule__FunctionDefinition__Group__3__Impl : ( ( rule__FunctionDefinition__BodyAssignment_3 ) ) ;
    public final void rule__FunctionDefinition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1028:1: ( ( ( rule__FunctionDefinition__BodyAssignment_3 ) ) )
            // InternalZeroKnowledge.g:1029:1: ( ( rule__FunctionDefinition__BodyAssignment_3 ) )
            {
            // InternalZeroKnowledge.g:1029:1: ( ( rule__FunctionDefinition__BodyAssignment_3 ) )
            // InternalZeroKnowledge.g:1030:2: ( rule__FunctionDefinition__BodyAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getBodyAssignment_3()); 
            }
            // InternalZeroKnowledge.g:1031:2: ( rule__FunctionDefinition__BodyAssignment_3 )
            // InternalZeroKnowledge.g:1031:3: rule__FunctionDefinition__BodyAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__BodyAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getBodyAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__3__Impl"


    // $ANTLR start "rule__FunctionDefinition__Group__4"
    // InternalZeroKnowledge.g:1039:1: rule__FunctionDefinition__Group__4 : rule__FunctionDefinition__Group__4__Impl rule__FunctionDefinition__Group__5 ;
    public final void rule__FunctionDefinition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1043:1: ( rule__FunctionDefinition__Group__4__Impl rule__FunctionDefinition__Group__5 )
            // InternalZeroKnowledge.g:1044:2: rule__FunctionDefinition__Group__4__Impl rule__FunctionDefinition__Group__5
            {
            pushFollow(FOLLOW_8);
            rule__FunctionDefinition__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__4"


    // $ANTLR start "rule__FunctionDefinition__Group__4__Impl"
    // InternalZeroKnowledge.g:1051:1: rule__FunctionDefinition__Group__4__Impl : ( ( ';' )? ) ;
    public final void rule__FunctionDefinition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1055:1: ( ( ( ';' )? ) )
            // InternalZeroKnowledge.g:1056:1: ( ( ';' )? )
            {
            // InternalZeroKnowledge.g:1056:1: ( ( ';' )? )
            // InternalZeroKnowledge.g:1057:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getSemicolonKeyword_4()); 
            }
            // InternalZeroKnowledge.g:1058:2: ( ';' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==10) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalZeroKnowledge.g:1058:3: ';'
                    {
                    match(input,10,FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getSemicolonKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__4__Impl"


    // $ANTLR start "rule__FunctionDefinition__Group__5"
    // InternalZeroKnowledge.g:1066:1: rule__FunctionDefinition__Group__5 : rule__FunctionDefinition__Group__5__Impl rule__FunctionDefinition__Group__6 ;
    public final void rule__FunctionDefinition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1070:1: ( rule__FunctionDefinition__Group__5__Impl rule__FunctionDefinition__Group__6 )
            // InternalZeroKnowledge.g:1071:2: rule__FunctionDefinition__Group__5__Impl rule__FunctionDefinition__Group__6
            {
            pushFollow(FOLLOW_6);
            rule__FunctionDefinition__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__Group__6();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__5"


    // $ANTLR start "rule__FunctionDefinition__Group__5__Impl"
    // InternalZeroKnowledge.g:1078:1: rule__FunctionDefinition__Group__5__Impl : ( '}' ) ;
    public final void rule__FunctionDefinition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1082:1: ( ( '}' ) )
            // InternalZeroKnowledge.g:1083:1: ( '}' )
            {
            // InternalZeroKnowledge.g:1083:1: ( '}' )
            // InternalZeroKnowledge.g:1084:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getRightCurlyBracketKeyword_5()); 
            }
            match(input,12,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getRightCurlyBracketKeyword_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__5__Impl"


    // $ANTLR start "rule__FunctionDefinition__Group__6"
    // InternalZeroKnowledge.g:1093:1: rule__FunctionDefinition__Group__6 : rule__FunctionDefinition__Group__6__Impl ;
    public final void rule__FunctionDefinition__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1097:1: ( rule__FunctionDefinition__Group__6__Impl )
            // InternalZeroKnowledge.g:1098:2: rule__FunctionDefinition__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__Group__6__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__6"


    // $ANTLR start "rule__FunctionDefinition__Group__6__Impl"
    // InternalZeroKnowledge.g:1104:1: rule__FunctionDefinition__Group__6__Impl : ( ( ';' )? ) ;
    public final void rule__FunctionDefinition__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1108:1: ( ( ( ';' )? ) )
            // InternalZeroKnowledge.g:1109:1: ( ( ';' )? )
            {
            // InternalZeroKnowledge.g:1109:1: ( ( ';' )? )
            // InternalZeroKnowledge.g:1110:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getSemicolonKeyword_6()); 
            }
            // InternalZeroKnowledge.g:1111:2: ( ';' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==10) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalZeroKnowledge.g:1111:3: ';'
                    {
                    match(input,10,FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getSemicolonKeyword_6()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__Group__6__Impl"


    // $ANTLR start "rule__ParameterList__Group__0"
    // InternalZeroKnowledge.g:1120:1: rule__ParameterList__Group__0 : rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1 ;
    public final void rule__ParameterList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1124:1: ( rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1 )
            // InternalZeroKnowledge.g:1125:2: rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__ParameterList__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__0"


    // $ANTLR start "rule__ParameterList__Group__0__Impl"
    // InternalZeroKnowledge.g:1132:1: rule__ParameterList__Group__0__Impl : ( '(' ) ;
    public final void rule__ParameterList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1136:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:1137:1: ( '(' )
            {
            // InternalZeroKnowledge.g:1137:1: ( '(' )
            // InternalZeroKnowledge.g:1138:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getLeftParenthesisKeyword_0()); 
            }
            match(input,13,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getLeftParenthesisKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__0__Impl"


    // $ANTLR start "rule__ParameterList__Group__1"
    // InternalZeroKnowledge.g:1147:1: rule__ParameterList__Group__1 : rule__ParameterList__Group__1__Impl rule__ParameterList__Group__2 ;
    public final void rule__ParameterList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1151:1: ( rule__ParameterList__Group__1__Impl rule__ParameterList__Group__2 )
            // InternalZeroKnowledge.g:1152:2: rule__ParameterList__Group__1__Impl rule__ParameterList__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__ParameterList__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__1"


    // $ANTLR start "rule__ParameterList__Group__1__Impl"
    // InternalZeroKnowledge.g:1159:1: rule__ParameterList__Group__1__Impl : ( ( rule__ParameterList__Group_1__0 )? ) ;
    public final void rule__ParameterList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1163:1: ( ( ( rule__ParameterList__Group_1__0 )? ) )
            // InternalZeroKnowledge.g:1164:1: ( ( rule__ParameterList__Group_1__0 )? )
            {
            // InternalZeroKnowledge.g:1164:1: ( ( rule__ParameterList__Group_1__0 )? )
            // InternalZeroKnowledge.g:1165:2: ( rule__ParameterList__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:1166:2: ( rule__ParameterList__Group_1__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_IDENTIFIER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalZeroKnowledge.g:1166:3: rule__ParameterList__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ParameterList__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__1__Impl"


    // $ANTLR start "rule__ParameterList__Group__2"
    // InternalZeroKnowledge.g:1174:1: rule__ParameterList__Group__2 : rule__ParameterList__Group__2__Impl ;
    public final void rule__ParameterList__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1178:1: ( rule__ParameterList__Group__2__Impl )
            // InternalZeroKnowledge.g:1179:2: rule__ParameterList__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__2"


    // $ANTLR start "rule__ParameterList__Group__2__Impl"
    // InternalZeroKnowledge.g:1185:1: rule__ParameterList__Group__2__Impl : ( ( rule__ParameterList__SymbolAssignment_2 ) ) ;
    public final void rule__ParameterList__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1189:1: ( ( ( rule__ParameterList__SymbolAssignment_2 ) ) )
            // InternalZeroKnowledge.g:1190:1: ( ( rule__ParameterList__SymbolAssignment_2 ) )
            {
            // InternalZeroKnowledge.g:1190:1: ( ( rule__ParameterList__SymbolAssignment_2 ) )
            // InternalZeroKnowledge.g:1191:2: ( rule__ParameterList__SymbolAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getSymbolAssignment_2()); 
            }
            // InternalZeroKnowledge.g:1192:2: ( rule__ParameterList__SymbolAssignment_2 )
            // InternalZeroKnowledge.g:1192:3: rule__ParameterList__SymbolAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__SymbolAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getSymbolAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group__2__Impl"


    // $ANTLR start "rule__ParameterList__Group_1__0"
    // InternalZeroKnowledge.g:1201:1: rule__ParameterList__Group_1__0 : rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1 ;
    public final void rule__ParameterList__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1205:1: ( rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1 )
            // InternalZeroKnowledge.g:1206:2: rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1
            {
            pushFollow(FOLLOW_10);
            rule__ParameterList__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1__0"


    // $ANTLR start "rule__ParameterList__Group_1__0__Impl"
    // InternalZeroKnowledge.g:1213:1: rule__ParameterList__Group_1__0__Impl : ( ( rule__ParameterList__ParametersAssignment_1_0 ) ) ;
    public final void rule__ParameterList__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1217:1: ( ( ( rule__ParameterList__ParametersAssignment_1_0 ) ) )
            // InternalZeroKnowledge.g:1218:1: ( ( rule__ParameterList__ParametersAssignment_1_0 ) )
            {
            // InternalZeroKnowledge.g:1218:1: ( ( rule__ParameterList__ParametersAssignment_1_0 ) )
            // InternalZeroKnowledge.g:1219:2: ( rule__ParameterList__ParametersAssignment_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getParametersAssignment_1_0()); 
            }
            // InternalZeroKnowledge.g:1220:2: ( rule__ParameterList__ParametersAssignment_1_0 )
            // InternalZeroKnowledge.g:1220:3: rule__ParameterList__ParametersAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__ParametersAssignment_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getParametersAssignment_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1__0__Impl"


    // $ANTLR start "rule__ParameterList__Group_1__1"
    // InternalZeroKnowledge.g:1228:1: rule__ParameterList__Group_1__1 : rule__ParameterList__Group_1__1__Impl ;
    public final void rule__ParameterList__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1232:1: ( rule__ParameterList__Group_1__1__Impl )
            // InternalZeroKnowledge.g:1233:2: rule__ParameterList__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1__1"


    // $ANTLR start "rule__ParameterList__Group_1__1__Impl"
    // InternalZeroKnowledge.g:1239:1: rule__ParameterList__Group_1__1__Impl : ( ( rule__ParameterList__Group_1_1__0 )* ) ;
    public final void rule__ParameterList__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1243:1: ( ( ( rule__ParameterList__Group_1_1__0 )* ) )
            // InternalZeroKnowledge.g:1244:1: ( ( rule__ParameterList__Group_1_1__0 )* )
            {
            // InternalZeroKnowledge.g:1244:1: ( ( rule__ParameterList__Group_1_1__0 )* )
            // InternalZeroKnowledge.g:1245:2: ( rule__ParameterList__Group_1_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getGroup_1_1()); 
            }
            // InternalZeroKnowledge.g:1246:2: ( rule__ParameterList__Group_1_1__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==14) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalZeroKnowledge.g:1246:3: rule__ParameterList__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__ParameterList__Group_1_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getGroup_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1__1__Impl"


    // $ANTLR start "rule__ParameterList__Group_1_1__0"
    // InternalZeroKnowledge.g:1255:1: rule__ParameterList__Group_1_1__0 : rule__ParameterList__Group_1_1__0__Impl rule__ParameterList__Group_1_1__1 ;
    public final void rule__ParameterList__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1259:1: ( rule__ParameterList__Group_1_1__0__Impl rule__ParameterList__Group_1_1__1 )
            // InternalZeroKnowledge.g:1260:2: rule__ParameterList__Group_1_1__0__Impl rule__ParameterList__Group_1_1__1
            {
            pushFollow(FOLLOW_12);
            rule__ParameterList__Group_1_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group_1_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1_1__0"


    // $ANTLR start "rule__ParameterList__Group_1_1__0__Impl"
    // InternalZeroKnowledge.g:1267:1: rule__ParameterList__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__ParameterList__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1271:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:1272:1: ( ',' )
            {
            // InternalZeroKnowledge.g:1272:1: ( ',' )
            // InternalZeroKnowledge.g:1273:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getCommaKeyword_1_1_0()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getCommaKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1_1__0__Impl"


    // $ANTLR start "rule__ParameterList__Group_1_1__1"
    // InternalZeroKnowledge.g:1282:1: rule__ParameterList__Group_1_1__1 : rule__ParameterList__Group_1_1__1__Impl ;
    public final void rule__ParameterList__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1286:1: ( rule__ParameterList__Group_1_1__1__Impl )
            // InternalZeroKnowledge.g:1287:2: rule__ParameterList__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__Group_1_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1_1__1"


    // $ANTLR start "rule__ParameterList__Group_1_1__1__Impl"
    // InternalZeroKnowledge.g:1293:1: rule__ParameterList__Group_1_1__1__Impl : ( ( rule__ParameterList__ParametersAssignment_1_1_1 ) ) ;
    public final void rule__ParameterList__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1297:1: ( ( ( rule__ParameterList__ParametersAssignment_1_1_1 ) ) )
            // InternalZeroKnowledge.g:1298:1: ( ( rule__ParameterList__ParametersAssignment_1_1_1 ) )
            {
            // InternalZeroKnowledge.g:1298:1: ( ( rule__ParameterList__ParametersAssignment_1_1_1 ) )
            // InternalZeroKnowledge.g:1299:2: ( rule__ParameterList__ParametersAssignment_1_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getParametersAssignment_1_1_1()); 
            }
            // InternalZeroKnowledge.g:1300:2: ( rule__ParameterList__ParametersAssignment_1_1_1 )
            // InternalZeroKnowledge.g:1300:3: rule__ParameterList__ParametersAssignment_1_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ParameterList__ParametersAssignment_1_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getParametersAssignment_1_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__Group_1_1__1__Impl"


    // $ANTLR start "rule__WitnessList__Group__0"
    // InternalZeroKnowledge.g:1309:1: rule__WitnessList__Group__0 : rule__WitnessList__Group__0__Impl rule__WitnessList__Group__1 ;
    public final void rule__WitnessList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1313:1: ( rule__WitnessList__Group__0__Impl rule__WitnessList__Group__1 )
            // InternalZeroKnowledge.g:1314:2: rule__WitnessList__Group__0__Impl rule__WitnessList__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__WitnessList__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group__0"


    // $ANTLR start "rule__WitnessList__Group__0__Impl"
    // InternalZeroKnowledge.g:1321:1: rule__WitnessList__Group__0__Impl : ( '(' ) ;
    public final void rule__WitnessList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1325:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:1326:1: ( '(' )
            {
            // InternalZeroKnowledge.g:1326:1: ( '(' )
            // InternalZeroKnowledge.g:1327:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getLeftParenthesisKeyword_0()); 
            }
            match(input,13,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getLeftParenthesisKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group__0__Impl"


    // $ANTLR start "rule__WitnessList__Group__1"
    // InternalZeroKnowledge.g:1336:1: rule__WitnessList__Group__1 : rule__WitnessList__Group__1__Impl rule__WitnessList__Group__2 ;
    public final void rule__WitnessList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1340:1: ( rule__WitnessList__Group__1__Impl rule__WitnessList__Group__2 )
            // InternalZeroKnowledge.g:1341:2: rule__WitnessList__Group__1__Impl rule__WitnessList__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__WitnessList__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group__1"


    // $ANTLR start "rule__WitnessList__Group__1__Impl"
    // InternalZeroKnowledge.g:1348:1: rule__WitnessList__Group__1__Impl : ( ( rule__WitnessList__Group_1__0 )? ) ;
    public final void rule__WitnessList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1352:1: ( ( ( rule__WitnessList__Group_1__0 )? ) )
            // InternalZeroKnowledge.g:1353:1: ( ( rule__WitnessList__Group_1__0 )? )
            {
            // InternalZeroKnowledge.g:1353:1: ( ( rule__WitnessList__Group_1__0 )? )
            // InternalZeroKnowledge.g:1354:2: ( rule__WitnessList__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:1355:2: ( rule__WitnessList__Group_1__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_IDENTIFIER) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalZeroKnowledge.g:1355:3: rule__WitnessList__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__WitnessList__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group__1__Impl"


    // $ANTLR start "rule__WitnessList__Group__2"
    // InternalZeroKnowledge.g:1363:1: rule__WitnessList__Group__2 : rule__WitnessList__Group__2__Impl ;
    public final void rule__WitnessList__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1367:1: ( rule__WitnessList__Group__2__Impl )
            // InternalZeroKnowledge.g:1368:2: rule__WitnessList__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group__2"


    // $ANTLR start "rule__WitnessList__Group__2__Impl"
    // InternalZeroKnowledge.g:1374:1: rule__WitnessList__Group__2__Impl : ( ( rule__WitnessList__SymbolAssignment_2 ) ) ;
    public final void rule__WitnessList__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1378:1: ( ( ( rule__WitnessList__SymbolAssignment_2 ) ) )
            // InternalZeroKnowledge.g:1379:1: ( ( rule__WitnessList__SymbolAssignment_2 ) )
            {
            // InternalZeroKnowledge.g:1379:1: ( ( rule__WitnessList__SymbolAssignment_2 ) )
            // InternalZeroKnowledge.g:1380:2: ( rule__WitnessList__SymbolAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getSymbolAssignment_2()); 
            }
            // InternalZeroKnowledge.g:1381:2: ( rule__WitnessList__SymbolAssignment_2 )
            // InternalZeroKnowledge.g:1381:3: rule__WitnessList__SymbolAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__SymbolAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getSymbolAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group__2__Impl"


    // $ANTLR start "rule__WitnessList__Group_1__0"
    // InternalZeroKnowledge.g:1390:1: rule__WitnessList__Group_1__0 : rule__WitnessList__Group_1__0__Impl rule__WitnessList__Group_1__1 ;
    public final void rule__WitnessList__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1394:1: ( rule__WitnessList__Group_1__0__Impl rule__WitnessList__Group_1__1 )
            // InternalZeroKnowledge.g:1395:2: rule__WitnessList__Group_1__0__Impl rule__WitnessList__Group_1__1
            {
            pushFollow(FOLLOW_10);
            rule__WitnessList__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group_1__0"


    // $ANTLR start "rule__WitnessList__Group_1__0__Impl"
    // InternalZeroKnowledge.g:1402:1: rule__WitnessList__Group_1__0__Impl : ( ( rule__WitnessList__WitnessesAssignment_1_0 ) ) ;
    public final void rule__WitnessList__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1406:1: ( ( ( rule__WitnessList__WitnessesAssignment_1_0 ) ) )
            // InternalZeroKnowledge.g:1407:1: ( ( rule__WitnessList__WitnessesAssignment_1_0 ) )
            {
            // InternalZeroKnowledge.g:1407:1: ( ( rule__WitnessList__WitnessesAssignment_1_0 ) )
            // InternalZeroKnowledge.g:1408:2: ( rule__WitnessList__WitnessesAssignment_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getWitnessesAssignment_1_0()); 
            }
            // InternalZeroKnowledge.g:1409:2: ( rule__WitnessList__WitnessesAssignment_1_0 )
            // InternalZeroKnowledge.g:1409:3: rule__WitnessList__WitnessesAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__WitnessesAssignment_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getWitnessesAssignment_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group_1__0__Impl"


    // $ANTLR start "rule__WitnessList__Group_1__1"
    // InternalZeroKnowledge.g:1417:1: rule__WitnessList__Group_1__1 : rule__WitnessList__Group_1__1__Impl ;
    public final void rule__WitnessList__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1421:1: ( rule__WitnessList__Group_1__1__Impl )
            // InternalZeroKnowledge.g:1422:2: rule__WitnessList__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group_1__1"


    // $ANTLR start "rule__WitnessList__Group_1__1__Impl"
    // InternalZeroKnowledge.g:1428:1: rule__WitnessList__Group_1__1__Impl : ( ( rule__WitnessList__Group_1_1__0 )* ) ;
    public final void rule__WitnessList__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1432:1: ( ( ( rule__WitnessList__Group_1_1__0 )* ) )
            // InternalZeroKnowledge.g:1433:1: ( ( rule__WitnessList__Group_1_1__0 )* )
            {
            // InternalZeroKnowledge.g:1433:1: ( ( rule__WitnessList__Group_1_1__0 )* )
            // InternalZeroKnowledge.g:1434:2: ( rule__WitnessList__Group_1_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getGroup_1_1()); 
            }
            // InternalZeroKnowledge.g:1435:2: ( rule__WitnessList__Group_1_1__0 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==14) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalZeroKnowledge.g:1435:3: rule__WitnessList__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__WitnessList__Group_1_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getGroup_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group_1__1__Impl"


    // $ANTLR start "rule__WitnessList__Group_1_1__0"
    // InternalZeroKnowledge.g:1444:1: rule__WitnessList__Group_1_1__0 : rule__WitnessList__Group_1_1__0__Impl rule__WitnessList__Group_1_1__1 ;
    public final void rule__WitnessList__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1448:1: ( rule__WitnessList__Group_1_1__0__Impl rule__WitnessList__Group_1_1__1 )
            // InternalZeroKnowledge.g:1449:2: rule__WitnessList__Group_1_1__0__Impl rule__WitnessList__Group_1_1__1
            {
            pushFollow(FOLLOW_12);
            rule__WitnessList__Group_1_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group_1_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group_1_1__0"


    // $ANTLR start "rule__WitnessList__Group_1_1__0__Impl"
    // InternalZeroKnowledge.g:1456:1: rule__WitnessList__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__WitnessList__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1460:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:1461:1: ( ',' )
            {
            // InternalZeroKnowledge.g:1461:1: ( ',' )
            // InternalZeroKnowledge.g:1462:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getCommaKeyword_1_1_0()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getCommaKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group_1_1__0__Impl"


    // $ANTLR start "rule__WitnessList__Group_1_1__1"
    // InternalZeroKnowledge.g:1471:1: rule__WitnessList__Group_1_1__1 : rule__WitnessList__Group_1_1__1__Impl ;
    public final void rule__WitnessList__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1475:1: ( rule__WitnessList__Group_1_1__1__Impl )
            // InternalZeroKnowledge.g:1476:2: rule__WitnessList__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group_1_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group_1_1__1"


    // $ANTLR start "rule__WitnessList__Group_1_1__1__Impl"
    // InternalZeroKnowledge.g:1482:1: rule__WitnessList__Group_1_1__1__Impl : ( ( rule__WitnessList__WitnessesAssignment_1_1_1 ) ) ;
    public final void rule__WitnessList__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1486:1: ( ( ( rule__WitnessList__WitnessesAssignment_1_1_1 ) ) )
            // InternalZeroKnowledge.g:1487:1: ( ( rule__WitnessList__WitnessesAssignment_1_1_1 ) )
            {
            // InternalZeroKnowledge.g:1487:1: ( ( rule__WitnessList__WitnessesAssignment_1_1_1 ) )
            // InternalZeroKnowledge.g:1488:2: ( rule__WitnessList__WitnessesAssignment_1_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getWitnessesAssignment_1_1_1()); 
            }
            // InternalZeroKnowledge.g:1489:2: ( rule__WitnessList__WitnessesAssignment_1_1_1 )
            // InternalZeroKnowledge.g:1489:3: rule__WitnessList__WitnessesAssignment_1_1_1
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__WitnessesAssignment_1_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getWitnessesAssignment_1_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__Group_1_1__1__Impl"


    // $ANTLR start "rule__Conjunction__Group__0"
    // InternalZeroKnowledge.g:1498:1: rule__Conjunction__Group__0 : rule__Conjunction__Group__0__Impl rule__Conjunction__Group__1 ;
    public final void rule__Conjunction__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1502:1: ( rule__Conjunction__Group__0__Impl rule__Conjunction__Group__1 )
            // InternalZeroKnowledge.g:1503:2: rule__Conjunction__Group__0__Impl rule__Conjunction__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__Conjunction__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Conjunction__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group__0"


    // $ANTLR start "rule__Conjunction__Group__0__Impl"
    // InternalZeroKnowledge.g:1510:1: rule__Conjunction__Group__0__Impl : ( ruleDisjunction ) ;
    public final void rule__Conjunction__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1514:1: ( ( ruleDisjunction ) )
            // InternalZeroKnowledge.g:1515:1: ( ruleDisjunction )
            {
            // InternalZeroKnowledge.g:1515:1: ( ruleDisjunction )
            // InternalZeroKnowledge.g:1516:2: ruleDisjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getDisjunctionParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleDisjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionAccess().getDisjunctionParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group__0__Impl"


    // $ANTLR start "rule__Conjunction__Group__1"
    // InternalZeroKnowledge.g:1525:1: rule__Conjunction__Group__1 : rule__Conjunction__Group__1__Impl ;
    public final void rule__Conjunction__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1529:1: ( rule__Conjunction__Group__1__Impl )
            // InternalZeroKnowledge.g:1530:2: rule__Conjunction__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Conjunction__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group__1"


    // $ANTLR start "rule__Conjunction__Group__1__Impl"
    // InternalZeroKnowledge.g:1536:1: rule__Conjunction__Group__1__Impl : ( ( rule__Conjunction__Group_1__0 )* ) ;
    public final void rule__Conjunction__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1540:1: ( ( ( rule__Conjunction__Group_1__0 )* ) )
            // InternalZeroKnowledge.g:1541:1: ( ( rule__Conjunction__Group_1__0 )* )
            {
            // InternalZeroKnowledge.g:1541:1: ( ( rule__Conjunction__Group_1__0 )* )
            // InternalZeroKnowledge.g:1542:2: ( rule__Conjunction__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:1543:2: ( rule__Conjunction__Group_1__0 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==16) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalZeroKnowledge.g:1543:3: rule__Conjunction__Group_1__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Conjunction__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group__1__Impl"


    // $ANTLR start "rule__Conjunction__Group_1__0"
    // InternalZeroKnowledge.g:1552:1: rule__Conjunction__Group_1__0 : rule__Conjunction__Group_1__0__Impl rule__Conjunction__Group_1__1 ;
    public final void rule__Conjunction__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1556:1: ( rule__Conjunction__Group_1__0__Impl rule__Conjunction__Group_1__1 )
            // InternalZeroKnowledge.g:1557:2: rule__Conjunction__Group_1__0__Impl rule__Conjunction__Group_1__1
            {
            pushFollow(FOLLOW_13);
            rule__Conjunction__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Conjunction__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group_1__0"


    // $ANTLR start "rule__Conjunction__Group_1__0__Impl"
    // InternalZeroKnowledge.g:1564:1: rule__Conjunction__Group_1__0__Impl : ( () ) ;
    public final void rule__Conjunction__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1568:1: ( ( () ) )
            // InternalZeroKnowledge.g:1569:1: ( () )
            {
            // InternalZeroKnowledge.g:1569:1: ( () )
            // InternalZeroKnowledge.g:1570:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getConjunctionLeftAction_1_0()); 
            }
            // InternalZeroKnowledge.g:1571:2: ()
            // InternalZeroKnowledge.g:1571:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionAccess().getConjunctionLeftAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group_1__0__Impl"


    // $ANTLR start "rule__Conjunction__Group_1__1"
    // InternalZeroKnowledge.g:1579:1: rule__Conjunction__Group_1__1 : rule__Conjunction__Group_1__1__Impl rule__Conjunction__Group_1__2 ;
    public final void rule__Conjunction__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1583:1: ( rule__Conjunction__Group_1__1__Impl rule__Conjunction__Group_1__2 )
            // InternalZeroKnowledge.g:1584:2: rule__Conjunction__Group_1__1__Impl rule__Conjunction__Group_1__2
            {
            pushFollow(FOLLOW_5);
            rule__Conjunction__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Conjunction__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group_1__1"


    // $ANTLR start "rule__Conjunction__Group_1__1__Impl"
    // InternalZeroKnowledge.g:1591:1: rule__Conjunction__Group_1__1__Impl : ( ( rule__Conjunction__OperationAssignment_1_1 ) ) ;
    public final void rule__Conjunction__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1595:1: ( ( ( rule__Conjunction__OperationAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:1596:1: ( ( rule__Conjunction__OperationAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:1596:1: ( ( rule__Conjunction__OperationAssignment_1_1 ) )
            // InternalZeroKnowledge.g:1597:2: ( rule__Conjunction__OperationAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getOperationAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:1598:2: ( rule__Conjunction__OperationAssignment_1_1 )
            // InternalZeroKnowledge.g:1598:3: rule__Conjunction__OperationAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Conjunction__OperationAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionAccess().getOperationAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group_1__1__Impl"


    // $ANTLR start "rule__Conjunction__Group_1__2"
    // InternalZeroKnowledge.g:1606:1: rule__Conjunction__Group_1__2 : rule__Conjunction__Group_1__2__Impl ;
    public final void rule__Conjunction__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1610:1: ( rule__Conjunction__Group_1__2__Impl )
            // InternalZeroKnowledge.g:1611:2: rule__Conjunction__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Conjunction__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group_1__2"


    // $ANTLR start "rule__Conjunction__Group_1__2__Impl"
    // InternalZeroKnowledge.g:1617:1: rule__Conjunction__Group_1__2__Impl : ( ( rule__Conjunction__RightAssignment_1_2 ) ) ;
    public final void rule__Conjunction__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1621:1: ( ( ( rule__Conjunction__RightAssignment_1_2 ) ) )
            // InternalZeroKnowledge.g:1622:1: ( ( rule__Conjunction__RightAssignment_1_2 ) )
            {
            // InternalZeroKnowledge.g:1622:1: ( ( rule__Conjunction__RightAssignment_1_2 ) )
            // InternalZeroKnowledge.g:1623:2: ( rule__Conjunction__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getRightAssignment_1_2()); 
            }
            // InternalZeroKnowledge.g:1624:2: ( rule__Conjunction__RightAssignment_1_2 )
            // InternalZeroKnowledge.g:1624:3: rule__Conjunction__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Conjunction__RightAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionAccess().getRightAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__Group_1__2__Impl"


    // $ANTLR start "rule__Disjunction__Group__0"
    // InternalZeroKnowledge.g:1633:1: rule__Disjunction__Group__0 : rule__Disjunction__Group__0__Impl rule__Disjunction__Group__1 ;
    public final void rule__Disjunction__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1637:1: ( rule__Disjunction__Group__0__Impl rule__Disjunction__Group__1 )
            // InternalZeroKnowledge.g:1638:2: rule__Disjunction__Group__0__Impl rule__Disjunction__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__Disjunction__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group__0"


    // $ANTLR start "rule__Disjunction__Group__0__Impl"
    // InternalZeroKnowledge.g:1645:1: rule__Disjunction__Group__0__Impl : ( ruleComparison ) ;
    public final void rule__Disjunction__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1649:1: ( ( ruleComparison ) )
            // InternalZeroKnowledge.g:1650:1: ( ruleComparison )
            {
            // InternalZeroKnowledge.g:1650:1: ( ruleComparison )
            // InternalZeroKnowledge.g:1651:2: ruleComparison
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getComparisonParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleComparison();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getComparisonParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group__0__Impl"


    // $ANTLR start "rule__Disjunction__Group__1"
    // InternalZeroKnowledge.g:1660:1: rule__Disjunction__Group__1 : rule__Disjunction__Group__1__Impl ;
    public final void rule__Disjunction__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1664:1: ( rule__Disjunction__Group__1__Impl )
            // InternalZeroKnowledge.g:1665:2: rule__Disjunction__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group__1"


    // $ANTLR start "rule__Disjunction__Group__1__Impl"
    // InternalZeroKnowledge.g:1671:1: rule__Disjunction__Group__1__Impl : ( ( rule__Disjunction__Group_1__0 )* ) ;
    public final void rule__Disjunction__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1675:1: ( ( ( rule__Disjunction__Group_1__0 )* ) )
            // InternalZeroKnowledge.g:1676:1: ( ( rule__Disjunction__Group_1__0 )* )
            {
            // InternalZeroKnowledge.g:1676:1: ( ( rule__Disjunction__Group_1__0 )* )
            // InternalZeroKnowledge.g:1677:2: ( rule__Disjunction__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:1678:2: ( rule__Disjunction__Group_1__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==17) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalZeroKnowledge.g:1678:3: rule__Disjunction__Group_1__0
            	    {
            	    pushFollow(FOLLOW_16);
            	    rule__Disjunction__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group__1__Impl"


    // $ANTLR start "rule__Disjunction__Group_1__0"
    // InternalZeroKnowledge.g:1687:1: rule__Disjunction__Group_1__0 : rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1 ;
    public final void rule__Disjunction__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1691:1: ( rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1 )
            // InternalZeroKnowledge.g:1692:2: rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1
            {
            pushFollow(FOLLOW_15);
            rule__Disjunction__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__0"


    // $ANTLR start "rule__Disjunction__Group_1__0__Impl"
    // InternalZeroKnowledge.g:1699:1: rule__Disjunction__Group_1__0__Impl : ( () ) ;
    public final void rule__Disjunction__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1703:1: ( ( () ) )
            // InternalZeroKnowledge.g:1704:1: ( () )
            {
            // InternalZeroKnowledge.g:1704:1: ( () )
            // InternalZeroKnowledge.g:1705:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getDisjunctionLeftAction_1_0()); 
            }
            // InternalZeroKnowledge.g:1706:2: ()
            // InternalZeroKnowledge.g:1706:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getDisjunctionLeftAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__0__Impl"


    // $ANTLR start "rule__Disjunction__Group_1__1"
    // InternalZeroKnowledge.g:1714:1: rule__Disjunction__Group_1__1 : rule__Disjunction__Group_1__1__Impl rule__Disjunction__Group_1__2 ;
    public final void rule__Disjunction__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1718:1: ( rule__Disjunction__Group_1__1__Impl rule__Disjunction__Group_1__2 )
            // InternalZeroKnowledge.g:1719:2: rule__Disjunction__Group_1__1__Impl rule__Disjunction__Group_1__2
            {
            pushFollow(FOLLOW_5);
            rule__Disjunction__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__1"


    // $ANTLR start "rule__Disjunction__Group_1__1__Impl"
    // InternalZeroKnowledge.g:1726:1: rule__Disjunction__Group_1__1__Impl : ( ( rule__Disjunction__OperationAssignment_1_1 ) ) ;
    public final void rule__Disjunction__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1730:1: ( ( ( rule__Disjunction__OperationAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:1731:1: ( ( rule__Disjunction__OperationAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:1731:1: ( ( rule__Disjunction__OperationAssignment_1_1 ) )
            // InternalZeroKnowledge.g:1732:2: ( rule__Disjunction__OperationAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getOperationAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:1733:2: ( rule__Disjunction__OperationAssignment_1_1 )
            // InternalZeroKnowledge.g:1733:3: rule__Disjunction__OperationAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__OperationAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getOperationAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__1__Impl"


    // $ANTLR start "rule__Disjunction__Group_1__2"
    // InternalZeroKnowledge.g:1741:1: rule__Disjunction__Group_1__2 : rule__Disjunction__Group_1__2__Impl ;
    public final void rule__Disjunction__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1745:1: ( rule__Disjunction__Group_1__2__Impl )
            // InternalZeroKnowledge.g:1746:2: rule__Disjunction__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__2"


    // $ANTLR start "rule__Disjunction__Group_1__2__Impl"
    // InternalZeroKnowledge.g:1752:1: rule__Disjunction__Group_1__2__Impl : ( ( rule__Disjunction__RightAssignment_1_2 ) ) ;
    public final void rule__Disjunction__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1756:1: ( ( ( rule__Disjunction__RightAssignment_1_2 ) ) )
            // InternalZeroKnowledge.g:1757:1: ( ( rule__Disjunction__RightAssignment_1_2 ) )
            {
            // InternalZeroKnowledge.g:1757:1: ( ( rule__Disjunction__RightAssignment_1_2 ) )
            // InternalZeroKnowledge.g:1758:2: ( rule__Disjunction__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getRightAssignment_1_2()); 
            }
            // InternalZeroKnowledge.g:1759:2: ( rule__Disjunction__RightAssignment_1_2 )
            // InternalZeroKnowledge.g:1759:3: rule__Disjunction__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Disjunction__RightAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getRightAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__Group_1__2__Impl"


    // $ANTLR start "rule__Comparison__Group__0"
    // InternalZeroKnowledge.g:1768:1: rule__Comparison__Group__0 : rule__Comparison__Group__0__Impl rule__Comparison__Group__1 ;
    public final void rule__Comparison__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1772:1: ( rule__Comparison__Group__0__Impl rule__Comparison__Group__1 )
            // InternalZeroKnowledge.g:1773:2: rule__Comparison__Group__0__Impl rule__Comparison__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__Comparison__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Comparison__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group__0"


    // $ANTLR start "rule__Comparison__Group__0__Impl"
    // InternalZeroKnowledge.g:1780:1: rule__Comparison__Group__0__Impl : ( ruleSum ) ;
    public final void rule__Comparison__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1784:1: ( ( ruleSum ) )
            // InternalZeroKnowledge.g:1785:1: ( ruleSum )
            {
            // InternalZeroKnowledge.g:1785:1: ( ruleSum )
            // InternalZeroKnowledge.g:1786:2: ruleSum
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getSumParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleSum();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getSumParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group__0__Impl"


    // $ANTLR start "rule__Comparison__Group__1"
    // InternalZeroKnowledge.g:1795:1: rule__Comparison__Group__1 : rule__Comparison__Group__1__Impl ;
    public final void rule__Comparison__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1799:1: ( rule__Comparison__Group__1__Impl )
            // InternalZeroKnowledge.g:1800:2: rule__Comparison__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group__1"


    // $ANTLR start "rule__Comparison__Group__1__Impl"
    // InternalZeroKnowledge.g:1806:1: rule__Comparison__Group__1__Impl : ( ( rule__Comparison__Group_1__0 )? ) ;
    public final void rule__Comparison__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1810:1: ( ( ( rule__Comparison__Group_1__0 )? ) )
            // InternalZeroKnowledge.g:1811:1: ( ( rule__Comparison__Group_1__0 )? )
            {
            // InternalZeroKnowledge.g:1811:1: ( ( rule__Comparison__Group_1__0 )? )
            // InternalZeroKnowledge.g:1812:2: ( rule__Comparison__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:1813:2: ( rule__Comparison__Group_1__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=18 && LA18_0<=23)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalZeroKnowledge.g:1813:3: rule__Comparison__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Comparison__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group__1__Impl"


    // $ANTLR start "rule__Comparison__Group_1__0"
    // InternalZeroKnowledge.g:1822:1: rule__Comparison__Group_1__0 : rule__Comparison__Group_1__0__Impl rule__Comparison__Group_1__1 ;
    public final void rule__Comparison__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1826:1: ( rule__Comparison__Group_1__0__Impl rule__Comparison__Group_1__1 )
            // InternalZeroKnowledge.g:1827:2: rule__Comparison__Group_1__0__Impl rule__Comparison__Group_1__1
            {
            pushFollow(FOLLOW_5);
            rule__Comparison__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1__0"


    // $ANTLR start "rule__Comparison__Group_1__0__Impl"
    // InternalZeroKnowledge.g:1834:1: rule__Comparison__Group_1__0__Impl : ( ( rule__Comparison__Alternatives_1_0 ) ) ;
    public final void rule__Comparison__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1838:1: ( ( ( rule__Comparison__Alternatives_1_0 ) ) )
            // InternalZeroKnowledge.g:1839:1: ( ( rule__Comparison__Alternatives_1_0 ) )
            {
            // InternalZeroKnowledge.g:1839:1: ( ( rule__Comparison__Alternatives_1_0 ) )
            // InternalZeroKnowledge.g:1840:2: ( rule__Comparison__Alternatives_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getAlternatives_1_0()); 
            }
            // InternalZeroKnowledge.g:1841:2: ( rule__Comparison__Alternatives_1_0 )
            // InternalZeroKnowledge.g:1841:3: rule__Comparison__Alternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Alternatives_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getAlternatives_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1__0__Impl"


    // $ANTLR start "rule__Comparison__Group_1__1"
    // InternalZeroKnowledge.g:1849:1: rule__Comparison__Group_1__1 : rule__Comparison__Group_1__1__Impl ;
    public final void rule__Comparison__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1853:1: ( rule__Comparison__Group_1__1__Impl )
            // InternalZeroKnowledge.g:1854:2: rule__Comparison__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1__1"


    // $ANTLR start "rule__Comparison__Group_1__1__Impl"
    // InternalZeroKnowledge.g:1860:1: rule__Comparison__Group_1__1__Impl : ( ( rule__Comparison__RightAssignment_1_1 ) ) ;
    public final void rule__Comparison__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1864:1: ( ( ( rule__Comparison__RightAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:1865:1: ( ( rule__Comparison__RightAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:1865:1: ( ( rule__Comparison__RightAssignment_1_1 ) )
            // InternalZeroKnowledge.g:1866:2: ( rule__Comparison__RightAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getRightAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:1867:2: ( rule__Comparison__RightAssignment_1_1 )
            // InternalZeroKnowledge.g:1867:3: rule__Comparison__RightAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__RightAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getRightAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1__1__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_0__0"
    // InternalZeroKnowledge.g:1876:1: rule__Comparison__Group_1_0_0__0 : rule__Comparison__Group_1_0_0__0__Impl rule__Comparison__Group_1_0_0__1 ;
    public final void rule__Comparison__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1880:1: ( rule__Comparison__Group_1_0_0__0__Impl rule__Comparison__Group_1_0_0__1 )
            // InternalZeroKnowledge.g:1881:2: rule__Comparison__Group_1_0_0__0__Impl rule__Comparison__Group_1_0_0__1
            {
            pushFollow(FOLLOW_18);
            rule__Comparison__Group_1_0_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_0__0"


    // $ANTLR start "rule__Comparison__Group_1_0_0__0__Impl"
    // InternalZeroKnowledge.g:1888:1: rule__Comparison__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1892:1: ( ( () ) )
            // InternalZeroKnowledge.g:1893:1: ( () )
            {
            // InternalZeroKnowledge.g:1893:1: ( () )
            // InternalZeroKnowledge.g:1894:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_0_0()); 
            }
            // InternalZeroKnowledge.g:1895:2: ()
            // InternalZeroKnowledge.g:1895:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_0__1"
    // InternalZeroKnowledge.g:1903:1: rule__Comparison__Group_1_0_0__1 : rule__Comparison__Group_1_0_0__1__Impl ;
    public final void rule__Comparison__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1907:1: ( rule__Comparison__Group_1_0_0__1__Impl )
            // InternalZeroKnowledge.g:1908:2: rule__Comparison__Group_1_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_0__1"


    // $ANTLR start "rule__Comparison__Group_1_0_0__1__Impl"
    // InternalZeroKnowledge.g:1914:1: rule__Comparison__Group_1_0_0__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_0_1 ) ) ;
    public final void rule__Comparison__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1918:1: ( ( ( rule__Comparison__OperationAssignment_1_0_0_1 ) ) )
            // InternalZeroKnowledge.g:1919:1: ( ( rule__Comparison__OperationAssignment_1_0_0_1 ) )
            {
            // InternalZeroKnowledge.g:1919:1: ( ( rule__Comparison__OperationAssignment_1_0_0_1 ) )
            // InternalZeroKnowledge.g:1920:2: ( rule__Comparison__OperationAssignment_1_0_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_0_1()); 
            }
            // InternalZeroKnowledge.g:1921:2: ( rule__Comparison__OperationAssignment_1_0_0_1 )
            // InternalZeroKnowledge.g:1921:3: rule__Comparison__OperationAssignment_1_0_0_1
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__OperationAssignment_1_0_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_1__0"
    // InternalZeroKnowledge.g:1930:1: rule__Comparison__Group_1_0_1__0 : rule__Comparison__Group_1_0_1__0__Impl rule__Comparison__Group_1_0_1__1 ;
    public final void rule__Comparison__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1934:1: ( rule__Comparison__Group_1_0_1__0__Impl rule__Comparison__Group_1_0_1__1 )
            // InternalZeroKnowledge.g:1935:2: rule__Comparison__Group_1_0_1__0__Impl rule__Comparison__Group_1_0_1__1
            {
            pushFollow(FOLLOW_19);
            rule__Comparison__Group_1_0_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_1__0"


    // $ANTLR start "rule__Comparison__Group_1_0_1__0__Impl"
    // InternalZeroKnowledge.g:1942:1: rule__Comparison__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1946:1: ( ( () ) )
            // InternalZeroKnowledge.g:1947:1: ( () )
            {
            // InternalZeroKnowledge.g:1947:1: ( () )
            // InternalZeroKnowledge.g:1948:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_1_0()); 
            }
            // InternalZeroKnowledge.g:1949:2: ()
            // InternalZeroKnowledge.g:1949:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_1__0__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_1__1"
    // InternalZeroKnowledge.g:1957:1: rule__Comparison__Group_1_0_1__1 : rule__Comparison__Group_1_0_1__1__Impl ;
    public final void rule__Comparison__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1961:1: ( rule__Comparison__Group_1_0_1__1__Impl )
            // InternalZeroKnowledge.g:1962:2: rule__Comparison__Group_1_0_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_1__1"


    // $ANTLR start "rule__Comparison__Group_1_0_1__1__Impl"
    // InternalZeroKnowledge.g:1968:1: rule__Comparison__Group_1_0_1__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_1_1 ) ) ;
    public final void rule__Comparison__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1972:1: ( ( ( rule__Comparison__OperationAssignment_1_0_1_1 ) ) )
            // InternalZeroKnowledge.g:1973:1: ( ( rule__Comparison__OperationAssignment_1_0_1_1 ) )
            {
            // InternalZeroKnowledge.g:1973:1: ( ( rule__Comparison__OperationAssignment_1_0_1_1 ) )
            // InternalZeroKnowledge.g:1974:2: ( rule__Comparison__OperationAssignment_1_0_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_1_1()); 
            }
            // InternalZeroKnowledge.g:1975:2: ( rule__Comparison__OperationAssignment_1_0_1_1 )
            // InternalZeroKnowledge.g:1975:3: rule__Comparison__OperationAssignment_1_0_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__OperationAssignment_1_0_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_1__1__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_2__0"
    // InternalZeroKnowledge.g:1984:1: rule__Comparison__Group_1_0_2__0 : rule__Comparison__Group_1_0_2__0__Impl rule__Comparison__Group_1_0_2__1 ;
    public final void rule__Comparison__Group_1_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1988:1: ( rule__Comparison__Group_1_0_2__0__Impl rule__Comparison__Group_1_0_2__1 )
            // InternalZeroKnowledge.g:1989:2: rule__Comparison__Group_1_0_2__0__Impl rule__Comparison__Group_1_0_2__1
            {
            pushFollow(FOLLOW_20);
            rule__Comparison__Group_1_0_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_2__0"


    // $ANTLR start "rule__Comparison__Group_1_0_2__0__Impl"
    // InternalZeroKnowledge.g:1996:1: rule__Comparison__Group_1_0_2__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2000:1: ( ( () ) )
            // InternalZeroKnowledge.g:2001:1: ( () )
            {
            // InternalZeroKnowledge.g:2001:1: ( () )
            // InternalZeroKnowledge.g:2002:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_2_0()); 
            }
            // InternalZeroKnowledge.g:2003:2: ()
            // InternalZeroKnowledge.g:2003:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_2_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_2__0__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_2__1"
    // InternalZeroKnowledge.g:2011:1: rule__Comparison__Group_1_0_2__1 : rule__Comparison__Group_1_0_2__1__Impl ;
    public final void rule__Comparison__Group_1_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2015:1: ( rule__Comparison__Group_1_0_2__1__Impl )
            // InternalZeroKnowledge.g:2016:2: rule__Comparison__Group_1_0_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_2__1"


    // $ANTLR start "rule__Comparison__Group_1_0_2__1__Impl"
    // InternalZeroKnowledge.g:2022:1: rule__Comparison__Group_1_0_2__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_2_1 ) ) ;
    public final void rule__Comparison__Group_1_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2026:1: ( ( ( rule__Comparison__OperationAssignment_1_0_2_1 ) ) )
            // InternalZeroKnowledge.g:2027:1: ( ( rule__Comparison__OperationAssignment_1_0_2_1 ) )
            {
            // InternalZeroKnowledge.g:2027:1: ( ( rule__Comparison__OperationAssignment_1_0_2_1 ) )
            // InternalZeroKnowledge.g:2028:2: ( rule__Comparison__OperationAssignment_1_0_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_2_1()); 
            }
            // InternalZeroKnowledge.g:2029:2: ( rule__Comparison__OperationAssignment_1_0_2_1 )
            // InternalZeroKnowledge.g:2029:3: rule__Comparison__OperationAssignment_1_0_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__OperationAssignment_1_0_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_2__1__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_3__0"
    // InternalZeroKnowledge.g:2038:1: rule__Comparison__Group_1_0_3__0 : rule__Comparison__Group_1_0_3__0__Impl rule__Comparison__Group_1_0_3__1 ;
    public final void rule__Comparison__Group_1_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2042:1: ( rule__Comparison__Group_1_0_3__0__Impl rule__Comparison__Group_1_0_3__1 )
            // InternalZeroKnowledge.g:2043:2: rule__Comparison__Group_1_0_3__0__Impl rule__Comparison__Group_1_0_3__1
            {
            pushFollow(FOLLOW_21);
            rule__Comparison__Group_1_0_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_3__0"


    // $ANTLR start "rule__Comparison__Group_1_0_3__0__Impl"
    // InternalZeroKnowledge.g:2050:1: rule__Comparison__Group_1_0_3__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2054:1: ( ( () ) )
            // InternalZeroKnowledge.g:2055:1: ( () )
            {
            // InternalZeroKnowledge.g:2055:1: ( () )
            // InternalZeroKnowledge.g:2056:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_3_0()); 
            }
            // InternalZeroKnowledge.g:2057:2: ()
            // InternalZeroKnowledge.g:2057:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_3_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_3__0__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_3__1"
    // InternalZeroKnowledge.g:2065:1: rule__Comparison__Group_1_0_3__1 : rule__Comparison__Group_1_0_3__1__Impl ;
    public final void rule__Comparison__Group_1_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2069:1: ( rule__Comparison__Group_1_0_3__1__Impl )
            // InternalZeroKnowledge.g:2070:2: rule__Comparison__Group_1_0_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_3__1"


    // $ANTLR start "rule__Comparison__Group_1_0_3__1__Impl"
    // InternalZeroKnowledge.g:2076:1: rule__Comparison__Group_1_0_3__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_3_1 ) ) ;
    public final void rule__Comparison__Group_1_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2080:1: ( ( ( rule__Comparison__OperationAssignment_1_0_3_1 ) ) )
            // InternalZeroKnowledge.g:2081:1: ( ( rule__Comparison__OperationAssignment_1_0_3_1 ) )
            {
            // InternalZeroKnowledge.g:2081:1: ( ( rule__Comparison__OperationAssignment_1_0_3_1 ) )
            // InternalZeroKnowledge.g:2082:2: ( rule__Comparison__OperationAssignment_1_0_3_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_3_1()); 
            }
            // InternalZeroKnowledge.g:2083:2: ( rule__Comparison__OperationAssignment_1_0_3_1 )
            // InternalZeroKnowledge.g:2083:3: rule__Comparison__OperationAssignment_1_0_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__OperationAssignment_1_0_3_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_3_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_3__1__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_4__0"
    // InternalZeroKnowledge.g:2092:1: rule__Comparison__Group_1_0_4__0 : rule__Comparison__Group_1_0_4__0__Impl rule__Comparison__Group_1_0_4__1 ;
    public final void rule__Comparison__Group_1_0_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2096:1: ( rule__Comparison__Group_1_0_4__0__Impl rule__Comparison__Group_1_0_4__1 )
            // InternalZeroKnowledge.g:2097:2: rule__Comparison__Group_1_0_4__0__Impl rule__Comparison__Group_1_0_4__1
            {
            pushFollow(FOLLOW_22);
            rule__Comparison__Group_1_0_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_4__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_4__0"


    // $ANTLR start "rule__Comparison__Group_1_0_4__0__Impl"
    // InternalZeroKnowledge.g:2104:1: rule__Comparison__Group_1_0_4__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2108:1: ( ( () ) )
            // InternalZeroKnowledge.g:2109:1: ( () )
            {
            // InternalZeroKnowledge.g:2109:1: ( () )
            // InternalZeroKnowledge.g:2110:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_4_0()); 
            }
            // InternalZeroKnowledge.g:2111:2: ()
            // InternalZeroKnowledge.g:2111:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_4_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_4__0__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_4__1"
    // InternalZeroKnowledge.g:2119:1: rule__Comparison__Group_1_0_4__1 : rule__Comparison__Group_1_0_4__1__Impl ;
    public final void rule__Comparison__Group_1_0_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2123:1: ( rule__Comparison__Group_1_0_4__1__Impl )
            // InternalZeroKnowledge.g:2124:2: rule__Comparison__Group_1_0_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_4__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_4__1"


    // $ANTLR start "rule__Comparison__Group_1_0_4__1__Impl"
    // InternalZeroKnowledge.g:2130:1: rule__Comparison__Group_1_0_4__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_4_1 ) ) ;
    public final void rule__Comparison__Group_1_0_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2134:1: ( ( ( rule__Comparison__OperationAssignment_1_0_4_1 ) ) )
            // InternalZeroKnowledge.g:2135:1: ( ( rule__Comparison__OperationAssignment_1_0_4_1 ) )
            {
            // InternalZeroKnowledge.g:2135:1: ( ( rule__Comparison__OperationAssignment_1_0_4_1 ) )
            // InternalZeroKnowledge.g:2136:2: ( rule__Comparison__OperationAssignment_1_0_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_4_1()); 
            }
            // InternalZeroKnowledge.g:2137:2: ( rule__Comparison__OperationAssignment_1_0_4_1 )
            // InternalZeroKnowledge.g:2137:3: rule__Comparison__OperationAssignment_1_0_4_1
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__OperationAssignment_1_0_4_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_4_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_4__1__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_5__0"
    // InternalZeroKnowledge.g:2146:1: rule__Comparison__Group_1_0_5__0 : rule__Comparison__Group_1_0_5__0__Impl rule__Comparison__Group_1_0_5__1 ;
    public final void rule__Comparison__Group_1_0_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2150:1: ( rule__Comparison__Group_1_0_5__0__Impl rule__Comparison__Group_1_0_5__1 )
            // InternalZeroKnowledge.g:2151:2: rule__Comparison__Group_1_0_5__0__Impl rule__Comparison__Group_1_0_5__1
            {
            pushFollow(FOLLOW_17);
            rule__Comparison__Group_1_0_5__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_5__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_5__0"


    // $ANTLR start "rule__Comparison__Group_1_0_5__0__Impl"
    // InternalZeroKnowledge.g:2158:1: rule__Comparison__Group_1_0_5__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2162:1: ( ( () ) )
            // InternalZeroKnowledge.g:2163:1: ( () )
            {
            // InternalZeroKnowledge.g:2163:1: ( () )
            // InternalZeroKnowledge.g:2164:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_5_0()); 
            }
            // InternalZeroKnowledge.g:2165:2: ()
            // InternalZeroKnowledge.g:2165:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_5_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_5__0__Impl"


    // $ANTLR start "rule__Comparison__Group_1_0_5__1"
    // InternalZeroKnowledge.g:2173:1: rule__Comparison__Group_1_0_5__1 : rule__Comparison__Group_1_0_5__1__Impl ;
    public final void rule__Comparison__Group_1_0_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2177:1: ( rule__Comparison__Group_1_0_5__1__Impl )
            // InternalZeroKnowledge.g:2178:2: rule__Comparison__Group_1_0_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__Group_1_0_5__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_5__1"


    // $ANTLR start "rule__Comparison__Group_1_0_5__1__Impl"
    // InternalZeroKnowledge.g:2184:1: rule__Comparison__Group_1_0_5__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_5_1 ) ) ;
    public final void rule__Comparison__Group_1_0_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2188:1: ( ( ( rule__Comparison__OperationAssignment_1_0_5_1 ) ) )
            // InternalZeroKnowledge.g:2189:1: ( ( rule__Comparison__OperationAssignment_1_0_5_1 ) )
            {
            // InternalZeroKnowledge.g:2189:1: ( ( rule__Comparison__OperationAssignment_1_0_5_1 ) )
            // InternalZeroKnowledge.g:2190:2: ( rule__Comparison__OperationAssignment_1_0_5_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_5_1()); 
            }
            // InternalZeroKnowledge.g:2191:2: ( rule__Comparison__OperationAssignment_1_0_5_1 )
            // InternalZeroKnowledge.g:2191:3: rule__Comparison__OperationAssignment_1_0_5_1
            {
            pushFollow(FOLLOW_2);
            rule__Comparison__OperationAssignment_1_0_5_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_5_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__Group_1_0_5__1__Impl"


    // $ANTLR start "rule__Sum__Group__0"
    // InternalZeroKnowledge.g:2200:1: rule__Sum__Group__0 : rule__Sum__Group__0__Impl rule__Sum__Group__1 ;
    public final void rule__Sum__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2204:1: ( rule__Sum__Group__0__Impl rule__Sum__Group__1 )
            // InternalZeroKnowledge.g:2205:2: rule__Sum__Group__0__Impl rule__Sum__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__Sum__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Sum__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group__0"


    // $ANTLR start "rule__Sum__Group__0__Impl"
    // InternalZeroKnowledge.g:2212:1: rule__Sum__Group__0__Impl : ( ruleProduct ) ;
    public final void rule__Sum__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2216:1: ( ( ruleProduct ) )
            // InternalZeroKnowledge.g:2217:1: ( ruleProduct )
            {
            // InternalZeroKnowledge.g:2217:1: ( ruleProduct )
            // InternalZeroKnowledge.g:2218:2: ruleProduct
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getProductParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleProduct();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getProductParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group__0__Impl"


    // $ANTLR start "rule__Sum__Group__1"
    // InternalZeroKnowledge.g:2227:1: rule__Sum__Group__1 : rule__Sum__Group__1__Impl ;
    public final void rule__Sum__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2231:1: ( rule__Sum__Group__1__Impl )
            // InternalZeroKnowledge.g:2232:2: rule__Sum__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Sum__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group__1"


    // $ANTLR start "rule__Sum__Group__1__Impl"
    // InternalZeroKnowledge.g:2238:1: rule__Sum__Group__1__Impl : ( ( rule__Sum__Group_1__0 )* ) ;
    public final void rule__Sum__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2242:1: ( ( ( rule__Sum__Group_1__0 )* ) )
            // InternalZeroKnowledge.g:2243:1: ( ( rule__Sum__Group_1__0 )* )
            {
            // InternalZeroKnowledge.g:2243:1: ( ( rule__Sum__Group_1__0 )* )
            // InternalZeroKnowledge.g:2244:2: ( rule__Sum__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:2245:2: ( rule__Sum__Group_1__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=24 && LA19_0<=25)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalZeroKnowledge.g:2245:3: rule__Sum__Group_1__0
            	    {
            	    pushFollow(FOLLOW_24);
            	    rule__Sum__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group__1__Impl"


    // $ANTLR start "rule__Sum__Group_1__0"
    // InternalZeroKnowledge.g:2254:1: rule__Sum__Group_1__0 : rule__Sum__Group_1__0__Impl rule__Sum__Group_1__1 ;
    public final void rule__Sum__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2258:1: ( rule__Sum__Group_1__0__Impl rule__Sum__Group_1__1 )
            // InternalZeroKnowledge.g:2259:2: rule__Sum__Group_1__0__Impl rule__Sum__Group_1__1
            {
            pushFollow(FOLLOW_5);
            rule__Sum__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Sum__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1__0"


    // $ANTLR start "rule__Sum__Group_1__0__Impl"
    // InternalZeroKnowledge.g:2266:1: rule__Sum__Group_1__0__Impl : ( ( rule__Sum__Alternatives_1_0 ) ) ;
    public final void rule__Sum__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2270:1: ( ( ( rule__Sum__Alternatives_1_0 ) ) )
            // InternalZeroKnowledge.g:2271:1: ( ( rule__Sum__Alternatives_1_0 ) )
            {
            // InternalZeroKnowledge.g:2271:1: ( ( rule__Sum__Alternatives_1_0 ) )
            // InternalZeroKnowledge.g:2272:2: ( rule__Sum__Alternatives_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getAlternatives_1_0()); 
            }
            // InternalZeroKnowledge.g:2273:2: ( rule__Sum__Alternatives_1_0 )
            // InternalZeroKnowledge.g:2273:3: rule__Sum__Alternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Sum__Alternatives_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getAlternatives_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1__0__Impl"


    // $ANTLR start "rule__Sum__Group_1__1"
    // InternalZeroKnowledge.g:2281:1: rule__Sum__Group_1__1 : rule__Sum__Group_1__1__Impl ;
    public final void rule__Sum__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2285:1: ( rule__Sum__Group_1__1__Impl )
            // InternalZeroKnowledge.g:2286:2: rule__Sum__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Sum__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1__1"


    // $ANTLR start "rule__Sum__Group_1__1__Impl"
    // InternalZeroKnowledge.g:2292:1: rule__Sum__Group_1__1__Impl : ( ( rule__Sum__RightAssignment_1_1 ) ) ;
    public final void rule__Sum__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2296:1: ( ( ( rule__Sum__RightAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:2297:1: ( ( rule__Sum__RightAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:2297:1: ( ( rule__Sum__RightAssignment_1_1 ) )
            // InternalZeroKnowledge.g:2298:2: ( rule__Sum__RightAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getRightAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:2299:2: ( rule__Sum__RightAssignment_1_1 )
            // InternalZeroKnowledge.g:2299:3: rule__Sum__RightAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Sum__RightAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getRightAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1__1__Impl"


    // $ANTLR start "rule__Sum__Group_1_0_0__0"
    // InternalZeroKnowledge.g:2308:1: rule__Sum__Group_1_0_0__0 : rule__Sum__Group_1_0_0__0__Impl rule__Sum__Group_1_0_0__1 ;
    public final void rule__Sum__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2312:1: ( rule__Sum__Group_1_0_0__0__Impl rule__Sum__Group_1_0_0__1 )
            // InternalZeroKnowledge.g:2313:2: rule__Sum__Group_1_0_0__0__Impl rule__Sum__Group_1_0_0__1
            {
            pushFollow(FOLLOW_25);
            rule__Sum__Group_1_0_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Sum__Group_1_0_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1_0_0__0"


    // $ANTLR start "rule__Sum__Group_1_0_0__0__Impl"
    // InternalZeroKnowledge.g:2320:1: rule__Sum__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Sum__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2324:1: ( ( () ) )
            // InternalZeroKnowledge.g:2325:1: ( () )
            {
            // InternalZeroKnowledge.g:2325:1: ( () )
            // InternalZeroKnowledge.g:2326:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getSumLeftAction_1_0_0_0()); 
            }
            // InternalZeroKnowledge.g:2327:2: ()
            // InternalZeroKnowledge.g:2327:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getSumLeftAction_1_0_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__Sum__Group_1_0_0__1"
    // InternalZeroKnowledge.g:2335:1: rule__Sum__Group_1_0_0__1 : rule__Sum__Group_1_0_0__1__Impl ;
    public final void rule__Sum__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2339:1: ( rule__Sum__Group_1_0_0__1__Impl )
            // InternalZeroKnowledge.g:2340:2: rule__Sum__Group_1_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Sum__Group_1_0_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1_0_0__1"


    // $ANTLR start "rule__Sum__Group_1_0_0__1__Impl"
    // InternalZeroKnowledge.g:2346:1: rule__Sum__Group_1_0_0__1__Impl : ( ( rule__Sum__OperationAssignment_1_0_0_1 ) ) ;
    public final void rule__Sum__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2350:1: ( ( ( rule__Sum__OperationAssignment_1_0_0_1 ) ) )
            // InternalZeroKnowledge.g:2351:1: ( ( rule__Sum__OperationAssignment_1_0_0_1 ) )
            {
            // InternalZeroKnowledge.g:2351:1: ( ( rule__Sum__OperationAssignment_1_0_0_1 ) )
            // InternalZeroKnowledge.g:2352:2: ( rule__Sum__OperationAssignment_1_0_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationAssignment_1_0_0_1()); 
            }
            // InternalZeroKnowledge.g:2353:2: ( rule__Sum__OperationAssignment_1_0_0_1 )
            // InternalZeroKnowledge.g:2353:3: rule__Sum__OperationAssignment_1_0_0_1
            {
            pushFollow(FOLLOW_2);
            rule__Sum__OperationAssignment_1_0_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getOperationAssignment_1_0_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__Sum__Group_1_0_1__0"
    // InternalZeroKnowledge.g:2362:1: rule__Sum__Group_1_0_1__0 : rule__Sum__Group_1_0_1__0__Impl rule__Sum__Group_1_0_1__1 ;
    public final void rule__Sum__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2366:1: ( rule__Sum__Group_1_0_1__0__Impl rule__Sum__Group_1_0_1__1 )
            // InternalZeroKnowledge.g:2367:2: rule__Sum__Group_1_0_1__0__Impl rule__Sum__Group_1_0_1__1
            {
            pushFollow(FOLLOW_23);
            rule__Sum__Group_1_0_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Sum__Group_1_0_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1_0_1__0"


    // $ANTLR start "rule__Sum__Group_1_0_1__0__Impl"
    // InternalZeroKnowledge.g:2374:1: rule__Sum__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Sum__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2378:1: ( ( () ) )
            // InternalZeroKnowledge.g:2379:1: ( () )
            {
            // InternalZeroKnowledge.g:2379:1: ( () )
            // InternalZeroKnowledge.g:2380:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getSumLeftAction_1_0_1_0()); 
            }
            // InternalZeroKnowledge.g:2381:2: ()
            // InternalZeroKnowledge.g:2381:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getSumLeftAction_1_0_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1_0_1__0__Impl"


    // $ANTLR start "rule__Sum__Group_1_0_1__1"
    // InternalZeroKnowledge.g:2389:1: rule__Sum__Group_1_0_1__1 : rule__Sum__Group_1_0_1__1__Impl ;
    public final void rule__Sum__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2393:1: ( rule__Sum__Group_1_0_1__1__Impl )
            // InternalZeroKnowledge.g:2394:2: rule__Sum__Group_1_0_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Sum__Group_1_0_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1_0_1__1"


    // $ANTLR start "rule__Sum__Group_1_0_1__1__Impl"
    // InternalZeroKnowledge.g:2400:1: rule__Sum__Group_1_0_1__1__Impl : ( ( rule__Sum__OperationAssignment_1_0_1_1 ) ) ;
    public final void rule__Sum__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2404:1: ( ( ( rule__Sum__OperationAssignment_1_0_1_1 ) ) )
            // InternalZeroKnowledge.g:2405:1: ( ( rule__Sum__OperationAssignment_1_0_1_1 ) )
            {
            // InternalZeroKnowledge.g:2405:1: ( ( rule__Sum__OperationAssignment_1_0_1_1 ) )
            // InternalZeroKnowledge.g:2406:2: ( rule__Sum__OperationAssignment_1_0_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationAssignment_1_0_1_1()); 
            }
            // InternalZeroKnowledge.g:2407:2: ( rule__Sum__OperationAssignment_1_0_1_1 )
            // InternalZeroKnowledge.g:2407:3: rule__Sum__OperationAssignment_1_0_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Sum__OperationAssignment_1_0_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getOperationAssignment_1_0_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__Group_1_0_1__1__Impl"


    // $ANTLR start "rule__Product__Group__0"
    // InternalZeroKnowledge.g:2416:1: rule__Product__Group__0 : rule__Product__Group__0__Impl rule__Product__Group__1 ;
    public final void rule__Product__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2420:1: ( rule__Product__Group__0__Impl rule__Product__Group__1 )
            // InternalZeroKnowledge.g:2421:2: rule__Product__Group__0__Impl rule__Product__Group__1
            {
            pushFollow(FOLLOW_26);
            rule__Product__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Product__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group__0"


    // $ANTLR start "rule__Product__Group__0__Impl"
    // InternalZeroKnowledge.g:2428:1: rule__Product__Group__0__Impl : ( rulePower ) ;
    public final void rule__Product__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2432:1: ( ( rulePower ) )
            // InternalZeroKnowledge.g:2433:1: ( rulePower )
            {
            // InternalZeroKnowledge.g:2433:1: ( rulePower )
            // InternalZeroKnowledge.g:2434:2: rulePower
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getPowerParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            rulePower();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getPowerParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group__0__Impl"


    // $ANTLR start "rule__Product__Group__1"
    // InternalZeroKnowledge.g:2443:1: rule__Product__Group__1 : rule__Product__Group__1__Impl ;
    public final void rule__Product__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2447:1: ( rule__Product__Group__1__Impl )
            // InternalZeroKnowledge.g:2448:2: rule__Product__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Product__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group__1"


    // $ANTLR start "rule__Product__Group__1__Impl"
    // InternalZeroKnowledge.g:2454:1: rule__Product__Group__1__Impl : ( ( rule__Product__Group_1__0 )* ) ;
    public final void rule__Product__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2458:1: ( ( ( rule__Product__Group_1__0 )* ) )
            // InternalZeroKnowledge.g:2459:1: ( ( rule__Product__Group_1__0 )* )
            {
            // InternalZeroKnowledge.g:2459:1: ( ( rule__Product__Group_1__0 )* )
            // InternalZeroKnowledge.g:2460:2: ( rule__Product__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:2461:2: ( rule__Product__Group_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>=26 && LA20_0<=27)) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalZeroKnowledge.g:2461:3: rule__Product__Group_1__0
            	    {
            	    pushFollow(FOLLOW_27);
            	    rule__Product__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group__1__Impl"


    // $ANTLR start "rule__Product__Group_1__0"
    // InternalZeroKnowledge.g:2470:1: rule__Product__Group_1__0 : rule__Product__Group_1__0__Impl rule__Product__Group_1__1 ;
    public final void rule__Product__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2474:1: ( rule__Product__Group_1__0__Impl rule__Product__Group_1__1 )
            // InternalZeroKnowledge.g:2475:2: rule__Product__Group_1__0__Impl rule__Product__Group_1__1
            {
            pushFollow(FOLLOW_5);
            rule__Product__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Product__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1__0"


    // $ANTLR start "rule__Product__Group_1__0__Impl"
    // InternalZeroKnowledge.g:2482:1: rule__Product__Group_1__0__Impl : ( ( rule__Product__Alternatives_1_0 ) ) ;
    public final void rule__Product__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2486:1: ( ( ( rule__Product__Alternatives_1_0 ) ) )
            // InternalZeroKnowledge.g:2487:1: ( ( rule__Product__Alternatives_1_0 ) )
            {
            // InternalZeroKnowledge.g:2487:1: ( ( rule__Product__Alternatives_1_0 ) )
            // InternalZeroKnowledge.g:2488:2: ( rule__Product__Alternatives_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getAlternatives_1_0()); 
            }
            // InternalZeroKnowledge.g:2489:2: ( rule__Product__Alternatives_1_0 )
            // InternalZeroKnowledge.g:2489:3: rule__Product__Alternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Product__Alternatives_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getAlternatives_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1__0__Impl"


    // $ANTLR start "rule__Product__Group_1__1"
    // InternalZeroKnowledge.g:2497:1: rule__Product__Group_1__1 : rule__Product__Group_1__1__Impl ;
    public final void rule__Product__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2501:1: ( rule__Product__Group_1__1__Impl )
            // InternalZeroKnowledge.g:2502:2: rule__Product__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Product__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1__1"


    // $ANTLR start "rule__Product__Group_1__1__Impl"
    // InternalZeroKnowledge.g:2508:1: rule__Product__Group_1__1__Impl : ( ( rule__Product__RightAssignment_1_1 ) ) ;
    public final void rule__Product__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2512:1: ( ( ( rule__Product__RightAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:2513:1: ( ( rule__Product__RightAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:2513:1: ( ( rule__Product__RightAssignment_1_1 ) )
            // InternalZeroKnowledge.g:2514:2: ( rule__Product__RightAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getRightAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:2515:2: ( rule__Product__RightAssignment_1_1 )
            // InternalZeroKnowledge.g:2515:3: rule__Product__RightAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Product__RightAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getRightAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1__1__Impl"


    // $ANTLR start "rule__Product__Group_1_0_0__0"
    // InternalZeroKnowledge.g:2524:1: rule__Product__Group_1_0_0__0 : rule__Product__Group_1_0_0__0__Impl rule__Product__Group_1_0_0__1 ;
    public final void rule__Product__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2528:1: ( rule__Product__Group_1_0_0__0__Impl rule__Product__Group_1_0_0__1 )
            // InternalZeroKnowledge.g:2529:2: rule__Product__Group_1_0_0__0__Impl rule__Product__Group_1_0_0__1
            {
            pushFollow(FOLLOW_28);
            rule__Product__Group_1_0_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Product__Group_1_0_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1_0_0__0"


    // $ANTLR start "rule__Product__Group_1_0_0__0__Impl"
    // InternalZeroKnowledge.g:2536:1: rule__Product__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Product__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2540:1: ( ( () ) )
            // InternalZeroKnowledge.g:2541:1: ( () )
            {
            // InternalZeroKnowledge.g:2541:1: ( () )
            // InternalZeroKnowledge.g:2542:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getProductLeftAction_1_0_0_0()); 
            }
            // InternalZeroKnowledge.g:2543:2: ()
            // InternalZeroKnowledge.g:2543:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getProductLeftAction_1_0_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__Product__Group_1_0_0__1"
    // InternalZeroKnowledge.g:2551:1: rule__Product__Group_1_0_0__1 : rule__Product__Group_1_0_0__1__Impl ;
    public final void rule__Product__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2555:1: ( rule__Product__Group_1_0_0__1__Impl )
            // InternalZeroKnowledge.g:2556:2: rule__Product__Group_1_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Product__Group_1_0_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1_0_0__1"


    // $ANTLR start "rule__Product__Group_1_0_0__1__Impl"
    // InternalZeroKnowledge.g:2562:1: rule__Product__Group_1_0_0__1__Impl : ( ( rule__Product__OperationAssignment_1_0_0_1 ) ) ;
    public final void rule__Product__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2566:1: ( ( ( rule__Product__OperationAssignment_1_0_0_1 ) ) )
            // InternalZeroKnowledge.g:2567:1: ( ( rule__Product__OperationAssignment_1_0_0_1 ) )
            {
            // InternalZeroKnowledge.g:2567:1: ( ( rule__Product__OperationAssignment_1_0_0_1 ) )
            // InternalZeroKnowledge.g:2568:2: ( rule__Product__OperationAssignment_1_0_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationAssignment_1_0_0_1()); 
            }
            // InternalZeroKnowledge.g:2569:2: ( rule__Product__OperationAssignment_1_0_0_1 )
            // InternalZeroKnowledge.g:2569:3: rule__Product__OperationAssignment_1_0_0_1
            {
            pushFollow(FOLLOW_2);
            rule__Product__OperationAssignment_1_0_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getOperationAssignment_1_0_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__Product__Group_1_0_1__0"
    // InternalZeroKnowledge.g:2578:1: rule__Product__Group_1_0_1__0 : rule__Product__Group_1_0_1__0__Impl rule__Product__Group_1_0_1__1 ;
    public final void rule__Product__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2582:1: ( rule__Product__Group_1_0_1__0__Impl rule__Product__Group_1_0_1__1 )
            // InternalZeroKnowledge.g:2583:2: rule__Product__Group_1_0_1__0__Impl rule__Product__Group_1_0_1__1
            {
            pushFollow(FOLLOW_26);
            rule__Product__Group_1_0_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Product__Group_1_0_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1_0_1__0"


    // $ANTLR start "rule__Product__Group_1_0_1__0__Impl"
    // InternalZeroKnowledge.g:2590:1: rule__Product__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Product__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2594:1: ( ( () ) )
            // InternalZeroKnowledge.g:2595:1: ( () )
            {
            // InternalZeroKnowledge.g:2595:1: ( () )
            // InternalZeroKnowledge.g:2596:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getProductLeftAction_1_0_1_0()); 
            }
            // InternalZeroKnowledge.g:2597:2: ()
            // InternalZeroKnowledge.g:2597:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getProductLeftAction_1_0_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1_0_1__0__Impl"


    // $ANTLR start "rule__Product__Group_1_0_1__1"
    // InternalZeroKnowledge.g:2605:1: rule__Product__Group_1_0_1__1 : rule__Product__Group_1_0_1__1__Impl ;
    public final void rule__Product__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2609:1: ( rule__Product__Group_1_0_1__1__Impl )
            // InternalZeroKnowledge.g:2610:2: rule__Product__Group_1_0_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Product__Group_1_0_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1_0_1__1"


    // $ANTLR start "rule__Product__Group_1_0_1__1__Impl"
    // InternalZeroKnowledge.g:2616:1: rule__Product__Group_1_0_1__1__Impl : ( ( rule__Product__OperationAssignment_1_0_1_1 ) ) ;
    public final void rule__Product__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2620:1: ( ( ( rule__Product__OperationAssignment_1_0_1_1 ) ) )
            // InternalZeroKnowledge.g:2621:1: ( ( rule__Product__OperationAssignment_1_0_1_1 ) )
            {
            // InternalZeroKnowledge.g:2621:1: ( ( rule__Product__OperationAssignment_1_0_1_1 ) )
            // InternalZeroKnowledge.g:2622:2: ( rule__Product__OperationAssignment_1_0_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationAssignment_1_0_1_1()); 
            }
            // InternalZeroKnowledge.g:2623:2: ( rule__Product__OperationAssignment_1_0_1_1 )
            // InternalZeroKnowledge.g:2623:3: rule__Product__OperationAssignment_1_0_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Product__OperationAssignment_1_0_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getOperationAssignment_1_0_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__Group_1_0_1__1__Impl"


    // $ANTLR start "rule__Power__Group__0"
    // InternalZeroKnowledge.g:2632:1: rule__Power__Group__0 : rule__Power__Group__0__Impl rule__Power__Group__1 ;
    public final void rule__Power__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2636:1: ( rule__Power__Group__0__Impl rule__Power__Group__1 )
            // InternalZeroKnowledge.g:2637:2: rule__Power__Group__0__Impl rule__Power__Group__1
            {
            pushFollow(FOLLOW_29);
            rule__Power__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Power__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group__0"


    // $ANTLR start "rule__Power__Group__0__Impl"
    // InternalZeroKnowledge.g:2644:1: rule__Power__Group__0__Impl : ( ruleConstruct ) ;
    public final void rule__Power__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2648:1: ( ( ruleConstruct ) )
            // InternalZeroKnowledge.g:2649:1: ( ruleConstruct )
            {
            // InternalZeroKnowledge.g:2649:1: ( ruleConstruct )
            // InternalZeroKnowledge.g:2650:2: ruleConstruct
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getConstructParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleConstruct();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getConstructParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group__0__Impl"


    // $ANTLR start "rule__Power__Group__1"
    // InternalZeroKnowledge.g:2659:1: rule__Power__Group__1 : rule__Power__Group__1__Impl ;
    public final void rule__Power__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2663:1: ( rule__Power__Group__1__Impl )
            // InternalZeroKnowledge.g:2664:2: rule__Power__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Power__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group__1"


    // $ANTLR start "rule__Power__Group__1__Impl"
    // InternalZeroKnowledge.g:2670:1: rule__Power__Group__1__Impl : ( ( rule__Power__Group_1__0 )? ) ;
    public final void rule__Power__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2674:1: ( ( ( rule__Power__Group_1__0 )? ) )
            // InternalZeroKnowledge.g:2675:1: ( ( rule__Power__Group_1__0 )? )
            {
            // InternalZeroKnowledge.g:2675:1: ( ( rule__Power__Group_1__0 )? )
            // InternalZeroKnowledge.g:2676:2: ( rule__Power__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:2677:2: ( rule__Power__Group_1__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==28) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalZeroKnowledge.g:2677:3: rule__Power__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Power__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group__1__Impl"


    // $ANTLR start "rule__Power__Group_1__0"
    // InternalZeroKnowledge.g:2686:1: rule__Power__Group_1__0 : rule__Power__Group_1__0__Impl rule__Power__Group_1__1 ;
    public final void rule__Power__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2690:1: ( rule__Power__Group_1__0__Impl rule__Power__Group_1__1 )
            // InternalZeroKnowledge.g:2691:2: rule__Power__Group_1__0__Impl rule__Power__Group_1__1
            {
            pushFollow(FOLLOW_29);
            rule__Power__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Power__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group_1__0"


    // $ANTLR start "rule__Power__Group_1__0__Impl"
    // InternalZeroKnowledge.g:2698:1: rule__Power__Group_1__0__Impl : ( () ) ;
    public final void rule__Power__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2702:1: ( ( () ) )
            // InternalZeroKnowledge.g:2703:1: ( () )
            {
            // InternalZeroKnowledge.g:2703:1: ( () )
            // InternalZeroKnowledge.g:2704:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getPowerLeftAction_1_0()); 
            }
            // InternalZeroKnowledge.g:2705:2: ()
            // InternalZeroKnowledge.g:2705:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getPowerLeftAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group_1__0__Impl"


    // $ANTLR start "rule__Power__Group_1__1"
    // InternalZeroKnowledge.g:2713:1: rule__Power__Group_1__1 : rule__Power__Group_1__1__Impl rule__Power__Group_1__2 ;
    public final void rule__Power__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2717:1: ( rule__Power__Group_1__1__Impl rule__Power__Group_1__2 )
            // InternalZeroKnowledge.g:2718:2: rule__Power__Group_1__1__Impl rule__Power__Group_1__2
            {
            pushFollow(FOLLOW_5);
            rule__Power__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Power__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group_1__1"


    // $ANTLR start "rule__Power__Group_1__1__Impl"
    // InternalZeroKnowledge.g:2725:1: rule__Power__Group_1__1__Impl : ( ( rule__Power__OperationAssignment_1_1 ) ) ;
    public final void rule__Power__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2729:1: ( ( ( rule__Power__OperationAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:2730:1: ( ( rule__Power__OperationAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:2730:1: ( ( rule__Power__OperationAssignment_1_1 ) )
            // InternalZeroKnowledge.g:2731:2: ( rule__Power__OperationAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getOperationAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:2732:2: ( rule__Power__OperationAssignment_1_1 )
            // InternalZeroKnowledge.g:2732:3: rule__Power__OperationAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Power__OperationAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getOperationAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group_1__1__Impl"


    // $ANTLR start "rule__Power__Group_1__2"
    // InternalZeroKnowledge.g:2740:1: rule__Power__Group_1__2 : rule__Power__Group_1__2__Impl ;
    public final void rule__Power__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2744:1: ( rule__Power__Group_1__2__Impl )
            // InternalZeroKnowledge.g:2745:2: rule__Power__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Power__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group_1__2"


    // $ANTLR start "rule__Power__Group_1__2__Impl"
    // InternalZeroKnowledge.g:2751:1: rule__Power__Group_1__2__Impl : ( ( rule__Power__RightAssignment_1_2 ) ) ;
    public final void rule__Power__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2755:1: ( ( ( rule__Power__RightAssignment_1_2 ) ) )
            // InternalZeroKnowledge.g:2756:1: ( ( rule__Power__RightAssignment_1_2 ) )
            {
            // InternalZeroKnowledge.g:2756:1: ( ( rule__Power__RightAssignment_1_2 ) )
            // InternalZeroKnowledge.g:2757:2: ( rule__Power__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getRightAssignment_1_2()); 
            }
            // InternalZeroKnowledge.g:2758:2: ( rule__Power__RightAssignment_1_2 )
            // InternalZeroKnowledge.g:2758:3: rule__Power__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Power__RightAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getRightAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group_1__2__Impl"


    // $ANTLR start "rule__Tuple__Group__0"
    // InternalZeroKnowledge.g:2767:1: rule__Tuple__Group__0 : rule__Tuple__Group__0__Impl rule__Tuple__Group__1 ;
    public final void rule__Tuple__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2771:1: ( rule__Tuple__Group__0__Impl rule__Tuple__Group__1 )
            // InternalZeroKnowledge.g:2772:2: rule__Tuple__Group__0__Impl rule__Tuple__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Tuple__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Tuple__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group__0"


    // $ANTLR start "rule__Tuple__Group__0__Impl"
    // InternalZeroKnowledge.g:2779:1: rule__Tuple__Group__0__Impl : ( ( rule__Tuple__Group_0__0 ) ) ;
    public final void rule__Tuple__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2783:1: ( ( ( rule__Tuple__Group_0__0 ) ) )
            // InternalZeroKnowledge.g:2784:1: ( ( rule__Tuple__Group_0__0 ) )
            {
            // InternalZeroKnowledge.g:2784:1: ( ( rule__Tuple__Group_0__0 ) )
            // InternalZeroKnowledge.g:2785:2: ( rule__Tuple__Group_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getGroup_0()); 
            }
            // InternalZeroKnowledge.g:2786:2: ( rule__Tuple__Group_0__0 )
            // InternalZeroKnowledge.g:2786:3: rule__Tuple__Group_0__0
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__Group_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getGroup_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group__0__Impl"


    // $ANTLR start "rule__Tuple__Group__1"
    // InternalZeroKnowledge.g:2794:1: rule__Tuple__Group__1 : rule__Tuple__Group__1__Impl rule__Tuple__Group__2 ;
    public final void rule__Tuple__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2798:1: ( rule__Tuple__Group__1__Impl rule__Tuple__Group__2 )
            // InternalZeroKnowledge.g:2799:2: rule__Tuple__Group__1__Impl rule__Tuple__Group__2
            {
            pushFollow(FOLLOW_30);
            rule__Tuple__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Tuple__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group__1"


    // $ANTLR start "rule__Tuple__Group__1__Impl"
    // InternalZeroKnowledge.g:2806:1: rule__Tuple__Group__1__Impl : ( ( rule__Tuple__ElementsAssignment_1 ) ) ;
    public final void rule__Tuple__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2810:1: ( ( ( rule__Tuple__ElementsAssignment_1 ) ) )
            // InternalZeroKnowledge.g:2811:1: ( ( rule__Tuple__ElementsAssignment_1 ) )
            {
            // InternalZeroKnowledge.g:2811:1: ( ( rule__Tuple__ElementsAssignment_1 ) )
            // InternalZeroKnowledge.g:2812:2: ( rule__Tuple__ElementsAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getElementsAssignment_1()); 
            }
            // InternalZeroKnowledge.g:2813:2: ( rule__Tuple__ElementsAssignment_1 )
            // InternalZeroKnowledge.g:2813:3: rule__Tuple__ElementsAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__ElementsAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getElementsAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group__1__Impl"


    // $ANTLR start "rule__Tuple__Group__2"
    // InternalZeroKnowledge.g:2821:1: rule__Tuple__Group__2 : rule__Tuple__Group__2__Impl rule__Tuple__Group__3 ;
    public final void rule__Tuple__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2825:1: ( rule__Tuple__Group__2__Impl rule__Tuple__Group__3 )
            // InternalZeroKnowledge.g:2826:2: rule__Tuple__Group__2__Impl rule__Tuple__Group__3
            {
            pushFollow(FOLLOW_30);
            rule__Tuple__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Tuple__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group__2"


    // $ANTLR start "rule__Tuple__Group__2__Impl"
    // InternalZeroKnowledge.g:2833:1: rule__Tuple__Group__2__Impl : ( ( rule__Tuple__Group_2__0 )* ) ;
    public final void rule__Tuple__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2837:1: ( ( ( rule__Tuple__Group_2__0 )* ) )
            // InternalZeroKnowledge.g:2838:1: ( ( rule__Tuple__Group_2__0 )* )
            {
            // InternalZeroKnowledge.g:2838:1: ( ( rule__Tuple__Group_2__0 )* )
            // InternalZeroKnowledge.g:2839:2: ( rule__Tuple__Group_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getGroup_2()); 
            }
            // InternalZeroKnowledge.g:2840:2: ( rule__Tuple__Group_2__0 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==14) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalZeroKnowledge.g:2840:3: rule__Tuple__Group_2__0
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__Tuple__Group_2__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getGroup_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group__2__Impl"


    // $ANTLR start "rule__Tuple__Group__3"
    // InternalZeroKnowledge.g:2848:1: rule__Tuple__Group__3 : rule__Tuple__Group__3__Impl ;
    public final void rule__Tuple__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2852:1: ( rule__Tuple__Group__3__Impl )
            // InternalZeroKnowledge.g:2853:2: rule__Tuple__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group__3"


    // $ANTLR start "rule__Tuple__Group__3__Impl"
    // InternalZeroKnowledge.g:2859:1: rule__Tuple__Group__3__Impl : ( ')' ) ;
    public final void rule__Tuple__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2863:1: ( ( ')' ) )
            // InternalZeroKnowledge.g:2864:1: ( ')' )
            {
            // InternalZeroKnowledge.g:2864:1: ( ')' )
            // InternalZeroKnowledge.g:2865:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getRightParenthesisKeyword_3()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getRightParenthesisKeyword_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group__3__Impl"


    // $ANTLR start "rule__Tuple__Group_0__0"
    // InternalZeroKnowledge.g:2875:1: rule__Tuple__Group_0__0 : rule__Tuple__Group_0__0__Impl ;
    public final void rule__Tuple__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2879:1: ( rule__Tuple__Group_0__0__Impl )
            // InternalZeroKnowledge.g:2880:2: rule__Tuple__Group_0__0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0__0"


    // $ANTLR start "rule__Tuple__Group_0__0__Impl"
    // InternalZeroKnowledge.g:2886:1: rule__Tuple__Group_0__0__Impl : ( ( rule__Tuple__Group_0_0__0 ) ) ;
    public final void rule__Tuple__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2890:1: ( ( ( rule__Tuple__Group_0_0__0 ) ) )
            // InternalZeroKnowledge.g:2891:1: ( ( rule__Tuple__Group_0_0__0 ) )
            {
            // InternalZeroKnowledge.g:2891:1: ( ( rule__Tuple__Group_0_0__0 ) )
            // InternalZeroKnowledge.g:2892:2: ( rule__Tuple__Group_0_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getGroup_0_0()); 
            }
            // InternalZeroKnowledge.g:2893:2: ( rule__Tuple__Group_0_0__0 )
            // InternalZeroKnowledge.g:2893:3: rule__Tuple__Group_0_0__0
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__Group_0_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getGroup_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0__0__Impl"


    // $ANTLR start "rule__Tuple__Group_0_0__0"
    // InternalZeroKnowledge.g:2902:1: rule__Tuple__Group_0_0__0 : rule__Tuple__Group_0_0__0__Impl rule__Tuple__Group_0_0__1 ;
    public final void rule__Tuple__Group_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2906:1: ( rule__Tuple__Group_0_0__0__Impl rule__Tuple__Group_0_0__1 )
            // InternalZeroKnowledge.g:2907:2: rule__Tuple__Group_0_0__0__Impl rule__Tuple__Group_0_0__1
            {
            pushFollow(FOLLOW_3);
            rule__Tuple__Group_0_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Tuple__Group_0_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0_0__0"


    // $ANTLR start "rule__Tuple__Group_0_0__0__Impl"
    // InternalZeroKnowledge.g:2914:1: rule__Tuple__Group_0_0__0__Impl : ( () ) ;
    public final void rule__Tuple__Group_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2918:1: ( ( () ) )
            // InternalZeroKnowledge.g:2919:1: ( () )
            {
            // InternalZeroKnowledge.g:2919:1: ( () )
            // InternalZeroKnowledge.g:2920:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getTupleAction_0_0_0()); 
            }
            // InternalZeroKnowledge.g:2921:2: ()
            // InternalZeroKnowledge.g:2921:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getTupleAction_0_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0_0__0__Impl"


    // $ANTLR start "rule__Tuple__Group_0_0__1"
    // InternalZeroKnowledge.g:2929:1: rule__Tuple__Group_0_0__1 : rule__Tuple__Group_0_0__1__Impl rule__Tuple__Group_0_0__2 ;
    public final void rule__Tuple__Group_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2933:1: ( rule__Tuple__Group_0_0__1__Impl rule__Tuple__Group_0_0__2 )
            // InternalZeroKnowledge.g:2934:2: rule__Tuple__Group_0_0__1__Impl rule__Tuple__Group_0_0__2
            {
            pushFollow(FOLLOW_5);
            rule__Tuple__Group_0_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Tuple__Group_0_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0_0__1"


    // $ANTLR start "rule__Tuple__Group_0_0__1__Impl"
    // InternalZeroKnowledge.g:2941:1: rule__Tuple__Group_0_0__1__Impl : ( '(' ) ;
    public final void rule__Tuple__Group_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2945:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:2946:1: ( '(' )
            {
            // InternalZeroKnowledge.g:2946:1: ( '(' )
            // InternalZeroKnowledge.g:2947:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getLeftParenthesisKeyword_0_0_1()); 
            }
            match(input,13,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getLeftParenthesisKeyword_0_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0_0__1__Impl"


    // $ANTLR start "rule__Tuple__Group_0_0__2"
    // InternalZeroKnowledge.g:2956:1: rule__Tuple__Group_0_0__2 : rule__Tuple__Group_0_0__2__Impl rule__Tuple__Group_0_0__3 ;
    public final void rule__Tuple__Group_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2960:1: ( rule__Tuple__Group_0_0__2__Impl rule__Tuple__Group_0_0__3 )
            // InternalZeroKnowledge.g:2961:2: rule__Tuple__Group_0_0__2__Impl rule__Tuple__Group_0_0__3
            {
            pushFollow(FOLLOW_10);
            rule__Tuple__Group_0_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Tuple__Group_0_0__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0_0__2"


    // $ANTLR start "rule__Tuple__Group_0_0__2__Impl"
    // InternalZeroKnowledge.g:2968:1: rule__Tuple__Group_0_0__2__Impl : ( ( rule__Tuple__ElementsAssignment_0_0_2 ) ) ;
    public final void rule__Tuple__Group_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2972:1: ( ( ( rule__Tuple__ElementsAssignment_0_0_2 ) ) )
            // InternalZeroKnowledge.g:2973:1: ( ( rule__Tuple__ElementsAssignment_0_0_2 ) )
            {
            // InternalZeroKnowledge.g:2973:1: ( ( rule__Tuple__ElementsAssignment_0_0_2 ) )
            // InternalZeroKnowledge.g:2974:2: ( rule__Tuple__ElementsAssignment_0_0_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getElementsAssignment_0_0_2()); 
            }
            // InternalZeroKnowledge.g:2975:2: ( rule__Tuple__ElementsAssignment_0_0_2 )
            // InternalZeroKnowledge.g:2975:3: rule__Tuple__ElementsAssignment_0_0_2
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__ElementsAssignment_0_0_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getElementsAssignment_0_0_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0_0__2__Impl"


    // $ANTLR start "rule__Tuple__Group_0_0__3"
    // InternalZeroKnowledge.g:2983:1: rule__Tuple__Group_0_0__3 : rule__Tuple__Group_0_0__3__Impl ;
    public final void rule__Tuple__Group_0_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2987:1: ( rule__Tuple__Group_0_0__3__Impl )
            // InternalZeroKnowledge.g:2988:2: rule__Tuple__Group_0_0__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__Group_0_0__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0_0__3"


    // $ANTLR start "rule__Tuple__Group_0_0__3__Impl"
    // InternalZeroKnowledge.g:2994:1: rule__Tuple__Group_0_0__3__Impl : ( ',' ) ;
    public final void rule__Tuple__Group_0_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2998:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:2999:1: ( ',' )
            {
            // InternalZeroKnowledge.g:2999:1: ( ',' )
            // InternalZeroKnowledge.g:3000:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getCommaKeyword_0_0_3()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getCommaKeyword_0_0_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_0_0__3__Impl"


    // $ANTLR start "rule__Tuple__Group_2__0"
    // InternalZeroKnowledge.g:3010:1: rule__Tuple__Group_2__0 : rule__Tuple__Group_2__0__Impl rule__Tuple__Group_2__1 ;
    public final void rule__Tuple__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3014:1: ( rule__Tuple__Group_2__0__Impl rule__Tuple__Group_2__1 )
            // InternalZeroKnowledge.g:3015:2: rule__Tuple__Group_2__0__Impl rule__Tuple__Group_2__1
            {
            pushFollow(FOLLOW_5);
            rule__Tuple__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Tuple__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_2__0"


    // $ANTLR start "rule__Tuple__Group_2__0__Impl"
    // InternalZeroKnowledge.g:3022:1: rule__Tuple__Group_2__0__Impl : ( ',' ) ;
    public final void rule__Tuple__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3026:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:3027:1: ( ',' )
            {
            // InternalZeroKnowledge.g:3027:1: ( ',' )
            // InternalZeroKnowledge.g:3028:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getCommaKeyword_2_0()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getCommaKeyword_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_2__0__Impl"


    // $ANTLR start "rule__Tuple__Group_2__1"
    // InternalZeroKnowledge.g:3037:1: rule__Tuple__Group_2__1 : rule__Tuple__Group_2__1__Impl ;
    public final void rule__Tuple__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3041:1: ( rule__Tuple__Group_2__1__Impl )
            // InternalZeroKnowledge.g:3042:2: rule__Tuple__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_2__1"


    // $ANTLR start "rule__Tuple__Group_2__1__Impl"
    // InternalZeroKnowledge.g:3048:1: rule__Tuple__Group_2__1__Impl : ( ( rule__Tuple__ElementsAssignment_2_1 ) ) ;
    public final void rule__Tuple__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3052:1: ( ( ( rule__Tuple__ElementsAssignment_2_1 ) ) )
            // InternalZeroKnowledge.g:3053:1: ( ( rule__Tuple__ElementsAssignment_2_1 ) )
            {
            // InternalZeroKnowledge.g:3053:1: ( ( rule__Tuple__ElementsAssignment_2_1 ) )
            // InternalZeroKnowledge.g:3054:2: ( rule__Tuple__ElementsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getElementsAssignment_2_1()); 
            }
            // InternalZeroKnowledge.g:3055:2: ( rule__Tuple__ElementsAssignment_2_1 )
            // InternalZeroKnowledge.g:3055:3: rule__Tuple__ElementsAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Tuple__ElementsAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getElementsAssignment_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__Group_2__1__Impl"


    // $ANTLR start "rule__Negative__Group_0__0"
    // InternalZeroKnowledge.g:3064:1: rule__Negative__Group_0__0 : rule__Negative__Group_0__0__Impl rule__Negative__Group_0__1 ;
    public final void rule__Negative__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3068:1: ( rule__Negative__Group_0__0__Impl rule__Negative__Group_0__1 )
            // InternalZeroKnowledge.g:3069:2: rule__Negative__Group_0__0__Impl rule__Negative__Group_0__1
            {
            pushFollow(FOLLOW_31);
            rule__Negative__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Negative__Group_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Negative__Group_0__0"


    // $ANTLR start "rule__Negative__Group_0__0__Impl"
    // InternalZeroKnowledge.g:3076:1: rule__Negative__Group_0__0__Impl : ( () ) ;
    public final void rule__Negative__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3080:1: ( ( () ) )
            // InternalZeroKnowledge.g:3081:1: ( () )
            {
            // InternalZeroKnowledge.g:3081:1: ( () )
            // InternalZeroKnowledge.g:3082:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getNegativeAction_0_0()); 
            }
            // InternalZeroKnowledge.g:3083:2: ()
            // InternalZeroKnowledge.g:3083:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNegativeAccess().getNegativeAction_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Negative__Group_0__0__Impl"


    // $ANTLR start "rule__Negative__Group_0__1"
    // InternalZeroKnowledge.g:3091:1: rule__Negative__Group_0__1 : rule__Negative__Group_0__1__Impl rule__Negative__Group_0__2 ;
    public final void rule__Negative__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3095:1: ( rule__Negative__Group_0__1__Impl rule__Negative__Group_0__2 )
            // InternalZeroKnowledge.g:3096:2: rule__Negative__Group_0__1__Impl rule__Negative__Group_0__2
            {
            pushFollow(FOLLOW_5);
            rule__Negative__Group_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Negative__Group_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Negative__Group_0__1"


    // $ANTLR start "rule__Negative__Group_0__1__Impl"
    // InternalZeroKnowledge.g:3103:1: rule__Negative__Group_0__1__Impl : ( ( rule__Negative__OperationAssignment_0_1 ) ) ;
    public final void rule__Negative__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3107:1: ( ( ( rule__Negative__OperationAssignment_0_1 ) ) )
            // InternalZeroKnowledge.g:3108:1: ( ( rule__Negative__OperationAssignment_0_1 ) )
            {
            // InternalZeroKnowledge.g:3108:1: ( ( rule__Negative__OperationAssignment_0_1 ) )
            // InternalZeroKnowledge.g:3109:2: ( rule__Negative__OperationAssignment_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getOperationAssignment_0_1()); 
            }
            // InternalZeroKnowledge.g:3110:2: ( rule__Negative__OperationAssignment_0_1 )
            // InternalZeroKnowledge.g:3110:3: rule__Negative__OperationAssignment_0_1
            {
            pushFollow(FOLLOW_2);
            rule__Negative__OperationAssignment_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNegativeAccess().getOperationAssignment_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Negative__Group_0__1__Impl"


    // $ANTLR start "rule__Negative__Group_0__2"
    // InternalZeroKnowledge.g:3118:1: rule__Negative__Group_0__2 : rule__Negative__Group_0__2__Impl ;
    public final void rule__Negative__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3122:1: ( rule__Negative__Group_0__2__Impl )
            // InternalZeroKnowledge.g:3123:2: rule__Negative__Group_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Negative__Group_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Negative__Group_0__2"


    // $ANTLR start "rule__Negative__Group_0__2__Impl"
    // InternalZeroKnowledge.g:3129:1: rule__Negative__Group_0__2__Impl : ( ( rule__Negative__TermAssignment_0_2 ) ) ;
    public final void rule__Negative__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3133:1: ( ( ( rule__Negative__TermAssignment_0_2 ) ) )
            // InternalZeroKnowledge.g:3134:1: ( ( rule__Negative__TermAssignment_0_2 ) )
            {
            // InternalZeroKnowledge.g:3134:1: ( ( rule__Negative__TermAssignment_0_2 ) )
            // InternalZeroKnowledge.g:3135:2: ( rule__Negative__TermAssignment_0_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getTermAssignment_0_2()); 
            }
            // InternalZeroKnowledge.g:3136:2: ( rule__Negative__TermAssignment_0_2 )
            // InternalZeroKnowledge.g:3136:3: rule__Negative__TermAssignment_0_2
            {
            pushFollow(FOLLOW_2);
            rule__Negative__TermAssignment_0_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNegativeAccess().getTermAssignment_0_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Negative__Group_0__2__Impl"


    // $ANTLR start "rule__Value__Group_3__0"
    // InternalZeroKnowledge.g:3145:1: rule__Value__Group_3__0 : rule__Value__Group_3__0__Impl rule__Value__Group_3__1 ;
    public final void rule__Value__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3149:1: ( rule__Value__Group_3__0__Impl rule__Value__Group_3__1 )
            // InternalZeroKnowledge.g:3150:2: rule__Value__Group_3__0__Impl rule__Value__Group_3__1
            {
            pushFollow(FOLLOW_5);
            rule__Value__Group_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Value__Group_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__0"


    // $ANTLR start "rule__Value__Group_3__0__Impl"
    // InternalZeroKnowledge.g:3157:1: rule__Value__Group_3__0__Impl : ( '(' ) ;
    public final void rule__Value__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3161:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:3162:1: ( '(' )
            {
            // InternalZeroKnowledge.g:3162:1: ( '(' )
            // InternalZeroKnowledge.g:3163:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueAccess().getLeftParenthesisKeyword_3_0()); 
            }
            match(input,13,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getValueAccess().getLeftParenthesisKeyword_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__0__Impl"


    // $ANTLR start "rule__Value__Group_3__1"
    // InternalZeroKnowledge.g:3172:1: rule__Value__Group_3__1 : rule__Value__Group_3__1__Impl rule__Value__Group_3__2 ;
    public final void rule__Value__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3176:1: ( rule__Value__Group_3__1__Impl rule__Value__Group_3__2 )
            // InternalZeroKnowledge.g:3177:2: rule__Value__Group_3__1__Impl rule__Value__Group_3__2
            {
            pushFollow(FOLLOW_32);
            rule__Value__Group_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Value__Group_3__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__1"


    // $ANTLR start "rule__Value__Group_3__1__Impl"
    // InternalZeroKnowledge.g:3184:1: rule__Value__Group_3__1__Impl : ( ruleBrackets ) ;
    public final void rule__Value__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3188:1: ( ( ruleBrackets ) )
            // InternalZeroKnowledge.g:3189:1: ( ruleBrackets )
            {
            // InternalZeroKnowledge.g:3189:1: ( ruleBrackets )
            // InternalZeroKnowledge.g:3190:2: ruleBrackets
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueAccess().getBracketsParserRuleCall_3_1()); 
            }
            pushFollow(FOLLOW_2);
            ruleBrackets();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getValueAccess().getBracketsParserRuleCall_3_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__1__Impl"


    // $ANTLR start "rule__Value__Group_3__2"
    // InternalZeroKnowledge.g:3199:1: rule__Value__Group_3__2 : rule__Value__Group_3__2__Impl ;
    public final void rule__Value__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3203:1: ( rule__Value__Group_3__2__Impl )
            // InternalZeroKnowledge.g:3204:2: rule__Value__Group_3__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Value__Group_3__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__2"


    // $ANTLR start "rule__Value__Group_3__2__Impl"
    // InternalZeroKnowledge.g:3210:1: rule__Value__Group_3__2__Impl : ( ')' ) ;
    public final void rule__Value__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3214:1: ( ( ')' ) )
            // InternalZeroKnowledge.g:3215:1: ( ')' )
            {
            // InternalZeroKnowledge.g:3215:1: ( ')' )
            // InternalZeroKnowledge.g:3216:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueAccess().getRightParenthesisKeyword_3_2()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getValueAccess().getRightParenthesisKeyword_3_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__2__Impl"


    // $ANTLR start "rule__FunctionCall__Group__0"
    // InternalZeroKnowledge.g:3226:1: rule__FunctionCall__Group__0 : rule__FunctionCall__Group__0__Impl rule__FunctionCall__Group__1 ;
    public final void rule__FunctionCall__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3230:1: ( rule__FunctionCall__Group__0__Impl rule__FunctionCall__Group__1 )
            // InternalZeroKnowledge.g:3231:2: rule__FunctionCall__Group__0__Impl rule__FunctionCall__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__FunctionCall__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group__0"


    // $ANTLR start "rule__FunctionCall__Group__0__Impl"
    // InternalZeroKnowledge.g:3238:1: rule__FunctionCall__Group__0__Impl : ( () ) ;
    public final void rule__FunctionCall__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3242:1: ( ( () ) )
            // InternalZeroKnowledge.g:3243:1: ( () )
            {
            // InternalZeroKnowledge.g:3243:1: ( () )
            // InternalZeroKnowledge.g:3244:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getFunctionCallAction_0()); 
            }
            // InternalZeroKnowledge.g:3245:2: ()
            // InternalZeroKnowledge.g:3245:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getFunctionCallAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group__0__Impl"


    // $ANTLR start "rule__FunctionCall__Group__1"
    // InternalZeroKnowledge.g:3253:1: rule__FunctionCall__Group__1 : rule__FunctionCall__Group__1__Impl ;
    public final void rule__FunctionCall__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3257:1: ( rule__FunctionCall__Group__1__Impl )
            // InternalZeroKnowledge.g:3258:2: rule__FunctionCall__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group__1"


    // $ANTLR start "rule__FunctionCall__Group__1__Impl"
    // InternalZeroKnowledge.g:3264:1: rule__FunctionCall__Group__1__Impl : ( ( rule__FunctionCall__Group_1__0 ) ) ;
    public final void rule__FunctionCall__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3268:1: ( ( ( rule__FunctionCall__Group_1__0 ) ) )
            // InternalZeroKnowledge.g:3269:1: ( ( rule__FunctionCall__Group_1__0 ) )
            {
            // InternalZeroKnowledge.g:3269:1: ( ( rule__FunctionCall__Group_1__0 ) )
            // InternalZeroKnowledge.g:3270:2: ( rule__FunctionCall__Group_1__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:3271:2: ( rule__FunctionCall__Group_1__0 )
            // InternalZeroKnowledge.g:3271:3: rule__FunctionCall__Group_1__0
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group__1__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1__0"
    // InternalZeroKnowledge.g:3280:1: rule__FunctionCall__Group_1__0 : rule__FunctionCall__Group_1__0__Impl rule__FunctionCall__Group_1__1 ;
    public final void rule__FunctionCall__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3284:1: ( rule__FunctionCall__Group_1__0__Impl rule__FunctionCall__Group_1__1 )
            // InternalZeroKnowledge.g:3285:2: rule__FunctionCall__Group_1__0__Impl rule__FunctionCall__Group_1__1
            {
            pushFollow(FOLLOW_33);
            rule__FunctionCall__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1__0"


    // $ANTLR start "rule__FunctionCall__Group_1__0__Impl"
    // InternalZeroKnowledge.g:3292:1: rule__FunctionCall__Group_1__0__Impl : ( ( rule__FunctionCall__Group_1_0__0 ) ) ;
    public final void rule__FunctionCall__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3296:1: ( ( ( rule__FunctionCall__Group_1_0__0 ) ) )
            // InternalZeroKnowledge.g:3297:1: ( ( rule__FunctionCall__Group_1_0__0 ) )
            {
            // InternalZeroKnowledge.g:3297:1: ( ( rule__FunctionCall__Group_1_0__0 ) )
            // InternalZeroKnowledge.g:3298:2: ( rule__FunctionCall__Group_1_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1_0()); 
            }
            // InternalZeroKnowledge.g:3299:2: ( rule__FunctionCall__Group_1_0__0 )
            // InternalZeroKnowledge.g:3299:3: rule__FunctionCall__Group_1_0__0
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getGroup_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1__0__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1__1"
    // InternalZeroKnowledge.g:3307:1: rule__FunctionCall__Group_1__1 : rule__FunctionCall__Group_1__1__Impl rule__FunctionCall__Group_1__2 ;
    public final void rule__FunctionCall__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3311:1: ( rule__FunctionCall__Group_1__1__Impl rule__FunctionCall__Group_1__2 )
            // InternalZeroKnowledge.g:3312:2: rule__FunctionCall__Group_1__1__Impl rule__FunctionCall__Group_1__2
            {
            pushFollow(FOLLOW_33);
            rule__FunctionCall__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1__1"


    // $ANTLR start "rule__FunctionCall__Group_1__1__Impl"
    // InternalZeroKnowledge.g:3319:1: rule__FunctionCall__Group_1__1__Impl : ( ( rule__FunctionCall__Group_1_1__0 )? ) ;
    public final void rule__FunctionCall__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3323:1: ( ( ( rule__FunctionCall__Group_1_1__0 )? ) )
            // InternalZeroKnowledge.g:3324:1: ( ( rule__FunctionCall__Group_1_1__0 )? )
            {
            // InternalZeroKnowledge.g:3324:1: ( ( rule__FunctionCall__Group_1_1__0 )? )
            // InternalZeroKnowledge.g:3325:2: ( rule__FunctionCall__Group_1_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1_1()); 
            }
            // InternalZeroKnowledge.g:3326:2: ( rule__FunctionCall__Group_1_1__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=RULE_IDENTIFIER && LA23_0<=RULE_INT)||LA23_0==13||LA23_0==25) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalZeroKnowledge.g:3326:3: rule__FunctionCall__Group_1_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__FunctionCall__Group_1_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getGroup_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1__1__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1__2"
    // InternalZeroKnowledge.g:3334:1: rule__FunctionCall__Group_1__2 : rule__FunctionCall__Group_1__2__Impl ;
    public final void rule__FunctionCall__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3338:1: ( rule__FunctionCall__Group_1__2__Impl )
            // InternalZeroKnowledge.g:3339:2: rule__FunctionCall__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1__2"


    // $ANTLR start "rule__FunctionCall__Group_1__2__Impl"
    // InternalZeroKnowledge.g:3345:1: rule__FunctionCall__Group_1__2__Impl : ( ')' ) ;
    public final void rule__FunctionCall__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3349:1: ( ( ')' ) )
            // InternalZeroKnowledge.g:3350:1: ( ')' )
            {
            // InternalZeroKnowledge.g:3350:1: ( ')' )
            // InternalZeroKnowledge.g:3351:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getRightParenthesisKeyword_1_2()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getRightParenthesisKeyword_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1__2__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1_0__0"
    // InternalZeroKnowledge.g:3361:1: rule__FunctionCall__Group_1_0__0 : rule__FunctionCall__Group_1_0__0__Impl ;
    public final void rule__FunctionCall__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3365:1: ( rule__FunctionCall__Group_1_0__0__Impl )
            // InternalZeroKnowledge.g:3366:2: rule__FunctionCall__Group_1_0__0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_0__0"


    // $ANTLR start "rule__FunctionCall__Group_1_0__0__Impl"
    // InternalZeroKnowledge.g:3372:1: rule__FunctionCall__Group_1_0__0__Impl : ( ( rule__FunctionCall__Group_1_0_0__0 ) ) ;
    public final void rule__FunctionCall__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3376:1: ( ( ( rule__FunctionCall__Group_1_0_0__0 ) ) )
            // InternalZeroKnowledge.g:3377:1: ( ( rule__FunctionCall__Group_1_0_0__0 ) )
            {
            // InternalZeroKnowledge.g:3377:1: ( ( rule__FunctionCall__Group_1_0_0__0 ) )
            // InternalZeroKnowledge.g:3378:2: ( rule__FunctionCall__Group_1_0_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1_0_0()); 
            }
            // InternalZeroKnowledge.g:3379:2: ( rule__FunctionCall__Group_1_0_0__0 )
            // InternalZeroKnowledge.g:3379:3: rule__FunctionCall__Group_1_0_0__0
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1_0_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getGroup_1_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_0__0__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1_0_0__0"
    // InternalZeroKnowledge.g:3388:1: rule__FunctionCall__Group_1_0_0__0 : rule__FunctionCall__Group_1_0_0__0__Impl rule__FunctionCall__Group_1_0_0__1 ;
    public final void rule__FunctionCall__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3392:1: ( rule__FunctionCall__Group_1_0_0__0__Impl rule__FunctionCall__Group_1_0_0__1 )
            // InternalZeroKnowledge.g:3393:2: rule__FunctionCall__Group_1_0_0__0__Impl rule__FunctionCall__Group_1_0_0__1
            {
            pushFollow(FOLLOW_3);
            rule__FunctionCall__Group_1_0_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1_0_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_0_0__0"


    // $ANTLR start "rule__FunctionCall__Group_1_0_0__0__Impl"
    // InternalZeroKnowledge.g:3400:1: rule__FunctionCall__Group_1_0_0__0__Impl : ( ( rule__FunctionCall__NameAssignment_1_0_0_0 ) ) ;
    public final void rule__FunctionCall__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3404:1: ( ( ( rule__FunctionCall__NameAssignment_1_0_0_0 ) ) )
            // InternalZeroKnowledge.g:3405:1: ( ( rule__FunctionCall__NameAssignment_1_0_0_0 ) )
            {
            // InternalZeroKnowledge.g:3405:1: ( ( rule__FunctionCall__NameAssignment_1_0_0_0 ) )
            // InternalZeroKnowledge.g:3406:2: ( rule__FunctionCall__NameAssignment_1_0_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getNameAssignment_1_0_0_0()); 
            }
            // InternalZeroKnowledge.g:3407:2: ( rule__FunctionCall__NameAssignment_1_0_0_0 )
            // InternalZeroKnowledge.g:3407:3: rule__FunctionCall__NameAssignment_1_0_0_0
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__NameAssignment_1_0_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getNameAssignment_1_0_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1_0_0__1"
    // InternalZeroKnowledge.g:3415:1: rule__FunctionCall__Group_1_0_0__1 : rule__FunctionCall__Group_1_0_0__1__Impl ;
    public final void rule__FunctionCall__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3419:1: ( rule__FunctionCall__Group_1_0_0__1__Impl )
            // InternalZeroKnowledge.g:3420:2: rule__FunctionCall__Group_1_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1_0_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_0_0__1"


    // $ANTLR start "rule__FunctionCall__Group_1_0_0__1__Impl"
    // InternalZeroKnowledge.g:3426:1: rule__FunctionCall__Group_1_0_0__1__Impl : ( '(' ) ;
    public final void rule__FunctionCall__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3430:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:3431:1: ( '(' )
            {
            // InternalZeroKnowledge.g:3431:1: ( '(' )
            // InternalZeroKnowledge.g:3432:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getLeftParenthesisKeyword_1_0_0_1()); 
            }
            match(input,13,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getLeftParenthesisKeyword_1_0_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1_1__0"
    // InternalZeroKnowledge.g:3442:1: rule__FunctionCall__Group_1_1__0 : rule__FunctionCall__Group_1_1__0__Impl rule__FunctionCall__Group_1_1__1 ;
    public final void rule__FunctionCall__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3446:1: ( rule__FunctionCall__Group_1_1__0__Impl rule__FunctionCall__Group_1_1__1 )
            // InternalZeroKnowledge.g:3447:2: rule__FunctionCall__Group_1_1__0__Impl rule__FunctionCall__Group_1_1__1
            {
            pushFollow(FOLLOW_10);
            rule__FunctionCall__Group_1_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_1__0"


    // $ANTLR start "rule__FunctionCall__Group_1_1__0__Impl"
    // InternalZeroKnowledge.g:3454:1: rule__FunctionCall__Group_1_1__0__Impl : ( ( rule__FunctionCall__ArgumentsAssignment_1_1_0 ) ) ;
    public final void rule__FunctionCall__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3458:1: ( ( ( rule__FunctionCall__ArgumentsAssignment_1_1_0 ) ) )
            // InternalZeroKnowledge.g:3459:1: ( ( rule__FunctionCall__ArgumentsAssignment_1_1_0 ) )
            {
            // InternalZeroKnowledge.g:3459:1: ( ( rule__FunctionCall__ArgumentsAssignment_1_1_0 ) )
            // InternalZeroKnowledge.g:3460:2: ( rule__FunctionCall__ArgumentsAssignment_1_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getArgumentsAssignment_1_1_0()); 
            }
            // InternalZeroKnowledge.g:3461:2: ( rule__FunctionCall__ArgumentsAssignment_1_1_0 )
            // InternalZeroKnowledge.g:3461:3: rule__FunctionCall__ArgumentsAssignment_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__ArgumentsAssignment_1_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getArgumentsAssignment_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_1__0__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1_1__1"
    // InternalZeroKnowledge.g:3469:1: rule__FunctionCall__Group_1_1__1 : rule__FunctionCall__Group_1_1__1__Impl ;
    public final void rule__FunctionCall__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3473:1: ( rule__FunctionCall__Group_1_1__1__Impl )
            // InternalZeroKnowledge.g:3474:2: rule__FunctionCall__Group_1_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_1__1"


    // $ANTLR start "rule__FunctionCall__Group_1_1__1__Impl"
    // InternalZeroKnowledge.g:3480:1: rule__FunctionCall__Group_1_1__1__Impl : ( ( rule__FunctionCall__Group_1_1_1__0 )* ) ;
    public final void rule__FunctionCall__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3484:1: ( ( ( rule__FunctionCall__Group_1_1_1__0 )* ) )
            // InternalZeroKnowledge.g:3485:1: ( ( rule__FunctionCall__Group_1_1_1__0 )* )
            {
            // InternalZeroKnowledge.g:3485:1: ( ( rule__FunctionCall__Group_1_1_1__0 )* )
            // InternalZeroKnowledge.g:3486:2: ( rule__FunctionCall__Group_1_1_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1_1_1()); 
            }
            // InternalZeroKnowledge.g:3487:2: ( rule__FunctionCall__Group_1_1_1__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==14) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalZeroKnowledge.g:3487:3: rule__FunctionCall__Group_1_1_1__0
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__FunctionCall__Group_1_1_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getGroup_1_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_1__1__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1_1_1__0"
    // InternalZeroKnowledge.g:3496:1: rule__FunctionCall__Group_1_1_1__0 : rule__FunctionCall__Group_1_1_1__0__Impl rule__FunctionCall__Group_1_1_1__1 ;
    public final void rule__FunctionCall__Group_1_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3500:1: ( rule__FunctionCall__Group_1_1_1__0__Impl rule__FunctionCall__Group_1_1_1__1 )
            // InternalZeroKnowledge.g:3501:2: rule__FunctionCall__Group_1_1_1__0__Impl rule__FunctionCall__Group_1_1_1__1
            {
            pushFollow(FOLLOW_5);
            rule__FunctionCall__Group_1_1_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1_1_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_1_1__0"


    // $ANTLR start "rule__FunctionCall__Group_1_1_1__0__Impl"
    // InternalZeroKnowledge.g:3508:1: rule__FunctionCall__Group_1_1_1__0__Impl : ( ',' ) ;
    public final void rule__FunctionCall__Group_1_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3512:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:3513:1: ( ',' )
            {
            // InternalZeroKnowledge.g:3513:1: ( ',' )
            // InternalZeroKnowledge.g:3514:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getCommaKeyword_1_1_1_0()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getCommaKeyword_1_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_1_1__0__Impl"


    // $ANTLR start "rule__FunctionCall__Group_1_1_1__1"
    // InternalZeroKnowledge.g:3523:1: rule__FunctionCall__Group_1_1_1__1 : rule__FunctionCall__Group_1_1_1__1__Impl ;
    public final void rule__FunctionCall__Group_1_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3527:1: ( rule__FunctionCall__Group_1_1_1__1__Impl )
            // InternalZeroKnowledge.g:3528:2: rule__FunctionCall__Group_1_1_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__Group_1_1_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_1_1__1"


    // $ANTLR start "rule__FunctionCall__Group_1_1_1__1__Impl"
    // InternalZeroKnowledge.g:3534:1: rule__FunctionCall__Group_1_1_1__1__Impl : ( ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 ) ) ;
    public final void rule__FunctionCall__Group_1_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3538:1: ( ( ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 ) ) )
            // InternalZeroKnowledge.g:3539:1: ( ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 ) )
            {
            // InternalZeroKnowledge.g:3539:1: ( ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 ) )
            // InternalZeroKnowledge.g:3540:2: ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getArgumentsAssignment_1_1_1_1()); 
            }
            // InternalZeroKnowledge.g:3541:2: ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 )
            // InternalZeroKnowledge.g:3541:3: rule__FunctionCall__ArgumentsAssignment_1_1_1_1
            {
            pushFollow(FOLLOW_2);
            rule__FunctionCall__ArgumentsAssignment_1_1_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getArgumentsAssignment_1_1_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__Group_1_1_1__1__Impl"


    // $ANTLR start "rule__Argument__Group__0"
    // InternalZeroKnowledge.g:3550:1: rule__Argument__Group__0 : rule__Argument__Group__0__Impl rule__Argument__Group__1 ;
    public final void rule__Argument__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3554:1: ( rule__Argument__Group__0__Impl rule__Argument__Group__1 )
            // InternalZeroKnowledge.g:3555:2: rule__Argument__Group__0__Impl rule__Argument__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Argument__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Argument__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Argument__Group__0"


    // $ANTLR start "rule__Argument__Group__0__Impl"
    // InternalZeroKnowledge.g:3562:1: rule__Argument__Group__0__Impl : ( () ) ;
    public final void rule__Argument__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3566:1: ( ( () ) )
            // InternalZeroKnowledge.g:3567:1: ( () )
            {
            // InternalZeroKnowledge.g:3567:1: ( () )
            // InternalZeroKnowledge.g:3568:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getArgumentAccess().getArgumentAction_0()); 
            }
            // InternalZeroKnowledge.g:3569:2: ()
            // InternalZeroKnowledge.g:3569:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getArgumentAccess().getArgumentAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Argument__Group__0__Impl"


    // $ANTLR start "rule__Argument__Group__1"
    // InternalZeroKnowledge.g:3577:1: rule__Argument__Group__1 : rule__Argument__Group__1__Impl ;
    public final void rule__Argument__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3581:1: ( rule__Argument__Group__1__Impl )
            // InternalZeroKnowledge.g:3582:2: rule__Argument__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Argument__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Argument__Group__1"


    // $ANTLR start "rule__Argument__Group__1__Impl"
    // InternalZeroKnowledge.g:3588:1: rule__Argument__Group__1__Impl : ( ( rule__Argument__ExpressionAssignment_1 ) ) ;
    public final void rule__Argument__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3592:1: ( ( ( rule__Argument__ExpressionAssignment_1 ) ) )
            // InternalZeroKnowledge.g:3593:1: ( ( rule__Argument__ExpressionAssignment_1 ) )
            {
            // InternalZeroKnowledge.g:3593:1: ( ( rule__Argument__ExpressionAssignment_1 ) )
            // InternalZeroKnowledge.g:3594:2: ( rule__Argument__ExpressionAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getArgumentAccess().getExpressionAssignment_1()); 
            }
            // InternalZeroKnowledge.g:3595:2: ( rule__Argument__ExpressionAssignment_1 )
            // InternalZeroKnowledge.g:3595:3: rule__Argument__ExpressionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Argument__ExpressionAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getArgumentAccess().getExpressionAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Argument__Group__1__Impl"


    // $ANTLR start "rule__Brackets__Group__0"
    // InternalZeroKnowledge.g:3604:1: rule__Brackets__Group__0 : rule__Brackets__Group__0__Impl rule__Brackets__Group__1 ;
    public final void rule__Brackets__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3608:1: ( rule__Brackets__Group__0__Impl rule__Brackets__Group__1 )
            // InternalZeroKnowledge.g:3609:2: rule__Brackets__Group__0__Impl rule__Brackets__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Brackets__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Brackets__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Brackets__Group__0"


    // $ANTLR start "rule__Brackets__Group__0__Impl"
    // InternalZeroKnowledge.g:3616:1: rule__Brackets__Group__0__Impl : ( () ) ;
    public final void rule__Brackets__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3620:1: ( ( () ) )
            // InternalZeroKnowledge.g:3621:1: ( () )
            {
            // InternalZeroKnowledge.g:3621:1: ( () )
            // InternalZeroKnowledge.g:3622:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBracketsAccess().getBracketsAction_0()); 
            }
            // InternalZeroKnowledge.g:3623:2: ()
            // InternalZeroKnowledge.g:3623:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBracketsAccess().getBracketsAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Brackets__Group__0__Impl"


    // $ANTLR start "rule__Brackets__Group__1"
    // InternalZeroKnowledge.g:3631:1: rule__Brackets__Group__1 : rule__Brackets__Group__1__Impl ;
    public final void rule__Brackets__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3635:1: ( rule__Brackets__Group__1__Impl )
            // InternalZeroKnowledge.g:3636:2: rule__Brackets__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Brackets__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Brackets__Group__1"


    // $ANTLR start "rule__Brackets__Group__1__Impl"
    // InternalZeroKnowledge.g:3642:1: rule__Brackets__Group__1__Impl : ( ( rule__Brackets__ContentAssignment_1 ) ) ;
    public final void rule__Brackets__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3646:1: ( ( ( rule__Brackets__ContentAssignment_1 ) ) )
            // InternalZeroKnowledge.g:3647:1: ( ( rule__Brackets__ContentAssignment_1 ) )
            {
            // InternalZeroKnowledge.g:3647:1: ( ( rule__Brackets__ContentAssignment_1 ) )
            // InternalZeroKnowledge.g:3648:2: ( rule__Brackets__ContentAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBracketsAccess().getContentAssignment_1()); 
            }
            // InternalZeroKnowledge.g:3649:2: ( rule__Brackets__ContentAssignment_1 )
            // InternalZeroKnowledge.g:3649:3: rule__Brackets__ContentAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Brackets__ContentAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBracketsAccess().getContentAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Brackets__Group__1__Impl"


    // $ANTLR start "rule__Model__FunctionsAssignment_0"
    // InternalZeroKnowledge.g:3658:1: rule__Model__FunctionsAssignment_0 : ( ruleFunctionDefinition ) ;
    public final void rule__Model__FunctionsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3662:1: ( ( ruleFunctionDefinition ) )
            // InternalZeroKnowledge.g:3663:2: ( ruleFunctionDefinition )
            {
            // InternalZeroKnowledge.g:3663:2: ( ruleFunctionDefinition )
            // InternalZeroKnowledge.g:3664:3: ruleFunctionDefinition
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getFunctionsFunctionDefinitionParserRuleCall_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleFunctionDefinition();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelAccess().getFunctionsFunctionDefinitionParserRuleCall_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__FunctionsAssignment_0"


    // $ANTLR start "rule__Model__WitnessListAssignment_1"
    // InternalZeroKnowledge.g:3673:1: rule__Model__WitnessListAssignment_1 : ( ruleWitnessList ) ;
    public final void rule__Model__WitnessListAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3677:1: ( ( ruleWitnessList ) )
            // InternalZeroKnowledge.g:3678:2: ( ruleWitnessList )
            {
            // InternalZeroKnowledge.g:3678:2: ( ruleWitnessList )
            // InternalZeroKnowledge.g:3679:3: ruleWitnessList
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getWitnessListWitnessListParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleWitnessList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelAccess().getWitnessListWitnessListParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__WitnessListAssignment_1"


    // $ANTLR start "rule__Model__ProofAssignment_3"
    // InternalZeroKnowledge.g:3688:1: rule__Model__ProofAssignment_3 : ( ruleExpression ) ;
    public final void rule__Model__ProofAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3692:1: ( ( ruleExpression ) )
            // InternalZeroKnowledge.g:3693:2: ( ruleExpression )
            {
            // InternalZeroKnowledge.g:3693:2: ( ruleExpression )
            // InternalZeroKnowledge.g:3694:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getProofExpressionParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getModelAccess().getProofExpressionParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__ProofAssignment_3"


    // $ANTLR start "rule__FunctionDefinition__NameAssignment_0"
    // InternalZeroKnowledge.g:3703:1: rule__FunctionDefinition__NameAssignment_0 : ( RULE_IDENTIFIER ) ;
    public final void rule__FunctionDefinition__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3707:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:3708:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:3708:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:3709:3: RULE_IDENTIFIER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getNameIDENTIFIERTerminalRuleCall_0_0()); 
            }
            match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getNameIDENTIFIERTerminalRuleCall_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__NameAssignment_0"


    // $ANTLR start "rule__FunctionDefinition__ParameterListAssignment_1"
    // InternalZeroKnowledge.g:3718:1: rule__FunctionDefinition__ParameterListAssignment_1 : ( ruleParameterList ) ;
    public final void rule__FunctionDefinition__ParameterListAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3722:1: ( ( ruleParameterList ) )
            // InternalZeroKnowledge.g:3723:2: ( ruleParameterList )
            {
            // InternalZeroKnowledge.g:3723:2: ( ruleParameterList )
            // InternalZeroKnowledge.g:3724:3: ruleParameterList
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getParameterListParameterListParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleParameterList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getParameterListParameterListParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__ParameterListAssignment_1"


    // $ANTLR start "rule__FunctionDefinition__BodyAssignment_3"
    // InternalZeroKnowledge.g:3733:1: rule__FunctionDefinition__BodyAssignment_3 : ( ruleExpression ) ;
    public final void rule__FunctionDefinition__BodyAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3737:1: ( ( ruleExpression ) )
            // InternalZeroKnowledge.g:3738:2: ( ruleExpression )
            {
            // InternalZeroKnowledge.g:3738:2: ( ruleExpression )
            // InternalZeroKnowledge.g:3739:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getBodyExpressionParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getBodyExpressionParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionDefinition__BodyAssignment_3"


    // $ANTLR start "rule__ParameterList__ParametersAssignment_1_0"
    // InternalZeroKnowledge.g:3748:1: rule__ParameterList__ParametersAssignment_1_0 : ( ruleParameter ) ;
    public final void rule__ParameterList__ParametersAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3752:1: ( ( ruleParameter ) )
            // InternalZeroKnowledge.g:3753:2: ( ruleParameter )
            {
            // InternalZeroKnowledge.g:3753:2: ( ruleParameter )
            // InternalZeroKnowledge.g:3754:3: ruleParameter
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getParametersParameterParserRuleCall_1_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleParameter();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getParametersParameterParserRuleCall_1_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__ParametersAssignment_1_0"


    // $ANTLR start "rule__ParameterList__ParametersAssignment_1_1_1"
    // InternalZeroKnowledge.g:3763:1: rule__ParameterList__ParametersAssignment_1_1_1 : ( ruleParameter ) ;
    public final void rule__ParameterList__ParametersAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3767:1: ( ( ruleParameter ) )
            // InternalZeroKnowledge.g:3768:2: ( ruleParameter )
            {
            // InternalZeroKnowledge.g:3768:2: ( ruleParameter )
            // InternalZeroKnowledge.g:3769:3: ruleParameter
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getParametersParameterParserRuleCall_1_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleParameter();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getParametersParameterParserRuleCall_1_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__ParametersAssignment_1_1_1"


    // $ANTLR start "rule__ParameterList__SymbolAssignment_2"
    // InternalZeroKnowledge.g:3778:1: rule__ParameterList__SymbolAssignment_2 : ( ( ')' ) ) ;
    public final void rule__ParameterList__SymbolAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3782:1: ( ( ( ')' ) ) )
            // InternalZeroKnowledge.g:3783:2: ( ( ')' ) )
            {
            // InternalZeroKnowledge.g:3783:2: ( ( ')' ) )
            // InternalZeroKnowledge.g:3784:3: ( ')' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getSymbolRightParenthesisKeyword_2_0()); 
            }
            // InternalZeroKnowledge.g:3785:3: ( ')' )
            // InternalZeroKnowledge.g:3786:4: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getSymbolRightParenthesisKeyword_2_0()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getSymbolRightParenthesisKeyword_2_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getSymbolRightParenthesisKeyword_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ParameterList__SymbolAssignment_2"


    // $ANTLR start "rule__Parameter__NameAssignment"
    // InternalZeroKnowledge.g:3797:1: rule__Parameter__NameAssignment : ( RULE_IDENTIFIER ) ;
    public final void rule__Parameter__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3801:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:3802:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:3802:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:3803:3: RULE_IDENTIFIER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterAccess().getNameIDENTIFIERTerminalRuleCall_0()); 
            }
            match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterAccess().getNameIDENTIFIERTerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__NameAssignment"


    // $ANTLR start "rule__WitnessList__WitnessesAssignment_1_0"
    // InternalZeroKnowledge.g:3812:1: rule__WitnessList__WitnessesAssignment_1_0 : ( ruleWitness ) ;
    public final void rule__WitnessList__WitnessesAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3816:1: ( ( ruleWitness ) )
            // InternalZeroKnowledge.g:3817:2: ( ruleWitness )
            {
            // InternalZeroKnowledge.g:3817:2: ( ruleWitness )
            // InternalZeroKnowledge.g:3818:3: ruleWitness
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_1_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleWitness();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_1_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__WitnessesAssignment_1_0"


    // $ANTLR start "rule__WitnessList__WitnessesAssignment_1_1_1"
    // InternalZeroKnowledge.g:3827:1: rule__WitnessList__WitnessesAssignment_1_1_1 : ( ruleWitness ) ;
    public final void rule__WitnessList__WitnessesAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3831:1: ( ( ruleWitness ) )
            // InternalZeroKnowledge.g:3832:2: ( ruleWitness )
            {
            // InternalZeroKnowledge.g:3832:2: ( ruleWitness )
            // InternalZeroKnowledge.g:3833:3: ruleWitness
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_1_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleWitness();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_1_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__WitnessesAssignment_1_1_1"


    // $ANTLR start "rule__WitnessList__SymbolAssignment_2"
    // InternalZeroKnowledge.g:3842:1: rule__WitnessList__SymbolAssignment_2 : ( ( ')' ) ) ;
    public final void rule__WitnessList__SymbolAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3846:1: ( ( ( ')' ) ) )
            // InternalZeroKnowledge.g:3847:2: ( ( ')' ) )
            {
            // InternalZeroKnowledge.g:3847:2: ( ( ')' ) )
            // InternalZeroKnowledge.g:3848:3: ( ')' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getSymbolRightParenthesisKeyword_2_0()); 
            }
            // InternalZeroKnowledge.g:3849:3: ( ')' )
            // InternalZeroKnowledge.g:3850:4: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getSymbolRightParenthesisKeyword_2_0()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getSymbolRightParenthesisKeyword_2_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getSymbolRightParenthesisKeyword_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WitnessList__SymbolAssignment_2"


    // $ANTLR start "rule__Witness__NameAssignment"
    // InternalZeroKnowledge.g:3861:1: rule__Witness__NameAssignment : ( RULE_IDENTIFIER ) ;
    public final void rule__Witness__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3865:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:3866:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:3866:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:3867:3: RULE_IDENTIFIER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessAccess().getNameIDENTIFIERTerminalRuleCall_0()); 
            }
            match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessAccess().getNameIDENTIFIERTerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Witness__NameAssignment"


    // $ANTLR start "rule__Conjunction__OperationAssignment_1_1"
    // InternalZeroKnowledge.g:3876:1: rule__Conjunction__OperationAssignment_1_1 : ( ( '&' ) ) ;
    public final void rule__Conjunction__OperationAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3880:1: ( ( ( '&' ) ) )
            // InternalZeroKnowledge.g:3881:2: ( ( '&' ) )
            {
            // InternalZeroKnowledge.g:3881:2: ( ( '&' ) )
            // InternalZeroKnowledge.g:3882:3: ( '&' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getOperationAmpersandKeyword_1_1_0()); 
            }
            // InternalZeroKnowledge.g:3883:3: ( '&' )
            // InternalZeroKnowledge.g:3884:4: '&'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getOperationAmpersandKeyword_1_1_0()); 
            }
            match(input,16,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionAccess().getOperationAmpersandKeyword_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionAccess().getOperationAmpersandKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__OperationAssignment_1_1"


    // $ANTLR start "rule__Conjunction__RightAssignment_1_2"
    // InternalZeroKnowledge.g:3895:1: rule__Conjunction__RightAssignment_1_2 : ( ruleDisjunction ) ;
    public final void rule__Conjunction__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3899:1: ( ( ruleDisjunction ) )
            // InternalZeroKnowledge.g:3900:2: ( ruleDisjunction )
            {
            // InternalZeroKnowledge.g:3900:2: ( ruleDisjunction )
            // InternalZeroKnowledge.g:3901:3: ruleDisjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getRightDisjunctionParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleDisjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConjunctionAccess().getRightDisjunctionParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Conjunction__RightAssignment_1_2"


    // $ANTLR start "rule__Disjunction__OperationAssignment_1_1"
    // InternalZeroKnowledge.g:3910:1: rule__Disjunction__OperationAssignment_1_1 : ( ( '|' ) ) ;
    public final void rule__Disjunction__OperationAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3914:1: ( ( ( '|' ) ) )
            // InternalZeroKnowledge.g:3915:2: ( ( '|' ) )
            {
            // InternalZeroKnowledge.g:3915:2: ( ( '|' ) )
            // InternalZeroKnowledge.g:3916:3: ( '|' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getOperationVerticalLineKeyword_1_1_0()); 
            }
            // InternalZeroKnowledge.g:3917:3: ( '|' )
            // InternalZeroKnowledge.g:3918:4: '|'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getOperationVerticalLineKeyword_1_1_0()); 
            }
            match(input,17,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getOperationVerticalLineKeyword_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getOperationVerticalLineKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__OperationAssignment_1_1"


    // $ANTLR start "rule__Disjunction__RightAssignment_1_2"
    // InternalZeroKnowledge.g:3929:1: rule__Disjunction__RightAssignment_1_2 : ( ruleComparison ) ;
    public final void rule__Disjunction__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3933:1: ( ( ruleComparison ) )
            // InternalZeroKnowledge.g:3934:2: ( ruleComparison )
            {
            // InternalZeroKnowledge.g:3934:2: ( ruleComparison )
            // InternalZeroKnowledge.g:3935:3: ruleComparison
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getRightComparisonParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleComparison();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDisjunctionAccess().getRightComparisonParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Disjunction__RightAssignment_1_2"


    // $ANTLR start "rule__Comparison__OperationAssignment_1_0_0_1"
    // InternalZeroKnowledge.g:3944:1: rule__Comparison__OperationAssignment_1_0_0_1 : ( ( '!=' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3948:1: ( ( ( '!=' ) ) )
            // InternalZeroKnowledge.g:3949:2: ( ( '!=' ) )
            {
            // InternalZeroKnowledge.g:3949:2: ( ( '!=' ) )
            // InternalZeroKnowledge.g:3950:3: ( '!=' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationExclamationMarkEqualsSignKeyword_1_0_0_1_0()); 
            }
            // InternalZeroKnowledge.g:3951:3: ( '!=' )
            // InternalZeroKnowledge.g:3952:4: '!='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationExclamationMarkEqualsSignKeyword_1_0_0_1_0()); 
            }
            match(input,18,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationExclamationMarkEqualsSignKeyword_1_0_0_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationExclamationMarkEqualsSignKeyword_1_0_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__OperationAssignment_1_0_0_1"


    // $ANTLR start "rule__Comparison__OperationAssignment_1_0_1_1"
    // InternalZeroKnowledge.g:3963:1: rule__Comparison__OperationAssignment_1_0_1_1 : ( ( '=' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3967:1: ( ( ( '=' ) ) )
            // InternalZeroKnowledge.g:3968:2: ( ( '=' ) )
            {
            // InternalZeroKnowledge.g:3968:2: ( ( '=' ) )
            // InternalZeroKnowledge.g:3969:3: ( '=' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationEqualsSignKeyword_1_0_1_1_0()); 
            }
            // InternalZeroKnowledge.g:3970:3: ( '=' )
            // InternalZeroKnowledge.g:3971:4: '='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationEqualsSignKeyword_1_0_1_1_0()); 
            }
            match(input,19,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationEqualsSignKeyword_1_0_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationEqualsSignKeyword_1_0_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__OperationAssignment_1_0_1_1"


    // $ANTLR start "rule__Comparison__OperationAssignment_1_0_2_1"
    // InternalZeroKnowledge.g:3982:1: rule__Comparison__OperationAssignment_1_0_2_1 : ( ( '>=' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3986:1: ( ( ( '>=' ) ) )
            // InternalZeroKnowledge.g:3987:2: ( ( '>=' ) )
            {
            // InternalZeroKnowledge.g:3987:2: ( ( '>=' ) )
            // InternalZeroKnowledge.g:3988:3: ( '>=' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationGreaterThanSignEqualsSignKeyword_1_0_2_1_0()); 
            }
            // InternalZeroKnowledge.g:3989:3: ( '>=' )
            // InternalZeroKnowledge.g:3990:4: '>='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationGreaterThanSignEqualsSignKeyword_1_0_2_1_0()); 
            }
            match(input,20,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationGreaterThanSignEqualsSignKeyword_1_0_2_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationGreaterThanSignEqualsSignKeyword_1_0_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__OperationAssignment_1_0_2_1"


    // $ANTLR start "rule__Comparison__OperationAssignment_1_0_3_1"
    // InternalZeroKnowledge.g:4001:1: rule__Comparison__OperationAssignment_1_0_3_1 : ( ( '<=' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4005:1: ( ( ( '<=' ) ) )
            // InternalZeroKnowledge.g:4006:2: ( ( '<=' ) )
            {
            // InternalZeroKnowledge.g:4006:2: ( ( '<=' ) )
            // InternalZeroKnowledge.g:4007:3: ( '<=' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationLessThanSignEqualsSignKeyword_1_0_3_1_0()); 
            }
            // InternalZeroKnowledge.g:4008:3: ( '<=' )
            // InternalZeroKnowledge.g:4009:4: '<='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationLessThanSignEqualsSignKeyword_1_0_3_1_0()); 
            }
            match(input,21,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationLessThanSignEqualsSignKeyword_1_0_3_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationLessThanSignEqualsSignKeyword_1_0_3_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__OperationAssignment_1_0_3_1"


    // $ANTLR start "rule__Comparison__OperationAssignment_1_0_4_1"
    // InternalZeroKnowledge.g:4020:1: rule__Comparison__OperationAssignment_1_0_4_1 : ( ( '>' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4024:1: ( ( ( '>' ) ) )
            // InternalZeroKnowledge.g:4025:2: ( ( '>' ) )
            {
            // InternalZeroKnowledge.g:4025:2: ( ( '>' ) )
            // InternalZeroKnowledge.g:4026:3: ( '>' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationGreaterThanSignKeyword_1_0_4_1_0()); 
            }
            // InternalZeroKnowledge.g:4027:3: ( '>' )
            // InternalZeroKnowledge.g:4028:4: '>'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationGreaterThanSignKeyword_1_0_4_1_0()); 
            }
            match(input,22,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationGreaterThanSignKeyword_1_0_4_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationGreaterThanSignKeyword_1_0_4_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__OperationAssignment_1_0_4_1"


    // $ANTLR start "rule__Comparison__OperationAssignment_1_0_5_1"
    // InternalZeroKnowledge.g:4039:1: rule__Comparison__OperationAssignment_1_0_5_1 : ( ( '<' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4043:1: ( ( ( '<' ) ) )
            // InternalZeroKnowledge.g:4044:2: ( ( '<' ) )
            {
            // InternalZeroKnowledge.g:4044:2: ( ( '<' ) )
            // InternalZeroKnowledge.g:4045:3: ( '<' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationLessThanSignKeyword_1_0_5_1_0()); 
            }
            // InternalZeroKnowledge.g:4046:3: ( '<' )
            // InternalZeroKnowledge.g:4047:4: '<'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationLessThanSignKeyword_1_0_5_1_0()); 
            }
            match(input,23,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationLessThanSignKeyword_1_0_5_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getOperationLessThanSignKeyword_1_0_5_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__OperationAssignment_1_0_5_1"


    // $ANTLR start "rule__Comparison__RightAssignment_1_1"
    // InternalZeroKnowledge.g:4058:1: rule__Comparison__RightAssignment_1_1 : ( ruleSum ) ;
    public final void rule__Comparison__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4062:1: ( ( ruleSum ) )
            // InternalZeroKnowledge.g:4063:2: ( ruleSum )
            {
            // InternalZeroKnowledge.g:4063:2: ( ruleSum )
            // InternalZeroKnowledge.g:4064:3: ruleSum
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getRightSumParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleSum();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getComparisonAccess().getRightSumParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Comparison__RightAssignment_1_1"


    // $ANTLR start "rule__Sum__OperationAssignment_1_0_0_1"
    // InternalZeroKnowledge.g:4073:1: rule__Sum__OperationAssignment_1_0_0_1 : ( ( '+' ) ) ;
    public final void rule__Sum__OperationAssignment_1_0_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4077:1: ( ( ( '+' ) ) )
            // InternalZeroKnowledge.g:4078:2: ( ( '+' ) )
            {
            // InternalZeroKnowledge.g:4078:2: ( ( '+' ) )
            // InternalZeroKnowledge.g:4079:3: ( '+' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationPlusSignKeyword_1_0_0_1_0()); 
            }
            // InternalZeroKnowledge.g:4080:3: ( '+' )
            // InternalZeroKnowledge.g:4081:4: '+'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationPlusSignKeyword_1_0_0_1_0()); 
            }
            match(input,24,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getOperationPlusSignKeyword_1_0_0_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getOperationPlusSignKeyword_1_0_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__OperationAssignment_1_0_0_1"


    // $ANTLR start "rule__Sum__OperationAssignment_1_0_1_1"
    // InternalZeroKnowledge.g:4092:1: rule__Sum__OperationAssignment_1_0_1_1 : ( ( '-' ) ) ;
    public final void rule__Sum__OperationAssignment_1_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4096:1: ( ( ( '-' ) ) )
            // InternalZeroKnowledge.g:4097:2: ( ( '-' ) )
            {
            // InternalZeroKnowledge.g:4097:2: ( ( '-' ) )
            // InternalZeroKnowledge.g:4098:3: ( '-' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationHyphenMinusKeyword_1_0_1_1_0()); 
            }
            // InternalZeroKnowledge.g:4099:3: ( '-' )
            // InternalZeroKnowledge.g:4100:4: '-'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationHyphenMinusKeyword_1_0_1_1_0()); 
            }
            match(input,25,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getOperationHyphenMinusKeyword_1_0_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getOperationHyphenMinusKeyword_1_0_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__OperationAssignment_1_0_1_1"


    // $ANTLR start "rule__Sum__RightAssignment_1_1"
    // InternalZeroKnowledge.g:4111:1: rule__Sum__RightAssignment_1_1 : ( ruleProduct ) ;
    public final void rule__Sum__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4115:1: ( ( ruleProduct ) )
            // InternalZeroKnowledge.g:4116:2: ( ruleProduct )
            {
            // InternalZeroKnowledge.g:4116:2: ( ruleProduct )
            // InternalZeroKnowledge.g:4117:3: ruleProduct
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getRightProductParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleProduct();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSumAccess().getRightProductParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sum__RightAssignment_1_1"


    // $ANTLR start "rule__Product__OperationAssignment_1_0_0_1"
    // InternalZeroKnowledge.g:4126:1: rule__Product__OperationAssignment_1_0_0_1 : ( ( '*' ) ) ;
    public final void rule__Product__OperationAssignment_1_0_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4130:1: ( ( ( '*' ) ) )
            // InternalZeroKnowledge.g:4131:2: ( ( '*' ) )
            {
            // InternalZeroKnowledge.g:4131:2: ( ( '*' ) )
            // InternalZeroKnowledge.g:4132:3: ( '*' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationAsteriskKeyword_1_0_0_1_0()); 
            }
            // InternalZeroKnowledge.g:4133:3: ( '*' )
            // InternalZeroKnowledge.g:4134:4: '*'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationAsteriskKeyword_1_0_0_1_0()); 
            }
            match(input,26,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getOperationAsteriskKeyword_1_0_0_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getOperationAsteriskKeyword_1_0_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__OperationAssignment_1_0_0_1"


    // $ANTLR start "rule__Product__OperationAssignment_1_0_1_1"
    // InternalZeroKnowledge.g:4145:1: rule__Product__OperationAssignment_1_0_1_1 : ( ( '/' ) ) ;
    public final void rule__Product__OperationAssignment_1_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4149:1: ( ( ( '/' ) ) )
            // InternalZeroKnowledge.g:4150:2: ( ( '/' ) )
            {
            // InternalZeroKnowledge.g:4150:2: ( ( '/' ) )
            // InternalZeroKnowledge.g:4151:3: ( '/' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationSolidusKeyword_1_0_1_1_0()); 
            }
            // InternalZeroKnowledge.g:4152:3: ( '/' )
            // InternalZeroKnowledge.g:4153:4: '/'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationSolidusKeyword_1_0_1_1_0()); 
            }
            match(input,27,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getOperationSolidusKeyword_1_0_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getOperationSolidusKeyword_1_0_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__OperationAssignment_1_0_1_1"


    // $ANTLR start "rule__Product__RightAssignment_1_1"
    // InternalZeroKnowledge.g:4164:1: rule__Product__RightAssignment_1_1 : ( rulePower ) ;
    public final void rule__Product__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4168:1: ( ( rulePower ) )
            // InternalZeroKnowledge.g:4169:2: ( rulePower )
            {
            // InternalZeroKnowledge.g:4169:2: ( rulePower )
            // InternalZeroKnowledge.g:4170:3: rulePower
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getRightPowerParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            rulePower();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getProductAccess().getRightPowerParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Product__RightAssignment_1_1"


    // $ANTLR start "rule__Power__OperationAssignment_1_1"
    // InternalZeroKnowledge.g:4179:1: rule__Power__OperationAssignment_1_1 : ( ( '^' ) ) ;
    public final void rule__Power__OperationAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4183:1: ( ( ( '^' ) ) )
            // InternalZeroKnowledge.g:4184:2: ( ( '^' ) )
            {
            // InternalZeroKnowledge.g:4184:2: ( ( '^' ) )
            // InternalZeroKnowledge.g:4185:3: ( '^' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getOperationCircumflexAccentKeyword_1_1_0()); 
            }
            // InternalZeroKnowledge.g:4186:3: ( '^' )
            // InternalZeroKnowledge.g:4187:4: '^'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getOperationCircumflexAccentKeyword_1_1_0()); 
            }
            match(input,28,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getOperationCircumflexAccentKeyword_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getOperationCircumflexAccentKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__OperationAssignment_1_1"


    // $ANTLR start "rule__Power__RightAssignment_1_2"
    // InternalZeroKnowledge.g:4198:1: rule__Power__RightAssignment_1_2 : ( rulePower ) ;
    public final void rule__Power__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4202:1: ( ( rulePower ) )
            // InternalZeroKnowledge.g:4203:2: ( rulePower )
            {
            // InternalZeroKnowledge.g:4203:2: ( rulePower )
            // InternalZeroKnowledge.g:4204:3: rulePower
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getRightPowerParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            rulePower();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getRightPowerParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__RightAssignment_1_2"


    // $ANTLR start "rule__StringLiteral__ValueAssignment"
    // InternalZeroKnowledge.g:4213:1: rule__StringLiteral__ValueAssignment : ( RULE_STRING_LITERAL ) ;
    public final void rule__StringLiteral__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4217:1: ( ( RULE_STRING_LITERAL ) )
            // InternalZeroKnowledge.g:4218:2: ( RULE_STRING_LITERAL )
            {
            // InternalZeroKnowledge.g:4218:2: ( RULE_STRING_LITERAL )
            // InternalZeroKnowledge.g:4219:3: RULE_STRING_LITERAL
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringLiteralAccess().getValueSTRING_LITERALTerminalRuleCall_0()); 
            }
            match(input,RULE_STRING_LITERAL,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringLiteralAccess().getValueSTRING_LITERALTerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringLiteral__ValueAssignment"


    // $ANTLR start "rule__Tuple__ElementsAssignment_0_0_2"
    // InternalZeroKnowledge.g:4228:1: rule__Tuple__ElementsAssignment_0_0_2 : ( ruleConjunction ) ;
    public final void rule__Tuple__ElementsAssignment_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4232:1: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:4233:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:4233:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:4234:3: ruleConjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getElementsConjunctionParserRuleCall_0_0_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleConjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getElementsConjunctionParserRuleCall_0_0_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__ElementsAssignment_0_0_2"


    // $ANTLR start "rule__Tuple__ElementsAssignment_1"
    // InternalZeroKnowledge.g:4243:1: rule__Tuple__ElementsAssignment_1 : ( ruleConjunction ) ;
    public final void rule__Tuple__ElementsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4247:1: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:4248:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:4248:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:4249:3: ruleConjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getElementsConjunctionParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleConjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getElementsConjunctionParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__ElementsAssignment_1"


    // $ANTLR start "rule__Tuple__ElementsAssignment_2_1"
    // InternalZeroKnowledge.g:4258:1: rule__Tuple__ElementsAssignment_2_1 : ( ruleConjunction ) ;
    public final void rule__Tuple__ElementsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4262:1: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:4263:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:4263:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:4264:3: ruleConjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getElementsConjunctionParserRuleCall_2_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleConjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTupleAccess().getElementsConjunctionParserRuleCall_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Tuple__ElementsAssignment_2_1"


    // $ANTLR start "rule__Negative__OperationAssignment_0_1"
    // InternalZeroKnowledge.g:4273:1: rule__Negative__OperationAssignment_0_1 : ( ( '-' ) ) ;
    public final void rule__Negative__OperationAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4277:1: ( ( ( '-' ) ) )
            // InternalZeroKnowledge.g:4278:2: ( ( '-' ) )
            {
            // InternalZeroKnowledge.g:4278:2: ( ( '-' ) )
            // InternalZeroKnowledge.g:4279:3: ( '-' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getOperationHyphenMinusKeyword_0_1_0()); 
            }
            // InternalZeroKnowledge.g:4280:3: ( '-' )
            // InternalZeroKnowledge.g:4281:4: '-'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getOperationHyphenMinusKeyword_0_1_0()); 
            }
            match(input,25,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNegativeAccess().getOperationHyphenMinusKeyword_0_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNegativeAccess().getOperationHyphenMinusKeyword_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Negative__OperationAssignment_0_1"


    // $ANTLR start "rule__Negative__TermAssignment_0_2"
    // InternalZeroKnowledge.g:4292:1: rule__Negative__TermAssignment_0_2 : ( ruleValue ) ;
    public final void rule__Negative__TermAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4296:1: ( ( ruleValue ) )
            // InternalZeroKnowledge.g:4297:2: ( ruleValue )
            {
            // InternalZeroKnowledge.g:4297:2: ( ruleValue )
            // InternalZeroKnowledge.g:4298:3: ruleValue
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getTermValueParserRuleCall_0_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleValue();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNegativeAccess().getTermValueParserRuleCall_0_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Negative__TermAssignment_0_2"


    // $ANTLR start "rule__FunctionCall__NameAssignment_1_0_0_0"
    // InternalZeroKnowledge.g:4307:1: rule__FunctionCall__NameAssignment_1_0_0_0 : ( RULE_IDENTIFIER ) ;
    public final void rule__FunctionCall__NameAssignment_1_0_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4311:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:4312:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:4312:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:4313:3: RULE_IDENTIFIER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getNameIDENTIFIERTerminalRuleCall_1_0_0_0_0()); 
            }
            match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getNameIDENTIFIERTerminalRuleCall_1_0_0_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__NameAssignment_1_0_0_0"


    // $ANTLR start "rule__FunctionCall__ArgumentsAssignment_1_1_0"
    // InternalZeroKnowledge.g:4322:1: rule__FunctionCall__ArgumentsAssignment_1_1_0 : ( ruleArgument ) ;
    public final void rule__FunctionCall__ArgumentsAssignment_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4326:1: ( ( ruleArgument ) )
            // InternalZeroKnowledge.g:4327:2: ( ruleArgument )
            {
            // InternalZeroKnowledge.g:4327:2: ( ruleArgument )
            // InternalZeroKnowledge.g:4328:3: ruleArgument
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getArgumentsArgumentParserRuleCall_1_1_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleArgument();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getArgumentsArgumentParserRuleCall_1_1_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__ArgumentsAssignment_1_1_0"


    // $ANTLR start "rule__FunctionCall__ArgumentsAssignment_1_1_1_1"
    // InternalZeroKnowledge.g:4337:1: rule__FunctionCall__ArgumentsAssignment_1_1_1_1 : ( ruleArgument ) ;
    public final void rule__FunctionCall__ArgumentsAssignment_1_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4341:1: ( ( ruleArgument ) )
            // InternalZeroKnowledge.g:4342:2: ( ruleArgument )
            {
            // InternalZeroKnowledge.g:4342:2: ( ruleArgument )
            // InternalZeroKnowledge.g:4343:3: ruleArgument
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getArgumentsArgumentParserRuleCall_1_1_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleArgument();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getArgumentsArgumentParserRuleCall_1_1_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FunctionCall__ArgumentsAssignment_1_1_1_1"


    // $ANTLR start "rule__Argument__ExpressionAssignment_1"
    // InternalZeroKnowledge.g:4352:1: rule__Argument__ExpressionAssignment_1 : ( ruleConjunction ) ;
    public final void rule__Argument__ExpressionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4356:1: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:4357:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:4357:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:4358:3: ruleConjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getArgumentAccess().getExpressionConjunctionParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleConjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getArgumentAccess().getExpressionConjunctionParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Argument__ExpressionAssignment_1"


    // $ANTLR start "rule__Variable__NameAssignment"
    // InternalZeroKnowledge.g:4367:1: rule__Variable__NameAssignment : ( RULE_IDENTIFIER ) ;
    public final void rule__Variable__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4371:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:4372:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:4372:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:4373:3: RULE_IDENTIFIER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getVariableAccess().getNameIDENTIFIERTerminalRuleCall_0()); 
            }
            match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getVariableAccess().getNameIDENTIFIERTerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variable__NameAssignment"


    // $ANTLR start "rule__NumberLiteral__ValueAssignment"
    // InternalZeroKnowledge.g:4382:1: rule__NumberLiteral__ValueAssignment : ( RULE_INT ) ;
    public final void rule__NumberLiteral__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4386:1: ( ( RULE_INT ) )
            // InternalZeroKnowledge.g:4387:2: ( RULE_INT )
            {
            // InternalZeroKnowledge.g:4387:2: ( RULE_INT )
            // InternalZeroKnowledge.g:4388:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNumberLiteralAccess().getValueINTTerminalRuleCall_0()); 
            }
            match(input,RULE_INT,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNumberLiteralAccess().getValueINTTerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteral__ValueAssignment"


    // $ANTLR start "rule__Brackets__ContentAssignment_1"
    // InternalZeroKnowledge.g:4397:1: rule__Brackets__ContentAssignment_1 : ( ruleExpression ) ;
    public final void rule__Brackets__ContentAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4401:1: ( ( ruleExpression ) )
            // InternalZeroKnowledge.g:4402:2: ( ruleExpression )
            {
            // InternalZeroKnowledge.g:4402:2: ( ruleExpression )
            // InternalZeroKnowledge.g:4403:3: ruleExpression
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBracketsAccess().getContentExpressionParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBracketsAccess().getContentExpressionParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Brackets__ContentAssignment_1"

    // $ANTLR start synpred9_InternalZeroKnowledge
    public final void synpred9_InternalZeroKnowledge_fragment() throws RecognitionException {   
        // InternalZeroKnowledge.g:726:2: ( ( ( ruleTuple ) ) )
        // InternalZeroKnowledge.g:726:2: ( ( ruleTuple ) )
        {
        // InternalZeroKnowledge.g:726:2: ( ( ruleTuple ) )
        // InternalZeroKnowledge.g:727:3: ( ruleTuple )
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getConstructAccess().getTupleParserRuleCall_1()); 
        }
        // InternalZeroKnowledge.g:728:3: ( ruleTuple )
        // InternalZeroKnowledge.g:728:4: ruleTuple
        {
        pushFollow(FOLLOW_2);
        ruleTuple();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred9_InternalZeroKnowledge

    // Delegated rules

    public final boolean synpred9_InternalZeroKnowledge() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_InternalZeroKnowledge_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000002002470L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000001400L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000FC0000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000003000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x000000000C000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x000000000200A470L});

}