<!-- 
    A component to load the Subzero markdown documentation, and convert it to html
 -->
<script>
    import SvelteMarkdown from 'svelte-markdown';
    import { Tile } from 'carbon-components-svelte';
    import { onMount } from 'svelte';
    
    import documentation from './documentation.js';
    
    const markdownOptions = {gfm: true};
    
    onMount(() => {
        // Fixes bug with svelte-markdown where backticks used to open/close code
        // blocks are included in the code block text
        const codeElements = document.getElementsByTagName('code');
        for (const code of codeElements) {
            code.innerHTML = code.innerHTML.replaceAll('`', '');
        }

        // Fixes headers not being created with IDs, which are required to
        // make header links work
        for (let i = 1; i <= 6; i++) {
            const headers = document.getElementsByTagName('h' + i);
            for (const header of headers) {
                header.id = header.innerHTML.toLowerCase().replaceAll(' ', '-');
            }
        }
    });
</script>

<Tile>
    <div class='documentation markdown-body-dark'>
        <SvelteMarkdown source={documentation} options={markdownOptions}/>
    </div>
</Tile>

<style lang='scss' global>
    .documentation {
        height: 44rem;
        width: 100%;
        overflow: auto;
    }

    .documentation::-webkit-scrollbar, .documentation pre::-webkit-scrollbar {
        width: 0.75rem;
        height: 0.75rem;
    }
    
    .documentation::-webkit-scrollbar-track, .documentation pre::-webkit-scrollbar-track {
        box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
    }
    
    .documentation::-webkit-scrollbar-thumb, .documentation pre::-webkit-scrollbar-thumb {
        background-color: darkgrey;
        outline: 1px solid slategrey;
    }
</style>
