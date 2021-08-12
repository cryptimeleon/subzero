<svelte:window on:keydown={handleKeydown} on:wheel|nonpassive={handleWheel}/>

<script context='module'>
    const DEFAULT_FONT_SIZE = 24;
    const MIN_FONT_SIZE = 8;
    const MAX_FONT_SIZE = 40;
    const FONT_SIZE_INCREMENT = 2;

    // Increases editor font size
    export function zoomIn() {
        zoomOutIsDisabled = false;

        if (currentFontSize >= MAX_FONT_SIZE) {
            zoomInIsDisabled = true;
            return;
        }

        zoomInIsDisabled = false;
        currentFontSize += FONT_SIZE_INCREMENT;
        editorSets.forEach((editors) => {
            editors.forEach((editor) => editor.setZoom(currentFontSize));
        });
    }

    // Decreases editor font size
    export function zoomOut() {
        zoomInIsDisabled = false;

        if (currentFontSize <= MIN_FONT_SIZE) {
            zoomOutIsDisabled = true;
            return;
        }

        zoomOutIsDisabled = false;
        currentFontSize -= FONT_SIZE_INCREMENT;
        editorSets.forEach((editors) => {
            editors.forEach((editor) => editor.setZoom(currentFontSize));
        });
    }

    export function zoomInDisabled() {
        return zoomInIsDisabled;
    }

    export function zoomOutDisabled() {
        return zoomOutIsDisabled;
    }

    let zoomInIsDisabled = false;
    let zoomOutIsDisabled = false;
    let currentFontSize = DEFAULT_FONT_SIZE;
    let editorSets = [];
</script>

<script>
    import { Tab, TabContent, Tabs } from 'carbon-components-svelte';

    import { darkMode, javaClasses } from './stores.js';
    
	import SubzeroEditor from './SubzeroEditor.svelte';
	import JavaEditor from './JavaEditor.svelte';
    import EditorTab from './EditorTab.svelte';

    let editors = [];
    editorSets.push(editors);

    const DARK_THEME = 'ace/theme/monokai';
    const LIGHT_THEME = 'ace/theme/tomorrow';
    const TAB_SIZE = 2;

    $: theme = $darkMode ? DARK_THEME : LIGHT_THEME;
    $: classNames = Object.keys($javaClasses);
    $: hideTabs = classNames.length === 0;

    let selectedIndex;

    function handleKeydown(event) {
        // Overrides default browser zooming in/out and replaces it
        // with zoom for the code editor
        if (event.ctrlKey && (event.key === '-' || event.key === '=')) {
            event.preventDefault();
            event.stopPropagation();
            event.key === '=' ? zoomIn() : zoomOut();
        }
    }

    function handleWheel(event) {
        if (event.ctrlKey) {
            event.preventDefault();
            event.deltaY > 0 ? zoomOut() : zoomIn();
        }
    }
</script>

<div class='editor-container'>
    <Tabs 
        bind:selected={selectedIndex}
        class={hideTabs ? 'hide-tabs' : ''}
    >
        <div class='editor-tabs-container'>
            <Tab label='Subzero'/>
            
            {#each classNames as className (className)}
                <EditorTab
                    className={className}
                    on:selectedDelete={() => selectedIndex = 0}
                />
            {/each}
        </div>
        <div class='editor-content' slot='content'>
            <TabContent>
                <SubzeroEditor
                    bind:this={editors[0]}
                    theme={theme}
                    tabSize={TAB_SIZE}
                    defaultFontSize={currentFontSize}
                />
            </TabContent>
            {#each classNames as className, index (className)}
                <TabContent>
                    <JavaEditor
                        bind:this={editors[index]}
                        javaClass={$javaClasses[className]}
                        theme={theme}
                        tabSize={TAB_SIZE}
                        defaultFontSize={currentFontSize}
                    />
                </TabContent>
            {/each}
        </div>
    </Tabs>
</div>

<style>
    .editor-container {
        width: 100%;
        height: 100%;
    }

    .editor-container :global(.hide-tabs) {
        display: none;
    }
    
    .editor-container :global(.bx--tabs__nav) {
        width: 100%;
        display: flex;
    }

    .editor-tabs-container :global(.bx--tabs__nav-item) {
        width: 100%;
    }

    .editor-tabs-container :global(.bx--tabs__nav-link) {
        width: 100%;
        text-align: center;
    }

    .editor-tabs-container {
        width: 100%;
        height: 100%;
        display: flex;
        overflow-x: scroll;
    }

    .editor-content {
        width: 100%;
        height: 100%;
    }
</style>