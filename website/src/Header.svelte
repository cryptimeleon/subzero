<!-- 
	The component for the main page header
 -->
<script>
    import { HeaderGlobalAction } from 'carbon-components-svelte';
    import Add32 from 'carbon-icons-svelte/lib/Add32';
	import Subtract32 from 'carbon-icons-svelte/lib/Subtract32';

	import { zoomIn, zoomOut, zoomInDisabled, zoomOutDisabled } from './Editor.svelte';
    import LoadFileButton from './LoadFileButton.svelte';
	import SaveCodeButton from './SaveCodeButton.svelte';
	import Examples from './Examples.svelte';
	import SourceButton from './SourceButton.svelte';
	import DarkModeButton from './DarkModeButton.svelte';
	import Tooltip from './Tooltip.svelte';
	import { darkMode } from './stores.js';

	let zoomInButtonDisabled = false;
	let zoomOutButtonDisabled = false;

	function handleZoomIn() {
		zoomIn();
		zoomInButtonDisabled = zoomInDisabled();
		zoomOutButtonDisabled = zoomOutDisabled();
	}

	function handleZoomOut() {
		zoomOut();
		zoomOutButtonDisabled = zoomOutDisabled();
		zoomInButtonDisabled = zoomInDisabled();
	}
</script>

<div class={'header header-' + ($darkMode ? 'dark' : 'light')}>
    <div class='left-header-container'>
        <div class='title-container'>
            <h1>Subzero - Zero Knowledge Compiler</h1>
        </div>
        <div class='editor-pane-container'>
            <!-- Flex flow is row reversed -->
            <Tooltip text='Zoom out editor'>
				<HeaderGlobalAction
					bind:disabled={zoomOutButtonDisabled}
					aria-label='Zoom editor out'
					icon={Subtract32}
					on:click={handleZoomOut}
				/>
			</Tooltip>

			<Tooltip text='Zoom in editor'>
				<HeaderGlobalAction
					bind:disabled={zoomInButtonDisabled}
					aria-label='Zoom editor in'
					icon={Add32}
					on:click={handleZoomIn}
				/>			
			</Tooltip>

            <div class='examples-container'>
                <Examples/>
            </div>

			<Tooltip text='Save Subzero code'>
				<SaveCodeButton/>
			</Tooltip>
			
			<Tooltip size='medium' text='Load Subzero code (.sub0, .txt)'>
				<LoadFileButton/>
			</Tooltip>
        </div>
    </div>
    <div class='right-header-container'>
		<Tooltip position='left' text='Source code'>
			<SourceButton/>
		</Tooltip>
		<Tooltip text='Toggle dark mode'>
			<DarkModeButton/>	
		</Tooltip>
		
    </div>
</div>

<style>
    .header {
		display: flex;
		flex-grow: 0;
  		flex-shrink: 1;
		flex-basis: 3rem;
		width: 100%;
	}

	.header-dark :global(.bx--header__action:disabled svg) {
		fill: dimgray;
	}

	.header-dark :global(.bx--header__action:hover:disabled) {
		background-color: inherit;
		cursor: default;
	}

	.header-light :global(svg) {
		fill: black;
	}

	.header-light :global(.bx--header__action:hover) {
		background-color: #e0e0e0;
	}

	.header-light :global(.bx--header__action:disabled svg) {
		fill: silver;
	}

	.header-light :global(.bx--header__action:hover:disabled) {
		background-color: inherit;
		cursor: default;
	}

    .left-header-container {
		margin: 0;
		width: 60%;
		height: 100%;
		display: flex;
	}

	.right-header-container {
		margin: 0;
		width: 40%;
		max-height: 100%;
		display: flex;
		flex-flow: row-reverse;
		align-items: center;
	}

    .title-container {
		width: 50%;
		height: 100%;
		display: flex;
		align-items: center;
	}

	.title-container > h1 {
		font-family: monospace;
		font-size: 1.5rem;
		padding-left: 1rem;
	}

	.editor-pane-container {
		width: 50%;
		height: 100%;
		display: flex;
		align-items: center;
		flex-flow: row-reverse;
	}

	.examples-container {
		flex-grow: 1;
		max-width: 50%;
	}
</style>