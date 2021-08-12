<script>
    import { onMount } from 'svelte';

    import { ace } from './stores.js';

    export let javaClass;
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

    onMount(() => {
        mounted = true;
    });

    $: {
        if (mounted && $ace) {
            createEditor($ace);
        }
    }

    // Creates the code editor and sets up features
    function createEditor(ace) {
        // Creates the editor and adds it to the DOM
        editor = ace.edit(editorContainer, {
            mode: 'ace/mode/java',
            theme: theme
        });

        editor.setReadOnly(true);

        // Enable word wrapping. Must be set separately (for some reason)
        editor.setOptions({wrap: true});

        editor.setFontSize(defaultFontSize);
        
        editor.session.setOptions({
            tabSize: tabSize,
            useSoftTabs: true
        });

        editor.setValue(javaClass);
    }
</script>

<div
    bind:this={editorContainer}
    class='editor'
>
</div>

<style>
    .editor {
        width: 100%;
        height: 100%;
    }
</style>