<script>
    import { onMount } from 'svelte';

    import {
        BASE_URL,
        CODE_RESOURCE_ID,
        DSL_EXTENSION,
        LATEX_RESOURCE_ID,
        editorCode,
        editorCodeHasChanged,
        environment,
        latexText,
        xtext,
        xtextEditor,
    } from './stores.js';
    import { validateCode } from './validation.js';

    import { openFile } from './LoadFileButton.svelte';
    import { saveCode } from './SaveCodeButton.svelte';

    export let theme;
    export let tabSize;
    export let defaultFontSize;

    export function setZoom(fontSize) {
        editor?.setFontSize(fontSize);
    }

    let mounted = false;

    // Bound to reference the editor's parent container
    let editorContainer;

    let editor;
    let hasInfo = false;

    // Timer for continuous LaTeX preview updating
    const TIMER_INTERVAL = 600;
    let timer;

    // Editor settings
    const SYNTAX_DEFINITION = 'syntax-highlighting';
    const SELECTION_UPDATE_DELAY = 550;
    const TEXT_UPDATE_DELAY = 500;

    onMount(() => {
        mounted = true;
    });

    $: {
        if (mounted && $xtext) {
            createEditor($xtext);
        }
    }

    $: if (editor) editor.setTheme(theme);

    // Creates the code editor and sets up features
    function createEditor(xtext) {
        // Creates the editor and adds it to the DOM
        editor = xtext.createEditor({
            parent: editorContainer,
            baseUrl: $BASE_URL,
            xtextLang: $DSL_EXTENSION,
            resourceId: $CODE_RESOURCE_ID,
            loadFromServer: false,
            sendFullText: true,
            syntaxDefinition: SYNTAX_DEFINITION,
            theme: theme,
            selectionUpdateDelay: SELECTION_UPDATE_DELAY,
            textUpdateDelay: TEXT_UPDATE_DELAY,
        });

        $xtextEditor = editor;

        // Enable word wrapping. Must be set separately (for some reason)
        editor.setOptions({wrap: true});

        editor.setFontSize(defaultFontSize);
        
        editor.on('change', function() {
            const code = editor.getValue();
            $editorCode = code;
            $editorCodeHasChanged = code.length !== 0;
            clearTimeout(timer);
            timer = setTimeout(updateLatexPreview, TIMER_INTERVAL);
        });

        const session = editor.getSession();

        session.setOptions({
            tabSize: tabSize,
            useSoftTabs: true
        });

        session.on('changeAnnotation', () => {
            const annotations = session.getAnnotations() || [];
            
            if (hasInfo) {
                hasInfo = false;
                return;
            }

            let infoAnnotation;
            hasInfo = annotations.some((annotation, index) => {
                if (annotation.type === 'info') {
                    infoAnnotation = annotation;
                    annotations.splice(index, 1);
                    
                    return true;
                }

                return false;
            });

            if (hasInfo) {
                $environment = JSON.parse(infoAnnotation.text);
                session.setAnnotations(annotations);
            }
        });

        editor.commands.addCommand({
            name: 'save',
            bindKey: {win: 'Ctrl-S', 'mac': 'Cmd-S'},
            exec: () => {
                saveCode();
            }
        });

        editor.commands.addCommand({
            name: 'open',
            bindKey: {win: 'Ctrl-O', 'mac': 'Cmd-O'},
            exec: () => {
                openFile();
            }
        });

        // Stores whether the last char typed in the editor was a '}'
        // Used for auto-indentation
        let lastCharWasLeftCurlyBrace = false;

        // Auto-indentation
        // Adds an indent when writing a function definition
        // A better solution might be possible for this
        editor.textInput.getElement().addEventListener('keydown', function(event) {
            handleBrackets(event, '(', ')');
            handleBrackets(event, '[', ']');

            if (lastCharWasLeftCurlyBrace && event.key === 'Enter') {
                // Stop a new line from being created in the code editor
                event.preventDefault();
                matchingBrace();
            }

            lastCharWasLeftCurlyBrace = event.key === '{'
        });
    }

    function handleBrackets(event, openingBracket, closingBracket) {
        if (event.key === openingBracket) handleOpeningBracket(closingBracket);
            if (event.key === closingBracket) {
                // Stop the closing bracket from being typed in the code editor
                event.preventDefault();
                handleClosingBracket(closingBracket);
            }
    }

    // Adds a matching closing bracket when an open bracket is typed
    function handleOpeningBracket(closingBracket) {
        const code = editor.getValue();

        let cursorIndex, cursorRow, cursorColumn;
        [cursorIndex, cursorRow, cursorColumn] = getCursorInfo();

        editor.setValue(code.substring(0, cursorIndex) + closingBracket + code.substring(cursorIndex));
        editor.gotoLine(cursorRow + 1, cursorColumn);
    }

    // Handles when a closing bracket is typed
    function handleClosingBracket(closingBracket) {
        const code = editor.getValue();

        let cursorIndex, cursorRow, cursorColumn;
        [cursorIndex, cursorRow, cursorColumn] = getCursorInfo();

        // Note: cursorIndex is always less than or equal to code length
        if (cursorIndex === code.length) {
            // Cursor is at the very end of the document
            editor.setValue(code + closingBracket);
        } else if (code.charAt(cursorIndex) !== closingBracket){
            editor.setValue(code.substring(0, cursorIndex) + closingBracket + code.substring(cursorIndex));
        }

        editor.gotoLine(cursorRow + 1, cursorColumn + 1);
    }

    // Returns a string that is the indentation at the start of some text
    function getIndentation(text) {
        for (let i = 0; i < text.length; i++) {
            if (text.charAt(i) !== ' ') {
                return text.substring(0, i);
            }
        }
        return text;
    }

    // Returns info about the current cursor
    function getCursorInfo() {
        const cursorIndex = editor.session.doc.positionToIndex(editor.selection.getCursor());
        const cursorPosition = editor.getCursorPosition();
        const cursorRow = cursorPosition.row;
        const cursorColumn = cursorPosition.column;

        return [cursorIndex, cursorRow, cursorColumn];
    }

    // Adds indentation and a closing right brace
    function matchingBrace() {
        const code = editor.getValue();

        let cursorIndex, cursorRow;
        [cursorIndex, cursorRow, ] = getCursorInfo();

        const currentLine = editor.getSession().getLine(cursorRow);
        const indentation = getIndentation(currentLine);
        let nextLine = '\n' + indentation;
        
        for (let i = 0; i < tabSize; i++) nextLine += ' ';
        nextLine += '\n' + indentation + '}';

        editor.setValue(code.substring(0, cursorIndex) + nextLine + code.substring(cursorIndex));
        editor.gotoLine(cursorRow + 2, indentation.length + tabSize);
    }



    // Updates the latex preview box based on the current contents of the code editor
    function updateLatexPreview() {
        validateCode($CODE_RESOURCE_ID, (isValid, issues) => {
            if (isValid) {
                editor.xtextServices.generatorService._encodedResourceId = $LATEX_RESOURCE_ID;
                editor.xtextServices.generate().then((text) => {
                    $latexText = text;
                });
            }
        });
    }
</script>

<div class='editor' bind:this={editorContainer}/>

<style>
    .editor {
        width: 100%;
        height: 100%;
    }

    /* Styles the table scrollbar */
    .editor :global(::-webkit-scrollbar) {
        width: 1em;
    }
    
    .editor :global(::-webkit-scrollbar-track) {
        box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
    }
    
    .editor :global(::-webkit-scrollbar-thumb) {
        background-color: darkgrey;
        outline: 1px solid slategrey;
    }

    .editor :global(::-webkit-scrollbar-corner) {
        box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
    }
</style>
