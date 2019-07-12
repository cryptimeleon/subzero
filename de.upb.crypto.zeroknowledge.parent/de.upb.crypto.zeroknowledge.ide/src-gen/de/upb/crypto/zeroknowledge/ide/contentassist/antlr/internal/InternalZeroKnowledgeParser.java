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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_IDENTIFIER", "RULE_STRING_LITERAL", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WHITESPACE", "';'", "'{'", "'}'", "'('", "')'", "','", "'^'", "'-'", "'?'", "'&'", "'|'", "'!='", "'='", "'>='", "'<='", "'>'", "'<'", "'+'", "'*'", "'/'"
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
    public static final int T__29=29;
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
    // InternalZeroKnowledge.g:188:1: ruleWitness : ( ( rule__Witness__Group__0 ) ) ;
    public final void ruleWitness() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:192:2: ( ( ( rule__Witness__Group__0 ) ) )
            // InternalZeroKnowledge.g:193:2: ( ( rule__Witness__Group__0 ) )
            {
            // InternalZeroKnowledge.g:193:2: ( ( rule__Witness__Group__0 ) )
            // InternalZeroKnowledge.g:194:3: ( rule__Witness__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:195:3: ( rule__Witness__Group__0 )
            // InternalZeroKnowledge.g:195:4: rule__Witness__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Witness__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessAccess().getGroup()); 
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


    // $ANTLR start "entryRuleVariable"
    // InternalZeroKnowledge.g:529:1: entryRuleVariable : ruleVariable EOF ;
    public final void entryRuleVariable() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:530:1: ( ruleVariable EOF )
            // InternalZeroKnowledge.g:531:1: ruleVariable EOF
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
    // InternalZeroKnowledge.g:538:1: ruleVariable : ( ( rule__Variable__NameAssignment ) ) ;
    public final void ruleVariable() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:542:2: ( ( ( rule__Variable__NameAssignment ) ) )
            // InternalZeroKnowledge.g:543:2: ( ( rule__Variable__NameAssignment ) )
            {
            // InternalZeroKnowledge.g:543:2: ( ( rule__Variable__NameAssignment ) )
            // InternalZeroKnowledge.g:544:3: ( rule__Variable__NameAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getVariableAccess().getNameAssignment()); 
            }
            // InternalZeroKnowledge.g:545:3: ( rule__Variable__NameAssignment )
            // InternalZeroKnowledge.g:545:4: rule__Variable__NameAssignment
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
    // InternalZeroKnowledge.g:554:1: entryRuleNumberLiteral : ruleNumberLiteral EOF ;
    public final void entryRuleNumberLiteral() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:555:1: ( ruleNumberLiteral EOF )
            // InternalZeroKnowledge.g:556:1: ruleNumberLiteral EOF
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
    // InternalZeroKnowledge.g:563:1: ruleNumberLiteral : ( ( rule__NumberLiteral__ValueAssignment ) ) ;
    public final void ruleNumberLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:567:2: ( ( ( rule__NumberLiteral__ValueAssignment ) ) )
            // InternalZeroKnowledge.g:568:2: ( ( rule__NumberLiteral__ValueAssignment ) )
            {
            // InternalZeroKnowledge.g:568:2: ( ( rule__NumberLiteral__ValueAssignment ) )
            // InternalZeroKnowledge.g:569:3: ( rule__NumberLiteral__ValueAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNumberLiteralAccess().getValueAssignment()); 
            }
            // InternalZeroKnowledge.g:570:3: ( rule__NumberLiteral__ValueAssignment )
            // InternalZeroKnowledge.g:570:4: rule__NumberLiteral__ValueAssignment
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
    // InternalZeroKnowledge.g:579:1: entryRuleBrackets : ruleBrackets EOF ;
    public final void entryRuleBrackets() throws RecognitionException {
        try {
            // InternalZeroKnowledge.g:580:1: ( ruleBrackets EOF )
            // InternalZeroKnowledge.g:581:1: ruleBrackets EOF
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
    // InternalZeroKnowledge.g:588:1: ruleBrackets : ( ( rule__Brackets__Group__0 ) ) ;
    public final void ruleBrackets() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:592:2: ( ( ( rule__Brackets__Group__0 ) ) )
            // InternalZeroKnowledge.g:593:2: ( ( rule__Brackets__Group__0 ) )
            {
            // InternalZeroKnowledge.g:593:2: ( ( rule__Brackets__Group__0 ) )
            // InternalZeroKnowledge.g:594:3: ( rule__Brackets__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBracketsAccess().getGroup()); 
            }
            // InternalZeroKnowledge.g:595:3: ( rule__Brackets__Group__0 )
            // InternalZeroKnowledge.g:595:4: rule__Brackets__Group__0
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
    // InternalZeroKnowledge.g:603:1: rule__Comparison__Alternatives_1_0 : ( ( ( rule__Comparison__Group_1_0_0__0 ) ) | ( ( rule__Comparison__Group_1_0_1__0 ) ) | ( ( rule__Comparison__Group_1_0_2__0 ) ) | ( ( rule__Comparison__Group_1_0_3__0 ) ) | ( ( rule__Comparison__Group_1_0_4__0 ) ) | ( ( rule__Comparison__Group_1_0_5__0 ) ) );
    public final void rule__Comparison__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:607:1: ( ( ( rule__Comparison__Group_1_0_0__0 ) ) | ( ( rule__Comparison__Group_1_0_1__0 ) ) | ( ( rule__Comparison__Group_1_0_2__0 ) ) | ( ( rule__Comparison__Group_1_0_3__0 ) ) | ( ( rule__Comparison__Group_1_0_4__0 ) ) | ( ( rule__Comparison__Group_1_0_5__0 ) ) )
            int alt1=6;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt1=1;
                }
                break;
            case 22:
                {
                alt1=2;
                }
                break;
            case 23:
                {
                alt1=3;
                }
                break;
            case 24:
                {
                alt1=4;
                }
                break;
            case 25:
                {
                alt1=5;
                }
                break;
            case 26:
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
                    // InternalZeroKnowledge.g:608:2: ( ( rule__Comparison__Group_1_0_0__0 ) )
                    {
                    // InternalZeroKnowledge.g:608:2: ( ( rule__Comparison__Group_1_0_0__0 ) )
                    // InternalZeroKnowledge.g:609:3: ( rule__Comparison__Group_1_0_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_0()); 
                    }
                    // InternalZeroKnowledge.g:610:3: ( rule__Comparison__Group_1_0_0__0 )
                    // InternalZeroKnowledge.g:610:4: rule__Comparison__Group_1_0_0__0
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
                    // InternalZeroKnowledge.g:614:2: ( ( rule__Comparison__Group_1_0_1__0 ) )
                    {
                    // InternalZeroKnowledge.g:614:2: ( ( rule__Comparison__Group_1_0_1__0 ) )
                    // InternalZeroKnowledge.g:615:3: ( rule__Comparison__Group_1_0_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_1()); 
                    }
                    // InternalZeroKnowledge.g:616:3: ( rule__Comparison__Group_1_0_1__0 )
                    // InternalZeroKnowledge.g:616:4: rule__Comparison__Group_1_0_1__0
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
                    // InternalZeroKnowledge.g:620:2: ( ( rule__Comparison__Group_1_0_2__0 ) )
                    {
                    // InternalZeroKnowledge.g:620:2: ( ( rule__Comparison__Group_1_0_2__0 ) )
                    // InternalZeroKnowledge.g:621:3: ( rule__Comparison__Group_1_0_2__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_2()); 
                    }
                    // InternalZeroKnowledge.g:622:3: ( rule__Comparison__Group_1_0_2__0 )
                    // InternalZeroKnowledge.g:622:4: rule__Comparison__Group_1_0_2__0
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
                    // InternalZeroKnowledge.g:626:2: ( ( rule__Comparison__Group_1_0_3__0 ) )
                    {
                    // InternalZeroKnowledge.g:626:2: ( ( rule__Comparison__Group_1_0_3__0 ) )
                    // InternalZeroKnowledge.g:627:3: ( rule__Comparison__Group_1_0_3__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_3()); 
                    }
                    // InternalZeroKnowledge.g:628:3: ( rule__Comparison__Group_1_0_3__0 )
                    // InternalZeroKnowledge.g:628:4: rule__Comparison__Group_1_0_3__0
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
                    // InternalZeroKnowledge.g:632:2: ( ( rule__Comparison__Group_1_0_4__0 ) )
                    {
                    // InternalZeroKnowledge.g:632:2: ( ( rule__Comparison__Group_1_0_4__0 ) )
                    // InternalZeroKnowledge.g:633:3: ( rule__Comparison__Group_1_0_4__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_4()); 
                    }
                    // InternalZeroKnowledge.g:634:3: ( rule__Comparison__Group_1_0_4__0 )
                    // InternalZeroKnowledge.g:634:4: rule__Comparison__Group_1_0_4__0
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
                    // InternalZeroKnowledge.g:638:2: ( ( rule__Comparison__Group_1_0_5__0 ) )
                    {
                    // InternalZeroKnowledge.g:638:2: ( ( rule__Comparison__Group_1_0_5__0 ) )
                    // InternalZeroKnowledge.g:639:3: ( rule__Comparison__Group_1_0_5__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getComparisonAccess().getGroup_1_0_5()); 
                    }
                    // InternalZeroKnowledge.g:640:3: ( rule__Comparison__Group_1_0_5__0 )
                    // InternalZeroKnowledge.g:640:4: rule__Comparison__Group_1_0_5__0
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
    // InternalZeroKnowledge.g:648:1: rule__Sum__Alternatives_1_0 : ( ( ( rule__Sum__Group_1_0_0__0 ) ) | ( ( rule__Sum__Group_1_0_1__0 ) ) );
    public final void rule__Sum__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:652:1: ( ( ( rule__Sum__Group_1_0_0__0 ) ) | ( ( rule__Sum__Group_1_0_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==27) ) {
                alt2=1;
            }
            else if ( (LA2_0==17) ) {
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
                    // InternalZeroKnowledge.g:653:2: ( ( rule__Sum__Group_1_0_0__0 ) )
                    {
                    // InternalZeroKnowledge.g:653:2: ( ( rule__Sum__Group_1_0_0__0 ) )
                    // InternalZeroKnowledge.g:654:3: ( rule__Sum__Group_1_0_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSumAccess().getGroup_1_0_0()); 
                    }
                    // InternalZeroKnowledge.g:655:3: ( rule__Sum__Group_1_0_0__0 )
                    // InternalZeroKnowledge.g:655:4: rule__Sum__Group_1_0_0__0
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
                    // InternalZeroKnowledge.g:659:2: ( ( rule__Sum__Group_1_0_1__0 ) )
                    {
                    // InternalZeroKnowledge.g:659:2: ( ( rule__Sum__Group_1_0_1__0 ) )
                    // InternalZeroKnowledge.g:660:3: ( rule__Sum__Group_1_0_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSumAccess().getGroup_1_0_1()); 
                    }
                    // InternalZeroKnowledge.g:661:3: ( rule__Sum__Group_1_0_1__0 )
                    // InternalZeroKnowledge.g:661:4: rule__Sum__Group_1_0_1__0
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
    // InternalZeroKnowledge.g:669:1: rule__Product__Alternatives_1_0 : ( ( ( rule__Product__Group_1_0_0__0 ) ) | ( ( rule__Product__Group_1_0_1__0 ) ) );
    public final void rule__Product__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:673:1: ( ( ( rule__Product__Group_1_0_0__0 ) ) | ( ( rule__Product__Group_1_0_1__0 ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==28) ) {
                alt3=1;
            }
            else if ( (LA3_0==29) ) {
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
                    // InternalZeroKnowledge.g:674:2: ( ( rule__Product__Group_1_0_0__0 ) )
                    {
                    // InternalZeroKnowledge.g:674:2: ( ( rule__Product__Group_1_0_0__0 ) )
                    // InternalZeroKnowledge.g:675:3: ( rule__Product__Group_1_0_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getProductAccess().getGroup_1_0_0()); 
                    }
                    // InternalZeroKnowledge.g:676:3: ( rule__Product__Group_1_0_0__0 )
                    // InternalZeroKnowledge.g:676:4: rule__Product__Group_1_0_0__0
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
                    // InternalZeroKnowledge.g:680:2: ( ( rule__Product__Group_1_0_1__0 ) )
                    {
                    // InternalZeroKnowledge.g:680:2: ( ( rule__Product__Group_1_0_1__0 ) )
                    // InternalZeroKnowledge.g:681:3: ( rule__Product__Group_1_0_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getProductAccess().getGroup_1_0_1()); 
                    }
                    // InternalZeroKnowledge.g:682:3: ( rule__Product__Group_1_0_1__0 )
                    // InternalZeroKnowledge.g:682:4: rule__Product__Group_1_0_1__0
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
    // InternalZeroKnowledge.g:690:1: rule__Construct__Alternatives : ( ( ruleStringLiteral ) | ( ( ruleTuple ) ) | ( ruleNegative ) );
    public final void rule__Construct__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:694:1: ( ( ruleStringLiteral ) | ( ( ruleTuple ) ) | ( ruleNegative ) )
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
            case 17:
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
                    // InternalZeroKnowledge.g:695:2: ( ruleStringLiteral )
                    {
                    // InternalZeroKnowledge.g:695:2: ( ruleStringLiteral )
                    // InternalZeroKnowledge.g:696:3: ruleStringLiteral
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
                    // InternalZeroKnowledge.g:701:2: ( ( ruleTuple ) )
                    {
                    // InternalZeroKnowledge.g:701:2: ( ( ruleTuple ) )
                    // InternalZeroKnowledge.g:702:3: ( ruleTuple )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getConstructAccess().getTupleParserRuleCall_1()); 
                    }
                    // InternalZeroKnowledge.g:703:3: ( ruleTuple )
                    // InternalZeroKnowledge.g:703:4: ruleTuple
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
                    // InternalZeroKnowledge.g:707:2: ( ruleNegative )
                    {
                    // InternalZeroKnowledge.g:707:2: ( ruleNegative )
                    // InternalZeroKnowledge.g:708:3: ruleNegative
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
    // InternalZeroKnowledge.g:717:1: rule__Negative__Alternatives : ( ( ( rule__Negative__Group_0__0 ) ) | ( ruleValue ) );
    public final void rule__Negative__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:721:1: ( ( ( rule__Negative__Group_0__0 ) ) | ( ruleValue ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==17) ) {
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
                    // InternalZeroKnowledge.g:722:2: ( ( rule__Negative__Group_0__0 ) )
                    {
                    // InternalZeroKnowledge.g:722:2: ( ( rule__Negative__Group_0__0 ) )
                    // InternalZeroKnowledge.g:723:3: ( rule__Negative__Group_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getNegativeAccess().getGroup_0()); 
                    }
                    // InternalZeroKnowledge.g:724:3: ( rule__Negative__Group_0__0 )
                    // InternalZeroKnowledge.g:724:4: rule__Negative__Group_0__0
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
                    // InternalZeroKnowledge.g:728:2: ( ruleValue )
                    {
                    // InternalZeroKnowledge.g:728:2: ( ruleValue )
                    // InternalZeroKnowledge.g:729:3: ruleValue
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
    // InternalZeroKnowledge.g:738:1: rule__Value__Alternatives : ( ( ruleFunctionCall ) | ( ruleVariable ) | ( ruleNumberLiteral ) | ( ( rule__Value__Group_3__0 ) ) );
    public final void rule__Value__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:742:1: ( ( ruleFunctionCall ) | ( ruleVariable ) | ( ruleNumberLiteral ) | ( ( rule__Value__Group_3__0 ) ) )
            int alt6=4;
            switch ( input.LA(1) ) {
            case RULE_IDENTIFIER:
                {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==13) ) {
                    alt6=1;
                }
                else if ( (LA6_1==EOF||LA6_1==10||LA6_1==12||(LA6_1>=14 && LA6_1<=17)||(LA6_1>=19 && LA6_1<=29)) ) {
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
                    // InternalZeroKnowledge.g:743:2: ( ruleFunctionCall )
                    {
                    // InternalZeroKnowledge.g:743:2: ( ruleFunctionCall )
                    // InternalZeroKnowledge.g:744:3: ruleFunctionCall
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
                    // InternalZeroKnowledge.g:749:2: ( ruleVariable )
                    {
                    // InternalZeroKnowledge.g:749:2: ( ruleVariable )
                    // InternalZeroKnowledge.g:750:3: ruleVariable
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
                    // InternalZeroKnowledge.g:755:2: ( ruleNumberLiteral )
                    {
                    // InternalZeroKnowledge.g:755:2: ( ruleNumberLiteral )
                    // InternalZeroKnowledge.g:756:3: ruleNumberLiteral
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
                    // InternalZeroKnowledge.g:761:2: ( ( rule__Value__Group_3__0 ) )
                    {
                    // InternalZeroKnowledge.g:761:2: ( ( rule__Value__Group_3__0 ) )
                    // InternalZeroKnowledge.g:762:3: ( rule__Value__Group_3__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getValueAccess().getGroup_3()); 
                    }
                    // InternalZeroKnowledge.g:763:3: ( rule__Value__Group_3__0 )
                    // InternalZeroKnowledge.g:763:4: rule__Value__Group_3__0
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
    // InternalZeroKnowledge.g:771:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:775:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // InternalZeroKnowledge.g:776:2: rule__Model__Group__0__Impl rule__Model__Group__1
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
    // InternalZeroKnowledge.g:783:1: rule__Model__Group__0__Impl : ( ( rule__Model__FunctionsAssignment_0 )* ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:787:1: ( ( ( rule__Model__FunctionsAssignment_0 )* ) )
            // InternalZeroKnowledge.g:788:1: ( ( rule__Model__FunctionsAssignment_0 )* )
            {
            // InternalZeroKnowledge.g:788:1: ( ( rule__Model__FunctionsAssignment_0 )* )
            // InternalZeroKnowledge.g:789:2: ( rule__Model__FunctionsAssignment_0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getFunctionsAssignment_0()); 
            }
            // InternalZeroKnowledge.g:790:2: ( rule__Model__FunctionsAssignment_0 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_IDENTIFIER) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalZeroKnowledge.g:790:3: rule__Model__FunctionsAssignment_0
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
    // InternalZeroKnowledge.g:798:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:802:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // InternalZeroKnowledge.g:803:2: rule__Model__Group__1__Impl rule__Model__Group__2
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
    // InternalZeroKnowledge.g:810:1: rule__Model__Group__1__Impl : ( ( rule__Model__WitnessListAssignment_1 ) ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:814:1: ( ( ( rule__Model__WitnessListAssignment_1 ) ) )
            // InternalZeroKnowledge.g:815:1: ( ( rule__Model__WitnessListAssignment_1 ) )
            {
            // InternalZeroKnowledge.g:815:1: ( ( rule__Model__WitnessListAssignment_1 ) )
            // InternalZeroKnowledge.g:816:2: ( rule__Model__WitnessListAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getWitnessListAssignment_1()); 
            }
            // InternalZeroKnowledge.g:817:2: ( rule__Model__WitnessListAssignment_1 )
            // InternalZeroKnowledge.g:817:3: rule__Model__WitnessListAssignment_1
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
    // InternalZeroKnowledge.g:825:1: rule__Model__Group__2 : rule__Model__Group__2__Impl rule__Model__Group__3 ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:829:1: ( rule__Model__Group__2__Impl rule__Model__Group__3 )
            // InternalZeroKnowledge.g:830:2: rule__Model__Group__2__Impl rule__Model__Group__3
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
    // InternalZeroKnowledge.g:837:1: rule__Model__Group__2__Impl : ( ( ';' )? ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:841:1: ( ( ( ';' )? ) )
            // InternalZeroKnowledge.g:842:1: ( ( ';' )? )
            {
            // InternalZeroKnowledge.g:842:1: ( ( ';' )? )
            // InternalZeroKnowledge.g:843:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getSemicolonKeyword_2()); 
            }
            // InternalZeroKnowledge.g:844:2: ( ';' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==10) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalZeroKnowledge.g:844:3: ';'
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
    // InternalZeroKnowledge.g:852:1: rule__Model__Group__3 : rule__Model__Group__3__Impl rule__Model__Group__4 ;
    public final void rule__Model__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:856:1: ( rule__Model__Group__3__Impl rule__Model__Group__4 )
            // InternalZeroKnowledge.g:857:2: rule__Model__Group__3__Impl rule__Model__Group__4
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
    // InternalZeroKnowledge.g:864:1: rule__Model__Group__3__Impl : ( ( rule__Model__ProofAssignment_3 ) ) ;
    public final void rule__Model__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:868:1: ( ( ( rule__Model__ProofAssignment_3 ) ) )
            // InternalZeroKnowledge.g:869:1: ( ( rule__Model__ProofAssignment_3 ) )
            {
            // InternalZeroKnowledge.g:869:1: ( ( rule__Model__ProofAssignment_3 ) )
            // InternalZeroKnowledge.g:870:2: ( rule__Model__ProofAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getProofAssignment_3()); 
            }
            // InternalZeroKnowledge.g:871:2: ( rule__Model__ProofAssignment_3 )
            // InternalZeroKnowledge.g:871:3: rule__Model__ProofAssignment_3
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
    // InternalZeroKnowledge.g:879:1: rule__Model__Group__4 : rule__Model__Group__4__Impl ;
    public final void rule__Model__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:883:1: ( rule__Model__Group__4__Impl )
            // InternalZeroKnowledge.g:884:2: rule__Model__Group__4__Impl
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
    // InternalZeroKnowledge.g:890:1: rule__Model__Group__4__Impl : ( ( ';' )? ) ;
    public final void rule__Model__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:894:1: ( ( ( ';' )? ) )
            // InternalZeroKnowledge.g:895:1: ( ( ';' )? )
            {
            // InternalZeroKnowledge.g:895:1: ( ( ';' )? )
            // InternalZeroKnowledge.g:896:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getModelAccess().getSemicolonKeyword_4()); 
            }
            // InternalZeroKnowledge.g:897:2: ( ';' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==10) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalZeroKnowledge.g:897:3: ';'
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
    // InternalZeroKnowledge.g:906:1: rule__FunctionDefinition__Group__0 : rule__FunctionDefinition__Group__0__Impl rule__FunctionDefinition__Group__1 ;
    public final void rule__FunctionDefinition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:910:1: ( rule__FunctionDefinition__Group__0__Impl rule__FunctionDefinition__Group__1 )
            // InternalZeroKnowledge.g:911:2: rule__FunctionDefinition__Group__0__Impl rule__FunctionDefinition__Group__1
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
    // InternalZeroKnowledge.g:918:1: rule__FunctionDefinition__Group__0__Impl : ( ( rule__FunctionDefinition__NameAssignment_0 ) ) ;
    public final void rule__FunctionDefinition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:922:1: ( ( ( rule__FunctionDefinition__NameAssignment_0 ) ) )
            // InternalZeroKnowledge.g:923:1: ( ( rule__FunctionDefinition__NameAssignment_0 ) )
            {
            // InternalZeroKnowledge.g:923:1: ( ( rule__FunctionDefinition__NameAssignment_0 ) )
            // InternalZeroKnowledge.g:924:2: ( rule__FunctionDefinition__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getNameAssignment_0()); 
            }
            // InternalZeroKnowledge.g:925:2: ( rule__FunctionDefinition__NameAssignment_0 )
            // InternalZeroKnowledge.g:925:3: rule__FunctionDefinition__NameAssignment_0
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
    // InternalZeroKnowledge.g:933:1: rule__FunctionDefinition__Group__1 : rule__FunctionDefinition__Group__1__Impl rule__FunctionDefinition__Group__2 ;
    public final void rule__FunctionDefinition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:937:1: ( rule__FunctionDefinition__Group__1__Impl rule__FunctionDefinition__Group__2 )
            // InternalZeroKnowledge.g:938:2: rule__FunctionDefinition__Group__1__Impl rule__FunctionDefinition__Group__2
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
    // InternalZeroKnowledge.g:945:1: rule__FunctionDefinition__Group__1__Impl : ( ( rule__FunctionDefinition__ParameterListAssignment_1 ) ) ;
    public final void rule__FunctionDefinition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:949:1: ( ( ( rule__FunctionDefinition__ParameterListAssignment_1 ) ) )
            // InternalZeroKnowledge.g:950:1: ( ( rule__FunctionDefinition__ParameterListAssignment_1 ) )
            {
            // InternalZeroKnowledge.g:950:1: ( ( rule__FunctionDefinition__ParameterListAssignment_1 ) )
            // InternalZeroKnowledge.g:951:2: ( rule__FunctionDefinition__ParameterListAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getParameterListAssignment_1()); 
            }
            // InternalZeroKnowledge.g:952:2: ( rule__FunctionDefinition__ParameterListAssignment_1 )
            // InternalZeroKnowledge.g:952:3: rule__FunctionDefinition__ParameterListAssignment_1
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
    // InternalZeroKnowledge.g:960:1: rule__FunctionDefinition__Group__2 : rule__FunctionDefinition__Group__2__Impl rule__FunctionDefinition__Group__3 ;
    public final void rule__FunctionDefinition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:964:1: ( rule__FunctionDefinition__Group__2__Impl rule__FunctionDefinition__Group__3 )
            // InternalZeroKnowledge.g:965:2: rule__FunctionDefinition__Group__2__Impl rule__FunctionDefinition__Group__3
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
    // InternalZeroKnowledge.g:972:1: rule__FunctionDefinition__Group__2__Impl : ( '{' ) ;
    public final void rule__FunctionDefinition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:976:1: ( ( '{' ) )
            // InternalZeroKnowledge.g:977:1: ( '{' )
            {
            // InternalZeroKnowledge.g:977:1: ( '{' )
            // InternalZeroKnowledge.g:978:2: '{'
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
    // InternalZeroKnowledge.g:987:1: rule__FunctionDefinition__Group__3 : rule__FunctionDefinition__Group__3__Impl rule__FunctionDefinition__Group__4 ;
    public final void rule__FunctionDefinition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:991:1: ( rule__FunctionDefinition__Group__3__Impl rule__FunctionDefinition__Group__4 )
            // InternalZeroKnowledge.g:992:2: rule__FunctionDefinition__Group__3__Impl rule__FunctionDefinition__Group__4
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
    // InternalZeroKnowledge.g:999:1: rule__FunctionDefinition__Group__3__Impl : ( ( rule__FunctionDefinition__BodyAssignment_3 ) ) ;
    public final void rule__FunctionDefinition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1003:1: ( ( ( rule__FunctionDefinition__BodyAssignment_3 ) ) )
            // InternalZeroKnowledge.g:1004:1: ( ( rule__FunctionDefinition__BodyAssignment_3 ) )
            {
            // InternalZeroKnowledge.g:1004:1: ( ( rule__FunctionDefinition__BodyAssignment_3 ) )
            // InternalZeroKnowledge.g:1005:2: ( rule__FunctionDefinition__BodyAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getBodyAssignment_3()); 
            }
            // InternalZeroKnowledge.g:1006:2: ( rule__FunctionDefinition__BodyAssignment_3 )
            // InternalZeroKnowledge.g:1006:3: rule__FunctionDefinition__BodyAssignment_3
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
    // InternalZeroKnowledge.g:1014:1: rule__FunctionDefinition__Group__4 : rule__FunctionDefinition__Group__4__Impl rule__FunctionDefinition__Group__5 ;
    public final void rule__FunctionDefinition__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1018:1: ( rule__FunctionDefinition__Group__4__Impl rule__FunctionDefinition__Group__5 )
            // InternalZeroKnowledge.g:1019:2: rule__FunctionDefinition__Group__4__Impl rule__FunctionDefinition__Group__5
            {
            pushFollow(FOLLOW_6);
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
    // InternalZeroKnowledge.g:1026:1: rule__FunctionDefinition__Group__4__Impl : ( '}' ) ;
    public final void rule__FunctionDefinition__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1030:1: ( ( '}' ) )
            // InternalZeroKnowledge.g:1031:1: ( '}' )
            {
            // InternalZeroKnowledge.g:1031:1: ( '}' )
            // InternalZeroKnowledge.g:1032:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getRightCurlyBracketKeyword_4()); 
            }
            match(input,12,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getRightCurlyBracketKeyword_4()); 
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
    // InternalZeroKnowledge.g:1041:1: rule__FunctionDefinition__Group__5 : rule__FunctionDefinition__Group__5__Impl ;
    public final void rule__FunctionDefinition__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1045:1: ( rule__FunctionDefinition__Group__5__Impl )
            // InternalZeroKnowledge.g:1046:2: rule__FunctionDefinition__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FunctionDefinition__Group__5__Impl();

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
    // InternalZeroKnowledge.g:1052:1: rule__FunctionDefinition__Group__5__Impl : ( ( ';' )? ) ;
    public final void rule__FunctionDefinition__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1056:1: ( ( ( ';' )? ) )
            // InternalZeroKnowledge.g:1057:1: ( ( ';' )? )
            {
            // InternalZeroKnowledge.g:1057:1: ( ( ';' )? )
            // InternalZeroKnowledge.g:1058:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionDefinitionAccess().getSemicolonKeyword_5()); 
            }
            // InternalZeroKnowledge.g:1059:2: ( ';' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==10) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalZeroKnowledge.g:1059:3: ';'
                    {
                    match(input,10,FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionDefinitionAccess().getSemicolonKeyword_5()); 
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


    // $ANTLR start "rule__ParameterList__Group__0"
    // InternalZeroKnowledge.g:1068:1: rule__ParameterList__Group__0 : rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1 ;
    public final void rule__ParameterList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1072:1: ( rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1 )
            // InternalZeroKnowledge.g:1073:2: rule__ParameterList__Group__0__Impl rule__ParameterList__Group__1
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
    // InternalZeroKnowledge.g:1080:1: rule__ParameterList__Group__0__Impl : ( '(' ) ;
    public final void rule__ParameterList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1084:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:1085:1: ( '(' )
            {
            // InternalZeroKnowledge.g:1085:1: ( '(' )
            // InternalZeroKnowledge.g:1086:2: '('
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
    // InternalZeroKnowledge.g:1095:1: rule__ParameterList__Group__1 : rule__ParameterList__Group__1__Impl rule__ParameterList__Group__2 ;
    public final void rule__ParameterList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1099:1: ( rule__ParameterList__Group__1__Impl rule__ParameterList__Group__2 )
            // InternalZeroKnowledge.g:1100:2: rule__ParameterList__Group__1__Impl rule__ParameterList__Group__2
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
    // InternalZeroKnowledge.g:1107:1: rule__ParameterList__Group__1__Impl : ( ( rule__ParameterList__Group_1__0 )? ) ;
    public final void rule__ParameterList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1111:1: ( ( ( rule__ParameterList__Group_1__0 )? ) )
            // InternalZeroKnowledge.g:1112:1: ( ( rule__ParameterList__Group_1__0 )? )
            {
            // InternalZeroKnowledge.g:1112:1: ( ( rule__ParameterList__Group_1__0 )? )
            // InternalZeroKnowledge.g:1113:2: ( rule__ParameterList__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:1114:2: ( rule__ParameterList__Group_1__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_IDENTIFIER) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalZeroKnowledge.g:1114:3: rule__ParameterList__Group_1__0
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
    // InternalZeroKnowledge.g:1122:1: rule__ParameterList__Group__2 : rule__ParameterList__Group__2__Impl ;
    public final void rule__ParameterList__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1126:1: ( rule__ParameterList__Group__2__Impl )
            // InternalZeroKnowledge.g:1127:2: rule__ParameterList__Group__2__Impl
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
    // InternalZeroKnowledge.g:1133:1: rule__ParameterList__Group__2__Impl : ( ')' ) ;
    public final void rule__ParameterList__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1137:1: ( ( ')' ) )
            // InternalZeroKnowledge.g:1138:1: ( ')' )
            {
            // InternalZeroKnowledge.g:1138:1: ( ')' )
            // InternalZeroKnowledge.g:1139:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getRightParenthesisKeyword_2()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getParameterListAccess().getRightParenthesisKeyword_2()); 
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
    // InternalZeroKnowledge.g:1149:1: rule__ParameterList__Group_1__0 : rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1 ;
    public final void rule__ParameterList__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1153:1: ( rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1 )
            // InternalZeroKnowledge.g:1154:2: rule__ParameterList__Group_1__0__Impl rule__ParameterList__Group_1__1
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
    // InternalZeroKnowledge.g:1161:1: rule__ParameterList__Group_1__0__Impl : ( ( rule__ParameterList__ParametersAssignment_1_0 ) ) ;
    public final void rule__ParameterList__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1165:1: ( ( ( rule__ParameterList__ParametersAssignment_1_0 ) ) )
            // InternalZeroKnowledge.g:1166:1: ( ( rule__ParameterList__ParametersAssignment_1_0 ) )
            {
            // InternalZeroKnowledge.g:1166:1: ( ( rule__ParameterList__ParametersAssignment_1_0 ) )
            // InternalZeroKnowledge.g:1167:2: ( rule__ParameterList__ParametersAssignment_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getParametersAssignment_1_0()); 
            }
            // InternalZeroKnowledge.g:1168:2: ( rule__ParameterList__ParametersAssignment_1_0 )
            // InternalZeroKnowledge.g:1168:3: rule__ParameterList__ParametersAssignment_1_0
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
    // InternalZeroKnowledge.g:1176:1: rule__ParameterList__Group_1__1 : rule__ParameterList__Group_1__1__Impl ;
    public final void rule__ParameterList__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1180:1: ( rule__ParameterList__Group_1__1__Impl )
            // InternalZeroKnowledge.g:1181:2: rule__ParameterList__Group_1__1__Impl
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
    // InternalZeroKnowledge.g:1187:1: rule__ParameterList__Group_1__1__Impl : ( ( rule__ParameterList__Group_1_1__0 )* ) ;
    public final void rule__ParameterList__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1191:1: ( ( ( rule__ParameterList__Group_1_1__0 )* ) )
            // InternalZeroKnowledge.g:1192:1: ( ( rule__ParameterList__Group_1_1__0 )* )
            {
            // InternalZeroKnowledge.g:1192:1: ( ( rule__ParameterList__Group_1_1__0 )* )
            // InternalZeroKnowledge.g:1193:2: ( rule__ParameterList__Group_1_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getGroup_1_1()); 
            }
            // InternalZeroKnowledge.g:1194:2: ( rule__ParameterList__Group_1_1__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==15) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalZeroKnowledge.g:1194:3: rule__ParameterList__Group_1_1__0
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__ParameterList__Group_1_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop12;
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
    // InternalZeroKnowledge.g:1203:1: rule__ParameterList__Group_1_1__0 : rule__ParameterList__Group_1_1__0__Impl rule__ParameterList__Group_1_1__1 ;
    public final void rule__ParameterList__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1207:1: ( rule__ParameterList__Group_1_1__0__Impl rule__ParameterList__Group_1_1__1 )
            // InternalZeroKnowledge.g:1208:2: rule__ParameterList__Group_1_1__0__Impl rule__ParameterList__Group_1_1__1
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
    // InternalZeroKnowledge.g:1215:1: rule__ParameterList__Group_1_1__0__Impl : ( ',' ) ;
    public final void rule__ParameterList__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1219:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:1220:1: ( ',' )
            {
            // InternalZeroKnowledge.g:1220:1: ( ',' )
            // InternalZeroKnowledge.g:1221:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getCommaKeyword_1_1_0()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:1230:1: rule__ParameterList__Group_1_1__1 : rule__ParameterList__Group_1_1__1__Impl ;
    public final void rule__ParameterList__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1234:1: ( rule__ParameterList__Group_1_1__1__Impl )
            // InternalZeroKnowledge.g:1235:2: rule__ParameterList__Group_1_1__1__Impl
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
    // InternalZeroKnowledge.g:1241:1: rule__ParameterList__Group_1_1__1__Impl : ( ( rule__ParameterList__ParametersAssignment_1_1_1 ) ) ;
    public final void rule__ParameterList__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1245:1: ( ( ( rule__ParameterList__ParametersAssignment_1_1_1 ) ) )
            // InternalZeroKnowledge.g:1246:1: ( ( rule__ParameterList__ParametersAssignment_1_1_1 ) )
            {
            // InternalZeroKnowledge.g:1246:1: ( ( rule__ParameterList__ParametersAssignment_1_1_1 ) )
            // InternalZeroKnowledge.g:1247:2: ( rule__ParameterList__ParametersAssignment_1_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getParameterListAccess().getParametersAssignment_1_1_1()); 
            }
            // InternalZeroKnowledge.g:1248:2: ( rule__ParameterList__ParametersAssignment_1_1_1 )
            // InternalZeroKnowledge.g:1248:3: rule__ParameterList__ParametersAssignment_1_1_1
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
    // InternalZeroKnowledge.g:1257:1: rule__WitnessList__Group__0 : rule__WitnessList__Group__0__Impl rule__WitnessList__Group__1 ;
    public final void rule__WitnessList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1261:1: ( rule__WitnessList__Group__0__Impl rule__WitnessList__Group__1 )
            // InternalZeroKnowledge.g:1262:2: rule__WitnessList__Group__0__Impl rule__WitnessList__Group__1
            {
            pushFollow(FOLLOW_13);
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
    // InternalZeroKnowledge.g:1269:1: rule__WitnessList__Group__0__Impl : ( '(' ) ;
    public final void rule__WitnessList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1273:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:1274:1: ( '(' )
            {
            // InternalZeroKnowledge.g:1274:1: ( '(' )
            // InternalZeroKnowledge.g:1275:2: '('
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
    // InternalZeroKnowledge.g:1284:1: rule__WitnessList__Group__1 : rule__WitnessList__Group__1__Impl rule__WitnessList__Group__2 ;
    public final void rule__WitnessList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1288:1: ( rule__WitnessList__Group__1__Impl rule__WitnessList__Group__2 )
            // InternalZeroKnowledge.g:1289:2: rule__WitnessList__Group__1__Impl rule__WitnessList__Group__2
            {
            pushFollow(FOLLOW_13);
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
    // InternalZeroKnowledge.g:1296:1: rule__WitnessList__Group__1__Impl : ( ( rule__WitnessList__WitnessesAssignment_1 )? ) ;
    public final void rule__WitnessList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1300:1: ( ( ( rule__WitnessList__WitnessesAssignment_1 )? ) )
            // InternalZeroKnowledge.g:1301:1: ( ( rule__WitnessList__WitnessesAssignment_1 )? )
            {
            // InternalZeroKnowledge.g:1301:1: ( ( rule__WitnessList__WitnessesAssignment_1 )? )
            // InternalZeroKnowledge.g:1302:2: ( rule__WitnessList__WitnessesAssignment_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getWitnessesAssignment_1()); 
            }
            // InternalZeroKnowledge.g:1303:2: ( rule__WitnessList__WitnessesAssignment_1 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_IDENTIFIER) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalZeroKnowledge.g:1303:3: rule__WitnessList__WitnessesAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__WitnessList__WitnessesAssignment_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getWitnessesAssignment_1()); 
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
    // InternalZeroKnowledge.g:1311:1: rule__WitnessList__Group__2 : rule__WitnessList__Group__2__Impl rule__WitnessList__Group__3 ;
    public final void rule__WitnessList__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1315:1: ( rule__WitnessList__Group__2__Impl rule__WitnessList__Group__3 )
            // InternalZeroKnowledge.g:1316:2: rule__WitnessList__Group__2__Impl rule__WitnessList__Group__3
            {
            pushFollow(FOLLOW_13);
            rule__WitnessList__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group__3();

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
    // InternalZeroKnowledge.g:1323:1: rule__WitnessList__Group__2__Impl : ( ( rule__WitnessList__Group_2__0 )* ) ;
    public final void rule__WitnessList__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1327:1: ( ( ( rule__WitnessList__Group_2__0 )* ) )
            // InternalZeroKnowledge.g:1328:1: ( ( rule__WitnessList__Group_2__0 )* )
            {
            // InternalZeroKnowledge.g:1328:1: ( ( rule__WitnessList__Group_2__0 )* )
            // InternalZeroKnowledge.g:1329:2: ( rule__WitnessList__Group_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getGroup_2()); 
            }
            // InternalZeroKnowledge.g:1330:2: ( rule__WitnessList__Group_2__0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==15) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalZeroKnowledge.g:1330:3: rule__WitnessList__Group_2__0
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__WitnessList__Group_2__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getGroup_2()); 
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


    // $ANTLR start "rule__WitnessList__Group__3"
    // InternalZeroKnowledge.g:1338:1: rule__WitnessList__Group__3 : rule__WitnessList__Group__3__Impl ;
    public final void rule__WitnessList__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1342:1: ( rule__WitnessList__Group__3__Impl )
            // InternalZeroKnowledge.g:1343:2: rule__WitnessList__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group__3__Impl();

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
    // $ANTLR end "rule__WitnessList__Group__3"


    // $ANTLR start "rule__WitnessList__Group__3__Impl"
    // InternalZeroKnowledge.g:1349:1: rule__WitnessList__Group__3__Impl : ( ')' ) ;
    public final void rule__WitnessList__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1353:1: ( ( ')' ) )
            // InternalZeroKnowledge.g:1354:1: ( ')' )
            {
            // InternalZeroKnowledge.g:1354:1: ( ')' )
            // InternalZeroKnowledge.g:1355:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getRightParenthesisKeyword_3()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getRightParenthesisKeyword_3()); 
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
    // $ANTLR end "rule__WitnessList__Group__3__Impl"


    // $ANTLR start "rule__WitnessList__Group_2__0"
    // InternalZeroKnowledge.g:1365:1: rule__WitnessList__Group_2__0 : rule__WitnessList__Group_2__0__Impl rule__WitnessList__Group_2__1 ;
    public final void rule__WitnessList__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1369:1: ( rule__WitnessList__Group_2__0__Impl rule__WitnessList__Group_2__1 )
            // InternalZeroKnowledge.g:1370:2: rule__WitnessList__Group_2__0__Impl rule__WitnessList__Group_2__1
            {
            pushFollow(FOLLOW_12);
            rule__WitnessList__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group_2__1();

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
    // $ANTLR end "rule__WitnessList__Group_2__0"


    // $ANTLR start "rule__WitnessList__Group_2__0__Impl"
    // InternalZeroKnowledge.g:1377:1: rule__WitnessList__Group_2__0__Impl : ( ',' ) ;
    public final void rule__WitnessList__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1381:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:1382:1: ( ',' )
            {
            // InternalZeroKnowledge.g:1382:1: ( ',' )
            // InternalZeroKnowledge.g:1383:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getCommaKeyword_2_0()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getCommaKeyword_2_0()); 
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
    // $ANTLR end "rule__WitnessList__Group_2__0__Impl"


    // $ANTLR start "rule__WitnessList__Group_2__1"
    // InternalZeroKnowledge.g:1392:1: rule__WitnessList__Group_2__1 : rule__WitnessList__Group_2__1__Impl ;
    public final void rule__WitnessList__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1396:1: ( rule__WitnessList__Group_2__1__Impl )
            // InternalZeroKnowledge.g:1397:2: rule__WitnessList__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__Group_2__1__Impl();

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
    // $ANTLR end "rule__WitnessList__Group_2__1"


    // $ANTLR start "rule__WitnessList__Group_2__1__Impl"
    // InternalZeroKnowledge.g:1403:1: rule__WitnessList__Group_2__1__Impl : ( ( rule__WitnessList__WitnessesAssignment_2_1 ) ) ;
    public final void rule__WitnessList__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1407:1: ( ( ( rule__WitnessList__WitnessesAssignment_2_1 ) ) )
            // InternalZeroKnowledge.g:1408:1: ( ( rule__WitnessList__WitnessesAssignment_2_1 ) )
            {
            // InternalZeroKnowledge.g:1408:1: ( ( rule__WitnessList__WitnessesAssignment_2_1 ) )
            // InternalZeroKnowledge.g:1409:2: ( rule__WitnessList__WitnessesAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getWitnessesAssignment_2_1()); 
            }
            // InternalZeroKnowledge.g:1410:2: ( rule__WitnessList__WitnessesAssignment_2_1 )
            // InternalZeroKnowledge.g:1410:3: rule__WitnessList__WitnessesAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__WitnessList__WitnessesAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getWitnessesAssignment_2_1()); 
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
    // $ANTLR end "rule__WitnessList__Group_2__1__Impl"


    // $ANTLR start "rule__Witness__Group__0"
    // InternalZeroKnowledge.g:1419:1: rule__Witness__Group__0 : rule__Witness__Group__0__Impl rule__Witness__Group__1 ;
    public final void rule__Witness__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1423:1: ( rule__Witness__Group__0__Impl rule__Witness__Group__1 )
            // InternalZeroKnowledge.g:1424:2: rule__Witness__Group__0__Impl rule__Witness__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__Witness__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Witness__Group__1();

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
    // $ANTLR end "rule__Witness__Group__0"


    // $ANTLR start "rule__Witness__Group__0__Impl"
    // InternalZeroKnowledge.g:1431:1: rule__Witness__Group__0__Impl : ( ( rule__Witness__NameAssignment_0 ) ) ;
    public final void rule__Witness__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1435:1: ( ( ( rule__Witness__NameAssignment_0 ) ) )
            // InternalZeroKnowledge.g:1436:1: ( ( rule__Witness__NameAssignment_0 ) )
            {
            // InternalZeroKnowledge.g:1436:1: ( ( rule__Witness__NameAssignment_0 ) )
            // InternalZeroKnowledge.g:1437:2: ( rule__Witness__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessAccess().getNameAssignment_0()); 
            }
            // InternalZeroKnowledge.g:1438:2: ( rule__Witness__NameAssignment_0 )
            // InternalZeroKnowledge.g:1438:3: rule__Witness__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Witness__NameAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessAccess().getNameAssignment_0()); 
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
    // $ANTLR end "rule__Witness__Group__0__Impl"


    // $ANTLR start "rule__Witness__Group__1"
    // InternalZeroKnowledge.g:1446:1: rule__Witness__Group__1 : rule__Witness__Group__1__Impl ;
    public final void rule__Witness__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1450:1: ( rule__Witness__Group__1__Impl )
            // InternalZeroKnowledge.g:1451:2: rule__Witness__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Witness__Group__1__Impl();

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
    // $ANTLR end "rule__Witness__Group__1"


    // $ANTLR start "rule__Witness__Group__1__Impl"
    // InternalZeroKnowledge.g:1457:1: rule__Witness__Group__1__Impl : ( ( rule__Witness__TestingAssignment_1 )? ) ;
    public final void rule__Witness__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1461:1: ( ( ( rule__Witness__TestingAssignment_1 )? ) )
            // InternalZeroKnowledge.g:1462:1: ( ( rule__Witness__TestingAssignment_1 )? )
            {
            // InternalZeroKnowledge.g:1462:1: ( ( rule__Witness__TestingAssignment_1 )? )
            // InternalZeroKnowledge.g:1463:2: ( rule__Witness__TestingAssignment_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessAccess().getTestingAssignment_1()); 
            }
            // InternalZeroKnowledge.g:1464:2: ( rule__Witness__TestingAssignment_1 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==18) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalZeroKnowledge.g:1464:3: rule__Witness__TestingAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Witness__TestingAssignment_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessAccess().getTestingAssignment_1()); 
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
    // $ANTLR end "rule__Witness__Group__1__Impl"


    // $ANTLR start "rule__Conjunction__Group__0"
    // InternalZeroKnowledge.g:1473:1: rule__Conjunction__Group__0 : rule__Conjunction__Group__0__Impl rule__Conjunction__Group__1 ;
    public final void rule__Conjunction__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1477:1: ( rule__Conjunction__Group__0__Impl rule__Conjunction__Group__1 )
            // InternalZeroKnowledge.g:1478:2: rule__Conjunction__Group__0__Impl rule__Conjunction__Group__1
            {
            pushFollow(FOLLOW_15);
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
    // InternalZeroKnowledge.g:1485:1: rule__Conjunction__Group__0__Impl : ( ruleDisjunction ) ;
    public final void rule__Conjunction__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1489:1: ( ( ruleDisjunction ) )
            // InternalZeroKnowledge.g:1490:1: ( ruleDisjunction )
            {
            // InternalZeroKnowledge.g:1490:1: ( ruleDisjunction )
            // InternalZeroKnowledge.g:1491:2: ruleDisjunction
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
    // InternalZeroKnowledge.g:1500:1: rule__Conjunction__Group__1 : rule__Conjunction__Group__1__Impl ;
    public final void rule__Conjunction__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1504:1: ( rule__Conjunction__Group__1__Impl )
            // InternalZeroKnowledge.g:1505:2: rule__Conjunction__Group__1__Impl
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
    // InternalZeroKnowledge.g:1511:1: rule__Conjunction__Group__1__Impl : ( ( rule__Conjunction__Group_1__0 )* ) ;
    public final void rule__Conjunction__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1515:1: ( ( ( rule__Conjunction__Group_1__0 )* ) )
            // InternalZeroKnowledge.g:1516:1: ( ( rule__Conjunction__Group_1__0 )* )
            {
            // InternalZeroKnowledge.g:1516:1: ( ( rule__Conjunction__Group_1__0 )* )
            // InternalZeroKnowledge.g:1517:2: ( rule__Conjunction__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:1518:2: ( rule__Conjunction__Group_1__0 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==19) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalZeroKnowledge.g:1518:3: rule__Conjunction__Group_1__0
            	    {
            	    pushFollow(FOLLOW_16);
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
    // InternalZeroKnowledge.g:1527:1: rule__Conjunction__Group_1__0 : rule__Conjunction__Group_1__0__Impl rule__Conjunction__Group_1__1 ;
    public final void rule__Conjunction__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1531:1: ( rule__Conjunction__Group_1__0__Impl rule__Conjunction__Group_1__1 )
            // InternalZeroKnowledge.g:1532:2: rule__Conjunction__Group_1__0__Impl rule__Conjunction__Group_1__1
            {
            pushFollow(FOLLOW_15);
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
    // InternalZeroKnowledge.g:1539:1: rule__Conjunction__Group_1__0__Impl : ( () ) ;
    public final void rule__Conjunction__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1543:1: ( ( () ) )
            // InternalZeroKnowledge.g:1544:1: ( () )
            {
            // InternalZeroKnowledge.g:1544:1: ( () )
            // InternalZeroKnowledge.g:1545:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getConjunctionLeftAction_1_0()); 
            }
            // InternalZeroKnowledge.g:1546:2: ()
            // InternalZeroKnowledge.g:1546:3: 
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
    // InternalZeroKnowledge.g:1554:1: rule__Conjunction__Group_1__1 : rule__Conjunction__Group_1__1__Impl rule__Conjunction__Group_1__2 ;
    public final void rule__Conjunction__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1558:1: ( rule__Conjunction__Group_1__1__Impl rule__Conjunction__Group_1__2 )
            // InternalZeroKnowledge.g:1559:2: rule__Conjunction__Group_1__1__Impl rule__Conjunction__Group_1__2
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
    // InternalZeroKnowledge.g:1566:1: rule__Conjunction__Group_1__1__Impl : ( ( rule__Conjunction__OperationAssignment_1_1 ) ) ;
    public final void rule__Conjunction__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1570:1: ( ( ( rule__Conjunction__OperationAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:1571:1: ( ( rule__Conjunction__OperationAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:1571:1: ( ( rule__Conjunction__OperationAssignment_1_1 ) )
            // InternalZeroKnowledge.g:1572:2: ( rule__Conjunction__OperationAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getOperationAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:1573:2: ( rule__Conjunction__OperationAssignment_1_1 )
            // InternalZeroKnowledge.g:1573:3: rule__Conjunction__OperationAssignment_1_1
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
    // InternalZeroKnowledge.g:1581:1: rule__Conjunction__Group_1__2 : rule__Conjunction__Group_1__2__Impl ;
    public final void rule__Conjunction__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1585:1: ( rule__Conjunction__Group_1__2__Impl )
            // InternalZeroKnowledge.g:1586:2: rule__Conjunction__Group_1__2__Impl
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
    // InternalZeroKnowledge.g:1592:1: rule__Conjunction__Group_1__2__Impl : ( ( rule__Conjunction__RightAssignment_1_2 ) ) ;
    public final void rule__Conjunction__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1596:1: ( ( ( rule__Conjunction__RightAssignment_1_2 ) ) )
            // InternalZeroKnowledge.g:1597:1: ( ( rule__Conjunction__RightAssignment_1_2 ) )
            {
            // InternalZeroKnowledge.g:1597:1: ( ( rule__Conjunction__RightAssignment_1_2 ) )
            // InternalZeroKnowledge.g:1598:2: ( rule__Conjunction__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getRightAssignment_1_2()); 
            }
            // InternalZeroKnowledge.g:1599:2: ( rule__Conjunction__RightAssignment_1_2 )
            // InternalZeroKnowledge.g:1599:3: rule__Conjunction__RightAssignment_1_2
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
    // InternalZeroKnowledge.g:1608:1: rule__Disjunction__Group__0 : rule__Disjunction__Group__0__Impl rule__Disjunction__Group__1 ;
    public final void rule__Disjunction__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1612:1: ( rule__Disjunction__Group__0__Impl rule__Disjunction__Group__1 )
            // InternalZeroKnowledge.g:1613:2: rule__Disjunction__Group__0__Impl rule__Disjunction__Group__1
            {
            pushFollow(FOLLOW_17);
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
    // InternalZeroKnowledge.g:1620:1: rule__Disjunction__Group__0__Impl : ( ruleComparison ) ;
    public final void rule__Disjunction__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1624:1: ( ( ruleComparison ) )
            // InternalZeroKnowledge.g:1625:1: ( ruleComparison )
            {
            // InternalZeroKnowledge.g:1625:1: ( ruleComparison )
            // InternalZeroKnowledge.g:1626:2: ruleComparison
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
    // InternalZeroKnowledge.g:1635:1: rule__Disjunction__Group__1 : rule__Disjunction__Group__1__Impl ;
    public final void rule__Disjunction__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1639:1: ( rule__Disjunction__Group__1__Impl )
            // InternalZeroKnowledge.g:1640:2: rule__Disjunction__Group__1__Impl
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
    // InternalZeroKnowledge.g:1646:1: rule__Disjunction__Group__1__Impl : ( ( rule__Disjunction__Group_1__0 )* ) ;
    public final void rule__Disjunction__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1650:1: ( ( ( rule__Disjunction__Group_1__0 )* ) )
            // InternalZeroKnowledge.g:1651:1: ( ( rule__Disjunction__Group_1__0 )* )
            {
            // InternalZeroKnowledge.g:1651:1: ( ( rule__Disjunction__Group_1__0 )* )
            // InternalZeroKnowledge.g:1652:2: ( rule__Disjunction__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:1653:2: ( rule__Disjunction__Group_1__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==20) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalZeroKnowledge.g:1653:3: rule__Disjunction__Group_1__0
            	    {
            	    pushFollow(FOLLOW_18);
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
    // InternalZeroKnowledge.g:1662:1: rule__Disjunction__Group_1__0 : rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1 ;
    public final void rule__Disjunction__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1666:1: ( rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1 )
            // InternalZeroKnowledge.g:1667:2: rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1
            {
            pushFollow(FOLLOW_17);
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
    // InternalZeroKnowledge.g:1674:1: rule__Disjunction__Group_1__0__Impl : ( () ) ;
    public final void rule__Disjunction__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1678:1: ( ( () ) )
            // InternalZeroKnowledge.g:1679:1: ( () )
            {
            // InternalZeroKnowledge.g:1679:1: ( () )
            // InternalZeroKnowledge.g:1680:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getDisjunctionLeftAction_1_0()); 
            }
            // InternalZeroKnowledge.g:1681:2: ()
            // InternalZeroKnowledge.g:1681:3: 
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
    // InternalZeroKnowledge.g:1689:1: rule__Disjunction__Group_1__1 : rule__Disjunction__Group_1__1__Impl rule__Disjunction__Group_1__2 ;
    public final void rule__Disjunction__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1693:1: ( rule__Disjunction__Group_1__1__Impl rule__Disjunction__Group_1__2 )
            // InternalZeroKnowledge.g:1694:2: rule__Disjunction__Group_1__1__Impl rule__Disjunction__Group_1__2
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
    // InternalZeroKnowledge.g:1701:1: rule__Disjunction__Group_1__1__Impl : ( ( rule__Disjunction__OperationAssignment_1_1 ) ) ;
    public final void rule__Disjunction__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1705:1: ( ( ( rule__Disjunction__OperationAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:1706:1: ( ( rule__Disjunction__OperationAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:1706:1: ( ( rule__Disjunction__OperationAssignment_1_1 ) )
            // InternalZeroKnowledge.g:1707:2: ( rule__Disjunction__OperationAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getOperationAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:1708:2: ( rule__Disjunction__OperationAssignment_1_1 )
            // InternalZeroKnowledge.g:1708:3: rule__Disjunction__OperationAssignment_1_1
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
    // InternalZeroKnowledge.g:1716:1: rule__Disjunction__Group_1__2 : rule__Disjunction__Group_1__2__Impl ;
    public final void rule__Disjunction__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1720:1: ( rule__Disjunction__Group_1__2__Impl )
            // InternalZeroKnowledge.g:1721:2: rule__Disjunction__Group_1__2__Impl
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
    // InternalZeroKnowledge.g:1727:1: rule__Disjunction__Group_1__2__Impl : ( ( rule__Disjunction__RightAssignment_1_2 ) ) ;
    public final void rule__Disjunction__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1731:1: ( ( ( rule__Disjunction__RightAssignment_1_2 ) ) )
            // InternalZeroKnowledge.g:1732:1: ( ( rule__Disjunction__RightAssignment_1_2 ) )
            {
            // InternalZeroKnowledge.g:1732:1: ( ( rule__Disjunction__RightAssignment_1_2 ) )
            // InternalZeroKnowledge.g:1733:2: ( rule__Disjunction__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getRightAssignment_1_2()); 
            }
            // InternalZeroKnowledge.g:1734:2: ( rule__Disjunction__RightAssignment_1_2 )
            // InternalZeroKnowledge.g:1734:3: rule__Disjunction__RightAssignment_1_2
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
    // InternalZeroKnowledge.g:1743:1: rule__Comparison__Group__0 : rule__Comparison__Group__0__Impl rule__Comparison__Group__1 ;
    public final void rule__Comparison__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1747:1: ( rule__Comparison__Group__0__Impl rule__Comparison__Group__1 )
            // InternalZeroKnowledge.g:1748:2: rule__Comparison__Group__0__Impl rule__Comparison__Group__1
            {
            pushFollow(FOLLOW_19);
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
    // InternalZeroKnowledge.g:1755:1: rule__Comparison__Group__0__Impl : ( ruleSum ) ;
    public final void rule__Comparison__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1759:1: ( ( ruleSum ) )
            // InternalZeroKnowledge.g:1760:1: ( ruleSum )
            {
            // InternalZeroKnowledge.g:1760:1: ( ruleSum )
            // InternalZeroKnowledge.g:1761:2: ruleSum
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
    // InternalZeroKnowledge.g:1770:1: rule__Comparison__Group__1 : rule__Comparison__Group__1__Impl ;
    public final void rule__Comparison__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1774:1: ( rule__Comparison__Group__1__Impl )
            // InternalZeroKnowledge.g:1775:2: rule__Comparison__Group__1__Impl
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
    // InternalZeroKnowledge.g:1781:1: rule__Comparison__Group__1__Impl : ( ( rule__Comparison__Group_1__0 )? ) ;
    public final void rule__Comparison__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1785:1: ( ( ( rule__Comparison__Group_1__0 )? ) )
            // InternalZeroKnowledge.g:1786:1: ( ( rule__Comparison__Group_1__0 )? )
            {
            // InternalZeroKnowledge.g:1786:1: ( ( rule__Comparison__Group_1__0 )? )
            // InternalZeroKnowledge.g:1787:2: ( rule__Comparison__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:1788:2: ( rule__Comparison__Group_1__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=21 && LA18_0<=26)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalZeroKnowledge.g:1788:3: rule__Comparison__Group_1__0
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
    // InternalZeroKnowledge.g:1797:1: rule__Comparison__Group_1__0 : rule__Comparison__Group_1__0__Impl rule__Comparison__Group_1__1 ;
    public final void rule__Comparison__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1801:1: ( rule__Comparison__Group_1__0__Impl rule__Comparison__Group_1__1 )
            // InternalZeroKnowledge.g:1802:2: rule__Comparison__Group_1__0__Impl rule__Comparison__Group_1__1
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
    // InternalZeroKnowledge.g:1809:1: rule__Comparison__Group_1__0__Impl : ( ( rule__Comparison__Alternatives_1_0 ) ) ;
    public final void rule__Comparison__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1813:1: ( ( ( rule__Comparison__Alternatives_1_0 ) ) )
            // InternalZeroKnowledge.g:1814:1: ( ( rule__Comparison__Alternatives_1_0 ) )
            {
            // InternalZeroKnowledge.g:1814:1: ( ( rule__Comparison__Alternatives_1_0 ) )
            // InternalZeroKnowledge.g:1815:2: ( rule__Comparison__Alternatives_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getAlternatives_1_0()); 
            }
            // InternalZeroKnowledge.g:1816:2: ( rule__Comparison__Alternatives_1_0 )
            // InternalZeroKnowledge.g:1816:3: rule__Comparison__Alternatives_1_0
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
    // InternalZeroKnowledge.g:1824:1: rule__Comparison__Group_1__1 : rule__Comparison__Group_1__1__Impl ;
    public final void rule__Comparison__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1828:1: ( rule__Comparison__Group_1__1__Impl )
            // InternalZeroKnowledge.g:1829:2: rule__Comparison__Group_1__1__Impl
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
    // InternalZeroKnowledge.g:1835:1: rule__Comparison__Group_1__1__Impl : ( ( rule__Comparison__RightAssignment_1_1 ) ) ;
    public final void rule__Comparison__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1839:1: ( ( ( rule__Comparison__RightAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:1840:1: ( ( rule__Comparison__RightAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:1840:1: ( ( rule__Comparison__RightAssignment_1_1 ) )
            // InternalZeroKnowledge.g:1841:2: ( rule__Comparison__RightAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getRightAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:1842:2: ( rule__Comparison__RightAssignment_1_1 )
            // InternalZeroKnowledge.g:1842:3: rule__Comparison__RightAssignment_1_1
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
    // InternalZeroKnowledge.g:1851:1: rule__Comparison__Group_1_0_0__0 : rule__Comparison__Group_1_0_0__0__Impl rule__Comparison__Group_1_0_0__1 ;
    public final void rule__Comparison__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1855:1: ( rule__Comparison__Group_1_0_0__0__Impl rule__Comparison__Group_1_0_0__1 )
            // InternalZeroKnowledge.g:1856:2: rule__Comparison__Group_1_0_0__0__Impl rule__Comparison__Group_1_0_0__1
            {
            pushFollow(FOLLOW_20);
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
    // InternalZeroKnowledge.g:1863:1: rule__Comparison__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1867:1: ( ( () ) )
            // InternalZeroKnowledge.g:1868:1: ( () )
            {
            // InternalZeroKnowledge.g:1868:1: ( () )
            // InternalZeroKnowledge.g:1869:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_0_0()); 
            }
            // InternalZeroKnowledge.g:1870:2: ()
            // InternalZeroKnowledge.g:1870:3: 
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
    // InternalZeroKnowledge.g:1878:1: rule__Comparison__Group_1_0_0__1 : rule__Comparison__Group_1_0_0__1__Impl ;
    public final void rule__Comparison__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1882:1: ( rule__Comparison__Group_1_0_0__1__Impl )
            // InternalZeroKnowledge.g:1883:2: rule__Comparison__Group_1_0_0__1__Impl
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
    // InternalZeroKnowledge.g:1889:1: rule__Comparison__Group_1_0_0__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_0_1 ) ) ;
    public final void rule__Comparison__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1893:1: ( ( ( rule__Comparison__OperationAssignment_1_0_0_1 ) ) )
            // InternalZeroKnowledge.g:1894:1: ( ( rule__Comparison__OperationAssignment_1_0_0_1 ) )
            {
            // InternalZeroKnowledge.g:1894:1: ( ( rule__Comparison__OperationAssignment_1_0_0_1 ) )
            // InternalZeroKnowledge.g:1895:2: ( rule__Comparison__OperationAssignment_1_0_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_0_1()); 
            }
            // InternalZeroKnowledge.g:1896:2: ( rule__Comparison__OperationAssignment_1_0_0_1 )
            // InternalZeroKnowledge.g:1896:3: rule__Comparison__OperationAssignment_1_0_0_1
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
    // InternalZeroKnowledge.g:1905:1: rule__Comparison__Group_1_0_1__0 : rule__Comparison__Group_1_0_1__0__Impl rule__Comparison__Group_1_0_1__1 ;
    public final void rule__Comparison__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1909:1: ( rule__Comparison__Group_1_0_1__0__Impl rule__Comparison__Group_1_0_1__1 )
            // InternalZeroKnowledge.g:1910:2: rule__Comparison__Group_1_0_1__0__Impl rule__Comparison__Group_1_0_1__1
            {
            pushFollow(FOLLOW_21);
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
    // InternalZeroKnowledge.g:1917:1: rule__Comparison__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1921:1: ( ( () ) )
            // InternalZeroKnowledge.g:1922:1: ( () )
            {
            // InternalZeroKnowledge.g:1922:1: ( () )
            // InternalZeroKnowledge.g:1923:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_1_0()); 
            }
            // InternalZeroKnowledge.g:1924:2: ()
            // InternalZeroKnowledge.g:1924:3: 
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
    // InternalZeroKnowledge.g:1932:1: rule__Comparison__Group_1_0_1__1 : rule__Comparison__Group_1_0_1__1__Impl ;
    public final void rule__Comparison__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1936:1: ( rule__Comparison__Group_1_0_1__1__Impl )
            // InternalZeroKnowledge.g:1937:2: rule__Comparison__Group_1_0_1__1__Impl
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
    // InternalZeroKnowledge.g:1943:1: rule__Comparison__Group_1_0_1__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_1_1 ) ) ;
    public final void rule__Comparison__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1947:1: ( ( ( rule__Comparison__OperationAssignment_1_0_1_1 ) ) )
            // InternalZeroKnowledge.g:1948:1: ( ( rule__Comparison__OperationAssignment_1_0_1_1 ) )
            {
            // InternalZeroKnowledge.g:1948:1: ( ( rule__Comparison__OperationAssignment_1_0_1_1 ) )
            // InternalZeroKnowledge.g:1949:2: ( rule__Comparison__OperationAssignment_1_0_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_1_1()); 
            }
            // InternalZeroKnowledge.g:1950:2: ( rule__Comparison__OperationAssignment_1_0_1_1 )
            // InternalZeroKnowledge.g:1950:3: rule__Comparison__OperationAssignment_1_0_1_1
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
    // InternalZeroKnowledge.g:1959:1: rule__Comparison__Group_1_0_2__0 : rule__Comparison__Group_1_0_2__0__Impl rule__Comparison__Group_1_0_2__1 ;
    public final void rule__Comparison__Group_1_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1963:1: ( rule__Comparison__Group_1_0_2__0__Impl rule__Comparison__Group_1_0_2__1 )
            // InternalZeroKnowledge.g:1964:2: rule__Comparison__Group_1_0_2__0__Impl rule__Comparison__Group_1_0_2__1
            {
            pushFollow(FOLLOW_22);
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
    // InternalZeroKnowledge.g:1971:1: rule__Comparison__Group_1_0_2__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1975:1: ( ( () ) )
            // InternalZeroKnowledge.g:1976:1: ( () )
            {
            // InternalZeroKnowledge.g:1976:1: ( () )
            // InternalZeroKnowledge.g:1977:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_2_0()); 
            }
            // InternalZeroKnowledge.g:1978:2: ()
            // InternalZeroKnowledge.g:1978:3: 
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
    // InternalZeroKnowledge.g:1986:1: rule__Comparison__Group_1_0_2__1 : rule__Comparison__Group_1_0_2__1__Impl ;
    public final void rule__Comparison__Group_1_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:1990:1: ( rule__Comparison__Group_1_0_2__1__Impl )
            // InternalZeroKnowledge.g:1991:2: rule__Comparison__Group_1_0_2__1__Impl
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
    // InternalZeroKnowledge.g:1997:1: rule__Comparison__Group_1_0_2__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_2_1 ) ) ;
    public final void rule__Comparison__Group_1_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2001:1: ( ( ( rule__Comparison__OperationAssignment_1_0_2_1 ) ) )
            // InternalZeroKnowledge.g:2002:1: ( ( rule__Comparison__OperationAssignment_1_0_2_1 ) )
            {
            // InternalZeroKnowledge.g:2002:1: ( ( rule__Comparison__OperationAssignment_1_0_2_1 ) )
            // InternalZeroKnowledge.g:2003:2: ( rule__Comparison__OperationAssignment_1_0_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_2_1()); 
            }
            // InternalZeroKnowledge.g:2004:2: ( rule__Comparison__OperationAssignment_1_0_2_1 )
            // InternalZeroKnowledge.g:2004:3: rule__Comparison__OperationAssignment_1_0_2_1
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
    // InternalZeroKnowledge.g:2013:1: rule__Comparison__Group_1_0_3__0 : rule__Comparison__Group_1_0_3__0__Impl rule__Comparison__Group_1_0_3__1 ;
    public final void rule__Comparison__Group_1_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2017:1: ( rule__Comparison__Group_1_0_3__0__Impl rule__Comparison__Group_1_0_3__1 )
            // InternalZeroKnowledge.g:2018:2: rule__Comparison__Group_1_0_3__0__Impl rule__Comparison__Group_1_0_3__1
            {
            pushFollow(FOLLOW_23);
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
    // InternalZeroKnowledge.g:2025:1: rule__Comparison__Group_1_0_3__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2029:1: ( ( () ) )
            // InternalZeroKnowledge.g:2030:1: ( () )
            {
            // InternalZeroKnowledge.g:2030:1: ( () )
            // InternalZeroKnowledge.g:2031:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_3_0()); 
            }
            // InternalZeroKnowledge.g:2032:2: ()
            // InternalZeroKnowledge.g:2032:3: 
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
    // InternalZeroKnowledge.g:2040:1: rule__Comparison__Group_1_0_3__1 : rule__Comparison__Group_1_0_3__1__Impl ;
    public final void rule__Comparison__Group_1_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2044:1: ( rule__Comparison__Group_1_0_3__1__Impl )
            // InternalZeroKnowledge.g:2045:2: rule__Comparison__Group_1_0_3__1__Impl
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
    // InternalZeroKnowledge.g:2051:1: rule__Comparison__Group_1_0_3__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_3_1 ) ) ;
    public final void rule__Comparison__Group_1_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2055:1: ( ( ( rule__Comparison__OperationAssignment_1_0_3_1 ) ) )
            // InternalZeroKnowledge.g:2056:1: ( ( rule__Comparison__OperationAssignment_1_0_3_1 ) )
            {
            // InternalZeroKnowledge.g:2056:1: ( ( rule__Comparison__OperationAssignment_1_0_3_1 ) )
            // InternalZeroKnowledge.g:2057:2: ( rule__Comparison__OperationAssignment_1_0_3_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_3_1()); 
            }
            // InternalZeroKnowledge.g:2058:2: ( rule__Comparison__OperationAssignment_1_0_3_1 )
            // InternalZeroKnowledge.g:2058:3: rule__Comparison__OperationAssignment_1_0_3_1
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
    // InternalZeroKnowledge.g:2067:1: rule__Comparison__Group_1_0_4__0 : rule__Comparison__Group_1_0_4__0__Impl rule__Comparison__Group_1_0_4__1 ;
    public final void rule__Comparison__Group_1_0_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2071:1: ( rule__Comparison__Group_1_0_4__0__Impl rule__Comparison__Group_1_0_4__1 )
            // InternalZeroKnowledge.g:2072:2: rule__Comparison__Group_1_0_4__0__Impl rule__Comparison__Group_1_0_4__1
            {
            pushFollow(FOLLOW_24);
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
    // InternalZeroKnowledge.g:2079:1: rule__Comparison__Group_1_0_4__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2083:1: ( ( () ) )
            // InternalZeroKnowledge.g:2084:1: ( () )
            {
            // InternalZeroKnowledge.g:2084:1: ( () )
            // InternalZeroKnowledge.g:2085:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_4_0()); 
            }
            // InternalZeroKnowledge.g:2086:2: ()
            // InternalZeroKnowledge.g:2086:3: 
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
    // InternalZeroKnowledge.g:2094:1: rule__Comparison__Group_1_0_4__1 : rule__Comparison__Group_1_0_4__1__Impl ;
    public final void rule__Comparison__Group_1_0_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2098:1: ( rule__Comparison__Group_1_0_4__1__Impl )
            // InternalZeroKnowledge.g:2099:2: rule__Comparison__Group_1_0_4__1__Impl
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
    // InternalZeroKnowledge.g:2105:1: rule__Comparison__Group_1_0_4__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_4_1 ) ) ;
    public final void rule__Comparison__Group_1_0_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2109:1: ( ( ( rule__Comparison__OperationAssignment_1_0_4_1 ) ) )
            // InternalZeroKnowledge.g:2110:1: ( ( rule__Comparison__OperationAssignment_1_0_4_1 ) )
            {
            // InternalZeroKnowledge.g:2110:1: ( ( rule__Comparison__OperationAssignment_1_0_4_1 ) )
            // InternalZeroKnowledge.g:2111:2: ( rule__Comparison__OperationAssignment_1_0_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_4_1()); 
            }
            // InternalZeroKnowledge.g:2112:2: ( rule__Comparison__OperationAssignment_1_0_4_1 )
            // InternalZeroKnowledge.g:2112:3: rule__Comparison__OperationAssignment_1_0_4_1
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
    // InternalZeroKnowledge.g:2121:1: rule__Comparison__Group_1_0_5__0 : rule__Comparison__Group_1_0_5__0__Impl rule__Comparison__Group_1_0_5__1 ;
    public final void rule__Comparison__Group_1_0_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2125:1: ( rule__Comparison__Group_1_0_5__0__Impl rule__Comparison__Group_1_0_5__1 )
            // InternalZeroKnowledge.g:2126:2: rule__Comparison__Group_1_0_5__0__Impl rule__Comparison__Group_1_0_5__1
            {
            pushFollow(FOLLOW_19);
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
    // InternalZeroKnowledge.g:2133:1: rule__Comparison__Group_1_0_5__0__Impl : ( () ) ;
    public final void rule__Comparison__Group_1_0_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2137:1: ( ( () ) )
            // InternalZeroKnowledge.g:2138:1: ( () )
            {
            // InternalZeroKnowledge.g:2138:1: ( () )
            // InternalZeroKnowledge.g:2139:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_5_0()); 
            }
            // InternalZeroKnowledge.g:2140:2: ()
            // InternalZeroKnowledge.g:2140:3: 
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
    // InternalZeroKnowledge.g:2148:1: rule__Comparison__Group_1_0_5__1 : rule__Comparison__Group_1_0_5__1__Impl ;
    public final void rule__Comparison__Group_1_0_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2152:1: ( rule__Comparison__Group_1_0_5__1__Impl )
            // InternalZeroKnowledge.g:2153:2: rule__Comparison__Group_1_0_5__1__Impl
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
    // InternalZeroKnowledge.g:2159:1: rule__Comparison__Group_1_0_5__1__Impl : ( ( rule__Comparison__OperationAssignment_1_0_5_1 ) ) ;
    public final void rule__Comparison__Group_1_0_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2163:1: ( ( ( rule__Comparison__OperationAssignment_1_0_5_1 ) ) )
            // InternalZeroKnowledge.g:2164:1: ( ( rule__Comparison__OperationAssignment_1_0_5_1 ) )
            {
            // InternalZeroKnowledge.g:2164:1: ( ( rule__Comparison__OperationAssignment_1_0_5_1 ) )
            // InternalZeroKnowledge.g:2165:2: ( rule__Comparison__OperationAssignment_1_0_5_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationAssignment_1_0_5_1()); 
            }
            // InternalZeroKnowledge.g:2166:2: ( rule__Comparison__OperationAssignment_1_0_5_1 )
            // InternalZeroKnowledge.g:2166:3: rule__Comparison__OperationAssignment_1_0_5_1
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
    // InternalZeroKnowledge.g:2175:1: rule__Sum__Group__0 : rule__Sum__Group__0__Impl rule__Sum__Group__1 ;
    public final void rule__Sum__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2179:1: ( rule__Sum__Group__0__Impl rule__Sum__Group__1 )
            // InternalZeroKnowledge.g:2180:2: rule__Sum__Group__0__Impl rule__Sum__Group__1
            {
            pushFollow(FOLLOW_25);
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
    // InternalZeroKnowledge.g:2187:1: rule__Sum__Group__0__Impl : ( ruleProduct ) ;
    public final void rule__Sum__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2191:1: ( ( ruleProduct ) )
            // InternalZeroKnowledge.g:2192:1: ( ruleProduct )
            {
            // InternalZeroKnowledge.g:2192:1: ( ruleProduct )
            // InternalZeroKnowledge.g:2193:2: ruleProduct
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
    // InternalZeroKnowledge.g:2202:1: rule__Sum__Group__1 : rule__Sum__Group__1__Impl ;
    public final void rule__Sum__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2206:1: ( rule__Sum__Group__1__Impl )
            // InternalZeroKnowledge.g:2207:2: rule__Sum__Group__1__Impl
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
    // InternalZeroKnowledge.g:2213:1: rule__Sum__Group__1__Impl : ( ( rule__Sum__Group_1__0 )* ) ;
    public final void rule__Sum__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2217:1: ( ( ( rule__Sum__Group_1__0 )* ) )
            // InternalZeroKnowledge.g:2218:1: ( ( rule__Sum__Group_1__0 )* )
            {
            // InternalZeroKnowledge.g:2218:1: ( ( rule__Sum__Group_1__0 )* )
            // InternalZeroKnowledge.g:2219:2: ( rule__Sum__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:2220:2: ( rule__Sum__Group_1__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==17||LA19_0==27) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalZeroKnowledge.g:2220:3: rule__Sum__Group_1__0
            	    {
            	    pushFollow(FOLLOW_26);
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
    // InternalZeroKnowledge.g:2229:1: rule__Sum__Group_1__0 : rule__Sum__Group_1__0__Impl rule__Sum__Group_1__1 ;
    public final void rule__Sum__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2233:1: ( rule__Sum__Group_1__0__Impl rule__Sum__Group_1__1 )
            // InternalZeroKnowledge.g:2234:2: rule__Sum__Group_1__0__Impl rule__Sum__Group_1__1
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
    // InternalZeroKnowledge.g:2241:1: rule__Sum__Group_1__0__Impl : ( ( rule__Sum__Alternatives_1_0 ) ) ;
    public final void rule__Sum__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2245:1: ( ( ( rule__Sum__Alternatives_1_0 ) ) )
            // InternalZeroKnowledge.g:2246:1: ( ( rule__Sum__Alternatives_1_0 ) )
            {
            // InternalZeroKnowledge.g:2246:1: ( ( rule__Sum__Alternatives_1_0 ) )
            // InternalZeroKnowledge.g:2247:2: ( rule__Sum__Alternatives_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getAlternatives_1_0()); 
            }
            // InternalZeroKnowledge.g:2248:2: ( rule__Sum__Alternatives_1_0 )
            // InternalZeroKnowledge.g:2248:3: rule__Sum__Alternatives_1_0
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
    // InternalZeroKnowledge.g:2256:1: rule__Sum__Group_1__1 : rule__Sum__Group_1__1__Impl ;
    public final void rule__Sum__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2260:1: ( rule__Sum__Group_1__1__Impl )
            // InternalZeroKnowledge.g:2261:2: rule__Sum__Group_1__1__Impl
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
    // InternalZeroKnowledge.g:2267:1: rule__Sum__Group_1__1__Impl : ( ( rule__Sum__RightAssignment_1_1 ) ) ;
    public final void rule__Sum__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2271:1: ( ( ( rule__Sum__RightAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:2272:1: ( ( rule__Sum__RightAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:2272:1: ( ( rule__Sum__RightAssignment_1_1 ) )
            // InternalZeroKnowledge.g:2273:2: ( rule__Sum__RightAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getRightAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:2274:2: ( rule__Sum__RightAssignment_1_1 )
            // InternalZeroKnowledge.g:2274:3: rule__Sum__RightAssignment_1_1
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
    // InternalZeroKnowledge.g:2283:1: rule__Sum__Group_1_0_0__0 : rule__Sum__Group_1_0_0__0__Impl rule__Sum__Group_1_0_0__1 ;
    public final void rule__Sum__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2287:1: ( rule__Sum__Group_1_0_0__0__Impl rule__Sum__Group_1_0_0__1 )
            // InternalZeroKnowledge.g:2288:2: rule__Sum__Group_1_0_0__0__Impl rule__Sum__Group_1_0_0__1
            {
            pushFollow(FOLLOW_27);
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
    // InternalZeroKnowledge.g:2295:1: rule__Sum__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Sum__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2299:1: ( ( () ) )
            // InternalZeroKnowledge.g:2300:1: ( () )
            {
            // InternalZeroKnowledge.g:2300:1: ( () )
            // InternalZeroKnowledge.g:2301:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getSumLeftAction_1_0_0_0()); 
            }
            // InternalZeroKnowledge.g:2302:2: ()
            // InternalZeroKnowledge.g:2302:3: 
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
    // InternalZeroKnowledge.g:2310:1: rule__Sum__Group_1_0_0__1 : rule__Sum__Group_1_0_0__1__Impl ;
    public final void rule__Sum__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2314:1: ( rule__Sum__Group_1_0_0__1__Impl )
            // InternalZeroKnowledge.g:2315:2: rule__Sum__Group_1_0_0__1__Impl
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
    // InternalZeroKnowledge.g:2321:1: rule__Sum__Group_1_0_0__1__Impl : ( ( rule__Sum__OperationAssignment_1_0_0_1 ) ) ;
    public final void rule__Sum__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2325:1: ( ( ( rule__Sum__OperationAssignment_1_0_0_1 ) ) )
            // InternalZeroKnowledge.g:2326:1: ( ( rule__Sum__OperationAssignment_1_0_0_1 ) )
            {
            // InternalZeroKnowledge.g:2326:1: ( ( rule__Sum__OperationAssignment_1_0_0_1 ) )
            // InternalZeroKnowledge.g:2327:2: ( rule__Sum__OperationAssignment_1_0_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationAssignment_1_0_0_1()); 
            }
            // InternalZeroKnowledge.g:2328:2: ( rule__Sum__OperationAssignment_1_0_0_1 )
            // InternalZeroKnowledge.g:2328:3: rule__Sum__OperationAssignment_1_0_0_1
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
    // InternalZeroKnowledge.g:2337:1: rule__Sum__Group_1_0_1__0 : rule__Sum__Group_1_0_1__0__Impl rule__Sum__Group_1_0_1__1 ;
    public final void rule__Sum__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2341:1: ( rule__Sum__Group_1_0_1__0__Impl rule__Sum__Group_1_0_1__1 )
            // InternalZeroKnowledge.g:2342:2: rule__Sum__Group_1_0_1__0__Impl rule__Sum__Group_1_0_1__1
            {
            pushFollow(FOLLOW_25);
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
    // InternalZeroKnowledge.g:2349:1: rule__Sum__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Sum__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2353:1: ( ( () ) )
            // InternalZeroKnowledge.g:2354:1: ( () )
            {
            // InternalZeroKnowledge.g:2354:1: ( () )
            // InternalZeroKnowledge.g:2355:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getSumLeftAction_1_0_1_0()); 
            }
            // InternalZeroKnowledge.g:2356:2: ()
            // InternalZeroKnowledge.g:2356:3: 
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
    // InternalZeroKnowledge.g:2364:1: rule__Sum__Group_1_0_1__1 : rule__Sum__Group_1_0_1__1__Impl ;
    public final void rule__Sum__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2368:1: ( rule__Sum__Group_1_0_1__1__Impl )
            // InternalZeroKnowledge.g:2369:2: rule__Sum__Group_1_0_1__1__Impl
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
    // InternalZeroKnowledge.g:2375:1: rule__Sum__Group_1_0_1__1__Impl : ( ( rule__Sum__OperationAssignment_1_0_1_1 ) ) ;
    public final void rule__Sum__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2379:1: ( ( ( rule__Sum__OperationAssignment_1_0_1_1 ) ) )
            // InternalZeroKnowledge.g:2380:1: ( ( rule__Sum__OperationAssignment_1_0_1_1 ) )
            {
            // InternalZeroKnowledge.g:2380:1: ( ( rule__Sum__OperationAssignment_1_0_1_1 ) )
            // InternalZeroKnowledge.g:2381:2: ( rule__Sum__OperationAssignment_1_0_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationAssignment_1_0_1_1()); 
            }
            // InternalZeroKnowledge.g:2382:2: ( rule__Sum__OperationAssignment_1_0_1_1 )
            // InternalZeroKnowledge.g:2382:3: rule__Sum__OperationAssignment_1_0_1_1
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
    // InternalZeroKnowledge.g:2391:1: rule__Product__Group__0 : rule__Product__Group__0__Impl rule__Product__Group__1 ;
    public final void rule__Product__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2395:1: ( rule__Product__Group__0__Impl rule__Product__Group__1 )
            // InternalZeroKnowledge.g:2396:2: rule__Product__Group__0__Impl rule__Product__Group__1
            {
            pushFollow(FOLLOW_28);
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
    // InternalZeroKnowledge.g:2403:1: rule__Product__Group__0__Impl : ( rulePower ) ;
    public final void rule__Product__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2407:1: ( ( rulePower ) )
            // InternalZeroKnowledge.g:2408:1: ( rulePower )
            {
            // InternalZeroKnowledge.g:2408:1: ( rulePower )
            // InternalZeroKnowledge.g:2409:2: rulePower
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
    // InternalZeroKnowledge.g:2418:1: rule__Product__Group__1 : rule__Product__Group__1__Impl ;
    public final void rule__Product__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2422:1: ( rule__Product__Group__1__Impl )
            // InternalZeroKnowledge.g:2423:2: rule__Product__Group__1__Impl
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
    // InternalZeroKnowledge.g:2429:1: rule__Product__Group__1__Impl : ( ( rule__Product__Group_1__0 )* ) ;
    public final void rule__Product__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2433:1: ( ( ( rule__Product__Group_1__0 )* ) )
            // InternalZeroKnowledge.g:2434:1: ( ( rule__Product__Group_1__0 )* )
            {
            // InternalZeroKnowledge.g:2434:1: ( ( rule__Product__Group_1__0 )* )
            // InternalZeroKnowledge.g:2435:2: ( rule__Product__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:2436:2: ( rule__Product__Group_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>=28 && LA20_0<=29)) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalZeroKnowledge.g:2436:3: rule__Product__Group_1__0
            	    {
            	    pushFollow(FOLLOW_29);
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
    // InternalZeroKnowledge.g:2445:1: rule__Product__Group_1__0 : rule__Product__Group_1__0__Impl rule__Product__Group_1__1 ;
    public final void rule__Product__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2449:1: ( rule__Product__Group_1__0__Impl rule__Product__Group_1__1 )
            // InternalZeroKnowledge.g:2450:2: rule__Product__Group_1__0__Impl rule__Product__Group_1__1
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
    // InternalZeroKnowledge.g:2457:1: rule__Product__Group_1__0__Impl : ( ( rule__Product__Alternatives_1_0 ) ) ;
    public final void rule__Product__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2461:1: ( ( ( rule__Product__Alternatives_1_0 ) ) )
            // InternalZeroKnowledge.g:2462:1: ( ( rule__Product__Alternatives_1_0 ) )
            {
            // InternalZeroKnowledge.g:2462:1: ( ( rule__Product__Alternatives_1_0 ) )
            // InternalZeroKnowledge.g:2463:2: ( rule__Product__Alternatives_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getAlternatives_1_0()); 
            }
            // InternalZeroKnowledge.g:2464:2: ( rule__Product__Alternatives_1_0 )
            // InternalZeroKnowledge.g:2464:3: rule__Product__Alternatives_1_0
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
    // InternalZeroKnowledge.g:2472:1: rule__Product__Group_1__1 : rule__Product__Group_1__1__Impl ;
    public final void rule__Product__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2476:1: ( rule__Product__Group_1__1__Impl )
            // InternalZeroKnowledge.g:2477:2: rule__Product__Group_1__1__Impl
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
    // InternalZeroKnowledge.g:2483:1: rule__Product__Group_1__1__Impl : ( ( rule__Product__RightAssignment_1_1 ) ) ;
    public final void rule__Product__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2487:1: ( ( ( rule__Product__RightAssignment_1_1 ) ) )
            // InternalZeroKnowledge.g:2488:1: ( ( rule__Product__RightAssignment_1_1 ) )
            {
            // InternalZeroKnowledge.g:2488:1: ( ( rule__Product__RightAssignment_1_1 ) )
            // InternalZeroKnowledge.g:2489:2: ( rule__Product__RightAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getRightAssignment_1_1()); 
            }
            // InternalZeroKnowledge.g:2490:2: ( rule__Product__RightAssignment_1_1 )
            // InternalZeroKnowledge.g:2490:3: rule__Product__RightAssignment_1_1
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
    // InternalZeroKnowledge.g:2499:1: rule__Product__Group_1_0_0__0 : rule__Product__Group_1_0_0__0__Impl rule__Product__Group_1_0_0__1 ;
    public final void rule__Product__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2503:1: ( rule__Product__Group_1_0_0__0__Impl rule__Product__Group_1_0_0__1 )
            // InternalZeroKnowledge.g:2504:2: rule__Product__Group_1_0_0__0__Impl rule__Product__Group_1_0_0__1
            {
            pushFollow(FOLLOW_30);
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
    // InternalZeroKnowledge.g:2511:1: rule__Product__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Product__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2515:1: ( ( () ) )
            // InternalZeroKnowledge.g:2516:1: ( () )
            {
            // InternalZeroKnowledge.g:2516:1: ( () )
            // InternalZeroKnowledge.g:2517:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getProductLeftAction_1_0_0_0()); 
            }
            // InternalZeroKnowledge.g:2518:2: ()
            // InternalZeroKnowledge.g:2518:3: 
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
    // InternalZeroKnowledge.g:2526:1: rule__Product__Group_1_0_0__1 : rule__Product__Group_1_0_0__1__Impl ;
    public final void rule__Product__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2530:1: ( rule__Product__Group_1_0_0__1__Impl )
            // InternalZeroKnowledge.g:2531:2: rule__Product__Group_1_0_0__1__Impl
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
    // InternalZeroKnowledge.g:2537:1: rule__Product__Group_1_0_0__1__Impl : ( ( rule__Product__OperationAssignment_1_0_0_1 ) ) ;
    public final void rule__Product__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2541:1: ( ( ( rule__Product__OperationAssignment_1_0_0_1 ) ) )
            // InternalZeroKnowledge.g:2542:1: ( ( rule__Product__OperationAssignment_1_0_0_1 ) )
            {
            // InternalZeroKnowledge.g:2542:1: ( ( rule__Product__OperationAssignment_1_0_0_1 ) )
            // InternalZeroKnowledge.g:2543:2: ( rule__Product__OperationAssignment_1_0_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationAssignment_1_0_0_1()); 
            }
            // InternalZeroKnowledge.g:2544:2: ( rule__Product__OperationAssignment_1_0_0_1 )
            // InternalZeroKnowledge.g:2544:3: rule__Product__OperationAssignment_1_0_0_1
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
    // InternalZeroKnowledge.g:2553:1: rule__Product__Group_1_0_1__0 : rule__Product__Group_1_0_1__0__Impl rule__Product__Group_1_0_1__1 ;
    public final void rule__Product__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2557:1: ( rule__Product__Group_1_0_1__0__Impl rule__Product__Group_1_0_1__1 )
            // InternalZeroKnowledge.g:2558:2: rule__Product__Group_1_0_1__0__Impl rule__Product__Group_1_0_1__1
            {
            pushFollow(FOLLOW_28);
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
    // InternalZeroKnowledge.g:2565:1: rule__Product__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Product__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2569:1: ( ( () ) )
            // InternalZeroKnowledge.g:2570:1: ( () )
            {
            // InternalZeroKnowledge.g:2570:1: ( () )
            // InternalZeroKnowledge.g:2571:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getProductLeftAction_1_0_1_0()); 
            }
            // InternalZeroKnowledge.g:2572:2: ()
            // InternalZeroKnowledge.g:2572:3: 
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
    // InternalZeroKnowledge.g:2580:1: rule__Product__Group_1_0_1__1 : rule__Product__Group_1_0_1__1__Impl ;
    public final void rule__Product__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2584:1: ( rule__Product__Group_1_0_1__1__Impl )
            // InternalZeroKnowledge.g:2585:2: rule__Product__Group_1_0_1__1__Impl
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
    // InternalZeroKnowledge.g:2591:1: rule__Product__Group_1_0_1__1__Impl : ( ( rule__Product__OperationAssignment_1_0_1_1 ) ) ;
    public final void rule__Product__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2595:1: ( ( ( rule__Product__OperationAssignment_1_0_1_1 ) ) )
            // InternalZeroKnowledge.g:2596:1: ( ( rule__Product__OperationAssignment_1_0_1_1 ) )
            {
            // InternalZeroKnowledge.g:2596:1: ( ( rule__Product__OperationAssignment_1_0_1_1 ) )
            // InternalZeroKnowledge.g:2597:2: ( rule__Product__OperationAssignment_1_0_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationAssignment_1_0_1_1()); 
            }
            // InternalZeroKnowledge.g:2598:2: ( rule__Product__OperationAssignment_1_0_1_1 )
            // InternalZeroKnowledge.g:2598:3: rule__Product__OperationAssignment_1_0_1_1
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
    // InternalZeroKnowledge.g:2607:1: rule__Power__Group__0 : rule__Power__Group__0__Impl rule__Power__Group__1 ;
    public final void rule__Power__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2611:1: ( rule__Power__Group__0__Impl rule__Power__Group__1 )
            // InternalZeroKnowledge.g:2612:2: rule__Power__Group__0__Impl rule__Power__Group__1
            {
            pushFollow(FOLLOW_31);
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
    // InternalZeroKnowledge.g:2619:1: rule__Power__Group__0__Impl : ( ruleConstruct ) ;
    public final void rule__Power__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2623:1: ( ( ruleConstruct ) )
            // InternalZeroKnowledge.g:2624:1: ( ruleConstruct )
            {
            // InternalZeroKnowledge.g:2624:1: ( ruleConstruct )
            // InternalZeroKnowledge.g:2625:2: ruleConstruct
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
    // InternalZeroKnowledge.g:2634:1: rule__Power__Group__1 : rule__Power__Group__1__Impl ;
    public final void rule__Power__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2638:1: ( rule__Power__Group__1__Impl )
            // InternalZeroKnowledge.g:2639:2: rule__Power__Group__1__Impl
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
    // InternalZeroKnowledge.g:2645:1: rule__Power__Group__1__Impl : ( ( rule__Power__Group_1__0 )? ) ;
    public final void rule__Power__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2649:1: ( ( ( rule__Power__Group_1__0 )? ) )
            // InternalZeroKnowledge.g:2650:1: ( ( rule__Power__Group_1__0 )? )
            {
            // InternalZeroKnowledge.g:2650:1: ( ( rule__Power__Group_1__0 )? )
            // InternalZeroKnowledge.g:2651:2: ( rule__Power__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:2652:2: ( rule__Power__Group_1__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==16) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalZeroKnowledge.g:2652:3: rule__Power__Group_1__0
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
    // InternalZeroKnowledge.g:2661:1: rule__Power__Group_1__0 : rule__Power__Group_1__0__Impl rule__Power__Group_1__1 ;
    public final void rule__Power__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2665:1: ( rule__Power__Group_1__0__Impl rule__Power__Group_1__1 )
            // InternalZeroKnowledge.g:2666:2: rule__Power__Group_1__0__Impl rule__Power__Group_1__1
            {
            pushFollow(FOLLOW_31);
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
    // InternalZeroKnowledge.g:2673:1: rule__Power__Group_1__0__Impl : ( () ) ;
    public final void rule__Power__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2677:1: ( ( () ) )
            // InternalZeroKnowledge.g:2678:1: ( () )
            {
            // InternalZeroKnowledge.g:2678:1: ( () )
            // InternalZeroKnowledge.g:2679:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getPowerLeftAction_1_0()); 
            }
            // InternalZeroKnowledge.g:2680:2: ()
            // InternalZeroKnowledge.g:2680:3: 
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
    // InternalZeroKnowledge.g:2688:1: rule__Power__Group_1__1 : rule__Power__Group_1__1__Impl rule__Power__Group_1__2 ;
    public final void rule__Power__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2692:1: ( rule__Power__Group_1__1__Impl rule__Power__Group_1__2 )
            // InternalZeroKnowledge.g:2693:2: rule__Power__Group_1__1__Impl rule__Power__Group_1__2
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
    // InternalZeroKnowledge.g:2700:1: rule__Power__Group_1__1__Impl : ( '^' ) ;
    public final void rule__Power__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2704:1: ( ( '^' ) )
            // InternalZeroKnowledge.g:2705:1: ( '^' )
            {
            // InternalZeroKnowledge.g:2705:1: ( '^' )
            // InternalZeroKnowledge.g:2706:2: '^'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getCircumflexAccentKeyword_1_1()); 
            }
            match(input,16,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPowerAccess().getCircumflexAccentKeyword_1_1()); 
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
    // InternalZeroKnowledge.g:2715:1: rule__Power__Group_1__2 : rule__Power__Group_1__2__Impl ;
    public final void rule__Power__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2719:1: ( rule__Power__Group_1__2__Impl )
            // InternalZeroKnowledge.g:2720:2: rule__Power__Group_1__2__Impl
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
    // InternalZeroKnowledge.g:2726:1: rule__Power__Group_1__2__Impl : ( ( rule__Power__RightAssignment_1_2 ) ) ;
    public final void rule__Power__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2730:1: ( ( ( rule__Power__RightAssignment_1_2 ) ) )
            // InternalZeroKnowledge.g:2731:1: ( ( rule__Power__RightAssignment_1_2 ) )
            {
            // InternalZeroKnowledge.g:2731:1: ( ( rule__Power__RightAssignment_1_2 ) )
            // InternalZeroKnowledge.g:2732:2: ( rule__Power__RightAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPowerAccess().getRightAssignment_1_2()); 
            }
            // InternalZeroKnowledge.g:2733:2: ( rule__Power__RightAssignment_1_2 )
            // InternalZeroKnowledge.g:2733:3: rule__Power__RightAssignment_1_2
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
    // InternalZeroKnowledge.g:2742:1: rule__Tuple__Group__0 : rule__Tuple__Group__0__Impl rule__Tuple__Group__1 ;
    public final void rule__Tuple__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2746:1: ( rule__Tuple__Group__0__Impl rule__Tuple__Group__1 )
            // InternalZeroKnowledge.g:2747:2: rule__Tuple__Group__0__Impl rule__Tuple__Group__1
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
    // InternalZeroKnowledge.g:2754:1: rule__Tuple__Group__0__Impl : ( ( rule__Tuple__Group_0__0 ) ) ;
    public final void rule__Tuple__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2758:1: ( ( ( rule__Tuple__Group_0__0 ) ) )
            // InternalZeroKnowledge.g:2759:1: ( ( rule__Tuple__Group_0__0 ) )
            {
            // InternalZeroKnowledge.g:2759:1: ( ( rule__Tuple__Group_0__0 ) )
            // InternalZeroKnowledge.g:2760:2: ( rule__Tuple__Group_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getGroup_0()); 
            }
            // InternalZeroKnowledge.g:2761:2: ( rule__Tuple__Group_0__0 )
            // InternalZeroKnowledge.g:2761:3: rule__Tuple__Group_0__0
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
    // InternalZeroKnowledge.g:2769:1: rule__Tuple__Group__1 : rule__Tuple__Group__1__Impl rule__Tuple__Group__2 ;
    public final void rule__Tuple__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2773:1: ( rule__Tuple__Group__1__Impl rule__Tuple__Group__2 )
            // InternalZeroKnowledge.g:2774:2: rule__Tuple__Group__1__Impl rule__Tuple__Group__2
            {
            pushFollow(FOLLOW_32);
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
    // InternalZeroKnowledge.g:2781:1: rule__Tuple__Group__1__Impl : ( ( rule__Tuple__ElementsAssignment_1 ) ) ;
    public final void rule__Tuple__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2785:1: ( ( ( rule__Tuple__ElementsAssignment_1 ) ) )
            // InternalZeroKnowledge.g:2786:1: ( ( rule__Tuple__ElementsAssignment_1 ) )
            {
            // InternalZeroKnowledge.g:2786:1: ( ( rule__Tuple__ElementsAssignment_1 ) )
            // InternalZeroKnowledge.g:2787:2: ( rule__Tuple__ElementsAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getElementsAssignment_1()); 
            }
            // InternalZeroKnowledge.g:2788:2: ( rule__Tuple__ElementsAssignment_1 )
            // InternalZeroKnowledge.g:2788:3: rule__Tuple__ElementsAssignment_1
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
    // InternalZeroKnowledge.g:2796:1: rule__Tuple__Group__2 : rule__Tuple__Group__2__Impl rule__Tuple__Group__3 ;
    public final void rule__Tuple__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2800:1: ( rule__Tuple__Group__2__Impl rule__Tuple__Group__3 )
            // InternalZeroKnowledge.g:2801:2: rule__Tuple__Group__2__Impl rule__Tuple__Group__3
            {
            pushFollow(FOLLOW_32);
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
    // InternalZeroKnowledge.g:2808:1: rule__Tuple__Group__2__Impl : ( ( rule__Tuple__Group_2__0 )* ) ;
    public final void rule__Tuple__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2812:1: ( ( ( rule__Tuple__Group_2__0 )* ) )
            // InternalZeroKnowledge.g:2813:1: ( ( rule__Tuple__Group_2__0 )* )
            {
            // InternalZeroKnowledge.g:2813:1: ( ( rule__Tuple__Group_2__0 )* )
            // InternalZeroKnowledge.g:2814:2: ( rule__Tuple__Group_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getGroup_2()); 
            }
            // InternalZeroKnowledge.g:2815:2: ( rule__Tuple__Group_2__0 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==15) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalZeroKnowledge.g:2815:3: rule__Tuple__Group_2__0
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
    // InternalZeroKnowledge.g:2823:1: rule__Tuple__Group__3 : rule__Tuple__Group__3__Impl ;
    public final void rule__Tuple__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2827:1: ( rule__Tuple__Group__3__Impl )
            // InternalZeroKnowledge.g:2828:2: rule__Tuple__Group__3__Impl
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
    // InternalZeroKnowledge.g:2834:1: rule__Tuple__Group__3__Impl : ( ')' ) ;
    public final void rule__Tuple__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2838:1: ( ( ')' ) )
            // InternalZeroKnowledge.g:2839:1: ( ')' )
            {
            // InternalZeroKnowledge.g:2839:1: ( ')' )
            // InternalZeroKnowledge.g:2840:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getRightParenthesisKeyword_3()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:2850:1: rule__Tuple__Group_0__0 : rule__Tuple__Group_0__0__Impl ;
    public final void rule__Tuple__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2854:1: ( rule__Tuple__Group_0__0__Impl )
            // InternalZeroKnowledge.g:2855:2: rule__Tuple__Group_0__0__Impl
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
    // InternalZeroKnowledge.g:2861:1: rule__Tuple__Group_0__0__Impl : ( ( rule__Tuple__Group_0_0__0 ) ) ;
    public final void rule__Tuple__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2865:1: ( ( ( rule__Tuple__Group_0_0__0 ) ) )
            // InternalZeroKnowledge.g:2866:1: ( ( rule__Tuple__Group_0_0__0 ) )
            {
            // InternalZeroKnowledge.g:2866:1: ( ( rule__Tuple__Group_0_0__0 ) )
            // InternalZeroKnowledge.g:2867:2: ( rule__Tuple__Group_0_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getGroup_0_0()); 
            }
            // InternalZeroKnowledge.g:2868:2: ( rule__Tuple__Group_0_0__0 )
            // InternalZeroKnowledge.g:2868:3: rule__Tuple__Group_0_0__0
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
    // InternalZeroKnowledge.g:2877:1: rule__Tuple__Group_0_0__0 : rule__Tuple__Group_0_0__0__Impl rule__Tuple__Group_0_0__1 ;
    public final void rule__Tuple__Group_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2881:1: ( rule__Tuple__Group_0_0__0__Impl rule__Tuple__Group_0_0__1 )
            // InternalZeroKnowledge.g:2882:2: rule__Tuple__Group_0_0__0__Impl rule__Tuple__Group_0_0__1
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
    // InternalZeroKnowledge.g:2889:1: rule__Tuple__Group_0_0__0__Impl : ( () ) ;
    public final void rule__Tuple__Group_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2893:1: ( ( () ) )
            // InternalZeroKnowledge.g:2894:1: ( () )
            {
            // InternalZeroKnowledge.g:2894:1: ( () )
            // InternalZeroKnowledge.g:2895:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getTupleAction_0_0_0()); 
            }
            // InternalZeroKnowledge.g:2896:2: ()
            // InternalZeroKnowledge.g:2896:3: 
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
    // InternalZeroKnowledge.g:2904:1: rule__Tuple__Group_0_0__1 : rule__Tuple__Group_0_0__1__Impl rule__Tuple__Group_0_0__2 ;
    public final void rule__Tuple__Group_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2908:1: ( rule__Tuple__Group_0_0__1__Impl rule__Tuple__Group_0_0__2 )
            // InternalZeroKnowledge.g:2909:2: rule__Tuple__Group_0_0__1__Impl rule__Tuple__Group_0_0__2
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
    // InternalZeroKnowledge.g:2916:1: rule__Tuple__Group_0_0__1__Impl : ( '(' ) ;
    public final void rule__Tuple__Group_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2920:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:2921:1: ( '(' )
            {
            // InternalZeroKnowledge.g:2921:1: ( '(' )
            // InternalZeroKnowledge.g:2922:2: '('
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
    // InternalZeroKnowledge.g:2931:1: rule__Tuple__Group_0_0__2 : rule__Tuple__Group_0_0__2__Impl rule__Tuple__Group_0_0__3 ;
    public final void rule__Tuple__Group_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2935:1: ( rule__Tuple__Group_0_0__2__Impl rule__Tuple__Group_0_0__3 )
            // InternalZeroKnowledge.g:2936:2: rule__Tuple__Group_0_0__2__Impl rule__Tuple__Group_0_0__3
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
    // InternalZeroKnowledge.g:2943:1: rule__Tuple__Group_0_0__2__Impl : ( ( rule__Tuple__ElementsAssignment_0_0_2 ) ) ;
    public final void rule__Tuple__Group_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2947:1: ( ( ( rule__Tuple__ElementsAssignment_0_0_2 ) ) )
            // InternalZeroKnowledge.g:2948:1: ( ( rule__Tuple__ElementsAssignment_0_0_2 ) )
            {
            // InternalZeroKnowledge.g:2948:1: ( ( rule__Tuple__ElementsAssignment_0_0_2 ) )
            // InternalZeroKnowledge.g:2949:2: ( rule__Tuple__ElementsAssignment_0_0_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getElementsAssignment_0_0_2()); 
            }
            // InternalZeroKnowledge.g:2950:2: ( rule__Tuple__ElementsAssignment_0_0_2 )
            // InternalZeroKnowledge.g:2950:3: rule__Tuple__ElementsAssignment_0_0_2
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
    // InternalZeroKnowledge.g:2958:1: rule__Tuple__Group_0_0__3 : rule__Tuple__Group_0_0__3__Impl ;
    public final void rule__Tuple__Group_0_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2962:1: ( rule__Tuple__Group_0_0__3__Impl )
            // InternalZeroKnowledge.g:2963:2: rule__Tuple__Group_0_0__3__Impl
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
    // InternalZeroKnowledge.g:2969:1: rule__Tuple__Group_0_0__3__Impl : ( ',' ) ;
    public final void rule__Tuple__Group_0_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2973:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:2974:1: ( ',' )
            {
            // InternalZeroKnowledge.g:2974:1: ( ',' )
            // InternalZeroKnowledge.g:2975:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getCommaKeyword_0_0_3()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:2985:1: rule__Tuple__Group_2__0 : rule__Tuple__Group_2__0__Impl rule__Tuple__Group_2__1 ;
    public final void rule__Tuple__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:2989:1: ( rule__Tuple__Group_2__0__Impl rule__Tuple__Group_2__1 )
            // InternalZeroKnowledge.g:2990:2: rule__Tuple__Group_2__0__Impl rule__Tuple__Group_2__1
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
    // InternalZeroKnowledge.g:2997:1: rule__Tuple__Group_2__0__Impl : ( ',' ) ;
    public final void rule__Tuple__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3001:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:3002:1: ( ',' )
            {
            // InternalZeroKnowledge.g:3002:1: ( ',' )
            // InternalZeroKnowledge.g:3003:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getCommaKeyword_2_0()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3012:1: rule__Tuple__Group_2__1 : rule__Tuple__Group_2__1__Impl ;
    public final void rule__Tuple__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3016:1: ( rule__Tuple__Group_2__1__Impl )
            // InternalZeroKnowledge.g:3017:2: rule__Tuple__Group_2__1__Impl
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
    // InternalZeroKnowledge.g:3023:1: rule__Tuple__Group_2__1__Impl : ( ( rule__Tuple__ElementsAssignment_2_1 ) ) ;
    public final void rule__Tuple__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3027:1: ( ( ( rule__Tuple__ElementsAssignment_2_1 ) ) )
            // InternalZeroKnowledge.g:3028:1: ( ( rule__Tuple__ElementsAssignment_2_1 ) )
            {
            // InternalZeroKnowledge.g:3028:1: ( ( rule__Tuple__ElementsAssignment_2_1 ) )
            // InternalZeroKnowledge.g:3029:2: ( rule__Tuple__ElementsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTupleAccess().getElementsAssignment_2_1()); 
            }
            // InternalZeroKnowledge.g:3030:2: ( rule__Tuple__ElementsAssignment_2_1 )
            // InternalZeroKnowledge.g:3030:3: rule__Tuple__ElementsAssignment_2_1
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
    // InternalZeroKnowledge.g:3039:1: rule__Negative__Group_0__0 : rule__Negative__Group_0__0__Impl rule__Negative__Group_0__1 ;
    public final void rule__Negative__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3043:1: ( rule__Negative__Group_0__0__Impl rule__Negative__Group_0__1 )
            // InternalZeroKnowledge.g:3044:2: rule__Negative__Group_0__0__Impl rule__Negative__Group_0__1
            {
            pushFollow(FOLLOW_33);
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
    // InternalZeroKnowledge.g:3051:1: rule__Negative__Group_0__0__Impl : ( () ) ;
    public final void rule__Negative__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3055:1: ( ( () ) )
            // InternalZeroKnowledge.g:3056:1: ( () )
            {
            // InternalZeroKnowledge.g:3056:1: ( () )
            // InternalZeroKnowledge.g:3057:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getNegativeAction_0_0()); 
            }
            // InternalZeroKnowledge.g:3058:2: ()
            // InternalZeroKnowledge.g:3058:3: 
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
    // InternalZeroKnowledge.g:3066:1: rule__Negative__Group_0__1 : rule__Negative__Group_0__1__Impl rule__Negative__Group_0__2 ;
    public final void rule__Negative__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3070:1: ( rule__Negative__Group_0__1__Impl rule__Negative__Group_0__2 )
            // InternalZeroKnowledge.g:3071:2: rule__Negative__Group_0__1__Impl rule__Negative__Group_0__2
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
    // InternalZeroKnowledge.g:3078:1: rule__Negative__Group_0__1__Impl : ( '-' ) ;
    public final void rule__Negative__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3082:1: ( ( '-' ) )
            // InternalZeroKnowledge.g:3083:1: ( '-' )
            {
            // InternalZeroKnowledge.g:3083:1: ( '-' )
            // InternalZeroKnowledge.g:3084:2: '-'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getHyphenMinusKeyword_0_1()); 
            }
            match(input,17,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNegativeAccess().getHyphenMinusKeyword_0_1()); 
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
    // InternalZeroKnowledge.g:3093:1: rule__Negative__Group_0__2 : rule__Negative__Group_0__2__Impl ;
    public final void rule__Negative__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3097:1: ( rule__Negative__Group_0__2__Impl )
            // InternalZeroKnowledge.g:3098:2: rule__Negative__Group_0__2__Impl
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
    // InternalZeroKnowledge.g:3104:1: rule__Negative__Group_0__2__Impl : ( ( rule__Negative__TermAssignment_0_2 ) ) ;
    public final void rule__Negative__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3108:1: ( ( ( rule__Negative__TermAssignment_0_2 ) ) )
            // InternalZeroKnowledge.g:3109:1: ( ( rule__Negative__TermAssignment_0_2 ) )
            {
            // InternalZeroKnowledge.g:3109:1: ( ( rule__Negative__TermAssignment_0_2 ) )
            // InternalZeroKnowledge.g:3110:2: ( rule__Negative__TermAssignment_0_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNegativeAccess().getTermAssignment_0_2()); 
            }
            // InternalZeroKnowledge.g:3111:2: ( rule__Negative__TermAssignment_0_2 )
            // InternalZeroKnowledge.g:3111:3: rule__Negative__TermAssignment_0_2
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
    // InternalZeroKnowledge.g:3120:1: rule__Value__Group_3__0 : rule__Value__Group_3__0__Impl rule__Value__Group_3__1 ;
    public final void rule__Value__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3124:1: ( rule__Value__Group_3__0__Impl rule__Value__Group_3__1 )
            // InternalZeroKnowledge.g:3125:2: rule__Value__Group_3__0__Impl rule__Value__Group_3__1
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
    // InternalZeroKnowledge.g:3132:1: rule__Value__Group_3__0__Impl : ( '(' ) ;
    public final void rule__Value__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3136:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:3137:1: ( '(' )
            {
            // InternalZeroKnowledge.g:3137:1: ( '(' )
            // InternalZeroKnowledge.g:3138:2: '('
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
    // InternalZeroKnowledge.g:3147:1: rule__Value__Group_3__1 : rule__Value__Group_3__1__Impl rule__Value__Group_3__2 ;
    public final void rule__Value__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3151:1: ( rule__Value__Group_3__1__Impl rule__Value__Group_3__2 )
            // InternalZeroKnowledge.g:3152:2: rule__Value__Group_3__1__Impl rule__Value__Group_3__2
            {
            pushFollow(FOLLOW_34);
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
    // InternalZeroKnowledge.g:3159:1: rule__Value__Group_3__1__Impl : ( ruleBrackets ) ;
    public final void rule__Value__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3163:1: ( ( ruleBrackets ) )
            // InternalZeroKnowledge.g:3164:1: ( ruleBrackets )
            {
            // InternalZeroKnowledge.g:3164:1: ( ruleBrackets )
            // InternalZeroKnowledge.g:3165:2: ruleBrackets
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
    // InternalZeroKnowledge.g:3174:1: rule__Value__Group_3__2 : rule__Value__Group_3__2__Impl ;
    public final void rule__Value__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3178:1: ( rule__Value__Group_3__2__Impl )
            // InternalZeroKnowledge.g:3179:2: rule__Value__Group_3__2__Impl
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
    // InternalZeroKnowledge.g:3185:1: rule__Value__Group_3__2__Impl : ( ')' ) ;
    public final void rule__Value__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3189:1: ( ( ')' ) )
            // InternalZeroKnowledge.g:3190:1: ( ')' )
            {
            // InternalZeroKnowledge.g:3190:1: ( ')' )
            // InternalZeroKnowledge.g:3191:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueAccess().getRightParenthesisKeyword_3_2()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3201:1: rule__FunctionCall__Group__0 : rule__FunctionCall__Group__0__Impl rule__FunctionCall__Group__1 ;
    public final void rule__FunctionCall__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3205:1: ( rule__FunctionCall__Group__0__Impl rule__FunctionCall__Group__1 )
            // InternalZeroKnowledge.g:3206:2: rule__FunctionCall__Group__0__Impl rule__FunctionCall__Group__1
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
    // InternalZeroKnowledge.g:3213:1: rule__FunctionCall__Group__0__Impl : ( () ) ;
    public final void rule__FunctionCall__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3217:1: ( ( () ) )
            // InternalZeroKnowledge.g:3218:1: ( () )
            {
            // InternalZeroKnowledge.g:3218:1: ( () )
            // InternalZeroKnowledge.g:3219:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getFunctionCallAction_0()); 
            }
            // InternalZeroKnowledge.g:3220:2: ()
            // InternalZeroKnowledge.g:3220:3: 
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
    // InternalZeroKnowledge.g:3228:1: rule__FunctionCall__Group__1 : rule__FunctionCall__Group__1__Impl ;
    public final void rule__FunctionCall__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3232:1: ( rule__FunctionCall__Group__1__Impl )
            // InternalZeroKnowledge.g:3233:2: rule__FunctionCall__Group__1__Impl
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
    // InternalZeroKnowledge.g:3239:1: rule__FunctionCall__Group__1__Impl : ( ( rule__FunctionCall__Group_1__0 ) ) ;
    public final void rule__FunctionCall__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3243:1: ( ( ( rule__FunctionCall__Group_1__0 ) ) )
            // InternalZeroKnowledge.g:3244:1: ( ( rule__FunctionCall__Group_1__0 ) )
            {
            // InternalZeroKnowledge.g:3244:1: ( ( rule__FunctionCall__Group_1__0 ) )
            // InternalZeroKnowledge.g:3245:2: ( rule__FunctionCall__Group_1__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1()); 
            }
            // InternalZeroKnowledge.g:3246:2: ( rule__FunctionCall__Group_1__0 )
            // InternalZeroKnowledge.g:3246:3: rule__FunctionCall__Group_1__0
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
    // InternalZeroKnowledge.g:3255:1: rule__FunctionCall__Group_1__0 : rule__FunctionCall__Group_1__0__Impl rule__FunctionCall__Group_1__1 ;
    public final void rule__FunctionCall__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3259:1: ( rule__FunctionCall__Group_1__0__Impl rule__FunctionCall__Group_1__1 )
            // InternalZeroKnowledge.g:3260:2: rule__FunctionCall__Group_1__0__Impl rule__FunctionCall__Group_1__1
            {
            pushFollow(FOLLOW_35);
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
    // InternalZeroKnowledge.g:3267:1: rule__FunctionCall__Group_1__0__Impl : ( ( rule__FunctionCall__Group_1_0__0 ) ) ;
    public final void rule__FunctionCall__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3271:1: ( ( ( rule__FunctionCall__Group_1_0__0 ) ) )
            // InternalZeroKnowledge.g:3272:1: ( ( rule__FunctionCall__Group_1_0__0 ) )
            {
            // InternalZeroKnowledge.g:3272:1: ( ( rule__FunctionCall__Group_1_0__0 ) )
            // InternalZeroKnowledge.g:3273:2: ( rule__FunctionCall__Group_1_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1_0()); 
            }
            // InternalZeroKnowledge.g:3274:2: ( rule__FunctionCall__Group_1_0__0 )
            // InternalZeroKnowledge.g:3274:3: rule__FunctionCall__Group_1_0__0
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
    // InternalZeroKnowledge.g:3282:1: rule__FunctionCall__Group_1__1 : rule__FunctionCall__Group_1__1__Impl rule__FunctionCall__Group_1__2 ;
    public final void rule__FunctionCall__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3286:1: ( rule__FunctionCall__Group_1__1__Impl rule__FunctionCall__Group_1__2 )
            // InternalZeroKnowledge.g:3287:2: rule__FunctionCall__Group_1__1__Impl rule__FunctionCall__Group_1__2
            {
            pushFollow(FOLLOW_35);
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
    // InternalZeroKnowledge.g:3294:1: rule__FunctionCall__Group_1__1__Impl : ( ( rule__FunctionCall__Group_1_1__0 )? ) ;
    public final void rule__FunctionCall__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3298:1: ( ( ( rule__FunctionCall__Group_1_1__0 )? ) )
            // InternalZeroKnowledge.g:3299:1: ( ( rule__FunctionCall__Group_1_1__0 )? )
            {
            // InternalZeroKnowledge.g:3299:1: ( ( rule__FunctionCall__Group_1_1__0 )? )
            // InternalZeroKnowledge.g:3300:2: ( rule__FunctionCall__Group_1_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1_1()); 
            }
            // InternalZeroKnowledge.g:3301:2: ( rule__FunctionCall__Group_1_1__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=RULE_IDENTIFIER && LA23_0<=RULE_INT)||LA23_0==13||LA23_0==17) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalZeroKnowledge.g:3301:3: rule__FunctionCall__Group_1_1__0
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
    // InternalZeroKnowledge.g:3309:1: rule__FunctionCall__Group_1__2 : rule__FunctionCall__Group_1__2__Impl ;
    public final void rule__FunctionCall__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3313:1: ( rule__FunctionCall__Group_1__2__Impl )
            // InternalZeroKnowledge.g:3314:2: rule__FunctionCall__Group_1__2__Impl
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
    // InternalZeroKnowledge.g:3320:1: rule__FunctionCall__Group_1__2__Impl : ( ')' ) ;
    public final void rule__FunctionCall__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3324:1: ( ( ')' ) )
            // InternalZeroKnowledge.g:3325:1: ( ')' )
            {
            // InternalZeroKnowledge.g:3325:1: ( ')' )
            // InternalZeroKnowledge.g:3326:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getRightParenthesisKeyword_1_2()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3336:1: rule__FunctionCall__Group_1_0__0 : rule__FunctionCall__Group_1_0__0__Impl ;
    public final void rule__FunctionCall__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3340:1: ( rule__FunctionCall__Group_1_0__0__Impl )
            // InternalZeroKnowledge.g:3341:2: rule__FunctionCall__Group_1_0__0__Impl
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
    // InternalZeroKnowledge.g:3347:1: rule__FunctionCall__Group_1_0__0__Impl : ( ( rule__FunctionCall__Group_1_0_0__0 ) ) ;
    public final void rule__FunctionCall__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3351:1: ( ( ( rule__FunctionCall__Group_1_0_0__0 ) ) )
            // InternalZeroKnowledge.g:3352:1: ( ( rule__FunctionCall__Group_1_0_0__0 ) )
            {
            // InternalZeroKnowledge.g:3352:1: ( ( rule__FunctionCall__Group_1_0_0__0 ) )
            // InternalZeroKnowledge.g:3353:2: ( rule__FunctionCall__Group_1_0_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1_0_0()); 
            }
            // InternalZeroKnowledge.g:3354:2: ( rule__FunctionCall__Group_1_0_0__0 )
            // InternalZeroKnowledge.g:3354:3: rule__FunctionCall__Group_1_0_0__0
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
    // InternalZeroKnowledge.g:3363:1: rule__FunctionCall__Group_1_0_0__0 : rule__FunctionCall__Group_1_0_0__0__Impl rule__FunctionCall__Group_1_0_0__1 ;
    public final void rule__FunctionCall__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3367:1: ( rule__FunctionCall__Group_1_0_0__0__Impl rule__FunctionCall__Group_1_0_0__1 )
            // InternalZeroKnowledge.g:3368:2: rule__FunctionCall__Group_1_0_0__0__Impl rule__FunctionCall__Group_1_0_0__1
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
    // InternalZeroKnowledge.g:3375:1: rule__FunctionCall__Group_1_0_0__0__Impl : ( ( rule__FunctionCall__NameAssignment_1_0_0_0 ) ) ;
    public final void rule__FunctionCall__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3379:1: ( ( ( rule__FunctionCall__NameAssignment_1_0_0_0 ) ) )
            // InternalZeroKnowledge.g:3380:1: ( ( rule__FunctionCall__NameAssignment_1_0_0_0 ) )
            {
            // InternalZeroKnowledge.g:3380:1: ( ( rule__FunctionCall__NameAssignment_1_0_0_0 ) )
            // InternalZeroKnowledge.g:3381:2: ( rule__FunctionCall__NameAssignment_1_0_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getNameAssignment_1_0_0_0()); 
            }
            // InternalZeroKnowledge.g:3382:2: ( rule__FunctionCall__NameAssignment_1_0_0_0 )
            // InternalZeroKnowledge.g:3382:3: rule__FunctionCall__NameAssignment_1_0_0_0
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
    // InternalZeroKnowledge.g:3390:1: rule__FunctionCall__Group_1_0_0__1 : rule__FunctionCall__Group_1_0_0__1__Impl ;
    public final void rule__FunctionCall__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3394:1: ( rule__FunctionCall__Group_1_0_0__1__Impl )
            // InternalZeroKnowledge.g:3395:2: rule__FunctionCall__Group_1_0_0__1__Impl
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
    // InternalZeroKnowledge.g:3401:1: rule__FunctionCall__Group_1_0_0__1__Impl : ( '(' ) ;
    public final void rule__FunctionCall__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3405:1: ( ( '(' ) )
            // InternalZeroKnowledge.g:3406:1: ( '(' )
            {
            // InternalZeroKnowledge.g:3406:1: ( '(' )
            // InternalZeroKnowledge.g:3407:2: '('
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
    // InternalZeroKnowledge.g:3417:1: rule__FunctionCall__Group_1_1__0 : rule__FunctionCall__Group_1_1__0__Impl rule__FunctionCall__Group_1_1__1 ;
    public final void rule__FunctionCall__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3421:1: ( rule__FunctionCall__Group_1_1__0__Impl rule__FunctionCall__Group_1_1__1 )
            // InternalZeroKnowledge.g:3422:2: rule__FunctionCall__Group_1_1__0__Impl rule__FunctionCall__Group_1_1__1
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
    // InternalZeroKnowledge.g:3429:1: rule__FunctionCall__Group_1_1__0__Impl : ( ( rule__FunctionCall__ArgumentsAssignment_1_1_0 ) ) ;
    public final void rule__FunctionCall__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3433:1: ( ( ( rule__FunctionCall__ArgumentsAssignment_1_1_0 ) ) )
            // InternalZeroKnowledge.g:3434:1: ( ( rule__FunctionCall__ArgumentsAssignment_1_1_0 ) )
            {
            // InternalZeroKnowledge.g:3434:1: ( ( rule__FunctionCall__ArgumentsAssignment_1_1_0 ) )
            // InternalZeroKnowledge.g:3435:2: ( rule__FunctionCall__ArgumentsAssignment_1_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getArgumentsAssignment_1_1_0()); 
            }
            // InternalZeroKnowledge.g:3436:2: ( rule__FunctionCall__ArgumentsAssignment_1_1_0 )
            // InternalZeroKnowledge.g:3436:3: rule__FunctionCall__ArgumentsAssignment_1_1_0
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
    // InternalZeroKnowledge.g:3444:1: rule__FunctionCall__Group_1_1__1 : rule__FunctionCall__Group_1_1__1__Impl ;
    public final void rule__FunctionCall__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3448:1: ( rule__FunctionCall__Group_1_1__1__Impl )
            // InternalZeroKnowledge.g:3449:2: rule__FunctionCall__Group_1_1__1__Impl
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
    // InternalZeroKnowledge.g:3455:1: rule__FunctionCall__Group_1_1__1__Impl : ( ( rule__FunctionCall__Group_1_1_1__0 )* ) ;
    public final void rule__FunctionCall__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3459:1: ( ( ( rule__FunctionCall__Group_1_1_1__0 )* ) )
            // InternalZeroKnowledge.g:3460:1: ( ( rule__FunctionCall__Group_1_1_1__0 )* )
            {
            // InternalZeroKnowledge.g:3460:1: ( ( rule__FunctionCall__Group_1_1_1__0 )* )
            // InternalZeroKnowledge.g:3461:2: ( rule__FunctionCall__Group_1_1_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getGroup_1_1_1()); 
            }
            // InternalZeroKnowledge.g:3462:2: ( rule__FunctionCall__Group_1_1_1__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==15) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalZeroKnowledge.g:3462:3: rule__FunctionCall__Group_1_1_1__0
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
    // InternalZeroKnowledge.g:3471:1: rule__FunctionCall__Group_1_1_1__0 : rule__FunctionCall__Group_1_1_1__0__Impl rule__FunctionCall__Group_1_1_1__1 ;
    public final void rule__FunctionCall__Group_1_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3475:1: ( rule__FunctionCall__Group_1_1_1__0__Impl rule__FunctionCall__Group_1_1_1__1 )
            // InternalZeroKnowledge.g:3476:2: rule__FunctionCall__Group_1_1_1__0__Impl rule__FunctionCall__Group_1_1_1__1
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
    // InternalZeroKnowledge.g:3483:1: rule__FunctionCall__Group_1_1_1__0__Impl : ( ',' ) ;
    public final void rule__FunctionCall__Group_1_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3487:1: ( ( ',' ) )
            // InternalZeroKnowledge.g:3488:1: ( ',' )
            {
            // InternalZeroKnowledge.g:3488:1: ( ',' )
            // InternalZeroKnowledge.g:3489:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getCommaKeyword_1_1_1_0()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3498:1: rule__FunctionCall__Group_1_1_1__1 : rule__FunctionCall__Group_1_1_1__1__Impl ;
    public final void rule__FunctionCall__Group_1_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3502:1: ( rule__FunctionCall__Group_1_1_1__1__Impl )
            // InternalZeroKnowledge.g:3503:2: rule__FunctionCall__Group_1_1_1__1__Impl
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
    // InternalZeroKnowledge.g:3509:1: rule__FunctionCall__Group_1_1_1__1__Impl : ( ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 ) ) ;
    public final void rule__FunctionCall__Group_1_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3513:1: ( ( ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 ) ) )
            // InternalZeroKnowledge.g:3514:1: ( ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 ) )
            {
            // InternalZeroKnowledge.g:3514:1: ( ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 ) )
            // InternalZeroKnowledge.g:3515:2: ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getArgumentsAssignment_1_1_1_1()); 
            }
            // InternalZeroKnowledge.g:3516:2: ( rule__FunctionCall__ArgumentsAssignment_1_1_1_1 )
            // InternalZeroKnowledge.g:3516:3: rule__FunctionCall__ArgumentsAssignment_1_1_1_1
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


    // $ANTLR start "rule__Brackets__Group__0"
    // InternalZeroKnowledge.g:3525:1: rule__Brackets__Group__0 : rule__Brackets__Group__0__Impl rule__Brackets__Group__1 ;
    public final void rule__Brackets__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3529:1: ( rule__Brackets__Group__0__Impl rule__Brackets__Group__1 )
            // InternalZeroKnowledge.g:3530:2: rule__Brackets__Group__0__Impl rule__Brackets__Group__1
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
    // InternalZeroKnowledge.g:3537:1: rule__Brackets__Group__0__Impl : ( () ) ;
    public final void rule__Brackets__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3541:1: ( ( () ) )
            // InternalZeroKnowledge.g:3542:1: ( () )
            {
            // InternalZeroKnowledge.g:3542:1: ( () )
            // InternalZeroKnowledge.g:3543:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBracketsAccess().getBracketsAction_0()); 
            }
            // InternalZeroKnowledge.g:3544:2: ()
            // InternalZeroKnowledge.g:3544:3: 
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
    // InternalZeroKnowledge.g:3552:1: rule__Brackets__Group__1 : rule__Brackets__Group__1__Impl ;
    public final void rule__Brackets__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3556:1: ( rule__Brackets__Group__1__Impl )
            // InternalZeroKnowledge.g:3557:2: rule__Brackets__Group__1__Impl
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
    // InternalZeroKnowledge.g:3563:1: rule__Brackets__Group__1__Impl : ( ( rule__Brackets__ContentAssignment_1 ) ) ;
    public final void rule__Brackets__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3567:1: ( ( ( rule__Brackets__ContentAssignment_1 ) ) )
            // InternalZeroKnowledge.g:3568:1: ( ( rule__Brackets__ContentAssignment_1 ) )
            {
            // InternalZeroKnowledge.g:3568:1: ( ( rule__Brackets__ContentAssignment_1 ) )
            // InternalZeroKnowledge.g:3569:2: ( rule__Brackets__ContentAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBracketsAccess().getContentAssignment_1()); 
            }
            // InternalZeroKnowledge.g:3570:2: ( rule__Brackets__ContentAssignment_1 )
            // InternalZeroKnowledge.g:3570:3: rule__Brackets__ContentAssignment_1
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
    // InternalZeroKnowledge.g:3579:1: rule__Model__FunctionsAssignment_0 : ( ruleFunctionDefinition ) ;
    public final void rule__Model__FunctionsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3583:1: ( ( ruleFunctionDefinition ) )
            // InternalZeroKnowledge.g:3584:2: ( ruleFunctionDefinition )
            {
            // InternalZeroKnowledge.g:3584:2: ( ruleFunctionDefinition )
            // InternalZeroKnowledge.g:3585:3: ruleFunctionDefinition
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
    // InternalZeroKnowledge.g:3594:1: rule__Model__WitnessListAssignment_1 : ( ruleWitnessList ) ;
    public final void rule__Model__WitnessListAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3598:1: ( ( ruleWitnessList ) )
            // InternalZeroKnowledge.g:3599:2: ( ruleWitnessList )
            {
            // InternalZeroKnowledge.g:3599:2: ( ruleWitnessList )
            // InternalZeroKnowledge.g:3600:3: ruleWitnessList
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
    // InternalZeroKnowledge.g:3609:1: rule__Model__ProofAssignment_3 : ( ruleExpression ) ;
    public final void rule__Model__ProofAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3613:1: ( ( ruleExpression ) )
            // InternalZeroKnowledge.g:3614:2: ( ruleExpression )
            {
            // InternalZeroKnowledge.g:3614:2: ( ruleExpression )
            // InternalZeroKnowledge.g:3615:3: ruleExpression
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
    // InternalZeroKnowledge.g:3624:1: rule__FunctionDefinition__NameAssignment_0 : ( RULE_IDENTIFIER ) ;
    public final void rule__FunctionDefinition__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3628:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:3629:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:3629:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:3630:3: RULE_IDENTIFIER
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
    // InternalZeroKnowledge.g:3639:1: rule__FunctionDefinition__ParameterListAssignment_1 : ( ruleParameterList ) ;
    public final void rule__FunctionDefinition__ParameterListAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3643:1: ( ( ruleParameterList ) )
            // InternalZeroKnowledge.g:3644:2: ( ruleParameterList )
            {
            // InternalZeroKnowledge.g:3644:2: ( ruleParameterList )
            // InternalZeroKnowledge.g:3645:3: ruleParameterList
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
    // InternalZeroKnowledge.g:3654:1: rule__FunctionDefinition__BodyAssignment_3 : ( ruleExpression ) ;
    public final void rule__FunctionDefinition__BodyAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3658:1: ( ( ruleExpression ) )
            // InternalZeroKnowledge.g:3659:2: ( ruleExpression )
            {
            // InternalZeroKnowledge.g:3659:2: ( ruleExpression )
            // InternalZeroKnowledge.g:3660:3: ruleExpression
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
    // InternalZeroKnowledge.g:3669:1: rule__ParameterList__ParametersAssignment_1_0 : ( ruleParameter ) ;
    public final void rule__ParameterList__ParametersAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3673:1: ( ( ruleParameter ) )
            // InternalZeroKnowledge.g:3674:2: ( ruleParameter )
            {
            // InternalZeroKnowledge.g:3674:2: ( ruleParameter )
            // InternalZeroKnowledge.g:3675:3: ruleParameter
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
    // InternalZeroKnowledge.g:3684:1: rule__ParameterList__ParametersAssignment_1_1_1 : ( ruleParameter ) ;
    public final void rule__ParameterList__ParametersAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3688:1: ( ( ruleParameter ) )
            // InternalZeroKnowledge.g:3689:2: ( ruleParameter )
            {
            // InternalZeroKnowledge.g:3689:2: ( ruleParameter )
            // InternalZeroKnowledge.g:3690:3: ruleParameter
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


    // $ANTLR start "rule__Parameter__NameAssignment"
    // InternalZeroKnowledge.g:3699:1: rule__Parameter__NameAssignment : ( RULE_IDENTIFIER ) ;
    public final void rule__Parameter__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3703:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:3704:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:3704:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:3705:3: RULE_IDENTIFIER
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


    // $ANTLR start "rule__WitnessList__WitnessesAssignment_1"
    // InternalZeroKnowledge.g:3714:1: rule__WitnessList__WitnessesAssignment_1 : ( ruleWitness ) ;
    public final void rule__WitnessList__WitnessesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3718:1: ( ( ruleWitness ) )
            // InternalZeroKnowledge.g:3719:2: ( ruleWitness )
            {
            // InternalZeroKnowledge.g:3719:2: ( ruleWitness )
            // InternalZeroKnowledge.g:3720:3: ruleWitness
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleWitness();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__WitnessList__WitnessesAssignment_1"


    // $ANTLR start "rule__WitnessList__WitnessesAssignment_2_1"
    // InternalZeroKnowledge.g:3729:1: rule__WitnessList__WitnessesAssignment_2_1 : ( ruleWitness ) ;
    public final void rule__WitnessList__WitnessesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3733:1: ( ( ruleWitness ) )
            // InternalZeroKnowledge.g:3734:2: ( ruleWitness )
            {
            // InternalZeroKnowledge.g:3734:2: ( ruleWitness )
            // InternalZeroKnowledge.g:3735:3: ruleWitness
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_2_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleWitness();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_2_1_0()); 
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
    // $ANTLR end "rule__WitnessList__WitnessesAssignment_2_1"


    // $ANTLR start "rule__Witness__NameAssignment_0"
    // InternalZeroKnowledge.g:3744:1: rule__Witness__NameAssignment_0 : ( RULE_IDENTIFIER ) ;
    public final void rule__Witness__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3748:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:3749:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:3749:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:3750:3: RULE_IDENTIFIER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessAccess().getNameIDENTIFIERTerminalRuleCall_0_0()); 
            }
            match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessAccess().getNameIDENTIFIERTerminalRuleCall_0_0()); 
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
    // $ANTLR end "rule__Witness__NameAssignment_0"


    // $ANTLR start "rule__Witness__TestingAssignment_1"
    // InternalZeroKnowledge.g:3759:1: rule__Witness__TestingAssignment_1 : ( ( '?' ) ) ;
    public final void rule__Witness__TestingAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3763:1: ( ( ( '?' ) ) )
            // InternalZeroKnowledge.g:3764:2: ( ( '?' ) )
            {
            // InternalZeroKnowledge.g:3764:2: ( ( '?' ) )
            // InternalZeroKnowledge.g:3765:3: ( '?' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessAccess().getTestingQuestionMarkKeyword_1_0()); 
            }
            // InternalZeroKnowledge.g:3766:3: ( '?' )
            // InternalZeroKnowledge.g:3767:4: '?'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWitnessAccess().getTestingQuestionMarkKeyword_1_0()); 
            }
            match(input,18,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessAccess().getTestingQuestionMarkKeyword_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWitnessAccess().getTestingQuestionMarkKeyword_1_0()); 
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
    // $ANTLR end "rule__Witness__TestingAssignment_1"


    // $ANTLR start "rule__Conjunction__OperationAssignment_1_1"
    // InternalZeroKnowledge.g:3778:1: rule__Conjunction__OperationAssignment_1_1 : ( ( '&' ) ) ;
    public final void rule__Conjunction__OperationAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3782:1: ( ( ( '&' ) ) )
            // InternalZeroKnowledge.g:3783:2: ( ( '&' ) )
            {
            // InternalZeroKnowledge.g:3783:2: ( ( '&' ) )
            // InternalZeroKnowledge.g:3784:3: ( '&' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getOperationAmpersandKeyword_1_1_0()); 
            }
            // InternalZeroKnowledge.g:3785:3: ( '&' )
            // InternalZeroKnowledge.g:3786:4: '&'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConjunctionAccess().getOperationAmpersandKeyword_1_1_0()); 
            }
            match(input,19,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3797:1: rule__Conjunction__RightAssignment_1_2 : ( ruleDisjunction ) ;
    public final void rule__Conjunction__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3801:1: ( ( ruleDisjunction ) )
            // InternalZeroKnowledge.g:3802:2: ( ruleDisjunction )
            {
            // InternalZeroKnowledge.g:3802:2: ( ruleDisjunction )
            // InternalZeroKnowledge.g:3803:3: ruleDisjunction
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
    // InternalZeroKnowledge.g:3812:1: rule__Disjunction__OperationAssignment_1_1 : ( ( '|' ) ) ;
    public final void rule__Disjunction__OperationAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3816:1: ( ( ( '|' ) ) )
            // InternalZeroKnowledge.g:3817:2: ( ( '|' ) )
            {
            // InternalZeroKnowledge.g:3817:2: ( ( '|' ) )
            // InternalZeroKnowledge.g:3818:3: ( '|' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getOperationVerticalLineKeyword_1_1_0()); 
            }
            // InternalZeroKnowledge.g:3819:3: ( '|' )
            // InternalZeroKnowledge.g:3820:4: '|'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDisjunctionAccess().getOperationVerticalLineKeyword_1_1_0()); 
            }
            match(input,20,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3831:1: rule__Disjunction__RightAssignment_1_2 : ( ruleComparison ) ;
    public final void rule__Disjunction__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3835:1: ( ( ruleComparison ) )
            // InternalZeroKnowledge.g:3836:2: ( ruleComparison )
            {
            // InternalZeroKnowledge.g:3836:2: ( ruleComparison )
            // InternalZeroKnowledge.g:3837:3: ruleComparison
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
    // InternalZeroKnowledge.g:3846:1: rule__Comparison__OperationAssignment_1_0_0_1 : ( ( '!=' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3850:1: ( ( ( '!=' ) ) )
            // InternalZeroKnowledge.g:3851:2: ( ( '!=' ) )
            {
            // InternalZeroKnowledge.g:3851:2: ( ( '!=' ) )
            // InternalZeroKnowledge.g:3852:3: ( '!=' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationExclamationMarkEqualsSignKeyword_1_0_0_1_0()); 
            }
            // InternalZeroKnowledge.g:3853:3: ( '!=' )
            // InternalZeroKnowledge.g:3854:4: '!='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationExclamationMarkEqualsSignKeyword_1_0_0_1_0()); 
            }
            match(input,21,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3865:1: rule__Comparison__OperationAssignment_1_0_1_1 : ( ( '=' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3869:1: ( ( ( '=' ) ) )
            // InternalZeroKnowledge.g:3870:2: ( ( '=' ) )
            {
            // InternalZeroKnowledge.g:3870:2: ( ( '=' ) )
            // InternalZeroKnowledge.g:3871:3: ( '=' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationEqualsSignKeyword_1_0_1_1_0()); 
            }
            // InternalZeroKnowledge.g:3872:3: ( '=' )
            // InternalZeroKnowledge.g:3873:4: '='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationEqualsSignKeyword_1_0_1_1_0()); 
            }
            match(input,22,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3884:1: rule__Comparison__OperationAssignment_1_0_2_1 : ( ( '>=' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3888:1: ( ( ( '>=' ) ) )
            // InternalZeroKnowledge.g:3889:2: ( ( '>=' ) )
            {
            // InternalZeroKnowledge.g:3889:2: ( ( '>=' ) )
            // InternalZeroKnowledge.g:3890:3: ( '>=' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationGreaterThanSignEqualsSignKeyword_1_0_2_1_0()); 
            }
            // InternalZeroKnowledge.g:3891:3: ( '>=' )
            // InternalZeroKnowledge.g:3892:4: '>='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationGreaterThanSignEqualsSignKeyword_1_0_2_1_0()); 
            }
            match(input,23,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3903:1: rule__Comparison__OperationAssignment_1_0_3_1 : ( ( '<=' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3907:1: ( ( ( '<=' ) ) )
            // InternalZeroKnowledge.g:3908:2: ( ( '<=' ) )
            {
            // InternalZeroKnowledge.g:3908:2: ( ( '<=' ) )
            // InternalZeroKnowledge.g:3909:3: ( '<=' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationLessThanSignEqualsSignKeyword_1_0_3_1_0()); 
            }
            // InternalZeroKnowledge.g:3910:3: ( '<=' )
            // InternalZeroKnowledge.g:3911:4: '<='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationLessThanSignEqualsSignKeyword_1_0_3_1_0()); 
            }
            match(input,24,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3922:1: rule__Comparison__OperationAssignment_1_0_4_1 : ( ( '>' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3926:1: ( ( ( '>' ) ) )
            // InternalZeroKnowledge.g:3927:2: ( ( '>' ) )
            {
            // InternalZeroKnowledge.g:3927:2: ( ( '>' ) )
            // InternalZeroKnowledge.g:3928:3: ( '>' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationGreaterThanSignKeyword_1_0_4_1_0()); 
            }
            // InternalZeroKnowledge.g:3929:3: ( '>' )
            // InternalZeroKnowledge.g:3930:4: '>'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationGreaterThanSignKeyword_1_0_4_1_0()); 
            }
            match(input,25,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3941:1: rule__Comparison__OperationAssignment_1_0_5_1 : ( ( '<' ) ) ;
    public final void rule__Comparison__OperationAssignment_1_0_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3945:1: ( ( ( '<' ) ) )
            // InternalZeroKnowledge.g:3946:2: ( ( '<' ) )
            {
            // InternalZeroKnowledge.g:3946:2: ( ( '<' ) )
            // InternalZeroKnowledge.g:3947:3: ( '<' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationLessThanSignKeyword_1_0_5_1_0()); 
            }
            // InternalZeroKnowledge.g:3948:3: ( '<' )
            // InternalZeroKnowledge.g:3949:4: '<'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getComparisonAccess().getOperationLessThanSignKeyword_1_0_5_1_0()); 
            }
            match(input,26,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3960:1: rule__Comparison__RightAssignment_1_1 : ( ruleSum ) ;
    public final void rule__Comparison__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3964:1: ( ( ruleSum ) )
            // InternalZeroKnowledge.g:3965:2: ( ruleSum )
            {
            // InternalZeroKnowledge.g:3965:2: ( ruleSum )
            // InternalZeroKnowledge.g:3966:3: ruleSum
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
    // InternalZeroKnowledge.g:3975:1: rule__Sum__OperationAssignment_1_0_0_1 : ( ( '+' ) ) ;
    public final void rule__Sum__OperationAssignment_1_0_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3979:1: ( ( ( '+' ) ) )
            // InternalZeroKnowledge.g:3980:2: ( ( '+' ) )
            {
            // InternalZeroKnowledge.g:3980:2: ( ( '+' ) )
            // InternalZeroKnowledge.g:3981:3: ( '+' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationPlusSignKeyword_1_0_0_1_0()); 
            }
            // InternalZeroKnowledge.g:3982:3: ( '+' )
            // InternalZeroKnowledge.g:3983:4: '+'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationPlusSignKeyword_1_0_0_1_0()); 
            }
            match(input,27,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:3994:1: rule__Sum__OperationAssignment_1_0_1_1 : ( ( '-' ) ) ;
    public final void rule__Sum__OperationAssignment_1_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:3998:1: ( ( ( '-' ) ) )
            // InternalZeroKnowledge.g:3999:2: ( ( '-' ) )
            {
            // InternalZeroKnowledge.g:3999:2: ( ( '-' ) )
            // InternalZeroKnowledge.g:4000:3: ( '-' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationHyphenMinusKeyword_1_0_1_1_0()); 
            }
            // InternalZeroKnowledge.g:4001:3: ( '-' )
            // InternalZeroKnowledge.g:4002:4: '-'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSumAccess().getOperationHyphenMinusKeyword_1_0_1_1_0()); 
            }
            match(input,17,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:4013:1: rule__Sum__RightAssignment_1_1 : ( ruleProduct ) ;
    public final void rule__Sum__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4017:1: ( ( ruleProduct ) )
            // InternalZeroKnowledge.g:4018:2: ( ruleProduct )
            {
            // InternalZeroKnowledge.g:4018:2: ( ruleProduct )
            // InternalZeroKnowledge.g:4019:3: ruleProduct
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
    // InternalZeroKnowledge.g:4028:1: rule__Product__OperationAssignment_1_0_0_1 : ( ( '*' ) ) ;
    public final void rule__Product__OperationAssignment_1_0_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4032:1: ( ( ( '*' ) ) )
            // InternalZeroKnowledge.g:4033:2: ( ( '*' ) )
            {
            // InternalZeroKnowledge.g:4033:2: ( ( '*' ) )
            // InternalZeroKnowledge.g:4034:3: ( '*' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationAsteriskKeyword_1_0_0_1_0()); 
            }
            // InternalZeroKnowledge.g:4035:3: ( '*' )
            // InternalZeroKnowledge.g:4036:4: '*'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationAsteriskKeyword_1_0_0_1_0()); 
            }
            match(input,28,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:4047:1: rule__Product__OperationAssignment_1_0_1_1 : ( ( '/' ) ) ;
    public final void rule__Product__OperationAssignment_1_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4051:1: ( ( ( '/' ) ) )
            // InternalZeroKnowledge.g:4052:2: ( ( '/' ) )
            {
            // InternalZeroKnowledge.g:4052:2: ( ( '/' ) )
            // InternalZeroKnowledge.g:4053:3: ( '/' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationSolidusKeyword_1_0_1_1_0()); 
            }
            // InternalZeroKnowledge.g:4054:3: ( '/' )
            // InternalZeroKnowledge.g:4055:4: '/'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getProductAccess().getOperationSolidusKeyword_1_0_1_1_0()); 
            }
            match(input,29,FOLLOW_2); if (state.failed) return ;
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
    // InternalZeroKnowledge.g:4066:1: rule__Product__RightAssignment_1_1 : ( rulePower ) ;
    public final void rule__Product__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4070:1: ( ( rulePower ) )
            // InternalZeroKnowledge.g:4071:2: ( rulePower )
            {
            // InternalZeroKnowledge.g:4071:2: ( rulePower )
            // InternalZeroKnowledge.g:4072:3: rulePower
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


    // $ANTLR start "rule__Power__RightAssignment_1_2"
    // InternalZeroKnowledge.g:4081:1: rule__Power__RightAssignment_1_2 : ( rulePower ) ;
    public final void rule__Power__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4085:1: ( ( rulePower ) )
            // InternalZeroKnowledge.g:4086:2: ( rulePower )
            {
            // InternalZeroKnowledge.g:4086:2: ( rulePower )
            // InternalZeroKnowledge.g:4087:3: rulePower
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
    // InternalZeroKnowledge.g:4096:1: rule__StringLiteral__ValueAssignment : ( RULE_STRING_LITERAL ) ;
    public final void rule__StringLiteral__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4100:1: ( ( RULE_STRING_LITERAL ) )
            // InternalZeroKnowledge.g:4101:2: ( RULE_STRING_LITERAL )
            {
            // InternalZeroKnowledge.g:4101:2: ( RULE_STRING_LITERAL )
            // InternalZeroKnowledge.g:4102:3: RULE_STRING_LITERAL
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
    // InternalZeroKnowledge.g:4111:1: rule__Tuple__ElementsAssignment_0_0_2 : ( ruleConjunction ) ;
    public final void rule__Tuple__ElementsAssignment_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4115:1: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:4116:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:4116:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:4117:3: ruleConjunction
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
    // InternalZeroKnowledge.g:4126:1: rule__Tuple__ElementsAssignment_1 : ( ruleConjunction ) ;
    public final void rule__Tuple__ElementsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4130:1: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:4131:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:4131:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:4132:3: ruleConjunction
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
    // InternalZeroKnowledge.g:4141:1: rule__Tuple__ElementsAssignment_2_1 : ( ruleConjunction ) ;
    public final void rule__Tuple__ElementsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4145:1: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:4146:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:4146:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:4147:3: ruleConjunction
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


    // $ANTLR start "rule__Negative__TermAssignment_0_2"
    // InternalZeroKnowledge.g:4156:1: rule__Negative__TermAssignment_0_2 : ( ruleValue ) ;
    public final void rule__Negative__TermAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4160:1: ( ( ruleValue ) )
            // InternalZeroKnowledge.g:4161:2: ( ruleValue )
            {
            // InternalZeroKnowledge.g:4161:2: ( ruleValue )
            // InternalZeroKnowledge.g:4162:3: ruleValue
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
    // InternalZeroKnowledge.g:4171:1: rule__FunctionCall__NameAssignment_1_0_0_0 : ( RULE_IDENTIFIER ) ;
    public final void rule__FunctionCall__NameAssignment_1_0_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4175:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:4176:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:4176:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:4177:3: RULE_IDENTIFIER
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
    // InternalZeroKnowledge.g:4186:1: rule__FunctionCall__ArgumentsAssignment_1_1_0 : ( ruleConjunction ) ;
    public final void rule__FunctionCall__ArgumentsAssignment_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4190:1: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:4191:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:4191:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:4192:3: ruleConjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getArgumentsConjunctionParserRuleCall_1_1_0_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleConjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getArgumentsConjunctionParserRuleCall_1_1_0_0()); 
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
    // InternalZeroKnowledge.g:4201:1: rule__FunctionCall__ArgumentsAssignment_1_1_1_1 : ( ruleConjunction ) ;
    public final void rule__FunctionCall__ArgumentsAssignment_1_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4205:1: ( ( ruleConjunction ) )
            // InternalZeroKnowledge.g:4206:2: ( ruleConjunction )
            {
            // InternalZeroKnowledge.g:4206:2: ( ruleConjunction )
            // InternalZeroKnowledge.g:4207:3: ruleConjunction
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFunctionCallAccess().getArgumentsConjunctionParserRuleCall_1_1_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleConjunction();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFunctionCallAccess().getArgumentsConjunctionParserRuleCall_1_1_1_1_0()); 
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


    // $ANTLR start "rule__Variable__NameAssignment"
    // InternalZeroKnowledge.g:4216:1: rule__Variable__NameAssignment : ( RULE_IDENTIFIER ) ;
    public final void rule__Variable__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4220:1: ( ( RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:4221:2: ( RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:4221:2: ( RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:4222:3: RULE_IDENTIFIER
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
    // InternalZeroKnowledge.g:4231:1: rule__NumberLiteral__ValueAssignment : ( RULE_INT ) ;
    public final void rule__NumberLiteral__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4235:1: ( ( RULE_INT ) )
            // InternalZeroKnowledge.g:4236:2: ( RULE_INT )
            {
            // InternalZeroKnowledge.g:4236:2: ( RULE_INT )
            // InternalZeroKnowledge.g:4237:3: RULE_INT
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
    // InternalZeroKnowledge.g:4246:1: rule__Brackets__ContentAssignment_1 : ( ruleExpression ) ;
    public final void rule__Brackets__ContentAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalZeroKnowledge.g:4250:1: ( ( ruleExpression ) )
            // InternalZeroKnowledge.g:4251:2: ( ruleExpression )
            {
            // InternalZeroKnowledge.g:4251:2: ( ruleExpression )
            // InternalZeroKnowledge.g:4252:3: ruleExpression
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
        // InternalZeroKnowledge.g:701:2: ( ( ( ruleTuple ) ) )
        // InternalZeroKnowledge.g:701:2: ( ( ruleTuple ) )
        {
        // InternalZeroKnowledge.g:701:2: ( ( ruleTuple ) )
        // InternalZeroKnowledge.g:702:3: ( ruleTuple )
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getConstructAccess().getTupleParserRuleCall_1()); 
        }
        // InternalZeroKnowledge.g:703:3: ( ruleTuple )
        // InternalZeroKnowledge.g:703:4: ruleTuple
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
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000022470L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x000000000000C010L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000007E00000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000008020000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000008020002L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000026470L});

}