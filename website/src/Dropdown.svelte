<!-- 
    A wrapper for the carbon-svelte Dropdown component to fix a bug
    where the dropdown arrow appears too low
 -->
<script>
    import { Dropdown } from 'carbon-components-svelte';
    import { onMount } from 'svelte';

    export let selectedIndex;
    export let items;
    export let light = false;
    export let titleText = '';
    export let hideLabel = false;
    export let label = '';

    let dropdownContainer;

    onMount(() => {
        // Switching the order of the dropdown box element and the arrow icon 
        // element fixes the dropdown arrow issue
        const parentNode = dropdownContainer.lastElementChild.lastElementChild.lastElementChild;
        parentNode.insertBefore(parentNode.lastElementChild, parentNode.firstElementChild);
    });
</script>

<div class='dropdown-container' bind:this={dropdownContainer} >
    <Dropdown
        bind:selectedIndex={selectedIndex}
        titleText={titleText}
        light={light}
        hideLabel={hideLabel}
        label={label}
        items={items}
    />
</div>

<style>
    .dropdown-container {
        width: 100%;
    }
</style>