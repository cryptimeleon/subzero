<!-- 
    A button component to save the LaTeX text
 -->
<script context='module'>
    export function saveLatex() {
        button.click();
    }

    let button;
</script>

<script>
	import { latexText } from './stores.js';

    import SaveButton from './SaveButton.svelte';

    $: data = $latexText.trimEnd();
</script>

<SaveButton
    bind:ref={button}
    {data}
    dataName={'LaTeX text'}
    extension='tex'
/>