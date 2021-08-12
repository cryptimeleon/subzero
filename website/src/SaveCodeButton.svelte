<!-- 
    A button component to save the Subzero code
 -->
<script context='module'>
    export function saveCode() {
        button.click();
    }

    let button;
</script>

<script>
	import { DSL_EXTENSION, editorCode } from './stores.js';

    import SaveButton from './SaveButton.svelte';
</script>

<SaveButton
    bind:ref={button}
    data={$editorCode}
    dataName={'Subzero code'}
    extension={$DSL_EXTENSION}
/>