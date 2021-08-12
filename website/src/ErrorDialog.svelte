<!-- 
    A component for displaying popup error messages
 -->
<script>
    import { Modal } from 'carbon-components-svelte';

    export let open;
    export let message;
</script>

<Modal
    bind:open
    passiveModal
    modalHeading='Error'
    on:open
    on:close
>
    <p>{message}</p>
</Modal>