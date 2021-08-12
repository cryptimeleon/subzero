<script>
    import { createEventDispatcher, onDestroy } from 'svelte';
    import { Tab } from 'carbon-components-svelte';
    import Close32 from 'carbon-icons-svelte/lib/Close32';

    import { javaClasses } from './stores.js';

    const dispatch = createEventDispatcher();

    export let className;

    let tab;
    let hovered = false;

    function deleteTab() {
        $javaClasses = delete $javaClasses[className] && $javaClasses;
    }

    onDestroy(() => {
        if (tab.ariaSelected === 'true') {
            dispatch('selectedDelete');
        }
    });

</script>

<div class='editor-tab-container'>
    <Tab label={className} bind:ref={tab}>
        <div class='editor-tab-name'>
            {className}
        </div>
        <div
            class='editor-tab-delete'
            on:click={deleteTab}
            on:mouseenter={() => {hovered = true}}
            on:mouseleave={() => {hovered = false}}
        >
            <Close32 class={hovered ? 'delete-icon-hovered' : 'delete-icon'}/>
        </div>
    </Tab>
</div>

<style>
    .editor-tab-container :global(.bx--tabs__nav-link) {
        display: flex;
        padding-right: 0;
    }
    .editor-tab-name {
        flex-shrink: 1;
        flex-grow: 0;
        width: 100%;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .editor-tab-delete {
        flex-shrink: 0;
        flex-grow: 1;
        width: 2rem;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .editor-tab-delete :global(.delete-icon) {
        float: right;
        z-index: 0;
        color:gray;
    }

    .editor-tab-delete :global(.delete-icon-hovered) {
        float: right;
        z-index: 0;
    }
</style>