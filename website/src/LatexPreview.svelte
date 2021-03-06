<svelte:head>
    <!-- MathJax must be loaded in this way to work -->
    <script
        src='https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.9/MathJax.js?config=TeX-MML-AM_CHTML'
        on:load={mathjaxLoaded}
    >
    </script>
</svelte:head>

<script>
    import { onMount } from 'svelte';
    
    import { Tile, HeaderGlobalAction } from 'carbon-components-svelte';
    import Add32 from 'carbon-icons-svelte/lib/Add32';
	import Subtract32 from 'carbon-icons-svelte/lib/Subtract32';
    
    import { loadFromStorage, saveToStorage } from './storage.js';
    import { darkMode, latexText } from './stores.js';

    import SaveLatexButton from './SaveLatexButton.svelte';
    import Tooltip from './Tooltip.svelte';

    export function refreshPreview() {
        // Force MathJax to update
        if ($latexText && $latexText.slice(-1) !== ' ') $latexText += ' ';
    }

    let mathjaxReady = false;
    let mounted = false;

    // Binds to reference the LaTeX preview box
    let latexPreview;

    // LaTeX preview settings
    const DEFAULT_PREVIEW_FONT_SIZE = 24;
    const MIN_PREVIEW_FONT_SIZE = 8;
    const MAX_PREVIEW_FONT_SIZE = 40;
    const PREVIEW_FONT_SIZE_INCREMENT = 4;

    let currentPreviewFontSize = loadFromStorage('latexFontSize', DEFAULT_PREVIEW_FONT_SIZE);

    // Updates the LaTeX preview whenever the text changes
    $: {
        // if (latexPreview && mounted && typeof MathJax !== 'undefined') {
        if (latexPreview && mounted && mathjaxReady) {
            latexPreview.style.fontSize = currentPreviewFontSize;

            if ($latexText.length > 0) {
                latexPreview.innerHTML = $latexText;
            }

            // Calls MathJax to re-render the formatted LaTeX
            MathJax.Hub.Queue(['Typeset', MathJax.Hub, latexPreview]);
        }
    }

    onMount(() => {
        mounted = true;
    });

    function mathjaxLoaded() {
        MathJax.Hub.Config({
            skipStartupTypeset: true,
            messageStyle: 'none',
            CommonHTML: {
                linebreaks: {
                    automatic: true
                }
            },
            'HTML-CSS': {
                linebreaks: {
                    automatic: true
                }
            },
            SVG: {
                linebreaks: {
                    automatic: true
                }
            }
	    });
        mathjaxReady = true;
    }

    function zoomPreviewIn() {
        if (currentPreviewFontSize >= MAX_PREVIEW_FONT_SIZE) return;

        currentPreviewFontSize += PREVIEW_FONT_SIZE_INCREMENT;
        saveToStorage('latexFontSize', currentPreviewFontSize);
    }

    function zoomPreviewOut() {
        if (currentPreviewFontSize <= MIN_PREVIEW_FONT_SIZE) return;

        currentPreviewFontSize -= PREVIEW_FONT_SIZE_INCREMENT;
        saveToStorage('latexFontSize', currentPreviewFontSize);
    }
</script>

<Tile>
    <div class={'preview-container preview-' + ($darkMode ? 'dark' : 'light')}>
        <!-- Flow direction is column reverse -->
        <div class='control-container'>
            <div class='save-container'>
                <Tooltip text='Save LaTeX text'>
                    <SaveLatexButton />
                </Tooltip>
            </div>
            <div class='zoom-container'>
                <Tooltip text='Zoom out LaTeX preview'>
                    <HeaderGlobalAction
                        disabled={currentPreviewFontSize === MIN_PREVIEW_FONT_SIZE}
                        aria-label='Zoom out LaTeX preview'
                        icon={Subtract32}
                        on:click={zoomPreviewOut}
                    />
                </Tooltip>
                <Tooltip text='Zoom in LaTeX preview'>
                    <HeaderGlobalAction
                        disabled={currentPreviewFontSize === MAX_PREVIEW_FONT_SIZE}
                        aria-label='Zoom in LaTeX preview'
                        icon={Add32}
                        on:click={zoomPreviewIn}
                    />
                </Tooltip>
            </div>
        </div>
        <div
            class='latex-preview'
            bind:this={latexPreview}
            style='font-size: {currentPreviewFontSize}px; text-align: center;
            padding: 5rem;'
        >
            <p class='empty-latex-message'>A formatted LaTeX preview of the protocol will be displayed here when the program is free from syntax/semantic errors.</p>
        </div>
    </div>
</Tile>

<style>
    .preview-container {
        height: 100%;
        width: 100%;
        padding: 0.5rem;
        display: flex;
        flex-flow: column;
        align-items: center;
    }

	.preview-dark :global(.bx--header__action:disabled svg) {
		fill: dimgray;
	}

	.preview-dark :global(.bx--header__action:hover:disabled) {
		background-color: inherit;
		cursor: default;
	}

	.preview-light :global(svg) {
		fill: black;
	}

	.preview-light :global(.bx--header__action:hover) {
		background-color: #e0e0e0;
	}

	.preview-light :global(.bx--header__action:disabled svg) {
		fill: silver;
	}

	.preview-light :global(.bx--header__action:hover:disabled) {
		background-color: inherit;
		cursor: default;
	}

    .latex-preview {
        flex-grow: 1;
        flex-shrink: 1;
        flex-basis: auto;
        overflow-y: auto;
        max-height: 40rem;
    }

    .control-container {
        width: 100%;
        display: flex;
        flex-flow: row;
    }

    .save-container {
        width: 100%;
        display: flex;
    }

    .zoom-container {
        width: 100%;
        display: flex;
        flex-flow: row-reverse;
    }

    .empty-latex-message {
        text-align: center;
        padding: 5rem;
    }
</style>