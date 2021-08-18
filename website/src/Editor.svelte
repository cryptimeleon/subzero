<svelte:window on:keydown={handleKeydown}/>

<script context='module'>
    import { loadFromStorage, saveToStorage } from './storage.js';

    const DEFAULT_FONT_SIZE = 24;
    const MIN_FONT_SIZE = 8;
    const MAX_FONT_SIZE = 40;
    const FONT_SIZE_INCREMENT = 2;

    export const darkThemes = [
        {name: 'Ambiance', file: 'ambiance'},
        {name: 'Chaos', file: 'chaos'},
        {name: 'Clouds Midnight', file: 'clouds_midnight'},
        {name: 'Cobalt', file: 'cobalt'},
        {name: 'Dracula', file: 'dracula'},
        {name: 'Gruvbox', file: 'gruvbox'},
        {name: 'Green on Black', file: 'gob'},
        {name: 'idle Fingers', file: 'idle_fingers'},
        {name: 'krTheme', file: 'kr_theme'},
        {name: 'Merbivore', file: 'merbivore'},
        {name: 'Merbivore Soft', file: 'merbivore_soft'},
        {name: 'Mono Industrial', file: 'mono_industrial'},
        {name: 'Monokai', file: 'monokai'},
        // {name: 'Nord Dark', file: 'nord_dark'},
        // {name: 'One Dark', file: 'one_dark'},
        {name: 'Pastel on dark', file: 'pastel_on_dark'},
        {name: 'Solarized Dark', file: 'solarized_dark'},
        {name: 'Terminal', file: 'terminal'},
        {name: 'Tomorrow Night', file: 'tomorrow_night'},
        {name: 'Tomorrow Night Blue', file: 'tomorrow_night_blue'},
        {name: 'Tomorrow Night Bright', file: 'tomorrow_night_bright'},
        {name: 'Tomorrow Night 80s', file: 'tomorrow_night_eighties'},
        {name: 'Twilight', file: 'twilight'},
        {name: 'Vibrant Ink', file: 'vibrant_ink'},
    ];

    export const lightThemes = [
        {name: 'Chrome', file: 'chrome'},
        {name: 'Clouds', file: 'clouds'},
        {name: 'Crimson Editor', file: 'crimson_editor'},
        {name: 'Dawn', file: 'dawn'},
        {name: 'Dreamweaver', file: 'dreamweaver'},
        {name: 'Eclipse', file: 'eclipse'},
        {name: 'GitHub', file: 'github'},
        {name: 'IPlastic', file: 'iplastic'},
        {name: 'KatzenMilch', file: 'katzenmilch'},
        {name: 'Kuroir', file: 'kuroir'},
        {name: 'Solarized Light', file: 'solarized_light'},
        {name: 'SQL Server', file: 'sqlserver'},
        {name: 'TextMate', file: 'textmate'},
        {name: 'Tomorrow', file: 'tomorrow'},
        {name: 'Xcode', file: 'xcode'},
    ];

    export const defaultDarkThemeIndex = darkThemes.findIndex(theme => theme.name === 'Monokai');
    export const defaultLightThemeIndex = lightThemes.findIndex(theme => theme.name === 'Tomorrow');
    export const themePath = 'ace/theme/';

    // Increases editor font size
    export function zoomIn() {
        zoomOutIsDisabled = false;

        if (currentFontSize >= MAX_FONT_SIZE) {
            zoomInIsDisabled = true;
            return;
        }

        zoomInIsDisabled = false;
        currentFontSize += FONT_SIZE_INCREMENT;
        saveToStorage('editorFontSize', currentFontSize);

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
        saveToStorage('editorFontSize', currentFontSize);

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

    let currentFontSize = loadFromStorage('editorFontSize', DEFAULT_FONT_SIZE);
    let zoomInIsDisabled = currentFontSize >= MAX_FONT_SIZE;
    let zoomOutIsDisabled = currentFontSize <= MIN_FONT_SIZE;
    let editorSets = [];
</script>

<script>
    import { Tab, TabContent, Tabs } from 'carbon-components-svelte';

    import { darkMode, darkTheme, javaClasses, lightTheme } from './stores.js';
    
	import SubzeroEditor from './SubzeroEditor.svelte';
	import JavaEditor from './JavaEditor.svelte';
    import EditorTab from './EditorTab.svelte';

    const TAB_SIZE = 2;

    let selectedTabIndex;
    let editors = [];
    editorSets.push(editors);

    $: theme = $darkMode ? $darkTheme : $lightTheme;
    $: classNames = Object.keys($javaClasses);
    $: hideTabs = classNames.length === 0;

    function handleKeydown(event) {
        // Overrides default browser zooming in/out and replaces it
        // with zoom for the code editor
        if (event.ctrlKey && (event.key === '-' || event.key === '=')) {
            event.preventDefault();
            event.stopPropagation();
            event.key === '=' ? zoomIn() : zoomOut();
        }
    }
</script>

<div class='editor-container'>
    <Tabs 
        bind:selected={selectedTabIndex}
        class={hideTabs ? 'hide-tabs' : ''}
    >
        <div class='editor-tabs-container'>
            <Tab label='Subzero'/>
            
            {#each classNames as className (className)}
                <EditorTab
                    className={className}
                    on:selectedDelete={() => selectedTabIndex = 0}
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