<!-- 
	The component for the main page content
 -->
<script>
	import { Tab, TabContent, Tabs, Tile } from 'carbon-components-svelte';

	import Editor from './Editor.svelte';
	import Documentation from './Documentation.svelte';
	import LatexPreview from './LatexPreview.svelte';
	import Environment from './Environment.svelte';
	import Options from './Options.svelte';
	import CompileButton from './CompileButton.svelte';
</script>

<div class='content'>
    <div class='left-content-container'>
        <Editor/>
    </div>
    <div class='right-content-container'>
        <Tile style='width: 100%; margin: 0; display: flex; flex-flow: column; padding: 0'>
            <div class='tab-container'>
                <Tabs type='container'>
                    <Tab label='Documentation'/>
                    <Tab label='LaTeX Preview'/>
                    <Tab label='Environment'/>
                    <Tab label='Options'/>
                    <div class='tab-container-content' slot='content'>
                        <TabContent>
							<Documentation/>
                        </TabContent>
                        <TabContent>
							<LatexPreview/>
                        </TabContent>
                        <TabContent>
							<Environment/>
                        </TabContent>
                        <TabContent>
							<Options/>
                        </TabContent>
                    </div>
                </Tabs>
            </div>
            <div class='compile-container'>
                <CompileButton/>
            </div>
        </Tile>
    </div>
</div>

<style>
    .content {
		width: 100%;
		flex-grow: 1;
		flex-shrink: 0;
		display: flex;
	}

	.tab-container {
		flex-grow: 1;
		flex-shrink: 0;
	}

	.tab-container-content {
		height: 100%;
	}

	.tab-container :global(.bx--tabs__nav) {
        width: 100%;
        display: flex;
    }

    .tab-container :global(.bx--tabs__nav-item) {
        width: 100%;
    }

    .tab-container :global(.bx--tabs__nav-link) {
        width: 100%;
        text-align: center;
    }

	.compile-container {
		flex-grow: 0;
		flex-shrink: 1;
        display: flex;
        align-items: center;
        justify-content: center;
	}

	.left-content-container {
		margin: 0;
		width: 60%;
		height: 100%;
		display: flex;
		flex-flow: column;
	}

    .left-content-container :global(slot) {
		padding: 0;
		height: 100%;
		width: 100%;
	}

	.left-content-container :global(.bx--tab-content) {
		padding: 0;
		height: 100%;
		width: 100%;
	}

	.right-content-container {
		margin: 0;
		width: 40%;
		max-height: 100%;
		display: flex;
	}
</style>