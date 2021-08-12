<!-- 
    A component for changin all editor and compiler options
 -->
<script>
    import { Tile, Toggle, TileGroup, RadioTile } from 'carbon-components-svelte';
    
    import {
        generatedProject,
        includeCode,
        includeLatex,
        openClassFilesInNewTabs,
    } from './stores';

    const generatedProjectOptions = [
        'Full project',
        'Protocol class only',
        'Test class only',
        'Public parameters class only',
    ]

    const includeCodeLabel = 'Include Subzero code file in generated project';
    const includeLatexLabel = 'Include LaTeX file in generated project';
    const openClassFilesInNewTabsLabel = 'Open the generated Java class files in (readonly) editor tabs';
</script>

<div class='options'>
    <Tile>
        <Toggle
            bind:toggled={$includeCode}
            labelText=''
            labelA={includeCodeLabel}
            labelB={includeCodeLabel}
        />
    </Tile>

    <Tile>
        <Toggle
            bind:toggled={$includeLatex}
            labelText=''
            labelA={includeLatexLabel}
            labelB={includeLatexLabel}
        />
    </Tile>
    
    <Tile>
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
            {#each generatedProjectOptions as option, index}
                <RadioTile
                    light
                    value={option}
                    checked={index === 0}
                >
                    {option}
                </RadioTile>
            {/each}
        </TileGroup>
    </Tile>
</div>

<style>
    .options :global(.bx--label) {
        font-size: 1rem;
    }

    .options :global(.bx--toggle__text--on), .options :global(.bx--toggle__text--off) {
        font-size: 1rem;
    }
</style>
