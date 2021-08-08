<!-- 
    A component for a popup dialog to enter a name for a file before downloading it
 -->
<script>
    import { Modal, TextInput } from 'carbon-components-svelte';
    import { onMount } from 'svelte';

    export let open;
    export let value;
    export let heading;
    export let description = '';

    let inputElement;

    onMount(() => {
        inputElement.autocomplete = 'off';
    });
</script>

<Modal
	bind:open={open}
	modalHeading={heading}
	primaryButtonText='Download'
	primaryButtonDisabled={value === ''}
    shouldSubmitOnEnter={value !== ''}
	on:open={() => {value = ''; inputElement.focus()}}
	on:close={() => {open = false}}
	on:submit={() => {open = false}}
    on:open
    on:close
    on:submit
>
    <p>{description}</p>
    <br/>
	<TextInput
        bind:value={value}
        bind:ref={inputElement}
        type='string'
        hideLabel
        labelText='File name'
        placeholder='Enter a name for the file'
    />
</Modal>