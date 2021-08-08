<script>
    import { Modal } from 'carbon-components-svelte';

    import { editorCodeHasChanged, xtextEditor } from './stores.js';
    import protocols from './protocols.js';
    
    import Dropdown from './Dropdown.svelte';

    const protocolNames = Object.keys(protocols).map((protocolName, index) => {
        return {id: index, text: protocolName};
    });

    let protocol;
    let exampleIndex = -1;
    let openDialog = false;

    $: exampleIndex, selectProtocol();

    function selectProtocol() {
        if (exampleIndex !== -1) {
            protocol = protocols[protocolNames[exampleIndex].text];
            if ($editorCodeHasChanged) {
                openDialog = true;
            } else {
                loadProtocol();
            }
        }
    }

    function resetSelect() {
        exampleIndex = -1;
    }

    function loadProtocol() {
        $xtextEditor.setValue(protocol.trim());
        $editorCodeHasChanged = false;
        openDialog = false;
    }
</script>

<Dropdown
    bind:selectedIndex={exampleIndex}
    hideLabel
    label='Load an example protocol'
    items={protocolNames}
/>

<Modal
    bind:open={openDialog}
    danger
    shouldSubmitOnEnter
    modalHeading='Overwrite editor text'
    primaryButtonText='Overwrite'
    secondaryButtonText='Cancel'
    on:click:button--secondary={() => {openDialog = false}}
    on:close={resetSelect}
    on:submit={loadProtocol}
    on:open
>
    <p>Loading an example protocol will overwrite the current editor text. Are you sure?</p>
</Modal>