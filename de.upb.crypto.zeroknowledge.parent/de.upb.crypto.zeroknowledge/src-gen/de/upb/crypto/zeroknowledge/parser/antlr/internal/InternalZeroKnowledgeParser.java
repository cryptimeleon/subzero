package de.upb.crypto.zeroknowledge.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.upb.crypto.zeroknowledge.services.ZeroKnowledgeGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalZeroKnowledgeParser extends AbstractInternalAntlrParser {
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

        public InternalZeroKnowledgeParser(TokenStream input, ZeroKnowledgeGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected ZeroKnowledgeGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalZeroKnowledge.g:64:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalZeroKnowledge.g:64:46: (iv_ruleModel= ruleModel EOF )
            // InternalZeroKnowledge.g:65:2: iv_ruleModel= ruleModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getModelRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleModel; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalZeroKnowledge.g:71:1: ruleModel returns [EObject current=null] : ( ( (lv_functions_0_0= ruleFunctionDefinition ) )* ( (lv_witnessList_1_0= ruleWitnessList ) ) (otherlv_2= ';' )? ( (lv_proof_3_0= ruleExpression ) ) (otherlv_4= ';' )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_functions_0_0 = null;

        EObject lv_witnessList_1_0 = null;

        EObject lv_proof_3_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:77:2: ( ( ( (lv_functions_0_0= ruleFunctionDefinition ) )* ( (lv_witnessList_1_0= ruleWitnessList ) ) (otherlv_2= ';' )? ( (lv_proof_3_0= ruleExpression ) ) (otherlv_4= ';' )? ) )
            // InternalZeroKnowledge.g:78:2: ( ( (lv_functions_0_0= ruleFunctionDefinition ) )* ( (lv_witnessList_1_0= ruleWitnessList ) ) (otherlv_2= ';' )? ( (lv_proof_3_0= ruleExpression ) ) (otherlv_4= ';' )? )
            {
            // InternalZeroKnowledge.g:78:2: ( ( (lv_functions_0_0= ruleFunctionDefinition ) )* ( (lv_witnessList_1_0= ruleWitnessList ) ) (otherlv_2= ';' )? ( (lv_proof_3_0= ruleExpression ) ) (otherlv_4= ';' )? )
            // InternalZeroKnowledge.g:79:3: ( (lv_functions_0_0= ruleFunctionDefinition ) )* ( (lv_witnessList_1_0= ruleWitnessList ) ) (otherlv_2= ';' )? ( (lv_proof_3_0= ruleExpression ) ) (otherlv_4= ';' )?
            {
            // InternalZeroKnowledge.g:79:3: ( (lv_functions_0_0= ruleFunctionDefinition ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_IDENTIFIER) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalZeroKnowledge.g:80:4: (lv_functions_0_0= ruleFunctionDefinition )
            	    {
            	    // InternalZeroKnowledge.g:80:4: (lv_functions_0_0= ruleFunctionDefinition )
            	    // InternalZeroKnowledge.g:81:5: lv_functions_0_0= ruleFunctionDefinition
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getModelAccess().getFunctionsFunctionDefinitionParserRuleCall_0_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_3);
            	    lv_functions_0_0=ruleFunctionDefinition();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getModelRule());
            	      					}
            	      					add(
            	      						current,
            	      						"functions",
            	      						lv_functions_0_0,
            	      						"de.upb.crypto.zeroknowledge.ZeroKnowledge.FunctionDefinition");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalZeroKnowledge.g:98:3: ( (lv_witnessList_1_0= ruleWitnessList ) )
            // InternalZeroKnowledge.g:99:4: (lv_witnessList_1_0= ruleWitnessList )
            {
            // InternalZeroKnowledge.g:99:4: (lv_witnessList_1_0= ruleWitnessList )
            // InternalZeroKnowledge.g:100:5: lv_witnessList_1_0= ruleWitnessList
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getModelAccess().getWitnessListWitnessListParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_4);
            lv_witnessList_1_0=ruleWitnessList();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getModelRule());
              					}
              					set(
              						current,
              						"witnessList",
              						lv_witnessList_1_0,
              						"de.upb.crypto.zeroknowledge.ZeroKnowledge.WitnessList");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalZeroKnowledge.g:117:3: (otherlv_2= ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==10) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalZeroKnowledge.g:118:4: otherlv_2= ';'
                    {
                    otherlv_2=(Token)match(input,10,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getModelAccess().getSemicolonKeyword_2());
                      			
                    }

                    }
                    break;

            }

            // InternalZeroKnowledge.g:123:3: ( (lv_proof_3_0= ruleExpression ) )
            // InternalZeroKnowledge.g:124:4: (lv_proof_3_0= ruleExpression )
            {
            // InternalZeroKnowledge.g:124:4: (lv_proof_3_0= ruleExpression )
            // InternalZeroKnowledge.g:125:5: lv_proof_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getModelAccess().getProofExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_proof_3_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getModelRule());
              					}
              					set(
              						current,
              						"proof",
              						lv_proof_3_0,
              						"de.upb.crypto.zeroknowledge.ZeroKnowledge.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalZeroKnowledge.g:142:3: (otherlv_4= ';' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==10) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalZeroKnowledge.g:143:4: otherlv_4= ';'
                    {
                    otherlv_4=(Token)match(input,10,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getModelAccess().getSemicolonKeyword_4());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleFunctionDefinition"
    // InternalZeroKnowledge.g:152:1: entryRuleFunctionDefinition returns [EObject current=null] : iv_ruleFunctionDefinition= ruleFunctionDefinition EOF ;
    public final EObject entryRuleFunctionDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionDefinition = null;


        try {
            // InternalZeroKnowledge.g:152:59: (iv_ruleFunctionDefinition= ruleFunctionDefinition EOF )
            // InternalZeroKnowledge.g:153:2: iv_ruleFunctionDefinition= ruleFunctionDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionDefinitionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFunctionDefinition=ruleFunctionDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunctionDefinition; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunctionDefinition"


    // $ANTLR start "ruleFunctionDefinition"
    // InternalZeroKnowledge.g:159:1: ruleFunctionDefinition returns [EObject current=null] : ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) ( (lv_parameterList_1_0= ruleParameterList ) ) otherlv_2= '{' ( (lv_body_3_0= ruleExpression ) ) (otherlv_4= ';' )? otherlv_5= '}' (otherlv_6= ';' )? ) ;
    public final EObject ruleFunctionDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_parameterList_1_0 = null;

        EObject lv_body_3_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:165:2: ( ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) ( (lv_parameterList_1_0= ruleParameterList ) ) otherlv_2= '{' ( (lv_body_3_0= ruleExpression ) ) (otherlv_4= ';' )? otherlv_5= '}' (otherlv_6= ';' )? ) )
            // InternalZeroKnowledge.g:166:2: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) ( (lv_parameterList_1_0= ruleParameterList ) ) otherlv_2= '{' ( (lv_body_3_0= ruleExpression ) ) (otherlv_4= ';' )? otherlv_5= '}' (otherlv_6= ';' )? )
            {
            // InternalZeroKnowledge.g:166:2: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) ( (lv_parameterList_1_0= ruleParameterList ) ) otherlv_2= '{' ( (lv_body_3_0= ruleExpression ) ) (otherlv_4= ';' )? otherlv_5= '}' (otherlv_6= ';' )? )
            // InternalZeroKnowledge.g:167:3: ( (lv_name_0_0= RULE_IDENTIFIER ) ) ( (lv_parameterList_1_0= ruleParameterList ) ) otherlv_2= '{' ( (lv_body_3_0= ruleExpression ) ) (otherlv_4= ';' )? otherlv_5= '}' (otherlv_6= ';' )?
            {
            // InternalZeroKnowledge.g:167:3: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:168:4: (lv_name_0_0= RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:168:4: (lv_name_0_0= RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:169:5: lv_name_0_0= RULE_IDENTIFIER
            {
            lv_name_0_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_0_0, grammarAccess.getFunctionDefinitionAccess().getNameIDENTIFIERTerminalRuleCall_0_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getFunctionDefinitionRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_0_0,
              						"de.upb.crypto.zeroknowledge.ZeroKnowledge.IDENTIFIER");
              				
            }

            }


            }

            // InternalZeroKnowledge.g:185:3: ( (lv_parameterList_1_0= ruleParameterList ) )
            // InternalZeroKnowledge.g:186:4: (lv_parameterList_1_0= ruleParameterList )
            {
            // InternalZeroKnowledge.g:186:4: (lv_parameterList_1_0= ruleParameterList )
            // InternalZeroKnowledge.g:187:5: lv_parameterList_1_0= ruleParameterList
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getParameterListParameterListParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_7);
            lv_parameterList_1_0=ruleParameterList();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getFunctionDefinitionRule());
              					}
              					set(
              						current,
              						"parameterList",
              						lv_parameterList_1_0,
              						"de.upb.crypto.zeroknowledge.ZeroKnowledge.ParameterList");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,11,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getFunctionDefinitionAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalZeroKnowledge.g:208:3: ( (lv_body_3_0= ruleExpression ) )
            // InternalZeroKnowledge.g:209:4: (lv_body_3_0= ruleExpression )
            {
            // InternalZeroKnowledge.g:209:4: (lv_body_3_0= ruleExpression )
            // InternalZeroKnowledge.g:210:5: lv_body_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getBodyExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_8);
            lv_body_3_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getFunctionDefinitionRule());
              					}
              					set(
              						current,
              						"body",
              						lv_body_3_0,
              						"de.upb.crypto.zeroknowledge.ZeroKnowledge.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalZeroKnowledge.g:227:3: (otherlv_4= ';' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==10) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalZeroKnowledge.g:228:4: otherlv_4= ';'
                    {
                    otherlv_4=(Token)match(input,10,FOLLOW_9); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getFunctionDefinitionAccess().getSemicolonKeyword_4());
                      			
                    }

                    }
                    break;

            }

            otherlv_5=(Token)match(input,12,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getFunctionDefinitionAccess().getRightCurlyBracketKeyword_5());
              		
            }
            // InternalZeroKnowledge.g:237:3: (otherlv_6= ';' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==10) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalZeroKnowledge.g:238:4: otherlv_6= ';'
                    {
                    otherlv_6=(Token)match(input,10,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_6, grammarAccess.getFunctionDefinitionAccess().getSemicolonKeyword_6());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunctionDefinition"


    // $ANTLR start "entryRuleParameterList"
    // InternalZeroKnowledge.g:247:1: entryRuleParameterList returns [EObject current=null] : iv_ruleParameterList= ruleParameterList EOF ;
    public final EObject entryRuleParameterList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterList = null;


        try {
            // InternalZeroKnowledge.g:247:54: (iv_ruleParameterList= ruleParameterList EOF )
            // InternalZeroKnowledge.g:248:2: iv_ruleParameterList= ruleParameterList EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParameterListRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleParameterList=ruleParameterList();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParameterList; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterList"


    // $ANTLR start "ruleParameterList"
    // InternalZeroKnowledge.g:254:1: ruleParameterList returns [EObject current=null] : (otherlv_0= '(' ( ( (lv_parameters_1_0= ruleParameter ) ) (otherlv_2= ',' ( (lv_parameters_3_0= ruleParameter ) ) )* )? ( (lv_symbol_4_0= ')' ) ) ) ;
    public final EObject ruleParameterList() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_symbol_4_0=null;
        EObject lv_parameters_1_0 = null;

        EObject lv_parameters_3_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:260:2: ( (otherlv_0= '(' ( ( (lv_parameters_1_0= ruleParameter ) ) (otherlv_2= ',' ( (lv_parameters_3_0= ruleParameter ) ) )* )? ( (lv_symbol_4_0= ')' ) ) ) )
            // InternalZeroKnowledge.g:261:2: (otherlv_0= '(' ( ( (lv_parameters_1_0= ruleParameter ) ) (otherlv_2= ',' ( (lv_parameters_3_0= ruleParameter ) ) )* )? ( (lv_symbol_4_0= ')' ) ) )
            {
            // InternalZeroKnowledge.g:261:2: (otherlv_0= '(' ( ( (lv_parameters_1_0= ruleParameter ) ) (otherlv_2= ',' ( (lv_parameters_3_0= ruleParameter ) ) )* )? ( (lv_symbol_4_0= ')' ) ) )
            // InternalZeroKnowledge.g:262:3: otherlv_0= '(' ( ( (lv_parameters_1_0= ruleParameter ) ) (otherlv_2= ',' ( (lv_parameters_3_0= ruleParameter ) ) )* )? ( (lv_symbol_4_0= ')' ) )
            {
            otherlv_0=(Token)match(input,13,FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getParameterListAccess().getLeftParenthesisKeyword_0());
              		
            }
            // InternalZeroKnowledge.g:266:3: ( ( (lv_parameters_1_0= ruleParameter ) ) (otherlv_2= ',' ( (lv_parameters_3_0= ruleParameter ) ) )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_IDENTIFIER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalZeroKnowledge.g:267:4: ( (lv_parameters_1_0= ruleParameter ) ) (otherlv_2= ',' ( (lv_parameters_3_0= ruleParameter ) ) )*
                    {
                    // InternalZeroKnowledge.g:267:4: ( (lv_parameters_1_0= ruleParameter ) )
                    // InternalZeroKnowledge.g:268:5: (lv_parameters_1_0= ruleParameter )
                    {
                    // InternalZeroKnowledge.g:268:5: (lv_parameters_1_0= ruleParameter )
                    // InternalZeroKnowledge.g:269:6: lv_parameters_1_0= ruleParameter
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getParameterListAccess().getParametersParameterParserRuleCall_1_0_0());
                      					
                    }
                    pushFollow(FOLLOW_11);
                    lv_parameters_1_0=ruleParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getParameterListRule());
                      						}
                      						add(
                      							current,
                      							"parameters",
                      							lv_parameters_1_0,
                      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Parameter");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalZeroKnowledge.g:286:4: (otherlv_2= ',' ( (lv_parameters_3_0= ruleParameter ) ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==14) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalZeroKnowledge.g:287:5: otherlv_2= ',' ( (lv_parameters_3_0= ruleParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,14,FOLLOW_12); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_2, grammarAccess.getParameterListAccess().getCommaKeyword_1_1_0());
                    	      				
                    	    }
                    	    // InternalZeroKnowledge.g:291:5: ( (lv_parameters_3_0= ruleParameter ) )
                    	    // InternalZeroKnowledge.g:292:6: (lv_parameters_3_0= ruleParameter )
                    	    {
                    	    // InternalZeroKnowledge.g:292:6: (lv_parameters_3_0= ruleParameter )
                    	    // InternalZeroKnowledge.g:293:7: lv_parameters_3_0= ruleParameter
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getParameterListAccess().getParametersParameterParserRuleCall_1_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_parameters_3_0=ruleParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getParameterListRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"parameters",
                    	      								lv_parameters_3_0,
                    	      								"de.upb.crypto.zeroknowledge.ZeroKnowledge.Parameter");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalZeroKnowledge.g:312:3: ( (lv_symbol_4_0= ')' ) )
            // InternalZeroKnowledge.g:313:4: (lv_symbol_4_0= ')' )
            {
            // InternalZeroKnowledge.g:313:4: (lv_symbol_4_0= ')' )
            // InternalZeroKnowledge.g:314:5: lv_symbol_4_0= ')'
            {
            lv_symbol_4_0=(Token)match(input,15,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_symbol_4_0, grammarAccess.getParameterListAccess().getSymbolRightParenthesisKeyword_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getParameterListRule());
              					}
              					setWithLastConsumed(current, "symbol", lv_symbol_4_0, ")");
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterList"


    // $ANTLR start "entryRuleParameter"
    // InternalZeroKnowledge.g:330:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalZeroKnowledge.g:330:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalZeroKnowledge.g:331:2: iv_ruleParameter= ruleParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParameter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalZeroKnowledge.g:337:1: ruleParameter returns [EObject current=null] : ( (lv_name_0_0= RULE_IDENTIFIER ) ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalZeroKnowledge.g:343:2: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) )
            // InternalZeroKnowledge.g:344:2: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            {
            // InternalZeroKnowledge.g:344:2: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:345:3: (lv_name_0_0= RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:345:3: (lv_name_0_0= RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:346:4: lv_name_0_0= RULE_IDENTIFIER
            {
            lv_name_0_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_name_0_0, grammarAccess.getParameterAccess().getNameIDENTIFIERTerminalRuleCall_0());
              			
            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getParameterRule());
              				}
              				setWithLastConsumed(
              					current,
              					"name",
              					lv_name_0_0,
              					"de.upb.crypto.zeroknowledge.ZeroKnowledge.IDENTIFIER");
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleWitnessList"
    // InternalZeroKnowledge.g:365:1: entryRuleWitnessList returns [EObject current=null] : iv_ruleWitnessList= ruleWitnessList EOF ;
    public final EObject entryRuleWitnessList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWitnessList = null;


        try {
            // InternalZeroKnowledge.g:365:52: (iv_ruleWitnessList= ruleWitnessList EOF )
            // InternalZeroKnowledge.g:366:2: iv_ruleWitnessList= ruleWitnessList EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWitnessListRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWitnessList=ruleWitnessList();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWitnessList; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWitnessList"


    // $ANTLR start "ruleWitnessList"
    // InternalZeroKnowledge.g:372:1: ruleWitnessList returns [EObject current=null] : (otherlv_0= '(' ( ( (lv_witnesses_1_0= ruleWitness ) ) (otherlv_2= ',' ( (lv_witnesses_3_0= ruleWitness ) ) )* )? ( (lv_symbol_4_0= ')' ) ) ) ;
    public final EObject ruleWitnessList() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_symbol_4_0=null;
        EObject lv_witnesses_1_0 = null;

        EObject lv_witnesses_3_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:378:2: ( (otherlv_0= '(' ( ( (lv_witnesses_1_0= ruleWitness ) ) (otherlv_2= ',' ( (lv_witnesses_3_0= ruleWitness ) ) )* )? ( (lv_symbol_4_0= ')' ) ) ) )
            // InternalZeroKnowledge.g:379:2: (otherlv_0= '(' ( ( (lv_witnesses_1_0= ruleWitness ) ) (otherlv_2= ',' ( (lv_witnesses_3_0= ruleWitness ) ) )* )? ( (lv_symbol_4_0= ')' ) ) )
            {
            // InternalZeroKnowledge.g:379:2: (otherlv_0= '(' ( ( (lv_witnesses_1_0= ruleWitness ) ) (otherlv_2= ',' ( (lv_witnesses_3_0= ruleWitness ) ) )* )? ( (lv_symbol_4_0= ')' ) ) )
            // InternalZeroKnowledge.g:380:3: otherlv_0= '(' ( ( (lv_witnesses_1_0= ruleWitness ) ) (otherlv_2= ',' ( (lv_witnesses_3_0= ruleWitness ) ) )* )? ( (lv_symbol_4_0= ')' ) )
            {
            otherlv_0=(Token)match(input,13,FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getWitnessListAccess().getLeftParenthesisKeyword_0());
              		
            }
            // InternalZeroKnowledge.g:384:3: ( ( (lv_witnesses_1_0= ruleWitness ) ) (otherlv_2= ',' ( (lv_witnesses_3_0= ruleWitness ) ) )* )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_IDENTIFIER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalZeroKnowledge.g:385:4: ( (lv_witnesses_1_0= ruleWitness ) ) (otherlv_2= ',' ( (lv_witnesses_3_0= ruleWitness ) ) )*
                    {
                    // InternalZeroKnowledge.g:385:4: ( (lv_witnesses_1_0= ruleWitness ) )
                    // InternalZeroKnowledge.g:386:5: (lv_witnesses_1_0= ruleWitness )
                    {
                    // InternalZeroKnowledge.g:386:5: (lv_witnesses_1_0= ruleWitness )
                    // InternalZeroKnowledge.g:387:6: lv_witnesses_1_0= ruleWitness
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_1_0_0());
                      					
                    }
                    pushFollow(FOLLOW_11);
                    lv_witnesses_1_0=ruleWitness();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getWitnessListRule());
                      						}
                      						add(
                      							current,
                      							"witnesses",
                      							lv_witnesses_1_0,
                      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Witness");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalZeroKnowledge.g:404:4: (otherlv_2= ',' ( (lv_witnesses_3_0= ruleWitness ) ) )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==14) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // InternalZeroKnowledge.g:405:5: otherlv_2= ',' ( (lv_witnesses_3_0= ruleWitness ) )
                    	    {
                    	    otherlv_2=(Token)match(input,14,FOLLOW_12); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_2, grammarAccess.getWitnessListAccess().getCommaKeyword_1_1_0());
                    	      				
                    	    }
                    	    // InternalZeroKnowledge.g:409:5: ( (lv_witnesses_3_0= ruleWitness ) )
                    	    // InternalZeroKnowledge.g:410:6: (lv_witnesses_3_0= ruleWitness )
                    	    {
                    	    // InternalZeroKnowledge.g:410:6: (lv_witnesses_3_0= ruleWitness )
                    	    // InternalZeroKnowledge.g:411:7: lv_witnesses_3_0= ruleWitness
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getWitnessListAccess().getWitnessesWitnessParserRuleCall_1_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_witnesses_3_0=ruleWitness();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getWitnessListRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"witnesses",
                    	      								lv_witnesses_3_0,
                    	      								"de.upb.crypto.zeroknowledge.ZeroKnowledge.Witness");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalZeroKnowledge.g:430:3: ( (lv_symbol_4_0= ')' ) )
            // InternalZeroKnowledge.g:431:4: (lv_symbol_4_0= ')' )
            {
            // InternalZeroKnowledge.g:431:4: (lv_symbol_4_0= ')' )
            // InternalZeroKnowledge.g:432:5: lv_symbol_4_0= ')'
            {
            lv_symbol_4_0=(Token)match(input,15,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_symbol_4_0, grammarAccess.getWitnessListAccess().getSymbolRightParenthesisKeyword_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getWitnessListRule());
              					}
              					setWithLastConsumed(current, "symbol", lv_symbol_4_0, ")");
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWitnessList"


    // $ANTLR start "entryRuleWitness"
    // InternalZeroKnowledge.g:448:1: entryRuleWitness returns [EObject current=null] : iv_ruleWitness= ruleWitness EOF ;
    public final EObject entryRuleWitness() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWitness = null;


        try {
            // InternalZeroKnowledge.g:448:48: (iv_ruleWitness= ruleWitness EOF )
            // InternalZeroKnowledge.g:449:2: iv_ruleWitness= ruleWitness EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWitnessRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWitness=ruleWitness();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWitness; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWitness"


    // $ANTLR start "ruleWitness"
    // InternalZeroKnowledge.g:455:1: ruleWitness returns [EObject current=null] : ( (lv_name_0_0= RULE_IDENTIFIER ) ) ;
    public final EObject ruleWitness() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalZeroKnowledge.g:461:2: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) )
            // InternalZeroKnowledge.g:462:2: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            {
            // InternalZeroKnowledge.g:462:2: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:463:3: (lv_name_0_0= RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:463:3: (lv_name_0_0= RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:464:4: lv_name_0_0= RULE_IDENTIFIER
            {
            lv_name_0_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_name_0_0, grammarAccess.getWitnessAccess().getNameIDENTIFIERTerminalRuleCall_0());
              			
            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getWitnessRule());
              				}
              				setWithLastConsumed(
              					current,
              					"name",
              					lv_name_0_0,
              					"de.upb.crypto.zeroknowledge.ZeroKnowledge.IDENTIFIER");
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWitness"


    // $ANTLR start "entryRuleExpression"
    // InternalZeroKnowledge.g:483:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalZeroKnowledge.g:483:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalZeroKnowledge.g:484:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalZeroKnowledge.g:490:1: ruleExpression returns [EObject current=null] : this_Conjunction_0= ruleConjunction ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Conjunction_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:496:2: (this_Conjunction_0= ruleConjunction )
            // InternalZeroKnowledge.g:497:2: this_Conjunction_0= ruleConjunction
            {
            if ( state.backtracking==0 ) {

              		newCompositeNode(grammarAccess.getExpressionAccess().getConjunctionParserRuleCall());
              	
            }
            pushFollow(FOLLOW_2);
            this_Conjunction_0=ruleConjunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current = this_Conjunction_0;
              		afterParserOrEnumRuleCall();
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleConjunction"
    // InternalZeroKnowledge.g:508:1: entryRuleConjunction returns [EObject current=null] : iv_ruleConjunction= ruleConjunction EOF ;
    public final EObject entryRuleConjunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConjunction = null;


        try {
            // InternalZeroKnowledge.g:508:52: (iv_ruleConjunction= ruleConjunction EOF )
            // InternalZeroKnowledge.g:509:2: iv_ruleConjunction= ruleConjunction EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConjunctionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConjunction=ruleConjunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConjunction; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConjunction"


    // $ANTLR start "ruleConjunction"
    // InternalZeroKnowledge.g:515:1: ruleConjunction returns [EObject current=null] : (this_Disjunction_0= ruleDisjunction ( () ( (lv_operation_2_0= '&' ) ) ( (lv_right_3_0= ruleDisjunction ) ) )* ) ;
    public final EObject ruleConjunction() throws RecognitionException {
        EObject current = null;

        Token lv_operation_2_0=null;
        EObject this_Disjunction_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:521:2: ( (this_Disjunction_0= ruleDisjunction ( () ( (lv_operation_2_0= '&' ) ) ( (lv_right_3_0= ruleDisjunction ) ) )* ) )
            // InternalZeroKnowledge.g:522:2: (this_Disjunction_0= ruleDisjunction ( () ( (lv_operation_2_0= '&' ) ) ( (lv_right_3_0= ruleDisjunction ) ) )* )
            {
            // InternalZeroKnowledge.g:522:2: (this_Disjunction_0= ruleDisjunction ( () ( (lv_operation_2_0= '&' ) ) ( (lv_right_3_0= ruleDisjunction ) ) )* )
            // InternalZeroKnowledge.g:523:3: this_Disjunction_0= ruleDisjunction ( () ( (lv_operation_2_0= '&' ) ) ( (lv_right_3_0= ruleDisjunction ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getConjunctionAccess().getDisjunctionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_13);
            this_Disjunction_0=ruleDisjunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Disjunction_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalZeroKnowledge.g:531:3: ( () ( (lv_operation_2_0= '&' ) ) ( (lv_right_3_0= ruleDisjunction ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==16) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalZeroKnowledge.g:532:4: () ( (lv_operation_2_0= '&' ) ) ( (lv_right_3_0= ruleDisjunction ) )
            	    {
            	    // InternalZeroKnowledge.g:532:4: ()
            	    // InternalZeroKnowledge.g:533:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getConjunctionAccess().getConjunctionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalZeroKnowledge.g:539:4: ( (lv_operation_2_0= '&' ) )
            	    // InternalZeroKnowledge.g:540:5: (lv_operation_2_0= '&' )
            	    {
            	    // InternalZeroKnowledge.g:540:5: (lv_operation_2_0= '&' )
            	    // InternalZeroKnowledge.g:541:6: lv_operation_2_0= '&'
            	    {
            	    lv_operation_2_0=(Token)match(input,16,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(lv_operation_2_0, grammarAccess.getConjunctionAccess().getOperationAmpersandKeyword_1_1_0());
            	      					
            	    }
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getConjunctionRule());
            	      						}
            	      						setWithLastConsumed(current, "operation", lv_operation_2_0, "&");
            	      					
            	    }

            	    }


            	    }

            	    // InternalZeroKnowledge.g:553:4: ( (lv_right_3_0= ruleDisjunction ) )
            	    // InternalZeroKnowledge.g:554:5: (lv_right_3_0= ruleDisjunction )
            	    {
            	    // InternalZeroKnowledge.g:554:5: (lv_right_3_0= ruleDisjunction )
            	    // InternalZeroKnowledge.g:555:6: lv_right_3_0= ruleDisjunction
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getConjunctionAccess().getRightDisjunctionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_13);
            	    lv_right_3_0=ruleDisjunction();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getConjunctionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Disjunction");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConjunction"


    // $ANTLR start "entryRuleDisjunction"
    // InternalZeroKnowledge.g:577:1: entryRuleDisjunction returns [EObject current=null] : iv_ruleDisjunction= ruleDisjunction EOF ;
    public final EObject entryRuleDisjunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDisjunction = null;


        try {
            // InternalZeroKnowledge.g:577:52: (iv_ruleDisjunction= ruleDisjunction EOF )
            // InternalZeroKnowledge.g:578:2: iv_ruleDisjunction= ruleDisjunction EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDisjunctionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleDisjunction=ruleDisjunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDisjunction; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDisjunction"


    // $ANTLR start "ruleDisjunction"
    // InternalZeroKnowledge.g:584:1: ruleDisjunction returns [EObject current=null] : (this_Comparison_0= ruleComparison ( () ( (lv_operation_2_0= '|' ) ) ( (lv_right_3_0= ruleComparison ) ) )* ) ;
    public final EObject ruleDisjunction() throws RecognitionException {
        EObject current = null;

        Token lv_operation_2_0=null;
        EObject this_Comparison_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:590:2: ( (this_Comparison_0= ruleComparison ( () ( (lv_operation_2_0= '|' ) ) ( (lv_right_3_0= ruleComparison ) ) )* ) )
            // InternalZeroKnowledge.g:591:2: (this_Comparison_0= ruleComparison ( () ( (lv_operation_2_0= '|' ) ) ( (lv_right_3_0= ruleComparison ) ) )* )
            {
            // InternalZeroKnowledge.g:591:2: (this_Comparison_0= ruleComparison ( () ( (lv_operation_2_0= '|' ) ) ( (lv_right_3_0= ruleComparison ) ) )* )
            // InternalZeroKnowledge.g:592:3: this_Comparison_0= ruleComparison ( () ( (lv_operation_2_0= '|' ) ) ( (lv_right_3_0= ruleComparison ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getDisjunctionAccess().getComparisonParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_14);
            this_Comparison_0=ruleComparison();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Comparison_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalZeroKnowledge.g:600:3: ( () ( (lv_operation_2_0= '|' ) ) ( (lv_right_3_0= ruleComparison ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==17) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalZeroKnowledge.g:601:4: () ( (lv_operation_2_0= '|' ) ) ( (lv_right_3_0= ruleComparison ) )
            	    {
            	    // InternalZeroKnowledge.g:601:4: ()
            	    // InternalZeroKnowledge.g:602:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getDisjunctionAccess().getDisjunctionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalZeroKnowledge.g:608:4: ( (lv_operation_2_0= '|' ) )
            	    // InternalZeroKnowledge.g:609:5: (lv_operation_2_0= '|' )
            	    {
            	    // InternalZeroKnowledge.g:609:5: (lv_operation_2_0= '|' )
            	    // InternalZeroKnowledge.g:610:6: lv_operation_2_0= '|'
            	    {
            	    lv_operation_2_0=(Token)match(input,17,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(lv_operation_2_0, grammarAccess.getDisjunctionAccess().getOperationVerticalLineKeyword_1_1_0());
            	      					
            	    }
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getDisjunctionRule());
            	      						}
            	      						setWithLastConsumed(current, "operation", lv_operation_2_0, "|");
            	      					
            	    }

            	    }


            	    }

            	    // InternalZeroKnowledge.g:622:4: ( (lv_right_3_0= ruleComparison ) )
            	    // InternalZeroKnowledge.g:623:5: (lv_right_3_0= ruleComparison )
            	    {
            	    // InternalZeroKnowledge.g:623:5: (lv_right_3_0= ruleComparison )
            	    // InternalZeroKnowledge.g:624:6: lv_right_3_0= ruleComparison
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getDisjunctionAccess().getRightComparisonParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_14);
            	    lv_right_3_0=ruleComparison();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getDisjunctionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Comparison");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDisjunction"


    // $ANTLR start "entryRuleComparison"
    // InternalZeroKnowledge.g:646:1: entryRuleComparison returns [EObject current=null] : iv_ruleComparison= ruleComparison EOF ;
    public final EObject entryRuleComparison() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparison = null;


        try {
            // InternalZeroKnowledge.g:646:51: (iv_ruleComparison= ruleComparison EOF )
            // InternalZeroKnowledge.g:647:2: iv_ruleComparison= ruleComparison EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComparisonRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleComparison=ruleComparison();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComparison; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComparison"


    // $ANTLR start "ruleComparison"
    // InternalZeroKnowledge.g:653:1: ruleComparison returns [EObject current=null] : (this_Sum_0= ruleSum ( ( ( () ( (lv_operation_2_0= '!=' ) ) ) | ( () ( (lv_operation_4_0= '=' ) ) ) | ( () ( (lv_operation_6_0= '>=' ) ) ) | ( () ( (lv_operation_8_0= '<=' ) ) ) | ( () ( (lv_operation_10_0= '>' ) ) ) | ( () ( (lv_operation_12_0= '<' ) ) ) ) ( (lv_right_13_0= ruleSum ) ) )? ) ;
    public final EObject ruleComparison() throws RecognitionException {
        EObject current = null;

        Token lv_operation_2_0=null;
        Token lv_operation_4_0=null;
        Token lv_operation_6_0=null;
        Token lv_operation_8_0=null;
        Token lv_operation_10_0=null;
        Token lv_operation_12_0=null;
        EObject this_Sum_0 = null;

        EObject lv_right_13_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:659:2: ( (this_Sum_0= ruleSum ( ( ( () ( (lv_operation_2_0= '!=' ) ) ) | ( () ( (lv_operation_4_0= '=' ) ) ) | ( () ( (lv_operation_6_0= '>=' ) ) ) | ( () ( (lv_operation_8_0= '<=' ) ) ) | ( () ( (lv_operation_10_0= '>' ) ) ) | ( () ( (lv_operation_12_0= '<' ) ) ) ) ( (lv_right_13_0= ruleSum ) ) )? ) )
            // InternalZeroKnowledge.g:660:2: (this_Sum_0= ruleSum ( ( ( () ( (lv_operation_2_0= '!=' ) ) ) | ( () ( (lv_operation_4_0= '=' ) ) ) | ( () ( (lv_operation_6_0= '>=' ) ) ) | ( () ( (lv_operation_8_0= '<=' ) ) ) | ( () ( (lv_operation_10_0= '>' ) ) ) | ( () ( (lv_operation_12_0= '<' ) ) ) ) ( (lv_right_13_0= ruleSum ) ) )? )
            {
            // InternalZeroKnowledge.g:660:2: (this_Sum_0= ruleSum ( ( ( () ( (lv_operation_2_0= '!=' ) ) ) | ( () ( (lv_operation_4_0= '=' ) ) ) | ( () ( (lv_operation_6_0= '>=' ) ) ) | ( () ( (lv_operation_8_0= '<=' ) ) ) | ( () ( (lv_operation_10_0= '>' ) ) ) | ( () ( (lv_operation_12_0= '<' ) ) ) ) ( (lv_right_13_0= ruleSum ) ) )? )
            // InternalZeroKnowledge.g:661:3: this_Sum_0= ruleSum ( ( ( () ( (lv_operation_2_0= '!=' ) ) ) | ( () ( (lv_operation_4_0= '=' ) ) ) | ( () ( (lv_operation_6_0= '>=' ) ) ) | ( () ( (lv_operation_8_0= '<=' ) ) ) | ( () ( (lv_operation_10_0= '>' ) ) ) | ( () ( (lv_operation_12_0= '<' ) ) ) ) ( (lv_right_13_0= ruleSum ) ) )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getComparisonAccess().getSumParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_15);
            this_Sum_0=ruleSum();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Sum_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalZeroKnowledge.g:669:3: ( ( ( () ( (lv_operation_2_0= '!=' ) ) ) | ( () ( (lv_operation_4_0= '=' ) ) ) | ( () ( (lv_operation_6_0= '>=' ) ) ) | ( () ( (lv_operation_8_0= '<=' ) ) ) | ( () ( (lv_operation_10_0= '>' ) ) ) | ( () ( (lv_operation_12_0= '<' ) ) ) ) ( (lv_right_13_0= ruleSum ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=18 && LA13_0<=23)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalZeroKnowledge.g:670:4: ( ( () ( (lv_operation_2_0= '!=' ) ) ) | ( () ( (lv_operation_4_0= '=' ) ) ) | ( () ( (lv_operation_6_0= '>=' ) ) ) | ( () ( (lv_operation_8_0= '<=' ) ) ) | ( () ( (lv_operation_10_0= '>' ) ) ) | ( () ( (lv_operation_12_0= '<' ) ) ) ) ( (lv_right_13_0= ruleSum ) )
                    {
                    // InternalZeroKnowledge.g:670:4: ( ( () ( (lv_operation_2_0= '!=' ) ) ) | ( () ( (lv_operation_4_0= '=' ) ) ) | ( () ( (lv_operation_6_0= '>=' ) ) ) | ( () ( (lv_operation_8_0= '<=' ) ) ) | ( () ( (lv_operation_10_0= '>' ) ) ) | ( () ( (lv_operation_12_0= '<' ) ) ) )
                    int alt12=6;
                    switch ( input.LA(1) ) {
                    case 18:
                        {
                        alt12=1;
                        }
                        break;
                    case 19:
                        {
                        alt12=2;
                        }
                        break;
                    case 20:
                        {
                        alt12=3;
                        }
                        break;
                    case 21:
                        {
                        alt12=4;
                        }
                        break;
                    case 22:
                        {
                        alt12=5;
                        }
                        break;
                    case 23:
                        {
                        alt12=6;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 0, input);

                        throw nvae;
                    }

                    switch (alt12) {
                        case 1 :
                            // InternalZeroKnowledge.g:671:5: ( () ( (lv_operation_2_0= '!=' ) ) )
                            {
                            // InternalZeroKnowledge.g:671:5: ( () ( (lv_operation_2_0= '!=' ) ) )
                            // InternalZeroKnowledge.g:672:6: () ( (lv_operation_2_0= '!=' ) )
                            {
                            // InternalZeroKnowledge.g:672:6: ()
                            // InternalZeroKnowledge.g:673:7: 
                            {
                            if ( state.backtracking==0 ) {

                              							current = forceCreateModelElementAndSet(
                              								grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_0_0(),
                              								current);
                              						
                            }

                            }

                            // InternalZeroKnowledge.g:679:6: ( (lv_operation_2_0= '!=' ) )
                            // InternalZeroKnowledge.g:680:7: (lv_operation_2_0= '!=' )
                            {
                            // InternalZeroKnowledge.g:680:7: (lv_operation_2_0= '!=' )
                            // InternalZeroKnowledge.g:681:8: lv_operation_2_0= '!='
                            {
                            lv_operation_2_0=(Token)match(input,18,FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								newLeafNode(lv_operation_2_0, grammarAccess.getComparisonAccess().getOperationExclamationMarkEqualsSignKeyword_1_0_0_1_0());
                              							
                            }
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElement(grammarAccess.getComparisonRule());
                              								}
                              								setWithLastConsumed(current, "operation", lv_operation_2_0, "!=");
                              							
                            }

                            }


                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalZeroKnowledge.g:695:5: ( () ( (lv_operation_4_0= '=' ) ) )
                            {
                            // InternalZeroKnowledge.g:695:5: ( () ( (lv_operation_4_0= '=' ) ) )
                            // InternalZeroKnowledge.g:696:6: () ( (lv_operation_4_0= '=' ) )
                            {
                            // InternalZeroKnowledge.g:696:6: ()
                            // InternalZeroKnowledge.g:697:7: 
                            {
                            if ( state.backtracking==0 ) {

                              							current = forceCreateModelElementAndSet(
                              								grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_1_0(),
                              								current);
                              						
                            }

                            }

                            // InternalZeroKnowledge.g:703:6: ( (lv_operation_4_0= '=' ) )
                            // InternalZeroKnowledge.g:704:7: (lv_operation_4_0= '=' )
                            {
                            // InternalZeroKnowledge.g:704:7: (lv_operation_4_0= '=' )
                            // InternalZeroKnowledge.g:705:8: lv_operation_4_0= '='
                            {
                            lv_operation_4_0=(Token)match(input,19,FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								newLeafNode(lv_operation_4_0, grammarAccess.getComparisonAccess().getOperationEqualsSignKeyword_1_0_1_1_0());
                              							
                            }
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElement(grammarAccess.getComparisonRule());
                              								}
                              								setWithLastConsumed(current, "operation", lv_operation_4_0, "=");
                              							
                            }

                            }


                            }


                            }


                            }
                            break;
                        case 3 :
                            // InternalZeroKnowledge.g:719:5: ( () ( (lv_operation_6_0= '>=' ) ) )
                            {
                            // InternalZeroKnowledge.g:719:5: ( () ( (lv_operation_6_0= '>=' ) ) )
                            // InternalZeroKnowledge.g:720:6: () ( (lv_operation_6_0= '>=' ) )
                            {
                            // InternalZeroKnowledge.g:720:6: ()
                            // InternalZeroKnowledge.g:721:7: 
                            {
                            if ( state.backtracking==0 ) {

                              							current = forceCreateModelElementAndSet(
                              								grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_2_0(),
                              								current);
                              						
                            }

                            }

                            // InternalZeroKnowledge.g:727:6: ( (lv_operation_6_0= '>=' ) )
                            // InternalZeroKnowledge.g:728:7: (lv_operation_6_0= '>=' )
                            {
                            // InternalZeroKnowledge.g:728:7: (lv_operation_6_0= '>=' )
                            // InternalZeroKnowledge.g:729:8: lv_operation_6_0= '>='
                            {
                            lv_operation_6_0=(Token)match(input,20,FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								newLeafNode(lv_operation_6_0, grammarAccess.getComparisonAccess().getOperationGreaterThanSignEqualsSignKeyword_1_0_2_1_0());
                              							
                            }
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElement(grammarAccess.getComparisonRule());
                              								}
                              								setWithLastConsumed(current, "operation", lv_operation_6_0, ">=");
                              							
                            }

                            }


                            }


                            }


                            }
                            break;
                        case 4 :
                            // InternalZeroKnowledge.g:743:5: ( () ( (lv_operation_8_0= '<=' ) ) )
                            {
                            // InternalZeroKnowledge.g:743:5: ( () ( (lv_operation_8_0= '<=' ) ) )
                            // InternalZeroKnowledge.g:744:6: () ( (lv_operation_8_0= '<=' ) )
                            {
                            // InternalZeroKnowledge.g:744:6: ()
                            // InternalZeroKnowledge.g:745:7: 
                            {
                            if ( state.backtracking==0 ) {

                              							current = forceCreateModelElementAndSet(
                              								grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_3_0(),
                              								current);
                              						
                            }

                            }

                            // InternalZeroKnowledge.g:751:6: ( (lv_operation_8_0= '<=' ) )
                            // InternalZeroKnowledge.g:752:7: (lv_operation_8_0= '<=' )
                            {
                            // InternalZeroKnowledge.g:752:7: (lv_operation_8_0= '<=' )
                            // InternalZeroKnowledge.g:753:8: lv_operation_8_0= '<='
                            {
                            lv_operation_8_0=(Token)match(input,21,FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								newLeafNode(lv_operation_8_0, grammarAccess.getComparisonAccess().getOperationLessThanSignEqualsSignKeyword_1_0_3_1_0());
                              							
                            }
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElement(grammarAccess.getComparisonRule());
                              								}
                              								setWithLastConsumed(current, "operation", lv_operation_8_0, "<=");
                              							
                            }

                            }


                            }


                            }


                            }
                            break;
                        case 5 :
                            // InternalZeroKnowledge.g:767:5: ( () ( (lv_operation_10_0= '>' ) ) )
                            {
                            // InternalZeroKnowledge.g:767:5: ( () ( (lv_operation_10_0= '>' ) ) )
                            // InternalZeroKnowledge.g:768:6: () ( (lv_operation_10_0= '>' ) )
                            {
                            // InternalZeroKnowledge.g:768:6: ()
                            // InternalZeroKnowledge.g:769:7: 
                            {
                            if ( state.backtracking==0 ) {

                              							current = forceCreateModelElementAndSet(
                              								grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_4_0(),
                              								current);
                              						
                            }

                            }

                            // InternalZeroKnowledge.g:775:6: ( (lv_operation_10_0= '>' ) )
                            // InternalZeroKnowledge.g:776:7: (lv_operation_10_0= '>' )
                            {
                            // InternalZeroKnowledge.g:776:7: (lv_operation_10_0= '>' )
                            // InternalZeroKnowledge.g:777:8: lv_operation_10_0= '>'
                            {
                            lv_operation_10_0=(Token)match(input,22,FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								newLeafNode(lv_operation_10_0, grammarAccess.getComparisonAccess().getOperationGreaterThanSignKeyword_1_0_4_1_0());
                              							
                            }
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElement(grammarAccess.getComparisonRule());
                              								}
                              								setWithLastConsumed(current, "operation", lv_operation_10_0, ">");
                              							
                            }

                            }


                            }


                            }


                            }
                            break;
                        case 6 :
                            // InternalZeroKnowledge.g:791:5: ( () ( (lv_operation_12_0= '<' ) ) )
                            {
                            // InternalZeroKnowledge.g:791:5: ( () ( (lv_operation_12_0= '<' ) ) )
                            // InternalZeroKnowledge.g:792:6: () ( (lv_operation_12_0= '<' ) )
                            {
                            // InternalZeroKnowledge.g:792:6: ()
                            // InternalZeroKnowledge.g:793:7: 
                            {
                            if ( state.backtracking==0 ) {

                              							current = forceCreateModelElementAndSet(
                              								grammarAccess.getComparisonAccess().getComparisonLeftAction_1_0_5_0(),
                              								current);
                              						
                            }

                            }

                            // InternalZeroKnowledge.g:799:6: ( (lv_operation_12_0= '<' ) )
                            // InternalZeroKnowledge.g:800:7: (lv_operation_12_0= '<' )
                            {
                            // InternalZeroKnowledge.g:800:7: (lv_operation_12_0= '<' )
                            // InternalZeroKnowledge.g:801:8: lv_operation_12_0= '<'
                            {
                            lv_operation_12_0=(Token)match(input,23,FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								newLeafNode(lv_operation_12_0, grammarAccess.getComparisonAccess().getOperationLessThanSignKeyword_1_0_5_1_0());
                              							
                            }
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElement(grammarAccess.getComparisonRule());
                              								}
                              								setWithLastConsumed(current, "operation", lv_operation_12_0, "<");
                              							
                            }

                            }


                            }


                            }


                            }
                            break;

                    }

                    // InternalZeroKnowledge.g:815:4: ( (lv_right_13_0= ruleSum ) )
                    // InternalZeroKnowledge.g:816:5: (lv_right_13_0= ruleSum )
                    {
                    // InternalZeroKnowledge.g:816:5: (lv_right_13_0= ruleSum )
                    // InternalZeroKnowledge.g:817:6: lv_right_13_0= ruleSum
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getComparisonAccess().getRightSumParserRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_right_13_0=ruleSum();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getComparisonRule());
                      						}
                      						set(
                      							current,
                      							"right",
                      							lv_right_13_0,
                      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Sum");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComparison"


    // $ANTLR start "entryRuleSum"
    // InternalZeroKnowledge.g:839:1: entryRuleSum returns [EObject current=null] : iv_ruleSum= ruleSum EOF ;
    public final EObject entryRuleSum() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSum = null;


        try {
            // InternalZeroKnowledge.g:839:44: (iv_ruleSum= ruleSum EOF )
            // InternalZeroKnowledge.g:840:2: iv_ruleSum= ruleSum EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSumRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSum=ruleSum();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSum; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSum"


    // $ANTLR start "ruleSum"
    // InternalZeroKnowledge.g:846:1: ruleSum returns [EObject current=null] : (this_Product_0= ruleProduct ( ( ( () ( (lv_operation_2_0= '+' ) ) ) | ( () ( (lv_operation_4_0= '-' ) ) ) ) ( (lv_right_5_0= ruleProduct ) ) )* ) ;
    public final EObject ruleSum() throws RecognitionException {
        EObject current = null;

        Token lv_operation_2_0=null;
        Token lv_operation_4_0=null;
        EObject this_Product_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:852:2: ( (this_Product_0= ruleProduct ( ( ( () ( (lv_operation_2_0= '+' ) ) ) | ( () ( (lv_operation_4_0= '-' ) ) ) ) ( (lv_right_5_0= ruleProduct ) ) )* ) )
            // InternalZeroKnowledge.g:853:2: (this_Product_0= ruleProduct ( ( ( () ( (lv_operation_2_0= '+' ) ) ) | ( () ( (lv_operation_4_0= '-' ) ) ) ) ( (lv_right_5_0= ruleProduct ) ) )* )
            {
            // InternalZeroKnowledge.g:853:2: (this_Product_0= ruleProduct ( ( ( () ( (lv_operation_2_0= '+' ) ) ) | ( () ( (lv_operation_4_0= '-' ) ) ) ) ( (lv_right_5_0= ruleProduct ) ) )* )
            // InternalZeroKnowledge.g:854:3: this_Product_0= ruleProduct ( ( ( () ( (lv_operation_2_0= '+' ) ) ) | ( () ( (lv_operation_4_0= '-' ) ) ) ) ( (lv_right_5_0= ruleProduct ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getSumAccess().getProductParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_16);
            this_Product_0=ruleProduct();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Product_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalZeroKnowledge.g:862:3: ( ( ( () ( (lv_operation_2_0= '+' ) ) ) | ( () ( (lv_operation_4_0= '-' ) ) ) ) ( (lv_right_5_0= ruleProduct ) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=24 && LA15_0<=25)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalZeroKnowledge.g:863:4: ( ( () ( (lv_operation_2_0= '+' ) ) ) | ( () ( (lv_operation_4_0= '-' ) ) ) ) ( (lv_right_5_0= ruleProduct ) )
            	    {
            	    // InternalZeroKnowledge.g:863:4: ( ( () ( (lv_operation_2_0= '+' ) ) ) | ( () ( (lv_operation_4_0= '-' ) ) ) )
            	    int alt14=2;
            	    int LA14_0 = input.LA(1);

            	    if ( (LA14_0==24) ) {
            	        alt14=1;
            	    }
            	    else if ( (LA14_0==25) ) {
            	        alt14=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 14, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt14) {
            	        case 1 :
            	            // InternalZeroKnowledge.g:864:5: ( () ( (lv_operation_2_0= '+' ) ) )
            	            {
            	            // InternalZeroKnowledge.g:864:5: ( () ( (lv_operation_2_0= '+' ) ) )
            	            // InternalZeroKnowledge.g:865:6: () ( (lv_operation_2_0= '+' ) )
            	            {
            	            // InternalZeroKnowledge.g:865:6: ()
            	            // InternalZeroKnowledge.g:866:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getSumAccess().getSumLeftAction_1_0_0_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            // InternalZeroKnowledge.g:872:6: ( (lv_operation_2_0= '+' ) )
            	            // InternalZeroKnowledge.g:873:7: (lv_operation_2_0= '+' )
            	            {
            	            // InternalZeroKnowledge.g:873:7: (lv_operation_2_0= '+' )
            	            // InternalZeroKnowledge.g:874:8: lv_operation_2_0= '+'
            	            {
            	            lv_operation_2_0=(Token)match(input,24,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              								newLeafNode(lv_operation_2_0, grammarAccess.getSumAccess().getOperationPlusSignKeyword_1_0_0_1_0());
            	              							
            	            }
            	            if ( state.backtracking==0 ) {

            	              								if (current==null) {
            	              									current = createModelElement(grammarAccess.getSumRule());
            	              								}
            	              								setWithLastConsumed(current, "operation", lv_operation_2_0, "+");
            	              							
            	            }

            	            }


            	            }


            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalZeroKnowledge.g:888:5: ( () ( (lv_operation_4_0= '-' ) ) )
            	            {
            	            // InternalZeroKnowledge.g:888:5: ( () ( (lv_operation_4_0= '-' ) ) )
            	            // InternalZeroKnowledge.g:889:6: () ( (lv_operation_4_0= '-' ) )
            	            {
            	            // InternalZeroKnowledge.g:889:6: ()
            	            // InternalZeroKnowledge.g:890:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getSumAccess().getSumLeftAction_1_0_1_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            // InternalZeroKnowledge.g:896:6: ( (lv_operation_4_0= '-' ) )
            	            // InternalZeroKnowledge.g:897:7: (lv_operation_4_0= '-' )
            	            {
            	            // InternalZeroKnowledge.g:897:7: (lv_operation_4_0= '-' )
            	            // InternalZeroKnowledge.g:898:8: lv_operation_4_0= '-'
            	            {
            	            lv_operation_4_0=(Token)match(input,25,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              								newLeafNode(lv_operation_4_0, grammarAccess.getSumAccess().getOperationHyphenMinusKeyword_1_0_1_1_0());
            	              							
            	            }
            	            if ( state.backtracking==0 ) {

            	              								if (current==null) {
            	              									current = createModelElement(grammarAccess.getSumRule());
            	              								}
            	              								setWithLastConsumed(current, "operation", lv_operation_4_0, "-");
            	              							
            	            }

            	            }


            	            }


            	            }


            	            }
            	            break;

            	    }

            	    // InternalZeroKnowledge.g:912:4: ( (lv_right_5_0= ruleProduct ) )
            	    // InternalZeroKnowledge.g:913:5: (lv_right_5_0= ruleProduct )
            	    {
            	    // InternalZeroKnowledge.g:913:5: (lv_right_5_0= ruleProduct )
            	    // InternalZeroKnowledge.g:914:6: lv_right_5_0= ruleProduct
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getSumAccess().getRightProductParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_16);
            	    lv_right_5_0=ruleProduct();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getSumRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_5_0,
            	      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Product");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSum"


    // $ANTLR start "entryRuleProduct"
    // InternalZeroKnowledge.g:936:1: entryRuleProduct returns [EObject current=null] : iv_ruleProduct= ruleProduct EOF ;
    public final EObject entryRuleProduct() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProduct = null;


        try {
            // InternalZeroKnowledge.g:936:48: (iv_ruleProduct= ruleProduct EOF )
            // InternalZeroKnowledge.g:937:2: iv_ruleProduct= ruleProduct EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getProductRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleProduct=ruleProduct();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleProduct; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProduct"


    // $ANTLR start "ruleProduct"
    // InternalZeroKnowledge.g:943:1: ruleProduct returns [EObject current=null] : (this_Power_0= rulePower ( ( ( () ( (lv_operation_2_0= '*' ) ) ) | ( () ( (lv_operation_4_0= '/' ) ) ) ) ( (lv_right_5_0= rulePower ) ) )* ) ;
    public final EObject ruleProduct() throws RecognitionException {
        EObject current = null;

        Token lv_operation_2_0=null;
        Token lv_operation_4_0=null;
        EObject this_Power_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:949:2: ( (this_Power_0= rulePower ( ( ( () ( (lv_operation_2_0= '*' ) ) ) | ( () ( (lv_operation_4_0= '/' ) ) ) ) ( (lv_right_5_0= rulePower ) ) )* ) )
            // InternalZeroKnowledge.g:950:2: (this_Power_0= rulePower ( ( ( () ( (lv_operation_2_0= '*' ) ) ) | ( () ( (lv_operation_4_0= '/' ) ) ) ) ( (lv_right_5_0= rulePower ) ) )* )
            {
            // InternalZeroKnowledge.g:950:2: (this_Power_0= rulePower ( ( ( () ( (lv_operation_2_0= '*' ) ) ) | ( () ( (lv_operation_4_0= '/' ) ) ) ) ( (lv_right_5_0= rulePower ) ) )* )
            // InternalZeroKnowledge.g:951:3: this_Power_0= rulePower ( ( ( () ( (lv_operation_2_0= '*' ) ) ) | ( () ( (lv_operation_4_0= '/' ) ) ) ) ( (lv_right_5_0= rulePower ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getProductAccess().getPowerParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_17);
            this_Power_0=rulePower();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Power_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalZeroKnowledge.g:959:3: ( ( ( () ( (lv_operation_2_0= '*' ) ) ) | ( () ( (lv_operation_4_0= '/' ) ) ) ) ( (lv_right_5_0= rulePower ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>=26 && LA17_0<=27)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalZeroKnowledge.g:960:4: ( ( () ( (lv_operation_2_0= '*' ) ) ) | ( () ( (lv_operation_4_0= '/' ) ) ) ) ( (lv_right_5_0= rulePower ) )
            	    {
            	    // InternalZeroKnowledge.g:960:4: ( ( () ( (lv_operation_2_0= '*' ) ) ) | ( () ( (lv_operation_4_0= '/' ) ) ) )
            	    int alt16=2;
            	    int LA16_0 = input.LA(1);

            	    if ( (LA16_0==26) ) {
            	        alt16=1;
            	    }
            	    else if ( (LA16_0==27) ) {
            	        alt16=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 16, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt16) {
            	        case 1 :
            	            // InternalZeroKnowledge.g:961:5: ( () ( (lv_operation_2_0= '*' ) ) )
            	            {
            	            // InternalZeroKnowledge.g:961:5: ( () ( (lv_operation_2_0= '*' ) ) )
            	            // InternalZeroKnowledge.g:962:6: () ( (lv_operation_2_0= '*' ) )
            	            {
            	            // InternalZeroKnowledge.g:962:6: ()
            	            // InternalZeroKnowledge.g:963:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getProductAccess().getProductLeftAction_1_0_0_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            // InternalZeroKnowledge.g:969:6: ( (lv_operation_2_0= '*' ) )
            	            // InternalZeroKnowledge.g:970:7: (lv_operation_2_0= '*' )
            	            {
            	            // InternalZeroKnowledge.g:970:7: (lv_operation_2_0= '*' )
            	            // InternalZeroKnowledge.g:971:8: lv_operation_2_0= '*'
            	            {
            	            lv_operation_2_0=(Token)match(input,26,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              								newLeafNode(lv_operation_2_0, grammarAccess.getProductAccess().getOperationAsteriskKeyword_1_0_0_1_0());
            	              							
            	            }
            	            if ( state.backtracking==0 ) {

            	              								if (current==null) {
            	              									current = createModelElement(grammarAccess.getProductRule());
            	              								}
            	              								setWithLastConsumed(current, "operation", lv_operation_2_0, "*");
            	              							
            	            }

            	            }


            	            }


            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalZeroKnowledge.g:985:5: ( () ( (lv_operation_4_0= '/' ) ) )
            	            {
            	            // InternalZeroKnowledge.g:985:5: ( () ( (lv_operation_4_0= '/' ) ) )
            	            // InternalZeroKnowledge.g:986:6: () ( (lv_operation_4_0= '/' ) )
            	            {
            	            // InternalZeroKnowledge.g:986:6: ()
            	            // InternalZeroKnowledge.g:987:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getProductAccess().getProductLeftAction_1_0_1_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            // InternalZeroKnowledge.g:993:6: ( (lv_operation_4_0= '/' ) )
            	            // InternalZeroKnowledge.g:994:7: (lv_operation_4_0= '/' )
            	            {
            	            // InternalZeroKnowledge.g:994:7: (lv_operation_4_0= '/' )
            	            // InternalZeroKnowledge.g:995:8: lv_operation_4_0= '/'
            	            {
            	            lv_operation_4_0=(Token)match(input,27,FOLLOW_4); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              								newLeafNode(lv_operation_4_0, grammarAccess.getProductAccess().getOperationSolidusKeyword_1_0_1_1_0());
            	              							
            	            }
            	            if ( state.backtracking==0 ) {

            	              								if (current==null) {
            	              									current = createModelElement(grammarAccess.getProductRule());
            	              								}
            	              								setWithLastConsumed(current, "operation", lv_operation_4_0, "/");
            	              							
            	            }

            	            }


            	            }


            	            }


            	            }
            	            break;

            	    }

            	    // InternalZeroKnowledge.g:1009:4: ( (lv_right_5_0= rulePower ) )
            	    // InternalZeroKnowledge.g:1010:5: (lv_right_5_0= rulePower )
            	    {
            	    // InternalZeroKnowledge.g:1010:5: (lv_right_5_0= rulePower )
            	    // InternalZeroKnowledge.g:1011:6: lv_right_5_0= rulePower
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getProductAccess().getRightPowerParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_17);
            	    lv_right_5_0=rulePower();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getProductRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_5_0,
            	      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Power");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProduct"


    // $ANTLR start "entryRulePower"
    // InternalZeroKnowledge.g:1033:1: entryRulePower returns [EObject current=null] : iv_rulePower= rulePower EOF ;
    public final EObject entryRulePower() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePower = null;


        try {
            // InternalZeroKnowledge.g:1033:46: (iv_rulePower= rulePower EOF )
            // InternalZeroKnowledge.g:1034:2: iv_rulePower= rulePower EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPowerRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePower=rulePower();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePower; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePower"


    // $ANTLR start "rulePower"
    // InternalZeroKnowledge.g:1040:1: rulePower returns [EObject current=null] : (this_Construct_0= ruleConstruct ( () ( (lv_operation_2_0= '^' ) ) ( (lv_right_3_0= rulePower ) ) )? ) ;
    public final EObject rulePower() throws RecognitionException {
        EObject current = null;

        Token lv_operation_2_0=null;
        EObject this_Construct_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:1046:2: ( (this_Construct_0= ruleConstruct ( () ( (lv_operation_2_0= '^' ) ) ( (lv_right_3_0= rulePower ) ) )? ) )
            // InternalZeroKnowledge.g:1047:2: (this_Construct_0= ruleConstruct ( () ( (lv_operation_2_0= '^' ) ) ( (lv_right_3_0= rulePower ) ) )? )
            {
            // InternalZeroKnowledge.g:1047:2: (this_Construct_0= ruleConstruct ( () ( (lv_operation_2_0= '^' ) ) ( (lv_right_3_0= rulePower ) ) )? )
            // InternalZeroKnowledge.g:1048:3: this_Construct_0= ruleConstruct ( () ( (lv_operation_2_0= '^' ) ) ( (lv_right_3_0= rulePower ) ) )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getPowerAccess().getConstructParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_18);
            this_Construct_0=ruleConstruct();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Construct_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalZeroKnowledge.g:1056:3: ( () ( (lv_operation_2_0= '^' ) ) ( (lv_right_3_0= rulePower ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==28) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalZeroKnowledge.g:1057:4: () ( (lv_operation_2_0= '^' ) ) ( (lv_right_3_0= rulePower ) )
                    {
                    // InternalZeroKnowledge.g:1057:4: ()
                    // InternalZeroKnowledge.g:1058:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElementAndSet(
                      						grammarAccess.getPowerAccess().getPowerLeftAction_1_0(),
                      						current);
                      				
                    }

                    }

                    // InternalZeroKnowledge.g:1064:4: ( (lv_operation_2_0= '^' ) )
                    // InternalZeroKnowledge.g:1065:5: (lv_operation_2_0= '^' )
                    {
                    // InternalZeroKnowledge.g:1065:5: (lv_operation_2_0= '^' )
                    // InternalZeroKnowledge.g:1066:6: lv_operation_2_0= '^'
                    {
                    lv_operation_2_0=(Token)match(input,28,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_operation_2_0, grammarAccess.getPowerAccess().getOperationCircumflexAccentKeyword_1_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getPowerRule());
                      						}
                      						setWithLastConsumed(current, "operation", lv_operation_2_0, "^");
                      					
                    }

                    }


                    }

                    // InternalZeroKnowledge.g:1078:4: ( (lv_right_3_0= rulePower ) )
                    // InternalZeroKnowledge.g:1079:5: (lv_right_3_0= rulePower )
                    {
                    // InternalZeroKnowledge.g:1079:5: (lv_right_3_0= rulePower )
                    // InternalZeroKnowledge.g:1080:6: lv_right_3_0= rulePower
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getPowerAccess().getRightPowerParserRuleCall_1_2_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_right_3_0=rulePower();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getPowerRule());
                      						}
                      						set(
                      							current,
                      							"right",
                      							lv_right_3_0,
                      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Power");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePower"


    // $ANTLR start "entryRuleConstruct"
    // InternalZeroKnowledge.g:1102:1: entryRuleConstruct returns [EObject current=null] : iv_ruleConstruct= ruleConstruct EOF ;
    public final EObject entryRuleConstruct() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstruct = null;


        try {
            // InternalZeroKnowledge.g:1102:50: (iv_ruleConstruct= ruleConstruct EOF )
            // InternalZeroKnowledge.g:1103:2: iv_ruleConstruct= ruleConstruct EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstructRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConstruct=ruleConstruct();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstruct; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstruct"


    // $ANTLR start "ruleConstruct"
    // InternalZeroKnowledge.g:1109:1: ruleConstruct returns [EObject current=null] : (this_StringLiteral_0= ruleStringLiteral | ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=>this_Tuple_1= ruleTuple ) | this_Negative_2= ruleNegative ) ;
    public final EObject ruleConstruct() throws RecognitionException {
        EObject current = null;

        EObject this_StringLiteral_0 = null;

        EObject this_Tuple_1 = null;

        EObject this_Negative_2 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:1115:2: ( (this_StringLiteral_0= ruleStringLiteral | ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=>this_Tuple_1= ruleTuple ) | this_Negative_2= ruleNegative ) )
            // InternalZeroKnowledge.g:1116:2: (this_StringLiteral_0= ruleStringLiteral | ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=>this_Tuple_1= ruleTuple ) | this_Negative_2= ruleNegative )
            {
            // InternalZeroKnowledge.g:1116:2: (this_StringLiteral_0= ruleStringLiteral | ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=>this_Tuple_1= ruleTuple ) | this_Negative_2= ruleNegative )
            int alt19=3;
            switch ( input.LA(1) ) {
            case RULE_STRING_LITERAL:
                {
                alt19=1;
                }
                break;
            case 13:
                {
                int LA19_2 = input.LA(2);

                if ( (synpred1_InternalZeroKnowledge()) ) {
                    alt19=2;
                }
                else if ( (true) ) {
                    alt19=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_IDENTIFIER:
            case RULE_INT:
            case 25:
                {
                alt19=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // InternalZeroKnowledge.g:1117:3: this_StringLiteral_0= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getConstructAccess().getStringLiteralParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_StringLiteral_0=ruleStringLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_StringLiteral_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalZeroKnowledge.g:1126:3: ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=>this_Tuple_1= ruleTuple )
                    {
                    // InternalZeroKnowledge.g:1126:3: ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=>this_Tuple_1= ruleTuple )
                    // InternalZeroKnowledge.g:1127:4: ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=>this_Tuple_1= ruleTuple
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getConstructAccess().getTupleParserRuleCall_1());
                      			
                    }
                    pushFollow(FOLLOW_2);
                    this_Tuple_1=ruleTuple();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_Tuple_1;
                      				afterParserOrEnumRuleCall();
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalZeroKnowledge.g:1149:3: this_Negative_2= ruleNegative
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getConstructAccess().getNegativeParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Negative_2=ruleNegative();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Negative_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstruct"


    // $ANTLR start "entryRuleStringLiteral"
    // InternalZeroKnowledge.g:1161:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // InternalZeroKnowledge.g:1161:54: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalZeroKnowledge.g:1162:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringLiteral"


    // $ANTLR start "ruleStringLiteral"
    // InternalZeroKnowledge.g:1168:1: ruleStringLiteral returns [EObject current=null] : ( (lv_value_0_0= RULE_STRING_LITERAL ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalZeroKnowledge.g:1174:2: ( ( (lv_value_0_0= RULE_STRING_LITERAL ) ) )
            // InternalZeroKnowledge.g:1175:2: ( (lv_value_0_0= RULE_STRING_LITERAL ) )
            {
            // InternalZeroKnowledge.g:1175:2: ( (lv_value_0_0= RULE_STRING_LITERAL ) )
            // InternalZeroKnowledge.g:1176:3: (lv_value_0_0= RULE_STRING_LITERAL )
            {
            // InternalZeroKnowledge.g:1176:3: (lv_value_0_0= RULE_STRING_LITERAL )
            // InternalZeroKnowledge.g:1177:4: lv_value_0_0= RULE_STRING_LITERAL
            {
            lv_value_0_0=(Token)match(input,RULE_STRING_LITERAL,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_value_0_0, grammarAccess.getStringLiteralAccess().getValueSTRING_LITERALTerminalRuleCall_0());
              			
            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getStringLiteralRule());
              				}
              				setWithLastConsumed(
              					current,
              					"value",
              					lv_value_0_0,
              					"de.upb.crypto.zeroknowledge.ZeroKnowledge.STRING_LITERAL");
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringLiteral"


    // $ANTLR start "entryRuleTuple"
    // InternalZeroKnowledge.g:1196:1: entryRuleTuple returns [EObject current=null] : iv_ruleTuple= ruleTuple EOF ;
    public final EObject entryRuleTuple() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTuple = null;


        try {
            // InternalZeroKnowledge.g:1196:46: (iv_ruleTuple= ruleTuple EOF )
            // InternalZeroKnowledge.g:1197:2: iv_ruleTuple= ruleTuple EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTupleRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTuple=ruleTuple();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTuple; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTuple"


    // $ANTLR start "ruleTuple"
    // InternalZeroKnowledge.g:1203:1: ruleTuple returns [EObject current=null] : ( ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=> ( () otherlv_1= '(' ( (lv_elements_2_0= ruleConjunction ) ) otherlv_3= ',' ) ) ( (lv_elements_4_0= ruleConjunction ) ) (otherlv_5= ',' ( (lv_elements_6_0= ruleConjunction ) ) )* otherlv_7= ')' ) ;
    public final EObject ruleTuple() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;

        EObject lv_elements_6_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:1209:2: ( ( ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=> ( () otherlv_1= '(' ( (lv_elements_2_0= ruleConjunction ) ) otherlv_3= ',' ) ) ( (lv_elements_4_0= ruleConjunction ) ) (otherlv_5= ',' ( (lv_elements_6_0= ruleConjunction ) ) )* otherlv_7= ')' ) )
            // InternalZeroKnowledge.g:1210:2: ( ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=> ( () otherlv_1= '(' ( (lv_elements_2_0= ruleConjunction ) ) otherlv_3= ',' ) ) ( (lv_elements_4_0= ruleConjunction ) ) (otherlv_5= ',' ( (lv_elements_6_0= ruleConjunction ) ) )* otherlv_7= ')' )
            {
            // InternalZeroKnowledge.g:1210:2: ( ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=> ( () otherlv_1= '(' ( (lv_elements_2_0= ruleConjunction ) ) otherlv_3= ',' ) ) ( (lv_elements_4_0= ruleConjunction ) ) (otherlv_5= ',' ( (lv_elements_6_0= ruleConjunction ) ) )* otherlv_7= ')' )
            // InternalZeroKnowledge.g:1211:3: ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=> ( () otherlv_1= '(' ( (lv_elements_2_0= ruleConjunction ) ) otherlv_3= ',' ) ) ( (lv_elements_4_0= ruleConjunction ) ) (otherlv_5= ',' ( (lv_elements_6_0= ruleConjunction ) ) )* otherlv_7= ')'
            {
            // InternalZeroKnowledge.g:1211:3: ( ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=> ( () otherlv_1= '(' ( (lv_elements_2_0= ruleConjunction ) ) otherlv_3= ',' ) )
            // InternalZeroKnowledge.g:1212:4: ( ( () '(' ( ( ruleConjunction ) ) ',' ) )=> ( () otherlv_1= '(' ( (lv_elements_2_0= ruleConjunction ) ) otherlv_3= ',' )
            {
            // InternalZeroKnowledge.g:1224:4: ( () otherlv_1= '(' ( (lv_elements_2_0= ruleConjunction ) ) otherlv_3= ',' )
            // InternalZeroKnowledge.g:1225:5: () otherlv_1= '(' ( (lv_elements_2_0= ruleConjunction ) ) otherlv_3= ','
            {
            // InternalZeroKnowledge.g:1225:5: ()
            // InternalZeroKnowledge.g:1226:6: 
            {
            if ( state.backtracking==0 ) {

              						current = forceCreateModelElement(
              							grammarAccess.getTupleAccess().getTupleAction_0_0_0(),
              							current);
              					
            }

            }

            otherlv_1=(Token)match(input,13,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getTupleAccess().getLeftParenthesisKeyword_0_0_1());
              				
            }
            // InternalZeroKnowledge.g:1236:5: ( (lv_elements_2_0= ruleConjunction ) )
            // InternalZeroKnowledge.g:1237:6: (lv_elements_2_0= ruleConjunction )
            {
            // InternalZeroKnowledge.g:1237:6: (lv_elements_2_0= ruleConjunction )
            // InternalZeroKnowledge.g:1238:7: lv_elements_2_0= ruleConjunction
            {
            if ( state.backtracking==0 ) {

              							newCompositeNode(grammarAccess.getTupleAccess().getElementsConjunctionParserRuleCall_0_0_2_0());
              						
            }
            pushFollow(FOLLOW_19);
            lv_elements_2_0=ruleConjunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              							if (current==null) {
              								current = createModelElementForParent(grammarAccess.getTupleRule());
              							}
              							add(
              								current,
              								"elements",
              								lv_elements_2_0,
              								"de.upb.crypto.zeroknowledge.ZeroKnowledge.Conjunction");
              							afterParserOrEnumRuleCall();
              						
            }

            }


            }

            otherlv_3=(Token)match(input,14,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_3, grammarAccess.getTupleAccess().getCommaKeyword_0_0_3());
              				
            }

            }


            }

            // InternalZeroKnowledge.g:1261:3: ( (lv_elements_4_0= ruleConjunction ) )
            // InternalZeroKnowledge.g:1262:4: (lv_elements_4_0= ruleConjunction )
            {
            // InternalZeroKnowledge.g:1262:4: (lv_elements_4_0= ruleConjunction )
            // InternalZeroKnowledge.g:1263:5: lv_elements_4_0= ruleConjunction
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTupleAccess().getElementsConjunctionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_11);
            lv_elements_4_0=ruleConjunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getTupleRule());
              					}
              					add(
              						current,
              						"elements",
              						lv_elements_4_0,
              						"de.upb.crypto.zeroknowledge.ZeroKnowledge.Conjunction");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalZeroKnowledge.g:1280:3: (otherlv_5= ',' ( (lv_elements_6_0= ruleConjunction ) ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==14) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalZeroKnowledge.g:1281:4: otherlv_5= ',' ( (lv_elements_6_0= ruleConjunction ) )
            	    {
            	    otherlv_5=(Token)match(input,14,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_5, grammarAccess.getTupleAccess().getCommaKeyword_2_0());
            	      			
            	    }
            	    // InternalZeroKnowledge.g:1285:4: ( (lv_elements_6_0= ruleConjunction ) )
            	    // InternalZeroKnowledge.g:1286:5: (lv_elements_6_0= ruleConjunction )
            	    {
            	    // InternalZeroKnowledge.g:1286:5: (lv_elements_6_0= ruleConjunction )
            	    // InternalZeroKnowledge.g:1287:6: lv_elements_6_0= ruleConjunction
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getTupleAccess().getElementsConjunctionParserRuleCall_2_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_11);
            	    lv_elements_6_0=ruleConjunction();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getTupleRule());
            	      						}
            	      						add(
            	      							current,
            	      							"elements",
            	      							lv_elements_6_0,
            	      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Conjunction");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            otherlv_7=(Token)match(input,15,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getTupleAccess().getRightParenthesisKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTuple"


    // $ANTLR start "entryRuleNegative"
    // InternalZeroKnowledge.g:1313:1: entryRuleNegative returns [EObject current=null] : iv_ruleNegative= ruleNegative EOF ;
    public final EObject entryRuleNegative() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNegative = null;


        try {
            // InternalZeroKnowledge.g:1313:49: (iv_ruleNegative= ruleNegative EOF )
            // InternalZeroKnowledge.g:1314:2: iv_ruleNegative= ruleNegative EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNegativeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNegative=ruleNegative();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNegative; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNegative"


    // $ANTLR start "ruleNegative"
    // InternalZeroKnowledge.g:1320:1: ruleNegative returns [EObject current=null] : ( ( () ( (lv_operation_1_0= '-' ) ) ( (lv_term_2_0= ruleValue ) ) ) | this_Value_3= ruleValue ) ;
    public final EObject ruleNegative() throws RecognitionException {
        EObject current = null;

        Token lv_operation_1_0=null;
        EObject lv_term_2_0 = null;

        EObject this_Value_3 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:1326:2: ( ( ( () ( (lv_operation_1_0= '-' ) ) ( (lv_term_2_0= ruleValue ) ) ) | this_Value_3= ruleValue ) )
            // InternalZeroKnowledge.g:1327:2: ( ( () ( (lv_operation_1_0= '-' ) ) ( (lv_term_2_0= ruleValue ) ) ) | this_Value_3= ruleValue )
            {
            // InternalZeroKnowledge.g:1327:2: ( ( () ( (lv_operation_1_0= '-' ) ) ( (lv_term_2_0= ruleValue ) ) ) | this_Value_3= ruleValue )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==25) ) {
                alt21=1;
            }
            else if ( (LA21_0==RULE_IDENTIFIER||LA21_0==RULE_INT||LA21_0==13) ) {
                alt21=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // InternalZeroKnowledge.g:1328:3: ( () ( (lv_operation_1_0= '-' ) ) ( (lv_term_2_0= ruleValue ) ) )
                    {
                    // InternalZeroKnowledge.g:1328:3: ( () ( (lv_operation_1_0= '-' ) ) ( (lv_term_2_0= ruleValue ) ) )
                    // InternalZeroKnowledge.g:1329:4: () ( (lv_operation_1_0= '-' ) ) ( (lv_term_2_0= ruleValue ) )
                    {
                    // InternalZeroKnowledge.g:1329:4: ()
                    // InternalZeroKnowledge.g:1330:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getNegativeAccess().getNegativeAction_0_0(),
                      						current);
                      				
                    }

                    }

                    // InternalZeroKnowledge.g:1336:4: ( (lv_operation_1_0= '-' ) )
                    // InternalZeroKnowledge.g:1337:5: (lv_operation_1_0= '-' )
                    {
                    // InternalZeroKnowledge.g:1337:5: (lv_operation_1_0= '-' )
                    // InternalZeroKnowledge.g:1338:6: lv_operation_1_0= '-'
                    {
                    lv_operation_1_0=(Token)match(input,25,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_operation_1_0, grammarAccess.getNegativeAccess().getOperationHyphenMinusKeyword_0_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getNegativeRule());
                      						}
                      						setWithLastConsumed(current, "operation", lv_operation_1_0, "-");
                      					
                    }

                    }


                    }

                    // InternalZeroKnowledge.g:1350:4: ( (lv_term_2_0= ruleValue ) )
                    // InternalZeroKnowledge.g:1351:5: (lv_term_2_0= ruleValue )
                    {
                    // InternalZeroKnowledge.g:1351:5: (lv_term_2_0= ruleValue )
                    // InternalZeroKnowledge.g:1352:6: lv_term_2_0= ruleValue
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getNegativeAccess().getTermValueParserRuleCall_0_2_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_term_2_0=ruleValue();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getNegativeRule());
                      						}
                      						set(
                      							current,
                      							"term",
                      							lv_term_2_0,
                      							"de.upb.crypto.zeroknowledge.ZeroKnowledge.Value");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalZeroKnowledge.g:1371:3: this_Value_3= ruleValue
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getNegativeAccess().getValueParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Value_3=ruleValue();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Value_3;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNegative"


    // $ANTLR start "entryRuleValue"
    // InternalZeroKnowledge.g:1383:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalZeroKnowledge.g:1383:46: (iv_ruleValue= ruleValue EOF )
            // InternalZeroKnowledge.g:1384:2: iv_ruleValue= ruleValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getValueRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleValue=ruleValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleValue; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalZeroKnowledge.g:1390:1: ruleValue returns [EObject current=null] : (this_FunctionCall_0= ruleFunctionCall | this_Variable_1= ruleVariable | this_NumberLiteral_2= ruleNumberLiteral | (otherlv_3= '(' this_Brackets_4= ruleBrackets otherlv_5= ')' ) ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject this_FunctionCall_0 = null;

        EObject this_Variable_1 = null;

        EObject this_NumberLiteral_2 = null;

        EObject this_Brackets_4 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:1396:2: ( (this_FunctionCall_0= ruleFunctionCall | this_Variable_1= ruleVariable | this_NumberLiteral_2= ruleNumberLiteral | (otherlv_3= '(' this_Brackets_4= ruleBrackets otherlv_5= ')' ) ) )
            // InternalZeroKnowledge.g:1397:2: (this_FunctionCall_0= ruleFunctionCall | this_Variable_1= ruleVariable | this_NumberLiteral_2= ruleNumberLiteral | (otherlv_3= '(' this_Brackets_4= ruleBrackets otherlv_5= ')' ) )
            {
            // InternalZeroKnowledge.g:1397:2: (this_FunctionCall_0= ruleFunctionCall | this_Variable_1= ruleVariable | this_NumberLiteral_2= ruleNumberLiteral | (otherlv_3= '(' this_Brackets_4= ruleBrackets otherlv_5= ')' ) )
            int alt22=4;
            switch ( input.LA(1) ) {
            case RULE_IDENTIFIER:
                {
                int LA22_1 = input.LA(2);

                if ( (LA22_1==EOF||LA22_1==10||LA22_1==12||(LA22_1>=14 && LA22_1<=28)) ) {
                    alt22=2;
                }
                else if ( (LA22_1==13) ) {
                    alt22=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
                {
                alt22=3;
                }
                break;
            case 13:
                {
                alt22=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // InternalZeroKnowledge.g:1398:3: this_FunctionCall_0= ruleFunctionCall
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getValueAccess().getFunctionCallParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_FunctionCall_0=ruleFunctionCall();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_FunctionCall_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalZeroKnowledge.g:1407:3: this_Variable_1= ruleVariable
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getValueAccess().getVariableParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Variable_1=ruleVariable();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Variable_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalZeroKnowledge.g:1416:3: this_NumberLiteral_2= ruleNumberLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getValueAccess().getNumberLiteralParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_NumberLiteral_2=ruleNumberLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_NumberLiteral_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalZeroKnowledge.g:1425:3: (otherlv_3= '(' this_Brackets_4= ruleBrackets otherlv_5= ')' )
                    {
                    // InternalZeroKnowledge.g:1425:3: (otherlv_3= '(' this_Brackets_4= ruleBrackets otherlv_5= ')' )
                    // InternalZeroKnowledge.g:1426:4: otherlv_3= '(' this_Brackets_4= ruleBrackets otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,13,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getValueAccess().getLeftParenthesisKeyword_3_0());
                      			
                    }
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getValueAccess().getBracketsParserRuleCall_3_1());
                      			
                    }
                    pushFollow(FOLLOW_20);
                    this_Brackets_4=ruleBrackets();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_Brackets_4;
                      				afterParserOrEnumRuleCall();
                      			
                    }
                    otherlv_5=(Token)match(input,15,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getValueAccess().getRightParenthesisKeyword_3_2());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleFunctionCall"
    // InternalZeroKnowledge.g:1447:1: entryRuleFunctionCall returns [EObject current=null] : iv_ruleFunctionCall= ruleFunctionCall EOF ;
    public final EObject entryRuleFunctionCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionCall = null;


        try {
            // InternalZeroKnowledge.g:1447:53: (iv_ruleFunctionCall= ruleFunctionCall EOF )
            // InternalZeroKnowledge.g:1448:2: iv_ruleFunctionCall= ruleFunctionCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionCallRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFunctionCall=ruleFunctionCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunctionCall; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunctionCall"


    // $ANTLR start "ruleFunctionCall"
    // InternalZeroKnowledge.g:1454:1: ruleFunctionCall returns [EObject current=null] : ( () ( ( ( RULE_IDENTIFIER )=> ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' ) ) ( ( (lv_arguments_3_0= ruleArgument ) ) (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )* )? otherlv_6= ')' ) ) ;
    public final EObject ruleFunctionCall() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_arguments_3_0 = null;

        EObject lv_arguments_5_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:1460:2: ( ( () ( ( ( RULE_IDENTIFIER )=> ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' ) ) ( ( (lv_arguments_3_0= ruleArgument ) ) (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )* )? otherlv_6= ')' ) ) )
            // InternalZeroKnowledge.g:1461:2: ( () ( ( ( RULE_IDENTIFIER )=> ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' ) ) ( ( (lv_arguments_3_0= ruleArgument ) ) (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )* )? otherlv_6= ')' ) )
            {
            // InternalZeroKnowledge.g:1461:2: ( () ( ( ( RULE_IDENTIFIER )=> ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' ) ) ( ( (lv_arguments_3_0= ruleArgument ) ) (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )* )? otherlv_6= ')' ) )
            // InternalZeroKnowledge.g:1462:3: () ( ( ( RULE_IDENTIFIER )=> ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' ) ) ( ( (lv_arguments_3_0= ruleArgument ) ) (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )* )? otherlv_6= ')' )
            {
            // InternalZeroKnowledge.g:1462:3: ()
            // InternalZeroKnowledge.g:1463:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getFunctionCallAccess().getFunctionCallAction_0(),
              					current);
              			
            }

            }

            // InternalZeroKnowledge.g:1469:3: ( ( ( RULE_IDENTIFIER )=> ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' ) ) ( ( (lv_arguments_3_0= ruleArgument ) ) (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )* )? otherlv_6= ')' )
            // InternalZeroKnowledge.g:1470:4: ( ( RULE_IDENTIFIER )=> ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' ) ) ( ( (lv_arguments_3_0= ruleArgument ) ) (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )* )? otherlv_6= ')'
            {
            // InternalZeroKnowledge.g:1470:4: ( ( RULE_IDENTIFIER )=> ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' ) )
            // InternalZeroKnowledge.g:1471:5: ( RULE_IDENTIFIER )=> ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' )
            {
            // InternalZeroKnowledge.g:1472:5: ( ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '(' )
            // InternalZeroKnowledge.g:1473:6: ( (lv_name_1_0= RULE_IDENTIFIER ) ) otherlv_2= '('
            {
            // InternalZeroKnowledge.g:1473:6: ( (lv_name_1_0= RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:1474:7: (lv_name_1_0= RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:1474:7: (lv_name_1_0= RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:1475:8: lv_name_1_0= RULE_IDENTIFIER
            {
            lv_name_1_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              								newLeafNode(lv_name_1_0, grammarAccess.getFunctionCallAccess().getNameIDENTIFIERTerminalRuleCall_1_0_0_0_0());
              							
            }
            if ( state.backtracking==0 ) {

              								if (current==null) {
              									current = createModelElement(grammarAccess.getFunctionCallRule());
              								}
              								setWithLastConsumed(
              									current,
              									"name",
              									lv_name_1_0,
              									"de.upb.crypto.zeroknowledge.ZeroKnowledge.IDENTIFIER");
              							
            }

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              						newLeafNode(otherlv_2, grammarAccess.getFunctionCallAccess().getLeftParenthesisKeyword_1_0_0_1());
              					
            }

            }


            }

            // InternalZeroKnowledge.g:1497:4: ( ( (lv_arguments_3_0= ruleArgument ) ) (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )* )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>=RULE_IDENTIFIER && LA24_0<=RULE_INT)||LA24_0==13||LA24_0==25) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalZeroKnowledge.g:1498:5: ( (lv_arguments_3_0= ruleArgument ) ) (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )*
                    {
                    // InternalZeroKnowledge.g:1498:5: ( (lv_arguments_3_0= ruleArgument ) )
                    // InternalZeroKnowledge.g:1499:6: (lv_arguments_3_0= ruleArgument )
                    {
                    // InternalZeroKnowledge.g:1499:6: (lv_arguments_3_0= ruleArgument )
                    // InternalZeroKnowledge.g:1500:7: lv_arguments_3_0= ruleArgument
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getFunctionCallAccess().getArgumentsArgumentParserRuleCall_1_1_0_0());
                      						
                    }
                    pushFollow(FOLLOW_11);
                    lv_arguments_3_0=ruleArgument();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getFunctionCallRule());
                      							}
                      							add(
                      								current,
                      								"arguments",
                      								lv_arguments_3_0,
                      								"de.upb.crypto.zeroknowledge.ZeroKnowledge.Argument");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }

                    // InternalZeroKnowledge.g:1517:5: (otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) ) )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==14) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // InternalZeroKnowledge.g:1518:6: otherlv_4= ',' ( (lv_arguments_5_0= ruleArgument ) )
                    	    {
                    	    otherlv_4=(Token)match(input,14,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      						newLeafNode(otherlv_4, grammarAccess.getFunctionCallAccess().getCommaKeyword_1_1_1_0());
                    	      					
                    	    }
                    	    // InternalZeroKnowledge.g:1522:6: ( (lv_arguments_5_0= ruleArgument ) )
                    	    // InternalZeroKnowledge.g:1523:7: (lv_arguments_5_0= ruleArgument )
                    	    {
                    	    // InternalZeroKnowledge.g:1523:7: (lv_arguments_5_0= ruleArgument )
                    	    // InternalZeroKnowledge.g:1524:8: lv_arguments_5_0= ruleArgument
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      								newCompositeNode(grammarAccess.getFunctionCallAccess().getArgumentsArgumentParserRuleCall_1_1_1_1_0());
                    	      							
                    	    }
                    	    pushFollow(FOLLOW_11);
                    	    lv_arguments_5_0=ruleArgument();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      								if (current==null) {
                    	      									current = createModelElementForParent(grammarAccess.getFunctionCallRule());
                    	      								}
                    	      								add(
                    	      									current,
                    	      									"arguments",
                    	      									lv_arguments_5_0,
                    	      									"de.upb.crypto.zeroknowledge.ZeroKnowledge.Argument");
                    	      								afterParserOrEnumRuleCall();
                    	      							
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(otherlv_6, grammarAccess.getFunctionCallAccess().getRightParenthesisKeyword_1_2());
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunctionCall"


    // $ANTLR start "entryRuleArgument"
    // InternalZeroKnowledge.g:1552:1: entryRuleArgument returns [EObject current=null] : iv_ruleArgument= ruleArgument EOF ;
    public final EObject entryRuleArgument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArgument = null;


        try {
            // InternalZeroKnowledge.g:1552:49: (iv_ruleArgument= ruleArgument EOF )
            // InternalZeroKnowledge.g:1553:2: iv_ruleArgument= ruleArgument EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArgumentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleArgument=ruleArgument();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArgument; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArgument"


    // $ANTLR start "ruleArgument"
    // InternalZeroKnowledge.g:1559:1: ruleArgument returns [EObject current=null] : ( () ( (lv_expression_1_0= ruleConjunction ) ) ) ;
    public final EObject ruleArgument() throws RecognitionException {
        EObject current = null;

        EObject lv_expression_1_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:1565:2: ( ( () ( (lv_expression_1_0= ruleConjunction ) ) ) )
            // InternalZeroKnowledge.g:1566:2: ( () ( (lv_expression_1_0= ruleConjunction ) ) )
            {
            // InternalZeroKnowledge.g:1566:2: ( () ( (lv_expression_1_0= ruleConjunction ) ) )
            // InternalZeroKnowledge.g:1567:3: () ( (lv_expression_1_0= ruleConjunction ) )
            {
            // InternalZeroKnowledge.g:1567:3: ()
            // InternalZeroKnowledge.g:1568:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getArgumentAccess().getArgumentAction_0(),
              					current);
              			
            }

            }

            // InternalZeroKnowledge.g:1574:3: ( (lv_expression_1_0= ruleConjunction ) )
            // InternalZeroKnowledge.g:1575:4: (lv_expression_1_0= ruleConjunction )
            {
            // InternalZeroKnowledge.g:1575:4: (lv_expression_1_0= ruleConjunction )
            // InternalZeroKnowledge.g:1576:5: lv_expression_1_0= ruleConjunction
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getArgumentAccess().getExpressionConjunctionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_expression_1_0=ruleConjunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getArgumentRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_1_0,
              						"de.upb.crypto.zeroknowledge.ZeroKnowledge.Conjunction");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArgument"


    // $ANTLR start "entryRuleVariable"
    // InternalZeroKnowledge.g:1597:1: entryRuleVariable returns [EObject current=null] : iv_ruleVariable= ruleVariable EOF ;
    public final EObject entryRuleVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariable = null;


        try {
            // InternalZeroKnowledge.g:1597:49: (iv_ruleVariable= ruleVariable EOF )
            // InternalZeroKnowledge.g:1598:2: iv_ruleVariable= ruleVariable EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getVariableRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleVariable=ruleVariable();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleVariable; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariable"


    // $ANTLR start "ruleVariable"
    // InternalZeroKnowledge.g:1604:1: ruleVariable returns [EObject current=null] : ( (lv_name_0_0= RULE_IDENTIFIER ) ) ;
    public final EObject ruleVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalZeroKnowledge.g:1610:2: ( ( (lv_name_0_0= RULE_IDENTIFIER ) ) )
            // InternalZeroKnowledge.g:1611:2: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            {
            // InternalZeroKnowledge.g:1611:2: ( (lv_name_0_0= RULE_IDENTIFIER ) )
            // InternalZeroKnowledge.g:1612:3: (lv_name_0_0= RULE_IDENTIFIER )
            {
            // InternalZeroKnowledge.g:1612:3: (lv_name_0_0= RULE_IDENTIFIER )
            // InternalZeroKnowledge.g:1613:4: lv_name_0_0= RULE_IDENTIFIER
            {
            lv_name_0_0=(Token)match(input,RULE_IDENTIFIER,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_name_0_0, grammarAccess.getVariableAccess().getNameIDENTIFIERTerminalRuleCall_0());
              			
            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getVariableRule());
              				}
              				setWithLastConsumed(
              					current,
              					"name",
              					lv_name_0_0,
              					"de.upb.crypto.zeroknowledge.ZeroKnowledge.IDENTIFIER");
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariable"


    // $ANTLR start "entryRuleNumberLiteral"
    // InternalZeroKnowledge.g:1632:1: entryRuleNumberLiteral returns [EObject current=null] : iv_ruleNumberLiteral= ruleNumberLiteral EOF ;
    public final EObject entryRuleNumberLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteral = null;


        try {
            // InternalZeroKnowledge.g:1632:54: (iv_ruleNumberLiteral= ruleNumberLiteral EOF )
            // InternalZeroKnowledge.g:1633:2: iv_ruleNumberLiteral= ruleNumberLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumberLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNumberLiteral=ruleNumberLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumberLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNumberLiteral"


    // $ANTLR start "ruleNumberLiteral"
    // InternalZeroKnowledge.g:1639:1: ruleNumberLiteral returns [EObject current=null] : ( (lv_value_0_0= RULE_INT ) ) ;
    public final EObject ruleNumberLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalZeroKnowledge.g:1645:2: ( ( (lv_value_0_0= RULE_INT ) ) )
            // InternalZeroKnowledge.g:1646:2: ( (lv_value_0_0= RULE_INT ) )
            {
            // InternalZeroKnowledge.g:1646:2: ( (lv_value_0_0= RULE_INT ) )
            // InternalZeroKnowledge.g:1647:3: (lv_value_0_0= RULE_INT )
            {
            // InternalZeroKnowledge.g:1647:3: (lv_value_0_0= RULE_INT )
            // InternalZeroKnowledge.g:1648:4: lv_value_0_0= RULE_INT
            {
            lv_value_0_0=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_value_0_0, grammarAccess.getNumberLiteralAccess().getValueINTTerminalRuleCall_0());
              			
            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getNumberLiteralRule());
              				}
              				setWithLastConsumed(
              					current,
              					"value",
              					lv_value_0_0,
              					"de.upb.crypto.zeroknowledge.ZeroKnowledge.INT");
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNumberLiteral"


    // $ANTLR start "entryRuleBrackets"
    // InternalZeroKnowledge.g:1667:1: entryRuleBrackets returns [EObject current=null] : iv_ruleBrackets= ruleBrackets EOF ;
    public final EObject entryRuleBrackets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBrackets = null;


        try {
            // InternalZeroKnowledge.g:1667:49: (iv_ruleBrackets= ruleBrackets EOF )
            // InternalZeroKnowledge.g:1668:2: iv_ruleBrackets= ruleBrackets EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBracketsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBrackets=ruleBrackets();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBrackets; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBrackets"


    // $ANTLR start "ruleBrackets"
    // InternalZeroKnowledge.g:1674:1: ruleBrackets returns [EObject current=null] : ( () ( (lv_content_1_0= ruleExpression ) ) ) ;
    public final EObject ruleBrackets() throws RecognitionException {
        EObject current = null;

        EObject lv_content_1_0 = null;



        	enterRule();

        try {
            // InternalZeroKnowledge.g:1680:2: ( ( () ( (lv_content_1_0= ruleExpression ) ) ) )
            // InternalZeroKnowledge.g:1681:2: ( () ( (lv_content_1_0= ruleExpression ) ) )
            {
            // InternalZeroKnowledge.g:1681:2: ( () ( (lv_content_1_0= ruleExpression ) ) )
            // InternalZeroKnowledge.g:1682:3: () ( (lv_content_1_0= ruleExpression ) )
            {
            // InternalZeroKnowledge.g:1682:3: ()
            // InternalZeroKnowledge.g:1683:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getBracketsAccess().getBracketsAction_0(),
              					current);
              			
            }

            }

            // InternalZeroKnowledge.g:1689:3: ( (lv_content_1_0= ruleExpression ) )
            // InternalZeroKnowledge.g:1690:4: (lv_content_1_0= ruleExpression )
            {
            // InternalZeroKnowledge.g:1690:4: (lv_content_1_0= ruleExpression )
            // InternalZeroKnowledge.g:1691:5: lv_content_1_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getBracketsAccess().getContentExpressionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_content_1_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getBracketsRule());
              					}
              					set(
              						current,
              						"content",
              						lv_content_1_0,
              						"de.upb.crypto.zeroknowledge.ZeroKnowledge.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBrackets"

    // $ANTLR start synpred1_InternalZeroKnowledge
    public final void synpred1_InternalZeroKnowledge_fragment() throws RecognitionException {   
        // InternalZeroKnowledge.g:1127:4: ( ( () '(' ( ( ruleConjunction ) ) ',' ) )
        // InternalZeroKnowledge.g:1127:5: ( () '(' ( ( ruleConjunction ) ) ',' )
        {
        // InternalZeroKnowledge.g:1127:5: ( () '(' ( ( ruleConjunction ) ) ',' )
        // InternalZeroKnowledge.g:1128:5: () '(' ( ( ruleConjunction ) ) ','
        {
        // InternalZeroKnowledge.g:1128:5: ()
        // InternalZeroKnowledge.g:1129:5: 
        {
        }

        match(input,13,FOLLOW_4); if (state.failed) return ;
        // InternalZeroKnowledge.g:1131:5: ( ( ruleConjunction ) )
        // InternalZeroKnowledge.g:1132:6: ( ruleConjunction )
        {
        // InternalZeroKnowledge.g:1132:6: ( ruleConjunction )
        // InternalZeroKnowledge.g:1133:7: ruleConjunction
        {
        pushFollow(FOLLOW_19);
        ruleConjunction();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        match(input,14,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred1_InternalZeroKnowledge

    // Delegated rules

    public final boolean synpred1_InternalZeroKnowledge() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalZeroKnowledge_fragment(); // can never throw exception
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
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000002002470L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000001400L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000FC0002L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000003000002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x000000000200A470L});

}