<!-- 
    A component for changin all editor and compiler options
 -->
<script>
    import { Button, Dropdown, RadioTile, Tile, Toggle, TileGroup } from 'carbon-components-svelte';
    
    import { saveToStorage } from './storage.js';
    import {
        darkMode,
        darkTheme,
        generatedProject,
        generatedProjectOptions,
        includeCode,
        includeLatex,
        lightTheme,
        openClassFilesInNewTabs,
    } from './stores';

    import {
        darkThemes,
        lightThemes,
        defaultDarkThemeIndex,
        defaultLightThemeIndex,
        themePath
    } from './Editor.svelte';

    const lightThemeOptions = lightThemes.map((theme, index) => {
        return {id: index, text: theme.name};
    });

    const darkThemeOptions = darkThemes.map((theme, index) => {
        return {id: index, text: theme.name};
    });

    const sortedGeneratedProjectOptions = Object.values($generatedProjectOptions).sort();
    const includeCodeLabel = 'Include Subzero code file in generated project';
    const includeLatexLabel = 'Include LaTeX file in generated project';
    const openClassFilesInNewTabsLabel = 'Open the generated Java class files in (readonly) editor tabs';

    let selectedDarkThemeIndex = defaultDarkThemeIndex;
    let selectedLightThemeIndex = defaultLightThemeIndex;

    $: $darkTheme = themePath + darkThemes[selectedDarkThemeIndex].file;
    $: $lightTheme = themePath + lightThemes[selectedLightThemeIndex].file;

    // Save options to local storage whenever they change
    $: saveToStorage('includeCode', $includeCode);
    $: saveToStorage('includeLatex', $includeLatex);
    $: saveToStorage('openClassFilesInNewTabs', $openClassFilesInNewTabs);
    $: saveToStorage('generatedProject', $generatedProject);
    $: saveToStorage('darkTheme', $darkTheme);
    $: saveToStorage('lightTheme', $lightTheme);
    $: saveToStorage('darkMode', $darkMode);

    function resetToDefaultOptions() {
        $includeCode = true;
        $includeLatex = true;
        $openClassFilesInNewTabs = false;
        $generatedProject = $generatedProjectOptions.full;
        selectedDarkThemeIndex = defaultDarkThemeIndex;
        selectedLightThemeIndex = defaultLightThemeIndex;
    }
</script>

<div class='options'>
    <Tile>
        <Toggle
            bind:toggled={$includeCode}
            labelText=''
            labelA={includeCodeLabel}
            labelB={includeCodeLabel}
        />

        <Toggle
            bind:toggled={$includeLatex}
            labelText=''
            labelA={includeLatexLabel}
            labelB={includeLatexLabel}
        />
    
        <Toggle
            bind:toggled={$openClassFilesInNewTabs}
            labelText=''
            labelA={openClassFilesInNewTabsLabel}
            labelB={openClassFilesInNewTabsLabel}
        />
    </Tile>

    <Tile>
        <TileGroup
            bind:selected={$generatedProject}
            legend='Choose project generated:'
        >
            {#each sortedGeneratedProjectOptions as option}
                <RadioTile
                    light
                    value={option}
                    checked={$generatedProject === option}
                >
                    {option}
                </RadioTile>
            {/each}
        </TileGroup>
    </Tile>

    <Tile>
        <Dropdown
            light
            titleText='Dark mode theme:'
            direction='top'
            items={darkThemeOptions}
            bind:selectedIndex={selectedDarkThemeIndex}
        ></Dropdown>
    </Tile>

    <Tile>
        <Dropdown
            light
            titleText='Light mode theme:'
            direction='top'
            items={lightThemeOptions}
            bind:selectedIndex={selectedLightThemeIndex}
        ></Dropdown>
    </Tile>

    <Tile>
        <Button
            kind='secondary'
            on:click={resetToDefaultOptions}
        >
            Reset to default options
        </Button>
    </Tile>

</div>

<style>
    .options :global(.bx--label) {
        font-size: 1rem;
    }

    .options :global(.bx--toggle__text--on), .options :global(.bx--toggle__text--off) {
        font-size: 1rem;
    }

    .options :global(.bx--btn) {
        padding-left: 1rem;
        padding-right: 1rem;
    }

    .options :global(.bx--list-box__menu::-webkit-scrollbar) {
        width: 0.5rem;
    }
</style>
