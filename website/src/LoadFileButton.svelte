<!-- 
    A button component that opens a file explorer to select
    a text or Subzero file to load into the editor
 -->
<script context='module'>
    export function openFile() {
        fileLoader.click();
    }

    let fileLoader;
</script>

<script>
    import { HeaderGlobalAction } from 'carbon-components-svelte';
	import FolderParent32 from 'carbon-icons-svelte/lib/FolderParent32';

    import { xtextEditor } from './stores.js';

    function loadFile() {
        if (fileLoader.files.length > 0) {
            const file = fileLoader.files[0];

            const reader = new FileReader();
            reader.onload = function(){
                const code = reader.result;
                $xtextEditor.setValue(code);
            };
            reader.readAsText(file);
        }
    }
</script>

<HeaderGlobalAction
    aria-label='Load file'
    icon={FolderParent32}
    on:click={openFile}
/>

<input
    bind:this={fileLoader}
    class='loadFileInput'
    type='file'
    accept='.sub0,.txt'
    on:change={loadFile}
>

<style>
    .loadFileInput {
        position: fixed;
        top: -100em;
    }
</style>